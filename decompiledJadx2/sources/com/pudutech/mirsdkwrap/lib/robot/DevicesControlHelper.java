package com.pudutech.mirsdkwrap.lib.robot;

import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import com.pudutech.mirsdk.aidl.IDeviceListener;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import com.pudutech.mirsdkwrap.lib.robot.device.DisinfectionPowerDeviceControl;
import com.pudutech.mirsdkwrap.lib.robot.device.IrledDeviceControl;
import com.pudutech.mirsdkwrap.lib.robot.device.LidarDeviceControl;
import com.pudutech.mirsdkwrap.lib.robot.device.MarkerCameraDeviceControl;
import com.pudutech.mirsdkwrap.lib.robot.device.RgbdDeviceControl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DevicesControlHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0011\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0002PQB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JG\u0010/\u001a\u00020\f2?\u00100\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\f\u0018\u00010\bJ\u000e\u00104\u001a\u00020\f2\u0006\u00100\u001a\u00020&J\u000e\u00105\u001a\u00020\n2\u0006\u00106\u001a\u000207J\u001e\u00108\u001a\u00020\f2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u00020709j\b\u0012\u0004\u0012\u000207`:J\u000e\u0010;\u001a\u00020\f2\u0006\u0010<\u001a\u00020\nJ\u0010\u0010=\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u0010\u0010?\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u0010\u0010@\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u0010\u0010A\u001a\u00020\f2\u0006\u0010B\u001a\u00020\nH\u0016J\u0010\u0010C\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u0010\u0010D\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u0010\u0010E\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u0010\u0010F\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u0010\u0010G\u001a\u00020\f2\u0006\u0010>\u001a\u00020\nH\u0016J\u001f\u0010H\u001a\u00020\f2\u0012\u00106\u001a\n\u0012\u0006\b\u0001\u0012\u0002070I\"\u000207¢\u0006\u0002\u0010JJ`\u0010H\u001a\u00020\f2\u0012\u00106\u001a\n\u0012\u0006\b\u0001\u0012\u0002070I\"\u0002072?\u0010\u0007\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\f\u0018\u00010\b¢\u0006\u0002\u0010KJ\u001e\u0010H\u001a\u00020\f2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u00020709j\b\u0012\u0004\u0012\u000207`:J\u0006\u0010L\u001a\u00020\fJ\u000e\u0010M\u001a\u00020\f2\u0006\u00100\u001a\u00020&J\u0019\u0010N\u001a\u00020\n2\u0006\u00106\u001a\u000207H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010OR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R8\u0010\u0007\u001a,\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n`\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001dX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R!\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b'\u0010(R\u0014\u0010+\u001a\u00020,X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006R"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "Lcom/pudutech/mirsdk/aidl/IDeviceListener$Stub;", "deviceInterface", "Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "(Lcom/pudutech/mirsdk/aidl/DeviceInterface;)V", "TAG", "", "cb", "Lkotlin/Function1;", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "", "getDeviceInterface$module_robot_mirsdk_wrapper_release", "()Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "deviceOpenStatusCollection", "disinfectionPowerDeviceControl", "Lcom/pudutech/mirsdkwrap/lib/robot/device/DisinfectionPowerDeviceControl;", "getDisinfectionPowerDeviceControl$module_robot_mirsdk_wrapper_release", "()Lcom/pudutech/mirsdkwrap/lib/robot/device/DisinfectionPowerDeviceControl;", "<set-?>", "emergencyKeyPressed", "getEmergencyKeyPressed", "()Z", "irledDeviceControl", "Lcom/pudutech/mirsdkwrap/lib/robot/device/IrledDeviceControl;", "getIrledDeviceControl$module_robot_mirsdk_wrapper_release", "()Lcom/pudutech/mirsdkwrap/lib/robot/device/IrledDeviceControl;", "lidarDeviceControl", "Lcom/pudutech/mirsdkwrap/lib/robot/device/LidarDeviceControl;", "getLidarDeviceControl$module_robot_mirsdk_wrapper_release", "()Lcom/pudutech/mirsdkwrap/lib/robot/device/LidarDeviceControl;", "markerCameraDeviceControl", "Lcom/pudutech/mirsdkwrap/lib/robot/device/MarkerCameraDeviceControl;", "getMarkerCameraDeviceControl$module_robot_mirsdk_wrapper_release", "()Lcom/pudutech/mirsdkwrap/lib/robot/device/MarkerCameraDeviceControl;", "onEmergencyKeyPressedListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$OnEmergencyKeyPressedListener;", "getOnEmergencyKeyPressedListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "onEmergencyKeyPressedListeners$delegate", "Lkotlin/Lazy;", "rgbdDeviceControl", "Lcom/pudutech/mirsdkwrap/lib/robot/device/RgbdDeviceControl;", "getRgbdDeviceControl$module_robot_mirsdk_wrapper_release", "()Lcom/pudutech/mirsdkwrap/lib/robot/device/RgbdDeviceControl;", "addDeviceOpenStatusListener", "l", "Lkotlin/ParameterName;", "name", "openStatus", "addOnEmergencyKeyPressedListener", "checkDeviceOpenState", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$DeviceName;", "closeDevice", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "lockMotor", "b", "onBumperSwitchStatus", "p0", "onCollision", "onDisinfectionPower", "onEmergencyKeyPressed", "isPressed", "onIRLED", "onLidar", "onLockMotor", "onMarkerCamera", "onRGBD", "openDevice", "", "([Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$DeviceName;)V", "([Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$DeviceName;Lkotlin/jvm/functions/Function1;)V", "removeDeviceOpenStatusListener", "removeOnEmergencyKeyPressedListener", "restartDevice", "(Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$DeviceName;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", DataRecordKey.MODEL, "OnEmergencyKeyPressedListener", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DevicesControlHelper extends IDeviceListener.Stub {
    private final String TAG;
    private Function1<? super HashMap<String, Boolean>, Unit> cb;
    private final DeviceInterface deviceInterface;
    private final HashMap<String, Boolean> deviceOpenStatusCollection;
    private final DisinfectionPowerDeviceControl disinfectionPowerDeviceControl;
    private volatile boolean emergencyKeyPressed;
    private final IrledDeviceControl irledDeviceControl;
    private final LidarDeviceControl lidarDeviceControl;
    private final MarkerCameraDeviceControl markerCameraDeviceControl;

    /* renamed from: onEmergencyKeyPressedListeners$delegate, reason: from kotlin metadata */
    private final Lazy onEmergencyKeyPressedListeners;
    private final RgbdDeviceControl rgbdDeviceControl;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: DevicesControlHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$DeviceName;", "", "(Ljava/lang/String;I)V", "IRLED", "LIDAR", "MARKER_CAMERA", "RGBD", "Disinfection", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum DeviceName {
        IRLED,
        LIDAR,
        MARKER_CAMERA,
        RGBD,
        Disinfection
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: DevicesControlHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper$OnEmergencyKeyPressedListener;", "", "onEmergencyKeyPressed", "", "pressed", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnEmergencyKeyPressedListener {
        void onEmergencyKeyPressed(boolean pressed);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DeviceName.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;

        static {
            $EnumSwitchMapping$0[DeviceName.IRLED.ordinal()] = 1;
            $EnumSwitchMapping$0[DeviceName.LIDAR.ordinal()] = 2;
            $EnumSwitchMapping$0[DeviceName.MARKER_CAMERA.ordinal()] = 3;
            $EnumSwitchMapping$0[DeviceName.RGBD.ordinal()] = 4;
            $EnumSwitchMapping$0[DeviceName.Disinfection.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[DeviceName.values().length];
            $EnumSwitchMapping$1[DeviceName.IRLED.ordinal()] = 1;
            $EnumSwitchMapping$1[DeviceName.LIDAR.ordinal()] = 2;
            $EnumSwitchMapping$1[DeviceName.MARKER_CAMERA.ordinal()] = 3;
            $EnumSwitchMapping$1[DeviceName.RGBD.ordinal()] = 4;
            $EnumSwitchMapping$1[DeviceName.Disinfection.ordinal()] = 5;
            $EnumSwitchMapping$2 = new int[DeviceName.values().length];
            $EnumSwitchMapping$2[DeviceName.IRLED.ordinal()] = 1;
            $EnumSwitchMapping$2[DeviceName.LIDAR.ordinal()] = 2;
            $EnumSwitchMapping$2[DeviceName.MARKER_CAMERA.ordinal()] = 3;
            $EnumSwitchMapping$2[DeviceName.RGBD.ordinal()] = 4;
            $EnumSwitchMapping$2[DeviceName.Disinfection.ordinal()] = 5;
            $EnumSwitchMapping$3 = new int[DeviceName.values().length];
            $EnumSwitchMapping$3[DeviceName.IRLED.ordinal()] = 1;
            $EnumSwitchMapping$3[DeviceName.LIDAR.ordinal()] = 2;
            $EnumSwitchMapping$3[DeviceName.MARKER_CAMERA.ordinal()] = 3;
            $EnumSwitchMapping$3[DeviceName.RGBD.ordinal()] = 4;
            $EnumSwitchMapping$3[DeviceName.Disinfection.ordinal()] = 5;
            $EnumSwitchMapping$4 = new int[DeviceName.values().length];
            $EnumSwitchMapping$4[DeviceName.IRLED.ordinal()] = 1;
            $EnumSwitchMapping$4[DeviceName.LIDAR.ordinal()] = 2;
            $EnumSwitchMapping$4[DeviceName.RGBD.ordinal()] = 3;
            $EnumSwitchMapping$4[DeviceName.MARKER_CAMERA.ordinal()] = 4;
            $EnumSwitchMapping$4[DeviceName.Disinfection.ordinal()] = 5;
            $EnumSwitchMapping$5 = new int[DeviceName.values().length];
            $EnumSwitchMapping$5[DeviceName.IRLED.ordinal()] = 1;
            $EnumSwitchMapping$5[DeviceName.LIDAR.ordinal()] = 2;
            $EnumSwitchMapping$5[DeviceName.RGBD.ordinal()] = 3;
            $EnumSwitchMapping$5[DeviceName.MARKER_CAMERA.ordinal()] = 4;
            $EnumSwitchMapping$5[DeviceName.Disinfection.ordinal()] = 5;
        }
    }

    private final ListenerList<OnEmergencyKeyPressedListener> getOnEmergencyKeyPressedListeners() {
        return (ListenerList) this.onEmergencyKeyPressedListeners.getValue();
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onCollision(boolean p0) {
    }

    public DevicesControlHelper(DeviceInterface deviceInterface) {
        Intrinsics.checkParameterIsNotNull(deviceInterface, "deviceInterface");
        this.deviceInterface = deviceInterface;
        this.TAG = "DeviceControlHelper";
        this.disinfectionPowerDeviceControl = new DisinfectionPowerDeviceControl(this.deviceInterface);
        this.irledDeviceControl = new IrledDeviceControl(this.deviceInterface);
        this.lidarDeviceControl = new LidarDeviceControl(this.deviceInterface);
        this.markerCameraDeviceControl = new MarkerCameraDeviceControl(this.deviceInterface);
        this.rgbdDeviceControl = new RgbdDeviceControl(this.deviceInterface);
        this.onEmergencyKeyPressedListeners = LazyKt.lazy(new Function0<ListenerList<OnEmergencyKeyPressedListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper$onEmergencyKeyPressedListeners$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ListenerList<DevicesControlHelper.OnEmergencyKeyPressedListener> invoke() {
                return new ListenerList<>();
            }
        });
        this.deviceOpenStatusCollection = new HashMap<>();
        Pdlog.m3273d(this.TAG, "add listener to listen device state");
        this.deviceInterface.addListener(this.TAG, this);
    }

    /* renamed from: getDeviceInterface$module_robot_mirsdk_wrapper_release, reason: from getter */
    public final DeviceInterface getDeviceInterface() {
        return this.deviceInterface;
    }

    /* renamed from: getDisinfectionPowerDeviceControl$module_robot_mirsdk_wrapper_release, reason: from getter */
    public final DisinfectionPowerDeviceControl getDisinfectionPowerDeviceControl() {
        return this.disinfectionPowerDeviceControl;
    }

    /* renamed from: getIrledDeviceControl$module_robot_mirsdk_wrapper_release, reason: from getter */
    public final IrledDeviceControl getIrledDeviceControl() {
        return this.irledDeviceControl;
    }

    /* renamed from: getLidarDeviceControl$module_robot_mirsdk_wrapper_release, reason: from getter */
    public final LidarDeviceControl getLidarDeviceControl() {
        return this.lidarDeviceControl;
    }

    /* renamed from: getMarkerCameraDeviceControl$module_robot_mirsdk_wrapper_release, reason: from getter */
    public final MarkerCameraDeviceControl getMarkerCameraDeviceControl() {
        return this.markerCameraDeviceControl;
    }

    /* renamed from: getRgbdDeviceControl$module_robot_mirsdk_wrapper_release, reason: from getter */
    public final RgbdDeviceControl getRgbdDeviceControl() {
        return this.rgbdDeviceControl;
    }

    public final boolean getEmergencyKeyPressed() {
        return this.emergencyKeyPressed;
    }

    public final void closeDevice(ArrayList<DeviceName> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(this.TAG, "lowPowerConsumption closeDevice " + l);
        Iterator<T> it = l.iterator();
        while (it.hasNext()) {
            int i = WhenMappings.$EnumSwitchMapping$0[((DeviceName) it.next()).ordinal()];
            if (i == 1) {
                this.irledDeviceControl.close();
            } else if (i == 2) {
                this.lidarDeviceControl.close();
            } else if (i == 3) {
                this.markerCameraDeviceControl.close();
            } else if (i == 4) {
                this.rgbdDeviceControl.close();
            } else if (i == 5) {
                this.disinfectionPowerDeviceControl.close();
            }
        }
    }

    public final boolean checkDeviceOpenState(DeviceName device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        int i = WhenMappings.$EnumSwitchMapping$1[device.ordinal()];
        if (i == 1) {
            return this.irledDeviceControl.getCurrentDeviceSwitch();
        }
        if (i == 2) {
            return this.lidarDeviceControl.getCurrentDeviceSwitch();
        }
        if (i == 3) {
            return this.markerCameraDeviceControl.getCurrentDeviceSwitch();
        }
        if (i == 4) {
            return this.rgbdDeviceControl.getCurrentDeviceSwitch();
        }
        if (i == 5) {
            return this.disinfectionPowerDeviceControl.getCurrentDeviceSwitch();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void addOnEmergencyKeyPressedListener(OnEmergencyKeyPressedListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (getOnEmergencyKeyPressedListeners().contains$module_robot_mirsdk_wrapper_release(l)) {
            return;
        }
        getOnEmergencyKeyPressedListeners().add$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeOnEmergencyKeyPressedListener(OnEmergencyKeyPressedListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnEmergencyKeyPressedListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void lockMotor(boolean b) {
        this.deviceInterface.lockMotor(b);
    }

    public final void openDevice(ArrayList<DeviceName> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(this.TAG, "openRobotMoveDevice " + l);
        Iterator<T> it = l.iterator();
        while (it.hasNext()) {
            int i = WhenMappings.$EnumSwitchMapping$2[((DeviceName) it.next()).ordinal()];
            if (i == 1) {
                this.irledDeviceControl.open();
            } else if (i == 2) {
                this.lidarDeviceControl.open();
            } else if (i == 3) {
                this.markerCameraDeviceControl.open();
            } else if (i == 4) {
                this.rgbdDeviceControl.open();
            } else if (i == 5) {
                this.disinfectionPowerDeviceControl.open();
            }
        }
    }

    public final void openDevice(DeviceName... device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        Pdlog.m3273d(this.TAG, "lowPowerConsumption openDevice " + ArraysKt.asList(device));
        for (DeviceName deviceName : device) {
            int i = WhenMappings.$EnumSwitchMapping$4[deviceName.ordinal()];
            if (i == 1) {
                this.irledDeviceControl.open();
            } else if (i == 2) {
                this.lidarDeviceControl.open();
            } else if (i == 3) {
                this.rgbdDeviceControl.open();
            } else if (i == 4) {
                this.markerCameraDeviceControl.open();
            } else if (i == 5) {
                this.disinfectionPowerDeviceControl.open();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object restartDevice(DeviceName deviceName, Continuation<? super Boolean> continuation) {
        DevicesControlHelper$restartDevice$1 devicesControlHelper$restartDevice$1;
        int i;
        boolean booleanValue;
        if (continuation instanceof DevicesControlHelper$restartDevice$1) {
            devicesControlHelper$restartDevice$1 = (DevicesControlHelper$restartDevice$1) continuation;
            if ((devicesControlHelper$restartDevice$1.label & Integer.MIN_VALUE) != 0) {
                devicesControlHelper$restartDevice$1.label -= Integer.MIN_VALUE;
                Object obj = devicesControlHelper$restartDevice$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = devicesControlHelper$restartDevice$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(this.TAG, "restartDevice : " + deviceName);
                    int i2 = WhenMappings.$EnumSwitchMapping$5[deviceName.ordinal()];
                    if (i2 == 1) {
                        IrledDeviceControl irledDeviceControl = this.irledDeviceControl;
                        devicesControlHelper$restartDevice$1.L$0 = this;
                        devicesControlHelper$restartDevice$1.L$1 = deviceName;
                        devicesControlHelper$restartDevice$1.label = 1;
                        obj = irledDeviceControl.restart$module_robot_mirsdk_wrapper_release(devicesControlHelper$restartDevice$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanValue = ((Boolean) obj).booleanValue();
                    } else if (i2 == 2) {
                        LidarDeviceControl lidarDeviceControl = this.lidarDeviceControl;
                        devicesControlHelper$restartDevice$1.L$0 = this;
                        devicesControlHelper$restartDevice$1.L$1 = deviceName;
                        devicesControlHelper$restartDevice$1.label = 2;
                        obj = lidarDeviceControl.restart$module_robot_mirsdk_wrapper_release(devicesControlHelper$restartDevice$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanValue = ((Boolean) obj).booleanValue();
                    } else if (i2 == 3) {
                        RgbdDeviceControl rgbdDeviceControl = this.rgbdDeviceControl;
                        devicesControlHelper$restartDevice$1.L$0 = this;
                        devicesControlHelper$restartDevice$1.L$1 = deviceName;
                        devicesControlHelper$restartDevice$1.label = 3;
                        obj = rgbdDeviceControl.restart$module_robot_mirsdk_wrapper_release(devicesControlHelper$restartDevice$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanValue = ((Boolean) obj).booleanValue();
                    } else if (i2 == 4) {
                        MarkerCameraDeviceControl markerCameraDeviceControl = this.markerCameraDeviceControl;
                        devicesControlHelper$restartDevice$1.L$0 = this;
                        devicesControlHelper$restartDevice$1.L$1 = deviceName;
                        devicesControlHelper$restartDevice$1.label = 4;
                        obj = markerCameraDeviceControl.restart$module_robot_mirsdk_wrapper_release(devicesControlHelper$restartDevice$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanValue = ((Boolean) obj).booleanValue();
                    } else {
                        if (i2 != 5) {
                            throw new NoWhenBranchMatchedException();
                        }
                        DisinfectionPowerDeviceControl disinfectionPowerDeviceControl = this.disinfectionPowerDeviceControl;
                        devicesControlHelper$restartDevice$1.L$0 = this;
                        devicesControlHelper$restartDevice$1.L$1 = deviceName;
                        devicesControlHelper$restartDevice$1.label = 5;
                        obj = disinfectionPowerDeviceControl.restart$module_robot_mirsdk_wrapper_release(devicesControlHelper$restartDevice$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanValue = ((Boolean) obj).booleanValue();
                    }
                } else if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                } else if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                } else if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                } else if (i == 4) {
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                } else {
                    if (i != 5) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                }
                return Boxing.boxBoolean(booleanValue);
            }
        }
        devicesControlHelper$restartDevice$1 = new DevicesControlHelper$restartDevice$1(this, continuation);
        Object obj2 = devicesControlHelper$restartDevice$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = devicesControlHelper$restartDevice$1.label;
        if (i != 0) {
        }
        return Boxing.boxBoolean(booleanValue);
    }

    public final void addDeviceOpenStatusListener(Function1<? super HashMap<String, Boolean>, Unit> l) {
        this.cb = l;
    }

    public final void removeDeviceOpenStatusListener() {
        if (this.cb != null) {
            this.cb = (Function1) null;
        }
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onLidar(boolean p0) {
        Pdlog.m3273d(this.TAG, "onLidar : p0 = " + p0 + "; ");
        this.lidarDeviceControl.setCurrentDeviceSwitch(p0);
        this.deviceOpenStatusCollection.put(DeviceName.LIDAR.name(), Boolean.valueOf(p0));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DevicesControlHelper$onLidar$1(this, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onDisinfectionPower(boolean p0) {
        Pdlog.m3273d(this.TAG, "onDisinfectionPower : p0 = " + p0 + "; ");
        this.disinfectionPowerDeviceControl.setCurrentDeviceSwitch(p0);
        this.deviceOpenStatusCollection.put(DeviceName.Disinfection.name(), Boolean.valueOf(p0));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DevicesControlHelper$onDisinfectionPower$1(this, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onIRLED(boolean p0) {
        Pdlog.m3273d(this.TAG, "onIRLED : p0 = " + p0 + "; ");
        this.irledDeviceControl.setCurrentDeviceSwitch(p0);
        this.deviceOpenStatusCollection.put(DeviceName.IRLED.name(), Boolean.valueOf(p0));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DevicesControlHelper$onIRLED$1(this, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onLockMotor(boolean p0) {
        Pdlog.m3273d(this.TAG, "onLockMotor: p0=" + p0);
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onBumperSwitchStatus(boolean p0) {
        Pdlog.m3273d(this.TAG, "onBumperSwitchStatus p0 = " + p0);
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onRGBD(boolean p0) {
        Pdlog.m3273d(this.TAG, "onRGBD : p0 = " + p0 + "; ");
        this.rgbdDeviceControl.setCurrentDeviceSwitch(p0);
        this.deviceOpenStatusCollection.put(DeviceName.RGBD.name(), Boolean.valueOf(p0));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DevicesControlHelper$onRGBD$1(this, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onMarkerCamera(boolean p0) {
        Pdlog.m3273d(this.TAG, "onMarkerCamera : p0 = " + p0 + "; ");
        this.markerCameraDeviceControl.setCurrentDeviceSwitch(p0);
        this.deviceOpenStatusCollection.put(DeviceName.MARKER_CAMERA.name(), Boolean.valueOf(p0));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DevicesControlHelper$onMarkerCamera$1(this, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onEmergencyKeyPressed(final boolean isPressed) {
        Pdlog.m3273d(this.TAG, "onEmergencyKeyPressed : p0 = " + isPressed + "; ");
        this.emergencyKeyPressed = isPressed;
        getOnEmergencyKeyPressedListeners().forEach(Dispatchers.getMain(), new Function1<OnEmergencyKeyPressedListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper$onEmergencyKeyPressed$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DevicesControlHelper.OnEmergencyKeyPressedListener onEmergencyKeyPressedListener) {
                invoke2(onEmergencyKeyPressedListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DevicesControlHelper.OnEmergencyKeyPressedListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onEmergencyKeyPressed(isPressed);
            }
        });
    }

    public final void openDevice(DeviceName[] device, Function1<? super HashMap<String, Boolean>, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        for (DeviceName deviceName : device) {
            int i = WhenMappings.$EnumSwitchMapping$3[deviceName.ordinal()];
            if (i == 1) {
                this.irledDeviceControl.open();
            } else if (i == 2) {
                this.lidarDeviceControl.open();
            } else if (i == 3) {
                this.markerCameraDeviceControl.open();
            } else if (i == 4) {
                this.rgbdDeviceControl.open();
            } else if (i == 5) {
                this.disinfectionPowerDeviceControl.open();
            }
        }
        this.cb = cb;
    }
}
