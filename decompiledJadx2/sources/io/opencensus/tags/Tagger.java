package io.opencensus.tags;

import io.opencensus.common.Scope;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Tagger {
    public abstract TagContextBuilder currentBuilder();

    public abstract TagContext empty();

    public abstract TagContextBuilder emptyBuilder();

    public abstract TagContext getCurrentTagContext();

    public abstract TagContextBuilder toBuilder(TagContext tagContext);

    public abstract Scope withTagContext(TagContext tagContext);
}
