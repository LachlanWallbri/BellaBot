package androidx.dynamicanimation.animation;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class FloatValueHolder {
    private float mValue = 0.0f;

    public FloatValueHolder() {
    }

    public FloatValueHolder(float f) {
        setValue(f);
    }

    public void setValue(float f) {
        this.mValue = f;
    }

    public float getValue() {
        return this.mValue;
    }
}
