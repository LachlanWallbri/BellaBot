package com.google.cloud.dialogflow.p049v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.DeleteSessionEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.GetSessionEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesRequest;
import com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponse;
import com.google.cloud.dialogflow.p049v2.SessionEntityType;
import com.google.cloud.dialogflow.p049v2.SessionEntityTypesClient;
import com.google.cloud.dialogflow.p049v2.UpdateSessionEntityTypeRequest;
import com.google.protobuf.Empty;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes2.dex */
public abstract class SessionEntityTypesStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    public UnaryCallable<ListSessionEntityTypesRequest, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listSessionEntityTypesPagedCallable()");
    }

    public UnaryCallable<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse> listSessionEntityTypesCallable() {
        throw new UnsupportedOperationException("Not implemented: listSessionEntityTypesCallable()");
    }

    public UnaryCallable<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: getSessionEntityTypeCallable()");
    }

    public UnaryCallable<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: createSessionEntityTypeCallable()");
    }

    public UnaryCallable<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: updateSessionEntityTypeCallable()");
    }

    public UnaryCallable<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteSessionEntityTypeCallable()");
    }
}
