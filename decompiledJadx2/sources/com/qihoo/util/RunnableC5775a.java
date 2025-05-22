package com.qihoo.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Looper;

/* renamed from: com.qihoo.util.a */
/* loaded from: classes.dex */
final class RunnableC5775a implements Runnable {

    /* renamed from: a */
    private /* synthetic */ Context f7595a;

    /* renamed from: b */
    private /* synthetic */ String f7596b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC5775a(Context context, String str) {
        this.f7595a = context;
        this.f7596b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Looper.prepare();
        AlertDialog create = new AlertDialog.Builder(this.f7595a).setMessage(this.f7596b).setCancelable(false).setPositiveButton("确定", new DialogInterfaceOnClickListenerC5776b(this)).create();
        create.getWindow().setType(2005);
        create.show();
        Looper.loop();
    }
}
