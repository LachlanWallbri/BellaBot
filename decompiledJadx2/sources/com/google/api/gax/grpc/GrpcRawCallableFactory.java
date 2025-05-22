package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallable;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use by google-cloud-java clients only")
/* loaded from: classes2.dex */
public class GrpcRawCallableFactory {
    private GrpcRawCallableFactory() {
    }

    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createUnaryCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, Set<StatusCode.Code> set) {
        UnaryCallable grpcDirectCallable = new GrpcDirectCallable(grpcCallSettings.getMethodDescriptor());
        if (grpcCallSettings.getParamsExtractor() != null) {
            grpcDirectCallable = new GrpcUnaryRequestParamCallable(grpcDirectCallable, grpcCallSettings.getParamsExtractor());
        }
        return new GrpcExceptionCallable(grpcDirectCallable, set);
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> BidiStreamingCallable<RequestT, ResponseT> createBidiStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, Set<StatusCode.Code> set) {
        return new GrpcExceptionBidiStreamingCallable(new GrpcDirectBidiStreamingCallable(grpcCallSettings.getMethodDescriptor()), set);
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, Set<StatusCode.Code> set) {
        ServerStreamingCallable grpcDirectServerStreamingCallable = new GrpcDirectServerStreamingCallable(grpcCallSettings.getMethodDescriptor());
        if (grpcCallSettings.getParamsExtractor() != null) {
            grpcDirectServerStreamingCallable = new GrpcServerStreamingRequestParamCallable(grpcDirectServerStreamingCallable, grpcCallSettings.getParamsExtractor());
        }
        return new GrpcExceptionServerStreamingCallable(grpcDirectServerStreamingCallable, set);
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ClientStreamingCallable<RequestT, ResponseT> createClientStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, Set<StatusCode.Code> set) {
        return new GrpcExceptionClientStreamingCallable(new GrpcDirectClientStreamingCallable(grpcCallSettings.getMethodDescriptor()), set);
    }
}
