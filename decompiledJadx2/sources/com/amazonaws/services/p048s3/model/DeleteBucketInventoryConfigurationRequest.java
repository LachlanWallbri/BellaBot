package com.amazonaws.services.p048s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeleteBucketInventoryConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private String bucketName;

    /* renamed from: id */
    private String f1186id;

    public DeleteBucketInventoryConfigurationRequest() {
    }

    public DeleteBucketInventoryConfigurationRequest(String str, String str2) {
        this.bucketName = str;
        this.f1186id = str2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public DeleteBucketInventoryConfigurationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public String getId() {
        return this.f1186id;
    }

    public void setId(String str) {
        this.f1186id = str;
    }

    public DeleteBucketInventoryConfigurationRequest withId(String str) {
        setId(str);
        return this;
    }
}
