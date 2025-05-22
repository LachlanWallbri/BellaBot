package org.yaml.snakeyaml.parser;

import org.yaml.snakeyaml.events.Event;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface Parser {
    boolean checkEvent(Event.EnumC8987ID enumC8987ID);

    Event getEvent();

    Event peekEvent();
}
