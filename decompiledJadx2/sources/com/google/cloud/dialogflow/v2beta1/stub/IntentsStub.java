package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.BatchDeleteIntentsRequest;
import com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsRequest;
import com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponse;
import com.google.cloud.dialogflow.v2beta1.CreateIntentRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteIntentRequest;
import com.google.cloud.dialogflow.v2beta1.GetIntentRequest;
import com.google.cloud.dialogflow.v2beta1.Intent;
import com.google.cloud.dialogflow.v2beta1.IntentsClient;
import com.google.cloud.dialogflow.v2beta1.ListIntentsRequest;
import com.google.cloud.dialogflow.v2beta1.ListIntentsResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateIntentRequest;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public abstract class IntentsStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationsStub getOperationsStub() {
        throw new UnsupportedOperationException("Not implemented: getOperationsStub()");
    }

    public UnaryCallable<ListIntentsRequest, IntentsClient.ListIntentsPagedResponse> listIntentsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listIntentsPagedCallable()");
    }

    public UnaryCallable<ListIntentsRequest, ListIntentsResponse> listIntentsCallable() {
        throw new UnsupportedOperationException("Not implemented: listIntentsCallable()");
    }

    public UnaryCallable<GetIntentRequest, Intent> getIntentCallable() {
        throw new UnsupportedOperationException("Not implemented: getIntentCallable()");
    }

    public UnaryCallable<CreateIntentRequest, Intent> createIntentCallable() {
        throw new UnsupportedOperationException("Not implemented: createIntentCallable()");
    }

    public UnaryCallable<UpdateIntentRequest, Intent> updateIntentCallable() {
        throw new UnsupportedOperationException("Not implemented: updateIntentCallable()");
    }

    public UnaryCallable<DeleteIntentRequest, Empty> deleteIntentCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteIntentCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: batchUpdateIntentsOperationCallable()");
    }

    public UnaryCallable<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsCallable() {
        throw new UnsupportedOperationException("Not implemented: batchUpdateIntentsCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: batchDeleteIntentsOperationCallable()");
    }

    public UnaryCallable<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsCallable() {
        throw new UnsupportedOperationException("Not implemented: batchDeleteIntentsCallable()");
    }
}
