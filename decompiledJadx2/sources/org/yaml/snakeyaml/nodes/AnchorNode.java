package org.yaml.snakeyaml.nodes;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class AnchorNode extends Node {
    private Node realNode;

    public AnchorNode(Node node) {
        super(node.getTag(), node.getStartMark(), node.getEndMark());
        this.realNode = node;
    }

    @Override // org.yaml.snakeyaml.nodes.Node
    public NodeId getNodeId() {
        return NodeId.anchor;
    }

    public Node getRealNode() {
        return this.realNode;
    }
}
