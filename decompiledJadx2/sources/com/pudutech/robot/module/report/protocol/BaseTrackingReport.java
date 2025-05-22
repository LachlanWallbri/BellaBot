package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseTrackingReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\n\u001a\u00020\u000bH&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "", "DEFAULT_TRACKING_FLOAT_VALUE", "", "getDEFAULT_TRACKING_FLOAT_VALUE", "()D", "DEFAULT_TRACKING_VALUE", "", "getDEFAULT_TRACKING_VALUE", "()J", "isEmpty", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface BaseTrackingReport {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BaseTrackingReport.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
        public static double getDEFAULT_TRACKING_FLOAT_VALUE(BaseTrackingReport baseTrackingReport) {
            return 0.0d;
        }

        public static long getDEFAULT_TRACKING_VALUE(BaseTrackingReport baseTrackingReport) {
            return 0L;
        }
    }

    double getDEFAULT_TRACKING_FLOAT_VALUE();

    long getDEFAULT_TRACKING_VALUE();

    boolean isEmpty();
}
