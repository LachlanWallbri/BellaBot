package org.simpleframework.xml.stream;

/* loaded from: classes9.dex */
public class CamelCaseStyle implements Style {
    private final Builder builder;
    private final Style style;

    public CamelCaseStyle() {
        this(true, false);
    }

    public CamelCaseStyle(boolean z) {
        this(z, false);
    }

    public CamelCaseStyle(boolean z, boolean z2) {
        this.style = new CamelCaseBuilder(z, z2);
        this.builder = new Builder(this.style);
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getAttribute(String str) {
        return this.builder.getAttribute(str);
    }

    public void setAttribute(String str, String str2) {
        this.builder.setAttribute(str, str2);
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getElement(String str) {
        return this.builder.getElement(str);
    }

    public void setElement(String str, String str2) {
        this.builder.setElement(str, str2);
    }
}
