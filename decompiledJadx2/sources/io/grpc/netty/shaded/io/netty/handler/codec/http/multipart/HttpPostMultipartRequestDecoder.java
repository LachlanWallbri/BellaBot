package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.QueryStringDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpPostMultipartRequestDecoder implements InterfaceHttpPostRequestDecoder {
    private static final String FILENAME_ENCODED = HttpHeaderValues.FILENAME.toString() + '*';
    private final List<InterfaceHttpData> bodyListHttpData;
    private int bodyListHttpDataRank;
    private final Map<String, List<InterfaceHttpData>> bodyMapHttpData;
    private Charset charset;
    private Attribute currentAttribute;
    private Map<CharSequence, Attribute> currentFieldAttributes;
    private FileUpload currentFileUpload;
    private HttpPostRequestDecoder.MultiPartStatus currentStatus;
    private boolean destroyed;
    private int discardThreshold;
    private final HttpDataFactory factory;
    private boolean isLastChunk;
    private String multipartDataBoundary;
    private String multipartMixedBoundary;
    private final HttpRequest request;
    private ByteBuf undecodedChunk;

    public HttpPostMultipartRequestDecoder(HttpRequest httpRequest) {
        this(new DefaultHttpDataFactory(16384L), httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostMultipartRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest) {
        this(httpDataFactory, httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostMultipartRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, Charset charset) {
        this.bodyListHttpData = new ArrayList();
        this.bodyMapHttpData = new TreeMap(CaseIgnoringComparator.INSTANCE);
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED;
        this.discardThreshold = OSSConfig.DEFAULT_MULTI_PART_UPLOAD_SIZE;
        this.request = (HttpRequest) ObjectUtil.checkNotNull(httpRequest, "request");
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
        this.factory = (HttpDataFactory) ObjectUtil.checkNotNull(httpDataFactory, "factory");
        setMultipart(this.request.headers().get(HttpHeaderNames.CONTENT_TYPE));
        if (httpRequest instanceof HttpContent) {
            offer((HttpContent) httpRequest);
        } else {
            parseBody();
        }
    }

    private void setMultipart(String str) {
        String[] multipartDataBoundary = HttpPostRequestDecoder.getMultipartDataBoundary(str);
        if (multipartDataBoundary != null) {
            this.multipartDataBoundary = multipartDataBoundary[0];
            if (multipartDataBoundary.length > 1 && multipartDataBoundary[1] != null) {
                this.charset = Charset.forName(multipartDataBoundary[1]);
            }
        } else {
            this.multipartDataBoundary = null;
        }
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER;
    }

    private void checkDestroyed() {
        if (this.destroyed) {
            throw new IllegalStateException(HttpPostMultipartRequestDecoder.class.getSimpleName() + " was destroyed already");
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean isMultipart() {
        checkDestroyed();
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void setDiscardThreshold(int i) {
        this.discardThreshold = ObjectUtil.checkPositiveOrZero(i, "discardThreshold");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public int getDiscardThreshold() {
        return this.discardThreshold;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas() {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        return this.bodyListHttpData;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas(String str) {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        return this.bodyMapHttpData.get(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData getBodyHttpData(String str) {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        List<InterfaceHttpData> list = this.bodyMapHttpData.get(str);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public HttpPostMultipartRequestDecoder offer(HttpContent httpContent) {
        ByteBuf writeBytes;
        checkDestroyed();
        if (httpContent instanceof LastHttpContent) {
            this.isLastChunk = true;
        }
        ByteBuf content = httpContent.content();
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null) {
            if (this.isLastChunk) {
                writeBytes = content.retainedSlice();
            } else {
                writeBytes = content.alloc().buffer(content.readableBytes()).writeBytes(content);
            }
            this.undecodedChunk = writeBytes;
        } else {
            byteBuf.writeBytes(content);
        }
        parseBody();
        ByteBuf byteBuf2 = this.undecodedChunk;
        if (byteBuf2 != null && byteBuf2.writerIndex() > this.discardThreshold) {
            this.undecodedChunk.discardReadBytes();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean hasNext() {
        checkDestroyed();
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE || this.bodyListHttpDataRank < this.bodyListHttpData.size()) {
            return !this.bodyListHttpData.isEmpty() && this.bodyListHttpDataRank < this.bodyListHttpData.size();
        }
        throw new HttpPostRequestDecoder.EndOfDataDecoderException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData next() {
        checkDestroyed();
        if (!hasNext()) {
            return null;
        }
        List<InterfaceHttpData> list = this.bodyListHttpData;
        int i = this.bodyListHttpDataRank;
        this.bodyListHttpDataRank = i + 1;
        return list.get(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData currentPartialHttpData() {
        FileUpload fileUpload = this.currentFileUpload;
        return fileUpload != null ? fileUpload : this.currentAttribute;
    }

    private void parseBody() {
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE || this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
            if (this.isLastChunk) {
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
                return;
            }
            return;
        }
        parseBodyMultipart();
    }

    protected void addHttpData(InterfaceHttpData interfaceHttpData) {
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

    private void parseBodyMultipart() {
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null || byteBuf.readableBytes() == 0) {
            return;
        }
        InterfaceHttpData decodeMultipart = decodeMultipart(this.currentStatus);
        while (decodeMultipart != null) {
            addHttpData(decodeMultipart);
            if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE || this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
                return;
            } else {
                decodeMultipart = decodeMultipart(this.currentStatus);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008b A[Catch: IOException -> 0x00b8, IllegalArgumentException -> 0x00bf, NullPointerException -> 0x00c6, TRY_ENTER, TryCatch #6 {IOException -> 0x00b8, IllegalArgumentException -> 0x00bf, NullPointerException -> 0x00c6, blocks: (B:26:0x008b, B:30:0x009e), top: B:24:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009e A[Catch: IOException -> 0x00b8, IllegalArgumentException -> 0x00bf, NullPointerException -> 0x00c6, TRY_LEAVE, TryCatch #6 {IOException -> 0x00b8, IllegalArgumentException -> 0x00bf, NullPointerException -> 0x00c6, blocks: (B:26:0x008b, B:30:0x009e), top: B:24:0x0089 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private InterfaceHttpData decodeMultipart(HttpPostRequestDecoder.MultiPartStatus multiPartStatus) {
        Charset forName;
        long parseLong;
        switch (multiPartStatus) {
            case NOTSTARTED:
                throw new HttpPostRequestDecoder.ErrorDataDecoderException("Should not be called with the current getStatus");
            case PREAMBLE:
                throw new HttpPostRequestDecoder.ErrorDataDecoderException("Should not be called with the current getStatus");
            case HEADERDELIMITER:
                return findMultipartDelimiter(this.multipartDataBoundary, HttpPostRequestDecoder.MultiPartStatus.DISPOSITION, HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE);
            case DISPOSITION:
                return findMultipartDisposition();
            case FIELD:
                Attribute attribute = this.currentFieldAttributes.get(HttpHeaderValues.CHARSET);
                if (attribute != null) {
                    try {
                        forName = Charset.forName(attribute.getValue());
                    } catch (IOException e) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
                    } catch (UnsupportedCharsetException e2) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e2);
                    }
                } else {
                    forName = null;
                }
                Attribute attribute2 = this.currentFieldAttributes.get(HttpHeaderValues.NAME);
                if (this.currentAttribute == null) {
                    Attribute attribute3 = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_LENGTH);
                    try {
                        if (attribute3 != null) {
                            try {
                                parseLong = Long.parseLong(attribute3.getValue());
                            } catch (IOException e3) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e3);
                            } catch (NumberFormatException unused) {
                            }
                            if (parseLong <= 0) {
                                this.currentAttribute = this.factory.createAttribute(this.request, cleanString(attribute2.getValue()), parseLong);
                            } else {
                                this.currentAttribute = this.factory.createAttribute(this.request, cleanString(attribute2.getValue()));
                            }
                            if (forName != null) {
                                this.currentAttribute.setCharset(forName);
                            }
                        }
                        if (parseLong <= 0) {
                        }
                        if (forName != null) {
                        }
                    } catch (IOException e4) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e4);
                    } catch (IllegalArgumentException e5) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e5);
                    } catch (NullPointerException e6) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e6);
                    }
                    parseLong = 0;
                }
                if (!loadDataMultipart(this.undecodedChunk, this.multipartDataBoundary, this.currentAttribute)) {
                    return null;
                }
                Attribute attribute4 = this.currentAttribute;
                this.currentAttribute = null;
                this.currentFieldAttributes = null;
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER;
                return attribute4;
            case FILEUPLOAD:
                return getFileUpload(this.multipartDataBoundary);
            case MIXEDDELIMITER:
                return findMultipartDelimiter(this.multipartMixedBoundary, HttpPostRequestDecoder.MultiPartStatus.MIXEDDISPOSITION, HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER);
            case MIXEDDISPOSITION:
                return findMultipartDisposition();
            case MIXEDFILEUPLOAD:
                return getFileUpload(this.multipartMixedBoundary);
            case PREEPILOGUE:
            case EPILOGUE:
                return null;
            default:
                throw new HttpPostRequestDecoder.ErrorDataDecoderException("Shouldn't reach here.");
        }
    }

    private static void skipControlCharacters(ByteBuf byteBuf) {
        if (!byteBuf.hasArray()) {
            try {
                skipControlCharactersStandard(byteBuf);
                return;
            } catch (IndexOutOfBoundsException e) {
                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
            }
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
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
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException("Access out of bounds");
    }

    private static void skipControlCharactersStandard(ByteBuf byteBuf) {
        while (true) {
            char readUnsignedByte = (char) byteBuf.readUnsignedByte();
            if (!Character.isISOControl(readUnsignedByte) && !Character.isWhitespace(readUnsignedByte)) {
                byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                return;
            }
        }
    }

    private InterfaceHttpData findMultipartDelimiter(String str, HttpPostRequestDecoder.MultiPartStatus multiPartStatus, HttpPostRequestDecoder.MultiPartStatus multiPartStatus2) {
        int readerIndex = this.undecodedChunk.readerIndex();
        try {
            skipControlCharacters(this.undecodedChunk);
            skipOneLine();
            try {
                String readDelimiter = readDelimiter(this.undecodedChunk, str);
                if (readDelimiter.equals(str)) {
                    this.currentStatus = multiPartStatus;
                    return decodeMultipart(multiPartStatus);
                }
                if (readDelimiter.equals(str + "--")) {
                    this.currentStatus = multiPartStatus2;
                    if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER) {
                        return null;
                    }
                    this.currentFieldAttributes = null;
                    return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER);
                }
                this.undecodedChunk.readerIndex(readerIndex);
                throw new HttpPostRequestDecoder.ErrorDataDecoderException("No Multipart delimiter found");
            } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused) {
                this.undecodedChunk.readerIndex(readerIndex);
                return null;
            }
        } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused2) {
            this.undecodedChunk.readerIndex(readerIndex);
            return null;
        }
    }

    private InterfaceHttpData findMultipartDisposition() {
        boolean z;
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
            this.currentFieldAttributes = new TreeMap(CaseIgnoringComparator.INSTANCE);
        }
        while (!skipOneLine()) {
            try {
                skipControlCharacters(this.undecodedChunk);
                String[] splitMultipartHeader = splitMultipartHeader(readLine(this.undecodedChunk, this.charset));
                if (HttpHeaderNames.CONTENT_DISPOSITION.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
                        z = HttpHeaderValues.FORM_DATA.contentEqualsIgnoreCase(splitMultipartHeader[1]);
                    } else {
                        z = HttpHeaderValues.ATTACHMENT.contentEqualsIgnoreCase(splitMultipartHeader[1]) || HttpHeaderValues.FILE.contentEqualsIgnoreCase(splitMultipartHeader[1]);
                    }
                    if (z) {
                        for (int i = 2; i < splitMultipartHeader.length; i++) {
                            try {
                                Attribute contentDispositionAttribute = getContentDispositionAttribute(splitMultipartHeader[i].split("=", 2));
                                this.currentFieldAttributes.put(contentDispositionAttribute.getName(), contentDispositionAttribute);
                            } catch (IllegalArgumentException e) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
                            } catch (NullPointerException e2) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e2);
                            }
                        }
                    } else {
                        continue;
                    }
                } else if (HttpHeaderNames.CONTENT_TRANSFER_ENCODING.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    try {
                        this.currentFieldAttributes.put(HttpHeaderNames.CONTENT_TRANSFER_ENCODING, this.factory.createAttribute(this.request, HttpHeaderNames.CONTENT_TRANSFER_ENCODING.toString(), cleanString(splitMultipartHeader[1])));
                    } catch (IllegalArgumentException e3) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e3);
                    } catch (NullPointerException e4) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e4);
                    }
                } else if (HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    try {
                        this.currentFieldAttributes.put(HttpHeaderNames.CONTENT_LENGTH, this.factory.createAttribute(this.request, HttpHeaderNames.CONTENT_LENGTH.toString(), cleanString(splitMultipartHeader[1])));
                    } catch (IllegalArgumentException e5) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e5);
                    } catch (NullPointerException e6) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException(e6);
                    }
                } else if (!HttpHeaderNames.CONTENT_TYPE.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    continue;
                } else {
                    if (HttpHeaderValues.MULTIPART_MIXED.contentEqualsIgnoreCase(splitMultipartHeader[1])) {
                        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
                            this.multipartMixedBoundary = "--" + StringUtil.substringAfter(splitMultipartHeader[2], '=');
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER;
                            return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER);
                        }
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException("Mixed Multipart found in a previous Mixed Multipart");
                    }
                    for (int i2 = 1; i2 < splitMultipartHeader.length; i2++) {
                        String asciiString = HttpHeaderValues.CHARSET.toString();
                        if (splitMultipartHeader[i2].regionMatches(true, 0, asciiString, 0, asciiString.length())) {
                            try {
                                this.currentFieldAttributes.put(HttpHeaderValues.CHARSET, this.factory.createAttribute(this.request, asciiString, cleanString(StringUtil.substringAfter(splitMultipartHeader[i2], '='))));
                            } catch (IllegalArgumentException e7) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e7);
                            } catch (NullPointerException e8) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e8);
                            }
                        } else {
                            try {
                                Attribute createAttribute = this.factory.createAttribute(this.request, cleanString(splitMultipartHeader[0]), splitMultipartHeader[i2]);
                                this.currentFieldAttributes.put(createAttribute.getName(), createAttribute);
                            } catch (IllegalArgumentException e9) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e9);
                            } catch (NullPointerException e10) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e10);
                            }
                        }
                    }
                }
            } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused) {
                this.undecodedChunk.readerIndex(readerIndex);
                return null;
            }
        }
        Attribute attribute = this.currentFieldAttributes.get(HttpHeaderValues.FILENAME);
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
            if (attribute != null) {
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.MIXEDFILEUPLOAD;
                return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.MIXEDFILEUPLOAD);
            }
            throw new HttpPostRequestDecoder.ErrorDataDecoderException("Filename not found");
        }
        if (attribute != null) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD;
            return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD);
        }
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
        return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.FIELD);
    }

    private Attribute getContentDispositionAttribute(String... strArr) {
        String cleanString = cleanString(strArr[0]);
        String str = strArr[1];
        if (HttpHeaderValues.FILENAME.contentEquals(cleanString)) {
            int length = str.length() - 1;
            if (length > 0 && str.charAt(0) == '\"' && str.charAt(length) == '\"') {
                str = str.substring(1, length);
            }
        } else if (FILENAME_ENCODED.equals(cleanString)) {
            try {
                cleanString = HttpHeaderValues.FILENAME.toString();
                String[] split = cleanString(str).split("'", 3);
                str = QueryStringDecoder.decodeComponent(split[2], Charset.forName(split[0]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            } catch (UnsupportedCharsetException e2) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e2);
            }
        } else {
            str = cleanString(str);
        }
        return this.factory.createAttribute(this.request, cleanString, str);
    }

    protected InterfaceHttpData getFileUpload(String str) {
        String value;
        Attribute attribute = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_TRANSFER_ENCODING);
        Charset charset = this.charset;
        HttpPostBodyUtil.TransferEncodingMechanism transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT7;
        if (attribute != null) {
            try {
                String lowerCase = attribute.getValue().toLowerCase();
                if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT7.value())) {
                    charset = CharsetUtil.US_ASCII;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT8.value())) {
                    charset = CharsetUtil.ISO_8859_1;
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT8;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value())) {
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BINARY;
                } else {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException("TransferEncoding Unknown: " + lowerCase);
                }
            } catch (IOException e) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            }
        }
        Attribute attribute2 = this.currentFieldAttributes.get(HttpHeaderValues.CHARSET);
        if (attribute2 != null) {
            try {
                charset = Charset.forName(attribute2.getValue());
            } catch (IOException e2) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e2);
            } catch (UnsupportedCharsetException e3) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e3);
            }
        }
        Charset charset2 = charset;
        if (this.currentFileUpload == null) {
            Attribute attribute3 = this.currentFieldAttributes.get(HttpHeaderValues.FILENAME);
            Attribute attribute4 = this.currentFieldAttributes.get(HttpHeaderValues.NAME);
            Attribute attribute5 = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_TYPE);
            Attribute attribute6 = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_LENGTH);
            long j = 0;
            if (attribute6 != null) {
                try {
                    j = Long.parseLong(attribute6.getValue());
                } catch (IOException e4) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e4);
                } catch (NumberFormatException unused) {
                }
            }
            long j2 = j;
            if (attribute5 != null) {
                try {
                    value = attribute5.getValue();
                } catch (IOException e5) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e5);
                } catch (IllegalArgumentException e6) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e6);
                } catch (NullPointerException e7) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e7);
                }
            } else {
                value = "application/octet-stream";
            }
            this.currentFileUpload = this.factory.createFileUpload(this.request, cleanString(attribute4.getValue()), cleanString(attribute3.getValue()), value, transferEncodingMechanism.value(), charset2, j2);
        }
        if (!loadDataMultipart(this.undecodedChunk, str, this.currentFileUpload) || !this.currentFileUpload.isCompleted()) {
            return null;
        }
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER;
            this.currentFieldAttributes = null;
        } else {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER;
            cleanMixedAttributes();
        }
        FileUpload fileUpload = this.currentFileUpload;
        this.currentFileUpload = null;
        return fileUpload;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void destroy() {
        cleanFiles();
        this.destroyed = true;
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null || byteBuf.refCnt() <= 0) {
            return;
        }
        this.undecodedChunk.release();
        this.undecodedChunk = null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void cleanFiles() {
        checkDestroyed();
        this.factory.cleanRequestHttpData(this.request);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData) {
        checkDestroyed();
        this.factory.removeHttpDataFromClean(this.request, interfaceHttpData);
    }

    private void cleanMixedAttributes() {
        this.currentFieldAttributes.remove(HttpHeaderValues.CHARSET);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_LENGTH);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_TRANSFER_ENCODING);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_TYPE);
        this.currentFieldAttributes.remove(HttpHeaderValues.FILENAME);
    }

    private static String readLineStandard(ByteBuf byteBuf, Charset charset) {
        int readerIndex = byteBuf.readerIndex();
        ByteBuf heapBuffer = byteBuf.alloc().heapBuffer(64);
        while (byteBuf.isReadable()) {
            try {
                try {
                    byte readByte = byteBuf.readByte();
                    if (readByte == 13) {
                        if (byteBuf.getByte(byteBuf.readerIndex()) == 10) {
                            byteBuf.readByte();
                            return heapBuffer.toString(charset);
                        }
                        heapBuffer.writeByte(13);
                    } else {
                        if (readByte == 10) {
                            return heapBuffer.toString(charset);
                        }
                        heapBuffer.writeByte(readByte);
                    }
                } catch (IndexOutOfBoundsException e) {
                    byteBuf.readerIndex(readerIndex);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
                }
            } finally {
                heapBuffer.release();
            }
        }
        heapBuffer.release();
        byteBuf.readerIndex(readerIndex);
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    private static String readLine(ByteBuf byteBuf, Charset charset) {
        if (!byteBuf.hasArray()) {
            return readLineStandard(byteBuf, charset);
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
        int readerIndex = byteBuf.readerIndex();
        ByteBuf heapBuffer = byteBuf.alloc().heapBuffer(64);
        while (seekAheadOptimize.pos < seekAheadOptimize.limit) {
            try {
                try {
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
                                return heapBuffer.toString(charset);
                            }
                            seekAheadOptimize.pos--;
                            heapBuffer.writeByte(13);
                        } else {
                            heapBuffer.writeByte(b);
                        }
                    } else {
                        if (b == 10) {
                            seekAheadOptimize.setReadPosition(0);
                            return heapBuffer.toString(charset);
                        }
                        heapBuffer.writeByte(b);
                    }
                } catch (IndexOutOfBoundsException e) {
                    byteBuf.readerIndex(readerIndex);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
                }
            } finally {
                heapBuffer.release();
            }
        }
        heapBuffer.release();
        byteBuf.readerIndex(readerIndex);
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    private static String readDelimiterStandard(ByteBuf byteBuf, String str) {
        int readerIndex = byteBuf.readerIndex();
        try {
            StringBuilder sb = new StringBuilder(64);
            int i = 0;
            int length = str.length();
            while (byteBuf.isReadable() && i < length) {
                byte readByte = byteBuf.readByte();
                if (readByte == str.charAt(i)) {
                    i++;
                    sb.append((char) readByte);
                } else {
                    byteBuf.readerIndex(readerIndex);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                }
            }
            if (byteBuf.isReadable()) {
                byte readByte2 = byteBuf.readByte();
                if (readByte2 == 13) {
                    if (byteBuf.readByte() == 10) {
                        return sb.toString();
                    }
                    byteBuf.readerIndex(readerIndex);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                }
                if (readByte2 == 10) {
                    return sb.toString();
                }
                if (readByte2 == 45) {
                    sb.append(Soundex.SILENT_MARKER);
                    if (byteBuf.readByte() == 45) {
                        sb.append(Soundex.SILENT_MARKER);
                        if (byteBuf.isReadable()) {
                            byte readByte3 = byteBuf.readByte();
                            if (readByte3 == 13) {
                                if (byteBuf.readByte() == 10) {
                                    return sb.toString();
                                }
                                byteBuf.readerIndex(readerIndex);
                                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                            }
                            if (readByte3 == 10) {
                                return sb.toString();
                            }
                            byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                            return sb.toString();
                        }
                        return sb.toString();
                    }
                }
            }
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
        }
    }

    private static String readDelimiter(ByteBuf byteBuf, String str) {
        if (!byteBuf.hasArray()) {
            return readDelimiterStandard(byteBuf, str);
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
        int readerIndex = byteBuf.readerIndex();
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
                    byteBuf.readerIndex(readerIndex);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
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
                        byteBuf.readerIndex(readerIndex);
                        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                    }
                    byteBuf.readerIndex(readerIndex);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                }
                if (b2 == 10) {
                    seekAheadOptimize.setReadPosition(0);
                    return sb.toString();
                }
                if (b2 == 45) {
                    sb.append(Soundex.SILENT_MARKER);
                    if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                        byte[] bArr4 = seekAheadOptimize.bytes;
                        int i5 = seekAheadOptimize.pos;
                        seekAheadOptimize.pos = i5 + 1;
                        if (bArr4[i5] == 45) {
                            sb.append(Soundex.SILENT_MARKER);
                            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                byte[] bArr5 = seekAheadOptimize.bytes;
                                int i6 = seekAheadOptimize.pos;
                                seekAheadOptimize.pos = i6 + 1;
                                byte b3 = bArr5[i6];
                                if (b3 != 13) {
                                    if (b3 == 10) {
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
                                    byteBuf.readerIndex(readerIndex);
                                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                                }
                                byteBuf.readerIndex(readerIndex);
                                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                            }
                            seekAheadOptimize.setReadPosition(0);
                            return sb.toString();
                        }
                    }
                }
            }
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
        }
    }

    private static boolean loadDataMultipartStandard(ByteBuf byteBuf, String str, HttpData httpData) {
        int readerIndex = byteBuf.readerIndex();
        int length = str.length();
        boolean z = false;
        int i = readerIndex;
        byte b = 10;
        int i2 = 0;
        while (true) {
            if (!byteBuf.isReadable()) {
                break;
            }
            byte readByte = byteBuf.readByte();
            if (b == 10 && readByte == str.codePointAt(i2)) {
                i2++;
                if (length == i2) {
                    z = true;
                    break;
                }
            } else {
                i = byteBuf.readerIndex();
                if (readByte == 10) {
                    i -= b == 13 ? 2 : 1;
                    i2 = 0;
                }
                b = readByte;
            }
        }
        if (b == 13) {
            i--;
        }
        try {
            httpData.addContent(byteBuf.retainedSlice(readerIndex, i - readerIndex), z);
            byteBuf.readerIndex(i);
            return z;
        } catch (IOException e) {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
        }
    }

    private static boolean loadDataMultipart(ByteBuf byteBuf, String str, HttpData httpData) {
        if (!byteBuf.hasArray()) {
            return loadDataMultipartStandard(byteBuf, str, httpData);
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
        int readerIndex = byteBuf.readerIndex();
        int length = str.length();
        boolean z = false;
        int i = seekAheadOptimize.pos;
        byte b = 10;
        int i2 = 0;
        while (true) {
            if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                break;
            }
            byte[] bArr = seekAheadOptimize.bytes;
            int i3 = seekAheadOptimize.pos;
            seekAheadOptimize.pos = i3 + 1;
            byte b2 = bArr[i3];
            if (b == 10 && b2 == str.codePointAt(i2)) {
                i2++;
                if (length == i2) {
                    z = true;
                    break;
                }
            } else {
                i = seekAheadOptimize.pos;
                if (b2 == 10) {
                    i -= b == 13 ? 2 : 1;
                    i2 = 0;
                }
                b = b2;
            }
        }
        if (b == 13) {
            i--;
        }
        int readPosition = seekAheadOptimize.getReadPosition(i);
        try {
            httpData.addContent(byteBuf.retainedSlice(readerIndex, readPosition - readerIndex), z);
            byteBuf.readerIndex(readPosition);
            return z;
        } catch (IOException e) {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
        }
    }

    private static String cleanString(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '\t') {
                if (charAt != '\"') {
                    if (charAt != ',' && charAt != '=' && charAt != ':' && charAt != ';') {
                        sb.append(charAt);
                    }
                }
            }
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    private boolean skipOneLine() {
        if (!this.undecodedChunk.isReadable()) {
            return false;
        }
        byte readByte = this.undecodedChunk.readByte();
        if (readByte != 13) {
            if (readByte == 10) {
                return true;
            }
            ByteBuf byteBuf = this.undecodedChunk;
            byteBuf.readerIndex(byteBuf.readerIndex() - 1);
            return false;
        }
        if (!this.undecodedChunk.isReadable()) {
            ByteBuf byteBuf2 = this.undecodedChunk;
            byteBuf2.readerIndex(byteBuf2.readerIndex() - 1);
            return false;
        }
        if (this.undecodedChunk.readByte() == 10) {
            return true;
        }
        this.undecodedChunk.readerIndex(r0.readerIndex() - 2);
        return false;
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
        String substring = findNonWhitespace2 >= findEndOfString ? "" : str.substring(findNonWhitespace2, findEndOfString);
        if (substring.indexOf(59) >= 0) {
            split = splitMultipartHeaderValues(substring);
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

    private static String[] splitMultipartHeaderValues(String str) {
        ArrayList arrayList = InternalThreadLocalMap.get().arrayList(1);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (z) {
                if (z2) {
                    z2 = false;
                } else if (charAt == '\\') {
                    z2 = true;
                } else if (charAt == '\"') {
                    z = false;
                }
            } else if (charAt == '\"') {
                z = true;
            } else if (charAt == ';') {
                arrayList.add(str.substring(i, i2));
                i = i2 + 1;
            }
        }
        arrayList.add(str.substring(i));
        return (String[]) arrayList.toArray(new String[0]);
    }
}
