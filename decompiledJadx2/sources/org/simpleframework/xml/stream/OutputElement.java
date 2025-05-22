package org.simpleframework.xml.stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class OutputElement implements OutputNode {
    private String comment;
    private String name;
    private OutputNode parent;
    private String reference;
    private NamespaceMap scope;
    private String value;
    private NodeWriter writer;
    private OutputNodeMap table = new OutputNodeMap(this);
    private Mode mode = Mode.INHERIT;

    public OutputElement(OutputNode outputNode, NodeWriter nodeWriter, String str) {
        this.scope = new PrefixResolver(outputNode);
        this.writer = nodeWriter;
        this.parent = outputNode;
        this.name = str;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getPrefix() {
        return getPrefix(true);
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getPrefix(boolean z) {
        String prefix = this.scope.getPrefix(this.reference);
        return (z && prefix == null) ? this.parent.getPrefix() : prefix;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getReference() {
        return this.reference;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setReference(String str) {
        this.reference = str;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public NamespaceMap getNamespaces() {
        return this.scope;
    }

    @Override // org.simpleframework.xml.stream.OutputNode, org.simpleframework.xml.stream.Node
    public OutputNode getParent() {
        return this.parent;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getName() {
        return this.name;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getValue() {
        return this.value;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public String getComment() {
        return this.comment;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public boolean isRoot() {
        return this.writer.isRoot(this);
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
    public OutputNodeMap getAttributes() {
        return this.table;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setComment(String str) {
        this.comment = str;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setValue(String str) {
        this.value = str;
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void setName(String str) {
        this.name = str;
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
    public OutputNode setAttribute(String str, String str2) {
        return this.table.put(str, str2);
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public OutputNode getChild(String str) throws Exception {
        return this.writer.writeElement(this, str);
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void remove() throws Exception {
        this.writer.remove(this);
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public void commit() throws Exception {
        this.writer.commit(this);
    }

    @Override // org.simpleframework.xml.stream.OutputNode
    public boolean isCommitted() {
        return this.writer.isCommitted(this);
    }

    public String toString() {
        return String.format("element %s", this.name);
    }
}
