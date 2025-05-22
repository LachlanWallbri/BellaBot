package com.alibaba.sdk.android.oss.model;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GetBucketACLRequest extends OSSRequest {
    private String bucketName;

    public GetBucketACLRequest(String str) {
        setBucketName(str);
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
