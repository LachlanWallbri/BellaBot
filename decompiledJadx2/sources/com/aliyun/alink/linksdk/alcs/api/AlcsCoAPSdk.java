package com.aliyun.alink.linksdk.alcs.api;

import android.os.Handler;
import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsCoAPSdk {
    public static Handler mHandler;

    public static void init() {
        mHandler = new Handler(Looper.getMainLooper());
    }
}
