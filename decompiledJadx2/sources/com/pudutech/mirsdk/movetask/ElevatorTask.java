package com.pudutech.mirsdk.movetask;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.base.SDKRobotStateKt;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.elv.proto.Elevator;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.ElevatorTask;
import com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface;
import com.pudutech.mirsdk.robotsdk.ElvLoraConnection;
import com.pudutech.mirsdk.robotsdk.ElvMqttConnection;
import com.pudutech.mirsdk.robotsdk.ElvROS2Connection;
import com.pudutech.mirsdk.robotsdk.MqttMsgListener;
import com.pudutech.mirsdk.robotsdk.ProtobufMsgListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0015\u0018\u0000 V2\u00020\u00012\u00020\u00022\u00020\u0003:\tVWXYZ[\\]^B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0014\u00106\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020907H\u0016J\u0014\u0010:\u001a\u00020\u00102\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u0010H\u0002J\u000e\u0010<\u001a\u0002052\u0006\u0010=\u001a\u00020\u001aJ\u0010\u0010>\u001a\u0002052\u0006\u0010?\u001a\u00020\u000bH\u0002J\u0018\u0010@\u001a\u0002052\u0006\u0010A\u001a\u00020\u00102\u0006\u0010B\u001a\u00020\u0010H\u0016J\u0018\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0010H\u0016J\u0010\u0010H\u001a\u0002052\u0006\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u000205H\u0016J\b\u0010L\u001a\u000205H\u0016J\b\u0010M\u001a\u000209H\u0002J\b\u0010N\u001a\u000209H\u0002J\b\u0010O\u001a\u000205H\u0016J!\u0010P\u001a\u0002052\u0006\u0010Q\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010SJ\u0010\u0010T\u001a\u0002052\b\u0010U\u001a\u0004\u0018\u000100R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u000e\u0010+\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010,\u001a\u00060-R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050403X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "Lcom/pudutech/mirsdk/robotsdk/MqttMsgListener;", "Lcom/pudutech/mirsdk/robotsdk/ProtobufMsgListener;", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;)V", "CALL_ELV_OVERTIME", "", "MOVE_OVERTIME", "RANGE_LOCATE_IN_ELV", "", "TAG", "", "kotlin.jvm.PlatformType", ES6Iterator.VALUE_PROPERTY, "", "callDistance", "getCallDistance", "()F", "setCallDistance", "(F)V", "context_", "Landroid/content/Context;", "currentSSID", "currentSSIDName", "elevatorListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/ElevatorRequestListener;", "getElevatorListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "elvLeftStatJob", "Lkotlinx/coroutines/Job;", "elvRemoteCommunicate", "Lcom/pudutech/mirsdk/robotsdk/ElevatorCommunicateInterface;", "goalSSID", "getGoalSSID", "()Ljava/lang/String;", "goalSSIDName", "getGoalSSIDName", "intoElevatorCount", "moveState", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "robotID", "robotPose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "seq", "stateNoticeQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lkotlin/Function0;", "", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "", "informationForElevatorListeners", "cur_floor", "initClient", "context", "moveToPoint", "segIndex", "onReceiverMessage", "topic", NotificationCompat.CATEGORY_MESSAGE, "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "state", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "parseMsg", "data", "", "pause", "resume", "robotInElevatorRange", "robotNearToWaitPoint", "startMoveAction", "switchWifi", "destId", "destSSID", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePose", "pose", "Companion", "GoWaiter", "MoveState", "MoveToElevator", "MoveToFinalGoal", "MovingOutElevator", "WaitElevator", "WaitingInElevator", "WaitingState", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ElevatorTask extends MoveTask implements MqttMsgListener, ProtobufMsgListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static ElevatorConnectionType elvConnection = ElevatorConnectionType.Lora;
    private final int CALL_ELV_OVERTIME;
    private int MOVE_OVERTIME;
    private final double RANGE_LOCATE_IN_ELV;
    private final String TAG;
    private float callDistance;
    private Context context_;
    private String currentSSID;
    private String currentSSIDName;
    private final ThreadSafeListener<ElevatorRequestListener> elevatorListener;
    private Job elvLeftStatJob;
    private ElevatorCommunicateInterface elvRemoteCommunicate;
    private final String goalSSID;
    private final String goalSSIDName;
    private int intoElevatorCount;
    private MoveState moveState;
    private final String robotID;
    private final Vector3d robotPose;
    private int seq;
    private final ArrayBlockingQueue<Function0<Unit>> stateNoticeQueue;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$WaitingState;", "", "(Ljava/lang/String;I)V", "Normal", "Pause", "LeaveWhenPause", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum WaitingState {
        Normal,
        Pause,
        LeaveWhenPause
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ElevatorConnectionType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            $EnumSwitchMapping$0[ElevatorConnectionType.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$0[ElevatorConnectionType.Mqtt.ordinal()] = 2;
            $EnumSwitchMapping$0[ElevatorConnectionType.Lora.ordinal()] = 3;
            $EnumSwitchMapping$0[ElevatorConnectionType.ROS2.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[ElevatorConnectionType.values().length];
            $EnumSwitchMapping$1[ElevatorConnectionType.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$1[ElevatorConnectionType.Mqtt.ordinal()] = 2;
            $EnumSwitchMapping$1[ElevatorConnectionType.Lora.ordinal()] = 3;
            $EnumSwitchMapping$1[ElevatorConnectionType.ROS2.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[BusinessType.values().length];
            $EnumSwitchMapping$2[BusinessType.GoGroup.ordinal()] = 1;
            $EnumSwitchMapping$3 = new int[BusinessType.values().length];
            $EnumSwitchMapping$3[BusinessType.GoGroup.ordinal()] = 1;
        }
    }

    public static final /* synthetic */ Context access$getContext_$p(ElevatorTask elevatorTask) {
        Context context = elevatorTask.context_;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context_");
        }
        return context;
    }

    public static final /* synthetic */ String access$getCurrentSSID$p(ElevatorTask elevatorTask) {
        String str = elevatorTask.currentSSID;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentSSID");
        }
        return str;
    }

    public static final /* synthetic */ String access$getCurrentSSIDName$p(ElevatorTask elevatorTask) {
        String str = elevatorTask.currentSSIDName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentSSIDName");
        }
        return str;
    }

    public static final /* synthetic */ ElevatorCommunicateInterface access$getElvRemoteCommunicate$p(ElevatorTask elevatorTask) {
        ElevatorCommunicateInterface elevatorCommunicateInterface = elevatorTask.elvRemoteCommunicate;
        if (elevatorCommunicateInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("elvRemoteCommunicate");
        }
        return elevatorCommunicateInterface;
    }

    public static final /* synthetic */ MoveState access$getMoveState$p(ElevatorTask elevatorTask) {
        MoveState moveState = elevatorTask.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        return moveState;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService) {
        super(action, coreService, null, null, null, false, 60, null);
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        this.TAG = getClass().getSimpleName();
        this.elevatorListener = new ThreadSafeListener<>();
        this.stateNoticeQueue = new ArrayBlockingQueue<>(10);
        this.CALL_ELV_OVERTIME = 60000;
        this.MOVE_OVERTIME = BZip2Constants.BASEBLOCKSIZE;
        this.RANGE_LOCATE_IN_ELV = 0.39d;
        this.robotPose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.robotID = CommonKt.getWIFIMac();
        this.goalSSID = "Lift_A";
        this.goalSSIDName = "\"Lift_A\"";
        this.callDistance = 5.0f;
    }

    public final ThreadSafeListener<ElevatorRequestListener> getElevatorListener() {
        return this.elevatorListener;
    }

    public final String getGoalSSID() {
        return this.goalSSID;
    }

    public final String getGoalSSIDName() {
        return this.goalSSIDName;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$Companion;", "", "()V", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;", "elevatorConnectionType", "getElevatorConnectionType", "()Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;", "setElevatorConnectionType", "(Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;)V", "elvConnection", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ElevatorConnectionType getElevatorConnectionType() {
            return ElevatorTask.elvConnection;
        }

        public final void setElevatorConnectionType(ElevatorConnectionType value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            ElevatorTask.elvConnection = value;
            SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
            edit.putString("elevator_conn_type", value.name());
            edit.apply();
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        }
    }

    public final float getCallDistance() {
        return this.callDistance;
    }

    public final void setCallDistance(float f) {
        this.callDistance = f;
        SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
        edit.putFloat("call_elevator_distance", this.callDistance);
        edit.apply();
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
    }

    public final void initClient(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context_ = context;
        float f = SDKConfig.INSTANCE.getPreferences().getFloat("call_elevator_distance", 0.0f);
        if (f == 0.0f) {
            f = 5.0f;
        }
        setCallDistance(f);
        String string = SDKConfig.INSTANCE.getPreferences().getString("elevator_conn_type", ElevatorConnectionType.Lora.name());
        if (string == null) {
            string = ElevatorConnectionType.Lora.name();
        }
        elvConnection = ElevatorConnectionType.valueOf(string);
        int i = WhenMappings.$EnumSwitchMapping$0[elvConnection.ordinal()];
        if (i == 1 || i == 2) {
            this.elvRemoteCommunicate = new ElvMqttConnection(context, elvConnection);
        } else if (i == 3) {
            this.elvRemoteCommunicate = new ElvLoraConnection(context);
        } else {
            if (i != 4) {
                return;
            }
            this.elvRemoteCommunicate = new ElvROS2Connection();
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x025e A[Catch: Exception -> 0x00d9, TryCatch #0 {Exception -> 0x00d9, blocks: (B:14:0x027a, B:15:0x0254, B:17:0x025e, B:21:0x0284, B:38:0x00d0, B:39:0x00b3, B:41:0x00bd, B:44:0x00dc, B:45:0x0109, B:47:0x010f, B:49:0x0154, B:52:0x015c, B:53:0x016b, B:55:0x0171, B:60:0x018c, B:75:0x01bc, B:61:0x01dd, B:62:0x01e5, B:64:0x01eb, B:66:0x0217, B:69:0x0223), top: B:37:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0284 A[Catch: Exception -> 0x00d9, TRY_LEAVE, TryCatch #0 {Exception -> 0x00d9, blocks: (B:14:0x027a, B:15:0x0254, B:17:0x025e, B:21:0x0284, B:38:0x00d0, B:39:0x00b3, B:41:0x00bd, B:44:0x00dc, B:45:0x0109, B:47:0x010f, B:49:0x0154, B:52:0x015c, B:53:0x016b, B:55:0x0171, B:60:0x018c, B:75:0x01bc, B:61:0x01dd, B:62:0x01e5, B:64:0x01eb, B:66:0x0217, B:69:0x0223), top: B:37:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bd A[Catch: Exception -> 0x00d9, TryCatch #0 {Exception -> 0x00d9, blocks: (B:14:0x027a, B:15:0x0254, B:17:0x025e, B:21:0x0284, B:38:0x00d0, B:39:0x00b3, B:41:0x00bd, B:44:0x00dc, B:45:0x0109, B:47:0x010f, B:49:0x0154, B:52:0x015c, B:53:0x016b, B:55:0x0171, B:60:0x018c, B:75:0x01bc, B:61:0x01dd, B:62:0x01e5, B:64:0x01eb, B:66:0x0217, B:69:0x0223), top: B:37:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00dc A[Catch: Exception -> 0x00d9, TryCatch #0 {Exception -> 0x00d9, blocks: (B:14:0x027a, B:15:0x0254, B:17:0x025e, B:21:0x0284, B:38:0x00d0, B:39:0x00b3, B:41:0x00bd, B:44:0x00dc, B:45:0x0109, B:47:0x010f, B:49:0x0154, B:52:0x015c, B:53:0x016b, B:55:0x0171, B:60:0x018c, B:75:0x01bc, B:61:0x01dd, B:62:0x01e5, B:64:0x01eb, B:66:0x0217, B:69:0x0223), top: B:37:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0277 -> B:14:0x027a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00cd -> B:37:0x00d0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object switchWifi(String str, String str2, Continuation<? super Unit> continuation) {
        ElevatorTask$switchWifi$1 elevatorTask$switchWifi$1;
        int i;
        ElevatorTask elevatorTask;
        String str3;
        String str4;
        WifiManager wifiManager;
        Object obj;
        int i2;
        List<ScanResult> list;
        String str5;
        Object obj2;
        WifiInfo wifiInfo;
        WifiManager wifiManager2;
        List<WifiConfiguration> list2;
        int i3;
        Object obj3;
        if (continuation instanceof ElevatorTask$switchWifi$1) {
            elevatorTask$switchWifi$1 = (ElevatorTask$switchWifi$1) continuation;
            if ((elevatorTask$switchWifi$1.label & Integer.MIN_VALUE) != 0) {
                elevatorTask$switchWifi$1.label -= Integer.MIN_VALUE;
                Object obj4 = elevatorTask$switchWifi$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = elevatorTask$switchWifi$1.label;
                int i4 = 1;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj4);
                    try {
                        Context context = this.context_;
                        if (context == null) {
                            try {
                                Intrinsics.throwUninitializedPropertyAccessException("context_");
                            } catch (Exception e) {
                                e = e;
                                str3 = str;
                                elevatorTask = this;
                            }
                        }
                        Object systemService = context.getSystemService("wifi");
                        if (systemService == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
                        }
                        WifiManager wifiManager3 = (WifiManager) systemService;
                        wifiManager3.startScan();
                        str3 = str;
                        str4 = str2;
                        wifiManager = wifiManager3;
                        obj = coroutine_suspended;
                        i2 = -1;
                        elevatorTask = this;
                        if (wifiManager.getScanResults().size() != i2) {
                        }
                    } catch (Exception e2) {
                        e = e2;
                        elevatorTask = this;
                        str3 = str;
                    }
                } else if (i == 1) {
                    int i5 = elevatorTask$switchWifi$1.I$0;
                    wifiManager = (WifiManager) elevatorTask$switchWifi$1.L$3;
                    String str6 = (String) elevatorTask$switchWifi$1.L$2;
                    String str7 = (String) elevatorTask$switchWifi$1.L$1;
                    ElevatorTask elevatorTask2 = (ElevatorTask) elevatorTask$switchWifi$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj4);
                        obj = coroutine_suspended;
                        elevatorTask = elevatorTask2;
                        str4 = str6;
                        str3 = str7;
                        try {
                            i2 = wifiManager.getScanResults().size();
                            if (wifiManager.getScanResults().size() != i2) {
                                elevatorTask$switchWifi$1.L$0 = elevatorTask;
                                elevatorTask$switchWifi$1.L$1 = str3;
                                elevatorTask$switchWifi$1.L$2 = str4;
                                elevatorTask$switchWifi$1.L$3 = wifiManager;
                                elevatorTask$switchWifi$1.I$0 = i2;
                                elevatorTask$switchWifi$1.label = 1;
                                if (DelayKt.delay(200L, elevatorTask$switchWifi$1) == obj) {
                                    return obj;
                                }
                                i2 = wifiManager.getScanResults().size();
                                if (wifiManager.getScanResults().size() != i2) {
                                    WifiInfo wifiInfo2 = wifiManager.getConnectionInfo();
                                    String str8 = elevatorTask.TAG;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("current wifi before switch ");
                                    Intrinsics.checkExpressionValueIsNotNull(wifiInfo2, "wifiInfo");
                                    sb.append(wifiInfo2.getSSID());
                                    Pdlog.m3273d(str8, sb.toString());
                                    List<ScanResult> scanResults = wifiManager.getScanResults();
                                    Iterator<ScanResult> it = scanResults.iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        ScanResult next = it.next();
                                        String str9 = elevatorTask.TAG;
                                        Object[] objArr = new Object[i4];
                                        objArr[0] = "scan ssid " + next.SSID + " is " + str3 + ' ' + Intrinsics.areEqual(next.SSID, str3);
                                        Pdlog.m3273d(str9, objArr);
                                        if ((!Intrinsics.areEqual(wifiInfo2.getSSID(), str4)) && Intrinsics.areEqual(next.SSID, str3)) {
                                            List<WifiConfiguration> configurations = wifiManager.getConfiguredNetworks();
                                            Intrinsics.checkExpressionValueIsNotNull(configurations, "configurations");
                                            Iterator<T> it2 = configurations.iterator();
                                            while (true) {
                                                if (!it2.hasNext()) {
                                                    obj3 = null;
                                                    break;
                                                }
                                                obj3 = it2.next();
                                                if (Boxing.boxBoolean(Intrinsics.areEqual(((WifiConfiguration) obj3).SSID, str4)).booleanValue()) {
                                                    break;
                                                }
                                            }
                                            if (obj3 == null) {
                                                WifiConfiguration wifiConfiguration = new WifiConfiguration();
                                                wifiConfiguration.SSID = str4;
                                                wifiConfiguration.preSharedKey = "\"pudu123456\"";
                                                wifiManager.addNetwork(wifiConfiguration);
                                                Pdlog.m3273d(elevatorTask.TAG, "add " + str4 + " to wifi configurations");
                                            } else {
                                                Pdlog.m3273d(elevatorTask.TAG, str4 + " has contained in configurations");
                                            }
                                        } else {
                                            i4 = 1;
                                        }
                                    }
                                    List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
                                    Iterator<WifiConfiguration> it3 = configuredNetworks.iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            break;
                                        }
                                        WifiConfiguration next2 = it3.next();
                                        Pdlog.m3273d(elevatorTask.TAG, "config ssid " + next2.SSID);
                                        if (Intrinsics.areEqual(next2.SSID, str4) && (!Intrinsics.areEqual(wifiInfo2.getSSID(), str4))) {
                                            wifiManager.disconnect();
                                            wifiManager.enableNetwork(next2.networkId, true);
                                            wifiManager.reconnect();
                                            Pdlog.m3273d(elevatorTask.TAG, "reconnect to " + str3);
                                            break;
                                        }
                                    }
                                    list = scanResults;
                                    str5 = str4;
                                    obj2 = obj;
                                    wifiInfo = wifiInfo2;
                                    wifiManager2 = wifiManager;
                                    list2 = configuredNetworks;
                                    i3 = -1;
                                    if (wifiManager2.getScanResults().size() != i3) {
                                    }
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        str3 = str7;
                        elevatorTask = elevatorTask2;
                    }
                } else if (i == 2) {
                    list2 = (List) elevatorTask$switchWifi$1.L$6;
                    list = (List) elevatorTask$switchWifi$1.L$5;
                    wifiInfo = (WifiInfo) elevatorTask$switchWifi$1.L$4;
                    int i6 = elevatorTask$switchWifi$1.I$0;
                    wifiManager2 = (WifiManager) elevatorTask$switchWifi$1.L$3;
                    str5 = (String) elevatorTask$switchWifi$1.L$2;
                    String str10 = (String) elevatorTask$switchWifi$1.L$1;
                    ElevatorTask elevatorTask3 = (ElevatorTask) elevatorTask$switchWifi$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj4);
                        obj2 = coroutine_suspended;
                        elevatorTask = elevatorTask3;
                        str3 = str10;
                        i3 = wifiManager2.getScanResults().size();
                    } catch (Exception e5) {
                        e = e5;
                        elevatorTask = elevatorTask3;
                        str3 = str10;
                    }
                    if (wifiManager2.getScanResults().size() != i3) {
                        elevatorTask$switchWifi$1.L$0 = elevatorTask;
                        elevatorTask$switchWifi$1.L$1 = str3;
                        elevatorTask$switchWifi$1.L$2 = str5;
                        elevatorTask$switchWifi$1.L$3 = wifiManager2;
                        elevatorTask$switchWifi$1.I$0 = i3;
                        elevatorTask$switchWifi$1.L$4 = wifiInfo;
                        elevatorTask$switchWifi$1.L$5 = list;
                        elevatorTask$switchWifi$1.L$6 = list2;
                        elevatorTask$switchWifi$1.label = 2;
                        if (DelayKt.delay(200L, elevatorTask$switchWifi$1) == obj2) {
                            return obj2;
                        }
                        i3 = wifiManager2.getScanResults().size();
                        if (wifiManager2.getScanResults().size() != i3) {
                            WifiInfo wifiInfo3 = wifiManager2.getConnectionInfo();
                            String str11 = elevatorTask.TAG;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("current wifi after switch ");
                            Intrinsics.checkExpressionValueIsNotNull(wifiInfo3, "wifiInfo");
                            sb2.append(wifiInfo3.getSSID());
                            Pdlog.m3273d(str11, sb2.toString());
                            return Unit.INSTANCE;
                        }
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Pdlog.m3277w(elevatorTask.TAG, "Switch Wifi to " + str3 + " failed " + e.getMessage());
                return Unit.INSTANCE;
            }
        }
        elevatorTask$switchWifi$1 = new ElevatorTask$switchWifi$1(this, continuation);
        Object obj42 = elevatorTask$switchWifi$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = elevatorTask$switchWifi$1.label;
        int i42 = 1;
        if (i != 0) {
        }
        Pdlog.m3277w(elevatorTask.TAG, "Switch Wifi to " + str3 + " failed " + e.getMessage());
        return Unit.INSTANCE;
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void startMoveAction() {
        if (this.elvRemoteCommunicate != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[elvConnection.ordinal()];
            if (i == 1 || i == 2) {
                ElevatorCommunicateInterface elevatorCommunicateInterface = this.elvRemoteCommunicate;
                if (elevatorCommunicateInterface == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("elvRemoteCommunicate");
                }
                if (elevatorCommunicateInterface == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.robotsdk.ElvMqttConnection");
                }
                ((ElvMqttConnection) elevatorCommunicateInterface).connect(this);
            } else if (i == 3) {
                ElevatorCommunicateInterface elevatorCommunicateInterface2 = this.elvRemoteCommunicate;
                if (elevatorCommunicateInterface2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("elvRemoteCommunicate");
                }
                if (elevatorCommunicateInterface2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.robotsdk.ElvLoraConnection");
                }
                ((ElvLoraConnection) elevatorCommunicateInterface2).connect(this);
            } else if (i == 4) {
                ElevatorCommunicateInterface elevatorCommunicateInterface3 = this.elvRemoteCommunicate;
                if (elevatorCommunicateInterface3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("elvRemoteCommunicate");
                }
                if (elevatorCommunicateInterface3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.robotsdk.ElvROS2Connection");
                }
                ((ElvROS2Connection) elevatorCommunicateInterface3).connect(this);
            }
        }
        if (getPathSegments() == null) {
            return;
        }
        if (elvConnection == ElevatorConnectionType.Mqtt) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$startMoveAction$2(this, null), 3, null);
        }
        this.intoElevatorCount = 0;
        Pdlog.m3273d(this.TAG, "print robot pose before go waiter (" + this.robotPose.getX() + ", " + this.robotPose.getY() + ')');
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
        final Pair<MoveState, RobotState> mo4466switch = moveState.mo4466switch(state);
        this.stateNoticeQueue.put(new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$onStateChange$1
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
                ElevatorTask.this.getAction().informStateChange((RobotState) mo4466switch.getSecond(), desc);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new ElevatorTask$onStateChange$2(this, null), 2, null);
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!Intrinsics.areEqual(r10, mo4466switch.getFirst())) {
            this.moveState = mo4466switch.getFirst();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$onStateChange$3(this, null), 3, null);
        }
        return mo4466switch.getSecond();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public Pair<NavigationMode, Boolean> checkNavigationMode() {
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        return moveState instanceof MoveToElevator ? new Pair<>(NavigationMode.InToElevator, true) : moveState instanceof MovingOutElevator ? new Pair<>(NavigationMode.OutofElevator, false) : moveState instanceof MoveToFinalGoal ? new Pair<>(NavigationMode.Normal, true) : moveState instanceof WaitElevator ? new Pair<>(NavigationMode.NearToWaiter, true) : new Pair<>(NavigationMode.Normal, false);
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

    @Override // com.pudutech.mirsdk.robotsdk.MqttMsgListener
    public void onReceiverMessage(String topic, String msg) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Pdlog.m3273d(this.TAG, "recv topic " + topic + " with msg " + msg);
        if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "elv_left_stat_ack", false, 2, (Object) null)) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$onReceiverMessage$1(this, null), 3, null);
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        MoveState parseMqtt = moveState.parseMqtt(topic, msg);
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!Intrinsics.areEqual(parseMqtt, r12)) {
            this.moveState = parseMqtt;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$onReceiverMessage$2(this, null), 3, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.mirsdk.robotsdk.ProtobufMsgListener
    public void parseMsg(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Elevator.Elv elv = ((Elevator.Elv.Builder) Elevator.Elv.newBuilder().mergeFrom(data)).build();
        Intrinsics.checkExpressionValueIsNotNull(elv, "elv");
        if (!Intrinsics.areEqual(elv.getRobotId(), this.robotID)) {
            Pdlog.m3273d(this.TAG, "elv.robotId:" + elv.getRobotId() + " != robotID:" + this.robotID);
            return;
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        MoveState parseProto = moveState.parseProto(elv);
        if (this.moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        if (!Intrinsics.areEqual(parseProto, r0)) {
            this.moveState = parseProto;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$parseMsg$1(this, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean robotInElevatorRange() {
        double x = this.robotPose.getX();
        PathSegments pathSegments = getPathSegments();
        if (pathSegments == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments = pathSegments.getSegments();
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        double x2 = x - segments.get(1).getPose().getX();
        double y = this.robotPose.getY();
        PathSegments pathSegments2 = getPathSegments();
        if (pathSegments2 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments2 = pathSegments2.getSegments();
        if (segments2 == null) {
            Intrinsics.throwNpe();
        }
        double y2 = y - segments2.get(1).getPose().getY();
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("robot pose (");
        sb.append(this.robotPose.getX());
        sb.append(", ");
        sb.append(this.robotPose.getY());
        sb.append("), elevator pose (");
        PathSegments pathSegments3 = getPathSegments();
        if (pathSegments3 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments3 = pathSegments3.getSegments();
        if (segments3 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(segments3.get(1).getPose().getX());
        sb.append(", ");
        PathSegments pathSegments4 = getPathSegments();
        if (pathSegments4 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments4 = pathSegments4.getSegments();
        if (segments4 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(segments4.get(1).getPose().getY());
        sb.append(')');
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        return Math.sqrt((x2 * x2) + (y2 * y2)) < this.RANGE_LOCATE_IN_ELV;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean robotNearToWaitPoint() {
        double x = this.robotPose.getX();
        PathSegments pathSegments = getPathSegments();
        if (pathSegments == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments = pathSegments.getSegments();
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        double x2 = x - segments.get(0).getPose().getX();
        double y = this.robotPose.getY();
        PathSegments pathSegments2 = getPathSegments();
        if (pathSegments2 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments2 = pathSegments2.getSegments();
        if (segments2 == null) {
            Intrinsics.throwNpe();
        }
        double y2 = y - segments2.get(0).getPose().getY();
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
        sb.append(segments3.get(0).getPose().getX());
        sb.append(", ");
        PathSegments pathSegments4 = getPathSegments();
        if (pathSegments4 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments4 = pathSegments4.getSegments();
        if (segments4 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(segments4.get(0).getPose().getY());
        sb.append(')');
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        return sqrt < ((double) this.callDistance);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00060\u0000R\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0014\u0010\b\u001a\u00060\u0000R\u00020\u00042\u0006\u0010\u0007\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u000bH\u0016J \u0010\u000e\u001a\u0012\u0012\b\u0012\u00060\u0000R\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "parseMqtt", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseProto", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv;", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public abstract class MoveState {
        public abstract MoveState parseMqtt(String topic, String msg);

        public abstract MoveState parseProto(Elevator.Elv msg);

        public abstract void pause();

        public abstract void resume();

        /* renamed from: switch */
        public abstract Pair<MoveState, RobotState> mo4466switch(SDKRobotState robotState);

        public MoveState() {
        }

        public void start() {
            BuildersKt__BuildersKt.runBlocking$default(null, new ElevatorTask$MoveState$start$1(this, null), 1, null);
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
        getAction().moveToPosition(getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$moveToPoint$1
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
                Object obj;
                ScheduleInterface scheduler2;
                ScheduleInterface scheduler3;
                String str = ElevatorTask.this.TAG;
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
                MirCoreInterface mirCoreInterface = ElevatorTask.this.getCoreService().getInterface();
                Boolean bool = null;
                NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                if (ElevatorTask.WhenMappings.$EnumSwitchMapping$2[ElevatorTask.this.getBusinessType().ordinal()] != 1) {
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
                int i = segIndex;
                PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                List<PathSegment> segments2 = pathSegments2 != null ? pathSegments2.getSegments() : null;
                if (segments2 == null) {
                    Intrinsics.throwNpe();
                }
                if (i == CollectionsKt.getLastIndex(segments2)) {
                    List<Destination> destinations = ElevatorTask.this.getAction().getDestinations();
                    if (destinations == null) {
                        Intrinsics.throwNpe();
                    }
                    Iterator<T> it = destinations.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        String name = ((Destination) obj).getName();
                        PathSegments pathSegments3 = ElevatorTask.this.getPathSegments();
                        List<PathSegment> segments3 = pathSegments3 != null ? pathSegments3.getSegments() : null;
                        if (segments3 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Intrinsics.areEqual(name, segments3.get(segIndex).getCurGoal())) {
                            break;
                        }
                    }
                    Destination destination = (Destination) obj;
                    if (destination == null) {
                        bool = false;
                    } else if (ElevatorTask.WhenMappings.$EnumSwitchMapping$3[ElevatorTask.this.getBusinessType().ordinal()] == 1) {
                        MirCoreInterface mirCoreInterface2 = ElevatorTask.this.getCoreService().getInterface();
                        if (mirCoreInterface2 != null && (scheduler2 = mirCoreInterface2.getScheduler()) != null) {
                            bool = Boolean.valueOf(scheduler2.prepareGoHomeTask(destination.getGroup(), com.pudutech.mirsdk.mircore.coreparcel.BusinessType.GoHome));
                        }
                    } else {
                        MirCoreInterface mirCoreInterface3 = ElevatorTask.this.getCoreService().getInterface();
                        if (mirCoreInterface3 != null && (scheduler3 = mirCoreInterface3.getScheduler()) != null) {
                            bool = Boolean.valueOf(scheduler3.prepareDeliverTask(new Vector3d(pose.getX(), pose.getY(), pose.getZ())));
                        }
                    }
                } else {
                    MirCoreInterface mirCoreInterface4 = ElevatorTask.this.getCoreService().getInterface();
                    if (mirCoreInterface4 != null && (scheduler = mirCoreInterface4.getScheduler()) != null) {
                        bool = Boolean.valueOf(scheduler.prepareDeliverTask(new Vector3d(pose.getX(), pose.getY(), pose.getZ())));
                    }
                }
                Pdlog.m3273d(ElevatorTask.this.TAG, "nav prepare result " + valueOf + " sch prepare result " + bool);
                return Intrinsics.areEqual((Object) valueOf, (Object) true) && Intrinsics.areEqual((Object) bool, (Object) true);
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0014\u0010\b\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0007\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J \u0010\u000e\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$GoWaiter;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseProto", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv;", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class GoWaiter extends MoveState {

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 2;
                $EnumSwitchMapping$1 = new int[RobotState.values().length];
                $EnumSwitchMapping$1[RobotState.Resume.ordinal()] = 1;
                $EnumSwitchMapping$1[RobotState.Moving.ordinal()] = 2;
            }
        }

        public GoWaiter() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void start() {
            if (ElevatorTask.this.robotInElevatorRange()) {
                Pdlog.m3273d(ElevatorTask.this.TAG, "robot in elevator");
                ElevatorTask elevatorTask = ElevatorTask.this;
                elevatorTask.moveState = new MoveToElevator();
                ElevatorTask.access$getMoveState$p(ElevatorTask.this).start();
                return;
            }
            if (ElevatorTask.this.robotNearToWaitPoint()) {
                Pdlog.m3273d(ElevatorTask.this.TAG, "robot near elevator waiter");
                ElevatorTask elevatorTask2 = ElevatorTask.this;
                elevatorTask2.moveState = new WaitElevator();
                ElevatorTask.access$getMoveState$p(ElevatorTask.this).start();
                return;
            }
            super.start();
            ElevatorTask.this.moveToPoint(0);
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void pause() {
            ElevatorTask.this.getAction().actionPause();
            Pdlog.m3273d(ElevatorTask.this.TAG, "pause when robot is going to elevator waiter");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void resume() {
            ElevatorTask.this.getAction().actionResume();
            Pdlog.m3273d(ElevatorTask.this.TAG, "resume task when robot is going to elevator waiter, check robot whether in elevator");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /* renamed from: switch, reason: not valid java name */
        public Pair<MoveState, RobotState> mo4466switch(SDKRobotState robotState) {
            RobotState robotState2;
            GoWaiter goWaiter;
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(ElevatorTask.this.TAG, "switch robot state: " + robotState + " in GoWaiter");
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1 || i == 2) {
                robotState2 = RobotState.Moving;
            } else {
                robotState2 = SDKRobotStateKt.peerConversion(robotState);
            }
            int i2 = WhenMappings.$EnumSwitchMapping$1[robotState2.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (ElevatorTask.this.robotInElevatorRange()) {
                    Pdlog.m3273d(ElevatorTask.this.TAG, "robot has been in elevator, start work to move into elevator");
                    goWaiter = new MoveToElevator();
                } else if (ElevatorTask.this.robotNearToWaitPoint()) {
                    Pdlog.m3273d(ElevatorTask.this.TAG, "robot has approaching waiter, start call elevator");
                    goWaiter = new WaitElevator();
                } else {
                    goWaiter = this;
                }
            } else {
                goWaiter = this;
            }
            return new Pair<>(goWaiter, robotState2);
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseProto(Elevator.Elv msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\n\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0014\u0010\u000e\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\r\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0016J \u0010\u0017\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$WaitElevator;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "controlWheelJob", "Lkotlinx/coroutines/Job;", "job", "overtimeCallElv", "", "robotInPause", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseProto", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv;", "pause", "", "resume", "runCallElvJob", "delayTime", "", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class WaitElevator extends MoveState {
        private Job controlWheelJob;
        private Job job;
        private boolean overtimeCallElv;
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
            public static final /* synthetic */ int[] $EnumSwitchMapping$3;

            static {
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Error.ordinal()] = 3;
                $EnumSwitchMapping$1 = new int[SDKRobotState.values().length];
                $EnumSwitchMapping$1[SDKRobotState.Error.ordinal()] = 1;
                $EnumSwitchMapping$2 = new int[RobotState.values().length];
                $EnumSwitchMapping$2[RobotState.Resume.ordinal()] = 1;
                $EnumSwitchMapping$2[RobotState.Moving.ordinal()] = 2;
                $EnumSwitchMapping$3 = new int[Elevator.Elv.MsgType.values().length];
                $EnumSwitchMapping$3[Elevator.Elv.MsgType.CallElvAck.ordinal()] = 1;
                $EnumSwitchMapping$3[Elevator.Elv.MsgType.PrepareRideAck.ordinal()] = 2;
                $EnumSwitchMapping$3[Elevator.Elv.MsgType.EnterElv.ordinal()] = 3;
            }
        }

        public WaitElevator() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void runCallElvJob(long delayTime) {
            Job launch$default;
            Pdlog.m3273d(ElevatorTask.this.TAG, "run call Elv");
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$runCallElvJob$1(this, delayTime, null), 3, null);
            this.job = launch$default;
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void start() {
            super.start();
            ElevatorTask elevatorTask = ElevatorTask.this;
            elevatorTask.seq++;
            int unused = elevatorTask.seq;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$start$1(this, null), 3, null);
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void pause() {
            ElevatorTask.this.getAction().actionPause();
            Pdlog.m3273d(ElevatorTask.this.TAG, "pause robot when waiting elevator");
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$pause$1(this, null), 3, null);
            this.robotInPause = true;
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void resume() {
            ElevatorTask.this.getAction().actionResume();
            Pdlog.m3273d(ElevatorTask.this.TAG, "resume robot when waiting elevator");
            this.robotInPause = false;
            if (this.job != null) {
                ElevatorTask elevatorTask = ElevatorTask.this;
                elevatorTask.seq++;
                int unused = elevatorTask.seq;
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$resume$1(this, null), 3, null);
            }
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4466switch(SDKRobotState robotState) {
            RobotState robotState2;
            Job launch$default;
            WaitElevator waitElevator;
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(ElevatorTask.this.TAG, "switch robot state: " + robotState + " in WaitElevator");
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1 || i == 2) {
                ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$switch$rState$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str) {
                        invoke2(elevatorRequestListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ElevatorRequestListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.informElevatorUtilizeState(ElevatorUtilizeState.WaitingElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                    }
                });
                if (robotState == RobotState.Arrive) {
                    launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$switch$rState$2(this, null), 3, null);
                    this.controlWheelJob = launch$default;
                }
                robotState2 = RobotState.Moving;
            } else if (i == 3) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$switch$rState$3(this, null), 3, null);
                robotState2 = RobotState.Error;
            } else {
                robotState2 = SDKRobotStateKt.peerConversion(robotState);
            }
            this.robotInPause = WhenMappings.$EnumSwitchMapping$1[robotState.ordinal()] == 1;
            int i2 = WhenMappings.$EnumSwitchMapping$2[robotState2.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (ElevatorTask.this.robotInElevatorRange()) {
                    Pdlog.m3273d(ElevatorTask.this.TAG, "robot has been in elevator, start work to move into elevator");
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$switch$mState$1(this, null), 3, null);
                    waitElevator = new MoveToElevator();
                } else {
                    waitElevator = this;
                }
            } else {
                waitElevator = this;
            }
            return new Pair<>(waitElevator, robotState2);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x009b A[ADDED_TO_REGION] */
        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public MoveState parseMqtt(String topic, String msg) {
            boolean z;
            boolean z2;
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            String str = topic;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "call_elv_ack", false, 2, (Object) null)) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$parseMqtt$1(this, null), 3, null);
                return this;
            }
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "enter_elv", false, 2, (Object) null)) {
                JSONObject jSONObject = new JSONObject(msg);
                if (jSONObject.has("elv_id")) {
                    String string = jSONObject.getString("elv_id");
                    PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                    if (pathSegments == null) {
                        Intrinsics.throwNpe();
                    }
                    List<PathSegment> segments = pathSegments.getSegments();
                    if (segments == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Intrinsics.areEqual(string, segments.get(1).getCurGoal())) {
                        z = true;
                        if (jSONObject.has("curr_floor")) {
                            String string2 = jSONObject.getString("curr_floor");
                            PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                            if (pathSegments2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (Intrinsics.areEqual(string2, pathSegments2.getCurFloor())) {
                                z2 = true;
                                if (!z && z2) {
                                    ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                                    long j = jSONObject.getLong("seq_num");
                                    String str2 = ElevatorTask.this.robotID;
                                    PathSegments pathSegments3 = ElevatorTask.this.getPathSegments();
                                    if (pathSegments3 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    List<PathSegment> segments2 = pathSegments3.getSegments();
                                    if (segments2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    String curGoal = segments2.get(1).getCurGoal();
                                    if (curGoal == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    access$getElvRemoteCommunicate$p.enterElvAck(j, str2, curGoal);
                                    Pdlog.m3273d(ElevatorTask.this.TAG, "recv elevator notice to enter elevator: current floor from elevator msg " + jSONObject.getString("curr_floor"));
                                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$parseMqtt$2(this, null), 3, null);
                                    if (this.robotInPause || this.overtimeCallElv) {
                                        Pdlog.m3273d(ElevatorTask.this.TAG, "recv elevator notice to enter elevator: robotInPause:" + this.robotInPause + " ," + this.overtimeCallElv);
                                        return this;
                                    }
                                    return new MoveToElevator();
                                }
                                return this;
                            }
                        }
                        z2 = false;
                        if (!z) {
                        }
                        return this;
                    }
                }
                z = false;
                if (jSONObject.has("curr_floor")) {
                }
                z2 = false;
                if (!z) {
                }
                return this;
            }
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00ed  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x012d  */
        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public MoveState parseProto(Elevator.Elv msg) {
            boolean z;
            PathSegments pathSegments;
            List<PathSegment> segments;
            PathSegments pathSegments2;
            PathSegments pathSegments3;
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            Pdlog.m3273d(ElevatorTask.this.TAG, "WaitElevator msg.msgType:" + msg.getMsgType());
            Elevator.Elv.MsgType msgType = msg.getMsgType();
            if (msgType != null) {
                int i = WhenMappings.$EnumSwitchMapping$3[msgType.ordinal()];
                if (i == 1) {
                    Job job = this.job;
                    if (job != null && job.isActive()) {
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$parseProto$1(this, null), 3, null);
                    }
                    return this;
                }
                if (i == 2) {
                    return this;
                }
                if (i == 3) {
                    if (msg.hasEnterElv()) {
                        String elvId = msg.getElvId();
                        PathSegments pathSegments4 = ElevatorTask.this.getPathSegments();
                        if (pathSegments4 == null) {
                            Intrinsics.throwNpe();
                        }
                        List<PathSegment> segments2 = pathSegments4.getSegments();
                        if (segments2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Intrinsics.areEqual(elvId, segments2.get(1).getCurGoal())) {
                            z = true;
                            String str = ElevatorTask.this.TAG;
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("checkElv：");
                            sb.append(z);
                            sb.append(" ,msg.hasEnterElv():");
                            sb.append(msg.hasEnterElv());
                            sb.append(", msg.elvId:");
                            sb.append(msg.getElvId());
                            sb.append(",curGoal:");
                            pathSegments = ElevatorTask.this.getPathSegments();
                            if (pathSegments == null) {
                                Intrinsics.throwNpe();
                            }
                            segments = pathSegments.getSegments();
                            if (segments == null) {
                                Intrinsics.throwNpe();
                            }
                            sb.append(segments.get(1).getCurGoal());
                            sb.append(' ');
                            objArr[0] = sb.toString();
                            Pdlog.m3273d(str, objArr);
                            Elevator.EnterElv enterElv = msg.getEnterElv();
                            Intrinsics.checkExpressionValueIsNotNull(enterElv, "msg.enterElv");
                            String currFloor = enterElv.getCurrFloor();
                            pathSegments2 = ElevatorTask.this.getPathSegments();
                            if (pathSegments2 == null) {
                                Intrinsics.throwNpe();
                            }
                            boolean areEqual = Intrinsics.areEqual(currFloor, pathSegments2.getCurFloor());
                            String str2 = ElevatorTask.this.TAG;
                            Object[] objArr2 = new Object[1];
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("checkCurrFloor：");
                            sb2.append(areEqual);
                            sb2.append(" ,msg.enterElv.currFloor:");
                            Elevator.EnterElv enterElv2 = msg.getEnterElv();
                            Intrinsics.checkExpressionValueIsNotNull(enterElv2, "msg.enterElv");
                            sb2.append(enterElv2.getCurrFloor());
                            sb2.append(",curFloor:");
                            pathSegments3 = ElevatorTask.this.getPathSegments();
                            if (pathSegments3 == null) {
                                Intrinsics.throwNpe();
                            }
                            sb2.append(pathSegments3.getCurFloor());
                            sb2.append(' ');
                            objArr2[0] = sb2.toString();
                            Pdlog.m3273d(str2, objArr2);
                            if (!z && areEqual) {
                                ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                                long seq = msg.getSeq();
                                String str3 = ElevatorTask.this.robotID;
                                PathSegments pathSegments5 = ElevatorTask.this.getPathSegments();
                                if (pathSegments5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                List<PathSegment> segments3 = pathSegments5.getSegments();
                                if (segments3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                String curGoal = segments3.get(1).getCurGoal();
                                if (curGoal == null) {
                                    Intrinsics.throwNpe();
                                }
                                access$getElvRemoteCommunicate$p.enterElvAck(seq, str3, curGoal);
                                String str4 = ElevatorTask.this.TAG;
                                Object[] objArr3 = new Object[1];
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("recv elevator notice to enter elevator: current floor from elevator msg ");
                                PathSegments pathSegments6 = ElevatorTask.this.getPathSegments();
                                if (pathSegments6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                List<PathSegment> segments4 = pathSegments6.getSegments();
                                if (segments4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                String curGoal2 = segments4.get(1).getCurGoal();
                                if (curGoal2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                sb3.append(curGoal2);
                                objArr3[0] = sb3.toString();
                                Pdlog.m3273d(str4, objArr3);
                                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitElevator$parseProto$2(this, null), 3, null);
                                if (this.robotInPause || this.overtimeCallElv) {
                                    return this;
                                }
                                ElevatorTask elevatorTask = ElevatorTask.this;
                                Elevator.EnterElv enterElv3 = msg.getEnterElv();
                                Intrinsics.checkExpressionValueIsNotNull(enterElv3, "msg.enterElv");
                                elevatorTask.MOVE_OVERTIME = enterElv3.getEffTime() * 1000;
                                return new MoveToElevator();
                            }
                            return this;
                        }
                    }
                    z = false;
                    String str5 = ElevatorTask.this.TAG;
                    Object[] objArr4 = new Object[1];
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("checkElv：");
                    sb4.append(z);
                    sb4.append(" ,msg.hasEnterElv():");
                    sb4.append(msg.hasEnterElv());
                    sb4.append(", msg.elvId:");
                    sb4.append(msg.getElvId());
                    sb4.append(",curGoal:");
                    pathSegments = ElevatorTask.this.getPathSegments();
                    if (pathSegments == null) {
                    }
                    segments = pathSegments.getSegments();
                    if (segments == null) {
                    }
                    sb4.append(segments.get(1).getCurGoal());
                    sb4.append(' ');
                    objArr4[0] = sb4.toString();
                    Pdlog.m3273d(str5, objArr4);
                    Elevator.EnterElv enterElv4 = msg.getEnterElv();
                    Intrinsics.checkExpressionValueIsNotNull(enterElv4, "msg.enterElv");
                    String currFloor2 = enterElv4.getCurrFloor();
                    pathSegments2 = ElevatorTask.this.getPathSegments();
                    if (pathSegments2 == null) {
                    }
                    boolean areEqual2 = Intrinsics.areEqual(currFloor2, pathSegments2.getCurFloor());
                    String str22 = ElevatorTask.this.TAG;
                    Object[] objArr22 = new Object[1];
                    StringBuilder sb22 = new StringBuilder();
                    sb22.append("checkCurrFloor：");
                    sb22.append(areEqual2);
                    sb22.append(" ,msg.enterElv.currFloor:");
                    Elevator.EnterElv enterElv22 = msg.getEnterElv();
                    Intrinsics.checkExpressionValueIsNotNull(enterElv22, "msg.enterElv");
                    sb22.append(enterElv22.getCurrFloor());
                    sb22.append(",curFloor:");
                    pathSegments3 = ElevatorTask.this.getPathSegments();
                    if (pathSegments3 == null) {
                    }
                    sb22.append(pathSegments3.getCurFloor());
                    sb22.append(' ');
                    objArr22[0] = sb22.toString();
                    Pdlog.m3273d(str22, objArr22);
                    if (!z) {
                    }
                    return this;
                }
            }
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String informationForElevatorListeners$default(ElevatorTask elevatorTask, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        return elevatorTask.informationForElevatorListeners(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String informationForElevatorListeners(String cur_floor) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"curr_floor\":\"");
        if (cur_floor == null) {
            PathSegments pathSegments = getPathSegments();
            if (pathSegments == null) {
                Intrinsics.throwNpe();
            }
            cur_floor = pathSegments.getCurFloor();
        }
        sb.append(cur_floor);
        sb.append("\", \"dst_floor\":\"");
        PathSegments pathSegments2 = getPathSegments();
        if (pathSegments2 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(pathSegments2.getDstFloor());
        sb.append("\", \"ele_id\":\"");
        PathSegments pathSegments3 = getPathSegments();
        if (pathSegments3 == null) {
            Intrinsics.throwNpe();
        }
        List<PathSegment> segments = pathSegments3.getSegments();
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        sb.append(segments.get(1).getCurGoal());
        sb.append("\"}");
        return sb.toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u000b\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0014\u0010\u000f\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J \u0010\u0015\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveToElevator;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "job", "Lkotlinx/coroutines/Job;", "overtimeCallElv", "", "robotArriveElevator", "startMoveToElevatorTimer", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseProto", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv;", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class MoveToElevator extends MoveState {
        private Job job;
        private boolean overtimeCallElv;
        private boolean robotArriveElevator;
        private long startMoveToElevatorTimer;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Error.ordinal()] = 3;
                $EnumSwitchMapping$1 = new int[Elevator.Elv.MsgType.values().length];
                $EnumSwitchMapping$1[Elevator.Elv.MsgType.CallElvAck.ordinal()] = 1;
            }
        }

        public MoveToElevator() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void start() {
            Job launch$default;
            super.start();
            if (ElevatorTask.this.intoElevatorCount != 5) {
                BuildersKt__BuildersKt.runBlocking$default(null, new ElevatorTask$MoveToElevator$start$2(this, null), 1, null);
                ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MoveToElevator$start$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str) {
                        invoke2(elevatorRequestListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ElevatorRequestListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.informElevatorUtilizeState(ElevatorUtilizeState.EnteringElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                    }
                });
                if (ElevatorTask.this.robotInElevatorRange()) {
                    Pdlog.m3273d(ElevatorTask.this.TAG, "robot has located at elevator, call elevator in cab");
                    launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToElevator$start$4(this, null), 3, null);
                    this.job = launch$default;
                }
                this.overtimeCallElv = false;
                ElevatorTask elevatorTask = ElevatorTask.this;
                elevatorTask.intoElevatorCount++;
                int unused = elevatorTask.intoElevatorCount;
                this.robotArriveElevator = false;
                Pdlog.m3273d(ElevatorTask.this.TAG, "pause last task, need to move into elevator");
                this.startMoveToElevatorTimer = SystemClock.elapsedRealtime();
                ElevatorTask.this.moveToPoint(1);
                return;
            }
            ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MoveToElevator$start$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str) {
                    invoke2(elevatorRequestListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ElevatorRequestListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.informElevatorUtilizeState(ElevatorUtilizeState.OvertimeEnterElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                }
            });
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void pause() {
            ElevatorTask.this.getAction().actionPause();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToElevator$pause$1(this, null), 3, null);
            Pdlog.m3273d(ElevatorTask.this.TAG, "pause robot when moving to elevator");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void resume() {
            ElevatorTask.this.getAction().actionResume();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToElevator$resume$1(this, null), 3, null);
            Pdlog.m3273d(ElevatorTask.this.TAG, "resume robot when moving to elevator");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4466switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(ElevatorTask.this.TAG, "switch robot state: " + robotState + " in MoveToElevator");
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToElevator$switch$1(this, null), 3, null);
                Pdlog.m3273d(ElevatorTask.this.TAG, "robot has move to elevator");
                if (!this.overtimeCallElv) {
                    return new Pair<>(new WaitingInElevator(), RobotState.Moving);
                }
                return new Pair<>(this, RobotState.Moving);
            }
            if (i == 2) {
                return new Pair<>(this, RobotState.Moving);
            }
            if (i == 3) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToElevator$switch$2(this, null), 3, null);
                return new Pair<>(this, RobotState.Error);
            }
            if (SystemClock.elapsedRealtime() - this.startMoveToElevatorTimer > ElevatorTask.this.MOVE_OVERTIME && !ElevatorTask.this.robotInElevatorRange()) {
                Pdlog.m3273d(ElevatorTask.this.TAG, "robot move to elevator overtime1,MOVE_OVERTIME: " + ElevatorTask.this.MOVE_OVERTIME + " ,startMoveToElevatorTimer:" + this.startMoveToElevatorTimer);
                return new Pair<>(new WaitElevator(), SDKRobotStateKt.peerConversion(robotState));
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "call_elv_ack", false, 2, (Object) null)) {
                ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MoveToElevator$parseMqtt$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str) {
                        invoke2(elevatorRequestListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ElevatorRequestListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.informElevatorUtilizeState(ElevatorUtilizeState.EnteringElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                    }
                });
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToElevator$parseMqtt$2(this, null), 3, null);
            }
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseProto(Elevator.Elv msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            Pdlog.m3273d(ElevatorTask.this.TAG, "MoveToElevator msg.msgType:" + msg.getMsgType());
            Elevator.Elv.MsgType msgType = msg.getMsgType();
            if (msgType != null && WhenMappings.$EnumSwitchMapping$1[msgType.ordinal()] == 1) {
                ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MoveToElevator$parseProto$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str) {
                        invoke2(elevatorRequestListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ElevatorRequestListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.informElevatorUtilizeState(ElevatorUtilizeState.EnteringElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                    }
                });
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToElevator$parseProto$2(this, null), 3, null);
            }
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u001c\u0010\u0014\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0016J\u0014\u0010\u0017\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J \u0010\u001c\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$WaitingInElevator;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "controlWheelJob", "Lkotlinx/coroutines/Job;", "elvPoses", "", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "job", "overtimeEnterdElv", "", "waitState", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$WaitingState;", "checkResumeWhetherOutElv", "controlWheel", "", "informEnterElevator", "floor", "parseMqtt", "topic", NotificationCompat.CATEGORY_MESSAGE, "parseProto", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv;", "pause", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class WaitingInElevator extends MoveState {
        private Job controlWheelJob;
        private Map<String, Vector3d> elvPoses;
        private Job job;
        private boolean overtimeEnterdElv;
        private WaitingState waitState;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ElevatorConnectionType.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;
            public static final /* synthetic */ int[] $EnumSwitchMapping$3;
            public static final /* synthetic */ int[] $EnumSwitchMapping$4;
            public static final /* synthetic */ int[] $EnumSwitchMapping$5;

            static {
                $EnumSwitchMapping$0[ElevatorConnectionType.Mqtt.ordinal()] = 1;
                $EnumSwitchMapping$1 = new int[SDKRobotState.values().length];
                $EnumSwitchMapping$1[SDKRobotState.Resume.ordinal()] = 1;
                $EnumSwitchMapping$1[SDKRobotState.Approaching.ordinal()] = 2;
                $EnumSwitchMapping$1[SDKRobotState.Arrive.ordinal()] = 3;
                $EnumSwitchMapping$2 = new int[WaitingState.values().length];
                $EnumSwitchMapping$2[WaitingState.Normal.ordinal()] = 1;
                $EnumSwitchMapping$3 = new int[WaitingState.values().length];
                $EnumSwitchMapping$3[WaitingState.Normal.ordinal()] = 1;
                $EnumSwitchMapping$4 = new int[Elevator.Elv.MsgType.values().length];
                $EnumSwitchMapping$4[Elevator.Elv.MsgType.ElvEnteredAck.ordinal()] = 1;
                $EnumSwitchMapping$4[Elevator.Elv.MsgType.LeaveElv.ordinal()] = 2;
                $EnumSwitchMapping$5 = new int[WaitingState.values().length];
                $EnumSwitchMapping$5[WaitingState.Pause.ordinal()] = 1;
            }
        }

        public WaitingInElevator() {
            super();
            this.waitState = WaitingState.Normal;
            this.elvPoses = new LinkedHashMap();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void informEnterElevator(String floor) {
            Job launch$default;
            ElevatorTask elevatorTask = ElevatorTask.this;
            elevatorTask.seq++;
            int unused = elevatorTask.seq;
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$informEnterElevator$1(this, floor, null), 3, null);
            this.job = launch$default;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void controlWheel() {
            Job launch$default;
            Job job = this.controlWheelJob;
            if (job == null || !job.isActive()) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$controlWheel$1(this, null), 3, null);
                this.controlWheelJob = launch$default;
            }
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void start() {
            super.start();
            this.waitState = WaitingState.Normal;
            Pdlog.m3273d(ElevatorTask.this.TAG, "finish enter elevator, notice elevator");
            ElevatorTask elevatorTask = ElevatorTask.this;
            elevatorTask.seq++;
            int unused = elevatorTask.seq;
            BuildersKt__BuildersKt.runBlocking$default(null, new ElevatorTask$WaitingInElevator$start$1(this, null), 1, null);
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void pause() {
            Pdlog.m3273d(ElevatorTask.this.TAG, "pause when robot waiting in elevator");
            this.waitState = WaitingState.Pause;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$pause$1(this, null), 3, null);
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void resume() {
            Pdlog.m3273d(ElevatorTask.this.TAG, "resume when robot waiting in elevator");
            ElevatorTask.this.onStateChange(SDKRobotState.Resume, "");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4466switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(ElevatorTask.this.TAG, "switch robot state: " + robotState + " in " + WaitingInElevator.class.getSimpleName());
            int i = WhenMappings.$EnumSwitchMapping$1[robotState.ordinal()];
            if (i != 1) {
                if (i == 2 || i == 3) {
                    return new Pair<>(this, RobotState.Moving);
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (checkResumeWhetherOutElv()) {
                Pdlog.m3273d(ElevatorTask.this.TAG, "robot has been moved out of elevator, run MovingOutElevator task");
                return new Pair<>(new MovingOutElevator(), SDKRobotStateKt.peerConversion(robotState));
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00e5  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00f3 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00ea  */
        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public MoveState parseMqtt(String topic, String msg) {
            boolean z;
            boolean z2;
            PathSegments pathSegments;
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            String str = topic;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "elv_entered_ack", false, 2, (Object) null)) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$parseMqtt$1(this, null), 3, null);
                return this;
            }
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "leave_elv", false, 2, (Object) null)) {
                JSONObject jSONObject = new JSONObject(msg);
                if (jSONObject.has("elv_id")) {
                    String string = jSONObject.getString("elv_id");
                    PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                    if (pathSegments2 == null) {
                        Intrinsics.throwNpe();
                    }
                    List<PathSegment> segments = pathSegments2.getSegments();
                    if (segments == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Intrinsics.areEqual(string, segments.get(1).getCurGoal())) {
                        z = true;
                        if (jSONObject.has("curr_floor")) {
                            String string2 = jSONObject.getString("curr_floor");
                            PathSegments pathSegments3 = ElevatorTask.this.getPathSegments();
                            if (pathSegments3 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (Intrinsics.areEqual(string2, pathSegments3.getDstFloor())) {
                                z2 = true;
                                String str2 = ElevatorTask.this.TAG;
                                Object[] objArr = new Object[1];
                                StringBuilder sb = new StringBuilder();
                                sb.append("recv msg to leave elevator, dst floor ");
                                pathSegments = ElevatorTask.this.getPathSegments();
                                if (pathSegments == null) {
                                    Intrinsics.throwNpe();
                                }
                                sb.append(pathSegments.getDstFloor());
                                sb.append(", msg has curr_floor ");
                                sb.append(jSONObject.has("curr_floor"));
                                sb.append(" and");
                                objArr[0] = sb.toString();
                                Pdlog.m3273d(str2, objArr);
                                String str3 = ElevatorTask.this.TAG;
                                Object[] objArr2 = new Object[1];
                                objArr2[0] = jSONObject.has("curr_floor") ? jSONObject.getString("curr_floor") : "";
                                Pdlog.m3273d(str3, objArr2);
                                if (z || !z2) {
                                    return this;
                                }
                                if (WhenMappings.$EnumSwitchMapping$2[this.waitState.ordinal()] == 1) {
                                    MovingOutElevator movingOutElevator = new MovingOutElevator();
                                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$parseMqtt$2(this, jSONObject, null), 3, null);
                                    return movingOutElevator;
                                }
                                this.waitState = WaitingState.LeaveWhenPause;
                                return this;
                            }
                        }
                        z2 = false;
                        String str22 = ElevatorTask.this.TAG;
                        Object[] objArr3 = new Object[1];
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("recv msg to leave elevator, dst floor ");
                        pathSegments = ElevatorTask.this.getPathSegments();
                        if (pathSegments == null) {
                        }
                        sb2.append(pathSegments.getDstFloor());
                        sb2.append(", msg has curr_floor ");
                        sb2.append(jSONObject.has("curr_floor"));
                        sb2.append(" and");
                        objArr3[0] = sb2.toString();
                        Pdlog.m3273d(str22, objArr3);
                        String str32 = ElevatorTask.this.TAG;
                        Object[] objArr22 = new Object[1];
                        objArr22[0] = jSONObject.has("curr_floor") ? jSONObject.getString("curr_floor") : "";
                        Pdlog.m3273d(str32, objArr22);
                        if (z) {
                        }
                        return this;
                    }
                }
                z = false;
                if (jSONObject.has("curr_floor")) {
                }
                z2 = false;
                String str222 = ElevatorTask.this.TAG;
                Object[] objArr32 = new Object[1];
                StringBuilder sb22 = new StringBuilder();
                sb22.append("recv msg to leave elevator, dst floor ");
                pathSegments = ElevatorTask.this.getPathSegments();
                if (pathSegments == null) {
                }
                sb22.append(pathSegments.getDstFloor());
                sb22.append(", msg has curr_floor ");
                sb22.append(jSONObject.has("curr_floor"));
                sb22.append(" and");
                objArr32[0] = sb22.toString();
                Pdlog.m3273d(str222, objArr32);
                String str322 = ElevatorTask.this.TAG;
                Object[] objArr222 = new Object[1];
                objArr222[0] = jSONObject.has("curr_floor") ? jSONObject.getString("curr_floor") : "";
                Pdlog.m3273d(str322, objArr222);
                if (z) {
                }
                return this;
            }
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseProto(Elevator.Elv msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            Pdlog.m3273d(ElevatorTask.this.TAG, "WaitingInElevator msg.msgType:" + msg.getMsgType());
            Elevator.Elv.MsgType msgType = msg.getMsgType();
            if (msgType != null) {
                int i = WhenMappings.$EnumSwitchMapping$4[msgType.ordinal()];
                if (i == 1) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$parseProto$1(this, null), 3, null);
                    return this;
                }
                if (i == 2) {
                    String elvId = msg.getElvId();
                    PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                    if (pathSegments == null) {
                        Intrinsics.throwNpe();
                    }
                    List<PathSegment> segments = pathSegments.getSegments();
                    if (segments == null) {
                        Intrinsics.throwNpe();
                    }
                    boolean areEqual = Intrinsics.areEqual(elvId, segments.get(1).getCurGoal());
                    Elevator.LeaveElv leaveElv = msg.getLeaveElv();
                    Intrinsics.checkExpressionValueIsNotNull(leaveElv, "msg.leaveElv");
                    String currFloor = leaveElv.getCurrFloor();
                    PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                    if (pathSegments2 == null) {
                        Intrinsics.throwNpe();
                    }
                    boolean areEqual2 = Intrinsics.areEqual(currFloor, pathSegments2.getDstFloor());
                    ElevatorTask elevatorTask = ElevatorTask.this;
                    Elevator.LeaveElv leaveElv2 = msg.getLeaveElv();
                    Intrinsics.checkExpressionValueIsNotNull(leaveElv2, "msg.leaveElv");
                    elevatorTask.MOVE_OVERTIME = leaveElv2.getEffTime() * 1000;
                    String str = ElevatorTask.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("recv msg to leave elevator, dst floor ");
                    PathSegments pathSegments3 = ElevatorTask.this.getPathSegments();
                    if (pathSegments3 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(pathSegments3.getDstFloor());
                    sb.append(", msg has curr_floor ");
                    Elevator.LeaveElv leaveElv3 = msg.getLeaveElv();
                    Intrinsics.checkExpressionValueIsNotNull(leaveElv3, "msg.leaveElv");
                    sb.append(leaveElv3.getCurrFloor());
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                    if (!areEqual || !areEqual2) {
                        return this;
                    }
                    if (WhenMappings.$EnumSwitchMapping$3[this.waitState.ordinal()] == 1) {
                        MovingOutElevator movingOutElevator = new MovingOutElevator();
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$parseProto$2(this, msg, null), 3, null);
                        return movingOutElevator;
                    }
                    this.waitState = WaitingState.LeaveWhenPause;
                    return this;
                }
            }
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final boolean checkResumeWhetherOutElv() {
            boolean z;
            if (!this.elvPoses.isEmpty()) {
                Map<String, Vector3d> map = this.elvPoses;
                PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                if (pathSegments == null) {
                    Intrinsics.throwNpe();
                }
                List<PathSegment> segments = pathSegments.getSegments();
                if (segments == null) {
                    Intrinsics.throwNpe();
                }
                Vector3d vector3d = map.get(segments.get(1).getCurGoal());
                if (vector3d != null) {
                    double x = ElevatorTask.this.robotPose.getX() - vector3d.getX();
                    double y = ElevatorTask.this.robotPose.getY() - vector3d.getY();
                    if (Math.sqrt((x * x) + (y * y)) > 1.0d) {
                        z = true;
                        if (!z) {
                            return z;
                        }
                        if (WhenMappings.$EnumSwitchMapping$5[this.waitState.ordinal()] != 1) {
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$WaitingInElevator$checkResumeWhetherOutElv$1(this, null), 3, null);
                            controlWheel();
                        } else {
                            if (this.job != null) {
                                PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                                if (pathSegments2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                informEnterElevator(pathSegments2.getCurFloor());
                            }
                            controlWheel();
                        }
                        this.waitState = WaitingState.Normal;
                        return false;
                    }
                }
            }
            z = false;
            if (!z) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\n\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0014\u0010\u000e\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\r\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0002J \u0010\u0015\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$MovingOutElevator;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "job", "Lkotlinx/coroutines/Job;", "recallElevator", "", "startOutElevator", "", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseProto", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv;", "pause", "", "resume", "start", "startLeftElvNoticeJob", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class MovingOutElevator extends MoveState {
        private Job job;
        private boolean recallElevator;
        private long startOutElevator;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ElevatorConnectionType.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;

            static {
                $EnumSwitchMapping$0[ElevatorConnectionType.Mqtt.ordinal()] = 1;
                $EnumSwitchMapping$1 = new int[SDKRobotState.values().length];
                $EnumSwitchMapping$1[SDKRobotState.Approaching.ordinal()] = 1;
                $EnumSwitchMapping$1[SDKRobotState.Arrive.ordinal()] = 2;
                $EnumSwitchMapping$2 = new int[Elevator.Elv.MsgType.values().length];
                $EnumSwitchMapping$2[Elevator.Elv.MsgType.LeaveElv.ordinal()] = 1;
                $EnumSwitchMapping$2[Elevator.Elv.MsgType.ElvLeftAck.ordinal()] = 2;
            }
        }

        public MovingOutElevator() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void start() {
            super.start();
            Pdlog.m3273d(ElevatorTask.this.TAG, "start moving out elevator task");
            if (WhenMappings.$EnumSwitchMapping$0[ElevatorTask.elvConnection.ordinal()] == 1) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$start$1(this, null), 3, null);
            }
            this.startOutElevator = SystemClock.elapsedRealtime();
            ElevatorTask.this.moveToPoint(2);
            ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MovingOutElevator$start$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str) {
                    invoke2(elevatorRequestListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ElevatorRequestListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.informElevatorUtilizeState(ElevatorUtilizeState.LeavingElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                }
            });
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void pause() {
            this.recallElevator = false;
            ElevatorTask.this.getAction().actionPause();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$pause$1(this, null), 3, null);
            Pdlog.m3273d(ElevatorTask.this.TAG, "pause robot when moving out elevator");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void resume() {
            ElevatorTask.this.getAction().actionResume();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$resume$1(this, null), 3, null);
            Pdlog.m3273d(ElevatorTask.this.TAG, "resume robot when moving out elevator, should check moving time");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4466switch(SDKRobotState robotState) {
            Job launch$default;
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(ElevatorTask.this.TAG, "switch robot state: " + robotState + " in " + MovingOutElevator.class.getSimpleName());
            int i = WhenMappings.$EnumSwitchMapping$1[robotState.ordinal()];
            if (i == 1 || i == 2) {
                Pdlog.m3273d(ElevatorTask.this.TAG, "robot arrive temp pose to tell elevator it has left");
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$switch$1(this, null), 3, null);
                startLeftElvNoticeJob();
                return new Pair<>(this, RobotState.Arrive);
            }
            if (SystemClock.elapsedRealtime() - this.startOutElevator > ElevatorTask.this.MOVE_OVERTIME && !this.recallElevator) {
                this.recallElevator = true;
                Pdlog.m3273d(ElevatorTask.this.TAG, "robot overtime to moving out of elevator,MOVE_OVERTIME:" + ElevatorTask.this.MOVE_OVERTIME + ",recallElevator:" + this.recallElevator);
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$switch$2(this, null), 3, null);
                this.job = launch$default;
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0090  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00d0 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0106  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x010f  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00c7  */
        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public MoveState parseMqtt(String topic, String msg) {
            boolean z;
            boolean z2;
            PathSegments pathSegments;
            PathSegments pathSegments2;
            List<PathSegment> segments;
            String curGoal;
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "leave_elv", false, 2, (Object) null)) {
                JSONObject jSONObject = new JSONObject(msg);
                if (jSONObject.has("elv_id")) {
                    String string = jSONObject.getString("elv_id");
                    PathSegments pathSegments3 = ElevatorTask.this.getPathSegments();
                    if (pathSegments3 == null) {
                        Intrinsics.throwNpe();
                    }
                    List<PathSegment> segments2 = pathSegments3.getSegments();
                    if (segments2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Intrinsics.areEqual(string, segments2.get(1).getCurGoal())) {
                        z = true;
                        if (jSONObject.has("curr_floor")) {
                            String string2 = jSONObject.getString("curr_floor");
                            PathSegments pathSegments4 = ElevatorTask.this.getPathSegments();
                            if (pathSegments4 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (Intrinsics.areEqual(string2, pathSegments4.getDstFloor())) {
                                z2 = true;
                                String str = ElevatorTask.this.TAG;
                                Object[] objArr = new Object[1];
                                StringBuilder sb = new StringBuilder();
                                sb.append("re-recv msg to leave elevator, dst floor ");
                                pathSegments = ElevatorTask.this.getPathSegments();
                                if (pathSegments == null) {
                                    Intrinsics.throwNpe();
                                }
                                sb.append(pathSegments.getDstFloor());
                                sb.append(", msg has curr_floor ");
                                sb.append(jSONObject.has("curr_floor"));
                                sb.append(" and");
                                objArr[0] = sb.toString();
                                Pdlog.m3273d(str, objArr);
                                String str2 = ElevatorTask.this.TAG;
                                Object[] objArr2 = new Object[1];
                                objArr2[0] = jSONObject.has("curr_floor") ? jSONObject.getString("curr_floor") : "";
                                Pdlog.m3273d(str2, objArr2);
                                if (z && z2 && this.recallElevator) {
                                    this.recallElevator = false;
                                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$parseMqtt$1(this, null), 3, null);
                                    ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                                    long j = jSONObject.getLong("seq_num");
                                    String str3 = ElevatorTask.this.robotID;
                                    pathSegments2 = ElevatorTask.this.getPathSegments();
                                    if (pathSegments2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    segments = pathSegments2.getSegments();
                                    if (segments == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    curGoal = segments.get(1).getCurGoal();
                                    if (curGoal == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    access$getElvRemoteCommunicate$p.leaveElvAck(j, str3, curGoal);
                                    this.startOutElevator = SystemClock.elapsedRealtime();
                                    ElevatorTask.this.moveToPoint(2);
                                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$parseMqtt$2(this, null), 3, null);
                                }
                                return this;
                            }
                        }
                        z2 = false;
                        String str4 = ElevatorTask.this.TAG;
                        Object[] objArr3 = new Object[1];
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("re-recv msg to leave elevator, dst floor ");
                        pathSegments = ElevatorTask.this.getPathSegments();
                        if (pathSegments == null) {
                        }
                        sb2.append(pathSegments.getDstFloor());
                        sb2.append(", msg has curr_floor ");
                        sb2.append(jSONObject.has("curr_floor"));
                        sb2.append(" and");
                        objArr3[0] = sb2.toString();
                        Pdlog.m3273d(str4, objArr3);
                        String str22 = ElevatorTask.this.TAG;
                        Object[] objArr22 = new Object[1];
                        objArr22[0] = jSONObject.has("curr_floor") ? jSONObject.getString("curr_floor") : "";
                        Pdlog.m3273d(str22, objArr22);
                        if (z) {
                            this.recallElevator = false;
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$parseMqtt$1(this, null), 3, null);
                            ElevatorCommunicateInterface access$getElvRemoteCommunicate$p2 = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                            long j2 = jSONObject.getLong("seq_num");
                            String str32 = ElevatorTask.this.robotID;
                            pathSegments2 = ElevatorTask.this.getPathSegments();
                            if (pathSegments2 == null) {
                            }
                            segments = pathSegments2.getSegments();
                            if (segments == null) {
                            }
                            curGoal = segments.get(1).getCurGoal();
                            if (curGoal == null) {
                            }
                            access$getElvRemoteCommunicate$p2.leaveElvAck(j2, str32, curGoal);
                            this.startOutElevator = SystemClock.elapsedRealtime();
                            ElevatorTask.this.moveToPoint(2);
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$parseMqtt$2(this, null), 3, null);
                        }
                        return this;
                    }
                }
                z = false;
                if (jSONObject.has("curr_floor")) {
                }
                z2 = false;
                String str42 = ElevatorTask.this.TAG;
                Object[] objArr32 = new Object[1];
                StringBuilder sb22 = new StringBuilder();
                sb22.append("re-recv msg to leave elevator, dst floor ");
                pathSegments = ElevatorTask.this.getPathSegments();
                if (pathSegments == null) {
                }
                sb22.append(pathSegments.getDstFloor());
                sb22.append(", msg has curr_floor ");
                sb22.append(jSONObject.has("curr_floor"));
                sb22.append(" and");
                objArr32[0] = sb22.toString();
                Pdlog.m3273d(str42, objArr32);
                String str222 = ElevatorTask.this.TAG;
                Object[] objArr222 = new Object[1];
                objArr222[0] = jSONObject.has("curr_floor") ? jSONObject.getString("curr_floor") : "";
                Pdlog.m3273d(str222, objArr222);
                if (z) {
                }
                return this;
            }
            return this;
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseProto(Elevator.Elv msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            Pdlog.m3273d(ElevatorTask.this.TAG, "MovingOutElevator msg.msgType:" + msg.getMsgType());
            Elevator.Elv.MsgType msgType = msg.getMsgType();
            if (msgType != null) {
                int i = WhenMappings.$EnumSwitchMapping$2[msgType.ordinal()];
                if (i == 1) {
                    String elvId = msg.getElvId();
                    PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                    if (pathSegments == null) {
                        Intrinsics.throwNpe();
                    }
                    List<PathSegment> segments = pathSegments.getSegments();
                    if (segments == null) {
                        Intrinsics.throwNpe();
                    }
                    boolean areEqual = Intrinsics.areEqual(elvId, segments.get(1).getCurGoal());
                    Elevator.LeaveElv leaveElv = msg.getLeaveElv();
                    Intrinsics.checkExpressionValueIsNotNull(leaveElv, "msg.leaveElv");
                    String currFloor = leaveElv.getCurrFloor();
                    PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                    if (pathSegments2 == null) {
                        Intrinsics.throwNpe();
                    }
                    boolean areEqual2 = Intrinsics.areEqual(currFloor, pathSegments2.getDstFloor());
                    String str = ElevatorTask.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("re-recv msg to leave elevator, dst floor ");
                    PathSegments pathSegments3 = ElevatorTask.this.getPathSegments();
                    if (pathSegments3 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(pathSegments3.getDstFloor());
                    sb.append(", msg has curr_floor ");
                    Elevator.LeaveElv leaveElv2 = msg.getLeaveElv();
                    Intrinsics.checkExpressionValueIsNotNull(leaveElv2, "msg.leaveElv");
                    sb.append(leaveElv2.getCurrFloor());
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                    if (areEqual && areEqual2 && this.recallElevator) {
                        this.recallElevator = false;
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$parseProto$1(this, null), 3, null);
                        ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                        long seq = msg.getSeq();
                        String str2 = ElevatorTask.this.robotID;
                        PathSegments pathSegments4 = ElevatorTask.this.getPathSegments();
                        if (pathSegments4 == null) {
                            Intrinsics.throwNpe();
                        }
                        List<PathSegment> segments2 = pathSegments4.getSegments();
                        if (segments2 == null) {
                            Intrinsics.throwNpe();
                        }
                        String curGoal = segments2.get(1).getCurGoal();
                        if (curGoal == null) {
                            Intrinsics.throwNpe();
                        }
                        access$getElvRemoteCommunicate$p.leaveElvAck(seq, str2, curGoal);
                        this.startOutElevator = SystemClock.elapsedRealtime();
                        ElevatorTask.this.moveToPoint(2);
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$parseProto$2(this, null), 3, null);
                    }
                    return this;
                }
                if (i == 2) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$parseProto$3(this, null), 3, null);
                    return this;
                }
            }
            return this;
        }

        private final void startLeftElvNoticeJob() {
            Job launch$default;
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MovingOutElevator$startLeftElvNoticeJob$1(this, null), 3, null);
            this.job = launch$default;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ElevatorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0014\u0010\n\u001a\u00060\u0001R\u00020\u00022\u0006\u0010\t\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0002J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002J \u0010\u0014\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveToFinalGoal;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "job", "Lkotlinx/coroutines/Job;", "parseMqtt", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "parseProto", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv;", "pause", "", "resume", "start", "startLeftElvNoticeJob", "startLeftStatNoticeJob", "stat", "curr", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class MoveToFinalGoal extends MoveState {
        private Job job;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 2;
                $EnumSwitchMapping$1 = new int[Elevator.Elv.MsgType.values().length];
                $EnumSwitchMapping$1[Elevator.Elv.MsgType.ElvLeftAck.ordinal()] = 1;
            }
        }

        public MoveToFinalGoal() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void start() {
            super.start();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToFinalGoal$start$1(this, null), 3, null);
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void pause() {
            ElevatorTask.this.getAction().actionPause();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToFinalGoal$pause$1(this, null), 3, null);
            Pdlog.m3273d(ElevatorTask.this.TAG, "pause when move to final goal");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public void resume() {
            Job job;
            ElevatorTask.this.getAction().actionResume();
            Job job2 = this.job;
            if (job2 != null && (job2 == null || !job2.isActive())) {
                startLeftElvNoticeJob();
            }
            if (ElevatorTask.this.elvLeftStatJob != null && ((job = ElevatorTask.this.elvLeftStatJob) == null || !job.isActive())) {
                PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                if (pathSegments == null) {
                    Intrinsics.throwNpe();
                }
                startLeftStatNoticeJob("OK", pathSegments.getDstFloor());
            }
            Pdlog.m3273d(ElevatorTask.this.TAG, "resume when move to final goal");
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4466switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Pdlog.m3273d(ElevatorTask.this.TAG, "switch robot state: " + robotState + " in " + MoveToFinalGoal.class.getSimpleName());
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1 || i == 2) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToFinalGoal$switch$1(this, null), 3, null);
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseMqtt(String topic, String msg) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            if (StringsKt.contains$default((CharSequence) topic, (CharSequence) "elv_left_ack", false, 2, (Object) null)) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToFinalGoal$parseMqtt$1(this, null), 3, null);
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void startLeftElvNoticeJob() {
            Job launch$default;
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1(this, null), 3, null);
            this.job = launch$default;
        }

        @Override // com.pudutech.mirsdk.movetask.ElevatorTask.MoveState
        public MoveState parseProto(Elevator.Elv msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            Pdlog.m3273d(ElevatorTask.this.TAG, "MoveToFinalGoal msg.msgType:" + msg.getMsgType());
            Elevator.Elv.MsgType msgType = msg.getMsgType();
            if (msgType != null && WhenMappings.$EnumSwitchMapping$1[msgType.ordinal()] == 1) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToFinalGoal$parseProto$1(this, null), 3, null);
            }
            return this;
        }

        private final void startLeftStatNoticeJob(String stat, String curr) {
            Job launch$default;
            ElevatorTask elevatorTask = ElevatorTask.this;
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ElevatorTask$MoveToFinalGoal$startLeftStatNoticeJob$1(this, stat, curr, null), 3, null);
            elevatorTask.elvLeftStatJob = launch$default;
        }
    }
}
