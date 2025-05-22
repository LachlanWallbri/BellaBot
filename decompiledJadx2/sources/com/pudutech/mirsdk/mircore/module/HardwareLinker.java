package com.pudutech.mirsdk.mircore.module;

import android.content.Context;
import com.aliyun.linksdk.alcs.AlcsConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IAccelerationData;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.ScheduleCommunication;
import com.pudutech.mirsdk.hardware.ScheduleInfoTransportor;
import com.pudutech.mirsdk.hardware.serialize.AccelerationType;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.MirCoreScopeKt;
import com.pudutech.mirsdk.mircore.ScheduleListener;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mircore.coreparcel.ScheduleFillInState;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import com.pudutech.mirsdk.mircore.mirschedulemaster.ScheduleMaster;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareLinker.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010*\u0002\u0007\"\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010)\u001a\u00020\u000eJ\u0019\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010.J\u000e\u0010/\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0006\u00100\u001a\u00020\u000eJ\u0006\u00101\u001a\u000202J\b\u00103\u001a\u0004\u0018\u00010\u0015J\u0006\u00104\u001a\u00020\u000eJ\u0006\u00105\u001a\u00020+J\u000e\u00106\u001a\u00020+2\u0006\u00107\u001a\u00020\u000eJ\u000e\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000eJ\b\u0010:\u001a\u00020+H\u0002J\u0016\u0010;\u001a\u00020+2\u0006\u0010<\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020\fJ\u000e\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u000202J\u000e\u0010@\u001a\u00020+2\u0006\u0010A\u001a\u00020\u000eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010#R\u001a\u0010$\u001a\u00020\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006B"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/HardwareLinker;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "accListener", "com/pudutech/mirsdk/mircore/module/HardwareLinker$accListener$1", "Lcom/pudutech/mirsdk/mircore/module/HardwareLinker$accListener$1;", "accStatus", "", "Lcom/pudutech/mirsdk/hardware/serialize/AccelerationType;", "", "connectStatus", "", "fillInGoal", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "fillInState", "Lcom/pudutech/mirsdk/mircore/coreparcel/ScheduleFillInState;", "hardwareConnection", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "hardwareService", "localizationModuleInited", "schCommContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "scheduleJob", "Lkotlinx/coroutines/Job;", "scheduleListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/mircore/ScheduleListener;", "getScheduleListeners", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "scheduleProducer", "com/pudutech/mirsdk/mircore/module/HardwareLinker$scheduleProducer$1", "Lcom/pudutech/mirsdk/mircore/module/HardwareLinker$scheduleProducer$1;", "schedule_skip_others", "getSchedule_skip_others$mircore_packRelease", "()Z", "setSchedule_skip_others$mircore_packRelease", "(Z)V", "addLinker", "createLinker", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "destroy", "getConnectStatus", "getExposure", "", "getHardwareService", "isRGBDEnabled", "removeLinker", "resetLocalizationModuleInited", "flag", "scheduleTestSkipOthers", "skip", "sendScheduleInfo", "setAccParam", "acc_type", ES6Iterator.VALUE_PROPERTY, "setExposure", "exposure", "setRgbdParkingMode", "into_park", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class HardwareLinker {
    private final HardwareLinker$accListener$1 accListener;
    private Map<AccelerationType, Double> accStatus;
    private boolean connectStatus;
    private final AIDLConnection<HardwareInterface> hardwareConnection;
    private HardwareInterface hardwareService;
    private volatile boolean localizationModuleInited;
    private final ExecutorCoroutineDispatcher schCommContext;
    private Job scheduleJob;
    private final HardwareLinker$scheduleProducer$1 scheduleProducer;
    private boolean schedule_skip_others;
    private final String TAG = getClass().getSimpleName();
    private final ThreadSafeListener<ScheduleListener> scheduleListeners = new ThreadSafeListener<>();
    private Vector3d fillInGoal = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private final ScheduleFillInState fillInState = new ScheduleFillInState();

    /* JADX WARN: Type inference failed for: r0v10, types: [com.pudutech.mirsdk.mircore.module.HardwareLinker$scheduleProducer$1] */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.pudutech.mirsdk.mircore.module.HardwareLinker$accListener$1] */
    public HardwareLinker() {
        AccelerationType accelerationType = AccelerationType.Acceleration;
        Double valueOf = Double.valueOf(0.7d);
        this.accStatus = MapsKt.mutableMapOf(TuplesKt.m3968to(accelerationType, valueOf), TuplesKt.m3968to(AccelerationType.Deceleration, valueOf), TuplesKt.m3968to(AccelerationType.EmergencyStopDeceleration, Double.valueOf(1.2d)));
        this.schCommContext = ThreadPoolDispatcherKt.newSingleThreadContext("SchCommLoop");
        this.scheduleProducer = new ScheduleInfoTransportor.Stub() { // from class: com.pudutech.mirsdk.mircore.module.HardwareLinker$scheduleProducer$1
            @Override // com.pudutech.mirsdk.hardware.ScheduleInfoTransportor
            public void updateOtherRobotInfo(RobotScheduleInfo info) {
                String str;
                boolean schedule_skip_others;
                String str2;
                Intrinsics.checkParameterIsNotNull(info, "info");
                str = HardwareLinker.this.TAG;
                Pdlog.m3273d(str, "receive other robot " + info.getRobot_id() + " schedule info from hardware, then send to native interface");
                Vector2d vector2d = new Vector2d(info.getVelocity().getX(), info.getVelocity().getY());
                MirCoreImpl.INSTANCE.updateDebugSchRecv$mircore_packRelease(info.getRobot_id(), info.getPose(), vector2d);
                double x = vector2d.getX();
                vector2d.getY();
                Perception.INSTANCE.setOtherRobotInfo(info.getRobot_id(), info.getPose().getX(), info.getPose().getY(), info.getPose().getZ(), x * Math.cos(info.getPose().getZ()), x * Math.sin(info.getPose().getZ()));
                synchronized (Boolean.valueOf(HardwareLinker.this.getSchedule_skip_others())) {
                    schedule_skip_others = HardwareLinker.this.getSchedule_skip_others();
                }
                if (schedule_skip_others) {
                    str2 = HardwareLinker.this.TAG;
                    Pdlog.m3273d(str2, "debug mode, robot info not sent to schedule");
                } else {
                    ScheduleMaster.INSTANCE.updateOtherRobotInfo(info);
                }
            }
        };
        this.accListener = new IAccelerationData.Stub() { // from class: com.pudutech.mirsdk.mircore.module.HardwareLinker$accListener$1
            @Override // com.pudutech.mirsdk.hardware.IAccelerationData
            public void onData(AccelerationType p0, double p1) {
                Map map;
                Intrinsics.checkParameterIsNotNull(p0, "p0");
                map = HardwareLinker.this.accStatus;
                map.put(p0, Double.valueOf(p1));
            }
        };
        this.hardwareConnection = new AIDLConnection<>("com.pudutech.mirsdk.hardware.HardwareService", HardwareLinker$hardwareConnection$1.INSTANCE, null, 4, null);
    }

    public final ThreadSafeListener<ScheduleListener> getScheduleListeners() {
        return this.scheduleListeners;
    }

    /* renamed from: getSchedule_skip_others$mircore_packRelease, reason: from getter */
    public final boolean getSchedule_skip_others() {
        return this.schedule_skip_others;
    }

    public final void setSchedule_skip_others$mircore_packRelease(boolean z) {
        this.schedule_skip_others = z;
    }

    public final boolean scheduleTestSkipOthers(boolean skip) {
        boolean z;
        synchronized (Boolean.valueOf(this.schedule_skip_others)) {
            this.schedule_skip_others = skip;
            z = this.schedule_skip_others;
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object createLinker(Context context, Continuation<? super Unit> continuation) {
        HardwareLinker$createLinker$1 hardwareLinker$createLinker$1;
        int i;
        HardwareLinker hardwareLinker;
        HardwareLinker hardwareLinker2;
        if (continuation instanceof HardwareLinker$createLinker$1) {
            hardwareLinker$createLinker$1 = (HardwareLinker$createLinker$1) continuation;
            if ((hardwareLinker$createLinker$1.label & Integer.MIN_VALUE) != 0) {
                hardwareLinker$createLinker$1.label -= Integer.MIN_VALUE;
                HardwareLinker$createLinker$1 hardwareLinker$createLinker$12 = hardwareLinker$createLinker$1;
                Object obj = hardwareLinker$createLinker$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hardwareLinker$createLinker$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    AIDLConnection<HardwareInterface> aIDLConnection = this.hardwareConnection;
                    hardwareLinker$createLinker$12.L$0 = this;
                    hardwareLinker$createLinker$12.L$1 = context;
                    hardwareLinker$createLinker$12.L$2 = this;
                    hardwareLinker$createLinker$12.label = 1;
                    obj = AIDLConnection.connect$default(aIDLConnection, context, null, hardwareLinker$createLinker$12, 2, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hardwareLinker = this;
                    hardwareLinker2 = hardwareLinker;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    hardwareLinker = (HardwareLinker) hardwareLinker$createLinker$12.L$2;
                    hardwareLinker2 = (HardwareLinker) hardwareLinker$createLinker$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                hardwareLinker.connectStatus = ((Boolean) obj).booleanValue();
                if (!hardwareLinker2.connectStatus) {
                    hardwareLinker2.hardwareService = hardwareLinker2.hardwareConnection.getInterface();
                } else {
                    Pdlog.m3274e(hardwareLinker2.TAG, "Link to Hardware Service Failed");
                }
                return Unit.INSTANCE;
            }
        }
        hardwareLinker$createLinker$1 = new HardwareLinker$createLinker$1(this, continuation);
        HardwareLinker$createLinker$1 hardwareLinker$createLinker$122 = hardwareLinker$createLinker$1;
        Object obj2 = hardwareLinker$createLinker$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hardwareLinker$createLinker$122.label;
        if (i != 0) {
        }
        hardwareLinker.connectStatus = ((Boolean) obj2).booleanValue();
        if (!hardwareLinker2.connectStatus) {
        }
        return Unit.INSTANCE;
    }

    public final boolean addLinker() {
        ScheduleCommunication scheduler;
        if (this.connectStatus) {
            try {
                Pdlog.m3275i(this.TAG, "connect to hardware service success");
                HardwareInterface hardwareInterface = this.hardwareConnection.getInterface();
                if (hardwareInterface != null && (scheduler = hardwareInterface.getScheduler()) != null) {
                    scheduler.addScheduleInfoProducer("Core_Data", this.scheduleProducer);
                }
                HardwareInterface hardwareInterface2 = this.hardwareConnection.getInterface();
                if (hardwareInterface2 != null) {
                    hardwareInterface2.addAccelerationListener(AlcsConstant.METHOD_DOMAIN_CORE, this.accListener);
                }
                Pdlog.m3275i(this.TAG, String.valueOf(this.hardwareConnection.getInterface()));
                BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new HardwareLinker$addLinker$1(this, null), 3, null);
                return true;
            } catch (Exception e) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("exception msg: ");
                sb.append(e.getLocalizedMessage());
                sb.append(", ex: ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3274e(str, sb.toString());
                return false;
            }
        }
        Pdlog.m3274e(this.TAG, "connect to hardware service fail");
        return false;
    }

    public final void removeLinker() {
        ScheduleCommunication scheduler;
        HardwareInterface hardwareInterface = this.hardwareConnection.getInterface();
        if (hardwareInterface != null && (scheduler = hardwareInterface.getScheduler()) != null) {
            scheduler.removeScheduleInfoProducer("Core_Data");
        }
        BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new HardwareLinker$removeLinker$1(this, null), 3, null);
    }

    public final boolean isRGBDEnabled() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = this.hardwareConnection.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return false;
        }
        return rGBDInterface.isEnabled();
    }

    public final void setRgbdParkingMode(boolean into_park) {
        HardwareInterface hardwareInterface;
        HardwareInterface hardwareInterface2 = this.hardwareConnection.getInterface();
        MachineInfo machineInfo = hardwareInterface2 != null ? hardwareInterface2.getMachineInfo() : null;
        if (machineInfo == null) {
            Intrinsics.throwNpe();
        }
        if (machineInfo.getRGBDMode() == MachineInfo.RGBDType.NODevice || (hardwareInterface = this.hardwareConnection.getInterface()) == null) {
            return;
        }
        hardwareInterface.setRgbdParkingMode(into_park);
    }

    public final void setAccParam(AccelerationType acc_type, double value) {
        HardwareInterface hardwareInterface;
        Intrinsics.checkParameterIsNotNull(acc_type, "acc_type");
        Double d = this.accStatus.get(acc_type);
        Double valueOf = d != null ? Double.valueOf(Math.abs(d.doubleValue() - value)) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        if (valueOf.doubleValue() >= 0.01d && (hardwareInterface = this.hardwareConnection.getInterface()) != null) {
            hardwareInterface.setAccelerationData(acc_type, value);
        }
    }

    public final boolean getConnectStatus() {
        return this.connectStatus;
    }

    public final HardwareInterface getHardwareService() {
        return this.hardwareConnection.getInterface();
    }

    public final void destroy(Context context) {
        ScheduleCommunication scheduler;
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (this.connectStatus) {
            this.connectStatus = false;
            HardwareInterface hardwareInterface = this.hardwareConnection.getInterface();
            if (hardwareInterface != null && (scheduler = hardwareInterface.getScheduler()) != null) {
                scheduler.removeScheduleInfoProducer("Core_Data");
            }
            HardwareInterface hardwareInterface2 = this.hardwareConnection.getInterface();
            if (hardwareInterface2 != null) {
                hardwareInterface2.removeAccelerationListener(AlcsConstant.METHOD_DOMAIN_CORE);
            }
            this.hardwareConnection.disconnect(context);
            this.hardwareService = (HardwareInterface) null;
            Pdlog.m3277w(this.TAG, "finish leave hardware connection");
        }
    }

    public final boolean setExposure(int exposure) {
        CameraInterface camera;
        HardwareInterface hardwareInterface = this.hardwareConnection.getInterface();
        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
            return false;
        }
        return camera.setCameraExposure(exposure);
    }

    public final int getExposure() {
        CameraInterface camera;
        HardwareInterface hardwareInterface = this.hardwareConnection.getInterface();
        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
            return 0;
        }
        return camera.getCameraExposure();
    }

    public final void resetLocalizationModuleInited(boolean flag) {
        this.localizationModuleInited = flag;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendScheduleInfo() {
        Vector3d position$mircore_packRelease;
        Vector2d speeds$mircore_packRelease;
        ScheduleCommunication scheduler;
        if (!this.localizationModuleInited && MirCoreImpl.INSTANCE.getLocalization().getLocalizationStatus().getStatus_level() != LocalizationStatusLevel.Error) {
            this.localizationModuleInited = true;
        }
        if (this.localizationModuleInited) {
            RobotScheduleInfo currentRobotInfo = ScheduleMaster.INSTANCE.getCurrentRobotInfo();
            synchronized (MirCoreImpl.INSTANCE.getPosition$mircore_packRelease()) {
                position$mircore_packRelease = MirCoreImpl.INSTANCE.getPosition$mircore_packRelease();
            }
            synchronized (MirCoreImpl.INSTANCE.getSpeeds$mircore_packRelease()) {
                speeds$mircore_packRelease = MirCoreImpl.INSTANCE.getSpeeds$mircore_packRelease();
            }
            currentRobotInfo.getPose().setX(position$mircore_packRelease.getX());
            currentRobotInfo.getPose().setY(position$mircore_packRelease.getY());
            currentRobotInfo.getPose().setZ(position$mircore_packRelease.getZ());
            currentRobotInfo.getVelocity().setX(speeds$mircore_packRelease.getX());
            currentRobotInfo.getVelocity().setY(speeds$mircore_packRelease.getY());
            HardwareInterface hardwareInterface = this.hardwareConnection.getInterface();
            if (hardwareInterface != null && (scheduler = hardwareInterface.getScheduler()) != null) {
                scheduler.sendRobotScheduleCommunicationInfo(currentRobotInfo);
            }
            ScheduleMaster.INSTANCE.setRobotPose(position$mircore_packRelease.getX(), position$mircore_packRelease.getY());
            if (currentRobotInfo.getScheduling_mode() == SchedulingMode.Arrived || currentRobotInfo.getScheduling_mode() == SchedulingMode.Free) {
                BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new HardwareLinker$sendScheduleInfo$1(this, currentRobotInfo, null), 3, null);
            }
        }
    }
}
