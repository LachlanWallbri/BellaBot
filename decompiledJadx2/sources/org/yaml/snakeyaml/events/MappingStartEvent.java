package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class MappingStartEvent extends CollectionStartEvent {
    public MappingStartEvent(String str, String str2, boolean z, Mark mark, Mark mark2, Boolean bool) {
        super(str, str2, z, mark, mark2, bool);
    }

    @Override // org.yaml.snakeyaml.events.Event
    /* renamed from: is */
    public boolean mo4255is(Event.EnumC8987ID enumC8987ID) {
        return Event.EnumC8987ID.MappingStart == enumC8987ID;
    }
}
