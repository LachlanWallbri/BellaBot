package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.batching.BatchingSettings;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class BatchingCallSettings<RequestT, ResponseT> extends UnaryCallSettings<RequestT, ResponseT> {
    private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
    private final BatchingSettings batchingSettings;
    private final FlowController flowController;

    public BatchingDescriptor<RequestT, ResponseT> getBatchingDescriptor() {
        return this.batchingDescriptor;
    }

    public BatchingSettings getBatchingSettings() {
        return this.batchingSettings;
    }

    public FlowController getFlowController() {
        return this.flowController;
    }

    private BatchingCallSettings(Builder<RequestT, ResponseT> builder) {
        super(builder);
        this.batchingDescriptor = ((Builder) builder).batchingDescriptor;
        this.batchingSettings = (BatchingSettings) Preconditions.checkNotNull(((Builder) builder).batchingSettings);
        FlowController flowController = ((Builder) builder).flowController;
        this.flowController = flowController == null ? new FlowController(this.batchingSettings.getFlowControlSettings()) : flowController;
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor) {
        return new Builder<>(batchingDescriptor);
    }

    @Override // com.google.api.gax.rpc.UnaryCallSettings
    public final Builder<RequestT, ResponseT> toBuilder() {
        return new Builder<>(this);
    }

    @Override // com.google.api.gax.rpc.UnaryCallSettings
    public String toString() {
        return MoreObjects.toStringHelper(this).add("retryableCodes", getRetryableCodes()).add("retrySettings", getRetrySettings()).add("batchingSettings", this.batchingSettings).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<RequestT, ResponseT> extends UnaryCallSettings.Builder<RequestT, ResponseT> {
        private BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
        private BatchingSettings batchingSettings;
        private FlowController flowController;

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public /* bridge */ /* synthetic */ UnaryCallSettings.Builder setRetryableCodes(Set set) {
            return setRetryableCodes((Set<StatusCode.Code>) set);
        }

        public Builder(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor) {
            this.batchingDescriptor = batchingDescriptor;
        }

        public Builder(BatchingCallSettings<RequestT, ResponseT> batchingCallSettings) {
            super(batchingCallSettings);
            this.batchingDescriptor = ((BatchingCallSettings) batchingCallSettings).batchingDescriptor;
            this.batchingSettings = ((BatchingCallSettings) batchingCallSettings).batchingSettings;
            this.flowController = ((BatchingCallSettings) batchingCallSettings).flowController;
        }

        public BatchingDescriptor<RequestT, ResponseT> getBatchingDescriptor() {
            return this.batchingDescriptor;
        }

        public Builder<RequestT, ResponseT> setBatchingSettings(BatchingSettings batchingSettings) {
            this.batchingSettings = batchingSettings;
            return this;
        }

        public BatchingSettings getBatchingSettings() {
            return this.batchingSettings;
        }

        public Builder<RequestT, ResponseT> setFlowController(FlowController flowController) {
            this.flowController = flowController;
            return this;
        }

        public FlowController getFlowController() {
            return this.flowController;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<RequestT, ResponseT> setRetryableCodes(Set<StatusCode.Code> set) {
            super.setRetryableCodes(set);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<RequestT, ResponseT> setRetryableCodes(StatusCode.Code... codeArr) {
            super.setRetryableCodes(codeArr);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<RequestT, ResponseT> setRetrySettings(RetrySettings retrySettings) {
            super.setRetrySettings(retrySettings);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public BatchingCallSettings<RequestT, ResponseT> build() {
            return new BatchingCallSettings<>(this);
        }
    }
}
