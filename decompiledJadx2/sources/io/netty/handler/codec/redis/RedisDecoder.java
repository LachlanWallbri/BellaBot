package io.netty.handler.codec.redis;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class RedisDecoder extends ByteToMessageDecoder {
    private final boolean decodeInlineCommands;
    private final int maxInlineMessageLength;
    private final RedisMessagePool messagePool;
    private int remainingBulkLength;
    private State state;
    private final ToPositiveLongProcessor toPositiveLongProcessor;
    private RedisMessageType type;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum State {
        DECODE_TYPE,
        DECODE_INLINE,
        DECODE_LENGTH,
        DECODE_BULK_STRING_EOL,
        DECODE_BULK_STRING_CONTENT
    }

    public RedisDecoder() {
        this(false);
    }

    public RedisDecoder(boolean z) {
        this(65536, FixedRedisMessagePool.INSTANCE, z);
    }

    public RedisDecoder(int i, RedisMessagePool redisMessagePool) {
        this(i, redisMessagePool, false);
    }

    public RedisDecoder(int i, RedisMessagePool redisMessagePool, boolean z) {
        this.toPositiveLongProcessor = new ToPositiveLongProcessor(null);
        this.state = State.DECODE_TYPE;
        if (i <= 0 || i > 536870912) {
            throw new RedisCodecException("maxInlineMessageLength: " + i + " (expected: <= " + PKIFailureInfo.duplicateCertReq + ")");
        }
        this.maxInlineMessageLength = i;
        this.messagePool = redisMessagePool;
        this.decodeInlineCommands = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0038, code lost:
    
        throw new io.netty.handler.codec.redis.RedisCodecException("Unknown state: " + r1.state);
     */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (true) {
            try {
                int i = C72591.$SwitchMap$io$netty$handler$codec$redis$RedisDecoder$State[this.state.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    break;
                                } else if (!decodeBulkStringContent(byteBuf, list)) {
                                    return;
                                }
                            } else if (!decodeBulkStringEndOfLine(byteBuf, list)) {
                                return;
                            }
                        } else if (!decodeLength(byteBuf, list)) {
                            return;
                        }
                    } else if (!decodeInline(byteBuf, list)) {
                        return;
                    }
                } else if (!decodeType(byteBuf)) {
                    return;
                }
            } catch (RedisCodecException e) {
                resetDecoder();
                throw e;
            } catch (Exception e2) {
                resetDecoder();
                throw new RedisCodecException(e2);
            }
        }
    }

    private void resetDecoder() {
        this.state = State.DECODE_TYPE;
        this.remainingBulkLength = 0;
    }

    private boolean decodeType(ByteBuf byteBuf) throws Exception {
        if (!byteBuf.isReadable()) {
            return false;
        }
        RedisMessageType readFrom = RedisMessageType.readFrom(byteBuf, this.decodeInlineCommands);
        this.type = readFrom;
        this.state = readFrom.isInline() ? State.DECODE_INLINE : State.DECODE_LENGTH;
        return true;
    }

    private boolean decodeInline(ByteBuf byteBuf, List<Object> list) throws Exception {
        ByteBuf readLine = readLine(byteBuf);
        if (readLine == null) {
            if (byteBuf.readableBytes() <= this.maxInlineMessageLength) {
                return false;
            }
            throw new RedisCodecException("length: " + byteBuf.readableBytes() + " (expected: <= " + this.maxInlineMessageLength + ")");
        }
        list.add(newInlineRedisMessage(this.type, readLine));
        resetDecoder();
        return true;
    }

    private boolean decodeLength(ByteBuf byteBuf, List<Object> list) throws Exception {
        ByteBuf readLine = readLine(byteBuf);
        if (readLine == null) {
            return false;
        }
        long parseRedisNumber = parseRedisNumber(readLine);
        if (parseRedisNumber < -1) {
            throw new RedisCodecException("length: " + parseRedisNumber + " (expected: >= -1)");
        }
        int i = C72591.$SwitchMap$io$netty$handler$codec$redis$RedisMessageType[this.type.ordinal()];
        if (i == 1) {
            list.add(new ArrayHeaderRedisMessage(parseRedisNumber));
            resetDecoder();
            return true;
        }
        if (i != 2) {
            throw new RedisCodecException("bad type: " + this.type);
        }
        if (parseRedisNumber > 536870912) {
            throw new RedisCodecException("length: " + parseRedisNumber + " (expected: <= " + PKIFailureInfo.duplicateCertReq + ")");
        }
        this.remainingBulkLength = (int) parseRedisNumber;
        return decodeBulkString(byteBuf, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.redis.RedisDecoder$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C72591 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$redis$RedisDecoder$State;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$redis$RedisMessageType;

        static {
            int[] iArr = new int[RedisMessageType.values().length];
            $SwitchMap$io$netty$handler$codec$redis$RedisMessageType = iArr;
            try {
                iArr[RedisMessageType.ARRAY_HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisMessageType[RedisMessageType.BULK_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisMessageType[RedisMessageType.INLINE_COMMAND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisMessageType[RedisMessageType.SIMPLE_STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisMessageType[RedisMessageType.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisMessageType[RedisMessageType.INTEGER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$redis$RedisDecoder$State = iArr2;
            try {
                iArr2[State.DECODE_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisDecoder$State[State.DECODE_INLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisDecoder$State[State.DECODE_LENGTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisDecoder$State[State.DECODE_BULK_STRING_EOL.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$redis$RedisDecoder$State[State.DECODE_BULK_STRING_CONTENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private boolean decodeBulkString(ByteBuf byteBuf, List<Object> list) throws Exception {
        int i = this.remainingBulkLength;
        if (i == -1) {
            list.add(FullBulkStringRedisMessage.NULL_INSTANCE);
            resetDecoder();
            return true;
        }
        if (i == 0) {
            this.state = State.DECODE_BULK_STRING_EOL;
            return decodeBulkStringEndOfLine(byteBuf, list);
        }
        list.add(new BulkStringHeaderRedisMessage(this.remainingBulkLength));
        this.state = State.DECODE_BULK_STRING_CONTENT;
        return decodeBulkStringContent(byteBuf, list);
    }

    private boolean decodeBulkStringEndOfLine(ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 2) {
            return false;
        }
        readEndOfLine(byteBuf);
        list.add(FullBulkStringRedisMessage.EMPTY_INSTANCE);
        resetDecoder();
        return true;
    }

    private boolean decodeBulkStringContent(ByteBuf byteBuf, List<Object> list) throws Exception {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            return false;
        }
        if (this.remainingBulkLength == 0 && readableBytes < 2) {
            return false;
        }
        int i = this.remainingBulkLength;
        if (readableBytes >= i + 2) {
            ByteBuf readSlice = byteBuf.readSlice(i);
            readEndOfLine(byteBuf);
            list.add(new DefaultLastBulkStringRedisContent(readSlice.retain()));
            resetDecoder();
            return true;
        }
        int min = Math.min(i, readableBytes);
        this.remainingBulkLength -= min;
        list.add(new DefaultBulkStringRedisContent(byteBuf.readSlice(min).retain()));
        return true;
    }

    private static void readEndOfLine(ByteBuf byteBuf) {
        short readShort = byteBuf.readShort();
        if (RedisConstants.EOL_SHORT == readShort) {
            return;
        }
        byte[] shortToBytes = RedisCodecUtil.shortToBytes(readShort);
        throw new RedisCodecException("delimiter: [" + ((int) shortToBytes[0]) + "," + ((int) shortToBytes[1]) + "] (expected: \\r\\n)");
    }

    private RedisMessage newInlineRedisMessage(RedisMessageType redisMessageType, ByteBuf byteBuf) {
        int i = C72591.$SwitchMap$io$netty$handler$codec$redis$RedisMessageType[redisMessageType.ordinal()];
        if (i == 3) {
            return new InlineCommandRedisMessage(byteBuf.toString(CharsetUtil.UTF_8));
        }
        if (i == 4) {
            SimpleStringRedisMessage simpleString = this.messagePool.getSimpleString(byteBuf);
            return simpleString != null ? simpleString : new SimpleStringRedisMessage(byteBuf.toString(CharsetUtil.UTF_8));
        }
        if (i == 5) {
            ErrorRedisMessage error = this.messagePool.getError(byteBuf);
            return error != null ? error : new ErrorRedisMessage(byteBuf.toString(CharsetUtil.UTF_8));
        }
        if (i == 6) {
            IntegerRedisMessage integer = this.messagePool.getInteger(byteBuf);
            return integer != null ? integer : new IntegerRedisMessage(parseRedisNumber(byteBuf));
        }
        throw new RedisCodecException("bad type: " + redisMessageType);
    }

    private static ByteBuf readLine(ByteBuf byteBuf) {
        int forEachByte;
        if (!byteBuf.isReadable(2) || (forEachByte = byteBuf.forEachByte(ByteProcessor.FIND_LF)) < 0) {
            return null;
        }
        ByteBuf readSlice = byteBuf.readSlice((forEachByte - byteBuf.readerIndex()) - 1);
        readEndOfLine(byteBuf);
        return readSlice;
    }

    private long parseRedisNumber(ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        int i = (readableBytes <= 0 || byteBuf.getByte(byteBuf.readerIndex()) != 45) ? 0 : 1;
        if (readableBytes <= i) {
            throw new RedisCodecException("no number to parse: " + byteBuf.toString(CharsetUtil.US_ASCII));
        }
        if (readableBytes <= i + 19) {
            if (i != 0) {
                return -parsePositiveNumber(byteBuf.skipBytes(i));
            }
            return parsePositiveNumber(byteBuf);
        }
        throw new RedisCodecException("too many characters to be a valid RESP Integer: " + byteBuf.toString(CharsetUtil.US_ASCII));
    }

    private long parsePositiveNumber(ByteBuf byteBuf) {
        this.toPositiveLongProcessor.reset();
        byteBuf.forEachByte(this.toPositiveLongProcessor);
        return this.toPositiveLongProcessor.content();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class ToPositiveLongProcessor implements ByteProcessor {
        private long result;

        private ToPositiveLongProcessor() {
        }

        /* synthetic */ ToPositiveLongProcessor(C72591 c72591) {
            this();
        }

        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            if (b < 48 || b > 57) {
                throw new RedisCodecException("bad byte in number: " + ((int) b));
            }
            this.result = (this.result * 10) + (b - TarConstants.LF_NORMAL);
            return true;
        }

        public long content() {
            return this.result;
        }

        public void reset() {
            this.result = 0L;
        }
    }
}
