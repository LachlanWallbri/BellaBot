package io.opencensus.trace.config;

import io.opencensus.trace.Sampler;
import io.opencensus.trace.config.TraceParams;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_TraceParams extends TraceParams {
    private final int maxNumberOfAnnotations;
    private final int maxNumberOfAttributes;
    private final int maxNumberOfLinks;
    private final int maxNumberOfMessageEvents;
    private final Sampler sampler;

    private AutoValue_TraceParams(Sampler sampler, int i, int i2, int i3, int i4) {
        this.sampler = sampler;
        this.maxNumberOfAttributes = i;
        this.maxNumberOfAnnotations = i2;
        this.maxNumberOfMessageEvents = i3;
        this.maxNumberOfLinks = i4;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public Sampler getSampler() {
        return this.sampler;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfAttributes() {
        return this.maxNumberOfAttributes;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfAnnotations() {
        return this.maxNumberOfAnnotations;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfMessageEvents() {
        return this.maxNumberOfMessageEvents;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfLinks() {
        return this.maxNumberOfLinks;
    }

    public String toString() {
        return "TraceParams{sampler=" + this.sampler + ", maxNumberOfAttributes=" + this.maxNumberOfAttributes + ", maxNumberOfAnnotations=" + this.maxNumberOfAnnotations + ", maxNumberOfMessageEvents=" + this.maxNumberOfMessageEvents + ", maxNumberOfLinks=" + this.maxNumberOfLinks + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceParams)) {
            return false;
        }
        TraceParams traceParams = (TraceParams) obj;
        return this.sampler.equals(traceParams.getSampler()) && this.maxNumberOfAttributes == traceParams.getMaxNumberOfAttributes() && this.maxNumberOfAnnotations == traceParams.getMaxNumberOfAnnotations() && this.maxNumberOfMessageEvents == traceParams.getMaxNumberOfMessageEvents() && this.maxNumberOfLinks == traceParams.getMaxNumberOfLinks();
    }

    public int hashCode() {
        return ((((((((this.sampler.hashCode() ^ 1000003) * 1000003) ^ this.maxNumberOfAttributes) * 1000003) ^ this.maxNumberOfAnnotations) * 1000003) ^ this.maxNumberOfMessageEvents) * 1000003) ^ this.maxNumberOfLinks;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public TraceParams.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static final class Builder extends TraceParams.Builder {
        private Integer maxNumberOfAnnotations;
        private Integer maxNumberOfAttributes;
        private Integer maxNumberOfLinks;
        private Integer maxNumberOfMessageEvents;
        private Sampler sampler;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(TraceParams traceParams) {
            this.sampler = traceParams.getSampler();
            this.maxNumberOfAttributes = Integer.valueOf(traceParams.getMaxNumberOfAttributes());
            this.maxNumberOfAnnotations = Integer.valueOf(traceParams.getMaxNumberOfAnnotations());
            this.maxNumberOfMessageEvents = Integer.valueOf(traceParams.getMaxNumberOfMessageEvents());
            this.maxNumberOfLinks = Integer.valueOf(traceParams.getMaxNumberOfLinks());
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setSampler(Sampler sampler) {
            if (sampler == null) {
                throw new NullPointerException("Null sampler");
            }
            this.sampler = sampler;
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfAttributes(int i) {
            this.maxNumberOfAttributes = Integer.valueOf(i);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfAnnotations(int i) {
            this.maxNumberOfAnnotations = Integer.valueOf(i);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfMessageEvents(int i) {
            this.maxNumberOfMessageEvents = Integer.valueOf(i);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfLinks(int i) {
            this.maxNumberOfLinks = Integer.valueOf(i);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        TraceParams autoBuild() {
            String str = "";
            if (this.sampler == null) {
                str = " sampler";
            }
            if (this.maxNumberOfAttributes == null) {
                str = str + " maxNumberOfAttributes";
            }
            if (this.maxNumberOfAnnotations == null) {
                str = str + " maxNumberOfAnnotations";
            }
            if (this.maxNumberOfMessageEvents == null) {
                str = str + " maxNumberOfMessageEvents";
            }
            if (this.maxNumberOfLinks == null) {
                str = str + " maxNumberOfLinks";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_TraceParams(this.sampler, this.maxNumberOfAttributes.intValue(), this.maxNumberOfAnnotations.intValue(), this.maxNumberOfMessageEvents.intValue(), this.maxNumberOfLinks.intValue());
        }
    }
}
