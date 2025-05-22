package com.google.api.gax.batching;

import com.google.api.gax.batching.AutoValue_BatchingSettings;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class BatchingSettings {
    @Nullable
    public abstract Duration getDelayThreshold();

    @Nullable
    public abstract Long getElementCountThreshold();

    public abstract FlowControlSettings getFlowControlSettings();

    public abstract Boolean getIsEnabled();

    @Nullable
    public abstract Long getRequestByteThreshold();

    public abstract Builder toBuilder();

    public static Builder newBuilder() {
        return new AutoValue_BatchingSettings.Builder().setIsEnabled(true).setElementCountThreshold(1L).setRequestByteThreshold(1L).setDelayThreshold(Duration.ofMillis(1L)).setFlowControlSettings(FlowControlSettings.newBuilder().setLimitExceededBehavior(FlowController.LimitExceededBehavior.Ignore).build());
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        abstract BatchingSettings autoBuild();

        public abstract Builder setDelayThreshold(Duration duration);

        public abstract Builder setElementCountThreshold(Long l);

        public abstract Builder setFlowControlSettings(FlowControlSettings flowControlSettings);

        public abstract Builder setIsEnabled(Boolean bool);

        public abstract Builder setRequestByteThreshold(Long l);

        public BatchingSettings build() {
            BatchingSettings autoBuild = autoBuild();
            Preconditions.checkArgument(autoBuild.getElementCountThreshold() == null || autoBuild.getElementCountThreshold().longValue() > 0, "elementCountThreshold must be either unset or positive");
            Preconditions.checkArgument(autoBuild.getRequestByteThreshold() == null || autoBuild.getRequestByteThreshold().longValue() > 0, "requestByteThreshold must be either unset or positive");
            Preconditions.checkArgument(autoBuild.getDelayThreshold() == null || autoBuild.getDelayThreshold().compareTo(Duration.ZERO) > 0, "delayThreshold must be either unset or positive");
            return autoBuild;
        }
    }
}
