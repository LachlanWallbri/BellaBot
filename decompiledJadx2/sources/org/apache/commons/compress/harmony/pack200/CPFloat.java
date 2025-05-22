package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPFloat extends CPConstant {
    private final float theFloat;

    public CPFloat(float f) {
        this.theFloat = f;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return Float.compare(this.theFloat, ((CPFloat) obj).theFloat);
    }

    public float getFloat() {
        return this.theFloat;
    }
}
