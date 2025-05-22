package com.pudutech.peanut.robot_ui.manager;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.Log;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.light_network.download.DownloadInfo;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowDownloadingDialog;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.robot.module.report.task.ReportAppUpgrade;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, m3961d2 = {"com/pudutech/peanut/robot_ui/manager/AppUpdateManager$downloadAndShowDialog$1", "Lcom/pudutech/lib_update/listener/IShowProgress;", "downloadProgress", "", "getDownloadProgress", "()I", "setDownloadProgress", "(I)V", "onFail", "", C3898x.f4338g, "", "onFinish", "onProgress", "downloadInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "onStartProgress", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AppUpdateManager$downloadAndShowDialog$1 implements IShowProgress {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ ShowDownloadingDialog $checkUpdateDownloadingDialog;
    final /* synthetic */ Function0 $onFailed;
    final /* synthetic */ VerionResult $vr;
    private int downloadProgress;

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onStartProgress() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppUpdateManager$downloadAndShowDialog$1(Activity activity, ShowDownloadingDialog showDownloadingDialog, Function0 function0, VerionResult verionResult) {
        this.$activity = activity;
        this.$checkUpdateDownloadingDialog = showDownloadingDialog;
        this.$onFailed = function0;
        this.$vr = verionResult;
    }

    public final int getDownloadProgress() {
        return this.downloadProgress;
    }

    public final void setDownloadProgress(int i) {
        this.downloadProgress = i;
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFail(Throwable e) {
        String str;
        String str2;
        String str3;
        Intrinsics.checkParameterIsNotNull(e, "e");
        if (this.$activity.isFinishing() || this.$activity.isDestroyed()) {
            AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
            str = AppUpdateManager.TAG;
            Pdlog.m3274e(str, "downloadAndShowDialog : ac is finish");
            return;
        }
        this.$checkUpdateDownloadingDialog.dismiss();
        AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
        str2 = AppUpdateManager.TAG;
        Pdlog.m3274e(str2, "onFail ac = " + this.$activity + ", " + Log.getStackTraceString(e));
        if (AppUpdateManager.INSTANCE.getShowTipDialog() == null) {
            AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
            Activity activity = this.$activity;
            if (activity == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
            }
            appUpdateManager3.setShowTipDialog(new ShowTipMsgDialog(activity));
        }
        AppUpdateManager appUpdateManager4 = AppUpdateManager.INSTANCE;
        str3 = AppUpdateManager.TAG;
        Pdlog.m3274e(str3, "onFail showTipDialog = " + AppUpdateManager.INSTANCE.getShowTipDialog());
        ShowTipMsgDialog showTipDialog = AppUpdateManager.INSTANCE.getShowTipDialog();
        if (showTipDialog != null) {
            showTipDialog.setCanCancel(true);
        }
        ShowTipMsgDialog showTipDialog2 = AppUpdateManager.INSTANCE.getShowTipDialog();
        if (showTipDialog2 != null) {
            String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr7_50);
            Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr7_50)");
            showTipDialog2.showTipMsg(string);
        }
        ShowTipMsgDialog showTipDialog3 = AppUpdateManager.INSTANCE.getShowTipDialog();
        if (showTipDialog3 != null) {
            showTipDialog3.show();
        }
        ShowTipMsgDialog showTipDialog4 = AppUpdateManager.INSTANCE.getShowTipDialog();
        if (showTipDialog4 != null) {
            showTipDialog4.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.manager.AppUpdateManager$downloadAndShowDialog$1$onFail$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    AppUpdateManager$downloadAndShowDialog$1.this.$onFailed.invoke();
                }
            });
        }
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFinish() {
        String str;
        if (this.$activity.isFinishing() || this.$activity.isDestroyed()) {
            AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
            str = AppUpdateManager.TAG;
            Pdlog.m3274e(str, "downloadAndShowDialog : ac is finish");
            return;
        }
        this.$checkUpdateDownloadingDialog.dismiss();
        ReportAppUpgrade reportAppUpgrade = new ReportAppUpgrade();
        Version version = this.$vr.getVersion();
        if (version == null) {
            Intrinsics.throwNpe();
        }
        reportAppUpgrade.record(version.getVersion_name());
        this.$checkUpdateDownloadingDialog.dismisscheckUpdateDownloadingDialog();
        if (AppUpdateManager.INSTANCE.getShowTipDialog() == null) {
            AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
            Activity activity = this.$activity;
            if (activity == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
            }
            appUpdateManager2.setShowTipDialog(new ShowTipMsgDialog(activity));
        }
        ShowTipMsgDialog showTipDialog = AppUpdateManager.INSTANCE.getShowTipDialog();
        if (showTipDialog != null) {
            showTipDialog.setCanCancel(false);
        }
        ShowTipMsgDialog showTipDialog2 = AppUpdateManager.INSTANCE.getShowTipDialog();
        if (showTipDialog2 != null) {
            String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr7_51);
            Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr7_51)");
            showTipDialog2.showTipMsg(string);
        }
        ShowTipMsgDialog showTipDialog3 = AppUpdateManager.INSTANCE.getShowTipDialog();
        if (showTipDialog3 != null) {
            showTipDialog3.show();
        }
    }

    public void onProgress(DownloadInfo downloadInfo) {
        String str;
        Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
        int progress = (int) ((((float) downloadInfo.getProgress()) / ((float) downloadInfo.getTotal())) * 100);
        if (progress != this.downloadProgress) {
            AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
            str = AppUpdateManager.TAG;
            Pdlog.m3273d(str, "onProgress " + progress);
            this.downloadProgress = progress;
            this.$checkUpdateDownloadingDialog.showProgress(String.valueOf(this.downloadProgress));
        }
    }
}
