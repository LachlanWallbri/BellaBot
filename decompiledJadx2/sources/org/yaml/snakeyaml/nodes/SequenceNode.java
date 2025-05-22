package org.yaml.snakeyaml.nodes;

import java.util.Iterator;
import java.util.List;
import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class SequenceNode extends CollectionNode {
    private final List<Node> value;

    public SequenceNode(Tag tag, boolean z, List<Node> list, Mark mark, Mark mark2, Boolean bool) {
        super(tag, mark, mark2, bool);
        if (list == null) {
            throw new NullPointerException("value in a Node is required.");
        }
        this.value = list;
        this.resolved = z;
    }

    public SequenceNode(Tag tag, List<Node> list, Boolean bool) {
        this(tag, true, list, null, null, bool);
    }

    @Override // org.yaml.snakeyaml.nodes.Node
    public NodeId getNodeId() {
        return NodeId.sequence;
    }

    public List<Node> getValue() {
        return this.value;
    }

    public void setListType(Class<? extends Object> cls) {
        Iterator<Node> it = this.value.iterator();
        while (it.hasNext()) {
            it.next().setType(cls);
        }
    }

    public String toString() {
        return "<" + getClass().getName() + " (tag=" + getTag() + ", value=" + getValue() + ")>";
    }
}
