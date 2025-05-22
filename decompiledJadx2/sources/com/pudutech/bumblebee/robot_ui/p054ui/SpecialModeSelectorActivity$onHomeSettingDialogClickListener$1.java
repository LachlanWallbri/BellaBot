package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.OnHomeSettingDialogClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialModeSelectorActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/OnHomeSettingDialogClickListener;", "onFunClick", "", "type", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;", "intent", "Landroid/content/Intent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1 implements OnHomeSettingDialogClickListener {
    final /* synthetic */ SpecialModeSelectorActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1(SpecialModeSelectorActivity specialModeSelectorActivity) {
        this.this$0 = specialModeSelectorActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.OnHomeSettingDialogClickListener
    public void onFunClick(final HomeSettingDialog.FunctionType type, final Intent intent) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type == HomeSettingDialog.FunctionType.SPECIAL_MODE) {
            return;
        }
        if (type == HomeSettingDialog.FunctionType.MUSIC_MODE) {
            this.this$0.isJumpToMusicAc = true;
            this.this$0.release();
        } else if (intent != null) {
            if (type == HomeSettingDialog.FunctionType.SETTING && Constans.INSTANCE.getSettingEnterSwitch()) {
                MyBaseActivity.showPasswordDialog$default(this.this$0, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1$onFunClick$$inlined$let$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        if (z) {
                            this.this$0.dismissPasswordDialog();
                            this.this$0.jumpAndFinish(intent);
                        }
                    }
                }, null, 2, null);
            } else {
                this.this$0.jumpAndFinish(intent);
            }
        }
    }
}
