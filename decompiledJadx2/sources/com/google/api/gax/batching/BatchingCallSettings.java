package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class BatchingCallSettings<ElementT, ElementResultT, RequestT, ResponseT> extends UnaryCallSettings<RequestT, ResponseT> {
    private final BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor;
    private final BatchingSettings batchingSettings;

    @InternalApi("For google-cloud-java client use only")
    public BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> getBatchingDescriptor() {
        return this.batchingDescriptor;
    }

    public BatchingSettings getBatchingSettings() {
        return this.batchingSettings;
    }

    private BatchingCallSettings(Builder<ElementT, ElementResultT, RequestT, ResponseT> builder) {
        super(builder);
        Preconditions.checkState(((Builder) builder).batchingSettings != null, "batching settings cannot be null");
        this.batchingDescriptor = ((Builder) builder).batchingDescriptor;
        this.batchingSettings = ((Builder) builder).batchingSettings;
    }

    @InternalApi("For google-cloud-java client use only")
    public static <ElementT, ElementResultT, RequestT, ResponseT> Builder<ElementT, ElementResultT, RequestT, ResponseT> newBuilder(BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor) {
        return new Builder<>(batchingDescriptor);
    }

    @Override // com.google.api.gax.rpc.UnaryCallSettings
    public final Builder<ElementT, ElementResultT, RequestT, ResponseT> toBuilder() {
        return new Builder<>();
    }

    @Override // com.google.api.gax.rpc.UnaryCallSettings
    public String toString() {
        return MoreObjects.toStringHelper(this).add("retryableCodes", getRetryableCodes()).add("retrySettings", getRetrySettings()).add("batchingSettings", this.batchingSettings).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<ElementT, ElementResultT, RequestT, ResponseT> extends UnaryCallSettings.Builder<RequestT, ResponseT> {
        private BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor;
        private BatchingSettings batchingSettings;

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public /* bridge */ /* synthetic */ UnaryCallSettings.Builder setRetryableCodes(Set set) {
            return setRetryableCodes((Set<StatusCode.Code>) set);
        }

        private Builder(BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor) {
            this.batchingDescriptor = (BatchingDescriptor) Preconditions.checkNotNull(batchingDescriptor, "batching descriptor cannot be null");
        }

        private Builder(BatchingCallSettings<ElementT, ElementResultT, RequestT, ResponseT> batchingCallSettings) {
            super(batchingCallSettings);
            this.batchingDescriptor = ((BatchingCallSettings) batchingCallSettings).batchingDescriptor;
            this.batchingSettings = ((BatchingCallSettings) batchingCallSettings).batchingSettings;
        }

        public Builder<ElementT, ElementResultT, RequestT, ResponseT> setBatchingSettings(BatchingSettings batchingSettings) {
            this.batchingSettings = batchingSettings;
            return this;
        }

        public BatchingSettings getBatchingSettings() {
            return this.batchingSettings;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<ElementT, ElementResultT, RequestT, ResponseT> setRetryableCodes(StatusCode.Code... codeArr) {
            super.setRetryableCodes(codeArr);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<ElementT, ElementResultT, RequestT, ResponseT> setRetryableCodes(Set<StatusCode.Code> set) {
            super.setRetryableCodes(set);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<ElementT, ElementResultT, RequestT, ResponseT> setRetrySettings(RetrySettings retrySettings) {
            super.setRetrySettings(retrySettings);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public BatchingCallSettings<ElementT, ElementResultT, RequestT, ResponseT> build() {
            return new BatchingCallSettings<>(this);
        }
    }
}
