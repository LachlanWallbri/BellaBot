package com.pudutech.robot.peripherals.disinfection.device;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.disinfection.DeviceName;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.SprayDeviceError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* compiled from: SprayPeripheral.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\n\u0018\u0000 42\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010$\u001a\u00020\u0016H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010%J\u001d\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050&j\b\u0012\u0004\u0012\u00020\u0005`'H\u0000¢\u0006\u0002\b(J!\u0010)\u001a\u00020\u00162\u0010\u0010*\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010+H\u0000¢\u0006\u0004\b,\u0010-J\u0015\u0010\u001b\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u000bH\u0000¢\u0006\u0002\b/J\u0011\u00100\u001a\u00020\u0016H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010%J\u0006\u00101\u001a\u00020\u0016J\b\u00102\u001a\u00020\u0016H\u0004J\b\u00103\u001a\u00020\u0016H\u0004R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eRC\u0010\u000f\u001a+\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u000bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\f\"\u0004\b\"\u0010\u000eR\u000e\u0010#\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/device/SprayPeripheral;", "Lcom/pudutech/robot/peripherals/disinfection/device/BasePeripheral;", "()V", "errorList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/robot/peripherals/disinfection/SprayDeviceError;", "getErrorList", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "handler", "Landroid/os/Handler;", "isStartCheck", "", "()Z", "setStartCheck", "(Z)V", "onCheckWaterPumpFailed", "Lkotlin/Function1;", "Lkotlin/Pair;", "", "Lkotlin/ParameterName;", "name", "error", "", "getOnCheckWaterPumpFailed$module_robot_peripherals_release", "()Lkotlin/jvm/functions/Function1;", "setOnCheckWaterPumpFailed$module_robot_peripherals_release", "(Lkotlin/jvm/functions/Function1;)V", "onSprayLiquidStatus", "getOnSprayLiquidStatus", "setOnSprayLiquidStatus", "pumpingTime", "", "spray", "getSpray$module_robot_peripherals_release", "setSpray$module_robot_peripherals_release", AUserTrack.UTKEY_START_TIME, "closeDevice", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getErrorList$module_robot_peripherals_release", "onError", "p0", "", "onError$module_robot_peripherals_release", "([Lcom/pudutech/robot/peripherals/disinfection/SprayDeviceError;)V", "boolean", "onSprayLiquidStatus$module_robot_peripherals_release", "openDevice", "resetPumpingTime", "startCheckWaterPump", "stopCheckWaterPump", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SprayPeripheral extends BasePeripheral {
    public static final String ERROR_LOWER_BOX = "lower_box_error";
    public static final String ERROR_PUMP = "pump_error";
    private static final int KEY_CHECK_TIMEOUT = 1001;
    protected static final String TAG = "SprayPeripheral_New";
    public static final long checkTime = 120000;
    private volatile boolean isStartCheck;
    private Function1<? super Pair<String, Boolean>, Unit> onCheckWaterPumpFailed;
    private boolean onSprayLiquidStatus;
    private long pumpingTime;
    private long startTime;
    private boolean spray = true;
    private final CopyOnWriteArrayList<SprayDeviceError> errorList = new CopyOnWriteArrayList<>();
    private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.robot.peripherals.disinfection.device.SprayPeripheral$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            if (1001 == message.what) {
                Iterator<T> it = SprayPeripheral.this.getErrorList().iterator();
                while (it.hasNext()) {
                    Pdlog.m3273d("SprayPeripheral_New", "peripherals check time out is error " + ((SprayDeviceError) it.next()));
                }
                Pdlog.m3274e("SprayPeripheral_New", "peripherals KEY_CHECK_TIMEOUT");
                if (SprayPeripheral.this.getErrorList().contains(SprayDeviceError.LOWER_WATER_BOX) && SprayPeripheral.this.getErrorList().contains(SprayDeviceError.LOWER_LIQUID)) {
                    Function1<Pair<String, Boolean>, Unit> onCheckWaterPumpFailed$module_robot_peripherals_release = SprayPeripheral.this.getOnCheckWaterPumpFailed$module_robot_peripherals_release();
                    if (onCheckWaterPumpFailed$module_robot_peripherals_release != null) {
                        onCheckWaterPumpFailed$module_robot_peripherals_release.invoke(new Pair<>(SprayPeripheral.ERROR_LOWER_BOX, true));
                    }
                } else {
                    Function1<Pair<String, Boolean>, Unit> onCheckWaterPumpFailed$module_robot_peripherals_release2 = SprayPeripheral.this.getOnCheckWaterPumpFailed$module_robot_peripherals_release();
                    if (onCheckWaterPumpFailed$module_robot_peripherals_release2 != null) {
                        onCheckWaterPumpFailed$module_robot_peripherals_release2.invoke(new Pair<>(SprayPeripheral.ERROR_PUMP, false));
                    }
                }
                SprayPeripheral.this.resetPumpingTime();
            }
            return true;
        }
    });

    /* renamed from: getSpray$module_robot_peripherals_release, reason: from getter */
    public final boolean getSpray() {
        return this.spray;
    }

    public final void setSpray$module_robot_peripherals_release(boolean z) {
        this.spray = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CopyOnWriteArrayList<SprayDeviceError> getErrorList() {
        return this.errorList;
    }

    protected final boolean getOnSprayLiquidStatus() {
        return this.onSprayLiquidStatus;
    }

    protected final void setOnSprayLiquidStatus(boolean z) {
        this.onSprayLiquidStatus = z;
    }

    /* renamed from: isStartCheck, reason: from getter */
    protected final boolean getIsStartCheck() {
        return this.isStartCheck;
    }

    protected final void setStartCheck(boolean z) {
        this.isStartCheck = z;
    }

    public final Function1<Pair<String, Boolean>, Unit> getOnCheckWaterPumpFailed$module_robot_peripherals_release() {
        return this.onCheckWaterPumpFailed;
    }

    public final void setOnCheckWaterPumpFailed$module_robot_peripherals_release(Function1<? super Pair<String, Boolean>, Unit> function1) {
        this.onCheckWaterPumpFailed = function1;
    }

    public final void resetPumpingTime() {
        this.pumpingTime = 0L;
        this.startTime = 0L;
    }

    public final void onSprayLiquidStatus$module_robot_peripherals_release(boolean r7) {
        this.onSprayLiquidStatus = r7;
        if (!r7) {
            Pdlog.m3273d(TAG, "peripherals current spray liquid flag is healthy? " + r7);
        }
        if (getSwitchStatus() && !r7 && !this.isStartCheck) {
            Pdlog.m3273d(TAG, "peripherals onSprayLiquidStatus : start check startCheckWaterPump");
            startCheckWaterPump();
        }
        if (getSwitchStatus() && r7 && this.isStartCheck) {
            Pdlog.m3273d(TAG, "peripherals onSprayLiquidStatus : stop check startCheckWaterPump");
            stopCheckWaterPump();
        }
        if (r7) {
            resetPumpingTime();
        }
    }

    protected final void startCheckWaterPump() {
        stopCheckWaterPump();
        this.isStartCheck = true;
        this.startTime = System.currentTimeMillis();
        long j = 120000 - this.pumpingTime;
        Pdlog.m3273d(TAG, "startCheckWaterPump: countDownTime=" + j);
        this.handler.sendEmptyMessageDelayed(1001, j);
    }

    protected final void stopCheckWaterPump() {
        this.isStartCheck = false;
        if (this.startTime != 0) {
            this.pumpingTime = (this.pumpingTime + System.currentTimeMillis()) - this.startTime;
            this.startTime = 0L;
            Pdlog.m3273d(TAG, "stopCheckWaterPump: pumpingTime=" + this.pumpingTime);
        }
        this.handler.removeMessages(1001);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.robot.peripherals.disinfection.device.BasePeripheral
    public Object openDevice(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(TAG, "peripherals openDevice");
        this.errorList.clear();
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.openSprayDevice(true, this.spray);
        }
        Pdlog.m3273d(TAG, "openDevice: switchStatus=" + getSwitchStatus() + "  isStartCheck=" + this.isStartCheck);
        if (getSwitchStatus() && !this.isStartCheck) {
            Pdlog.m3273d(TAG, "openDevice: start check startCheckWaterPump");
            startCheckWaterPump();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.robot.peripherals.disinfection.device.BasePeripheral
    public Object closeDevice(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(TAG, "peripherals closeDevice");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.closeDevice(DeviceName.SprayDevice);
        }
        Pdlog.m3273d(TAG, "peripherals closeDevice : stop check startCheckWaterPump");
        stopCheckWaterPump();
        return Unit.INSTANCE;
    }

    public final void onError$module_robot_peripherals_release(SprayDeviceError[] p0) {
        if (p0 != null) {
            if (!ArraysKt.toList(p0).isEmpty()) {
                int i = 0;
                for (Object obj : ArraysKt.toList(p0)) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SprayDeviceError sprayDeviceError = (SprayDeviceError) obj;
                    if (SprayDeviceError.LOWER_LIQUID != sprayDeviceError || SprayDeviceError.SPRAY_RESET_ERROR != sprayDeviceError) {
                        Pdlog.m3273d(TAG, "peripherals onError for SprayDevice " + i + " error info " + sprayDeviceError);
                    }
                    i = i2;
                }
            }
            this.errorList.clear();
            this.errorList.addAll(ArraysKt.toList(p0));
        }
    }

    public final ArrayList<SprayDeviceError> getErrorList$module_robot_peripherals_release() {
        ArrayList<SprayDeviceError> arrayList = new ArrayList<>();
        Pdlog.m3273d(TAG, "peripherals before filter the error " + this.errorList);
        CopyOnWriteArrayList<SprayDeviceError> copyOnWriteArrayList = this.errorList;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            SprayDeviceError sprayDeviceError = (SprayDeviceError) obj;
            if (!(sprayDeviceError == SprayDeviceError.SPRAY_RESET_ERROR || sprayDeviceError == SprayDeviceError.LOWER_LIQUID)) {
                arrayList2.add(obj);
            }
        }
        arrayList.addAll(arrayList2);
        Pdlog.m3273d(TAG, "peripherals after filter the error " + arrayList);
        return arrayList;
    }
}
