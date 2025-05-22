package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class SequenceEndEvent extends CollectionEndEvent {
    public SequenceEndEvent(Mark mark, Mark mark2) {
        super(mark, mark2);
    }

    @Override // org.yaml.snakeyaml.events.Event
    /* renamed from: is */
    public boolean mo4255is(Event.EnumC8987ID enumC8987ID) {
        return Event.EnumC8987ID.SequenceEnd == enumC8987ID;
    }
}
