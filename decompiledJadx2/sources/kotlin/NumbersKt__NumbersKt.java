package kotlin;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Numbers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\b\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\r\u0010\t\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\t\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0003*\u00020\u0003H\u0087\b¨\u0006\u000b"}, m3961d2 = {"countLeadingZeroBits", "", "", "", "countOneBits", "countTrailingZeroBits", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "kotlin-stdlib"}, m3962k = 5, m3963mv = {1, 1, 16}, m3965xi = 1, m3966xs = "kotlin/NumbersKt")
/* loaded from: classes2.dex */
class NumbersKt__NumbersKt extends NumbersKt__NumbersJVMKt {
    public static final byte rotateLeft(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> (8 - i2)) | (b << i2));
    }

    public static final short rotateLeft(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & 65535) >>> (16 - i2)) | (s << i2));
    }

    public static final byte rotateRight(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> i2) | (b << (8 - i2)));
    }

    public static final short rotateRight(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & 65535) >>> i2) | (s << (16 - i2)));
    }

    private static final int countOneBits(byte b) {
        return Integer.bitCount(b & 255);
    }

    private static final int countLeadingZeroBits(byte b) {
        return Integer.numberOfLeadingZeros(b & 255) - 24;
    }

    private static final int countTrailingZeroBits(byte b) {
        return Integer.numberOfTrailingZeros(b | 256);
    }

    private static final byte takeHighestOneBit(byte b) {
        return (byte) Integer.highestOneBit(b & 255);
    }

    private static final byte takeLowestOneBit(byte b) {
        return (byte) Integer.lowestOneBit(b);
    }

    private static final int countOneBits(short s) {
        return Integer.bitCount(s & UShort.MAX_VALUE);
    }

    private static final int countLeadingZeroBits(short s) {
        return Integer.numberOfLeadingZeros(s & UShort.MAX_VALUE) - 16;
    }

    private static final int countTrailingZeroBits(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    private static final short takeHighestOneBit(short s) {
        return (short) Integer.highestOneBit(s & UShort.MAX_VALUE);
    }

    private static final short takeLowestOneBit(short s) {
        return (short) Integer.lowestOneBit(s);
    }
}
