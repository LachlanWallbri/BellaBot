package io.netty.handler.codec.xml;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class XmlAttribute {
    private final String name;
    private final String namespace;
    private final String prefix;
    private final String type;
    private final String value;

    public XmlAttribute(String str, String str2, String str3, String str4, String str5) {
        this.type = str;
        this.name = str2;
        this.prefix = str3;
        this.namespace = str4;
        this.value = str5;
    }

    public String type() {
        return this.type;
    }

    public String name() {
        return this.name;
    }

    public String prefix() {
        return this.prefix;
    }

    public String namespace() {
        return this.namespace;
    }

    public String value() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        XmlAttribute xmlAttribute = (XmlAttribute) obj;
        if (!this.name.equals(xmlAttribute.name)) {
            return false;
        }
        String str = this.namespace;
        if (str == null ? xmlAttribute.namespace != null : !str.equals(xmlAttribute.namespace)) {
            return false;
        }
        String str2 = this.prefix;
        if (str2 == null ? xmlAttribute.prefix != null : !str2.equals(xmlAttribute.prefix)) {
            return false;
        }
        String str3 = this.type;
        if (str3 == null ? xmlAttribute.type != null : !str3.equals(xmlAttribute.type)) {
            return false;
        }
        String str4 = this.value;
        String str5 = xmlAttribute.value;
        return str4 == null ? str5 == null : str4.equals(str5);
    }

    public int hashCode() {
        String str = this.type;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.name.hashCode()) * 31;
        String str2 = this.prefix;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.namespace;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.value;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "XmlAttribute{type='" + this.type + "', name='" + this.name + "', prefix='" + this.prefix + "', namespace='" + this.namespace + "', value='" + this.value + "'}";
    }
}
