package com.google.cloud.dialogflow.p049v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequest;
import com.google.cloud.dialogflow.p049v2.BatchDeleteEntitiesRequest;
import com.google.cloud.dialogflow.p049v2.BatchDeleteEntityTypesRequest;
import com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequest;
import com.google.cloud.dialogflow.p049v2.BatchUpdateEntityTypesRequest;
import com.google.cloud.dialogflow.p049v2.BatchUpdateEntityTypesResponse;
import com.google.cloud.dialogflow.p049v2.CreateEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.DeleteEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.EntityType;
import com.google.cloud.dialogflow.p049v2.EntityTypesClient;
import com.google.cloud.dialogflow.p049v2.GetEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.ListEntityTypesRequest;
import com.google.cloud.dialogflow.p049v2.ListEntityTypesResponse;
import com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequest;
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
/* loaded from: classes2.dex */
public class GrpcEntityTypesStub extends EntityTypesStub {
    private final BackgroundResource backgroundResources;
    private final UnaryCallable<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesCallable;
    private final OperationCallable<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationCallable;
    private final UnaryCallable<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesCallable;
    private final OperationCallable<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationCallable;
    private final UnaryCallable<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesCallable;
    private final OperationCallable<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationCallable;
    private final UnaryCallable<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesCallable;
    private final OperationCallable<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationCallable;
    private final UnaryCallable<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesCallable;
    private final OperationCallable<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationCallable;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<CreateEntityTypeRequest, EntityType> createEntityTypeCallable;
    private final UnaryCallable<DeleteEntityTypeRequest, Empty> deleteEntityTypeCallable;
    private final UnaryCallable<GetEntityTypeRequest, EntityType> getEntityTypeCallable;
    private final UnaryCallable<ListEntityTypesRequest, ListEntityTypesResponse> listEntityTypesCallable;
    private final UnaryCallable<ListEntityTypesRequest, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesPagedCallable;
    private final GrpcOperationsStub operationsStub;
    private final UnaryCallable<UpdateEntityTypeRequest, EntityType> updateEntityTypeCallable;
    private static final MethodDescriptor<ListEntityTypesRequest, ListEntityTypesResponse> listEntityTypesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/ListEntityTypes").setRequestMarshaller(ProtoUtils.marshaller(ListEntityTypesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListEntityTypesResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetEntityTypeRequest, EntityType> getEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/GetEntityType").setRequestMarshaller(ProtoUtils.marshaller(GetEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(EntityType.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateEntityTypeRequest, EntityType> createEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/CreateEntityType").setRequestMarshaller(ProtoUtils.marshaller(CreateEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(EntityType.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateEntityTypeRequest, EntityType> updateEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/UpdateEntityType").setRequestMarshaller(ProtoUtils.marshaller(UpdateEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(EntityType.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteEntityTypeRequest, Empty> deleteEntityTypeMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/DeleteEntityType").setRequestMarshaller(ProtoUtils.marshaller(DeleteEntityTypeRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/BatchUpdateEntityTypes").setRequestMarshaller(ProtoUtils.marshaller(BatchUpdateEntityTypesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/BatchDeleteEntityTypes").setRequestMarshaller(ProtoUtils.marshaller(BatchDeleteEntityTypesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/BatchCreateEntities").setRequestMarshaller(ProtoUtils.marshaller(BatchCreateEntitiesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/BatchUpdateEntities").setRequestMarshaller(ProtoUtils.marshaller(BatchUpdateEntitiesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2.EntityTypes/BatchDeleteEntities").setRequestMarshaller(ProtoUtils.marshaller(BatchDeleteEntitiesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();

    public static final GrpcEntityTypesStub create(EntityTypesStubSettings entityTypesStubSettings) throws IOException {
        return new GrpcEntityTypesStub(entityTypesStubSettings, ClientContext.create(entityTypesStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2.stub.EntityTypesStubSettings] */
    public static final GrpcEntityTypesStub create(ClientContext clientContext) throws IOException {
        return new GrpcEntityTypesStub(EntityTypesStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2.stub.EntityTypesStubSettings] */
    public static final GrpcEntityTypesStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcEntityTypesStub(EntityTypesStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcEntityTypesStub(EntityTypesStubSettings entityTypesStubSettings, ClientContext clientContext) throws IOException {
        this(entityTypesStubSettings, clientContext, new GrpcEntityTypesCallableFactory());
    }

    protected GrpcEntityTypesStub(EntityTypesStubSettings entityTypesStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        this.operationsStub = GrpcOperationsStub.create(clientContext, grpcStubCallableFactory);
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(listEntityTypesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ListEntityTypesRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ListEntityTypesRequest listEntityTypesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(listEntityTypesRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(getEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetEntityTypeRequest getEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(getEntityTypeRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(createEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<CreateEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(CreateEntityTypeRequest createEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(createEntityTypeRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(updateEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<UpdateEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(UpdateEntityTypeRequest updateEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("entity_type.name", String.valueOf(updateEntityTypeRequest.getEntityType().getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteEntityTypeMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteEntityTypeRequest deleteEntityTypeRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(deleteEntityTypeRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build6 = GrpcCallSettings.newBuilder().setMethodDescriptor(batchUpdateEntityTypesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<BatchUpdateEntityTypesRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.6
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(batchUpdateEntityTypesRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build7 = GrpcCallSettings.newBuilder().setMethodDescriptor(batchDeleteEntityTypesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<BatchDeleteEntityTypesRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.7
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(batchDeleteEntityTypesRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build8 = GrpcCallSettings.newBuilder().setMethodDescriptor(batchCreateEntitiesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<BatchCreateEntitiesRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.8
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(BatchCreateEntitiesRequest batchCreateEntitiesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(batchCreateEntitiesRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build9 = GrpcCallSettings.newBuilder().setMethodDescriptor(batchUpdateEntitiesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<BatchUpdateEntitiesRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.9
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(BatchUpdateEntitiesRequest batchUpdateEntitiesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(batchUpdateEntitiesRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build10 = GrpcCallSettings.newBuilder().setMethodDescriptor(batchDeleteEntitiesMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<BatchDeleteEntitiesRequest>() { // from class: com.google.cloud.dialogflow.v2.stub.GrpcEntityTypesStub.10
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(BatchDeleteEntitiesRequest batchDeleteEntitiesRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(batchDeleteEntitiesRequest.getParent()));
                return builder.build();
            }
        }).build();
        this.listEntityTypesCallable = grpcStubCallableFactory.createUnaryCallable(build, entityTypesStubSettings.listEntityTypesSettings(), clientContext);
        this.listEntityTypesPagedCallable = grpcStubCallableFactory.createPagedCallable(build, entityTypesStubSettings.listEntityTypesSettings(), clientContext);
        this.getEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build2, entityTypesStubSettings.getEntityTypeSettings(), clientContext);
        this.createEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build3, entityTypesStubSettings.createEntityTypeSettings(), clientContext);
        this.updateEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build4, entityTypesStubSettings.updateEntityTypeSettings(), clientContext);
        this.deleteEntityTypeCallable = grpcStubCallableFactory.createUnaryCallable(build5, entityTypesStubSettings.deleteEntityTypeSettings(), clientContext);
        this.batchUpdateEntityTypesCallable = grpcStubCallableFactory.createUnaryCallable(build6, entityTypesStubSettings.batchUpdateEntityTypesSettings(), clientContext);
        this.batchUpdateEntityTypesOperationCallable = grpcStubCallableFactory.createOperationCallable(build6, entityTypesStubSettings.batchUpdateEntityTypesOperationSettings(), clientContext, this.operationsStub);
        this.batchDeleteEntityTypesCallable = grpcStubCallableFactory.createUnaryCallable(build7, entityTypesStubSettings.batchDeleteEntityTypesSettings(), clientContext);
        this.batchDeleteEntityTypesOperationCallable = grpcStubCallableFactory.createOperationCallable(build7, entityTypesStubSettings.batchDeleteEntityTypesOperationSettings(), clientContext, this.operationsStub);
        this.batchCreateEntitiesCallable = grpcStubCallableFactory.createUnaryCallable(build8, entityTypesStubSettings.batchCreateEntitiesSettings(), clientContext);
        this.batchCreateEntitiesOperationCallable = grpcStubCallableFactory.createOperationCallable(build8, entityTypesStubSettings.batchCreateEntitiesOperationSettings(), clientContext, this.operationsStub);
        this.batchUpdateEntitiesCallable = grpcStubCallableFactory.createUnaryCallable(build9, entityTypesStubSettings.batchUpdateEntitiesSettings(), clientContext);
        this.batchUpdateEntitiesOperationCallable = grpcStubCallableFactory.createOperationCallable(build9, entityTypesStubSettings.batchUpdateEntitiesOperationSettings(), clientContext, this.operationsStub);
        this.batchDeleteEntitiesCallable = grpcStubCallableFactory.createUnaryCallable(build10, entityTypesStubSettings.batchDeleteEntitiesSettings(), clientContext);
        this.batchDeleteEntitiesOperationCallable = grpcStubCallableFactory.createOperationCallable(build10, entityTypesStubSettings.batchDeleteEntitiesOperationSettings(), clientContext, this.operationsStub);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public GrpcOperationsStub getOperationsStub() {
        return this.operationsStub;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<ListEntityTypesRequest, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesPagedCallable() {
        return this.listEntityTypesPagedCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<ListEntityTypesRequest, ListEntityTypesResponse> listEntityTypesCallable() {
        return this.listEntityTypesCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<GetEntityTypeRequest, EntityType> getEntityTypeCallable() {
        return this.getEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<CreateEntityTypeRequest, EntityType> createEntityTypeCallable() {
        return this.createEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<UpdateEntityTypeRequest, EntityType> updateEntityTypeCallable() {
        return this.updateEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<DeleteEntityTypeRequest, Empty> deleteEntityTypeCallable() {
        return this.deleteEntityTypeCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationCallable() {
        return this.batchUpdateEntityTypesOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesCallable() {
        return this.batchUpdateEntityTypesCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationCallable() {
        return this.batchDeleteEntityTypesOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesCallable() {
        return this.batchDeleteEntityTypesCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationCallable() {
        return this.batchCreateEntitiesOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesCallable() {
        return this.batchCreateEntitiesCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationCallable() {
        return this.batchUpdateEntitiesOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesCallable() {
        return this.batchUpdateEntitiesCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationCallable() {
        return this.batchDeleteEntitiesOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub
    public UnaryCallable<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesCallable() {
        return this.batchDeleteEntitiesCallable;
    }

    @Override // com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub, java.lang.AutoCloseable
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
