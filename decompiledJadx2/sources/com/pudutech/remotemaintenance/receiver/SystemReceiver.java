package com.pudutech.remotemaintenance.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.service.RemoteMaintenanceService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/receiver/SystemReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SystemReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d("SystemReceiver", "收到广播：intent = " + intent);
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        switch (action.hashCode()) {
            case -905063602:
                if (!action.equals("android.intent.action.LOCKED_BOOT_COMPLETED")) {
                    return;
                }
                Log.d("SystemReceiver", "开机了，启动RemoteMaintenanceService");
                RemoteMaintenanceService.INSTANCE.start(context);
                return;
            case -810471698:
                str = "android.intent.action.PACKAGE_REPLACED";
                break;
            case 798292259:
                if (!action.equals("android.intent.action.BOOT_COMPLETED")) {
                    return;
                }
                Log.d("SystemReceiver", "开机了，启动RemoteMaintenanceService");
                RemoteMaintenanceService.INSTANCE.start(context);
                return;
            case 1544582882:
                str = "android.intent.action.PACKAGE_ADDED";
                break;
            default:
                return;
        }
        action.equals(str);
    }
}
