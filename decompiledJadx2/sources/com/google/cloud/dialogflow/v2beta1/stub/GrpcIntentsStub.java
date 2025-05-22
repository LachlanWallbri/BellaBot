package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.RequestParamsExtractor;
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
import com.google.common.collect.ImmutableMap;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.GrpcOperationsStub;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public class GrpcIntentsStub extends IntentsStub {
    private final BackgroundResource backgroundResources;
    private final UnaryCallable<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsCallable;
    private final OperationCallable<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationCallable;
    private final UnaryCallable<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsCallable;
    private final OperationCallable<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationCallable;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<CreateIntentRequest, Intent> createIntentCallable;
    private final UnaryCallable<DeleteIntentRequest, Empty> deleteIntentCallable;
    private final UnaryCallable<GetIntentRequest, Intent> getIntentCallable;
    private final UnaryCallable<ListIntentsRequest, ListIntentsResponse> listIntentsCallable;
    private final UnaryCallable<ListIntentsRequest, IntentsClient.ListIntentsPagedResponse> listIntentsPagedCallable;
    private final GrpcOperationsStub operationsStub;
    private final UnaryCallable<UpdateIntentRequest, Intent> updateIntentCallable;
    private static final MethodDescriptor<ListIntentsRequest, ListIntentsResponse> listIntentsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Intents/ListIntents").setRequestMarshaller(ProtoUtils.marshaller(ListIntentsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListIntentsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetIntentRequest, Intent> getIntentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Intents/GetIntent").setRequestMarshaller(ProtoUtils.marshaller(GetIntentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Intent.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateIntentRequest, Intent> createIntentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Intents/CreateIntent").setRequestMarshaller(ProtoUtils.marshaller(CreateIntentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Intent.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateIntentRequest, Intent> updateIntentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Intents/UpdateIntent").setRequestMarshaller(ProtoUtils.marshaller(UpdateIntentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Intent.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteIntentRequest, Empty> deleteIntentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Intents/DeleteIntent").setRequestMarshaller(ProtoUtils.marshaller(DeleteIntentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Intents/BatchUpdateIntents").setRequestMarshaller(ProtoUtils.marshaller(BatchUpdateIntentsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Intents/BatchDeleteIntents").setRequestMarshaller(ProtoUtils.marshaller(BatchDeleteIntentsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();

    public static final GrpcIntentsStub create(IntentsStubSettings intentsStubSettings) throws IOException {
        return new GrpcIntentsStub(intentsStubSettings, ClientContext.create(intentsStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.IntentsStubSettings] */
    public static final GrpcIntentsStub create(ClientContext clientContext) throws IOException {
        return new GrpcIntentsStub(IntentsStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.IntentsStubSettings] */
    public static final GrpcIntentsStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcIntentsStub(IntentsStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcIntentsStub(IntentsStubSettings intentsStubSettings, ClientContext clientContext) throws IOException {
        this(intentsStubSettings, clientContext, new GrpcIntentsCallableFactory());
    }

    protected GrpcIntentsStub(IntentsStubSettings intentsStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        this.operationsStub = GrpcOperationsStub.create(clientContext, grpcStubCallableFactory);
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(listIntentsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ListIntentsRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcIntentsStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ListIntentsRequest listIntentsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(listIntentsRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(getIntentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcIntentsStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetIntentRequest getIntentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(getIntentRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(createIntentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<CreateIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcIntentsStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(CreateIntentRequest createIntentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(createIntentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(updateIntentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<UpdateIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcIntentsStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(UpdateIntentRequest updateIntentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("intent.name", String.valueOf(updateIntentRequest.getIntent().getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteIntentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcIntentsStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteIntentRequest deleteIntentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(deleteIntentRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build6 = GrpcCallSettings.newBuilder().setMethodDescriptor(batchUpdateIntentsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<BatchUpdateIntentsRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcIntentsStub.6
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(BatchUpdateIntentsRequest batchUpdateIntentsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(batchUpdateIntentsRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build7 = GrpcCallSettings.newBuilder().setMethodDescriptor(batchDeleteIntentsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<BatchDeleteIntentsRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcIntentsStub.7
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(BatchDeleteIntentsRequest batchDeleteIntentsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(batchDeleteIntentsRequest.getParent()));
                return builder.build();
            }
        }).build();
        this.listIntentsCallable = grpcStubCallableFactory.createUnaryCallable(build, intentsStubSettings.listIntentsSettings(), clientContext);
        this.listIntentsPagedCallable = grpcStubCallableFactory.createPagedCallable(build, intentsStubSettings.listIntentsSettings(), clientContext);
        this.getIntentCallable = grpcStubCallableFactory.createUnaryCallable(build2, intentsStubSettings.getIntentSettings(), clientContext);
        this.createIntentCallable = grpcStubCallableFactory.createUnaryCallable(build3, intentsStubSettings.createIntentSettings(), clientContext);
        this.updateIntentCallable = grpcStubCallableFactory.createUnaryCallable(build4, intentsStubSettings.updateIntentSettings(), clientContext);
        this.deleteIntentCallable = grpcStubCallableFactory.createUnaryCallable(build5, intentsStubSettings.deleteIntentSettings(), clientContext);
        this.batchUpdateIntentsCallable = grpcStubCallableFactory.createUnaryCallable(build6, intentsStubSettings.batchUpdateIntentsSettings(), clientContext);
        this.batchUpdateIntentsOperationCallable = grpcStubCallableFactory.createOperationCallable(build6, intentsStubSettings.batchUpdateIntentsOperationSettings(), clientContext, this.operationsStub);
        this.batchDeleteIntentsCallable = grpcStubCallableFactory.createUnaryCallable(build7, intentsStubSettings.batchDeleteIntentsSettings(), clientContext);
        this.batchDeleteIntentsOperationCallable = grpcStubCallableFactory.createOperationCallable(build7, intentsStubSettings.batchDeleteIntentsOperationSettings(), clientContext, this.operationsStub);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public GrpcOperationsStub getOperationsStub() {
        return this.operationsStub;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<ListIntentsRequest, IntentsClient.ListIntentsPagedResponse> listIntentsPagedCallable() {
        return this.listIntentsPagedCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<ListIntentsRequest, ListIntentsResponse> listIntentsCallable() {
        return this.listIntentsCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<GetIntentRequest, Intent> getIntentCallable() {
        return this.getIntentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<CreateIntentRequest, Intent> createIntentCallable() {
        return this.createIntentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<UpdateIntentRequest, Intent> updateIntentCallable() {
        return this.updateIntentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<DeleteIntentRequest, Empty> deleteIntentCallable() {
        return this.deleteIntentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationCallable() {
        return this.batchUpdateIntentsOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsCallable() {
        return this.batchUpdateIntentsCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationCallable() {
        return this.batchDeleteIntentsOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub
    public UnaryCallable<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsCallable() {
        return this.batchDeleteIntentsCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.IntentsStub, java.lang.AutoCloseable
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
