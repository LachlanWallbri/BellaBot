package com.google.api.gax.batching;

import com.google.api.gax.batching.BatchingSettings;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_BatchingSettings extends BatchingSettings {
    private final Duration delayThreshold;
    private final Long elementCountThreshold;
    private final FlowControlSettings flowControlSettings;
    private final Boolean isEnabled;
    private final Long requestByteThreshold;

    private AutoValue_BatchingSettings(@Nullable Long l, @Nullable Long l2, @Nullable Duration duration, Boolean bool, FlowControlSettings flowControlSettings) {
        this.elementCountThreshold = l;
        this.requestByteThreshold = l2;
        this.delayThreshold = duration;
        this.isEnabled = bool;
        this.flowControlSettings = flowControlSettings;
    }

    @Override // com.google.api.gax.batching.BatchingSettings
    @Nullable
    public Long getElementCountThreshold() {
        return this.elementCountThreshold;
    }

    @Override // com.google.api.gax.batching.BatchingSettings
    @Nullable
    public Long getRequestByteThreshold() {
        return this.requestByteThreshold;
    }

    @Override // com.google.api.gax.batching.BatchingSettings
    @Nullable
    public Duration getDelayThreshold() {
        return this.delayThreshold;
    }

    @Override // com.google.api.gax.batching.BatchingSettings
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    @Override // com.google.api.gax.batching.BatchingSettings
    public FlowControlSettings getFlowControlSettings() {
        return this.flowControlSettings;
    }

    public String toString() {
        return "BatchingSettings{elementCountThreshold=" + this.elementCountThreshold + ", requestByteThreshold=" + this.requestByteThreshold + ", delayThreshold=" + this.delayThreshold + ", isEnabled=" + this.isEnabled + ", flowControlSettings=" + this.flowControlSettings + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BatchingSettings)) {
            return false;
        }
        BatchingSettings batchingSettings = (BatchingSettings) obj;
        Long l = this.elementCountThreshold;
        if (l != null ? l.equals(batchingSettings.getElementCountThreshold()) : batchingSettings.getElementCountThreshold() == null) {
            Long l2 = this.requestByteThreshold;
            if (l2 != null ? l2.equals(batchingSettings.getRequestByteThreshold()) : batchingSettings.getRequestByteThreshold() == null) {
                Duration duration = this.delayThreshold;
                if (duration != null ? duration.equals(batchingSettings.getDelayThreshold()) : batchingSettings.getDelayThreshold() == null) {
                    if (this.isEnabled.equals(batchingSettings.getIsEnabled()) && this.flowControlSettings.equals(batchingSettings.getFlowControlSettings())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Long l = this.elementCountThreshold;
        int hashCode = ((l == null ? 0 : l.hashCode()) ^ 1000003) * 1000003;
        Long l2 = this.requestByteThreshold;
        int hashCode2 = (hashCode ^ (l2 == null ? 0 : l2.hashCode())) * 1000003;
        Duration duration = this.delayThreshold;
        return ((((hashCode2 ^ (duration != null ? duration.hashCode() : 0)) * 1000003) ^ this.isEnabled.hashCode()) * 1000003) ^ this.flowControlSettings.hashCode();
    }

    @Override // com.google.api.gax.batching.BatchingSettings
    public BatchingSettings.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static final class Builder extends BatchingSettings.Builder {
        private Duration delayThreshold;
        private Long elementCountThreshold;
        private FlowControlSettings flowControlSettings;
        private Boolean isEnabled;
        private Long requestByteThreshold;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(BatchingSettings batchingSettings) {
            this.elementCountThreshold = batchingSettings.getElementCountThreshold();
            this.requestByteThreshold = batchingSettings.getRequestByteThreshold();
            this.delayThreshold = batchingSettings.getDelayThreshold();
            this.isEnabled = batchingSettings.getIsEnabled();
            this.flowControlSettings = batchingSettings.getFlowControlSettings();
        }

        @Override // com.google.api.gax.batching.BatchingSettings.Builder
        public BatchingSettings.Builder setElementCountThreshold(@Nullable Long l) {
            this.elementCountThreshold = l;
            return this;
        }

        @Override // com.google.api.gax.batching.BatchingSettings.Builder
        public BatchingSettings.Builder setRequestByteThreshold(@Nullable Long l) {
            this.requestByteThreshold = l;
            return this;
        }

        @Override // com.google.api.gax.batching.BatchingSettings.Builder
        public BatchingSettings.Builder setDelayThreshold(@Nullable Duration duration) {
            this.delayThreshold = duration;
            return this;
        }

        @Override // com.google.api.gax.batching.BatchingSettings.Builder
        public BatchingSettings.Builder setIsEnabled(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("Null isEnabled");
            }
            this.isEnabled = bool;
            return this;
        }

        @Override // com.google.api.gax.batching.BatchingSettings.Builder
        public BatchingSettings.Builder setFlowControlSettings(FlowControlSettings flowControlSettings) {
            if (flowControlSettings == null) {
                throw new NullPointerException("Null flowControlSettings");
            }
            this.flowControlSettings = flowControlSettings;
            return this;
        }

        @Override // com.google.api.gax.batching.BatchingSettings.Builder
        BatchingSettings autoBuild() {
            String str = "";
            if (this.isEnabled == null) {
                str = " isEnabled";
            }
            if (this.flowControlSettings == null) {
                str = str + " flowControlSettings";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_BatchingSettings(this.elementCountThreshold, this.requestByteThreshold, this.delayThreshold, this.isEnabled, this.flowControlSettings);
        }
    }
}
