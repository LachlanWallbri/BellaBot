package com.pudutech.robot.peripherals.hola;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.recyclebot.robot.nfc.RFIDReader;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.config.LightBeltAnimationFrame;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.hola.IFunctionPanelListener;
import com.pudutech.robot.peripherals.manager.CANConfig;
import com.pudutech.serialport.library.ISerialPortDataReceiveCallback;
import com.pudutech.serialport.library.ISerialPortOpenStatusCallback;
import com.pudutech.serialport.library.SerialPortHelper;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;
import org.simpleframework.xml.strategy.Name;

/* compiled from: HolaBotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 t2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001tB\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0012\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020\u0016H\u0016J\b\u00100\u001a\u00020,H\u0016J\u0010\u00101\u001a\u00020,2\u0006\u00102\u001a\u00020\bH\u0002J\b\u00103\u001a\u00020,H\u0016J\b\u00104\u001a\u00020\rH\u0016J\b\u00105\u001a\u00020$H\u0016J\u0010\u00106\u001a\u000207H\u0016ø\u0001\u0000¢\u0006\u0002\u00108J\u0010\u00109\u001a\u00020,2\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010<\u001a\u00020,H\u0002J$\u0010=\u001a\u00020,2\u0006\u0010>\u001a\u00020?2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010@\u001a\u00020,2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0018\u0010A\u001a\u00020,2\u0006\u0010:\u001a\u00020;2\u0006\u0010B\u001a\u00020\bH\u0016J\u0018\u0010C\u001a\u00020,2\u0006\u0010:\u001a\u00020;2\u0006\u0010B\u001a\u00020\bH\u0016J\b\u0010D\u001a\u00020,H\u0016J!\u0010E\u001a\u00020,2\u0006\u0010F\u001a\u00020\b2\u0006\u0010:\u001a\u00020;H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010GJ)\u0010H\u001a\u00020,2\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020K0J\"\u00020K2\u0006\u0010L\u001a\u00020MH\u0016¢\u0006\u0002\u0010NJ\u0012\u0010O\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010P\u001a\u00020,2\u0006\u0010:\u001a\u00020;H\u0016J&\u0010Q\u001a\u00020,2\u0006\u0010F\u001a\u00020R2\u0006\u0010L\u001a\u00020MH\u0082@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bS\u0010TJ\u0010\u0010U\u001a\u00020,2\u0006\u0010\f\u001a\u00020\rH\u0016JN\u0010V\u001a\u00020,2\u0012\u0010W\u001a\n\u0012\u0006\b\u0001\u0012\u00020K0J\"\u00020K2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020R2\u0006\u0010]\u001a\u00020R2\u0006\u0010^\u001a\u00020RH\u0016ø\u0001\u0000¢\u0006\u0004\b_\u0010`J2\u0010a\u001a\u00020,2\u0006\u0010W\u001a\u00020K2\u0006\u0010b\u001a\u00020R2\u0006\u0010c\u001a\u00020R2\u0006\u0010d\u001a\u00020RH\u0016ø\u0001\u0000¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020,2\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010h\u001a\u00020\u00162\u0006\u00102\u001a\u00020\b2\u0006\u0010i\u001a\u00020jH\u0002J\u0010\u0010k\u001a\u00020,2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010l\u001a\u00020,2\u0006\u0010W\u001a\u00020K2\u0006\u0010m\u001a\u00020\bH\u0002J\u0010\u0010n\u001a\u00020,2\u0006\u0010o\u001a\u00020pH\u0002J0\u0010q\u001a\u00020\b2\b\b\u0002\u0010b\u001a\u00020R2\b\b\u0002\u0010c\u001a\u00020R2\b\b\u0002\u0010d\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\br\u0010sR\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00168V@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006u"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/HolaBotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/hola/IHolaBotPeripherals;", "Lcom/pudutech/serialport/library/ISerialPortOpenStatusCallback;", "Lcom/pudutech/serialport/library/ISerialPortDataReceiveCallback;", "Lcom/pudutech/recyclebot/robot/nfc/RFIDReader$OnUIDListener;", "()V", "BUTTON_DISPASH", "", "FILTER_TIME_MS", "", "SENSOR_DISPASH", "expression", "Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "getExpression", "()Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "setExpression", "(Lcom/pudutech/robot/peripherals/hola/ExpressionType;)V", "functionPanelListeners", "Landroid/util/SparseArray;", "Lcom/pudutech/robot/peripherals/hola/IFunctionPanelListener;", "isLoRaOpened", "", ES6Iterator.VALUE_PROPERTY, "isMute", "()Z", "setMute", "(Z)V", "isOnError", "setOnError", "lastDownTime", "loRaDataReceivedCallback", "Lcom/pudutech/robot/peripherals/hola/ILoRaDataReceivedCallback;", "loRaStatusCallback", "Lcom/pudutech/robot/peripherals/hola/ILoRaStatusCallback;", "loRaType", "Lcom/pudutech/robot/peripherals/hola/LoRaType;", "mainHandler", "Landroid/os/Handler;", "nfcSwipeCardListener", "Lcom/pudutech/robot/peripherals/hola/INFCSwipeCardListener;", "serialPortHelper", "Lcom/pudutech/serialport/library/SerialPortHelper;", "addFunctionPanelListener", "", "listener", "callbackOpenStatus", "opened", "closeLoRa", "exportAndSetupDirectionOut", "gpioNum", "flushLoRa", "getFace", "getLoRaType", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "handleSensingAreaTouchEventData", "data", "", "initChmod", "initLoRa", "context", "Landroid/content/Context;", "initNFC", "onReceive", Name.LENGTH, "onSwipe", "openLoRa", "parseData", "id", "(I[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", C3898x.f4339h, "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;)V", "removeFunctionPanelListener", "sendData", "sendLightCan2", "Lkotlin/UByte;", "sendLightCan2-25hgefk", "(BLcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setFace", "setLightMode", "led", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/hola/LightColor;", "lightMode", "Lcom/pudutech/robot/peripherals/hola/LightMode;", "params2", "params1", "params0", "setLightMode-3UDGHhA", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/hola/LightColor;Lcom/pudutech/robot/peripherals/hola/LightMode;BBB)V", "setLightRGB", "red", "green", "blue", "setLightRGB-Fh2MPcY", "(Lcom/pudutech/robot/peripherals/config/LightBeltType;BBB)V", "setLoRaType", "setupLevel", "level", "Lcom/pudutech/robot/peripherals/hola/LoRaLevel;", "showEyes", "showLight", "headRGB", "switchLevelMode", "mode", "Lcom/pudutech/robot/peripherals/hola/LoRaLevelMode;", "toRGBInt", "toRGBInt-b33U2AM", "(BBB)I", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class HolaBotPeripherals extends CommonRobotPeripherals implements IHolaBotPeripherals, ISerialPortOpenStatusCallback, ISerialPortDataReceiveCallback, RFIDReader.OnUIDListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<HolaBotPeripherals>() { // from class: com.pudutech.robot.peripherals.hola.HolaBotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HolaBotPeripherals invoke() {
            return new HolaBotPeripherals(null);
        }
    });
    private static final String TAG = "HolaBotPeripherals";
    private final int BUTTON_DISPASH;
    private final long FILTER_TIME_MS;
    private final int SENSOR_DISPASH;
    private ExpressionType expression;
    private SparseArray<IFunctionPanelListener> functionPanelListeners;
    private boolean isLoRaOpened;
    private boolean isMute;
    private boolean isOnError;
    private long lastDownTime;
    private ILoRaDataReceivedCallback loRaDataReceivedCallback;
    private ILoRaStatusCallback loRaStatusCallback;
    private LoRaType loRaType;
    private final Handler mainHandler;
    private INFCSwipeCardListener nfcSwipeCardListener;
    private final SerialPortHelper serialPortHelper;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LoRaLevelMode.values().length];

        static {
            $EnumSwitchMapping$0[LoRaLevelMode.DeepSleep.ordinal()] = 1;
            $EnumSwitchMapping$0[LoRaLevelMode.Normal.ordinal()] = 2;
        }
    }

    /* renamed from: toRGBInt-b33U2AM, reason: not valid java name */
    private final int m4495toRGBIntb33U2AM(byte red, byte green, byte blue) {
        return ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
    }

    private HolaBotPeripherals() {
        this.serialPortHelper = SerialPortHelper.INSTANCE.getINSTANCE();
        this.functionPanelListeners = new SparseArray<>();
        this.loRaType = LoRaType.NoDevice;
        this.expression = ExpressionType.EYES_FAST;
        this.FILTER_TIME_MS = 150L;
        this.BUTTON_DISPASH = 101;
        this.SENSOR_DISPASH = 102;
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.robot.peripherals.hola.HolaBotPeripherals$mainHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2;
                SparseArray sparseArray;
                SparseArray sparseArray2;
                SparseArray sparseArray3;
                SparseArray sparseArray4;
                int i3 = message.what;
                i = HolaBotPeripherals.this.BUTTON_DISPASH;
                int i4 = 0;
                if (i3 != i) {
                    i2 = HolaBotPeripherals.this.SENSOR_DISPASH;
                    if (i3 == i2) {
                        if (HolaBotPeripherals.this.isMute() || HolaBotPeripherals.this.isOnError()) {
                            Pdlog.m3275i("HolaBotPeripherals", "onTrigger in INFRARED_SENSOR, but isMute=" + HolaBotPeripherals.this.isMute() + "  isOnError=" + HolaBotPeripherals.this.isOnError());
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("INFRARED_SENSOR listeners ");
                            sparseArray = HolaBotPeripherals.this.functionPanelListeners;
                            sb.append(sparseArray.size());
                            sb.append(' ');
                            Pdlog.m3273d("HolaBotPeripherals", sb.toString());
                            sparseArray2 = HolaBotPeripherals.this.functionPanelListeners;
                            int size = sparseArray2.size();
                            while (i4 < size) {
                                sparseArray2.keyAt(i4);
                                ((IFunctionPanelListener) sparseArray2.valueAt(i4)).onTrigger(IFunctionPanelListener.TriggerType.INFRARED_SENSOR);
                                i4++;
                            }
                        }
                    }
                } else {
                    if (HolaBotPeripherals.this.isMute() || HolaBotPeripherals.this.isOnError()) {
                        Pdlog.m3275i("HolaBotPeripherals", "onTrigger in FUNCTION_BUTTON, but isMute=" + HolaBotPeripherals.this.isMute() + "  isOnError=" + HolaBotPeripherals.this.isOnError());
                        return true;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("FUNCTION_BUTTON listeners ");
                    sparseArray3 = HolaBotPeripherals.this.functionPanelListeners;
                    sb2.append(sparseArray3.size());
                    sb2.append(' ');
                    Pdlog.m3273d("HolaBotPeripherals", sb2.toString());
                    sparseArray4 = HolaBotPeripherals.this.functionPanelListeners;
                    int size2 = sparseArray4.size();
                    while (i4 < size2) {
                        sparseArray4.keyAt(i4);
                        ((IFunctionPanelListener) sparseArray4.valueAt(i4)).onTrigger(IFunctionPanelListener.TriggerType.FUNCTION_BUTTON);
                        i4++;
                    }
                    HolaBotPeripherals.this.lastDownTime = SystemClock.elapsedRealtime() + 500;
                }
                return true;
            }
        });
    }

    public /* synthetic */ HolaBotPeripherals(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: HolaBotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/HolaBotPeripherals$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/robot/peripherals/hola/HolaBotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/hola/HolaBotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final HolaBotPeripherals getINSTANCE() {
            Lazy lazy = HolaBotPeripherals.INSTANCE$delegate;
            Companion companion = HolaBotPeripherals.INSTANCE;
            return (HolaBotPeripherals) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public byte[] getRecvCmds() {
        return new byte[]{CANConfig.INSTANCE.getCAN_CMD_HEAD_94_PROTOCOL()};
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public LoRaType getLoRaType() {
        return this.loRaType;
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void setLoRaType(LoRaType loRaType) {
        Intrinsics.checkParameterIsNotNull(loRaType, "loRaType");
        this.loRaType = loRaType;
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void initLoRa(Context context, ILoRaStatusCallback loRaStatusCallback, ILoRaDataReceivedCallback loRaDataReceivedCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.loRaStatusCallback = loRaStatusCallback;
        this.loRaDataReceivedCallback = loRaDataReceivedCallback;
        this.serialPortHelper.init(context, "0403", "6011", "Quad RS232-HS", "00", 9600, 0, this, this);
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void openLoRa() {
        Pdlog.m3273d(TAG, "openLoRa() isLoRaOpened = " + this.isLoRaOpened);
        if (this.isLoRaOpened) {
            return;
        }
        ILoRaStatusCallback iLoRaStatusCallback = this.loRaStatusCallback;
        if (iLoRaStatusCallback != null) {
            iLoRaStatusCallback.callback(LoRaStatus.Opening);
        }
        this.serialPortHelper.openSerialPort();
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void closeLoRa() {
        Pdlog.m3273d(TAG, "closeLoRa() isLoRaOpened = " + this.isLoRaOpened);
        if (this.isLoRaOpened) {
            this.serialPortHelper.closeSerialPort();
        }
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void flushLoRa() {
        Pdlog.m3273d(TAG, "flushLoRa() isLoRaOpened = " + this.isLoRaOpened);
        if (this.isLoRaOpened) {
            this.serialPortHelper.flush();
        }
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void sendData(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d(TAG, "sendData() isLoRaOpened = " + this.isLoRaOpened + ", data = " + CommonKt.toHexString(data) + ", length = " + data.length);
        if (this.isLoRaOpened) {
            this.serialPortHelper.writeData(data);
        }
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void initNFC(INFCSwipeCardListener nfcSwipeCardListener) {
        this.nfcSwipeCardListener = nfcSwipeCardListener;
        new RFIDReader().open(getContext(), this);
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void addFunctionPanelListener(IFunctionPanelListener listener) {
        if (listener == null) {
            return;
        }
        int hashCode = listener.hashCode();
        if (this.functionPanelListeners.indexOfKey(hashCode) >= 0) {
            this.functionPanelListeners.remove(hashCode);
        }
        this.functionPanelListeners.put(hashCode, listener);
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void removeFunctionPanelListener(IFunctionPanelListener listener) {
        if (listener == null) {
            return;
        }
        int hashCode = listener.hashCode();
        if (this.functionPanelListeners.indexOfKey(hashCode) >= 0) {
            this.functionPanelListeners.remove(hashCode);
        }
    }

    @Override // com.pudutech.serialport.library.ISerialPortOpenStatusCallback
    public void callbackOpenStatus(boolean opened) {
        ILoRaStatusCallback iLoRaStatusCallback;
        Pdlog.m3273d(TAG, "callbackOpenStatus() opened = " + opened);
        this.isLoRaOpened = opened;
        if (!opened) {
            if (opened || (iLoRaStatusCallback = this.loRaStatusCallback) == null) {
                return;
            }
            iLoRaStatusCallback.callback(LoRaStatus.Closed);
            return;
        }
        exportAndSetupDirectionOut(131);
        exportAndSetupDirectionOut(134);
        exportAndSetupDirectionOut(132);
        initChmod();
        switchLevelMode(LoRaLevelMode.Normal);
        ILoRaStatusCallback iLoRaStatusCallback2 = this.loRaStatusCallback;
        if (iLoRaStatusCallback2 != null) {
            iLoRaStatusCallback2.callback(LoRaStatus.Opened);
        }
    }

    @Override // com.pudutech.serialport.library.ISerialPortDataReceiveCallback
    public void onReceive(byte[] data, int length) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        StringBuilder sb = new StringBuilder();
        sb.append("onReceive() data = ");
        String arrays = Arrays.toString(data);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        sb.append(", length = ");
        sb.append(length);
        Pdlog.m3273d(TAG, sb.toString());
        ILoRaDataReceivedCallback iLoRaDataReceivedCallback = this.loRaDataReceivedCallback;
        if (iLoRaDataReceivedCallback != null) {
            iLoRaDataReceivedCallback.onReceivedData(data);
        }
    }

    private final void initChmod() {
        Tools.execCommand("chmod 777 /sys/class/gpio/gpio134/value", true);
        Tools.execCommand("chmod 777 /sys/class/gpio/gpio131/value", true);
        Tools.execCommand("chmod 777 /sys/class/gpio/gpio132/value", true);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void exportAndSetupDirectionOut(int gpioNum) {
        FileWriter fileWriter;
        String str;
        FileWriter fileWriter2 = (FileWriter) null;
        try {
            str = "/sys/class/gpio/gpio" + gpioNum;
            Pdlog.m3273d(TAG, "exportAndSetupDirectionOut() filePath = " + str);
        } catch (Exception e) {
            e = e;
            fileWriter = fileWriter2;
        } catch (Throwable th) {
            th = th;
            fileWriter = fileWriter2;
        }
        if (new File(str).exists()) {
            return;
        }
        String str2 = "chmod 777 /sys/class/gpio/export";
        Pdlog.m3273d(TAG, "exportAndSetupDirectionOut() exportCmd = " + str2);
        Pair<Integer, String> execCommand = Tools.execCommand(str2, true);
        Pdlog.m3273d(TAG, "exportAndSetupDirectionOut() exportResult first = " + ((Integer) execCommand.first) + "\tsecond = " + ((String) execCommand.second));
        StringBuilder sb = new StringBuilder();
        sb.append("/sys/class/gpio/");
        sb.append("export");
        FileWriter fileWriter3 = new FileWriter(new File(sb.toString()));
        try {
            fileWriter3.write(String.valueOf(gpioNum));
            fileWriter3.flush();
            String str3 = "chmod 777 /sys/class/gpio/gpio" + gpioNum + "/direction";
            Pdlog.m3273d(TAG, "exportAndSetupDirectionOut() directionCmd = " + str3);
            Pair<Integer, String> execCommand2 = Tools.execCommand(str3, true);
            Pdlog.m3273d(TAG, "exportAndSetupDirectionOut() directionResult first = " + ((Integer) execCommand2.first) + "\tsecond = " + ((String) execCommand2.second));
            fileWriter = new FileWriter(new File("/sys/class/gpio/gpio" + gpioNum + "/direction"));
            try {
                fileWriter.write("out");
                fileWriter.flush();
                fileWriter3.close();
            } catch (Exception e2) {
                e = e2;
                fileWriter2 = fileWriter3;
                try {
                    e.printStackTrace();
                    if (fileWriter2 != null) {
                        fileWriter2.close();
                    }
                    if (fileWriter == null) {
                        return;
                    }
                    fileWriter.close();
                } catch (Throwable th2) {
                    th = th2;
                    if (fileWriter2 != null) {
                        fileWriter2.close();
                    }
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileWriter2 = fileWriter3;
                if (fileWriter2 != null) {
                }
                if (fileWriter != null) {
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileWriter = fileWriter2;
        } catch (Throwable th4) {
            th = th4;
            fileWriter = fileWriter2;
        }
        fileWriter.close();
    }

    private final void switchLevelMode(LoRaLevelMode mode) {
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            Pdlog.m3273d(TAG, "setup level to low : m1[" + setupLevel(131, LoRaLevel.Low) + "], m0[" + setupLevel(134, LoRaLevel.Low) + "], execTime = " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            return;
        }
        if (i != 2) {
            return;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        Pdlog.m3273d(TAG, "setup level to high : m1[" + setupLevel(131, LoRaLevel.High) + "], m0[" + setupLevel(134, LoRaLevel.High) + "], execTime = " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
    }

    private final boolean setupLevel(int gpioNum, LoRaLevel level) {
        FileWriter fileWriter = (FileWriter) null;
        try {
            try {
                FileWriter fileWriter2 = new FileWriter(new File("/sys/class/gpio/gpio" + gpioNum + "/value"));
                try {
                    fileWriter2.write(level.getValue());
                    fileWriter2.flush();
                    fileWriter2.close();
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override // com.pudutech.recyclebot.robot.nfc.RFIDReader.OnUIDListener
    public void onSwipe(byte[] data, int length) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d(TAG, "NFC onSwipe() data = " + data + ", length = " + length);
        if (length < 4) {
            Pdlog.m3277w(TAG, "illegal length");
            return;
        }
        long j = (data[3] & 255) | ((data[0] & 255) << 24) | ((data[1] & 255) << 16) | ((data[2] & 255) << 8);
        INFCSwipeCardListener iNFCSwipeCardListener = this.nfcSwipeCardListener;
        if (iNFCSwipeCardListener != null) {
            iNFCSwipeCardListener.onSwipe(String.valueOf(j));
        }
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public Object parseData(int i, byte[] bArr, Continuation<? super Unit> continuation) {
        if (UByte.m4528constructorimpl((byte) i) == CANConfig.INSTANCE.getCAN_CMD_HEAD_94_PROTOCOL()) {
            handleSensingAreaTouchEventData(bArr);
        }
        return Unit.INSTANCE;
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    /* renamed from: setLightRGB-Fh2MPcY, reason: not valid java name */
    public void mo4499setLightRGBFh2MPcY(LightBeltType led, byte red, byte green, byte blue) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        showLight(led, m4495toRGBIntb33U2AM(red, green, blue));
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void setFace(ExpressionType expression) {
        Intrinsics.checkParameterIsNotNull(expression, "expression");
        this.expression = expression;
        showEyes(expression);
    }

    public final ExpressionType getExpression() {
        return this.expression;
    }

    public final void setExpression(ExpressionType expressionType) {
        Intrinsics.checkParameterIsNotNull(expressionType, "<set-?>");
        this.expression = expressionType;
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public ExpressionType getFace() {
        return this.expression;
    }

    private final void showLight(LightBeltType led, int headRGB) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HolaBotPeripherals$showLight$1(this, led, headRGB, null), 3, null);
    }

    private final void showEyes(ExpressionType expression) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HolaBotPeripherals$showEyes$1(this, expression, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    /* renamed from: setLightMode-3UDGHhA, reason: not valid java name */
    public void mo4498setLightMode3UDGHhA(LightBeltType[] led, LightColor color, LightMode lightMode, byte params2, byte params1, byte params0) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        Intrinsics.checkParameterIsNotNull(color, "color");
        Intrinsics.checkParameterIsNotNull(lightMode, "lightMode");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HolaBotPeripherals$setLightMode$1(this, led, color, lightMode, params2, params1, params0, null), 3, null);
    }

    /* renamed from: toRGBInt-b33U2AM$default, reason: not valid java name */
    static /* synthetic */ int m4496toRGBIntb33U2AM$default(HolaBotPeripherals holaBotPeripherals, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        return holaBotPeripherals.m4495toRGBIntb33U2AM(b, b2, b3);
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void playBreathing(LightBeltType[] lightBelts, LightBeltAnimationFrame f) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(f, "f");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HolaBotPeripherals$playBreathing$1(this, lightBelts, f, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: sendLightCan2-25hgefk, reason: not valid java name */
    public final /* synthetic */ Object m4497sendLightCan225hgefk(byte b, LightBeltAnimationFrame lightBeltAnimationFrame, Continuation<? super Unit> continuation) {
        Object obj = m4476sendDatab7CxX8A(new byte[]{-96, UByte.m4528constructorimpl((byte) (((b & 255) << 4) | (lightBeltAnimationFrame.getDuration() <= 16 ? lightBeltAnimationFrame.getDuration() : 16))), UByte.m4528constructorimpl((byte) lightBeltAnimationFrame.getHead().getR()), UByte.m4528constructorimpl((byte) lightBeltAnimationFrame.getHead().getG()), UByte.m4528constructorimpl((byte) lightBeltAnimationFrame.getHead().getB()), UByte.m4528constructorimpl((byte) lightBeltAnimationFrame.getEnd().getR()), UByte.m4528constructorimpl((byte) lightBeltAnimationFrame.getEnd().getG()), UByte.m4528constructorimpl((byte) lightBeltAnimationFrame.getEnd().getB())}, continuation);
        return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
    }

    private final void handleSensingAreaTouchEventData(byte[] data) {
        try {
            TouchPlace m4501valueOf7apg3OU = TouchPlace.INSTANCE.m4501valueOf7apg3OU(UByte.m4528constructorimpl(data[1]));
            TouchState m4502valueOf7apg3OU = TouchState.INSTANCE.m4502valueOf7apg3OU(UByte.m4528constructorimpl(data[2]));
            if (!isMute() && !this.isOnError) {
                if (m4501valueOf7apg3OU == TouchPlace.TouchKey) {
                    if (m4502valueOf7apg3OU == TouchState.DOWN) {
                        if (SystemClock.elapsedRealtime() - this.lastDownTime > this.FILTER_TIME_MS) {
                            this.mainHandler.removeCallbacksAndMessages(null);
                            this.mainHandler.sendEmptyMessageDelayed(this.BUTTON_DISPASH, this.FILTER_TIME_MS);
                            this.lastDownTime = SystemClock.elapsedRealtime();
                            return;
                        } else {
                            Pdlog.m3277w(TAG, "second touch in short time.ignore it. " + (SystemClock.elapsedRealtime() - this.lastDownTime));
                            return;
                        }
                    }
                    if (m4502valueOf7apg3OU != TouchState.UP) {
                        Pdlog.m3274e(TAG, "not define touch state type");
                        return;
                    }
                    if (SystemClock.elapsedRealtime() - this.lastDownTime <= this.FILTER_TIME_MS) {
                        this.mainHandler.removeCallbacksAndMessages(null);
                        Pdlog.m3277w(TAG, "touch to short.ignore it.  " + (SystemClock.elapsedRealtime() - this.lastDownTime));
                        return;
                    }
                    return;
                }
                if (m4501valueOf7apg3OU == TouchPlace.NonTouchKey) {
                    if (m4502valueOf7apg3OU == TouchState.DOWN) {
                        this.mainHandler.removeCallbacksAndMessages(null);
                    }
                    if (m4502valueOf7apg3OU == TouchState.UP) {
                        this.mainHandler.removeCallbacksAndMessages(null);
                        this.mainHandler.sendEmptyMessage(this.SENSOR_DISPASH);
                        return;
                    }
                    return;
                }
                return;
            }
            Pdlog.m3275i(TAG, "onTrigger FunctionButtonTask, but isMute=" + isMute() + "  isOnError=" + this.isOnError);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "handleSensingAreaTouchEventData() e = " + e);
        }
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public synchronized boolean isMute() {
        return this.isMute;
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public synchronized void setMute(boolean z) {
        this.isMute = z;
        Pdlog.m3275i(TAG, "set isMute=" + z);
    }

    public final synchronized boolean isOnError() {
        return this.isOnError;
    }

    public final synchronized void setOnError(boolean z) {
        this.isOnError = z;
        Pdlog.m3275i(TAG, "set isOnError=" + z);
    }
}
