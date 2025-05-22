package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.grpc.ProtoOperationTransformers;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.OperationCallSettings;
import com.google.api.gax.rpc.PageContext;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.PagedListDescriptor;
import com.google.api.gax.rpc.PagedListResponseFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.Agent;
import com.google.cloud.dialogflow.v2beta1.AgentsClient;
import com.google.cloud.dialogflow.v2beta1.DeleteAgentRequest;
import com.google.cloud.dialogflow.v2beta1.ExportAgentRequest;
import com.google.cloud.dialogflow.v2beta1.ExportAgentResponse;
import com.google.cloud.dialogflow.v2beta1.GetAgentRequest;
import com.google.cloud.dialogflow.v2beta1.GetValidationResultRequest;
import com.google.cloud.dialogflow.v2beta1.ImportAgentRequest;
import com.google.cloud.dialogflow.v2beta1.RestoreAgentRequest;
import com.google.cloud.dialogflow.v2beta1.SearchAgentsRequest;
import com.google.cloud.dialogflow.v2beta1.SearchAgentsResponse;
import com.google.cloud.dialogflow.v2beta1.SetAgentRequest;
import com.google.cloud.dialogflow.v2beta1.TrainAgentRequest;
import com.google.cloud.dialogflow.v2beta1.ValidationResult;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class AgentsStubSettings extends StubSettings<AgentsStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").add((ImmutableList.Builder) "https://www.googleapis.com/auth/dialogflow").build();
    private static final PagedListDescriptor<SearchAgentsRequest, SearchAgentsResponse, Agent> SEARCH_AGENTS_PAGE_STR_DESC = new PagedListDescriptor<SearchAgentsRequest, SearchAgentsResponse, Agent>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.AgentsStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public SearchAgentsRequest injectToken(SearchAgentsRequest searchAgentsRequest, String str) {
            return SearchAgentsRequest.newBuilder(searchAgentsRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public SearchAgentsRequest injectPageSize(SearchAgentsRequest searchAgentsRequest, int i) {
            return SearchAgentsRequest.newBuilder(searchAgentsRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(SearchAgentsRequest searchAgentsRequest) {
            return Integer.valueOf(searchAgentsRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(SearchAgentsResponse searchAgentsResponse) {
            return searchAgentsResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<Agent> extractResources(SearchAgentsResponse searchAgentsResponse) {
            if (searchAgentsResponse.getAgentsList() != null) {
                return searchAgentsResponse.getAgentsList();
            }
            return ImmutableList.m634of();
        }
    };
    private static final PagedListResponseFactory<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse> SEARCH_AGENTS_PAGE_STR_FACT = new PagedListResponseFactory<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.AgentsStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<AgentsClient.SearchAgentsPagedResponse> getFuturePagedResponse(UnaryCallable<SearchAgentsRequest, SearchAgentsResponse> unaryCallable, SearchAgentsRequest searchAgentsRequest, ApiCallContext apiCallContext, ApiFuture<SearchAgentsResponse> apiFuture) {
            return AgentsClient.SearchAgentsPagedResponse.createAsync(PageContext.create(unaryCallable, AgentsStubSettings.SEARCH_AGENTS_PAGE_STR_DESC, searchAgentsRequest, apiCallContext), apiFuture);
        }
    };
    private final UnaryCallSettings<DeleteAgentRequest, Empty> deleteAgentSettings;
    private final OperationCallSettings<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationSettings;
    private final UnaryCallSettings<ExportAgentRequest, Operation> exportAgentSettings;
    private final UnaryCallSettings<GetAgentRequest, Agent> getAgentSettings;
    private final UnaryCallSettings<GetValidationResultRequest, ValidationResult> getValidationResultSettings;
    private final OperationCallSettings<ImportAgentRequest, Empty, Struct> importAgentOperationSettings;
    private final UnaryCallSettings<ImportAgentRequest, Operation> importAgentSettings;
    private final OperationCallSettings<RestoreAgentRequest, Empty, Struct> restoreAgentOperationSettings;
    private final UnaryCallSettings<RestoreAgentRequest, Operation> restoreAgentSettings;
    private final PagedCallSettings<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse> searchAgentsSettings;
    private final UnaryCallSettings<SetAgentRequest, Agent> setAgentSettings;
    private final OperationCallSettings<TrainAgentRequest, Empty, Struct> trainAgentOperationSettings;
    private final UnaryCallSettings<TrainAgentRequest, Operation> trainAgentSettings;

    public static String getDefaultEndpoint() {
        return "dialogflow.googleapis.com:443";
    }

    public UnaryCallSettings<SetAgentRequest, Agent> setAgentSettings() {
        return this.setAgentSettings;
    }

    public UnaryCallSettings<DeleteAgentRequest, Empty> deleteAgentSettings() {
        return this.deleteAgentSettings;
    }

    public UnaryCallSettings<GetAgentRequest, Agent> getAgentSettings() {
        return this.getAgentSettings;
    }

    public PagedCallSettings<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse> searchAgentsSettings() {
        return this.searchAgentsSettings;
    }

    public UnaryCallSettings<TrainAgentRequest, Operation> trainAgentSettings() {
        return this.trainAgentSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<TrainAgentRequest, Empty, Struct> trainAgentOperationSettings() {
        return this.trainAgentOperationSettings;
    }

    public UnaryCallSettings<ExportAgentRequest, Operation> exportAgentSettings() {
        return this.exportAgentSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationSettings() {
        return this.exportAgentOperationSettings;
    }

    public UnaryCallSettings<ImportAgentRequest, Operation> importAgentSettings() {
        return this.importAgentSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<ImportAgentRequest, Empty, Struct> importAgentOperationSettings() {
        return this.importAgentOperationSettings;
    }

    public UnaryCallSettings<RestoreAgentRequest, Operation> restoreAgentSettings() {
        return this.restoreAgentSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<RestoreAgentRequest, Empty, Struct> restoreAgentOperationSettings() {
        return this.restoreAgentOperationSettings;
    }

    public UnaryCallSettings<GetValidationResultRequest, ValidationResult> getValidationResultSettings() {
        return this.getValidationResultSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public AgentsStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcAgentsStub.create(this);
        }
        throw new UnsupportedOperationException("Transport not supported: " + getTransportChannelProvider().getTransportName());
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return InstantiatingExecutorProvider.newBuilder();
    }

    public static List<String> getDefaultServiceScopes() {
        return DEFAULT_SERVICE_SCOPES;
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return GoogleCredentialsProvider.newBuilder().setScopesToApply(DEFAULT_SERVICE_SCOPES);
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return InstantiatingGrpcChannelProvider.newBuilder().setMaxInboundMessageSize(Integer.MAX_VALUE);
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return defaultGrpcTransportProviderBuilder().build();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(AgentsStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
    }

    public static Builder newBuilder() {
        return Builder.access$000();
    }

    public static Builder newBuilder(ClientContext clientContext) {
        return new Builder(clientContext);
    }

    @Override // com.google.api.gax.rpc.StubSettings
    public Builder toBuilder() {
        return new Builder(this);
    }

    protected AgentsStubSettings(Builder builder) throws IOException {
        super(builder);
        this.setAgentSettings = builder.setAgentSettings().build();
        this.deleteAgentSettings = builder.deleteAgentSettings().build();
        this.getAgentSettings = builder.getAgentSettings().build();
        this.searchAgentsSettings = builder.searchAgentsSettings().build();
        this.trainAgentSettings = builder.trainAgentSettings().build();
        this.trainAgentOperationSettings = builder.trainAgentOperationSettings().build();
        this.exportAgentSettings = builder.exportAgentSettings().build();
        this.exportAgentOperationSettings = builder.exportAgentOperationSettings().build();
        this.importAgentSettings = builder.importAgentSettings().build();
        this.importAgentOperationSettings = builder.importAgentOperationSettings().build();
        this.restoreAgentSettings = builder.restoreAgentSettings().build();
        this.restoreAgentOperationSettings = builder.restoreAgentOperationSettings().build();
        this.getValidationResultSettings = builder.getValidationResultSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<AgentsStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final UnaryCallSettings.Builder<DeleteAgentRequest, Empty> deleteAgentSettings;
        private final OperationCallSettings.Builder<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationSettings;
        private final UnaryCallSettings.Builder<ExportAgentRequest, Operation> exportAgentSettings;
        private final UnaryCallSettings.Builder<GetAgentRequest, Agent> getAgentSettings;
        private final UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult> getValidationResultSettings;
        private final OperationCallSettings.Builder<ImportAgentRequest, Empty, Struct> importAgentOperationSettings;
        private final UnaryCallSettings.Builder<ImportAgentRequest, Operation> importAgentSettings;
        private final OperationCallSettings.Builder<RestoreAgentRequest, Empty, Struct> restoreAgentOperationSettings;
        private final UnaryCallSettings.Builder<RestoreAgentRequest, Operation> restoreAgentSettings;
        private final PagedCallSettings.Builder<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse> searchAgentsSettings;
        private final UnaryCallSettings.Builder<SetAgentRequest, Agent> setAgentSettings;
        private final OperationCallSettings.Builder<TrainAgentRequest, Empty, Struct> trainAgentOperationSettings;
        private final UnaryCallSettings.Builder<TrainAgentRequest, Operation> trainAgentSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;

        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        static {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.put("idempotent", ImmutableSet.copyOf((Collection) Lists.newArrayList(StatusCode.Code.DEADLINE_EXCEEDED, StatusCode.Code.UNAVAILABLE)));
            builder.put("non_idempotent", ImmutableSet.copyOf((Collection) Lists.newArrayList()));
            RETRYABLE_CODE_DEFINITIONS = builder.build();
            ImmutableMap.Builder builder2 = ImmutableMap.builder();
            builder2.put("default", RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(100L)).setRetryDelayMultiplier(1.3d).setMaxRetryDelay(Duration.ofMillis(60000L)).setInitialRpcTimeout(Duration.ofMillis(20000L)).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ofMillis(20000L)).setTotalTimeout(Duration.ofMillis(600000L)).build());
            RETRY_PARAM_DEFINITIONS = builder2.build();
        }

        protected Builder() {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(clientContext);
            this.setAgentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteAgentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.getAgentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.searchAgentsSettings = PagedCallSettings.newBuilder(AgentsStubSettings.SEARCH_AGENTS_PAGE_STR_FACT);
            this.trainAgentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.trainAgentOperationSettings = OperationCallSettings.newBuilder();
            this.exportAgentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.exportAgentOperationSettings = OperationCallSettings.newBuilder();
            this.importAgentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.importAgentOperationSettings = OperationCallSettings.newBuilder();
            this.restoreAgentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.restoreAgentOperationSettings = OperationCallSettings.newBuilder();
            this.getValidationResultSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m643of((UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.setAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.deleteAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.getAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.searchAgentsSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.trainAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.exportAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.importAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.restoreAgentSettings, this.getValidationResultSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(AgentsStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(AgentsStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(AgentsStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(AgentsStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.setAgentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteAgentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getAgentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.searchAgentsSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.trainAgentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.exportAgentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.importAgentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.restoreAgentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getValidationResultSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.trainAgentOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.exportAgentOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(ExportAgentResponse.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.importAgentOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.restoreAgentOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            return builder;
        }

        protected Builder(AgentsStubSettings agentsStubSettings) {
            super(agentsStubSettings);
            this.setAgentSettings = agentsStubSettings.setAgentSettings.toBuilder();
            this.deleteAgentSettings = agentsStubSettings.deleteAgentSettings.toBuilder();
            this.getAgentSettings = agentsStubSettings.getAgentSettings.toBuilder();
            this.searchAgentsSettings = agentsStubSettings.searchAgentsSettings.toBuilder();
            this.trainAgentSettings = agentsStubSettings.trainAgentSettings.toBuilder();
            this.trainAgentOperationSettings = agentsStubSettings.trainAgentOperationSettings.toBuilder();
            this.exportAgentSettings = agentsStubSettings.exportAgentSettings.toBuilder();
            this.exportAgentOperationSettings = agentsStubSettings.exportAgentOperationSettings.toBuilder();
            this.importAgentSettings = agentsStubSettings.importAgentSettings.toBuilder();
            this.importAgentOperationSettings = agentsStubSettings.importAgentOperationSettings.toBuilder();
            this.restoreAgentSettings = agentsStubSettings.restoreAgentSettings.toBuilder();
            this.restoreAgentOperationSettings = agentsStubSettings.restoreAgentOperationSettings.toBuilder();
            this.getValidationResultSettings = agentsStubSettings.getValidationResultSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m643of((UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.setAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.deleteAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.getAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.searchAgentsSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.trainAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.exportAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.importAgentSettings, (UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult>) this.restoreAgentSettings, this.getValidationResultSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public UnaryCallSettings.Builder<SetAgentRequest, Agent> setAgentSettings() {
            return this.setAgentSettings;
        }

        public UnaryCallSettings.Builder<DeleteAgentRequest, Empty> deleteAgentSettings() {
            return this.deleteAgentSettings;
        }

        public UnaryCallSettings.Builder<GetAgentRequest, Agent> getAgentSettings() {
            return this.getAgentSettings;
        }

        public PagedCallSettings.Builder<SearchAgentsRequest, SearchAgentsResponse, AgentsClient.SearchAgentsPagedResponse> searchAgentsSettings() {
            return this.searchAgentsSettings;
        }

        public UnaryCallSettings.Builder<TrainAgentRequest, Operation> trainAgentSettings() {
            return this.trainAgentSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<TrainAgentRequest, Empty, Struct> trainAgentOperationSettings() {
            return this.trainAgentOperationSettings;
        }

        public UnaryCallSettings.Builder<ExportAgentRequest, Operation> exportAgentSettings() {
            return this.exportAgentSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationSettings() {
            return this.exportAgentOperationSettings;
        }

        public UnaryCallSettings.Builder<ImportAgentRequest, Operation> importAgentSettings() {
            return this.importAgentSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<ImportAgentRequest, Empty, Struct> importAgentOperationSettings() {
            return this.importAgentOperationSettings;
        }

        public UnaryCallSettings.Builder<RestoreAgentRequest, Operation> restoreAgentSettings() {
            return this.restoreAgentSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<RestoreAgentRequest, Empty, Struct> restoreAgentOperationSettings() {
            return this.restoreAgentOperationSettings;
        }

        public UnaryCallSettings.Builder<GetValidationResultRequest, ValidationResult> getValidationResultSettings() {
            return this.getValidationResultSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new AgentsStubSettings(this);
        }
    }
}
