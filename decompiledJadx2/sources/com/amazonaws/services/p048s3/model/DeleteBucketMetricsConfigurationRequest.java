package com.amazonaws.services.p048s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeleteBucketMetricsConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private String bucketName;

    /* renamed from: id */
    private String f1187id;

    public DeleteBucketMetricsConfigurationRequest() {
    }

    public DeleteBucketMetricsConfigurationRequest(String str, String str2) {
        this.bucketName = str;
        this.f1187id = str2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public DeleteBucketMetricsConfigurationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public String getId() {
        return this.f1187id;
    }

    public void setId(String str) {
        this.f1187id = str;
    }

    public DeleteBucketMetricsConfigurationRequest withId(String str) {
        setId(str);
        return this;
    }
}
