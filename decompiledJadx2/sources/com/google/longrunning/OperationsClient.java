package com.google.longrunning;

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
import com.google.common.util.concurrent.MoreExecutors;
import com.google.longrunning.stub.OperationsStub;
import com.google.longrunning.stub.OperationsStubSettings;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public class OperationsClient implements BackgroundResource {
    private final OperationsSettings settings;
    private final OperationsStub stub;

    public static final OperationsClient create(OperationsSettings operationsSettings) throws IOException {
        return new OperationsClient(operationsSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final OperationsClient create(OperationsStub operationsStub) {
        return new OperationsClient(operationsStub);
    }

    protected OperationsClient(OperationsSettings operationsSettings) throws IOException {
        this.settings = operationsSettings;
        this.stub = ((OperationsStubSettings) operationsSettings.getStubSettings()).createStub();
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected OperationsClient(OperationsStub operationsStub) {
        this.settings = null;
        this.stub = operationsStub;
    }

    public final OperationsSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public OperationsStub getStub() {
        return this.stub;
    }

    public final Operation getOperation(String str) {
        return getOperation(GetOperationRequest.newBuilder().setName(str).build());
    }

    private final Operation getOperation(GetOperationRequest getOperationRequest) {
        return getOperationCallable().call(getOperationRequest);
    }

    public final UnaryCallable<GetOperationRequest, Operation> getOperationCallable() {
        return this.stub.getOperationCallable();
    }

    public final ListOperationsPagedResponse listOperations(String str, String str2) {
        return listOperations(ListOperationsRequest.newBuilder().setName(str).setFilter(str2).build());
    }

    public final ListOperationsPagedResponse listOperations(ListOperationsRequest listOperationsRequest) {
        return listOperationsPagedCallable().call(listOperationsRequest);
    }

    public final UnaryCallable<ListOperationsRequest, ListOperationsPagedResponse> listOperationsPagedCallable() {
        return this.stub.listOperationsPagedCallable();
    }

    public final UnaryCallable<ListOperationsRequest, ListOperationsResponse> listOperationsCallable() {
        return this.stub.listOperationsCallable();
    }

    public final void cancelOperation(String str) {
        cancelOperation(CancelOperationRequest.newBuilder().setName(str).build());
    }

    private final void cancelOperation(CancelOperationRequest cancelOperationRequest) {
        cancelOperationCallable().call(cancelOperationRequest);
    }

    public final UnaryCallable<CancelOperationRequest, Empty> cancelOperationCallable() {
        return this.stub.cancelOperationCallable();
    }

    public final void deleteOperation(String str) {
        deleteOperation(DeleteOperationRequest.newBuilder().setName(str).build());
    }

    private final void deleteOperation(DeleteOperationRequest deleteOperationRequest) {
        deleteOperationCallable().call(deleteOperationRequest);
    }

    public final UnaryCallable<DeleteOperationRequest, Empty> deleteOperationCallable() {
        return this.stub.deleteOperationCallable();
    }

    public final Operation waitOperation(WaitOperationRequest waitOperationRequest) {
        return waitOperationCallable().call(waitOperationRequest);
    }

    public final UnaryCallable<WaitOperationRequest, Operation> waitOperationCallable() {
        return this.stub.waitOperationCallable();
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
    public static class ListOperationsPagedResponse extends AbstractPagedListResponse<ListOperationsRequest, ListOperationsResponse, Operation, ListOperationsPage, ListOperationsFixedSizeCollection> {
        public static ApiFuture<ListOperationsPagedResponse> createAsync(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> pageContext, ApiFuture<ListOperationsResponse> apiFuture) {
            return ApiFutures.transform(ListOperationsPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<ListOperationsPage, ListOperationsPagedResponse>() { // from class: com.google.longrunning.OperationsClient.ListOperationsPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public ListOperationsPagedResponse apply(ListOperationsPage listOperationsPage) {
                    return new ListOperationsPagedResponse(listOperationsPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListOperationsPagedResponse(ListOperationsPage listOperationsPage) {
            super(listOperationsPage, ListOperationsFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListOperationsPage extends AbstractPage<ListOperationsRequest, ListOperationsResponse, Operation, ListOperationsPage> {
        static /* synthetic */ ListOperationsPage access$000() {
            return createEmptyPage();
        }

        private ListOperationsPage(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> pageContext, ListOperationsResponse listOperationsResponse) {
            super(pageContext, listOperationsResponse);
        }

        private static ListOperationsPage createEmptyPage() {
            return new ListOperationsPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public ListOperationsPage createPage(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> pageContext, ListOperationsResponse listOperationsResponse) {
            return new ListOperationsPage(pageContext, listOperationsResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<ListOperationsPage> createPageAsync(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> pageContext, ApiFuture<ListOperationsResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class ListOperationsFixedSizeCollection extends AbstractFixedSizeCollection<ListOperationsRequest, ListOperationsResponse, Operation, ListOperationsPage, ListOperationsFixedSizeCollection> {
        static /* synthetic */ ListOperationsFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private ListOperationsFixedSizeCollection(List<ListOperationsPage> list, int i) {
            super(list, i);
        }

        private static ListOperationsFixedSizeCollection createEmptyCollection() {
            return new ListOperationsFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public ListOperationsFixedSizeCollection createCollection(List<ListOperationsPage> list, int i) {
            return new ListOperationsFixedSizeCollection(list, i);
        }
    }
}
