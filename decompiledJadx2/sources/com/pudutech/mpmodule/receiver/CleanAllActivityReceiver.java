package com.pudutech.mpmodule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CleanAllActivityReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Pdlog.m3273d("CleanAllActivityReceiver", "receive demand, to clean all the activities");
        PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
    }
}
