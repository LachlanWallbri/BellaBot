package com.amazonaws.services.p048s3.model;

import com.amazonaws.AmazonWebServiceRequest;

/* loaded from: classes.dex */
public class HeadBucketRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public HeadBucketRequest(String str) {
        this.bucketName = str;
    }
}
