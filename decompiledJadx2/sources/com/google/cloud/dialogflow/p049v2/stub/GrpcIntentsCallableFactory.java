package com.google.cloud.dialogflow.p049v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcCallableFactory;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.BatchingCallSettings;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.api.gax.rpc.OperationCallSettings;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.ServerStreamingCallSettings;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StreamingCallSettings;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class GrpcIntentsCallableFactory implements GrpcStubCallableFactory {
    @Override // com.google.api.gax.grpc.GrpcStubCallableFactory
    public <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createUnaryCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, UnaryCallSettings<RequestT, ResponseT> unaryCallSettings, ClientContext clientContext) {
        return GrpcCallableFactory.createUnaryCallable(grpcCallSettings, unaryCallSettings, clientContext);
    }

    @Override // com.google.api.gax.grpc.GrpcStubCallableFactory
    public <RequestT, ResponseT, PagedListResponseT> UnaryCallable<RequestT, PagedListResponseT> createPagedCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, PagedCallSettings<RequestT, ResponseT, PagedListResponseT> pagedCallSettings, ClientContext clientContext) {
        return GrpcCallableFactory.createPagedCallable(grpcCallSettings, pagedCallSettings, clientContext);
    }

    @Override // com.google.api.gax.grpc.GrpcStubCallableFactory
    public <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createBatchingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext clientContext) {
        return GrpcCallableFactory.createBatchingCallable(grpcCallSettings, batchingCallSettings, clientContext);
    }

    @Override // com.google.api.gax.grpc.GrpcStubCallableFactory
    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public <RequestT, ResponseT, MetadataT> OperationCallable<RequestT, ResponseT, MetadataT> createOperationCallable(GrpcCallSettings<RequestT, Operation> grpcCallSettings, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, OperationsStub operationsStub) {
        return GrpcCallableFactory.createOperationCallable(grpcCallSettings, operationCallSettings, clientContext, operationsStub);
    }

    @Override // com.google.api.gax.grpc.GrpcStubCallableFactory
    public <RequestT, ResponseT> BidiStreamingCallable<RequestT, ResponseT> createBidiStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        return GrpcCallableFactory.createBidiStreamingCallable(grpcCallSettings, streamingCallSettings, clientContext);
    }

    @Override // com.google.api.gax.grpc.GrpcStubCallableFactory
    public <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, ServerStreamingCallSettings<RequestT, ResponseT> serverStreamingCallSettings, ClientContext clientContext) {
        return GrpcCallableFactory.createServerStreamingCallable((GrpcCallSettings) grpcCallSettings, (ServerStreamingCallSettings) serverStreamingCallSettings, clientContext);
    }

    @Override // com.google.api.gax.grpc.GrpcStubCallableFactory
    public <RequestT, ResponseT> ClientStreamingCallable<RequestT, ResponseT> createClientStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        return GrpcCallableFactory.createClientStreamingCallable(grpcCallSettings, streamingCallSettings, clientContext);
    }
}
