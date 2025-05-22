package com.google.longrunning.stub;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.GrpcTransportChannel;
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
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.ListOperationsResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.longrunning.WaitOperationRequest;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class OperationsStubSettings extends StubSettings<OperationsStubSettings> {
    private static final PagedListDescriptor<ListOperationsRequest, ListOperationsResponse, Operation> LIST_OPERATIONS_PAGE_STR_DESC = new PagedListDescriptor<ListOperationsRequest, ListOperationsResponse, Operation>() { // from class: com.google.longrunning.stub.OperationsStubSettings.1
        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String emptyToken() {
            return "";
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListOperationsRequest injectToken(ListOperationsRequest listOperationsRequest, String str) {
            return ListOperationsRequest.newBuilder(listOperationsRequest).setPageToken(str).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public ListOperationsRequest injectPageSize(ListOperationsRequest listOperationsRequest, int i) {
            return ListOperationsRequest.newBuilder(listOperationsRequest).setPageSize(i).build();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Integer extractPageSize(ListOperationsRequest listOperationsRequest) {
            return Integer.valueOf(listOperationsRequest.getPageSize());
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public String extractNextToken(ListOperationsResponse listOperationsResponse) {
            return listOperationsResponse.getNextPageToken();
        }

        @Override // com.google.api.gax.rpc.PagedListDescriptor
        public Iterable<Operation> extractResources(ListOperationsResponse listOperationsResponse) {
            return listOperationsResponse.getOperationsList();
        }
    };
    private static final PagedListResponseFactory<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> LIST_OPERATIONS_PAGE_STR_FACT = new PagedListResponseFactory<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse>() { // from class: com.google.longrunning.stub.OperationsStubSettings.2
        @Override // com.google.api.gax.rpc.PagedListResponseFactory
        public ApiFuture<OperationsClient.ListOperationsPagedResponse> getFuturePagedResponse(UnaryCallable<ListOperationsRequest, ListOperationsResponse> unaryCallable, ListOperationsRequest listOperationsRequest, ApiCallContext apiCallContext, ApiFuture<ListOperationsResponse> apiFuture) {
            return OperationsClient.ListOperationsPagedResponse.createAsync(PageContext.create(unaryCallable, OperationsStubSettings.LIST_OPERATIONS_PAGE_STR_DESC, listOperationsRequest, apiCallContext), apiFuture);
        }
    };
    private final UnaryCallSettings<CancelOperationRequest, Empty> cancelOperationSettings;
    private final UnaryCallSettings<DeleteOperationRequest, Empty> deleteOperationSettings;
    private final UnaryCallSettings<GetOperationRequest, Operation> getOperationSettings;
    private final PagedCallSettings<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings;
    private final UnaryCallSettings<WaitOperationRequest, Operation> waitOperationSettings;

    public UnaryCallSettings<GetOperationRequest, Operation> getOperationSettings() {
        return this.getOperationSettings;
    }

    public PagedCallSettings<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
        return this.listOperationsSettings;
    }

    public UnaryCallSettings<CancelOperationRequest, Empty> cancelOperationSettings() {
        return this.cancelOperationSettings;
    }

    public UnaryCallSettings<DeleteOperationRequest, Empty> deleteOperationSettings() {
        return this.deleteOperationSettings;
    }

    public UnaryCallSettings<WaitOperationRequest, Operation> waitOperationSettings() {
        return this.waitOperationSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public OperationsStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcOperationsStub.create(this);
        }
        throw new UnsupportedOperationException("Transport not supported: " + getTransportChannelProvider().getTransportName());
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return InstantiatingExecutorProvider.newBuilder();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return GoogleCredentialsProvider.newBuilder();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(OperationsStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected OperationsStubSettings(Builder builder) throws IOException {
        super(builder);
        this.getOperationSettings = builder.getOperationSettings().build();
        this.listOperationsSettings = builder.listOperationsSettings().build();
        this.cancelOperationSettings = builder.cancelOperationSettings().build();
        this.deleteOperationSettings = builder.deleteOperationSettings().build();
        this.waitOperationSettings = builder.waitOperationSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<OperationsStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final UnaryCallSettings.Builder<CancelOperationRequest, Empty> cancelOperationSettings;
        private final UnaryCallSettings.Builder<DeleteOperationRequest, Empty> deleteOperationSettings;
        private final UnaryCallSettings.Builder<GetOperationRequest, Operation> getOperationSettings;
        private final PagedCallSettings.Builder<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<WaitOperationRequest, Operation> waitOperationSettings;

        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        static {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.put("idempotent", ImmutableSet.copyOf((Collection) Lists.newArrayList(StatusCode.Code.DEADLINE_EXCEEDED, StatusCode.Code.UNAVAILABLE)));
            builder.put("non_idempotent", ImmutableSet.copyOf((Collection) Lists.newArrayList()));
            RETRYABLE_CODE_DEFINITIONS = builder.build();
            ImmutableMap.Builder builder2 = ImmutableMap.builder();
            builder2.put("default", RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(100L)).setRetryDelayMultiplier(1.3d).setMaxRetryDelay(Duration.ofMillis(60000L)).setInitialRpcTimeout(Duration.ofMillis(90000L)).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ofMillis(90000L)).setTotalTimeout(Duration.ofMillis(600000L)).build());
            RETRY_PARAM_DEFINITIONS = builder2.build();
        }

        protected Builder() {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(clientContext);
            this.getOperationSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.listOperationsSettings = PagedCallSettings.newBuilder(OperationsStubSettings.LIST_OPERATIONS_PAGE_STR_FACT);
            this.cancelOperationSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteOperationSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.waitOperationSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m638of((UnaryCallSettings.Builder<DeleteOperationRequest, Empty>) this.getOperationSettings, (UnaryCallSettings.Builder<DeleteOperationRequest, Empty>) this.listOperationsSettings, (UnaryCallSettings.Builder<DeleteOperationRequest, Empty>) this.cancelOperationSettings, this.deleteOperationSettings);
            initDefaults(this);
        }

        private static Builder createDefault() {
            return initDefaults(new Builder((ClientContext) null));
        }

        private static Builder initDefaults(Builder builder) {
            builder.getOperationSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.listOperationsSettings().setRetryableCodes((Set<StatusCode.Code>) RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.cancelOperationSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteOperationSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.waitOperationSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(OperationsStubSettings operationsStubSettings) {
            super(operationsStubSettings);
            this.getOperationSettings = operationsStubSettings.getOperationSettings.toBuilder();
            this.listOperationsSettings = operationsStubSettings.listOperationsSettings.toBuilder();
            this.cancelOperationSettings = operationsStubSettings.cancelOperationSettings.toBuilder();
            this.deleteOperationSettings = operationsStubSettings.deleteOperationSettings.toBuilder();
            this.waitOperationSettings = operationsStubSettings.waitOperationSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m639of((UnaryCallSettings.Builder<WaitOperationRequest, Operation>) this.getOperationSettings, (UnaryCallSettings.Builder<WaitOperationRequest, Operation>) this.listOperationsSettings, (UnaryCallSettings.Builder<WaitOperationRequest, Operation>) this.cancelOperationSettings, (UnaryCallSettings.Builder<WaitOperationRequest, Operation>) this.deleteOperationSettings, this.waitOperationSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public UnaryCallSettings.Builder<GetOperationRequest, Operation> getOperationSettings() {
            return this.getOperationSettings;
        }

        public PagedCallSettings.Builder<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
            return this.listOperationsSettings;
        }

        public UnaryCallSettings.Builder<CancelOperationRequest, Empty> cancelOperationSettings() {
            return this.cancelOperationSettings;
        }

        public UnaryCallSettings.Builder<DeleteOperationRequest, Empty> deleteOperationSettings() {
            return this.deleteOperationSettings;
        }

        public UnaryCallSettings.Builder<WaitOperationRequest, Operation> waitOperationSettings() {
            return this.waitOperationSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new OperationsStubSettings(this);
        }
    }
}
