package org.simpleframework.xml.stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class OutputDocument implements OutputNode {
    private String comment;
    private String name;
    private String reference;
    private OutputStack stack;
    private String value;
    private NodeWriter writer;
    private OutputNodeMap table = new OutputNodeMap(this);
    private Mode mode = Mode.INHERIT;

    @Override // org.simpleframework.xml.stream.Node
    public String getName() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public NamespaceMap getNamespaces() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.OutputNode, org.simpleframework.xml.stream.Node
    public OutputNode getParent() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getPrefix() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getPrefix(boolean z) {
        return null;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public boolean isRoot() {
        return true;
    }

    public OutputDocument(NodeWriter nodeWriter, OutputStack outputStack) {
        this.writer = nodeWriter;
        this.stack = outputStack;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getReference() {
        return this.reference;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setReference(String str) {
        this.reference = str;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getValue() throws Exception {
        return this.value;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getComment() {
        return this.comment;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public Mode getMode() {
        return this.mode;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public OutputNode setAttribute(String str, String str2) {
        return this.table.put(str, str2);
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public NodeMap<OutputNode> getAttributes() {
        return this.table;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setName(String str) {
        this.name = str;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setValue(String str) {
        this.value = str;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setComment(String str) {
        this.comment = str;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setData(boolean z) {
        if (z) {
            this.mode = Mode.DATA;
        } else {
            this.mode = Mode.ESCAPE;
        }
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public OutputNode getChild(String str) throws Exception {
        return this.writer.writeElement(this, str);
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void remove() throws Exception {
        if (this.stack.isEmpty()) {
            throw new NodeException("No root node");
        }
        this.stack.bottom().remove();
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void commit() throws Exception {
        if (this.stack.isEmpty()) {
            throw new NodeException("No root node");
        }
        this.stack.bottom().commit();
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public boolean isCommitted() {
        return this.stack.isEmpty();
    }
}
