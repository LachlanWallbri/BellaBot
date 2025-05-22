package org.yaml.snakeyaml.nodes;

import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class Node {
    protected Mark endMark;
    protected boolean resolved;
    private Mark startMark;
    private Tag tag;
    private boolean twoStepsConstruction;
    private Class<? extends Object> type;
    protected Boolean useClassConstructor;

    public abstract NodeId getNodeId();

    public Node(Tag tag, Mark mark, Mark mark2) {
        setTag(tag);
        this.startMark = mark;
        this.endMark = mark2;
        this.type = Object.class;
        this.twoStepsConstruction = false;
        this.resolved = true;
        this.useClassConstructor = null;
    }

    public Tag getTag() {
        return this.tag;
    }

    public Mark getEndMark() {
        return this.endMark;
    }

    public Mark getStartMark() {
        return this.startMark;
    }

    public void setTag(Tag tag) {
        if (tag == null) {
            throw new NullPointerException("tag in a Node is required.");
        }
        this.tag = tag;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Class<? extends Object> getType() {
        return this.type;
    }

    public void setType(Class<? extends Object> cls) {
        if (cls.isAssignableFrom(this.type)) {
            return;
        }
        this.type = cls;
    }

    public void setTwoStepsConstruction(boolean z) {
        this.twoStepsConstruction = z;
    }

    public boolean isTwoStepsConstruction() {
        return this.twoStepsConstruction;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public boolean useClassConstructor() {
        Boolean bool = this.useClassConstructor;
        if (bool == null) {
            return !(this.tag.isSecondary() || !isResolved() || Object.class.equals(this.type) || this.tag.equals(Tag.NULL)) || this.tag.isCompatible(getType());
        }
        return bool.booleanValue();
    }

    public void setUseClassConstructor(Boolean bool) {
        this.useClassConstructor = bool;
    }

    public boolean isResolved() {
        return this.resolved;
    }
}
