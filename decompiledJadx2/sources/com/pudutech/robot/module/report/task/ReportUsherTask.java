package com.pudutech.robot.module.report.task;

import android.os.SystemClock;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import com.pudutech.robot.module.report.protocol.UsherTrackingReport;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportUsherTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0011J\u0006\u0010\u0017\u001a\u00020\u0011J\u0006\u0010\u0018\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportUsherTask;", "Lcom/pudutech/robot/module/report/task/BaseTrackingReportTask;", "()V", "MAX_FLOW_SAVE_NUM", "", "TAG", "", ReportUsherTask.USHER_FLOW_NUMBER_KEY, ReportUsherTask.USHER_NUMBER_KEY, "USHER_RUNNING_MILES", "USHER_USE_TIME", "mUsherTrackingTime", "", "userFlowNum", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "remove", "", "trackRunningMiles", "l", "", "trackingUsherEnd", "trackingUsherEvent", "trackingUsherFlowEvent", "trackingUsherStart", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportUsherTask extends BaseTrackingReportTask {
    public static final ReportUsherTask INSTANCE = new ReportUsherTask();
    private static final int MAX_FLOW_SAVE_NUM = 5;
    private static final String TAG = "ReportUsherTask";
    private static final String USHER_FLOW_NUMBER_KEY = "USHER_FLOW_NUMBER_KEY";
    private static final String USHER_NUMBER_KEY = "USHER_NUMBER_KEY";
    private static final String USHER_RUNNING_MILES = "user_running_miles_new";
    private static final String USHER_USE_TIME = "usher_use_time";
    private static long mUsherTrackingTime;
    private static long userFlowNum;

    private ReportUsherTask() {
    }

    @Override // com.pudutech.robot.module.report.task.BaseTrackingReportTask
    public BaseTrackingReport getReportData() {
        UsherTrackingReport usherTrackingReport = new UsherTrackingReport();
        usherTrackingReport.setUsherFlowNumber(getTracking(USHER_FLOW_NUMBER_KEY));
        usherTrackingReport.setUsherNumber(getTracking(USHER_NUMBER_KEY));
        usherTrackingReport.setUsherMiles(getTrackingFloatValue(USHER_RUNNING_MILES));
        usherTrackingReport.setUsherUseTime(getTracking(USHER_USE_TIME) / 1000);
        NetWorkLog.INSTANCE.mo3278d(TAG, "data:" + usherTrackingReport);
        return usherTrackingReport;
    }

    public final void trackingUsherFlowEvent() {
        userFlowNum++;
        long j = userFlowNum;
        if (j >= 5) {
            userFlowNum = 0L;
            addTracking(USHER_FLOW_NUMBER_KEY, j);
        }
    }

    public final void trackingUsherEvent() {
        BaseTrackingReportTask.addTracking$default(this, USHER_NUMBER_KEY, 0L, 2, null);
    }

    public final void trackRunningMiles(double l) {
        addTrackingFloatValue(USHER_RUNNING_MILES, l);
    }

    public final void trackingUsherStart() {
        mUsherTrackingTime = SystemClock.elapsedRealtime();
    }

    public final void trackingUsherEnd() {
        if (mUsherTrackingTime <= 0) {
            mUsherTrackingTime = 0L;
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = elapsedRealtime - mUsherTrackingTime;
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingUsherEnd : " + j + " endTime:" + elapsedRealtime + " startTime " + j);
        mUsherTrackingTime = 0L;
        addTracking(USHER_USE_TIME, j);
    }

    public final void remove() {
        removeTracking(USHER_FLOW_NUMBER_KEY);
        removeTracking(USHER_NUMBER_KEY);
        removeTracking(USHER_RUNNING_MILES, "0.0");
        removeTracking(USHER_USE_TIME);
    }
}
