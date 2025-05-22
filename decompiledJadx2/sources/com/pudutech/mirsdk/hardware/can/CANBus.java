package com.pudutech.mirsdk.hardware.can;

import android.content.Context;
import android.net.LocalSocket;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.presenter.robot_open_task.config.MqttConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.mirsdk.hardware.Encoder;
import com.pudutech.mirsdk.hardware.Gyroscope;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.IAccelerationData;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.cloud.BatteryHealth;
import com.pudutech.mirsdk.hardware.serialize.AccelerationType;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareBoard;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.TickerChannelsKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0007\u0018\u00002\u00020\u0001:\u0004\u008c\u0001\u008d\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0011\u0010`\u001a\u00020aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010bJ\u0006\u0010c\u001a\u00020dJ\u0006\u0010e\u001a\u00020dJ\u0011\u0010f\u001a\u000203H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010bJ\u000e\u0010g\u001a\u00020d2\u0006\u0010h\u001a\u000203J\u000e\u0010i\u001a\u00020d2\u0006\u0010j\u001a\u000203J\u001e\u0010k\u001a\u00020d2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020m2\u0006\u0010o\u001a\u000203J\u000e\u0010p\u001a\u00020d2\u0006\u0010q\u001a\u00020rJ\u0011\u0010s\u001a\u000203H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010bJ\u0010\u0010t\u001a\u00020d2\u0006\u0010u\u001a\u00020\tH\u0002J\u0010\u0010v\u001a\u00020d2\u0006\u0010w\u001a\u000203H\u0002J\b\u0010x\u001a\u00020dH\u0002J$\u0010y\u001a\u00020d2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020;0.2\f\u0010{\u001a\b\u0012\u0004\u0012\u00020;0.H\u0002J#\u0010|\u001a\u00020d2\u0006\u0010}\u001a\u0002092\u0006\u0010~\u001a\u00020\u001fH\u0002ø\u0001\u0000¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u001c\u0010\u0081\u0001\u001a\u00020a2\u0007\u0010\u0082\u0001\u001a\u000203H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u0083\u0001J\u0007\u0010\u0084\u0001\u001a\u00020dJ\u001b\u0010\u0085\u0001\u001a\u00020d2\u0006\u0010~\u001a\u00020\u001fø\u0001\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0018\u0010\u0088\u0001\u001a\u00020d2\u0006\u0010q\u001a\u00020r2\u0007\u0010\u0089\u0001\u001a\u00020mJ\u0007\u0010\u008a\u0001\u001a\u00020dJ\u0012\u0010\u008b\u0001\u001a\u000203H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010bR\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\rR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020;0.X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020=X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+R\u000e\u0010C\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010E\u001a\u000203X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001a\u0010J\u001a\u000203X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010G\"\u0004\bL\u0010IR\u0016\u0010M\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010O0NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010R\u001a\b\u0012\u0004\u0012\u00020;0.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010O0NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\u0005¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\rR\u0010\u0010W\u001a\u0004\u0018\u00010XX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Y\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010[\u001a\u0014\u0012\u0004\u0012\u000209\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0.0\\X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010]\u001a\u001a\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u000209\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0.0^X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010_\u001a\u001a\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u000209\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0.0^X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u008e\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/can/CANBus;", "", "appContext", "Landroid/content/Context;", "hardwareListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IHardware;", "(Landroid/content/Context;Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "TAG", "", "accelerationDataProvider", "Lcom/pudutech/mirsdk/hardware/IAccelerationData;", "getAccelerationDataProvider", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "getAppContext", "()Landroid/content/Context;", "canListener", "Lcom/pudutech/mirsdk/hardware/can/CANBus$CANListener;", "getCanListener", "setCanListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "canParserManager", "Lcom/pudutech/mirsdk/hardware/can/CANParserManager;", "getCanParserManager", "()Lcom/pudutech/mirsdk/hardware/can/CANParserManager;", "checkJobFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "controlWheelJob", "Lkotlinx/coroutines/Job;", "dataQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lkotlin/UByteArray;", "encoder", "Lcom/pudutech/mirsdk/hardware/Encoder;", "getEncoder", "()Lcom/pudutech/mirsdk/hardware/Encoder;", "gyroscope", "Lcom/pudutech/mirsdk/hardware/Gyroscope;", "getGyroscope", "()Lcom/pudutech/mirsdk/hardware/Gyroscope;", "hardwareBoards", "", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareBoard;", "[Lcom/pudutech/mirsdk/hardware/serialize/HardwareBoard;", "getHardwareListener", "hardwareVersions", "", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareVersion;", "getHardwareVersions", "()Ljava/util/List;", "isConnect", "", "lastChargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "lastMotorNormalUpdateTimer", "", "lastPowerPecent", "", "leftWheelLastError", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "getMachineType", "()Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "setMachineType", "(Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;)V", "markerBoards", "productType", "receivedCAN", "receivedEncoder", "getReceivedEncoder", "()Z", "setReceivedEncoder", "(Z)V", "receivedIMU", "getReceivedIMU", "setReceivedIMU", "recvThread", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/lang/Thread;", "reportBatteryHealth", "Lcom/pudutech/mirsdk/hardware/cloud/BatteryHealth;", "rightWheelLastError", "sendThread", "sensorDataProvider", "Lcom/pudutech/mirsdk/hardware/ISensorData;", "getSensorDataProvider", "socketInputStream", "Ljava/io/InputStream;", "socketOutputStream", "Ljava/io/OutputStream;", "wheelFlagToList42", "Lkotlin/Function1;", "wheelFlagToList43", "Lkotlin/Function2;", "wheelFlagToList47", "bootUp", "Lcom/pudutech/mirsdk/hardware/can/CANBus$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearWheelError", "", "closeHeadUsb", "connectToCanSocketService", "controlCameraIRDLED", "lightOn", "controlDisinfectionPower", "powerOn", "controlWheel", "linearSpeed", "", "angularSpeed", "isCloseLoop", "getAccelerationData", "type", "Lcom/pudutech/mirsdk/hardware/serialize/AccelerationType;", "getHardwareVersion", "killUnusedExe", "exe", "notifyChangeStateChargingPile", "state", "notifyCharging", "notifyWheelError", "leftError", "rightError", "onRecv", "id", "bytes", "onRecv-eUNIVaU", "(I[B)V", "openOrCloseHeadUsb", DebugKt.DEBUG_PROPERTY_VALUE_ON, "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "regProtocol", MqttServiceConstants.SEND_ACTION, "send-GBYM_sE", "([B)V", "setAccelerationData", "data", "suspendUsingCharingPile", "wasInIAPMode", "CANListener", "Result", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CANBus {
    private final String TAG;
    private final ThreadSafeListener<IAccelerationData> accelerationDataProvider;
    private final Context appContext;
    private ThreadSafeListener<CANListener> canListener;
    private final CANParserManager canParserManager;
    private AtomicBoolean checkJobFlag;
    private Job controlWheelJob;
    private final ArrayBlockingQueue<UByteArray> dataQueue;
    private final Encoder encoder;
    private final Gyroscope gyroscope;
    private final HardwareBoard[] hardwareBoards;
    private final ThreadSafeListener<IHardware> hardwareListener;
    private final List<HardwareVersion> hardwareVersions;
    private boolean isConnect;
    private ChargeState lastChargeState;
    private long lastMotorNormalUpdateTimer;
    private int lastPowerPecent;
    private List<WheelError> leftWheelLastError;
    private ProductMachineType machineType;
    private final HardwareBoard[] markerBoards;
    private ProductMachineType productType;
    private volatile boolean receivedCAN;
    private volatile boolean receivedEncoder;
    private volatile boolean receivedIMU;
    private AtomicReference<Thread> recvThread;
    private BatteryHealth reportBatteryHealth;
    private List<WheelError> rightWheelLastError;
    private AtomicReference<Thread> sendThread;
    private final ThreadSafeListener<ISensorData> sensorDataProvider;
    private InputStream socketInputStream;
    private OutputStream socketOutputStream;
    private final Function1<Integer, List<WheelError>> wheelFlagToList42;
    private final Function2<Integer, Integer, List<WheelError>> wheelFlagToList43;
    private final Function2<Integer, Integer, List<WheelError>> wheelFlagToList47;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MachineModel.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MachineModel.Firefox.ordinal()] = 1;
        }
    }

    public CANBus(Context appContext, ThreadSafeListener<IHardware> hardwareListener) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(hardwareListener, "hardwareListener");
        this.appContext = appContext;
        this.hardwareListener = hardwareListener;
        this.TAG = "CAN";
        this.sensorDataProvider = new ThreadSafeListener<>();
        this.accelerationDataProvider = new ThreadSafeListener<>();
        this.canParserManager = new CANParserManager();
        this.dataQueue = new ArrayBlockingQueue<>(10);
        this.sendThread = new AtomicReference<>(null);
        this.recvThread = new AtomicReference<>(null);
        this.hardwareBoards = new HardwareBoard[]{HardwareBoard.Main, HardwareBoard.CAN2USB};
        this.markerBoards = new HardwareBoard[]{HardwareBoard.Main, HardwareBoard.MarkerMCU, HardwareBoard.Extern3399};
        this.hardwareVersions = new ArrayList();
        this.encoder = new Encoder();
        this.gyroscope = new Gyroscope();
        this.lastChargeState = ChargeState.Idle;
        this.lastPowerPecent = 100;
        this.leftWheelLastError = new ArrayList();
        this.rightWheelLastError = new ArrayList();
        this.reportBatteryHealth = new BatteryHealth(this.hardwareListener);
        ProductMachineType productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
        this.productType = productMachineType;
        this.machineType = productMachineType;
        this.canListener = new ThreadSafeListener<>();
        this.checkJobFlag = new AtomicBoolean(false);
        this.wheelFlagToList42 = new Function1<Integer, List<WheelError>>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$wheelFlagToList42$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ List<WheelError> invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final List<WheelError> invoke(int i) {
                ArrayList arrayList = new ArrayList();
                int i2 = i & 1;
                if (i2 != 0) {
                    arrayList.add(WheelError.SchOver);
                }
                if (i2 != 0) {
                    arrayList.add(WheelError.Stall);
                }
                if ((i & 4) != 0) {
                    arrayList.add(WheelError.SpeedOverOld);
                }
                if ((i & 8) != 0) {
                    arrayList.add(WheelError.MotorFly);
                }
                if ((i & 16) != 0) {
                    arrayList.add(WheelError.SpdFollowErr);
                }
                if ((i & 32) != 0) {
                    arrayList.add(WheelError.SpdLose);
                }
                if ((i & 64) != 0) {
                    arrayList.add(WheelError.JY01Close);
                }
                if ((i & 128) != 0) {
                    arrayList.add(WheelError.Preseve);
                }
                return arrayList;
            }
        };
        this.wheelFlagToList43 = new Function2<Integer, Integer, List<WheelError>>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$wheelFlagToList43$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ List<WheelError> invoke(Integer num, Integer num2) {
                return invoke(num.intValue(), num2.intValue());
            }

            public final List<WheelError> invoke(int i, int i2) {
                ArrayList arrayList = new ArrayList();
                if ((i2 & 1) != 0) {
                    arrayList.add(WheelError.HardwareCurrentOver);
                }
                if ((i2 & 2) != 0) {
                    arrayList.add(WheelError.MotherCurrentOver);
                }
                if ((i2 & 4) != 0) {
                    arrayList.add(WheelError.MotherVoltageOver);
                }
                if ((i2 & 8) != 0) {
                    arrayList.add(WheelError.MotherVoltageLow);
                }
                if ((i2 & 16) != 0) {
                    arrayList.add(WheelError.TemperatureOver);
                }
                if ((i2 & 32) != 0) {
                    arrayList.add(WheelError.EncoderError);
                }
                if ((i2 & 64) != 0) {
                    arrayList.add(WheelError.ABZBreak);
                }
                if ((i2 & 128) != 0) {
                    arrayList.add(WheelError.HallError);
                }
                if ((i & 1) != 0) {
                    arrayList.add(WheelError.SpeedOver);
                }
                if ((i & 2) != 0) {
                    arrayList.add(WheelError.MotorStuck);
                }
                if ((i & 4) != 0) {
                    arrayList.add(WheelError.MotorOver);
                }
                if ((i & 8) != 0) {
                    arrayList.add(WheelError.SpeedFlowDeviation);
                }
                if ((i & 16) != 0) {
                    arrayList.add(WheelError.CANCmdLose);
                }
                if ((i & 32) != 0) {
                    arrayList.add(WheelError.CANBreak);
                }
                if ((i & 128) != 0) {
                    arrayList.add(WheelError.InternalError);
                }
                return arrayList;
            }
        };
        this.wheelFlagToList47 = new Function2<Integer, Integer, List<WheelError>>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$wheelFlagToList47$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ List<WheelError> invoke(Integer num, Integer num2) {
                return invoke(num.intValue(), num2.intValue());
            }

            public final List<WheelError> invoke(int i, int i2) {
                ArrayList arrayList = new ArrayList();
                if ((i2 & 1) != 0) {
                    arrayList.add(WheelError.CurrentZeroDriftError);
                }
                if ((i2 & 2) != 0) {
                    arrayList.add(WheelError.TaskLoadOver);
                }
                if ((i2 & 4) != 0) {
                    arrayList.add(WheelError.EepromError);
                }
                if ((i2 & 8) != 0) {
                    arrayList.add(WheelError.OutLosePhase);
                }
                if ((i2 & 16) != 0) {
                    arrayList.add(WheelError.PhaseCurOver);
                }
                if ((i2 & 32) != 0) {
                    arrayList.add(WheelError.MotorTempOver);
                }
                if ((i2 & 64) != 0) {
                    arrayList.add(WheelError.MosTempOver);
                }
                if ((i2 & 128) != 0) {
                    arrayList.add(WheelError.MosSoftStartError);
                }
                if ((i & 1) != 0) {
                    arrayList.add(WheelError.BumpSwitchReset);
                }
                if ((i & 128) != 0) {
                    arrayList.add(WheelError.EmergencyKeyPressed);
                }
                return arrayList;
            }
        };
    }

    public final Context getAppContext() {
        return this.appContext;
    }

    public final ThreadSafeListener<IHardware> getHardwareListener() {
        return this.hardwareListener;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: CANBus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/can/CANBus$Result;", "", "isSuccess", "", "description", "", "(ZLjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final /* data */ class Result {
        private final String description;
        private final boolean isSuccess;

        public static /* synthetic */ Result copy$default(Result result, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = result.isSuccess;
            }
            if ((i & 2) != 0) {
                str = result.description;
            }
            return result.copy(z, str);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSuccess() {
            return this.isSuccess;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final Result copy(boolean isSuccess, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            return new Result(isSuccess, description);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.isSuccess == result.isSuccess && Intrinsics.areEqual(this.description, result.description);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.description;
            return i + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(isSuccess=" + this.isSuccess + ", description=" + this.description + ")";
        }

        public Result(boolean z, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.isSuccess = z;
            this.description = description;
        }

        public final String getDescription() {
            return this.description;
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }
    }

    public final ThreadSafeListener<ISensorData> getSensorDataProvider() {
        return this.sensorDataProvider;
    }

    public final ThreadSafeListener<IAccelerationData> getAccelerationDataProvider() {
        return this.accelerationDataProvider;
    }

    public final CANParserManager getCanParserManager() {
        return this.canParserManager;
    }

    public final List<HardwareVersion> getHardwareVersions() {
        return this.hardwareVersions;
    }

    public final Encoder getEncoder() {
        return this.encoder;
    }

    public final Gyroscope getGyroscope() {
        return this.gyroscope;
    }

    public final ProductMachineType getMachineType() {
        return this.machineType;
    }

    public final void setMachineType(ProductMachineType productMachineType) {
        Intrinsics.checkParameterIsNotNull(productMachineType, "<set-?>");
        this.machineType = productMachineType;
    }

    public final boolean getReceivedIMU() {
        return this.receivedIMU;
    }

    public final void setReceivedIMU(boolean z) {
        this.receivedIMU = z;
    }

    public final boolean getReceivedEncoder() {
        return this.receivedEncoder;
    }

    public final void setReceivedEncoder(boolean z) {
        this.receivedEncoder = z;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: CANBus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/can/CANBus$CANListener;", "", "listen_id", "", "listener", "Lcom/pudutech/mirsdk/hardware/ICANData;", "([BLcom/pudutech/mirsdk/hardware/ICANData;)V", "getListen_id", "()[B", "getListener", "()Lcom/pudutech/mirsdk/hardware/ICANData;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class CANListener {
        private final byte[] listen_id;
        private final ICANData listener;

        public CANListener(byte[] listen_id, ICANData listener) {
            Intrinsics.checkParameterIsNotNull(listen_id, "listen_id");
            Intrinsics.checkParameterIsNotNull(listener, "listener");
            this.listen_id = listen_id;
            this.listener = listener;
        }

        public final byte[] getListen_id() {
            return this.listen_id;
        }

        public final ICANData getListener() {
            return this.listener;
        }
    }

    public final ThreadSafeListener<CANListener> getCanListener() {
        return this.canListener;
    }

    public final void setCanListener(ThreadSafeListener<CANListener> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        this.canListener = threadSafeListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onRecv-eUNIVaU, reason: not valid java name */
    public final void m4424onRecveUNIVaU(final int id, byte[] bytes) {
        Pdlog.m3273d(this.TAG, "recv:" + UByte.m4563toStringimpl(UByte.m4528constructorimpl((byte) id)) + ' ' + CommonKt.m4421toHexStringGBYM_sE(bytes));
        this.canParserManager.m4427parseeUNIVaU(id, bytes);
        final byte[] copyOf = Arrays.copyOf(bytes, bytes.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        this.canListener.notify(new Function2<CANListener, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$onRecv$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CANBus.CANListener cANListener, String str) {
                invoke2(cANListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CANBus.CANListener l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                if (ArraysKt.contains(l.getListen_id(), (byte) id)) {
                    l.getListener().onData(id, copyOf);
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0105 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Type inference failed for: r10v4, types: [T, kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r10v7, types: [T, kotlinx.coroutines.channels.ReceiveChannel] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object bootUp(Continuation<? super Result> continuation) {
        CANBus$bootUp$1 cANBus$bootUp$1;
        Object obj;
        int i;
        final CANBus cANBus;
        CANBus$bootUp$1 cANBus$bootUp$12;
        CANBus cANBus2;
        int i2;
        Ref.ObjectRef objectRef;
        Boolean bool;
        if (continuation instanceof CANBus$bootUp$1) {
            cANBus$bootUp$1 = (CANBus$bootUp$1) continuation;
            if ((cANBus$bootUp$1.label & Integer.MIN_VALUE) != 0) {
                cANBus$bootUp$1.label -= Integer.MIN_VALUE;
                obj = cANBus$bootUp$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cANBus$bootUp$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    cANBus$bootUp$1.L$0 = this;
                    cANBus$bootUp$1.label = 1;
                    obj = connectToCanSocketService(cANBus$bootUp$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    cANBus = this;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                objectRef = (Ref.ObjectRef) cANBus$bootUp$1.L$1;
                                int i3 = cANBus$bootUp$1.I$0;
                                cANBus2 = (CANBus) cANBus$bootUp$1.L$0;
                                ResultKt.throwOnFailure(obj);
                                bool = (Boolean) obj;
                                String str = "wait can receive timeout";
                                if (bool != null) {
                                    Pdlog.m3274e(cANBus2.TAG, "wait can receive timeout");
                                    ReceiveChannel receiveChannel = (ReceiveChannel) objectRef.element;
                                    if (receiveChannel != null) {
                                        ReceiveChannel.DefaultImpls.cancel$default(receiveChannel, (CancellationException) null, 1, (Object) null);
                                    }
                                } else {
                                    str = "";
                                }
                                return new Result(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)), str);
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        i2 = cANBus$bootUp$1.I$0;
                        CANBus cANBus3 = (CANBus) cANBus$bootUp$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        cANBus$bootUp$12 = cANBus$bootUp$1;
                        cANBus2 = cANBus3;
                        do {
                            i2--;
                            if (i2 <= 0) {
                                cANBus2.m4425sendGBYM_sE(new byte[]{22, 2, 4, 1, 0, 0, -23});
                                cANBus2.m4425sendGBYM_sE(new byte[]{22, 4, 4, 1, 0, 0, -23});
                                cANBus2.m4425sendGBYM_sE(new byte[]{22, 6, 4, 1, 0, 0, -23});
                                cANBus2.m4425sendGBYM_sE(new byte[]{22, -1, -31, 4, 0, 0, -23});
                                cANBus$bootUp$12.L$0 = cANBus2;
                                cANBus$bootUp$12.I$0 = i2;
                                cANBus$bootUp$12.label = 2;
                            } else {
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                objectRef2.element = (ReceiveChannel) 0;
                                if (Build.VERSION.SDK_INT < 26) {
                                    objectRef2.element = TickerChannelsKt.ticker$default(1000L, 0L, null, null, 12, null);
                                    BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new CANBus$bootUp$4(cANBus2, objectRef2, null), 3, null);
                                }
                                CANBus$bootUp$bootUpResult$1 cANBus$bootUp$bootUpResult$1 = new CANBus$bootUp$bootUpResult$1(cANBus2, null);
                                cANBus$bootUp$12.L$0 = cANBus2;
                                cANBus$bootUp$12.I$0 = i2;
                                cANBus$bootUp$12.L$1 = objectRef2;
                                cANBus$bootUp$12.label = 3;
                                obj = TimeoutKt.withTimeoutOrNull(MqttConfig.POINT_IS_EMPTY, cANBus$bootUp$bootUpResult$1, cANBus$bootUp$12);
                                if (obj == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                objectRef = objectRef2;
                                bool = (Boolean) obj;
                                String str2 = "wait can receive timeout";
                                if (bool != null) {
                                }
                                return new Result(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)), str2);
                            }
                        } while (DelayKt.delay(200L, cANBus$bootUp$12) != coroutine_suspended);
                        return coroutine_suspended;
                    }
                    cANBus = (CANBus) cANBus$bootUp$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (((Boolean) obj).booleanValue()) {
                    return new Result(false, "connection to can_service failed");
                }
                if (cANBus.sendThread.get() == null) {
                    cANBus.sendThread.set(ThreadsKt.thread$default(false, false, null, "SendCAN", 0, new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$bootUp$2
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
                            String str3;
                            String str4;
                            AtomicReference atomicReference;
                            String str5;
                            ArrayBlockingQueue arrayBlockingQueue;
                            String str6;
                            OutputStream outputStream;
                            while (true) {
                                try {
                                    arrayBlockingQueue = CANBus.this.dataQueue;
                                    Object take = arrayBlockingQueue.take();
                                    Intrinsics.checkExpressionValueIsNotNull(take, "dataQueue.take()");
                                    byte[] storage = ((UByteArray) take).getStorage();
                                    byte[] copyOf = Arrays.copyOf(storage, storage.length);
                                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                                    str6 = CANBus.this.TAG;
                                    Pdlog.m3273d(str6, "output to can service " + CommonKt.toHexString(copyOf));
                                    outputStream = CANBus.this.socketOutputStream;
                                    if (outputStream != null) {
                                        outputStream.write(copyOf);
                                    }
                                } catch (IOException e) {
                                    str5 = CANBus.this.TAG;
                                    Pdlog.m3274e(str5, "send exception:", Log.getStackTraceString(e));
                                    atomicReference = CANBus.this.sendThread;
                                    atomicReference.set(null);
                                    return;
                                } catch (InterruptedException e2) {
                                    str4 = CANBus.this.TAG;
                                    Pdlog.m3274e(str4, "send exception:", Log.getStackTraceString(e2));
                                    atomicReference = CANBus.this.sendThread;
                                    atomicReference.set(null);
                                    return;
                                } catch (Exception e3) {
                                    str3 = CANBus.this.TAG;
                                    Pdlog.m3274e(str3, "send exception:", Log.getStackTraceString(e3));
                                    atomicReference = CANBus.this.sendThread;
                                    atomicReference.set(null);
                                    return;
                                }
                            }
                        }
                    }, 23, null));
                }
                cANBus.receivedCAN = false;
                cANBus.receivedIMU = false;
                cANBus.receivedEncoder = false;
                if (cANBus.recvThread.get() == null) {
                    final byte[] bArr = new byte[8];
                    cANBus.recvThread.set(ThreadsKt.thread$default(false, false, null, "RecvCAN", 0, new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$bootUp$3
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
                            String str3;
                            AtomicReference atomicReference;
                            InputStream inputStream;
                            String str4;
                            while (true) {
                                try {
                                    inputStream = CANBus.this.socketInputStream;
                                    Integer valueOf = inputStream != null ? Integer.valueOf(inputStream.read(bArr, 0, 8)) : null;
                                    if (valueOf != null && valueOf.intValue() == 8) {
                                        CANBus.this.receivedCAN = true;
                                        CANBus cANBus4 = CANBus.this;
                                        byte b = bArr[0];
                                        byte[] bArr2 = bArr;
                                        byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length);
                                        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                                        cANBus4.m4424onRecveUNIVaU(b, UByteArray.m4572constructorimpl(copyOf));
                                    }
                                    str4 = CANBus.this.TAG;
                                    Pdlog.m3274e(str4, "recv CAN size error:" + valueOf);
                                } catch (Exception e) {
                                    str3 = CANBus.this.TAG;
                                    Pdlog.m3274e(str3, "recv exception:", Log.getStackTraceString(e));
                                    atomicReference = CANBus.this.recvThread;
                                    atomicReference.set(null);
                                    return;
                                }
                            }
                        }
                    }, 23, null));
                }
                cANBus$bootUp$12 = cANBus$bootUp$1;
                cANBus2 = cANBus;
                i2 = 3;
                do {
                    i2--;
                    if (i2 <= 0) {
                    }
                } while (DelayKt.delay(200L, cANBus$bootUp$12) != coroutine_suspended);
                return coroutine_suspended;
            }
        }
        cANBus$bootUp$1 = new CANBus$bootUp$1(this, continuation);
        obj = cANBus$bootUp$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cANBus$bootUp$1.label;
        if (i != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    public final void regProtocol() {
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 115, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.lastPowerPecent = UByteArray.m4577getimpl(bytes, 3) & 255;
                CANBus.this.lastChargeState = ChargeState.INSTANCE.m4433fromValue7apg3OU(UByteArray.m4577getimpl(bytes, 1));
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$1.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str) {
                        int i2;
                        ChargeState chargeState;
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        i2 = CANBus.this.lastPowerPecent;
                        chargeState = CANBus.this.lastChargeState;
                        l.onBattery(i2, chargeState);
                    }
                });
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 9, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.lastPowerPecent = UByteArray.m4577getimpl(bytes, 1) & 255;
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$2.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str) {
                        int i2;
                        ChargeState chargeState;
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        i2 = CANBus.this.lastPowerPecent;
                        chargeState = CANBus.this.lastChargeState;
                        l.onBattery(i2, chargeState);
                    }
                });
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 13, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                if ((UByteArray.m4577getimpl(bytes, 1) & 255) == 1) {
                    CANBus.this.lastChargeState = ChargeState.Charging;
                } else {
                    CANBus.this.lastChargeState = ChargeState.Idle;
                }
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$3.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str) {
                        int i2;
                        ChargeState chargeState;
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        i2 = CANBus.this.lastPowerPecent;
                        chargeState = CANBus.this.lastChargeState;
                        l.onBattery(i2, chargeState);
                    }
                });
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 1, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Job job;
                AtomicBoolean atomicBoolean;
                AtomicBoolean atomicBoolean2;
                String str;
                AtomicBoolean atomicBoolean3;
                String str2;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.getEncoder().update((short) ((((UByteArray.m4577getimpl(bytes, 1) & 255) & 255) << 8) | (UByteArray.m4577getimpl(bytes, 2) & 255)), (short) ((UByteArray.m4577getimpl(bytes, 4) & 255) | (((UByteArray.m4577getimpl(bytes, 3) & 255) & 255) << 8)));
                CANBus.this.setReceivedEncoder(true);
                CANBus.this.getSensorDataProvider().notify(new Function2<ISensorData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$4.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISensorData iSensorData, String str3) {
                        invoke2(iSensorData, str3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISensorData it, String str3) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                        it.onEncoder(CANBus.this.getEncoder().get_distance().getLeft(), CANBus.this.getEncoder().get_distance().getRight(), 0.025d);
                        it.onSpeed(CANBus.this.getEncoder().get_speed().getLineSpeed(), CANBus.this.getEncoder().get_speed().getAngularSpeed(), 0.025d);
                    }
                });
                job = CANBus.this.controlWheelJob;
                if (job == null || !job.isActive()) {
                    return;
                }
                atomicBoolean = CANBus.this.checkJobFlag;
                if (atomicBoolean.get()) {
                    return;
                }
                if (Math.abs(CANBus.this.getEncoder().get_speed().getLineSpeed()) > 0.15d || Math.abs(CANBus.this.getEncoder().get_speed().getAngularSpeed()) > 0.25d) {
                    atomicBoolean2 = CANBus.this.checkJobFlag;
                    atomicBoolean2.set(true);
                    str = CANBus.this.TAG;
                    Pdlog.m3273d(str, "check to cancel wheel encoder.speed.lineSpeed=" + CANBus.this.getEncoder().get_speed().getLineSpeed() + ",encoder.speed.angularSpeed=" + CANBus.this.getEncoder().get_speed().getAngularSpeed());
                    BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C51862(null), 3, null);
                    atomicBoolean3 = CANBus.this.checkJobFlag;
                    atomicBoolean3.set(false);
                    str2 = CANBus.this.TAG;
                    Pdlog.m3273d(str2, "check to cancel wheel end");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Classes with same name are omitted:
              classes.dex
              classes4.dex
             */
            /* compiled from: CANBus.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$4$2", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {280}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$4$2 */
            /* loaded from: classes2.dex */
            public static final class C51862 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6044p$;

                C51862(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C51862 c51862 = new C51862(completion);
                    c51862.f6044p$ = (CoroutineScope) obj;
                    return c51862;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C51862) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    Job job;
                    String str2;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f6044p$;
                        str = CANBus.this.TAG;
                        Pdlog.m3273d(str, "cancel wheel start");
                        CANBus.this.notifyChangeStateChargingPile(false);
                        job = CANBus.this.controlWheelJob;
                        if (job != null) {
                            this.L$0 = coroutineScope;
                            this.label = 1;
                            if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    CANBus.this.controlWheel(0.0d, 0.0d, false);
                    str2 = CANBus.this.TAG;
                    Pdlog.m3273d(str2, "cancel wheel end");
                    return Unit.INSTANCE;
                }
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 16, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.getGyroscope().update((short) ((((UByteArray.m4577getimpl(bytes, 1) & 255) & 255) << 8) | (UByteArray.m4577getimpl(bytes, 2) & 255)), (short) ((((UByteArray.m4577getimpl(bytes, 3) & 255) & 255) << 8) | (UByteArray.m4577getimpl(bytes, 4) & 255)), (short) ((UByteArray.m4577getimpl(bytes, 6) & 255) | (((UByteArray.m4577getimpl(bytes, 5) & 255) & 255) << 8)));
                CANBus.this.setReceivedIMU(true);
                CANBus.this.getSensorDataProvider().notify(new Function2<ISensorData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$5.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISensorData iSensorData, String str) {
                        invoke2(iSensorData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISensorData it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onIMU(CANBus.this.getGyroscope().get_last().getX(), CANBus.this.getGyroscope().get_last().getY(), CANBus.this.getGyroscope().get_last().getZ(), 0.025d);
                    }
                });
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 114, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, final byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$6.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                        invoke2(iHardware, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onCameraIRDLED((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                    }
                });
                str = CANBus.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("isCameraIRDLEDLightOn ");
                sb.append((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 66, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Function1 function1;
                Function1 function12;
                String str;
                long j;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                function1 = CANBus.this.wheelFlagToList42;
                final List list = (List) function1.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 1) & 255));
                function12 = CANBus.this.wheelFlagToList42;
                final List list2 = (List) function12.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 2) & 255));
                if (!list.isEmpty() || !list2.isEmpty()) {
                    CANBus.this.lastMotorNormalUpdateTimer = 0L;
                    CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$7.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                            invoke2(iHardware, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str2) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            Object[] array = list.toArray(new WheelError[0]);
                            if (array == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            WheelError[] wheelErrorArr = (WheelError[]) array;
                            Object[] array2 = list2.toArray(new WheelError[0]);
                            if (array2 != null) {
                                l.onWheelError(wheelErrorArr, (WheelError[]) array2);
                                return;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    });
                    str = CANBus.this.TAG;
                    Pdlog.m3273d(str, "onWheelError " + list + ' ' + list2);
                    return;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                j = CANBus.this.lastMotorNormalUpdateTimer;
                if (elapsedRealtime - j > 1000) {
                    CANBus.this.lastMotorNormalUpdateTimer = elapsedRealtime;
                    CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$7.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                            invoke2(iHardware, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str2) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            Object[] array = list.toArray(new WheelError[0]);
                            if (array == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            WheelError[] wheelErrorArr = (WheelError[]) array;
                            Object[] array2 = list2.toArray(new WheelError[0]);
                            if (array2 != null) {
                                l.onWheelError(wheelErrorArr, (WheelError[]) array2);
                                return;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    });
                }
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 67, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Function2 function2;
                Function2 function22;
                List list;
                List list2;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                function2 = CANBus.this.wheelFlagToList43;
                List list3 = (List) function2.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 1) & 255), Integer.valueOf(UByteArray.m4577getimpl(bytes, 2) & 255));
                function22 = CANBus.this.wheelFlagToList43;
                List list4 = (List) function22.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 3) & 255), Integer.valueOf(UByteArray.m4577getimpl(bytes, 4) & 255));
                List list5 = list3;
                if (!list5.isEmpty()) {
                    list2 = CANBus.this.leftWheelLastError;
                    list2.addAll(list5);
                }
                List list6 = list4;
                if (!list6.isEmpty()) {
                    list = CANBus.this.rightWheelLastError;
                    list.addAll(list6);
                }
                if (CANBus.this.getMachineType().getModel() == MachineModel.BellaBot && CANBus.this.getMachineType().getMainVersion() == 0 && CANBus.this.getMachineType().getMinorVersion() == 1) {
                    CANBus.this.notifyWheelError(list3, list4);
                }
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) 71, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Function2 function2;
                Function2 function22;
                List list;
                List list2;
                List list3;
                List list4;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                function2 = CANBus.this.wheelFlagToList47;
                List list5 = (List) function2.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 1) & 255), Integer.valueOf(UByteArray.m4577getimpl(bytes, 2) & 255));
                function22 = CANBus.this.wheelFlagToList47;
                List list6 = (List) function22.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 3) & 255), Integer.valueOf(UByteArray.m4577getimpl(bytes, 4) & 255));
                list = CANBus.this.leftWheelLastError;
                if (!list.isEmpty()) {
                    list4 = CANBus.this.leftWheelLastError;
                    list5.addAll(list4);
                }
                list2 = CANBus.this.rightWheelLastError;
                if (true ^ list2.isEmpty()) {
                    list3 = CANBus.this.rightWheelLastError;
                    list6.addAll(list3);
                }
                CANBus.this.notifyWheelError(list5, list6);
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU(PrinterUtils.BarCode.CODE128, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                final AccelerationType accelerationType;
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                int m4577getimpl = UByteArray.m4577getimpl(bytes, 1) & 255;
                if (m4577getimpl == 1) {
                    accelerationType = AccelerationType.Acceleration;
                } else if (m4577getimpl == 2) {
                    accelerationType = AccelerationType.Deceleration;
                } else if (m4577getimpl == 3) {
                    accelerationType = AccelerationType.EmergencyStopDeceleration;
                } else {
                    accelerationType = AccelerationType.Unknow;
                }
                final double m4577getimpl2 = (((((UByteArray.m4577getimpl(bytes, 2) & 255) << 24) + ((UByteArray.m4577getimpl(bytes, 3) & 255) << 16)) + ((UByteArray.m4577getimpl(bytes, 4) & 255) << 8)) + (UByteArray.m4577getimpl(bytes, 5) & 255)) / 10000;
                str = CANBus.this.TAG;
                Pdlog.m3273d(str, "onAcceleration " + accelerationType + ", " + m4577getimpl2);
                CANBus.this.getAccelerationDataProvider().notify(new Function2<IAccelerationData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$10.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IAccelerationData iAccelerationData, String str2) {
                        invoke2(iAccelerationData, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IAccelerationData l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onData(AccelerationType.this, m4577getimpl2);
                    }
                });
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) -126, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                BatteryHealth batteryHealth;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                batteryHealth = CANBus.this.reportBatteryHealth;
                batteryHealth.m4432reportGBYM_sE(UByteArray.m4572constructorimpl(ArraysKt.copyOfRange(bytes, 1, 6)));
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU(Constans.CAN_REV_DIS_MODE_SWITCH, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, final byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$12.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                        invoke2(iHardware, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onDisinfectionPower((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                    }
                });
                str = CANBus.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("onDisinfectionPower ");
                sb.append((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) -104, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, final byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$13.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                        invoke2(iHardware, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onAntiCollisionSwitch((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                    }
                });
                str = CANBus.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("onAntiCollisionSwitch ");
                sb.append((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) -93, new CANBus$regProtocol$14(this));
        this.canParserManager.m4428regCanParsereLRuwBU((byte) -122, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, final byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$15.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                        invoke2(iHardware, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onSlamCorePower((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                    }
                });
                str = CANBus.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("onSlamCorePower ");
                sb.append((UByteArray.m4577getimpl(bytes, 1) & 255) == 1);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU(DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$16
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, final byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$16.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                        invoke2(iHardware, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onBatterySOH(UByteArray.m4577getimpl(bytes, 3) & 255);
                    }
                });
                str = CANBus.this.TAG;
                Pdlog.m3273d(str, "onBatterySOH " + (UByteArray.m4577getimpl(bytes, 3) & 255));
            }
        });
        this.canParserManager.m4428regCanParsereLRuwBU((byte) -77, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$17
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.String] */
            public final void invoke(int i, byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                byte b = (byte) 0;
                if (UByteArray.m4577getimpl(bytes, 1) == UByte.m4528constructorimpl(b) && UByteArray.m4577getimpl(bytes, 2) == UByte.m4528constructorimpl(b) && UByteArray.m4577getimpl(bytes, 3) == UByte.m4528constructorimpl(b) && UByteArray.m4577getimpl(bytes, 4) == UByte.m4528constructorimpl(b) && UByteArray.m4577getimpl(bytes, 5) == UByte.m4528constructorimpl(b) && UByteArray.m4577getimpl(bytes, 6) == UByte.m4528constructorimpl(b)) {
                    return;
                }
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = BatteryFaultConvert.INSTANCE.m4422getBatteryFaultMsgGBYM_sE(bytes);
                CANBus.this.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$17.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                        invoke2(iHardware, str2);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware it, String str2) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it.onHarewareInfoReport((String) Ref.ObjectRef.this.element);
                    }
                });
                str = CANBus.this.TAG;
                Pdlog.m3273d(str, "battery has occur fault " + ((String) objectRef.element));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyCharging() {
        Pdlog.m3273d(this.TAG, "chargepile connected send cmd to control wheel");
        for (int i = 1; i <= 5; i++) {
            controlWheel(0.0d, 0.0d, true);
        }
        notifyChangeStateChargingPile(true);
        Pdlog.m3273d(this.TAG, "chargepile connected send cmd to control wheel end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyWheelError(final List<WheelError> leftError, final List<WheelError> rightError) {
        this.leftWheelLastError.clear();
        this.rightWheelLastError.clear();
        if (leftError.isEmpty() && rightError.isEmpty()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.lastMotorNormalUpdateTimer > 1000) {
                this.lastMotorNormalUpdateTimer = elapsedRealtime;
                this.hardwareListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$notifyWheelError$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        Object[] array = leftError.toArray(new WheelError[0]);
                        if (array == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        WheelError[] wheelErrorArr = (WheelError[]) array;
                        Object[] array2 = rightError.toArray(new WheelError[0]);
                        if (array2 != null) {
                            l.onWheelError(wheelErrorArr, (WheelError[]) array2);
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                });
                return;
            }
            return;
        }
        this.lastMotorNormalUpdateTimer = 0L;
        this.hardwareListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$notifyWheelError$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                invoke2(iHardware, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                Object[] array = leftError.toArray(new WheelError[0]);
                if (array == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                WheelError[] wheelErrorArr = (WheelError[]) array;
                Object[] array2 = rightError.toArray(new WheelError[0]);
                if (array2 != null) {
                    l.onWheelError(wheelErrorArr, (WheelError[]) array2);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        Pdlog.m3273d(this.TAG, "onWheelError " + leftError + ' ' + rightError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyChangeStateChargingPile(boolean state) {
        if (state) {
            m4425sendGBYM_sE(new byte[]{-93, 0, 1, 0, 0, 0, 0});
        } else {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new CANBus$notifyChangeStateChargingPile$1(this, null), 3, null);
        }
    }

    public final void suspendUsingCharingPile() {
        Job job = this.controlWheelJob;
        if (job == null || !job.isActive()) {
            return;
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new CANBus$suspendUsingCharingPile$1(this, null), 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object getHardwareVersion(Continuation<? super Boolean> continuation) {
        CANBus$getHardwareVersion$1 cANBus$getHardwareVersion$1;
        int i;
        CANBus cANBus;
        Boolean bool;
        if (continuation instanceof CANBus$getHardwareVersion$1) {
            cANBus$getHardwareVersion$1 = (CANBus$getHardwareVersion$1) continuation;
            if ((cANBus$getHardwareVersion$1.label & Integer.MIN_VALUE) != 0) {
                cANBus$getHardwareVersion$1.label -= Integer.MIN_VALUE;
                Object obj = cANBus$getHardwareVersion$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cANBus$getHardwareVersion$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    this.canParserManager.m4428regCanParsereLRuwBU((byte) 19, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$getHardwareVersion$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                            invoke(num.intValue(), uByteArray.getStorage());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2, byte[] data) {
                            Object obj2;
                            String str;
                            Intrinsics.checkParameterIsNotNull(data, "data");
                            int m4577getimpl = UByteArray.m4577getimpl(data, 6) & 255;
                            synchronized (CANBus.this.getHardwareVersions()) {
                                Iterator<T> it = CANBus.this.getHardwareVersions().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        obj2 = null;
                                        break;
                                    } else {
                                        obj2 = it.next();
                                        if (((HardwareVersion) obj2).getBoard().getId() == m4577getimpl) {
                                            break;
                                        }
                                    }
                                }
                                HardwareVersion hardwareVersion = (HardwareVersion) obj2;
                                if (hardwareVersion != null) {
                                    CANBus.this.getHardwareVersions().remove(hardwareVersion);
                                }
                                HardwareVersion hardwareVersion2 = new HardwareVersion(HardwareBoard.INSTANCE.fromId(m4577getimpl), UByteArray.m4577getimpl(data, 1) & 255, UByteArray.m4577getimpl(data, 2) & 255, UByteArray.m4577getimpl(data, 3) & 255);
                                str = CANBus.this.TAG;
                                Pdlog.m3275i(str, "get hardware version hv=" + hardwareVersion2.getBoard() + ' ');
                                CANBus.this.getHardwareVersions().add(hardwareVersion2);
                            }
                        }
                    });
                    CANBus$getHardwareVersion$result$1 cANBus$getHardwareVersion$result$1 = new CANBus$getHardwareVersion$result$1(this, null);
                    cANBus$getHardwareVersion$1.L$0 = this;
                    cANBus$getHardwareVersion$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(10000L, cANBus$getHardwareVersion$result$1, cANBus$getHardwareVersion$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    cANBus = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    cANBus = (CANBus) cANBus$getHardwareVersion$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                bool = (Boolean) obj;
                cANBus.canParserManager.m4429unRegCanParser7apg3OU((byte) 19);
                if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                    Pdlog.m3275i(cANBus.TAG, "get hardware version success");
                } else {
                    Pdlog.m3274e(cANBus.TAG, "get hardware version fail");
                }
                return Boxing.boxBoolean(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)));
            }
        }
        cANBus$getHardwareVersion$1 = new CANBus$getHardwareVersion$1(this, continuation);
        Object obj2 = cANBus$getHardwareVersion$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cANBus$getHardwareVersion$1.label;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        cANBus.canParserManager.m4429unRegCanParser7apg3OU((byte) 19);
        if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
        }
        return Boxing.boxBoolean(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v14, types: [byte[], T] */
    /* renamed from: send-GBYM_sE, reason: not valid java name */
    public final void m4425sendGBYM_sE(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (UByteArray.m4580isEmptyimpl(bytes)) {
            Pdlog.m3273d(this.TAG, "send empty data");
            return;
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        int m4578getSizeimpl = UByteArray.m4578getSizeimpl(bytes);
        T t = bytes;
        if (m4578getSizeimpl < 7) {
            int m4578getSizeimpl2 = 7 - UByteArray.m4578getSizeimpl(bytes);
            byte[] bArr = new byte[m4578getSizeimpl2];
            for (int i = 0; i < m4578getSizeimpl2; i++) {
                bArr[i] = 0;
            }
            t = UByteArray.m4572constructorimpl(ArraysKt.plus(bytes, UByteArray.m4572constructorimpl(bArr)));
        }
        objectRef.element = t;
        if (UByteArray.m4578getSizeimpl((byte[]) objectRef.element) == 7) {
            objectRef.element = UByteArray.m4572constructorimpl(ArraysKt.plus((byte[]) objectRef.element, CommonKt.m4420canChecksumGBYM_sE((byte[]) objectRef.element)));
        }
        if (UByteArray.m4578getSizeimpl((byte[]) objectRef.element) == 8) {
            BuildersKt__BuildersKt.runBlocking$default(null, new CANBus$send$1(this, objectRef, null), 1, null);
            return;
        }
        Pdlog.m3273d(this.TAG, "send invalid data size:" + UByteArray.m4578getSizeimpl((byte[]) objectRef.element));
    }

    public final void controlCameraIRDLED(boolean lightOn) {
        m4425sendGBYM_sE(new byte[]{113, lightOn ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0});
    }

    public final void controlWheel(double linearSpeed, double angularSpeed, boolean isCloseLoop) {
        double d = 2;
        double d2 = linearSpeed * d;
        double d3 = 10000;
        int wheelSpacing = (int) (((d2 - (this.encoder.getWheelSpacing() * angularSpeed)) / d) * d3);
        int wheelSpacing2 = (int) (((d2 + (this.encoder.getWheelSpacing() * angularSpeed)) / d) * d3);
        byte m4528constructorimpl = isCloseLoop ? UByte.m4528constructorimpl((byte) 64) : (byte) 0;
        if (linearSpeed == 0.0d && angularSpeed == 0.0d && isCloseLoop) {
            m4528constructorimpl = UByte.m4528constructorimpl((byte) (m4528constructorimpl | Byte.MIN_VALUE));
        }
        m4425sendGBYM_sE(new byte[]{64, UByte.m4528constructorimpl((byte) ((wheelSpacing & 65280) >> 8)), UByte.m4528constructorimpl((byte) (wheelSpacing & 255)), UByte.m4528constructorimpl((byte) ((wheelSpacing2 & 65280) >> 8)), UByte.m4528constructorimpl((byte) (wheelSpacing2 & 255)), m4528constructorimpl, 0});
    }

    public final void getAccelerationData(AccelerationType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        m4425sendGBYM_sE(new byte[]{0, PrinterUtils.BarCode.CODE128, type.getValue(), 0, 0, 0, 0});
    }

    public final void setAccelerationData(AccelerationType type, double data) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        byte[] bytes = ByteBuffer.allocate(4).putInt((int) (data * 10000)).array();
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setAccelerationData ");
        Intrinsics.checkExpressionValueIsNotNull(bytes, "bytes");
        sb.append(CommonKt.toHexString(bytes));
        Pdlog.m3273d(str, sb.toString());
        m4425sendGBYM_sE(new byte[]{PrinterUtils.BarCode.CODE93, type.getValue(), UByte.m4528constructorimpl(bytes[0]), UByte.m4528constructorimpl(bytes[1]), UByte.m4528constructorimpl(bytes[2]), UByte.m4528constructorimpl(bytes[3]), 0});
    }

    public final void clearWheelError() {
        m4425sendGBYM_sE(new byte[]{65, 1});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object connectToCanSocketService(Continuation<? super Boolean> continuation) {
        CANBus$connectToCanSocketService$1 cANBus$connectToCanSocketService$1;
        Object obj;
        int i;
        CANBus cANBus;
        if (continuation instanceof CANBus$connectToCanSocketService$1) {
            cANBus$connectToCanSocketService$1 = (CANBus$connectToCanSocketService$1) continuation;
            if ((cANBus$connectToCanSocketService$1.label & Integer.MIN_VALUE) != 0) {
                cANBus$connectToCanSocketService$1.label -= Integer.MIN_VALUE;
                CANBus$connectToCanSocketService$1 cANBus$connectToCanSocketService$12 = cANBus$connectToCanSocketService$1;
                obj = cANBus$connectToCanSocketService$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cANBus$connectToCanSocketService$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = Build.VERSION.SDK_INT > 22;
                    Pdlog.m3275i(this.TAG, "release can_service from assets, use_can_service_usb:" + z);
                    File filesDir = this.appContext.getFilesDir();
                    Intrinsics.checkExpressionValueIsNotNull(filesDir, "appContext.filesDir");
                    String absolutePath = filesDir.getAbsolutePath();
                    try {
                        InputStream open = this.appContext.getAssets().open(z ? "can_service_usb" : "can_service_socket");
                        File file = new File(absolutePath + "/can_service");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = open.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        open.close();
                    } catch (FileNotFoundException unused) {
                        Pdlog.m3275i(this.TAG, "can_service can not be overwrite, maybe busy");
                    } catch (IOException e) {
                        Pdlog.m3274e(this.TAG, "put can_service fail", Log.getStackTraceString(e));
                    }
                    Pdlog.m3275i(this.TAG, "release can_service success");
                    killUnusedExe("can_service");
                    Tools.execCommand("chmod 777 " + absolutePath + "/can_service", z);
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    Tools.execCommand(absolutePath + "/can_service " + elapsedRealtime, z);
                    Pdlog.m3275i(this.TAG, "connecting can_service");
                    LocalSocket localSocket = new LocalSocket();
                    CANBus$connectToCanSocketService$2 cANBus$connectToCanSocketService$2 = new CANBus$connectToCanSocketService$2(this, localSocket, z, elapsedRealtime, null);
                    cANBus$connectToCanSocketService$12.L$0 = this;
                    cANBus$connectToCanSocketService$12.L$1 = absolutePath;
                    cANBus$connectToCanSocketService$12.J$0 = elapsedRealtime;
                    cANBus$connectToCanSocketService$12.L$2 = localSocket;
                    cANBus$connectToCanSocketService$12.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(5000L, cANBus$connectToCanSocketService$2, cANBus$connectToCanSocketService$12);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    cANBus = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    long j = cANBus$connectToCanSocketService$12.J$0;
                    cANBus = (CANBus) cANBus$connectToCanSocketService$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
                    return Boxing.boxBoolean(true);
                }
                Pdlog.m3274e(cANBus.TAG, "connect CAN service timeout");
                cANBus.isConnect = false;
                return Boxing.boxBoolean(false);
            }
        }
        cANBus$connectToCanSocketService$1 = new CANBus$connectToCanSocketService$1(this, continuation);
        CANBus$connectToCanSocketService$1 cANBus$connectToCanSocketService$122 = cANBus$connectToCanSocketService$1;
        obj = cANBus$connectToCanSocketService$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cANBus$connectToCanSocketService$122.label;
        if (i != 0) {
        }
        if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
        }
    }

    private final void killUnusedExe(String exe) {
        int i = 0;
        Pair<Integer, String> execCommand = Tools.execCommand("ps -A | grep " + exe, false);
        String str = this.TAG;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%d - %s", Arrays.copyOf(new Object[]{execCommand.first, execCommand.second}, 2));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        Pdlog.m3275i(str, "kill", exe, format);
        Integer num = (Integer) execCommand.first;
        if (num != null && num.intValue() == 0) {
            Object obj = execCommand.second;
            Intrinsics.checkExpressionValueIsNotNull(obj, "res.second");
            String str2 = null;
            if (StringsKt.contains$default((CharSequence) obj, (CharSequence) exe, false, 2, (Object) null)) {
                Object obj2 = execCommand.second;
                Intrinsics.checkExpressionValueIsNotNull(obj2, "res.second");
                Object[] array = StringsKt.split$default((CharSequence) obj2, new String[]{"\n"}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array != null) {
                    String[] strArr = (String[]) array;
                    int length = strArr.length;
                    int i2 = 0;
                    while (i2 < length) {
                        Object[] array2 = StringsKt.split$default((CharSequence) strArr[i2], new String[]{"\\s"}, false, 0, 6, (Object) null).toArray(new String[i]);
                        if (array2 != null) {
                            String[] strArr2 = (String[]) array2;
                            String str3 = this.TAG;
                            Object[] objArr = new Object[1];
                            objArr[i] = "fields: " + Arrays.toString(strArr2);
                            Pdlog.m3275i(str3, objArr);
                            if (strArr2.length > 1) {
                                String str4 = str2;
                                for (int i3 = 1; i3 < strArr2.length && str4 == null; i3++) {
                                    String str5 = strArr2[i3];
                                    int length2 = str5.length() - 1;
                                    int i4 = 0;
                                    boolean z = false;
                                    while (i4 <= length2) {
                                        boolean z2 = str5.charAt(!z ? i4 : length2) <= ' ';
                                        if (z) {
                                            if (!z2) {
                                                break;
                                            } else {
                                                length2--;
                                            }
                                        } else if (z2) {
                                            i4++;
                                        } else {
                                            z = true;
                                        }
                                    }
                                    if (!TextUtils.isEmpty(str5.subSequence(i4, length2 + 1).toString())) {
                                        str4 = strArr2[i3];
                                    }
                                }
                                if (str4 == null) {
                                    try {
                                        Intrinsics.throwNpe();
                                    } catch (Exception e) {
                                        Pdlog.m3274e(this.TAG, "parse pid failed", e);
                                    }
                                }
                                Tools.execCommand("kill " + Integer.parseInt(str4), true);
                            }
                            i2++;
                            i = 0;
                            str2 = null;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object wasInIAPMode(Continuation<? super Boolean> continuation) {
        CANBus$wasInIAPMode$1 cANBus$wasInIAPMode$1;
        int i;
        CANBus cANBus;
        Boolean bool;
        if (continuation instanceof CANBus$wasInIAPMode$1) {
            cANBus$wasInIAPMode$1 = (CANBus$wasInIAPMode$1) continuation;
            if ((cANBus$wasInIAPMode$1.label & Integer.MIN_VALUE) != 0) {
                cANBus$wasInIAPMode$1.label -= Integer.MIN_VALUE;
                CANBus$wasInIAPMode$1 cANBus$wasInIAPMode$12 = cANBus$wasInIAPMode$1;
                Object obj = cANBus$wasInIAPMode$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cANBus$wasInIAPMode$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    booleanRef.element = false;
                    byte b = (byte) 2;
                    final List mutableListOf = CollectionsKt.mutableListOf(UByte.m4522boximpl(UByte.m4528constructorimpl(b)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 4)));
                    final List mutableListOf2 = CollectionsKt.mutableListOf(UByte.m4522boximpl(UByte.m4528constructorimpl(b)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 6)));
                    this.canParserManager.m4428regCanParsereLRuwBU((byte) 22, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$wasInIAPMode$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                            invoke(num.intValue(), uByteArray.getStorage());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2, byte[] data) {
                            String str;
                            Intrinsics.checkParameterIsNotNull(data, "data");
                            int m4577getimpl = UByteArray.m4577getimpl(data, 2) & 255;
                            int m4577getimpl2 = UByteArray.m4577getimpl(data, 3) & 255;
                            if (m4577getimpl == 2 && m4577getimpl2 == 1) {
                                str = CANBus.this.TAG;
                                Pdlog.m3275i(str, "targetId " + UByte.m4563toStringimpl(UByteArray.m4577getimpl(data, 1)) + " was in IAP mode");
                                booleanRef.element = true;
                            }
                            mutableListOf.remove(UByte.m4522boximpl(UByteArray.m4577getimpl(data, 1)));
                            mutableListOf2.remove(UByte.m4522boximpl(UByteArray.m4577getimpl(data, 1)));
                        }
                    });
                    CANBus$wasInIAPMode$result$1 cANBus$wasInIAPMode$result$1 = new CANBus$wasInIAPMode$result$1(this, mutableListOf, mutableListOf2, booleanRef, null);
                    cANBus$wasInIAPMode$12.L$0 = this;
                    cANBus$wasInIAPMode$12.L$1 = booleanRef;
                    cANBus$wasInIAPMode$12.L$2 = mutableListOf;
                    cANBus$wasInIAPMode$12.L$3 = mutableListOf2;
                    cANBus$wasInIAPMode$12.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(1000L, cANBus$wasInIAPMode$result$1, cANBus$wasInIAPMode$12);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    cANBus = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    cANBus = (CANBus) cANBus$wasInIAPMode$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                bool = (Boolean) obj;
                cANBus.canParserManager.m4429unRegCanParser7apg3OU((byte) 22);
                if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                    Pdlog.m3275i(cANBus.TAG, "was in IAP mode");
                } else {
                    Pdlog.m3275i(cANBus.TAG, "was not in IAP mode");
                }
                return Boxing.boxBoolean(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)));
            }
        }
        cANBus$wasInIAPMode$1 = new CANBus$wasInIAPMode$1(this, continuation);
        CANBus$wasInIAPMode$1 cANBus$wasInIAPMode$122 = cANBus$wasInIAPMode$1;
        Object obj2 = cANBus$wasInIAPMode$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cANBus$wasInIAPMode$122.label;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        cANBus.canParserManager.m4429unRegCanParser7apg3OU((byte) 22);
        if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
        }
        return Boxing.boxBoolean(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)));
    }

    public final void controlDisinfectionPower(boolean powerOn) {
        m4425sendGBYM_sE(new byte[]{-125, powerOn ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0});
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object openOrCloseHeadUsb(boolean z, Continuation<? super Result> continuation) {
        CANBus$openOrCloseHeadUsb$1 cANBus$openOrCloseHeadUsb$1;
        int i;
        CANBus cANBus;
        Boolean bool;
        String str;
        if (continuation instanceof CANBus$openOrCloseHeadUsb$1) {
            cANBus$openOrCloseHeadUsb$1 = (CANBus$openOrCloseHeadUsb$1) continuation;
            if ((cANBus$openOrCloseHeadUsb$1.label & Integer.MIN_VALUE) != 0) {
                cANBus$openOrCloseHeadUsb$1.label -= Integer.MIN_VALUE;
                Object obj = cANBus$openOrCloseHeadUsb$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cANBus$openOrCloseHeadUsb$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    booleanRef.element = !z;
                    this.canParserManager.m4428regCanParsereLRuwBU((byte) -32, new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$openOrCloseHeadUsb$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                            invoke(num.intValue(), uByteArray.getStorage());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i2, byte[] data) {
                            String str2;
                            Intrinsics.checkParameterIsNotNull(data, "data");
                            str2 = CANBus.this.TAG;
                            Pdlog.m3273d(str2, "openOrCloseHeadUsb:  " + CommonKt.m4421toHexStringGBYM_sE(data));
                            booleanRef.element = UByteArray.m4577getimpl(data, 1) == UByte.m4528constructorimpl((byte) 1);
                        }
                    });
                    String str2 = this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("openOrCloseHeadUsb start to ");
                    sb.append(z ? "open" : "close ");
                    sb.append(' ');
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str2, objArr);
                    byte[] bArr = {-32, 0, 0, 0, 0, 0, 0};
                    if (z) {
                        UByteArray.m4582setVurrAj0(bArr, 1, (byte) 1);
                    }
                    m4425sendGBYM_sE(bArr);
                    CANBus$openOrCloseHeadUsb$result$1 cANBus$openOrCloseHeadUsb$result$1 = new CANBus$openOrCloseHeadUsb$result$1(z, booleanRef, null);
                    cANBus$openOrCloseHeadUsb$1.L$0 = this;
                    cANBus$openOrCloseHeadUsb$1.Z$0 = z;
                    cANBus$openOrCloseHeadUsb$1.L$1 = booleanRef;
                    cANBus$openOrCloseHeadUsb$1.L$2 = bArr;
                    cANBus$openOrCloseHeadUsb$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(1000L, cANBus$openOrCloseHeadUsb$result$1, cANBus$openOrCloseHeadUsb$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    cANBus = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    z = cANBus$openOrCloseHeadUsb$1.Z$0;
                    cANBus = (CANBus) cANBus$openOrCloseHeadUsb$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                bool = (Boolean) obj;
                cANBus.canParserManager.m4429unRegCanParser7apg3OU((byte) -32);
                if (bool != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("wait ");
                    sb2.append(z ? "open" : "close ");
                    sb2.append("  head usb  timeout");
                    str = sb2.toString();
                    Pdlog.m3274e(cANBus.TAG, str);
                } else {
                    str = "";
                }
                return new Result(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)), str);
            }
        }
        cANBus$openOrCloseHeadUsb$1 = new CANBus$openOrCloseHeadUsb$1(this, continuation);
        Object obj2 = cANBus$openOrCloseHeadUsb$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cANBus$openOrCloseHeadUsb$1.label;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        cANBus.canParserManager.m4429unRegCanParser7apg3OU((byte) -32);
        if (bool != null) {
        }
        return new Result(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)), str);
    }

    public final void closeHeadUsb() {
        m4425sendGBYM_sE(new byte[]{-32, 1, 0, 0, 0, 0, 0});
    }
}
