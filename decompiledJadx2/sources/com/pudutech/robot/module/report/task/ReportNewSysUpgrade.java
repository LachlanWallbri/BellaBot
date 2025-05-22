package com.pudutech.robot.module.report.task;

import android.util.Log;
import com.google.gson.Gson;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.presenter.utils.SystemTool;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.UpgradeReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ReportNewSysUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportNewSysUpgrade;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "KEY_SYSTEM_UPGRADE_INFO", "", "TAG", "UPGRADE_SYSTEM_NAME", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "record", "", "targetVerCode", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportNewSysUpgrade extends BaseReportTask {
    private final String TAG = "ReportSysUpgrade";
    private final String KEY_SYSTEM_UPGRADE_INFO = "key_system_upgrade_info_new";
    private final String UPGRADE_SYSTEM_NAME = AIUIConstant.AUDIO_CAPTOR_SYSTEM;

    public final void record(String targetVerCode) {
        Intrinsics.checkParameterIsNotNull(targetVerCode, "targetVerCode");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "recordSystemUpgrade : targetVerCode = " + targetVerCode);
        UpgradeReport upgradeReport = new UpgradeReport();
        upgradeReport.setName(this.UPGRADE_SYSTEM_NAME);
        upgradeReport.setVername_from(SystemTool.INSTANCE.getProperty(BaseApplication.INSTANCE.getInstance(), "ro.pudutech.version_code"));
        upgradeReport.setVername_to(targetVerCode);
        String json = new Gson().toJson(upgradeReport);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "recordSystemUpgrade record = " + json);
        MMKVManager.INSTANCE.getINSTANCE().set(this.KEY_SYSTEM_UPGRADE_INFO, json);
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(this.KEY_SYSTEM_UPGRADE_INFO, "");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade = " + string);
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            string = SpUtils.get(BaseApplication.INSTANCE.getInstance(), "key_system_upgrade_info", "");
            NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade oldJson= " + string);
        }
        String str2 = string;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return null;
        }
        try {
            UpgradeReport upgradeReport = (UpgradeReport) getGson().fromJson(string, UpgradeReport.class);
            String vername_to = upgradeReport.getVername_to();
            String property = SystemTool.INSTANCE.getProperty(BaseApplication.INSTANCE.getInstance(), "ro.pudutech.version_code");
            NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade vernameTo = " + vername_to + " , currentVCode = " + property);
            upgradeReport.setStatus(Intrinsics.areEqual(vername_to, property));
            return upgradeReport;
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String str3 = this.TAG;
            String stackTraceString = Log.getStackTraceString(e);
            Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
            netWorkLog.mo3279e(str3, stackTraceString);
            return null;
        } finally {
            MMKVManager.INSTANCE.getINSTANCE().set(this.KEY_SYSTEM_UPGRADE_INFO, "");
            SpUtils.set(BaseApplication.INSTANCE.getInstance(), "key_system_upgrade_info", "");
        }
    }
}
