package org.jboss.netty.handler.codec.http;

import java.util.List;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;

/* loaded from: classes7.dex */
public abstract class HttpMessageDecoder extends ReplayingDecoder<State> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long chunkSize;
    private ChannelBuffer content;
    private int contentRead;
    private int headerSize;
    private final int maxChunkSize;
    private final int maxHeaderSize;
    private final int maxInitialLineLength;
    private HttpMessage message;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes7.dex */
    public enum State {
        SKIP_CONTROL_CHARS,
        READ_INITIAL,
        READ_HEADER,
        READ_VARIABLE_LENGTH_CONTENT,
        READ_VARIABLE_LENGTH_CONTENT_AS_CHUNKS,
        READ_FIXED_LENGTH_CONTENT,
        READ_FIXED_LENGTH_CONTENT_AS_CHUNKS,
        READ_CHUNK_SIZE,
        READ_CHUNKED_CONTENT,
        READ_CHUNKED_CONTENT_AS_CHUNKS,
        READ_CHUNK_DELIMITER,
        READ_CHUNK_FOOTER
    }

    protected abstract HttpMessage createMessage(String[] strArr) throws Exception;

    protected abstract boolean isDecodingRequest();

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpMessageDecoder() {
        this(4096, 8192, 8192);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpMessageDecoder(int i, int i2, int i3) {
        super(State.SKIP_CONTROL_CHARS, true);
        if (i <= 0) {
            throw new IllegalArgumentException("maxInitialLineLength must be a positive integer: " + i);
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxHeaderSize must be a positive integer: " + i2);
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("maxChunkSize must be a positive integer: " + i3);
        }
        this.maxInitialLineLength = i;
        this.maxHeaderSize = i2;
        this.maxChunkSize = i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000c. Please report as an issue. */
    @Override // org.jboss.netty.handler.codec.replay.ReplayingDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, State state) throws Exception {
        switch (state) {
            case READ_FIXED_LENGTH_CONTENT:
                return readFixedLengthContent(channelBuffer);
            case READ_VARIABLE_LENGTH_CONTENT:
                int actualReadableBytes = actualReadableBytes();
                int i = this.maxChunkSize;
                if (actualReadableBytes > i) {
                    actualReadableBytes = i;
                }
                if (!this.message.isChunked()) {
                    this.message.setChunked(true);
                    return new Object[]{this.message, new DefaultHttpChunk(channelBuffer.readBytes(actualReadableBytes))};
                }
                return new DefaultHttpChunk(channelBuffer.readBytes(actualReadableBytes));
            case SKIP_CONTROL_CHARS:
                try {
                    skipControlCharacters(channelBuffer);
                    checkpoint(State.READ_INITIAL);
                } finally {
                    checkpoint();
                }
            case READ_INITIAL:
                String[] splitInitialLine = splitInitialLine(readLine(channelBuffer, this.maxInitialLineLength));
                if (splitInitialLine.length < 3) {
                    checkpoint(State.SKIP_CONTROL_CHARS);
                    return null;
                }
                this.message = createMessage(splitInitialLine);
                checkpoint(State.READ_HEADER);
            case READ_HEADER:
                State readHeaders = readHeaders(channelBuffer);
                checkpoint(readHeaders);
                if (readHeaders == State.READ_CHUNK_SIZE) {
                    this.message.setChunked(true);
                    return this.message;
                }
                if (readHeaders == State.SKIP_CONTROL_CHARS) {
                    this.message.removeHeader("Transfer-Encoding");
                    return this.message;
                }
                long contentLength = HttpHeaders.getContentLength(this.message, -1L);
                if (contentLength == 0 || (contentLength == -1 && isDecodingRequest())) {
                    this.content = ChannelBuffers.EMPTY_BUFFER;
                    return reset();
                }
                int i2 = C87101.f10033x5907aa57[readHeaders.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (channelBuffer.readableBytes() > this.maxChunkSize || HttpHeaders.is100ContinueExpected(this.message)) {
                            checkpoint(State.READ_VARIABLE_LENGTH_CONTENT_AS_CHUNKS);
                            this.message.setChunked(true);
                            return this.message;
                        }
                    } else {
                        throw new IllegalStateException("Unexpected state: " + readHeaders);
                    }
                } else if (contentLength > this.maxChunkSize || HttpHeaders.is100ContinueExpected(this.message)) {
                    checkpoint(State.READ_FIXED_LENGTH_CONTENT_AS_CHUNKS);
                    this.message.setChunked(true);
                    this.chunkSize = HttpHeaders.getContentLength(this.message, -1L);
                    return this.message;
                }
                return null;
            case READ_VARIABLE_LENGTH_CONTENT_AS_CHUNKS:
                int actualReadableBytes2 = actualReadableBytes();
                int i3 = this.maxChunkSize;
                if (actualReadableBytes2 > i3) {
                    actualReadableBytes2 = i3;
                }
                DefaultHttpChunk defaultHttpChunk = new DefaultHttpChunk(channelBuffer.readBytes(actualReadableBytes2));
                if (!channelBuffer.readable()) {
                    reset();
                    if (!defaultHttpChunk.isLast()) {
                        return new Object[]{defaultHttpChunk, HttpChunk.LAST_CHUNK};
                    }
                }
                return defaultHttpChunk;
            case READ_FIXED_LENGTH_CONTENT_AS_CHUNKS:
                int i4 = (int) this.chunkSize;
                int actualReadableBytes3 = actualReadableBytes();
                if (actualReadableBytes3 == 0) {
                    return null;
                }
                int i5 = this.maxChunkSize;
                if (i4 <= i5) {
                    i5 = i4;
                }
                if (i5 > actualReadableBytes3) {
                    i5 = actualReadableBytes3;
                }
                DefaultHttpChunk defaultHttpChunk2 = new DefaultHttpChunk(channelBuffer.readBytes(i5));
                int i6 = i4 > i5 ? i4 - i5 : 0;
                this.chunkSize = i6;
                if (i6 == 0) {
                    reset();
                    if (!defaultHttpChunk2.isLast()) {
                        return new Object[]{defaultHttpChunk2, HttpChunk.LAST_CHUNK};
                    }
                }
                return defaultHttpChunk2;
            case READ_CHUNK_SIZE:
                int chunkSize = getChunkSize(readLine(channelBuffer, this.maxInitialLineLength));
                this.chunkSize = chunkSize;
                if (chunkSize == 0) {
                    checkpoint(State.READ_CHUNK_FOOTER);
                    return null;
                }
                if (chunkSize > this.maxChunkSize) {
                    checkpoint(State.READ_CHUNKED_CONTENT_AS_CHUNKS);
                } else {
                    checkpoint(State.READ_CHUNKED_CONTENT);
                }
            case READ_CHUNKED_CONTENT:
                DefaultHttpChunk defaultHttpChunk3 = new DefaultHttpChunk(channelBuffer.readBytes((int) this.chunkSize));
                checkpoint(State.READ_CHUNK_DELIMITER);
                return defaultHttpChunk3;
            case READ_CHUNKED_CONTENT_AS_CHUNKS:
                int i7 = (int) this.chunkSize;
                int actualReadableBytes4 = actualReadableBytes();
                if (actualReadableBytes4 == 0) {
                    return null;
                }
                int i8 = this.maxChunkSize;
                if (i7 <= i8) {
                    i8 = i7;
                }
                if (i8 <= actualReadableBytes4) {
                    actualReadableBytes4 = i8;
                }
                DefaultHttpChunk defaultHttpChunk4 = new DefaultHttpChunk(channelBuffer.readBytes(actualReadableBytes4));
                int i9 = i7 > actualReadableBytes4 ? i7 - actualReadableBytes4 : 0;
                this.chunkSize = i9;
                if (i9 == 0) {
                    checkpoint(State.READ_CHUNK_DELIMITER);
                }
                if (!defaultHttpChunk4.isLast()) {
                    return defaultHttpChunk4;
                }
            case READ_CHUNK_DELIMITER:
                while (true) {
                    byte readByte = channelBuffer.readByte();
                    if (readByte == 13) {
                        if (channelBuffer.readByte() == 10) {
                            checkpoint(State.READ_CHUNK_SIZE);
                            return null;
                        }
                    } else if (readByte == 10) {
                        checkpoint(State.READ_CHUNK_SIZE);
                        return null;
                    }
                }
            case READ_CHUNK_FOOTER:
                HttpChunkTrailer readTrailingHeaders = readTrailingHeaders(channelBuffer);
                if (this.maxChunkSize == 0) {
                    return reset();
                }
                reset();
                return readTrailingHeaders;
            default:
                throw new Error("Shouldn't reach here.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        if (httpMessage instanceof HttpResponse) {
            HttpResponse httpResponse = (HttpResponse) httpMessage;
            int code = httpResponse.getStatus().getCode();
            if (code >= 100 && code < 200) {
                return code != 101 || httpResponse.containsHeader("Sec-WebSocket-Accept");
            }
            if (code == 204 || code == 205 || code == 304) {
                return true;
            }
        }
        return false;
    }

    private Object reset() {
        HttpMessage httpMessage = this.message;
        ChannelBuffer channelBuffer = this.content;
        if (channelBuffer != null) {
            httpMessage.setContent(channelBuffer);
            this.content = null;
        }
        this.message = null;
        checkpoint(State.SKIP_CONTROL_CHARS);
        return httpMessage;
    }

    private static void skipControlCharacters(ChannelBuffer channelBuffer) {
        while (true) {
            char readUnsignedByte = (char) channelBuffer.readUnsignedByte();
            if (!Character.isISOControl(readUnsignedByte) && !Character.isWhitespace(readUnsignedByte)) {
                channelBuffer.readerIndex(channelBuffer.readerIndex() - 1);
                return;
            }
        }
    }

    private Object readFixedLengthContent(ChannelBuffer channelBuffer) {
        long contentLength = HttpHeaders.getContentLength(this.message, -1L);
        int i = (int) contentLength;
        int i2 = i - this.contentRead;
        if (i2 > actualReadableBytes()) {
            i2 = actualReadableBytes();
        }
        this.contentRead += i2;
        if (contentLength < this.contentRead) {
            if (!this.message.isChunked()) {
                this.message.setChunked(true);
                return new Object[]{this.message, new DefaultHttpChunk(read(channelBuffer, i2))};
            }
            return new DefaultHttpChunk(read(channelBuffer, i2));
        }
        ChannelBuffer channelBuffer2 = this.content;
        if (channelBuffer2 == null) {
            this.content = read(channelBuffer, i);
        } else {
            channelBuffer2.writeBytes(channelBuffer.readBytes(i));
        }
        return reset();
    }

    private ChannelBuffer read(ChannelBuffer channelBuffer, int i) {
        ChannelBuffer internalBuffer = internalBuffer();
        if (internalBuffer.readableBytes() >= i) {
            int readerIndex = internalBuffer.readerIndex();
            ChannelBuffer slice = internalBuffer.slice(readerIndex, i);
            channelBuffer.readerIndex(readerIndex + i);
            return slice;
        }
        return channelBuffer.readBytes(i);
    }

    private State readHeaders(ChannelBuffer channelBuffer) throws TooLongFrameException {
        String str;
        this.headerSize = 0;
        HttpMessage httpMessage = this.message;
        String readHeader = readHeader(channelBuffer);
        if (readHeader.length() != 0) {
            httpMessage.clearHeaders();
            String str2 = null;
            String str3 = null;
            do {
                char charAt = readHeader.charAt(0);
                if (str2 != null && (charAt == ' ' || charAt == '\t')) {
                    str = str3 + ' ' + readHeader.trim();
                } else {
                    if (str2 != null) {
                        httpMessage.addHeader(str2, str3);
                    }
                    String[] splitHeader = splitHeader(readHeader);
                    str2 = splitHeader[0];
                    str = splitHeader[1];
                }
                str3 = str;
                readHeader = readHeader(channelBuffer);
            } while (readHeader.length() != 0);
            if (str2 != null) {
                httpMessage.addHeader(str2, str3);
            }
        }
        if (isContentAlwaysEmpty(httpMessage)) {
            return State.SKIP_CONTROL_CHARS;
        }
        if (httpMessage.isChunked()) {
            return State.READ_CHUNK_SIZE;
        }
        if (HttpHeaders.getContentLength(httpMessage, -1L) >= 0) {
            return State.READ_FIXED_LENGTH_CONTENT;
        }
        return State.READ_VARIABLE_LENGTH_CONTENT;
    }

    private HttpChunkTrailer readTrailingHeaders(ChannelBuffer channelBuffer) throws TooLongFrameException {
        this.headerSize = 0;
        String readHeader = readHeader(channelBuffer);
        if (readHeader.length() != 0) {
            DefaultHttpChunkTrailer defaultHttpChunkTrailer = new DefaultHttpChunkTrailer();
            String str = null;
            do {
                char charAt = readHeader.charAt(0);
                if (str != null && (charAt == ' ' || charAt == '\t')) {
                    List<String> headers = defaultHttpChunkTrailer.getHeaders(str);
                    if (headers.size() != 0) {
                        int size = headers.size() - 1;
                        headers.set(size, headers.get(size) + readHeader.trim());
                    }
                } else {
                    String[] splitHeader = splitHeader(readHeader);
                    str = splitHeader[0];
                    if (!str.equalsIgnoreCase("Content-Length") && !str.equalsIgnoreCase("Transfer-Encoding") && !str.equalsIgnoreCase("Trailer")) {
                        defaultHttpChunkTrailer.addHeader(str, splitHeader[1]);
                    }
                }
                readHeader = readHeader(channelBuffer);
            } while (readHeader.length() != 0);
            return defaultHttpChunkTrailer;
        }
        return HttpChunk.LAST_CHUNK;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x004f, code lost:
    
        return r0.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0049, code lost:
    
        r5.headerSize = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String readHeader(ChannelBuffer channelBuffer) throws TooLongFrameException {
        StringBuilder sb = new StringBuilder(64);
        int i = this.headerSize;
        while (true) {
            char readByte = (char) channelBuffer.readByte();
            i++;
            if (readByte == '\n') {
                break;
            }
            if (readByte == '\r') {
                readByte = (char) channelBuffer.readByte();
                i++;
                if (readByte == '\n') {
                    break;
                }
            }
            if (i >= this.maxHeaderSize) {
                throw new TooLongFrameException("HTTP header is larger than " + this.maxHeaderSize + " bytes.");
            }
            sb.append(readByte);
        }
    }

    private static int getChunkSize(String str) {
        String trim = str.trim();
        for (int i = 0; i < trim.length(); i++) {
            char charAt = trim.charAt(i);
            if (charAt == ';' || Character.isWhitespace(charAt) || Character.isISOControl(charAt)) {
                trim = trim.substring(0, i);
                break;
            }
        }
        return Integer.parseInt(trim, 16);
    }

    private static String readLine(ChannelBuffer channelBuffer, int i) throws TooLongFrameException {
        StringBuilder sb = new StringBuilder(64);
        int i2 = 0;
        while (true) {
            byte readByte = channelBuffer.readByte();
            if (readByte == 13) {
                if (channelBuffer.readByte() == 10) {
                    return sb.toString();
                }
            } else {
                if (readByte == 10) {
                    return sb.toString();
                }
                if (i2 >= i) {
                    throw new TooLongFrameException("An HTTP line is larger than " + i + " bytes.");
                }
                i2++;
                sb.append((char) readByte);
            }
        }
    }

    private static String[] splitInitialLine(String str) {
        int findNonWhitespace = findNonWhitespace(str, 0);
        int findWhitespace = findWhitespace(str, findNonWhitespace);
        int findNonWhitespace2 = findNonWhitespace(str, findWhitespace);
        int findWhitespace2 = findWhitespace(str, findNonWhitespace2);
        int findNonWhitespace3 = findNonWhitespace(str, findWhitespace2);
        int findEndOfString = findEndOfString(str);
        String[] strArr = new String[3];
        strArr[0] = str.substring(findNonWhitespace, findWhitespace);
        strArr[1] = str.substring(findNonWhitespace2, findWhitespace2);
        strArr[2] = findNonWhitespace3 < findEndOfString ? str.substring(findNonWhitespace3, findEndOfString) : "";
        return strArr;
    }

    private static String[] splitHeader(String str) {
        char charAt;
        int length = str.length();
        int findNonWhitespace = findNonWhitespace(str, 0);
        int i = findNonWhitespace;
        while (i < length && (charAt = str.charAt(i)) != ':' && !Character.isWhitespace(charAt)) {
            i++;
        }
        int i2 = i;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (str.charAt(i2) == ':') {
                i2++;
                break;
            }
            i2++;
        }
        int findNonWhitespace2 = findNonWhitespace(str, i2);
        return findNonWhitespace2 == length ? new String[]{str.substring(findNonWhitespace, i), ""} : new String[]{str.substring(findNonWhitespace, i), str.substring(findNonWhitespace2, findEndOfString(str))};
    }

    private static int findNonWhitespace(String str, int i) {
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return i;
    }

    private static int findWhitespace(String str, int i) {
        while (i < str.length() && !Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return i;
    }

    private static int findEndOfString(String str) {
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return length;
    }
}
