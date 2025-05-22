package com.pudutech.mirsdk.movetask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.serialize.AccessControlServer;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.base.SDKRobotStateKt;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.elements.AccessControlPoint;
import com.pudutech.mirsdk.map.elements.ChargingPile;
import com.pudutech.mirsdk.map.elements.ElevatorWaiter;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.AccessControlTask;
import com.pudutech.mirsdk.robotsdk.AccConnectionInterface;
import com.pudutech.mirsdk.robotsdk.AccLoraConnection;
import com.pudutech.mirsdk.robotsdk.AccMqttConnection;
import com.pudutech.mirsdk.robotsdk.AccROS2Connection;
import com.pudutech.mirsdk.robotsdk.YouDianBody;
import com.pudutech.mirsdk.robotsdk.YouDianMqtt;
import com.pudutech.mqtt.component.client.callback.MessageReceiverListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccessControlTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\u0018\u0000 Q2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0007QRSTUVWB\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0010\u00103\u001a\u0002012\u0006\u00104\u001a\u000205H\u0016J\u0014\u00106\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020507H\u0016J\u001c\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020;2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\u0010\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020\fH\u0002J\u0018\u0010>\u001a\u0002012\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000fH\u0016J\u0018\u0010A\u001a\u00020B2\u0006\u00104\u001a\u00020C2\u0006\u0010D\u001a\u00020\u000fH\u0016J \u0010E\u001a\u0002012\u0006\u0010F\u001a\u00020\u000f2\u0006\u0010G\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020IH\u0016J\b\u0010J\u001a\u000201H\u0016J\b\u0010K\u001a\u00020LH\u0002J\b\u0010M\u001a\u000201H\u0016J\b\u0010N\u001a\u000201H\u0016J\u0010\u0010O\u001a\u0002012\b\u0010P\u001a\u0004\u0018\u00010,R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 @FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010(\u001a\u00060)R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000201000/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "Lcom/pudutech/mqtt/component/client/callback/MessageReceiverListener;", "Lcom/pudutech/mirsdk/robotsdk/AccLoraConnection$AccLoraListener;", "Lcom/pudutech/mirsdk/robotsdk/AccROS2Connection$AccROS2MsgInterface;", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;)V", "CALL_ERRTIME", "", "CALL_OVERTIME", "TAG", "", "kotlin.jvm.PlatformType", "accessControlClient", "Lcom/pudutech/mirsdk/robotsdk/AccConnectionInterface;", "accessControlCloseJob", "Lkotlinx/coroutines/Job;", "accessControlJob", "accessControlListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/AccessDoorListener;", "getAccessControlListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "accessControlPoints", "", "Lcom/pudutech/mirsdk/map/elements/AccessControlPoint;", "acrossAccessControlId", ES6Iterator.VALUE_PROPERTY, "", "callDistance", "getCallDistance", "()F", "setCallDistance", "(F)V", "gson", "Lcom/google/gson/Gson;", "moveState", "Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveState;", "robotID", "robotPose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "seq", "stateNoticeQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lkotlin/Function0;", "", "taskIndex", "accState", "state", "", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "initClient", "context", "Landroid/content/Context;", "moveToPoint", "segIndex", "onReceiverMessage", "topic", NotificationCompat.CATEGORY_MESSAGE, "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "onStatus", "accId", "robotId", "cmd", "", "pause", "rangeToAccessWaitPoint", "", "resume", "startMoveAction", "updatePose", "pose", "Companion", "GoChargingState", "GoWaiter", "MoveAcrossAccessControl", "MoveState", "MoveToFinalGoal", "WaitAccessControl", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessControlTask extends MoveTask implements MessageReceiverListener, AccLoraConnection.AccLoraListener, AccROS2Connection.AccROS2MsgInterface {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static AccessControlServer accessConnection = AccessControlServer.XinYiLian;
    private static int goChargingTimes;
    private final int CALL_ERRTIME;
    private final int CALL_OVERTIME;
    private final String TAG;
    private AccConnectionInterface accessControlClient;
    private Job accessControlCloseJob;
    private Job accessControlJob;
    private final ThreadSafeListener<AccessDoorListener> accessControlListener;
    private List<AccessControlPoint> accessControlPoints;
    private String acrossAccessControlId;
    private float callDistance;
    private final Gson gson;
    private MoveState moveState;
    private final String robotID;
    private final Vector3d robotPose;
    private int seq;
    private final ArrayBlockingQueue<Function0<Unit>> stateNoticeQueue;
    private int taskIndex;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[AccessControlServer.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$0[AccessControlServer.XinYiLian.ordinal()] = 2;
            $EnumSwitchMapping$0[AccessControlServer.YouDian.ordinal()] = 3;
            $EnumSwitchMapping$0[AccessControlServer.YouDianTest.ordinal()] = 4;
            $EnumSwitchMapping$0[AccessControlServer.Lora.ordinal()] = 5;
            $EnumSwitchMapping$0[AccessControlServer.ROS2.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[AccessControlServer.values().length];
            $EnumSwitchMapping$1[AccessControlServer.YouDian.ordinal()] = 1;
            $EnumSwitchMapping$1[AccessControlServer.YouDianTest.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[BusinessType.values().length];
            $EnumSwitchMapping$2[BusinessType.GoCharging.ordinal()] = 1;
            $EnumSwitchMapping$2[BusinessType.GoGroup.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[BusinessType.values().length];
            $EnumSwitchMapping$3[BusinessType.GoCharging.ordinal()] = 1;
            $EnumSwitchMapping$4 = new int[BusinessType.values().length];
            $EnumSwitchMapping$4[BusinessType.GoGroup.ordinal()] = 1;
        }
    }

    public static final /* synthetic */ AccConnectionInterface access$getAccessControlClient$p(AccessControlTask accessControlTask) {
        AccConnectionInterface accConnectionInterface = accessControlTask.accessControlClient;
        if (accConnectionInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessControlClient");
        }
        return accConnectionInterface;
    }

    public static final /* synthetic */ List access$getAccessControlPoints$p(AccessControlTask accessControlTask) {
        List<AccessControlPoint> list = accessControlTask.accessControlPoints;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessControlPoints");
        }
        return list;
    }

    public static final /* synthetic */ MoveState access$getMoveState$p(AccessControlTask accessControlTask) {
        MoveState moveState = accessControlTask.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        return moveState;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService) {
        super(action, coreService, null, null, null, false, 60, null);
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        this.TAG = AccessControlTask.class.getSimpleName();
        this.accessControlListener = new ThreadSafeListener<>();
        this.stateNoticeQueue = new ArrayBlockingQueue<>(10);
        this.CALL_ERRTIME = 60000;
        this.CALL_OVERTIME = 10000;
        this.taskIndex = -1;
        this.robotPose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.robotID = CommonKt.getWIFIMac();
        this.gson = new Gson();
        this.callDistance = 1.0f;
        float f = SDKConfig.INSTANCE.getPreferences().getFloat("call_access_control_distance", 0.0f);
        setCallDistance(f != 0.0f ? f : 1.0f);
    }

    public final ThreadSafeListener<AccessDoorListener> getAccessControlListener() {
        return this.accessControlListener;
    }

    public final float getCallDistance() {
        return this.callDistance;
    }

    public final void setCallDistance(float f) {
        this.callDistance = f;
        SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
        edit.putFloat("call_access_control_distance", this.callDistance);
        edit.apply();
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
    }

    public final void initClient(Context context, List<AccessControlPoint> accessControlPoints) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(accessControlPoints, "accessControlPoints");
        this.accessControlPoints = accessControlPoints;
        String accessServer = SDKConfig.INSTANCE.getPreferences().getString("access_control_server", AccessControlServer.XinYiLian.name());
        Intrinsics.checkExpressionValueIsNotNull(accessServer, "accessServer");
        accessConnection = AccessControlServer.valueOf(accessServer);
        switch (accessConnection) {
            case CommCat:
            case XinYiLian:
            case YouDian:
            case YouDianTest:
                this.accessControlClient = new AccMqttConnection(context, accessConnection);
                ArrayList arrayList = new ArrayList();
                Iterator<AccessControlPoint> it = accessControlPoints.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getId());
                }
                AccConnectionInterface accConnectionInterface = this.accessControlClient;
                if (accConnectionInterface == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("accessControlClient");
                }
                if (accConnectionInterface == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.robotsdk.AccMqttConnection");
                }
                ((AccMqttConnection) accConnectionInterface).init(this, arrayList);
                return;
            case Lora:
                this.accessControlClient = new AccLoraConnection(context);
                AccConnectionInterface accConnectionInterface2 = this.accessControlClient;
                if (accConnectionInterface2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("accessControlClient");
                }
                if (accConnectionInterface2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.robotsdk.AccLoraConnection");
                }
                ((AccLoraConnection) accConnectionInterface2).connect(this);
                return;
            case ROS2:
                this.accessControlClient = new AccROS2Connection();
                AccConnectionInterface accConnectionInterface3 = this.accessControlClient;
                if (accConnectionInterface3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("accessControlClient");
                }
                if (accConnectionInterface3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.robotsdk.AccROS2Connection");
                }
                ((AccROS2Connection) accConnectionInterface3).connect(this);
                return;
            default:
                return;
        }
    }

    public final void updatePose(Vector3d pose) {
        if (pose == null) {
            return;
        }
        this.robotPose.setX(pose.getX());
        this.robotPose.setY(pose.getY());
        this.robotPose.setZ(pose.getZ());
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void startMoveAction() {
        List<PathSegment> segments;
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("path segments ");
        sb.append(getPathSegments());
        sb.append(" size is ");
        PathSegments pathSegments = getPathSegments();
        sb.append((pathSegments == null || (segments = pathSegments.getSegments()) == null) ? null : Integer.valueOf(segments.size()));
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (getPathSegments() == null) {
            return;
        }
        Pdlog.m3273d(this.TAG, "print robot pose before go waiter (" + this.robotPose.getX() + ", " + this.robotPose.getY() + ')');
        this.taskIndex = 0;
        PathSegments pathSegments2 = getPathSegments();
        if (pathSegments2 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments2 = pathSegments2.getSegments();
        if (segments2 == null) {
            Intrinsics.throwNpe();
        }
        int size = segments2.size();
        for (int i = 0; i < size; i++) {
            String str2 = this.TAG;
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("segment ");
            sb2.append(i);
            sb2.append(" goal ");
            PathSegments pathSegments3 = getPathSegments();
            if (pathSegments3 == null) {
                Intrinsics.throwNpe();
            }
            List<PathSegment> segments3 = pathSegments3.getSegments();
            if (segments3 == null) {
                Intrinsics.throwNpe();
            }
            sb2.append(segments3.get(i).getCurGoal());
            sb2.append(" mode ");
            PathSegments pathSegments4 = getPathSegments();
            if (pathSegments4 == null) {
                Intrinsics.throwNpe();
            }
            List<PathSegment> segments4 = pathSegments4.getSegments();
            if (segments4 == null) {
                Intrinsics.throwNpe();
            }
            sb2.append(segments4.get(i).getNavMode());
            objArr2[0] = sb2.toString();
            Pdlog.m3273d(str2, objArr2);
        }
        this.moveState = new GoWaiter();
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.start();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public RobotState onStateChange(SDKRobotState state, final String desc) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        final Pair<MoveState, RobotState> mo4464switch = moveState.mo4464switch(state);
        this.stateNoticeQueue.put(new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$onStateChange$1
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
                AccessControlTask.this.getAction().informStateChange((RobotState) mo4464switch.getSecond(), desc);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new AccessControlTask$onStateChange$2(this, null), 2, null);
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!Intrinsics.areEqual(r10, mo4464switch.getFirst())) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("switch move state from ");
            MoveState moveState2 = this.moveState;
            if (moveState2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            sb.append(moveState2.getClass().getName());
            sb.append(" to ");
            sb.append(mo4464switch.getFirst().getClass().getName());
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            this.moveState = mo4464switch.getFirst();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$onStateChange$3(this, null), 3, null);
        }
        return mo4464switch.getSecond();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public Pair<NavigationMode, Boolean> checkNavigationMode() {
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (moveState instanceof MoveAcrossAccessControl) {
            return new Pair<>(NavigationMode.AccessControl, false);
        }
        if (!(moveState instanceof WaitAccessControl) && !(moveState instanceof MoveToFinalGoal) && !(moveState instanceof GoChargingState)) {
            return new Pair<>(NavigationMode.Normal, false);
        }
        return new Pair<>(NavigationMode.Normal, true);
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void pause() {
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.pause();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void resume() {
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.resume();
    }

    @Override // com.pudutech.mqtt.component.client.callback.MessageReceiverListener
    public void onReceiverMessage(String topic, String msg) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Pdlog.m3273d(this.TAG, "recv topic " + topic + " with msg " + msg);
        int i = WhenMappings.$EnumSwitchMapping$1[accessConnection.ordinal()];
        if (i == 1 || i == 2) {
            if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "pdrobot/user/door_status", false, 2, (Object) null) && new JSONObject(msg).has("body")) {
                JSONObject jSONObject = new JSONObject(msg).getJSONObject("body");
                if (jSONObject.has("status") && (Intrinsics.areEqual(jSONObject.getString("status"), "close_ack") || Intrinsics.areEqual(jSONObject.getString("status"), "close"))) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$onReceiverMessage$1(this, null), 3, null);
                }
            }
        } else if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "returnstatus", false, 2, (Object) null)) {
            JSONObject jSONObject2 = new JSONObject(msg);
            if (jSONObject2.has("deviceId") && Intrinsics.areEqual(jSONObject2.getString("deviceId"), this.acrossAccessControlId) && jSONObject2.has("status") && Intrinsics.areEqual(jSONObject2.getString("status"), "OFF")) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$onReceiverMessage$2(this, null), 3, null);
            }
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        MoveState parseMqtt = moveState.parseMqtt(topic, msg);
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!Intrinsics.areEqual(parseMqtt, r14)) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("switch move state from ");
            MoveState moveState2 = this.moveState;
            if (moveState2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            sb.append(moveState2.getClass().getName());
            sb.append(" to ");
            sb.append(parseMqtt.getClass().getName());
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            this.moveState = parseMqtt;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$onReceiverMessage$3(this, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccLoraConnection.AccLoraListener
    public void onStatus(String accId, String robotId, byte cmd) {
        Intrinsics.checkParameterIsNotNull(accId, "accId");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Pdlog.m3273d(this.TAG, "recv cmd msg " + ((int) cmd));
        if ((!Intrinsics.areEqual(accId, this.acrossAccessControlId)) || (!Intrinsics.areEqual(robotId, this.robotID))) {
            return;
        }
        if (cmd == ((byte) 4)) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$onStatus$1(this, null), 3, null);
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        MoveState parseLora = moveState.parseLora(cmd);
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!Intrinsics.areEqual(parseLora, r14)) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("switch move state from ");
            MoveState moveState2 = this.moveState;
            if (moveState2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            sb.append(moveState2.getClass().getName());
            sb.append(" to ");
            sb.append(parseLora.getClass().getName());
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            this.moveState = parseLora;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$onStatus$2(this, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccROS2Connection.AccROS2MsgInterface
    public void accState(boolean state) {
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        MoveState parseROS2 = moveState.parseROS2(state);
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!Intrinsics.areEqual(parseROS2, r0)) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("switch move state from ");
            MoveState moveState2 = this.moveState;
            if (moveState2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            sb.append(moveState2.getClass().getName());
            sb.append(" to ");
            sb.append(parseROS2.getClass().getName());
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            this.moveState = parseROS2;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$accState$1(this, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double rangeToAccessWaitPoint() {
        double x = this.robotPose.getX();
        PathSegments pathSegments = getPathSegments();
        if (pathSegments == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments = pathSegments.getSegments();
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        double x2 = x - segments.get(this.taskIndex).getPose().getX();
        double y = this.robotPose.getY();
        PathSegments pathSegments2 = getPathSegments();
        if (pathSegments2 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments2 = pathSegments2.getSegments();
        if (segments2 == null) {
            Intrinsics.throwNpe();
        }
        double y2 = y - segments2.get(this.taskIndex).getPose().getY();
        double sqrt = Math.sqrt((x2 * x2) + (y2 * y2));
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("range: ");
        sb.append(sqrt);
        sb.append(", distance: ");
        sb.append(this.callDistance);
        sb.append(", rpose(");
        sb.append(this.robotPose.getX());
        sb.append(", ");
        sb.append(this.robotPose.getY());
        sb.append(") wpose(");
        PathSegments pathSegments3 = getPathSegments();
        if (pathSegments3 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments3 = pathSegments3.getSegments();
        if (segments3 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(segments3.get(this.taskIndex).getPose().getX());
        sb.append(", ");
        PathSegments pathSegments4 = getPathSegments();
        if (pathSegments4 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments4 = pathSegments4.getSegments();
        if (segments4 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(segments4.get(this.taskIndex).getPose().getY());
        sb.append(')');
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        return sqrt;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00060\u0000R\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001c\u0010\u0007\u001a\u00060\u0000R\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\u0014\u0010\u000b\u001a\u00060\u0000R\u00020\u00042\u0006\u0010\u0005\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u000eH&J\b\u0010\u0010\u001a\u00020\u000eH\u0016J \u0010\u0011\u001a\u0012\u0012\b\u0012\u00060\u0000R\u00020\u0004\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H&¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveState;", "", "(Lcom/pudutech/mirsdk/movetask/AccessControlTask;)V", "parseLora", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "cmd", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseROS2", "", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public abstract class MoveState {
        public abstract MoveState parseLora(byte cmd);

        public abstract MoveState parseMqtt(String topic, String msg);

        public abstract MoveState parseROS2(boolean cmd);

        public abstract void pause();

        public abstract void resume();

        /* renamed from: switch */
        public abstract Pair<MoveState, RobotState> mo4464switch(SDKRobotState robotState);

        public MoveState() {
        }

        public void start() {
            BuildersKt__BuildersKt.runBlocking$default(null, new AccessControlTask$MoveState$start$1(this, null), 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveToPoint(final int segIndex) {
        PathSegments pathSegments = getPathSegments();
        List<PathSegment> segments = pathSegments != null ? pathSegments.getSegments() : null;
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        final Vector3d pose = segments.get(segIndex).getPose();
        getAction().moveToPosition(getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$moveToPoint$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:53:0x027d  */
            /* JADX WARN: Removed duplicated region for block: B:62:0x0283  */
            /* JADX WARN: Type inference failed for: r7v49 */
            /* JADX WARN: Type inference failed for: r7v50 */
            /* JADX WARN: Type inference failed for: r7v51, types: [java.lang.Object] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final boolean invoke2() {
                Boolean valueOf;
                ScheduleInterface scheduler;
                Object obj;
                Destination destination;
                ScheduleInterface scheduler2;
                ScheduleInterface scheduler3;
                Object obj2;
                Destination destination2;
                String str = AccessControlTask.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getName());
                sb.append("]move to (");
                sb.append(com.pudutech.base.CommonKt.format(pose.getX(), 2));
                sb.append(',');
                sb.append(com.pudutech.base.CommonKt.format(pose.getY(), 2));
                sb.append(") dir:");
                sb.append(com.pudutech.base.CommonKt.format(pose.getZ(), 2));
                Pdlog.m3273d(str, sb.toString());
                MirCoreInterface mirCoreInterface = AccessControlTask.this.getCoreService().getInterface();
                Boolean bool = null;
                NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                int i = AccessControlTask.WhenMappings.$EnumSwitchMapping$2[AccessControlTask.this.getBusinessType().ordinal()];
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
                int i2 = segIndex;
                PathSegments pathSegments2 = AccessControlTask.this.getPathSegments();
                List<PathSegment> segments2 = pathSegments2 != null ? pathSegments2.getSegments() : null;
                if (segments2 == null) {
                    Intrinsics.throwNpe();
                }
                if (i2 != CollectionsKt.getLastIndex(segments2)) {
                    MirCoreInterface mirCoreInterface2 = AccessControlTask.this.getCoreService().getInterface();
                    if (mirCoreInterface2 != null && (scheduler = mirCoreInterface2.getScheduler()) != null) {
                        bool = Boolean.valueOf(scheduler.prepareDeliverTask(new Vector3d(pose.getX(), pose.getY(), pose.getZ())));
                    }
                } else if (AccessControlTask.WhenMappings.$EnumSwitchMapping$3[AccessControlTask.this.getBusinessType().ordinal()] == 1) {
                    PathSegments pathSegments3 = AccessControlTask.this.getPathSegments();
                    String finalGoal = pathSegments3 != null ? pathSegments3.getFinalGoal() : null;
                    Iterator it = AccessControlTask.this.getAction().getAtlas().getMapDecode().getChargingPiles().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ChargingPile) obj).getId(), finalGoal)) {
                            break;
                        }
                    }
                    ChargingPile chargingPile = (ChargingPile) obj;
                    if (chargingPile != null) {
                        Vector3d vector3d = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
                        vector3d.setX(chargingPile.getPose().getX() + Math.cos(chargingPile.getPose().getZ()));
                        vector3d.setY(chargingPile.getPose().getY() + Math.sin(chargingPile.getPose().getZ()));
                        vector3d.setZ(chargingPile.getPose().getZ() >= ((double) 0) ? chargingPile.getPose().getZ() - 3.141592653589793d : chargingPile.getPose().getZ() + 3.141592653589793d);
                        destination = new Destination(chargingPile.getId(), chargingPile.getFloor(), vector3d.getX(), vector3d.getY(), vector3d.getZ(), false, "", "", false);
                        if (destination == null) {
                        }
                    }
                    destination = null;
                    if (destination == null) {
                    }
                } else {
                    List<Destination> destinations = AccessControlTask.this.getAction().getDestinations();
                    if (destinations != null) {
                        Iterator it2 = destinations.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                destination2 = 0;
                                break;
                            }
                            destination2 = it2.next();
                            String name = ((Destination) destination2).getName();
                            PathSegments pathSegments4 = AccessControlTask.this.getPathSegments();
                            List<PathSegment> segments3 = pathSegments4 != null ? pathSegments4.getSegments() : null;
                            if (segments3 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (Intrinsics.areEqual(name, segments3.get(segIndex).getCurGoal())) {
                                break;
                            }
                        }
                        destination = destination2;
                    } else {
                        destination = null;
                    }
                    if (destination == null) {
                        Iterator it3 = AccessControlTask.this.getAction().getElevWaiters().iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                obj2 = null;
                                break;
                            }
                            obj2 = it3.next();
                            String id = ((ElevatorWaiter) obj2).getId();
                            PathSegments pathSegments5 = AccessControlTask.this.getPathSegments();
                            List<PathSegment> segments4 = pathSegments5 != null ? pathSegments5.getSegments() : null;
                            if (segments4 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (Intrinsics.areEqual(id, segments4.get(segIndex).getCurGoal())) {
                                break;
                            }
                        }
                        ElevatorWaiter elevatorWaiter = (ElevatorWaiter) obj2;
                        if (elevatorWaiter != null) {
                            destination = new Destination(elevatorWaiter.getFloor(), elevatorWaiter.getId(), elevatorWaiter.getPose().getX(), elevatorWaiter.getPose().getY(), elevatorWaiter.getPose().getZ(), false, "", "", false);
                        }
                        destination = null;
                    }
                    if (destination == null) {
                        bool = false;
                    } else if (AccessControlTask.WhenMappings.$EnumSwitchMapping$4[AccessControlTask.this.getBusinessType().ordinal()] == 1) {
                        MirCoreInterface mirCoreInterface3 = AccessControlTask.this.getCoreService().getInterface();
                        if (mirCoreInterface3 != null && (scheduler2 = mirCoreInterface3.getScheduler()) != null) {
                            bool = Boolean.valueOf(scheduler2.prepareGoHomeTask(destination.getGroup(), com.pudutech.mirsdk.mircore.coreparcel.BusinessType.GoHome));
                        }
                    } else {
                        MirCoreInterface mirCoreInterface4 = AccessControlTask.this.getCoreService().getInterface();
                        if (mirCoreInterface4 != null && (scheduler3 = mirCoreInterface4.getScheduler()) != null) {
                            bool = Boolean.valueOf(scheduler3.prepareDeliverTask(new Vector3d(destination.getX(), destination.getY(), destination.getDirection())));
                        }
                    }
                }
                return Intrinsics.areEqual((Object) valueOf, (Object) true) && Intrinsics.areEqual((Object) bool, (Object) true);
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\u0007\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0014\u0010\u000b\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0005\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J \u0010\u0011\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask$GoWaiter;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "(Lcom/pudutech/mirsdk/movetask/AccessControlTask;)V", "parseLora", "cmd", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseROS2", "", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class GoWaiter extends MoveState {

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 2;
            }
        }

        public GoWaiter() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void start() {
            if (AccessControlTask.this.rangeToAccessWaitPoint() < AccessControlTask.this.getCallDistance()) {
                Pdlog.m3273d(AccessControlTask.this.TAG, "robot has been near to wait point, start work to call access control");
                AccessControlTask accessControlTask = AccessControlTask.this;
                accessControlTask.moveState = new WaitAccessControl();
                AccessControlTask.access$getMoveState$p(AccessControlTask.this).start();
                return;
            }
            super.start();
            AccessControlTask accessControlTask2 = AccessControlTask.this;
            accessControlTask2.moveToPoint(accessControlTask2.taskIndex);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void pause() {
            AccessControlTask.this.getAction().actionPause();
            Pdlog.m3273d(AccessControlTask.this.TAG, "pause when robot is going to access control waiter");
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void resume() {
            AccessControlTask.this.onStateChange(SDKRobotState.Resume, "");
            Pdlog.m3273d(AccessControlTask.this.TAG, "resume task when robot is going to access control waiter");
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4464switch(SDKRobotState robotState) {
            GoWaiter goWaiter;
            RobotState robotState2;
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(AccessControlTask.this.TAG, "switch robot state: " + robotState + " in GoWaiter");
            if (AccessControlTask.this.rangeToAccessWaitPoint() < AccessControlTask.this.getCallDistance()) {
                Pdlog.m3273d(AccessControlTask.this.TAG, "robot has been near to wait point, start work to call access control");
                goWaiter = new WaitAccessControl();
            } else {
                goWaiter = this;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1 || i == 2) {
                robotState2 = RobotState.Moving;
            } else {
                robotState2 = SDKRobotStateKt.peerConversion(robotState);
            }
            return new Pair<>(goWaiter, robotState2);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseLora(byte cmd) {
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseROS2(boolean cmd) {
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\t\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0014\u0010\u0010\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\n\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0016J \u0010\u0016\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask$WaitAccessControl;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "(Lcom/pudutech/mirsdk/movetask/AccessControlTask;)V", "recvResponseFromAccess", "", "recvResponseTime", "", "robotInPause", "parseLora", "cmd", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseROS2", "pause", "", "resume", "runCallAccessControlJob", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class WaitAccessControl extends MoveState {
        private boolean recvResponseFromAccess;
        private long recvResponseTime;
        private boolean robotInPause;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;

            static {
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Error.ordinal()] = 3;
                $EnumSwitchMapping$1 = new int[SDKRobotState.values().length];
                $EnumSwitchMapping$1[SDKRobotState.Error.ordinal()] = 1;
                $EnumSwitchMapping$2 = new int[AccessControlServer.values().length];
                $EnumSwitchMapping$2[AccessControlServer.YouDian.ordinal()] = 1;
                $EnumSwitchMapping$2[AccessControlServer.YouDianTest.ordinal()] = 2;
            }
        }

        public WaitAccessControl() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void runCallAccessControlJob() {
            Job launch$default;
            AccessControlTask accessControlTask = AccessControlTask.this;
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$WaitAccessControl$runCallAccessControlJob$1(this, null), 3, null);
            accessControlTask.accessControlJob = launch$default;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void start() {
            super.start();
            AccessControlTask accessControlTask = AccessControlTask.this;
            accessControlTask.seq++;
            int unused = accessControlTask.seq;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$WaitAccessControl$start$1(this, null), 3, null);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void pause() {
            AccessControlTask.this.getAction().actionPause();
            this.robotInPause = true;
            Pdlog.m3273d(AccessControlTask.this.TAG, "pause robot when waiting access control");
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$WaitAccessControl$pause$1(this, null), 3, null);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void resume() {
            AccessControlTask.this.onStateChange(SDKRobotState.Resume, "");
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4464switch(SDKRobotState robotState) {
            RobotState robotState2;
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(AccessControlTask.this.TAG, "switch robot state: " + robotState + " in WaitAccessControl");
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1) {
                robotState2 = RobotState.Moving;
            } else if (i == 2) {
                if (this.recvResponseFromAccess) {
                    AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$switch$rState$1
                        /* JADX INFO: Access modifiers changed from: package-private */
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
                            it.informAccessDoorControlState(AccessDoorControlState.WaitingOpenAccessDoor, AccessControlTask.this.acrossAccessControlId);
                        }
                    });
                }
                robotState2 = RobotState.Moving;
            } else if (i == 3) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$WaitAccessControl$switch$rState$2(this, null), 3, null);
                robotState2 = RobotState.Error;
            } else {
                robotState2 = SDKRobotStateKt.peerConversion(robotState);
            }
            this.robotInPause = WhenMappings.$EnumSwitchMapping$1[robotState.ordinal()] == 1;
            return new Pair<>(this, robotState2);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            int i = WhenMappings.$EnumSwitchMapping$2[AccessControlTask.accessConnection.ordinal()];
            if (i == 1 || i == 2) {
                if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "pdrobot/user/door_status", false, 2, (Object) null)) {
                    YouDianMqtt youDianMqtt = (YouDianMqtt) AccessControlTask.this.gson.fromJson(msg, YouDianMqtt.class);
                    YouDianBody body = youDianMqtt.getBody();
                    if (Intrinsics.areEqual(body != null ? body.getAccess_id() : null, AccessControlTask.this.acrossAccessControlId)) {
                        YouDianBody body2 = youDianMqtt.getBody();
                        String status = body2 != null ? body2.getStatus() : null;
                        if (Intrinsics.areEqual(status, "open_ack") || Intrinsics.areEqual(status, "open")) {
                            this.recvResponseFromAccess = true;
                        }
                    }
                    Pdlog.m3273d(AccessControlTask.this.TAG, "recv response from access " + this.recvResponseFromAccess + " robot in pause? " + this.robotInPause, "waiting access control " + AccessControlTask.this.acrossAccessControlId);
                    YouDianBody body3 = youDianMqtt.getBody();
                    if (Intrinsics.areEqual(body3 != null ? body3.getAccess_id() : null, AccessControlTask.this.acrossAccessControlId)) {
                        YouDianBody body4 = youDianMqtt.getBody();
                        if (Intrinsics.areEqual(body4 != null ? body4.getStatus() : null, "open") && !this.robotInPause) {
                            MoveAcrossAccessControl moveAcrossAccessControl = new MoveAcrossAccessControl();
                            AccessControlTask.this.taskIndex++;
                            return moveAcrossAccessControl;
                        }
                    }
                    return this;
                }
                return this;
            }
            if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "returnstatus", false, 2, (Object) null)) {
                JSONObject jSONObject = new JSONObject(msg);
                if (jSONObject.has("deviceId") && Intrinsics.areEqual(jSONObject.getString("deviceId"), AccessControlTask.this.acrossAccessControlId) && jSONObject.has("status") && Intrinsics.areEqual(jSONObject.getString("status"), "ON")) {
                    Pdlog.m3273d(AccessControlTask.this.TAG, "recv response from access " + this.recvResponseFromAccess + " robot in pause? " + this.robotInPause);
                    if (!this.recvResponseFromAccess && !this.robotInPause) {
                        this.recvResponseFromAccess = true;
                        this.recvResponseTime = SystemClock.elapsedRealtime();
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$WaitAccessControl$parseMqtt$1(this, null), 3, null);
                    }
                }
            }
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseLora(byte cmd) {
            if (cmd == ((byte) 2)) {
                this.recvResponseFromAccess = true;
                Pdlog.m3273d(AccessControlTask.this.TAG, "recv response from access " + this.recvResponseFromAccess + " robot in pause? " + this.robotInPause, "waiting access control " + AccessControlTask.this.acrossAccessControlId);
                if (!this.recvResponseFromAccess && !this.robotInPause) {
                    this.recvResponseFromAccess = true;
                    this.recvResponseTime = SystemClock.elapsedRealtime();
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$WaitAccessControl$parseLora$1(this, null), 3, null);
                }
                return this;
            }
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseROS2(boolean cmd) {
            if (cmd) {
                this.recvResponseFromAccess = true;
                Pdlog.m3273d(AccessControlTask.this.TAG, "recv response from access " + this.recvResponseFromAccess + " robot in pause? " + this.robotInPause, "waiting access control " + AccessControlTask.this.acrossAccessControlId);
                if (!this.robotInPause) {
                    MoveAcrossAccessControl moveAcrossAccessControl = new MoveAcrossAccessControl();
                    AccessControlTask.this.taskIndex++;
                    return moveAcrossAccessControl;
                }
                return this;
            }
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\u0007\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0014\u0010\u000b\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0005\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J \u0010\u0011\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveAcrossAccessControl;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "(Lcom/pudutech/mirsdk/movetask/AccessControlTask;)V", "parseLora", "cmd", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseROS2", "", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class MoveAcrossAccessControl extends MoveState {

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Error.ordinal()] = 3;
            }
        }

        public MoveAcrossAccessControl() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void start() {
            super.start();
            AccessControlTask accessControlTask = AccessControlTask.this;
            accessControlTask.moveToPoint(accessControlTask.taskIndex);
            BuildersKt__BuildersKt.runBlocking$default(null, new AccessControlTask$MoveAcrossAccessControl$start$1(this, null), 1, null);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void pause() {
            AccessControlTask.this.getAction().actionPause();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$MoveAcrossAccessControl$pause$1(this, null), 3, null);
            Pdlog.m3273d(AccessControlTask.this.TAG, "pause robot when moving across access control");
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void resume() {
            AccessControlTask.this.onStateChange(SDKRobotState.Resume, "");
            Pdlog.m3273d(AccessControlTask.this.TAG, "resume robot when moving across access control");
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4464switch(SDKRobotState robotState) {
            GoWaiter goWaiter;
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(AccessControlTask.this.TAG, "switch robot state: " + robotState + " in MoveAcrossAccessControl");
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1) {
                return new Pair<>(this, RobotState.Moving);
            }
            if (i != 2) {
                if (i == 3) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$MoveAcrossAccessControl$switch$2(this, null), 3, null);
                    return new Pair<>(this, RobotState.Error);
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            AccessControlTask.this.taskIndex++;
            PathSegments pathSegments = AccessControlTask.this.getPathSegments();
            if (pathSegments == null) {
                Intrinsics.throwNpe();
            }
            List<PathSegment> segments = pathSegments.getSegments();
            if (segments == null) {
                Intrinsics.throwNpe();
            }
            if (segments.size() - AccessControlTask.this.taskIndex != 1) {
                String str = AccessControlTask.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("finish across access control ");
                sb.append(AccessControlTask.this.acrossAccessControlId);
                sb.append(", next access control is ");
                PathSegments pathSegments2 = AccessControlTask.this.getPathSegments();
                if (pathSegments2 == null) {
                    Intrinsics.throwNpe();
                }
                List<PathSegment> segments2 = pathSegments2.getSegments();
                if (segments2 == null) {
                    Intrinsics.throwNpe();
                }
                String curGoal = segments2.get(AccessControlTask.this.taskIndex + 1).getCurGoal();
                if (curGoal == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(curGoal);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                goWaiter = new GoWaiter();
            } else {
                goWaiter = new MoveToFinalGoal();
            }
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$MoveAcrossAccessControl$switch$1(this, null), 3, null);
            return new Pair<>(goWaiter, RobotState.Moving);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseLora(byte cmd) {
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseROS2(boolean cmd) {
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001c\u0010\t\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0014\u0010\r\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J \u0010\u0013\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveToFinalGoal;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "(Lcom/pudutech/mirsdk/movetask/AccessControlTask;)V", "job", "Lkotlinx/coroutines/Job;", "parseLora", "cmd", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseROS2", "", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class MoveToFinalGoal extends MoveState {
        private Job job;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BusinessType.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;

            static {
                $EnumSwitchMapping$0[BusinessType.GoCharging.ordinal()] = 1;
                $EnumSwitchMapping$1 = new int[BusinessType.values().length];
                $EnumSwitchMapping$1[BusinessType.GoCharging.ordinal()] = 1;
                $EnumSwitchMapping$2 = new int[SDKRobotState.values().length];
                $EnumSwitchMapping$2[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$2[SDKRobotState.Approaching.ordinal()] = 2;
            }
        }

        public MoveToFinalGoal() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void start() {
            super.start();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$MoveToFinalGoal$start$1(this, null), 3, null);
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void pause() {
            AccessControlTask.this.getAction().actionPause();
            Pdlog.m3273d(AccessControlTask.this.TAG, "pause when move to final goal");
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void resume() {
            AccessControlTask.this.onStateChange(SDKRobotState.Resume, "");
            Pdlog.m3273d(AccessControlTask.this.TAG, "resume when move to final goal");
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4464switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(AccessControlTask.this.TAG, "switch robot state: " + robotState + " in " + MoveToFinalGoal.class.getSimpleName());
            int i = WhenMappings.$EnumSwitchMapping$2[robotState.ordinal()];
            if (i == 1) {
                if (WhenMappings.$EnumSwitchMapping$0[AccessControlTask.this.getBusinessType().ordinal()] == 1) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$MoveToFinalGoal$switch$1(this, null), 3, null);
                    return new Pair<>(this, RobotState.Moving);
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (i != 2) {
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (WhenMappings.$EnumSwitchMapping$1[AccessControlTask.this.getBusinessType().ordinal()] == 1) {
                return new Pair<>(this, RobotState.Moving);
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseLora(byte cmd) {
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseROS2(boolean cmd) {
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\u0007\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0014\u0010\u000b\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0005\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J \u0010\u0011\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask$GoChargingState;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "(Lcom/pudutech/mirsdk/movetask/AccessControlTask;)V", "parseLora", "cmd", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseROS2", "", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class GoChargingState extends MoveState {

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 1;
            }
        }

        public GoChargingState() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void start() {
            Object obj;
            super.start();
            AccessControlTask.INSTANCE.setGoChargingTimes(0);
            Iterator<T> it = AccessControlTask.this.getAction().getAtlas().getMapDecode().getChargingPiles().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                String id = ((ChargingPile) obj).getId();
                PathSegments pathSegments = AccessControlTask.this.getPathSegments();
                if (Intrinsics.areEqual(id, pathSegments != null ? pathSegments.getFinalGoal() : null)) {
                    break;
                }
            }
            final ChargingPile chargingPile = (ChargingPile) obj;
            if (chargingPile == null) {
                String str = AccessControlTask.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("cannot find pile ");
                PathSegments pathSegments2 = AccessControlTask.this.getPathSegments();
                sb.append(pathSegments2 != null ? pathSegments2.getFinalGoal() : null);
                objArr[0] = sb.toString();
                Pdlog.m3277w(str, objArr);
                return;
            }
            AccessControlTask.this.getAction().moveToPosition(AccessControlTask.this.getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$GoChargingState$start$1
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
                    String str2 = AccessControlTask.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append('[');
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb2.append(currentThread.getName());
                    sb2.append("]move to charge pile (");
                    sb2.append(com.pudutech.base.CommonKt.format(chargingPile.getPose().getX(), 2));
                    sb2.append(',');
                    sb2.append(com.pudutech.base.CommonKt.format(chargingPile.getPose().getY(), 2));
                    sb2.append(") dir:");
                    sb2.append(chargingPile.getPose().getZ());
                    Pdlog.m3273d(str2, sb2.toString());
                    MirCoreInterface mirCoreInterface = AccessControlTask.this.getCoreService().getInterface();
                    NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                    if (navigator != null) {
                        return navigator.prepareAutoChargeTask(new Vector3d(chargingPile.getPose().getX(), chargingPile.getPose().getY(), chargingPile.getPose().getZ()));
                    }
                    return false;
                }
            });
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void pause() {
            AccessControlTask.this.getAction().actionPause();
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public void resume() {
            AccessControlTask.INSTANCE.setGoChargingTimes(0);
            AccessControlTask.this.getAction().actionResume();
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        /* renamed from: switch, reason: not valid java name */
        public Pair<MoveState, RobotState> mo4464switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            if (WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()] == 1) {
                if (AccessControlTask.INSTANCE.getGoChargingTimes() < 1) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AccessControlTask$GoChargingState$switch$1(this, null), 3, null);
                    Companion companion = AccessControlTask.INSTANCE;
                    companion.setGoChargingTimes(companion.getGoChargingTimes() + 1);
                    return new Pair<>(this, RobotState.Moving);
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseLora(byte cmd) {
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.AccessControlTask.MoveState
        public MoveState parseROS2(boolean cmd) {
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AccessControlTask$Companion;", "", "()V", "accessConnection", "Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;", ES6Iterator.VALUE_PROPERTY, "accessConnectionType", "getAccessConnectionType", "()Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;", "setAccessConnectionType", "(Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;)V", "goChargingTimes", "", "getGoChargingTimes", "()I", "setGoChargingTimes", "(I)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getGoChargingTimes() {
            return AccessControlTask.goChargingTimes;
        }

        public final void setGoChargingTimes(int i) {
            AccessControlTask.goChargingTimes = i;
        }

        public final AccessControlServer getAccessConnectionType() {
            return AccessControlTask.accessConnection;
        }

        public final void setAccessConnectionType(AccessControlServer value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            AccessControlTask.accessConnection = value;
            SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
            edit.putString("access_control_server", AccessControlTask.accessConnection.name());
            edit.apply();
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        }
    }
}
