package org.yaml.snakeyaml.nodes;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class NodeTuple {
    private Node keyNode;
    private Node valueNode;

    public NodeTuple(Node node, Node node2) {
        if (node == null || node2 == null) {
            throw new NullPointerException("Nodes must be provided.");
        }
        this.keyNode = node;
        this.valueNode = node2;
    }

    public Node getKeyNode() {
        return this.keyNode;
    }

    public Node getValueNode() {
        return this.valueNode;
    }

    public String toString() {
        return "<NodeTuple keyNode=" + this.keyNode.toString() + "; valueNode=" + this.valueNode.toString() + ">";
    }
}
