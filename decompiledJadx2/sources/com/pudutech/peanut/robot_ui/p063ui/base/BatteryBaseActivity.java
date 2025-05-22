package com.pudutech.peanut.robot_ui.p063ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.aidl.serialize.BatteryState;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.CheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserCheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.AppSettingActivity;
import com.pudutech.peanut.robot_ui.p063ui.DeliverTaskEditActivity;
import com.pudutech.peanut.robot_ui.p063ui.HomeActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryBaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\rH\u0002J7\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0014H&¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\bH\u0016J\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\rH\u0014J\u0010\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020\rH\u0014J\b\u0010'\u001a\u00020\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006("}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnBatteryEventListener;", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnPowerChangeListener;", "()V", "TAG", "", "isChargeActivity", "", "()Z", "setChargeActivity", "(Z)V", "bindPresenter", "", "chargingFromIntent", "i", "Landroid/content/Intent;", "jumpToCharging", "notifyBatteryInfo", "state", "", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onBatteryError", "chargeState", "onCharging", "boolean", "onChargingType", "chargingType", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$ChargingType;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPowerChanger", "power", "onResume", "unBindPresenter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class BatteryBaseActivity extends MyBaseActivity implements BatteryInfoManager.OnBatteryEventListener, BatteryInfoManager.OnPowerChangeListener {
    private final String TAG = "BatteryBaseActivity";
    private HashMap _$_findViewCache;
    private boolean isChargeActivity;

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    public void chargingFromIntent(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
    }

    public abstract void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i);

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onChargingType(BatteryInfoManager.ChargingType chargingType) {
        Intrinsics.checkParameterIsNotNull(chargingType, "chargingType");
    }

    /* renamed from: isChargeActivity, reason: from getter */
    public final boolean getIsChargeActivity() {
        return this.isChargeActivity;
    }

    public final void setChargeActivity(boolean z) {
        this.isChargeActivity = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindPresenter();
    }

    private final void bindPresenter() {
        BatteryInfoManager.INSTANCE.addBatteryEventListener(this);
        BatteryInfoManager.INSTANCE.addPowerChangeListener(this);
    }

    private final void unBindPresenter() {
        BatteryInfoManager.INSTANCE.removeBatteryEventListener(this);
        BatteryInfoManager.INSTANCE.removePowerChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BatteryState robotBattery = RobotMapManager.INSTANCE.getRobotBattery();
        Integer valueOf = robotBattery != null ? Integer.valueOf(robotBattery.getPercent()) : BatteryInfoManager.INSTANCE.getPower();
        onPowerChanger(valueOf != null ? valueOf.intValue() : SpUtils.get((Context) this, Constans.KEY_CHARGING_POWER, 0));
        onCharging(BatteryInfoManager.INSTANCE.isCharging());
        onBatteryError(BatteryInfoManager.INSTANCE.getCurrentBatteryEvent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unBindPresenter();
    }

    public static /* synthetic */ void notifyBatteryInfo$default(BatteryBaseActivity batteryBaseActivity, int i, ChargeState chargeState, boolean z, Integer num, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: notifyBatteryInfo");
        }
        if ((i2 & 2) != 0) {
            chargeState = (ChargeState) null;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        if ((i2 & 8) != 0) {
            num = 0;
        }
        batteryBaseActivity.notifyBatteryInfo(i, chargeState, z, num);
    }

    private final void jumpToCharging() {
        Pdlog.m3275i(this.TAG, "jump to Charging");
        Intent intent = new Intent(this, (Class<?>) RobotChargingActivity.class);
        chargingFromIntent(intent);
        if (this instanceof DeliverTaskEditActivity) {
            startActivity(intent);
            finish();
            return;
        }
        if ((this instanceof CheckLocationActivity) || (this instanceof LaserCheckLocationActivity)) {
            intent.putExtra("state", 1);
            jumpAndFinish(intent);
            return;
        }
        if (this instanceof AppSettingActivity) {
            if (AppSettingActivity.INSTANCE.isStop() == 0) {
                startActivity(intent);
                finish();
                return;
            }
            return;
        }
        if (this instanceof HomeActivity) {
            if (HomeActivity.INSTANCE.isStop() == 0) {
                startActivity(intent);
                finish();
                return;
            }
            return;
        }
        jumpAndFinish(intent);
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onCharging(boolean r11) {
        if (r11) {
            if (this.isChargeActivity) {
                return;
            }
            notifyBatteryInfo$default(this, 4, null, r11, null, 8, null);
            jumpToCharging();
            return;
        }
        if (this.isChargeActivity) {
            Pdlog.m3275i(this.TAG, "stop Charging");
            notifyBatteryInfo$default(this, 3, ChargeState.Idle, false, null, 12, null);
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onBatteryError(ChargeState chargeState) {
        Intrinsics.checkParameterIsNotNull(chargeState, "chargeState");
        Pdlog.m3275i(this.TAG, "chargeState：" + chargeState.toString());
        if (chargeState != ChargeState.Idle) {
            notifyBatteryInfo$default(this, 3, chargeState, false, null, 12, null);
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnPowerChangeListener
    public void onPowerChanger(int power) {
        Pdlog.m3275i(this.TAG, "power：" + power);
        boolean z = power <= 10;
        notifyBatteryInfo(2, null, false, Integer.valueOf(power));
        if (z) {
            notifyBatteryInfo(1, null, false, Integer.valueOf(power));
        }
    }
}
