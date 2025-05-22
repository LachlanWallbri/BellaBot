package org.jboss.netty.handler.codec.http.multipart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
public class InternalAttribute implements InterfaceHttpData {
    protected List<String> value = new ArrayList();

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return "InternalAttribute";
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.InternalAttribute;
    }

    public List<String> getValue() {
        return this.value;
    }

    public void addValue(String str) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        this.value.add(str);
    }

    public void addValue(String str, int i) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        this.value.add(i, str);
    }

    public void setValue(String str, int i) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        this.value.set(i, str);
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Attribute) {
            return getName().equalsIgnoreCase(((Attribute) obj).getName());
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        if (!(interfaceHttpData instanceof InternalAttribute)) {
            throw new ClassCastException("Cannot compare " + getHttpDataType() + " with " + interfaceHttpData.getHttpDataType());
        }
        return compareTo((InternalAttribute) interfaceHttpData);
    }

    public int compareTo(InternalAttribute internalAttribute) {
        return getName().compareToIgnoreCase(internalAttribute.getName());
    }

    public int size() {
        Iterator<String> it = this.value.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().length();
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.value.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }
}
