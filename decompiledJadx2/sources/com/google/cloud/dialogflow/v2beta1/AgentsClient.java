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
import com.google.cloud.dialogflow.v2beta1.stub.AgentsStub;
import com.google.cloud.dialogflow.v2beta1.stub.AgentsStubSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class AgentsClient implements BackgroundResource {
    private final OperationsClient operationsClient;
    private final AgentsSettings settings;
    private final AgentsStub stub;

    public static final AgentsClient create() throws IOException {
        return create(AgentsSettings.newBuilder().build());
    }

    public static final AgentsClient create(AgentsSettings agentsSettings) throws IOException {
        return new AgentsClient(agentsSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final AgentsClient create(AgentsStub agentsStub) {
        return new AgentsClient(agentsStub);
    }

    protected AgentsClient(AgentsSettings agentsSettings) throws IOException {
        this.settings = agentsSettings;
        this.stub = ((AgentsStubSettings) agentsSettings.getStubSettings()).createStub();
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected AgentsClient(AgentsStub agentsStub) {
        this.settings = null;
        this.stub = agentsStub;
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    public final AgentsSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public AgentsStub getStub() {
        return this.stub;
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationsClient getOperationsClient() {
        return this.operationsClient;
    }

    public final Agent setAgent(Agent agent) {
        return setAgent(SetAgentRequest.newBuilder().setAgent(agent).build());
    }

    public final Agent setAgent(SetAgentRequest setAgentRequest) {
        return setAgentCallable().call(setAgentRequest);
    }

    public final UnaryCallable<SetAgentRequest, Agent> setAgentCallable() {
        return this.stub.setAgentCallable();
    }

    public final void deleteAgent(ProjectName projectName) {
        deleteAgent(DeleteAgentRequest.newBuilder().setParent(projectName == null ? null : projectName.toString()).build());
    }

    public final void deleteAgent(String str) {
        deleteAgent(DeleteAgentRequest.newBuilder().setParent(str).build());
    }

    public final void deleteAgent(DeleteAgentRequest deleteAgentRequest) {
        deleteAgentCallable().call(deleteAgentRequest);
    }

    public final UnaryCallable<DeleteAgentRequest, Empty> deleteAgentCallable() {
        return this.stub.deleteAgentCallable();
    }

    public final Agent getAgent(ProjectName projectName) {
        return getAgent(GetAgentRequest.newBuilder().setParent(projectName == null ? null : projectName.toString()).build());
    }

    public final Agent getAgent(String str) {
        return getAgent(GetAgentRequest.newBuilder().setParent(str).build());
    }

    public final Agent getAgent(GetAgentRequest getAgentRequest) {
        return getAgentCallable().call(getAgentRequest);
    }

    public final UnaryCallable<GetAgentRequest, Agent> getAgentCallable() {
        return this.stub.getAgentCallable();
    }

    public final SearchAgentsPagedResponse searchAgents(ProjectName projectName) {
        return searchAgents(SearchAgentsRequest.newBuilder().setParent(projectName == null ? null : projectName.toString()).build());
    }

    public final SearchAgentsPagedResponse searchAgents(String str) {
        return searchAgents(SearchAgentsRequest.newBuilder().setParent(str).build());
    }

    public final SearchAgentsPagedResponse searchAgents(SearchAgentsRequest searchAgentsRequest) {
        return searchAgentsPagedCallable().call(searchAgentsRequest);
    }

    public final UnaryCallable<SearchAgentsRequest, SearchAgentsPagedResponse> searchAgentsPagedCallable() {
        return this.stub.searchAgentsPagedCallable();
    }

    public final UnaryCallable<SearchAgentsRequest, SearchAgentsResponse> searchAgentsCallable() {
        return this.stub.searchAgentsCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> trainAgentAsync(ProjectName projectName) {
        return trainAgentAsync(TrainAgentRequest.newBuilder().setParent(projectName == null ? null : projectName.toString()).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> trainAgentAsync(String str) {
        return trainAgentAsync(TrainAgentRequest.newBuilder().setParent(str).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> trainAgentAsync(TrainAgentRequest trainAgentRequest) {
        return trainAgentOperationCallable().futureCall(trainAgentRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<TrainAgentRequest, Empty, Struct> trainAgentOperationCallable() {
        return this.stub.trainAgentOperationCallable();
    }

    public final UnaryCallable<TrainAgentRequest, Operation> trainAgentCallable() {
        return this.stub.trainAgentCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<ExportAgentResponse, Struct> exportAgentAsync(ProjectName projectName) {
        return exportAgentAsync(ExportAgentRequest.newBuilder().setParent(projectName == null ? null : projectName.toString()).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<ExportAgentResponse, Struct> exportAgentAsync(String str) {
        return exportAgentAsync(ExportAgentRequest.newBuilder().setParent(str).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<ExportAgentResponse, Struct> exportAgentAsync(ExportAgentRequest exportAgentRequest) {
        return exportAgentOperationCallable().futureCall(exportAgentRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationCallable() {
        return this.stub.exportAgentOperationCallable();
    }

    public final UnaryCallable<ExportAgentRequest, Operation> exportAgentCallable() {
        return this.stub.exportAgentCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> importAgentAsync(ImportAgentRequest importAgentRequest) {
        return importAgentOperationCallable().futureCall(importAgentRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<ImportAgentRequest, Empty, Struct> importAgentOperationCallable() {
        return this.stub.importAgentOperationCallable();
    }

    public final UnaryCallable<ImportAgentRequest, Operation> importAgentCallable() {
        return this.stub.importAgentCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> restoreAgentAsync(RestoreAgentRequest restoreAgentRequest) {
        return restoreAgentOperationCallable().futureCall(restoreAgentRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<RestoreAgentRequest, Empty, Struct> restoreAgentOperationCallable() {
        return this.stub.restoreAgentOperationCallable();
    }

    public final UnaryCallable<RestoreAgentRequest, Operation> restoreAgentCallable() {
        return this.stub.restoreAgentCallable();
    }

    public final ValidationResult getValidationResult(GetValidationResultRequest getValidationResultRequest) {
        return getValidationResultCallable().call(getValidationResultRequest);
    }

    public final UnaryCallable<GetValidationResultRequest, ValidationResult> getValidationResultCallable() {
        return this.stub.getValidationResultCallable();
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
    public static class SearchAgentsPagedResponse extends AbstractPagedListResponse<SearchAgentsRequest, SearchAgentsResponse, Agent, SearchAgentsPage, SearchAgentsFixedSizeCollection> {
        public static ApiFuture<SearchAgentsPagedResponse> createAsync(PageContext<SearchAgentsRequest, SearchAgentsResponse, Agent> pageContext, ApiFuture<SearchAgentsResponse> apiFuture) {
            return ApiFutures.transform(SearchAgentsPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<SearchAgentsPage, SearchAgentsPagedResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.AgentsClient.SearchAgentsPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public SearchAgentsPagedResponse apply(SearchAgentsPage searchAgentsPage) {
                    return new SearchAgentsPagedResponse(searchAgentsPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private SearchAgentsPagedResponse(SearchAgentsPage searchAgentsPage) {
            super(searchAgentsPage, SearchAgentsFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class SearchAgentsPage extends AbstractPage<SearchAgentsRequest, SearchAgentsResponse, Agent, SearchAgentsPage> {
        static /* synthetic */ SearchAgentsPage access$000() {
            return createEmptyPage();
        }

        private SearchAgentsPage(PageContext<SearchAgentsRequest, SearchAgentsResponse, Agent> pageContext, SearchAgentsResponse searchAgentsResponse) {
            super(pageContext, searchAgentsResponse);
        }

        private static SearchAgentsPage createEmptyPage() {
            return new SearchAgentsPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public SearchAgentsPage createPage(PageContext<SearchAgentsRequest, SearchAgentsResponse, Agent> pageContext, SearchAgentsResponse searchAgentsResponse) {
            return new SearchAgentsPage(pageContext, searchAgentsResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<SearchAgentsPage> createPageAsync(PageContext<SearchAgentsRequest, SearchAgentsResponse, Agent> pageContext, ApiFuture<SearchAgentsResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class SearchAgentsFixedSizeCollection extends AbstractFixedSizeCollection<SearchAgentsRequest, SearchAgentsResponse, Agent, SearchAgentsPage, SearchAgentsFixedSizeCollection> {
        static /* synthetic */ SearchAgentsFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private SearchAgentsFixedSizeCollection(List<SearchAgentsPage> list, int i) {
            super(list, i);
        }

        private static SearchAgentsFixedSizeCollection createEmptyCollection() {
            return new SearchAgentsFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public SearchAgentsFixedSizeCollection createCollection(List<SearchAgentsPage> list, int i) {
            return new SearchAgentsFixedSizeCollection(list, i);
        }
    }
}
