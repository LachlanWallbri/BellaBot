package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.view.View;
import android.view.ViewStub;
import android.widget.RadioGroup;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceRadioGroupChangeListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Landroid/widget/RadioGroup;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class AdvancedSettingsFragment$steadTypeLayout$2 extends Lambda implements Function0<RadioGroup> {
    final /* synthetic */ AdvancedSettingsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdvancedSettingsFragment$steadTypeLayout$2(AdvancedSettingsFragment advancedSettingsFragment) {
        super(0);
        this.this$0 = advancedSettingsFragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final RadioGroup invoke() {
        View inflate = ((ViewStub) this.this$0.getView().findViewById(C4188R.id.steady_type_stub)).inflate();
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RadioGroup");
        }
        RadioGroup radioGroup = (RadioGroup) inflate;
        int i = C4188R.id.rbt_single;
        int steadyModeType = Constans.INSTANCE.getSteadyModeType();
        if (steadyModeType == 0) {
            i = C4188R.id.rbt_single;
        } else if (steadyModeType == 1) {
            i = C4188R.id.rbt_long;
        } else if (steadyModeType == 2) {
            i = C4188R.id.rbt_repeat_last;
        }
        radioGroup.check(i);
        radioGroup.setOnCheckedChangeListener(new VoiceRadioGroupChangeListener(null, 0, new Function2<RadioGroup, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$steadTypeLayout$2$$special$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RadioGroup radioGroup2, Integer num) {
                invoke(radioGroup2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(RadioGroup group, int i2) {
                String str;
                Intrinsics.checkParameterIsNotNull(group, "group");
                if (i2 == C4188R.id.rbt_single) {
                    Constans.INSTANCE.setSteadyModeType(0);
                } else if (i2 == C4188R.id.rbt_long) {
                    Constans.INSTANCE.setSteadyModeType(1);
                } else if (i2 == C4188R.id.rbt_repeat_last) {
                    Constans.INSTANCE.setSteadyModeType(2);
                }
                str = AdvancedSettingsFragment$steadTypeLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str, "steadTypeLayout mode = " + Constans.INSTANCE.getSteadyModeType());
            }
        }, 3, null));
        return radioGroup;
    }
}
