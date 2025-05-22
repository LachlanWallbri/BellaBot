package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.location.view.SignalView;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: BirthdayModeMusicSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0017H\u0016R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/BirthdayModeMusicSettingActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "mode_type", "", "initListener", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BirthdayModeMusicSettingActivity extends MyBaseActivity implements BatteryContract2.ViewInterface {
    private HashMap _$_findViewCache;
    private final String TAG = getClass().getSimpleName();
    private int mode_type = 2;

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_birthday_mode_music_setting);
        Intent intent = getIntent();
        if (intent != null) {
            this.mode_type = intent.getIntExtra("MODE_TYPE", 2);
        }
        initView();
        initListener();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    private final void initListener() {
        ((LinearLayout) _$_findCachedViewById(C4188R.id.back_ll)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.BirthdayModeMusicSettingActivity$initListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                int i;
                str = BirthdayModeMusicSettingActivity.this.TAG;
                Pdlog.m3273d(str, "back_ll onSingleClick");
                Constans.INSTANCE.setFromBirthdayMusicSet(true);
                Intent intent = new Intent(BirthdayModeMusicSettingActivity.this, (Class<?>) DeliverTaskEditActivity.class);
                i = BirthdayModeMusicSettingActivity.this.mode_type;
                intent.putExtra("MODE_TYPE", i);
                BirthdayModeMusicSettingActivity.this.startActivity(intent);
                BirthdayModeMusicSettingActivity.this.finish();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.tv_edit)).setOnClickListener(new BirthdayModeMusicSettingActivity$initListener$2(this));
        ((Switch) _$_findCachedViewById(C4188R.id.on_the_way_music_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.BirthdayModeMusicSettingActivity$initListener$3
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
                String str;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str = BirthdayModeMusicSettingActivity.this.TAG;
                Pdlog.m3273d(str, "on_the_way_music_switch--birthdayMusicOnTheWay:" + z);
                Constans.INSTANCE.setBirthdayMusicOnTheWay(z);
            }
        }, 7, null));
        ((Switch) _$_findCachedViewById(C4188R.id.arrival_music_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.BirthdayModeMusicSettingActivity$initListener$4
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
                String str;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str = BirthdayModeMusicSettingActivity.this.TAG;
                Pdlog.m3273d(str, "arrival_music_switch--birthdayMusicArrival:" + z);
                Constans.INSTANCE.setBirthdayMusicArrival(z);
            }
        }, 7, null));
    }

    private final void initView() {
        showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
        if (TextUtils.isEmpty(Constans.INSTANCE.getBirthdayText())) {
            TextView tv_birthday_text = (TextView) _$_findCachedViewById(C4188R.id.tv_birthday_text);
            Intrinsics.checkExpressionValueIsNotNull(tv_birthday_text, "tv_birthday_text");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr9_2, new Object[]{"X"});
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr9_2, \"X\")");
            Object[] objArr = new Object[0];
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            tv_birthday_text.setText(format);
            ((TextView) _$_findCachedViewById(C4188R.id.tv_birthday_text)).setTextColor(getColor(C4188R.color.black));
        } else {
            TextView tv_birthday_text2 = (TextView) _$_findCachedViewById(C4188R.id.tv_birthday_text);
            Intrinsics.checkExpressionValueIsNotNull(tv_birthday_text2, "tv_birthday_text");
            tv_birthday_text2.setText(Constans.INSTANCE.getBirthdayText());
            ((TextView) _$_findCachedViewById(C4188R.id.tv_birthday_text)).setTextColor(getColor(C4188R.color.switch_text_on));
        }
        Switch on_the_way_music_switch = (Switch) _$_findCachedViewById(C4188R.id.on_the_way_music_switch);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_music_switch, "on_the_way_music_switch");
        on_the_way_music_switch.setChecked(Constans.INSTANCE.isBirthdayMusicOnTheWay());
        Switch arrival_music_switch = (Switch) _$_findCachedViewById(C4188R.id.arrival_music_switch);
        Intrinsics.checkExpressionValueIsNotNull(arrival_music_switch, "arrival_music_switch");
        arrival_music_switch.setChecked(Constans.INSTANCE.isBirthdayMusicArrival());
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.tv_birthday_text);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3275i(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
            startActivity(new Intent(this, (Class<?>) RobotChargingActivity.class));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        Pdlog.m3273d(this.TAG, "showPowerChange : i = " + i + "; ");
        ((MyStatusBarLayout) _$_findCachedViewById(C4188R.id.layout_my_status_bar)).setBattery(i);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3275i(this.TAG, "showPowerEvent " + model);
    }
}
