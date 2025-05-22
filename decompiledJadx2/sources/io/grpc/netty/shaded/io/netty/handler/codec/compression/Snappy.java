package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Snappy {
    private static final int COPY_1_BYTE_OFFSET = 1;
    private static final int COPY_2_BYTE_OFFSET = 2;
    private static final int COPY_4_BYTE_OFFSET = 3;
    private static final int LITERAL = 0;
    private static final int MAX_HT_SIZE = 16384;
    private static final int MIN_COMPRESSIBLE_BYTES = 15;
    private static final int NOT_ENOUGH_INPUT = -1;
    private static final int PREAMBLE_NOT_FULL = -1;
    private State state = State.READY;
    private byte tag;
    private int written;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private enum State {
        READY,
        READING_PREAMBLE,
        READING_TAG,
        READING_LITERAL,
        READING_COPY
    }

    static int maskChecksum(long j) {
        return (int) (((j << 17) | (j >> 15)) - 1568478504);
    }

    public void reset() {
        this.state = State.READY;
        this.tag = (byte) 0;
        this.written = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0055, code lost:
    
        encodeLiteral(r13, r14, r3 - r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        r4 = findMatchingLength(r13, r10 + 4, r3 + 4, r15) + 4;
        r5 = r3 + r4;
        encodeCopy(r14, r3 - r10, r4);
        r13.readerIndex(r13.readerIndex() + r4);
        r3 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0074, code lost:
    
        if (r5 < r8) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0077, code lost:
    
        r6 = r5 - r0;
        r1[hash(r13, r3, r2)] = (short) (r6 - 1);
        r4 = r3 + 1;
        r7 = hash(r13, r4, r2);
        r10 = r0 + r1[r7];
        r1[r7] = (short) r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0097, code lost:
    
        if (r13.getInt(r4) == r13.getInt(r10)) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a2, code lost:
    
        r3 = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void encode(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        while (true) {
            i2 = i >>> (i5 * 7);
            if ((i2 & (-128)) == 0) {
                break;
            }
            byteBuf2.writeByte((i2 & 127) | 128);
            i5++;
        }
        byteBuf2.writeByte(i2);
        int readerIndex = byteBuf.readerIndex();
        short[] hashTable = getHashTable(i);
        int numberOfLeadingZeros = Integer.numberOfLeadingZeros(hashTable.length) + 1;
        if (i - readerIndex >= 15) {
            int i6 = readerIndex + 1;
            int hash = hash(byteBuf, i6, numberOfLeadingZeros);
            i3 = readerIndex;
            loop1: while (true) {
                int i7 = 32;
                while (true) {
                    int i8 = i7 + 1;
                    int i9 = (i7 >> 5) + i6;
                    int i10 = i - 4;
                    if (i9 > i10) {
                        break loop1;
                    }
                    int hash2 = hash(byteBuf, i9, numberOfLeadingZeros);
                    int i11 = hashTable[hash] + readerIndex;
                    hashTable[hash] = (short) (i6 - readerIndex);
                    if (byteBuf.getInt(i6) == byteBuf.getInt(i11)) {
                        break;
                    }
                    i6 = i9;
                    i7 = i8;
                    hash = hash2;
                }
                hash = hash(byteBuf, i4 + 2, numberOfLeadingZeros);
                i6 = i3 + 1;
            }
        } else {
            i3 = readerIndex;
        }
        if (i3 < i) {
            encodeLiteral(byteBuf, byteBuf2, i - i3);
        }
    }

    private static int hash(ByteBuf byteBuf, int i, int i2) {
        return (byteBuf.getInt(i) * 506832829) >>> i2;
    }

    private static short[] getHashTable(int i) {
        int i2 = 256;
        while (i2 < 16384 && i2 < i) {
            i2 <<= 1;
        }
        return new short[i2];
    }

    private static int findMatchingLength(ByteBuf byteBuf, int i, int i2, int i3) {
        int i4 = 0;
        while (i2 <= i3 - 4 && byteBuf.getInt(i2) == byteBuf.getInt(i + i4)) {
            i2 += 4;
            i4 += 4;
        }
        while (i2 < i3 && byteBuf.getByte(i + i4) == byteBuf.getByte(i2)) {
            i2++;
            i4++;
        }
        return i4;
    }

    private static int bitsToEncode(int i) {
        int highestOneBit = Integer.highestOneBit(i);
        int i2 = 0;
        while (true) {
            highestOneBit >>= 1;
            if (highestOneBit == 0) {
                return i2;
            }
            i2++;
        }
    }

    static void encodeLiteral(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (i < 61) {
            byteBuf2.writeByte((i - 1) << 2);
        } else {
            int i2 = i - 1;
            int bitsToEncode = (bitsToEncode(i2) / 8) + 1;
            byteBuf2.writeByte((bitsToEncode + 59) << 2);
            for (int i3 = 0; i3 < bitsToEncode; i3++) {
                byteBuf2.writeByte((i2 >> (i3 * 8)) & 255);
            }
        }
        byteBuf2.writeBytes(byteBuf, i);
    }

    private static void encodeCopyWithOffset(ByteBuf byteBuf, int i, int i2) {
        if (i2 < 12 && i < 2048) {
            byteBuf.writeByte(((i2 - 4) << 2) | 1 | ((i >> 8) << 5));
            byteBuf.writeByte(i & 255);
        } else {
            byteBuf.writeByte(((i2 - 1) << 2) | 2);
            byteBuf.writeByte(i & 255);
            byteBuf.writeByte((i >> 8) & 255);
        }
    }

    private static void encodeCopy(ByteBuf byteBuf, int i, int i2) {
        while (i2 >= 68) {
            encodeCopyWithOffset(byteBuf, i, 64);
            i2 -= 64;
        }
        if (i2 > 64) {
            encodeCopyWithOffset(byteBuf, i, 60);
            i2 -= 60;
        }
        encodeCopyWithOffset(byteBuf, i, i2);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64661 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$Snappy$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_PREAMBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_TAG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_LITERAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_COPY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0097 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decode(ByteBuf byteBuf, ByteBuf byteBuf2) {
        while (byteBuf.isReadable()) {
            int i = C64661.$SwitchMap$io$netty$handler$codec$compression$Snappy$State[this.state.ordinal()];
            if (i == 1) {
                this.state = State.READING_PREAMBLE;
            } else if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        int decodeLiteral = decodeLiteral(this.tag, byteBuf, byteBuf2);
                        if (decodeLiteral == -1) {
                            return;
                        }
                        this.state = State.READING_TAG;
                        this.written += decodeLiteral;
                    } else if (i == 5) {
                        byte b = this.tag;
                        int i2 = b & 3;
                        if (i2 == 1) {
                            int decodeCopyWith1ByteOffset = decodeCopyWith1ByteOffset(b, byteBuf, byteBuf2, this.written);
                            if (decodeCopyWith1ByteOffset == -1) {
                                return;
                            }
                            this.state = State.READING_TAG;
                            this.written += decodeCopyWith1ByteOffset;
                        } else if (i2 == 2) {
                            int decodeCopyWith2ByteOffset = decodeCopyWith2ByteOffset(b, byteBuf, byteBuf2, this.written);
                            if (decodeCopyWith2ByteOffset == -1) {
                                return;
                            }
                            this.state = State.READING_TAG;
                            this.written += decodeCopyWith2ByteOffset;
                        } else if (i2 == 3) {
                            int decodeCopyWith4ByteOffset = decodeCopyWith4ByteOffset(b, byteBuf, byteBuf2, this.written);
                            if (decodeCopyWith4ByteOffset == -1) {
                                return;
                            }
                            this.state = State.READING_TAG;
                            this.written += decodeCopyWith4ByteOffset;
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (byteBuf.isReadable()) {
                        return;
                    }
                    this.tag = byteBuf.readByte();
                    int i3 = this.tag & 3;
                    if (i3 == 0) {
                        this.state = State.READING_LITERAL;
                    } else if (i3 == 1 || i3 == 2 || i3 == 3) {
                        this.state = State.READING_COPY;
                    }
                }
            }
            int readPreamble = readPreamble(byteBuf);
            if (readPreamble == -1) {
                return;
            }
            if (readPreamble == 0) {
                this.state = State.READY;
                return;
            } else {
                byteBuf2.ensureWritable(readPreamble);
                this.state = State.READING_TAG;
                if (byteBuf.isReadable()) {
                }
            }
        }
    }

    private static int readPreamble(ByteBuf byteBuf) {
        int i = 0;
        int i2 = 0;
        while (byteBuf.isReadable()) {
            short readUnsignedByte = byteBuf.readUnsignedByte();
            int i3 = i2 + 1;
            i |= (readUnsignedByte & 127) << (i2 * 7);
            if ((readUnsignedByte & 128) == 0) {
                return i;
            }
            if (i3 >= 4) {
                throw new DecompressionException("Preamble is greater than 4 bytes");
            }
            i2 = i3;
        }
        return 0;
    }

    static int decodeLiteral(byte b, ByteBuf byteBuf, ByteBuf byteBuf2) {
        byteBuf.markReaderIndex();
        int i = (b >> 2) & 63;
        switch (i) {
            case 60:
                if (!byteBuf.isReadable()) {
                    return -1;
                }
                i = byteBuf.readUnsignedByte();
                break;
            case 61:
                if (byteBuf.readableBytes() >= 2) {
                    i = byteBuf.readUnsignedShortLE();
                    break;
                } else {
                    return -1;
                }
            case 62:
                if (byteBuf.readableBytes() >= 3) {
                    i = byteBuf.readUnsignedMediumLE();
                    break;
                } else {
                    return -1;
                }
            case 63:
                if (byteBuf.readableBytes() >= 4) {
                    i = byteBuf.readIntLE();
                    break;
                } else {
                    return -1;
                }
        }
        int i2 = i + 1;
        if (byteBuf.readableBytes() < i2) {
            byteBuf.resetReaderIndex();
            return -1;
        }
        byteBuf2.writeBytes(byteBuf, i2);
        return i2;
    }

    private static int decodeCopyWith1ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (!byteBuf.isReadable()) {
            return -1;
        }
        int writerIndex = byteBuf2.writerIndex();
        int i2 = ((b & 28) >> 2) + 4;
        int readUnsignedByte = (((b & 224) << 8) >> 5) | byteBuf.readUnsignedByte();
        validateOffset(readUnsignedByte, i);
        byteBuf2.markReaderIndex();
        if (readUnsignedByte < i2) {
            for (int i3 = i2 / readUnsignedByte; i3 > 0; i3--) {
                byteBuf2.readerIndex(writerIndex - readUnsignedByte);
                byteBuf2.readBytes(byteBuf2, readUnsignedByte);
            }
            int i4 = i2 % readUnsignedByte;
            if (i4 != 0) {
                byteBuf2.readerIndex(writerIndex - readUnsignedByte);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(writerIndex - readUnsignedByte);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static int decodeCopyWith2ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (byteBuf.readableBytes() < 2) {
            return -1;
        }
        int writerIndex = byteBuf2.writerIndex();
        int i2 = ((b >> 2) & 63) + 1;
        int readUnsignedShortLE = byteBuf.readUnsignedShortLE();
        validateOffset(readUnsignedShortLE, i);
        byteBuf2.markReaderIndex();
        if (readUnsignedShortLE < i2) {
            for (int i3 = i2 / readUnsignedShortLE; i3 > 0; i3--) {
                byteBuf2.readerIndex(writerIndex - readUnsignedShortLE);
                byteBuf2.readBytes(byteBuf2, readUnsignedShortLE);
            }
            int i4 = i2 % readUnsignedShortLE;
            if (i4 != 0) {
                byteBuf2.readerIndex(writerIndex - readUnsignedShortLE);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(writerIndex - readUnsignedShortLE);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static int decodeCopyWith4ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (byteBuf.readableBytes() < 4) {
            return -1;
        }
        int writerIndex = byteBuf2.writerIndex();
        int i2 = ((b >> 2) & 63) + 1;
        int readIntLE = byteBuf.readIntLE();
        validateOffset(readIntLE, i);
        byteBuf2.markReaderIndex();
        if (readIntLE < i2) {
            for (int i3 = i2 / readIntLE; i3 > 0; i3--) {
                byteBuf2.readerIndex(writerIndex - readIntLE);
                byteBuf2.readBytes(byteBuf2, readIntLE);
            }
            int i4 = i2 % readIntLE;
            if (i4 != 0) {
                byteBuf2.readerIndex(writerIndex - readIntLE);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(writerIndex - readIntLE);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static void validateOffset(int i, int i2) {
        if (i == 0) {
            throw new DecompressionException("Offset is less than minimum permissible value");
        }
        if (i < 0) {
            throw new DecompressionException("Offset is greater than maximum value supported by this implementation");
        }
        if (i > i2) {
            throw new DecompressionException("Offset exceeds size of chunk");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int calculateChecksum(ByteBuf byteBuf) {
        return calculateChecksum(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    static int calculateChecksum(ByteBuf byteBuf, int i, int i2) {
        Crc32c crc32c = new Crc32c();
        try {
            crc32c.update(byteBuf, i, i2);
            return maskChecksum(crc32c.getValue());
        } finally {
            crc32c.reset();
        }
    }

    static void validateChecksum(int i, ByteBuf byteBuf) {
        validateChecksum(i, byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateChecksum(int i, ByteBuf byteBuf, int i2, int i3) {
        int calculateChecksum = calculateChecksum(byteBuf, i2, i3);
        if (calculateChecksum == i) {
            return;
        }
        throw new DecompressionException("mismatching checksum: " + Integer.toHexString(calculateChecksum) + " (expected: " + Integer.toHexString(i) + ')');
    }
}
