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
import com.google.cloud.dialogflow.v2beta1.Context;
import com.google.cloud.dialogflow.v2beta1.ContextsClient;
import com.google.cloud.dialogflow.v2beta1.CreateContextRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteAllContextsRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteContextRequest;
import com.google.cloud.dialogflow.v2beta1.GetContextRequest;
import com.google.cloud.dialogflow.v2beta1.ListContextsRequest;
import com.google.cloud.dialogflow.v2beta1.ListContextsResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateContextRequest;
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
public class ContextsStubSettings extends StubSettings<ContextsStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").add((ImmutableList.Builder) "https://www.googleapis.com/auth/dialogflow").build();
    private static final PagedListDescriptor<ListContextsRequest, ListContextsResponse, Context> LIST_CONTEXTS_PAGE_STR_DESC = new PagedListDescriptor<ListContextsRequest, ListContextsResponse, Context>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.ContextsStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListContextsRequest injectToken(ListContextsRequest listContextsRequest, String str) {
            return ListContextsRequest.newBuilder(listContextsRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListContextsRequest injectPageSize(ListContextsRequest listContextsRequest, int i) {
            return ListContextsRequest.newBuilder(listContextsRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(ListContextsRequest listContextsRequest) {
            return Integer.valueOf(listContextsRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(ListContextsResponse listContextsResponse) {
            return listContextsResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<Context> extractResources(ListContextsResponse listContextsResponse) {
            if (listContextsResponse.getContextsList() != null) {
                return listContextsResponse.getContextsList();
            }
            return ImmutableList.m634of();
        }
    };
    private static final PagedListResponseFactory<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse> LIST_CONTEXTS_PAGE_STR_FACT = new PagedListResponseFactory<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.ContextsStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<ContextsClient.ListContextsPagedResponse> getFuturePagedResponse(UnaryCallable<ListContextsRequest, ListContextsResponse> unaryCallable, ListContextsRequest listContextsRequest, ApiCallContext apiCallContext, ApiFuture<ListContextsResponse> apiFuture) {
            return ContextsClient.ListContextsPagedResponse.createAsync(PageContext.create(unaryCallable, ContextsStubSettings.LIST_CONTEXTS_PAGE_STR_DESC, listContextsRequest, apiCallContext), apiFuture);
        }
    };
    private final UnaryCallSettings<CreateContextRequest, Context> createContextSettings;
    private final UnaryCallSettings<DeleteAllContextsRequest, Empty> deleteAllContextsSettings;
    private final UnaryCallSettings<DeleteContextRequest, Empty> deleteContextSettings;
    private final UnaryCallSettings<GetContextRequest, Context> getContextSettings;
    private final PagedCallSettings<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse> listContextsSettings;
    private final UnaryCallSettings<UpdateContextRequest, Context> updateContextSettings;

    public static String getDefaultEndpoint() {
        return "dialogflow.googleapis.com:443";
    }

    public PagedCallSettings<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse> listContextsSettings() {
        return this.listContextsSettings;
    }

    public UnaryCallSettings<GetContextRequest, Context> getContextSettings() {
        return this.getContextSettings;
    }

    public UnaryCallSettings<CreateContextRequest, Context> createContextSettings() {
        return this.createContextSettings;
    }

    public UnaryCallSettings<UpdateContextRequest, Context> updateContextSettings() {
        return this.updateContextSettings;
    }

    public UnaryCallSettings<DeleteContextRequest, Empty> deleteContextSettings() {
        return this.deleteContextSettings;
    }

    public UnaryCallSettings<DeleteAllContextsRequest, Empty> deleteAllContextsSettings() {
        return this.deleteAllContextsSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public ContextsStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcContextsStub.create(this);
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
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(ContextsStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected ContextsStubSettings(Builder builder) throws IOException {
        super(builder);
        this.listContextsSettings = builder.listContextsSettings().build();
        this.getContextSettings = builder.getContextSettings().build();
        this.createContextSettings = builder.createContextSettings().build();
        this.updateContextSettings = builder.updateContextSettings().build();
        this.deleteContextSettings = builder.deleteContextSettings().build();
        this.deleteAllContextsSettings = builder.deleteAllContextsSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<ContextsStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final UnaryCallSettings.Builder<CreateContextRequest, Context> createContextSettings;
        private final UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty> deleteAllContextsSettings;
        private final UnaryCallSettings.Builder<DeleteContextRequest, Empty> deleteContextSettings;
        private final UnaryCallSettings.Builder<GetContextRequest, Context> getContextSettings;
        private final PagedCallSettings.Builder<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse> listContextsSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<UpdateContextRequest, Context> updateContextSettings;

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
            this.listContextsSettings = PagedCallSettings.newBuilder(ContextsStubSettings.LIST_CONTEXTS_PAGE_STR_FACT);
            this.getContextSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createContextSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateContextSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteContextSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteAllContextsSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m640of((UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.listContextsSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.getContextSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.createContextSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.updateContextSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.deleteContextSettings, this.deleteAllContextsSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(ContextsStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(ContextsStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(ContextsStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(ContextsStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.listContextsSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getContextSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createContextSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateContextSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteContextSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteAllContextsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(ContextsStubSettings contextsStubSettings) {
            super(contextsStubSettings);
            this.listContextsSettings = contextsStubSettings.listContextsSettings.toBuilder();
            this.getContextSettings = contextsStubSettings.getContextSettings.toBuilder();
            this.createContextSettings = contextsStubSettings.createContextSettings.toBuilder();
            this.updateContextSettings = contextsStubSettings.updateContextSettings.toBuilder();
            this.deleteContextSettings = contextsStubSettings.deleteContextSettings.toBuilder();
            this.deleteAllContextsSettings = contextsStubSettings.deleteAllContextsSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m640of((UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.listContextsSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.getContextSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.createContextSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.updateContextSettings, (UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty>) this.deleteContextSettings, this.deleteAllContextsSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse> listContextsSettings() {
            return this.listContextsSettings;
        }

        public UnaryCallSettings.Builder<GetContextRequest, Context> getContextSettings() {
            return this.getContextSettings;
        }

        public UnaryCallSettings.Builder<CreateContextRequest, Context> createContextSettings() {
            return this.createContextSettings;
        }

        public UnaryCallSettings.Builder<UpdateContextRequest, Context> updateContextSettings() {
            return this.updateContextSettings;
        }

        public UnaryCallSettings.Builder<DeleteContextRequest, Empty> deleteContextSettings() {
            return this.deleteContextSettings;
        }

        public UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty> deleteAllContextsSettings() {
            return this.deleteAllContextsSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new ContextsStubSettings(this);
        }
    }
}
