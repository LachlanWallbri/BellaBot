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
import com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStub;
import com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStubSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class SessionEntityTypesClient implements BackgroundResource {
    private final SessionEntityTypesSettings settings;
    private final SessionEntityTypesStub stub;

    public static final SessionEntityTypesClient create() throws IOException {
        return create(SessionEntityTypesSettings.newBuilder().build());
    }

    public static final SessionEntityTypesClient create(SessionEntityTypesSettings sessionEntityTypesSettings) throws IOException {
        return new SessionEntityTypesClient(sessionEntityTypesSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final SessionEntityTypesClient create(SessionEntityTypesStub sessionEntityTypesStub) {
        return new SessionEntityTypesClient(sessionEntityTypesStub);
    }

    protected SessionEntityTypesClient(SessionEntityTypesSettings sessionEntityTypesSettings) throws IOException {
        this.settings = sessionEntityTypesSettings;
        this.stub = ((SessionEntityTypesStubSettings) sessionEntityTypesSettings.getStubSettings()).createStub();
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected SessionEntityTypesClient(SessionEntityTypesStub sessionEntityTypesStub) {
        this.settings = null;
        this.stub = sessionEntityTypesStub;
    }

    public final SessionEntityTypesSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public SessionEntityTypesStub getStub() {
        return this.stub;
    }

    public final ListSessionEntityTypesPagedResponse listSessionEntityTypes(SessionName sessionName) {
        return listSessionEntityTypes(ListSessionEntityTypesRequest.newBuilder().setParent(sessionName == null ? null : sessionName.toString()).build());
    }

    public final ListSessionEntityTypesPagedResponse listSessionEntityTypes(String str) {
        return listSessionEntityTypes(ListSessionEntityTypesRequest.newBuilder().setParent(str).build());
    }

    public final ListSessionEntityTypesPagedResponse listSessionEntityTypes(ListSessionEntityTypesRequest listSessionEntityTypesRequest) {
        return listSessionEntityTypesPagedCallable().call(listSessionEntityTypesRequest);
    }

    public final UnaryCallable<ListSessionEntityTypesRequest, ListSessionEntityTypesPagedResponse> listSessionEntityTypesPagedCallable() {
        return this.stub.listSessionEntityTypesPagedCallable();
    }

    public final UnaryCallable<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse> listSessionEntityTypesCallable() {
        return this.stub.listSessionEntityTypesCallable();
    }

    public final SessionEntityType getSessionEntityType(SessionEntityTypeName sessionEntityTypeName) {
        return getSessionEntityType(GetSessionEntityTypeRequest.newBuilder().setName(sessionEntityTypeName == null ? null : sessionEntityTypeName.toString()).build());
    }

    public final SessionEntityType getSessionEntityType(String str) {
        return getSessionEntityType(GetSessionEntityTypeRequest.newBuilder().setName(str).build());
    }

    public final SessionEntityType getSessionEntityType(GetSessionEntityTypeRequest getSessionEntityTypeRequest) {
        return getSessionEntityTypeCallable().call(getSessionEntityTypeRequest);
    }

    public final UnaryCallable<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeCallable() {
        return this.stub.getSessionEntityTypeCallable();
    }

    public final SessionEntityType createSessionEntityType(SessionName sessionName, SessionEntityType sessionEntityType) {
        return createSessionEntityType(CreateSessionEntityTypeRequest.newBuilder().setParent(sessionName == null ? null : sessionName.toString()).setSessionEntityType(sessionEntityType).build());
    }

    public final SessionEntityType createSessionEntityType(String str, SessionEntityType sessionEntityType) {
        return createSessionEntityType(CreateSessionEntityTypeRequest.newBuilder().setParent(str).setSessionEntityType(sessionEntityType).build());
    }

    public final SessionEntityType createSessionEntityType(CreateSessionEntityTypeRequest createSessionEntityTypeRequest) {
        return createSessionEntityTypeCallable().call(createSessionEntityTypeRequest);
    }

    public final UnaryCallable<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeCallable() {
        return this.stub.createSessionEntityTypeCallable();
    }

    public final SessionEntityType updateSessionEntityType(SessionEntityType sessionEntityType) {
        return updateSessionEntityType(UpdateSessionEntityTypeRequest.newBuilder().setSessionEntityType(sessionEntityType).build());
    }

    public final SessionEntityType updateSessionEntityType(UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest) {
        return updateSessionEntityTypeCallable().call(updateSessionEntityTypeRequest);
    }

    public final UnaryCallable<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeCallable() {
        return this.stub.updateSessionEntityTypeCallable();
    }

    public final void deleteSessionEntityType(SessionEntityTypeName sessionEntityTypeName) {
        deleteSessionEntityType(DeleteSessionEntityTypeRequest.newBuilder().setName(sessionEntityTypeName == null ? null : sessionEntityTypeName.toString()).build());
    }

    public final void deleteSessionEntityType(String str) {
        deleteSessionEntityType(DeleteSessionEntityTypeRequest.newBuilder().setName(str).build());
    }

    public final void deleteSessionEntityType(DeleteSessionEntityTypeRequest deleteSessionEntityTypeRequest) {
        deleteSessionEntityTypeCallable().call(deleteSessionEntityTypeRequest);
    }

    public final UnaryCallable<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeCallable() {
        return this.stub.deleteSessionEntityTypeCallable();
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
    public static class ListSessionEntityTypesPagedResponse extends AbstractPagedListResponse<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType, ListSessionEntityTypesPage, ListSessionEntityTypesFixedSizeCollection> {
        public static ApiFuture<ListSessionEntityTypesPagedResponse> createAsync(PageContext<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType> pageContext, ApiFuture<ListSessionEntityTypesResponse> apiFuture) {
            return ApiFutures.transform(ListSessionEntityTypesPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<ListSessionEntityTypesPage, ListSessionEntityTypesPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.SessionEntityTypesClient.ListSessionEntityTypesPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public ListSessionEntityTypesPagedResponse apply(ListSessionEntityTypesPage listSessionEntityTypesPage) {
                    return new ListSessionEntityTypesPagedResponse(listSessionEntityTypesPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListSessionEntityTypesPagedResponse(ListSessionEntityTypesPage listSessionEntityTypesPage) {
            super(listSessionEntityTypesPage, ListSessionEntityTypesFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListSessionEntityTypesPage extends AbstractPage<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType, ListSessionEntityTypesPage> {
        static /* synthetic */ ListSessionEntityTypesPage access$000() {
            return createEmptyPage();
        }

        private ListSessionEntityTypesPage(PageContext<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType> pageContext, ListSessionEntityTypesResponse listSessionEntityTypesResponse) {
            super(pageContext, listSessionEntityTypesResponse);
        }

        private static ListSessionEntityTypesPage createEmptyPage() {
            return new ListSessionEntityTypesPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public ListSessionEntityTypesPage createPage(PageContext<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType> pageContext, ListSessionEntityTypesResponse listSessionEntityTypesResponse) {
            return new ListSessionEntityTypesPage(pageContext, listSessionEntityTypesResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<ListSessionEntityTypesPage> createPageAsync(PageContext<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType> pageContext, ApiFuture<ListSessionEntityTypesResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListSessionEntityTypesFixedSizeCollection extends AbstractFixedSizeCollection<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityType, ListSessionEntityTypesPage, ListSessionEntityTypesFixedSizeCollection> {
        static /* synthetic */ ListSessionEntityTypesFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private ListSessionEntityTypesFixedSizeCollection(List<ListSessionEntityTypesPage> list, int i) {
            super(list, i);
        }

        private static ListSessionEntityTypesFixedSizeCollection createEmptyCollection() {
            return new ListSessionEntityTypesFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public ListSessionEntityTypesFixedSizeCollection createCollection(List<ListSessionEntityTypesPage> list, int i) {
            return new ListSessionEntityTypesFixedSizeCollection(list, i);
        }
    }
}
