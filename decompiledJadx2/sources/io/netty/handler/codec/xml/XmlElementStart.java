package io.netty.handler.codec.xml;

import java.util.LinkedList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class XmlElementStart extends XmlElement {
    private final List<XmlAttribute> attributes;

    public XmlElementStart(String str, String str2, String str3) {
        super(str, str2, str3);
        this.attributes = new LinkedList();
    }

    public List<XmlAttribute> attributes() {
        return this.attributes;
    }

    @Override // io.netty.handler.codec.xml.XmlElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        List<XmlAttribute> list = this.attributes;
        List<XmlAttribute> list2 = ((XmlElementStart) obj).attributes;
        return list == null ? list2 == null : list.equals(list2);
    }

    @Override // io.netty.handler.codec.xml.XmlElement
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        List<XmlAttribute> list = this.attributes;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    @Override // io.netty.handler.codec.xml.XmlElement
    public String toString() {
        return "XmlElementStart{attributes=" + this.attributes + super.toString() + "} ";
    }
}
