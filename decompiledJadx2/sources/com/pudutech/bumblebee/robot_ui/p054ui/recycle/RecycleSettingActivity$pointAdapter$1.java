package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import android.widget.CompoundButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecycleSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000e"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/recycle/RecycleSettingActivity$pointAdapter$1", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "selected", "", "getSelected", "()Ljava/lang/String;", "setSelected", "(Ljava/lang/String;)V", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecycleSettingActivity$pointAdapter$1 extends BaseQuickAdapter<Destination, BaseViewHolder> {
    private String selected;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecycleSettingActivity$pointAdapter$1(int i) {
        super(i);
        this.selected = "";
    }

    public final String getSelected() {
        return this.selected;
    }

    public final void setSelected(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.selected = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, final Destination item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        helper.setText(C4188R.id.name, item.getName());
        helper.setChecked(C4188R.id.f4805rb, Intrinsics.areEqual(item.getName(), this.selected));
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((CompoundButton) helper.getView(C4188R.id.f4805rb), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$pointAdapter$1$convert$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                RecycleSettingActivity$pointAdapter$1.this.setSelected(item.getName());
                RecycleSettingActivity$pointAdapter$1.this.notifyDataSetChanged();
                RobotMapManager.INSTANCE.setDashWashChosen(RecycleSettingActivity$pointAdapter$1.this.getSelected());
            }
        }, 7, null);
    }
}
