package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: numbers.kt */
/* loaded from: classes2.dex */
public final class NumberWithRadix {
    private final String number;
    private final int radix;

    public final String component1() {
        return this.number;
    }

    public final int component2() {
        return this.radix;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NumberWithRadix) {
                NumberWithRadix numberWithRadix = (NumberWithRadix) obj;
                if (Intrinsics.areEqual(this.number, numberWithRadix.number)) {
                    if (this.radix == numberWithRadix.radix) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.number;
        return ((str != null ? str.hashCode() : 0) * 31) + this.radix;
    }

    public String toString() {
        return "NumberWithRadix(number=" + this.number + ", radix=" + this.radix + ")";
    }

    public NumberWithRadix(String number, int i) {
        Intrinsics.checkParameterIsNotNull(number, "number");
        this.number = number;
        this.radix = i;
    }
}
