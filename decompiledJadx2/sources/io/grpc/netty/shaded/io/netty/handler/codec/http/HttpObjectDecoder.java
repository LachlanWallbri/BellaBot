package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.PrematureChannelClosureException;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.AppendableCharSequence;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.List;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class HttpObjectDecoder extends ByteToMessageDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    public static final boolean DEFAULT_ALLOW_DUPLICATE_CONTENT_LENGTHS = false;
    public static final boolean DEFAULT_CHUNKED_SUPPORTED = true;
    public static final int DEFAULT_INITIAL_BUFFER_SIZE = 128;
    public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
    public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
    public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
    public static final boolean DEFAULT_VALIDATE_HEADERS = true;
    private static final String EMPTY_VALUE = "";
    private final boolean allowDuplicateContentLengths;
    private long chunkSize;
    private final boolean chunkedSupported;
    private long contentLength;
    private State currentState;
    private final HeaderParser headerParser;
    private final LineParser lineParser;
    private final int maxChunkSize;
    private HttpMessage message;
    private CharSequence name;
    private volatile boolean resetRequested;
    private LastHttpContent trailer;
    protected final boolean validateHeaders;
    private CharSequence value;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        SKIP_CONTROL_CHARS,
        READ_INITIAL,
        READ_HEADER,
        READ_VARIABLE_LENGTH_CONTENT,
        READ_FIXED_LENGTH_CONTENT,
        READ_CHUNK_SIZE,
        READ_CHUNKED_CONTENT,
        READ_CHUNK_DELIMITER,
        READ_CHUNK_FOOTER,
        BAD_MESSAGE,
        UPGRADED
    }

    private static boolean isOWS(char c) {
        return c == ' ' || c == '\t';
    }

    private static boolean isSPLenient(char c) {
        return c == ' ' || c == '\t' || c == 11 || c == '\f' || c == '\r';
    }

    protected abstract HttpMessage createInvalidMessage();

    protected abstract HttpMessage createMessage(String[] strArr) throws Exception;

    protected abstract boolean isDecodingRequest();

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpObjectDecoder() {
        this(4096, 8192, 8192, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpObjectDecoder(int i, int i2, int i3, boolean z) {
        this(i, i2, i3, z, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2) {
        this(i, i2, i3, z, z2, 128);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2, int i4) {
        this(i, i2, i3, z, z2, i4, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2, int i4, boolean z3) {
        this.contentLength = Long.MIN_VALUE;
        this.currentState = State.SKIP_CONTROL_CHARS;
        ObjectUtil.checkPositive(i, "maxInitialLineLength");
        ObjectUtil.checkPositive(i2, "maxHeaderSize");
        ObjectUtil.checkPositive(i3, "maxChunkSize");
        AppendableCharSequence appendableCharSequence = new AppendableCharSequence(i4);
        this.lineParser = new LineParser(appendableCharSequence, i);
        this.headerParser = new HeaderParser(appendableCharSequence, i2);
        this.maxChunkSize = i3;
        this.chunkedSupported = z;
        this.validateHeaders = z2;
        this.allowDuplicateContentLengths = z3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ce A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0108 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x013b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x013c A[Catch: Exception -> 0x019b, TryCatch #2 {Exception -> 0x019b, blocks: (B:71:0x0135, B:74:0x013c, B:78:0x014c, B:82:0x015a, B:85:0x0161, B:87:0x016a, B:90:0x016d, B:92:0x017b, B:94:0x017f, B:96:0x0185, B:97:0x018c, B:98:0x018d), top: B:70:0x0135 }] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        State readHeaders;
        int writerIndex;
        int readerIndex;
        int i;
        int min;
        if (this.resetRequested) {
            resetNow();
        }
        switch (this.currentState) {
            case SKIP_CONTROL_CHARS:
            case READ_INITIAL:
                try {
                    AppendableCharSequence parse = this.lineParser.parse(byteBuf);
                    if (parse == null) {
                        return;
                    }
                    String[] splitInitialLine = splitInitialLine(parse);
                    if (splitInitialLine.length < 3) {
                        this.currentState = State.SKIP_CONTROL_CHARS;
                        return;
                    }
                    this.message = createMessage(splitInitialLine);
                    this.currentState = State.READ_HEADER;
                    try {
                        readHeaders = readHeaders(byteBuf);
                        if (readHeaders != null) {
                            return;
                        }
                        this.currentState = readHeaders;
                        int i2 = C64811.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[readHeaders.ordinal()];
                        if (i2 == 1) {
                            list.add(this.message);
                            list.add(LastHttpContent.EMPTY_LAST_CONTENT);
                            resetNow();
                            return;
                        }
                        if (i2 == 2) {
                            if (!this.chunkedSupported) {
                                throw new IllegalArgumentException("Chunked messages not supported");
                            }
                            list.add(this.message);
                            return;
                        }
                        long contentLength = contentLength();
                        if (contentLength != 0 && (contentLength != -1 || !isDecodingRequest())) {
                            list.add(this.message);
                            if (readHeaders == State.READ_FIXED_LENGTH_CONTENT) {
                                this.chunkSize = contentLength;
                                return;
                            }
                            return;
                        }
                        list.add(this.message);
                        list.add(LastHttpContent.EMPTY_LAST_CONTENT);
                        resetNow();
                        return;
                    } catch (Exception e) {
                        list.add(invalidMessage(byteBuf, e));
                        return;
                    }
                } catch (Exception e2) {
                    list.add(invalidMessage(byteBuf, e2));
                    return;
                }
            case READ_CHUNK_SIZE:
                try {
                    AppendableCharSequence parse2 = this.lineParser.parse(byteBuf);
                    if (parse2 == null) {
                        return;
                    }
                    int chunkSize = getChunkSize(parse2.toString());
                    this.chunkSize = chunkSize;
                    if (chunkSize == 0) {
                        this.currentState = State.READ_CHUNK_FOOTER;
                        return;
                    }
                    this.currentState = State.READ_CHUNKED_CONTENT;
                    min = Math.min(Math.min((int) this.chunkSize, this.maxChunkSize), byteBuf.readableBytes());
                    if (min != 0) {
                        return;
                    }
                    DefaultHttpContent defaultHttpContent = new DefaultHttpContent(byteBuf.readRetainedSlice(min));
                    this.chunkSize -= min;
                    list.add(defaultHttpContent);
                    if (this.chunkSize != 0) {
                        return;
                    }
                    this.currentState = State.READ_CHUNK_DELIMITER;
                    writerIndex = byteBuf.writerIndex();
                    readerIndex = byteBuf.readerIndex();
                    while (true) {
                        if (writerIndex <= readerIndex) {
                            i = readerIndex + 1;
                            if (byteBuf.getByte(readerIndex) == 10) {
                                this.currentState = State.READ_CHUNK_SIZE;
                                readerIndex = i;
                            } else {
                                readerIndex = i;
                            }
                        }
                    }
                    byteBuf.readerIndex(readerIndex);
                    return;
                } catch (Exception e3) {
                    list.add(invalidChunk(byteBuf, e3));
                    return;
                }
            case READ_HEADER:
                readHeaders = readHeaders(byteBuf);
                if (readHeaders != null) {
                }
                break;
            case READ_VARIABLE_LENGTH_CONTENT:
                int min2 = Math.min(byteBuf.readableBytes(), this.maxChunkSize);
                if (min2 > 0) {
                    list.add(new DefaultHttpContent(byteBuf.readRetainedSlice(min2)));
                    return;
                }
                return;
            case READ_FIXED_LENGTH_CONTENT:
                int readableBytes = byteBuf.readableBytes();
                if (readableBytes == 0) {
                    return;
                }
                int min3 = Math.min(readableBytes, this.maxChunkSize);
                long j = min3;
                long j2 = this.chunkSize;
                if (j > j2) {
                    min3 = (int) j2;
                }
                ByteBuf readRetainedSlice = byteBuf.readRetainedSlice(min3);
                this.chunkSize -= min3;
                if (this.chunkSize == 0) {
                    list.add(new DefaultLastHttpContent(readRetainedSlice, this.validateHeaders));
                    resetNow();
                    return;
                } else {
                    list.add(new DefaultHttpContent(readRetainedSlice));
                    return;
                }
            case READ_CHUNKED_CONTENT:
                min = Math.min(Math.min((int) this.chunkSize, this.maxChunkSize), byteBuf.readableBytes());
                if (min != 0) {
                }
                break;
            case READ_CHUNK_DELIMITER:
                writerIndex = byteBuf.writerIndex();
                readerIndex = byteBuf.readerIndex();
                while (true) {
                    if (writerIndex <= readerIndex) {
                    }
                    readerIndex = i;
                }
                byteBuf.readerIndex(readerIndex);
                return;
            case READ_CHUNK_FOOTER:
                try {
                    LastHttpContent readTrailingHeaders = readTrailingHeaders(byteBuf);
                    if (readTrailingHeaders == null) {
                        return;
                    }
                    list.add(readTrailingHeaders);
                    resetNow();
                    return;
                } catch (Exception e4) {
                    list.add(invalidChunk(byteBuf, e4));
                    return;
                }
            case BAD_MESSAGE:
                byteBuf.skipBytes(byteBuf.readableBytes());
                return;
            case UPGRADED:
                int readableBytes2 = byteBuf.readableBytes();
                if (readableBytes2 > 0) {
                    list.add(byteBuf.readBytes(readableBytes2));
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    public void decodeLast(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        super.decodeLast(channelHandlerContext, byteBuf, list);
        if (this.resetRequested) {
            resetNow();
        }
        HttpMessage httpMessage = this.message;
        if (httpMessage != null) {
            boolean isTransferEncodingChunked = HttpUtil.isTransferEncodingChunked(httpMessage);
            if (this.currentState == State.READ_VARIABLE_LENGTH_CONTENT && !byteBuf.isReadable() && !isTransferEncodingChunked) {
                list.add(LastHttpContent.EMPTY_LAST_CONTENT);
                resetNow();
                return;
            }
            if (this.currentState == State.READ_HEADER) {
                list.add(invalidMessage(Unpooled.EMPTY_BUFFER, new PrematureChannelClosureException("Connection closed before received headers")));
                resetNow();
                return;
            }
            boolean z = true;
            if (!isDecodingRequest() && !isTransferEncodingChunked && contentLength() <= 0) {
                z = false;
            }
            if (!z) {
                list.add(LastHttpContent.EMPTY_LAST_CONTENT);
            }
            resetNow();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        int i;
        if ((obj instanceof HttpExpectationFailedEvent) && ((i = C64811.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[this.currentState.ordinal()]) == 2 || i == 5 || i == 6)) {
            reset();
        }
        super.userEventTriggered(channelHandlerContext, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        if (httpMessage instanceof HttpResponse) {
            HttpResponse httpResponse = (HttpResponse) httpMessage;
            int code = httpResponse.status().code();
            if (code >= 100 && code < 200) {
                return (code == 101 && !httpResponse.headers().contains(HttpHeaderNames.SEC_WEBSOCKET_ACCEPT) && httpResponse.headers().contains((CharSequence) HttpHeaderNames.UPGRADE, (CharSequence) HttpHeaderValues.WEBSOCKET, true)) ? false : true;
            }
            if (code == 204 || code == 304) {
                return true;
            }
        }
        return false;
    }

    protected boolean isSwitchingToNonHttp1Protocol(HttpResponse httpResponse) {
        if (httpResponse.status().code() != HttpResponseStatus.SWITCHING_PROTOCOLS.code()) {
            return false;
        }
        String str = httpResponse.headers().get(HttpHeaderNames.UPGRADE);
        return str == null || !(str.contains(HttpVersion.HTTP_1_0.text()) || str.contains(HttpVersion.HTTP_1_1.text()));
    }

    public void reset() {
        this.resetRequested = true;
    }

    private void resetNow() {
        HttpResponse httpResponse;
        HttpMessage httpMessage = this.message;
        this.message = null;
        this.name = null;
        this.value = null;
        this.contentLength = Long.MIN_VALUE;
        this.lineParser.reset();
        this.headerParser.reset();
        this.trailer = null;
        if (!isDecodingRequest() && (httpResponse = (HttpResponse) httpMessage) != null && isSwitchingToNonHttp1Protocol(httpResponse)) {
            this.currentState = State.UPGRADED;
        } else {
            this.resetRequested = false;
            this.currentState = State.SKIP_CONTROL_CHARS;
        }
    }

    private HttpMessage invalidMessage(ByteBuf byteBuf, Exception exc) {
        this.currentState = State.BAD_MESSAGE;
        byteBuf.skipBytes(byteBuf.readableBytes());
        if (this.message == null) {
            this.message = createInvalidMessage();
        }
        this.message.setDecoderResult(DecoderResult.failure(exc));
        HttpMessage httpMessage = this.message;
        this.message = null;
        return httpMessage;
    }

    private HttpContent invalidChunk(ByteBuf byteBuf, Exception exc) {
        this.currentState = State.BAD_MESSAGE;
        byteBuf.skipBytes(byteBuf.readableBytes());
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
        defaultLastHttpContent.setDecoderResult(DecoderResult.failure(exc));
        this.message = null;
        this.trailer = null;
        return defaultLastHttpContent;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
    
        if (r4 == ' ') goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0025, code lost:
    
        if (r4 != '\t') goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
    
        r2 = r2.toString().trim();
        r10.value = java.lang.String.valueOf(r10.value) + ' ' + r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0056, code lost:
    
        r2 = r10.headerParser.parse(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005c, code lost:
    
        if (r2 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0063, code lost:
    
        if (r2.length() > 0) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005e, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004a, code lost:
    
        r4 = r10.name;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
    
        if (r4 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
    
        r1.add(r4, r10.value);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
    
        splitHeader(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0065, code lost:
    
        r11 = r10.name;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0067, code lost:
    
        if (r11 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0069, code lost:
    
        r1.add(r11, r10.value);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006e, code lost:
    
        r10.name = null;
        r10.value = null;
        r11 = r1.getAll(io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007c, code lost:
    
        if (r11.isEmpty() != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007e, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
    
        if (r11.size() > 1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0091, code lost:
    
        if (r11.get(0).indexOf(44) < 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0094, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0095, code lost:
    
        if (r4 == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x009d, code lost:
    
        if (r0.protocolVersion() != io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion.HTTP_1_1) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a3, code lost:
    
        if (r10.allowDuplicateContentLengths == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a5, code lost:
    
        r2 = r11.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ad, code lost:
    
        if (r2.hasNext() == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00af, code lost:
    
        r6 = io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.COMMA_PATTERN.split(r2.next(), -1);
        r7 = r6.length;
        r8 = r3;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bf, code lost:
    
        if (r3 >= r7) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c1, code lost:
    
        r9 = r6[r3].trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c7, code lost:
    
        if (r8 != null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c9, code lost:
    
        r8 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d1, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00cf, code lost:
    
        if (r9.equals(r8) == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e8, code lost:
    
        throw new java.lang.IllegalArgumentException("Multiple Content-Length values found: " + r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00e9, code lost:
    
        r3 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00eb, code lost:
    
        r1.set(io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH, r3);
        r10.contentLength = java.lang.Long.parseLong(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x010b, code lost:
    
        throw new java.lang.IllegalArgumentException("Multiple Content-Length values found: " + r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x010c, code lost:
    
        r10.contentLength = java.lang.Long.parseLong(r11.get(0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x011c, code lost:
    
        if (isContentAlwaysEmpty(r0) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x011e, code lost:
    
        io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil.setTransferEncodingChunked(r0, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0123, code lost:
    
        return io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.State.SKIP_CONTROL_CHARS;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0128, code lost:
    
        if (io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil.isTransferEncodingChunked(r0) == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0015, code lost:
    
        if (r2.length() > 0) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x012e, code lost:
    
        if (r11.isEmpty() != false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0136, code lost:
    
        if (r0.protocolVersion() != io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion.HTTP_1_1) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0138, code lost:
    
        handleTransferEncodingChunkedWithContentLength(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x013d, code lost:
    
        return io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.State.READ_CHUNK_SIZE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0146, code lost:
    
        if (contentLength() < 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
    
        r4 = r2.charAtUnsafe(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x014a, code lost:
    
        return io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.State.READ_FIXED_LENGTH_CONTENT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x014d, code lost:
    
        return io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.State.READ_VARIABLE_LENGTH_CONTENT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
    
        if (r10.name == null) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private State readHeaders(ByteBuf byteBuf) {
        HttpMessage httpMessage = this.message;
        HttpHeaders headers = httpMessage.headers();
        AppendableCharSequence parse = this.headerParser.parse(byteBuf);
        String str = null;
        if (parse == null) {
            return null;
        }
    }

    protected void handleTransferEncodingChunkedWithContentLength(HttpMessage httpMessage) {
        httpMessage.headers().remove(HttpHeaderNames.CONTENT_LENGTH);
        this.contentLength = Long.MIN_VALUE;
    }

    private long contentLength() {
        if (this.contentLength == Long.MIN_VALUE) {
            this.contentLength = HttpUtil.getContentLength(this.message, -1L);
        }
        return this.contentLength;
    }

    private LastHttpContent readTrailingHeaders(ByteBuf byteBuf) {
        AppendableCharSequence parse = this.headerParser.parse(byteBuf);
        if (parse == null) {
            return null;
        }
        LastHttpContent lastHttpContent = this.trailer;
        if (parse.length() == 0 && lastHttpContent == null) {
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        if (lastHttpContent == null) {
            lastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, this.validateHeaders);
            this.trailer = lastHttpContent;
        }
        CharSequence charSequence = null;
        while (parse.length() > 0) {
            char charAtUnsafe = parse.charAtUnsafe(0);
            if (charSequence != null && (charAtUnsafe == ' ' || charAtUnsafe == '\t')) {
                List<String> all = lastHttpContent.trailingHeaders().getAll(charSequence);
                if (!all.isEmpty()) {
                    int size = all.size() - 1;
                    String trim = parse.toString().trim();
                    all.set(size, all.get(size) + trim);
                }
            } else {
                splitHeader(parse);
                CharSequence charSequence2 = this.name;
                if (!HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(charSequence2) && !HttpHeaderNames.TRANSFER_ENCODING.contentEqualsIgnoreCase(charSequence2) && !HttpHeaderNames.TRAILER.contentEqualsIgnoreCase(charSequence2)) {
                    lastHttpContent.trailingHeaders().add(charSequence2, this.value);
                }
                CharSequence charSequence3 = this.name;
                this.name = null;
                this.value = null;
                charSequence = charSequence3;
            }
            parse = this.headerParser.parse(byteBuf);
            if (parse == null) {
                return null;
            }
        }
        this.trailer = null;
        return lastHttpContent;
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

    private static String[] splitInitialLine(AppendableCharSequence appendableCharSequence) {
        int findNonSPLenient = findNonSPLenient(appendableCharSequence, 0);
        int findSPLenient = findSPLenient(appendableCharSequence, findNonSPLenient);
        int findNonSPLenient2 = findNonSPLenient(appendableCharSequence, findSPLenient);
        int findSPLenient2 = findSPLenient(appendableCharSequence, findNonSPLenient2);
        int findNonSPLenient3 = findNonSPLenient(appendableCharSequence, findSPLenient2);
        int findEndOfString = findEndOfString(appendableCharSequence);
        String[] strArr = new String[3];
        strArr[0] = appendableCharSequence.subStringUnsafe(findNonSPLenient, findSPLenient);
        strArr[1] = appendableCharSequence.subStringUnsafe(findNonSPLenient2, findSPLenient2);
        strArr[2] = findNonSPLenient3 < findEndOfString ? appendableCharSequence.subStringUnsafe(findNonSPLenient3, findEndOfString) : "";
        return strArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0026, code lost:
    
        r4 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
    
        if (r4 >= r0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
    
        if (r7.charAtUnsafe(r4) != ':') goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002f, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0035, code lost:
    
        r6.name = r7.subStringUnsafe(r1, r2);
        r1 = findNonWhitespace(r7, r4, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
    
        if (r1 != r0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
    
        r6.value = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0047, code lost:
    
        r6.value = r7.subStringUnsafe(r1, findEndOfString(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0051, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void splitHeader(AppendableCharSequence appendableCharSequence) {
        char charAtUnsafe;
        int length = appendableCharSequence.length();
        int findNonWhitespace = findNonWhitespace(appendableCharSequence, 0, false);
        int i = findNonWhitespace;
        while (i < length && (charAtUnsafe = appendableCharSequence.charAtUnsafe(i)) != ':' && (isDecodingRequest() || !isOWS(charAtUnsafe))) {
            i++;
        }
        throw new IllegalArgumentException("No colon found");
    }

    private static int findNonSPLenient(AppendableCharSequence appendableCharSequence, int i) {
        while (i < appendableCharSequence.length()) {
            char charAtUnsafe = appendableCharSequence.charAtUnsafe(i);
            if (!isSPLenient(charAtUnsafe)) {
                if (Character.isWhitespace(charAtUnsafe)) {
                    throw new IllegalArgumentException("Invalid separator");
                }
                return i;
            }
            i++;
        }
        return appendableCharSequence.length();
    }

    private static int findSPLenient(AppendableCharSequence appendableCharSequence, int i) {
        while (i < appendableCharSequence.length()) {
            if (isSPLenient(appendableCharSequence.charAtUnsafe(i))) {
                return i;
            }
            i++;
        }
        return appendableCharSequence.length();
    }

    private static int findNonWhitespace(AppendableCharSequence appendableCharSequence, int i, boolean z) {
        while (i < appendableCharSequence.length()) {
            char charAtUnsafe = appendableCharSequence.charAtUnsafe(i);
            if (!Character.isWhitespace(charAtUnsafe)) {
                return i;
            }
            if (z && !isOWS(charAtUnsafe)) {
                throw new IllegalArgumentException("Invalid separator, only a single space or horizontal tab allowed, but received a '" + charAtUnsafe + "'");
            }
            i++;
        }
        return appendableCharSequence.length();
    }

    private static int findEndOfString(AppendableCharSequence appendableCharSequence) {
        for (int length = appendableCharSequence.length() - 1; length > 0; length--) {
            if (!Character.isWhitespace(appendableCharSequence.charAtUnsafe(length))) {
                return length + 1;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class HeaderParser implements ByteProcessor {
        private final int maxLength;
        private final AppendableCharSequence seq;
        private int size;

        HeaderParser(AppendableCharSequence appendableCharSequence, int i) {
            this.seq = appendableCharSequence;
            this.maxLength = i;
        }

        public AppendableCharSequence parse(ByteBuf byteBuf) {
            int i = this.size;
            this.seq.reset();
            int forEachByte = byteBuf.forEachByte(this);
            if (forEachByte == -1) {
                this.size = i;
                return null;
            }
            byteBuf.readerIndex(forEachByte + 1);
            return this.seq;
        }

        public void reset() {
            this.size = 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            char c = (char) (b & 255);
            if (c == '\n') {
                int length = this.seq.length();
                if (length < 1) {
                    return false;
                }
                int i = length - 1;
                if (this.seq.charAtUnsafe(i) != '\r') {
                    return false;
                }
                this.size--;
                this.seq.setLength(i);
                return false;
            }
            increaseCount();
            this.seq.append(c);
            return true;
        }

        protected final void increaseCount() {
            int i = this.size + 1;
            this.size = i;
            int i2 = this.maxLength;
            if (i > i2) {
                throw newException(i2);
            }
        }

        protected TooLongFrameException newException(int i) {
            return new TooLongFrameException("HTTP header is larger than " + i + " bytes.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class LineParser extends HeaderParser {
        LineParser(AppendableCharSequence appendableCharSequence, int i) {
            super(appendableCharSequence, i);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser
        public AppendableCharSequence parse(ByteBuf byteBuf) {
            reset();
            return super.parse(byteBuf);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser, io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            if (HttpObjectDecoder.this.currentState == State.SKIP_CONTROL_CHARS) {
                char c = (char) (b & 255);
                if (Character.isISOControl(c) || Character.isWhitespace(c)) {
                    increaseCount();
                    return true;
                }
                HttpObjectDecoder.this.currentState = State.READ_INITIAL;
            }
            return super.process(b);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser
        protected TooLongFrameException newException(int i) {
            return new TooLongFrameException("An HTTP line is larger than " + i + " bytes.");
        }
    }
}
