package io.netty.handler.codec.xml;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class XmlDTD {
    private final String text;

    public XmlDTD(String str) {
        this.text = str;
    }

    public String text() {
        return this.text;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.text;
        String str2 = ((XmlDTD) obj).text;
        return str == null ? str2 == null : str.equals(str2);
    }

    public int hashCode() {
        String str = this.text;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "XmlDTD{text='" + this.text + "'}";
    }
}
