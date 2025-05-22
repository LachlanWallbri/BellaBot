package com.google.api.gax.batching;

import com.google.api.gax.batching.AutoValue_FlowControlSettings;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class FlowControlSettings {
    public abstract FlowController.LimitExceededBehavior getLimitExceededBehavior();

    @Nullable
    public abstract Long getMaxOutstandingElementCount();

    @Nullable
    public abstract Long getMaxOutstandingRequestBytes();

    public abstract Builder toBuilder();

    public static FlowControlSettings getDefaultInstance() {
        return newBuilder().build();
    }

    public static Builder newBuilder() {
        return new AutoValue_FlowControlSettings.Builder().setLimitExceededBehavior(FlowController.LimitExceededBehavior.Block);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        abstract FlowControlSettings autoBuild();

        public abstract Builder setLimitExceededBehavior(FlowController.LimitExceededBehavior limitExceededBehavior);

        public abstract Builder setMaxOutstandingElementCount(Long l);

        public abstract Builder setMaxOutstandingRequestBytes(Long l);

        public FlowControlSettings build() {
            FlowControlSettings autoBuild = autoBuild();
            Preconditions.checkArgument(autoBuild.getMaxOutstandingElementCount() == null || autoBuild.getMaxOutstandingElementCount().longValue() > 0, "maxOutstandingElementCount limit is disabled by default, but if set it must be set to a value greater than 0.");
            Preconditions.checkArgument(autoBuild.getMaxOutstandingRequestBytes() == null || autoBuild.getMaxOutstandingRequestBytes().longValue() > 0, "maxOutstandingRequestBytes limit is disabled by default, but if set it must be set to a value greater than 0.");
            return autoBuild;
        }
    }
}
