package kotlin.reflect.jvm.internal.impl.types;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Variance.kt */
/* loaded from: classes2.dex */
public enum Variance {
    INVARIANT("", true, true, 0),
    IN_VARIANCE("in", true, false, -1),
    OUT_VARIANCE("out", false, true, 1);

    private final boolean allowsInPosition;
    private final boolean allowsOutPosition;
    private final String label;
    private final int superpositionFactor;

    Variance(String str, boolean z, boolean z2, int i) {
        this.label = str;
        this.allowsInPosition = z;
        this.allowsOutPosition = z2;
        this.superpositionFactor = i;
    }

    public final String getLabel() {
        return this.label;
    }

    public final boolean getAllowsOutPosition() {
        return this.allowsOutPosition;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.label;
    }
}
