package com.google.longrunning;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.longrunning.OperationsClient;
import com.google.longrunning.stub.OperationsStubSettings;
import com.google.protobuf.Empty;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public class OperationsSettings extends ClientSettings<OperationsSettings> {
    public UnaryCallSettings<GetOperationRequest, Operation> getOperationSettings() {
        return ((OperationsStubSettings) getStubSettings()).getOperationSettings();
    }

    public PagedCallSettings<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
        return ((OperationsStubSettings) getStubSettings()).listOperationsSettings();
    }

    public UnaryCallSettings<CancelOperationRequest, Empty> cancelOperationSettings() {
        return ((OperationsStubSettings) getStubSettings()).cancelOperationSettings();
    }

    public UnaryCallSettings<DeleteOperationRequest, Empty> deleteOperationSettings() {
        return ((OperationsStubSettings) getStubSettings()).deleteOperationSettings();
    }

    public UnaryCallSettings<WaitOperationRequest, Operation> waitOperationSettings() {
        return ((OperationsStubSettings) getStubSettings()).waitOperationSettings();
    }

    public static final OperationsSettings create(OperationsStubSettings operationsStubSettings) throws IOException {
        return new Builder(operationsStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return OperationsStubSettings.defaultExecutorProviderBuilder();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return OperationsStubSettings.defaultCredentialsProviderBuilder();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return OperationsStubSettings.defaultApiClientHeaderProviderBuilder();
    }

    public static Builder newBuilder() {
        return Builder.access$000();
    }

    public static Builder newBuilder(ClientContext clientContext) {
        return new Builder(clientContext);
    }

    @Override // com.google.api.gax.rpc.ClientSettings
    public Builder toBuilder() {
        return new Builder(this);
    }

    protected OperationsSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<OperationsSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(OperationsStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(OperationsStubSettings.newBuilder());
        }

        protected Builder(OperationsSettings operationsSettings) {
            super(operationsSettings.getStubSettings().toBuilder());
        }

        protected Builder(OperationsStubSettings.Builder builder) {
            super(builder);
        }

        public OperationsStubSettings.Builder getStubSettingsBuilder() {
            return (OperationsStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public UnaryCallSettings.Builder<GetOperationRequest, Operation> getOperationSettings() {
            return getStubSettingsBuilder().getOperationSettings();
        }

        public PagedCallSettings.Builder<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
            return getStubSettingsBuilder().listOperationsSettings();
        }

        public UnaryCallSettings.Builder<CancelOperationRequest, Empty> cancelOperationSettings() {
            return getStubSettingsBuilder().cancelOperationSettings();
        }

        public UnaryCallSettings.Builder<DeleteOperationRequest, Empty> deleteOperationSettings() {
            return getStubSettingsBuilder().deleteOperationSettings();
        }

        public UnaryCallSettings.Builder<WaitOperationRequest, Operation> waitOperationSettings() {
            return getStubSettingsBuilder().waitOperationSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public OperationsSettings build() throws IOException {
            return new OperationsSettings(this);
        }
    }
}
