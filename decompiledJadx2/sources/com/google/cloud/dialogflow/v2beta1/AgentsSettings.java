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
import com.google.cloud.dialogflow.v2beta1.AgentsClient;
import com.google.cloud.dialogflow.v2beta1.stub.AgentsStubSettings;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class AgentsSettings extends ClientSettings<AgentsSettings> {
    public UnaryCallSettings<SetAgentRequest, Agent> setAgentSettings() {
        return ((AgentsStubSettings) getStubSettings()).setAgentSettings();
    }

    public UnaryCallSettings<DeleteAgentRequest, Empty> deleteAgentSettings() {
        return ((AgentsStubSettings) getStubSettings()).deleteAgentSettings();
    }

    public UnaryCallSettings<GetAgentRequest, Agent> getAgentSettings() {
        return ((AgentsStubSettings) getStubSettings()).getAgentSettings();
    }

    public PagedCallSettings<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse> searchAgentsSettings() {
        return ((AgentsStubSettings) getStubSettings()).searchAgentsSettings();
    }

    public UnaryCallSettings<TrainAgentRequest, Operation> trainAgentSettings() {
        return ((AgentsStubSettings) getStubSettings()).trainAgentSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<TrainAgentRequest, Empty, Struct> trainAgentOperationSettings() {
        return ((AgentsStubSettings) getStubSettings()).trainAgentOperationSettings();
    }

    public UnaryCallSettings<ExportAgentRequest, Operation> exportAgentSettings() {
        return ((AgentsStubSettings) getStubSettings()).exportAgentSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationSettings() {
        return ((AgentsStubSettings) getStubSettings()).exportAgentOperationSettings();
    }

    public UnaryCallSettings<ImportAgentRequest, Operation> importAgentSettings() {
        return ((AgentsStubSettings) getStubSettings()).importAgentSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<ImportAgentRequest, Empty, Struct> importAgentOperationSettings() {
        return ((AgentsStubSettings) getStubSettings()).importAgentOperationSettings();
    }

    public UnaryCallSettings<RestoreAgentRequest, Operation> restoreAgentSettings() {
        return ((AgentsStubSettings) getStubSettings()).restoreAgentSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<RestoreAgentRequest, Empty, Struct> restoreAgentOperationSettings() {
        return ((AgentsStubSettings) getStubSettings()).restoreAgentOperationSettings();
    }

    public UnaryCallSettings<GetValidationResultRequest, ValidationResult> getValidationResultSettings() {
        return ((AgentsStubSettings) getStubSettings()).getValidationResultSettings();
    }

    public static final AgentsSettings create(AgentsStubSettings agentsStubSettings) throws IOException {
        return new Builder(agentsStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return AgentsStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return AgentsStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return AgentsStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return AgentsStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return AgentsStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return AgentsStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return AgentsStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected AgentsSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<AgentsSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(AgentsStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(AgentsStubSettings.newBuilder());
        }

        protected Builder(AgentsSettings agentsSettings) {
            super(agentsSettings.getStubSettings().toBuilder());
        }

        protected Builder(AgentsStubSettings.Builder builder) {
            super(builder);
        }

        public AgentsStubSettings.Builder getStubSettingsBuilder() {
            return (AgentsStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public UnaryCallSettings.Builder<SetAgentRequest, Agent> setAgentSettings() {
            return getStubSettingsBuilder().setAgentSettings();
        }

        public UnaryCallSettings.Builder<DeleteAgentRequest, Empty> deleteAgentSettings() {
            return getStubSettingsBuilder().deleteAgentSettings();
        }

        public UnaryCallSettings.Builder<GetAgentRequest, Agent> getAgentSettings() {
            return getStubSettingsBuilder().getAgentSettings();
        }

        public PagedCallSettings.Builder<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse> searchAgentsSettings() {
            return getStubSettingsBuilder().searchAgentsSettings();
        }

        public UnaryCallSettings.Builder<TrainAgentRequest, Operation> trainAgentSettings() {
            return getStubSettingsBuilder().trainAgentSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<TrainAgentRequest, Empty, Struct> trainAgentOperationSettings() {
            return getStubSettingsBuilder().trainAgentOperationSettings();
        }

        public UnaryCallSettings.Builder<ExportAgentRequest, Operation> exportAgentSettings() {
            return getStubSettingsBuilder().exportAgentSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationSettings() {
            return getStubSettingsBuilder().exportAgentOperationSettings();
        }

        public UnaryCallSettings.Builder<ImportAgentRequest, Operation> importAgentSettings() {
            return getStubSettingsBuilder().importAgentSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<ImportAgentRequest, Empty, Struct> importAgentOperationSettings() {
            return getStubSettingsBuilder().importAgentOperationSettings();
        }

        public UnaryCallSettings.Builder<RestoreAgentRequest, Operation> restoreAgentSettings() {
            return getStubSettingsBuilder().restoreAgentSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<RestoreAgentRequest, Empty, Struct> restoreAgentOperationSettings() {
            return getStubSettingsBuilder().restoreAgentOperationSettings();
        }

        public UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult> getValidationResultSettings() {
            return getStubSettingsBuilder().getValidationResultSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public AgentsSettings build() throws IOException {
            return new AgentsSettings(this);
        }
    }
}
