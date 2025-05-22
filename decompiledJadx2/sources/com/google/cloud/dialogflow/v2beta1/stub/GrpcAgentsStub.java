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
import com.google.cloud.dialogflow.v2beta1.Agent;
import com.google.cloud.dialogflow.v2beta1.AgentsClient;
import com.google.cloud.dialogflow.v2beta1.DeleteAgentRequest;
import com.google.cloud.dialogflow.v2beta1.ExportAgentRequest;
import com.google.cloud.dialogflow.v2beta1.ExportAgentResponse;
import com.google.cloud.dialogflow.v2beta1.GetAgentRequest;
import com.google.cloud.dialogflow.v2beta1.GetValidationResultRequest;
import com.google.cloud.dialogflow.v2beta1.ImportAgentRequest;
import com.google.cloud.dialogflow.v2beta1.RestoreAgentRequest;
import com.google.cloud.dialogflow.v2beta1.SearchAgentsRequest;
import com.google.cloud.dialogflow.v2beta1.SearchAgentsResponse;
import com.google.cloud.dialogflow.v2beta1.SetAgentRequest;
import com.google.cloud.dialogflow.v2beta1.TrainAgentRequest;
import com.google.cloud.dialogflow.v2beta1.ValidationResult;
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
public class GrpcAgentsStub extends AgentsStub {
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<DeleteAgentRequest, Empty> deleteAgentCallable;
    private final UnaryCallable<ExportAgentRequest, Operation> exportAgentCallable;
    private final OperationCallable<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationCallable;
    private final UnaryCallable<GetAgentRequest, Agent> getAgentCallable;
    private final UnaryCallable<GetValidationResultRequest, ValidationResult> getValidationResultCallable;
    private final UnaryCallable<ImportAgentRequest, Operation> importAgentCallable;
    private final OperationCallable<ImportAgentRequest, Empty, Struct> importAgentOperationCallable;
    private final GrpcOperationsStub operationsStub;
    private final UnaryCallable<RestoreAgentRequest, Operation> restoreAgentCallable;
    private final OperationCallable<RestoreAgentRequest, Empty, Struct> restoreAgentOperationCallable;
    private final UnaryCallable<SearchAgentsRequest, SearchAgentsResponse> searchAgentsCallable;
    private final UnaryCallable<SearchAgentsRequest, AgentsClient.SearchAgentsPagedResponse> searchAgentsPagedCallable;
    private final UnaryCallable<SetAgentRequest, Agent> setAgentCallable;
    private final UnaryCallable<TrainAgentRequest, Operation> trainAgentCallable;
    private final OperationCallable<TrainAgentRequest, Empty, Struct> trainAgentOperationCallable;
    private static final MethodDescriptor<SetAgentRequest, Agent> setAgentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/SetAgent").setRequestMarshaller(ProtoUtils.marshaller(SetAgentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Agent.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteAgentRequest, Empty> deleteAgentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/DeleteAgent").setRequestMarshaller(ProtoUtils.marshaller(DeleteAgentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<GetAgentRequest, Agent> getAgentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/GetAgent").setRequestMarshaller(ProtoUtils.marshaller(GetAgentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Agent.getDefaultInstance())).build();
    private static final MethodDescriptor<SearchAgentsRequest, SearchAgentsResponse> searchAgentsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/SearchAgents").setRequestMarshaller(ProtoUtils.marshaller(SearchAgentsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(SearchAgentsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<TrainAgentRequest, Operation> trainAgentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/TrainAgent").setRequestMarshaller(ProtoUtils.marshaller(TrainAgentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<ExportAgentRequest, Operation> exportAgentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/ExportAgent").setRequestMarshaller(ProtoUtils.marshaller(ExportAgentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<ImportAgentRequest, Operation> importAgentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/ImportAgent").setRequestMarshaller(ProtoUtils.marshaller(ImportAgentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<RestoreAgentRequest, Operation> restoreAgentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/RestoreAgent").setRequestMarshaller(ProtoUtils.marshaller(RestoreAgentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<GetValidationResultRequest, ValidationResult> getValidationResultMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Agents/GetValidationResult").setRequestMarshaller(ProtoUtils.marshaller(GetValidationResultRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ValidationResult.getDefaultInstance())).build();

    public static final GrpcAgentsStub create(AgentsStubSettings agentsStubSettings) throws IOException {
        return new GrpcAgentsStub(agentsStubSettings, ClientContext.create(agentsStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.AgentsStubSettings] */
    public static final GrpcAgentsStub create(ClientContext clientContext) throws IOException {
        return new GrpcAgentsStub(AgentsStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.AgentsStubSettings] */
    public static final GrpcAgentsStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcAgentsStub(AgentsStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcAgentsStub(AgentsStubSettings agentsStubSettings, ClientContext clientContext) throws IOException {
        this(agentsStubSettings, clientContext, new GrpcAgentsCallableFactory());
    }

    protected GrpcAgentsStub(AgentsStubSettings agentsStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        this.operationsStub = GrpcOperationsStub.create(clientContext, grpcStubCallableFactory);
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(setAgentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<SetAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(SetAgentRequest setAgentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("agent.parent", String.valueOf(setAgentRequest.getAgent().getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteAgentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteAgentRequest deleteAgentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(deleteAgentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(getAgentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetAgentRequest getAgentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(getAgentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(searchAgentsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<SearchAgentsRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(SearchAgentsRequest searchAgentsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(searchAgentsRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(trainAgentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<TrainAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(TrainAgentRequest trainAgentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(trainAgentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build6 = GrpcCallSettings.newBuilder().setMethodDescriptor(exportAgentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ExportAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.6
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ExportAgentRequest exportAgentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(exportAgentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build7 = GrpcCallSettings.newBuilder().setMethodDescriptor(importAgentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ImportAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.7
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ImportAgentRequest importAgentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(importAgentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build8 = GrpcCallSettings.newBuilder().setMethodDescriptor(restoreAgentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<RestoreAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.8
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(RestoreAgentRequest restoreAgentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(restoreAgentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build9 = GrpcCallSettings.newBuilder().setMethodDescriptor(getValidationResultMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetValidationResultRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcAgentsStub.9
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetValidationResultRequest getValidationResultRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(getValidationResultRequest.getParent()));
                return builder.build();
            }
        }).build();
        this.setAgentCallable = grpcStubCallableFactory.createUnaryCallable(build, agentsStubSettings.setAgentSettings(), clientContext);
        this.deleteAgentCallable = grpcStubCallableFactory.createUnaryCallable(build2, agentsStubSettings.deleteAgentSettings(), clientContext);
        this.getAgentCallable = grpcStubCallableFactory.createUnaryCallable(build3, agentsStubSettings.getAgentSettings(), clientContext);
        this.searchAgentsCallable = grpcStubCallableFactory.createUnaryCallable(build4, agentsStubSettings.searchAgentsSettings(), clientContext);
        this.searchAgentsPagedCallable = grpcStubCallableFactory.createPagedCallable(build4, agentsStubSettings.searchAgentsSettings(), clientContext);
        this.trainAgentCallable = grpcStubCallableFactory.createUnaryCallable(build5, agentsStubSettings.trainAgentSettings(), clientContext);
        this.trainAgentOperationCallable = grpcStubCallableFactory.createOperationCallable(build5, agentsStubSettings.trainAgentOperationSettings(), clientContext, this.operationsStub);
        this.exportAgentCallable = grpcStubCallableFactory.createUnaryCallable(build6, agentsStubSettings.exportAgentSettings(), clientContext);
        this.exportAgentOperationCallable = grpcStubCallableFactory.createOperationCallable(build6, agentsStubSettings.exportAgentOperationSettings(), clientContext, this.operationsStub);
        this.importAgentCallable = grpcStubCallableFactory.createUnaryCallable(build7, agentsStubSettings.importAgentSettings(), clientContext);
        this.importAgentOperationCallable = grpcStubCallableFactory.createOperationCallable(build7, agentsStubSettings.importAgentOperationSettings(), clientContext, this.operationsStub);
        this.restoreAgentCallable = grpcStubCallableFactory.createUnaryCallable(build8, agentsStubSettings.restoreAgentSettings(), clientContext);
        this.restoreAgentOperationCallable = grpcStubCallableFactory.createOperationCallable(build8, agentsStubSettings.restoreAgentOperationSettings(), clientContext, this.operationsStub);
        this.getValidationResultCallable = grpcStubCallableFactory.createUnaryCallable(build9, agentsStubSettings.getValidationResultSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public GrpcOperationsStub getOperationsStub() {
        return this.operationsStub;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<SetAgentRequest, Agent> setAgentCallable() {
        return this.setAgentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<DeleteAgentRequest, Empty> deleteAgentCallable() {
        return this.deleteAgentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<GetAgentRequest, Agent> getAgentCallable() {
        return this.getAgentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<SearchAgentsRequest, AgentsClient.SearchAgentsPagedResponse> searchAgentsPagedCallable() {
        return this.searchAgentsPagedCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<SearchAgentsRequest, SearchAgentsResponse> searchAgentsCallable() {
        return this.searchAgentsCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<TrainAgentRequest, Empty, Struct> trainAgentOperationCallable() {
        return this.trainAgentOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<TrainAgentRequest, Operation> trainAgentCallable() {
        return this.trainAgentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationCallable() {
        return this.exportAgentOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<ExportAgentRequest, Operation> exportAgentCallable() {
        return this.exportAgentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<ImportAgentRequest, Empty, Struct> importAgentOperationCallable() {
        return this.importAgentOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<ImportAgentRequest, Operation> importAgentCallable() {
        return this.importAgentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<RestoreAgentRequest, Empty, Struct> restoreAgentOperationCallable() {
        return this.restoreAgentOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<RestoreAgentRequest, Operation> restoreAgentCallable() {
        return this.restoreAgentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub
    public UnaryCallable<GetValidationResultRequest, ValidationResult> getValidationResultCallable() {
        return this.getValidationResultCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.AgentsStub, java.lang.AutoCloseable
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
