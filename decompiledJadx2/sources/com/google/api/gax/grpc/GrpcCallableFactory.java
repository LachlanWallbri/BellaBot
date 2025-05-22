package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.BatchingCallSettings;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.Callables;
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
import com.google.api.gax.tracing.SpanName;
import com.google.api.gax.tracing.TracedBatchingCallable;
import com.google.api.gax.tracing.TracedBidiCallable;
import com.google.api.gax.tracing.TracedClientStreamingCallable;
import com.google.api.gax.tracing.TracedOperationCallable;
import com.google.api.gax.tracing.TracedOperationInitialCallable;
import com.google.api.gax.tracing.TracedServerStreamingCallable;
import com.google.api.gax.tracing.TracedUnaryCallable;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import io.grpc.MethodDescriptor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class GrpcCallableFactory {
    private static final Pattern FULL_METHOD_NAME_REGEX = Pattern.compile("^.*?([^./]+)/([^./]+)$");

    private GrpcCallableFactory() {
    }

    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createBaseUnaryCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, UnaryCallSettings<?, ?> unaryCallSettings, ClientContext clientContext) {
        return Callables.retrying(GrpcRawCallableFactory.createUnaryCallable(grpcCallSettings, unaryCallSettings.getRetryableCodes()), unaryCallSettings, clientContext);
    }

    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createUnaryCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, UnaryCallSettings<RequestT, ResponseT> unaryCallSettings, ClientContext clientContext) {
        return new TracedUnaryCallable(createBaseUnaryCallable(grpcCallSettings, unaryCallSettings, clientContext), clientContext.getTracerFactory(), getSpanName(grpcCallSettings.getMethodDescriptor())).withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    public static <RequestT, ResponseT, PagedListResponseT> UnaryCallable<RequestT, PagedListResponseT> createPagedCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, PagedCallSettings<RequestT, ResponseT, PagedListResponseT> pagedCallSettings, ClientContext clientContext) {
        return Callables.paged(createBaseUnaryCallable(grpcCallSettings, pagedCallSettings, clientContext), pagedCallSettings).withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi("The surface for batching is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createBatchingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext clientContext) {
        return Callables.batching(new TracedBatchingCallable(createBaseUnaryCallable(grpcCallSettings, batchingCallSettings, clientContext), clientContext.getTracerFactory(), getSpanName(grpcCallSettings.getMethodDescriptor()), batchingCallSettings.getBatchingDescriptor()), batchingCallSettings, clientContext).withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public static <RequestT, ResponseT, MetadataT> OperationCallable<RequestT, ResponseT, MetadataT> createOperationCallable(GrpcCallSettings<RequestT, Operation> grpcCallSettings, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, OperationsStub operationsStub) {
        SpanName spanName = getSpanName(grpcCallSettings.getMethodDescriptor());
        return new TracedOperationCallable(Callables.longRunningOperation(new TracedOperationInitialCallable(new GrpcOperationSnapshotCallable(createBaseUnaryCallable(grpcCallSettings, operationCallSettings.getInitialCallSettings(), clientContext)), clientContext.getTracerFactory(), spanName), operationCallSettings, clientContext, new GrpcLongRunningClient(operationsStub)), clientContext.getTracerFactory(), SpanName.m579of(spanName.getClientName(), spanName.getMethodName() + "Operation")).withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> BidiStreamingCallable<RequestT, ResponseT> createBidiStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        return new TracedBidiCallable(GrpcRawCallableFactory.createBidiStreamingCallable(grpcCallSettings, ImmutableSet.m676of()), clientContext.getTracerFactory(), getSpanName(grpcCallSettings.getMethodDescriptor())).withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    @Deprecated
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        return createServerStreamingCallable((GrpcCallSettings) grpcCallSettings, (ServerStreamingCallSettings) ServerStreamingCallSettings.newBuilder().build(), clientContext);
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, ServerStreamingCallSettings<RequestT, ResponseT> serverStreamingCallSettings, ClientContext clientContext) {
        ServerStreamingCallable createServerStreamingCallable = GrpcRawCallableFactory.createServerStreamingCallable(grpcCallSettings, serverStreamingCallSettings.getRetryableCodes());
        if (clientContext.getStreamWatchdog() != null) {
            createServerStreamingCallable = Callables.watched(createServerStreamingCallable, serverStreamingCallSettings, clientContext);
        }
        return new TracedServerStreamingCallable(Callables.retrying(createServerStreamingCallable, serverStreamingCallSettings, clientContext), clientContext.getTracerFactory(), getSpanName(grpcCallSettings.getMethodDescriptor())).withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ClientStreamingCallable<RequestT, ResponseT> createClientStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        return new TracedClientStreamingCallable(GrpcRawCallableFactory.createClientStreamingCallable(grpcCallSettings, ImmutableSet.m676of()), clientContext.getTracerFactory(), getSpanName(grpcCallSettings.getMethodDescriptor())).withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @InternalApi("Visible for testing")
    static SpanName getSpanName(@Nonnull MethodDescriptor<?, ?> methodDescriptor) {
        Matcher matcher = FULL_METHOD_NAME_REGEX.matcher(methodDescriptor.getFullMethodName());
        Preconditions.checkArgument(matcher.matches(), "Invalid fullMethodName");
        return SpanName.m579of(matcher.group(1), matcher.group(2));
    }
}
