package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.view.View;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ConfirmCountdownDialog;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class AdvancedSettingsFragment$initClean$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ AdvancedSettingsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdvancedSettingsFragment$initClean$1(AdvancedSettingsFragment advancedSettingsFragment) {
        super(1);
        this.this$0 = advancedSettingsFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(View view) {
        invoke2(view);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(View it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        Context it2 = this.this$0.getContext();
        if (it2 != null) {
            if (CoreDevices.INSTANCE.getBattery().getPowerPercent() >= 10) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                final ConfirmCountdownDialog confirmCountdownDialog = new ConfirmCountdownDialog(it2);
                String string = this.this$0.getString(C4188R.string.pdStr11_18);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr11_18)");
                confirmCountdownDialog.setTitle(string);
                String string2 = this.this$0.getString(C4188R.string.advance_clean_content);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.advance_clean_content)");
                confirmCountdownDialog.setContent(string2);
                String string3 = this.this$0.getString(C4188R.string.box_delete_no);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.box_delete_no)");
                confirmCountdownDialog.setBtn1Text(string3);
                String string4 = this.this$0.getString(C4188R.string.box_delete_yes);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.box_delete_yes)");
                confirmCountdownDialog.setBtn2Text(string4);
                confirmCountdownDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initClean$1$1$1
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
                        ConfirmCountdownDialog.this.dismiss();
                    }
                });
                confirmCountdownDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initClean$1$$special$$inlined$let$lambda$1
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
                        this.this$0.showCleanWaitDialog();
                        ConfirmCountdownDialog.this.dismiss();
                    }
                });
                confirmCountdownDialog.show();
                return;
            }
            ToastUtils.show(it2, C4188R.string.advance_clean_warn_toast);
        }
    }
}
