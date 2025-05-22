package com.google.longrunning.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.collect.ImmutableMap;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.ListOperationsResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.longrunning.WaitOperationRequest;
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
public class GrpcOperationsStub extends OperationsStub {
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<CancelOperationRequest, Empty> cancelOperationCallable;
    private final UnaryCallable<DeleteOperationRequest, Empty> deleteOperationCallable;
    private final UnaryCallable<GetOperationRequest, Operation> getOperationCallable;
    private final UnaryCallable<ListOperationsRequest, ListOperationsResponse> listOperationsCallable;
    private final UnaryCallable<ListOperationsRequest, OperationsClient.ListOperationsPagedResponse> listOperationsPagedCallable;
    private final UnaryCallable<WaitOperationRequest, Operation> waitOperationCallable;
    private static final MethodDescriptor<GetOperationRequest, Operation> getOperationMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/GetOperation").setRequestMarshaller(ProtoUtils.marshaller(GetOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<ListOperationsRequest, ListOperationsResponse> listOperationsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/ListOperations").setRequestMarshaller(ProtoUtils.marshaller(ListOperationsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListOperationsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<CancelOperationRequest, Empty> cancelOperationMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/CancelOperation").setRequestMarshaller(ProtoUtils.marshaller(CancelOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteOperationRequest, Empty> deleteOperationMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/DeleteOperation").setRequestMarshaller(ProtoUtils.marshaller(DeleteOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<WaitOperationRequest, Operation> waitOperationMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/WaitOperation").setRequestMarshaller(ProtoUtils.marshaller(WaitOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();

    public static final GrpcOperationsStub create(OperationsStubSettings operationsStubSettings) throws IOException {
        return new GrpcOperationsStub(operationsStubSettings, ClientContext.create(operationsStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.longrunning.stub.OperationsStubSettings] */
    public static final GrpcOperationsStub create(ClientContext clientContext) throws IOException {
        return new GrpcOperationsStub(OperationsStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.longrunning.stub.OperationsStubSettings] */
    public static final GrpcOperationsStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcOperationsStub(OperationsStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcOperationsStub(OperationsStubSettings operationsStubSettings, ClientContext clientContext) throws IOException {
        this(operationsStubSettings, clientContext, new GrpcOperationsCallableFactory());
    }

    protected GrpcOperationsStub(OperationsStubSettings operationsStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(getOperationMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetOperationRequest>() { // from class: com.google.longrunning.stub.GrpcOperationsStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetOperationRequest getOperationRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(getOperationRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(listOperationsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ListOperationsRequest>() { // from class: com.google.longrunning.stub.GrpcOperationsStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ListOperationsRequest listOperationsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(listOperationsRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(cancelOperationMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<CancelOperationRequest>() { // from class: com.google.longrunning.stub.GrpcOperationsStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(CancelOperationRequest cancelOperationRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(cancelOperationRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteOperationMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteOperationRequest>() { // from class: com.google.longrunning.stub.GrpcOperationsStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteOperationRequest deleteOperationRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(deleteOperationRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(waitOperationMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<WaitOperationRequest>() { // from class: com.google.longrunning.stub.GrpcOperationsStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(WaitOperationRequest waitOperationRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(waitOperationRequest.getName()));
                return builder.build();
            }
        }).build();
        this.getOperationCallable = grpcStubCallableFactory.createUnaryCallable(build, operationsStubSettings.getOperationSettings(), clientContext);
        this.listOperationsCallable = grpcStubCallableFactory.createUnaryCallable(build2, operationsStubSettings.listOperationsSettings(), clientContext);
        this.listOperationsPagedCallable = grpcStubCallableFactory.createPagedCallable(build2, operationsStubSettings.listOperationsSettings(), clientContext);
        this.cancelOperationCallable = grpcStubCallableFactory.createUnaryCallable(build3, operationsStubSettings.cancelOperationSettings(), clientContext);
        this.deleteOperationCallable = grpcStubCallableFactory.createUnaryCallable(build4, operationsStubSettings.deleteOperationSettings(), clientContext);
        this.waitOperationCallable = grpcStubCallableFactory.createUnaryCallable(build5, operationsStubSettings.waitOperationSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.longrunning.stub.OperationsStub
    public UnaryCallable<GetOperationRequest, Operation> getOperationCallable() {
        return this.getOperationCallable;
    }

    @Override // com.google.longrunning.stub.OperationsStub
    public UnaryCallable<ListOperationsRequest, OperationsClient.ListOperationsPagedResponse> listOperationsPagedCallable() {
        return this.listOperationsPagedCallable;
    }

    @Override // com.google.longrunning.stub.OperationsStub
    public UnaryCallable<ListOperationsRequest, ListOperationsResponse> listOperationsCallable() {
        return this.listOperationsCallable;
    }

    @Override // com.google.longrunning.stub.OperationsStub
    public UnaryCallable<CancelOperationRequest, Empty> cancelOperationCallable() {
        return this.cancelOperationCallable;
    }

    @Override // com.google.longrunning.stub.OperationsStub
    public UnaryCallable<DeleteOperationRequest, Empty> deleteOperationCallable() {
        return this.deleteOperationCallable;
    }

    @Override // com.google.longrunning.stub.OperationsStub
    public UnaryCallable<WaitOperationRequest, Operation> waitOperationCallable() {
        return this.waitOperationCallable;
    }

    @Override // com.google.longrunning.stub.OperationsStub, java.lang.AutoCloseable
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
