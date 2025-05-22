package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ScalarEvent extends NodeEvent {
    private final ImplicitTuple implicit;
    private final Character style;
    private final String tag;
    private final String value;

    public ScalarEvent(String str, String str2, ImplicitTuple implicitTuple, String str3, Mark mark, Mark mark2, Character ch) {
        super(str, mark, mark2);
        this.tag = str2;
        this.implicit = implicitTuple;
        this.value = str3;
        this.style = ch;
    }

    public String getTag() {
        return this.tag;
    }

    public Character getStyle() {
        return this.style;
    }

    public String getValue() {
        return this.value;
    }

    public ImplicitTuple getImplicit() {
        return this.implicit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.yaml.snakeyaml.events.NodeEvent, org.yaml.snakeyaml.events.Event
    public String getArguments() {
        return super.getArguments() + ", tag=" + this.tag + ", " + this.implicit + ", value=" + this.value;
    }

    @Override // org.yaml.snakeyaml.events.Event
    /* renamed from: is */
    public boolean mo4255is(Event.EnumC8987ID enumC8987ID) {
        return Event.EnumC8987ID.Scalar == enumC8987ID;
    }
}
