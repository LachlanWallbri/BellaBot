package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ConfirmTipDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$initQrCodeView$4", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AdvancedSettingsFragment$initQrCodeView$4 extends OnLazyClickListener {
    final /* synthetic */ AdvancedSettingsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdvancedSettingsFragment$initQrCodeView$4(AdvancedSettingsFragment advancedSettingsFragment) {
        this.this$0 = advancedSettingsFragment;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
    public void onSingleClick() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "unbind_all_tv onSingleClick ");
        Context context = this.this$0.getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        final ConfirmTipDialog confirmTipDialog = new ConfirmTipDialog(context);
        String string = this.this$0.getString(C4188R.string.pdStr5_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr5_1)");
        confirmTipDialog.setTitle(string);
        String string2 = this.this$0.getString(C4188R.string.pdStr8_2);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr8_2)");
        confirmTipDialog.setBtn1Text(string2);
        String string3 = this.this$0.getString(C4188R.string.pdStr8_1);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr8_1)");
        confirmTipDialog.setBtn2Text(string3);
        String string4 = this.this$0.getString(C4188R.string.pdStr7_145);
        Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr7_145)");
        confirmTipDialog.setMsg(string4);
        confirmTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$4$onSingleClick$1
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
                ConfirmTipDialog.this.dismiss();
            }
        });
        confirmTipDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$4$onSingleClick$2
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
                BeeperBindPresenter beeperBindPresenter;
                confirmTipDialog.dismiss();
                beeperBindPresenter = AdvancedSettingsFragment$initQrCodeView$4.this.this$0.getBeeperBindPresenter();
                beeperBindPresenter.unBindAllDevice(new ICallback() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$4$onSingleClick$2.1
                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onFailed(Exception e) {
                        String str2;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        str2 = AdvancedSettingsFragment$initQrCodeView$4.this.this$0.TAG;
                        Pdlog.m3274e(str2, "unBindAllDevice onFailed , " + Log.getStackTraceString(e));
                        Context context2 = AdvancedSettingsFragment$initQrCodeView$4.this.this$0.getContext();
                        if (context2 != null) {
                            ToastUtils.show(context2, AdvancedSettingsFragment$initQrCodeView$4.this.this$0.getString(C4188R.string.pdStr7_147), new Object[0]);
                        }
                    }

                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onSuccess(IBody result) {
                        String str2;
                        str2 = AdvancedSettingsFragment$initQrCodeView$4.this.this$0.TAG;
                        Pdlog.m3273d(str2, "unBindAllDevice onSuccess ");
                    }
                });
            }
        });
        confirmTipDialog.show();
    }
}
