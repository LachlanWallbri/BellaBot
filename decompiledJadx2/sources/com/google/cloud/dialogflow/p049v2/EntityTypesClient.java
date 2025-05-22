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
import com.google.cloud.dialogflow.p049v2.EntityType;
import com.google.cloud.dialogflow.p049v2.stub.EntityTypesStub;
import com.google.cloud.dialogflow.p049v2.stub.EntityTypesStubSettings;
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
/* loaded from: classes2.dex */
public class EntityTypesClient implements BackgroundResource {
    private final OperationsClient operationsClient;
    private final EntityTypesSettings settings;
    private final EntityTypesStub stub;

    public static final EntityTypesClient create() throws IOException {
        return create(EntityTypesSettings.newBuilder().build());
    }

    public static final EntityTypesClient create(EntityTypesSettings entityTypesSettings) throws IOException {
        return new EntityTypesClient(entityTypesSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final EntityTypesClient create(EntityTypesStub entityTypesStub) {
        return new EntityTypesClient(entityTypesStub);
    }

    protected EntityTypesClient(EntityTypesSettings entityTypesSettings) throws IOException {
        this.settings = entityTypesSettings;
        this.stub = ((EntityTypesStubSettings) entityTypesSettings.getStubSettings()).createStub();
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected EntityTypesClient(EntityTypesStub entityTypesStub) {
        this.settings = null;
        this.stub = entityTypesStub;
        this.operationsClient = OperationsClient.create(this.stub.getOperationsStub());
    }

    public final EntityTypesSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public EntityTypesStub getStub() {
        return this.stub;
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationsClient getOperationsClient() {
        return this.operationsClient;
    }

    public final ListEntityTypesPagedResponse listEntityTypes(ProjectAgentName projectAgentName) {
        return listEntityTypes(ListEntityTypesRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).build());
    }

    public final ListEntityTypesPagedResponse listEntityTypes(String str) {
        return listEntityTypes(ListEntityTypesRequest.newBuilder().setParent(str).build());
    }

    public final ListEntityTypesPagedResponse listEntityTypes(ProjectAgentName projectAgentName, String str) {
        return listEntityTypes(ListEntityTypesRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).setLanguageCode(str).build());
    }

    public final ListEntityTypesPagedResponse listEntityTypes(String str, String str2) {
        return listEntityTypes(ListEntityTypesRequest.newBuilder().setParent(str).setLanguageCode(str2).build());
    }

    public final ListEntityTypesPagedResponse listEntityTypes(ListEntityTypesRequest listEntityTypesRequest) {
        return listEntityTypesPagedCallable().call(listEntityTypesRequest);
    }

    public final UnaryCallable<ListEntityTypesRequest, ListEntityTypesPagedResponse> listEntityTypesPagedCallable() {
        return this.stub.listEntityTypesPagedCallable();
    }

    public final UnaryCallable<ListEntityTypesRequest, ListEntityTypesResponse> listEntityTypesCallable() {
        return this.stub.listEntityTypesCallable();
    }

    public final EntityType getEntityType(EntityTypeName entityTypeName) {
        return getEntityType(GetEntityTypeRequest.newBuilder().setName(entityTypeName == null ? null : entityTypeName.toString()).build());
    }

    public final EntityType getEntityType(String str) {
        return getEntityType(GetEntityTypeRequest.newBuilder().setName(str).build());
    }

    public final EntityType getEntityType(EntityTypeName entityTypeName, String str) {
        return getEntityType(GetEntityTypeRequest.newBuilder().setName(entityTypeName == null ? null : entityTypeName.toString()).setLanguageCode(str).build());
    }

    public final EntityType getEntityType(String str, String str2) {
        return getEntityType(GetEntityTypeRequest.newBuilder().setName(str).setLanguageCode(str2).build());
    }

    public final EntityType getEntityType(GetEntityTypeRequest getEntityTypeRequest) {
        return getEntityTypeCallable().call(getEntityTypeRequest);
    }

    public final UnaryCallable<GetEntityTypeRequest, EntityType> getEntityTypeCallable() {
        return this.stub.getEntityTypeCallable();
    }

    public final EntityType createEntityType(ProjectAgentName projectAgentName, EntityType entityType) {
        return createEntityType(CreateEntityTypeRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).setEntityType(entityType).build());
    }

    public final EntityType createEntityType(String str, EntityType entityType) {
        return createEntityType(CreateEntityTypeRequest.newBuilder().setParent(str).setEntityType(entityType).build());
    }

    public final EntityType createEntityType(ProjectAgentName projectAgentName, EntityType entityType, String str) {
        return createEntityType(CreateEntityTypeRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).setEntityType(entityType).setLanguageCode(str).build());
    }

    public final EntityType createEntityType(String str, EntityType entityType, String str2) {
        return createEntityType(CreateEntityTypeRequest.newBuilder().setParent(str).setEntityType(entityType).setLanguageCode(str2).build());
    }

    public final EntityType createEntityType(CreateEntityTypeRequest createEntityTypeRequest) {
        return createEntityTypeCallable().call(createEntityTypeRequest);
    }

    public final UnaryCallable<CreateEntityTypeRequest, EntityType> createEntityTypeCallable() {
        return this.stub.createEntityTypeCallable();
    }

    public final EntityType updateEntityType(EntityType entityType) {
        return updateEntityType(UpdateEntityTypeRequest.newBuilder().setEntityType(entityType).build());
    }

    public final EntityType updateEntityType(EntityType entityType, String str) {
        return updateEntityType(UpdateEntityTypeRequest.newBuilder().setEntityType(entityType).setLanguageCode(str).build());
    }

    public final EntityType updateEntityType(UpdateEntityTypeRequest updateEntityTypeRequest) {
        return updateEntityTypeCallable().call(updateEntityTypeRequest);
    }

    public final UnaryCallable<UpdateEntityTypeRequest, EntityType> updateEntityTypeCallable() {
        return this.stub.updateEntityTypeCallable();
    }

    public final void deleteEntityType(EntityTypeName entityTypeName) {
        deleteEntityType(DeleteEntityTypeRequest.newBuilder().setName(entityTypeName == null ? null : entityTypeName.toString()).build());
    }

    public final void deleteEntityType(String str) {
        deleteEntityType(DeleteEntityTypeRequest.newBuilder().setName(str).build());
    }

    public final void deleteEntityType(DeleteEntityTypeRequest deleteEntityTypeRequest) {
        deleteEntityTypeCallable().call(deleteEntityTypeRequest);
    }

    public final UnaryCallable<DeleteEntityTypeRequest, Empty> deleteEntityTypeCallable() {
        return this.stub.deleteEntityTypeCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesAsync(BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest) {
        return batchUpdateEntityTypesOperationCallable().futureCall(batchUpdateEntityTypesRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationCallable() {
        return this.stub.batchUpdateEntityTypesOperationCallable();
    }

    public final UnaryCallable<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesCallable() {
        return this.stub.batchUpdateEntityTypesCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntityTypesAsync(ProjectAgentName projectAgentName, List<String> list) {
        return batchDeleteEntityTypesAsync(BatchDeleteEntityTypesRequest.newBuilder().setParent(projectAgentName == null ? null : projectAgentName.toString()).addAllEntityTypeNames(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntityTypesAsync(String str, List<String> list) {
        return batchDeleteEntityTypesAsync(BatchDeleteEntityTypesRequest.newBuilder().setParent(str).addAllEntityTypeNames(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntityTypesAsync(BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest) {
        return batchDeleteEntityTypesOperationCallable().futureCall(batchDeleteEntityTypesRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationCallable() {
        return this.stub.batchDeleteEntityTypesOperationCallable();
    }

    public final UnaryCallable<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesCallable() {
        return this.stub.batchDeleteEntityTypesCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchCreateEntitiesAsync(EntityTypeName entityTypeName, List<EntityType.Entity> list) {
        return batchCreateEntitiesAsync(BatchCreateEntitiesRequest.newBuilder().setParent(entityTypeName == null ? null : entityTypeName.toString()).addAllEntities(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchCreateEntitiesAsync(String str, List<EntityType.Entity> list) {
        return batchCreateEntitiesAsync(BatchCreateEntitiesRequest.newBuilder().setParent(str).addAllEntities(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchCreateEntitiesAsync(EntityTypeName entityTypeName, List<EntityType.Entity> list, String str) {
        return batchCreateEntitiesAsync(BatchCreateEntitiesRequest.newBuilder().setParent(entityTypeName == null ? null : entityTypeName.toString()).addAllEntities(list).setLanguageCode(str).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchCreateEntitiesAsync(String str, List<EntityType.Entity> list, String str2) {
        return batchCreateEntitiesAsync(BatchCreateEntitiesRequest.newBuilder().setParent(str).addAllEntities(list).setLanguageCode(str2).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchCreateEntitiesAsync(BatchCreateEntitiesRequest batchCreateEntitiesRequest) {
        return batchCreateEntitiesOperationCallable().futureCall(batchCreateEntitiesRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationCallable() {
        return this.stub.batchCreateEntitiesOperationCallable();
    }

    public final UnaryCallable<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesCallable() {
        return this.stub.batchCreateEntitiesCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchUpdateEntitiesAsync(EntityTypeName entityTypeName, List<EntityType.Entity> list) {
        return batchUpdateEntitiesAsync(BatchUpdateEntitiesRequest.newBuilder().setParent(entityTypeName == null ? null : entityTypeName.toString()).addAllEntities(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchUpdateEntitiesAsync(String str, List<EntityType.Entity> list) {
        return batchUpdateEntitiesAsync(BatchUpdateEntitiesRequest.newBuilder().setParent(str).addAllEntities(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchUpdateEntitiesAsync(EntityTypeName entityTypeName, List<EntityType.Entity> list, String str) {
        return batchUpdateEntitiesAsync(BatchUpdateEntitiesRequest.newBuilder().setParent(entityTypeName == null ? null : entityTypeName.toString()).addAllEntities(list).setLanguageCode(str).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchUpdateEntitiesAsync(String str, List<EntityType.Entity> list, String str2) {
        return batchUpdateEntitiesAsync(BatchUpdateEntitiesRequest.newBuilder().setParent(str).addAllEntities(list).setLanguageCode(str2).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchUpdateEntitiesAsync(BatchUpdateEntitiesRequest batchUpdateEntitiesRequest) {
        return batchUpdateEntitiesOperationCallable().futureCall(batchUpdateEntitiesRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationCallable() {
        return this.stub.batchUpdateEntitiesOperationCallable();
    }

    public final UnaryCallable<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesCallable() {
        return this.stub.batchUpdateEntitiesCallable();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntitiesAsync(EntityTypeName entityTypeName, List<String> list) {
        return batchDeleteEntitiesAsync(BatchDeleteEntitiesRequest.newBuilder().setParent(entityTypeName == null ? null : entityTypeName.toString()).addAllEntityValues(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntitiesAsync(String str, List<String> list) {
        return batchDeleteEntitiesAsync(BatchDeleteEntitiesRequest.newBuilder().setParent(str).addAllEntityValues(list).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntitiesAsync(EntityTypeName entityTypeName, List<String> list, String str) {
        return batchDeleteEntitiesAsync(BatchDeleteEntitiesRequest.newBuilder().setParent(entityTypeName == null ? null : entityTypeName.toString()).addAllEntityValues(list).setLanguageCode(str).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntitiesAsync(String str, List<String> list, String str2) {
        return batchDeleteEntitiesAsync(BatchDeleteEntitiesRequest.newBuilder().setParent(str).addAllEntityValues(list).setLanguageCode(str2).build());
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public final OperationFuture<Empty, Struct> batchDeleteEntitiesAsync(BatchDeleteEntitiesRequest batchDeleteEntitiesRequest) {
        return batchDeleteEntitiesOperationCallable().futureCall(batchDeleteEntitiesRequest);
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public final OperationCallable<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationCallable() {
        return this.stub.batchDeleteEntitiesOperationCallable();
    }

    public final UnaryCallable<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesCallable() {
        return this.stub.batchDeleteEntitiesCallable();
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
    public static class ListEntityTypesPagedResponse extends AbstractPagedListResponse<ListEntityTypesRequest, ListEntityTypesResponse, EntityType, ListEntityTypesPage, ListEntityTypesFixedSizeCollection> {
        public static ApiFuture<ListEntityTypesPagedResponse> createAsync(PageContext<ListEntityTypesRequest, ListEntityTypesResponse, EntityType> pageContext, ApiFuture<ListEntityTypesResponse> apiFuture) {
            return ApiFutures.transform(ListEntityTypesPage.access$000().createPageAsync(pageContext, apiFuture), new ApiFunction<ListEntityTypesPage, ListEntityTypesPagedResponse>() { // from class: com.google.cloud.dialogflow.v2.EntityTypesClient.ListEntityTypesPagedResponse.1
                @Override // com.google.api.core.ApiFunction
                public ListEntityTypesPagedResponse apply(ListEntityTypesPage listEntityTypesPage) {
                    return new ListEntityTypesPagedResponse(listEntityTypesPage);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListEntityTypesPagedResponse(ListEntityTypesPage listEntityTypesPage) {
            super(listEntityTypesPage, ListEntityTypesFixedSizeCollection.access$200());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ListEntityTypesPage extends AbstractPage<ListEntityTypesRequest, ListEntityTypesResponse, EntityType, ListEntityTypesPage> {
        static /* synthetic */ ListEntityTypesPage access$000() {
            return createEmptyPage();
        }

        private ListEntityTypesPage(PageContext<ListEntityTypesRequest, ListEntityTypesResponse, EntityType> pageContext, ListEntityTypesResponse listEntityTypesResponse) {
            super(pageContext, listEntityTypesResponse);
        }

        private static ListEntityTypesPage createEmptyPage() {
            return new ListEntityTypesPage(null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractPage
        public ListEntityTypesPage createPage(PageContext<ListEntityTypesRequest, ListEntityTypesResponse, EntityType> pageContext, ListEntityTypesResponse listEntityTypesResponse) {
            return new ListEntityTypesPage(pageContext, listEntityTypesResponse);
        }

        @Override // com.google.api.gax.paging.AbstractPage
        public ApiFuture<ListEntityTypesPage> createPageAsync(PageContext<ListEntityTypesRequest, ListEntityTypesResponse, EntityType> pageContext, ApiFuture<ListEntityTypesResponse> apiFuture) {
            return super.createPageAsync(pageContext, apiFuture);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ListEntityTypesFixedSizeCollection extends AbstractFixedSizeCollection<ListEntityTypesRequest, ListEntityTypesResponse, EntityType, ListEntityTypesPage, ListEntityTypesFixedSizeCollection> {
        static /* synthetic */ ListEntityTypesFixedSizeCollection access$200() {
            return createEmptyCollection();
        }

        private ListEntityTypesFixedSizeCollection(List<ListEntityTypesPage> list, int i) {
            super(list, i);
        }

        private static ListEntityTypesFixedSizeCollection createEmptyCollection() {
            return new ListEntityTypesFixedSizeCollection(null, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.gax.paging.AbstractFixedSizeCollection
        public ListEntityTypesFixedSizeCollection createCollection(List<ListEntityTypesPage> list, int i) {
            return new ListEntityTypesFixedSizeCollection(list, i);
        }
    }
}
