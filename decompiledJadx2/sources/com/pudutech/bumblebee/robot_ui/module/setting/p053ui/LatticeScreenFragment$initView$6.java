package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LatticeInputDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LatticeScreenFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/module/setting/ui/LatticeScreenFragment$initView$6", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LatticeScreenFragment$initView$6 extends OnLazyVoiceClickListener {
    final /* synthetic */ LatticeScreenFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LatticeScreenFragment$initView$6(LatticeScreenFragment latticeScreenFragment) {
        super(null, 0, 3, null);
        this.this$0 = latticeScreenFragment;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "recycle_text onSingleClick");
        LatticeInputDialog latticeInputDialog = new LatticeInputDialog();
        latticeInputDialog.setContent(Constans.INSTANCE.getRecycleModeText());
        latticeInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$initView$6$onSingleClick$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LatticeScreenFragment latticeScreenFragment = LatticeScreenFragment$initView$6.this.this$0;
                TextView recycle_text = (TextView) LatticeScreenFragment$initView$6.this.this$0._$_findCachedViewById(C4188R.id.recycle_text);
                Intrinsics.checkExpressionValueIsNotNull(recycle_text, "recycle_text");
                LatticeScreenFragment.changeContent$default(latticeScreenFragment, recycle_text, it, null, 4, null);
                Constans.INSTANCE.setRecycleModeText(it);
            }
        });
        FragmentManager fragmentManager = this.this$0.getFragmentManager();
        if (fragmentManager == null) {
            Intrinsics.throwNpe();
        }
        latticeInputDialog.show(fragmentManager, "LatticeInputDialog");
    }
}
