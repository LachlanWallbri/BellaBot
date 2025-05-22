package io.opencensus.trace;

import io.opencensus.trace.EndSpanOptions;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_EndSpanOptions extends EndSpanOptions {
    private final boolean sampleToLocalSpanStore;
    private final Status status;

    private AutoValue_EndSpanOptions(boolean z, @Nullable Status status) {
        this.sampleToLocalSpanStore = z;
        this.status = status;
    }

    @Override // io.opencensus.trace.EndSpanOptions
    public boolean getSampleToLocalSpanStore() {
        return this.sampleToLocalSpanStore;
    }

    @Override // io.opencensus.trace.EndSpanOptions
    @Nullable
    public Status getStatus() {
        return this.status;
    }

    public String toString() {
        return "EndSpanOptions{sampleToLocalSpanStore=" + this.sampleToLocalSpanStore + ", status=" + this.status + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EndSpanOptions)) {
            return false;
        }
        EndSpanOptions endSpanOptions = (EndSpanOptions) obj;
        if (this.sampleToLocalSpanStore == endSpanOptions.getSampleToLocalSpanStore()) {
            Status status = this.status;
            if (status == null) {
                if (endSpanOptions.getStatus() == null) {
                    return true;
                }
            } else if (status.equals(endSpanOptions.getStatus())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = ((this.sampleToLocalSpanStore ? 1231 : 1237) ^ 1000003) * 1000003;
        Status status = this.status;
        return i ^ (status == null ? 0 : status.hashCode());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static final class Builder extends EndSpanOptions.Builder {
        private Boolean sampleToLocalSpanStore;
        private Status status;

        @Override // io.opencensus.trace.EndSpanOptions.Builder
        public EndSpanOptions.Builder setSampleToLocalSpanStore(boolean z) {
            this.sampleToLocalSpanStore = Boolean.valueOf(z);
            return this;
        }

        @Override // io.opencensus.trace.EndSpanOptions.Builder
        public EndSpanOptions.Builder setStatus(@Nullable Status status) {
            this.status = status;
            return this;
        }

        @Override // io.opencensus.trace.EndSpanOptions.Builder
        public EndSpanOptions build() {
            String str = "";
            if (this.sampleToLocalSpanStore == null) {
                str = " sampleToLocalSpanStore";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_EndSpanOptions(this.sampleToLocalSpanStore.booleanValue(), this.status);
        }
    }
}
