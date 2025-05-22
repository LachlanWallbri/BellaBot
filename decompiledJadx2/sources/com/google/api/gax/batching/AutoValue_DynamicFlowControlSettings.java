package com.google.api.gax.batching;

import com.google.api.gax.batching.DynamicFlowControlSettings;
import com.google.api.gax.batching.FlowController;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_DynamicFlowControlSettings extends DynamicFlowControlSettings {
    private final Long initialOutstandingElementCount;
    private final Long initialOutstandingRequestBytes;
    private final FlowController.LimitExceededBehavior limitExceededBehavior;
    private final Long maxOutstandingElementCount;
    private final Long maxOutstandingRequestBytes;
    private final Long minOutstandingElementCount;
    private final Long minOutstandingRequestBytes;

    private AutoValue_DynamicFlowControlSettings(@Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @Nullable Long l5, @Nullable Long l6, FlowController.LimitExceededBehavior limitExceededBehavior) {
        this.initialOutstandingElementCount = l;
        this.initialOutstandingRequestBytes = l2;
        this.maxOutstandingElementCount = l3;
        this.maxOutstandingRequestBytes = l4;
        this.minOutstandingElementCount = l5;
        this.minOutstandingRequestBytes = l6;
        this.limitExceededBehavior = limitExceededBehavior;
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    @Nullable
    public Long getInitialOutstandingElementCount() {
        return this.initialOutstandingElementCount;
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    @Nullable
    public Long getInitialOutstandingRequestBytes() {
        return this.initialOutstandingRequestBytes;
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    @Nullable
    public Long getMaxOutstandingElementCount() {
        return this.maxOutstandingElementCount;
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    @Nullable
    public Long getMaxOutstandingRequestBytes() {
        return this.maxOutstandingRequestBytes;
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    @Nullable
    public Long getMinOutstandingElementCount() {
        return this.minOutstandingElementCount;
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    @Nullable
    public Long getMinOutstandingRequestBytes() {
        return this.minOutstandingRequestBytes;
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    public FlowController.LimitExceededBehavior getLimitExceededBehavior() {
        return this.limitExceededBehavior;
    }

    public String toString() {
        return "DynamicFlowControlSettings{initialOutstandingElementCount=" + this.initialOutstandingElementCount + ", initialOutstandingRequestBytes=" + this.initialOutstandingRequestBytes + ", maxOutstandingElementCount=" + this.maxOutstandingElementCount + ", maxOutstandingRequestBytes=" + this.maxOutstandingRequestBytes + ", minOutstandingElementCount=" + this.minOutstandingElementCount + ", minOutstandingRequestBytes=" + this.minOutstandingRequestBytes + ", limitExceededBehavior=" + this.limitExceededBehavior + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DynamicFlowControlSettings)) {
            return false;
        }
        DynamicFlowControlSettings dynamicFlowControlSettings = (DynamicFlowControlSettings) obj;
        Long l = this.initialOutstandingElementCount;
        if (l != null ? l.equals(dynamicFlowControlSettings.getInitialOutstandingElementCount()) : dynamicFlowControlSettings.getInitialOutstandingElementCount() == null) {
            Long l2 = this.initialOutstandingRequestBytes;
            if (l2 != null ? l2.equals(dynamicFlowControlSettings.getInitialOutstandingRequestBytes()) : dynamicFlowControlSettings.getInitialOutstandingRequestBytes() == null) {
                Long l3 = this.maxOutstandingElementCount;
                if (l3 != null ? l3.equals(dynamicFlowControlSettings.getMaxOutstandingElementCount()) : dynamicFlowControlSettings.getMaxOutstandingElementCount() == null) {
                    Long l4 = this.maxOutstandingRequestBytes;
                    if (l4 != null ? l4.equals(dynamicFlowControlSettings.getMaxOutstandingRequestBytes()) : dynamicFlowControlSettings.getMaxOutstandingRequestBytes() == null) {
                        Long l5 = this.minOutstandingElementCount;
                        if (l5 != null ? l5.equals(dynamicFlowControlSettings.getMinOutstandingElementCount()) : dynamicFlowControlSettings.getMinOutstandingElementCount() == null) {
                            Long l6 = this.minOutstandingRequestBytes;
                            if (l6 != null ? l6.equals(dynamicFlowControlSettings.getMinOutstandingRequestBytes()) : dynamicFlowControlSettings.getMinOutstandingRequestBytes() == null) {
                                if (this.limitExceededBehavior.equals(dynamicFlowControlSettings.getLimitExceededBehavior())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Long l = this.initialOutstandingElementCount;
        int hashCode = ((l == null ? 0 : l.hashCode()) ^ 1000003) * 1000003;
        Long l2 = this.initialOutstandingRequestBytes;
        int hashCode2 = (hashCode ^ (l2 == null ? 0 : l2.hashCode())) * 1000003;
        Long l3 = this.maxOutstandingElementCount;
        int hashCode3 = (hashCode2 ^ (l3 == null ? 0 : l3.hashCode())) * 1000003;
        Long l4 = this.maxOutstandingRequestBytes;
        int hashCode4 = (hashCode3 ^ (l4 == null ? 0 : l4.hashCode())) * 1000003;
        Long l5 = this.minOutstandingElementCount;
        int hashCode5 = (hashCode4 ^ (l5 == null ? 0 : l5.hashCode())) * 1000003;
        Long l6 = this.minOutstandingRequestBytes;
        return ((hashCode5 ^ (l6 != null ? l6.hashCode() : 0)) * 1000003) ^ this.limitExceededBehavior.hashCode();
    }

    @Override // com.google.api.gax.batching.DynamicFlowControlSettings
    public DynamicFlowControlSettings.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends DynamicFlowControlSettings.Builder {
        private Long initialOutstandingElementCount;
        private Long initialOutstandingRequestBytes;
        private FlowController.LimitExceededBehavior limitExceededBehavior;
        private Long maxOutstandingElementCount;
        private Long maxOutstandingRequestBytes;
        private Long minOutstandingElementCount;
        private Long minOutstandingRequestBytes;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(DynamicFlowControlSettings dynamicFlowControlSettings) {
            this.initialOutstandingElementCount = dynamicFlowControlSettings.getInitialOutstandingElementCount();
            this.initialOutstandingRequestBytes = dynamicFlowControlSettings.getInitialOutstandingRequestBytes();
            this.maxOutstandingElementCount = dynamicFlowControlSettings.getMaxOutstandingElementCount();
            this.maxOutstandingRequestBytes = dynamicFlowControlSettings.getMaxOutstandingRequestBytes();
            this.minOutstandingElementCount = dynamicFlowControlSettings.getMinOutstandingElementCount();
            this.minOutstandingRequestBytes = dynamicFlowControlSettings.getMinOutstandingRequestBytes();
            this.limitExceededBehavior = dynamicFlowControlSettings.getLimitExceededBehavior();
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        public DynamicFlowControlSettings.Builder setInitialOutstandingElementCount(@Nullable Long l) {
            this.initialOutstandingElementCount = l;
            return this;
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        public DynamicFlowControlSettings.Builder setInitialOutstandingRequestBytes(@Nullable Long l) {
            this.initialOutstandingRequestBytes = l;
            return this;
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        public DynamicFlowControlSettings.Builder setMaxOutstandingElementCount(@Nullable Long l) {
            this.maxOutstandingElementCount = l;
            return this;
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        public DynamicFlowControlSettings.Builder setMaxOutstandingRequestBytes(@Nullable Long l) {
            this.maxOutstandingRequestBytes = l;
            return this;
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        public DynamicFlowControlSettings.Builder setMinOutstandingElementCount(@Nullable Long l) {
            this.minOutstandingElementCount = l;
            return this;
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        public DynamicFlowControlSettings.Builder setMinOutstandingRequestBytes(@Nullable Long l) {
            this.minOutstandingRequestBytes = l;
            return this;
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        public DynamicFlowControlSettings.Builder setLimitExceededBehavior(FlowController.LimitExceededBehavior limitExceededBehavior) {
            if (limitExceededBehavior == null) {
                throw new NullPointerException("Null limitExceededBehavior");
            }
            this.limitExceededBehavior = limitExceededBehavior;
            return this;
        }

        @Override // com.google.api.gax.batching.DynamicFlowControlSettings.Builder
        DynamicFlowControlSettings autoBuild() {
            String str = "";
            if (this.limitExceededBehavior == null) {
                str = " limitExceededBehavior";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_DynamicFlowControlSettings(this.initialOutstandingElementCount, this.initialOutstandingRequestBytes, this.maxOutstandingElementCount, this.maxOutstandingRequestBytes, this.minOutstandingElementCount, this.minOutstandingRequestBytes, this.limitExceededBehavior);
        }
    }
}
