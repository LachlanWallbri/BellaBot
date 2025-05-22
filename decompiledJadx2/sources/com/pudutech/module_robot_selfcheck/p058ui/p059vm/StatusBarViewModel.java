package com.pudutech.module_robot_selfcheck.p058ui.p059vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.network.manager.NetState;
import com.pudutech.disinfect.baselib.network.manager.NetworkStateManager;
import com.pudutech.disinfect.baselib.util.NetStatusUtil;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StatusBarViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\b\u0012\u0004\u0012\u00020\u00060\u0005B\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0016H\u0014J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\nH\u0016J\u0010\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\nH\u0016R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f¨\u0006%"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/vm/StatusBarViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnPowerChangeListener;", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$SchedulerCountChangeListener;", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnBatteryEventListener;", "Landroidx/lifecycle/Observer;", "Lcom/pudutech/disinfect/baselib/network/manager/NetState;", "()V", "batteryLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getBatteryLiveData", "()Landroidx/lifecycle/MutableLiveData;", "bluetoothStatusLiveData", "", "getBluetoothStatusLiveData", "isChargingLiveData", "scheduleLiveData", "getScheduleLiveData", "wifiStatusLiveData", "getWifiStatusLiveData", "onBatteryError", "", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onChanged", "t", "onCharging", "boolean", "onChargingType", "chargingType", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$ChargingType;", "onCleared", "onCountChange", "int", "onPowerChanger", "power", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class StatusBarViewModel extends BaseViewModel implements BatteryInfoManager.OnPowerChangeListener, RobotMoveManager.SchedulerCountChangeListener, BatteryInfoManager.OnBatteryEventListener, Observer<NetState> {
    private final MutableLiveData<Integer> batteryLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> scheduleLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> wifiStatusLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> bluetoothStatusLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isChargingLiveData = new MutableLiveData<>();

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onBatteryError(ChargeState chargeState) {
        Intrinsics.checkParameterIsNotNull(chargeState, "chargeState");
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onChargingType(BatteryInfoManager.ChargingType chargingType) {
        Intrinsics.checkParameterIsNotNull(chargingType, "chargingType");
    }

    public StatusBarViewModel() {
        this.batteryLiveData.postValue(BatteryInfoManager.INSTANCE.getPower());
        this.scheduleLiveData.postValue(Integer.valueOf(RobotMoveManager.INSTANCE.getSchedulerCount()));
        this.wifiStatusLiveData.postValue(Boolean.valueOf(NetStatusUtil.isConnected(BaseApp.INSTANCE.getINSTANCE().getApplicationContext())));
        this.bluetoothStatusLiveData.postValue(false);
        this.isChargingLiveData.postValue(Boolean.valueOf(BatteryInfoManager.INSTANCE.isCharging()));
        BatteryInfoManager.INSTANCE.addPowerChangeListener(this);
        RobotMoveManager.INSTANCE.addSchedulerCountChangeListener(this);
        BatteryInfoManager.INSTANCE.addBatteryEventListener(this);
        NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().observeForever(this);
    }

    public final MutableLiveData<Integer> getBatteryLiveData() {
        return this.batteryLiveData;
    }

    public final MutableLiveData<Integer> getScheduleLiveData() {
        return this.scheduleLiveData;
    }

    public final MutableLiveData<Boolean> getWifiStatusLiveData() {
        return this.wifiStatusLiveData;
    }

    public final MutableLiveData<Boolean> getBluetoothStatusLiveData() {
        return this.bluetoothStatusLiveData;
    }

    public final MutableLiveData<Boolean> isChargingLiveData() {
        return this.isChargingLiveData;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        BatteryInfoManager.INSTANCE.removePowerChangeListener(this);
        RobotMoveManager.INSTANCE.removeSchedulerCountChangeListener(this);
        BatteryInfoManager.INSTANCE.removeBatteryEventListener(this);
        NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().removeObserver(this);
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnPowerChangeListener
    public void onPowerChanger(int power) {
        this.batteryLiveData.postValue(Integer.valueOf(power));
    }

    @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
    public void onCountChange(int r2) {
        this.scheduleLiveData.postValue(Integer.valueOf(r2));
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryEventListener
    public void onCharging(boolean r2) {
        this.isChargingLiveData.postValue(Boolean.valueOf(r2));
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(NetState t) {
        this.wifiStatusLiveData.postValue(Boolean.valueOf(NetStatusUtil.isConnected(BaseApp.INSTANCE.getINSTANCE().getApplicationContext())));
    }
}
