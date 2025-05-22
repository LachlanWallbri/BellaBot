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
import com.google.cloud.dialogflow.v2beta1.BatchDeleteIntentsRequest;
import com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsRequest;
import com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponse;
import com.google.cloud.dialogflow.v2beta1.CreateIntentRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteIntentRequest;
import com.google.cloud.dialogflow.v2beta1.GetIntentRequest;
import com.google.cloud.dialogflow.v2beta1.Intent;
import com.google.cloud.dialogflow.v2beta1.IntentsClient;
import com.google.cloud.dialogflow.v2beta1.ListIntentsRequest;
import com.google.cloud.dialogflow.v2beta1.ListIntentsResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateIntentRequest;
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
public class IntentsStubSettings extends StubSettings<IntentsStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").add((ImmutableList.Builder) "https://www.googleapis.com/auth/dialogflow").build();
    private static final PagedListDescriptor<ListIntentsRequest, ListIntentsResponse, Intent> LIST_INTENTS_PAGE_STR_DESC = new PagedListDescriptor<ListIntentsRequest, ListIntentsResponse, Intent>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.IntentsStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListIntentsRequest injectToken(ListIntentsRequest listIntentsRequest, String str) {
            return ListIntentsRequest.newBuilder(listIntentsRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListIntentsRequest injectPageSize(ListIntentsRequest listIntentsRequest, int i) {
            return ListIntentsRequest.newBuilder(listIntentsRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(ListIntentsRequest listIntentsRequest) {
            return Integer.valueOf(listIntentsRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(ListIntentsResponse listIntentsResponse) {
            return listIntentsResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<Intent> extractResources(ListIntentsResponse listIntentsResponse) {
            if (listIntentsResponse.getIntentsList() != null) {
                return listIntentsResponse.getIntentsList();
            }
            return ImmutableList.m634of();
        }
    };
    private static final PagedListResponseFactory<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse> LIST_INTENTS_PAGE_STR_FACT = new PagedListResponseFactory<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.IntentsStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<IntentsClient.ListIntentsPagedResponse> getFuturePagedResponse(UnaryCallable<ListIntentsRequest, ListIntentsResponse> unaryCallable, ListIntentsRequest listIntentsRequest, ApiCallContext apiCallContext, ApiFuture<ListIntentsResponse> apiFuture) {
            return IntentsClient.ListIntentsPagedResponse.createAsync(PageContext.create(unaryCallable, IntentsStubSettings.LIST_INTENTS_PAGE_STR_DESC, listIntentsRequest, apiCallContext), apiFuture);
        }
    };
    private final OperationCallSettings<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationSettings;
    private final UnaryCallSettings<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsSettings;
    private final OperationCallSettings<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationSettings;
    private final UnaryCallSettings<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsSettings;
    private final UnaryCallSettings<CreateIntentRequest, Intent> createIntentSettings;
    private final UnaryCallSettings<DeleteIntentRequest, Empty> deleteIntentSettings;
    private final UnaryCallSettings<GetIntentRequest, Intent> getIntentSettings;
    private final PagedCallSettings<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse> listIntentsSettings;
    private final UnaryCallSettings<UpdateIntentRequest, Intent> updateIntentSettings;

    public static String getDefaultEndpoint() {
        return "dialogflow.googleapis.com:443";
    }

    public PagedCallSettings<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse> listIntentsSettings() {
        return this.listIntentsSettings;
    }

    public UnaryCallSettings<GetIntentRequest, Intent> getIntentSettings() {
        return this.getIntentSettings;
    }

    public UnaryCallSettings<CreateIntentRequest, Intent> createIntentSettings() {
        return this.createIntentSettings;
    }

    public UnaryCallSettings<UpdateIntentRequest, Intent> updateIntentSettings() {
        return this.updateIntentSettings;
    }

    public UnaryCallSettings<DeleteIntentRequest, Empty> deleteIntentSettings() {
        return this.deleteIntentSettings;
    }

    public UnaryCallSettings<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsSettings() {
        return this.batchUpdateIntentsSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationSettings() {
        return this.batchUpdateIntentsOperationSettings;
    }

    public UnaryCallSettings<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsSettings() {
        return this.batchDeleteIntentsSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationSettings() {
        return this.batchDeleteIntentsOperationSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public IntentsStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcIntentsStub.create(this);
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
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(IntentsStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected IntentsStubSettings(Builder builder) throws IOException {
        super(builder);
        this.listIntentsSettings = builder.listIntentsSettings().build();
        this.getIntentSettings = builder.getIntentSettings().build();
        this.createIntentSettings = builder.createIntentSettings().build();
        this.updateIntentSettings = builder.updateIntentSettings().build();
        this.deleteIntentSettings = builder.deleteIntentSettings().build();
        this.batchUpdateIntentsSettings = builder.batchUpdateIntentsSettings().build();
        this.batchUpdateIntentsOperationSettings = builder.batchUpdateIntentsOperationSettings().build();
        this.batchDeleteIntentsSettings = builder.batchDeleteIntentsSettings().build();
        this.batchDeleteIntentsOperationSettings = builder.batchDeleteIntentsOperationSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<IntentsStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final OperationCallSettings.Builder<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationSettings;
        private final UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsSettings;
        private final OperationCallSettings.Builder<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationSettings;
        private final UnaryCallSettings.Builder<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsSettings;
        private final UnaryCallSettings.Builder<CreateIntentRequest, Intent> createIntentSettings;
        private final UnaryCallSettings.Builder<DeleteIntentRequest, Empty> deleteIntentSettings;
        private final UnaryCallSettings.Builder<GetIntentRequest, Intent> getIntentSettings;
        private final PagedCallSettings.Builder<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse> listIntentsSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<UpdateIntentRequest, Intent> updateIntentSettings;

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
            this.listIntentsSettings = PagedCallSettings.newBuilder(IntentsStubSettings.LIST_INTENTS_PAGE_STR_FACT);
            this.getIntentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createIntentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateIntentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteIntentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchUpdateIntentsSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchUpdateIntentsOperationSettings = OperationCallSettings.newBuilder();
            this.batchDeleteIntentsSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchDeleteIntentsOperationSettings = OperationCallSettings.newBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m641of((UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.listIntentsSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.getIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.createIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.updateIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.deleteIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.batchUpdateIntentsSettings, this.batchDeleteIntentsSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(IntentsStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(IntentsStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(IntentsStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(IntentsStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.listIntentsSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getIntentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createIntentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateIntentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteIntentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchUpdateIntentsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchDeleteIntentsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchUpdateIntentsOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(BatchUpdateIntentsResponse.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.batchDeleteIntentsOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            return builder;
        }

        protected Builder(IntentsStubSettings intentsStubSettings) {
            super(intentsStubSettings);
            this.listIntentsSettings = intentsStubSettings.listIntentsSettings.toBuilder();
            this.getIntentSettings = intentsStubSettings.getIntentSettings.toBuilder();
            this.createIntentSettings = intentsStubSettings.createIntentSettings.toBuilder();
            this.updateIntentSettings = intentsStubSettings.updateIntentSettings.toBuilder();
            this.deleteIntentSettings = intentsStubSettings.deleteIntentSettings.toBuilder();
            this.batchUpdateIntentsSettings = intentsStubSettings.batchUpdateIntentsSettings.toBuilder();
            this.batchUpdateIntentsOperationSettings = intentsStubSettings.batchUpdateIntentsOperationSettings.toBuilder();
            this.batchDeleteIntentsSettings = intentsStubSettings.batchDeleteIntentsSettings.toBuilder();
            this.batchDeleteIntentsOperationSettings = intentsStubSettings.batchDeleteIntentsOperationSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m641of((UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.listIntentsSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.getIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.createIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.updateIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.deleteIntentSettings, (UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation>) this.batchUpdateIntentsSettings, this.batchDeleteIntentsSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListIntentsRequest, ListIntentsResponse, IntentsClient.ListIntentsPagedResponse> listIntentsSettings() {
            return this.listIntentsSettings;
        }

        public UnaryCallSettings.Builder<GetIntentRequest, Intent> getIntentSettings() {
            return this.getIntentSettings;
        }

        public UnaryCallSettings.Builder<CreateIntentRequest, Intent> createIntentSettings() {
            return this.createIntentSettings;
        }

        public UnaryCallSettings.Builder<UpdateIntentRequest, Intent> updateIntentSettings() {
            return this.updateIntentSettings;
        }

        public UnaryCallSettings.Builder<DeleteIntentRequest, Empty> deleteIntentSettings() {
            return this.deleteIntentSettings;
        }

        public UnaryCallSettings.Builder<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsSettings() {
            return this.batchUpdateIntentsSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationSettings() {
            return this.batchUpdateIntentsOperationSettings;
        }

        public UnaryCallSettings.Builder<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsSettings() {
            return this.batchDeleteIntentsSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationSettings() {
            return this.batchDeleteIntentsOperationSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new IntentsStubSettings(this);
        }
    }
}
