package com.pudutech.robot.module.report.task;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.DeliveryReport;
import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportDelivery.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\b\u001a\u00020\u00002\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportDelivery;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "deliveryReport", "Lcom/pudutech/robot/module/report/protocol/DeliveryReport;", "getDeliveryReport", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", TmpConstant.PROPERTY_IDENTIFIER_SET, "info", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/protocol/bean/DeliveryInfo;", "Lkotlin/collections/ArrayList;", "totalTime", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportDelivery extends BaseReportTask {
    private final DeliveryReport deliveryReport = new DeliveryReport();

    public final DeliveryReport getDeliveryReport() {
        return this.deliveryReport;
    }

    public final ReportDelivery set(ArrayList<DeliveryInfo> info, long totalTime) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        DeliveryReport deliveryReport = this.deliveryReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        deliveryReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        if (this.deliveryReport.getTask_id() <= 0) {
            this.deliveryReport.setTask_id(System.currentTimeMillis() / 1000);
        }
        this.deliveryReport.setTotal_time(totalTime);
        double d = 0.0d;
        for (DeliveryInfo deliveryInfo : info) {
            DeliveryReport deliveryReport2 = this.deliveryReport;
            deliveryReport2.setMileage(deliveryReport2.getMileage() + deliveryInfo.mileage);
            if (deliveryInfo.average != 0.0d) {
                d += deliveryInfo.mileage / deliveryInfo.average;
            }
            if (d != 0.0d) {
                DeliveryReport deliveryReport3 = this.deliveryReport;
                deliveryReport3.setAverage(deliveryReport3.getMileage() / d);
            }
        }
        this.deliveryReport.getInfo().addAll(info);
        return this;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        if (this.deliveryReport.getInfo().isEmpty()) {
            return null;
        }
        return this.deliveryReport;
    }
}
