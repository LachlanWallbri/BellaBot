package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J&\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/LedSettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "initData", "", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LedSettingsFragment extends Fragment {
    private final String TAG = "LedSettingsFragment";
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_led_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    private final void initView() {
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.delivery_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView delivery_switch = " + z);
                Constans.INSTANCE.setDeliverLedSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.cruise_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView cruise_switch = " + z);
                Constans.INSTANCE.setCruiseLedSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.direct_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView direct_switch = " + z);
                Constans.INSTANCE.setDirectDeliverLedSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.greeter_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView greeter_switch = " + z);
                Constans.INSTANCE.setGreeterLedSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.special_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView special_switch = " + z);
                Constans.INSTANCE.setSpecialLedSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.birthday_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView birthday_switch = " + z);
                Constans.INSTANCE.setBirthdayLedSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.recycle_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView recycle_switch = " + z);
                Constans.INSTANCE.setRecyclePlateLedSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.voice_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView: voice_switch = " + z);
                Constans.INSTANCE.setVoiceSwitch(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.touch_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LedSettingsFragment$initView$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z) {
                String str;
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                str = LedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initView: touch_switch = " + z);
                Constans.INSTANCE.setTouchSwitch(z);
            }
        }, 7, null);
    }

    private final void initData() {
        Switch delivery_switch = (Switch) _$_findCachedViewById(C4188R.id.delivery_switch);
        Intrinsics.checkExpressionValueIsNotNull(delivery_switch, "delivery_switch");
        delivery_switch.setChecked(Constans.INSTANCE.isDeliverLedSwitch());
        Switch cruise_switch = (Switch) _$_findCachedViewById(C4188R.id.cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_switch, "cruise_switch");
        cruise_switch.setChecked(Constans.INSTANCE.isCruiseLedSwitch());
        Switch direct_switch = (Switch) _$_findCachedViewById(C4188R.id.direct_switch);
        Intrinsics.checkExpressionValueIsNotNull(direct_switch, "direct_switch");
        direct_switch.setChecked(Constans.INSTANCE.isDirectDeliverLedSwitch());
        Switch greeter_switch = (Switch) _$_findCachedViewById(C4188R.id.greeter_switch);
        Intrinsics.checkExpressionValueIsNotNull(greeter_switch, "greeter_switch");
        greeter_switch.setChecked(Constans.INSTANCE.isGreeterLedSwitch());
        Switch special_switch = (Switch) _$_findCachedViewById(C4188R.id.special_switch);
        Intrinsics.checkExpressionValueIsNotNull(special_switch, "special_switch");
        special_switch.setChecked(Constans.INSTANCE.isSpecialLedSwitch());
        Switch birthday_switch = (Switch) _$_findCachedViewById(C4188R.id.birthday_switch);
        Intrinsics.checkExpressionValueIsNotNull(birthday_switch, "birthday_switch");
        birthday_switch.setChecked(Constans.INSTANCE.isBirthdayLedSwitch());
        Switch voice_switch = (Switch) _$_findCachedViewById(C4188R.id.voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(voice_switch, "voice_switch");
        voice_switch.setChecked(Constans.INSTANCE.getVoiceSwitch());
        Switch touch_switch = (Switch) _$_findCachedViewById(C4188R.id.touch_switch);
        Intrinsics.checkExpressionValueIsNotNull(touch_switch, "touch_switch");
        touch_switch.setChecked(Constans.INSTANCE.getTouchSwitch());
    }
}
