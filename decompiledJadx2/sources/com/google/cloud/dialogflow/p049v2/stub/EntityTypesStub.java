package com.google.cloud.dialogflow.p049v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequest;
import com.google.cloud.dialogflow.p049v2.BatchDeleteEntitiesRequest;
import com.google.cloud.dialogflow.p049v2.BatchDeleteEntityTypesRequest;
import com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequest;
import com.google.cloud.dialogflow.p049v2.BatchUpdateEntityTypesRequest;
import com.google.cloud.dialogflow.p049v2.BatchUpdateEntityTypesResponse;
import com.google.cloud.dialogflow.p049v2.CreateEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.DeleteEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.EntityType;
import com.google.cloud.dialogflow.p049v2.EntityTypesClient;
import com.google.cloud.dialogflow.p049v2.GetEntityTypeRequest;
import com.google.cloud.dialogflow.p049v2.ListEntityTypesRequest;
import com.google.cloud.dialogflow.p049v2.ListEntityTypesResponse;
import com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequest;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes2.dex */
public abstract class EntityTypesStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationsStub getOperationsStub() {
        throw new UnsupportedOperationException("Not implemented: getOperationsStub()");
    }

    public UnaryCallable<ListEntityTypesRequest, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listEntityTypesPagedCallable()");
    }

    public UnaryCallable<ListEntityTypesRequest, ListEntityTypesResponse> listEntityTypesCallable() {
        throw new UnsupportedOperationException("Not implemented: listEntityTypesCallable()");
    }

    public UnaryCallable<GetEntityTypeRequest, EntityType> getEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: getEntityTypeCallable()");
    }

    public UnaryCallable<CreateEntityTypeRequest, EntityType> createEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: createEntityTypeCallable()");
    }

    public UnaryCallable<UpdateEntityTypeRequest, EntityType> updateEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: updateEntityTypeCallable()");
    }

    public UnaryCallable<DeleteEntityTypeRequest, Empty> deleteEntityTypeCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteEntityTypeCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: batchUpdateEntityTypesOperationCallable()");
    }

    public UnaryCallable<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesCallable() {
        throw new UnsupportedOperationException("Not implemented: batchUpdateEntityTypesCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: batchDeleteEntityTypesOperationCallable()");
    }

    public UnaryCallable<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesCallable() {
        throw new UnsupportedOperationException("Not implemented: batchDeleteEntityTypesCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: batchCreateEntitiesOperationCallable()");
    }

    public UnaryCallable<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesCallable() {
        throw new UnsupportedOperationException("Not implemented: batchCreateEntitiesCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: batchUpdateEntitiesOperationCallable()");
    }

    public UnaryCallable<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesCallable() {
        throw new UnsupportedOperationException("Not implemented: batchUpdateEntitiesCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: batchDeleteEntitiesOperationCallable()");
    }

    public UnaryCallable<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesCallable() {
        throw new UnsupportedOperationException("Not implemented: batchDeleteEntitiesCallable()");
    }
}
