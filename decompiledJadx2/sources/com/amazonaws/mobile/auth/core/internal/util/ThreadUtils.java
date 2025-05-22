package com.amazonaws.mobile.auth.core.internal.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class ThreadUtils {
    private ThreadUtils() {
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }
}
