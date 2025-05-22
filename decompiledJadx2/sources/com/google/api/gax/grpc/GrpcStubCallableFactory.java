package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
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
public interface GrpcStubCallableFactory {
    @BetaApi("The surface for batching is not stable yet and may change in the future.")
    <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createBatchingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext clientContext);

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    <RequestT, ResponseT> BidiStreamingCallable<RequestT, ResponseT> createBidiStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext);

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    <RequestT, ResponseT> ClientStreamingCallable<RequestT, ResponseT> createClientStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext);

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    <RequestT, ResponseT, MetadataT> OperationCallable<RequestT, ResponseT, MetadataT> createOperationCallable(GrpcCallSettings<RequestT, Operation> grpcCallSettings, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, OperationsStub operationsStub);

    <RequestT, ResponseT, PagedListResponseT> UnaryCallable<RequestT, PagedListResponseT> createPagedCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, PagedCallSettings<RequestT, ResponseT, PagedListResponseT> pagedCallSettings, ClientContext clientContext);

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, ServerStreamingCallSettings<RequestT, ResponseT> serverStreamingCallSettings, ClientContext clientContext);

    <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createUnaryCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, UnaryCallSettings<RequestT, ResponseT> unaryCallSettings, ClientContext clientContext);
}
