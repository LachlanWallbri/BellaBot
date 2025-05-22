package com.pudutech.bumblebee.presenter.report_cloud.task;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.UpgradeReport;
import com.pudutech.robot.module.report.task.BaseReportTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ReportNewAppUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportNewAppUpgrade;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "KEY_APP_UPGRADE_INFO", "", "TAG", "UPGRADE_APP_NAME", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "recordAppUpgrade", "", "appContext", "Landroid/content/Context;", "targetVerCode", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportNewAppUpgrade extends BaseReportTask {
    private final String TAG = "ReportAppUpgrade";
    private final String UPGRADE_APP_NAME = "software";
    private final String KEY_APP_UPGRADE_INFO = "key_app_upgrade_info_new";

    public final void recordAppUpgrade(Context appContext, String targetVerCode) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(targetVerCode, "targetVerCode");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "recordSystemUpgrade : targetVerCode = " + targetVerCode);
        UpgradeReport upgradeReport = new UpgradeReport();
        upgradeReport.setName(this.UPGRADE_APP_NAME);
        String str = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0).versionName;
        Intrinsics.checkExpressionValueIsNotNull(str, "appContext.packageManage…ckageName, 0).versionName");
        upgradeReport.setVername_from(str);
        upgradeReport.setVername_to(targetVerCode);
        String json = new Gson().toJson(upgradeReport);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "recordSystemUpgrade record = " + json);
        MMKVManager.INSTANCE.getINSTANCE().set(this.KEY_APP_UPGRADE_INFO, json);
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(this.KEY_APP_UPGRADE_INFO, "");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade json = " + string);
        String str = string;
        boolean z = true;
        if (str == null || StringsKt.isBlank(str)) {
            string = SpUtils.get(BaseApplication.INSTANCE.getInstance(), "key_app_upgrade_info", "");
            NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade jsonOld = " + string);
        }
        String str2 = string;
        if (str2 != null && !StringsKt.isBlank(str2)) {
            z = false;
        }
        if (z) {
            return null;
        }
        try {
            UpgradeReport upgradeReport = (UpgradeReport) getGson().fromJson(string, UpgradeReport.class);
            String vername_to = upgradeReport.getVername_to();
            String str3 = BaseApplication.INSTANCE.getInstance().getPackageManager().getPackageInfo(BaseApplication.INSTANCE.getInstance().getPackageName(), 0).versionName;
            NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportSystemUpgrade vernameTo = " + vername_to + " , currentVCode = " + str3);
            upgradeReport.setStatus(Intrinsics.areEqual(vername_to, str3));
            return upgradeReport;
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String str4 = this.TAG;
            String stackTraceString = Log.getStackTraceString(e);
            Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
            netWorkLog.mo3279e(str4, stackTraceString);
            return null;
        } finally {
            MMKVManager.INSTANCE.getINSTANCE().set(this.KEY_APP_UPGRADE_INFO, "");
            SpUtils.set(BaseApplication.INSTANCE.getInstance(), "key_app_upgrade_info", "");
        }
    }
}
