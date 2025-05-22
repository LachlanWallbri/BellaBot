package defpackage;

/* renamed from: $r8$backportedMethods$utility$Double$1$hashCode, reason: invalid class name */
/* loaded from: classes.dex */
public /* synthetic */ class C$r8$backportedMethods$utility$Double$1$hashCode {
    public static /* synthetic */ int hashCode(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }
}
