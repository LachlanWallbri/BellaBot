package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.NewPasswordDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.PasswordDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ResetPasswordDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SecuritySettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0006\u0010\u001b\u001a\u00020\rR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SecuritySettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "newPasswordDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/NewPasswordDialog;", "passwordDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/PasswordDialog;", "resetPasswordDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ResetPasswordDialog;", "initData", "", "initPasswordEdit", "initProtectView", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "resetPassword", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SecuritySettingsFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    private NewPasswordDialog newPasswordDialog;
    private PasswordDialog passwordDialog;
    private ResetPasswordDialog resetPasswordDialog;

    private final void initData() {
    }

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
        return inflater.inflate(C4188R.layout.fragment_security_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    private final void initView() {
        initProtectView();
        initPasswordEdit();
    }

    private final void initProtectView() {
        ImageView protect_toggle = (ImageView) _$_findCachedViewById(C4188R.id.protect_toggle);
        Intrinsics.checkExpressionValueIsNotNull(protect_toggle, "protect_toggle");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(protect_toggle, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initProtectView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LinearLayout container_panel = (LinearLayout) SecuritySettingsFragment.this._$_findCachedViewById(C4188R.id.container_panel);
                Intrinsics.checkExpressionValueIsNotNull(container_panel, "container_panel");
                if (container_panel.getVisibility() == 0) {
                    LinearLayout container_panel2 = (LinearLayout) SecuritySettingsFragment.this._$_findCachedViewById(C4188R.id.container_panel);
                    Intrinsics.checkExpressionValueIsNotNull(container_panel2, "container_panel");
                    container_panel2.setVisibility(8);
                    ((ImageView) SecuritySettingsFragment.this._$_findCachedViewById(C4188R.id.protect_toggle)).setImageResource(C4188R.drawable.icon_settings_advanced_open);
                    return;
                }
                LinearLayout container_panel3 = (LinearLayout) SecuritySettingsFragment.this._$_findCachedViewById(C4188R.id.container_panel);
                Intrinsics.checkExpressionValueIsNotNull(container_panel3, "container_panel");
                container_panel3.setVisibility(0);
                ((ImageView) SecuritySettingsFragment.this._$_findCachedViewById(C4188R.id.protect_toggle)).setImageResource(C4188R.drawable.icon_settings_advanced_packup);
            }
        }, 3, null);
        Switch cruise_exit_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.cruise_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_exit_mode_switch, "cruise_exit_mode_switch");
        cruise_exit_mode_switch.setChecked(Constans.INSTANCE.getCruiseExitSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initCruiseExitSwitch ");
        Switch cruise_exit_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.cruise_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_exit_mode_switch2, "cruise_exit_mode_switch");
        sb.append(cruise_exit_mode_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.cruise_exit_mode_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initProtectView$2
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
                str2 = SecuritySettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "initCruiseExitSwitch " + z);
                Constans.INSTANCE.setCruiseExitSwitch(z);
            }
        }, 7, null);
        Switch deliver_exit_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.deliver_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_exit_mode_switch, "deliver_exit_mode_switch");
        deliver_exit_mode_switch.setChecked(Constans.INSTANCE.getDeliverExitSwitch());
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("initDeliverExitSwitch ");
        Switch deliver_exit_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.deliver_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_exit_mode_switch2, "deliver_exit_mode_switch");
        sb2.append(deliver_exit_mode_switch2.isChecked());
        Pdlog.m3273d(str2, sb2.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.deliver_exit_mode_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initProtectView$3
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
                String str3;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str3 = SecuritySettingsFragment.this.TAG;
                Pdlog.m3273d(str3, "initDeliverExitSwitch " + z);
                Constans.INSTANCE.setDeliverExitSwitch(z);
            }
        }, 7, null);
        Switch greeter_exit_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.greeter_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(greeter_exit_mode_switch, "greeter_exit_mode_switch");
        greeter_exit_mode_switch.setChecked(Constans.INSTANCE.getGreeterExitSwitch());
        String str3 = this.TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("initGreeterExitSwitch ");
        Switch greeter_exit_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.greeter_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(greeter_exit_mode_switch2, "greeter_exit_mode_switch");
        sb3.append(greeter_exit_mode_switch2.isChecked());
        Pdlog.m3273d(str3, sb3.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.greeter_exit_mode_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initProtectView$4
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
                String str4;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str4 = SecuritySettingsFragment.this.TAG;
                Pdlog.m3273d(str4, "initGreeterExitSwitch " + z);
                Constans.INSTANCE.setGreeterExitSwitch(z);
            }
        }, 7, null);
        Switch setting_enter_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.setting_enter_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(setting_enter_mode_switch, "setting_enter_mode_switch");
        setting_enter_mode_switch.setChecked(Constans.INSTANCE.getSettingEnterSwitch());
        String str4 = this.TAG;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("initSettingEnterSwitch ");
        Switch setting_enter_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.setting_enter_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(setting_enter_mode_switch2, "setting_enter_mode_switch");
        sb4.append(setting_enter_mode_switch2.isChecked());
        Pdlog.m3273d(str4, sb4.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.setting_enter_mode_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initProtectView$5
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
                String str5;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str5 = SecuritySettingsFragment.this.TAG;
                Pdlog.m3273d(str5, "initSettingEnterSwitch " + z);
                Constans.INSTANCE.setSettingEnterSwitch(z);
            }
        }, 7, null);
        Switch recycle_exit_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.recycle_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(recycle_exit_mode_switch, "recycle_exit_mode_switch");
        recycle_exit_mode_switch.setChecked(Constans.INSTANCE.getRecycleExitSwitch());
        String str5 = this.TAG;
        StringBuilder sb5 = new StringBuilder();
        sb5.append("initRecycleExitSwitch ");
        Switch recycle_exit_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.recycle_exit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(recycle_exit_mode_switch2, "recycle_exit_mode_switch");
        sb5.append(recycle_exit_mode_switch2.isChecked());
        Pdlog.m3273d(str5, sb5.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.recycle_exit_mode_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initProtectView$6
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
                String str6;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str6 = SecuritySettingsFragment.this.TAG;
                Pdlog.m3273d(str6, "initRecycleExitSwitch " + z);
                Constans.INSTANCE.setRecycleExitSwitch(z);
            }
        }, 7, null);
    }

    private final void initPasswordEdit() {
        Button edit_password = (Button) _$_findCachedViewById(C4188R.id.edit_password);
        Intrinsics.checkExpressionValueIsNotNull(edit_password, "edit_password");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(edit_password, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initPasswordEdit$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                PasswordDialog passwordDialog;
                PasswordDialog passwordDialog2;
                PasswordDialog passwordDialog3;
                Intrinsics.checkParameterIsNotNull(it, "it");
                passwordDialog = SecuritySettingsFragment.this.passwordDialog;
                if (passwordDialog == null) {
                    SecuritySettingsFragment securitySettingsFragment = SecuritySettingsFragment.this;
                    Context requireContext = securitySettingsFragment.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
                    securitySettingsFragment.passwordDialog = new PasswordDialog(requireContext);
                }
                passwordDialog2 = SecuritySettingsFragment.this.passwordDialog;
                if (passwordDialog2 != null) {
                    passwordDialog2.setOnDoneListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SecuritySettingsFragment$initPasswordEdit$1.1
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
                            NewPasswordDialog newPasswordDialog;
                            NewPasswordDialog newPasswordDialog2;
                            newPasswordDialog = SecuritySettingsFragment.this.newPasswordDialog;
                            if (newPasswordDialog == null) {
                                SecuritySettingsFragment securitySettingsFragment2 = SecuritySettingsFragment.this;
                                Context requireContext2 = SecuritySettingsFragment.this.requireContext();
                                Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
                                securitySettingsFragment2.newPasswordDialog = new NewPasswordDialog(requireContext2);
                            }
                            newPasswordDialog2 = SecuritySettingsFragment.this.newPasswordDialog;
                            if (newPasswordDialog2 != null) {
                                newPasswordDialog2.show();
                            }
                        }
                    });
                }
                passwordDialog3 = SecuritySettingsFragment.this.passwordDialog;
                if (passwordDialog3 != null) {
                    passwordDialog3.show();
                }
            }
        }, 3, null);
    }

    public final void resetPassword() {
        if (this.resetPasswordDialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.resetPasswordDialog = new ResetPasswordDialog(requireContext);
        }
        ResetPasswordDialog resetPasswordDialog = this.resetPasswordDialog;
        if (resetPasswordDialog != null) {
            resetPasswordDialog.show();
        }
    }
}
