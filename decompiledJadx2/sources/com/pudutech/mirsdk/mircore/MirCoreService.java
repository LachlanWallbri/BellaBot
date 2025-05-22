package com.pudutech.mirsdk.mircore;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.aliyun.linksdk.alcs.AlcsConstant;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirCoreService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirCoreService;", "Landroid/app/Service;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "iBinder", "Lcom/pudutech/mirsdk/mircore/MirCoreBinder;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", AlcsConstant.METHOD_DOMAIN_CORE, "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MirCoreService extends Service {
    public static WeakReference<Context> context;

    /* renamed from: core, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = getClass().getSimpleName();
    private final MirCoreBinder iBinder = MirCoreBinder.INSTANCE;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirCoreService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirCoreService$core;", "", "()V", "context", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "getContext", "()Ljava/lang/ref/WeakReference;", "setContext", "(Ljava/lang/ref/WeakReference;)V", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.MirCoreService$core, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WeakReference<Context> getContext() {
            WeakReference<Context> weakReference = MirCoreService.context;
            if (weakReference == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            return weakReference;
        }

        public final void setContext(WeakReference<Context> weakReference) {
            Intrinsics.checkParameterIsNotNull(weakReference, "<set-?>");
            MirCoreService.context = weakReference;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(this);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.iBinder;
    }
}
