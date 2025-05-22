package com.amazonaws.services.p048s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeleteBucketAnalyticsConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private String bucketName;

    /* renamed from: id */
    private String f1185id;

    public DeleteBucketAnalyticsConfigurationRequest() {
    }

    public DeleteBucketAnalyticsConfigurationRequest(String str, String str2) {
        this.bucketName = str;
        this.f1185id = str2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public DeleteBucketAnalyticsConfigurationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public String getId() {
        return this.f1185id;
    }

    public void setId(String str) {
        this.f1185id = str;
    }

    public DeleteBucketAnalyticsConfigurationRequest withId(String str) {
        setId(str);
        return this;
    }
}
