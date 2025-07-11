package io.netty.handler.codec.xml;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class XmlDocumentStart {
    private final String encoding;
    private final String encodingScheme;
    private final boolean standalone;
    private final String version;

    public XmlDocumentStart(String str, String str2, boolean z, String str3) {
        this.encoding = str;
        this.version = str2;
        this.standalone = z;
        this.encodingScheme = str3;
    }

    public String encoding() {
        return this.encoding;
    }

    public String version() {
        return this.version;
    }

    public boolean standalone() {
        return this.standalone;
    }

    public String encodingScheme() {
        return this.encodingScheme;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        XmlDocumentStart xmlDocumentStart = (XmlDocumentStart) obj;
        if (this.standalone != xmlDocumentStart.standalone) {
            return false;
        }
        String str = this.encoding;
        if (str == null ? xmlDocumentStart.encoding != null : !str.equals(xmlDocumentStart.encoding)) {
            return false;
        }
        String str2 = this.encodingScheme;
        if (str2 == null ? xmlDocumentStart.encodingScheme != null : !str2.equals(xmlDocumentStart.encodingScheme)) {
            return false;
        }
        String str3 = this.version;
        String str4 = xmlDocumentStart.version;
        return str3 == null ? str4 == null : str3.equals(str4);
    }

    public int hashCode() {
        String str = this.encoding;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.version;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + (this.standalone ? 1 : 0)) * 31;
        String str3 = this.encodingScheme;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "XmlDocumentStart{encoding='" + this.encoding + "', version='" + this.version + "', standalone=" + this.standalone + ", encodingScheme='" + this.encodingScheme + "'}";
    }
}
