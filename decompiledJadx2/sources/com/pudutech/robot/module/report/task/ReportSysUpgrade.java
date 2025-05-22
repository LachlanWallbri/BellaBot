package com.pudutech.robot.module.report.task;

import android.util.Log;
import com.google.gson.Gson;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.UpgradeReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportSysUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportSysUpgrade;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "KEY_SYSTEM_UPGRADE_INFO", "", "TAG", "UPGRADE_SYSTEM_NAME", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "record", "", "targetVerCode", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportSysUpgrade extends BaseReportTask {
    private final String TAG = "ReportUpgrade";
    private final String KEY_SYSTEM_UPGRADE_INFO = "key_system_upgrade_info";
    private final String UPGRADE_SYSTEM_NAME = AIUIConstant.AUDIO_CAPTOR_SYSTEM;

    public final void record(String targetVerCode) {
        Intrinsics.checkParameterIsNotNull(targetVerCode, "targetVerCode");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "recordSystemUpgrade : targetVerCode = " + targetVerCode);
        UpgradeReport upgradeReport = new UpgradeReport();
        upgradeReport.setName(this.UPGRADE_SYSTEM_NAME);
        upgradeReport.setVername_from(PuduReportManager.INSTANCE.getSystemVersionCode$module_robot_report_release());
        upgradeReport.setVername_to(targetVerCode);
        String json = new Gson().toJson(upgradeReport);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "recordSystemUpgrade record = " + json);
        PuduReportManager.INSTANCE.getSharedPreferences$module_robot_report_release().edit().putString(this.KEY_SYSTEM_UPGRADE_INFO, json).apply();
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        String string = PuduReportManager.INSTANCE.getSharedPreferences$module_robot_report_release().getString(this.KEY_SYSTEM_UPGRADE_INFO, "");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade = " + string);
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        try {
            UpgradeReport upgradeReport = (UpgradeReport) getGson().fromJson(string, UpgradeReport.class);
            String vername_to = upgradeReport.getVername_to();
            String systemVersionCode$module_robot_report_release = PuduReportManager.INSTANCE.getSystemVersionCode$module_robot_report_release();
            NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade vernameTo = " + vername_to + " , currentVCode = " + systemVersionCode$module_robot_report_release);
            upgradeReport.setStatus(Intrinsics.areEqual(vername_to, systemVersionCode$module_robot_report_release));
            return upgradeReport;
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String str2 = this.TAG;
            String stackTraceString = Log.getStackTraceString(e);
            Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
            netWorkLog.mo3279e(str2, stackTraceString);
            return null;
        } finally {
            PuduReportManager.INSTANCE.getSharedPreferences$module_robot_report_release().edit().putString(this.KEY_SYSTEM_UPGRADE_INFO, "").apply();
        }
    }
}
