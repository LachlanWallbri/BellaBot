package com.google.cloud.dialogflow.v2beta1;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.OperationCallSettings;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.dialogflow.v2beta1.IntentsClient;
import com.google.cloud.dialogflow.v2beta1.stub.IntentsStubSettings;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class IntentsSettings extends ClientSettings<IntentsSettings> {
    public PagedCallSettings<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse> listIntentsSettings() {
        return ((IntentsStubSettings) getStubSettings()).listIntentsSettings();
    }

    public UnaryCallSettings<GetIntentRequest, Intent> getIntentSettings() {
        return ((IntentsStubSettings) getStubSettings()).getIntentSettings();
    }

    public UnaryCallSettings<CreateIntentRequest, Intent> createIntentSettings() {
        return ((IntentsStubSettings) getStubSettings()).createIntentSettings();
    }

    public UnaryCallSettings<UpdateIntentRequest, Intent> updateIntentSettings() {
        return ((IntentsStubSettings) getStubSettings()).updateIntentSettings();
    }

    public UnaryCallSettings<DeleteIntentRequest, Empty> deleteIntentSettings() {
        return ((IntentsStubSettings) getStubSettings()).deleteIntentSettings();
    }

    public UnaryCallSettings<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsSettings() {
        return ((IntentsStubSettings) getStubSettings()).batchUpdateIntentsSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationSettings() {
        return ((IntentsStubSettings) getStubSettings()).batchUpdateIntentsOperationSettings();
    }

    public UnaryCallSettings<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsSettings() {
        return ((IntentsStubSettings) getStubSettings()).batchDeleteIntentsSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationSettings() {
        return ((IntentsStubSettings) getStubSettings()).batchDeleteIntentsOperationSettings();
    }

    public static final IntentsSettings create(IntentsStubSettings intentsStubSettings) throws IOException {
        return new Builder(intentsStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return IntentsStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return IntentsStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return IntentsStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return IntentsStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return IntentsStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return IntentsStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return IntentsStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected IntentsSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<IntentsSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(IntentsStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(IntentsStubSettings.newBuilder());
        }

        protected Builder(IntentsSettings intentsSettings) {
            super(intentsSettings.getStubSettings().toBuilder());
        }

        protected Builder(IntentsStubSettings.Builder builder) {
            super(builder);
        }

        public IntentsStubSettings.Builder getStubSettingsBuilder() {
            return (IntentsStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public PagedCallSettings.Builder<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse> listIntentsSettings() {
            return getStubSettingsBuilder().listIntentsSettings();
        }

        public UnaryCallSettings.Builder<GetIntentRequest, Intent> getIntentSettings() {
            return getStubSettingsBuilder().getIntentSettings();
        }

        public UnaryCallSettings.Builder<CreateIntentRequest, Intent> createIntentSettings() {
            return getStubSettingsBuilder().createIntentSettings();
        }

        public UnaryCallSettings.Builder<UpdateIntentRequest, Intent> updateIntentSettings() {
            return getStubSettingsBuilder().updateIntentSettings();
        }

        public UnaryCallSettings.Builder<DeleteIntentRequest, Empty> deleteIntentSettings() {
            return getStubSettingsBuilder().deleteIntentSettings();
        }

        public UnaryCallSettings.Builder<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsSettings() {
            return getStubSettingsBuilder().batchUpdateIntentsSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationSettings() {
            return getStubSettingsBuilder().batchUpdateIntentsOperationSettings();
        }

        public UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsSettings() {
            return getStubSettingsBuilder().batchDeleteIntentsSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationSettings() {
            return getStubSettingsBuilder().batchDeleteIntentsOperationSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public IntentsSettings build() throws IOException {
            return new IntentsSettings(this);
        }
    }
}
