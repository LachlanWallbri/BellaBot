package org.yaml.snakeyaml.nodes;

import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class CollectionNode extends Node {
    private Boolean flowStyle;

    public CollectionNode(Tag tag, Mark mark, Mark mark2, Boolean bool) {
        super(tag, mark, mark2);
        this.flowStyle = bool;
    }

    public Boolean getFlowStyle() {
        return this.flowStyle;
    }

    public void setFlowStyle(Boolean bool) {
        this.flowStyle = bool;
    }

    public void setEndMark(Mark mark) {
        this.endMark = mark;
    }
}
