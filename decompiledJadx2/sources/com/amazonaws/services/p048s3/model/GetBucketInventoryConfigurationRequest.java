package com.amazonaws.services.p048s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GetBucketInventoryConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private String bucketName;

    /* renamed from: id */
    private String f1190id;

    public GetBucketInventoryConfigurationRequest() {
    }

    public GetBucketInventoryConfigurationRequest(String str, String str2) {
        this.bucketName = str;
        this.f1190id = str2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public GetBucketInventoryConfigurationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public String getId() {
        return this.f1190id;
    }

    public void setId(String str) {
        this.f1190id = str;
    }

    public GetBucketInventoryConfigurationRequest withId(String str) {
        setId(str);
        return this;
    }
}
