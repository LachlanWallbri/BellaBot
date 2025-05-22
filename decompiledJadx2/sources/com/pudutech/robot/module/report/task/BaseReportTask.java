package com.pudutech.robot.module.report.task;

import com.google.gson.Gson;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseApiNoNumReport;
import com.pudutech.robot.module.report.protocol.BaseApiReport;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.BaseV2Report;
import com.pudutech.robot.module.report.protocol.BaseV4Report;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseReportTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/BaseReportTask;", "", "()V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "report", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseReportTask {
    private final Gson gson = new Gson();

    public abstract BaseReport getReportData();

    /* JADX INFO: Access modifiers changed from: protected */
    public final Gson getGson() {
        return this.gson;
    }

    public final void report() {
        BaseReport reportData = getReportData();
        if (reportData != null) {
            if (reportData instanceof BaseV4Report) {
                PuduReportManager.INSTANCE.reportV4$module_robot_report_release((BaseV4Report) reportData, reportData.getUrlPath());
                return;
            }
            if (reportData instanceof BaseV2Report) {
                PuduReportManager.report$module_robot_report_release$default(PuduReportManager.INSTANCE, (BaseV2Report) reportData, false, reportData.getUrlPath(), false, 10, null);
            } else if (reportData instanceof BaseApiReport) {
                PuduReportManager.reportApi$module_robot_report_release$default(PuduReportManager.INSTANCE, (BaseApiReport) reportData, false, reportData.getUrlPath(), 2, null);
            } else if (reportData instanceof BaseApiNoNumReport) {
                PuduReportManager.reportNoNumApi$module_robot_report_release$default(PuduReportManager.INSTANCE, (BaseApiNoNumReport) reportData, false, reportData.getUrlPath(), 2, null);
            }
        }
    }
}
