package com.pudutech.bumblebee.business.core_devices_task.mileage_task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MileageTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0018\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0016\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\bJ\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/mileage_task/MileageTask;", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/SpeedListener;", "()V", "FileName", "", "KEY_TOTAL", "TAG", "lastRecordMeters", "", "lastTimestamp_ms", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "totalMeters", "getTotalMeters", "()D", "setTotalMeters", "(D)V", "getMilestone", AIUIConstant.KEY_TAG, "loadRecord", "", "context", "Landroid/content/Context;", "onSpeed", "p0", "p1", "setMilestone", "meters", "syncSpeed", "speed", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MileageTask implements SpeedListener {
    private double lastRecordMeters;
    private long lastTimestamp_ms;
    private SharedPreferences sharedPreferences;
    private double totalMeters;
    private final String TAG = "MileageTask";
    private final String FileName = "Mileage";
    private final String KEY_TOTAL = "total";

    public final double getTotalMeters() {
        return this.totalMeters;
    }

    public final void setTotalMeters(double d) {
        this.totalMeters = d;
    }

    public final void loadRecord(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.sharedPreferences = context.getSharedPreferences(this.FileName, 0);
        if (this.sharedPreferences != null) {
            this.totalMeters = r6.getFloat(this.KEY_TOTAL, 0.0f);
            this.lastRecordMeters = this.totalMeters;
        }
        Pdlog.m3275i(this.TAG, "loadRecord. totalMeters=" + this.totalMeters);
    }

    private final void syncSpeed(double speed) {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putFloat;
        if (this.lastTimestamp_ms > 0) {
            this.totalMeters += ((SystemClock.elapsedRealtime() - this.lastTimestamp_ms) / 1000.0d) * speed;
            if (this.totalMeters - this.lastRecordMeters > 1.0d) {
                Pdlog.m3275i(this.TAG, "record. totalMeters=" + this.totalMeters + ". lastTimeTamp_ms=" + this.lastTimestamp_ms);
                SharedPreferences sharedPreferences = this.sharedPreferences;
                if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null && (putFloat = edit.putFloat(this.KEY_TOTAL, (float) this.totalMeters)) != null) {
                    putFloat.apply();
                }
                this.lastRecordMeters = this.totalMeters;
            }
        }
        this.lastTimestamp_ms = SystemClock.elapsedRealtime();
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
    public void onSpeed(double p0, double p1) {
        syncSpeed(p0);
    }

    public final void setMilestone(String tag, double meters) {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putFloat;
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Pdlog.m3275i(this.TAG, "setMilestone " + tag + ' ' + meters);
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null || (putFloat = edit.putFloat(tag, (float) meters)) == null) {
            return;
        }
        putFloat.apply();
    }

    public final double getMilestone(String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        if (this.sharedPreferences != null) {
            return r0.getFloat(tag, 0.0f);
        }
        return 0.0d;
    }
}
