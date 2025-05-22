package com.pudutech.bumblebee.robot_ui.manager;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.Log;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportUpgrade;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowDownloadingDialog;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0018"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/manager/AppUpdateManager$downloadAndShowDialog$1", "Lcom/pudutech/lib_update/listener/IShowProgress;", "downloadProgress", "", "getDownloadProgress", "()D", "setDownloadProgress", "(D)V", "showTipDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "getShowTipDialog", "()Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "setShowTipDialog", "(Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;)V", "onFail", "", C3898x.f4338g, "", "onFinish", "onProgress", "crtSize", "", "totalSize", "onStartProgress", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AppUpdateManager$downloadAndShowDialog$1 implements IShowProgress {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ ShowDownloadingDialog $checkUpdateDownloadingDialog;
    final /* synthetic */ Function0 $onFailed;
    final /* synthetic */ VerionResult $vr;
    private double downloadProgress;
    private ShowTipMsgDialog showTipDialog;

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onStartProgress() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppUpdateManager$downloadAndShowDialog$1(ShowDownloadingDialog showDownloadingDialog, Activity activity, Function0 function0, VerionResult verionResult) {
        this.$checkUpdateDownloadingDialog = showDownloadingDialog;
        this.$activity = activity;
        this.$onFailed = function0;
        this.$vr = verionResult;
    }

    public final double getDownloadProgress() {
        return this.downloadProgress;
    }

    public final void setDownloadProgress(double d) {
        this.downloadProgress = d;
    }

    public final ShowTipMsgDialog getShowTipDialog() {
        return this.showTipDialog;
    }

    public final void setShowTipDialog(ShowTipMsgDialog showTipMsgDialog) {
        this.showTipDialog = showTipMsgDialog;
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFail(Throwable e) {
        String str;
        Intrinsics.checkParameterIsNotNull(e, "e");
        this.$checkUpdateDownloadingDialog.dismiss();
        AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
        str = AppUpdateManager.TAG;
        Pdlog.m3274e(str, "onFail ac = " + this.$activity + ", " + Log.getStackTraceString(e));
        if (this.showTipDialog == null) {
            Activity activity = this.$activity;
            if (activity == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
            }
            this.showTipDialog = new ShowTipMsgDialog(activity);
        }
        ShowTipMsgDialog showTipMsgDialog = this.showTipDialog;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.setCanCancel(true);
        }
        ShowTipMsgDialog showTipMsgDialog2 = this.showTipDialog;
        if (showTipMsgDialog2 != null) {
            String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_50);
            Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr7_50)");
            showTipMsgDialog2.showTipMsg(string);
        }
        ShowTipMsgDialog showTipMsgDialog3 = this.showTipDialog;
        if (showTipMsgDialog3 != null) {
            showTipMsgDialog3.show();
        }
        ShowTipMsgDialog showTipMsgDialog4 = this.showTipDialog;
        if (showTipMsgDialog4 != null) {
            showTipMsgDialog4.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager$downloadAndShowDialog$1$onFail$1
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
        ReportUpgrade reportUpgrade = ReportUpgrade.INSTANCE;
        Activity activity = this.$activity;
        Version version = this.$vr.getVersion();
        if (version == null) {
            Intrinsics.throwNpe();
        }
        reportUpgrade.recordAppUpgrade(activity, version.getVersion_name());
        this.$checkUpdateDownloadingDialog.dismisscheckUpdateDownloadingDialog();
        if (this.showTipDialog == null) {
            Activity activity2 = this.$activity;
            if (activity2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
            }
            this.showTipDialog = new ShowTipMsgDialog(activity2);
        }
        ShowTipMsgDialog showTipMsgDialog = this.showTipDialog;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.setCanCancel(false);
        }
        ShowTipMsgDialog showTipMsgDialog2 = this.showTipDialog;
        if (showTipMsgDialog2 != null) {
            String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_51);
            Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr7_51)");
            showTipMsgDialog2.showTipMsg(string);
        }
        ShowTipMsgDialog showTipMsgDialog3 = this.showTipDialog;
        if (showTipMsgDialog3 != null) {
            showTipMsgDialog3.show();
        }
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onProgress(long crtSize, long totalSize) {
        String str;
        double d = (crtSize / totalSize) * 100.0d;
        AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
        str = AppUpdateManager.TAG;
        Pdlog.m3273d(str, "onProgress value = " + d);
        if (d != this.downloadProgress) {
            this.downloadProgress = d;
            this.$checkUpdateDownloadingDialog.showProgress(String.valueOf(Math.round(this.downloadProgress * 10) / 10.0d));
        }
    }
}
