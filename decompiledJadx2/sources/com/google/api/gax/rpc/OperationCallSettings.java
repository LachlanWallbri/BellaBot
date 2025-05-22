package com.google.api.gax.rpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class OperationCallSettings<RequestT, ResponseT, MetadataT> {
    private final UnaryCallSettings<RequestT, OperationSnapshot> initialCallSettings;
    private final ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;
    private final TimedRetryAlgorithm pollingAlgorithm;
    private final ApiFunction<OperationSnapshot, ResponseT> responseTransformer;

    public final UnaryCallSettings<RequestT, OperationSnapshot> getInitialCallSettings() {
        return this.initialCallSettings;
    }

    public final TimedRetryAlgorithm getPollingAlgorithm() {
        return this.pollingAlgorithm;
    }

    public final ApiFunction<OperationSnapshot, ResponseT> getResponseTransformer() {
        return this.responseTransformer;
    }

    public final ApiFunction<OperationSnapshot, MetadataT> getMetadataTransformer() {
        return this.metadataTransformer;
    }

    private OperationCallSettings(UnaryCallSettings<RequestT, OperationSnapshot> unaryCallSettings, TimedRetryAlgorithm timedRetryAlgorithm, ApiFunction<OperationSnapshot, ResponseT> apiFunction, ApiFunction<OperationSnapshot, MetadataT> apiFunction2) {
        this.initialCallSettings = (UnaryCallSettings) Preconditions.checkNotNull(unaryCallSettings);
        this.pollingAlgorithm = (TimedRetryAlgorithm) Preconditions.checkNotNull(timedRetryAlgorithm);
        this.responseTransformer = (ApiFunction) Preconditions.checkNotNull(apiFunction);
        this.metadataTransformer = apiFunction2;
    }

    public static <RequestT, ResponseT, MetadataT> Builder<RequestT, ResponseT, MetadataT> newBuilder() {
        return new Builder<>();
    }

    public final Builder<RequestT, ResponseT, MetadataT> toBuilder() {
        return new Builder<>(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<RequestT, ResponseT, MetadataT> {
        private UnaryCallSettings<RequestT, OperationSnapshot> initialCallSettings;
        private ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;
        private TimedRetryAlgorithm pollingAlgorithm;
        private ApiFunction<OperationSnapshot, ResponseT> responseTransformer;

        public Builder() {
        }

        public Builder(OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings) {
            this.initialCallSettings = ((OperationCallSettings) operationCallSettings).initialCallSettings.toBuilder().build();
            this.pollingAlgorithm = ((OperationCallSettings) operationCallSettings).pollingAlgorithm;
            this.responseTransformer = ((OperationCallSettings) operationCallSettings).responseTransformer;
            this.metadataTransformer = ((OperationCallSettings) operationCallSettings).metadataTransformer;
        }

        public Builder<RequestT, ResponseT, MetadataT> setPollingAlgorithm(TimedRetryAlgorithm timedRetryAlgorithm) {
            this.pollingAlgorithm = timedRetryAlgorithm;
            return this;
        }

        public TimedRetryAlgorithm getPollingAlgorithm() {
            return this.pollingAlgorithm;
        }

        public Builder<RequestT, ResponseT, MetadataT> setInitialCallSettings(UnaryCallSettings<RequestT, OperationSnapshot> unaryCallSettings) {
            this.initialCallSettings = unaryCallSettings;
            return this;
        }

        public UnaryCallSettings<RequestT, OperationSnapshot> getInitialCallSettings() {
            return this.initialCallSettings;
        }

        public final ApiFunction<OperationSnapshot, ResponseT> getResponseTransformer() {
            return this.responseTransformer;
        }

        public Builder<RequestT, ResponseT, MetadataT> setResponseTransformer(ApiFunction<OperationSnapshot, ResponseT> apiFunction) {
            this.responseTransformer = apiFunction;
            return this;
        }

        public final ApiFunction<OperationSnapshot, MetadataT> getMetadataTransformer() {
            return this.metadataTransformer;
        }

        public Builder<RequestT, ResponseT, MetadataT> setMetadataTransformer(ApiFunction<OperationSnapshot, MetadataT> apiFunction) {
            this.metadataTransformer = apiFunction;
            return this;
        }

        public OperationCallSettings<RequestT, ResponseT, MetadataT> build() {
            return new OperationCallSettings<>(this.initialCallSettings, this.pollingAlgorithm, this.responseTransformer, this.metadataTransformer);
        }
    }
}
