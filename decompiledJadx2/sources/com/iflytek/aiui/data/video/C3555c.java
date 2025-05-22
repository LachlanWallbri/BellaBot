package com.iflytek.aiui.data.video;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/* renamed from: com.iflytek.aiui.data.video.c */
/* loaded from: classes4.dex */
class C3555c {

    /* renamed from: c */
    private static final String f2188c = "c";

    /* renamed from: a */
    private WindowManager f2189a;

    /* renamed from: b */
    private View f2190b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3555c(Context context, View view) {
        this.f2189a = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.f2190b = view;
    }

    /* renamed from: a */
    public void m826a() {
        View view;
        try {
            WindowManager windowManager = this.f2189a;
            if (windowManager == null || (view = this.f2190b) == null) {
                return;
            }
            windowManager.removeView(view);
            String str = f2188c;
            Log.d(str, str + " dismissed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m827b() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = 1;
        layoutParams.height = 1;
        layoutParams.alpha = 0.0f;
        layoutParams.type = Build.VERSION.SDK_INT >= 26 ? 2038 : 2003;
        layoutParams.flags = 56;
        this.f2189a.addView(this.f2190b, layoutParams);
        String str = f2188c;
        Log.i(str, str + " showing");
    }
}
