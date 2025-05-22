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
import com.google.cloud.dialogflow.v2beta1.CreateDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.Document;
import com.google.cloud.dialogflow.v2beta1.DocumentsClient;
import com.google.cloud.dialogflow.v2beta1.GetDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadata;
import com.google.cloud.dialogflow.v2beta1.ListDocumentsRequest;
import com.google.cloud.dialogflow.v2beta1.ListDocumentsResponse;
import com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequest;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class DocumentsStubSettings extends StubSettings<DocumentsStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").add((ImmutableList.Builder) "https://www.googleapis.com/auth/dialogflow").build();
    private static final PagedListDescriptor<ListDocumentsRequest, ListDocumentsResponse, Document> LIST_DOCUMENTS_PAGE_STR_DESC = new PagedListDescriptor<ListDocumentsRequest, ListDocumentsResponse, Document>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.DocumentsStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListDocumentsRequest injectToken(ListDocumentsRequest listDocumentsRequest, String str) {
            return ListDocumentsRequest.newBuilder(listDocumentsRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListDocumentsRequest injectPageSize(ListDocumentsRequest listDocumentsRequest, int i) {
            return ListDocumentsRequest.newBuilder(listDocumentsRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(ListDocumentsRequest listDocumentsRequest) {
            return Integer.valueOf(listDocumentsRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(ListDocumentsResponse listDocumentsResponse) {
            return listDocumentsResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<Document> extractResources(ListDocumentsResponse listDocumentsResponse) {
            if (listDocumentsResponse.getDocumentsList() != null) {
                return listDocumentsResponse.getDocumentsList();
            }
            return ImmutableList.m634of();
        }
    };
    private static final PagedListResponseFactory<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse> LIST_DOCUMENTS_PAGE_STR_FACT = new PagedListResponseFactory<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.DocumentsStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<DocumentsClient.ListDocumentsPagedResponse> getFuturePagedResponse(UnaryCallable<ListDocumentsRequest, ListDocumentsResponse> unaryCallable, ListDocumentsRequest listDocumentsRequest, ApiCallContext apiCallContext, ApiFuture<ListDocumentsResponse> apiFuture) {
            return DocumentsClient.ListDocumentsPagedResponse.createAsync(PageContext.create(unaryCallable, DocumentsStubSettings.LIST_DOCUMENTS_PAGE_STR_DESC, listDocumentsRequest, apiCallContext), apiFuture);
        }
    };
    private final OperationCallSettings<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationSettings;
    private final UnaryCallSettings<CreateDocumentRequest, Operation> createDocumentSettings;
    private final OperationCallSettings<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationSettings;
    private final UnaryCallSettings<DeleteDocumentRequest, Operation> deleteDocumentSettings;
    private final UnaryCallSettings<GetDocumentRequest, Document> getDocumentSettings;
    private final PagedCallSettings<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse> listDocumentsSettings;
    private final UnaryCallSettings<ReloadDocumentRequest, Operation> reloadDocumentSettings;
    private final OperationCallSettings<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationSettings;
    private final UnaryCallSettings<UpdateDocumentRequest, Operation> updateDocumentSettings;

    public static String getDefaultEndpoint() {
        return "dialogflow.googleapis.com:443";
    }

    public PagedCallSettings<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse> listDocumentsSettings() {
        return this.listDocumentsSettings;
    }

    public UnaryCallSettings<GetDocumentRequest, Document> getDocumentSettings() {
        return this.getDocumentSettings;
    }

    public UnaryCallSettings<CreateDocumentRequest, Operation> createDocumentSettings() {
        return this.createDocumentSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationSettings() {
        return this.createDocumentOperationSettings;
    }

    public UnaryCallSettings<DeleteDocumentRequest, Operation> deleteDocumentSettings() {
        return this.deleteDocumentSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationSettings() {
        return this.deleteDocumentOperationSettings;
    }

    public UnaryCallSettings<UpdateDocumentRequest, Operation> updateDocumentSettings() {
        return this.updateDocumentSettings;
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallSettings<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationSettings() {
        return this.updateDocumentOperationSettings;
    }

    public UnaryCallSettings<ReloadDocumentRequest, Operation> reloadDocumentSettings() {
        return this.reloadDocumentSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public DocumentsStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcDocumentsStub.create(this);
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
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(DocumentsStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected DocumentsStubSettings(Builder builder) throws IOException {
        super(builder);
        this.listDocumentsSettings = builder.listDocumentsSettings().build();
        this.getDocumentSettings = builder.getDocumentSettings().build();
        this.createDocumentSettings = builder.createDocumentSettings().build();
        this.createDocumentOperationSettings = builder.createDocumentOperationSettings().build();
        this.deleteDocumentSettings = builder.deleteDocumentSettings().build();
        this.deleteDocumentOperationSettings = builder.deleteDocumentOperationSettings().build();
        this.updateDocumentSettings = builder.updateDocumentSettings().build();
        this.updateDocumentOperationSettings = builder.updateDocumentOperationSettings().build();
        this.reloadDocumentSettings = builder.reloadDocumentSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<DocumentsStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final OperationCallSettings.Builder<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationSettings;
        private final UnaryCallSettings.Builder<CreateDocumentRequest, Operation> createDocumentSettings;
        private final OperationCallSettings.Builder<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationSettings;
        private final UnaryCallSettings.Builder<DeleteDocumentRequest, Operation> deleteDocumentSettings;
        private final UnaryCallSettings.Builder<GetDocumentRequest, Document> getDocumentSettings;
        private final PagedCallSettings.Builder<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse> listDocumentsSettings;
        private final UnaryCallSettings.Builder<ReloadDocumentRequest, Operation> reloadDocumentSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final OperationCallSettings.Builder<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationSettings;
        private final UnaryCallSettings.Builder<UpdateDocumentRequest, Operation> updateDocumentSettings;

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
            this.listDocumentsSettings = PagedCallSettings.newBuilder(DocumentsStubSettings.LIST_DOCUMENTS_PAGE_STR_FACT);
            this.getDocumentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createDocumentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createDocumentOperationSettings = OperationCallSettings.newBuilder();
            this.deleteDocumentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteDocumentOperationSettings = OperationCallSettings.newBuilder();
            this.updateDocumentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateDocumentOperationSettings = OperationCallSettings.newBuilder();
            this.reloadDocumentSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m640of((UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.listDocumentsSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.getDocumentSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.createDocumentSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.deleteDocumentSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.updateDocumentSettings, this.reloadDocumentSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(DocumentsStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(DocumentsStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(DocumentsStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(DocumentsStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.listDocumentsSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getDocumentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createDocumentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteDocumentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateDocumentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.reloadDocumentSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createDocumentOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Document.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(KnowledgeOperationMetadata.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.deleteDocumentOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Empty.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(KnowledgeOperationMetadata.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            builder.updateDocumentOperationSettings().setInitialCallSettings(UnaryCallSettings.newUnaryCallSettingsBuilder().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default")).build()).setResponseTransformer(ProtoOperationTransformers.ResponseTransformer.create(Document.class)).setMetadataTransformer(ProtoOperationTransformers.MetadataTransformer.create(KnowledgeOperationMetadata.class)).setPollingAlgorithm(OperationTimedPollAlgorithm.create(RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(500L)).setRetryDelayMultiplier(1.5d).setMaxRetryDelay(Duration.ofMillis(5000L)).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setTotalTimeout(Duration.ofMillis(300000L)).build()));
            return builder;
        }

        protected Builder(DocumentsStubSettings documentsStubSettings) {
            super(documentsStubSettings);
            this.listDocumentsSettings = documentsStubSettings.listDocumentsSettings.toBuilder();
            this.getDocumentSettings = documentsStubSettings.getDocumentSettings.toBuilder();
            this.createDocumentSettings = documentsStubSettings.createDocumentSettings.toBuilder();
            this.createDocumentOperationSettings = documentsStubSettings.createDocumentOperationSettings.toBuilder();
            this.deleteDocumentSettings = documentsStubSettings.deleteDocumentSettings.toBuilder();
            this.deleteDocumentOperationSettings = documentsStubSettings.deleteDocumentOperationSettings.toBuilder();
            this.updateDocumentSettings = documentsStubSettings.updateDocumentSettings.toBuilder();
            this.updateDocumentOperationSettings = documentsStubSettings.updateDocumentOperationSettings.toBuilder();
            this.reloadDocumentSettings = documentsStubSettings.reloadDocumentSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m640of((UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.listDocumentsSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.getDocumentSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.createDocumentSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.deleteDocumentSettings, (UnaryCallSettings.Builder<ReloadDocumentRequest, Operation>) this.updateDocumentSettings, this.reloadDocumentSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse> listDocumentsSettings() {
            return this.listDocumentsSettings;
        }

        public UnaryCallSettings.Builder<GetDocumentRequest, Document> getDocumentSettings() {
            return this.getDocumentSettings;
        }

        public UnaryCallSettings.Builder<CreateDocumentRequest, Operation> createDocumentSettings() {
            return this.createDocumentSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationSettings() {
            return this.createDocumentOperationSettings;
        }

        public UnaryCallSettings.Builder<DeleteDocumentRequest, Operation> deleteDocumentSettings() {
            return this.deleteDocumentSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationSettings() {
            return this.deleteDocumentOperationSettings;
        }

        public UnaryCallSettings.Builder<UpdateDocumentRequest, Operation> updateDocumentSettings() {
            return this.updateDocumentSettings;
        }

        @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationSettings() {
            return this.updateDocumentOperationSettings;
        }

        public UnaryCallSettings.Builder<ReloadDocumentRequest, Operation> reloadDocumentSettings() {
            return this.reloadDocumentSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new DocumentsStubSettings(this);
        }
    }
}
