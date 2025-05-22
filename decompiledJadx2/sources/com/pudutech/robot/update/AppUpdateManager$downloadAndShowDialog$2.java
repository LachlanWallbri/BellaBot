package com.pudutech.robot.update;

import android.util.Log;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.dialog.ShowTipsDialog;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.light_network.download.DownloadInfo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AppUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, m3961d2 = {"com/pudutech/robot/update/AppUpdateManager$downloadAndShowDialog$2", "Lcom/pudutech/lib_update/listener/IShowProgress;", "downloadProgress", "", "getDownloadProgress", "()I", "setDownloadProgress", "(I)V", "isDownFinish", "", "()Z", "setDownFinish", "(Z)V", "onFail", "", C3898x.f4338g, "", "onFinish", "onProgress", "downloadInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "onStartProgress", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AppUpdateManager$downloadAndShowDialog$2 implements IShowProgress {
    final /* synthetic */ ShowTipsDialog $dialog;
    final /* synthetic */ Function0 $onDownFinish;
    private int downloadProgress;
    private boolean isDownFinish;

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onStartProgress() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppUpdateManager$downloadAndShowDialog$2(ShowTipsDialog showTipsDialog, Function0 function0) {
        this.$dialog = showTipsDialog;
        this.$onDownFinish = function0;
    }

    public final int getDownloadProgress() {
        return this.downloadProgress;
    }

    public final void setDownloadProgress(int i) {
        this.downloadProgress = i;
    }

    /* renamed from: isDownFinish, reason: from getter */
    public final boolean getIsDownFinish() {
        return this.isDownFinish;
    }

    public final void setDownFinish(boolean z) {
        this.isDownFinish = z;
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFail(Throwable e) {
        String str;
        Intrinsics.checkParameterIsNotNull(e, "e");
        AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
        str = AppUpdateManager.TAG;
        Pdlog.m3274e(str, "onFail : " + Log.getStackTraceString(e));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AppUpdateManager$downloadAndShowDialog$2$onFail$1(this, null), 2, null);
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFinish() {
        this.isDownFinish = true;
        Function0 function0 = this.$onDownFinish;
        if (function0 != null) {
        }
        if (this.$dialog.isShowing()) {
            this.$dialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_AND_TIPS());
            ShowTipsDialog showTipsDialog = this.$dialog;
            String string = AppUpdateManager.access$getContext$p(AppUpdateManager.INSTANCE).getString(C5722R.string.pdStr27_7);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr27_7)");
            showTipsDialog.setTips(string);
            this.$dialog.setCancelable(false);
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
            if (this.$dialog.isShowing()) {
                this.$dialog.setProgress(String.valueOf(this.downloadProgress));
                this.$dialog.setCancelable(false);
            }
        }
    }
}
