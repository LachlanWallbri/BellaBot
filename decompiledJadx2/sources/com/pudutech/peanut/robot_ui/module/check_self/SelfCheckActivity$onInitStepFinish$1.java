package com.pudutech.peanut.robot_ui.module.check_self;

import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.manager.AppUpdateManager;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelfCheckActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/peanut/robot_ui/module/check_self/SelfCheckActivity$onInitStepFinish$1", "Lcom/pudutech/peanut/robot_ui/manager/AppUpdateManager$OnCheckResult;", "onFailed", "", "onSuccess", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckActivity$onInitStepFinish$1 implements AppUpdateManager.OnCheckResult {
    final /* synthetic */ SelfCheckActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SelfCheckActivity$onInitStepFinish$1(SelfCheckActivity selfCheckActivity) {
        this.this$0 = selfCheckActivity;
    }

    @Override // com.pudutech.peanut.robot_ui.manager.AppUpdateManager.OnCheckResult
    public void onSuccess(final VerionResult vr) {
        String str;
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "checkAppUpdate onSuccess " + vr + " , Power = " + BatteryInfoManager.INSTANCE.getPower());
        AppUpdateManager.UpdateLevel updateLevel = AppUpdateManager.INSTANCE.getUpdateLevel(vr);
        if (updateLevel == AppUpdateManager.UpdateLevel.emergency) {
            Integer power = BatteryInfoManager.INSTANCE.getPower();
            if ((power != null ? power.intValue() : 0) > 20) {
                AppUpdateManager.INSTANCE.showUpdateResultDialog(this.this$0, vr, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$onInitStepFinish$1$onSuccess$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Integer power2 = BatteryInfoManager.INSTANCE.getPower();
                        if ((power2 != null ? power2.intValue() : 0) > 20) {
                            SelfCheckActivity$onInitStepFinish$1.this.this$0.downAndInstallApp(vr);
                        } else {
                            ToastUtils.show(SelfCheckActivity$onInitStepFinish$1.this.this$0, SelfCheckActivity$onInitStepFinish$1.this.this$0.getString(C5508R.string.pdStr7_49), new Object[0]);
                            SelfCheckActivity$onInitStepFinish$1.this.this$0.checkSystemAndFinish();
                        }
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$onInitStepFinish$1$onSuccess$2
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                }, false);
                return;
            }
        }
        if (updateLevel == AppUpdateManager.UpdateLevel.major) {
            Integer power2 = BatteryInfoManager.INSTANCE.getPower();
            if ((power2 != null ? power2.intValue() : 0) > 20) {
                AppUpdateManager.showUpdateResultDialog$default(AppUpdateManager.INSTANCE, this.this$0, vr, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$onInitStepFinish$1$onSuccess$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        SelfCheckActivity$onInitStepFinish$1.this.this$0.downAndInstallApp(vr);
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$onInitStepFinish$1$onSuccess$4
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        SelfCheckActivity$onInitStepFinish$1.this.this$0.checkSystemAndFinish();
                    }
                }, false, 16, null);
                return;
            }
        }
        this.this$0.checkSystemAndFinish();
    }

    @Override // com.pudutech.peanut.robot_ui.manager.AppUpdateManager.OnCheckResult
    public void onFailed() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3274e(str, "checkAppUpdate onFailed ");
        this.this$0.checkSystemAndFinish();
    }
}
