package com.google.longrunning.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.ListOperationsResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.longrunning.WaitOperationRequest;
import com.google.protobuf.Empty;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public abstract class OperationsStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    public UnaryCallable<GetOperationRequest, Operation> getOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: getOperationCallable()");
    }

    public UnaryCallable<ListOperationsRequest, OperationsClient.ListOperationsPagedResponse> listOperationsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listOperationsPagedCallable()");
    }

    public UnaryCallable<ListOperationsRequest, ListOperationsResponse> listOperationsCallable() {
        throw new UnsupportedOperationException("Not implemented: listOperationsCallable()");
    }

    public UnaryCallable<CancelOperationRequest, Empty> cancelOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: cancelOperationCallable()");
    }

    public UnaryCallable<DeleteOperationRequest, Empty> deleteOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteOperationCallable()");
    }

    public UnaryCallable<WaitOperationRequest, Operation> waitOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: waitOperationCallable()");
    }
}
