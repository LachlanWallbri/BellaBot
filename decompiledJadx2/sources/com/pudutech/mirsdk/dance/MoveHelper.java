package com.pudutech.mirsdk.dance;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.WatchDog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusInfo;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: MoveHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u007f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\f\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\u0002\u0010\u0017J\b\u0010.\u001a\u00020\u0013H\u0002J\u0011\u0010/\u001a\u00020\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u00100J\b\u00101\u001a\u00020\u0013H\u0002J\u0013\u00102\u001a\u00020\u0013H\u0080@ø\u0001\u0000¢\u0006\u0004\b3\u00100J\u0006\u00104\u001a\u00020\u0013J\u0011\u00105\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u00100J\u0006\u00106\u001a\u00020\u0013J\u001c\u00107\u001a\u00020\u00132\u0014\u00108\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001109J\u000e\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\u0011J\u0014\u0010<\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020\u001109H\u0002R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000R>\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\u001b\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, m3961d2 = {"Lcom/pudutech/mirsdk/dance/MoveHelper;", "", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "_onStateChange", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "state", "", TmpConstant.SERVICE_DESC, "", "actionStart", "Lkotlin/Function0;", "actionStop", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/mirhardware/RobotStatus;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "TAG", "brakeJob", "Lkotlinx/coroutines/Job;", "errorClean", "Lkotlin/Function1;", "", "isCleaned", "getErrorClean", "()Lkotlin/jvm/functions/Function1;", "setErrorClean", "(Lkotlin/jvm/functions/Function1;)V", "errorJob", "helperJob", "lastSensorError", "", "localizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "stopNotify", "getStopNotify", "()Lkotlin/jvm/functions/Function0;", "setStopNotify", "(Lkotlin/jvm/functions/Function0;)V", "brakeUntilStop", "checkAndClearWheelError", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retriveMCU", "robotStop", "robotStop$MirFunction_packRelease", "startJob", "stopAndWaitBrake", "stopJob", "suspendWarningWelfunction", "warning", "Lkotlin/Pair;", "triggerError", "error", "watchDogCheck", "Lcom/pudutech/mirsdk/SolicitService$WatchLevel;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MoveHelper {
    private final String TAG;
    private final Function2<RobotState, String, Unit> _onStateChange;
    private final Function0<Unit> actionStart;
    private final Function0<Unit> actionStop;
    private Job brakeJob;
    private final AIDLConnection<MirCoreInterface> coreService;
    private Function1<? super Boolean, Unit> errorClean;
    private Job errorJob;
    private Job helperJob;
    private final List<String> lastSensorError;
    private LocalizationStatus localizationStatus;
    private final RobotHardware robotHardware;
    private final RobotStatus robotStatus;
    private Function0<Unit> stopNotify;
    private final WatchDog watchDog;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SolicitService.WatchLevel.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            $EnumSwitchMapping$0[SolicitService.WatchLevel.Over.ordinal()] = 1;
            $EnumSwitchMapping$0[SolicitService.WatchLevel.Pause.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[LocalizationStatusInfo.values().length];
            $EnumSwitchMapping$1[LocalizationStatusInfo.NoMarker.ordinal()] = 1;
            $EnumSwitchMapping$1[LocalizationStatusInfo.MarkerError.ordinal()] = 2;
            $EnumSwitchMapping$1[LocalizationStatusInfo.LaserLostRecovering.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[LocalizationStatusInfo.values().length];
            $EnumSwitchMapping$2[LocalizationStatusInfo.NoParam.ordinal()] = 1;
            $EnumSwitchMapping$2[LocalizationStatusInfo.NoInit.ordinal()] = 2;
            $EnumSwitchMapping$2[LocalizationStatusInfo.ParamError.ordinal()] = 3;
            $EnumSwitchMapping$2[LocalizationStatusInfo.MapError.ordinal()] = 4;
            $EnumSwitchMapping$2[LocalizationStatusInfo.NoMarker.ordinal()] = 5;
            $EnumSwitchMapping$2[LocalizationStatusInfo.MarkerError.ordinal()] = 6;
            $EnumSwitchMapping$2[LocalizationStatusInfo.LaserLocateLose.ordinal()] = 7;
            $EnumSwitchMapping$3 = new int[LocalizationStatusLevel.values().length];
            $EnumSwitchMapping$3[LocalizationStatusLevel.Normal.ordinal()] = 1;
            $EnumSwitchMapping$3[LocalizationStatusLevel.Warning.ordinal()] = 2;
            $EnumSwitchMapping$3[LocalizationStatusLevel.Error.ordinal()] = 3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MoveHelper(RobotHardware robotHardware, AIDLConnection<MirCoreInterface> coreService, RobotStatus robotStatus, WatchDog watchDog, Function2<? super RobotState, ? super String, Unit> _onStateChange, Function0<Unit> actionStart, Function0<Unit> actionStop) {
        Intrinsics.checkParameterIsNotNull(robotHardware, "robotHardware");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(robotStatus, "robotStatus");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(_onStateChange, "_onStateChange");
        Intrinsics.checkParameterIsNotNull(actionStart, "actionStart");
        Intrinsics.checkParameterIsNotNull(actionStop, "actionStop");
        this.robotHardware = robotHardware;
        this.coreService = coreService;
        this.robotStatus = robotStatus;
        this.watchDog = watchDog;
        this._onStateChange = _onStateChange;
        this.actionStart = actionStart;
        this.actionStop = actionStop;
        this.TAG = "MoveCheckUtils";
        this.lastSensorError = new ArrayList();
        this.localizationStatus = new LocalizationStatus(null, null, null, 7, null);
    }

    public final Function0<Unit> getStopNotify() {
        return this.stopNotify;
    }

    public final void setStopNotify(Function0<Unit> function0) {
        this.stopNotify = function0;
    }

    public final Function1<Boolean, Unit> getErrorClean() {
        return this.errorClean;
    }

    public final void setErrorClean(Function1<? super Boolean, Unit> function1) {
        this.errorClean = function1;
    }

    public final void startJob() {
        Job launch$default;
        Pdlog.m3273d(this.TAG, "startJob enter");
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveHelper$startJob$1(this, null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getMoveActionWorker(), null, new MoveHelper$startJob$2(this, null), 2, null);
        this.helperJob = launch$default;
    }

    public final void stopJob() {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("stopJob start ");
        Job job = this.helperJob;
        sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveHelper$stopJob$1(this, null), 1, null);
        Pdlog.m3273d(this.TAG, "stopJob end");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkAndClearWheelError(Continuation<? super Boolean> continuation) {
        MoveHelper$checkAndClearWheelError$1 moveHelper$checkAndClearWheelError$1;
        int i;
        MoveHelper moveHelper;
        Boolean bool;
        HardwareInterface hardwareInterface;
        Function1<? super Boolean, Unit> function1;
        if (continuation instanceof MoveHelper$checkAndClearWheelError$1) {
            moveHelper$checkAndClearWheelError$1 = (MoveHelper$checkAndClearWheelError$1) continuation;
            if ((moveHelper$checkAndClearWheelError$1.label & Integer.MIN_VALUE) != 0) {
                moveHelper$checkAndClearWheelError$1.label -= Integer.MIN_VALUE;
                Object obj = moveHelper$checkAndClearWheelError$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveHelper$checkAndClearWheelError$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.robotHardware.getWheelMulfunctionState()) {
                        MoveHelper$checkAndClearWheelError$result$1 moveHelper$checkAndClearWheelError$result$1 = new MoveHelper$checkAndClearWheelError$result$1(this, null);
                        moveHelper$checkAndClearWheelError$1.L$0 = this;
                        moveHelper$checkAndClearWheelError$1.label = 1;
                        obj = TimeoutKt.withTimeoutOrNull(500L, moveHelper$checkAndClearWheelError$result$1, moveHelper$checkAndClearWheelError$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        moveHelper = this;
                    } else {
                        return Boxing.boxBoolean(true);
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    moveHelper = (MoveHelper) moveHelper$checkAndClearWheelError$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                bool = (Boolean) obj;
                hardwareInterface = moveHelper.robotHardware.getInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.controlWheel(0.0d, 0.0d, false);
                }
                function1 = moveHelper.errorClean;
                if (function1 != null) {
                    function1.invoke(bool);
                }
                boolean z = false;
                if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
                    Pdlog.m3277w(moveHelper.TAG, "clear wheel error fail");
                    moveHelper._onStateChange.invoke(RobotState.Error, moveHelper.robotHardware.getWheelInWarning() ? moveHelper.robotHardware.getLastWarnMulfunction() : moveHelper.robotHardware.getLastErrorMulfunction());
                } else {
                    moveHelper.robotHardware.setWheelInError(false);
                    moveHelper.robotHardware.setWheelInWarning(false);
                    Pdlog.m3273d(moveHelper.TAG, "wheel error being cleared");
                    z = true;
                }
                return Boxing.boxBoolean(z);
            }
        }
        moveHelper$checkAndClearWheelError$1 = new MoveHelper$checkAndClearWheelError$1(this, continuation);
        Object obj2 = moveHelper$checkAndClearWheelError$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveHelper$checkAndClearWheelError$1.label;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        hardwareInterface = moveHelper.robotHardware.getInterface();
        if (hardwareInterface != null) {
        }
        function1 = moveHelper.errorClean;
        if (function1 != null) {
        }
        boolean z2 = false;
        if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
        }
        return Boxing.boxBoolean(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<SolicitService.WatchLevel, String> watchDogCheck() {
        Iterator<String> it;
        String str;
        DeviceType deviceType;
        Iterator<String> it2;
        String str2;
        DeviceType deviceType2;
        Pair<List<String>, List<String>> check = this.watchDog.check();
        int i = 1;
        String str3 = "[";
        if (!check.getSecond().isEmpty()) {
            this.lastSensorError.clear();
            Pdlog.m3274e(this.TAG, "navigation fail, Watch dog timeout:" + CollectionsKt.joinToString$default(check.getSecond(), null, null, null, 0, null, null, 63, null));
            Iterator<String> it3 = check.getSecond().iterator();
            while (it3.hasNext()) {
                String next = it3.next();
                this.lastSensorError.add(next);
                HardwareInterface hardwareInterface = this.robotHardware.getInterface();
                if (hardwareInterface != null) {
                    DeviceType[] deviceTypeArr = new DeviceType[i];
                    int hashCode = next.hashCode();
                    it2 = it3;
                    if (hashCode == -2018805884) {
                        if (next.equals("Lindar")) {
                            deviceType2 = DeviceType.Lidar;
                            deviceTypeArr[0] = deviceType2;
                            str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    } else if (hashCode != 2513207) {
                        if (hashCode == 2011082565 && next.equals("Camera")) {
                            deviceType2 = DeviceType.Camera;
                            deviceTypeArr[0] = deviceType2;
                            str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    } else {
                        if (next.equals("RGBD")) {
                            deviceType2 = DeviceType.RGBD;
                            deviceTypeArr[0] = deviceType2;
                            str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    }
                } else {
                    it2 = it3;
                    str2 = null;
                }
                str3 = str3 + "{\"error_type\":\"Lost" + next + "\", \"level\":\"Error\",\"detail\":\"" + str2 + "\"},";
                it3 = it2;
                i = 1;
            }
            String str4 = str3 + "]";
            if (check.getSecond().contains("CAN")) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveHelper$watchDogCheck$1(this, null), 3, null);
            }
            return new Pair<>(SolicitService.WatchLevel.Over, str4);
        }
        if (!check.getFirst().isEmpty()) {
            if (check.getFirst().contains("Encoder") || check.getFirst().contains("IMU") || check.getFirst().contains("CAN")) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveHelper$watchDogCheck$2(this, null), 3, null);
            }
            Iterator<String> it4 = check.getFirst().iterator();
            String str5 = "[";
            boolean z = false;
            while (it4.hasNext()) {
                String next2 = it4.next();
                HardwareInterface hardwareInterface2 = this.robotHardware.getInterface();
                if (hardwareInterface2 != null) {
                    DeviceType[] deviceTypeArr2 = new DeviceType[1];
                    int hashCode2 = next2.hashCode();
                    it = it4;
                    if (hashCode2 == -2018805884) {
                        if (next2.equals("Lindar")) {
                            deviceType = DeviceType.Lidar;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    } else if (hashCode2 != 2513207) {
                        if (hashCode2 == 2011082565 && next2.equals("Camera")) {
                            deviceType = DeviceType.Camera;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    } else {
                        if (next2.equals("RGBD")) {
                            deviceType = DeviceType.RGBD;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    }
                } else {
                    it = it4;
                    str = null;
                }
                str5 = str5 + "{\"error_type\":\"Lost" + next2 + "\", \"level\":\"Warning\",\"detail\":\"" + str + "\"},";
                if (this.lastSensorError.contains(next2)) {
                    str3 = str3 + "{\"error_type\":\"Lost" + next2 + "\", \"level\":\"Error\",\"detail\":\"" + str + "\"},";
                    z = true;
                }
                it4 = it;
            }
            String str6 = str3 + "]";
            String str7 = str5 + "]";
            if (z) {
                return new Pair<>(SolicitService.WatchLevel.Over, str6);
            }
            return new Pair<>(SolicitService.WatchLevel.Pause, str7);
        }
        this.lastSensorError.clear();
        return new Pair<>(SolicitService.WatchLevel.Normal, "");
    }

    private final void brakeUntilStop() {
        Job launch$default;
        Job job = this.brakeJob;
        if (job == null || job == null || !job.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveHelper$brakeUntilStop$1(this, null), 3, null);
            this.brakeJob = launch$default;
        } else {
            Pdlog.m3277w(this.TAG, "quit brake because previous brake still running");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object robotStop$MirFunction_packRelease(Continuation<? super Unit> continuation) {
        MoveHelper$robotStop$1 moveHelper$robotStop$1;
        int i;
        MoveHelper moveHelper;
        HardwareInterface hardwareInterface;
        Function0<Unit> function0;
        if (continuation instanceof MoveHelper$robotStop$1) {
            moveHelper$robotStop$1 = (MoveHelper$robotStop$1) continuation;
            if ((moveHelper$robotStop$1.label & Integer.MIN_VALUE) != 0) {
                moveHelper$robotStop$1.label -= Integer.MIN_VALUE;
                Object obj = moveHelper$robotStop$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveHelper$robotStop$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    MoveHelper$robotStop$2 moveHelper$robotStop$2 = new MoveHelper$robotStop$2(this, null);
                    moveHelper$robotStop$1.L$0 = this;
                    moveHelper$robotStop$1.label = 1;
                    if (TimeoutKt.withTimeoutOrNull(1000L, moveHelper$robotStop$2, moveHelper$robotStop$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    moveHelper = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    moveHelper = (MoveHelper) moveHelper$robotStop$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                hardwareInterface = moveHelper.robotHardware.getInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.controlWheel(0.0d, 0.0d, false);
                }
                function0 = moveHelper.stopNotify;
                if (function0 != null) {
                    function0.invoke();
                }
                String str = moveHelper.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("has stopped------> ");
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getName());
                Pdlog.m3273d(str, sb.toString());
                return Unit.INSTANCE;
            }
        }
        moveHelper$robotStop$1 = new MoveHelper$robotStop$1(this, continuation);
        Object obj2 = moveHelper$robotStop$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveHelper$robotStop$1.label;
        if (i != 0) {
        }
        hardwareInterface = moveHelper.robotHardware.getInterface();
        if (hardwareInterface != null) {
        }
        function0 = moveHelper.stopNotify;
        if (function0 != null) {
        }
        String str2 = moveHelper.TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("has stopped------> ");
        Thread currentThread2 = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread2, "Thread.currentThread()");
        sb2.append(currentThread2.getName());
        Pdlog.m3273d(str2, sb2.toString());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retriveMCU() {
        LidarInterface lidarInterface;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.sendCAN(new byte[]{22, 2, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface2 = this.robotHardware.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.sendCAN(new byte[]{22, 4, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface3 = this.robotHardware.getInterface();
        if (hardwareInterface3 != null) {
            hardwareInterface3.sendCAN(new byte[]{22, 6, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface4 = this.robotHardware.getInterface();
        if (hardwareInterface4 != null) {
            hardwareInterface4.sendCAN(new byte[]{22, (byte) 255, (byte) 225, 4, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface5 = this.robotHardware.getInterface();
        if (hardwareInterface5 == null || (lidarInterface = hardwareInterface5.getLidarInterface()) == null) {
            return;
        }
        lidarInterface.open();
    }

    public final void triggerError(String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("triggerError  ");
        Job job = this.errorJob;
        sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
        sb.append(',');
        Job job2 = this.helperJob;
        sb.append(job2 != null ? Boolean.valueOf(job2.isActive()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        Job job3 = this.errorJob;
        if (job3 != null && job3.isActive()) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveHelper$triggerError$1(this, error, null), 3, null);
            return;
        }
        Job job4 = this.helperJob;
        if (job4 == null || !job4.isActive()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveHelper$triggerError$2(this, error, null), 3, null);
    }

    public final void suspendWarningWelfunction(Pair<Boolean, String> warning) {
        Job job;
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(warning, "warning");
        if (warning.getFirst().booleanValue()) {
            Job job2 = this.errorJob;
            if ((job2 == null || !job2.isActive()) && (job = this.helperJob) != null && job.isActive()) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveHelper$suspendWarningWelfunction$1(this, warning, null), 3, null);
                this.errorJob = launch$default;
                return;
            }
            return;
        }
        Job job3 = this.errorJob;
        if (job3 == null || !job3.isActive()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveHelper$suspendWarningWelfunction$2(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038 A[Catch: all -> 0x006b, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000f, B:8:0x001a, B:11:0x0028, B:12:0x0057, B:16:0x0030, B:17:0x0037, B:18:0x0038, B:20:0x004a, B:25:0x0015), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized /* synthetic */ Object stopAndWaitBrake(Continuation<? super Unit> continuation) {
        MoveHelper$stopAndWaitBrake$1 moveHelper$stopAndWaitBrake$1;
        int i;
        MoveHelper moveHelper;
        if (continuation instanceof MoveHelper$stopAndWaitBrake$1) {
            moveHelper$stopAndWaitBrake$1 = (MoveHelper$stopAndWaitBrake$1) continuation;
            if ((moveHelper$stopAndWaitBrake$1.label & Integer.MIN_VALUE) != 0) {
                moveHelper$stopAndWaitBrake$1.label -= Integer.MIN_VALUE;
                Object obj = moveHelper$stopAndWaitBrake$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveHelper$stopAndWaitBrake$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(this.TAG, "stopAndWaitBrake start");
                    Job job = this.helperJob;
                    if (job != null) {
                        moveHelper$stopAndWaitBrake$1.L$0 = this;
                        moveHelper$stopAndWaitBrake$1.label = 1;
                        if (JobKt.cancelAndJoin(job, moveHelper$stopAndWaitBrake$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    moveHelper = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    moveHelper = (MoveHelper) moveHelper$stopAndWaitBrake$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                moveHelper.actionStop.invoke();
                Pdlog.m3273d(moveHelper.TAG, "stopAndWaitBrake end ---");
                return Unit.INSTANCE;
            }
        }
        moveHelper$stopAndWaitBrake$1 = new MoveHelper$stopAndWaitBrake$1(this, continuation);
        Object obj2 = moveHelper$stopAndWaitBrake$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveHelper$stopAndWaitBrake$1.label;
        if (i != 0) {
        }
        moveHelper.actionStop.invoke();
        Pdlog.m3273d(moveHelper.TAG, "stopAndWaitBrake end ---");
        return Unit.INSTANCE;
    }
}
