package com.google.cloud.dialogflow.v2beta1;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.paging.AbstractFixedSizeCollection;
import com.google.api.gax.paging.AbstractPage;
import com.google.api.gax.paging.AbstractPagedListResponse;
import com.google.api.gax.rpc.PageContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStub;
import com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStubSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class KnowledgeBasesClient implements BackgroundResource {
    private final KnowledgeBasesSettings settings;
    private final KnowledgeBasesStub stub;

    public static final KnowledgeBasesClient create() throws IOException {
        return create(KnowledgeBasesSettings.newBuilder().build());
    }

    public static final KnowledgeBasesClient create(KnowledgeBasesSettings knowledgeBasesSettings) throws IOException {
        return new KnowledgeBasesClient(knowledgeBasesSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final KnowledgeBasesClient create(KnowledgeBasesStub knowledgeBasesStub) {
        return new KnowledgeBasesClient(knowledgeBasesStub);
    }

    protected KnowledgeBasesClient(KnowledgeBasesSettings knowledgeBasesSettings) throws IOException {
        this.settings = knowledgeBasesSettings;
        this.stub = ((KnowledgeBasesStubSettings) knowledgeBasesSettings.getStubSettings()).createStub();
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected KnowledgeBasesClient(KnowledgeBasesStub knowledgeBasesStub) {
        this.settings = null;
        this.stub = knowledgeBasesStub;
    }

    public final KnowledgeBasesSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public KnowledgeBasesStub getStub() {
        return this.stub;
    }

    public final ListKnowledgeBasesPagedResponse listKnowledgeBases(ProjectName projectName) {
        return listKnowledgeBases(ListKnowledgeBasesRequest.newBuilder().setParent(projectName == null ? null : projectName.toString()).build());
    }

    public final ListKnowledgeBasesPagedResponse listKnowledgeBases(String str) {
        return listKnowledgeBases(ListKnowledgeBasesRequest.newBuilder().setParent(str).build());
    }

    public final ListKnowledgeBasesPagedResponse listKnowledgeBases(ListKnowledgeBasesRequest listKnowledgeBasesRequest) {
        return listKnowledgeBasesPagedCallable().call(listKnowledgeBasesRequest);
    }

    public final UnaryCallable<ListKnowledgeBasesRequest, ListKnowledgeBasesPagedResponse> listKnowledgeBasesPagedCallable() {
        return this.stub.listKnowledgeBasesPagedCallable();
    }

    public final UnaryCallable<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse> listKnowledgeBasesCallable() {
        return this.stub.listKnowledgeBasesCallable();
    }

    public final KnowledgeBase getKnowledgeBase(KnowledgeBaseName knowledgeBaseName) {
        return getKnowledgeBase(GetKnowledgeBaseRequest.newBuilder().setName(knowledgeBaseName == null ? null : knowledgeBaseName.toString()).build());
    }

    public final KnowledgeBase getKnowledgeBase(String str) {
        return getKnowledgeBase(GetKnowledgeBaseRequest.newBuilder().setName(str).build());
    }

    public final KnowledgeBase getKnowledgeBase(GetKnowledgeBaseRequest getKnowledgeBaseRequest) {
        return getKnowledgeBaseCallable().call(getKnowledgeBaseRequest);
    }

    public final UnaryCallable<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseCallable() {
        return this.stub.getKnowledgeBaseCallable();
    }

    public final KnowledgeBase createKnowledgeBase(ProjectName projectName, KnowledgeBase knowledgeBase) {
        return createKnowledgeBase(CreateKnowledgeBaseRequest.newBuilder().setParent(projectName == null ? null : projectName.toString()).setKnowledgeBase(knowledgeBase).build());
    }

    public final KnowledgeBase createKnowledgeBase(String str, KnowledgeBase knowledgeBase) {
        return createKnowledgeBase(CreateKnowledgeBaseRequest.newBuilder().setParent(str).setKnowledgeBase(knowledgeBase).build());
    }

    public final KnowledgeBase createKnowledgeBase(CreateKnowledgeBaseRequest createKnowledgeBaseRequest) {
        return createKnowledgeBaseCallable().call(createKnowledgeBaseRequest);
    }

    public final UnaryCallable<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseCallable() {
        return this.stub.createKnowledgeBaseCallable();
    }

    public final void deleteKnowledgeBase(KnowledgeBaseName knowledgeBaseName) {
        deleteKnowledgeBase(DeleteKnowledgeBaseRequest.newBuilder().setName(knowledgeBaseName == null ? null : knowledgeBaseName.toString()).build());
    }

    public final void deleteKnowledgeBase(String str) {
        deleteKnowledgeBase(DeleteKnowledgeBaseRequest.newBuilder().setName(str).build());
    }

    public final void deleteKnowledgeBase(DeleteKnowledgeBaseRequest deleteKnowledgeBaseRequest) {
        deleteKnowledgeBaseCallable().call(deleteKnowledgeBaseRequest);
    }

    public final UnaryCallable<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseCallable() {
        return this.stub.deleteKnowledgeBaseCallable();
    }

    public final KnowledgeBase updateKnowledgeBase(KnowledgeBase knowledgeBase) {
        return updateKnowledgeBase(UpdateKnowledgeBaseRequest.newBuilder().setKnowledgeBase(knowledgeBase).build());
    }

    public final KnowledgeBase updateKnowledgeBase(UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest) {
        return updateKnowledgeBaseCallable().call(updateKnowledgeBaseRequest);
    }

    public final UnaryCallable<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseCallable() {
        return this.stub.updateKnowledgeBaseCallable();
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
    public static class ListKnowledgeBasesPagedResponse extends AbstractPagedListResponse<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase, ListKnowledgeBasesPage, ListKnowledgeBasesFixedSizeCollection> {
        public static ApiFuture<ListKnowledgeBasesPagedResponse> createAsync(PageContext<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase> pageContext, ApiFuture<ListKnowledgeBasesResponse> apiFuture) {
            return ApiFutures.transform(ListKnowledgeBasesPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<ListKnowledgeBasesPage, ListKnowledgeBasesPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.KnowledgeBasesClient.ListKnowledgeBasesPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public ListKnowledgeBasesPagedResponse apply(ListKnowledgeBasesPage listKnowledgeBasesPage) {
                    return new ListKnowledgeBasesPagedResponse(listKnowledgeBasesPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListKnowledgeBasesPagedResponse(ListKnowledgeBasesPage listKnowledgeBasesPage) {
            super(listKnowledgeBasesPage, ListKnowledgeBasesFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListKnowledgeBasesPage extends AbstractPage<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase, ListKnowledgeBasesPage> {
        static /* synthetic */ ListKnowledgeBasesPage access$000() {
            return createEmptyPage();
        }

        private ListKnowledgeBasesPage(PageContext<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase> pageContext, ListKnowledgeBasesResponse listKnowledgeBasesResponse) {
            super(pageContext, listKnowledgeBasesResponse);
        }

        private static ListKnowledgeBasesPage createEmptyPage() {
            return new ListKnowledgeBasesPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public ListKnowledgeBasesPage createPage(PageContext<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase> pageContext, ListKnowledgeBasesResponse listKnowledgeBasesResponse) {
            return new ListKnowledgeBasesPage(pageContext, listKnowledgeBasesResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<ListKnowledgeBasesPage> createPageAsync(PageContext<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase> pageContext, ApiFuture<ListKnowledgeBasesResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListKnowledgeBasesFixedSizeCollection extends AbstractFixedSizeCollection<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBase, ListKnowledgeBasesPage, ListKnowledgeBasesFixedSizeCollection> {
        static /* synthetic */ ListKnowledgeBasesFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private ListKnowledgeBasesFixedSizeCollection(List<ListKnowledgeBasesPage> list, int i) {
            super(list, i);
        }

        private static ListKnowledgeBasesFixedSizeCollection createEmptyCollection() {
            return new ListKnowledgeBasesFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public ListKnowledgeBasesFixedSizeCollection createCollection(List<ListKnowledgeBasesPage> list, int i) {
            return new ListKnowledgeBasesFixedSizeCollection(list, i);
        }
    }
}
