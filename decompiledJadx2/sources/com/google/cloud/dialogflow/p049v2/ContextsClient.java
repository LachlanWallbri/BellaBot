package com.google.cloud.dialogflow.p049v2;

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
import com.google.cloud.dialogflow.p049v2.stub.ContextsStub;
import com.google.cloud.dialogflow.p049v2.stub.ContextsStubSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class ContextsClient implements BackgroundResource {
    private final ContextsSettings settings;
    private final ContextsStub stub;

    public static final ContextsClient create() throws IOException {
        return create(ContextsSettings.newBuilder().build());
    }

    public static final ContextsClient create(ContextsSettings contextsSettings) throws IOException {
        return new ContextsClient(contextsSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final ContextsClient create(ContextsStub contextsStub) {
        return new ContextsClient(contextsStub);
    }

    protected ContextsClient(ContextsSettings contextsSettings) throws IOException {
        this.settings = contextsSettings;
        this.stub = ((ContextsStubSettings) contextsSettings.getStubSettings()).createStub();
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected ContextsClient(ContextsStub contextsStub) {
        this.settings = null;
        this.stub = contextsStub;
    }

    public final ContextsSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public ContextsStub getStub() {
        return this.stub;
    }

    public final ListContextsPagedResponse listContexts(SessionName sessionName) {
        return listContexts(ListContextsRequest.newBuilder().setParent(sessionName == null ? null : sessionName.toString()).build());
    }

    public final ListContextsPagedResponse listContexts(String str) {
        return listContexts(ListContextsRequest.newBuilder().setParent(str).build());
    }

    public final ListContextsPagedResponse listContexts(ListContextsRequest listContextsRequest) {
        return listContextsPagedCallable().call(listContextsRequest);
    }

    public final UnaryCallable<ListContextsRequest, ListContextsPagedResponse> listContextsPagedCallable() {
        return this.stub.listContextsPagedCallable();
    }

    public final UnaryCallable<ListContextsRequest, ListContextsResponse> listContextsCallable() {
        return this.stub.listContextsCallable();
    }

    public final Context getContext(ContextName contextName) {
        return getContext(GetContextRequest.newBuilder().setName(contextName == null ? null : contextName.toString()).build());
    }

    public final Context getContext(String str) {
        return getContext(GetContextRequest.newBuilder().setName(str).build());
    }

    public final Context getContext(GetContextRequest getContextRequest) {
        return getContextCallable().call(getContextRequest);
    }

    public final UnaryCallable<GetContextRequest, Context> getContextCallable() {
        return this.stub.getContextCallable();
    }

    public final Context createContext(SessionName sessionName, Context context) {
        return createContext(CreateContextRequest.newBuilder().setParent(sessionName == null ? null : sessionName.toString()).setContext(context).build());
    }

    public final Context createContext(String str, Context context) {
        return createContext(CreateContextRequest.newBuilder().setParent(str).setContext(context).build());
    }

    public final Context createContext(CreateContextRequest createContextRequest) {
        return createContextCallable().call(createContextRequest);
    }

    public final UnaryCallable<CreateContextRequest, Context> createContextCallable() {
        return this.stub.createContextCallable();
    }

    public final Context updateContext(Context context) {
        return updateContext(UpdateContextRequest.newBuilder().setContext(context).build());
    }

    public final Context updateContext(UpdateContextRequest updateContextRequest) {
        return updateContextCallable().call(updateContextRequest);
    }

    public final UnaryCallable<UpdateContextRequest, Context> updateContextCallable() {
        return this.stub.updateContextCallable();
    }

    public final void deleteContext(ContextName contextName) {
        deleteContext(DeleteContextRequest.newBuilder().setName(contextName == null ? null : contextName.toString()).build());
    }

    public final void deleteContext(String str) {
        deleteContext(DeleteContextRequest.newBuilder().setName(str).build());
    }

    public final void deleteContext(DeleteContextRequest deleteContextRequest) {
        deleteContextCallable().call(deleteContextRequest);
    }

    public final UnaryCallable<DeleteContextRequest, Empty> deleteContextCallable() {
        return this.stub.deleteContextCallable();
    }

    public final void deleteAllContexts(SessionName sessionName) {
        deleteAllContexts(DeleteAllContextsRequest.newBuilder().setParent(sessionName == null ? null : sessionName.toString()).build());
    }

    public final void deleteAllContexts(String str) {
        deleteAllContexts(DeleteAllContextsRequest.newBuilder().setParent(str).build());
    }

    public final void deleteAllContexts(DeleteAllContextsRequest deleteAllContextsRequest) {
        deleteAllContextsCallable().call(deleteAllContextsRequest);
    }

    public final UnaryCallable<DeleteAllContextsRequest, Empty> deleteAllContextsCallable() {
        return this.stub.deleteAllContextsCallable();
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
    /* loaded from: classes2.dex */
    public static class ListContextsPagedResponse extends AbstractPagedListResponse<ListContextsRequest, ListContextsResponse, Context, ListContextsPage, ListContextsFixedSizeCollection> {
        public static ApiFuture<ListContextsPagedResponse> createAsync(PageContext<ListContextsRequest, ListContextsResponse, Context> pageContext, ApiFuture<ListContextsResponse> apiFuture) {
            return ApiFutures.transform(ListContextsPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<ListContextsPage, ListContextsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2.ContextsClient.ListContextsPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public ListContextsPagedResponse apply(ListContextsPage listContextsPage) {
                    return new ListContextsPagedResponse(listContextsPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListContextsPagedResponse(ListContextsPage listContextsPage) {
            super(listContextsPage, ListContextsFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ListContextsPage extends AbstractPage<ListContextsRequest, ListContextsResponse, Context, ListContextsPage> {
        static /* synthetic */ ListContextsPage access$000() {
            return createEmptyPage();
        }

        private ListContextsPage(PageContext<ListContextsRequest, ListContextsResponse, Context> pageContext, ListContextsResponse listContextsResponse) {
            super(pageContext, listContextsResponse);
        }

        private static ListContextsPage createEmptyPage() {
            return new ListContextsPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public ListContextsPage createPage(PageContext<ListContextsRequest, ListContextsResponse, Context> pageContext, ListContextsResponse listContextsResponse) {
            return new ListContextsPage(pageContext, listContextsResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<ListContextsPage> createPageAsync(PageContext<ListContextsRequest, ListContextsResponse, Context> pageContext, ApiFuture<ListContextsResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ListContextsFixedSizeCollection extends AbstractFixedSizeCollection<ListContextsRequest, ListContextsResponse, Context, ListContextsPage, ListContextsFixedSizeCollection> {
        static /* synthetic */ ListContextsFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private ListContextsFixedSizeCollection(List<ListContextsPage> list, int i) {
            super(list, i);
        }

        private static ListContextsFixedSizeCollection createEmptyCollection() {
            return new ListContextsFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public ListContextsFixedSizeCollection createCollection(List<ListContextsPage> list, int i) {
            return new ListContextsFixedSizeCollection(list, i);
        }
    }
}
