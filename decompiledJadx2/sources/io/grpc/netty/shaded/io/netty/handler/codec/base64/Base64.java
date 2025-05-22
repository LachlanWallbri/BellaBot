package io.grpc.netty.shaded.io.netty.handler.codec.base64;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Base64 {
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    private static final byte WHITE_SPACE_ENC = -5;

    static int decodedBufferSize(int i) {
        return i - (i >>> 2);
    }

    private static int toInt(byte b) {
        return (b & 255) << 16;
    }

    private static int toIntBE(int i) {
        return (i & 255) | (16711680 & i) | (65280 & i);
    }

    private static int toIntBE(short s) {
        return ((s & 255) << 8) | ((65280 & s) << 8);
    }

    private static int toIntLE(int i) {
        return ((i & 16711680) >>> 16) | ((i & 255) << 16) | (65280 & i);
    }

    private static int toIntLE(short s) {
        return (s & 65280) | ((s & 255) << 16);
    }

    private static byte[] alphabet(Base64Dialect base64Dialect) {
        return ((Base64Dialect) ObjectUtil.checkNotNull(base64Dialect, "dialect")).alphabet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] decodabet(Base64Dialect base64Dialect) {
        return ((Base64Dialect) ObjectUtil.checkNotNull(base64Dialect, "dialect")).decodabet;
    }

    private static boolean breakLines(Base64Dialect base64Dialect) {
        return ((Base64Dialect) ObjectUtil.checkNotNull(base64Dialect, "dialect")).breakLinesByDefault;
    }

    public static ByteBuf encode(ByteBuf byteBuf) {
        return encode(byteBuf, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, Base64Dialect base64Dialect) {
        return encode(byteBuf, breakLines(base64Dialect), base64Dialect);
    }

    public static ByteBuf encode(ByteBuf byteBuf, boolean z) {
        return encode(byteBuf, z, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, boolean z, Base64Dialect base64Dialect) {
        ObjectUtil.checkNotNull(byteBuf, "src");
        ByteBuf encode = encode(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), z, base64Dialect);
        byteBuf.readerIndex(byteBuf.writerIndex());
        return encode;
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2) {
        return encode(byteBuf, i, i2, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, Base64Dialect base64Dialect) {
        return encode(byteBuf, i, i2, breakLines(base64Dialect), base64Dialect);
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, boolean z) {
        return encode(byteBuf, i, i2, z, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, boolean z, Base64Dialect base64Dialect) {
        return encode(byteBuf, i, i2, z, base64Dialect, byteBuf.alloc());
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, boolean z, Base64Dialect base64Dialect, ByteBufAllocator byteBufAllocator) {
        ObjectUtil.checkNotNull(byteBuf, "src");
        ObjectUtil.checkNotNull(base64Dialect, "dialect");
        ByteBuf order = byteBufAllocator.buffer(encodedBufferSize(i2, z)).order(byteBuf.order());
        byte[] alphabet = alphabet(base64Dialect);
        int i3 = i2 - 2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i3) {
            encode3to4(byteBuf, i4 + i, 3, order, i5, alphabet);
            i6 += 4;
            if (z && i6 == 76) {
                order.setByte(i5 + 4, 10);
                i5++;
                i6 = 0;
            }
            i4 += 3;
            i5 += 4;
        }
        if (i4 < i2) {
            encode3to4(byteBuf, i4 + i, i2 - i4, order, i5, alphabet);
            i5 += 4;
        }
        if (i5 > 1 && order.getByte(i5 - 1) == 10) {
            i5--;
        }
        return order.slice(0, i5);
    }

    private static void encode3to4(ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2, int i3, byte[] bArr) {
        int i4 = 0;
        if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
            if (i2 == 1) {
                i4 = toInt(byteBuf.getByte(i));
            } else if (i2 == 2) {
                i4 = toIntBE(byteBuf.getShort(i));
            } else if (i2 > 0) {
                i4 = toIntBE(byteBuf.getMedium(i));
            }
            encode3to4BigEndian(i4, i2, byteBuf2, i3, bArr);
            return;
        }
        if (i2 == 1) {
            i4 = toInt(byteBuf.getByte(i));
        } else if (i2 == 2) {
            i4 = toIntLE(byteBuf.getShort(i));
        } else if (i2 > 0) {
            i4 = toIntLE(byteBuf.getMedium(i));
        }
        encode3to4LittleEndian(i4, i2, byteBuf2, i3, bArr);
    }

    static int encodedBufferSize(int i, boolean z) {
        long j = (i << 2) / 3;
        long j2 = (3 + j) & (-4);
        if (z) {
            j2 += j / 76;
        }
        if (j2 < 2147483647L) {
            return (int) j2;
        }
        return Integer.MAX_VALUE;
    }

    private static void encode3to4BigEndian(int i, int i2, ByteBuf byteBuf, int i3, byte[] bArr) {
        if (i2 == 1) {
            byteBuf.setInt(i3, (bArr[(i >>> 12) & 63] << 16) | (bArr[i >>> 18] << 24) | 15616 | 61);
        } else if (i2 == 2) {
            byteBuf.setInt(i3, (bArr[(i >>> 6) & 63] << 8) | (bArr[i >>> 18] << 24) | (bArr[(i >>> 12) & 63] << 16) | 61);
        } else {
            if (i2 != 3) {
                return;
            }
            byteBuf.setInt(i3, bArr[i & 63] | (bArr[i >>> 18] << 24) | (bArr[(i >>> 12) & 63] << 16) | (bArr[(i >>> 6) & 63] << 8));
        }
    }

    private static void encode3to4LittleEndian(int i, int i2, ByteBuf byteBuf, int i3, byte[] bArr) {
        if (i2 == 1) {
            byteBuf.setInt(i3, (bArr[(i >>> 12) & 63] << 8) | bArr[i >>> 18] | 3997696 | 1023410176);
        } else if (i2 == 2) {
            byteBuf.setInt(i3, (bArr[(i >>> 6) & 63] << 16) | bArr[i >>> 18] | (bArr[(i >>> 12) & 63] << 8) | 1023410176);
        } else {
            if (i2 != 3) {
                return;
            }
            byteBuf.setInt(i3, (bArr[i & 63] << 24) | bArr[i >>> 18] | (bArr[(i >>> 12) & 63] << 8) | (bArr[(i >>> 6) & 63] << 16));
        }
    }

    public static ByteBuf decode(ByteBuf byteBuf) {
        return decode(byteBuf, Base64Dialect.STANDARD);
    }

    public static ByteBuf decode(ByteBuf byteBuf, Base64Dialect base64Dialect) {
        ObjectUtil.checkNotNull(byteBuf, "src");
        ByteBuf decode = decode(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), base64Dialect);
        byteBuf.readerIndex(byteBuf.writerIndex());
        return decode;
    }

    public static ByteBuf decode(ByteBuf byteBuf, int i, int i2) {
        return decode(byteBuf, i, i2, Base64Dialect.STANDARD);
    }

    public static ByteBuf decode(ByteBuf byteBuf, int i, int i2, Base64Dialect base64Dialect) {
        return decode(byteBuf, i, i2, base64Dialect, byteBuf.alloc());
    }

    public static ByteBuf decode(ByteBuf byteBuf, int i, int i2, Base64Dialect base64Dialect, ByteBufAllocator byteBufAllocator) {
        ObjectUtil.checkNotNull(byteBuf, "src");
        ObjectUtil.checkNotNull(base64Dialect, "dialect");
        return new Decoder().decode(byteBuf, i, i2, byteBufAllocator, base64Dialect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Decoder implements ByteProcessor {

        /* renamed from: b4 */
        private final byte[] f8349b4;
        private int b4Posn;
        private byte[] decodabet;
        private ByteBuf dest;
        private int outBuffPosn;

        private Decoder() {
            this.f8349b4 = new byte[4];
        }

        ByteBuf decode(ByteBuf byteBuf, int i, int i2, ByteBufAllocator byteBufAllocator, Base64Dialect base64Dialect) {
            this.dest = byteBufAllocator.buffer(Base64.decodedBufferSize(i2)).order(byteBuf.order());
            this.decodabet = Base64.decodabet(base64Dialect);
            try {
                byteBuf.forEachByte(i, i2, this);
                return this.dest.slice(0, this.outBuffPosn);
            } catch (Throwable th) {
                this.dest.release();
                PlatformDependent.throwException(th);
                return null;
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            byte[] bArr;
            byte b2;
            if (b <= 0 || (b2 = (bArr = this.decodabet)[b]) < -5) {
                throw new IllegalArgumentException("invalid Base64 input character: " + ((int) ((short) (b & 255))) + " (decimal)");
            }
            if (b2 >= -1) {
                byte[] bArr2 = this.f8349b4;
                int i = this.b4Posn;
                this.b4Posn = i + 1;
                bArr2[i] = b;
                if (this.b4Posn > 3) {
                    int i2 = this.outBuffPosn;
                    this.outBuffPosn = i2 + decode4to3(bArr2, this.dest, i2, bArr);
                    this.b4Posn = 0;
                    return b != 61;
                }
            }
            return true;
        }

        private static int decode4to3(byte[] bArr, ByteBuf byteBuf, int i, byte[] bArr2) {
            int i2;
            int i3;
            int i4;
            byte b = bArr[0];
            byte b2 = bArr[1];
            byte b3 = bArr[2];
            if (b3 == 61) {
                try {
                    byteBuf.setByte(i, ((bArr2[b] & 255) << 2) | ((bArr2[b2] & 255) >>> 4));
                    return 1;
                } catch (IndexOutOfBoundsException unused) {
                    throw new IllegalArgumentException("not encoded in Base64");
                }
            }
            byte b4 = bArr[3];
            if (b4 == 61) {
                byte b5 = bArr2[b2];
                try {
                    if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
                        i4 = ((b5 & 15) << 4) | ((((bArr2[b] & 63) << 2) | ((b5 & 240) >> 4)) << 8) | ((bArr2[b3] & 252) >>> 2);
                    } else {
                        i4 = ((((b5 & 15) << 4) | ((bArr2[b3] & 252) >>> 2)) << 8) | ((bArr2[b] & 63) << 2) | ((b5 & 240) >> 4);
                    }
                    byteBuf.setShort(i, i4);
                    return 2;
                } catch (IndexOutOfBoundsException unused2) {
                    throw new IllegalArgumentException("not encoded in Base64");
                }
            }
            try {
                if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
                    i2 = ((bArr2[b] & 63) << 18) | ((bArr2[b2] & 255) << 12) | ((bArr2[b3] & 255) << 6);
                    i3 = bArr2[b4] & 255;
                } else {
                    byte b6 = bArr2[b2];
                    byte b7 = bArr2[b3];
                    i2 = ((bArr2[b] & 63) << 2) | ((b6 & 15) << 12) | ((b6 & 240) >>> 4) | ((b7 & 3) << 22) | ((b7 & 252) << 6);
                    i3 = (bArr2[b4] & 255) << 16;
                }
                byteBuf.setMedium(i, i3 | i2);
                return 3;
            } catch (IndexOutOfBoundsException unused3) {
                throw new IllegalArgumentException("not encoded in Base64");
            }
        }
    }

    private Base64() {
    }
}
