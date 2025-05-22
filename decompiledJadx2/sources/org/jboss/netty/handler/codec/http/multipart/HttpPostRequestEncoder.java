package org.jboss.netty.handler.codec.http.multipart;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.apache.http.protocol.HTTP;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpChunk;
import org.jboss.netty.handler.codec.http.HttpChunk;
import org.jboss.netty.handler.codec.http.HttpConstants;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import org.jboss.netty.handler.stream.ChunkedInput;

/* loaded from: classes7.dex */
public class HttpPostRequestEncoder implements ChunkedInput {
    private final List<InterfaceHttpData> bodyListDatas;
    private final Charset charset;
    private ChannelBuffer currentBuffer;
    private InterfaceHttpData currentData;
    private FileUpload currentFileUpload;
    private boolean duringMixedMode;
    private final HttpDataFactory factory;
    private long globalBodySize;
    private boolean headerFinalized;
    private boolean isChunked;
    private boolean isKey;
    private boolean isLastChunk;
    private boolean isLastChunkSent;
    private final boolean isMultipart;
    private ListIterator<InterfaceHttpData> iterator;
    private String multipartDataBoundary;
    private final List<InterfaceHttpData> multipartHttpDatas;
    private String multipartMixedBoundary;
    private final HttpRequest request;

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
    }

    public HttpPostRequestEncoder(HttpRequest httpRequest, boolean z) throws ErrorDataEncoderException {
        this(new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE), httpRequest, z, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostRequestEncoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, boolean z) throws ErrorDataEncoderException {
        this(httpDataFactory, httpRequest, z, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostRequestEncoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, boolean z, Charset charset) throws ErrorDataEncoderException {
        this.isKey = true;
        if (httpDataFactory == null) {
            throw new NullPointerException("factory");
        }
        if (httpRequest == null) {
            throw new NullPointerException("request");
        }
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        if (httpRequest.getMethod() != HttpMethod.POST) {
            throw new ErrorDataEncoderException("Cannot create a Encoder if not a POST");
        }
        this.request = httpRequest;
        this.charset = charset;
        this.factory = httpDataFactory;
        this.bodyListDatas = new ArrayList();
        this.isLastChunk = false;
        this.isLastChunkSent = false;
        this.isMultipart = z;
        this.multipartHttpDatas = new ArrayList();
        if (this.isMultipart) {
            initDataMultipart();
        }
    }

    public void cleanFiles() {
        this.factory.cleanRequestHttpDatas(this.request);
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
        return Long.toHexString(new Random().nextLong()).toLowerCase();
    }

    public List<InterfaceHttpData> getBodyListAttributes() {
        return this.bodyListDatas;
    }

    public void setBodyHttpDatas(List<InterfaceHttpData> list) throws ErrorDataEncoderException {
        if (list == null) {
            throw new NullPointerException("datas");
        }
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
        if (str == null) {
            throw new NullPointerException("name");
        }
        if (str2 == null) {
            str2 = "";
        }
        addBodyHttpData(this.factory.createAttribute(this.request, str, str2));
    }

    public void addBodyFileUpload(String str, File file, String str2, boolean z) throws ErrorDataEncoderException {
        if (str == null) {
            throw new NullPointerException("name");
        }
        if (file == null) {
            throw new NullPointerException("file");
        }
        if (str2 == null) {
            str2 = z ? "text/plain" : "application/octet-stream";
        }
        FileUpload createFileUpload = this.factory.createFileUpload(this.request, str, file.getName(), str2, z ? null : HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value, null, file.length());
        try {
            createFileUpload.setContent(file);
            addBodyHttpData(createFileUpload);
        } catch (IOException e) {
            throw new ErrorDataEncoderException(e);
        }
    }

    public void addBodyFileUploads(String str, File[] fileArr, String[] strArr, boolean[] zArr) throws ErrorDataEncoderException {
        if (fileArr.length != strArr.length && fileArr.length != zArr.length) {
            throw new NullPointerException("Different array length");
        }
        for (int i = 0; i < fileArr.length; i++) {
            addBodyFileUpload(str, fileArr[i], strArr[i], zArr[i]);
        }
    }

    public void addBodyHttpData(InterfaceHttpData interfaceHttpData) throws ErrorDataEncoderException {
        String str;
        if (this.headerFinalized) {
            throw new ErrorDataEncoderException("Cannot add value once finalized");
        }
        if (interfaceHttpData == null) {
            throw new NullPointerException("data");
        }
        this.bodyListDatas.add(interfaceHttpData);
        boolean z = true;
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
                FileUpload fileUpload = (FileUpload) interfaceHttpData;
                Attribute createAttribute2 = this.factory.createAttribute(this.request, encodeAttribute(fileUpload.getName(), this.charset), encodeAttribute(fileUpload.getFilename(), this.charset));
                this.multipartHttpDatas.add(createAttribute2);
                this.globalBodySize += createAttribute2.getName().length() + 1 + createAttribute2.length() + 1;
                return;
            }
            return;
        }
        if (interfaceHttpData instanceof Attribute) {
            if (this.duringMixedMode) {
                InternalAttribute internalAttribute = new InternalAttribute();
                internalAttribute.addValue("\r\n--" + this.multipartMixedBoundary + "--");
                this.multipartHttpDatas.add(internalAttribute);
                this.multipartMixedBoundary = null;
                this.currentFileUpload = null;
                this.duringMixedMode = false;
            }
            InternalAttribute internalAttribute2 = new InternalAttribute();
            if (this.multipartHttpDatas.size() > 0) {
                internalAttribute2.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
            }
            internalAttribute2.addValue("--" + this.multipartDataBoundary + IOUtils.LINE_SEPARATOR_WINDOWS);
            Attribute attribute2 = (Attribute) interfaceHttpData;
            internalAttribute2.addValue("Content-Disposition: form-data; name=\"" + encodeAttribute(attribute2.getName(), this.charset) + "\"\r\n");
            Charset charset = attribute2.getCharset();
            if (charset != null) {
                internalAttribute2.addValue("Content-Type: charset=" + charset + IOUtils.LINE_SEPARATOR_WINDOWS);
            }
            internalAttribute2.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
            this.multipartHttpDatas.add(internalAttribute2);
            this.multipartHttpDatas.add(interfaceHttpData);
            this.globalBodySize += attribute2.length() + internalAttribute2.size();
            return;
        }
        if (interfaceHttpData instanceof FileUpload) {
            FileUpload fileUpload2 = (FileUpload) interfaceHttpData;
            InternalAttribute internalAttribute3 = new InternalAttribute();
            if (this.multipartHttpDatas.size() > 0) {
                internalAttribute3.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
            }
            if (this.duringMixedMode) {
                FileUpload fileUpload3 = this.currentFileUpload;
                if (fileUpload3 == null || !fileUpload3.getName().equals(fileUpload2.getName())) {
                    internalAttribute3.addValue("--" + this.multipartMixedBoundary + "--");
                    this.multipartHttpDatas.add(internalAttribute3);
                    this.multipartMixedBoundary = null;
                    internalAttribute3 = new InternalAttribute();
                    internalAttribute3.addValue(IOUtils.LINE_SEPARATOR_WINDOWS);
                    this.currentFileUpload = fileUpload2;
                    this.duringMixedMode = false;
                    str = "Content-Disposition: form-data; name=\"";
                    z = false;
                } else {
                    str = "Content-Disposition: form-data; name=\"";
                }
            } else {
                FileUpload fileUpload4 = this.currentFileUpload;
                if (fileUpload4 != null && fileUpload4.getName().equals(fileUpload2.getName())) {
                    initMixedMultipart();
                    InternalAttribute internalAttribute4 = (InternalAttribute) this.multipartHttpDatas.get(r5.size() - 2);
                    str = "Content-Disposition: form-data; name=\"";
                    this.globalBodySize -= internalAttribute4.size();
                    internalAttribute4.setValue((((str + encodeAttribute(fileUpload2.getName(), this.charset) + "\"\r\n") + "Content-Type: multipart/mixed; boundary=" + this.multipartMixedBoundary + "\r\n\r\n") + "--" + this.multipartMixedBoundary + IOUtils.LINE_SEPARATOR_WINDOWS) + "Content-Disposition: file; filename=\"" + encodeAttribute(fileUpload2.getFilename(), this.charset) + "\"\r\n", 1);
                    this.globalBodySize = this.globalBodySize + ((long) internalAttribute4.size());
                    z = true;
                    this.duringMixedMode = true;
                } else {
                    str = "Content-Disposition: form-data; name=\"";
                    this.currentFileUpload = fileUpload2;
                    this.duringMixedMode = false;
                    z = false;
                }
            }
            if (z) {
                internalAttribute3.addValue("--" + this.multipartMixedBoundary + IOUtils.LINE_SEPARATOR_WINDOWS);
                internalAttribute3.addValue("Content-Disposition: file; filename=\"" + encodeAttribute(fileUpload2.getFilename(), this.charset) + "\"\r\n");
            } else {
                internalAttribute3.addValue("--" + this.multipartDataBoundary + IOUtils.LINE_SEPARATOR_WINDOWS);
                internalAttribute3.addValue(str + encodeAttribute(fileUpload2.getName(), this.charset) + "\"; filename=\"" + encodeAttribute(fileUpload2.getFilename(), this.charset) + "\"\r\n");
            }
            internalAttribute3.addValue("Content-Type: " + fileUpload2.getContentType());
            String contentTransferEncoding = fileUpload2.getContentTransferEncoding();
            if (contentTransferEncoding != null && contentTransferEncoding.equals(HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value)) {
                internalAttribute3.addValue("\r\nContent-Transfer-Encoding: " + HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value + "\r\n\r\n");
            } else if (fileUpload2.getCharset() != null) {
                internalAttribute3.addValue(HTTP.CHARSET_PARAM + fileUpload2.getCharset() + "\r\n\r\n");
            } else {
                internalAttribute3.addValue("\r\n\r\n");
            }
            this.multipartHttpDatas.add(internalAttribute3);
            this.multipartHttpDatas.add(interfaceHttpData);
            this.globalBodySize += fileUpload2.length() + internalAttribute3.size();
        }
    }

    public HttpRequest finalizeRequest() throws ErrorDataEncoderException {
        if (!this.headerFinalized) {
            if (this.isMultipart) {
                InternalAttribute internalAttribute = new InternalAttribute();
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
            List<String> headers = this.request.getHeaders("Content-Type");
            List<String> headers2 = this.request.getHeaders("Transfer-Encoding");
            if (headers != null) {
                this.request.removeHeader("Content-Type");
                for (String str : headers) {
                    if (!str.toLowerCase().startsWith("multipart/form-data") && !str.toLowerCase().startsWith("application/x-www-form-urlencoded")) {
                        this.request.addHeader("Content-Type", str);
                    }
                }
            }
            if (this.isMultipart) {
                this.request.addHeader("Content-Type", "multipart/form-data; boundary=" + this.multipartDataBoundary);
            } else {
                this.request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            }
            long j = this.globalBodySize;
            if (this.isMultipart) {
                this.iterator = this.multipartHttpDatas.listIterator();
            } else {
                j--;
                this.iterator = this.multipartHttpDatas.listIterator();
            }
            this.request.setHeader("Content-Length", String.valueOf(j));
            if (j > HttpPostBodyUtil.chunkSize) {
                this.isChunked = true;
                if (headers2 != null) {
                    this.request.removeHeader("Transfer-Encoding");
                    for (String str2 : headers2) {
                        if (!str2.equalsIgnoreCase("chunked")) {
                            this.request.addHeader("Transfer-Encoding", str2);
                        }
                    }
                }
                this.request.addHeader("Transfer-Encoding", "chunked");
                this.request.setContent(ChannelBuffers.EMPTY_BUFFER);
            } else {
                this.request.setContent(nextChunk().getContent());
            }
            return this.request;
        }
        throw new ErrorDataEncoderException("Header already encoded");
    }

    public boolean isChunked() {
        return this.isChunked;
    }

    private static String encodeAttribute(String str, Charset charset) throws ErrorDataEncoderException {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, charset.name());
        } catch (UnsupportedEncodingException e) {
            throw new ErrorDataEncoderException(charset.name(), e);
        }
    }

    private ChannelBuffer fillChannelBuffer() {
        if (this.currentBuffer.readableBytes() > HttpPostBodyUtil.chunkSize) {
            ChannelBuffer channelBuffer = this.currentBuffer;
            ChannelBuffer slice = channelBuffer.slice(channelBuffer.readerIndex(), HttpPostBodyUtil.chunkSize);
            this.currentBuffer.skipBytes(HttpPostBodyUtil.chunkSize);
            return slice;
        }
        ChannelBuffer channelBuffer2 = this.currentBuffer;
        this.currentBuffer = null;
        return channelBuffer2;
    }

    private HttpChunk encodeNextChunkMultipart(int i) throws ErrorDataEncoderException {
        ChannelBuffer chunk;
        InterfaceHttpData interfaceHttpData = this.currentData;
        if (interfaceHttpData == null) {
            return null;
        }
        if (interfaceHttpData instanceof InternalAttribute) {
            try {
                chunk = ChannelBuffers.wrappedBuffer(((InternalAttribute) interfaceHttpData).toString().getBytes(HTTP.ASCII));
                this.currentData = null;
            } catch (UnsupportedEncodingException e) {
                throw new ErrorDataEncoderException(e);
            }
        } else {
            if (interfaceHttpData instanceof Attribute) {
                try {
                    chunk = ((Attribute) interfaceHttpData).getChunk(i);
                } catch (IOException e2) {
                    throw new ErrorDataEncoderException(e2);
                }
            } else {
                try {
                    chunk = ((FileUpload) interfaceHttpData).getChunk(i);
                } catch (IOException e3) {
                    throw new ErrorDataEncoderException(e3);
                }
            }
            if (chunk.capacity() == 0) {
                this.currentData = null;
                return null;
            }
        }
        ChannelBuffer channelBuffer = this.currentBuffer;
        if (channelBuffer == null) {
            this.currentBuffer = chunk;
        } else {
            this.currentBuffer = ChannelBuffers.wrappedBuffer(channelBuffer, chunk);
        }
        if (this.currentBuffer.readableBytes() < HttpPostBodyUtil.chunkSize) {
            this.currentData = null;
            return null;
        }
        return new DefaultHttpChunk(fillChannelBuffer());
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private HttpChunk encodeNextChunkUrlEncoded(int i) throws ErrorDataEncoderException {
        ChannelBuffer channelBuffer;
        int readableBytes;
        InterfaceHttpData interfaceHttpData = this.currentData;
        if (interfaceHttpData == null) {
            return null;
        }
        if (this.isKey) {
            ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(interfaceHttpData.getName().getBytes());
            this.isKey = false;
            ChannelBuffer channelBuffer2 = this.currentBuffer;
            if (channelBuffer2 == null) {
                this.currentBuffer = ChannelBuffers.wrappedBuffer(wrappedBuffer, ChannelBuffers.wrappedBuffer("=".getBytes()));
                readableBytes = wrappedBuffer.readableBytes();
            } else {
                this.currentBuffer = ChannelBuffers.wrappedBuffer(channelBuffer2, wrappedBuffer, ChannelBuffers.wrappedBuffer("=".getBytes()));
                readableBytes = wrappedBuffer.readableBytes();
            }
            i -= readableBytes + 1;
            if (this.currentBuffer.readableBytes() >= HttpPostBodyUtil.chunkSize) {
                return new DefaultHttpChunk(fillChannelBuffer());
            }
        }
        try {
            ChannelBuffer chunk = ((Attribute) this.currentData).getChunk(i);
            if (chunk.readableBytes() < i) {
                this.isKey = true;
                if (this.iterator.hasNext()) {
                    channelBuffer = ChannelBuffers.wrappedBuffer("&".getBytes());
                    if (chunk.capacity() != 0) {
                        this.currentData = null;
                        ChannelBuffer channelBuffer3 = this.currentBuffer;
                        if (channelBuffer3 == null) {
                            this.currentBuffer = channelBuffer;
                        } else if (channelBuffer != null) {
                            this.currentBuffer = ChannelBuffers.wrappedBuffer(channelBuffer3, channelBuffer);
                        }
                        if (this.currentBuffer.readableBytes() >= HttpPostBodyUtil.chunkSize) {
                            return new DefaultHttpChunk(fillChannelBuffer());
                        }
                        return null;
                    }
                    ChannelBuffer channelBuffer4 = this.currentBuffer;
                    if (channelBuffer4 == null) {
                        if (channelBuffer != null) {
                            this.currentBuffer = ChannelBuffers.wrappedBuffer(chunk, channelBuffer);
                        } else {
                            this.currentBuffer = chunk;
                        }
                    } else if (channelBuffer != null) {
                        this.currentBuffer = ChannelBuffers.wrappedBuffer(channelBuffer4, chunk, channelBuffer);
                    } else {
                        this.currentBuffer = ChannelBuffers.wrappedBuffer(channelBuffer4, chunk);
                    }
                    if (this.currentBuffer.readableBytes() < HttpPostBodyUtil.chunkSize) {
                        this.currentData = null;
                        this.isKey = true;
                        return null;
                    }
                    return new DefaultHttpChunk(fillChannelBuffer());
                }
            }
            channelBuffer = null;
            if (chunk.capacity() != 0) {
            }
        } catch (IOException e) {
            throw new ErrorDataEncoderException(e);
        }
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public HttpChunk nextChunk() throws ErrorDataEncoderException {
        HttpChunk encodeNextChunkUrlEncoded;
        if (this.isLastChunk) {
            this.isLastChunkSent = true;
            return new DefaultHttpChunk(ChannelBuffers.EMPTY_BUFFER);
        }
        int i = HttpPostBodyUtil.chunkSize;
        ChannelBuffer channelBuffer = this.currentBuffer;
        if (channelBuffer != null) {
            i -= channelBuffer.readableBytes();
        }
        if (i <= 0) {
            return new DefaultHttpChunk(fillChannelBuffer());
        }
        if (this.currentData != null) {
            if (this.isMultipart) {
                HttpChunk encodeNextChunkMultipart = encodeNextChunkMultipart(i);
                if (encodeNextChunkMultipart != null) {
                    return encodeNextChunkMultipart;
                }
            } else {
                HttpChunk encodeNextChunkUrlEncoded2 = encodeNextChunkUrlEncoded(i);
                if (encodeNextChunkUrlEncoded2 != null) {
                    return encodeNextChunkUrlEncoded2;
                }
            }
            i = HttpPostBodyUtil.chunkSize - this.currentBuffer.readableBytes();
        }
        if (!this.iterator.hasNext()) {
            this.isLastChunk = true;
            ChannelBuffer channelBuffer2 = this.currentBuffer;
            this.currentBuffer = null;
            return new DefaultHttpChunk(channelBuffer2);
        }
        while (i > 0 && this.iterator.hasNext()) {
            this.currentData = this.iterator.next();
            if (this.isMultipart) {
                encodeNextChunkUrlEncoded = encodeNextChunkMultipart(i);
            } else {
                encodeNextChunkUrlEncoded = encodeNextChunkUrlEncoded(i);
            }
            if (encodeNextChunkUrlEncoded != null) {
                return encodeNextChunkUrlEncoded;
            }
            i = HttpPostBodyUtil.chunkSize - this.currentBuffer.readableBytes();
        }
        this.isLastChunk = true;
        ChannelBuffer channelBuffer3 = this.currentBuffer;
        if (channelBuffer3 == null) {
            this.isLastChunkSent = true;
            return new DefaultHttpChunk(ChannelBuffers.EMPTY_BUFFER);
        }
        this.currentBuffer = null;
        return new DefaultHttpChunk(channelBuffer3);
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        return this.isLastChunkSent;
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean hasNextChunk() throws Exception {
        return !this.isLastChunkSent;
    }

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
}
