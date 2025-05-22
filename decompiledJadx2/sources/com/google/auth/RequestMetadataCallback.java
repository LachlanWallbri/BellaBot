package com.google.auth;

import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface RequestMetadataCallback {
    void onFailure(Throwable th);

    void onSuccess(Map<String, List<String>> map);
}
