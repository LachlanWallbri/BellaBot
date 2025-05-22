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
import com.google.cloud.dialogflow.v2beta1.CreateSessionEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteSessionEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.GetSessionEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.ListSessionEntityTypesRequest;
import com.google.cloud.dialogflow.v2beta1.ListSessionEntityTypesResponse;
import com.google.cloud.dialogflow.v2beta1.SessionEntityType;
import com.google.cloud.dialogflow.v2beta1.SessionEntityTypesClient;
import com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequest;
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
public class SessionEntityTypesStubSettings extends StubSettings<SessionEntityTypesStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").add((ImmutableList.Builder) "https://www.googleapis.com/auth/dialogflow").build();
    private static final PagedListDescriptor<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType> LIST_SESSION_ENTITY_TYPES_PAGE_STR_DESC = new PagedListDescriptor<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListSessionEntityTypesRequest injectToken(ListSessionEntityTypesRequest listSessionEntityTypesRequest, String str) {
            return ListSessionEntityTypesRequest.newBuilder(listSessionEntityTypesRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListSessionEntityTypesRequest injectPageSize(ListSessionEntityTypesRequest listSessionEntityTypesRequest, int i) {
            return ListSessionEntityTypesRequest.newBuilder(listSessionEntityTypesRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(ListSessionEntityTypesRequest listSessionEntityTypesRequest) {
            return Integer.valueOf(listSessionEntityTypesRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(ListSessionEntityTypesResponse listSessionEntityTypesResponse) {
            return listSessionEntityTypesResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<SessionEntityType> extractResources(ListSessionEntityTypesResponse listSessionEntityTypesResponse) {
            if (listSessionEntityTypesResponse.getSessionEntityTypesList() != null) {
                return listSessionEntityTypesResponse.getSessionEntityTypesList();
            }
            return ImmutableList.m634of();
        }
    };
    private static final PagedListResponseFactory<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> LIST_SESSION_ENTITY_TYPES_PAGE_STR_FACT = new PagedListResponseFactory<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> getFuturePagedResponse(UnaryCallable<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse> unaryCallable, ListSessionEntityTypesRequest listSessionEntityTypesRequest, ApiCallContext apiCallContext, ApiFuture<ListSessionEntityTypesResponse> apiFuture) {
            return SessionEntityTypesClient.ListSessionEntityTypesPagedResponse.createAsync(PageContext.create(unaryCallable, SessionEntityTypesStubSettings.LIST_SESSION_ENTITY_TYPES_PAGE_STR_DESC, listSessionEntityTypesRequest, apiCallContext), apiFuture);
        }
    };
    private final UnaryCallSettings<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeSettings;
    private final UnaryCallSettings<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeSettings;
    private final UnaryCallSettings<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeSettings;
    private final PagedCallSettings<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesSettings;
    private final UnaryCallSettings<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeSettings;

    public static String getDefaultEndpoint() {
        return "dialogflow.googleapis.com:443";
    }

    public PagedCallSettings<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesSettings() {
        return this.listSessionEntityTypesSettings;
    }

    public UnaryCallSettings<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeSettings() {
        return this.getSessionEntityTypeSettings;
    }

    public UnaryCallSettings<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeSettings() {
        return this.createSessionEntityTypeSettings;
    }

    public UnaryCallSettings<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeSettings() {
        return this.updateSessionEntityTypeSettings;
    }

    public UnaryCallSettings<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeSettings() {
        return this.deleteSessionEntityTypeSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public SessionEntityTypesStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcSessionEntityTypesStub.create(this);
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
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(SessionEntityTypesStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected SessionEntityTypesStubSettings(Builder builder) throws IOException {
        super(builder);
        this.listSessionEntityTypesSettings = builder.listSessionEntityTypesSettings().build();
        this.getSessionEntityTypeSettings = builder.getSessionEntityTypeSettings().build();
        this.createSessionEntityTypeSettings = builder.createSessionEntityTypeSettings().build();
        this.updateSessionEntityTypeSettings = builder.updateSessionEntityTypeSettings().build();
        this.deleteSessionEntityTypeSettings = builder.deleteSessionEntityTypeSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<SessionEntityTypesStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final UnaryCallSettings.Builder<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeSettings;
        private final UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeSettings;
        private final UnaryCallSettings.Builder<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeSettings;
        private final PagedCallSettings.Builder<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeSettings;

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
            this.listSessionEntityTypesSettings = PagedCallSettings.newBuilder(SessionEntityTypesStubSettings.LIST_SESSION_ENTITY_TYPES_PAGE_STR_FACT);
            this.getSessionEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createSessionEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateSessionEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteSessionEntityTypeSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m639of((UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.listSessionEntityTypesSettings, (UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.getSessionEntityTypeSettings, (UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.createSessionEntityTypeSettings, (UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.updateSessionEntityTypeSettings, this.deleteSessionEntityTypeSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(SessionEntityTypesStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(SessionEntityTypesStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(SessionEntityTypesStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(SessionEntityTypesStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.listSessionEntityTypesSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getSessionEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createSessionEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateSessionEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteSessionEntityTypeSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(SessionEntityTypesStubSettings sessionEntityTypesStubSettings) {
            super(sessionEntityTypesStubSettings);
            this.listSessionEntityTypesSettings = sessionEntityTypesStubSettings.listSessionEntityTypesSettings.toBuilder();
            this.getSessionEntityTypeSettings = sessionEntityTypesStubSettings.getSessionEntityTypeSettings.toBuilder();
            this.createSessionEntityTypeSettings = sessionEntityTypesStubSettings.createSessionEntityTypeSettings.toBuilder();
            this.updateSessionEntityTypeSettings = sessionEntityTypesStubSettings.updateSessionEntityTypeSettings.toBuilder();
            this.deleteSessionEntityTypeSettings = sessionEntityTypesStubSettings.deleteSessionEntityTypeSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m639of((UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.listSessionEntityTypesSettings, (UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.getSessionEntityTypeSettings, (UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.createSessionEntityTypeSettings, (UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty>) this.updateSessionEntityTypeSettings, this.deleteSessionEntityTypeSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesSettings() {
            return this.listSessionEntityTypesSettings;
        }

        public UnaryCallSettings.Builder<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeSettings() {
            return this.getSessionEntityTypeSettings;
        }

        public UnaryCallSettings.Builder<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeSettings() {
            return this.createSessionEntityTypeSettings;
        }

        public UnaryCallSettings.Builder<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeSettings() {
            return this.updateSessionEntityTypeSettings;
        }

        public UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeSettings() {
            return this.deleteSessionEntityTypeSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new SessionEntityTypesStubSettings(this);
        }
    }
}
