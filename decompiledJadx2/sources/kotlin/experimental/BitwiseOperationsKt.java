package kotlin.experimental;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: bitwiseOperations.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0005\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f¨\u0006\u0007"}, m3961d2 = {"and", "", "other", "", "inv", "or", "xor", "kotlin-stdlib"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class BitwiseOperationsKt {
    private static final byte and(byte b, byte b2) {
        return (byte) (b & b2);
    }

    private static final short and(short s, short s2) {
        return (short) (s & s2);
    }

    private static final byte inv(byte b) {
        return (byte) (~b);
    }

    private static final short inv(short s) {
        return (short) (~s);
    }

    /* renamed from: or */
    private static final byte m3977or(byte b, byte b2) {
        return (byte) (b | b2);
    }

    /* renamed from: or */
    private static final short m3978or(short s, short s2) {
        return (short) (s | s2);
    }

    private static final byte xor(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    private static final short xor(short s, short s2) {
        return (short) (s ^ s2);
    }
}
