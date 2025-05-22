package com.amazonaws.services.p048s3.model;

import com.amazonaws.services.p048s3.model.DeleteObjectsResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class MultiObjectDeleteException extends AmazonS3Exception {
    private static final long serialVersionUID = -2004213552302446866L;
    private final List<DeleteObjectsResult.DeletedObject> deletedObjects;
    private final List<DeleteError> errors;

    public MultiObjectDeleteException(Collection<DeleteError> collection, Collection<DeleteObjectsResult.DeletedObject> collection2) {
        super("One or more objects could not be deleted");
        this.errors = new ArrayList();
        this.deletedObjects = new ArrayList();
        this.deletedObjects.addAll(collection2);
        this.errors.addAll(collection);
    }

    @Override // com.amazonaws.AmazonServiceException
    public String getErrorCode() {
        return super.getErrorCode();
    }

    public List<DeleteObjectsResult.DeletedObject> getDeletedObjects() {
        return this.deletedObjects;
    }

    public List<DeleteError> getErrors() {
        return this.errors;
    }

    /* loaded from: classes.dex */
    public static class DeleteError {
        private String code;
        private String key;
        private String message;
        private String versionId;

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public String getVersionId() {
            return this.versionId;
        }

        public void setVersionId(String str) {
            this.versionId = str;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }
    }
}
