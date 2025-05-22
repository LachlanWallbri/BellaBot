package org.jboss.netty.handler.codec.http.multipart;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpChunk;
import org.jboss.netty.handler.codec.http.HttpConstants;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import org.jboss.netty.util.internal.CaseIgnoringComparator;

/* loaded from: classes7.dex */
public class HttpPostRequestDecoder {
    private final List<InterfaceHttpData> bodyListHttpData;
    private int bodyListHttpDataRank;
    private final Map<String, List<InterfaceHttpData>> bodyMapHttpData;
    private boolean bodyToDecode;
    private final Charset charset;
    private Attribute currentAttribute;
    private Map<String, Attribute> currentFieldAttributes;
    private FileUpload currentFileUpload;
    private MultiPartStatus currentStatus;
    private final HttpDataFactory factory;
    private boolean isLastChunk;
    private boolean isMultipart;
    private String multipartDataBoundary;
    private String multipartMixedBoundary;
    private final HttpRequest request;
    private ChannelBuffer undecodedChunk;

    /* loaded from: classes7.dex */
    public static class EndOfDataDecoderException extends Exception {
        private static final long serialVersionUID = 1336267941020800769L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public enum MultiPartStatus {
        NOTSTARTED,
        PREAMBLE,
        HEADERDELIMITER,
        DISPOSITION,
        FIELD,
        FILEUPLOAD,
        MIXEDPREAMBLE,
        MIXEDDELIMITER,
        MIXEDDISPOSITION,
        MIXEDFILEUPLOAD,
        MIXEDCLOSEDELIMITER,
        CLOSEDELIMITER,
        PREEPILOGUE,
        EPILOGUE
    }

    public HttpPostRequestDecoder(HttpRequest httpRequest) throws ErrorDataDecoderException, IncompatibleDataDecoderException {
        this(new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE), httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest) throws ErrorDataDecoderException, IncompatibleDataDecoderException {
        this(httpDataFactory, httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, Charset charset) throws ErrorDataDecoderException, IncompatibleDataDecoderException {
        this.bodyListHttpData = new ArrayList();
        this.bodyMapHttpData = new TreeMap(CaseIgnoringComparator.INSTANCE);
        this.currentStatus = MultiPartStatus.NOTSTARTED;
        if (httpDataFactory == null) {
            throw new NullPointerException("factory");
        }
        if (httpRequest == null) {
            throw new NullPointerException("request");
        }
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.request = httpRequest;
        HttpMethod method = httpRequest.getMethod();
        if (method.equals(HttpMethod.POST) || method.equals(HttpMethod.PUT) || method.equals(HttpMethod.PATCH)) {
            this.bodyToDecode = true;
        }
        this.charset = charset;
        this.factory = httpDataFactory;
        if (this.request.containsHeader("Content-Type")) {
            checkMultipart(this.request.getHeader("Content-Type"));
        } else {
            this.isMultipart = false;
        }
        if (!this.bodyToDecode) {
            throw new IncompatibleDataDecoderException("No Body to decode");
        }
        if (this.request.isChunked()) {
            return;
        }
        this.undecodedChunk = this.request.getContent();
        this.isLastChunk = true;
        parseBody();
    }

    private void checkMultipart(String str) throws ErrorDataDecoderException {
        String[] splitHeaderContentType = splitHeaderContentType(str);
        if (splitHeaderContentType[0].toLowerCase().startsWith("multipart/form-data") && splitHeaderContentType[1].toLowerCase().startsWith("boundary")) {
            String[] split = splitHeaderContentType[1].split("=");
            if (split.length != 2) {
                throw new ErrorDataDecoderException("Needs a boundary value");
            }
            this.multipartDataBoundary = "--" + split[1];
            this.isMultipart = true;
            this.currentStatus = MultiPartStatus.HEADERDELIMITER;
            return;
        }
        this.isMultipart = false;
    }

    public boolean isMultipart() {
        return this.isMultipart;
    }

    public List<InterfaceHttpData> getBodyHttpDatas() throws NotEnoughDataDecoderException {
        if (!this.isLastChunk) {
            throw new NotEnoughDataDecoderException();
        }
        return this.bodyListHttpData;
    }

    public List<InterfaceHttpData> getBodyHttpDatas(String str) throws NotEnoughDataDecoderException {
        if (!this.isLastChunk) {
            throw new NotEnoughDataDecoderException();
        }
        return this.bodyMapHttpData.get(str);
    }

    public InterfaceHttpData getBodyHttpData(String str) throws NotEnoughDataDecoderException {
        if (!this.isLastChunk) {
            throw new NotEnoughDataDecoderException();
        }
        List<InterfaceHttpData> list = this.bodyMapHttpData.get(str);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    public void offer(HttpChunk httpChunk) throws ErrorDataDecoderException {
        ChannelBuffer content = httpChunk.getContent();
        ChannelBuffer channelBuffer = this.undecodedChunk;
        if (channelBuffer == null) {
            this.undecodedChunk = content;
        } else {
            this.undecodedChunk = ChannelBuffers.wrappedBuffer(channelBuffer, content);
        }
        if (httpChunk.isLast()) {
            this.isLastChunk = true;
        }
        parseBody();
    }

    public boolean hasNext() throws EndOfDataDecoderException {
        if (this.currentStatus != MultiPartStatus.EPILOGUE || this.bodyListHttpDataRank < this.bodyListHttpData.size()) {
            return this.bodyListHttpData.size() > 0 && this.bodyListHttpDataRank < this.bodyListHttpData.size();
        }
        throw new EndOfDataDecoderException();
    }

    public InterfaceHttpData next() throws EndOfDataDecoderException {
        if (!hasNext()) {
            return null;
        }
        List<InterfaceHttpData> list = this.bodyListHttpData;
        int i = this.bodyListHttpDataRank;
        this.bodyListHttpDataRank = i + 1;
        return list.get(i);
    }

    private void parseBody() throws ErrorDataDecoderException {
        if (this.currentStatus == MultiPartStatus.PREEPILOGUE || this.currentStatus == MultiPartStatus.EPILOGUE) {
            if (this.isLastChunk) {
                this.currentStatus = MultiPartStatus.EPILOGUE;
            }
        } else if (this.isMultipart) {
            parseBodyMultipart();
        } else {
            parseBodyAttributes();
        }
    }

    private void addHttpData(InterfaceHttpData interfaceHttpData) {
        if (interfaceHttpData == null) {
            return;
        }
        List<InterfaceHttpData> list = this.bodyMapHttpData.get(interfaceHttpData.getName());
        if (list == null) {
            list = new ArrayList<>(1);
            this.bodyMapHttpData.put(interfaceHttpData.getName(), list);
        }
        list.add(interfaceHttpData);
        this.bodyListHttpData.add(interfaceHttpData);
    }

    private void parseBodyAttributesStandard() throws ErrorDataDecoderException {
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == MultiPartStatus.NOTSTARTED) {
            this.currentStatus = MultiPartStatus.DISPOSITION;
        }
        int i = readerIndex;
        int i2 = i;
        boolean z = true;
        while (this.undecodedChunk.readable() && z) {
            try {
                char readUnsignedByte = (char) this.undecodedChunk.readUnsignedByte();
                i++;
                int i3 = C87111.f10035x86fdd994[this.currentStatus.ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        if (readUnsignedByte == '&') {
                            this.currentStatus = MultiPartStatus.DISPOSITION;
                            setFinalBuffer(this.undecodedChunk.slice(i2, (i - 1) - i2));
                            z = true;
                        } else if (readUnsignedByte == '\r') {
                            if (this.undecodedChunk.readable()) {
                                i++;
                                if (((char) this.undecodedChunk.readUnsignedByte()) == '\n') {
                                    this.currentStatus = MultiPartStatus.PREEPILOGUE;
                                    setFinalBuffer(this.undecodedChunk.slice(i2, (i - 2) - i2));
                                    i2 = i;
                                } else {
                                    throw new ErrorDataDecoderException("Bad end of line");
                                }
                            } else {
                                i--;
                            }
                        } else if (readUnsignedByte == '\n') {
                            this.currentStatus = MultiPartStatus.PREEPILOGUE;
                            setFinalBuffer(this.undecodedChunk.slice(i2, (i - 1) - i2));
                            i2 = i;
                        }
                    }
                    z = false;
                } else if (readUnsignedByte == '=') {
                    this.currentStatus = MultiPartStatus.FIELD;
                    this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i2, (i - 1) - i2, this.charset), this.charset));
                } else if (readUnsignedByte == '&') {
                    this.currentStatus = MultiPartStatus.DISPOSITION;
                    this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i2, (i - 1) - i2, this.charset), this.charset));
                    this.currentAttribute.setValue("");
                    addHttpData(this.currentAttribute);
                    this.currentAttribute = null;
                    z = true;
                }
                i2 = i;
            } catch (IOException e) {
                e = e;
            } catch (ErrorDataDecoderException e2) {
                e = e2;
            }
        }
        try {
            if (this.isLastChunk && this.currentAttribute != null) {
                if (i > i2) {
                    setFinalBuffer(this.undecodedChunk.slice(i2, i - i2));
                } else if (!this.currentAttribute.isCompleted()) {
                    setFinalBuffer(ChannelBuffers.EMPTY_BUFFER);
                }
                this.currentStatus = MultiPartStatus.EPILOGUE;
                return;
            }
            if (!z || this.currentAttribute == null) {
                return;
            }
            if (this.currentStatus == MultiPartStatus.FIELD) {
                this.currentAttribute.addContent(this.undecodedChunk.slice(i2, i - i2), false);
            } else {
                i = i2;
            }
            this.undecodedChunk.readerIndex(i);
        } catch (IOException e3) {
            e = e3;
            i2 = i;
            this.undecodedChunk.readerIndex(i2);
            throw new ErrorDataDecoderException(e);
        } catch (ErrorDataDecoderException e4) {
            e = e4;
            i2 = i;
            this.undecodedChunk.readerIndex(i2);
            throw e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b2, code lost:
    
        r2 = r1;
        r6 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseBodyAttributes() throws ErrorDataDecoderException {
        int i;
        boolean z;
        int i2;
        try {
            HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
            int readerIndex = this.undecodedChunk.readerIndex();
            if (this.currentStatus == MultiPartStatus.NOTSTARTED) {
                this.currentStatus = MultiPartStatus.DISPOSITION;
            }
            try {
                loop0: while (true) {
                    i = readerIndex;
                    while (true) {
                        try {
                            z = true;
                            if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                                break loop0;
                            }
                            byte[] bArr = seekAheadOptimize.bytes;
                            int i3 = seekAheadOptimize.pos;
                            seekAheadOptimize.pos = i3 + 1;
                            char c = (char) (bArr[i3] & 255);
                            readerIndex++;
                            int i4 = C87111.f10035x86fdd994[this.currentStatus.ordinal()];
                            if (i4 != 1) {
                                if (i4 != 2) {
                                    seekAheadOptimize.setReadPosition(0);
                                    z = false;
                                    break loop0;
                                }
                                if (c == '&') {
                                    this.currentStatus = MultiPartStatus.DISPOSITION;
                                    setFinalBuffer(this.undecodedChunk.slice(i, (readerIndex - 1) - i));
                                    break;
                                }
                                if (c == '\r') {
                                    if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                        byte[] bArr2 = seekAheadOptimize.bytes;
                                        int i5 = seekAheadOptimize.pos;
                                        seekAheadOptimize.pos = i5 + 1;
                                        readerIndex++;
                                        if (((char) (bArr2[i5] & 255)) == '\n') {
                                            this.currentStatus = MultiPartStatus.PREEPILOGUE;
                                            seekAheadOptimize.setReadPosition(0);
                                            setFinalBuffer(this.undecodedChunk.slice(i, (readerIndex - 2) - i));
                                        } else {
                                            seekAheadOptimize.setReadPosition(0);
                                            throw new ErrorDataDecoderException("Bad end of line");
                                        }
                                    } else if (seekAheadOptimize.limit > 0) {
                                        readerIndex--;
                                    }
                                } else if (c == '\n') {
                                    this.currentStatus = MultiPartStatus.PREEPILOGUE;
                                    seekAheadOptimize.setReadPosition(0);
                                    setFinalBuffer(this.undecodedChunk.slice(i, (readerIndex - 1) - i));
                                    break loop0;
                                }
                                if (!this.isLastChunk && this.currentAttribute != null) {
                                    if (i2 > readerIndex) {
                                        setFinalBuffer(this.undecodedChunk.slice(readerIndex, i2 - readerIndex));
                                    } else if (!this.currentAttribute.isCompleted()) {
                                        setFinalBuffer(ChannelBuffers.EMPTY_BUFFER);
                                    }
                                    this.currentStatus = MultiPartStatus.EPILOGUE;
                                    return;
                                }
                                if (z || this.currentAttribute == null) {
                                    return;
                                }
                                if (this.currentStatus == MultiPartStatus.FIELD) {
                                    this.currentAttribute.addContent(this.undecodedChunk.slice(readerIndex, i2 - readerIndex), false);
                                } else {
                                    i2 = readerIndex;
                                }
                                this.undecodedChunk.readerIndex(i2);
                                return;
                            }
                            if (c == '=') {
                                this.currentStatus = MultiPartStatus.FIELD;
                                this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                                break;
                            } else if (c == '&') {
                                this.currentStatus = MultiPartStatus.DISPOSITION;
                                this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                                this.currentAttribute.setValue("");
                                addHttpData(this.currentAttribute);
                                this.currentAttribute = null;
                                break;
                            }
                        } catch (IOException e) {
                            e = e;
                        } catch (ErrorDataDecoderException e2) {
                            e = e2;
                        }
                    }
                }
                if (!this.isLastChunk) {
                }
                if (z) {
                    return;
                } else {
                    return;
                }
            } catch (IOException e3) {
                e = e3;
                i = readerIndex;
                this.undecodedChunk.readerIndex(i);
                throw new ErrorDataDecoderException(e);
            } catch (ErrorDataDecoderException e4) {
                e = e4;
                i = readerIndex;
                this.undecodedChunk.readerIndex(i);
                throw e;
            }
            i2 = readerIndex;
            readerIndex = i;
        } catch (HttpPostBodyUtil.SeekAheadNoBackArrayException unused) {
            parseBodyAttributesStandard();
        }
    }

    private void setFinalBuffer(ChannelBuffer channelBuffer) throws ErrorDataDecoderException, IOException {
        this.currentAttribute.addContent(channelBuffer, true);
        this.currentAttribute.setValue(decodeAttribute(this.currentAttribute.getChannelBuffer().toString(this.charset), this.charset));
        addHttpData(this.currentAttribute);
        this.currentAttribute = null;
    }

    private static String decodeAttribute(String str, Charset charset) throws ErrorDataDecoderException {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, charset.name());
        } catch (UnsupportedEncodingException e) {
            throw new ErrorDataDecoderException(charset.toString(), e);
        }
    }

    private void parseBodyMultipart() throws ErrorDataDecoderException {
        ChannelBuffer channelBuffer = this.undecodedChunk;
        if (channelBuffer == null || channelBuffer.readableBytes() == 0) {
            return;
        }
        InterfaceHttpData decodeMultipart = decodeMultipart(this.currentStatus);
        while (decodeMultipart != null) {
            addHttpData(decodeMultipart);
            if (this.currentStatus == MultiPartStatus.PREEPILOGUE || this.currentStatus == MultiPartStatus.EPILOGUE) {
                return;
            } else {
                decodeMultipart = decodeMultipart(this.currentStatus);
            }
        }
    }

    private InterfaceHttpData decodeMultipart(MultiPartStatus multiPartStatus) throws ErrorDataDecoderException {
        Charset forName;
        switch (multiPartStatus) {
            case DISPOSITION:
                return findMultipartDisposition();
            case FIELD:
                Attribute attribute = this.currentFieldAttributes.get("charset");
                if (attribute != null) {
                    try {
                        forName = Charset.forName(attribute.getValue());
                    } catch (IOException e) {
                        throw new ErrorDataDecoderException(e);
                    }
                } else {
                    forName = null;
                }
                Attribute attribute2 = this.currentFieldAttributes.get("name");
                if (this.currentAttribute == null) {
                    try {
                        this.currentAttribute = this.factory.createAttribute(this.request, attribute2.getValue());
                        if (forName != null) {
                            this.currentAttribute.setCharset(forName);
                        }
                    } catch (IOException e2) {
                        throw new ErrorDataDecoderException(e2);
                    } catch (IllegalArgumentException e3) {
                        throw new ErrorDataDecoderException(e3);
                    } catch (NullPointerException e4) {
                        throw new ErrorDataDecoderException(e4);
                    }
                }
                try {
                    loadFieldMultipart(this.multipartDataBoundary);
                    Attribute attribute3 = this.currentAttribute;
                    this.currentAttribute = null;
                    this.currentFieldAttributes = null;
                    this.currentStatus = MultiPartStatus.HEADERDELIMITER;
                    return attribute3;
                } catch (NotEnoughDataDecoderException unused) {
                    return null;
                }
            case NOTSTARTED:
                throw new ErrorDataDecoderException("Should not be called with the current status");
            case PREAMBLE:
                throw new ErrorDataDecoderException("Should not be called with the current status");
            case HEADERDELIMITER:
                return findMultipartDelimiter(this.multipartDataBoundary, MultiPartStatus.DISPOSITION, MultiPartStatus.PREEPILOGUE);
            case FILEUPLOAD:
                return getFileUpload(this.multipartDataBoundary);
            case MIXEDDELIMITER:
                return findMultipartDelimiter(this.multipartMixedBoundary, MultiPartStatus.MIXEDDISPOSITION, MultiPartStatus.HEADERDELIMITER);
            case MIXEDDISPOSITION:
                return findMultipartDisposition();
            case MIXEDFILEUPLOAD:
                return getFileUpload(this.multipartMixedBoundary);
            case PREEPILOGUE:
            case EPILOGUE:
                return null;
            default:
                throw new ErrorDataDecoderException("Shouldn't reach here.");
        }
    }

    void skipControlCharacters() {
        try {
            HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
            while (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                byte[] bArr = seekAheadOptimize.bytes;
                int i = seekAheadOptimize.pos;
                seekAheadOptimize.pos = i + 1;
                char c = (char) (bArr[i] & 255);
                if (!Character.isISOControl(c) && !Character.isWhitespace(c)) {
                    seekAheadOptimize.setReadPosition(1);
                    return;
                }
            }
            seekAheadOptimize.setReadPosition(0);
        } catch (HttpPostBodyUtil.SeekAheadNoBackArrayException unused) {
            skipControlCharactersStandard(this.undecodedChunk);
        }
    }

    static void skipControlCharactersStandard(ChannelBuffer channelBuffer) {
        while (true) {
            char readUnsignedByte = (char) channelBuffer.readUnsignedByte();
            if (!Character.isISOControl(readUnsignedByte) && !Character.isWhitespace(readUnsignedByte)) {
                channelBuffer.readerIndex(channelBuffer.readerIndex() - 1);
                return;
            }
        }
    }

    private InterfaceHttpData findMultipartDelimiter(String str, MultiPartStatus multiPartStatus, MultiPartStatus multiPartStatus2) throws ErrorDataDecoderException {
        int readerIndex = this.undecodedChunk.readerIndex();
        skipControlCharacters();
        skipOneLine();
        try {
            String readDelimiter = readDelimiter(str);
            if (readDelimiter.equals(str)) {
                this.currentStatus = multiPartStatus;
                return decodeMultipart(multiPartStatus);
            }
            if (readDelimiter.equals(str + "--")) {
                this.currentStatus = multiPartStatus2;
                if (this.currentStatus != MultiPartStatus.HEADERDELIMITER) {
                    return null;
                }
                this.currentFieldAttributes = null;
                return decodeMultipart(MultiPartStatus.HEADERDELIMITER);
            }
            this.undecodedChunk.readerIndex(readerIndex);
            throw new ErrorDataDecoderException("No Multipart delimiter found");
        } catch (NotEnoughDataDecoderException unused) {
            this.undecodedChunk.readerIndex(readerIndex);
            return null;
        }
    }

    private InterfaceHttpData findMultipartDisposition() throws ErrorDataDecoderException {
        boolean z;
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == MultiPartStatus.DISPOSITION) {
            this.currentFieldAttributes = new TreeMap(CaseIgnoringComparator.INSTANCE);
        }
        while (!skipOneLine()) {
            skipControlCharacters();
            try {
                String readLine = readLine();
                String[] splitMultipartHeader = splitMultipartHeader(readLine);
                if (splitMultipartHeader[0].equalsIgnoreCase("Content-Disposition")) {
                    if (this.currentStatus == MultiPartStatus.DISPOSITION) {
                        z = splitMultipartHeader[1].equalsIgnoreCase(HttpPostBodyUtil.FORM_DATA);
                    } else {
                        z = splitMultipartHeader[1].equalsIgnoreCase(HttpPostBodyUtil.ATTACHMENT) || splitMultipartHeader[1].equalsIgnoreCase("file");
                    }
                    if (z) {
                        for (int i = 2; i < splitMultipartHeader.length; i++) {
                            String[] split = splitMultipartHeader[i].split("=");
                            try {
                                Attribute createAttribute = this.factory.createAttribute(this.request, split[0].trim(), decodeAttribute(cleanString(split[1]), this.charset));
                                this.currentFieldAttributes.put(createAttribute.getName(), createAttribute);
                            } catch (IllegalArgumentException e) {
                                throw new ErrorDataDecoderException(e);
                            } catch (NullPointerException e2) {
                                throw new ErrorDataDecoderException(e2);
                            }
                        }
                    } else {
                        continue;
                    }
                } else if (splitMultipartHeader[0].equalsIgnoreCase("Content-Transfer-Encoding")) {
                    try {
                        this.currentFieldAttributes.put("Content-Transfer-Encoding", this.factory.createAttribute(this.request, "Content-Transfer-Encoding", cleanString(splitMultipartHeader[1])));
                    } catch (IllegalArgumentException e3) {
                        throw new ErrorDataDecoderException(e3);
                    } catch (NullPointerException e4) {
                        throw new ErrorDataDecoderException(e4);
                    }
                } else if (splitMultipartHeader[0].equalsIgnoreCase("Content-Length")) {
                    try {
                        this.currentFieldAttributes.put("Content-Length", this.factory.createAttribute(this.request, "Content-Length", cleanString(splitMultipartHeader[1])));
                    } catch (IllegalArgumentException e5) {
                        throw new ErrorDataDecoderException(e5);
                    } catch (NullPointerException e6) {
                        throw new ErrorDataDecoderException(e6);
                    }
                } else if (splitMultipartHeader[0].equalsIgnoreCase("Content-Type")) {
                    if (splitMultipartHeader[1].equalsIgnoreCase(HttpPostBodyUtil.MULTIPART_MIXED)) {
                        if (this.currentStatus == MultiPartStatus.DISPOSITION) {
                            this.multipartMixedBoundary = "--" + splitMultipartHeader[2].split("=")[1];
                            this.currentStatus = MultiPartStatus.MIXEDDELIMITER;
                            return decodeMultipart(MultiPartStatus.MIXEDDELIMITER);
                        }
                        throw new ErrorDataDecoderException("Mixed Multipart found in a previous Mixed Multipart");
                    }
                    for (int i2 = 1; i2 < splitMultipartHeader.length; i2++) {
                        if (splitMultipartHeader[i2].toLowerCase().startsWith("charset")) {
                            try {
                                this.currentFieldAttributes.put("charset", this.factory.createAttribute(this.request, "charset", cleanString(splitMultipartHeader[i2].split("=")[1])));
                            } catch (IllegalArgumentException e7) {
                                throw new ErrorDataDecoderException(e7);
                            } catch (NullPointerException e8) {
                                throw new ErrorDataDecoderException(e8);
                            }
                        } else {
                            try {
                                Attribute createAttribute2 = this.factory.createAttribute(this.request, splitMultipartHeader[0].trim(), decodeAttribute(cleanString(splitMultipartHeader[i2]), this.charset));
                                this.currentFieldAttributes.put(createAttribute2.getName(), createAttribute2);
                            } catch (IllegalArgumentException e9) {
                                throw new ErrorDataDecoderException(e9);
                            } catch (NullPointerException e10) {
                                throw new ErrorDataDecoderException(e10);
                            }
                        }
                    }
                } else {
                    throw new ErrorDataDecoderException("Unknown Params: " + readLine);
                }
            } catch (NotEnoughDataDecoderException unused) {
                this.undecodedChunk.readerIndex(readerIndex);
                return null;
            }
        }
        Attribute attribute = this.currentFieldAttributes.get("filename");
        if (this.currentStatus != MultiPartStatus.DISPOSITION) {
            if (attribute != null) {
                this.currentStatus = MultiPartStatus.MIXEDFILEUPLOAD;
                return decodeMultipart(MultiPartStatus.MIXEDFILEUPLOAD);
            }
            throw new ErrorDataDecoderException("Filename not found");
        }
        if (attribute != null) {
            this.currentStatus = MultiPartStatus.FILEUPLOAD;
            return decodeMultipart(MultiPartStatus.FILEUPLOAD);
        }
        this.currentStatus = MultiPartStatus.FIELD;
        return decodeMultipart(MultiPartStatus.FIELD);
    }

    private InterfaceHttpData getFileUpload(String str) throws ErrorDataDecoderException {
        Attribute attribute = this.currentFieldAttributes.get("Content-Transfer-Encoding");
        Charset charset = this.charset;
        HttpPostBodyUtil.TransferEncodingMechanism transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT7;
        if (attribute != null) {
            try {
                String lowerCase = attribute.getValue().toLowerCase();
                if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT7.value)) {
                    charset = HttpPostBodyUtil.US_ASCII;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT8.value)) {
                    charset = HttpPostBodyUtil.ISO_8859_1;
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT8;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value)) {
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BINARY;
                } else {
                    throw new ErrorDataDecoderException("TransferEncoding Unknown: " + lowerCase);
                }
            } catch (IOException e) {
                throw new ErrorDataDecoderException(e);
            }
        }
        Attribute attribute2 = this.currentFieldAttributes.get("charset");
        if (attribute2 != null) {
            try {
                charset = Charset.forName(attribute2.getValue());
            } catch (IOException e2) {
                throw new ErrorDataDecoderException(e2);
            }
        }
        Charset charset2 = charset;
        if (this.currentFileUpload == null) {
            Attribute attribute3 = this.currentFieldAttributes.get("filename");
            Attribute attribute4 = this.currentFieldAttributes.get("name");
            Attribute attribute5 = this.currentFieldAttributes.get("Content-Type");
            if (attribute5 == null) {
                throw new ErrorDataDecoderException("Content-Type is absent but required");
            }
            Attribute attribute6 = this.currentFieldAttributes.get("Content-Length");
            long j = 0;
            if (attribute6 != null) {
                try {
                    j = Long.parseLong(attribute6.getValue());
                } catch (IOException e3) {
                    throw new ErrorDataDecoderException(e3);
                } catch (NumberFormatException unused) {
                }
            }
            try {
                this.currentFileUpload = this.factory.createFileUpload(this.request, attribute4.getValue(), attribute3.getValue(), attribute5.getValue(), transferEncodingMechanism.value, charset2, j);
            } catch (IOException e4) {
                throw new ErrorDataDecoderException(e4);
            } catch (IllegalArgumentException e5) {
                throw new ErrorDataDecoderException(e5);
            } catch (NullPointerException e6) {
                throw new ErrorDataDecoderException(e6);
            }
        }
        try {
            readFileUploadByteMultipart(str);
            if (this.currentFileUpload.isCompleted()) {
                if (this.currentStatus == MultiPartStatus.FILEUPLOAD) {
                    this.currentStatus = MultiPartStatus.HEADERDELIMITER;
                    this.currentFieldAttributes = null;
                } else {
                    this.currentStatus = MultiPartStatus.MIXEDDELIMITER;
                    cleanMixedAttributes();
                }
                FileUpload fileUpload = this.currentFileUpload;
                this.currentFileUpload = null;
                return fileUpload;
            }
        } catch (NotEnoughDataDecoderException unused2) {
        }
        return null;
    }

    public void cleanFiles() {
        this.factory.cleanRequestHttpDatas(this.request);
    }

    public void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData) {
        this.factory.removeHttpDataFromClean(this.request, interfaceHttpData);
    }

    private void cleanMixedAttributes() {
        this.currentFieldAttributes.remove("charset");
        this.currentFieldAttributes.remove("Content-Length");
        this.currentFieldAttributes.remove("Content-Transfer-Encoding");
        this.currentFieldAttributes.remove("Content-Type");
        this.currentFieldAttributes.remove("filename");
    }

    private String readLineStandard() throws NotEnoughDataDecoderException {
        int readerIndex = this.undecodedChunk.readerIndex();
        try {
            StringBuilder sb = new StringBuilder(64);
            while (this.undecodedChunk.readable()) {
                byte readByte = this.undecodedChunk.readByte();
                if (readByte == 13) {
                    if (this.undecodedChunk.readByte() == 10) {
                        return sb.toString();
                    }
                } else {
                    if (readByte == 10) {
                        return sb.toString();
                    }
                    sb.append((char) readByte);
                }
            }
            this.undecodedChunk.readerIndex(readerIndex);
            throw new NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            this.undecodedChunk.readerIndex(readerIndex);
            throw new NotEnoughDataDecoderException(e);
        }
    }

    private String readLine() throws NotEnoughDataDecoderException {
        try {
            HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
            int readerIndex = this.undecodedChunk.readerIndex();
            try {
                StringBuilder sb = new StringBuilder(64);
                while (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                    byte[] bArr = seekAheadOptimize.bytes;
                    int i = seekAheadOptimize.pos;
                    seekAheadOptimize.pos = i + 1;
                    byte b = bArr[i];
                    if (b == 13) {
                        if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                            byte[] bArr2 = seekAheadOptimize.bytes;
                            int i2 = seekAheadOptimize.pos;
                            seekAheadOptimize.pos = i2 + 1;
                            if (bArr2[i2] == 10) {
                                seekAheadOptimize.setReadPosition(0);
                                return sb.toString();
                            }
                        } else {
                            sb.append((char) b);
                        }
                    } else {
                        if (b == 10) {
                            seekAheadOptimize.setReadPosition(0);
                            return sb.toString();
                        }
                        sb.append((char) b);
                    }
                }
                this.undecodedChunk.readerIndex(readerIndex);
                throw new NotEnoughDataDecoderException();
            } catch (IndexOutOfBoundsException e) {
                this.undecodedChunk.readerIndex(readerIndex);
                throw new NotEnoughDataDecoderException(e);
            }
        } catch (HttpPostBodyUtil.SeekAheadNoBackArrayException unused) {
            return readLineStandard();
        }
    }

    private String readDelimiterStandard(String str) throws NotEnoughDataDecoderException {
        int readerIndex = this.undecodedChunk.readerIndex();
        try {
            StringBuilder sb = new StringBuilder(64);
            int i = 0;
            int length = str.length();
            while (this.undecodedChunk.readable() && i < length) {
                byte readByte = this.undecodedChunk.readByte();
                if (readByte == str.charAt(i)) {
                    i++;
                    sb.append((char) readByte);
                } else {
                    this.undecodedChunk.readerIndex(readerIndex);
                    throw new NotEnoughDataDecoderException();
                }
            }
            if (this.undecodedChunk.readable()) {
                byte readByte2 = this.undecodedChunk.readByte();
                if (readByte2 == 13) {
                    if (this.undecodedChunk.readByte() == 10) {
                        return sb.toString();
                    }
                    this.undecodedChunk.readerIndex(readerIndex);
                    throw new NotEnoughDataDecoderException();
                }
                if (readByte2 == 10) {
                    return sb.toString();
                }
                if (readByte2 == 45) {
                    sb.append((char) readByte2);
                    byte readByte3 = this.undecodedChunk.readByte();
                    if (readByte3 == 45) {
                        sb.append((char) readByte3);
                        if (this.undecodedChunk.readable()) {
                            byte readByte4 = this.undecodedChunk.readByte();
                            if (readByte4 == 13) {
                                if (this.undecodedChunk.readByte() == 10) {
                                    return sb.toString();
                                }
                                this.undecodedChunk.readerIndex(readerIndex);
                                throw new NotEnoughDataDecoderException();
                            }
                            if (readByte4 == 10) {
                                return sb.toString();
                            }
                            this.undecodedChunk.readerIndex(this.undecodedChunk.readerIndex() - 1);
                            return sb.toString();
                        }
                        return sb.toString();
                    }
                }
            }
            this.undecodedChunk.readerIndex(readerIndex);
            throw new NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            this.undecodedChunk.readerIndex(readerIndex);
            throw new NotEnoughDataDecoderException(e);
        }
    }

    private String readDelimiter(String str) throws NotEnoughDataDecoderException {
        try {
            HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
            int readerIndex = this.undecodedChunk.readerIndex();
            int length = str.length();
            try {
                StringBuilder sb = new StringBuilder(64);
                int i = 0;
                while (seekAheadOptimize.pos < seekAheadOptimize.limit && i < length) {
                    byte[] bArr = seekAheadOptimize.bytes;
                    int i2 = seekAheadOptimize.pos;
                    seekAheadOptimize.pos = i2 + 1;
                    byte b = bArr[i2];
                    if (b == str.charAt(i)) {
                        i++;
                        sb.append((char) b);
                    } else {
                        this.undecodedChunk.readerIndex(readerIndex);
                        throw new NotEnoughDataDecoderException();
                    }
                }
                if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                    byte[] bArr2 = seekAheadOptimize.bytes;
                    int i3 = seekAheadOptimize.pos;
                    seekAheadOptimize.pos = i3 + 1;
                    byte b2 = bArr2[i3];
                    if (b2 == 13) {
                        if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                            byte[] bArr3 = seekAheadOptimize.bytes;
                            int i4 = seekAheadOptimize.pos;
                            seekAheadOptimize.pos = i4 + 1;
                            if (bArr3[i4] == 10) {
                                seekAheadOptimize.setReadPosition(0);
                                return sb.toString();
                            }
                        } else {
                            this.undecodedChunk.readerIndex(readerIndex);
                            throw new NotEnoughDataDecoderException();
                        }
                    } else {
                        if (b2 == 10) {
                            seekAheadOptimize.setReadPosition(0);
                            return sb.toString();
                        }
                        if (b2 == 45) {
                            sb.append((char) b2);
                            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                byte[] bArr4 = seekAheadOptimize.bytes;
                                int i5 = seekAheadOptimize.pos;
                                seekAheadOptimize.pos = i5 + 1;
                                byte b3 = bArr4[i5];
                                if (b3 == 45) {
                                    sb.append((char) b3);
                                    if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                        byte[] bArr5 = seekAheadOptimize.bytes;
                                        int i6 = seekAheadOptimize.pos;
                                        seekAheadOptimize.pos = i6 + 1;
                                        byte b4 = bArr5[i6];
                                        if (b4 != 13) {
                                            if (b4 == 10) {
                                                seekAheadOptimize.setReadPosition(0);
                                                return sb.toString();
                                            }
                                            seekAheadOptimize.setReadPosition(1);
                                            return sb.toString();
                                        }
                                        if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                            byte[] bArr6 = seekAheadOptimize.bytes;
                                            int i7 = seekAheadOptimize.pos;
                                            seekAheadOptimize.pos = i7 + 1;
                                            if (bArr6[i7] == 10) {
                                                seekAheadOptimize.setReadPosition(0);
                                                return sb.toString();
                                            }
                                        } else {
                                            this.undecodedChunk.readerIndex(readerIndex);
                                            throw new NotEnoughDataDecoderException();
                                        }
                                    }
                                    seekAheadOptimize.setReadPosition(0);
                                    return sb.toString();
                                }
                            }
                        }
                    }
                }
                this.undecodedChunk.readerIndex(readerIndex);
                throw new NotEnoughDataDecoderException();
            } catch (IndexOutOfBoundsException e) {
                this.undecodedChunk.readerIndex(readerIndex);
                throw new NotEnoughDataDecoderException(e);
            }
        } catch (HttpPostBodyUtil.SeekAheadNoBackArrayException unused) {
            return readDelimiterStandard(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x007a, code lost:
    
        r1 = r1 - 2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void readFileUploadByteMultipartStandard(String str) throws NotEnoughDataDecoderException, ErrorDataDecoderException {
        int i;
        boolean z;
        int readerIndex;
        int readerIndex2;
        int readerIndex3 = this.undecodedChunk.readerIndex();
        int readerIndex4 = this.undecodedChunk.readerIndex();
        loop0: while (true) {
            i = readerIndex4;
            int i2 = 0;
            boolean z2 = true;
            while (true) {
                if (!this.undecodedChunk.readable()) {
                    z = false;
                    break loop0;
                }
                byte readByte = this.undecodedChunk.readByte();
                if (z2) {
                    if (readByte == str.codePointAt(i2)) {
                        i2++;
                        if (str.length() == i2) {
                            z = true;
                            break loop0;
                        }
                    } else {
                        if (readByte == 13) {
                            if (this.undecodedChunk.readable() && this.undecodedChunk.readByte() == 10) {
                                readerIndex2 = this.undecodedChunk.readerIndex();
                                break;
                            }
                        } else {
                            if (readByte == 10) {
                                readerIndex = this.undecodedChunk.readerIndex();
                                break;
                            }
                            i = this.undecodedChunk.readerIndex();
                        }
                        z2 = false;
                        i2 = 0;
                    }
                } else if (readByte == 13) {
                    if (this.undecodedChunk.readable() && this.undecodedChunk.readByte() == 10) {
                        readerIndex2 = this.undecodedChunk.readerIndex();
                        break;
                    }
                } else {
                    if (readByte == 10) {
                        readerIndex = this.undecodedChunk.readerIndex();
                        break;
                    }
                    i = this.undecodedChunk.readerIndex();
                }
            }
            readerIndex4 = readerIndex - 1;
        }
        ChannelBuffer slice = this.undecodedChunk.slice(readerIndex3, i - readerIndex3);
        if (z) {
            try {
                this.currentFileUpload.addContent(slice, true);
                this.undecodedChunk.readerIndex(i);
                return;
            } catch (IOException e) {
                throw new ErrorDataDecoderException(e);
            }
        }
        try {
            this.currentFileUpload.addContent(slice, false);
            this.undecodedChunk.readerIndex(i);
            throw new NotEnoughDataDecoderException();
        } catch (IOException e2) {
            throw new ErrorDataDecoderException(e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008a, code lost:
    
        r3 = r3 - 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0098, code lost:
    
        r3 = r3 - 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void readFileUploadByteMultipart(String str) throws NotEnoughDataDecoderException, ErrorDataDecoderException {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        try {
            HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
            int readerIndex = this.undecodedChunk.readerIndex();
            int readerIndex2 = this.undecodedChunk.readerIndex();
            int i9 = -1;
            loop0: while (true) {
                int i10 = 0;
                boolean z2 = true;
                while (true) {
                    if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                        z = false;
                        break loop0;
                    }
                    byte[] bArr = seekAheadOptimize.bytes;
                    int i11 = seekAheadOptimize.pos;
                    seekAheadOptimize.pos = i11 + 1;
                    byte b = bArr[i11];
                    if (!z2) {
                        if (b == 13) {
                            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                byte[] bArr2 = seekAheadOptimize.bytes;
                                int i12 = seekAheadOptimize.pos;
                                seekAheadOptimize.pos = i12 + 1;
                                if (bArr2[i12] == 10) {
                                    i = seekAheadOptimize.pos;
                                    i5 = seekAheadOptimize.pos;
                                    break;
                                }
                            } else {
                                i7 = seekAheadOptimize.pos;
                                i8 = seekAheadOptimize.pos;
                            }
                        } else if (b == 10) {
                            i = seekAheadOptimize.pos;
                            i2 = seekAheadOptimize.pos;
                            break;
                        } else {
                            i7 = seekAheadOptimize.pos;
                            i8 = seekAheadOptimize.pos;
                        }
                        int i13 = i8;
                        i9 = i7;
                        readerIndex2 = i13;
                    } else if (b == str.codePointAt(i10)) {
                        i10++;
                        if (str.length() == i10) {
                            seekAheadOptimize.setReadPosition(0);
                            z = true;
                            break loop0;
                        }
                    } else {
                        if (b == 13) {
                            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                byte[] bArr3 = seekAheadOptimize.bytes;
                                int i14 = seekAheadOptimize.pos;
                                seekAheadOptimize.pos = i14 + 1;
                                if (bArr3[i14] == 10) {
                                    i = seekAheadOptimize.pos;
                                    i5 = seekAheadOptimize.pos;
                                    break;
                                }
                                z2 = false;
                                i10 = 0;
                            } else {
                                i3 = seekAheadOptimize.pos;
                                i4 = seekAheadOptimize.pos;
                            }
                        } else if (b == 10) {
                            i = seekAheadOptimize.pos;
                            i2 = seekAheadOptimize.pos;
                            break;
                        } else {
                            i3 = seekAheadOptimize.pos;
                            i4 = seekAheadOptimize.pos;
                        }
                        i9 = i3;
                        readerIndex2 = i4;
                        z2 = false;
                        i10 = 0;
                    }
                    i9 = i;
                    readerIndex2 = i6;
                }
            }
            if (i9 > 0) {
                seekAheadOptimize.pos = i9;
                seekAheadOptimize.setReadPosition(0);
            }
            ChannelBuffer slice = this.undecodedChunk.slice(readerIndex, readerIndex2 - readerIndex);
            if (z) {
                try {
                    this.currentFileUpload.addContent(slice, true);
                    this.undecodedChunk.readerIndex(readerIndex2);
                    return;
                } catch (IOException e) {
                    throw new ErrorDataDecoderException(e);
                }
            }
            try {
                this.currentFileUpload.addContent(slice, false);
                this.undecodedChunk.readerIndex(readerIndex2);
                throw new NotEnoughDataDecoderException();
            } catch (IOException e2) {
                throw new ErrorDataDecoderException(e2);
            }
        } catch (HttpPostBodyUtil.SeekAheadNoBackArrayException unused) {
            readFileUploadByteMultipartStandard(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x007a, code lost:
    
        r1 = r1 - 2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void loadFieldMultipartStandard(String str) throws NotEnoughDataDecoderException, ErrorDataDecoderException {
        int i;
        boolean z;
        int readerIndex;
        int readerIndex2;
        int readerIndex3 = this.undecodedChunk.readerIndex();
        try {
            int readerIndex4 = this.undecodedChunk.readerIndex();
            loop0: while (true) {
                i = readerIndex4;
                int i2 = 0;
                boolean z2 = true;
                while (true) {
                    if (!this.undecodedChunk.readable()) {
                        z = false;
                        break loop0;
                    }
                    byte readByte = this.undecodedChunk.readByte();
                    if (z2) {
                        if (readByte == str.codePointAt(i2)) {
                            i2++;
                            if (str.length() == i2) {
                                z = true;
                                break loop0;
                            }
                        } else {
                            if (readByte == 13) {
                                if (this.undecodedChunk.readable() && this.undecodedChunk.readByte() == 10) {
                                    readerIndex2 = this.undecodedChunk.readerIndex();
                                    break;
                                }
                            } else {
                                if (readByte == 10) {
                                    readerIndex = this.undecodedChunk.readerIndex();
                                    break;
                                }
                                i = this.undecodedChunk.readerIndex();
                            }
                            z2 = false;
                            i2 = 0;
                        }
                    } else if (readByte == 13) {
                        if (this.undecodedChunk.readable() && this.undecodedChunk.readByte() == 10) {
                            readerIndex2 = this.undecodedChunk.readerIndex();
                            break;
                        }
                    } else {
                        if (readByte == 10) {
                            readerIndex = this.undecodedChunk.readerIndex();
                            break;
                        }
                        i = this.undecodedChunk.readerIndex();
                    }
                }
                readerIndex4 = readerIndex - 1;
            }
            if (z) {
                try {
                    this.currentAttribute.addContent(this.undecodedChunk.slice(readerIndex3, i - readerIndex3), true);
                    this.undecodedChunk.readerIndex(i);
                    return;
                } catch (IOException e) {
                    throw new ErrorDataDecoderException(e);
                }
            }
            try {
                this.currentAttribute.addContent(this.undecodedChunk.slice(readerIndex3, i - readerIndex3), false);
                this.undecodedChunk.readerIndex(i);
                throw new NotEnoughDataDecoderException();
            } catch (IOException e2) {
                throw new ErrorDataDecoderException(e2);
            }
        } catch (IndexOutOfBoundsException e3) {
            this.undecodedChunk.readerIndex(readerIndex3);
            throw new NotEnoughDataDecoderException(e3);
        }
        this.undecodedChunk.readerIndex(readerIndex3);
        throw new NotEnoughDataDecoderException(e3);
    }

    private void loadFieldMultipart(String str) throws NotEnoughDataDecoderException, ErrorDataDecoderException {
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        try {
            HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
            int readerIndex = this.undecodedChunk.readerIndex();
            try {
                int readerIndex2 = this.undecodedChunk.readerIndex();
                int i5 = -1;
                loop0: while (true) {
                    i = readerIndex2;
                    i2 = i5;
                    int i6 = 0;
                    boolean z2 = true;
                    while (true) {
                        if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                            z = false;
                            break loop0;
                        }
                        byte[] bArr = seekAheadOptimize.bytes;
                        int i7 = seekAheadOptimize.pos;
                        seekAheadOptimize.pos = i7 + 1;
                        byte b = bArr[i7];
                        if (z2) {
                            if (b == str.codePointAt(i6)) {
                                i6++;
                                if (str.length() == i6) {
                                    seekAheadOptimize.setReadPosition(0);
                                    z = true;
                                    break loop0;
                                }
                            } else {
                                if (b == 13) {
                                    if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                        byte[] bArr2 = seekAheadOptimize.bytes;
                                        int i8 = seekAheadOptimize.pos;
                                        seekAheadOptimize.pos = i8 + 1;
                                        if (bArr2[i8] == 10) {
                                            readerIndex2 = seekAheadOptimize.pos - 2;
                                            i5 = seekAheadOptimize.pos;
                                            break;
                                        }
                                    } else {
                                        i3 = seekAheadOptimize.pos;
                                        i4 = seekAheadOptimize.pos;
                                        i = i3;
                                        i2 = i4;
                                    }
                                } else if (b == 10) {
                                    readerIndex2 = seekAheadOptimize.pos - 1;
                                    i5 = seekAheadOptimize.pos;
                                    break;
                                } else {
                                    i3 = seekAheadOptimize.pos;
                                    i4 = seekAheadOptimize.pos;
                                    i = i3;
                                    i2 = i4;
                                }
                                z2 = false;
                                i6 = 0;
                            }
                        } else if (b == 13) {
                            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                byte[] bArr3 = seekAheadOptimize.bytes;
                                int i9 = seekAheadOptimize.pos;
                                seekAheadOptimize.pos = i9 + 1;
                                if (bArr3[i9] == 10) {
                                    readerIndex2 = seekAheadOptimize.pos - 2;
                                    i5 = seekAheadOptimize.pos;
                                    break;
                                }
                            } else {
                                i = seekAheadOptimize.pos;
                                i2 = seekAheadOptimize.pos;
                            }
                        } else if (b == 10) {
                            readerIndex2 = seekAheadOptimize.pos - 1;
                            i5 = seekAheadOptimize.pos;
                            break;
                        } else {
                            i = seekAheadOptimize.pos;
                            i2 = seekAheadOptimize.pos;
                        }
                    }
                }
                if (i2 > 0) {
                    seekAheadOptimize.pos = i2;
                    seekAheadOptimize.setReadPosition(0);
                }
                if (z) {
                    try {
                        this.currentAttribute.addContent(this.undecodedChunk.slice(readerIndex, i - readerIndex), true);
                        this.undecodedChunk.readerIndex(i);
                        return;
                    } catch (IOException e) {
                        throw new ErrorDataDecoderException(e);
                    }
                }
                try {
                    this.currentAttribute.addContent(this.undecodedChunk.slice(readerIndex, i - readerIndex), false);
                    this.undecodedChunk.readerIndex(i);
                    throw new NotEnoughDataDecoderException();
                } catch (IOException e2) {
                    throw new ErrorDataDecoderException(e2);
                }
            } catch (IndexOutOfBoundsException e3) {
                this.undecodedChunk.readerIndex(readerIndex);
                throw new NotEnoughDataDecoderException(e3);
            }
            this.undecodedChunk.readerIndex(readerIndex);
            throw new NotEnoughDataDecoderException(e3);
        } catch (HttpPostBodyUtil.SeekAheadNoBackArrayException unused) {
            loadFieldMultipartStandard(str);
        }
    }

    private static String cleanString(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ':') {
                sb.append(32);
            } else if (charAt == ',') {
                sb.append(32);
            } else if (charAt == '=') {
                sb.append(32);
            } else if (charAt == ';') {
                sb.append(32);
            } else if (charAt == '\t') {
                sb.append(32);
            } else if (charAt != '\"') {
                sb.append(charAt);
            }
        }
        return sb.toString().trim();
    }

    private boolean skipOneLine() {
        if (!this.undecodedChunk.readable()) {
            return false;
        }
        byte readByte = this.undecodedChunk.readByte();
        if (readByte != 13) {
            if (readByte == 10) {
                return true;
            }
            ChannelBuffer channelBuffer = this.undecodedChunk;
            channelBuffer.readerIndex(channelBuffer.readerIndex() - 1);
            return false;
        }
        if (!this.undecodedChunk.readable()) {
            ChannelBuffer channelBuffer2 = this.undecodedChunk;
            channelBuffer2.readerIndex(channelBuffer2.readerIndex() - 1);
            return false;
        }
        if (this.undecodedChunk.readByte() == 10) {
            return true;
        }
        this.undecodedChunk.readerIndex(r0.readerIndex() - 2);
        return false;
    }

    private static String[] splitHeaderContentType(String str) {
        int length = str.length();
        int findNonWhitespace = HttpPostBodyUtil.findNonWhitespace(str, 0);
        int findWhitespace = HttpPostBodyUtil.findWhitespace(str, findNonWhitespace);
        if (findWhitespace >= length) {
            return new String[]{str, ""};
        }
        if (str.charAt(findWhitespace) == ';') {
            findWhitespace--;
        }
        return new String[]{str.substring(findNonWhitespace, findWhitespace), str.substring(HttpPostBodyUtil.findNonWhitespace(str, findWhitespace), HttpPostBodyUtil.findEndOfString(str))};
    }

    private static String[] splitMultipartHeader(String str) {
        String[] split;
        char charAt;
        ArrayList arrayList = new ArrayList(1);
        int findNonWhitespace = HttpPostBodyUtil.findNonWhitespace(str, 0);
        int i = findNonWhitespace;
        while (i < str.length() && (charAt = str.charAt(i)) != ':' && !Character.isWhitespace(charAt)) {
            i++;
        }
        int i2 = i;
        while (true) {
            if (i2 >= str.length()) {
                break;
            }
            if (str.charAt(i2) == ':') {
                i2++;
                break;
            }
            i2++;
        }
        int findNonWhitespace2 = HttpPostBodyUtil.findNonWhitespace(str, i2);
        int findEndOfString = HttpPostBodyUtil.findEndOfString(str);
        arrayList.add(str.substring(findNonWhitespace, i));
        String substring = str.substring(findNonWhitespace2, findEndOfString);
        if (substring.indexOf(VoiceWakeuperAidl.PARAMS_SEPARATE) >= 0) {
            split = substring.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
        } else {
            split = substring.split(",");
        }
        for (String str2 : split) {
            arrayList.add(str2.trim());
        }
        String[] strArr = new String[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            strArr[i3] = (String) arrayList.get(i3);
        }
        return strArr;
    }

    /* loaded from: classes7.dex */
    public static class NotEnoughDataDecoderException extends Exception {
        private static final long serialVersionUID = -7846841864603865638L;

        public NotEnoughDataDecoderException() {
        }

        public NotEnoughDataDecoderException(String str) {
            super(str);
        }

        public NotEnoughDataDecoderException(Throwable th) {
            super(th);
        }

        public NotEnoughDataDecoderException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes7.dex */
    public static class ErrorDataDecoderException extends Exception {
        private static final long serialVersionUID = 5020247425493164465L;

        public ErrorDataDecoderException() {
        }

        public ErrorDataDecoderException(String str) {
            super(str);
        }

        public ErrorDataDecoderException(Throwable th) {
            super(th);
        }

        public ErrorDataDecoderException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes7.dex */
    public static class IncompatibleDataDecoderException extends Exception {
        private static final long serialVersionUID = -953268047926250267L;

        public IncompatibleDataDecoderException() {
        }

        public IncompatibleDataDecoderException(String str) {
            super(str);
        }

        public IncompatibleDataDecoderException(Throwable th) {
            super(th);
        }

        public IncompatibleDataDecoderException(String str, Throwable th) {
            super(str, th);
        }
    }
}
