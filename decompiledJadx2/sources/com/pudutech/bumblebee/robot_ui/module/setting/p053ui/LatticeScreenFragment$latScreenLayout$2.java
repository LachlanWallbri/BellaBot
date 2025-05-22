package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.view.View;
import android.view.ViewStub;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LatticeScreenFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Landroidx/appcompat/widget/LinearLayoutCompat;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LatticeScreenFragment$latScreenLayout$2 extends Lambda implements Function0<LinearLayoutCompat> {
    final /* synthetic */ LatticeScreenFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LatticeScreenFragment$latScreenLayout$2(LatticeScreenFragment latticeScreenFragment) {
        super(0);
        this.this$0 = latticeScreenFragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final LinearLayoutCompat invoke() {
        String str;
        View inflate = ((ViewStub) this.this$0.getView().findViewById(C4188R.id.lat_screen_root)).inflate();
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.widget.LinearLayoutCompat");
        }
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) inflate;
        linearLayoutCompat.setVisibility(8);
        Switch lat_screen_delivery_sw = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_delivery_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_delivery_sw, "lat_screen_delivery_sw");
        lat_screen_delivery_sw.setChecked(Constans.INSTANCE.getDeliverFaceSwitch());
        ((Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_delivery_sw)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$latScreenLayout$2$$special$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = LatticeScreenFragment$latScreenLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str2, "latScreenLayout--delivery:" + z);
                Constans.INSTANCE.setDeliverFaceSwitch(z);
            }
        }, 7, null));
        Switch lat_screen_cruise_sw = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_cruise_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_cruise_sw, "lat_screen_cruise_sw");
        lat_screen_cruise_sw.setChecked(Constans.INSTANCE.isOpenCruiseFace());
        ((Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_cruise_sw)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$latScreenLayout$2$$special$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = LatticeScreenFragment$latScreenLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str2, "latScreenLayout--cruise:" + z);
                Constans.INSTANCE.setOpenCruiseFace(z);
            }
        }, 7, null));
        Switch lat_screen_welcome_sw = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_welcome_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_welcome_sw, "lat_screen_welcome_sw");
        lat_screen_welcome_sw.setChecked(Constans.INSTANCE.isWelcomeFace());
        ((Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_welcome_sw)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$latScreenLayout$2$$special$$inlined$apply$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = LatticeScreenFragment$latScreenLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str2, "latScreenLayout--welcome:" + z);
                Constans.INSTANCE.setWelcomeFace(z);
            }
        }, 7, null));
        Switch lat_screen_leading_sw = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_leading_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_leading_sw, "lat_screen_leading_sw");
        lat_screen_leading_sw.setChecked(Constans.INSTANCE.isLeadingFace());
        ((Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_leading_sw)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$latScreenLayout$2$$special$$inlined$apply$lambda$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = LatticeScreenFragment$latScreenLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str2, "latScreenLayout--leading:" + z);
                Constans.INSTANCE.setLeadingFace(z);
            }
        }, 7, null));
        Switch lat_screen_return_sw = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_return_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_return_sw, "lat_screen_return_sw");
        lat_screen_return_sw.setChecked(Constans.INSTANCE.isReturnFace());
        ((Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_return_sw)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$latScreenLayout$2$$special$$inlined$apply$lambda$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = LatticeScreenFragment$latScreenLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str2, "latScreenLayout--return:" + z);
                Constans.INSTANCE.setReturnFace(z);
            }
        }, 7, null));
        Switch lat_screen_recycle_sw = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_recycle_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_recycle_sw, "lat_screen_recycle_sw");
        lat_screen_recycle_sw.setChecked(Constans.INSTANCE.isRecyclePlateFace());
        ((Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_recycle_sw)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$latScreenLayout$2$$special$$inlined$apply$lambda$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = LatticeScreenFragment$latScreenLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str2, "latScreenLayout--return:" + z);
                Constans.INSTANCE.setRecyclePlateFace(z);
            }
        }, 7, null));
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("latScreenLayout#delivery:");
        Switch lat_screen_delivery_sw2 = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_delivery_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_delivery_sw2, "lat_screen_delivery_sw");
        sb.append(lat_screen_delivery_sw2.isChecked());
        sb.append("#cruise:");
        Switch lat_screen_cruise_sw2 = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_cruise_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_cruise_sw2, "lat_screen_cruise_sw");
        sb.append(lat_screen_cruise_sw2.isChecked());
        sb.append("#welcome:");
        Switch lat_screen_welcome_sw2 = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_welcome_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_welcome_sw2, "lat_screen_welcome_sw");
        sb.append(lat_screen_welcome_sw2.isChecked());
        sb.append("#lead:");
        Switch lat_screen_leading_sw2 = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_leading_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_leading_sw2, "lat_screen_leading_sw");
        sb.append(lat_screen_leading_sw2.isChecked());
        sb.append("#return:");
        Switch lat_screen_return_sw2 = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_return_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_return_sw2, "lat_screen_return_sw");
        sb.append(lat_screen_return_sw2.isChecked());
        sb.append("#recycle:");
        Switch lat_screen_recycle_sw2 = (Switch) this.this$0._$_findCachedViewById(C4188R.id.lat_screen_recycle_sw);
        Intrinsics.checkExpressionValueIsNotNull(lat_screen_recycle_sw2, "lat_screen_recycle_sw");
        sb.append(lat_screen_recycle_sw2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        return linearLayoutCompat;
    }
}
