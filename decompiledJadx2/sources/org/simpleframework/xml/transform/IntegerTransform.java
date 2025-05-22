package org.simpleframework.xml.transform;

/* loaded from: classes9.dex */
class IntegerTransform implements Transform<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Integer read(String str) {
        return Integer.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Integer num) {
        return num.toString();
    }
}
