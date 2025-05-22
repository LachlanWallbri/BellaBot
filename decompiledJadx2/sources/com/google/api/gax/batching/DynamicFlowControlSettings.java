package com.google.api.gax.batching;

import com.google.api.core.InternalApi;
import com.google.api.gax.batching.AutoValue_DynamicFlowControlSettings;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For google-cloud-java client use only")
/* loaded from: classes2.dex */
public abstract class DynamicFlowControlSettings {
    @Nullable
    public abstract Long getInitialOutstandingElementCount();

    @Nullable
    public abstract Long getInitialOutstandingRequestBytes();

    public abstract FlowController.LimitExceededBehavior getLimitExceededBehavior();

    @Nullable
    public abstract Long getMaxOutstandingElementCount();

    @Nullable
    public abstract Long getMaxOutstandingRequestBytes();

    @Nullable
    public abstract Long getMinOutstandingElementCount();

    @Nullable
    public abstract Long getMinOutstandingRequestBytes();

    public abstract Builder toBuilder();

    public static Builder newBuilder() {
        return new AutoValue_DynamicFlowControlSettings.Builder().setLimitExceededBehavior(FlowController.LimitExceededBehavior.Block);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        abstract DynamicFlowControlSettings autoBuild();

        public abstract Builder setInitialOutstandingElementCount(Long l);

        public abstract Builder setInitialOutstandingRequestBytes(Long l);

        public abstract Builder setLimitExceededBehavior(FlowController.LimitExceededBehavior limitExceededBehavior);

        public abstract Builder setMaxOutstandingElementCount(Long l);

        public abstract Builder setMaxOutstandingRequestBytes(Long l);

        public abstract Builder setMinOutstandingElementCount(Long l);

        public abstract Builder setMinOutstandingRequestBytes(Long l);

        public DynamicFlowControlSettings build() {
            DynamicFlowControlSettings autoBuild = autoBuild();
            verifyElementCountSettings(autoBuild);
            verifyRequestBytesSettings(autoBuild);
            return autoBuild;
        }

        private void verifyElementCountSettings(DynamicFlowControlSettings dynamicFlowControlSettings) {
            if (dynamicFlowControlSettings.getLimitExceededBehavior() == FlowController.LimitExceededBehavior.Ignore) {
                return;
            }
            if (dynamicFlowControlSettings.getInitialOutstandingElementCount() == null && dynamicFlowControlSettings.getMinOutstandingElementCount() == null && dynamicFlowControlSettings.getMaxOutstandingElementCount() == null) {
                return;
            }
            Preconditions.checkState((dynamicFlowControlSettings.getInitialOutstandingElementCount() == null || dynamicFlowControlSettings.getMinOutstandingElementCount() == null || dynamicFlowControlSettings.getMaxOutstandingElementCount() == null) ? false : true, "Throttling on element count is disabled by default. To enable this setting, minOutstandingElementCount, initialOutstandingElementCount, and maxOutstandingElementCount must all be set.");
            Preconditions.checkState(dynamicFlowControlSettings.getMinOutstandingElementCount().longValue() > 0 && dynamicFlowControlSettings.getInitialOutstandingElementCount().longValue() <= dynamicFlowControlSettings.getMaxOutstandingElementCount().longValue() && dynamicFlowControlSettings.getInitialOutstandingElementCount().longValue() >= dynamicFlowControlSettings.getMinOutstandingElementCount().longValue(), "If throttling on element count is set, minOutstandingElementCount must be greater than 0, and minOutstandingElementCount <= initialOutstandingElementCount <= maxOutstandingElementCount");
        }

        private void verifyRequestBytesSettings(DynamicFlowControlSettings dynamicFlowControlSettings) {
            if (dynamicFlowControlSettings.getLimitExceededBehavior() == FlowController.LimitExceededBehavior.Ignore) {
                return;
            }
            if (dynamicFlowControlSettings.getInitialOutstandingRequestBytes() == null && dynamicFlowControlSettings.getMinOutstandingRequestBytes() == null && dynamicFlowControlSettings.getMaxOutstandingRequestBytes() == null) {
                return;
            }
            Preconditions.checkState((dynamicFlowControlSettings.getInitialOutstandingRequestBytes() == null || dynamicFlowControlSettings.getMinOutstandingRequestBytes() == null || dynamicFlowControlSettings.getMaxOutstandingRequestBytes() == null) ? false : true, "Throttling on number of bytes is disabled by default. To enable this setting, minOutstandingRequestBytes, initialOutstandingRequestBytes, and maxOutstandingRequestBytes must all be set");
            Preconditions.checkState(dynamicFlowControlSettings.getMinOutstandingRequestBytes().longValue() > 0 && dynamicFlowControlSettings.getInitialOutstandingRequestBytes().longValue() <= dynamicFlowControlSettings.getMaxOutstandingRequestBytes().longValue() && dynamicFlowControlSettings.getInitialOutstandingRequestBytes().longValue() >= dynamicFlowControlSettings.getMinOutstandingRequestBytes().longValue(), "If throttling on number of bytes is set, minOutstandingRequestBytes must be greater than 0, and minOutstandingRequestBytes <= initialOutstandingRequestBytes <= maxOutstandingRequestBytes");
        }
    }
}
