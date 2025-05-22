package com.pudutech.bumblebee.robot;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener;
import com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.IUpdateListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDFaceScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateObject;
import com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager;
import com.pudutech.bumblebee.robot.led_screen.LEDScreen;
import com.pudutech.bumblebee.robot.led_screen.UpdateFontLibHelper;
import com.pudutech.bumblebee.robot.lora.LoraToVIP;
import com.pudutech.bumblebee.robot.markscanner.MarkScanner;
import com.pudutech.bumblebee.robot.nfc.NFC;
import com.pudutech.bumblebee.robot.pallet.PalletProtocol;
import com.pudutech.bumblebee.robot.remote_device.LoRaClient;
import com.pudutech.bumblebee.robot.remote_device.RemoteDeviceInterface;
import com.pudutech.lora.library.LoraWatch;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: RobotService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ç\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010)\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010*H\u0016J\u001c\u0010+\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010,\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010!H\u0016J\b\u0010-\u001a\u00020%H\u0016J\u0012\u0010.\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\b\u00101\u001a\u00020%H\u0016J\u0012\u00102\u001a\u00020%2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0012\u00105\u001a\u00020%2\b\u00103\u001a\u0004\u0018\u000106H\u0016J*\u00107\u001a\u00020%2\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;H\u0016J\b\u0010>\u001a\u00020%H\u0016J\u0012\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010C\u001a\u00020%H\u0002J\u0012\u0010D\u001a\u00020%2\b\u0010E\u001a\u0004\u0018\u00010'H\u0016J\u000e\u0010F\u001a\u00020%2\u0006\u0010G\u001a\u00020HJ\b\u0010I\u001a\u00020%H\u0016J\b\u0010J\u001a\u00020%H\u0002J\u0018\u0010K\u001a\u00020%2\u0006\u0010I\u001a\u00020L2\u0006\u0010M\u001a\u00020LH\u0016J\u0018\u0010N\u001a\u00020%2\u0006\u0010I\u001a\u00020L2\u0006\u0010O\u001a\u00020LH\u0016J\u0012\u0010P\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010Q\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010R\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010S\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u0010T\u001a\u00020%H\u0016J\u001a\u0010U\u001a\u00020%2\b\u0010E\u001a\u0004\u0018\u00010B2\u0006\u0010V\u001a\u00020LH\u0016J\u0010\u0010W\u001a\u00020%2\u0006\u0010X\u001a\u00020;H\u0016J\u001a\u0010Y\u001a\u00020%2\b\u0010Z\u001a\u0004\u0018\u00010'2\u0006\u0010[\u001a\u00020;H\u0016J\"\u0010\\\u001a\u00020%2\b\u0010Z\u001a\u0004\u0018\u00010'2\u0006\u0010[\u001a\u00020;2\u0006\u0010]\u001a\u00020;H\u0016J\b\u0010^\u001a\u00020%H\u0016J$\u0010_\u001a\u00020%2\b\u0010`\u001a\u0004\u0018\u00010a2\b\u0010(\u001a\u0004\u0018\u00010b2\u0006\u0010c\u001a\u00020LH\u0016J\u001e\u0010d\u001a\u00020%*\u00020\u00112\u0006\u0010e\u001a\u00020fH\u0002ø\u0001\u0000¢\u0006\u0004\bg\u0010hR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006i"}, m3961d2 = {"com/pudutech/bumblebee/robot/RobotService$iBinder$1", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface$Stub;", "canParser", "Lcom/pudutech/bumblebee/robot/CANParserManager;", "commonListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/bumblebee/robot/aidl/IRobotListener;", "connectJob", "Lkotlinx/coroutines/Job;", "getConnectJob", "()Lkotlinx/coroutines/Job;", "setConnectJob", "(Lkotlinx/coroutines/Job;)V", "deliveryRobotListener", "Lcom/pudutech/bumblebee/robot/aidl/IDeliveryRobotListener;", "hardware", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "getHardware", "()Lcom/pudutech/base/architecture/AIDLConnection;", "ledScreen", "Lcom/pudutech/bumblebee/robot/led_screen/LEDScreen;", "getLedScreen", "()Lcom/pudutech/bumblebee/robot/led_screen/LEDScreen;", "ledScreen$delegate", "Lkotlin/Lazy;", "loraClient", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaClient;", "loraToVIP", "Lcom/pudutech/bumblebee/robot/lora/LoraToVIP;", "nfc", "Lcom/pudutech/bumblebee/robot/nfc/NFC;", "recycleRobotListener", "Lcom/pudutech/bumblebee/robot/aidl/IRecycleRobotListener;", "remoteDevice", "Lcom/pudutech/bumblebee/robot/remote_device/RemoteDeviceInterface;", "addDeliveryRobotListener", "", "name", "", "listener", "addDisinfectionRobotListener", "Lcom/pudutech/bumblebee/robot/aidl/IDisinfectionRobotListener;", "addListener", "addRecycleRobotListener", "backFlowSprayLiquid", "broadcastToRemoteDevice", NotificationCompat.CATEGORY_MESSAGE, "", "checkRemoteDeviceResponse", "controlLEDFaceScreen", "mode", "Lcom/pudutech/bumblebee/robot/aidl/serialize/LEDFaceScreenMode;", "controlLEDScreen", "Lcom/pudutech/bumblebee/robot/aidl/serialize/LEDScreenMode;", "controlRGB", "led", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SurfaceLED;", "headRGB", "", "endRGB", "time_ms", "flushLoraSerialPortCache", "getDeviceStatus", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;", "initLEDScreen", "noticeToVIP", "p0", "onCreate", "context", "Landroid/content/Context;", "open", "openDevices", "openSprayDevice", "", "open1", "openUvLampDevice", "closeUvLamp", "removeDeliveryRobotListener", "removeDisinfectionRobotListener", "removeListener", "removeRecycleRobotListener", "requestPallets", "setPeripheralDevicePower", "p1", "setSprayPower", "i", "setupLEDScreenContent", "text", TypedValues.Custom.S_COLOR, "setupLEDScreenContentByYCoordinate", "startYCoordinate", "stopNoticeVIP", "update", "obj", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateObject;", "Lcom/pudutech/bumblebee/robot/aidl/IUpdateListener;", "isForces", "sendCAN", "cmd", "Lkotlin/UByteArray;", "sendCAN-tkIK95M", "(Lcom/pudutech/mirsdk/hardware/HardwareInterface;[B)V", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RobotService$iBinder$1 extends RobotInterface.Stub {
    private Job connectJob;
    private LoRaClient loraClient;
    private LoraToVIP loraToVIP;
    private NFC nfc;
    private RemoteDeviceInterface remoteDevice;
    final /* synthetic */ RobotService this$0;
    private ThreadSafeListener<IRobotListener> commonListener = new ThreadSafeListener<>();
    private ThreadSafeListener<IDeliveryRobotListener> deliveryRobotListener = new ThreadSafeListener<>();
    private ThreadSafeListener<IRecycleRobotListener> recycleRobotListener = new ThreadSafeListener<>();
    private final AIDLConnection<HardwareInterface> hardware = new AIDLConnection<>("com.pudutech.mirsdk.hardware.HardwareService", RobotService$iBinder$1$hardware$1.INSTANCE, null, 4, null);

    /* renamed from: ledScreen$delegate, reason: from kotlin metadata */
    private final Lazy ledScreen = LazyKt.lazy(new Function0<LEDScreen>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$ledScreen$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LEDScreen invoke() {
            return new LEDScreen();
        }
    });
    private final CANParserManager canParser = new CANParserManager(this.hardware, this.commonListener, this.deliveryRobotListener);

    public final LEDScreen getLedScreen() {
        return (LEDScreen) this.ledScreen.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotService$iBinder$1(RobotService robotService) {
        this.this$0 = robotService;
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void addListener(String name, IRobotListener listener) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "addListener " + name);
        if (name == null || listener == null) {
            return;
        }
        this.commonListener.add(name, listener);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void removeListener(String name) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "removeDeviceListener");
        if (name != null) {
            this.commonListener.remove(name);
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void addDisinfectionRobotListener(String name, IDisinfectionRobotListener listener) {
        String str = name;
        if ((str == null || str.length() == 0) || listener == null) {
            return;
        }
        DisinfectionModuleManager.INSTANCE.addListener(name, listener);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void addDeliveryRobotListener(String name, IDeliveryRobotListener listener) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "addDeliveryRobotListener name=" + name);
        if (name == null || listener == null) {
            return;
        }
        this.deliveryRobotListener.add(name, listener);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void removeDeliveryRobotListener(String name) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "removeDeliveryRobotListener name=" + name);
        if (name != null) {
            this.deliveryRobotListener.remove(name);
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void addRecycleRobotListener(String name, IRecycleRobotListener listener) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "addRecycleRobotListener name=" + name);
        if (name == null || listener == null) {
            return;
        }
        this.recycleRobotListener.add(name, listener);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void removeRecycleRobotListener(String name) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "removeRecycleRobotListener name=" + name);
        if (name != null) {
            this.recycleRobotListener.remove(name);
        }
    }

    public final AIDLConnection<HardwareInterface> getHardware() {
        return this.hardware;
    }

    private final void initLEDScreen() {
        getLedScreen().setSender(new Function1<UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$initLEDScreen$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UByteArray uByteArray) {
                invoke(uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(byte[] uByteArray) {
                Intrinsics.checkParameterIsNotNull(uByteArray, "uByteArray");
                HardwareInterface hardwareInterface = RobotService$iBinder$1.this.getHardware().getInterface();
                if (hardwareInterface != null) {
                    RobotService$iBinder$1.this.m4301sendCANtkIK95M(hardwareInterface, uByteArray);
                }
            }
        });
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.addCANDataListener("LEDScreen", new byte[]{LEDScreen.INSTANCE.getProtocolHead(), LEDScreen.INSTANCE.getVersionProtocolHead()}, new ICANData.Stub() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$initLEDScreen$2
                @Override // com.pudutech.mirsdk.hardware.ICANData
                public void onData(int p0, byte[] p1) {
                    Function1<UByteArray, Unit> receiveListener = RobotService$iBinder$1.this.getLedScreen().getReceiveListener();
                    if (receiveListener != null) {
                        if (p1 == null) {
                            Intrinsics.throwNpe();
                        }
                        byte[] copyOf = Arrays.copyOf(p1, p1.length);
                        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                        receiveListener.invoke(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(copyOf)));
                    }
                }
            });
        }
    }

    public final Job getConnectJob() {
        return this.connectJob;
    }

    public final void setConnectJob(Job job) {
        this.connectJob = job;
    }

    public final void onCreate(Context context) {
        String str;
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(context, "context");
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "onCreate context=" + context);
        ExecutorCoroutineDispatcher newSingleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("RobotConnectHardware");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, newSingleThreadContext, null, new RobotService$iBinder$1$onCreate$1(this, context, newSingleThreadContext, null), 2, null);
        this.connectJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendCAN-tkIK95M, reason: not valid java name */
    public final void m4301sendCANtkIK95M(HardwareInterface hardwareInterface, byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        hardwareInterface.sendCAN(copyOf);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void requestPallets() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "requestPallets()");
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.sendCAN(PalletProtocol.INSTANCE.getRequestCMD());
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void checkRemoteDeviceResponse() {
        LoRaClient loRaClient = this.loraClient;
        if (loRaClient != null) {
            loRaClient.checkResponse(new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$checkRemoteDeviceResponse$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final int i) {
                    ThreadSafeListener threadSafeListener;
                    threadSafeListener = RobotService$iBinder$1.this.recycleRobotListener;
                    threadSafeListener.notify(new Function2<IRecycleRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$checkRemoteDeviceResponse$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IRecycleRobotListener iRecycleRobotListener, String str) {
                            invoke2(iRecycleRobotListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IRecycleRobotListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.onRemoteDeviceResponseChecking(i);
                        }
                    });
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void controlRGB(SurfaceLED led, int headRGB, int endRGB, int time_ms) {
        String str;
        HardwareInterface hardwareInterface;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("controlRGB ");
        sb.append(led);
        sb.append(" headRGB=");
        sb.append(headRGB);
        sb.append(" endRGB=");
        sb.append(endRGB);
        sb.append(" time_ms=");
        sb.append(time_ms);
        sb.append(' ');
        sb.append(this.hardware.getInterface() == null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        if (led == null || (hardwareInterface = this.hardware.getInterface()) == null) {
            return;
        }
        m4301sendCANtkIK95M(hardwareInterface, new byte[]{-112, led.getValue(), 0, 5, UByte.m4528constructorimpl((byte) (headRGB >>> 16)), UByte.m4528constructorimpl((byte) (headRGB >>> 8)), UByte.m4528constructorimpl((byte) headRGB)});
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void openSprayDevice(boolean open, boolean open1) {
        DisinfectionModuleManager.INSTANCE.getSprayDevice().open(open, open1);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void update(UpdateObject obj, IUpdateListener listener, boolean isForces) {
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        if (hardwareInterface != null) {
            UpdateFontLibHelper.INSTANCE.update(this.this$0, hardwareInterface, listener, isForces);
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void setPeripheralDevicePower(PeripheralDevice p0, boolean p1) {
        String str;
        HardwareInterface hardwareInterface;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "setPeripheralDevicePower p0=" + p0 + "  p1=" + p1);
        PalletProtocol palletProtocol = PalletProtocol.INSTANCE;
        if (p0 == null) {
            Intrinsics.throwNpe();
        }
        if (!palletProtocol.checkIsPallet(p0) || (hardwareInterface = this.hardware.getInterface()) == null) {
            return;
        }
        m4301sendCANtkIK95M(hardwareInterface, PalletProtocol.INSTANCE.powerCMD(p0, p1));
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void controlLEDScreen(LEDScreenMode mode) {
        LEDScreen ledScreen = getLedScreen();
        if (mode == null) {
            Intrinsics.throwNpe();
        }
        ledScreen.showAnimation(mode);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void flushLoraSerialPortCache() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "flushLoraSerialPortCache ");
        RemoteDeviceInterface remoteDeviceInterface = this.remoteDevice;
        if (remoteDeviceInterface != null) {
            remoteDeviceInterface.flush();
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void setupLEDScreenContent(String text, int color) {
        setupLEDScreenContentByYCoordinate(text, color, 8);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void setupLEDScreenContentByYCoordinate(String text, int color, int startYCoordinate) {
        String str;
        String str2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "setupLEDScreenContent() text[" + text + "], color[" + color + "], startYCoordinate[" + startYCoordinate + ']');
        String str3 = text;
        if (str3 == null || str3.length() == 0) {
            str2 = this.this$0.TAG;
            Pdlog.m3277w(str2, "setupLEDScreenContentByYCoordinate() text is null or empty.");
        } else {
            getLedScreen().showText(text, color, startYCoordinate);
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void removeDisinfectionRobotListener(String name) {
        if (name != null) {
            DisinfectionModuleManager.INSTANCE.removeListener(name);
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void broadcastToRemoteDevice(byte[] msg) {
        String str;
        RemoteDeviceInterface remoteDeviceInterface;
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "broadcastToRemoteDeivce msg=" + msg);
        if (msg == null || (remoteDeviceInterface = this.remoteDevice) == null) {
            return;
        }
        remoteDeviceInterface.send(msg);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void openUvLampDevice(boolean open, boolean closeUvLamp) {
        DisinfectionModuleManager.INSTANCE.getUvLampDevice().open(open, closeUvLamp);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void noticeToVIP(String p0) {
        LoraToVIP loraToVIP = this.loraToVIP;
        if (loraToVIP != null) {
            loraToVIP.noticeVIP(p0);
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void backFlowSprayLiquid() {
        DisinfectionModuleManager.INSTANCE.getSprayDevice().drain();
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void controlLEDFaceScreen(LEDFaceScreenMode mode) {
        String str;
        HardwareInterface hardwareInterface;
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "controlLEDFaceScreen mode=" + mode);
        if (mode == null || (hardwareInterface = this.hardware.getInterface()) == null) {
            return;
        }
        byte[] copyOf = Arrays.copyOf(new byte[]{-110, mode.getValue(), 0, 0, 0, 0, 0}, 7);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        hardwareInterface.sendCAN(copyOf);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void stopNoticeVIP() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "stopNoticeVIP ");
        LoraToVIP loraToVIP = this.loraToVIP;
        if (loraToVIP != null) {
            loraToVIP.stopNoticeVIP();
        }
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void open() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "open ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotService$iBinder$1$open$1(this, null), 3, null);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public void setSprayPower(int i) {
        DisinfectionModuleManager.INSTANCE.getSprayDevice().powerControl(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openDevices() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        MachineInfo machineInfo;
        MachineInfo machineInfo2;
        MachineInfo machineInfo3;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("interface = ");
        sb.append(this.hardware.getInterface());
        sb.append(", machineInfo = ");
        HardwareInterface hardwareInterface = this.hardware.getInterface();
        sb.append(hardwareInterface != null ? hardwareInterface.getMachineInfo() : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        HardwareInterface hardwareInterface2 = this.hardware.getInterface();
        ProductMachineType productType = (hardwareInterface2 == null || (machineInfo3 = hardwareInterface2.getMachineInfo()) == null) ? null : machineInfo3.getProductType();
        HardwareInterface hardwareInterface3 = this.hardware.getInterface();
        MachineInfo.LoraType loraType = (hardwareInterface3 == null || (machineInfo2 = hardwareInterface3.getMachineInfo()) == null) ? null : machineInfo2.getLoraType();
        HardwareInterface hardwareInterface4 = this.hardware.getInterface();
        MachineInfo.ScanCodeType scanCodeDeviceType = (hardwareInterface4 == null || (machineInfo = hardwareInterface4.getMachineInfo()) == null) ? null : machineInfo.getScanCodeDeviceType();
        str2 = this.this$0.TAG;
        Pdlog.m3275i(str2, "open product type=" + productType + " loraType=" + loraType + " qrScannerType=" + scanCodeDeviceType);
        if ((productType != null ? productType.getModel() : null) == MachineModel.BellaBot && scanCodeDeviceType == MachineInfo.ScanCodeType.Default) {
            str6 = this.this$0.TAG;
            Pdlog.m3275i(str6, "init mark scanner");
            MarkScanner markScanner = new MarkScanner();
            markScanner.getMarkListener().add("robot", new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str7) {
                    invoke2(str7);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final String mark) {
                    ThreadSafeListener threadSafeListener;
                    Intrinsics.checkParameterIsNotNull(mark, "mark");
                    threadSafeListener = RobotService$iBinder$1.this.deliveryRobotListener;
                    threadSafeListener.notify(new Function2<IDeliveryRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IDeliveryRobotListener iDeliveryRobotListener, String str7) {
                            invoke2(iDeliveryRobotListener, str7);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IDeliveryRobotListener it, String str7) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                            it.onQRScanMsg(mark);
                        }
                    });
                }
            });
            markScanner.initScanner(this.this$0);
        }
        if ((productType != null ? productType.getModel() : null) == MachineModel.Hls) {
            if (scanCodeDeviceType == MachineInfo.ScanCodeType.Default) {
                str5 = this.this$0.TAG;
                Pdlog.m3275i(str5, "init mark scanner");
                MarkScanner markScanner2 = new MarkScanner();
                markScanner2.getMarkListener().add("robot", new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str7) {
                        invoke2(str7);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final String mark) {
                        ThreadSafeListener threadSafeListener;
                        Intrinsics.checkParameterIsNotNull(mark, "mark");
                        threadSafeListener = RobotService$iBinder$1.this.deliveryRobotListener;
                        threadSafeListener.notify(new Function2<IDeliveryRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IDeliveryRobotListener iDeliveryRobotListener, String str7) {
                                invoke2(iDeliveryRobotListener, str7);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IDeliveryRobotListener it, String str7) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                it.onQRScanMsg(mark);
                            }
                        });
                    }
                });
                markScanner2.initScanner(this.this$0);
            }
            if (loraType != MachineInfo.LoraType.NoDevice) {
                if (this.loraToVIP == null) {
                    this.loraToVIP = new LoraToVIP(this.this$0);
                }
                str4 = this.this$0.TAG;
                Pdlog.m3275i(str4, "open lora");
                LoraToVIP loraToVIP = this.loraToVIP;
                if (loraToVIP != null) {
                    loraToVIP.openLora(LoraWatch.JXYL);
                }
            }
        }
        if ((productType != null ? productType.getModel() : null) == MachineModel.RecycleDog) {
            str3 = this.this$0.TAG;
            Pdlog.m3275i(str3, "open the recycle dog special peripherals");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotService$iBinder$1$openDevices$3(this, loraType, null), 3, null);
        }
        if ((productType != null ? productType.getModel() : null) == MachineModel.Ninetales) {
            DisinfectionModuleManager.INSTANCE.init(this.hardware.getInterface());
            DisinfectionModuleManager.INSTANCE.boot();
        }
        initLEDScreen();
    }

    @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
    public PeripheralDeviceStatus getDeviceStatus(PeripheralDevice device) {
        RemoteDeviceInterface remoteDeviceInterface;
        return (device != PeripheralDevice.LORA || (remoteDeviceInterface = this.remoteDevice) == null) ? PeripheralDeviceStatus.FAULT : remoteDeviceInterface.getStatus();
    }
}
