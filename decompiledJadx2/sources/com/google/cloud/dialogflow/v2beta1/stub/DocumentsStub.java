package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.CreateDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.Document;
import com.google.cloud.dialogflow.v2beta1.DocumentsClient;
import com.google.cloud.dialogflow.v2beta1.GetDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadata;
import com.google.cloud.dialogflow.v2beta1.ListDocumentsRequest;
import com.google.cloud.dialogflow.v2beta1.ListDocumentsResponse;
import com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequest;
import com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequest;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import com.google.protobuf.Empty;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public abstract class DocumentsStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationsStub getOperationsStub() {
        throw new UnsupportedOperationException("Not implemented: getOperationsStub()");
    }

    public UnaryCallable<ListDocumentsRequest, DocumentsClient.ListDocumentsPagedResponse> listDocumentsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listDocumentsPagedCallable()");
    }

    public UnaryCallable<ListDocumentsRequest, ListDocumentsResponse> listDocumentsCallable() {
        throw new UnsupportedOperationException("Not implemented: listDocumentsCallable()");
    }

    public UnaryCallable<GetDocumentRequest, Document> getDocumentCallable() {
        throw new UnsupportedOperationException("Not implemented: getDocumentCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: createDocumentOperationCallable()");
    }

    public UnaryCallable<CreateDocumentRequest, Operation> createDocumentCallable() {
        throw new UnsupportedOperationException("Not implemented: createDocumentCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteDocumentOperationCallable()");
    }

    public UnaryCallable<DeleteDocumentRequest, Operation> deleteDocumentCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteDocumentCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: updateDocumentOperationCallable()");
    }

    public UnaryCallable<UpdateDocumentRequest, Operation> updateDocumentCallable() {
        throw new UnsupportedOperationException("Not implemented: updateDocumentCallable()");
    }

    public UnaryCallable<ReloadDocumentRequest, Operation> reloadDocumentCallable() {
        throw new UnsupportedOperationException("Not implemented: reloadDocumentCallable()");
    }
}
