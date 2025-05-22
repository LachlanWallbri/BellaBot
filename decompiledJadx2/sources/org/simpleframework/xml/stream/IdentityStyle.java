package org.simpleframework.xml.stream;

/* loaded from: classes9.dex */
class IdentityStyle implements Style {
    @Override // org.simpleframework.xml.stream.Style
    public String getAttribute(String str) {
        return str;
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getElement(String str) {
        return str;
    }
}
