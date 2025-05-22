package org.yaml.snakeyaml.nodes;

import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ScalarNode extends Node {
    private Character style;
    private String value;

    public ScalarNode(Tag tag, String str, Mark mark, Mark mark2, Character ch) {
        this(tag, true, str, mark, mark2, ch);
    }

    public ScalarNode(Tag tag, boolean z, String str, Mark mark, Mark mark2, Character ch) {
        super(tag, mark, mark2);
        if (str == null) {
            throw new NullPointerException("value in a Node is required.");
        }
        this.value = str;
        this.style = ch;
        this.resolved = z;
    }

    public Character getStyle() {
        return this.style;
    }

    @Override // org.yaml.snakeyaml.nodes.Node
    public NodeId getNodeId() {
        return NodeId.scalar;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "<" + getClass().getName() + " (tag=" + getTag() + ", value=" + getValue() + ")>";
    }
}
