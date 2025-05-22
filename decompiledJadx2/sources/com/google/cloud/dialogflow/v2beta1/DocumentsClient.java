package com.google.cloud.dialogflow.v2beta1;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.paging.AbstractFixedSizeCollection;
import com.google.api.gax.paging.AbstractPage;
import com.google.api.gax.paging.AbstractPagedListResponse;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.PageContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.stub.DocumentsStub;
import com.google.cloud.dialogflow.v2beta1.stub.DocumentsStubSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class DocumentsClient implements BackgroundResource {
    private final OperationsClient operationsClient;
    private final DocumentsSettings settings;
    private final DocumentsStub stub;

    public static final DocumentsClient create() throws IOException {
        return create(DocumentsSettings.newBuilder().build());
    }

    public static final DocumentsClient create(DocumentsSettings documentsSettings) throws IOException {
        return new DocumentsClient(documentsSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final DocumentsClient create(DocumentsStub documentsStub) {
        return new DocumentsClient(documentsStub);
    }

    protected DocumentsClient(DocumentsSettings documentsSettings) throws IOException {
        this.settings = documentsSettings;
        this.stub = ((DocumentsStubSettings) documentsSettings.getStubSettings()).createStub();
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected DocumentsClient(DocumentsStub documentsStub) {
        this.settings = null;
        this.stub = documentsStub;
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    public final DocumentsSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public DocumentsStub getStub() {
        return this.stub;
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationsClient getOperationsClient() {
        return this.operationsClient;
    }

    public final ListDocumentsPagedResponse listDocuments(KnowledgeBaseName knowledgeBaseName) {
        return listDocuments(ListDocumentsRequest.newBuilder().setParent(knowledgeBaseName == null ? null : knowledgeBaseName.toString()).build());
    }

    public final ListDocumentsPagedResponse listDocuments(String str) {
        return listDocuments(ListDocumentsRequest.newBuilder().setParent(str).build());
    }

    public final ListDocumentsPagedResponse listDocuments(ListDocumentsRequest listDocumentsRequest) {
        return listDocumentsPagedCallable().call(listDocumentsRequest);
    }

    public final UnaryCallable<ListDocumentsRequest, ListDocumentsPagedResponse> listDocumentsPagedCallable() {
        return this.stub.listDocumentsPagedCallable();
    }

    public final UnaryCallable<ListDocumentsRequest, ListDocumentsResponse> listDocumentsCallable() {
        return this.stub.listDocumentsCallable();
    }

    public final Document getDocument(DocumentName documentName) {
        return getDocument(GetDocumentRequest.newBuilder().setName(documentName == null ? null : documentName.toString()).build());
    }

    public final Document getDocument(String str) {
        return getDocument(GetDocumentRequest.newBuilder().setName(str).build());
    }

    public final Document getDocument(GetDocumentRequest getDocumentRequest) {
        return getDocumentCallable().call(getDocumentRequest);
    }

    public final UnaryCallable<GetDocumentRequest, Document> getDocumentCallable() {
        return this.stub.getDocumentCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Document, KnowledgeOperationMetadata> createDocumentAsync(KnowledgeBaseName knowledgeBaseName, Document document) {
        return createDocumentAsync(CreateDocumentRequest.newBuilder().setParent(knowledgeBaseName == null ? null : knowledgeBaseName.toString()).setDocument(document).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Document, KnowledgeOperationMetadata> createDocumentAsync(String str, Document document) {
        return createDocumentAsync(CreateDocumentRequest.newBuilder().setParent(str).setDocument(document).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Document, KnowledgeOperationMetadata> createDocumentAsync(CreateDocumentRequest createDocumentRequest) {
        return createDocumentOperationCallable().futureCall(createDocumentRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationCallable() {
        return this.stub.createDocumentOperationCallable();
    }

    public final UnaryCallable<CreateDocumentRequest, Operation> createDocumentCallable() {
        return this.stub.createDocumentCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, KnowledgeOperationMetadata> deleteDocumentAsync(DocumentName documentName) {
        return deleteDocumentAsync(DeleteDocumentRequest.newBuilder().setName(documentName == null ? null : documentName.toString()).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, KnowledgeOperationMetadata> deleteDocumentAsync(String str) {
        return deleteDocumentAsync(DeleteDocumentRequest.newBuilder().setName(str).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, KnowledgeOperationMetadata> deleteDocumentAsync(DeleteDocumentRequest deleteDocumentRequest) {
        return deleteDocumentOperationCallable().futureCall(deleteDocumentRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationCallable() {
        return this.stub.deleteDocumentOperationCallable();
    }

    public final UnaryCallable<DeleteDocumentRequest, Operation> deleteDocumentCallable() {
        return this.stub.deleteDocumentCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Document, KnowledgeOperationMetadata> updateDocumentAsync(Document document) {
        return updateDocumentAsync(UpdateDocumentRequest.newBuilder().setDocument(document).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Document, KnowledgeOperationMetadata> updateDocumentAsync(UpdateDocumentRequest updateDocumentRequest) {
        return updateDocumentOperationCallable().futureCall(updateDocumentRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationCallable() {
        return this.stub.updateDocumentOperationCallable();
    }

    public final UnaryCallable<UpdateDocumentRequest, Operation> updateDocumentCallable() {
        return this.stub.updateDocumentCallable();
    }

    public final Operation reloadDocument(ReloadDocumentRequest reloadDocumentRequest) {
        return reloadDocumentCallable().call(reloadDocumentRequest);
    }

    public final UnaryCallable<ReloadDocumentRequest, Operation> reloadDocumentCallable() {
        return this.stub.reloadDocumentCallable();
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.stub.close();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        this.stub.shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return this.stub.isShutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return this.stub.isTerminated();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        this.stub.shutdownNow();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.stub.awaitTermination(j, timeUnit);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListDocumentsPagedResponse extends AbstractPagedListResponse<ListDocumentsRequest, ListDocumentsResponse, Document, ListDocumentsPage, ListDocumentsFixedSizeCollection> {
        public static ApiFuture<ListDocumentsPagedResponse> createAsync(PageContext<ListDocumentsRequest, ListDocumentsResponse, Document> pageContext, ApiFuture<ListDocumentsResponse> apiFuture) {
            return ApiFutures.transform(ListDocumentsPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<ListDocumentsPage, ListDocumentsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.DocumentsClient.ListDocumentsPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public ListDocumentsPagedResponse apply(ListDocumentsPage listDocumentsPage) {
                    return new ListDocumentsPagedResponse(listDocumentsPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListDocumentsPagedResponse(ListDocumentsPage listDocumentsPage) {
            super(listDocumentsPage, ListDocumentsFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListDocumentsPage extends AbstractPage<ListDocumentsRequest, ListDocumentsResponse, Document, ListDocumentsPage> {
        static /* synthetic */ ListDocumentsPage access$000() {
            return createEmptyPage();
        }

        private ListDocumentsPage(PageContext<ListDocumentsRequest, ListDocumentsResponse, Document> pageContext, ListDocumentsResponse listDocumentsResponse) {
            super(pageContext, listDocumentsResponse);
        }

        private static ListDocumentsPage createEmptyPage() {
            return new ListDocumentsPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public ListDocumentsPage createPage(PageContext<ListDocumentsRequest, ListDocumentsResponse, Document> pageContext, ListDocumentsResponse listDocumentsResponse) {
            return new ListDocumentsPage(pageContext, listDocumentsResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<ListDocumentsPage> createPageAsync(PageContext<ListDocumentsRequest, ListDocumentsResponse, Document> pageContext, ApiFuture<ListDocumentsResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListDocumentsFixedSizeCollection extends AbstractFixedSizeCollection<ListDocumentsRequest, ListDocumentsResponse, Document, ListDocumentsPage, ListDocumentsFixedSizeCollection> {
        static /* synthetic */ ListDocumentsFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private ListDocumentsFixedSizeCollection(List<ListDocumentsPage> list, int i) {
            super(list, i);
        }

        private static ListDocumentsFixedSizeCollection createEmptyCollection() {
            return new ListDocumentsFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public ListDocumentsFixedSizeCollection createCollection(List<ListDocumentsPage> list, int i) {
            return new ListDocumentsFixedSizeCollection(list, i);
        }
    }
}
