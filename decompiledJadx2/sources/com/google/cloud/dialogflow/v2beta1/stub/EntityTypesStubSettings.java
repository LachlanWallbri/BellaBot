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
import com.google.cloud.dialogflow.v2beta1.BatchCreateEntitiesRequest;
import com.google.cloud.dialogflow.v2beta1.BatchDeleteEntitiesRequest;
import com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequest;
import com.google.cloud.dialogflow.v2beta1.BatchUpdateEntitiesRequest;
import com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequest;
import com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesResponse;
import com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.EntityType;
import com.google.cloud.dialogflow.v2beta1.EntityTypesClient;
import com.google.cloud.dialogflow.v2beta1.GetEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.ListEntityTypesRequest;
import com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateEntityTypeRequest;
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
public class EntityTypesStubSettings extends StubSettings<EntityTypesStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").add((ImmutableList.Builder) "https://www.googleapis.com/auth/dialogflow").build();
    private static final PagedListDescriptor<ListEntityTypesRequest, ListEntityTypesResponse, EntityType> LIST_ENTITY_TYPES_PAGE_STR_DESC = new PagedListDescriptor<ListEntityTypesRequest, ListEntityTypesResponse, EntityType>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.EntityTypesStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListEntityTypesRequest injectToken(ListEntityTypesRequest listEntityTypesRequest, String str) {
            return ListEntityTypesRequest.newBuilder(listEntityTypesRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListEntityTypesRequest injectPageSize(ListEntityTypesRequest listEntityTypesRequest, int i) {
            return ListEntityTypesRequest.newBuilder(listEntityTypesRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(ListEntityTypesRequest listEntityTypesRequest) {
            return Integer.valueOf(listEntityTypesRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(ListEntityTypesResponse listEntityTypesResponse) {
            return listEntityTypesResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<EntityType> extractResources(ListEntityTypesResponse listEntityTypesResponse) {
            if (listEntityTypesResponse.getEntityTypesList() != null) {
                return listEntityTypesResponse.getEntityTypesList();
            }
            return ImmutableList.m634of();
        }
    };
    private static final PagedListResponseFactory<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse> LIST_ENTITY_TYPES_PAGE_STR_FACT = new PagedListResponseFactory<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.EntityTypesStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<EntityTypesClient.ListEntityTypesPagedResponse> getFuturePagedResponse(UnaryCallable<ListEntityTypesRequest, ListEntityTypesResponse> unaryCallable, ListEntityTypesRequest listEntityTypesRequest, ApiCallContext apiCallContext, ApiFuture<ListEntityTypesResponse> apiFuture) {
            return EntityTypesClient.ListEntityTypesPagedResponse.createAsync(PageContext.create(unaryCallable, EntityTypesStubSettings.LIST_ENTITY_TYPES_PAGE_STR_DESC, listEntityTypesRequest, apiCallContext), apiFuture);
        }
    };
    private final OperationCallSettings<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationSettings;
    private final UnaryCallSettings<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesSettings;
    private final OperationCallSettings<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationSettings;
    private final UnaryCallSettings<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesSettings;
    private final OperationCallSettings<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationSettings;
    private final UnaryCallSettings<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesSettings;
    private final OperationCallSettings<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationSettings;
    private final UnaryCallSettings<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesSettings;
    private final OperationCallSettings<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationSettings;
    private final UnaryCallSettings<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesSettings;
    private final UnaryCallSettings<CreateEntityTypeRequest, EntityType> createEntityTypeSettings;
    private final UnaryCallSettings<DeleteEntityTypeRequest, Empty> deleteEntityTypeSettings;
    private final UnaryCallSettings<GetEntityTypeRequest, EntityType> getEntityTypeSettings;
    private final PagedCallSettings<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesSettings;
    private final UnaryCallSettings<UpdateEntityTypeRequest, EntityType> updateEntityTypeSettings;

    public static String getDefaultEndpoint() {
        return "dialogflow.googleapis.com:443";
    }

    public PagedCallSettings<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesSettings() {
        return this.listEntityTypesSettings;
    }

    public UnaryCallSettings<GetEntityTypeRequest, EntityType> getEntityTypeSettings() {
        return this.getEntityTypeSettings;
    }

    public UnaryCallSettings<CreateEntityTypeRequest, EntityType> createEntityTypeSettings() {
        return this.createEntityTypeSettings;
    }

    public UnaryCallSettings<UpdateEntityTypeRequest, EntityType> updateEntityTypeSettings() {
        return this.updateEntityTypeSettings;
    }

    public UnaryCallSettings<DeleteEntityTypeRequest, Empty> deleteEntityTypeSettings() {
        return this.deleteEntityTypeSettings;
    }

    public UnaryCallSettings<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesSettings() {
        return this.batchUpdateEntityTypesSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationSettings() {
        return this.batchUpdateEntityTypesOperationSettings;
    }

    public UnaryCallSettings<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesSettings() {
        return this.batchDeleteEntityTypesSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationSettings() {
        return this.batchDeleteEntityTypesOperationSettings;
    }

    public UnaryCallSettings<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesSettings() {
        return this.batchCreateEntitiesSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationSettings() {
        return this.batchCreateEntitiesOperationSettings;
    }

    public UnaryCallSettings<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesSettings() {
        return this.batchUpdateEntitiesSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationSettings() {
        return this.batchUpdateEntitiesOperationSettings;
    }

    public UnaryCallSettings<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesSettings() {
        return this.batchDeleteEntitiesSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationSettings() {
        return this.batchDeleteEntitiesOperationSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public EntityTypesStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcEntityTypesStub.create(this);
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
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(EntityTypesStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected EntityTypesStubSettings(Builder builder) throws IOException {
        super(builder);
        this.listEntityTypesSettings = builder.listEntityTypesSettings().build();
        this.getEntityTypeSettings = builder.getEntityTypeSettings().build();
        this.createEntityTypeSettings = builder.createEntityTypeSettings().build();
        this.updateEntityTypeSettings = builder.updateEntityTypeSettings().build();
        this.deleteEntityTypeSettings = builder.deleteEntityTypeSettings().build();
        this.batchUpdateEntityTypesSettings = builder.batchUpdateEntityTypesSettings().build();
        this.batchUpdateEntityTypesOperationSettings = builder.batchUpdateEntityTypesOperationSettings().build();
        this.batchDeleteEntityTypesSettings = builder.batchDeleteEntityTypesSettings().build();
        this.batchDeleteEntityTypesOperationSettings = builder.batchDeleteEntityTypesOperationSettings().build();
        this.batchCreateEntitiesSettings = builder.batchCreateEntitiesSettings().build();
        this.batchCreateEntitiesOperationSettings = builder.batchCreateEntitiesOperationSettings().build();
        this.batchUpdateEntitiesSettings = builder.batchUpdateEntitiesSettings().build();
        this.batchUpdateEntitiesOperationSettings = builder.batchUpdateEntitiesOperationSettings().build();
        this.batchDeleteEntitiesSettings = builder.batchDeleteEntitiesSettings().build();
        this.batchDeleteEntitiesOperationSettings = builder.batchDeleteEntitiesOperationSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<EntityTypesStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final OperationCallSettings.Builder<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationSettings;
        private final UnaryCallSettings.Builder<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesSettings;
        private final OperationCallSettings.Builder<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationSettings;
        private final UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesSettings;
        private final OperationCallSettings.Builder<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationSettings;
        private final UnaryCallSettings.Builder<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesSettings;
        private final OperationCallSettings.Builder<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationSettings;
        private final UnaryCallSettings.Builder<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesSettings;
        private final OperationCallSettings.Builder<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationSettings;
        private final UnaryCallSettings.Builder<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesSettings;
        private final UnaryCallSettings.Builder<CreateEntityTypeRequest, EntityType> createEntityTypeSettings;
        private final UnaryCallSettings.Builder<DeleteEntityTypeRequest, Empty> deleteEntityTypeSettings;
        private final UnaryCallSettings.Builder<GetEntityTypeRequest, EntityType> getEntityTypeSettings;
        private final PagedCallSettings.Builder<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<UpdateEntityTypeRequest, EntityType> updateEntityTypeSettings;

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
            this.listEntityTypesSettings = PagedCallSettings.newBuilder(EntityTypesStubSettings.LIST_ENTITY_TYPES_PAGE_STR_FACT);
            this.getEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchUpdateEntityTypesSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchUpdateEntityTypesOperationSettings = OperationCallSettings.newBuilder();
            this.batchDeleteEntityTypesSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchDeleteEntityTypesOperationSettings = OperationCallSettings.newBuilder();
            this.batchCreateEntitiesSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchCreateEntitiesOperationSettings = OperationCallSettings.newBuilder();
            this.batchUpdateEntitiesSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchUpdateEntitiesOperationSettings = OperationCallSettings.newBuilder();
            this.batchDeleteEntitiesSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.batchDeleteEntitiesOperationSettings = OperationCallSettings.newBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m644of((UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.listEntityTypesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.getEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.createEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.updateEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.deleteEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchUpdateEntityTypesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchDeleteEntityTypesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchCreateEntitiesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchUpdateEntitiesSettings, this.batchDeleteEntitiesSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(EntityTypesStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(EntityTypesStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(EntityTypesStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(EntityTypesStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.listEntityTypesSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchUpdateEntityTypesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchDeleteEntityTypesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchCreateEntitiesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchUpdateEntitiesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchDeleteEntitiesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.batchUpdateEntityTypesOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(BatchUpdateEntityTypesResponse.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.batchDeleteEntityTypesOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.batchCreateEntitiesOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.batchUpdateEntitiesOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.batchDeleteEntitiesOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(Struct.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            return builder;
        }

        protected Builder(EntityTypesStubSettings entityTypesStubSettings) {
            super(entityTypesStubSettings);
            this.listEntityTypesSettings = entityTypesStubSettings.listEntityTypesSettings.toBuilder();
            this.getEntityTypeSettings = entityTypesStubSettings.getEntityTypeSettings.toBuilder();
            this.createEntityTypeSettings = entityTypesStubSettings.createEntityTypeSettings.toBuilder();
            this.updateEntityTypeSettings = entityTypesStubSettings.updateEntityTypeSettings.toBuilder();
            this.deleteEntityTypeSettings = entityTypesStubSettings.deleteEntityTypeSettings.toBuilder();
            this.batchUpdateEntityTypesSettings = entityTypesStubSettings.batchUpdateEntityTypesSettings.toBuilder();
            this.batchUpdateEntityTypesOperationSettings = entityTypesStubSettings.batchUpdateEntityTypesOperationSettings.toBuilder();
            this.batchDeleteEntityTypesSettings = entityTypesStubSettings.batchDeleteEntityTypesSettings.toBuilder();
            this.batchDeleteEntityTypesOperationSettings = entityTypesStubSettings.batchDeleteEntityTypesOperationSettings.toBuilder();
            this.batchCreateEntitiesSettings = entityTypesStubSettings.batchCreateEntitiesSettings.toBuilder();
            this.batchCreateEntitiesOperationSettings = entityTypesStubSettings.batchCreateEntitiesOperationSettings.toBuilder();
            this.batchUpdateEntitiesSettings = entityTypesStubSettings.batchUpdateEntitiesSettings.toBuilder();
            this.batchUpdateEntitiesOperationSettings = entityTypesStubSettings.batchUpdateEntitiesOperationSettings.toBuilder();
            this.batchDeleteEntitiesSettings = entityTypesStubSettings.batchDeleteEntitiesSettings.toBuilder();
            this.batchDeleteEntitiesOperationSettings = entityTypesStubSettings.batchDeleteEntitiesOperationSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m644of((UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.listEntityTypesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.getEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.createEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.updateEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.deleteEntityTypeSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchUpdateEntityTypesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchDeleteEntityTypesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchCreateEntitiesSettings, (UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation>) this.batchUpdateEntitiesSettings, this.batchDeleteEntitiesSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesSettings() {
            return this.listEntityTypesSettings;
        }

        public UnaryCallSettings.Builder<GetEntityTypeRequest, EntityType> getEntityTypeSettings() {
            return this.getEntityTypeSettings;
        }

        public UnaryCallSettings.Builder<CreateEntityTypeRequest, EntityType> createEntityTypeSettings() {
            return this.createEntityTypeSettings;
        }

        public UnaryCallSettings.Builder<UpdateEntityTypeRequest, EntityType> updateEntityTypeSettings() {
            return this.updateEntityTypeSettings;
        }

        public UnaryCallSettings.Builder<DeleteEntityTypeRequest, Empty> deleteEntityTypeSettings() {
            return this.deleteEntityTypeSettings;
        }

        public UnaryCallSettings.Builder<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesSettings() {
            return this.batchUpdateEntityTypesSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationSettings() {
            return this.batchUpdateEntityTypesOperationSettings;
        }

        public UnaryCallSettings.Builder<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesSettings() {
            return this.batchDeleteEntityTypesSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationSettings() {
            return this.batchDeleteEntityTypesOperationSettings;
        }

        public UnaryCallSettings.Builder<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesSettings() {
            return this.batchCreateEntitiesSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationSettings() {
            return this.batchCreateEntitiesOperationSettings;
        }

        public UnaryCallSettings.Builder<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesSettings() {
            return this.batchUpdateEntitiesSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationSettings() {
            return this.batchUpdateEntitiesOperationSettings;
        }

        public UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesSettings() {
            return this.batchDeleteEntitiesSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationSettings() {
            return this.batchDeleteEntitiesOperationSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new EntityTypesStubSettings(this);
        }
    }
}
