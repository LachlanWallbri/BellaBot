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
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.PageContext;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.PagedListDescriptor;
import com.google.api.gax.rpc.PagedListResponseFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.GetKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.KnowledgeBase;
import com.google.cloud.dialogflow.v2beta1.KnowledgeBasesClient;
import com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesRequest;
import com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequest;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
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
public class KnowledgeBasesStubSettings extends StubSettings<KnowledgeBasesStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").add((ImmutableList.Builder) "https://www.googleapis.com/auth/dialogflow").build();
    private static final PagedListDescriptor<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase> LIST_KNOWLEDGE_BASES_PAGE_STR_DESC = new PagedListDescriptor<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListKnowledgeBasesRequest injectToken(ListKnowledgeBasesRequest listKnowledgeBasesRequest, String str) {
            return ListKnowledgeBasesRequest.newBuilder(listKnowledgeBasesRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListKnowledgeBasesRequest injectPageSize(ListKnowledgeBasesRequest listKnowledgeBasesRequest, int i) {
            return ListKnowledgeBasesRequest.newBuilder(listKnowledgeBasesRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(ListKnowledgeBasesRequest listKnowledgeBasesRequest) {
            return Integer.valueOf(listKnowledgeBasesRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(ListKnowledgeBasesResponse listKnowledgeBasesResponse) {
            return listKnowledgeBasesResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<KnowledgeBase> extractResources(ListKnowledgeBasesResponse listKnowledgeBasesResponse) {
            if (listKnowledgeBasesResponse.getKnowledgeBasesList() != null) {
                return listKnowledgeBasesResponse.getKnowledgeBasesList();
            }
            return ImmutableList.m634of();
        }
    };
    private static final PagedListResponseFactory<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> LIST_KNOWLEDGE_BASES_PAGE_STR_FACT = new PagedListResponseFactory<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> getFuturePagedResponse(UnaryCallable<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse> unaryCallable, ListKnowledgeBasesRequest listKnowledgeBasesRequest, ApiCallContext apiCallContext, ApiFuture<ListKnowledgeBasesResponse> apiFuture) {
            return KnowledgeBasesClient.ListKnowledgeBasesPagedResponse.createAsync(PageContext.create(unaryCallable, KnowledgeBasesStubSettings.LIST_KNOWLEDGE_BASES_PAGE_STR_DESC, listKnowledgeBasesRequest, apiCallContext), apiFuture);
        }
    };
    private final UnaryCallSettings<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseSettings;
    private final UnaryCallSettings<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseSettings;
    private final UnaryCallSettings<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseSettings;
    private final PagedCallSettings<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesSettings;
    private final UnaryCallSettings<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseSettings;

    public static String getDefaultEndpoint() {
        return "dialogflow.googleapis.com:443";
    }

    public PagedCallSettings<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesSettings() {
        return this.listKnowledgeBasesSettings;
    }

    public UnaryCallSettings<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseSettings() {
        return this.getKnowledgeBaseSettings;
    }

    public UnaryCallSettings<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseSettings() {
        return this.createKnowledgeBaseSettings;
    }

    public UnaryCallSettings<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseSettings() {
        return this.deleteKnowledgeBaseSettings;
    }

    public UnaryCallSettings<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseSettings() {
        return this.updateKnowledgeBaseSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public KnowledgeBasesStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcKnowledgeBasesStub.create(this);
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
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(KnowledgeBasesStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected KnowledgeBasesStubSettings(Builder builder) throws IOException {
        super(builder);
        this.listKnowledgeBasesSettings = builder.listKnowledgeBasesSettings().build();
        this.getKnowledgeBaseSettings = builder.getKnowledgeBaseSettings().build();
        this.createKnowledgeBaseSettings = builder.createKnowledgeBaseSettings().build();
        this.deleteKnowledgeBaseSettings = builder.deleteKnowledgeBaseSettings().build();
        this.updateKnowledgeBaseSettings = builder.updateKnowledgeBaseSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<KnowledgeBasesStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final UnaryCallSettings.Builder<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseSettings;
        private final UnaryCallSettings.Builder<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseSettings;
        private final UnaryCallSettings.Builder<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseSettings;
        private final PagedCallSettings.Builder<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseSettings;

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
            this.listKnowledgeBasesSettings = PagedCallSettings.newBuilder(KnowledgeBasesStubSettings.LIST_KNOWLEDGE_BASES_PAGE_STR_FACT);
            this.getKnowledgeBaseSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createKnowledgeBaseSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteKnowledgeBaseSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateKnowledgeBaseSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m639of((UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.listKnowledgeBasesSettings, (UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.getKnowledgeBaseSettings, (UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.createKnowledgeBaseSettings, (UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.deleteKnowledgeBaseSettings, this.updateKnowledgeBaseSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(KnowledgeBasesStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(KnowledgeBasesStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(KnowledgeBasesStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(KnowledgeBasesStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.listKnowledgeBasesSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getKnowledgeBaseSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createKnowledgeBaseSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteKnowledgeBaseSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateKnowledgeBaseSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(KnowledgeBasesStubSettings knowledgeBasesStubSettings) {
            super(knowledgeBasesStubSettings);
            this.listKnowledgeBasesSettings = knowledgeBasesStubSettings.listKnowledgeBasesSettings.toBuilder();
            this.getKnowledgeBaseSettings = knowledgeBasesStubSettings.getKnowledgeBaseSettings.toBuilder();
            this.createKnowledgeBaseSettings = knowledgeBasesStubSettings.createKnowledgeBaseSettings.toBuilder();
            this.deleteKnowledgeBaseSettings = knowledgeBasesStubSettings.deleteKnowledgeBaseSettings.toBuilder();
            this.updateKnowledgeBaseSettings = knowledgeBasesStubSettings.updateKnowledgeBaseSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m639of((UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.listKnowledgeBasesSettings, (UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.getKnowledgeBaseSettings, (UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.createKnowledgeBaseSettings, (UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase>) this.deleteKnowledgeBaseSettings, this.updateKnowledgeBaseSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesSettings() {
            return this.listKnowledgeBasesSettings;
        }

        public UnaryCallSettings.Builder<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseSettings() {
            return this.getKnowledgeBaseSettings;
        }

        public UnaryCallSettings.Builder<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseSettings() {
            return this.createKnowledgeBaseSettings;
        }

        public UnaryCallSettings.Builder<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseSettings() {
            return this.deleteKnowledgeBaseSettings;
        }

        public UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseSettings() {
            return this.updateKnowledgeBaseSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new KnowledgeBasesStubSettings(this);
        }
    }
}
