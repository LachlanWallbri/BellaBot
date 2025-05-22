package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class DocumentEndEvent extends Event {
    private final boolean explicit;

    public DocumentEndEvent(Mark mark, Mark mark2, boolean z) {
        super(mark, mark2);
        this.explicit = z;
    }

    public boolean getExplicit() {
        return this.explicit;
    }

    @Override // org.yaml.snakeyaml.events.Event
    /* renamed from: is */
    public boolean mo4255is(Event.EnumC8987ID enumC8987ID) {
        return Event.EnumC8987ID.DocumentEnd == enumC8987ID;
    }
}
