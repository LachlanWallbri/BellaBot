package com.amazonaws.services.p048s3.internal.crypto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes.dex */
public abstract class MultipartUploadContext {
    private final String bucketName;
    private boolean hasFinalPartBeenSeen;
    private final String key;
    private Map<String, String> materialsDescription;

    /* JADX INFO: Access modifiers changed from: protected */
    public MultipartUploadContext(String str, String str2) {
        this.bucketName = str;
        this.key = str2;
    }

    public final String getBucketName() {
        return this.bucketName;
    }

    public final String getKey() {
        return this.key;
    }

    public final boolean hasFinalPartBeenSeen() {
        return this.hasFinalPartBeenSeen;
    }

    public final void setHasFinalPartBeenSeen(boolean z) {
        this.hasFinalPartBeenSeen = z;
    }

    public final Map<String, String> getMaterialsDescription() {
        return this.materialsDescription;
    }

    public final void setMaterialsDescription(Map<String, String> map) {
        this.materialsDescription = map == null ? null : Collections.unmodifiableMap(new HashMap(map));
    }
}
