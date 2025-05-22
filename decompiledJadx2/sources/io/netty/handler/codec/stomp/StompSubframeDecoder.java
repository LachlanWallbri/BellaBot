package io.netty.handler.codec.stomp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.internal.AppendableCharSequence;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class StompSubframeDecoder extends ReplayingDecoder<State> {
    private static final int DEFAULT_CHUNK_SIZE = 8132;
    private static final int DEFAULT_MAX_LINE_LENGTH = 1024;
    private int alreadyReadChunkSize;
    private long contentLength;
    private LastStompContentSubframe lastContent;
    private final int maxChunkSize;
    private final int maxLineLength;
    private final boolean validateHeaders;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum State {
        SKIP_CONTROL_CHARACTERS,
        READ_HEADERS,
        READ_CONTENT,
        FINALIZE_FRAME_READ,
        BAD_FRAME,
        INVALID_CHUNK
    }

    public StompSubframeDecoder() {
        this(1024, DEFAULT_CHUNK_SIZE);
    }

    public StompSubframeDecoder(boolean z) {
        this(1024, DEFAULT_CHUNK_SIZE, z);
    }

    public StompSubframeDecoder(int i, int i2) {
        this(i, i2, false);
    }

    public StompSubframeDecoder(int i, int i2, boolean z) {
        super(State.SKIP_CONTROL_CHARACTERS);
        this.contentLength = -1L;
        if (i <= 0) {
            throw new IllegalArgumentException("maxLineLength must be a positive integer: " + i);
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxChunkSize must be a positive integer: " + i2);
        }
        this.maxChunkSize = i2;
        this.maxLineLength = i;
        this.validateHeaders = z;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.stomp.StompSubframeDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72891 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$stomp$StompSubframeDecoder$State */
        static final /* synthetic */ int[] f8547x1128a9ae;

        static {
            int[] iArr = new int[State.values().length];
            f8547x1128a9ae = iArr;
            try {
                iArr[State.SKIP_CONTROL_CHARACTERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8547x1128a9ae[State.READ_HEADERS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8547x1128a9ae[State.BAD_FRAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8547x1128a9ae[State.READ_CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8547x1128a9ae[State.FINALIZE_FRAME_READ.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ee A[Catch: Exception -> 0x0104, TryCatch #2 {Exception -> 0x0104, blocks: (B:11:0x0042, B:17:0x00e7, B:19:0x00ee, B:20:0x00f2, B:22:0x0058, B:25:0x005f, B:27:0x0063, B:28:0x0065, B:30:0x006d, B:33:0x0077, B:35:0x008b, B:36:0x0098, B:38:0x00a1, B:40:0x00b4, B:42:0x00bc, B:43:0x00cc, B:45:0x00db, B:46:0x00fb, B:48:0x00c3), top: B:10:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058 A[Catch: Exception -> 0x0104, TryCatch #2 {Exception -> 0x0104, blocks: (B:11:0x0042, B:17:0x00e7, B:19:0x00ee, B:20:0x00f2, B:22:0x0058, B:25:0x005f, B:27:0x0063, B:28:0x0065, B:30:0x006d, B:33:0x0077, B:35:0x008b, B:36:0x0098, B:38:0x00a1, B:40:0x00b4, B:42:0x00bc, B:43:0x00cc, B:45:0x00db, B:46:0x00fb, B:48:0x00c3), top: B:10:0x0042 }] */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        StompCommand stompCommand;
        DefaultStompHeadersSubframe defaultStompHeadersSubframe;
        DefaultStompHeadersSubframe defaultStompHeadersSubframe2;
        int i;
        int writerIndex;
        int i2 = C72891.f8547x1128a9ae[state().ordinal()];
        try {
            try {
                try {
                    if (i2 == 1) {
                        skipControlCharacters(byteBuf);
                        checkpoint(State.READ_HEADERS);
                    } else if (i2 != 2) {
                        if (i2 == 3) {
                            byteBuf.skipBytes(actualReadableBytes());
                            return;
                        }
                        i = C72891.f8547x1128a9ae[state().ordinal()];
                        if (i != 4) {
                            int readableBytes = byteBuf.readableBytes();
                            if (readableBytes == 0) {
                                return;
                            }
                            if (readableBytes > this.maxChunkSize) {
                                readableBytes = this.maxChunkSize;
                            }
                            if (this.contentLength >= 0) {
                                int i3 = (int) (this.contentLength - this.alreadyReadChunkSize);
                                if (readableBytes > i3) {
                                    readableBytes = i3;
                                }
                                ByteBuf readBytes = ByteBufUtil.readBytes(channelHandlerContext.alloc(), byteBuf, readableBytes);
                                int i4 = this.alreadyReadChunkSize + readableBytes;
                                this.alreadyReadChunkSize = i4;
                                if (i4 >= this.contentLength) {
                                    this.lastContent = new DefaultLastStompContentSubframe(readBytes);
                                    checkpoint(State.FINALIZE_FRAME_READ);
                                } else {
                                    list.add(new DefaultStompContentSubframe(readBytes));
                                    return;
                                }
                            } else {
                                int indexOf = ByteBufUtil.indexOf(byteBuf, byteBuf.readerIndex(), byteBuf.writerIndex(), (byte) 0);
                                if (indexOf == byteBuf.readerIndex()) {
                                    checkpoint(State.FINALIZE_FRAME_READ);
                                } else {
                                    if (indexOf > 0) {
                                        writerIndex = indexOf - byteBuf.readerIndex();
                                    } else {
                                        writerIndex = byteBuf.writerIndex() - byteBuf.readerIndex();
                                    }
                                    ByteBuf readBytes2 = ByteBufUtil.readBytes(channelHandlerContext.alloc(), byteBuf, writerIndex);
                                    this.alreadyReadChunkSize += writerIndex;
                                    if (indexOf > 0) {
                                        this.lastContent = new DefaultLastStompContentSubframe(readBytes2);
                                        checkpoint(State.FINALIZE_FRAME_READ);
                                    } else {
                                        list.add(new DefaultStompContentSubframe(readBytes2));
                                        return;
                                    }
                                }
                            }
                        } else if (i != 5) {
                            return;
                        }
                        skipNullCharacter(byteBuf);
                        if (this.lastContent == null) {
                            this.lastContent = LastStompContentSubframe.EMPTY_LAST_CONTENT;
                        }
                        list.add(this.lastContent);
                        resetDecoder();
                        return;
                    }
                    i = C72891.f8547x1128a9ae[state().ordinal()];
                    if (i != 4) {
                    }
                    skipNullCharacter(byteBuf);
                    if (this.lastContent == null) {
                    }
                    list.add(this.lastContent);
                    resetDecoder();
                    return;
                } catch (Exception e) {
                    DefaultLastStompContentSubframe defaultLastStompContentSubframe = new DefaultLastStompContentSubframe(Unpooled.EMPTY_BUFFER);
                    defaultLastStompContentSubframe.setDecoderResult(DecoderResult.failure(e));
                    list.add(defaultLastStompContentSubframe);
                    checkpoint(State.BAD_FRAME);
                    return;
                }
                checkpoint(readHeaders(byteBuf, defaultStompHeadersSubframe2.headers()));
                list.add(defaultStompHeadersSubframe2);
            } catch (Exception e2) {
                e = e2;
                defaultStompHeadersSubframe = defaultStompHeadersSubframe2;
                if (defaultStompHeadersSubframe == null) {
                    defaultStompHeadersSubframe = new DefaultStompHeadersSubframe(stompCommand);
                }
                defaultStompHeadersSubframe.setDecoderResult(DecoderResult.failure(e));
                list.add(defaultStompHeadersSubframe);
                checkpoint(State.BAD_FRAME);
                return;
            }
            stompCommand = readCommand(byteBuf);
            defaultStompHeadersSubframe2 = new DefaultStompHeadersSubframe(stompCommand);
        } catch (Exception e3) {
            e = e3;
        }
        stompCommand = StompCommand.UNKNOWN;
        defaultStompHeadersSubframe = null;
    }

    private StompCommand readCommand(ByteBuf byteBuf) {
        StompCommand stompCommand;
        String readLine = readLine(byteBuf, 16);
        try {
            stompCommand = StompCommand.valueOf(readLine);
        } catch (IllegalArgumentException unused) {
            stompCommand = null;
        }
        if (stompCommand == null) {
            try {
                stompCommand = StompCommand.valueOf(readLine.toUpperCase(Locale.US));
            } catch (IllegalArgumentException unused2) {
            }
        }
        if (stompCommand != null) {
            return stompCommand;
        }
        throw new DecoderException("failed to read command from channel");
    }

    private State readHeaders(ByteBuf byteBuf, StompHeaders stompHeaders) {
        do {
        } while (readHeader(stompHeaders, new AppendableCharSequence(128), byteBuf));
        if (stompHeaders.contains(StompHeaders.CONTENT_LENGTH)) {
            long contentLength = getContentLength(stompHeaders, 0L);
            this.contentLength = contentLength;
            if (contentLength == 0) {
                return State.FINALIZE_FRAME_READ;
            }
        }
        return State.READ_CONTENT;
    }

    private static long getContentLength(StompHeaders stompHeaders, long j) {
        long j2 = stompHeaders.getLong(StompHeaders.CONTENT_LENGTH, j);
        if (j2 >= 0) {
            return j2;
        }
        throw new DecoderException(((Object) StompHeaders.CONTENT_LENGTH) + " must be non-negative");
    }

    private static void skipNullCharacter(ByteBuf byteBuf) {
        byte readByte = byteBuf.readByte();
        if (readByte == 0) {
            return;
        }
        throw new IllegalStateException("unexpected byte in buffer " + ((int) readByte) + " while expecting NULL byte");
    }

    private static void skipControlCharacters(ByteBuf byteBuf) {
        while (true) {
            byte readByte = byteBuf.readByte();
            if (readByte != 13 && readByte != 10) {
                byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                return;
            }
        }
    }

    private String readLine(ByteBuf byteBuf, int i) {
        AppendableCharSequence appendableCharSequence = new AppendableCharSequence(i);
        int i2 = 0;
        while (true) {
            byte readByte = byteBuf.readByte();
            if (readByte != 13) {
                if (readByte == 10) {
                    return appendableCharSequence.toString();
                }
                if (i2 >= this.maxLineLength) {
                    invalidLineLength();
                }
                i2++;
                appendableCharSequence.append((char) readByte);
            }
        }
    }

    private boolean readHeader(StompHeaders stompHeaders, AppendableCharSequence appendableCharSequence, ByteBuf byteBuf) {
        appendableCharSequence.reset();
        String str = null;
        int i = 0;
        boolean z = false;
        while (true) {
            byte readByte = byteBuf.readByte();
            if (readByte == 58 && str == null) {
                str = appendableCharSequence.toString();
                appendableCharSequence.reset();
                z = true;
            } else if (readByte == 13) {
                continue;
            } else {
                if (readByte == 10) {
                    break;
                }
                if (i >= this.maxLineLength) {
                    invalidLineLength();
                }
                if (readByte == 58 && str != null) {
                    z = false;
                }
                i++;
                appendableCharSequence.append((char) readByte);
            }
        }
        if (str == null && i == 0) {
            return false;
        }
        if (z) {
            stompHeaders.add((StompHeaders) str, appendableCharSequence.toString());
        } else if (this.validateHeaders) {
            invalidHeader(str, appendableCharSequence.toString());
        }
        return true;
    }

    private void invalidHeader(String str, String str2) {
        if (str != null) {
            str2 = str + ":" + str2;
        }
        throw new IllegalArgumentException("a header value or name contains a prohibited character ':', " + str2);
    }

    private void invalidLineLength() {
        throw new TooLongFrameException("An STOMP line is larger than " + this.maxLineLength + " bytes.");
    }

    private void resetDecoder() {
        checkpoint(State.SKIP_CONTROL_CHARACTERS);
        this.contentLength = -1L;
        this.alreadyReadChunkSize = 0;
        this.lastContent = null;
    }
}
