package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class CollectionStartEvent extends NodeEvent {
    private final Boolean flowStyle;
    private final boolean implicit;
    private final String tag;

    public CollectionStartEvent(String str, String str2, boolean z, Mark mark, Mark mark2, Boolean bool) {
        super(str, mark, mark2);
        this.tag = str2;
        this.implicit = z;
        this.flowStyle = bool;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean getImplicit() {
        return this.implicit;
    }

    public Boolean getFlowStyle() {
        return this.flowStyle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.yaml.snakeyaml.events.NodeEvent, org.yaml.snakeyaml.events.Event
    public String getArguments() {
        return super.getArguments() + ", tag=" + this.tag + ", implicit=" + this.implicit;
    }
}
