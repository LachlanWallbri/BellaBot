package com.pudutech.robot.peripherals.common;

import android.content.Context;
import android.util.Log;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.robot.peripherals.config.LightBeltAnimation;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import com.pudutech.robot.peripherals.interf.IShutdownEventListener;
import com.pudutech.robot.peripherals.manager.CANManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteSpreadBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CommonRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0012\n\u0002\b\u000b*\u0001\u001e\b'\u0018\u0000 L2\u00020\u0001:\u0001LB\u0005¢\u0006\u0002\u0010\u0002J-\u0010&\u001a\u00020\u00132#\u0010'\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000eH\u0016J\u0012\u0010(\u001a\u00020\u00132\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010)\u001a\u00020\u0013H\u0002J)\u0010*\u001a\u00020\u00132\u0012\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0,\"\u00020-2\u0006\u0010.\u001a\u00020\u001bH\u0016¢\u0006\u0002\u0010/J)\u00100\u001a\u00020\u00132\u0012\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0,\"\u00020-2\u0006\u00101\u001a\u00020\u001aH\u0016¢\u0006\u0002\u00102J\u0018\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u001a2\u0006\u0010'\u001a\u000205H\u0016J\u0010\u00106\u001a\u00020\"H\u0002ø\u0001\u0000¢\u0006\u0002\u00107J\b\u00108\u001a\u0004\u0018\u00010\u0016J\u0010\u00109\u001a\u00020\"H&ø\u0001\u0000¢\u0006\u0002\u00107J\b\u0010:\u001a\u00020\u0013H\u0002J\u0018\u0010;\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u000fH\u0016J\b\u0010=\u001a\u00020\u0013H\u0002J\b\u0010>\u001a\u00020\u0013H\u0014J!\u0010?\u001a\u00020\u00132\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020BH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010CJ\b\u0010D\u001a\u00020\u0013H\u0016J\b\u0010E\u001a\u00020\u0013H\u0016J\u001e\u0010F\u001a\u00020\u00132\u0006\u0010A\u001a\u00020\"H\u0086@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ\b\u0010I\u001a\u00020\u0013H\u0016J!\u0010J\u001a\u00020\u00132\u0012\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0,\"\u00020-H\u0016¢\u0006\u0002\u0010KR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\r\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019j\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010!\u001a\u00020\"X\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006M"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "()V", "MAX_CONNECT_COUNT", "", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "gson", "Lcom/google/gson/Gson;", "hardwareConnectListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isConnect", "", "hardwareService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "isHardwareServiceConnected", "lightBeltAnimationMap", "Ljava/util/HashMap;", "", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;", "Lkotlin/collections/HashMap;", "onCANDataReceivedListener", "com/pudutech/robot/peripherals/common/CommonRobotPeripherals$onCANDataReceivedListener$1", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals$onCANDataReceivedListener$1;", "reConnectCount", "recvCmds", "Lkotlin/UByteArray;", "[B", "shutdownEventListener", "Lcom/pudutech/robot/peripherals/interf/IShutdownEventListener;", "addHardWareConnectListener", "l", "addShutdownEventListener", "connectHardwareService", "controlLight", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", "ani", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;)V", "controlLightBelt", "assetFileName", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Ljava/lang/String;)V", "debugOpen", TransferTable.COLUMN_KEY, "Lcom/pudutech/mirsdk/hardware/IHardware;", "getAllReceiveCmds", "()[B", "getHardWareInterface", "getRecvCmds", "handleShutdownEvent", "init", "isMock", "loadLightBeltAnimationListFromAssets", "onHardwareConnect", "parseData", "id", "data", "", "(I[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "release", "removeHardWareConnectListener", "sendData", "sendData-b7CxX8A", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shutdown", "stopLightBelt", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;)V", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class CommonRobotPeripherals implements IRobotPeripherals {
    private static final String LIGHT_BELT_ANIMATIONS_ASSETS_PATH = "led_animation";
    protected Context context;
    private Function1<? super Boolean, Unit> hardwareConnectListener;
    private boolean isHardwareServiceConnected;
    private int reConnectCount;
    private IShutdownEventListener shutdownEventListener;
    private final HashMap<String, LightBeltAnimation> lightBeltAnimationMap = new HashMap<>();
    private final Gson gson = new Gson();
    private final int MAX_CONNECT_COUNT = 3;
    private final AIDLConnection<HardwareInterface> hardwareService = new AIDLConnection<>("com.pudutech.mirsdk.hardware.HardwareService", CommonRobotPeripherals$hardwareService$1.INSTANCE, null, 4, null);
    private byte[] recvCmds = getRecvCmds();
    private final CommonRobotPeripherals$onCANDataReceivedListener$1 onCANDataReceivedListener = new CommonRobotPeripherals$onCANDataReceivedListener$1(this);

    public abstract byte[] getRecvCmds();

    protected void onHardwareConnect() {
    }

    public Object parseData(int i, byte[] bArr, Continuation<? super Unit> continuation) {
        return parseData$suspendImpl(this, i, bArr, continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getContext() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context;
    }

    protected final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final HardwareInterface getHardWareInterface() {
        return this.hardwareService.getInterface();
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void init(Context context, boolean isMock) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Log.d(getClass().getSimpleName(), "init() context = " + context + ", isMock = " + isMock);
        this.context = context;
        this.reConnectCount = 0;
        release();
        if (!isMock) {
            connectHardwareService();
        }
        loadLightBeltAnimationListFromAssets();
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void debugOpen(String key, IHardware l) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(l, "l");
        HardwareInterface hardwareInterface = this.hardwareService.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.addListener(key, l);
        }
        HardwareInterface hardwareInterface2 = this.hardwareService.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.open();
        }
    }

    private final void loadLightBeltAnimationListFromAssets() {
        String str;
        Pdlog.m3273d(getClass().getSimpleName(), "loadLightBeltAnimationListFromAssets()");
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String[] list = context.getAssets().list(LIGHT_BELT_ANIMATIONS_ASSETS_PATH);
        if (list != null) {
            for (String fileName : list) {
                String str2 = LIGHT_BELT_ANIMATIONS_ASSETS_PATH + File.separator + fileName;
                Log.d(getClass().getSimpleName(), "loadLightBeltAnimationListFromAssets() realFileName = " + str2);
                Context context2 = this.context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                InputStream open = context2.getAssets().open(str2);
                Intrinsics.checkExpressionValueIsNotNull(open, "context.assets.open(realFileName)");
                StringBuilder sb = new StringBuilder();
                BufferedReader inputStreamReader = new InputStreamReader(open);
                String str3 = null;
                Throwable th = (Throwable) null;
                try {
                    inputStreamReader = new BufferedReader(inputStreamReader);
                    Throwable th2 = (Throwable) null;
                    try {
                        BufferedReader bufferedReader = inputStreamReader;
                        String str4 = (String) null;
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                str = readLine;
                            } else {
                                readLine = str3;
                                str = str4;
                            }
                            if (readLine == null) {
                                break;
                            }
                            sb.append(str);
                            str4 = str;
                            str3 = null;
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(inputStreamReader, th2);
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(inputStreamReader, th);
                        String sb2 = sb.toString();
                        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
                        if (sb2.length() > 0) {
                            try {
                                LightBeltAnimation lightBeltAnimation = (LightBeltAnimation) this.gson.fromJson(sb2, LightBeltAnimation.class);
                                lightBeltAnimation.filterNull$module_robot_peripherals_release();
                                HashMap<String, LightBeltAnimation> hashMap = this.lightBeltAnimationMap;
                                Intrinsics.checkExpressionValueIsNotNull(fileName, "fileName");
                                String replace$default = StringsKt.replace$default(fileName, ".json", "", false, 4, (Object) null);
                                Intrinsics.checkExpressionValueIsNotNull(lightBeltAnimation, "lightBeltAnimation");
                                hashMap.put(replace$default, lightBeltAnimation);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } finally {
                    }
                } finally {
                }
            }
        }
        File file = new File("sdcard" + File.separator + LIGHT_BELT_ANIMATIONS_ASSETS_PATH);
        if (!file.exists()) {
            Pdlog.m3273d(getClass().getSimpleName(), "led_animation not exist in sdcard");
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File it : listFiles) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (it.isFile()) {
                        String readText = TextStreamsKt.readText(new FileReader(it));
                        Pdlog.m3275i(getClass().getSimpleName(), "read from sdcard. " + it.getName() + " : " + readText);
                        try {
                            LightBeltAnimation lightBeltAnimation2 = (LightBeltAnimation) this.gson.fromJson(readText, LightBeltAnimation.class);
                            lightBeltAnimation2.filterNull$module_robot_peripherals_release();
                            if (lightBeltAnimation2 != null) {
                                HashMap<String, LightBeltAnimation> hashMap2 = this.lightBeltAnimationMap;
                                String name = it.getName();
                                Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                                hashMap2.put(StringsKt.replace$default(name, ".json", "", false, 4, (Object) null), lightBeltAnimation2);
                            }
                        } catch (Exception e2) {
                            Pdlog.m3274e(getClass().getSimpleName(), "loadLightBeltAnimationListFromAssets : " + Log.getStackTraceString(e2));
                        }
                    }
                }
            }
        }
        for (Map.Entry<String, LightBeltAnimation> entry : this.lightBeltAnimationMap.entrySet()) {
            Pdlog.m3273d(getClass().getSimpleName(), "key = " + entry.getKey() + "\tvalue = " + entry.getValue() + '\n');
        }
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void shutdown() {
        Pdlog.m3273d("CommonRobotPeripherals", "send shutDownCmd");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CommonRobotPeripherals$shutdown$1(this, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void addShutdownEventListener(IShutdownEventListener shutdownEventListener) {
        Pdlog.m3273d(getClass().getSimpleName(), "addShutdownEventListener()");
        this.shutdownEventListener = shutdownEventListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connectHardwareService() {
        Pdlog.m3273d(getClass().getSimpleName(), "connectHardwareService()");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CommonRobotPeripherals$connectHardwareService$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] getAllReceiveCmds() {
        ByteSpreadBuilder byteSpreadBuilder = new ByteSpreadBuilder(2);
        byteSpreadBuilder.addSpread(CANManager.INSTANCE.getINSTANCE().getReceiveCmds());
        byteSpreadBuilder.addSpread(getRecvCmds());
        return byteSpreadBuilder.toArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleShutdownEvent() {
        Pdlog.m3273d(getClass().getSimpleName(), "handleShutdownEvent()");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new CommonRobotPeripherals$handleShutdownEvent$1(this, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void controlLightBelt(LightBeltType[] lightBelts, String assetFileName) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(assetFileName, "assetFileName");
        Pdlog.m3273d(getClass().getSimpleName(), "controlLightBelt() lightBelts = " + lightBelts + ", assetFileName = " + assetFileName);
        LightBeltAnimation lightBeltAnimation = this.lightBeltAnimationMap.get(assetFileName);
        if (lightBeltAnimation == null) {
            Pdlog.m3277w(getClass().getSimpleName(), assetFileName + " animation file not found, please check the assets/led_animation/" + assetFileName + ".json file exists");
            return;
        }
        Pdlog.m3273d(getClass().getSimpleName(), "lightBeltAnimation = " + lightBeltAnimation);
        controlLight((LightBeltType[]) Arrays.copyOf(lightBelts, lightBelts.length), lightBeltAnimation);
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void controlLight(LightBeltType[] lightBelts, LightBeltAnimation ani) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(ani, "ani");
        LightBeltsPlayHelper.play$module_robot_peripherals_release$default(LightBeltsPlayHelper.INSTANCE, (LightBeltType[]) Arrays.copyOf(lightBelts, lightBelts.length), ani, null, 4, null);
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void stopLightBelt(LightBeltType... lightBelts) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        LightBeltsPlayHelper.INSTANCE.stop$module_robot_peripherals_release((LightBeltType[]) Arrays.copyOf(lightBelts, lightBelts.length));
    }

    static /* synthetic */ Object parseData$suspendImpl(CommonRobotPeripherals commonRobotPeripherals, int i, byte[] bArr, Continuation continuation) {
        return Unit.INSTANCE;
    }

    /* renamed from: sendData-b7CxX8A, reason: not valid java name */
    public final Object m4476sendDatab7CxX8A(byte[] bArr, Continuation<? super Unit> continuation) {
        Unit unit;
        HardwareInterface hardwareInterface = this.hardwareService.getInterface();
        if (hardwareInterface != null) {
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
            hardwareInterface.sendCAN(copyOf);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void release() {
        Pdlog.m3273d(getClass().getSimpleName(), "release()");
        if (this.isHardwareServiceConnected) {
            AIDLConnection<HardwareInterface> aIDLConnection = this.hardwareService;
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            aIDLConnection.disconnect(context);
        }
        this.isHardwareServiceConnected = false;
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void addHardWareConnectListener(Function1<? super Boolean, Unit> l) {
        this.hardwareConnectListener = l;
    }

    @Override // com.pudutech.robot.peripherals.interf.IRobotPeripherals
    public void removeHardWareConnectListener() {
        if (this.hardwareConnectListener != null) {
            this.hardwareConnectListener = (Function1) null;
        }
    }
}
