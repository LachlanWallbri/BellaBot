package org.apache.commons.codec.net;

import java.nio.ByteBuffer;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes7.dex */
public class PercentCodec implements BinaryEncoder, BinaryDecoder {
    private static final byte ESCAPE_CHAR = 37;
    private final BitSet alwaysEncodeChars;
    private int alwaysEncodeCharsMax;
    private int alwaysEncodeCharsMin;
    private final boolean plusForSpace;

    private boolean isAsciiChar(byte b) {
        return b >= 0;
    }

    public PercentCodec() {
        this.alwaysEncodeChars = new BitSet();
        this.alwaysEncodeCharsMin = Integer.MAX_VALUE;
        this.alwaysEncodeCharsMax = Integer.MIN_VALUE;
        this.plusForSpace = false;
        insertAlwaysEncodeChar(ESCAPE_CHAR);
    }

    public PercentCodec(byte[] bArr, boolean z) {
        this.alwaysEncodeChars = new BitSet();
        this.alwaysEncodeCharsMin = Integer.MAX_VALUE;
        this.alwaysEncodeCharsMax = Integer.MIN_VALUE;
        this.plusForSpace = z;
        insertAlwaysEncodeChars(bArr);
    }

    private void insertAlwaysEncodeChars(byte[] bArr) {
        if (bArr != null) {
            for (byte b : bArr) {
                insertAlwaysEncodeChar(b);
            }
        }
        insertAlwaysEncodeChar(ESCAPE_CHAR);
    }

    private void insertAlwaysEncodeChar(byte b) {
        this.alwaysEncodeChars.set(b);
        if (b < this.alwaysEncodeCharsMin) {
            this.alwaysEncodeCharsMin = b;
        }
        if (b > this.alwaysEncodeCharsMax) {
            this.alwaysEncodeCharsMax = b;
        }
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) throws EncoderException {
        if (bArr == null) {
            return null;
        }
        int expectedEncodingBytes = expectedEncodingBytes(bArr);
        boolean z = expectedEncodingBytes != bArr.length;
        return (z || (this.plusForSpace && containsSpace(bArr))) ? doEncode(bArr, expectedEncodingBytes, z) : bArr;
    }

    private byte[] doEncode(byte[] bArr, int i, boolean z) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (byte b : bArr) {
            if (z && canEncode(b)) {
                if (b < 0) {
                    b = (byte) (b + 256);
                }
                char hexDigit = Utils.hexDigit(b >> 4);
                char hexDigit2 = Utils.hexDigit(b);
                allocate.put(ESCAPE_CHAR);
                allocate.put((byte) hexDigit);
                allocate.put((byte) hexDigit2);
            } else if (this.plusForSpace && b == 32) {
                allocate.put((byte) 43);
            } else {
                allocate.put(b);
            }
        }
        return allocate.array();
    }

    private int expectedEncodingBytes(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i += canEncode(b) ? 3 : 1;
        }
        return i;
    }

    private boolean containsSpace(byte[] bArr) {
        for (byte b : bArr) {
            if (b == 32) {
                return true;
            }
        }
        return false;
    }

    private boolean canEncode(byte b) {
        return !isAsciiChar(b) || (inAlwaysEncodeCharsRange(b) && this.alwaysEncodeChars.get(b));
    }

    private boolean inAlwaysEncodeCharsRange(byte b) {
        return b >= this.alwaysEncodeCharsMin && b <= this.alwaysEncodeCharsMax;
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) throws DecoderException {
        if (bArr == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(expectedDecodingBytes(bArr));
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == 37) {
                int i2 = i + 1;
                try {
                    int digit16 = Utils.digit16(bArr[i2]);
                    i = i2 + 1;
                    allocate.put((byte) ((digit16 << 4) + Utils.digit16(bArr[i])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid percent decoding: ", e);
                }
            } else if (this.plusForSpace && b == 43) {
                allocate.put((byte) 32);
            } else {
                allocate.put(b);
            }
            i++;
        }
        return allocate.array();
    }

    private int expectedDecodingBytes(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            i += bArr[i] == 37 ? 3 : 1;
            i2++;
        }
        return i2;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent encoded");
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent decoded");
    }
}
