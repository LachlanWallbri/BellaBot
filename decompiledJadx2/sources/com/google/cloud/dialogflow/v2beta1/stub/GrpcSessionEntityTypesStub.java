package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.CreateSessionEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteSessionEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.GetSessionEntityTypeRequest;
import com.google.cloud.dialogflow.v2beta1.ListSessionEntityTypesRequest;
import com.google.cloud.dialogflow.v2beta1.ListSessionEntityTypesResponse;
import com.google.cloud.dialogflow.v2beta1.SessionEntityType;
import com.google.cloud.dialogflow.v2beta1.SessionEntityTypesClient;
import com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequest;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.Empty;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public class GrpcSessionEntityTypesStub extends SessionEntityTypesStub {
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeCallable;
    private final UnaryCallable<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeCallable;
    private final UnaryCallable<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeCallable;
    private final UnaryCallable<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse> listSessionEntityTypesCallable;
    private final UnaryCallable<ListSessionEntityTypesRequest, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesPagedCallable;
    private final UnaryCallable<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeCallable;
    private static final MethodDescriptor<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse> listSessionEntityTypesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.SessionEntityTypes/ListSessionEntityTypes").setRequestMarshaller(ProtoUtils.marshaller(ListSessionEntityTypesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListSessionEntityTypesResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.SessionEntityTypes/GetSessionEntityType").setRequestMarshaller(ProtoUtils.marshaller(GetSessionEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(SessionEntityType.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.SessionEntityTypes/CreateSessionEntityType").setRequestMarshaller(ProtoUtils.marshaller(CreateSessionEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(SessionEntityType.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.SessionEntityTypes/UpdateSessionEntityType").setRequestMarshaller(ProtoUtils.marshaller(UpdateSessionEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(SessionEntityType.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.SessionEntityTypes/DeleteSessionEntityType").setRequestMarshaller(ProtoUtils.marshaller(DeleteSessionEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();

    public static final GrpcSessionEntityTypesStub create(SessionEntityTypesStubSettings sessionEntityTypesStubSettings) throws IOException {
        return new GrpcSessionEntityTypesStub(sessionEntityTypesStubSettings, ClientContext.create(sessionEntityTypesStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStubSettings] */
    public static final GrpcSessionEntityTypesStub create(ClientContext clientContext) throws IOException {
        return new GrpcSessionEntityTypesStub(SessionEntityTypesStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStubSettings] */
    public static final GrpcSessionEntityTypesStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcSessionEntityTypesStub(SessionEntityTypesStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcSessionEntityTypesStub(SessionEntityTypesStubSettings sessionEntityTypesStubSettings, ClientContext clientContext) throws IOException {
        this(sessionEntityTypesStubSettings, clientContext, new GrpcSessionEntityTypesCallableFactory());
    }

    protected GrpcSessionEntityTypesStub(SessionEntityTypesStubSettings sessionEntityTypesStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(listSessionEntityTypesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ListSessionEntityTypesRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcSessionEntityTypesStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ListSessionEntityTypesRequest listSessionEntityTypesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(listSessionEntityTypesRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(getSessionEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetSessionEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcSessionEntityTypesStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetSessionEntityTypeRequest getSessionEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(getSessionEntityTypeRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(createSessionEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<CreateSessionEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcSessionEntityTypesStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(CreateSessionEntityTypeRequest createSessionEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(createSessionEntityTypeRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(updateSessionEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<UpdateSessionEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcSessionEntityTypesStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("session_entity_type.name", String.valueOf(updateSessionEntityTypeRequest.getSessionEntityType().getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteSessionEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteSessionEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcSessionEntityTypesStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteSessionEntityTypeRequest deleteSessionEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(deleteSessionEntityTypeRequest.getName()));
                return builder.build();
            }
        }).build();
        this.listSessionEntityTypesCallable = grpcStubCallableFactory.createUnaryCallable(build, sessionEntityTypesStubSettings.listSessionEntityTypesSettings(), clientContext);
        this.listSessionEntityTypesPagedCallable = grpcStubCallableFactory.createPagedCallable(build, sessionEntityTypesStubSettings.listSessionEntityTypesSettings(), clientContext);
        this.getSessionEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build2, sessionEntityTypesStubSettings.getSessionEntityTypeSettings(), clientContext);
        this.createSessionEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build3, sessionEntityTypesStubSettings.createSessionEntityTypeSettings(), clientContext);
        this.updateSessionEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build4, sessionEntityTypesStubSettings.updateSessionEntityTypeSettings(), clientContext);
        this.deleteSessionEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build5, sessionEntityTypesStubSettings.deleteSessionEntityTypeSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub
    public UnaryCallable<ListSessionEntityTypesRequest, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesPagedCallable() {
        return this.listSessionEntityTypesPagedCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub
    public UnaryCallable<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse> listSessionEntityTypesCallable() {
        return this.listSessionEntityTypesCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub
    public UnaryCallable<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeCallable() {
        return this.getSessionEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub
    public UnaryCallable<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeCallable() {
        return this.createSessionEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub
    public UnaryCallable<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeCallable() {
        return this.updateSessionEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub
    public UnaryCallable<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeCallable() {
        return this.deleteSessionEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub, java.lang.AutoCloseable
    public final void close() {
        shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        this.backgroundResources.shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return this.backgroundResources.isShutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return this.backgroundResources.isTerminated();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        this.backgroundResources.shutdownNow();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.backgroundResources.awaitTermination(j, timeUnit);
    }
}
