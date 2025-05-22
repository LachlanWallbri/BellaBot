package com.pudutech.rgbdviewer;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.felhr.utils.HexData;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.activity.HardwareConnection;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlinx.coroutines.DelayKt;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: HardwareManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\bJ\u0006\u0010\u001e\u001a\u00020\bJ\u0006\u0010\u001f\u001a\u00020\bJ\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u0015H\u0002JD\u0010#\u001a\u00020\u00152<\u0010\r\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fRD\u0010\r\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\n\"\u0004\b\u0018\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m3961d2 = {"Lcom/pudutech/rgbdviewer/HardwareManager;", "", "()V", "INIT_LISTENER", "", "SENSOR_LISTENER", "TAG", "isConnecting", "", "isInitSuccess", "()Z", "setInitSuccess", "(Z)V", "onOpenStep", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "Lkotlin/ParameterName;", "name", "p0", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "p1", "", "openCanSuccess", "getOpenCanSuccess", "setOpenCanSuccess", MqttServiceConstants.CONNECT_ACTION, "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentRobotIsBellabot", "currentRobotIsNinetales", "currentRobotIsRecycleDog", "getRgbdVersion", "", "killRobotIfAny", "setOnOpenStep", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class HardwareManager {
    private static final String INIT_LISTENER = "init_listener";
    public static final HardwareManager INSTANCE = new HardwareManager();
    private static final String SENSOR_LISTENER = "sensor_listener";
    public static final String TAG = "HardwareManager";
    private static boolean isConnecting;
    private static boolean isInitSuccess;
    private static Function2<? super HardwareOpenStep, ? super StepState, Unit> onOpenStep;
    private static boolean openCanSuccess;

    private HardwareManager() {
    }

    public final boolean isInitSuccess() {
        return isInitSuccess;
    }

    public final void setInitSuccess(boolean z) {
        isInitSuccess = z;
    }

    public final boolean getOpenCanSuccess() {
        return openCanSuccess;
    }

    public final void setOpenCanSuccess(boolean z) {
        openCanSuccess = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0130 -> B:12:0x0039). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object connect(Context context, Continuation<? super Boolean> continuation) {
        HardwareManager$connect$1 hardwareManager$connect$1;
        int i;
        HardwareManager hardwareManager;
        int i2;
        Context context2;
        try {
            if (continuation instanceof HardwareManager$connect$1) {
                hardwareManager$connect$1 = (HardwareManager$connect$1) continuation;
                if ((hardwareManager$connect$1.label & Integer.MIN_VALUE) != 0) {
                    hardwareManager$connect$1.label -= Integer.MIN_VALUE;
                    Object obj = hardwareManager$connect$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = hardwareManager$connect$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        Pdlog.m3273d(TAG, MqttServiceConstants.CONNECT_ACTION);
                        if (isConnecting) {
                            Pdlog.m3274e(TAG, "connect isConnecting");
                            return Boxing.boxBoolean(true);
                        }
                        isConnecting = true;
                        onOpenStep = onOpenStep;
                        killRobotIfAny();
                        HardwareConnection hardwareConnection = HardwareConnection.INSTANCE;
                        hardwareManager$connect$1.L$0 = this;
                        hardwareManager$connect$1.L$1 = context;
                        hardwareManager$connect$1.label = 1;
                        obj = hardwareConnection.connect(context, null, hardwareManager$connect$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        hardwareManager = this;
                    } else {
                        if (i != 1) {
                            if (i == 2) {
                                int i3 = hardwareManager$connect$1.I$0;
                                context2 = (Context) hardwareManager$connect$1.L$1;
                                hardwareManager = (HardwareManager) hardwareManager$connect$1.L$0;
                                ResultKt.throwOnFailure(obj);
                                i2 = i3;
                                if (i2 <= 0 && !openCanSuccess) {
                                    i3 = i2 - 1;
                                    hardwareManager$connect$1.L$0 = hardwareManager;
                                    hardwareManager$connect$1.L$1 = context2;
                                    hardwareManager$connect$1.I$0 = i3;
                                    hardwareManager$connect$1.label = 2;
                                    if (DelayKt.delay(100L, hardwareManager$connect$1) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    i2 = i3;
                                    if (i2 <= 0) {
                                    }
                                    return Boxing.boxBoolean(openCanSuccess);
                                }
                                return Boxing.boxBoolean(openCanSuccess);
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        context = (Context) hardwareManager$connect$1.L$1;
                        hardwareManager = (HardwareManager) hardwareManager$connect$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    Pdlog.m3273d(TAG, "connect " + Thread.currentThread() + "  connect = " + booleanValue);
                    synchronized (HardwareManager.class) {
                        isConnecting = false;
                        isInitSuccess = true;
                        Unit unit = Unit.INSTANCE;
                    }
                    if (booleanValue) {
                        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
                        if (hardwareInterface == null) {
                            Intrinsics.throwNpe();
                        }
                        hardwareInterface.addListener(INIT_LISTENER, new IHardware.Stub() { // from class: com.pudutech.rgbdviewer.HardwareManager$connect$3
                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onAntiCollisionSwitch(boolean p0) {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onBattery(int p0, ChargeState p1) {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onCameraIRDLED(boolean p0) {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onConnectedChargingPile() {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onDisinfectionPower(boolean p0) {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onHarewareEmergencyBrake(boolean p0) {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onHarewareInfoReport(String p0) {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onWheelError(WheelError[] p0, WheelError[] p1) {
                            }

                            @Override // com.pudutech.mirsdk.hardware.IHardware
                            public void onOpenStep(HardwareOpenStep p0, StepState p1, String p2) {
                                Function2 function2;
                                Intrinsics.checkParameterIsNotNull(p2, "p2");
                                Pdlog.m3273d(HardwareManager.TAG, "name = " + p0 + " ---> state = " + p1);
                                if (p0 == HardwareOpenStep.FetchMachineInfo && p1 == StepState.Success) {
                                    HardwareManager.INSTANCE.setOpenCanSuccess(true);
                                    HardwareManager hardwareManager2 = HardwareManager.INSTANCE;
                                    function2 = HardwareManager.onOpenStep;
                                    if (function2 != null) {
                                    }
                                }
                            }
                        });
                        HardwareInterface hardwareInterface2 = HardwareConnection.INSTANCE.getInterface();
                        if (hardwareInterface2 == null) {
                            Intrinsics.throwNpe();
                        }
                        hardwareInterface2.openCAN();
                        HardwareInterface hardwareInterface3 = HardwareConnection.INSTANCE.getInterface();
                        if (hardwareInterface3 == null) {
                            Intrinsics.throwNpe();
                        }
                        hardwareInterface3.addCANDataListener("machine_info", new byte[]{82, TarConstants.LF_GNUTYPE_SPARSE, 84}, new ICANData.Stub() { // from class: com.pudutech.rgbdviewer.HardwareManager$connect$4
                            @Override // com.pudutech.mirsdk.hardware.ICANData
                            public void onData(int p0, byte[] p1) {
                                Pdlog.m3273d(HardwareManager.TAG, "addCANDataListener p0 = " + p0 + " , p1 = " + HexData.hexToString(p1));
                            }
                        });
                        i2 = 100;
                        context2 = context;
                        if (i2 <= 0) {
                        }
                        return Boxing.boxBoolean(openCanSuccess);
                    }
                    synchronized (HardwareManager.class) {
                        isInitSuccess = false;
                        isConnecting = false;
                        Unit unit2 = Unit.INSTANCE;
                    }
                    Pdlog.m3274e(TAG, "HardwareConnection connect failed");
                    return Boxing.boxBoolean(false);
                }
            }
            if (i != 0) {
            }
            boolean booleanValue2 = ((Boolean) obj).booleanValue();
            Pdlog.m3273d(TAG, "connect " + Thread.currentThread() + "  connect = " + booleanValue2);
            synchronized (HardwareManager.class) {
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            synchronized (HardwareManager.class) {
                isInitSuccess = false;
                isConnecting = false;
                Unit unit3 = Unit.INSTANCE;
                return Boxing.boxBoolean(false);
            }
        }
        hardwareManager$connect$1 = new HardwareManager$connect$1(this, continuation);
        Object obj2 = hardwareManager$connect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hardwareManager$connect$1.label;
    }

    public final boolean currentRobotIsBellabot() {
        if (isInitSuccess) {
            HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
            return hardwareInterface != null && hardwareInterface.getMachineInfo().getProductMachineType().getModel() == MachineModel.BellaBot;
        }
        Pdlog.m3274e(TAG, "getCurrentRobotType isInitSuccess is failed");
        return false;
    }

    public final int getRgbdVersion() {
        HardwareInterface hardwareInterface;
        if (isInitSuccess && (hardwareInterface = HardwareConnection.INSTANCE.getInterface()) != null) {
            return hardwareInterface.getMachineInfo().getRGBDMode().getId() & 255;
        }
        Pdlog.m3273d(TAG, "rgbd not init");
        return 1;
    }

    public final boolean currentRobotIsRecycleDog() {
        if (isInitSuccess) {
            HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
            return hardwareInterface != null && hardwareInterface.getMachineInfo().getProductMachineType().getModel() == MachineModel.RecycleDog;
        }
        Pdlog.m3274e(TAG, "getCurrentRobotType isInitSuccess is failed");
        return false;
    }

    public final boolean currentRobotIsNinetales() {
        if (isInitSuccess) {
            HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
            return hardwareInterface != null && hardwareInterface.getMachineInfo().getProductMachineType().getModel() == MachineModel.Ninetales;
        }
        Pdlog.m3274e(TAG, "getCurrentRobotType isInitSuccess is failed");
        return false;
    }

    public final void setOnOpenStep(Function2<? super HardwareOpenStep, ? super StepState, Unit> onOpenStep2) {
        onOpenStep = onOpenStep2;
    }

    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3 */
    private final void killRobotIfAny() {
        List emptyList;
        List emptyList2;
        String[] strArr = {"com.pudutech.pdrobot", com.pudutech.bumblebee.BuildConfig.APPLICATION_ID, "com.pudutech.recycle.robot"};
        int i = 0;
        int i2 = 0;
        while (i2 < 3) {
            Pair<Integer, String> execCommand = Tools.execCommand("ps | grep " + strArr[i2], i);
            Object[] objArr = new Object[1];
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr2 = new Object[2];
            objArr2[i] = execCommand.first;
            objArr2[1] = execCommand.second;
            String format = String.format("%d - %s", Arrays.copyOf(objArr2, 2));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            objArr[i] = format;
            Pdlog.m3275i(TAG, objArr);
            Integer num = (Integer) execCommand.first;
            if (num != null && num.intValue() == 0) {
                Object obj = execCommand.second;
                Intrinsics.checkExpressionValueIsNotNull(obj, "res.second");
                List<String> split = new Regex("\n").split((CharSequence) obj, i);
                if (!split.isEmpty()) {
                    ListIterator<String> listIterator = split.listIterator(split.size());
                    while (listIterator.hasPrevious()) {
                        if (!(listIterator.previous().length() == 0)) {
                            emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                emptyList = CollectionsKt.emptyList();
                Object[] array = emptyList.toArray(new String[i]);
                if (array != null) {
                    String[] strArr2 = (String[]) array;
                    int length = strArr2.length;
                    int i3 = 0;
                    int i4 = i;
                    while (i3 < length) {
                        List<String> split2 = new Regex("\\s").split(strArr2[i3], i4);
                        if (!split2.isEmpty()) {
                            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                            while (listIterator2.hasPrevious()) {
                                if (!(listIterator2.previous().length() == 0)) {
                                    emptyList2 = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                                    break;
                                }
                            }
                        }
                        emptyList2 = CollectionsKt.emptyList();
                        Object[] array2 = emptyList2.toArray(new String[i4]);
                        if (array2 != null) {
                            String[] strArr3 = (String[]) array2;
                            Object[] objArr3 = new Object[1];
                            objArr3[i4] = "fields: " + Arrays.toString(strArr3);
                            Pdlog.m3275i(TAG, objArr3);
                            if (strArr3.length > 1) {
                                String str = (String) null;
                                for (int i5 = 1; i5 < strArr3.length && str == null; i5++) {
                                    String str2 = strArr3[i5];
                                    int length2 = str2.length() - 1;
                                    int i6 = 0;
                                    boolean z = false;
                                    while (i6 <= length2) {
                                        boolean z2 = str2.charAt(!z ? i6 : length2) <= ' ';
                                        if (z) {
                                            if (!z2) {
                                                break;
                                            } else {
                                                length2--;
                                            }
                                        } else if (z2) {
                                            i6++;
                                        } else {
                                            z = true;
                                        }
                                    }
                                    if (!TextUtils.isEmpty(str2.subSequence(i6, length2 + 1).toString())) {
                                        str = strArr3[i5];
                                    }
                                }
                                if (str == null) {
                                    try {
                                        Intrinsics.throwNpe();
                                    } catch (Exception e) {
                                        Pdlog.m3274e(TAG, "parse pid failed", e);
                                    }
                                }
                                Tools.execCommand("kill " + Integer.parseInt(str), true);
                            }
                            i3++;
                            i4 = 0;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            i2++;
            i = 0;
        }
    }
}
