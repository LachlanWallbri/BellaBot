package com.pudutech.module_project_common.statusBar;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.base.ScreenLedManager;
import com.pudutech.disinfect.baselib.base.activity.BaseVmActivity;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.mirsdk.aidl.serialize.BatteryState;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.module_project_common.statusBar.bt.BtInterface;
import com.pudutech.module_project_common.statusBar.bt.BtUtil;
import com.pudutech.peanut.robot_ui.config.Constans;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StatusBaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u00042\u00020\u00052\u00020\u0006B\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\"\u001a\u00020\u001bH\u0002J\b\u0010#\u001a\u00020\u001bH\u0002J\u0012\u0010$\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010'\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010(\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010)\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010*\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010+\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010,\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020&H\u0016J\b\u0010-\u001a\u00020\u0018H&J\b\u0010.\u001a\u00020\u001bH\u0016J\b\u0010/\u001a\u00020\u001bH\u0016J\u0010\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u0016H\u0016J\u0010\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u00020\u0018H\u0016J\u0010\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u000206H\u0016J\u0012\u00107\u001a\u00020\u001b2\b\u00108\u001a\u0004\u0018\u000109H\u0014J\b\u0010:\u001a\u00020\u001bH\u0014J\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u0012H\u0016J\b\u0010=\u001a\u00020\u001bH\u0014J\b\u0010>\u001a\u00020\u001bH\u0014J\b\u0010?\u001a\u00020\u001bH\u0014J\b\u0010@\u001a\u00020\u001bH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fRz\u0010\u0010\u001ab\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, m3961d2 = {"Lcom/pudutech/module_project_common/statusBar/StatusBaseActivity;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Lcom/pudutech/disinfect/baselib/base/activity/BaseVmActivity;", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnBatteryEventListener;", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnPowerChangeListener;", "Lcom/pudutech/module_project_common/statusBar/bt/BtInterface;", "()V", "TAG", "", "mBtReceiver", "Landroid/content/BroadcastReceiver;", "getMBtReceiver", "()Landroid/content/BroadcastReceiver;", "setMBtReceiver", "(Landroid/content/BroadcastReceiver;)V", "notifyBatteryInfo", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "state", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "model", "", "isCharging", "i", "", "getNotifyBatteryInfo", "()Lkotlin/jvm/functions/Function4;", "setNotifyBatteryInfo", "(Lkotlin/jvm/functions/Function4;)V", "robotStatusBarLayout", "Lcom/pudutech/module_project_common/statusBar/RobotStatusBarLayout;", "addViewToContent", "bindPresenter", "btBondStatusChange", "intent", "Landroid/content/Intent;", "btFinishDiscovery", "btFoundDevice", "btPairingRequest", "btStartDiscovery", "btStatusChanged", "chargingFromIntent", "currentActivityIsDark", "finish", "jumpChargeActivity", "onBatteryError", "chargeState", "onCharging", "boolean", "onChargingType", "chargingType", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$ChargingType;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPowerChanger", "power", "onResume", "onStart", "onStop", "unBindPresenter", "module_project_common_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class StatusBaseActivity<T extends BaseViewModel> extends BaseVmActivity<T> implements BatteryInfoManager.OnBatteryEventListener, BatteryInfoManager.OnPowerChangeListener, BtInterface {
    private final String TAG = "StatusBaseActivity";
    private BroadcastReceiver mBtReceiver = new BroadcastReceiver() { // from class: com.pudutech.module_project_common.statusBar.StatusBaseActivity$mBtReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_STARTED", action)) {
                StatusBaseActivity.this.btStartDiscovery(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_FINISHED", action)) {
                StatusBaseActivity.this.btFinishDiscovery(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.STATE_CHANGED", action)) {
                StatusBaseActivity.this.btStatusChanged(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.device.action.FOUND", action)) {
                StatusBaseActivity.this.btFoundDevice(intent);
            } else if (Intrinsics.areEqual("android.bluetooth.device.action.BOND_STATE_CHANGED", action)) {
                StatusBaseActivity.this.btBondStatusChange(intent);
            } else if (Intrinsics.areEqual("android.bluetooth.device.action.PAIRING_REQUEST", action)) {
                StatusBaseActivity.this.btPairingRequest(intent);
            }
        }
    };
    private Function4<? super Integer, ? super ChargeState, ? super Boolean, ? super Integer, Unit> notifyBatteryInfo;
    private RobotStatusBarLayout robotStatusBarLayout;

    @Override // com.pudutech.module_project_common.statusBar.bt.BtInterface
    public void btBondStatusChange(Intent intent) {
    }

    @Override // com.pudutech.module_project_common.statusBar.bt.BtInterface
    public void btFinishDiscovery(Intent intent) {
    }

    @Override // com.pudutech.module_project_common.statusBar.bt.BtInterface
    public void btPairingRequest(Intent intent) {
    }

    @Override // com.pudutech.module_project_common.statusBar.bt.BtInterface
    public void btStartDiscovery(Intent intent) {
    }

    public void chargingFromIntent(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
    }

    public abstract boolean currentActivityIsDark();

    public void jumpChargeActivity() {
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onChargingType(BatteryInfoManager.ChargingType chargingType) {
        Intrinsics.checkParameterIsNotNull(chargingType, "chargingType");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindPresenter();
        addViewToContent();
        NavigationBarUtil.hideNavigationBar(getWindow());
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
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BatteryState robotBattery = RobotMapManager.INSTANCE.getRobotBattery();
        Integer valueOf = robotBattery != null ? Integer.valueOf(robotBattery.getPercent()) : BatteryInfoManager.INSTANCE.getPower();
        onPowerChanger(valueOf != null ? valueOf.intValue() : SpUtils.get((Context) this, Constans.KEY_CHARGING_POWER, 0));
        onCharging(BatteryInfoManager.INSTANCE.isCharging());
        onBatteryError(BatteryInfoManager.INSTANCE.getCurrentBatteryEvent());
    }

    private final void addViewToContent() {
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        window.getDecorView().post(new Runnable() { // from class: com.pudutech.module_project_common.statusBar.StatusBaseActivity$addViewToContent$1
            @Override // java.lang.Runnable
            public final void run() {
                RobotStatusBarLayout robotStatusBarLayout;
                RobotStatusBarLayout robotStatusBarLayout2;
                RobotStatusBarLayout robotStatusBarLayout3;
                RobotStatusBarLayout robotStatusBarLayout4;
                View findViewById = StatusBaseActivity.this.findViewById(R.id.content);
                Intrinsics.checkExpressionValueIsNotNull(findViewById, "this.findViewById(Window.ID_ANDROID_CONTENT)");
                FrameLayout frameLayout = (FrameLayout) findViewById;
                StatusBaseActivity statusBaseActivity = StatusBaseActivity.this;
                statusBaseActivity.robotStatusBarLayout = new RobotStatusBarLayout(statusBaseActivity);
                robotStatusBarLayout = StatusBaseActivity.this.robotStatusBarLayout;
                if (robotStatusBarLayout != null) {
                    robotStatusBarLayout.startChangeListener(StatusBaseActivity.this);
                }
                robotStatusBarLayout2 = StatusBaseActivity.this.robotStatusBarLayout;
                if (robotStatusBarLayout2 != null) {
                    robotStatusBarLayout2.setWhiteBg(StatusBaseActivity.this.currentActivityIsDark());
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 5;
                robotStatusBarLayout3 = StatusBaseActivity.this.robotStatusBarLayout;
                if (robotStatusBarLayout3 != null) {
                    robotStatusBarLayout3.setLayoutParams(layoutParams);
                }
                robotStatusBarLayout4 = StatusBaseActivity.this.robotStatusBarLayout;
                frameLayout.addView(robotStatusBarLayout4);
            }
        });
    }

    @Override // android.app.Activity
    public void finish() {
        overridePendingTransition(0, 0);
        super.finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unBindPresenter();
        RobotStatusBarLayout robotStatusBarLayout = this.robotStatusBarLayout;
        if (robotStatusBarLayout != null) {
            robotStatusBarLayout.stopChangeListener();
        }
    }

    public Function4<Integer, ChargeState, Boolean, Integer, Unit> getNotifyBatteryInfo() {
        return this.notifyBatteryInfo;
    }

    public void setNotifyBatteryInfo(Function4<? super Integer, ? super ChargeState, ? super Boolean, ? super Integer, Unit> function4) {
        this.notifyBatteryInfo = function4;
    }

    public final BroadcastReceiver getMBtReceiver() {
        return this.mBtReceiver;
    }

    public final void setMBtReceiver(BroadcastReceiver broadcastReceiver) {
        Intrinsics.checkParameterIsNotNull(broadcastReceiver, "<set-?>");
        this.mBtReceiver = broadcastReceiver;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        BtUtil.registerBluetoothReceiver(this.mBtReceiver, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        BtUtil.unregisterBluetoothReceiver(this.mBtReceiver, this);
    }

    @Override // com.pudutech.module_project_common.statusBar.bt.BtInterface
    public void btStatusChanged(Intent intent) {
        Pdlog.m3274e(this.TAG, intent);
    }

    @Override // com.pudutech.module_project_common.statusBar.bt.BtInterface
    public void btFoundDevice(Intent intent) {
        RobotStatusBarLayout robotStatusBarLayout = this.robotStatusBarLayout;
        if (robotStatusBarLayout != null) {
            robotStatusBarLayout.setBlueToothConnect(true);
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onCharging(boolean r6) {
        if (r6) {
            ScreenLedManager.INSTANCE.stopScreen();
            Function4<Integer, ChargeState, Boolean, Integer, Unit> notifyBatteryInfo = getNotifyBatteryInfo();
            if (notifyBatteryInfo != null) {
                notifyBatteryInfo.invoke(4, null, Boolean.valueOf(r6), null);
            }
            jumpChargeActivity();
            return;
        }
        ScreenLedManager.INSTANCE.startScreen();
        Pdlog.m3275i(this.TAG, "stop Charging");
        Function4<Integer, ChargeState, Boolean, Integer, Unit> notifyBatteryInfo2 = getNotifyBatteryInfo();
        if (notifyBatteryInfo2 != null) {
            notifyBatteryInfo2.invoke(3, ChargeState.Idle, Boolean.valueOf(r6), null);
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onBatteryError(ChargeState chargeState) {
        Function4<Integer, ChargeState, Boolean, Integer, Unit> notifyBatteryInfo;
        Intrinsics.checkParameterIsNotNull(chargeState, "chargeState");
        Pdlog.m3275i(this.TAG, "chargeState：" + chargeState.toString());
        if (chargeState == ChargeState.Idle || (notifyBatteryInfo = getNotifyBatteryInfo()) == null) {
            return;
        }
        notifyBatteryInfo.invoke(3, chargeState, false, null);
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnPowerChangeListener
    public void onPowerChanger(int power) {
        Pdlog.m3275i(this.TAG, "power：" + power);
        boolean z = power <= 10;
        RobotStatusBarLayout robotStatusBarLayout = this.robotStatusBarLayout;
        if (robotStatusBarLayout != null) {
            robotStatusBarLayout.setBattery(power);
        }
        if (z) {
            Function4<Integer, ChargeState, Boolean, Integer, Unit> notifyBatteryInfo = getNotifyBatteryInfo();
            if (notifyBatteryInfo != null) {
                notifyBatteryInfo.invoke(1, null, false, Integer.valueOf(power));
                return;
            }
            return;
        }
        Function4<Integer, ChargeState, Boolean, Integer, Unit> notifyBatteryInfo2 = getNotifyBatteryInfo();
        if (notifyBatteryInfo2 != null) {
            notifyBatteryInfo2.invoke(2, null, false, Integer.valueOf(power));
        }
    }
}
