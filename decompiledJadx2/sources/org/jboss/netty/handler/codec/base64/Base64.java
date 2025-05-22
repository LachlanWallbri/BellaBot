package org.jboss.netty.handler.codec.base64;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.HeapChannelBufferFactory;

/* loaded from: classes7.dex */
public final class Base64 {
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    private static final byte WHITE_SPACE_ENC = -5;

    private static byte[] alphabet(Base64Dialect base64Dialect) {
        if (base64Dialect == null) {
            throw new NullPointerException("dialect");
        }
        return base64Dialect.alphabet;
    }

    private static byte[] decodabet(Base64Dialect base64Dialect) {
        if (base64Dialect == null) {
            throw new NullPointerException("dialect");
        }
        return base64Dialect.decodabet;
    }

    private static boolean breakLines(Base64Dialect base64Dialect) {
        if (base64Dialect == null) {
            throw new NullPointerException("dialect");
        }
        return base64Dialect.breakLinesByDefault;
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer) {
        return encode(channelBuffer, Base64Dialect.STANDARD);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, Base64Dialect base64Dialect) {
        return encode(channelBuffer, breakLines(base64Dialect), base64Dialect);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, ChannelBufferFactory channelBufferFactory) {
        return encode(channelBuffer, Base64Dialect.STANDARD, channelBufferFactory);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, Base64Dialect base64Dialect, ChannelBufferFactory channelBufferFactory) {
        return encode(channelBuffer, breakLines(base64Dialect), base64Dialect, channelBufferFactory);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, boolean z) {
        return encode(channelBuffer, z, Base64Dialect.STANDARD);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, boolean z, Base64Dialect base64Dialect) {
        return encode(channelBuffer, z, base64Dialect, HeapChannelBufferFactory.getInstance());
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, boolean z, ChannelBufferFactory channelBufferFactory) {
        return encode(channelBuffer, z, Base64Dialect.STANDARD, channelBufferFactory);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, boolean z, Base64Dialect base64Dialect, ChannelBufferFactory channelBufferFactory) {
        if (channelBuffer == null) {
            throw new NullPointerException("src");
        }
        ChannelBuffer encode = encode(channelBuffer, channelBuffer.readerIndex(), channelBuffer.readableBytes(), z, base64Dialect, channelBufferFactory);
        channelBuffer.readerIndex(channelBuffer.writerIndex());
        return encode;
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2) {
        return encode(channelBuffer, i, i2, Base64Dialect.STANDARD);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2, Base64Dialect base64Dialect) {
        return encode(channelBuffer, i, i2, breakLines(base64Dialect), base64Dialect);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2, ChannelBufferFactory channelBufferFactory) {
        return encode(channelBuffer, i, i2, Base64Dialect.STANDARD, channelBufferFactory);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2, Base64Dialect base64Dialect, ChannelBufferFactory channelBufferFactory) {
        return encode(channelBuffer, i, i2, breakLines(base64Dialect), base64Dialect, channelBufferFactory);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2, boolean z) {
        return encode(channelBuffer, i, i2, z, Base64Dialect.STANDARD);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2, boolean z, Base64Dialect base64Dialect) {
        return encode(channelBuffer, i, i2, z, base64Dialect, HeapChannelBufferFactory.getInstance());
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2, boolean z, ChannelBufferFactory channelBufferFactory) {
        return encode(channelBuffer, i, i2, z, Base64Dialect.STANDARD, channelBufferFactory);
    }

    public static ChannelBuffer encode(ChannelBuffer channelBuffer, int i, int i2, boolean z, Base64Dialect base64Dialect, ChannelBufferFactory channelBufferFactory) {
        if (channelBuffer == null) {
            throw new NullPointerException("src");
        }
        if (base64Dialect == null) {
            throw new NullPointerException("dialect");
        }
        if (channelBufferFactory == null) {
            throw new NullPointerException("bufferFactory");
        }
        int i3 = (i2 * 4) / 3;
        ChannelBuffer buffer = channelBufferFactory.getBuffer(channelBuffer.order(), (i2 % 3 > 0 ? 4 : 0) + i3 + (z ? i3 / 76 : 0));
        int i4 = i2 - 2;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i4) {
            encode3to4(channelBuffer, i5 + i, 3, buffer, i6, base64Dialect);
            i7 += 4;
            if (z && i7 == 76) {
                buffer.setByte(i6 + 4, 10);
                i6++;
                i7 = 0;
            }
            i5 += 3;
            i6 += 4;
        }
        if (i5 < i2) {
            encode3to4(channelBuffer, i5 + i, i2 - i5, buffer, i6, base64Dialect);
            i6 += 4;
        }
        return buffer.slice(0, i6);
    }

    private static void encode3to4(ChannelBuffer channelBuffer, int i, int i2, ChannelBuffer channelBuffer2, int i3, Base64Dialect base64Dialect) {
        byte[] alphabet = alphabet(base64Dialect);
        int i4 = (i2 > 0 ? (channelBuffer.getByte(i) << 24) >>> 8 : 0) | (i2 > 1 ? (channelBuffer.getByte(i + 1) << 24) >>> 16 : 0) | (i2 > 2 ? (channelBuffer.getByte(i + 2) << 24) >>> 24 : 0);
        if (i2 == 1) {
            channelBuffer2.setByte(i3, alphabet[i4 >>> 18]);
            channelBuffer2.setByte(i3 + 1, alphabet[(i4 >>> 12) & 63]);
            channelBuffer2.setByte(i3 + 2, 61);
            channelBuffer2.setByte(i3 + 3, 61);
            return;
        }
        if (i2 == 2) {
            channelBuffer2.setByte(i3, alphabet[i4 >>> 18]);
            channelBuffer2.setByte(i3 + 1, alphabet[(i4 >>> 12) & 63]);
            channelBuffer2.setByte(i3 + 2, alphabet[(i4 >>> 6) & 63]);
            channelBuffer2.setByte(i3 + 3, 61);
            return;
        }
        if (i2 != 3) {
            return;
        }
        channelBuffer2.setByte(i3, alphabet[i4 >>> 18]);
        channelBuffer2.setByte(i3 + 1, alphabet[(i4 >>> 12) & 63]);
        channelBuffer2.setByte(i3 + 2, alphabet[(i4 >>> 6) & 63]);
        channelBuffer2.setByte(i3 + 3, alphabet[i4 & 63]);
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer) {
        return decode(channelBuffer, Base64Dialect.STANDARD);
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer, Base64Dialect base64Dialect) {
        return decode(channelBuffer, base64Dialect, HeapChannelBufferFactory.getInstance());
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer, ChannelBufferFactory channelBufferFactory) {
        return decode(channelBuffer, Base64Dialect.STANDARD, channelBufferFactory);
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer, Base64Dialect base64Dialect, ChannelBufferFactory channelBufferFactory) {
        if (channelBuffer == null) {
            throw new NullPointerException("src");
        }
        ChannelBuffer decode = decode(channelBuffer, channelBuffer.readerIndex(), channelBuffer.readableBytes(), base64Dialect, channelBufferFactory);
        channelBuffer.readerIndex(channelBuffer.writerIndex());
        return decode;
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer, int i, int i2) {
        return decode(channelBuffer, i, i2, Base64Dialect.STANDARD);
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer, int i, int i2, Base64Dialect base64Dialect) {
        return decode(channelBuffer, i, i2, base64Dialect, HeapChannelBufferFactory.getInstance());
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer, int i, int i2, ChannelBufferFactory channelBufferFactory) {
        return decode(channelBuffer, i, i2, Base64Dialect.STANDARD, channelBufferFactory);
    }

    public static ChannelBuffer decode(ChannelBuffer channelBuffer, int i, int i2, Base64Dialect base64Dialect, ChannelBufferFactory channelBufferFactory) {
        if (channelBuffer == null) {
            throw new NullPointerException("src");
        }
        if (base64Dialect == null) {
            throw new NullPointerException("dialect");
        }
        if (channelBufferFactory == null) {
            throw new NullPointerException("bufferFactory");
        }
        byte[] decodabet = decodabet(base64Dialect);
        ChannelBuffer buffer = channelBufferFactory.getBuffer(channelBuffer.order(), (i2 * 3) / 4);
        byte[] bArr = new byte[4];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = i; i5 < i + i2; i5++) {
            byte b = (byte) (channelBuffer.getByte(i5) & Byte.MAX_VALUE);
            byte b2 = decodabet[b];
            if (b2 < -5) {
                throw new IllegalArgumentException("bad Base64 input character at " + i5 + ": " + ((int) channelBuffer.getUnsignedByte(i5)) + " (decimal)");
            }
            if (b2 >= -1) {
                int i6 = i3 + 1;
                bArr[i3] = b;
                if (i6 > 3) {
                    i4 += decode4to3(bArr, 0, buffer, i4, base64Dialect);
                    if (b == 61) {
                        break;
                    }
                    i3 = 0;
                } else {
                    i3 = i6;
                }
            }
        }
        return buffer.slice(0, i4);
    }

    private static int decode4to3(byte[] bArr, int i, ChannelBuffer channelBuffer, int i2, Base64Dialect base64Dialect) {
        byte[] decodabet = decodabet(base64Dialect);
        int i3 = i + 2;
        if (bArr[i3] == 61) {
            channelBuffer.setByte(i2, (byte) ((((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i]] & 255) << 18)) >>> 16));
            return 1;
        }
        int i4 = i + 3;
        if (bArr[i4] == 61) {
            int i5 = ((decodabet[bArr[i3]] & 255) << 6) | ((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i]] & 255) << 18);
            channelBuffer.setByte(i2, (byte) (i5 >>> 16));
            channelBuffer.setByte(i2 + 1, (byte) (i5 >>> 8));
            return 2;
        }
        try {
            int i6 = (decodabet[bArr[i4]] & 255) | ((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i]] & 255) << 18) | ((decodabet[bArr[i3]] & 255) << 6);
            channelBuffer.setByte(i2, (byte) (i6 >> 16));
            channelBuffer.setByte(i2 + 1, (byte) (i6 >> 8));
            channelBuffer.setByte(i2 + 2, (byte) i6);
            return 3;
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("not encoded in Base64");
        }
    }

    private Base64() {
    }
}
