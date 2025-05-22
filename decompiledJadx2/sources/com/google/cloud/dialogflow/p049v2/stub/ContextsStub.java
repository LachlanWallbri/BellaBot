package com.google.cloud.dialogflow.p049v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.p049v2.Context;
import com.google.cloud.dialogflow.p049v2.ContextsClient;
import com.google.cloud.dialogflow.p049v2.CreateContextRequest;
import com.google.cloud.dialogflow.p049v2.DeleteAllContextsRequest;
import com.google.cloud.dialogflow.p049v2.DeleteContextRequest;
import com.google.cloud.dialogflow.p049v2.GetContextRequest;
import com.google.cloud.dialogflow.p049v2.ListContextsRequest;
import com.google.cloud.dialogflow.p049v2.ListContextsResponse;
import com.google.cloud.dialogflow.p049v2.UpdateContextRequest;
import com.google.protobuf.Empty;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes2.dex */
public abstract class ContextsStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    public UnaryCallable<ListContextsRequest, ContextsClient.ListContextsPagedResponse> listContextsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listContextsPagedCallable()");
    }

    public UnaryCallable<ListContextsRequest, ListContextsResponse> listContextsCallable() {
        throw new UnsupportedOperationException("Not implemented: listContextsCallable()");
    }

    public UnaryCallable<GetContextRequest, Context> getContextCallable() {
        throw new UnsupportedOperationException("Not implemented: getContextCallable()");
    }

    public UnaryCallable<CreateContextRequest, Context> createContextCallable() {
        throw new UnsupportedOperationException("Not implemented: createContextCallable()");
    }

    public UnaryCallable<UpdateContextRequest, Context> updateContextCallable() {
        throw new UnsupportedOperationException("Not implemented: updateContextCallable()");
    }

    public UnaryCallable<DeleteContextRequest, Empty> deleteContextCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteContextCallable()");
    }

    public UnaryCallable<DeleteAllContextsRequest, Empty> deleteAllContextsCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteAllContextsCallable()");
    }
}
