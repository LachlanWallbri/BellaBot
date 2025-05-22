package com.google.cloud.dialogflow.p049v2;

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
import com.google.cloud.dialogflow.p049v2.stub.IntentsStub;
import com.google.cloud.dialogflow.p049v2.stub.IntentsStubSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.protobuf.Empty;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class IntentsClient implements BackgroundResource {
    private final OperationsClient operationsClient;
    private final IntentsSettings settings;
    private final IntentsStub stub;

    public static final IntentsClient create() throws IOException {
        return create(IntentsSettings.newBuilder().build());
    }

    public static final IntentsClient create(IntentsSettings intentsSettings) throws IOException {
        return new IntentsClient(intentsSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final IntentsClient create(IntentsStub intentsStub) {
        return new IntentsClient(intentsStub);
    }

    protected IntentsClient(IntentsSettings intentsSettings) throws IOException {
        this.settings = intentsSettings;
        this.stub = ((IntentsStubSettings) intentsSettings.getStubSettings()).createStub();
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected IntentsClient(IntentsStub intentsStub) {
        this.settings = null;
        this.stub = intentsStub;
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    public final IntentsSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public IntentsStub getStub() {
        return this.stub;
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationsClient getOperationsClient() {
        return this.operationsClient;
    }

    public final ListIntentsPagedResponse listIntents(ProjectAgentName projectAgentName) {
        return listIntents(ListIntentsRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).build());
    }

    public final ListIntentsPagedResponse listIntents(String str) {
        return listIntents(ListIntentsRequest.newBuilder().setParent(str).build());
    }

    public final ListIntentsPagedResponse listIntents(ProjectAgentName projectAgentName, String str) {
        return listIntents(ListIntentsRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).setLanguageCode(str).build());
    }

    public final ListIntentsPagedResponse listIntents(String str, String str2) {
        return listIntents(ListIntentsRequest.newBuilder().setParent(str).setLanguageCode(str2).build());
    }

    public final ListIntentsPagedResponse listIntents(ListIntentsRequest listIntentsRequest) {
        return listIntentsPagedCallable().call(listIntentsRequest);
    }

    public final UnaryCallable<ListIntentsRequest, ListIntentsPagedResponse> listIntentsPagedCallable() {
        return this.stub.listIntentsPagedCallable();
    }

    public final UnaryCallable<ListIntentsRequest, ListIntentsResponse> listIntentsCallable() {
        return this.stub.listIntentsCallable();
    }

    public final Intent getIntent(IntentName intentName) {
        return getIntent(GetIntentRequest.newBuilder().setName(intentName == null ? null : intentName.toString()).build());
    }

    public final Intent getIntent(String str) {
        return getIntent(GetIntentRequest.newBuilder().setName(str).build());
    }

    public final Intent getIntent(IntentName intentName, String str) {
        return getIntent(GetIntentRequest.newBuilder().setName(intentName == null ? null : intentName.toString()).setLanguageCode(str).build());
    }

    public final Intent getIntent(String str, String str2) {
        return getIntent(GetIntentRequest.newBuilder().setName(str).setLanguageCode(str2).build());
    }

    public final Intent getIntent(GetIntentRequest getIntentRequest) {
        return getIntentCallable().call(getIntentRequest);
    }

    public final UnaryCallable<GetIntentRequest, Intent> getIntentCallable() {
        return this.stub.getIntentCallable();
    }

    public final Intent createIntent(ProjectAgentName projectAgentName, Intent intent) {
        return createIntent(CreateIntentRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).setIntent(intent).build());
    }

    public final Intent createIntent(String str, Intent intent) {
        return createIntent(CreateIntentRequest.newBuilder().setParent(str).setIntent(intent).build());
    }

    public final Intent createIntent(ProjectAgentName projectAgentName, Intent intent, String str) {
        return createIntent(CreateIntentRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).setIntent(intent).setLanguageCode(str).build());
    }

    public final Intent createIntent(String str, Intent intent, String str2) {
        return createIntent(CreateIntentRequest.newBuilder().setParent(str).setIntent(intent).setLanguageCode(str2).build());
    }

    public final Intent createIntent(CreateIntentRequest createIntentRequest) {
        return createIntentCallable().call(createIntentRequest);
    }

    public final UnaryCallable<CreateIntentRequest, Intent> createIntentCallable() {
        return this.stub.createIntentCallable();
    }

    public final Intent updateIntent(Intent intent, String str) {
        return updateIntent(UpdateIntentRequest.newBuilder().setIntent(intent).setLanguageCode(str).build());
    }

    public final Intent updateIntent(Intent intent, String str, FieldMask fieldMask) {
        return updateIntent(UpdateIntentRequest.newBuilder().setIntent(intent).setLanguageCode(str).setUpdateMask(fieldMask).build());
    }

    public final Intent updateIntent(UpdateIntentRequest updateIntentRequest) {
        return updateIntentCallable().call(updateIntentRequest);
    }

    public final UnaryCallable<UpdateIntentRequest, Intent> updateIntentCallable() {
        return this.stub.updateIntentCallable();
    }

    public final void deleteIntent(IntentName intentName) {
        deleteIntent(DeleteIntentRequest.newBuilder().setName(intentName == null ? null : intentName.toString()).build());
    }

    public final void deleteIntent(String str) {
        deleteIntent(DeleteIntentRequest.newBuilder().setName(str).build());
    }

    public final void deleteIntent(DeleteIntentRequest deleteIntentRequest) {
        deleteIntentCallable().call(deleteIntentRequest);
    }

    public final UnaryCallable<DeleteIntentRequest, Empty> deleteIntentCallable() {
        return this.stub.deleteIntentCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<BatchUpdateIntentsResponse, Struct> batchUpdateIntentsAsync(BatchUpdateIntentsRequest batchUpdateIntentsRequest) {
        return batchUpdateIntentsOperationCallable().futureCall(batchUpdateIntentsRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<BatchUpdateIntentsRequest, BatchUpdateIntentsResponse, Struct> batchUpdateIntentsOperationCallable() {
        return this.stub.batchUpdateIntentsOperationCallable();
    }

    public final UnaryCallable<BatchUpdateIntentsRequest, Operation> batchUpdateIntentsCallable() {
        return this.stub.batchUpdateIntentsCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteIntentsAsync(ProjectAgentName projectAgentName, List<Intent> list) {
        return batchDeleteIntentsAsync(BatchDeleteIntentsRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).addAllIntents(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteIntentsAsync(String str, List<Intent> list) {
        return batchDeleteIntentsAsync(BatchDeleteIntentsRequest.newBuilder().setParent(str).addAllIntents(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteIntentsAsync(BatchDeleteIntentsRequest batchDeleteIntentsRequest) {
        return batchDeleteIntentsOperationCallable().futureCall(batchDeleteIntentsRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<BatchDeleteIntentsRequest, Empty, Struct> batchDeleteIntentsOperationCallable() {
        return this.stub.batchDeleteIntentsOperationCallable();
    }

    public final UnaryCallable<BatchDeleteIntentsRequest, Operation> batchDeleteIntentsCallable() {
        return this.stub.batchDeleteIntentsCallable();
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
    public static class ListIntentsPagedResponse extends AbstractPagedListResponse<ListIntentsRequest, ListIntentsResponse, Intent, ListIntentsPage, ListIntentsFixedSizeCollection> {
        public static ApiFuture<ListIntentsPagedResponse> createAsync(PageContext<ListIntentsRequest, ListIntentsResponse, Intent> pageContext, ApiFuture<ListIntentsResponse> apiFuture) {
            return ApiFutures.transform(ListIntentsPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<ListIntentsPage, ListIntentsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2.IntentsClient.ListIntentsPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public ListIntentsPagedResponse apply(ListIntentsPage listIntentsPage) {
                    return new ListIntentsPagedResponse(listIntentsPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListIntentsPagedResponse(ListIntentsPage listIntentsPage) {
            super(listIntentsPage, ListIntentsFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ListIntentsPage extends AbstractPage<ListIntentsRequest, ListIntentsResponse, Intent, ListIntentsPage> {
        static /* synthetic */ ListIntentsPage access$000() {
            return createEmptyPage();
        }

        private ListIntentsPage(PageContext<ListIntentsRequest, ListIntentsResponse, Intent> pageContext, ListIntentsResponse listIntentsResponse) {
            super(pageContext, listIntentsResponse);
        }

        private static ListIntentsPage createEmptyPage() {
            return new ListIntentsPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public ListIntentsPage createPage(PageContext<ListIntentsRequest, ListIntentsResponse, Intent> pageContext, ListIntentsResponse listIntentsResponse) {
            return new ListIntentsPage(pageContext, listIntentsResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<ListIntentsPage> createPageAsync(PageContext<ListIntentsRequest, ListIntentsResponse, Intent> pageContext, ApiFuture<ListIntentsResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ListIntentsFixedSizeCollection extends AbstractFixedSizeCollection<ListIntentsRequest, ListIntentsResponse, Intent, ListIntentsPage, ListIntentsFixedSizeCollection> {
        static /* synthetic */ ListIntentsFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private ListIntentsFixedSizeCollection(List<ListIntentsPage> list, int i) {
            super(list, i);
        }

        private static ListIntentsFixedSizeCollection createEmptyCollection() {
            return new ListIntentsFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public ListIntentsFixedSizeCollection createCollection(List<ListIntentsPage> list, int i) {
            return new ListIntentsFixedSizeCollection(list, i);
        }
    }
}
