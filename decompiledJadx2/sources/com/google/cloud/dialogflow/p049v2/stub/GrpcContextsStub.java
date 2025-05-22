package com.google.cloud.dialogflow.p049v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.p049v2.Context;
import com.google.cloud.dialogflow.p049v2.ContextsClient;
import com.google.cloud.dialogflow.p049v2.CreateContextRequest;
import com.google.cloud.dialogflow.p049v2.DeleteAllContextsRequest;
import com.google.cloud.dialogflow.p049v2.DeleteContextRequest;
import com.google.cloud.dialogflow.p049v2.GetContextRequest;
import com.google.cloud.dialogflow.p049v2.ListContextsRequest;
import com.google.cloud.dialogflow.p049v2.ListContextsResponse;
import com.google.cloud.dialogflow.p049v2.UpdateContextRequest;
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
/* loaded from: classes2.dex */
public class GrpcContextsStub extends ContextsStub {
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<CreateContextRequest, Context> createContextCallable;
    private final UnaryCallable<DeleteAllContextsRequest, Empty> deleteAllContextsCallable;
    private final UnaryCallable<DeleteContextRequest, Empty> deleteContextCallable;
    private final UnaryCallable<GetContextRequest, Context> getContextCallable;
    private final UnaryCallable<ListContextsRequest, ListContextsResponse> listContextsCallable;
    private final UnaryCallable<ListContextsRequest, ContextsClient.ListContextsPagedResponse> listContextsPagedCallable;
    private final UnaryCallable<UpdateContextRequest, Context> updateContextCallable;
    private static final MethodDescriptor<ListContextsRequest, ListContextsResponse> listContextsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.Contexts/ListContexts").setRequestMarshaller(ProtoUtils.marshaller(ListContextsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListContextsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetContextRequest, Context> getContextMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.Contexts/GetContext").setRequestMarshaller(ProtoUtils.marshaller(GetContextRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Context.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateContextRequest, Context> createContextMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.Contexts/CreateContext").setRequestMarshaller(ProtoUtils.marshaller(CreateContextRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Context.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateContextRequest, Context> updateContextMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.Contexts/UpdateContext").setRequestMarshaller(ProtoUtils.marshaller(UpdateContextRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Context.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteContextRequest, Empty> deleteContextMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.Contexts/DeleteContext").setRequestMarshaller(ProtoUtils.marshaller(DeleteContextRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteAllContextsRequest, Empty> deleteAllContextsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.Contexts/DeleteAllContexts").setRequestMarshaller(ProtoUtils.marshaller(DeleteAllContextsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();

    public static final GrpcContextsStub create(ContextsStubSettings contextsStubSettings) throws IOException {
        return new GrpcContextsStub(contextsStubSettings, ClientContext.create(contextsStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2.stub.ContextsStubSettings] */
    public static final GrpcContextsStub create(ClientContext clientContext) throws IOException {
        return new GrpcContextsStub(ContextsStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2.stub.ContextsStubSettings] */
    public static final GrpcContextsStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcContextsStub(ContextsStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcContextsStub(ContextsStubSettings contextsStubSettings, ClientContext clientContext) throws IOException {
        this(contextsStubSettings, clientContext, new GrpcContextsCallableFactory());
    }

    protected GrpcContextsStub(ContextsStubSettings contextsStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(listContextsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ListContextsRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcContextsStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ListContextsRequest listContextsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(listContextsRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(getContextMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetContextRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcContextsStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetContextRequest getContextRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(getContextRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(createContextMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<CreateContextRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcContextsStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(CreateContextRequest createContextRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(createContextRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(updateContextMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<UpdateContextRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcContextsStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(UpdateContextRequest updateContextRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("context.name", String.valueOf(updateContextRequest.getContext().getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteContextMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteContextRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcContextsStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteContextRequest deleteContextRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(deleteContextRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build6 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteAllContextsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteAllContextsRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcContextsStub.6
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteAllContextsRequest deleteAllContextsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(deleteAllContextsRequest.getParent()));
                return builder.build();
            }
        }).build();
        this.listContextsCallable = grpcStubCallableFactory.createUnaryCallable(build, contextsStubSettings.listContextsSettings(), clientContext);
        this.listContextsPagedCallable = grpcStubCallableFactory.createPagedCallable(build, contextsStubSettings.listContextsSettings(), clientContext);
        this.getContextCallable = grpcStubCallableFactory.createUnaryCallable(build2, contextsStubSettings.getContextSettings(), clientContext);
        this.createContextCallable = grpcStubCallableFactory.createUnaryCallable(build3, contextsStubSettings.createContextSettings(), clientContext);
        this.updateContextCallable = grpcStubCallableFactory.createUnaryCallable(build4, contextsStubSettings.updateContextSettings(), clientContext);
        this.deleteContextCallable = grpcStubCallableFactory.createUnaryCallable(build5, contextsStubSettings.deleteContextSettings(), clientContext);
        this.deleteAllContextsCallable = grpcStubCallableFactory.createUnaryCallable(build6, contextsStubSettings.deleteAllContextsSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub
    public UnaryCallable<ListContextsRequest, ContextsClient.ListContextsPagedResponse> listContextsPagedCallable() {
        return this.listContextsPagedCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub
    public UnaryCallable<ListContextsRequest, ListContextsResponse> listContextsCallable() {
        return this.listContextsCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub
    public UnaryCallable<GetContextRequest, Context> getContextCallable() {
        return this.getContextCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub
    public UnaryCallable<CreateContextRequest, Context> createContextCallable() {
        return this.createContextCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub
    public UnaryCallable<UpdateContextRequest, Context> updateContextCallable() {
        return this.updateContextCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub
    public UnaryCallable<DeleteContextRequest, Empty> deleteContextCallable() {
        return this.deleteContextCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub
    public UnaryCallable<DeleteAllContextsRequest, Empty> deleteAllContextsCallable() {
        return this.deleteAllContextsCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.ContextsStub, java.lang.AutoCloseable
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
