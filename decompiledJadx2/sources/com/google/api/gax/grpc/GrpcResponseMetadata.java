package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.common.base.Preconditions;
import io.grpc.Metadata;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for response metadata is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class GrpcResponseMetadata implements ResponseMetadataHandler {
    private volatile Metadata responseMetadata;
    private volatile Metadata trailingMetadata;

    public GrpcCallContext addHandlers(ApiCallContext apiCallContext) {
        if (Preconditions.checkNotNull(apiCallContext) instanceof GrpcCallContext) {
            return addHandlers((GrpcCallContext) apiCallContext);
        }
        throw new IllegalArgumentException("context must be an instance of GrpcCallContext, but found " + apiCallContext.getClass().getName());
    }

    public GrpcCallContext createContextWithHandlers() {
        return addHandlers(GrpcCallContext.createDefault());
    }

    private GrpcCallContext addHandlers(GrpcCallContext grpcCallContext) {
        return ((GrpcCallContext) Preconditions.checkNotNull(grpcCallContext)).withCallOptions(CallOptionsUtil.putMetadataHandlerOption(grpcCallContext.getCallOptions(), this));
    }

    public Metadata getMetadata() {
        return this.responseMetadata;
    }

    public Metadata getTrailingMetadata() {
        return this.trailingMetadata;
    }

    @Override // com.google.api.gax.grpc.ResponseMetadataHandler
    public void onHeaders(Metadata metadata) {
        this.responseMetadata = metadata;
    }

    @Override // com.google.api.gax.grpc.ResponseMetadataHandler
    public void onTrailers(Metadata metadata) {
        this.trailingMetadata = metadata;
    }
}
