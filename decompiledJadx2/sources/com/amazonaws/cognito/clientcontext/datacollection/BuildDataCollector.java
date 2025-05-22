package com.amazonaws.cognito.clientcontext.datacollection;

import android.content.Context;
import android.os.Build;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BuildDataCollector extends DataCollector {
    @Override // com.amazonaws.cognito.clientcontext.datacollection.DataCollector
    public Map<String, String> collect(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put(DataRecordKey.BRAND, Build.BRAND);
        hashMap.put(DataRecordKey.FINGERPRINT, Build.FINGERPRINT);
        hashMap.put(DataRecordKey.HARDWARE, Build.HARDWARE);
        hashMap.put(DataRecordKey.MODEL, Build.MODEL);
        hashMap.put(DataRecordKey.PRODUCT, Build.PRODUCT);
        hashMap.put(DataRecordKey.BUILD_TYPE, Build.TYPE);
        hashMap.put(DataRecordKey.VERSION_RELEASE, Build.VERSION.RELEASE);
        hashMap.put(DataRecordKey.VERSION_SDK, Build.VERSION.SDK);
        return hashMap;
    }
}
