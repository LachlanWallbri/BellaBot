package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class NodeEvent extends Event {
    private final String anchor;

    public NodeEvent(String str, Mark mark, Mark mark2) {
        super(mark, mark2);
        this.anchor = str;
    }

    public String getAnchor() {
        return this.anchor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.yaml.snakeyaml.events.Event
    public String getArguments() {
        return "anchor=" + this.anchor;
    }
}
