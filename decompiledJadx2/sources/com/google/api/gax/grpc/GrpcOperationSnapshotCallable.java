package com.google.api.gax.grpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.longrunning.Operation;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcOperationSnapshotCallable<RequestT> extends UnaryCallable<RequestT, OperationSnapshot> {
    private final UnaryCallable<RequestT, Operation> innerCallable;

    public GrpcOperationSnapshotCallable(UnaryCallable<RequestT, Operation> unaryCallable) {
        this.innerCallable = unaryCallable;
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<OperationSnapshot> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        return ApiFutures.transform(this.innerCallable.futureCall(requestt, apiCallContext), new ApiFunction<Operation, OperationSnapshot>() { // from class: com.google.api.gax.grpc.GrpcOperationSnapshotCallable.1
            @Override // com.google.api.core.ApiFunction
            public OperationSnapshot apply(Operation operation) {
                return new GrpcOperationSnapshot(operation);
            }
        }, MoreExecutors.directExecutor());
    }
}
