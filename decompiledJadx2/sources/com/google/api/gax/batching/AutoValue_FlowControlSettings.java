package com.google.api.gax.batching;

import com.google.api.gax.batching.FlowControlSettings;
import com.google.api.gax.batching.FlowController;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_FlowControlSettings extends FlowControlSettings {
    private final FlowController.LimitExceededBehavior limitExceededBehavior;
    private final Long maxOutstandingElementCount;
    private final Long maxOutstandingRequestBytes;

    private AutoValue_FlowControlSettings(@Nullable Long l, @Nullable Long l2, FlowController.LimitExceededBehavior limitExceededBehavior) {
        this.maxOutstandingElementCount = l;
        this.maxOutstandingRequestBytes = l2;
        this.limitExceededBehavior = limitExceededBehavior;
    }

    @Override // com.google.api.gax.batching.FlowControlSettings
    @Nullable
    public Long getMaxOutstandingElementCount() {
        return this.maxOutstandingElementCount;
    }

    @Override // com.google.api.gax.batching.FlowControlSettings
    @Nullable
    public Long getMaxOutstandingRequestBytes() {
        return this.maxOutstandingRequestBytes;
    }

    @Override // com.google.api.gax.batching.FlowControlSettings
    public FlowController.LimitExceededBehavior getLimitExceededBehavior() {
        return this.limitExceededBehavior;
    }

    public String toString() {
        return "FlowControlSettings{maxOutstandingElementCount=" + this.maxOutstandingElementCount + ", maxOutstandingRequestBytes=" + this.maxOutstandingRequestBytes + ", limitExceededBehavior=" + this.limitExceededBehavior + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FlowControlSettings)) {
            return false;
        }
        FlowControlSettings flowControlSettings = (FlowControlSettings) obj;
        Long l = this.maxOutstandingElementCount;
        if (l != null ? l.equals(flowControlSettings.getMaxOutstandingElementCount()) : flowControlSettings.getMaxOutstandingElementCount() == null) {
            Long l2 = this.maxOutstandingRequestBytes;
            if (l2 != null ? l2.equals(flowControlSettings.getMaxOutstandingRequestBytes()) : flowControlSettings.getMaxOutstandingRequestBytes() == null) {
                if (this.limitExceededBehavior.equals(flowControlSettings.getLimitExceededBehavior())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Long l = this.maxOutstandingElementCount;
        int hashCode = ((l == null ? 0 : l.hashCode()) ^ 1000003) * 1000003;
        Long l2 = this.maxOutstandingRequestBytes;
        return ((hashCode ^ (l2 != null ? l2.hashCode() : 0)) * 1000003) ^ this.limitExceededBehavior.hashCode();
    }

    @Override // com.google.api.gax.batching.FlowControlSettings
    public FlowControlSettings.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends FlowControlSettings.Builder {
        private FlowController.LimitExceededBehavior limitExceededBehavior;
        private Long maxOutstandingElementCount;
        private Long maxOutstandingRequestBytes;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(FlowControlSettings flowControlSettings) {
            this.maxOutstandingElementCount = flowControlSettings.getMaxOutstandingElementCount();
            this.maxOutstandingRequestBytes = flowControlSettings.getMaxOutstandingRequestBytes();
            this.limitExceededBehavior = flowControlSettings.getLimitExceededBehavior();
        }

        @Override // com.google.api.gax.batching.FlowControlSettings.Builder
        public FlowControlSettings.Builder setMaxOutstandingElementCount(@Nullable Long l) {
            this.maxOutstandingElementCount = l;
            return this;
        }

        @Override // com.google.api.gax.batching.FlowControlSettings.Builder
        public FlowControlSettings.Builder setMaxOutstandingRequestBytes(@Nullable Long l) {
            this.maxOutstandingRequestBytes = l;
            return this;
        }

        @Override // com.google.api.gax.batching.FlowControlSettings.Builder
        public FlowControlSettings.Builder setLimitExceededBehavior(FlowController.LimitExceededBehavior limitExceededBehavior) {
            if (limitExceededBehavior == null) {
                throw new NullPointerException("Null limitExceededBehavior");
            }
            this.limitExceededBehavior = limitExceededBehavior;
            return this;
        }

        @Override // com.google.api.gax.batching.FlowControlSettings.Builder
        FlowControlSettings autoBuild() {
            String str = "";
            if (this.limitExceededBehavior == null) {
                str = " limitExceededBehavior";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_FlowControlSettings(this.maxOutstandingElementCount, this.maxOutstandingRequestBytes, this.limitExceededBehavior);
        }
    }
}
