package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class Event {
    private final Mark endMark;
    private final Mark startMark;

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.yaml.snakeyaml.events.Event$ID */
    /* loaded from: classes9.dex */
    public enum EnumC8987ID {
        Alias,
        DocumentEnd,
        DocumentStart,
        MappingEnd,
        MappingStart,
        Scalar,
        SequenceEnd,
        SequenceStart,
        StreamEnd,
        StreamStart
    }

    protected String getArguments() {
        return "";
    }

    /* renamed from: is */
    public abstract boolean mo4255is(EnumC8987ID enumC8987ID);

    public Event(Mark mark, Mark mark2) {
        this.startMark = mark;
        this.endMark = mark2;
    }

    public String toString() {
        return "<" + getClass().getName() + "(" + getArguments() + ")>";
    }

    public Mark getStartMark() {
        return this.startMark;
    }

    public Mark getEndMark() {
        return this.endMark;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            return toString().equals(obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
