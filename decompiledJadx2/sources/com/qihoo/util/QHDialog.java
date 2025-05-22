package com.qihoo.util;

import android.content.Context;
import android.os.Build;
import com.pudutech.mirsdk.SolicitService;

/* loaded from: classes.dex */
public class QHDialog {
    public static void showDialog(Context context, String str) {
        Thread thread = new Thread(new RunnableC5775a(context, str));
        synchronized (thread) {
            try {
                thread.start();
                if (Build.VERSION.SDK_INT >= 19) {
                    thread.wait();
                } else {
                    Thread.sleep(SolicitService.CAMERA_OPEN_TIME_OUT);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
