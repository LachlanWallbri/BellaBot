package io.opencensus.trace;

import io.opencensus.trace.AutoValue_EndSpanOptions;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class EndSpanOptions {
    public static final EndSpanOptions DEFAULT = builder().build();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract EndSpanOptions build();

        public abstract Builder setSampleToLocalSpanStore(boolean z);

        public abstract Builder setStatus(Status status);
    }

    public abstract boolean getSampleToLocalSpanStore();

    @Nullable
    public abstract Status getStatus();

    public static Builder builder() {
        return new AutoValue_EndSpanOptions.Builder().setSampleToLocalSpanStore(false);
    }
}
