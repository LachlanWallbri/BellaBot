package com.pudutech.module_robot_selfcheck.domain.request;

import android.app.Application;
import android.content.DialogInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.dialog.ShowTipsDialog;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.robot.update.AppUpdateManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateSoftwareRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/module_robot_selfcheck/domain/request/UpdateSoftwareRequest$requestUpdateSoftware$1", "Lcom/pudutech/robot/update/AppUpdateManager$OnCheckResult;", "onFailed", "", "onSuccess", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UpdateSoftwareRequest$requestUpdateSoftware$1 implements AppUpdateManager.OnCheckResult {
    final /* synthetic */ Application $context;
    final /* synthetic */ UpdateSoftwareRequest this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UpdateSoftwareRequest$requestUpdateSoftware$1(UpdateSoftwareRequest updateSoftwareRequest, Application application) {
        this.this$0 = updateSoftwareRequest;
        this.$context = application;
    }

    @Override // com.pudutech.robot.update.AppUpdateManager.OnCheckResult
    public void onSuccess(VerionResult vr) {
        String tag;
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        tag = this.this$0.getTAG();
        Pdlog.m3273d(tag, "checkSoftwareUpdate onSuccess " + vr + " , Power = " + BatteryInfoManager.INSTANCE.getPower());
        AppUpdateManager.UpdateLevel updateLevel = AppUpdateManager.INSTANCE.getUpdateLevel(vr);
        if (updateLevel == AppUpdateManager.UpdateLevel.emergency || updateLevel == AppUpdateManager.UpdateLevel.major) {
            Integer power = BatteryInfoManager.INSTANCE.getPower();
            if ((power != null ? power.intValue() : 0) > 20) {
                ShowTipsDialog showTipsDialog = new ShowTipsDialog(this.$context);
                showTipsDialog.setCancelable(false);
                AppUpdateManager.showResult$default(AppUpdateManager.INSTANCE, showTipsDialog, vr, null, null, 12, null);
                showTipsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.module_robot_selfcheck.domain.request.UpdateSoftwareRequest$requestUpdateSoftware$1$onSuccess$1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        String tag2;
                        tag2 = UpdateSoftwareRequest$requestUpdateSoftware$1.this.this$0.getTAG();
                        Pdlog.m3273d(tag2, "AppUpdateManager showResult OnDismiss ");
                        UpdateSoftwareRequest$requestUpdateSoftware$1.this.this$0.getIsSkipUpdate().postValue(true);
                    }
                });
                return;
            }
        }
        this.this$0.getIsSkipUpdate().postValue(true);
    }

    @Override // com.pudutech.robot.update.AppUpdateManager.OnCheckResult
    public void onFailed() {
        String tag;
        tag = this.this$0.getTAG();
        Pdlog.m3277w(tag, "checkSoftwareUpdate onFailed()");
        this.this$0.getIsSkipUpdate().postValue(true);
    }
}
