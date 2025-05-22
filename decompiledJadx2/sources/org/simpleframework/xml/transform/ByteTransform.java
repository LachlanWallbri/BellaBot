package org.simpleframework.xml.transform;

/* loaded from: classes9.dex */
class ByteTransform implements Transform<Byte> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Byte read(String str) {
        return Byte.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Byte b) {
        return b.toString();
    }
}
