package com.pudutech.mirsdk.movetask;

import android.content.Context;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.gatecontrollerlib.GateController;
import com.pudutech.gatecontrollerlib.GateControllerListener;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.base.SDKRobotStateKt;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.elements.ElevatorWaiter;
import com.pudutech.mirsdk.map.elements.FireFoxGateSource;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.GatePassTask;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: GatePassTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u00012\u00020\u0002:\u0004\\]^_B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u00107\u001a\u00020\u001aH\u0002J\u0014\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u001a09H\u0016J\u0006\u0010;\u001a\u00020<J\b\u0010=\u001a\u0004\u0018\u00010>J\u000e\u0010?\u001a\u0002062\u0006\u0010@\u001a\u00020AJ\u0006\u0010B\u001a\u00020\u001aJ\u0010\u0010C\u001a\u0002062\u0006\u0010D\u001a\u00020\u001aH\u0002J\u0010\u0010E\u001a\u0002062\b\b\u0002\u0010F\u001a\u00020\u001aJ\u000e\u0010G\u001a\u0002062\u0006\u0010H\u001a\u00020IJ\u0018\u0010J\u001a\u0002062\u0006\u0010K\u001a\u00020\n2\u0006\u0010L\u001a\u00020MH\u0016J\u0018\u0010N\u001a\u0002062\u0006\u0010K\u001a\u00020\n2\u0006\u0010O\u001a\u00020\u0012H\u0016J\u0018\u0010P\u001a\u00020Q2\u0006\u0010H\u001a\u00020R2\u0006\u0010S\u001a\u00020\nH\u0016J\u0012\u0010T\u001a\u0002062\b\b\u0002\u0010U\u001a\u00020\u0012H\u0002J\u0006\u0010V\u001a\u000206J\b\u0010W\u001a\u000206H\u0016J\b\u0010X\u001a\u000206H\u0016J\b\u0010Y\u001a\u000206H\u0016J\b\u0010Z\u001a\u000206H\u0016J\u0006\u0010[\u001a\u000206R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001c\"\u0004\b%\u0010\u001eR\u001a\u0010&\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\u001a\u0010(\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001c\"\u0004\b*\u0010\u001eR\u0012\u0010+\u001a\u00060,R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001c\"\u0004\b/\u0010\u001eR\u001a\u00100\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0014\"\u0004\b2\u0010\u0016R\u001a\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002060504X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GatePassTask;", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "Lcom/pudutech/gatecontrollerlib/GateControllerListener;", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;)V", "TAG", "", "kotlin.jvm.PlatformType", "accessControlListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/AccessDoorListener;", "getAccessControlListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "connectTimeout", "", "getConnectTimeout", "()I", "setConnectTimeout", "(I)V", "gateController", "Lcom/pudutech/gatecontrollerlib/GateController;", "gateDir", "", "getGateDir", "()Z", "setGateDir", "(Z)V", "gateMAC", "getGateMAC", "()Ljava/lang/String;", "setGateMAC", "(Ljava/lang/String;)V", "isError", "setError", "isPause", "setPause", "manualMode", "getManualMode", "setManualMode", "moveState", "Lcom/pudutech/mirsdk/movetask/GatePassTask$MoveState;", "realArrive", "getRealArrive", "setRealArrive", "sendFailTime", "getSendFailTime", "setSendFailTime", "stateNoticeQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lkotlin/Function0;", "", "checkManualMode", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "distanceFromGate", "", "findDestination", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "initGateController", "context", "Landroid/content/Context;", "isPauseable", "lockWheel", "isLock", "moveToPoint", "resetSchedule", "notifyListener", "state", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "onCommandResult", "mac", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/gatecontrollerlib/GateControllerMsg;", "onConnectStateChange", "connectState", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "openGateWithDir", "openTime", "passAccessDoor", "pause", "resume", "startMoveAction", "taskStop", "updateGateDir", "ApproachingGate", "MoveState", "PassGate", "StopBeforeGate", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class GatePassTask extends MoveTask implements GateControllerListener {
    private final String TAG;
    private final ThreadSafeListener<AccessDoorListener> accessControlListener;
    private int connectTimeout;
    private GateController gateController;
    private boolean gateDir;
    private String gateMAC;
    private boolean isError;
    private boolean isPause;
    private boolean manualMode;
    private MoveState moveState;
    private boolean realArrive;
    private int sendFailTime;
    private final ArrayBlockingQueue<Function0<Unit>> stateNoticeQueue;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BusinessType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[BusinessType.GoCharging.ordinal()] = 1;
            $EnumSwitchMapping$0[BusinessType.GoGroup.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[BusinessType.values().length];
            $EnumSwitchMapping$1[BusinessType.GoGroup.ordinal()] = 1;
        }
    }

    public static final /* synthetic */ GateController access$getGateController$p(GatePassTask gatePassTask) {
        GateController gateController = gatePassTask.gateController;
        if (gateController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gateController");
        }
        return gateController;
    }

    public static final /* synthetic */ MoveState access$getMoveState$p(GatePassTask gatePassTask) {
        MoveState moveState = gatePassTask.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        return moveState;
    }

    @Override // com.pudutech.gatecontrollerlib.GateControllerListener
    public void onData(String mac, byte[] data) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(data, "data");
        GateControllerListener.DefaultImpls.onData(this, mac, data);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GatePassTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService) {
        super(action, coreService, null, null, null, false, 60, null);
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        this.gateMAC = "";
        this.accessControlListener = new ThreadSafeListener<>();
        this.TAG = GatePassTask.class.getSimpleName();
        this.stateNoticeQueue = new ArrayBlockingQueue<>(10);
    }

    public final String getGateMAC() {
        return this.gateMAC;
    }

    public final void setGateMAC(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.gateMAC = str;
    }

    /* renamed from: isPause, reason: from getter */
    public final boolean getIsPause() {
        return this.isPause;
    }

    public final void setPause(boolean z) {
        this.isPause = z;
    }

    /* renamed from: isError, reason: from getter */
    public final boolean getIsError() {
        return this.isError;
    }

    public final void setError(boolean z) {
        this.isError = z;
    }

    public final boolean getRealArrive() {
        return this.realArrive;
    }

    public final void setRealArrive(boolean z) {
        this.realArrive = z;
    }

    public final boolean getManualMode() {
        return this.manualMode;
    }

    public final void setManualMode(boolean z) {
        this.manualMode = z;
    }

    public final ThreadSafeListener<AccessDoorListener> getAccessControlListener() {
        return this.accessControlListener;
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void startMoveAction() {
        this.isPause = false;
        this.isError = false;
        this.realArrive = false;
        Regex regex = new Regex("([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}");
        if (!checkManualMode() && !regex.matches(this.gateMAC)) {
            Pdlog.m3274e(this.TAG, "Mac error and not in manual mode " + this.gateMAC);
            getAction().informStateChange(RobotState.Error, "{\"error_type\":\"GatePassError\", \"level\":\"Error\",\"detail\":\"Mac error and not in manual mode\"}");
            return;
        }
        Pdlog.m3275i(this.TAG, "startMoveAction");
        if (getPathSegments() == null) {
            getAction().informStateChange(RobotState.Error, "{\"error_type\":\"GatePassError\", \"level\":\"Error\",\"detail\":\"pathSegments is null\"}");
            return;
        }
        this.moveState = new ApproachingGate();
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a3  */
    @Override // com.pudutech.gatecontrollerlib.GateControllerListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCommandResult(String mac, GateControllerMsg msg) {
        MoveState moveState;
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!StringsKt.equals(mac, this.gateMAC, true)) {
            Pdlog.m3277w(this.TAG, "onCommandResult mac: " + mac + " is not gateMac: " + this.gateMAC + ", msg = " + msg);
            return;
        }
        MoveState moveState2 = this.moveState;
        if (moveState2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!(moveState2 instanceof ApproachingGate)) {
            MoveState moveState3 = this.moveState;
            if (moveState3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            if (!(moveState3 instanceof StopBeforeGate) || msg.getErrorCode() != GateControllerMsg.ErrorType.Success || msg.getCMD() != 51 || this.isPause) {
                if (this.isPause) {
                    Pdlog.m3275i(this.TAG, "robot is pause");
                }
                String str = this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("current state is  ");
                moveState = this.moveState;
                if (moveState == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveState");
                }
                sb.append(moveState);
                objArr[0] = sb.toString();
                Pdlog.m3275i(str, objArr);
            }
        }
        this.moveState = new PassGate();
        MoveState moveState4 = this.moveState;
        if (moveState4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState4.start();
        String str2 = this.TAG;
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("current state is  ");
        moveState = this.moveState;
        if (moveState == null) {
        }
        sb2.append(moveState);
        objArr2[0] = sb2.toString();
        Pdlog.m3275i(str2, objArr2);
    }

    private final boolean checkManualMode() {
        this.manualMode = new Regex("[A-Za-z]{3,}[:][A-Za-z0-9]*").matches(this.gateMAC);
        return this.manualMode;
    }

    @Override // com.pudutech.gatecontrollerlib.GateControllerListener
    public void onConnectStateChange(String mac, int connectState) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        if (!StringsKt.equals(mac, this.gateMAC, true)) {
            Pdlog.m3277w(this.TAG, "onConnectStateChange mac: " + mac + " is not gateMac: " + this.gateMAC + ", connectState = " + connectState);
            return;
        }
        Pdlog.m3273d(this.TAG, "onConnectStateChange + " + connectState);
        if (connectState == 2) {
            this.connectTimeout = 0;
        }
    }

    public final void notifyListener(final AccessDoorControlState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.accessControlListener.notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.GatePassTask$notifyListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str) {
                invoke2(accessDoorListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AccessDoorListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.informAccessDoorControlState(state, GatePassTask.this.getGateMAC());
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
    
        if (r6.isPause == false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void passAccessDoor() {
        MoveState moveState;
        MoveState moveState2 = this.moveState;
        if (moveState2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!(moveState2 instanceof ApproachingGate)) {
            MoveState moveState3 = this.moveState;
            if (moveState3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            if (moveState3 instanceof StopBeforeGate) {
            }
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("current state is  ");
            moveState = this.moveState;
            if (moveState == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            sb.append(moveState);
            objArr[0] = sb.toString();
            Pdlog.m3275i(str, objArr);
        }
        this.moveState = new PassGate();
        MoveState moveState4 = this.moveState;
        if (moveState4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState4.start();
        String str2 = this.TAG;
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("current state is  ");
        moveState = this.moveState;
        if (moveState == null) {
        }
        sb2.append(moveState);
        objArr2[0] = sb2.toString();
        Pdlog.m3275i(str2, objArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void lockWheel(boolean isLock) {
        Pdlog.m3273d(this.TAG, "lockWheel isLock:" + isLock);
        getAction().lockWheel(isLock);
    }

    public final double distanceFromGate() {
        FireFoxGateSource fireFoxGateSource = getAction().getAtlas().getMapDecode().getFireFoxGates().get(this.gateMAC);
        if (fireFoxGateSource == null) {
            Intrinsics.throwNpe();
        }
        Vector3d pose = fireFoxGateSource.getPose();
        Vector3d curRobotPose = getAction().getCurRobotPose();
        return Math.sqrt(((pose.getX() - curRobotPose.getX()) * (pose.getX() - curRobotPose.getX())) + ((pose.getY() - curRobotPose.getY()) * (pose.getY() - curRobotPose.getY())));
    }

    public final boolean getGateDir() {
        return this.gateDir;
    }

    public final void setGateDir(boolean z) {
        this.gateDir = z;
    }

    public final void updateGateDir() {
        FireFoxGateSource fireFoxGateSource = getAction().getAtlas().getMapDecode().getFireFoxGates().get(this.gateMAC);
        if (fireFoxGateSource == null) {
            Intrinsics.throwNpe();
        }
        Vector3d pose = fireFoxGateSource.getPose();
        Vector3d curRobotPose = getAction().getCurRobotPose();
        if (pose.getZ() >= 6.283185307179586d || pose.getZ() <= -6.283185307179586d) {
            pose.setZ(pose.getZ() % 6.283185307179586d);
        }
        double z = pose.getZ() - Math.atan2(pose.getY() - curRobotPose.getY(), pose.getX() - curRobotPose.getX());
        this.gateDir = z > 1.5707963267948966d || z < -1.5707963267948966d;
    }

    public final int getSendFailTime() {
        return this.sendFailTime;
    }

    public final void setSendFailTime(int i) {
        this.sendFailTime = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void openGateWithDir$default(GatePassTask gatePassTask, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 3;
        }
        gatePassTask.openGateWithDir(i);
    }

    private final void openGateWithDir(int openTime) {
        GateController gateController = this.gateController;
        if (gateController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gateController");
        }
        if (gateController.openGate(this.gateMAC, openTime, this.gateDir)) {
            this.sendFailTime = 0;
            return;
        }
        this.sendFailTime++;
        if (this.sendFailTime >= 5) {
            Pdlog.m3274e(this.TAG, "send data fail try to reconnect");
            GateController gateController2 = this.gateController;
            if (gateController2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gateController");
            }
            gateController2.disconnect(this.gateMAC);
            this.sendFailTime = 0;
        }
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public RobotState onStateChange(SDKRobotState state, final String desc) {
        final RobotState second;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        Pair<MoveState, RobotState> mo4467switch = moveState.mo4467switch(state);
        if (state == SDKRobotState.Error) {
            this.isPause = true;
            BuildersKt__BuildersKt.runBlocking$default(null, new GatePassTask$onStateChange$1(this, null), 1, null);
            GateController gateController = this.gateController;
            if (gateController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gateController");
            }
            gateController.disconnect(this.gateMAC);
            lockWheel(false);
            if (!isPauseable()) {
                notifyListener(AccessDoorControlState.StopAccessDoor);
            }
        }
        if (mo4467switch.getSecond() == RobotState.Approaching) {
            second = RobotState.Moving;
        } else if (mo4467switch.getSecond() == RobotState.Arrive && !this.realArrive) {
            second = RobotState.Moving;
        } else {
            second = mo4467switch.getSecond();
        }
        this.stateNoticeQueue.put(new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.GatePassTask$onStateChange$2
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
                GatePassTask.this.getAction().informStateChange(second, desc);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new GatePassTask$onStateChange$3(this, null), 2, null);
        return mo4467switch.getSecond();
    }

    public final boolean isPauseable() {
        if (this.moveState == null) {
            return true;
        }
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        return !(r0 instanceof PassGate);
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public Pair<NavigationMode, Boolean> checkNavigationMode() {
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (moveState instanceof ApproachingGate) {
            Pdlog.m3275i(this.TAG, "set Navigation Mode to GatePass Mode");
            return new Pair<>(NavigationMode.GatePassMode, true);
        }
        if (moveState instanceof StopBeforeGate) {
            Pdlog.m3275i(this.TAG, "set Navigation Mode to stop Mode");
            return new Pair<>(NavigationMode.StopMode, false);
        }
        if (moveState instanceof PassGate) {
            Pdlog.m3275i(this.TAG, "set Navigation Mode to GatePass Mode");
            return new Pair<>(NavigationMode.GatePassMode, true);
        }
        return new Pair<>(NavigationMode.Normal, false);
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void taskStop() {
        super.taskStop();
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.pause();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Object] */
    public final Destination findDestination() {
        Destination destination;
        Object obj;
        Destination destination2;
        List<Destination> destinations = getAction().getDestinations();
        if (destinations != null) {
            Iterator it = destinations.iterator();
            while (true) {
                if (!it.hasNext()) {
                    destination2 = 0;
                    break;
                }
                destination2 = it.next();
                String name = ((Destination) destination2).getName();
                PathSegments pathSegments = getPathSegments();
                if (Intrinsics.areEqual(name, pathSegments != null ? pathSegments.getFinalGoal() : null)) {
                    break;
                }
            }
            destination = destination2;
        } else {
            destination = null;
        }
        if (destination != null) {
            return destination;
        }
        Iterator it2 = getAction().getElevWaiters().iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            String id = ((ElevatorWaiter) obj).getId();
            PathSegments pathSegments2 = getPathSegments();
            if (Intrinsics.areEqual(id, pathSegments2 != null ? pathSegments2.getFinalGoal() : null)) {
                break;
            }
        }
        ElevatorWaiter elevatorWaiter = (ElevatorWaiter) obj;
        if (elevatorWaiter != null) {
            return new Destination(elevatorWaiter.getFloor(), elevatorWaiter.getId(), elevatorWaiter.getPose().getX(), elevatorWaiter.getPose().getY(), elevatorWaiter.getPose().getZ(), false, "", "", false);
        }
        return null;
    }

    public static /* synthetic */ void moveToPoint$default(GatePassTask gatePassTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        gatePassTask.moveToPoint(z);
    }

    public final void moveToPoint(final boolean resetSchedule) {
        Pdlog.m3273d(this.TAG, "send target again");
        PathSegments pathSegments = getPathSegments();
        List<PathSegment> segments = pathSegments != null ? pathSegments.getSegments() : null;
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        final Vector3d pose = segments.get(0).getPose();
        getAction().moveToPosition(getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.GatePassTask$moveToPoint$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                Boolean valueOf;
                ScheduleInterface scheduler;
                ScheduleInterface scheduler2;
                MirCoreInterface mirCoreInterface = GatePassTask.this.getCoreService().getInterface();
                Boolean bool = null;
                NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                int i = GatePassTask.WhenMappings.$EnumSwitchMapping$0[GatePassTask.this.getBusinessType().ordinal()];
                if (i == 1) {
                    if (navigator != null) {
                        valueOf = Boolean.valueOf(navigator.prepareMoveToChargeTask());
                    }
                    valueOf = null;
                } else if (i != 2) {
                    if (navigator != null) {
                        valueOf = Boolean.valueOf(navigator.prepareDeliverTask(false, false));
                    }
                    valueOf = null;
                } else {
                    if (navigator != null) {
                        valueOf = Boolean.valueOf(navigator.prepareGoHomeTask());
                    }
                    valueOf = null;
                }
                Boolean bool2 = true;
                if (resetSchedule) {
                    if (GatePassTask.WhenMappings.$EnumSwitchMapping$1[GatePassTask.this.getBusinessType().ordinal()] == 1) {
                        com.pudutech.mirsdk.mircore.coreparcel.BusinessType businessType = com.pudutech.mirsdk.mircore.coreparcel.BusinessType.GoHome;
                        if (GatePassTask.this.getIsFillIn()) {
                            businessType = com.pudutech.mirsdk.mircore.coreparcel.BusinessType.WaitTake;
                        }
                        Pdlog.m3273d(GatePassTask.this.TAG, " go home task status isFillIn: " + GatePassTask.this.getIsFillIn() + "  , business_type : " + businessType.name());
                        MirCoreInterface mirCoreInterface2 = GatePassTask.this.getCoreService().getInterface();
                        if (mirCoreInterface2 != null && (scheduler = mirCoreInterface2.getScheduler()) != null) {
                            Destination findDestination = GatePassTask.this.findDestination();
                            if (findDestination == null) {
                                Intrinsics.throwNpe();
                            }
                            bool = Boolean.valueOf(scheduler.prepareGoHomeTask(findDestination.getGroup(), businessType));
                        }
                    } else {
                        MirCoreInterface mirCoreInterface3 = GatePassTask.this.getCoreService().getInterface();
                        if (mirCoreInterface3 != null && (scheduler2 = mirCoreInterface3.getScheduler()) != null) {
                            bool = Boolean.valueOf(scheduler2.prepareDeliverTask(new Vector3d(pose.getX(), pose.getY(), pose.getZ())));
                        }
                    }
                    bool2 = bool;
                }
                return Intrinsics.areEqual((Object) valueOf, (Object) true) && Intrinsics.areEqual((Object) bool2, (Object) true);
            }
        });
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void pause() {
        GateController gateController = this.gateController;
        if (gateController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gateController");
        }
        gateController.disconnect(this.gateMAC);
        lockWheel(false);
        this.isPause = true;
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.pause();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void resume() {
        if (isPauseable()) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("gate task resume, and movestate is ");
            MoveState moveState = this.moveState;
            if (moveState == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            sb.append(moveState);
            sb.append(' ');
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            this.isPause = false;
            this.isError = false;
            MoveState moveState2 = this.moveState;
            if (moveState2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            moveState2.resume();
            return;
        }
        String str2 = this.TAG;
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("gate task can not resume, because movestate is ");
        MoveState moveState3 = this.moveState;
        if (moveState3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        sb2.append(moveState3);
        sb2.append(' ');
        objArr2[0] = sb2.toString();
        Pdlog.m3273d(str2, objArr2);
        this.stateNoticeQueue.put(new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.GatePassTask$resume$1
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
                GatePassTask.this.getAction().informStateChange(RobotState.Arrive, "gate pass task is finished");
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new GatePassTask$resume$2(this, null), 2, null);
    }

    public final void initGateController(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.gateController = new GateController(context);
        GateController gateController = this.gateController;
        if (gateController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gateController");
        }
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        gateController.addBluetoothBleListeners(TAG, this);
    }

    /* compiled from: GatePassTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH&J\b\u0010\f\u001a\u00020\nH\u0016J \u0010\r\u001a\u0012\u0012\b\u0012\u00060\u0000R\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H&R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GatePassTask$MoveState;", "", "(Lcom/pudutech/mirsdk/movetask/GatePassTask;)V", "currentJob", "Lkotlinx/coroutines/Job;", "getCurrentJob", "()Lkotlinx/coroutines/Job;", "setCurrentJob", "(Lkotlinx/coroutines/Job;)V", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/movetask/GatePassTask;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public abstract class MoveState {
        private Job currentJob;

        public abstract void resume();

        /* renamed from: switch */
        public abstract Pair<MoveState, RobotState> mo4467switch(SDKRobotState robotState);

        public MoveState() {
        }

        public final Job getCurrentJob() {
            return this.currentJob;
        }

        public final void setCurrentJob(Job job) {
            this.currentJob = job;
        }

        public void start() {
            BuildersKt__BuildersKt.runBlocking$default(null, new GatePassTask$MoveState$start$1(this, null), 1, null);
        }

        public void pause() {
            BuildersKt__BuildersKt.runBlocking$default(null, new GatePassTask$MoveState$pause$1(this, null), 1, null);
        }
    }

    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    public final void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GatePassTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J \u0010\b\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GatePassTask$ApproachingGate;", "Lcom/pudutech/mirsdk/movetask/GatePassTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/GatePassTask;", "(Lcom/pudutech/mirsdk/movetask/GatePassTask;)V", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class ApproachingGate extends MoveState {

        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Moving.ordinal()] = 3;
            }
        }

        public ApproachingGate() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void start() {
            Job launch$default;
            super.start();
            Pdlog.m3273d(GatePassTask.this.TAG, "start Approaching Gate");
            long elapsedRealtime = SystemClock.elapsedRealtime();
            GatePassTask.this.updateGateDir();
            GatePassTask.this.notifyListener(AccessDoorControlState.CallingAccessDoor);
            GatePassTask.this.setConnectTimeout(0);
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GatePassTask$ApproachingGate$start$1(this, elapsedRealtime, null), 3, null);
            setCurrentJob(launch$default);
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void pause() {
            Pdlog.m3273d(GatePassTask.this.TAG, "pause when Approaching Gate");
            super.pause();
            GatePassTask.this.getAction().actionPause();
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void resume() {
            GatePassTask.this.getAction().actionResume();
            start();
            Pdlog.m3273d(GatePassTask.this.TAG, "resume when Approaching Gate");
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        /* renamed from: switch, reason: not valid java name */
        public Pair<MoveState, RobotState> mo4467switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(GatePassTask.this.TAG, "switch robot state: " + robotState + " in " + ApproachingGate.class.getSimpleName());
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1) {
                GatePassTask.this.setRealArrive(true);
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (i == 2) {
                return new Pair<>(this, RobotState.Moving);
            }
            if (i == 3) {
                if (GatePassTask.this.getIsError()) {
                    return new Pair<>(this, RobotState.Arrive);
                }
                return new Pair<>(this, RobotState.Moving);
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GatePassTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J \u0010\b\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GatePassTask$StopBeforeGate;", "Lcom/pudutech/mirsdk/movetask/GatePassTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/GatePassTask;", "(Lcom/pudutech/mirsdk/movetask/GatePassTask;)V", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class StopBeforeGate extends MoveState {

        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Moving.ordinal()] = 3;
            }
        }

        public StopBeforeGate() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void start() {
            Job launch$default;
            super.start();
            GatePassTask.this.lockWheel(true);
            Pdlog.m3273d(GatePassTask.this.TAG, "start stop before gate");
            GatePassTask.this.setConnectTimeout(0);
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GatePassTask$StopBeforeGate$start$1(this, null), 3, null);
            setCurrentJob(launch$default);
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void pause() {
            Pdlog.m3273d(GatePassTask.this.TAG, "pause when stop before Gate");
            super.pause();
            GatePassTask.this.lockWheel(false);
            GatePassTask.this.getAction().actionPause();
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void resume() {
            Pdlog.m3273d(GatePassTask.this.TAG, "resume when stop before Gate");
            GatePassTask.this.getAction().actionResume();
            start();
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4467switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(GatePassTask.this.TAG, "switch robot state: " + robotState + " in " + StopBeforeGate.class.getSimpleName());
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1) {
                GatePassTask.this.setRealArrive(true);
                return new Pair<>(this, RobotState.Moving);
            }
            if (i == 2 || i == 3) {
                if (GatePassTask.this.getIsError()) {
                    return new Pair<>(this, RobotState.Arrive);
                }
                return new Pair<>(this, RobotState.Moving);
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GatePassTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J \u0010\r\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GatePassTask$PassGate;", "Lcom/pudutech/mirsdk/movetask/GatePassTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/GatePassTask;", "(Lcom/pudutech/mirsdk/movetask/GatePassTask;)V", "isLeaveGate", "", "()Z", "setLeaveGate", "(Z)V", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class PassGate extends MoveState {
        private boolean isLeaveGate;

        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Moving.ordinal()] = 3;
            }
        }

        public PassGate() {
            super();
        }

        /* renamed from: isLeaveGate, reason: from getter */
        public final boolean getIsLeaveGate() {
            return this.isLeaveGate;
        }

        public final void setLeaveGate(boolean z) {
            this.isLeaveGate = z;
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void start() {
            Job launch$default;
            super.start();
            GatePassTask.this.lockWheel(false);
            GatePassTask.this.notifyListener(AccessDoorControlState.MovingToAccessDoor);
            GatePassTask.moveToPoint$default(GatePassTask.this, false, 1, null);
            Pdlog.m3273d(GatePassTask.this.TAG, "start Pass Gate");
            MirCoreInterface mirCoreInterface = GatePassTask.this.getCoreService().getInterface();
            NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
            if (navigator == null) {
                Intrinsics.throwNpe();
            }
            FireFoxGateSource fireFoxGateSource = GatePassTask.this.getAction().getAtlas().getMapDecode().getFireFoxGates().get(GatePassTask.this.getGateMAC());
            if (fireFoxGateSource == null) {
                Intrinsics.throwNpe();
            }
            navigator.updateGateLimitSpeed(fireFoxGateSource.getUp_speed());
            String str = GatePassTask.this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("set speed to ");
            FireFoxGateSource fireFoxGateSource2 = GatePassTask.this.getAction().getAtlas().getMapDecode().getFireFoxGates().get(GatePassTask.this.getGateMAC());
            if (fireFoxGateSource2 == null) {
                Intrinsics.throwNpe();
            }
            sb.append(fireFoxGateSource2.getUp_speed());
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GatePassTask$PassGate$start$1(this, null), 3, null);
            setCurrentJob(launch$default);
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void pause() {
            super.pause();
            Pdlog.m3273d(GatePassTask.this.TAG, "pause when Pass Gate");
            GatePassTask.this.getAction().actionPause();
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        public void resume() {
            Pdlog.m3273d(GatePassTask.this.TAG, "resume when Pass Gate");
            GatePassTask.this.getAction().actionResume();
        }

        @Override // com.pudutech.mirsdk.movetask.GatePassTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4467switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(GatePassTask.this.TAG, "switch robot state: " + robotState + " in " + PassGate.class.getSimpleName());
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1) {
                BuildersKt__BuildersKt.runBlocking$default(null, new GatePassTask$PassGate$switch$1(this, null), 1, null);
                GatePassTask.access$getGateController$p(GatePassTask.this).disconnect(GatePassTask.this.getGateMAC());
                GatePassTask.this.notifyListener(AccessDoorControlState.LeavingAccessDoor);
                GatePassTask.this.setRealArrive(true);
                return new Pair<>(this, RobotState.Arrive);
            }
            if (i == 2 || i == 3) {
                if (this.isLeaveGate) {
                    return new Pair<>(this, RobotState.Arrive);
                }
                return new Pair<>(this, RobotState.Moving);
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }
}
