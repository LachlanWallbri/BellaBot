package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.presenter.BusinessContext;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingModel;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TrayAlarmSwitchTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceRadioGroupChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeliverSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t*\u0001\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0002J&\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u001a\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u000b2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u000e\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u000eH\u0002J\u0010\u0010*\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u000eH\u0002J\b\u0010,\u001a\u00020\u0010H\u0002J\b\u0010-\u001a\u00020\u0010H\u0002J\b\u0010.\u001a\u00020\u0010H\u0002J\b\u0010/\u001a\u00020\u0010H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/DeliverSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "trayEditClick", "com/pudutech/bumblebee/robot_ui/module/setting/ui/DeliverSettingFragment$trayEditClick$1", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/DeliverSettingFragment$trayEditClick$1;", "trayList", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "checkIsLastEnable", "", "initAssignTrayLightView", "", "initAutoDeliverView", "initBottomTraySwitch", "initOrderDeliveryModeSwitch", "initRepeatPreTaskSwitch", "initRoadBlockSetting", "initSingleTrayMultiTableSwitch", "initTableInputTypeView", "initTableNumber", "initTrayAlarmView", "initTrayEditLayout", "initTraySensorView", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showColumn", "column", "", "showSelectTrayAlarmDialog", "isChecked", "showSmartInputTypes", "boolean", "showTipDialog", "updateSelectInputType", "updateSelectSmartInputDefaultType", "updateTrayView", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverSettingFragment extends Fragment {
    private HashMap _$_findViewCache;
    private final String TAG = getClass().getSimpleName();
    private final ArrayList<View> trayList = new ArrayList<>();
    private final DeliverSettingFragment$trayEditClick$1 trayEditClick = new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$trayEditClick$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(null, 0, 3, null);
        }

        @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
        public void onSingleClick(View v) {
            String str;
            Object tag;
            boolean checkIsLastEnable;
            Intrinsics.checkParameterIsNotNull(v, "v");
            super.onSingleClick(v);
            try {
                tag = v.getTag();
            } catch (Exception e) {
                str = DeliverSettingFragment.this.TAG;
                Pdlog.m3274e(str, "onSingleClick : " + Log.getStackTraceString(e));
            }
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            int intValue = ((Integer) tag).intValue();
            boolean isPalletEnable = PalletCountHelper.INSTANCE.isPalletEnable(intValue);
            if (isPalletEnable) {
                checkIsLastEnable = DeliverSettingFragment.this.checkIsLastEnable();
                if (checkIsLastEnable) {
                    DeliverSettingFragment.this.showTipDialog();
                    return;
                }
            }
            PalletCountHelper.INSTANCE.setPalletEnable(intValue, !isPalletEnable);
            DeliverSettingFragment.this.updateTrayView();
            Constans.INSTANCE.clearLastTask();
        }
    };

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

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTipDialog() {
        Context context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity");
        }
        String string = getString(C4188R.string.pdStr7_158);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_158)");
        MyBaseActivity.showTipDialog$default((MyBaseActivity) context, string, null, null, getString(C4188R.string.pdStr8_15), 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkIsLastEnable() {
        int count = PalletCountHelper.INSTANCE.getCount();
        int i = 0;
        for (int i2 = 0; i2 < count; i2++) {
            if (PalletCountHelper.INSTANCE.isPalletEnable(i2)) {
                i++;
            }
        }
        return i == 1;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_pallet_sensor, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initTraySensorView();
        initTrayAlarmView();
        initAssignTrayLightView();
        initTrayEditLayout();
        initBottomTraySwitch();
        initTableNumber();
        initTableInputTypeView();
        initSingleTrayMultiTableSwitch();
        initOrderDeliveryModeSwitch();
        initRepeatPreTaskSwitch();
        initAutoDeliverView();
        initRoadBlockSetting();
    }

    private final void initBottomTraySwitch() {
        Switch tray_switch = (Switch) _$_findCachedViewById(C4188R.id.tray_switch);
        Intrinsics.checkExpressionValueIsNotNull(tray_switch, "tray_switch");
        tray_switch.setChecked(PalletCountHelper.INSTANCE.getCount() == 4);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initBottomTraySwitch ");
        Switch tray_switch2 = (Switch) _$_findCachedViewById(C4188R.id.tray_switch);
        Intrinsics.checkExpressionValueIsNotNull(tray_switch2, "tray_switch");
        sb.append(tray_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.tray_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initBottomTraySwitch$1
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
                boolean checkIsLastEnable;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "tray_switch " + z);
                if (!z) {
                    checkIsLastEnable = DeliverSettingFragment.this.checkIsLastEnable();
                    if (checkIsLastEnable && PalletCountHelper.INSTANCE.isPalletEnable(3)) {
                        PalletCountHelper.INSTANCE.setPalletEnable(2, true);
                    }
                    PalletCountHelper.INSTANCE.setCount(3);
                } else {
                    PalletCountHelper.INSTANCE.setPalletEnable(3, true);
                    PalletCountHelper.INSTANCE.setCount(4);
                }
                DeliverSettingFragment.this.updateTrayView();
                Constans.INSTANCE.clearLastTask();
            }
        }, 7, null);
        Drawable drawable = getResources().getDrawable(C4188R.drawable.img_robot_pause);
        if (Build.VERSION.SDK_INT >= 19 && drawable != null) {
            drawable.setAutoMirrored(true);
        }
        ((ImageView) _$_findCachedViewById(C4188R.id.robot_img)).setImageDrawable(drawable);
    }

    private final void initTraySensorView() {
        Switch tray_sensor_switch = (Switch) _$_findCachedViewById(C4188R.id.tray_sensor_switch);
        Intrinsics.checkExpressionValueIsNotNull(tray_sensor_switch, "tray_sensor_switch");
        tray_sensor_switch.setChecked(!PalletTask.INSTANCE.isMute());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initTraySensorView ");
        Switch tray_sensor_switch2 = (Switch) _$_findCachedViewById(C4188R.id.tray_sensor_switch);
        Intrinsics.checkExpressionValueIsNotNull(tray_sensor_switch2, "tray_sensor_switch");
        sb.append(tray_sensor_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.tray_sensor_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTraySensorView$1
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
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "tray_sensor_switch " + z);
                PalletTask.INSTANCE.setMute(z ^ true);
                LinearLayout container_pallet_error = (LinearLayout) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.container_pallet_error);
                Intrinsics.checkExpressionValueIsNotNull(container_pallet_error, "container_pallet_error");
                container_pallet_error.setVisibility(PalletTask.INSTANCE.isMute() ^ true ? 0 : 8);
                View divider_pallet_sensor = DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.divider_pallet_sensor);
                Intrinsics.checkExpressionValueIsNotNull(divider_pallet_sensor, "divider_pallet_sensor");
                divider_pallet_sensor.setVisibility(true ^ PalletTask.INSTANCE.isMute() ? 0 : 8);
                if (!z && Constans.INSTANCE.getPalletAlarmSwitch()) {
                    Constans.INSTANCE.setPalletAlarmSwitch(z);
                    Switch tray_alarm_switch = (Switch) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.tray_alarm_switch);
                    Intrinsics.checkExpressionValueIsNotNull(tray_alarm_switch, "tray_alarm_switch");
                    tray_alarm_switch.setChecked(Constans.INSTANCE.getPalletAlarmSwitch());
                }
                if (z || !Constans.INSTANCE.getTrayDestinationWarn()) {
                    return;
                }
                Constans.INSTANCE.setTrayDestinationWarn(z);
                Switch tray_destination_switch = (Switch) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.tray_destination_switch);
                Intrinsics.checkExpressionValueIsNotNull(tray_destination_switch, "tray_destination_switch");
                tray_destination_switch.setChecked(Constans.INSTANCE.getTrayDestinationWarn());
            }
        }, 7, null);
    }

    private final void initTrayAlarmView() {
        LinearLayout container_pallet_error = (LinearLayout) _$_findCachedViewById(C4188R.id.container_pallet_error);
        Intrinsics.checkExpressionValueIsNotNull(container_pallet_error, "container_pallet_error");
        container_pallet_error.setVisibility(PalletTask.INSTANCE.isMute() ^ true ? 0 : 8);
        View divider_pallet_sensor = _$_findCachedViewById(C4188R.id.divider_pallet_sensor);
        Intrinsics.checkExpressionValueIsNotNull(divider_pallet_sensor, "divider_pallet_sensor");
        divider_pallet_sensor.setVisibility(PalletTask.INSTANCE.isMute() ^ true ? 0 : 8);
        Switch tray_alarm_switch = (Switch) _$_findCachedViewById(C4188R.id.tray_alarm_switch);
        Intrinsics.checkExpressionValueIsNotNull(tray_alarm_switch, "tray_alarm_switch");
        tray_alarm_switch.setChecked(Constans.INSTANCE.getPalletAlarmSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initTrayAlarmView ");
        Switch tray_alarm_switch2 = (Switch) _$_findCachedViewById(C4188R.id.tray_alarm_switch);
        Intrinsics.checkExpressionValueIsNotNull(tray_alarm_switch2, "tray_alarm_switch");
        sb.append(tray_alarm_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        Button tray_alarm_btn = (Button) _$_findCachedViewById(C4188R.id.tray_alarm_btn);
        Intrinsics.checkExpressionValueIsNotNull(tray_alarm_btn, "tray_alarm_btn");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(tray_alarm_btn, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTrayAlarmView$1
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
                Switch tray_alarm_switch3 = (Switch) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.tray_alarm_switch);
                Intrinsics.checkExpressionValueIsNotNull(tray_alarm_switch3, "tray_alarm_switch");
                boolean isChecked = tray_alarm_switch3.isChecked();
                if (isChecked) {
                    DeliverSettingFragment deliverSettingFragment = DeliverSettingFragment.this;
                    Switch tray_alarm_switch4 = (Switch) deliverSettingFragment._$_findCachedViewById(C4188R.id.tray_alarm_switch);
                    Intrinsics.checkExpressionValueIsNotNull(tray_alarm_switch4, "tray_alarm_switch");
                    deliverSettingFragment.showSelectTrayAlarmDialog(tray_alarm_switch4.isChecked());
                    return;
                }
                Switch tray_alarm_switch5 = (Switch) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.tray_alarm_switch);
                Intrinsics.checkExpressionValueIsNotNull(tray_alarm_switch5, "tray_alarm_switch");
                tray_alarm_switch5.setChecked(!isChecked);
            }
        }, 3, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.tray_alarm_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTrayAlarmView$2
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
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "tray_alarm_switch " + z);
                Constans.INSTANCE.setPalletAlarmSwitch(z);
                if (z && PalletTask.INSTANCE.isMute()) {
                    PalletTask.INSTANCE.setMute(!z);
                    Switch tray_sensor_switch = (Switch) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.tray_sensor_switch);
                    Intrinsics.checkExpressionValueIsNotNull(tray_sensor_switch, "tray_sensor_switch");
                    tray_sensor_switch.setChecked(!PalletTask.INSTANCE.isMute());
                }
            }
        }, 7, null);
        ((EditText) _$_findCachedViewById(C4188R.id.pallet_sensor_delay_et)).setText(String.valueOf(RobotSetting.INSTANCE.getPalletCheckEmptyDelay() / 1000));
        EditText pallet_sensor_delay_et = (EditText) _$_findCachedViewById(C4188R.id.pallet_sensor_delay_et);
        Intrinsics.checkExpressionValueIsNotNull(pallet_sensor_delay_et, "pallet_sensor_delay_et");
        pallet_sensor_delay_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTrayAlarmView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "pallet_sensor_delay_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "0";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 30) {
                        parseLong = 30;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.pallet_sensor_delay_et)).setText(String.valueOf(30L));
                        EditText editText = (EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.pallet_sensor_delay_et);
                        EditText pallet_sensor_delay_et2 = (EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.pallet_sensor_delay_et);
                        Intrinsics.checkExpressionValueIsNotNull(pallet_sensor_delay_et2, "pallet_sensor_delay_et");
                        editText.setSelection(pallet_sensor_delay_et2.getText().length());
                    } else if (parseLong < 5) {
                        parseLong = 5;
                    }
                    RobotSetting.INSTANCE.setPalletCheckEmptyDelay(parseLong * 1000);
                } catch (Exception e) {
                    str3 = DeliverSettingFragment.this.TAG;
                    Pdlog.m3274e(str3, "pallet_sensor_delay_et : " + Log.getStackTraceString(e));
                }
            }
        });
        TextView pallet_sensor_delay_tv = (TextView) _$_findCachedViewById(C4188R.id.pallet_sensor_delay_tv);
        Intrinsics.checkExpressionValueIsNotNull(pallet_sensor_delay_tv, "pallet_sensor_delay_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(pallet_sensor_delay_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTrayAlarmView$4
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
                RobotSetting.INSTANCE.setPalletCheckEmptyDelay(1000 * 5);
                ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.pallet_sensor_delay_et)).setText(String.valueOf(5L));
            }
        }, 3, null);
        Switch tray_destination_switch = (Switch) _$_findCachedViewById(C4188R.id.tray_destination_switch);
        Intrinsics.checkExpressionValueIsNotNull(tray_destination_switch, "tray_destination_switch");
        tray_destination_switch.setChecked(Constans.INSTANCE.getTrayDestinationWarn());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.tray_destination_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTrayAlarmView$5
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
                String str2;
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "initTrayAlarmView: " + z);
                Constans.INSTANCE.setTrayDestinationWarn(z);
            }
        }, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSelectTrayAlarmDialog(final boolean isChecked) {
        Pdlog.m3273d(this.TAG, "showSelectTrayAlarmDialog");
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        final TrayAlarmSwitchTipDialog trayAlarmSwitchTipDialog = new TrayAlarmSwitchTipDialog(requireContext);
        trayAlarmSwitchTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$showSelectTrayAlarmDialog$1
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
                TrayAlarmSwitchTipDialog.this.dismiss();
            }
        });
        trayAlarmSwitchTipDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$showSelectTrayAlarmDialog$2
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
                Switch tray_alarm_switch = (Switch) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.tray_alarm_switch);
                Intrinsics.checkExpressionValueIsNotNull(tray_alarm_switch, "tray_alarm_switch");
                tray_alarm_switch.setChecked(!isChecked);
                trayAlarmSwitchTipDialog.dismiss();
            }
        });
        trayAlarmSwitchTipDialog.show();
    }

    private final void initTrayEditLayout() {
        int i = 0;
        Pdlog.m3273d(this.TAG, "initTrayEditLayout ");
        this.trayList.clear();
        this.trayList.add(_$_findCachedViewById(C4188R.id.tray_item_1));
        this.trayList.add(_$_findCachedViewById(C4188R.id.tray_item_2));
        this.trayList.add(_$_findCachedViewById(C4188R.id.tray_item_3));
        this.trayList.add(_$_findCachedViewById(C4188R.id.tray_item_4));
        for (Object obj : this.trayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView v = (TextView) ((View) obj).findViewById(C4188R.id.tray_edit_item_btn);
            Intrinsics.checkExpressionValueIsNotNull(v, "v");
            v.setTag(Integer.valueOf(i));
            v.setOnClickListener(this.trayEditClick);
            i = i2;
        }
        updateTrayView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTrayView() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : this.trayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            View view = (View) obj;
            if (i > PalletCountHelper.INSTANCE.getCount() - 1) {
                view.setVisibility(8);
            } else {
                view.setVisibility(0);
                ImageView imageView = (ImageView) view.findViewById(C4188R.id.tray_edit_item_img);
                TextView itemBtn = (TextView) view.findViewById(C4188R.id.tray_edit_item_btn);
                if (PalletCountHelper.INSTANCE.isPalletEnable(i)) {
                    imageView.setImageResource(C4188R.drawable.shape_edit_tray_item_bg);
                    itemBtn.setBackgroundResource(C4188R.drawable.shape_delete_tray_btn_bg);
                    Context requireContext = requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
                    itemBtn.setTextColor(requireContext.getResources().getColor(C4188R.color.setting_tray_enable_color));
                    itemBtn.setText(C4188R.string.pdStr7_93);
                    arrayList.add(view);
                } else {
                    imageView.setImageResource(C4188R.drawable.settings_tray_disable);
                    itemBtn.setBackgroundResource(C4188R.drawable.shape_add_tray_btn_bg);
                    itemBtn.setText(C4188R.string.pdStr7_155);
                    Context requireContext2 = requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
                    itemBtn.setTextColor(requireContext2.getResources().getColor(C4188R.color.white));
                }
                Context context = getContext();
                if (context != null) {
                    int dp2px = DensityUtil.dp2px(context, 120.0f);
                    Intrinsics.checkExpressionValueIsNotNull(itemBtn, "itemBtn");
                    UiUtils.adjustTvTextSize(itemBtn, dp2px, itemBtn.getText().toString());
                }
            }
            i = i2;
        }
        if (arrayList.size() == 1) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                TextView textView = (TextView) ((View) it.next()).findViewById(C4188R.id.tray_edit_item_btn);
                textView.setBackgroundResource(C4188R.drawable.shape_delete_tray_btn_disable_bg);
                Context requireContext3 = requireContext();
                Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
                textView.setTextColor(requireContext3.getResources().getColor(C4188R.color.setting_tray_disable_color));
            }
        }
    }

    private final void initTableNumber() {
        showColumn(DeliverSettingModel.INSTANCE.getTableColumn(BusinessContext.INSTANCE.getContext()));
        ((RadioGroup) _$_findCachedViewById(C4188R.id.column_group)).setOnCheckedChangeListener(new VoiceRadioGroupChangeListener(null, 0, new Function2<RadioGroup, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTableNumber$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RadioGroup radioGroup, Integer num) {
                invoke(radioGroup, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(RadioGroup group, int i) {
                String str;
                Intrinsics.checkParameterIsNotNull(group, "group");
                str = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str, "initView checkedId:" + i + ' ');
                int i2 = 4;
                if (i == C4188R.id.three_column) {
                    i2 = 3;
                } else if (i != C4188R.id.four_column && i == C4188R.id.five_column) {
                    i2 = 5;
                }
                DeliverSettingModel.INSTANCE.saveTableColumn(BusinessContext.INSTANCE.getContext(), i2);
            }
        }, 3, null));
    }

    public final void showColumn(int column) {
        Pdlog.m3273d(this.TAG, "showColumn column:" + column + ' ');
        if (column == 3) {
            ((RadioGroup) _$_findCachedViewById(C4188R.id.column_group)).check(C4188R.id.three_column);
        } else if (column == 4) {
            ((RadioGroup) _$_findCachedViewById(C4188R.id.column_group)).check(C4188R.id.four_column);
        } else {
            if (column != 5) {
                return;
            }
            ((RadioGroup) _$_findCachedViewById(C4188R.id.column_group)).check(C4188R.id.five_column);
        }
    }

    private final void initTableInputTypeView() {
        updateSelectInputType();
        updateSelectSmartInputDefaultType();
        ((ImageView) _$_findCachedViewById(C4188R.id.table_setting_toggle)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTableInputTypeView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                ConstraintLayout input_type_open_layout = (ConstraintLayout) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.input_type_open_layout);
                Intrinsics.checkExpressionValueIsNotNull(input_type_open_layout, "input_type_open_layout");
                if (input_type_open_layout.getVisibility() == 0) {
                    ConstraintLayout input_type_open_layout2 = (ConstraintLayout) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.input_type_open_layout);
                    Intrinsics.checkExpressionValueIsNotNull(input_type_open_layout2, "input_type_open_layout");
                    input_type_open_layout2.setVisibility(8);
                    ((ImageView) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.table_setting_toggle)).setImageResource(C4188R.drawable.icon_settings_advanced_open);
                    return;
                }
                ConstraintLayout input_type_open_layout3 = (ConstraintLayout) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.input_type_open_layout);
                Intrinsics.checkExpressionValueIsNotNull(input_type_open_layout3, "input_type_open_layout");
                input_type_open_layout3.setVisibility(0);
                ((ImageView) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.table_setting_toggle)).setImageResource(C4188R.drawable.icon_settings_advanced_packup);
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.smart_input_title_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTableInputTypeView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                Constans.INSTANCE.setTableInputType(1);
                DeliverSettingFragment.this.updateSelectInputType();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.select_input_title_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTableInputTypeView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                Constans.INSTANCE.setTableInputType(0);
                DeliverSettingFragment.this.updateSelectInputType();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.smart_input_digital_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTableInputTypeView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                Constans.INSTANCE.setTableInputSmartDefaultType(0);
                DeliverSettingFragment.this.updateSelectSmartInputDefaultType();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.smart_input_letter_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initTableInputTypeView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                Constans.INSTANCE.setTableInputSmartDefaultType(1);
                DeliverSettingFragment.this.updateSelectSmartInputDefaultType();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectInputType() {
        Drawable select = getResources().getDrawable(C4188R.drawable.icon_settings_selected);
        Drawable unselect = getResources().getDrawable(C4188R.drawable.icon_settings_unselect);
        Intrinsics.checkExpressionValueIsNotNull(select, "select");
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        Intrinsics.checkExpressionValueIsNotNull(unselect, "unselect");
        unselect.setBounds(0, 0, unselect.getMinimumWidth(), unselect.getMinimumHeight());
        if (Constans.INSTANCE.getTableInputType() == 0) {
            showSmartInputTypes(false);
            ((TextView) _$_findCachedViewById(C4188R.id.smart_input_title_tv)).setCompoundDrawables(unselect, null, null, null);
            ((TextView) _$_findCachedViewById(C4188R.id.select_input_title_tv)).setCompoundDrawables(select, null, null, null);
        } else {
            showSmartInputTypes(true);
            ((TextView) _$_findCachedViewById(C4188R.id.smart_input_title_tv)).setCompoundDrawables(select, null, null, null);
            ((TextView) _$_findCachedViewById(C4188R.id.select_input_title_tv)).setCompoundDrawables(unselect, null, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectSmartInputDefaultType() {
        Drawable select = getResources().getDrawable(C4188R.drawable.icon_settings_selected);
        Drawable unselect = getResources().getDrawable(C4188R.drawable.icon_settings_unselect);
        Intrinsics.checkExpressionValueIsNotNull(select, "select");
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        Intrinsics.checkExpressionValueIsNotNull(unselect, "unselect");
        unselect.setBounds(0, 0, unselect.getMinimumWidth(), unselect.getMinimumHeight());
        if (Constans.INSTANCE.getTableInputSmartDefaultType() == 0) {
            ((TextView) _$_findCachedViewById(C4188R.id.smart_input_digital_tv)).setCompoundDrawables(select, null, null, null);
            ((TextView) _$_findCachedViewById(C4188R.id.smart_input_letter_tv)).setCompoundDrawables(unselect, null, null, null);
        } else {
            ((TextView) _$_findCachedViewById(C4188R.id.smart_input_digital_tv)).setCompoundDrawables(unselect, null, null, null);
            ((TextView) _$_findCachedViewById(C4188R.id.smart_input_letter_tv)).setCompoundDrawables(select, null, null, null);
        }
    }

    private final void showSmartInputTypes(boolean r3) {
        if (r3) {
            TextView smart_input_digital_tv = (TextView) _$_findCachedViewById(C4188R.id.smart_input_digital_tv);
            Intrinsics.checkExpressionValueIsNotNull(smart_input_digital_tv, "smart_input_digital_tv");
            smart_input_digital_tv.setVisibility(0);
            TextView smart_input_letter_tv = (TextView) _$_findCachedViewById(C4188R.id.smart_input_letter_tv);
            Intrinsics.checkExpressionValueIsNotNull(smart_input_letter_tv, "smart_input_letter_tv");
            smart_input_letter_tv.setVisibility(0);
            return;
        }
        TextView smart_input_digital_tv2 = (TextView) _$_findCachedViewById(C4188R.id.smart_input_digital_tv);
        Intrinsics.checkExpressionValueIsNotNull(smart_input_digital_tv2, "smart_input_digital_tv");
        smart_input_digital_tv2.setVisibility(8);
        TextView smart_input_letter_tv2 = (TextView) _$_findCachedViewById(C4188R.id.smart_input_letter_tv);
        Intrinsics.checkExpressionValueIsNotNull(smart_input_letter_tv2, "smart_input_letter_tv");
        smart_input_letter_tv2.setVisibility(8);
    }

    private final void initSingleTrayMultiTableSwitch() {
        Switch single_tray_multi_table_switch = (Switch) _$_findCachedViewById(C4188R.id.single_tray_multi_table_switch);
        Intrinsics.checkExpressionValueIsNotNull(single_tray_multi_table_switch, "single_tray_multi_table_switch");
        single_tray_multi_table_switch.setChecked(Constans.INSTANCE.getSingleTrayMultiTableSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initSingleTrayMultiTableSwitch ");
        Switch single_tray_multi_table_switch2 = (Switch) _$_findCachedViewById(C4188R.id.single_tray_multi_table_switch);
        Intrinsics.checkExpressionValueIsNotNull(single_tray_multi_table_switch2, "single_tray_multi_table_switch");
        sb.append(single_tray_multi_table_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C4188R.id.single_tray_multi_table_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initSingleTrayMultiTableSwitch$1
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
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "single_tray_multi_table_switch " + z);
                Constans.INSTANCE.setSingleTrayMultiTableSwitch(z);
                Constans.INSTANCE.clearLastTask();
            }
        }, 7, null));
    }

    private final void initOrderDeliveryModeSwitch() {
        Switch switch_order_delivery_mode = (Switch) _$_findCachedViewById(C4188R.id.switch_order_delivery_mode);
        Intrinsics.checkExpressionValueIsNotNull(switch_order_delivery_mode, "switch_order_delivery_mode");
        switch_order_delivery_mode.setChecked(Constans.INSTANCE.getOrderDeliveryModeSwitch());
        ((Switch) _$_findCachedViewById(C4188R.id.switch_order_delivery_mode)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initOrderDeliveryModeSwitch$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                Constans.INSTANCE.setOrderDeliveryModeSwitch(z);
            }
        }, 7, null));
    }

    private final void initRepeatPreTaskSwitch() {
        Switch setting_repeat_task_switch = (Switch) _$_findCachedViewById(C4188R.id.setting_repeat_task_switch);
        Intrinsics.checkExpressionValueIsNotNull(setting_repeat_task_switch, "setting_repeat_task_switch");
        setting_repeat_task_switch.setChecked(Constans.INSTANCE.getRepeatLastTaskSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initRepeatPreTaskSwitch ");
        Switch setting_repeat_task_switch2 = (Switch) _$_findCachedViewById(C4188R.id.setting_repeat_task_switch);
        Intrinsics.checkExpressionValueIsNotNull(setting_repeat_task_switch2, "setting_repeat_task_switch");
        sb.append(setting_repeat_task_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.setting_repeat_task_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initRepeatPreTaskSwitch$1
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
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "initRepeatPreTaskSwitch " + z);
                Constans.INSTANCE.setRepeatLastTaskSwitch(z);
            }
        }, 7, null);
    }

    private final void initAutoDeliverView() {
        ((ImageView) _$_findCachedViewById(C4188R.id.deliver_setting_toggle)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$1
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
                View layout_deliver_extra_setting = DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.layout_deliver_extra_setting);
                Intrinsics.checkExpressionValueIsNotNull(layout_deliver_extra_setting, "layout_deliver_extra_setting");
                if (layout_deliver_extra_setting.getVisibility() == 0) {
                    View layout_deliver_extra_setting2 = DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.layout_deliver_extra_setting);
                    Intrinsics.checkExpressionValueIsNotNull(layout_deliver_extra_setting2, "layout_deliver_extra_setting");
                    layout_deliver_extra_setting2.setVisibility(8);
                    ((ImageView) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.deliver_setting_toggle)).setImageResource(C4188R.drawable.icon_settings_advanced_open);
                    return;
                }
                View layout_deliver_extra_setting3 = DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.layout_deliver_extra_setting);
                Intrinsics.checkExpressionValueIsNotNull(layout_deliver_extra_setting3, "layout_deliver_extra_setting");
                layout_deliver_extra_setting3.setVisibility(0);
                ((ImageView) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.deliver_setting_toggle)).setImageResource(C4188R.drawable.icon_settings_advanced_packup);
            }
        }, 3, null));
        Switch auto_delivery_switch = (Switch) _$_findCachedViewById(C4188R.id.auto_delivery_switch);
        Intrinsics.checkExpressionValueIsNotNull(auto_delivery_switch, "auto_delivery_switch");
        auto_delivery_switch.setChecked(BusinessSetting.INSTANCE.getDelayAutoFinishSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initAutoDeliverView ");
        Switch auto_delivery_switch2 = (Switch) _$_findCachedViewById(C4188R.id.auto_delivery_switch);
        Intrinsics.checkExpressionValueIsNotNull(auto_delivery_switch2, "auto_delivery_switch");
        sb.append(auto_delivery_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.auto_delivery_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$2
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
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "auto_delivery_switch " + z);
                BusinessSetting.INSTANCE.setDelayAutoFinishSwitch(z);
            }
        }, 7, null);
        TextView deliver_voice_loop_reset_tv = (TextView) _$_findCachedViewById(C4188R.id.deliver_voice_loop_reset_tv);
        Intrinsics.checkExpressionValueIsNotNull(deliver_voice_loop_reset_tv, "deliver_voice_loop_reset_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(deliver_voice_loop_reset_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$3
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
                String str2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_voice_loop_reset_tv");
                BusinessSetting.INSTANCE.setArrivalLoopVoiceDelayTime_ms(1000 * 15);
                ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.deliver_voice_loop_et)).setText(String.valueOf(15L));
            }
        }, 3, null);
        TextView escorting_finish_time_reset_tv = (TextView) _$_findCachedViewById(C4188R.id.escorting_finish_time_reset_tv);
        Intrinsics.checkExpressionValueIsNotNull(escorting_finish_time_reset_tv, "escorting_finish_time_reset_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(escorting_finish_time_reset_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$4
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
                String str2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "escorting_finish_time_reset_tv");
                BusinessSetting.INSTANCE.setAutoBackOrNextSize(1000 * 15);
                ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.escorting_finish_time_et)).setText(String.valueOf(15L));
            }
        }, 3, null);
        TextView pause_auto_active_reset_tv = (TextView) _$_findCachedViewById(C4188R.id.pause_auto_active_reset_tv);
        Intrinsics.checkExpressionValueIsNotNull(pause_auto_active_reset_tv, "pause_auto_active_reset_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(pause_auto_active_reset_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$5
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
                String str2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "pause_auto_active_reset_tv");
                BusinessSetting.INSTANCE.setNotCruisePauseKeepTime_ms(1000 * 10);
                ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.pause_auto_active_et)).setText(String.valueOf(10L));
            }
        }, 3, null);
        TextView cruise_pause_auto_active_reset_tv = (TextView) _$_findCachedViewById(C4188R.id.cruise_pause_auto_active_reset_tv);
        Intrinsics.checkExpressionValueIsNotNull(cruise_pause_auto_active_reset_tv, "cruise_pause_auto_active_reset_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(cruise_pause_auto_active_reset_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$6
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
                String str2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "cruise_pause_auto_active_reset_tv");
                BusinessSetting.INSTANCE.setCruisePauseKeepTime_ms(1000 * 20);
                ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.cruise_pause_auto_active_et)).setText(String.valueOf(20L));
            }
        }, 3, null);
        long j = 1000;
        ((EditText) _$_findCachedViewById(C4188R.id.auto_finish_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getDelayAutoFinish_ms() / j));
        ((EditText) _$_findCachedViewById(C4188R.id.escorting_finish_time_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getAutoBackOrNextSize() / j));
        ((EditText) _$_findCachedViewById(C4188R.id.deliver_voice_loop_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getArrivalLoopVoiceDelayTime_ms() / j));
        ((EditText) _$_findCachedViewById(C4188R.id.pause_auto_active_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / j));
        ((EditText) _$_findCachedViewById(C4188R.id.cruise_pause_auto_active_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getCruisePauseKeepTime_ms() / j));
        EditText auto_finish_et = (EditText) _$_findCachedViewById(C4188R.id.auto_finish_et);
        Intrinsics.checkExpressionValueIsNotNull(auto_finish_et, "auto_finish_et");
        auto_finish_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "auto_finish_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.auto_finish_et)).setText(String.valueOf(600L));
                    } else if (parseLong <= 0) {
                        parseLong = 1;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.auto_finish_et)).setText(String.valueOf(1L));
                    }
                    BusinessSetting.INSTANCE.setDelayAutoFinish_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = DeliverSettingFragment.this.TAG;
                    Pdlog.m3274e(str3, "auto_finish_et : " + Log.getStackTraceString(e));
                }
            }
        });
        EditText escorting_finish_time_et = (EditText) _$_findCachedViewById(C4188R.id.escorting_finish_time_et);
        Intrinsics.checkExpressionValueIsNotNull(escorting_finish_time_et, "escorting_finish_time_et");
        escorting_finish_time_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "escorting_finish_time_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 30) {
                        parseLong = 30;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.escorting_finish_time_et)).setText(String.valueOf(30L));
                    } else if (parseLong <= 0) {
                        parseLong = 1;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.escorting_finish_time_et)).setText(String.valueOf(1L));
                    }
                    BusinessSetting.INSTANCE.setAutoBackOrNextSize(parseLong * 1000);
                } catch (Exception e) {
                    str3 = DeliverSettingFragment.this.TAG;
                    Pdlog.m3274e(str3, "escorting_finish_time_et : " + Log.getStackTraceString(e));
                }
            }
        });
        EditText deliver_voice_loop_et = (EditText) _$_findCachedViewById(C4188R.id.deliver_voice_loop_et);
        Intrinsics.checkExpressionValueIsNotNull(deliver_voice_loop_et, "deliver_voice_loop_et");
        deliver_voice_loop_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$$inlined$doAfterTextChanged$3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_voice_loop_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 30) {
                        parseLong = 30;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.deliver_voice_loop_et)).setText(String.valueOf(30L));
                    } else if (parseLong <= 0) {
                        parseLong = 1;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.deliver_voice_loop_et)).setText(String.valueOf(1L));
                    }
                    BusinessSetting.INSTANCE.setArrivalLoopVoiceDelayTime_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = DeliverSettingFragment.this.TAG;
                    Pdlog.m3274e(str3, "deliver_voice_loop_et : " + Log.getStackTraceString(e));
                }
            }
        });
        EditText pause_auto_active_et = (EditText) _$_findCachedViewById(C4188R.id.pause_auto_active_et);
        Intrinsics.checkExpressionValueIsNotNull(pause_auto_active_et, "pause_auto_active_et");
        pause_auto_active_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$$inlined$doAfterTextChanged$4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "pause_auto_active_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "0";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.pause_auto_active_et)).setText(String.valueOf(600L));
                    } else if (parseLong < 5) {
                        parseLong = 5;
                    }
                    BusinessSetting.INSTANCE.setNotCruisePauseKeepTime_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = DeliverSettingFragment.this.TAG;
                    Pdlog.m3274e(str3, "pause_auto_active_et : " + Log.getStackTraceString(e));
                }
            }
        });
        EditText cruise_pause_auto_active_et = (EditText) _$_findCachedViewById(C4188R.id.cruise_pause_auto_active_et);
        Intrinsics.checkExpressionValueIsNotNull(cruise_pause_auto_active_et, "cruise_pause_auto_active_et");
        cruise_pause_auto_active_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAutoDeliverView$$inlined$doAfterTextChanged$5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "pause_auto_active_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "0";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.cruise_pause_auto_active_et)).setText(String.valueOf(600L));
                    } else if (parseLong < 5) {
                        parseLong = 5;
                    }
                    BusinessSetting.INSTANCE.setCruisePauseKeepTime_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = DeliverSettingFragment.this.TAG;
                    Pdlog.m3274e(str3, "cruise_pause_auto_active_et : " + Log.getStackTraceString(e));
                }
            }
        });
    }

    private final void initAssignTrayLightView() {
        Switch switchAssignPalletLight = (Switch) _$_findCachedViewById(C4188R.id.switchAssignPalletLight);
        Intrinsics.checkExpressionValueIsNotNull(switchAssignPalletLight, "switchAssignPalletLight");
        switchAssignPalletLight.setChecked(Constans.INSTANCE.getAssignPalletLightSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initAssignTrayLightView ");
        Switch switchAssignPalletLight2 = (Switch) _$_findCachedViewById(C4188R.id.switchAssignPalletLight);
        Intrinsics.checkExpressionValueIsNotNull(switchAssignPalletLight2, "switchAssignPalletLight");
        sb.append(switchAssignPalletLight2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.switchAssignPalletLight), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initAssignTrayLightView$1
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
                String str2;
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "assign tray light switch " + z);
                Constans.INSTANCE.setAssignPalletLightSwitch(z);
            }
        }, 3, null);
    }

    private final void initRoadBlockSetting() {
        Switch road_block_sw = (Switch) _$_findCachedViewById(C4188R.id.road_block_sw);
        Intrinsics.checkExpressionValueIsNotNull(road_block_sw, "road_block_sw");
        road_block_sw.setChecked(RobotSetting.INSTANCE.getEnableBlockReplan());
        Group replan_group = (Group) _$_findCachedViewById(C4188R.id.replan_group);
        Intrinsics.checkExpressionValueIsNotNull(replan_group, "replan_group");
        Switch road_block_sw2 = (Switch) _$_findCachedViewById(C4188R.id.road_block_sw);
        Intrinsics.checkExpressionValueIsNotNull(road_block_sw2, "road_block_sw");
        replan_group.setVisibility(road_block_sw2.isChecked() ? 0 : 8);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("init replan_switch ");
        Switch road_block_sw3 = (Switch) _$_findCachedViewById(C4188R.id.road_block_sw);
        Intrinsics.checkExpressionValueIsNotNull(road_block_sw3, "road_block_sw");
        sb.append(road_block_sw3.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C4188R.id.road_block_sw)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initRoadBlockSetting$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = DeliverSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "replan_switch " + z);
                Group replan_group2 = (Group) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.replan_group);
                Intrinsics.checkExpressionValueIsNotNull(replan_group2, "replan_group");
                replan_group2.setVisibility(z ? 0 : 8);
                RobotSetting.INSTANCE.setEnableBlockReplan(z);
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
            }
        });
        ((EditText) _$_findCachedViewById(C4188R.id.replan_wait_time_et)).setText(String.valueOf(RobotSetting.INSTANCE.getRePlanWaitTime()));
        EditText replan_wait_time_et = (EditText) _$_findCachedViewById(C4188R.id.replan_wait_time_et);
        Intrinsics.checkExpressionValueIsNotNull(replan_wait_time_et, "replan_wait_time_et");
        replan_wait_time_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initRoadBlockSetting$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String valueOf = String.valueOf(s);
                if (valueOf == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                Integer intOrNull = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) valueOf).toString());
                int intValue = intOrNull != null ? intOrNull.intValue() : 0;
                if (intValue > 600) {
                    ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.replan_wait_time_et)).setText("600");
                    EditText editText = (EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.replan_wait_time_et);
                    EditText replan_wait_time_et2 = (EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.replan_wait_time_et);
                    Intrinsics.checkExpressionValueIsNotNull(replan_wait_time_et2, "replan_wait_time_et");
                    editText.setSelection(replan_wait_time_et2.getText().length());
                    return;
                }
                if (intValue < 10) {
                    intValue = 10;
                }
                RobotSetting.INSTANCE.setRePlanWaitTime(intValue);
            }
        });
        ((EditText) _$_findCachedViewById(C4188R.id.road_lock_time_et)).setText(String.valueOf(RobotSetting.INSTANCE.getPathLockedTime()));
        EditText road_lock_time_et = (EditText) _$_findCachedViewById(C4188R.id.road_lock_time_et);
        Intrinsics.checkExpressionValueIsNotNull(road_lock_time_et, "road_lock_time_et");
        road_lock_time_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DeliverSettingFragment$initRoadBlockSetting$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String valueOf = String.valueOf(s);
                if (valueOf == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                Integer intOrNull = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) valueOf).toString());
                int intValue = intOrNull != null ? intOrNull.intValue() : 0;
                if (intValue > 600) {
                    ((EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.road_lock_time_et)).setText("600");
                    EditText editText = (EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.road_lock_time_et);
                    EditText road_lock_time_et2 = (EditText) DeliverSettingFragment.this._$_findCachedViewById(C4188R.id.road_lock_time_et);
                    Intrinsics.checkExpressionValueIsNotNull(road_lock_time_et2, "road_lock_time_et");
                    editText.setSelection(road_lock_time_et2.getText().length());
                    return;
                }
                if (intValue < 30) {
                    intValue = 30;
                }
                RobotSetting.INSTANCE.setPathLockedTime(intValue);
            }
        });
    }
}
