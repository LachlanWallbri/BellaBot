package com.alibaba.sdk.android.oss.model;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class HeadObjectResult extends OSSResult {
    private ObjectMetadata metadata = new ObjectMetadata();

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }
}
