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
import com.google.cloud.dialogflow.v2beta1.CreateDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.Document;
import com.google.cloud.dialogflow.v2beta1.DocumentsClient;
import com.google.cloud.dialogflow.v2beta1.GetDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadata;
import com.google.cloud.dialogflow.v2beta1.ListDocumentsRequest;
import com.google.cloud.dialogflow.v2beta1.ListDocumentsResponse;
import com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequest;
import com.google.common.collect.ImmutableMap;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.GrpcOperationsStub;
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
public class GrpcDocumentsStub extends DocumentsStub {
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<CreateDocumentRequest, Operation> createDocumentCallable;
    private final OperationCallable<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationCallable;
    private final UnaryCallable<DeleteDocumentRequest, Operation> deleteDocumentCallable;
    private final OperationCallable<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationCallable;
    private final UnaryCallable<GetDocumentRequest, Document> getDocumentCallable;
    private final UnaryCallable<ListDocumentsRequest, ListDocumentsResponse> listDocumentsCallable;
    private final UnaryCallable<ListDocumentsRequest, DocumentsClient.ListDocumentsPagedResponse> listDocumentsPagedCallable;
    private final GrpcOperationsStub operationsStub;
    private final UnaryCallable<ReloadDocumentRequest, Operation> reloadDocumentCallable;
    private final UnaryCallable<UpdateDocumentRequest, Operation> updateDocumentCallable;
    private final OperationCallable<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationCallable;
    private static final MethodDescriptor<ListDocumentsRequest, ListDocumentsResponse> listDocumentsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Documents/ListDocuments").setRequestMarshaller(ProtoUtils.marshaller(ListDocumentsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListDocumentsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetDocumentRequest, Document> getDocumentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Documents/GetDocument").setRequestMarshaller(ProtoUtils.marshaller(GetDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Document.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateDocumentRequest, Operation> createDocumentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Documents/CreateDocument").setRequestMarshaller(ProtoUtils.marshaller(CreateDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteDocumentRequest, Operation> deleteDocumentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Documents/DeleteDocument").setRequestMarshaller(ProtoUtils.marshaller(DeleteDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateDocumentRequest, Operation> updateDocumentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Documents/UpdateDocument").setRequestMarshaller(ProtoUtils.marshaller(UpdateDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<ReloadDocumentRequest, Operation> reloadDocumentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Documents/ReloadDocument").setRequestMarshaller(ProtoUtils.marshaller(ReloadDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();

    public static final GrpcDocumentsStub create(DocumentsStubSettings documentsStubSettings) throws IOException {
        return new GrpcDocumentsStub(documentsStubSettings, ClientContext.create(documentsStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.DocumentsStubSettings] */
    public static final GrpcDocumentsStub create(ClientContext clientContext) throws IOException {
        return new GrpcDocumentsStub(DocumentsStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.DocumentsStubSettings] */
    public static final GrpcDocumentsStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcDocumentsStub(DocumentsStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcDocumentsStub(DocumentsStubSettings documentsStubSettings, ClientContext clientContext) throws IOException {
        this(documentsStubSettings, clientContext, new GrpcDocumentsCallableFactory());
    }

    protected GrpcDocumentsStub(DocumentsStubSettings documentsStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        this.operationsStub = GrpcOperationsStub.create(clientContext, grpcStubCallableFactory);
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(listDocumentsMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ListDocumentsRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcDocumentsStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ListDocumentsRequest listDocumentsRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(listDocumentsRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(getDocumentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<GetDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcDocumentsStub.2
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(GetDocumentRequest getDocumentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(getDocumentRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build3 = GrpcCallSettings.newBuilder().setMethodDescriptor(createDocumentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<CreateDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcDocumentsStub.3
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(CreateDocumentRequest createDocumentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("parent", String.valueOf(createDocumentRequest.getParent()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build4 = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteDocumentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DeleteDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcDocumentsStub.4
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DeleteDocumentRequest deleteDocumentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(deleteDocumentRequest.getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build5 = GrpcCallSettings.newBuilder().setMethodDescriptor(updateDocumentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<UpdateDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcDocumentsStub.5
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(UpdateDocumentRequest updateDocumentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("document.name", String.valueOf(updateDocumentRequest.getDocument().getName()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build6 = GrpcCallSettings.newBuilder().setMethodDescriptor(reloadDocumentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<ReloadDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcDocumentsStub.6
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(ReloadDocumentRequest reloadDocumentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("name", String.valueOf(reloadDocumentRequest.getName()));
                return builder.build();
            }
        }).build();
        this.listDocumentsCallable = grpcStubCallableFactory.createUnaryCallable(build, documentsStubSettings.listDocumentsSettings(), clientContext);
        this.listDocumentsPagedCallable = grpcStubCallableFactory.createPagedCallable(build, documentsStubSettings.listDocumentsSettings(), clientContext);
        this.getDocumentCallable = grpcStubCallableFactory.createUnaryCallable(build2, documentsStubSettings.getDocumentSettings(), clientContext);
        this.createDocumentCallable = grpcStubCallableFactory.createUnaryCallable(build3, documentsStubSettings.createDocumentSettings(), clientContext);
        this.createDocumentOperationCallable = grpcStubCallableFactory.createOperationCallable(build3, documentsStubSettings.createDocumentOperationSettings(), clientContext, this.operationsStub);
        this.deleteDocumentCallable = grpcStubCallableFactory.createUnaryCallable(build4, documentsStubSettings.deleteDocumentSettings(), clientContext);
        this.deleteDocumentOperationCallable = grpcStubCallableFactory.createOperationCallable(build4, documentsStubSettings.deleteDocumentOperationSettings(), clientContext, this.operationsStub);
        this.updateDocumentCallable = grpcStubCallableFactory.createUnaryCallable(build5, documentsStubSettings.updateDocumentSettings(), clientContext);
        this.updateDocumentOperationCallable = grpcStubCallableFactory.createOperationCallable(build5, documentsStubSettings.updateDocumentOperationSettings(), clientContext, this.operationsStub);
        this.reloadDocumentCallable = grpcStubCallableFactory.createUnaryCallable(build6, documentsStubSettings.reloadDocumentSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public GrpcOperationsStub getOperationsStub() {
        return this.operationsStub;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    public UnaryCallable<ListDocumentsRequest, DocumentsClient.ListDocumentsPagedResponse> listDocumentsPagedCallable() {
        return this.listDocumentsPagedCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    public UnaryCallable<ListDocumentsRequest, ListDocumentsResponse> listDocumentsCallable() {
        return this.listDocumentsCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    public UnaryCallable<GetDocumentRequest, Document> getDocumentCallable() {
        return this.getDocumentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationCallable() {
        return this.createDocumentOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    public UnaryCallable<CreateDocumentRequest, Operation> createDocumentCallable() {
        return this.createDocumentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationCallable() {
        return this.deleteDocumentOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    public UnaryCallable<DeleteDocumentRequest, Operation> deleteDocumentCallable() {
        return this.deleteDocumentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationCallable() {
        return this.updateDocumentOperationCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    public UnaryCallable<UpdateDocumentRequest, Operation> updateDocumentCallable() {
        return this.updateDocumentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub
    public UnaryCallable<ReloadDocumentRequest, Operation> reloadDocumentCallable() {
        return this.reloadDocumentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub, java.lang.AutoCloseable
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
