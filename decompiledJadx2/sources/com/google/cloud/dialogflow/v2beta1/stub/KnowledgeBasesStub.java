package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.GetKnowledgeBaseRequest;
import com.google.cloud.dialogflow.v2beta1.KnowledgeBase;
import com.google.cloud.dialogflow.v2beta1.KnowledgeBasesClient;
import com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesRequest;
import com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequest;
import com.google.protobuf.Empty;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public abstract class KnowledgeBasesStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    public UnaryCallable<ListKnowledgeBasesRequest, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listKnowledgeBasesPagedCallable()");
    }

    public UnaryCallable<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse> listKnowledgeBasesCallable() {
        throw new UnsupportedOperationException("Not implemented: listKnowledgeBasesCallable()");
    }

    public UnaryCallable<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseCallable() {
        throw new UnsupportedOperationException("Not implemented: getKnowledgeBaseCallable()");
    }

    public UnaryCallable<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseCallable() {
        throw new UnsupportedOperationException("Not implemented: createKnowledgeBaseCallable()");
    }

    public UnaryCallable<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteKnowledgeBaseCallable()");
    }

    public UnaryCallable<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseCallable() {
        throw new UnsupportedOperationException("Not implemented: updateKnowledgeBaseCallable()");
    }
}
