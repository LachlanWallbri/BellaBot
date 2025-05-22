package com.google.api.gax.grpc;

import com.google.api.core.ApiFunction;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.LongRunningClient;
import com.google.api.gax.rpc.TranslatingUnaryCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import com.google.protobuf.Empty;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcLongRunningClient implements LongRunningClient {
    private final OperationsStub operationsStub;

    public GrpcLongRunningClient(OperationsStub operationsStub) {
        this.operationsStub = operationsStub;
    }

    @Override // com.google.api.gax.rpc.LongRunningClient
    public UnaryCallable<String, OperationSnapshot> getOperationCallable() {
        return TranslatingUnaryCallable.create(this.operationsStub.getOperationCallable(), new ApiFunction<String, GetOperationRequest>() { // from class: com.google.api.gax.grpc.GrpcLongRunningClient.1
            @Override // com.google.api.core.ApiFunction
            public GetOperationRequest apply(String str) {
                return GetOperationRequest.newBuilder().setName(str).build();
            }
        }, new ApiFunction<Operation, OperationSnapshot>() { // from class: com.google.api.gax.grpc.GrpcLongRunningClient.2
            @Override // com.google.api.core.ApiFunction
            public OperationSnapshot apply(Operation operation) {
                return GrpcOperationSnapshot.create(operation);
            }
        });
    }

    @Override // com.google.api.gax.rpc.LongRunningClient
    public UnaryCallable<String, Void> cancelOperationCallable() {
        return TranslatingUnaryCallable.create(this.operationsStub.cancelOperationCallable(), new ApiFunction<String, CancelOperationRequest>() { // from class: com.google.api.gax.grpc.GrpcLongRunningClient.3
            @Override // com.google.api.core.ApiFunction
            public CancelOperationRequest apply(String str) {
                return CancelOperationRequest.newBuilder().setName(str).build();
            }
        }, new ApiFunction<Empty, Void>() { // from class: com.google.api.gax.grpc.GrpcLongRunningClient.4
            @Override // com.google.api.core.ApiFunction
            public Void apply(Empty empty) {
                return null;
            }
        });
    }

    @Override // com.google.api.gax.rpc.LongRunningClient
    public UnaryCallable<String, Void> deleteOperationCallable() {
        return TranslatingUnaryCallable.create(this.operationsStub.deleteOperationCallable(), new ApiFunction<String, DeleteOperationRequest>() { // from class: com.google.api.gax.grpc.GrpcLongRunningClient.5
            @Override // com.google.api.core.ApiFunction
            public DeleteOperationRequest apply(String str) {
                return DeleteOperationRequest.newBuilder().setName(str).build();
            }
        }, new ApiFunction<Empty, Void>() { // from class: com.google.api.gax.grpc.GrpcLongRunningClient.6
            @Override // com.google.api.core.ApiFunction
            public Void apply(Empty empty) {
                return null;
            }
        });
    }

    public static GrpcLongRunningClient create(OperationsStub operationsStub) {
        return new GrpcLongRunningClient(operationsStub);
    }
}
