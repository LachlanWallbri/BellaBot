package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.GetKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.KnowledgeBase;
import com.google.cloud.dialogflow.v2beta1.KnowledgeBasesClient;
import com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesRequest;
import com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequest;
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
public class GrpcKnowledgeBasesStub extends KnowledgeBasesStub {
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseCallable;
    private final UnaryCallable<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseCallable;
    private final UnaryCallable<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseCallable;
    private final UnaryCallable<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse> listKnowledgeBasesCallable;
    private final UnaryCallable<ListKnowledgeBasesRequest, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesPagedCallable;
    private final UnaryCallable<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseCallable;
    private static final MethodDescriptor<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse> listKnowledgeBasesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.KnowledgeBases/ListKnowledgeBases").setRequestMarshaller(ProtoUtils.marshaller(ListKnowledgeBasesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListKnowledgeBasesResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.KnowledgeBases/GetKnowledgeBase").setRequestMarshaller(ProtoUtils.marshaller(GetKnowledgeBaseRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(KnowledgeBase.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.KnowledgeBases/CreateKnowledgeBase").setRequestMarshaller(ProtoUtils.marshaller(CreateKnowledgeBaseRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(KnowledgeBase.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.KnowledgeBases/DeleteKnowledgeBase").setRequestMarshaller(ProtoUtils.marshaller(DeleteKnowledgeBaseRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.KnowledgeBases/UpdateKnowledgeBase").setRequestMarshaller(ProtoUtils.marshaller(UpdateKnowledgeBaseRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(KnowledgeBase.getDefaultInstance())).build();

    public static final GrpcKnowledgeBasesStub create(KnowledgeBasesStubSettings knowledgeBasesStubSettings) throws IOException {
        return new GrpcKnowledgeBasesStub(knowledgeBasesStubSettings, ClientContext.create(knowledgeBasesStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStubSettings] */
    public static final GrpcKnowledgeBasesStub create(ClientContext clientContext) throws IOException {
        return new GrpcKnowledgeBasesStub(KnowledgeBasesStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStubSettings] */
    public static final GrpcKnowledgeBasesStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcKnowledgeBasesStub(KnowledgeBasesStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcKnowledgeBasesStub(KnowledgeBasesStubSettings knowledgeBasesStubSettings, ClientContext clientContext) throws IOException {
        this(knowledgeBasesStubSettings, clientContext, new GrpcKnowledgeBasesCallableFactory());
    }

    protected GrpcKnowledgeBasesStub(KnowledgeBasesStubSettings knowledgeBasesStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(listKnowledgeBasesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ListKnowledgeBasesRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcKnowledgeBasesStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ListKnowledgeBasesRequest listKnowledgeBasesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(listKnowledgeBasesRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(getKnowledgeBaseMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetKnowledgeBaseRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcKnowledgeBasesStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetKnowledgeBaseRequest getKnowledgeBaseRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(getKnowledgeBaseRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(createKnowledgeBaseMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<CreateKnowledgeBaseRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcKnowledgeBasesStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(CreateKnowledgeBaseRequest createKnowledgeBaseRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(createKnowledgeBaseRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteKnowledgeBaseMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteKnowledgeBaseRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcKnowledgeBasesStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteKnowledgeBaseRequest deleteKnowledgeBaseRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(deleteKnowledgeBaseRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(updateKnowledgeBaseMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<UpdateKnowledgeBaseRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcKnowledgeBasesStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("knowledge_base.name", String.valueOf(updateKnowledgeBaseRequest.getKnowledgeBase().getName()));
                return builder.build();
            }
        }).build();
        this.listKnowledgeBasesCallable = grpcStubCallableFactory.createUnaryCallable(build, knowledgeBasesStubSettings.listKnowledgeBasesSettings(), clientContext);
        this.listKnowledgeBasesPagedCallable = grpcStubCallableFactory.createPagedCallable(build, knowledgeBasesStubSettings.listKnowledgeBasesSettings(), clientContext);
        this.getKnowledgeBaseCallable = grpcStubCallableFactory.createUnaryCallable(build2, knowledgeBasesStubSettings.getKnowledgeBaseSettings(), clientContext);
        this.createKnowledgeBaseCallable = grpcStubCallableFactory.createUnaryCallable(build3, knowledgeBasesStubSettings.createKnowledgeBaseSettings(), clientContext);
        this.deleteKnowledgeBaseCallable = grpcStubCallableFactory.createUnaryCallable(build4, knowledgeBasesStubSettings.deleteKnowledgeBaseSettings(), clientContext);
        this.updateKnowledgeBaseCallable = grpcStubCallableFactory.createUnaryCallable(build5, knowledgeBasesStubSettings.updateKnowledgeBaseSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub
    public UnaryCallable<ListKnowledgeBasesRequest, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesPagedCallable() {
        return this.listKnowledgeBasesPagedCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub
    public UnaryCallable<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse> listKnowledgeBasesCallable() {
        return this.listKnowledgeBasesCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub
    public UnaryCallable<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseCallable() {
        return this.getKnowledgeBaseCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub
    public UnaryCallable<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseCallable() {
        return this.createKnowledgeBaseCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub
    public UnaryCallable<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseCallable() {
        return this.deleteKnowledgeBaseCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub
    public UnaryCallable<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseCallable() {
        return this.updateKnowledgeBaseCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub, java.lang.AutoCloseable
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
