package org.simpleframework.xml.stream;

/* loaded from: classes9.dex */
class InputPosition implements Position {
    private EventNode source;

    public InputPosition(EventNode eventNode) {
        this.source = eventNode;
    }

    @Override // org.simpleframework.xml.stream.Position
    public int getLine() {
        return this.source.getLine();
    }

    @Override // org.simpleframework.xml.stream.Position
    public String toString() {
        return String.format("line %s", Integer.valueOf(getLine()));
    }
}
