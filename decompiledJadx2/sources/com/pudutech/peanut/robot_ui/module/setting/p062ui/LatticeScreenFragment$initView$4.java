package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import androidx.fragment.app.FragmentManager;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LatticeInputDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LatticeScreenFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/peanut/robot_ui/module/setting/ui/LatticeScreenFragment$initView$4", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LatticeScreenFragment$initView$4 extends OnLazyVoiceClickListener {
    final /* synthetic */ LatticeScreenFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LatticeScreenFragment$initView$4(LatticeScreenFragment latticeScreenFragment) {
        this.this$0 = latticeScreenFragment;
    }

    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "guide_table_text onSingleClick");
        LatticeInputDialog latticeInputDialog = new LatticeInputDialog();
        latticeInputDialog.setContent(SpUtils.get(this.this$0.getContext(), "key_lattice_guide_table", ""));
        latticeInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.LatticeScreenFragment$initView$4$onSingleClick$1
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
                SpUtils.set(LatticeScreenFragment$initView$4.this.this$0.getContext(), "key_lattice_guide_table", it);
            }
        });
        FragmentManager fragmentManager = this.this$0.getFragmentManager();
        if (fragmentManager == null) {
            Intrinsics.throwNpe();
        }
        latticeInputDialog.show(fragmentManager, "LatticeInputDialog");
    }
}
