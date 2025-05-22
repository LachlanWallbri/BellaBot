package com.pudutech.peanut.robot_ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.config.Constans;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShotDownBroadcastReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/receiver/ShotDownBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "ACTION_SHUTDOWN", "", "TAG", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ShotDownBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = "ShotDownBroadcastReceiver";
    private final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Pdlog.m3274e(this.TAG, "POWER = " + BatteryInfoManager.INSTANCE.getPower(), intent);
        if (intent == null || !Intrinsics.areEqual(intent.getAction(), this.ACTION_SHUTDOWN)) {
            return;
        }
        Constans constans = Constans.INSTANCE;
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        constans.setChargingPower(power != null ? power.intValue() : 0);
    }
}
