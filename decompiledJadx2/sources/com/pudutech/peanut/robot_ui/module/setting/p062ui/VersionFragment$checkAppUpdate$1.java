package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.manager.AppUpdateManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VersionFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/peanut/robot_ui/module/setting/ui/VersionFragment$checkAppUpdate$1", "Lcom/pudutech/peanut/robot_ui/manager/AppUpdateManager$OnCheckResult;", "onFailed", "", "onSuccess", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class VersionFragment$checkAppUpdate$1 implements AppUpdateManager.OnCheckResult {
    final /* synthetic */ VersionFragment this$0;

    @Override // com.pudutech.peanut.robot_ui.manager.AppUpdateManager.OnCheckResult
    public void onFailed() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VersionFragment$checkAppUpdate$1(VersionFragment versionFragment) {
        this.this$0 = versionFragment;
    }

    @Override // com.pudutech.peanut.robot_ui.manager.AppUpdateManager.OnCheckResult
    public void onSuccess(final VerionResult vr) {
        String str;
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        if (this.this$0.getContext() == null || this.this$0.isDetached()) {
            str = this.this$0.TAG;
            Pdlog.m3274e(str, "onSuccess : vr = " + vr + "; isDetached ");
            return;
        }
        if (vr.getAvailable() && vr.getVersion() != null) {
            TextView version_show = (TextView) this.this$0._$_findCachedViewById(C5508R.id.version_show);
            Intrinsics.checkExpressionValueIsNotNull(version_show, "version_show");
            Version version = vr.getVersion();
            version_show.setText(version != null ? version.getVersion_name() : null);
            if (AppUpdateManager.INSTANCE.getUpdateLevel(vr) == AppUpdateManager.UpdateLevel.emergency) {
                Integer power = BatteryInfoManager.INSTANCE.getPower();
                if ((power != null ? power.intValue() : 0) > 20) {
                    AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
                    Context context = this.this$0.getContext();
                    if (context == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                    }
                    appUpdateManager.showUpdateResultDialog((Activity) context, vr, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VersionFragment$checkAppUpdate$1$onSuccess$1
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
                                AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                                Context context2 = VersionFragment$checkAppUpdate$1.this.this$0.getContext();
                                if (context2 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                                }
                                appUpdateManager2.downloadAndShowDialog((Activity) context2, vr, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VersionFragment$checkAppUpdate$1$onSuccess$1.1
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
                                        String str2;
                                        str2 = VersionFragment$checkAppUpdate$1.this.this$0.TAG;
                                        Pdlog.m3274e(str2, "AppUpdateManager : downloadAndShowDialog failed");
                                    }
                                });
                                return;
                            }
                            FragmentActivity it = VersionFragment$checkAppUpdate$1.this.this$0.getActivity();
                            if (it != null) {
                                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                if (it.isFinishing() || it.isDestroyed()) {
                                    return;
                                }
                                VersionFragment versionFragment = VersionFragment$checkAppUpdate$1.this.this$0;
                                String string = VersionFragment$checkAppUpdate$1.this.this$0.getString(C5508R.string.pdStr7_49);
                                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_49)");
                                versionFragment.showTipDialog(string);
                            }
                        }
                    }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VersionFragment$checkAppUpdate$1$onSuccess$2
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
                return;
            }
            return;
        }
        TextView version_show2 = (TextView) this.this$0._$_findCachedViewById(C5508R.id.version_show);
        Intrinsics.checkExpressionValueIsNotNull(version_show2, "version_show");
        version_show2.setText(this.this$0.getString(C5508R.string.pdStr7_47));
    }
}
