package com.pudutech.mpmodule.utils;

import android.os.CountDownTimer;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CountTimer extends CountDownTimer {
    private String TAG;

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
    }

    public CountTimer(long j, long j2) {
        super(j, j2);
        this.TAG = "MusicModuleCountTimer";
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        Pdlog.m3273d(this.TAG, "CountDownTimer finish, destory all music activities.");
        PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
    }
}
