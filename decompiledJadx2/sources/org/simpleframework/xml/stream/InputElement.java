package org.simpleframework.xml.stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class InputElement implements InputNode {
    private final InputNodeMap map;
    private final EventNode node;
    private final InputNode parent;
    private final NodeReader reader;

    @Override // org.simpleframework.xml.stream.InputNode
    public boolean isElement() {
        return true;
    }

    public InputElement(InputNode inputNode, NodeReader nodeReader, EventNode eventNode) {
        this.map = new InputNodeMap(this, eventNode);
        this.reader = nodeReader;
        this.parent = inputNode;
        this.node = eventNode;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public Object getSource() {
        return this.node.getSource();
    }

    @Override // org.simpleframework.xml.stream.InputNode, org.simpleframework.xml.stream.Node
    public InputNode getParent() {
        return this.parent;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public Position getPosition() {
        return new InputPosition(this.node);
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getName() {
        return this.node.getName();
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public String getPrefix() {
        return this.node.getPrefix();
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public String getReference() {
        return this.node.getReference();
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public boolean isRoot() {
        return this.reader.isRoot(this);
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public InputNode getAttribute(String str) {
        return this.map.get(str);
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public NodeMap<InputNode> getAttributes() {
        return this.map;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getValue() throws Exception {
        return this.reader.readValue(this);
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public InputNode getNext() throws Exception {
        return this.reader.readElement(this);
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public InputNode getNext(String str) throws Exception {
        return this.reader.readElement(this, str);
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public void skip() throws Exception {
        this.reader.skipElement(this);
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public boolean isEmpty() throws Exception {
        if (this.map.isEmpty()) {
            return this.reader.isEmpty(this);
        }
        return false;
    }

    public String toString() {
        return String.format("element %s", getName());
    }
}
