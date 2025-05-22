package com.amazonaws.services.p048s3.internal;

import com.amazonaws.services.p048s3.model.DeleteObjectsResult;
import com.amazonaws.services.p048s3.model.MultiObjectDeleteException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DeleteObjectsResponse implements S3RequesterChargedResult {
    private List<DeleteObjectsResult.DeletedObject> deletedObjects;
    private List<MultiObjectDeleteException.DeleteError> errors;
    private boolean isRequesterCharged;

    public DeleteObjectsResponse() {
        this(new ArrayList(), new ArrayList());
    }

    public DeleteObjectsResponse(List<DeleteObjectsResult.DeletedObject> list, List<MultiObjectDeleteException.DeleteError> list2) {
        this.deletedObjects = list;
        this.errors = list2;
    }

    public List<DeleteObjectsResult.DeletedObject> getDeletedObjects() {
        return this.deletedObjects;
    }

    public void setDeletedObjects(List<DeleteObjectsResult.DeletedObject> list) {
        this.deletedObjects = list;
    }

    public List<MultiObjectDeleteException.DeleteError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<MultiObjectDeleteException.DeleteError> list) {
        this.errors = list;
    }

    @Override // com.amazonaws.services.p048s3.internal.S3RequesterChargedResult
    public boolean isRequesterCharged() {
        return this.isRequesterCharged;
    }

    @Override // com.amazonaws.services.p048s3.internal.S3RequesterChargedResult
    public void setRequesterCharged(boolean z) {
        this.isRequesterCharged = z;
    }
}
