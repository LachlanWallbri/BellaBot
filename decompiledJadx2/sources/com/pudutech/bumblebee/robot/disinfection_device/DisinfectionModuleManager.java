package com.pudutech.bumblebee.robot.disinfection_device;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.OpenState;
import com.pudutech.bumblebee.robot.aidl.serialize.SprayDeviceError;
import com.pudutech.bumblebee.robot.aidl.serialize.UvLampDeviceError;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DisinfectionModuleManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010#\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u000eJ\u0006\u0010%\u001a\u00020\u001aJ\u0006\u0010&\u001a\u00020\u001aJ\u0006\u0010'\u001a\u00020 J\u0006\u0010(\u001a\u00020\"J\u001a\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+H\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u0006\u0010.\u001a\u00020\u0004J\u0010\u0010/\u001a\u00020\u001a2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u00100\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+ø\u0001\u0000¢\u0006\u0004\b1\u0010-J\u000e\u00102\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u0004J\u0018\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u000205ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001a\u00108\u001a\u00020\u001a2\u0006\u00109\u001a\u00020+H\u0002ø\u0001\u0000¢\u0006\u0004\b:\u0010-R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R7\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006;"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/disinfection_device/DisinfectionModuleManager;", "", "()V", "TAG", "", "hardware", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "hardwareVersion", "getHardwareVersion", "()Ljava/lang/String;", "setHardwareVersion", "(Ljava/lang/String;)V", "listeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/bumblebee/robot/aidl/IDisinfectionRobotListener;", "modeSwitch", "", "getModeSwitch", "()Z", "setModeSwitch", "(Z)V", "onSwitchListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "", "getOnSwitchListener", "()Lkotlin/jvm/functions/Function1;", "setOnSwitchListener", "(Lkotlin/jvm/functions/Function1;)V", "sprayDevice", "Lcom/pudutech/bumblebee/robot/disinfection_device/SprayDevice;", "uvLampDevice", "Lcom/pudutech/bumblebee/robot/disinfection_device/UvLampDevice;", "addListener", "listener", "boot", "close", "getSprayDevice", "getUvLampDevice", "handleReceiveData", "bytes", "Lkotlin/UByteArray;", "handleReceiveData-GBYM_sE", "([B)V", "handlerHardVersion", "init", "onDataReceive", "onDataReceive-GBYM_sE", "removeListener", "selectSpringType", "byte", "Lkotlin/UByte;", "selectSpringType-7apg3OU", "(B)V", "sendCan", "u", "sendCan-GBYM_sE", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DisinfectionModuleManager {
    private static HardwareInterface hardware;
    private static boolean modeSwitch;
    private static Function1<? super Boolean, Unit> onSwitchListener;
    public static final DisinfectionModuleManager INSTANCE = new DisinfectionModuleManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final SprayDevice sprayDevice = new SprayDevice();
    private static final UvLampDevice uvLampDevice = new UvLampDevice();
    private static final ThreadSafeListener<IDisinfectionRobotListener> listeners = new ThreadSafeListener<>();
    private static String hardwareVersion = "";

    private DisinfectionModuleManager() {
    }

    public final boolean getModeSwitch() {
        return modeSwitch;
    }

    public final void setModeSwitch(boolean z) {
        modeSwitch = z;
    }

    public final Function1<Boolean, Unit> getOnSwitchListener() {
        return onSwitchListener;
    }

    public final void setOnSwitchListener(Function1<? super Boolean, Unit> function1) {
        onSwitchListener = function1;
    }

    public final String getHardwareVersion() {
        return hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        hardwareVersion = str;
    }

    public final void init(HardwareInterface hardware2) {
        hardware = hardware2;
        sprayDevice.init(hardware2);
        sprayDevice.setOnSprayDeviceOpenListener$Robot_release(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                ThreadSafeListener threadSafeListener;
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onSprayDiveceOpen(z);
                    }
                });
            }
        });
        sprayDevice.setOnSpraySpringOpenStatusListener$Robot_release(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                ThreadSafeListener threadSafeListener;
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onSpringOpenStatus(z);
                    }
                });
            }
        });
        sprayDevice.setOnSprayDeviceErrorListener$Robot_release(new Function1<SprayDeviceError[], Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SprayDeviceError[] sprayDeviceErrorArr) {
                invoke2(sprayDeviceErrorArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final SprayDeviceError[] es2) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(es2, "es");
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onSprayDiveceError(es2);
                    }
                });
            }
        });
        sprayDevice.setOnSprayLiquidLevelListener$Robot_release(new Function2<Double, Double, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Double d, Double d2) {
                invoke(d.doubleValue(), d2.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final double d, final double d2) {
                ThreadSafeListener threadSafeListener;
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$4.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onLiquidLevelChange(d);
                        it.onSprayChamberLevelChange(d2);
                    }
                });
            }
        });
        sprayDevice.setOnSprayLiquidLevelStatusListener$Robot_release(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$5
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                ThreadSafeListener threadSafeListener;
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onSprayLiquidStatus(z);
                    }
                });
            }
        });
        uvLampDevice.init(hardware2);
        uvLampDevice.setOnUvDeviceOpenListener$Robot_release(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                ThreadSafeListener threadSafeListener;
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$6.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onUvLampDeviceOpen(z);
                    }
                });
            }
        });
        uvLampDevice.setOnUvDeviceErrorListener$Robot_release(new Function1<UvLampDeviceError[], Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$7
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UvLampDeviceError[] uvLampDeviceErrorArr) {
                invoke2(uvLampDeviceErrorArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final UvLampDeviceError[] es2) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(es2, "es");
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$7.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onUvLampDeviceError(es2);
                    }
                });
            }
        });
        uvLampDevice.setOnUvDevicePlateOpenStateListener$Robot_release(new Function1<OpenState, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OpenState openState) {
                invoke2(openState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final OpenState st) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(st, "st");
                DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
                threadSafeListener = DisinfectionModuleManager.listeners;
                threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$8.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                        invoke2(iDisinfectionRobotListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDisinfectionRobotListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onUvLampPlateOpenState(OpenState.this);
                    }
                });
            }
        });
    }

    public final String handlerHardVersion() {
        HardwareInterface hardwareInterface = hardware;
        HardwareVersion[] hardwareVersion2 = hardwareInterface != null ? hardwareInterface.getHardwareVersion() : null;
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("current hard ware version ");
        sb.append(hardwareVersion2 != null ? Integer.valueOf(hardwareVersion2.length) : null);
        sb.append("  ");
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (hardwareVersion2 != null) {
            StringBuilder sb2 = new StringBuilder();
            for (HardwareVersion hardwareVersion3 : hardwareVersion2) {
                Pdlog.m3273d(TAG, "CURRENT VERISON IS " + hardwareVersion3);
                sb2.append(hardwareVersion3.getVer0());
                sb2.append("_");
                sb2.append(hardwareVersion3.getVer1());
                sb2.append("_");
                sb2.append(hardwareVersion3.getVer2());
            }
            String sb3 = sb2.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb3, "stringBuilder.toString()");
            hardwareVersion = sb3;
        } else {
            Pdlog.m3273d(TAG, "hared 1 is null");
        }
        return hardwareVersion;
    }

    public final void boot() {
        Pdlog.m3273d(TAG, "boot ");
        m4320sendCanGBYM_sE(new byte[]{-125, 1, 0, 0, 0, 0, 0});
    }

    public final void close() {
        Pdlog.m3273d(TAG, "close ");
        m4320sendCanGBYM_sE(new byte[]{-125, 0, 0, 0, 0, 0, 0});
    }

    /* renamed from: selectSpringType-7apg3OU, reason: not valid java name */
    public final void m4322selectSpringType7apg3OU(byte r6) {
        Pdlog.m3273d(TAG, "select Spring Type " + UByte.m4563toStringimpl(r6));
        m4320sendCanGBYM_sE(new byte[]{Constans.CAN_REV_UV_SET_RESULT, 9, 1, r6, 0, 0, 0});
    }

    /* renamed from: sendCan-GBYM_sE, reason: not valid java name */
    private final void m4320sendCanGBYM_sE(byte[] u) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectionModuleManager$sendCan$1(u, null), 3, null);
    }

    public final SprayDevice getSprayDevice() {
        return sprayDevice;
    }

    public final UvLampDevice getUvLampDevice() {
        return uvLampDevice;
    }

    /* renamed from: onDataReceive-GBYM_sE, reason: not valid java name */
    public final void m4321onDataReceiveGBYM_sE(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        byte m4577getimpl = UByteArray.m4577getimpl(bytes, 0);
        if (m4577getimpl == -124) {
            m4319handleReceiveDataGBYM_sE(bytes);
            return;
        }
        if (m4577getimpl == -120) {
            uvLampDevice.m4327onDataReceiveGBYM_sE(bytes);
        } else if (m4577getimpl == -121 || m4577getimpl == -119) {
            sprayDevice.m4325onDataReceiveGBYM_sE(bytes);
        }
    }

    public final void addListener(String name, IDisinfectionRobotListener listener) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "addListener : name = " + name + "; listener = " + listener + "; ");
        listeners.add(name, listener);
    }

    public final void removeListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(TAG, "removeListener : name = " + name + "; ");
        listeners.remove(name);
    }

    /* renamed from: handleReceiveData-GBYM_sE, reason: not valid java name */
    private final void m4319handleReceiveDataGBYM_sE(byte[] bytes) {
        if (UByteArray.m4577getimpl(bytes, 0) != -124) {
            return;
        }
        byte m4577getimpl = UByteArray.m4577getimpl(bytes, 1);
        Pdlog.m3273d(TAG, "handleReceiveData : switch = " + UByte.m4563toStringimpl(m4577getimpl) + "; ");
        boolean z = (m4577getimpl & 255) != 0;
        modeSwitch = z;
        Function1<? super Boolean, Unit> function1 = onSwitchListener;
        if (function1 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectionModuleManager$handleReceiveData$1$1(function1, z, null), 2, null);
        }
    }
}
