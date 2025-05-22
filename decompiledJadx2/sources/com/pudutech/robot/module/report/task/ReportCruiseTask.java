package com.pudutech.robot.module.report.task;

import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import com.pudutech.robot.module.report.protocol.CruiseTrackingReport;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportCruiseTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012J\u0006\u0010\u0016\u001a\u00020\u0012J\u0006\u0010\u0017\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportCruiseTask;", "Lcom/pudutech/robot/module/report/task/BaseTrackingReportTask;", "()V", "BUSINESS_INTERACTIVE_NUMBER_KEY", "", "COUPON_NUMBER_KEY", ReportCruiseTask.CRUISE_DURATION_KEY, "CRUISE_END_TIME", "CRUISE_START_TIME", "FEATUR_DISHES_NUMBER_KEY", "GOSHOP_NUMBER_KEY", "INTERACTIVE_NUMBER_KEY", "TAG", "mCruiseTrackingTime", "", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "remove", "", "trackingBusinessInteractiveEvent", "trackingCouponEvent", "trackingCruiseEndEvent", "trackingCruiseStartEvent", "trackingFeaturDishesEvent", "trackingGoShopEvent", "trackingInteractiveEvent", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportCruiseTask extends BaseTrackingReportTask {
    private static final String BUSINESS_INTERACTIVE_NUMBER_KEY = "CRUISE_BUSINESS_INTERACTIVE_NUMBER_KEY";
    private static final String COUPON_NUMBER_KEY = "CRUISE_COUPON_NUMBER_KEY";
    private static final String CRUISE_DURATION_KEY = "CRUISE_DURATION_KEY";
    private static final String CRUISE_END_TIME = "cruise_end_time";
    private static final String CRUISE_START_TIME = "cruise_start_time";
    private static final String FEATUR_DISHES_NUMBER_KEY = "CRUISE_FEATUR_DISHES_NUMBER_KEY";
    private static final String GOSHOP_NUMBER_KEY = "CRUISE_GOSHOP_NUMBER_KEY";
    public static final ReportCruiseTask INSTANCE = new ReportCruiseTask();
    private static final String INTERACTIVE_NUMBER_KEY = "CRUISE_INTERACTIVE_NUMBER_KEY";
    private static final String TAG = "ReportCruiseTask";
    private static long mCruiseTrackingTime;

    private ReportCruiseTask() {
    }

    @Override // com.pudutech.robot.module.report.task.BaseTrackingReportTask
    public BaseTrackingReport getReportData() {
        CruiseTrackingReport cruiseTrackingReport = new CruiseTrackingReport();
        cruiseTrackingReport.setCruiseDuration(getTracking(CRUISE_DURATION_KEY) / 1000);
        cruiseTrackingReport.setInteractiveNumber(getTracking(INTERACTIVE_NUMBER_KEY));
        cruiseTrackingReport.setBusinessInteractiveNumber(getTracking(BUSINESS_INTERACTIVE_NUMBER_KEY));
        cruiseTrackingReport.setFeaturDishesNumber(getTracking(FEATUR_DISHES_NUMBER_KEY));
        cruiseTrackingReport.setCouponNumber(getTracking(COUPON_NUMBER_KEY));
        cruiseTrackingReport.setGoShopNumber(getTracking(GOSHOP_NUMBER_KEY));
        NetWorkLog.INSTANCE.mo3278d(TAG, "data:" + cruiseTrackingReport);
        return cruiseTrackingReport;
    }

    public final void trackingCruiseStartEvent() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingCruiseStartEvent ");
        mCruiseTrackingTime = System.currentTimeMillis();
    }

    public final void trackingCruiseEndEvent() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingCruiseEndEvent");
        if (mCruiseTrackingTime <= 0) {
            mCruiseTrackingTime = 0L;
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - mCruiseTrackingTime;
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingCruiseDurationEndEvent:" + j + ";endTime:" + currentTimeMillis + ";startTime:" + j);
        mCruiseTrackingTime = 0L;
        addTracking(CRUISE_DURATION_KEY, j);
    }

    public final void trackingInteractiveEvent() {
        BaseTrackingReportTask.addTracking$default(this, INTERACTIVE_NUMBER_KEY, 0L, 2, null);
    }

    public final void trackingBusinessInteractiveEvent() {
        BaseTrackingReportTask.addTracking$default(this, BUSINESS_INTERACTIVE_NUMBER_KEY, 0L, 2, null);
    }

    public final void trackingFeaturDishesEvent() {
        BaseTrackingReportTask.addTracking$default(this, FEATUR_DISHES_NUMBER_KEY, 0L, 2, null);
    }

    public final void trackingCouponEvent() {
        BaseTrackingReportTask.addTracking$default(this, COUPON_NUMBER_KEY, 0L, 2, null);
    }

    public final void trackingGoShopEvent() {
        BaseTrackingReportTask.addTracking$default(this, GOSHOP_NUMBER_KEY, 0L, 2, null);
    }

    public final void remove() {
        removeTracking(CRUISE_DURATION_KEY);
        removeTracking(INTERACTIVE_NUMBER_KEY);
        removeTracking(BUSINESS_INTERACTIVE_NUMBER_KEY);
        removeTracking(FEATUR_DISHES_NUMBER_KEY);
        removeTracking(COUPON_NUMBER_KEY);
        removeTracking(GOSHOP_NUMBER_KEY);
    }
}
