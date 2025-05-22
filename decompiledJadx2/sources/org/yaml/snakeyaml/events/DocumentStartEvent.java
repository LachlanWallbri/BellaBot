package org.yaml.snakeyaml.events;

import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class DocumentStartEvent extends Event {
    private final boolean explicit;
    private final Map<String, String> tags;
    private final DumperOptions.Version version;

    public DocumentStartEvent(Mark mark, Mark mark2, boolean z, DumperOptions.Version version, Map<String, String> map) {
        super(mark, mark2);
        this.explicit = z;
        this.version = version;
        this.tags = map;
    }

    public boolean getExplicit() {
        return this.explicit;
    }

    public DumperOptions.Version getVersion() {
        return this.version;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    @Override // org.yaml.snakeyaml.events.Event
    /* renamed from: is */
    public boolean mo4255is(Event.EnumC8987ID enumC8987ID) {
        return Event.EnumC8987ID.DocumentStart == enumC8987ID;
    }
}
