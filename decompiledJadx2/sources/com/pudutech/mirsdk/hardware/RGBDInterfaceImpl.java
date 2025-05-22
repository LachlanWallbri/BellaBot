package com.pudutech.mirsdk.hardware;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.RGBDState;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.rgbdlib.config.RGBDConfig;
import com.pudutech.rgbdlib.config.RGBDJson;
import java.io.FileReader;
import java.io.Reader;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: RGBDInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002NOB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010.\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u00062\b\u00100\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u00101\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u00062\b\u00100\u001a\u0004\u0018\u00010\u0019H\u0016J\u001c\u00102\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u00062\b\u00100\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u00103\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u00062\b\u00100\u001a\u0004\u0018\u00010)H\u0016J\u001c\u00104\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u00062\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u000e\u00108\u001a\u00020\u000b2\u0006\u00109\u001a\u00020-J\u0006\u0010:\u001a\u00020\u0006J\u0006\u0010;\u001a\u00020\u0006J\u0006\u0010<\u001a\u00020 J\b\u0010=\u001a\u00020-H\u0016J\u000e\u0010>\u001a\u00020-2\u0006\u0010?\u001a\u00020-J\u0006\u0010@\u001a\u00020\u000bJ\u0012\u0010A\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010B\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010C\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010D\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010E\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u0006H\u0016J\u0016\u0010F\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020-2\u0006\u0010G\u001a\u00020-J\u000e\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020KJ\b\u0010L\u001a\u00020\u000bH\u0016J\u0006\u0010M\u001a\u00020-R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R,\u0010\u0007\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0012\u001a\u00060\u0013R\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\rX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u001a\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\rX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u001d\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\rX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010*\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006P"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl;", "Lcom/pudutech/mirsdk/hardware/RGBDInterface$Stub;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "centerRGBDSubscription", "Lkotlin/Function4;", "Landroid/os/ParcelFileDescriptor;", "", "", "centernRGBDListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/ICenterRgbdData;", "configFileName", "configure", "Lcom/pudutech/rgbdlib/config/RGBDConfig;", "container", "Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl$RGBDStateContainer;", "getContainer", "()Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl$RGBDStateContainer;", "controlJob", "Lkotlinx/coroutines/Job;", "downRGBDListener", "Lcom/pudutech/mirsdk/hardware/IDownRgbdData;", "downRGBDSubscription", "leftRGBDListeners", "Lcom/pudutech/mirsdk/hardware/ILeftRgbdData;", "leftRGBDSubscription", "machineMode", "rgbdDevicesStatus", "Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl$RGBDDevicesStatus;", "rgbdService", "Lcom/pudutech/rgbdlib/RGBDSensor;", "rgbdState", "Ljava/util/concurrent/atomic/AtomicBoolean;", "rgbdType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType;", "rgbdVersion", "rightRGBDListeners", "Lcom/pudutech/mirsdk/hardware/IRightRgbdData;", "rightRGBDSubscription", "setParkingModeJob", "started", "", "addCenterRgbdListener", "p0", "p1", "addDownRgbdListener", "addLeftRgbdListener", "addRightRgbdListener", "addStateListener", "name", "listener", "Lcom/pudutech/mirsdk/hardware/IRgbdStatus;", "controlRGBD", DebugKt.DEBUG_PROPERTY_VALUE_ON, "getLastError", "getRGBDGit", "getRgbdDevicesStatus", "isEnabled", "oilStainCheck", "isRGBDVersion", "refreshRgbdConfireu", "removeCenterRgbdListener", "removeDownRgbdListener", "removeLeftRgbdListener", "removeRightRgbdListener", "removeStateListener", "setParkingMode", "enable", "start", "Lcom/pudutech/rgbdlib/RGBDSensor$Result;", "version", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "startPreviewActivity", "updateFirmware", "RGBDDevicesStatus", "RGBDStateContainer", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RGBDInterfaceImpl extends RGBDInterface.Stub {
    private final String TAG;
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> centerRGBDSubscription;
    private final ThreadSafeListener<ICenterRgbdData> centernRGBDListeners;
    private final String configFileName;
    private RGBDConfig configure;
    private final RGBDStateContainer container;
    private final Context context;
    private Job controlJob;
    private final ThreadSafeListener<IDownRgbdData> downRGBDListener;
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> downRGBDSubscription;
    private final ThreadSafeListener<ILeftRgbdData> leftRGBDListeners;
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> leftRGBDSubscription;
    private int machineMode;
    private RGBDDevicesStatus rgbdDevicesStatus;
    private final RGBDSensor rgbdService;
    private AtomicBoolean rgbdState;
    private MachineInfo.RGBDType rgbdType;
    private MachineInfo.RGBDType rgbdVersion;
    private final ThreadSafeListener<IRightRgbdData> rightRGBDListeners;
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> rightRGBDSubscription;
    private Job setParkingModeJob;
    private boolean started;

    public final boolean oilStainCheck(boolean isRGBDVersion) {
        if (!isRGBDVersion) {
        }
        return true;
    }

    public RGBDInterfaceImpl(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "rgbd";
        this.container = new RGBDStateContainer();
        this.configFileName = "/sdcard/pudu/config/rgbd.json";
        this.configure = new RGBDConfig(null, null, null, null, null, null);
        this.rgbdService = new RGBDSensor(this.context);
        this.rgbdType = MachineInfo.RGBDType.NODevice;
        this.machineMode = 1;
        this.leftRGBDListeners = new ThreadSafeListener<>();
        this.rightRGBDListeners = new ThreadSafeListener<>();
        this.centernRGBDListeners = new ThreadSafeListener<>();
        this.downRGBDListener = new ThreadSafeListener<>();
        this.leftRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$leftRGBDSubscription$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
                invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final ParcelFileDescriptor parcelFileDescriptor, final int i, final int i2, final int i3) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                RGBDInterfaceImpl.this.getContainer().invoke(RGBDState.LeftRgbdOK);
                threadSafeListener = RGBDInterfaceImpl.this.leftRGBDListeners;
                threadSafeListener.notify(new Function2<ILeftRgbdData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$leftRGBDSubscription$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ILeftRgbdData iLeftRgbdData, String str) {
                        invoke2(iLeftRgbdData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ILeftRgbdData it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onLeftFrameDescriptor(parcelFileDescriptor, i, i2, i3);
                    }
                });
            }
        };
        this.rightRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$rightRGBDSubscription$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
                invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final ParcelFileDescriptor parcelFileDescriptor, final int i, final int i2, final int i3) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                RGBDInterfaceImpl.this.getContainer().invoke(RGBDState.RightRgbdOK);
                threadSafeListener = RGBDInterfaceImpl.this.rightRGBDListeners;
                threadSafeListener.notify(new Function2<IRightRgbdData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$rightRGBDSubscription$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IRightRgbdData iRightRgbdData, String str) {
                        invoke2(iRightRgbdData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IRightRgbdData it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onRightFrameDescriptor(parcelFileDescriptor, i, i2, i3);
                    }
                });
            }
        };
        this.centerRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$centerRGBDSubscription$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
                invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final ParcelFileDescriptor parcelFileDescriptor, final int i, final int i2, final int i3) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                RGBDInterfaceImpl.this.getContainer().invoke(RGBDState.CenterRgbdOK);
                threadSafeListener = RGBDInterfaceImpl.this.centernRGBDListeners;
                threadSafeListener.notify(new Function2<ICenterRgbdData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$centerRGBDSubscription$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ICenterRgbdData iCenterRgbdData, String str) {
                        invoke2(iCenterRgbdData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ICenterRgbdData it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onCenterFrameDescriptor(parcelFileDescriptor, i, i2, i3);
                    }
                });
            }
        };
        this.downRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$downRGBDSubscription$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
                invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final ParcelFileDescriptor parcelFileDescriptor, final int i, final int i2, final int i3) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                RGBDInterfaceImpl.this.getContainer().invoke(RGBDState.DownRgbdOK);
                threadSafeListener = RGBDInterfaceImpl.this.downRGBDListener;
                threadSafeListener.notify(new Function2<IDownRgbdData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$downRGBDSubscription$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDownRgbdData iDownRgbdData, String str) {
                        invoke2(iDownRgbdData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDownRgbdData it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onDownFrameDescriptor(parcelFileDescriptor, i, i2, i3);
                    }
                });
            }
        };
        this.rgbdVersion = MachineInfo.RGBDType.NODevice;
        this.rgbdDevicesStatus = new RGBDDevicesStatus(false, false, false, false);
        this.rgbdState = new AtomicBoolean(false);
    }

    public final RGBDStateContainer getContainer() {
        return this.container;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: RGBDInterfaceImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl$RGBDDevicesStatus;", "", "leftRGBDStatus", "", "rightRGBDStatus", "centerRGBDStatus", "downRGBDStatus", "(ZZZZ)V", "getCenterRGBDStatus", "()Z", "getDownRGBDStatus", "getLeftRGBDStatus", "getRightRGBDStatus", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class RGBDDevicesStatus {
        private final boolean centerRGBDStatus;
        private final boolean downRGBDStatus;
        private final boolean leftRGBDStatus;
        private final boolean rightRGBDStatus;

        public static /* synthetic */ RGBDDevicesStatus copy$default(RGBDDevicesStatus rGBDDevicesStatus, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
            if ((i & 1) != 0) {
                z = rGBDDevicesStatus.leftRGBDStatus;
            }
            if ((i & 2) != 0) {
                z2 = rGBDDevicesStatus.rightRGBDStatus;
            }
            if ((i & 4) != 0) {
                z3 = rGBDDevicesStatus.centerRGBDStatus;
            }
            if ((i & 8) != 0) {
                z4 = rGBDDevicesStatus.downRGBDStatus;
            }
            return rGBDDevicesStatus.copy(z, z2, z3, z4);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getLeftRGBDStatus() {
            return this.leftRGBDStatus;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getRightRGBDStatus() {
            return this.rightRGBDStatus;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getCenterRGBDStatus() {
            return this.centerRGBDStatus;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getDownRGBDStatus() {
            return this.downRGBDStatus;
        }

        public final RGBDDevicesStatus copy(boolean leftRGBDStatus, boolean rightRGBDStatus, boolean centerRGBDStatus, boolean downRGBDStatus) {
            return new RGBDDevicesStatus(leftRGBDStatus, rightRGBDStatus, centerRGBDStatus, downRGBDStatus);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RGBDDevicesStatus)) {
                return false;
            }
            RGBDDevicesStatus rGBDDevicesStatus = (RGBDDevicesStatus) other;
            return this.leftRGBDStatus == rGBDDevicesStatus.leftRGBDStatus && this.rightRGBDStatus == rGBDDevicesStatus.rightRGBDStatus && this.centerRGBDStatus == rGBDDevicesStatus.centerRGBDStatus && this.downRGBDStatus == rGBDDevicesStatus.downRGBDStatus;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v2, types: [boolean] */
        public int hashCode() {
            boolean z = this.leftRGBDStatus;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            ?? r2 = this.rightRGBDStatus;
            int i2 = r2;
            if (r2 != 0) {
                i2 = 1;
            }
            int i3 = (i + i2) * 31;
            ?? r22 = this.centerRGBDStatus;
            int i4 = r22;
            if (r22 != 0) {
                i4 = 1;
            }
            int i5 = (i3 + i4) * 31;
            boolean z2 = this.downRGBDStatus;
            return i5 + (z2 ? 1 : z2 ? 1 : 0);
        }

        public String toString() {
            return "RGBDDevicesStatus(leftRGBDStatus=" + this.leftRGBDStatus + ", rightRGBDStatus=" + this.rightRGBDStatus + ", centerRGBDStatus=" + this.centerRGBDStatus + ", downRGBDStatus=" + this.downRGBDStatus + ")";
        }

        public RGBDDevicesStatus(boolean z, boolean z2, boolean z3, boolean z4) {
            this.leftRGBDStatus = z;
            this.rightRGBDStatus = z2;
            this.centerRGBDStatus = z3;
            this.downRGBDStatus = z4;
        }

        public final boolean getCenterRGBDStatus() {
            return this.centerRGBDStatus;
        }

        public final boolean getDownRGBDStatus() {
            return this.downRGBDStatus;
        }

        public final boolean getLeftRGBDStatus() {
            return this.leftRGBDStatus;
        }

        public final boolean getRightRGBDStatus() {
            return this.rightRGBDStatus;
        }
    }

    public final RGBDSensor.Result start(MachineInfo version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        MachineInfo.RGBDType rGBDMode = version.getRGBDMode();
        this.rgbdVersion = rGBDMode;
        if (this.started) {
            return new RGBDSensor.Result(true, "");
        }
        this.rgbdType = rGBDMode;
        int id = version.getProductType().getModel().getId();
        this.machineMode = id;
        RGBDSensor.Result start = this.rgbdService.start(this.rgbdType, id, this.leftRGBDSubscription, this.rightRGBDSubscription, this.centerRGBDSubscription, this.downRGBDSubscription);
        if (start.isSuccess()) {
            this.started = true;
            this.container.getStateListeners().notify(new Function2<IRgbdStatus, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$start$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IRgbdStatus iRgbdStatus, String str) {
                    invoke2(iRgbdStatus, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IRgbdStatus l, String str) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    l.onRGBDOpened(true);
                }
            });
        } else {
            this.started = false;
            this.container.getStateListeners().notify(new Function2<IRgbdStatus, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$start$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IRgbdStatus iRgbdStatus, String str) {
                    invoke2(iRgbdStatus, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IRgbdStatus l, String str) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    l.onRGBDOpened(false);
                }
            });
        }
        try {
            Object fromJson = new Gson().fromJson((Reader) new FileReader(this.configFileName), (Class<Object>) RGBDConfig.class);
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(FileReader…, RGBDConfig::class.java)");
            this.configure = (RGBDConfig) fromJson;
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "read rgbd config file exception " + e.getLocalizedMessage());
        }
        return start;
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public boolean isEnabled() {
        return this.rgbdState.get();
    }

    public final RGBDDevicesStatus getRgbdDevicesStatus() {
        return this.rgbdDevicesStatus;
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void addStateListener(String name, IRgbdStatus listener) {
        Pdlog.m3273d(this.TAG, "addStateListener " + name);
        if (listener == null || name == null) {
            return;
        }
        this.container.getStateListeners().add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void removeStateListener(String name) {
        Pdlog.m3275i(this.TAG, "removeStateListener " + name);
        if (name != null) {
            this.container.getStateListeners().remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void addCenterRgbdListener(String p0, ICenterRgbdData p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.centernRGBDListeners.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void addDownRgbdListener(String p0, IDownRgbdData p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.downRGBDListener.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void removeDownRgbdListener(String name) {
        if (name == null) {
            return;
        }
        this.downRGBDListener.remove(name);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void addLeftRgbdListener(String p0, ILeftRgbdData p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.leftRGBDListeners.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void addRightRgbdListener(String p0, IRightRgbdData p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.rightRGBDListeners.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void removeCenterRgbdListener(String p0) {
        if (p0 == null) {
            return;
        }
        this.centernRGBDListeners.remove(p0);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void removeLeftRgbdListener(String p0) {
        if (p0 == null) {
            return;
        }
        this.leftRGBDListeners.remove(p0);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void removeRightRgbdListener(String p0) {
        if (p0 == null) {
            return;
        }
        this.rightRGBDListeners.remove(p0);
    }

    @Override // com.pudutech.mirsdk.hardware.RGBDInterface
    public void startPreviewActivity() {
        RGBDSensor rGBDSensor = this.rgbdService;
        rGBDSensor.startPreviewActivity(this.context, rGBDSensor);
    }

    public final boolean updateFirmware() {
        return this.rgbdService.checkUpdateFirmware();
    }

    public final void controlRGBD(final boolean on) {
        Job launch$default;
        Pdlog.m3273d(this.TAG, "controlRGBD " + on);
        if (this.rgbdType == MachineInfo.RGBDType.NODevice) {
            this.container.getStateListeners().notify(new Function2<IRgbdStatus, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$controlRGBD$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IRgbdStatus iRgbdStatus, String str) {
                    invoke2(iRgbdStatus, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IRgbdStatus l, String str) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    l.onRGBDOpened(on);
                }
            });
            return;
        }
        Job job = this.controlJob;
        if (job == null || !job.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new RGBDInterfaceImpl$controlRGBD$2(this, on, null), 3, null);
            this.controlJob = launch$default;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RGBDInterfaceImpl$controlRGBD$3(this, null), 3, null);
            return;
        }
        Pdlog.m3276v(this.TAG, "rgbd control job is running");
    }

    public final void refreshRgbdConfireu() {
        this.rgbdService.RefreshConfigure();
    }

    public final String getRGBDGit() {
        return this.rgbdService.getGitHash();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: RGBDInterfaceImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl$RGBDStateContainer;", "", "(Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl;)V", "centerLastFrameTimestamps", "", "downLastFrameTimestamps", "leftLastFrameTimestamps", "rightLastFrameTimestamps", "stateListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IRgbdStatus;", "getStateListeners", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "invoke", "", "state", "Lcom/pudutech/mirsdk/hardware/serialize/RGBDState;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final class RGBDStateContainer {
        private long centerLastFrameTimestamps;
        private long downLastFrameTimestamps;
        private long leftLastFrameTimestamps;
        private long rightLastFrameTimestamps;
        private final ThreadSafeListener<IRgbdStatus> stateListeners = new ThreadSafeListener<>();

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
          classes5.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[RGBDState.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[RGBDState.LeftRgbdOK.ordinal()] = 1;
                $EnumSwitchMapping$0[RGBDState.RightRgbdOK.ordinal()] = 2;
                $EnumSwitchMapping$0[RGBDState.CenterRgbdOK.ordinal()] = 3;
                $EnumSwitchMapping$0[RGBDState.DownRgbdOK.ordinal()] = 4;
            }
        }

        public RGBDStateContainer() {
        }

        public final ThreadSafeListener<IRgbdStatus> getStateListeners() {
            return this.stateListeners;
        }

        public final void invoke(RGBDState state) {
            RGBDJson down_rgbd;
            RGBDJson center_rgbd;
            RGBDJson right_rgbd;
            RGBDJson left_rgbd;
            Intrinsics.checkParameterIsNotNull(state, "state");
            long elapsedRealtime = SystemClock.elapsedRealtime();
            RGBDConfig rGBDConfig = RGBDInterfaceImpl.this.configure;
            Boolean bool = null;
            boolean z = !Intrinsics.areEqual((Object) ((rGBDConfig == null || (left_rgbd = rGBDConfig.getLeft_rgbd()) == null) ? null : left_rgbd.getShield()), (Object) false) || elapsedRealtime - this.leftLastFrameTimestamps < ((long) 3000);
            RGBDConfig rGBDConfig2 = RGBDInterfaceImpl.this.configure;
            boolean z2 = !Intrinsics.areEqual((Object) ((rGBDConfig2 == null || (right_rgbd = rGBDConfig2.getRight_rgbd()) == null) ? null : right_rgbd.getShield()), (Object) false) || elapsedRealtime - this.rightLastFrameTimestamps < ((long) 3000);
            RGBDConfig rGBDConfig3 = RGBDInterfaceImpl.this.configure;
            boolean z3 = !Intrinsics.areEqual((Object) ((rGBDConfig3 == null || (center_rgbd = rGBDConfig3.getCenter_rgbd()) == null) ? null : center_rgbd.getShield()), (Object) false) || elapsedRealtime - this.centerLastFrameTimestamps < ((long) 3000);
            RGBDConfig rGBDConfig4 = RGBDInterfaceImpl.this.configure;
            if (rGBDConfig4 != null && (down_rgbd = rGBDConfig4.getDown_rgbd()) != null) {
                bool = down_rgbd.getShield();
            }
            boolean z4 = !Intrinsics.areEqual((Object) bool, (Object) false) || elapsedRealtime - this.downLastFrameTimestamps < ((long) 3000);
            int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i == 1) {
                this.leftLastFrameTimestamps = elapsedRealtime;
            } else if (i == 2) {
                this.rightLastFrameTimestamps = elapsedRealtime;
            } else if (i == 3) {
                this.centerLastFrameTimestamps = elapsedRealtime;
            } else if (i != 4) {
                Pdlog.m3273d(RGBDInterfaceImpl.this.TAG, "RGBD state: " + state);
            } else {
                this.downLastFrameTimestamps = elapsedRealtime;
            }
            if (!z || !z2 || !z3 || !z4) {
                RGBDInterfaceImpl.this.rgbdDevicesStatus = new RGBDDevicesStatus(z, z2, z3, z4);
                Pdlog.m3273d(RGBDInterfaceImpl.this.TAG, "RGBD data timeout,leftDataStatus:" + z + " rightDataStatus:" + z2 + " centerDataStatus:" + z3 + " downDataStatus:" + z4);
                return;
            }
            if (!RGBDInterfaceImpl.this.rgbdState.get()) {
                RGBDInterfaceImpl.this.rgbdState.set(true);
            }
            this.stateListeners.notify(new Function2<IRgbdStatus, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$RGBDStateContainer$invoke$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IRgbdStatus iRgbdStatus, String str) {
                    invoke2(iRgbdStatus, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IRgbdStatus it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onRGBDFrameTick();
                }
            });
        }
    }

    public final void setParkingMode(boolean isRGBDVersion, final boolean enable) {
        Job launch$default;
        if (!isRGBDVersion) {
            this.container.getStateListeners().notify(new Function2<IRgbdStatus, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$setParkingMode$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IRgbdStatus iRgbdStatus, String str) {
                    invoke2(iRgbdStatus, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IRgbdStatus l, String str) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    l.onParkingMode(enable);
                }
            });
            return;
        }
        Job job = this.setParkingModeJob;
        if (job == null || !job.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new RGBDInterfaceImpl$setParkingMode$2(this, enable, null), 3, null);
            this.setParkingModeJob = launch$default;
        } else {
            Pdlog.m3276v(this.TAG, "rgbd set parking mode job is running");
        }
    }

    public final String getLastError() {
        return this.rgbdService.getLastError();
    }
}
