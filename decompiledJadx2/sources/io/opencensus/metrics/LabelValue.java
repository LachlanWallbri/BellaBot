package io.opencensus.metrics;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class LabelValue {
    @Nullable
    public abstract String getValue();

    public static LabelValue create(@Nullable String str) {
        return new AutoValue_LabelValue(str);
    }
}
