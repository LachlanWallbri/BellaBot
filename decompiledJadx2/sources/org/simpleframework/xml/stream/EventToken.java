package org.simpleframework.xml.stream;

import java.util.Iterator;

/* loaded from: classes9.dex */
abstract class EventToken implements EventNode {
    @Override // org.simpleframework.xml.stream.EventNode
    public int getLine() {
        return -1;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public String getName() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public String getPrefix() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public String getReference() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public Object getSource() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public String getValue() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public boolean isEnd() {
        return false;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public boolean isStart() {
        return false;
    }

    @Override // org.simpleframework.xml.stream.EventNode
    public boolean isText() {
        return false;
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return null;
    }
}
