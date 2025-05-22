package com.pudutech.mirsdkwrap.lib.move.bean;

import android.os.SystemClock;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveReportData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b'\n\u0002\u0010\u0002\n\u0002\b\u001b\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007¢\u0006\u0002\u0010\u000fJ\u0015\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\fH\u0000¢\u0006\u0002\b6J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\tHÆ\u0003J\t\u0010;\u001a\u00020\u0007HÆ\u0003J\t\u0010<\u001a\u00020\fHÆ\u0003J\t\u0010=\u001a\u00020\fHÆ\u0003J\t\u0010>\u001a\u00020\u0007HÆ\u0003JY\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0007HÆ\u0001J\u0017\u0010@\u001a\u0002042\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0000¢\u0006\u0002\bAJ\r\u0010B\u001a\u000204H\u0000¢\u0006\u0002\bCJ\u0013\u0010D\u001a\u00020\u00052\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010F\u001a\u00020\tHÖ\u0001J\r\u0010G\u001a\u000204H\u0000¢\u0006\u0002\bHJ\r\u0010I\u001a\u000204H\u0000¢\u0006\u0002\bJJ\u0015\u0010K\u001a\u0002042\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\bLJ\r\u00100\u001a\u000204H\u0000¢\u0006\u0002\bMJ\t\u0010N\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\r\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0019\"\u0004\b)\u0010\u001bR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0012\"\u0004\b/\u0010\u0014R\u000e\u00100\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveReportData;", "", "goalId", "", "cancel", "", "allTime", "", "pauseCount", "", "pauseTime", "mileage", "", "average", "arriveWaitTime", "(Ljava/lang/String;ZJIJDDJ)V", "TAG", "getAllTime", "()J", "setAllTime", "(J)V", "arriveTime", "getArriveWaitTime", "setArriveWaitTime", "getAverage", "()D", "setAverage", "(D)V", "getCancel", "()Z", "setCancel", "(Z)V", AUserTrack.UTKEY_END_TIME, "getGoalId", "()Ljava/lang/String;", "setGoalId", "(Ljava/lang/String;)V", "isStart", "lastSpeed", "lastSpeedTime", "getMileage", "setMileage", "getPauseCount", "()I", "setPauseCount", "(I)V", "getPauseTime", "setPauseTime", "startPause", AUserTrack.UTKEY_START_TIME, "validSpeedTime", "addMeters", "", "speed", "addMeters$module_robot_mirsdk_wrapper_release", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "end", "end$module_robot_mirsdk_wrapper_release", "endPause", "endPause$module_robot_mirsdk_wrapper_release", "equals", "other", "hashCode", "recode", "recode$module_robot_mirsdk_wrapper_release", "recodeArrive", "recodeArrive$module_robot_mirsdk_wrapper_release", "start", "start$module_robot_mirsdk_wrapper_release", "startPause$module_robot_mirsdk_wrapper_release", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveReportData {
    private final String TAG;
    private long allTime;
    private long arriveTime;
    private long arriveWaitTime;
    private double average;
    private boolean cancel;
    private long endTime;
    private String goalId;
    private boolean isStart;
    private double lastSpeed;
    private long lastSpeedTime;
    private double mileage;
    private int pauseCount;
    private long pauseTime;
    private long startPause;
    private long startTime;
    private long validSpeedTime;

    public MoveReportData() {
        this(null, false, 0L, 0, 0L, 0.0d, 0.0d, 0L, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getGoalId() {
        return this.goalId;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getCancel() {
        return this.cancel;
    }

    /* renamed from: component3, reason: from getter */
    public final long getAllTime() {
        return this.allTime;
    }

    /* renamed from: component4, reason: from getter */
    public final int getPauseCount() {
        return this.pauseCount;
    }

    /* renamed from: component5, reason: from getter */
    public final long getPauseTime() {
        return this.pauseTime;
    }

    /* renamed from: component6, reason: from getter */
    public final double getMileage() {
        return this.mileage;
    }

    /* renamed from: component7, reason: from getter */
    public final double getAverage() {
        return this.average;
    }

    /* renamed from: component8, reason: from getter */
    public final long getArriveWaitTime() {
        return this.arriveWaitTime;
    }

    public final MoveReportData copy(String goalId, boolean cancel, long allTime, int pauseCount, long pauseTime, double mileage, double average, long arriveWaitTime) {
        Intrinsics.checkParameterIsNotNull(goalId, "goalId");
        return new MoveReportData(goalId, cancel, allTime, pauseCount, pauseTime, mileage, average, arriveWaitTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoveReportData)) {
            return false;
        }
        MoveReportData moveReportData = (MoveReportData) other;
        return Intrinsics.areEqual(this.goalId, moveReportData.goalId) && this.cancel == moveReportData.cancel && this.allTime == moveReportData.allTime && this.pauseCount == moveReportData.pauseCount && this.pauseTime == moveReportData.pauseTime && Double.compare(this.mileage, moveReportData.mileage) == 0 && Double.compare(this.average, moveReportData.average) == 0 && this.arriveWaitTime == moveReportData.arriveWaitTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.goalId;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.cancel;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        long j = this.allTime;
        int i3 = (((i2 + ((int) (j ^ (j >>> 32)))) * 31) + this.pauseCount) * 31;
        long j2 = this.pauseTime;
        int i4 = (i3 + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.mileage);
        int i5 = (i4 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.average);
        int i6 = (i5 + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
        long j3 = this.arriveWaitTime;
        return i6 + ((int) (j3 ^ (j3 >>> 32)));
    }

    public String toString() {
        return "MoveReportData(goalId=" + this.goalId + ", cancel=" + this.cancel + ", allTime=" + this.allTime + ", pauseCount=" + this.pauseCount + ", pauseTime=" + this.pauseTime + ", mileage=" + this.mileage + ", average=" + this.average + ", arriveWaitTime=" + this.arriveWaitTime + ")";
    }

    public MoveReportData(String goalId, boolean z, long j, int i, long j2, double d, double d2, long j3) {
        Intrinsics.checkParameterIsNotNull(goalId, "goalId");
        this.goalId = goalId;
        this.cancel = z;
        this.allTime = j;
        this.pauseCount = i;
        this.pauseTime = j2;
        this.mileage = d;
        this.average = d2;
        this.arriveWaitTime = j3;
        this.TAG = "MoveReportData";
    }

    public final String getGoalId() {
        return this.goalId;
    }

    public final void setGoalId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.goalId = str;
    }

    public final boolean getCancel() {
        return this.cancel;
    }

    public final void setCancel(boolean z) {
        this.cancel = z;
    }

    public final long getAllTime() {
        return this.allTime;
    }

    public final void setAllTime(long j) {
        this.allTime = j;
    }

    public final int getPauseCount() {
        return this.pauseCount;
    }

    public final void setPauseCount(int i) {
        this.pauseCount = i;
    }

    public final long getPauseTime() {
        return this.pauseTime;
    }

    public final void setPauseTime(long j) {
        this.pauseTime = j;
    }

    public final double getMileage() {
        return this.mileage;
    }

    public final void setMileage(double d) {
        this.mileage = d;
    }

    public final double getAverage() {
        return this.average;
    }

    public final void setAverage(double d) {
        this.average = d;
    }

    public /* synthetic */ MoveReportData(String str, boolean z, long j, int i, long j2, double d, double d2, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? 0L : j, (i2 & 8) == 0 ? i : 0, (i2 & 16) != 0 ? 0L : j2, (i2 & 32) != 0 ? 0.0d : d, (i2 & 64) == 0 ? d2 : 0.0d, (i2 & 128) == 0 ? j3 : 0L);
    }

    public final long getArriveWaitTime() {
        return this.arriveWaitTime;
    }

    public final void setArriveWaitTime(long j) {
        this.arriveWaitTime = j;
    }

    public final void addMeters$module_robot_mirsdk_wrapper_release(double speed) {
        if (this.isStart) {
            if (this.lastSpeed != speed) {
                Pdlog.m3273d(this.TAG, "addMeters speed:" + speed);
            }
            if (this.lastSpeedTime == 0) {
                this.lastSpeedTime = SystemClock.elapsedRealtime();
                this.lastSpeed = speed;
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.lastSpeedTime;
            this.mileage += (elapsedRealtime / 1000.0d) * this.lastSpeed;
            if (speed >= 0.05d) {
                this.validSpeedTime += elapsedRealtime;
            }
            this.lastSpeedTime = SystemClock.elapsedRealtime();
            if (speed < 0.05d) {
                speed = 0.0d;
            }
            this.lastSpeed = speed;
        }
    }

    public final void start$module_robot_mirsdk_wrapper_release(String goalId) {
        Intrinsics.checkParameterIsNotNull(goalId, "goalId");
        Pdlog.m3273d(this.TAG, "start " + goalId);
        if (!this.isStart && this.startTime == 0) {
            this.isStart = true;
            this.startTime = SystemClock.elapsedRealtime();
            this.goalId = goalId;
            return;
        }
        Pdlog.m3277w(this.TAG, "start : not need start again");
    }

    public final void startPause$module_robot_mirsdk_wrapper_release() {
        this.startPause = SystemClock.elapsedRealtime();
        this.pauseCount++;
        Pdlog.m3273d(this.TAG, "startPause " + this.pauseCount);
    }

    public final void endPause$module_robot_mirsdk_wrapper_release() {
        if (this.startPause != 0) {
            this.pauseTime += (long) Math.ceil((SystemClock.elapsedRealtime() - this.startPause) / 1000.0d);
            this.startPause = 0L;
            Pdlog.m3273d(this.TAG, "endPause " + this.pauseTime);
        }
    }

    public final void recodeArrive$module_robot_mirsdk_wrapper_release() {
        if (this.arriveTime == 0) {
            this.arriveTime = SystemClock.elapsedRealtime();
            Pdlog.m3273d(this.TAG, "recodeArrive " + this.arriveTime);
        }
    }

    public static /* synthetic */ void end$module_robot_mirsdk_wrapper_release$default(MoveReportData moveReportData, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        moveReportData.end$module_robot_mirsdk_wrapper_release(z);
    }

    public final void end$module_robot_mirsdk_wrapper_release(boolean cancel) {
        if (this.isStart) {
            this.cancel = cancel;
            if (cancel) {
                endPause$module_robot_mirsdk_wrapper_release();
            }
            recode$module_robot_mirsdk_wrapper_release();
            this.isStart = false;
            Pdlog.m3273d(this.TAG, "end : cancel = " + cancel + "; average = " + this.average + " ; arriveWaitTime = " + this.arriveWaitTime + " ; allTime = " + this.allTime + " ; pauseCount = " + this.pauseCount + " ; pauseTime = " + this.pauseTime);
        }
    }

    public final void recode$module_robot_mirsdk_wrapper_release() {
        long j = this.validSpeedTime;
        if (j > 0) {
            this.average = (this.mileage / j) * 1000.0d;
        }
        this.endTime = SystemClock.elapsedRealtime();
        long j2 = this.arriveTime;
        if (j2 > 0) {
            this.arriveWaitTime = (this.endTime - j2) / 1000;
        }
        this.allTime = (this.endTime - this.startTime) / 1000;
        Pdlog.m3273d(this.TAG, "recode : cancel = " + this.cancel + "; average = " + this.average + " ; arriveWaitTime = " + this.arriveWaitTime + " ; allTime = " + this.allTime + " ; pauseCount = " + this.pauseCount + " ; pauseTime = " + this.pauseTime);
    }
}
