package org.simpleframework.xml.transform;

/* loaded from: classes9.dex */
class ShortTransform implements Transform<Short> {
    @Override // org.simpleframework.xml.transform.Transform
    public Short read(String str) {
        return Short.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Short sh) {
        return sh.toString();
    }
}
