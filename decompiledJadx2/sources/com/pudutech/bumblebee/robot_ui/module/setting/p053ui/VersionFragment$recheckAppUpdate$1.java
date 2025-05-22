package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VersionFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/module/setting/ui/VersionFragment$recheckAppUpdate$1", "Lcom/pudutech/bumblebee/robot_ui/manager/AppUpdateManager$OnCheckResult;", "onFailed", "", "onSuccess", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VersionFragment$recheckAppUpdate$1 implements AppUpdateManager.OnCheckResult {
    final /* synthetic */ VersionFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VersionFragment$recheckAppUpdate$1(VersionFragment versionFragment) {
        this.this$0 = versionFragment;
    }

    @Override // com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager.OnCheckResult
    public void onSuccess(final VerionResult vr) {
        String str;
        String str2;
        Dialog dialog;
        String str3;
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "recheckAppUpdate onSuccess = " + vr);
        if (this.this$0.getContext() == null || this.this$0.isDetached()) {
            str2 = this.this$0.TAG;
            Pdlog.m3274e(str2, "recheckAppUpdate onSuccess : vr = " + vr + "; isDetached ");
            return;
        }
        dialog = this.this$0.checkUpdateDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        if (!vr.getAvailable() || vr.getVersion() == null) {
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "recheckAppUpdate onSuccess 当前已是最新版本");
            VersionFragment versionFragment = this.this$0;
            String string = versionFragment.getString(C4188R.string.pdStr7_47);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_47)");
            versionFragment.showTipDialog(string);
            return;
        }
        TextView version_show = (TextView) this.this$0._$_findCachedViewById(C4188R.id.version_show);
        Intrinsics.checkExpressionValueIsNotNull(version_show, "version_show");
        Version version = vr.getVersion();
        version_show.setText(version != null ? version.getVersion_name() : null);
        if (AppUpdateManager.INSTANCE.getUpdateLevel(vr) == AppUpdateManager.UpdateLevel.emergency && BatteryInfoManager.INSTANCE.getPower() > 20) {
            AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
            Context context = this.this$0.getContext();
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
            }
            appUpdateManager.showUpdateResultDialog((Activity) context, vr, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$recheckAppUpdate$1$onSuccess$1
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
                    String str4;
                    if (BatteryInfoManager.INSTANCE.getPower() > 20) {
                        AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                        Context context2 = VersionFragment$recheckAppUpdate$1.this.this$0.getContext();
                        if (context2 != null) {
                            appUpdateManager2.downloadAndShowDialog((Activity) context2, vr, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$recheckAppUpdate$1$onSuccess$1.1
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
                                    String str5;
                                    str5 = VersionFragment$recheckAppUpdate$1.this.this$0.TAG;
                                    Pdlog.m3274e(str5, "AppUpdateManager : downloadAndShowDialog failed");
                                }
                            });
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                    }
                    VersionFragment versionFragment2 = VersionFragment$recheckAppUpdate$1.this.this$0;
                    String string2 = VersionFragment$recheckAppUpdate$1.this.this$0.getString(C4188R.string.pdStr7_49);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr7_49)");
                    versionFragment2.showTipDialog(string2);
                    str4 = VersionFragment$recheckAppUpdate$1.this.this$0.TAG;
                    Pdlog.m3273d(str4, "recheckAppUpdate onSuccess 电量过低，无法完成升级");
                }
            }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$recheckAppUpdate$1$onSuccess$2
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
        AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
        Context context2 = this.this$0.getContext();
        if (context2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        AppUpdateManager.showUpdateResultDialog$default(appUpdateManager2, (Activity) context2, vr, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$recheckAppUpdate$1$onSuccess$3
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
                String str4;
                if (BatteryInfoManager.INSTANCE.getPower() > 20) {
                    AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
                    Context context3 = VersionFragment$recheckAppUpdate$1.this.this$0.getContext();
                    if (context3 != null) {
                        appUpdateManager3.downloadAndShowDialog((Activity) context3, vr, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$recheckAppUpdate$1$onSuccess$3.1
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }
                        });
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                }
                VersionFragment versionFragment2 = VersionFragment$recheckAppUpdate$1.this.this$0;
                String string2 = VersionFragment$recheckAppUpdate$1.this.this$0.getString(C4188R.string.pdStr7_49);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr7_49)");
                versionFragment2.showTipDialog(string2);
                str4 = VersionFragment$recheckAppUpdate$1.this.this$0.TAG;
                Pdlog.m3273d(str4, "recheckAppUpdate onSuccess 电量过低，无法完成升级");
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$recheckAppUpdate$1$onSuccess$4
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
                String str4;
                str4 = VersionFragment$recheckAppUpdate$1.this.this$0.TAG;
                Pdlog.m3273d(str4, "recheckAppUpdate showUpdateResultDialog cancle ");
            }
        }, false, 16, null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager.OnCheckResult
    public void onFailed() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "recheckAppUpdate onFailed()");
        VersionFragment versionFragment = this.this$0;
        String string = versionFragment.getString(C4188R.string.pdStr7_50);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_50)");
        versionFragment.showTipDialog(string);
    }
}
