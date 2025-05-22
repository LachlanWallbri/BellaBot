package com.pudutech.importmusic.utils;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import com.pudutech.base.Pdlog;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class CountTimer extends CountDownTimer {
    private String TAG;
    private Context mAppContext;

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
    }

    public CountTimer(long j, long j2, Context context) {
        super(j, j2);
        this.TAG = "MusicImporterCountTimer";
        this.mAppContext = context;
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        if (this.mAppContext != null) {
            Pdlog.m3273d(this.TAG, "CountDownTimer finish, destory all music activities.");
            Intent intent = new Intent("pudu.intent.action.CLEAN_ALL_ACTIVITIES");
            intent.setClassName(this.mAppContext, "com.pudutech.mpmodule.receiver.CleanAllActivityReceiver");
            this.mAppContext.sendBroadcast(intent);
            this.mAppContext = null;
        }
    }
}
