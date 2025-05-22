package com.qihoo.util;

import android.content.DialogInterface;
import android.os.Build;

/* renamed from: com.qihoo.util.b */
/* loaded from: classes.dex */
final class DialogInterfaceOnClickListenerC5776b implements DialogInterface.OnClickListener {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnClickListenerC5776b(RunnableC5775a runnableC5775a) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Thread currentThread = Thread.currentThread();
        synchronized (currentThread) {
            if (Build.VERSION.SDK_INT >= 19) {
                currentThread.notify();
            }
        }
    }
}
