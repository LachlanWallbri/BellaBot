package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.EmptyHttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpPostRequestEncoder implements ChunkedInput<HttpContent> {
    private static final Map.Entry[] percentEncodings = {new AbstractMap.SimpleImmutableEntry(Pattern.compile("\\*"), "%2A"), new AbstractMap.SimpleImmutableEntry(Pattern.compile("\\+"), "%20"), new AbstractMap.SimpleImmutableEntry(Pattern.compile("~"), "%7E")};
    private final List<InterfaceHttpData> bodyListDatas;
    private final Charset charset;
    private ByteBuf currentBuffer;
    private InterfaceHttpData currentData;
    private FileUpload currentFileUpload;
    private boolean duringMixedMode;
    private final EncoderMode encoderMode;
    private final HttpDataFactory factory;
    private long globalBodySize;
    private long globalProgress;
    private boolean headerFinalized;
    private boolean isChunked;
    private boolean isKey;
    private boolean isLastChunk;
    private boolean isLastChunkSent;
    private final boolean isMultipart;
    private ListIterator<InterfaceHttpData> iterator;
    String multipartDataBoundary;
    final List<InterfaceHttpData> multipartHttpDatas;
    String multipartMixedBoundary;
    private final HttpRequest request;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum EncoderMode {
        RFC1738,
        RFC3986,
        HTML5
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
    }

    public HttpPostRequestEncoder(HttpRequest httpRequest, boolean z) throws ErrorDataEncoderException {
        this(new DefaultHttpDataFactory(16384L), httpRequest, z, HttpConstants.DEFAULT_CHARSET, EncoderMode.RFC1738);
    }

    public HttpPostRequestEncoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, boolean z) throws ErrorDataEncoderException {
        this(httpDataFactory, httpRequest, z, HttpConstants.DEFAULT_CHARSET, EncoderMode.RFC1738);
    }

    public HttpPostRequestEncoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, boolean z, Charset charset, EncoderMode encoderMode) throws ErrorDataEncoderException {
        this.isKey = true;
        this.request = (HttpRequest) ObjectUtil.checkNotNull(httpRequest, "request");
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
        this.factory = (HttpDataFactory) ObjectUtil.checkNotNull(httpDataFactory, "factory");
        if (HttpMethod.TRACE.equals(httpRequest.method())) {
            throw new ErrorDataEncoderException("Cannot create a Encoder if request is a TRACE");
        }
        this.bodyListDatas = new ArrayList();
        this.isLastChunk = false;
        this.isLastChunkSent = false;
        this.isMultipart = z;
        this.multipartHttpDatas = new ArrayList();
        this.encoderMode = encoderMode;
        if (this.isMultipart) {
            initDataMultipart();
        }
    }

    public void cleanFiles() {
        this.factory.cleanRequestHttpData(this.request);
    }

    public boolean isMultipart() {
        return this.isMultipart;
    }

    private void initDataMultipart() {
        this.multipartDataBoundary = getNewMultipartDelimiter();
    }

    private void initMixedMultipart() {
        this.multipartMixedBoundary = getNewMultipartDelimiter();
    }

    private static String getNewMultipartDelimiter() {
        return Long.toHexString(PlatformDependent.threadLocalRandom().nextLong());
    }

    public List<InterfaceHttpData> getBodyListAttributes() {
        return this.bodyListDatas;
    }

    public void setBodyHttpDatas(List<InterfaceHttpData> list) throws ErrorDataEncoderException {
        ObjectUtil.checkNotNull(list, "datas");
        this.globalBodySize = 0L;
        this.bodyListDatas.clear();
        this.currentFileUpload = null;
        this.duringMixedMode = false;
        this.multipartHttpDatas.clear();
        Iterator<InterfaceHttpData> it = list.iterator();
        while (it.hasNext()) {
            addBodyHttpData(it.next());
        }
    }

    public void addBodyAttribute(String str, String str2) throws ErrorDataEncoderException {
        if (str2 == null) {
            str2 = "";
        }
        addBodyHttpData(this.factory.createAttribute(this.request, (String) ObjectUtil.checkNotNull(str, "name"), str2));
    }

    public void addBodyFileUpload(String str, File file, String str2, boolean z) throws ErrorDataEncoderException {
        addBodyFileUpload(str, file.getName(), file, str2, z);
    }

    public void addBodyFileUpload(String str, String str2, File file, String str3, boolean z) throws ErrorDataEncoderException {
        ObjectUtil.checkNotNull(str, "name");
        ObjectUtil.checkNotNull(file, "file");
        if (str2 == null) {
            str2 = "";
        }
        String str4 = str2;
        if (str3 == null) {
            str3 = z ? "text/plain" : "application/octet-stream";
        }
        FileUpload createFileUpload = this.factory.createFileUpload(this.request, str, str4, str3, z ? null : HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value(), null, file.length());
        try {
            createFileUpload.setContent(file);
            addBodyHttpData(createFileUpload);
        } catch (IOException e) {
            throw new ErrorDataEncoderException(e);
        }
    }

    public void addBodyFileUploads(String str, File[] fileArr, String[] strArr, boolean[] zArr) throws ErrorDataEncoderException {
        if (fileArr.length != strArr.length && fileArr.length != zArr.length) {
            throw new IllegalArgumentException("Different array length");
        }
        for (int i = 0; i < fileArr.length; i++) {
            addBodyFileUpload(str, fileArr[i], strArr[i], zArr[i]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addBodyHttpData(InterfaceHttpData interfaceHttpData) throws ErrorDataEncoderException {
        String str;
        String str2;
        FileUpload fileUpload;
        if (this.headerFinalized) {
            throw new ErrorDataEncoderException("Cannot add value once finalized");
        }
        this.bodyListDatas.add(ObjectUtil.checkNotNull(interfaceHttpData, "data"));
        if (!this.isMultipart) {
            if (interfaceHttpData instanceof Attribute) {
                Attribute attribute = (Attribute) interfaceHttpData;
                try {
                    Attribute createAttribute = this.factory.createAttribute(this.request, encodeAttribute(attribute.getName(), this.charset), encodeAttribute(attribute.getValue(), this.charset));
                    this.multipartHttpDatas.add(createAttribute);
                    this.globalBodySize += createAttribute.getName().length() + 1 + createAttribute.length() + 1;
                    return;
                } catch (IOException e) {
                    throw new ErrorDataEncoderException(e);
                }
            }
            if (interfaceHttpData instanceof FileUpload) {
                FileUpload fileUpload2 = (FileUpload) interfaceHttpData;
                Attribute createAttribute2 = this.factory.createAttribute(this.request, encodeAttribute(fileUpload2.getName(), this.charset), encodeAttribute(fileUpload2.getFilename(), this.charset));
                this.multipartHttpDatas.add(createAttribute2);
                this.globalBodySize += createAttribute2.getName().length() + 1 + createAttribute2.length() + 1;
                return;
            }
            return;
        }
        boolean z = false;
        if (interfaceHttpData instanceof Attribute) {
            if (this.duringMixedMode) {
                InternalAttribute internalAttribute = new InternalAttribute(this.charset);
                internalAttribute.addValue("\r\n--" + this.multipartMixedBoundary + "--");
                this.multipartHttpDatas.add(internalAttribute);
                this.multipartMixedBoundary = null;
                this.currentFileUpload = null;
                this.duringMixedMode = false;
            }
            InternalAttribute internalAttribute2 = new InternalAttribute(this.charset);
            if (!this.multipartHttpDatas.isEmpty()) {
                internalAttribute2.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
            }
            internalAttribute2.addValue("--" + this.multipartDataBoundary + IOUtils.LINE_SEPARATOR_WINDOWS);
            Attribute attribute2 = (Attribute) interfaceHttpData;
            internalAttribute2.addValue(((Object) HttpHeaderNames.CONTENT_DISPOSITION) + ": " + ((Object) HttpHeaderValues.FORM_DATA) + "; " + ((Object) HttpHeaderValues.NAME) + "=\"" + attribute2.getName() + "\"\r\n");
            StringBuilder sb = new StringBuilder();
            sb.append((Object) HttpHeaderNames.CONTENT_LENGTH);
            sb.append(": ");
            sb.append(attribute2.length());
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            internalAttribute2.addValue(sb.toString());
            Charset charset = attribute2.getCharset();
            if (charset != null) {
                internalAttribute2.addValue(((Object) HttpHeaderNames.CONTENT_TYPE) + ": text/plain; " + ((Object) HttpHeaderValues.CHARSET) + '=' + charset.name() + IOUtils.LINE_SEPARATOR_WINDOWS);
            }
            internalAttribute2.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
            this.multipartHttpDatas.add(internalAttribute2);
            this.multipartHttpDatas.add(interfaceHttpData);
            this.globalBodySize += attribute2.length() + internalAttribute2.size();
            return;
        }
        if (interfaceHttpData instanceof FileUpload) {
            FileUpload fileUpload3 = (FileUpload) interfaceHttpData;
            InternalAttribute internalAttribute3 = new InternalAttribute(this.charset);
            if (!this.multipartHttpDatas.isEmpty()) {
                internalAttribute3.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
            }
            if (this.duringMixedMode) {
                FileUpload fileUpload4 = this.currentFileUpload;
                if (fileUpload4 == null || !fileUpload4.getName().equals(fileUpload3.getName())) {
                    internalAttribute3.addValue("--" + this.multipartMixedBoundary + "--");
                    this.multipartHttpDatas.add(internalAttribute3);
                    this.multipartMixedBoundary = null;
                    internalAttribute3 = new InternalAttribute(this.charset);
                    internalAttribute3.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
                    this.currentFileUpload = fileUpload3;
                    this.duringMixedMode = false;
                    str = "=\"";
                    str2 = "\r\n\r\n";
                } else {
                    str = "=\"";
                    str2 = "\r\n\r\n";
                    z = true;
                }
            } else if (this.encoderMode != EncoderMode.HTML5 && (fileUpload = this.currentFileUpload) != null && fileUpload.getName().equals(fileUpload3.getName())) {
                initMixedMultipart();
                List<InterfaceHttpData> list = this.multipartHttpDatas;
                InternalAttribute internalAttribute4 = (InternalAttribute) list.get(list.size() - 2);
                this.globalBodySize -= internalAttribute4.size();
                StringBuilder sb2 = new StringBuilder(this.multipartDataBoundary.length() + 139 + (this.multipartMixedBoundary.length() * 2) + fileUpload3.getFilename().length() + fileUpload3.getName().length());
                sb2.append("--");
                sb2.append(this.multipartDataBoundary);
                sb2.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                sb2.append((CharSequence) HttpHeaderNames.CONTENT_DISPOSITION);
                sb2.append(": ");
                sb2.append((CharSequence) HttpHeaderValues.FORM_DATA);
                sb2.append("; ");
                sb2.append((CharSequence) HttpHeaderValues.NAME);
                str = "=\"";
                sb2.append(str);
                sb2.append(fileUpload3.getName());
                sb2.append("\"\r\n");
                sb2.append((CharSequence) HttpHeaderNames.CONTENT_TYPE);
                sb2.append(": ");
                sb2.append((CharSequence) HttpHeaderValues.MULTIPART_MIXED);
                sb2.append("; ");
                sb2.append((CharSequence) HttpHeaderValues.BOUNDARY);
                sb2.append('=');
                sb2.append(this.multipartMixedBoundary);
                sb2.append("\r\n\r\n");
                sb2.append("--");
                sb2.append(this.multipartMixedBoundary);
                sb2.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                sb2.append((CharSequence) HttpHeaderNames.CONTENT_DISPOSITION);
                sb2.append(": ");
                sb2.append((CharSequence) HttpHeaderValues.ATTACHMENT);
                if (!fileUpload3.getFilename().isEmpty()) {
                    sb2.append("; ");
                    sb2.append((CharSequence) HttpHeaderValues.FILENAME);
                    sb2.append(str);
                    sb2.append(this.currentFileUpload.getFilename());
                    sb2.append('\"');
                }
                sb2.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                internalAttribute4.setValue(sb2.toString(), 1);
                internalAttribute4.setValue("", 2);
                str2 = "\r\n\r\n";
                this.globalBodySize += internalAttribute4.size();
                this.duringMixedMode = true;
                z = true;
            } else {
                str = "=\"";
                str2 = "\r\n\r\n";
                this.currentFileUpload = fileUpload3;
                this.duringMixedMode = false;
            }
            if (z) {
                internalAttribute3.addValue("--" + this.multipartMixedBoundary + IOUtils.LINE_SEPARATOR_WINDOWS);
                if (fileUpload3.getFilename().isEmpty()) {
                    internalAttribute3.addValue(((Object) HttpHeaderNames.CONTENT_DISPOSITION) + ": " + ((Object) HttpHeaderValues.ATTACHMENT) + IOUtils.LINE_SEPARATOR_WINDOWS);
                } else {
                    internalAttribute3.addValue(((Object) HttpHeaderNames.CONTENT_DISPOSITION) + ": " + ((Object) HttpHeaderValues.ATTACHMENT) + "; " + ((Object) HttpHeaderValues.FILENAME) + str + fileUpload3.getFilename() + "\"\r\n");
                }
            } else {
                internalAttribute3.addValue("--" + this.multipartDataBoundary + IOUtils.LINE_SEPARATOR_WINDOWS);
                if (fileUpload3.getFilename().isEmpty()) {
                    internalAttribute3.addValue(((Object) HttpHeaderNames.CONTENT_DISPOSITION) + ": " + ((Object) HttpHeaderValues.FORM_DATA) + "; " + ((Object) HttpHeaderValues.NAME) + str + fileUpload3.getName() + "\"\r\n");
                } else {
                    internalAttribute3.addValue(((Object) HttpHeaderNames.CONTENT_DISPOSITION) + ": " + ((Object) HttpHeaderValues.FORM_DATA) + "; " + ((Object) HttpHeaderValues.NAME) + str + fileUpload3.getName() + "\"; " + ((Object) HttpHeaderValues.FILENAME) + str + fileUpload3.getFilename() + "\"\r\n");
                }
            }
            internalAttribute3.addValue(((Object) HttpHeaderNames.CONTENT_LENGTH) + ": " + fileUpload3.length() + IOUtils.LINE_SEPARATOR_WINDOWS);
            StringBuilder sb3 = new StringBuilder();
            sb3.append((Object) HttpHeaderNames.CONTENT_TYPE);
            sb3.append(": ");
            sb3.append(fileUpload3.getContentType());
            internalAttribute3.addValue(sb3.toString());
            String contentTransferEncoding = fileUpload3.getContentTransferEncoding();
            if (contentTransferEncoding != null && contentTransferEncoding.equals(HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value())) {
                internalAttribute3.addValue(IOUtils.LINE_SEPARATOR_WINDOWS + ((Object) HttpHeaderNames.CONTENT_TRANSFER_ENCODING) + ": " + HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value() + str2);
            } else if (fileUpload3.getCharset() != null) {
                internalAttribute3.addValue("; " + ((Object) HttpHeaderValues.CHARSET) + '=' + fileUpload3.getCharset().name() + str2);
            } else {
                internalAttribute3.addValue(str2);
            }
            this.multipartHttpDatas.add(internalAttribute3);
            this.multipartHttpDatas.add(interfaceHttpData);
            this.globalBodySize += fileUpload3.length() + internalAttribute3.size();
        }
    }

    public HttpRequest finalizeRequest() throws ErrorDataEncoderException {
        if (!this.headerFinalized) {
            if (this.isMultipart) {
                InternalAttribute internalAttribute = new InternalAttribute(this.charset);
                if (this.duringMixedMode) {
                    internalAttribute.addValue("\r\n--" + this.multipartMixedBoundary + "--");
                }
                internalAttribute.addValue("\r\n--" + this.multipartDataBoundary + "--\r\n");
                this.multipartHttpDatas.add(internalAttribute);
                this.multipartMixedBoundary = null;
                this.currentFileUpload = null;
                this.duringMixedMode = false;
                this.globalBodySize += internalAttribute.size();
            }
            this.headerFinalized = true;
            HttpHeaders headers = this.request.headers();
            List<String> all = headers.getAll(HttpHeaderNames.CONTENT_TYPE);
            List<String> all2 = headers.getAll(HttpHeaderNames.TRANSFER_ENCODING);
            if (all != null) {
                headers.remove(HttpHeaderNames.CONTENT_TYPE);
                for (String str : all) {
                    String lowerCase = str.toLowerCase();
                    if (!lowerCase.startsWith(HttpHeaderValues.MULTIPART_FORM_DATA.toString()) && !lowerCase.startsWith(HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())) {
                        headers.add(HttpHeaderNames.CONTENT_TYPE, str);
                    }
                }
            }
            if (this.isMultipart) {
                headers.add(HttpHeaderNames.CONTENT_TYPE, ((Object) HttpHeaderValues.MULTIPART_FORM_DATA) + "; " + ((Object) HttpHeaderValues.BOUNDARY) + '=' + this.multipartDataBoundary);
            } else {
                headers.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED);
            }
            long j = this.globalBodySize;
            if (!this.isMultipart) {
                j--;
            }
            this.iterator = this.multipartHttpDatas.listIterator();
            headers.set(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(j));
            if (j > 8096 || this.isMultipart) {
                this.isChunked = true;
                if (all2 != null) {
                    headers.remove(HttpHeaderNames.TRANSFER_ENCODING);
                    for (String str2 : all2) {
                        if (!HttpHeaderValues.CHUNKED.contentEqualsIgnoreCase(str2)) {
                            headers.add(HttpHeaderNames.TRANSFER_ENCODING, str2);
                        }
                    }
                }
                HttpUtil.setTransferEncodingChunked(this.request, true);
                return new WrappedHttpRequest(this.request);
            }
            HttpContent nextChunk = nextChunk();
            HttpRequest httpRequest = this.request;
            if (httpRequest instanceof FullHttpRequest) {
                FullHttpRequest fullHttpRequest = (FullHttpRequest) httpRequest;
                ByteBuf content = nextChunk.content();
                if (fullHttpRequest.content() != content) {
                    fullHttpRequest.content().clear().writeBytes(content);
                    content.release();
                }
                return fullHttpRequest;
            }
            return new WrappedFullHttpRequest(httpRequest, nextChunk);
        }
        throw new ErrorDataEncoderException("Header already encoded");
    }

    public boolean isChunked() {
        return this.isChunked;
    }

    private String encodeAttribute(String str, Charset charset) throws ErrorDataEncoderException {
        if (str == null) {
            return "";
        }
        try {
            String encode = URLEncoder.encode(str, charset.name());
            if (this.encoderMode == EncoderMode.RFC3986) {
                for (Map.Entry entry : percentEncodings) {
                    encode = ((Pattern) entry.getKey()).matcher(encode).replaceAll((String) entry.getValue());
                }
            }
            return encode;
        } catch (UnsupportedEncodingException e) {
            throw new ErrorDataEncoderException(charset.name(), e);
        }
    }

    private ByteBuf fillByteBuf() {
        if (this.currentBuffer.readableBytes() > 8096) {
            return this.currentBuffer.readRetainedSlice(8096);
        }
        ByteBuf byteBuf = this.currentBuffer;
        this.currentBuffer = null;
        return byteBuf;
    }

    private HttpContent encodeNextChunkMultipart(int i) throws ErrorDataEncoderException {
        ByteBuf chunk;
        InterfaceHttpData interfaceHttpData = this.currentData;
        if (interfaceHttpData == null) {
            return null;
        }
        if (interfaceHttpData instanceof InternalAttribute) {
            chunk = ((InternalAttribute) interfaceHttpData).toByteBuf();
            this.currentData = null;
        } else {
            try {
                chunk = ((HttpData) interfaceHttpData).getChunk(i);
                if (chunk.capacity() == 0) {
                    this.currentData = null;
                    return null;
                }
            } catch (IOException e) {
                throw new ErrorDataEncoderException(e);
            }
        }
        ByteBuf byteBuf = this.currentBuffer;
        if (byteBuf == null) {
            this.currentBuffer = chunk;
        } else {
            this.currentBuffer = Unpooled.wrappedBuffer(byteBuf, chunk);
        }
        if (this.currentBuffer.readableBytes() < 8096) {
            this.currentData = null;
            return null;
        }
        return new DefaultHttpContent(fillByteBuf());
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private HttpContent encodeNextChunkUrlEncoded(int i) throws ErrorDataEncoderException {
        ByteBuf byteBuf;
        InterfaceHttpData interfaceHttpData = this.currentData;
        if (interfaceHttpData == null) {
            return null;
        }
        if (this.isKey) {
            ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(interfaceHttpData.getName().getBytes());
            this.isKey = false;
            ByteBuf byteBuf2 = this.currentBuffer;
            if (byteBuf2 == null) {
                this.currentBuffer = Unpooled.wrappedBuffer(wrappedBuffer, Unpooled.wrappedBuffer("=".getBytes()));
            } else {
                this.currentBuffer = Unpooled.wrappedBuffer(byteBuf2, wrappedBuffer, Unpooled.wrappedBuffer("=".getBytes()));
            }
            i -= wrappedBuffer.readableBytes() + 1;
            if (this.currentBuffer.readableBytes() >= 8096) {
                return new DefaultHttpContent(fillByteBuf());
            }
        }
        try {
            ByteBuf chunk = ((HttpData) this.currentData).getChunk(i);
            if (chunk.readableBytes() < i) {
                this.isKey = true;
                if (this.iterator.hasNext()) {
                    byteBuf = Unpooled.wrappedBuffer("&".getBytes());
                    if (chunk.capacity() != 0) {
                        this.currentData = null;
                        ByteBuf byteBuf3 = this.currentBuffer;
                        if (byteBuf3 == null) {
                            if (byteBuf == null) {
                                return null;
                            }
                            this.currentBuffer = byteBuf;
                        } else if (byteBuf != null) {
                            this.currentBuffer = Unpooled.wrappedBuffer(byteBuf3, byteBuf);
                        }
                        if (this.currentBuffer.readableBytes() >= 8096) {
                            return new DefaultHttpContent(fillByteBuf());
                        }
                        return null;
                    }
                    ByteBuf byteBuf4 = this.currentBuffer;
                    if (byteBuf4 == null) {
                        if (byteBuf != null) {
                            this.currentBuffer = Unpooled.wrappedBuffer(chunk, byteBuf);
                        } else {
                            this.currentBuffer = chunk;
                        }
                    } else if (byteBuf != null) {
                        this.currentBuffer = Unpooled.wrappedBuffer(byteBuf4, chunk, byteBuf);
                    } else {
                        this.currentBuffer = Unpooled.wrappedBuffer(byteBuf4, chunk);
                    }
                    if (this.currentBuffer.readableBytes() < 8096) {
                        this.currentData = null;
                        this.isKey = true;
                        return null;
                    }
                    return new DefaultHttpContent(fillByteBuf());
                }
            }
            byteBuf = null;
            if (chunk.capacity() != 0) {
            }
        } catch (IOException e) {
            throw new ErrorDataEncoderException(e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    @Deprecated
    public HttpContent readChunk(ChannelHandlerContext channelHandlerContext) throws Exception {
        return readChunk(channelHandlerContext.alloc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public HttpContent readChunk(ByteBufAllocator byteBufAllocator) throws Exception {
        if (this.isLastChunkSent) {
            return null;
        }
        HttpContent nextChunk = nextChunk();
        this.globalProgress += nextChunk.content().readableBytes();
        return nextChunk;
    }

    private HttpContent nextChunk() throws ErrorDataEncoderException {
        HttpContent encodeNextChunkUrlEncoded;
        HttpContent encodeNextChunkUrlEncoded2;
        if (this.isLastChunk) {
            this.isLastChunkSent = true;
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        int calculateRemainingSize = calculateRemainingSize();
        if (calculateRemainingSize <= 0) {
            return new DefaultHttpContent(fillByteBuf());
        }
        if (this.currentData != null) {
            if (this.isMultipart) {
                encodeNextChunkUrlEncoded2 = encodeNextChunkMultipart(calculateRemainingSize);
            } else {
                encodeNextChunkUrlEncoded2 = encodeNextChunkUrlEncoded(calculateRemainingSize);
            }
            if (encodeNextChunkUrlEncoded2 != null) {
                return encodeNextChunkUrlEncoded2;
            }
            calculateRemainingSize = calculateRemainingSize();
        }
        if (!this.iterator.hasNext()) {
            return lastChunk();
        }
        while (calculateRemainingSize > 0 && this.iterator.hasNext()) {
            this.currentData = this.iterator.next();
            if (this.isMultipart) {
                encodeNextChunkUrlEncoded = encodeNextChunkMultipart(calculateRemainingSize);
            } else {
                encodeNextChunkUrlEncoded = encodeNextChunkUrlEncoded(calculateRemainingSize);
            }
            if (encodeNextChunkUrlEncoded != null) {
                return encodeNextChunkUrlEncoded;
            }
            calculateRemainingSize = calculateRemainingSize();
        }
        return lastChunk();
    }

    private int calculateRemainingSize() {
        ByteBuf byteBuf = this.currentBuffer;
        if (byteBuf != null) {
            return 8096 - byteBuf.readableBytes();
        }
        return 8096;
    }

    private HttpContent lastChunk() {
        this.isLastChunk = true;
        ByteBuf byteBuf = this.currentBuffer;
        if (byteBuf == null) {
            this.isLastChunkSent = true;
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        this.currentBuffer = null;
        return new DefaultHttpContent(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        return this.isLastChunkSent;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long length() {
        return this.isMultipart ? this.globalBodySize : this.globalBodySize - 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long progress() {
        return this.globalProgress;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class ErrorDataEncoderException extends Exception {
        private static final long serialVersionUID = 5020247425493164465L;

        public ErrorDataEncoderException() {
        }

        public ErrorDataEncoderException(String str) {
            super(str);
        }

        public ErrorDataEncoderException(Throwable th) {
            super(th);
        }

        public ErrorDataEncoderException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class WrappedHttpRequest implements HttpRequest {
        private final HttpRequest request;

        WrappedHttpRequest(HttpRequest httpRequest) {
            this.request = httpRequest;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public HttpRequest setProtocolVersion(HttpVersion httpVersion) {
            this.request.setProtocolVersion(httpVersion);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public HttpRequest setMethod(HttpMethod httpMethod) {
            this.request.setMethod(httpMethod);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public HttpRequest setUri(String str) {
            this.request.setUri(str);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public HttpMethod getMethod() {
            return this.request.method();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public HttpMethod method() {
            return this.request.method();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public String getUri() {
            return this.request.uri();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public String uri() {
            return this.request.uri();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage
        public HttpVersion getProtocolVersion() {
            return this.request.protocolVersion();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage
        public HttpVersion protocolVersion() {
            return this.request.protocolVersion();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage
        public HttpHeaders headers() {
            return this.request.headers();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
        public DecoderResult decoderResult() {
            return this.request.decoderResult();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObject
        @Deprecated
        public DecoderResult getDecoderResult() {
            return this.request.getDecoderResult();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
        public void setDecoderResult(DecoderResult decoderResult) {
            this.request.setDecoderResult(decoderResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class WrappedFullHttpRequest extends WrappedHttpRequest implements FullHttpRequest {
        private final HttpContent content;

        private WrappedFullHttpRequest(HttpRequest httpRequest, HttpContent httpContent) {
            super(httpRequest);
            this.content = httpContent;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.WrappedHttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpRequest setProtocolVersion(HttpVersion httpVersion) {
            super.setProtocolVersion(httpVersion);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.WrappedHttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpRequest setMethod(HttpMethod httpMethod) {
            super.setMethod(httpMethod);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.WrappedHttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpRequest setUri(String str) {
            super.setUri(str);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest copy() {
            return replace(content().copy());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest duplicate() {
            return replace(content().duplicate());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest retainedDuplicate() {
            return replace(content().retainedDuplicate());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest replace(ByteBuf byteBuf) {
            DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(protocolVersion(), method(), uri(), byteBuf);
            defaultFullHttpRequest.headers().set(headers());
            defaultFullHttpRequest.trailingHeaders().set(trailingHeaders());
            return defaultFullHttpRequest;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest retain(int i) {
            this.content.retain(i);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest retain() {
            this.content.retain();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest touch() {
            this.content.touch();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest touch(Object obj) {
            this.content.touch(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public ByteBuf content() {
            return this.content.content();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent
        public HttpHeaders trailingHeaders() {
            HttpContent httpContent = this.content;
            if (httpContent instanceof LastHttpContent) {
                return ((LastHttpContent) httpContent).trailingHeaders();
            }
            return EmptyHttpHeaders.INSTANCE;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public int refCnt() {
            return this.content.refCnt();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public boolean release() {
            return this.content.release();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public boolean release(int i) {
            return this.content.release(i);
        }
    }
}
