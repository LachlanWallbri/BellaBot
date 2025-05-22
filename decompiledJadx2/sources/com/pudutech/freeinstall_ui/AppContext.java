package com.pudutech.freeinstall_ui;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppContext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/AppContext;", "", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "listener", "Lcom/pudutech/freeinstall_ui/AppContext$StartActivityListener;", "getListener", "()Lcom/pudutech/freeinstall_ui/AppContext$StartActivityListener;", "setListener", "(Lcom/pudutech/freeinstall_ui/AppContext$StartActivityListener;)V", "init", "", "ctx", "StartActivityListener", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AppContext {
    public static final AppContext INSTANCE = new AppContext();
    public static Context context;
    private static StartActivityListener listener;

    /* compiled from: AppContext.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/AppContext$StartActivityListener;", "", "startDeliActivity", "", "context", "Landroid/content/Context;", "startSayHiAnimationActivity", "startToChargingActivity", "startWifiConnectActivity", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface StartActivityListener {
        void startDeliActivity(Context context);

        void startSayHiAnimationActivity(Context context);

        void startToChargingActivity(Context context);

        void startWifiConnectActivity(Context context);
    }

    private AppContext() {
    }

    public final Context getContext() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "<set-?>");
        context = context2;
    }

    public final StartActivityListener getListener() {
        return listener;
    }

    public final void setListener(StartActivityListener startActivityListener) {
        listener = startActivityListener;
    }

    @JvmStatic
    public static final void init(Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Context applicationContext = ctx.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "ctx.applicationContext");
        context = applicationContext;
    }
}
