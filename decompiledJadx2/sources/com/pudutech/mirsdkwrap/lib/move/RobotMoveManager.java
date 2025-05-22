package com.pudutech.mirsdkwrap.lib.move;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.oss.OssDropUpload;
import com.pudutech.mirsdk.aidl.CliffDistanceStateListener;
import com.pudutech.mirsdk.aidl.MapAreaDetectionListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByChargeTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveCruiseTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveDestinationTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Dispatchers;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotMoveManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017*\u0002\u000b\u0017\bÆ\u0002\u0018\u00002\u00020\u0001:\u000e\u0093\u0001\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001\u0099\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010^\u001a\u00020,2\u0006\u0010_\u001a\u00020=J\u000e\u0010`\u001a\u00020,2\u0006\u0010_\u001a\u00020\"J-\u0010a\u001a\u00020,2%\u0010_\u001a!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,0(j\u0002`-JB\u0010b\u001a\u00020,2:\u0010_\u001a6\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(2\u0012\u0013\u0012\u001103¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020,01j\u0002`5J\u000e\u0010c\u001a\u00020,2\u0006\u0010_\u001a\u000209J\u000e\u0010d\u001a\u00020,2\u0006\u0010_\u001a\u00020EJ\u000e\u0010e\u001a\u00020,2\u0006\u0010_\u001a\u00020NJ\u000e\u0010f\u001a\u00020,2\u0006\u0010_\u001a\u00020ZJ\u0010\u0010g\u001a\u0004\u0018\u00010h2\u0006\u0010i\u001a\u00020jJ\u000e\u0010k\u001a\u00020,2\u0006\u0010l\u001a\u00020\u0010J\u0006\u0010m\u001a\u00020AJ\b\u0010n\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010o\u001a\u00020RJ%\u0010p\u001a\u00020,2\u0006\u0010\u001d\u001a\u00020q2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\brJ\u0006\u0010s\u001a\u00020\u0010J\u0010\u0010t\u001a\u0004\u0018\u00010u2\u0006\u0010i\u001a\u00020vJ*\u0010w\u001a\u0004\u0018\u00010x2\u0016\u0010y\u001a\u0012\u0012\u0004\u0012\u00020{0zj\b\u0012\u0004\u0012\u00020{`|2\b\b\u0002\u0010}\u001a\u00020~J\u0012\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u00012\u0007\u0010i\u001a\u00030\u0081\u0001J\n\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0083\u0001J\u000f\u0010\u0084\u0001\u001a\u00020,2\u0006\u0010_\u001a\u00020=J\u000f\u0010\u0085\u0001\u001a\u00020,2\u0006\u0010_\u001a\u00020\"J.\u0010\u0086\u0001\u001a\u00020,2%\u0010_\u001a!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,0(j\u0002`-JC\u0010\u0087\u0001\u001a\u00020,2:\u0010_\u001a6\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(2\u0012\u0013\u0012\u001103¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020,01j\u0002`5J\u000f\u0010\u0088\u0001\u001a\u00020,2\u0006\u0010_\u001a\u000209J\u000f\u0010\u0089\u0001\u001a\u00020,2\u0006\u0010_\u001a\u00020EJ\u000f\u0010\u008a\u0001\u001a\u00020,2\u0006\u0010_\u001a\u00020NJ\u000f\u0010\u008b\u0001\u001a\u00020,2\u0006\u0010_\u001a\u00020ZJ\u0010\u0010\u008c\u0001\u001a\u00020,2\u0007\u0010\u008d\u0001\u001a\u000203J\u0016\u0010\u008e\u0001\u001a\u00020,2\u000b\u0010\u008f\u0001\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\u0010\u0010\u0090\u0001\u001a\u00020\u00102\u0007\u0010\u0091\u0001\u001a\u00020IJ\u0010\u0010\u0092\u0001\u001a\u00020\u00102\u0007\u0010\u0091\u0001\u001a\u00020IR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR!\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R@\u0010'\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,0(j\u0002`-0!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010&\u001a\u0004\b.\u0010$RU\u00100\u001a<\u00128\u00126\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(2\u0012\u0013\u0012\u001103¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020,01j\u0002`50!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010&\u001a\u0004\b6\u0010$R!\u00108\u001a\b\u0012\u0004\u0012\u0002090!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010&\u001a\u0004\b:\u0010$R!\u0010<\u001a\b\u0012\u0004\u0012\u00020=0!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b?\u0010&\u001a\u0004\b>\u0010$R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010B\u001a\u001a\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020,0CX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010D\u001a\b\u0012\u0004\u0012\u00020E0!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bG\u0010&\u001a\u0004\bF\u0010$R-\u0010H\u001a!\u0012\u0013\u0012\u00110I¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020,0(j\u0002`KX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010L\u001a\u0014\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020,01X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010M\u001a\b\u0012\u0004\u0012\u00020N0!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bP\u0010&\u001a\u0004\bO\u0010$RF\u0010Q\u001a:\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(S\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(T\u0012\u0004\u0012\u00020,01j\u0002`UX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010V\u001a\u00020I2\u0006\u0010\u001b\u001a\u00020I@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bW\u0010XR!\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\\\u0010&\u001a\u0004\b[\u0010$R-\u0010]\u001a!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,0(j\u0002`-X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u009a\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager;", "", "()V", "TAG", "", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;", "baseMove", "setBaseMove", "(Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;)V", "cliffDistanceStateListener", "com/pudutech/mirsdkwrap/lib/move/RobotMoveManager$cliffDistanceStateListener$1", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$cliffDistanceStateListener$1;", "deviceControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "isDangerArea", "", "()Z", "setDangerArea", "(Z)V", "isUpdataDropPic", "setUpdataDropPic", "mDefMapAreaDetectionListener", "com/pudutech/mirsdkwrap/lib/move/RobotMoveManager$mDefMapAreaDetectionListener$1", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$mDefMapAreaDetectionListener$1;", "mirSdkListenerWrap", "Lcom/pudutech/mirsdkwrap/lib/robot/MirSdkListenerWrap;", "<set-?>", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "moveActionInterface", "getMoveActionInterface", "()Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "onRobotMoveTaskChangeListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$OnRobotMoveTaskChangeListener;", "getOnRobotMoveTaskChangeListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "onRobotMoveTaskChangeListeners$delegate", "Lkotlin/Lazy;", "onSecurityFeedBackListeners", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "isOpen", "", "Lcom/pudutech/mirsdkwrap/lib/interf/SecurityFeedBackListener;", "getOnSecurityFeedBackListeners", "onSecurityFeedBackListeners$delegate", "onSpeedLimitAreaChangeListeners", "Lkotlin/Function2;", "isInArea", "", "maxLimitSpeed", "Lcom/pudutech/mirsdkwrap/lib/interf/OnSpeedLimitAreaListener;", "getOnSpeedLimitAreaChangeListeners", "onSpeedLimitAreaChangeListeners$delegate", "robotCommonMovingStateChangeListeners", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$OnRobotMoveStateChangeListener;", "getRobotCommonMovingStateChangeListeners", "robotCommonMovingStateChangeListeners$delegate", "robotMovingTargetChangeListeners", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$OnRobotMoveTargetChangeListener;", "getRobotMovingTargetChangeListeners", "robotMovingTargetChangeListeners$delegate", "robotPose2D", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$Pose2D;", "robotPoseListener", "Lkotlin/Function3;", "robotPoseListeners", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$RobotPoseListener;", "getRobotPoseListeners", "robotPoseListeners$delegate", "robotSchedulerCountListener", "", "count", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSchedulerCountListener;", "robotSpeedListener", "robotSpeedListeners", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$RobotSpeedListener;", "getRobotSpeedListeners", "robotSpeedListeners$delegate", "robotStateListener", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "p0", "p1", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "schedulerCount", "getSchedulerCount", "()I", "schedulerCountChangeListeners", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$SchedulerCountChangeListener;", "getSchedulerCountChangeListeners", "schedulerCountChangeListeners$delegate", "securityFeedBackListener", "addOnRobotMoveTargetChangeListener", "l", "addOnRobotMoveTaskChangeListener", "addOnSecurityFeedBackListener", "addOnSpeedLimitAreaChangeListener", "addRobotCommonMovingStateChangeListener", "addRobotPoseListener", "addRobotSpeedListener", "addSchedulerCountChangeListener", "cruise", "Lcom/pudutech/mirsdkwrap/lib/move/MoveCruise;", "task", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveCruiseTask;", "enableReplan", "enable", "get2DPosition", "getDevicesControlHelper", "getMoveState", "init", "Lcom/pudutech/mirsdk/aidl/MoveActionInterface;", "init$module_robot_mirsdk_wrapper_release", "isWorking", "moveByCharge", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByCharge;", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByChargeTask;", "moveByDestination", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByDestination;", "destinationTasks", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveDestinationTask;", "Lkotlin/collections/ArrayList;", "moveSortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "moveByGroup", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByGroup;", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByGroupTask;", "moveBySuspendCharge", "Lcom/pudutech/mirsdkwrap/lib/move/MoveBySuspendCharge;", "removeOnRobotMoveTargetChangeListener", "removeOnRobotMoveTaskChangeListener", "removeOnSecurityFeedBackListener", "removeOnSpeedLimitAreaChangeListener", "removeRobotCommonMovingStateChangeListener", "removeRobotPoseListener", "removeRobotSpeedListener", "removeSchedulerCountChangeListener", "rotate", "radians", "setMoveDestroyListener", "moveTask", "setReplanWaitTime", "time", "setRoadBlockTime", "OnRobotMoveStateChangeListener", "OnRobotMoveTargetChangeListener", "OnRobotMoveTaskChangeListener", "Pose2D", "RobotPoseListener", "RobotSpeedListener", "SchedulerCountChangeListener", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotMoveManager {
    private static BaseMove<?> baseMove;
    private static DevicesControlHelper deviceControlHelper;
    private static volatile boolean isDangerArea;
    private static boolean isUpdataDropPic;
    private static MirSdkListenerWrap mirSdkListenerWrap;
    private static RobotMoveInterfaceDecorator moveActionInterface;
    private static int schedulerCount;
    public static final RobotMoveManager INSTANCE = new RobotMoveManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final Pose2D robotPose2D = new Pose2D(0.0d, 0.0d, 0.0d);

    /* renamed from: schedulerCountChangeListeners$delegate, reason: from kotlin metadata */
    private static final Lazy schedulerCountChangeListeners = LazyKt.lazy(new Function0<ListenerList<SchedulerCountChangeListener>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$schedulerCountChangeListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RobotMoveManager.SchedulerCountChangeListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotSpeedListeners$delegate, reason: from kotlin metadata */
    private static final Lazy robotSpeedListeners = LazyKt.lazy(new Function0<ListenerList<RobotSpeedListener>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotSpeedListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RobotMoveManager.RobotSpeedListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotPoseListeners$delegate, reason: from kotlin metadata */
    private static final Lazy robotPoseListeners = LazyKt.lazy(new Function0<ListenerList<RobotPoseListener>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotPoseListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RobotMoveManager.RobotPoseListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotMovingTargetChangeListeners$delegate, reason: from kotlin metadata */
    private static final Lazy robotMovingTargetChangeListeners = LazyKt.lazy(new Function0<ListenerList<OnRobotMoveTargetChangeListener>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotMovingTargetChangeListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RobotMoveManager.OnRobotMoveTargetChangeListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotCommonMovingStateChangeListeners$delegate, reason: from kotlin metadata */
    private static final Lazy robotCommonMovingStateChangeListeners = LazyKt.lazy(new Function0<ListenerList<OnRobotMoveStateChangeListener>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotCommonMovingStateChangeListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RobotMoveManager.OnRobotMoveStateChangeListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onRobotMoveTaskChangeListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onRobotMoveTaskChangeListeners = LazyKt.lazy(new Function0<ListenerList<OnRobotMoveTaskChangeListener>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$onRobotMoveTaskChangeListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RobotMoveManager.OnRobotMoveTaskChangeListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onSpeedLimitAreaChangeListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onSpeedLimitAreaChangeListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super Boolean, ? super Double, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$onSpeedLimitAreaChangeListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function2<? super Boolean, ? super Double, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onSecurityFeedBackListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onSecurityFeedBackListeners = LazyKt.lazy(new Function0<ListenerList<Function1<? super Boolean, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$onSecurityFeedBackListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function1<? super Boolean, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });
    private static final Function2<Double, Double, Unit> robotSpeedListener = new Function2<Double, Double, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotSpeedListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Double d, Double d2) {
            invoke(d.doubleValue(), d2.doubleValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final double d, final double d2) {
            ListenerList robotSpeedListeners2;
            robotSpeedListeners2 = RobotMoveManager.INSTANCE.getRobotSpeedListeners();
            robotSpeedListeners2.forEach(Dispatchers.getMain(), new Function1<RobotMoveManager.RobotSpeedListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotSpeedListener$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RobotMoveManager.RobotSpeedListener robotSpeedListener2) {
                    invoke2(robotSpeedListener2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RobotMoveManager.RobotSpeedListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onMove(d, d2);
                }
            });
        }
    };
    private static final Function2<RobotState, String, Unit> robotStateListener = new Function2<RobotState, String, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotStateListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
            invoke2(robotState, str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(RobotState robotState, String str) {
            RobotMoveInterfaceDecorator moveActionInterface2 = RobotMoveManager.INSTANCE.getMoveActionInterface();
            if (moveActionInterface2 != null) {
                moveActionInterface2.onStateChange$module_robot_mirsdk_wrapper_release(robotState, str);
            }
        }
    };
    private static final Function1<Boolean, Unit> securityFeedBackListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$securityFeedBackListener$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final boolean z) {
            ListenerList onSecurityFeedBackListeners2;
            onSecurityFeedBackListeners2 = RobotMoveManager.INSTANCE.getOnSecurityFeedBackListeners();
            onSecurityFeedBackListeners2.forEach(Dispatchers.getMain(), new Function1<Function1<? super Boolean, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$securityFeedBackListener$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Boolean, ? extends Unit> function1) {
                    invoke2((Function1<? super Boolean, Unit>) function1);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Function1<? super Boolean, Unit> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.invoke(Boolean.valueOf(z));
                }
            });
        }
    };
    private static final Function1<Integer, Unit> robotSchedulerCountListener = new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotSchedulerCountListener$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final int i) {
            ListenerList schedulerCountChangeListeners2;
            if (RobotMoveManager.INSTANCE.getSchedulerCount() != i) {
                RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
                RobotMoveManager.schedulerCount = i;
                schedulerCountChangeListeners2 = RobotMoveManager.INSTANCE.getSchedulerCountChangeListeners();
                schedulerCountChangeListeners2.forEach(Dispatchers.getMain(), new Function1<RobotMoveManager.SchedulerCountChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotSchedulerCountListener$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(RobotMoveManager.SchedulerCountChangeListener schedulerCountChangeListener) {
                        invoke2(schedulerCountChangeListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RobotMoveManager.SchedulerCountChangeListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onCountChange(i);
                    }
                });
            }
        }
    };
    private static final Function3<Double, Double, Double, Unit> robotPoseListener = new Function3<Double, Double, Double, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotPoseListener$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Double d, Double d2, Double d3) {
            invoke(d.doubleValue(), d2.doubleValue(), d3.doubleValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final double d, final double d2, final double d3) {
            RobotMoveManager.Pose2D pose2D;
            RobotMoveManager.Pose2D pose2D2;
            RobotMoveManager.Pose2D pose2D3;
            ListenerList robotPoseListeners2;
            RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
            pose2D = RobotMoveManager.robotPose2D;
            pose2D.setX(d);
            RobotMoveManager robotMoveManager2 = RobotMoveManager.INSTANCE;
            pose2D2 = RobotMoveManager.robotPose2D;
            pose2D2.setY(d2);
            RobotMoveManager robotMoveManager3 = RobotMoveManager.INSTANCE;
            pose2D3 = RobotMoveManager.robotPose2D;
            pose2D3.setYaw(d3);
            robotPoseListeners2 = RobotMoveManager.INSTANCE.getRobotPoseListeners();
            robotPoseListeners2.forEach(Dispatchers.getMain(), new Function1<RobotMoveManager.RobotPoseListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$robotPoseListener$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RobotMoveManager.RobotPoseListener robotPoseListener2) {
                    invoke2(robotPoseListener2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RobotMoveManager.RobotPoseListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onPose(d, d2, d3);
                }
            });
        }
    };
    private static final RobotMoveManager$cliffDistanceStateListener$1 cliffDistanceStateListener = new CliffDistanceStateListener.Stub() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$cliffDistanceStateListener$1
        @Override // com.pudutech.mirsdk.aidl.CliffDistanceStateListener
        public void cliffDistance(int p0) {
        }

        @Override // com.pudutech.mirsdk.aidl.CliffDistanceStateListener
        public void enterSpeedLimitArea(boolean p0) {
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0070 A[Catch: Exception -> 0x00a0, TryCatch #0 {Exception -> 0x00a0, blocks: (B:4:0x002a, B:6:0x0032, B:8:0x005e, B:12:0x0070, B:15:0x007d, B:19:0x008d), top: B:2:0x0028 }] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x007d A[Catch: Exception -> 0x00a0, TryCatch #0 {Exception -> 0x00a0, blocks: (B:4:0x002a, B:6:0x0032, B:8:0x005e, B:12:0x0070, B:15:0x007d, B:19:0x008d), top: B:2:0x0028 }] */
        @Override // com.pudutech.mirsdk.aidl.CliffDistanceStateListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void cliffIrImg(ParcelFileDescriptor p0, int p1) {
            String str;
            String str2;
            String str3;
            boolean z;
            String str4;
            RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
            str = RobotMoveManager.TAG;
            Pdlog.m3273d(str, "cliffIrImg()p0:" + p0 + "###p1:" + p1);
            try {
                if (p0 != null) {
                    if (!RobotMoveManager.INSTANCE.isUpdataDropPic()) {
                        return;
                    }
                    Locale locale = BaseApp.INSTANCE.getINSTANCE().getResources().getConfiguration().locale;
                    Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
                    String language = locale.getLanguage();
                    String country = locale.getCountry();
                    Intrinsics.checkExpressionValueIsNotNull(language, "language");
                    if (StringsKt.endsWith$default(language, "zh", false, 2, (Object) null)) {
                        Intrinsics.checkExpressionValueIsNotNull(country, "country");
                        if (StringsKt.endsWith$default(country, "CN", false, 2, (Object) null)) {
                            z = true;
                            if (!z) {
                                new OssDropUpload.Builder().build().upLoadCliffIrImg(p0, p1);
                                return;
                            }
                            RobotMoveManager robotMoveManager2 = RobotMoveManager.INSTANCE;
                            str4 = RobotMoveManager.TAG;
                            Pdlog.m3273d(str4, "cliffIrImg() no is zh_CN:");
                            return;
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                } else {
                    RobotMoveManager$cliffDistanceStateListener$1 robotMoveManager$cliffDistanceStateListener$1 = this;
                    RobotMoveManager robotMoveManager3 = RobotMoveManager.INSTANCE;
                    str3 = RobotMoveManager.TAG;
                    Pdlog.m3273d(str3, "cliffIrImg() ParcelFileDescriptor is null");
                }
            } catch (Exception e) {
                RobotMoveManager robotMoveManager4 = RobotMoveManager.INSTANCE;
                str2 = RobotMoveManager.TAG;
                Pdlog.m3273d(str2, "cliffIrImg():" + e.getMessage());
            }
        }
    };
    private static final RobotMoveManager$mDefMapAreaDetectionListener$1 mDefMapAreaDetectionListener = new MapAreaDetectionListener.Stub() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$mDefMapAreaDetectionListener$1
        @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
        public void OnSpeedLimitListener(final boolean p0, final double p1) {
            ListenerList onSpeedLimitAreaChangeListeners2;
            String str;
            RobotMoveManager.INSTANCE.setDangerArea(p0);
            onSpeedLimitAreaChangeListeners2 = RobotMoveManager.INSTANCE.getOnSpeedLimitAreaChangeListeners();
            onSpeedLimitAreaChangeListeners2.forEach(Dispatchers.getMain(), new Function1<Function2<? super Boolean, ? super Double, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$mDefMapAreaDetectionListener$1$OnSpeedLimitListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Boolean, ? super Double, ? extends Unit> function2) {
                    invoke2((Function2<? super Boolean, ? super Double, Unit>) function2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Function2<? super Boolean, ? super Double, Unit> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.invoke(Boolean.valueOf(p0), Double.valueOf(p1));
                }
            });
            RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
            str = RobotMoveManager.TAG;
            Pdlog.m3273d(str, "OnSpeedLimitListener p0 = " + p0 + " p1 = " + p1 + " isDangerArea =" + RobotMoveManager.INSTANCE.isDangerArea());
        }

        @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
        public void OnNoDetourListener(boolean p0, double p1) {
            String str;
            RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
            str = RobotMoveManager.TAG;
            Pdlog.m3273d(str, "OnNoDetourListener p0 = " + p0 + " p1 = " + p1);
        }

        @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
        public void OnRGBDFunLimitListener(boolean p0, int p1) {
            String str;
            RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
            str = RobotMoveManager.TAG;
            Pdlog.m3273d(str, "OnRGBDFunLimitListener p0 = " + p0 + " p1 = " + p1);
        }

        @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
        public void OnElevatorZoneListener(boolean p0) {
            String str;
            RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
            str = RobotMoveManager.TAG;
            Pdlog.m3273d(str, "OnElevatorZoneListener p0 = " + p0);
        }
    };

    /* compiled from: RobotMoveManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$OnRobotMoveStateChangeListener;", "", "onMovingState", "", "i", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "errors", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnRobotMoveStateChangeListener {
        void onMovingState(RobotState i, MoveErrorHelper errors);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMoveManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$OnRobotMoveTargetChangeListener;", "", "onMovingTarget", "", "i", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnRobotMoveTargetChangeListener {
        void onMovingTarget(String i);
    }

    /* compiled from: RobotMoveManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$OnRobotMoveTaskChangeListener;", "", "onMovingTask", "", "moveTask", "Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnRobotMoveTaskChangeListener {
        void onMovingTask(BaseMove<?> moveTask);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMoveManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$RobotPoseListener;", "", "onPose", "", "x", "", "y", "yaw", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface RobotPoseListener {
        void onPose(double x, double y, double yaw);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMoveManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$RobotSpeedListener;", "", "onMove", "", "left", "", "right", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface RobotSpeedListener {
        void onMove(double left, double right);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMoveManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$SchedulerCountChangeListener;", "", "onCountChange", "", "int", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface SchedulerCountChangeListener {
        void onCountChange(int r1);
    }

    private final ListenerList<OnRobotMoveTaskChangeListener> getOnRobotMoveTaskChangeListeners() {
        return (ListenerList) onRobotMoveTaskChangeListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<Function1<Boolean, Unit>> getOnSecurityFeedBackListeners() {
        return (ListenerList) onSecurityFeedBackListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<Function2<Boolean, Double, Unit>> getOnSpeedLimitAreaChangeListeners() {
        return (ListenerList) onSpeedLimitAreaChangeListeners.getValue();
    }

    private final ListenerList<OnRobotMoveStateChangeListener> getRobotCommonMovingStateChangeListeners() {
        return (ListenerList) robotCommonMovingStateChangeListeners.getValue();
    }

    private final ListenerList<OnRobotMoveTargetChangeListener> getRobotMovingTargetChangeListeners() {
        return (ListenerList) robotMovingTargetChangeListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<RobotPoseListener> getRobotPoseListeners() {
        return (ListenerList) robotPoseListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<RobotSpeedListener> getRobotSpeedListeners() {
        return (ListenerList) robotSpeedListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<SchedulerCountChangeListener> getSchedulerCountChangeListeners() {
        return (ListenerList) schedulerCountChangeListeners.getValue();
    }

    private RobotMoveManager() {
    }

    public final RobotMoveInterfaceDecorator getMoveActionInterface() {
        return moveActionInterface;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBaseMove(final BaseMove<?> baseMove2) {
        getOnRobotMoveTaskChangeListeners().forEach(new Function1<OnRobotMoveTaskChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$baseMove$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotMoveManager.OnRobotMoveTaskChangeListener onRobotMoveTaskChangeListener) {
                invoke2(onRobotMoveTaskChangeListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RobotMoveManager.OnRobotMoveTaskChangeListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onMovingTask(BaseMove.this);
            }
        });
        baseMove = baseMove2;
    }

    public final int getSchedulerCount() {
        return schedulerCount;
    }

    public final Pose2D get2DPosition() {
        return robotPose2D;
    }

    public final void init$module_robot_mirsdk_wrapper_release(MoveActionInterface moveActionInterface2, MirSdkListenerWrap mirSdkListenerWrap2, DevicesControlHelper deviceControlHelper2) {
        Intrinsics.checkParameterIsNotNull(moveActionInterface2, "moveActionInterface");
        Intrinsics.checkParameterIsNotNull(mirSdkListenerWrap2, "mirSdkListenerWrap");
        Intrinsics.checkParameterIsNotNull(deviceControlHelper2, "deviceControlHelper");
        Pdlog.m3273d(TAG, "init ");
        deviceControlHelper = deviceControlHelper2;
        moveActionInterface = new RobotMoveInterfaceDecorator(moveActionInterface2, deviceControlHelper2, getRobotMovingTargetChangeListeners());
        mirSdkListenerWrap = mirSdkListenerWrap2;
        mirSdkListenerWrap2.getRobotSpeedListeners().addNotSame$module_robot_mirsdk_wrapper_release(robotSpeedListener);
        mirSdkListenerWrap2.getRobotStateListeners().addNotSame$module_robot_mirsdk_wrapper_release(robotStateListener);
        mirSdkListenerWrap2.getRobotPoseListeners().addNotSame$module_robot_mirsdk_wrapper_release(robotPoseListener);
        mirSdkListenerWrap2.getRobotSchedulerCountListeners().addNotSame$module_robot_mirsdk_wrapper_release(robotSchedulerCountListener);
        mirSdkListenerWrap2.getSecurityFeedBackListeners().addNotSame$module_robot_mirsdk_wrapper_release(securityFeedBackListener);
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator != null) {
            robotMoveInterfaceDecorator.addCliffDistanceStateListener(TAG, cliffDistanceStateListener);
        }
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator2 = moveActionInterface;
        if (robotMoveInterfaceDecorator2 != null) {
            robotMoveInterfaceDecorator2.addMapAreaDetectionListener(TAG, mDefMapAreaDetectionListener);
        }
    }

    public static /* synthetic */ MoveByDestination moveByDestination$default(RobotMoveManager robotMoveManager, ArrayList arrayList, MoveSortType moveSortType, int i, Object obj) {
        if ((i & 2) != 0) {
            moveSortType = MoveSortType.AUTO;
        }
        return robotMoveManager.moveByDestination(arrayList, moveSortType);
    }

    public final MoveByDestination moveByDestination(ArrayList<MoveDestinationTask> destinationTasks, MoveSortType moveSortType) {
        Intrinsics.checkParameterIsNotNull(destinationTasks, "destinationTasks");
        Intrinsics.checkParameterIsNotNull(moveSortType, "moveSortType");
        BaseMove<?> baseMove2 = baseMove;
        if (baseMove2 != null && baseMove2 != null) {
            baseMove2.destroy$module_robot_mirsdk_wrapper_release();
        }
        if (moveActionInterface == null) {
            Pdlog.m3274e(TAG, "moveDestination : moveActionInterface is null");
            return null;
        }
        if (deviceControlHelper == null) {
            Pdlog.m3274e(TAG, "moveDestination : deviceControlHelper is null");
            return null;
        }
        Pdlog.m3273d(TAG, "moveDestination : destinationTasks = " + destinationTasks + "; moveSortType = " + moveSortType + "; ");
        ArrayList arrayList = new ArrayList();
        ArrayList<MoveDestinationTask> arrayList2 = destinationTasks;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator<T> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(MoveDestinationTask.copy$default((MoveDestinationTask) it.next(), null, null, 3, null));
        }
        arrayList.addAll(arrayList3);
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator == null) {
            Intrinsics.throwNpe();
        }
        DevicesControlHelper devicesControlHelper = deviceControlHelper;
        if (devicesControlHelper == null) {
            Intrinsics.throwNpe();
        }
        MirSdkListenerWrap mirSdkListenerWrap2 = mirSdkListenerWrap;
        MoveByDestination moveByDestination = new MoveByDestination(robotMoveInterfaceDecorator, devicesControlHelper, arrayList, moveSortType, mirSdkListenerWrap2 != null ? mirSdkListenerWrap2.getRobotSpeedListeners() : null);
        MoveByDestination moveByDestination2 = moveByDestination;
        setMoveDestroyListener(moveByDestination2);
        setBaseMove(moveByDestination2);
        return moveByDestination;
    }

    private final void setMoveDestroyListener(final BaseMove<?> moveTask) {
        moveTask.setOnDestroyListener$module_robot_mirsdk_wrapper_release(new Function0<Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveManager$setMoveDestroyListener$1
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
                BaseMove baseMove2;
                RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
                baseMove2 = RobotMoveManager.baseMove;
                if (Intrinsics.areEqual(baseMove2, BaseMove.this)) {
                    RobotMoveManager.INSTANCE.setBaseMove((BaseMove) null);
                }
            }
        });
    }

    public final MoveByGroup moveByGroup(MoveByGroupTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Pdlog.m3273d(TAG, "moveByGroup");
        BaseMove<?> baseMove2 = baseMove;
        if (baseMove2 != null && baseMove2 != null) {
            baseMove2.destroy$module_robot_mirsdk_wrapper_release();
        }
        if (moveActionInterface == null) {
            Pdlog.m3274e(TAG, "moveGroup : moveActionInterface is null");
            return null;
        }
        if (deviceControlHelper == null) {
            Pdlog.m3274e(TAG, "moveGroup : deviceControlHelper is null");
            return null;
        }
        Pdlog.m3273d(TAG, "moveGroup : task = " + task + "; ");
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator == null) {
            Intrinsics.throwNpe();
        }
        DevicesControlHelper devicesControlHelper = deviceControlHelper;
        if (devicesControlHelper == null) {
            Intrinsics.throwNpe();
        }
        MirSdkListenerWrap mirSdkListenerWrap2 = mirSdkListenerWrap;
        MoveByGroup moveByGroup = new MoveByGroup(robotMoveInterfaceDecorator, devicesControlHelper, task, mirSdkListenerWrap2 != null ? mirSdkListenerWrap2.getRobotSpeedListeners() : null);
        MoveByGroup moveByGroup2 = moveByGroup;
        setMoveDestroyListener(moveByGroup2);
        setBaseMove(moveByGroup2);
        return moveByGroup;
    }

    public final MoveByCharge moveByCharge(MoveByChargeTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Pdlog.m3273d(TAG, "moveByCharge");
        BaseMove<?> baseMove2 = baseMove;
        if (baseMove2 != null && baseMove2 != null) {
            baseMove2.destroy$module_robot_mirsdk_wrapper_release();
        }
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator == null) {
            Pdlog.m3274e(TAG, "moveGroup : moveActionInterface is null");
            return null;
        }
        if (deviceControlHelper == null) {
            Pdlog.m3274e(TAG, "moveGroup : deviceControlHelper is null");
            return null;
        }
        if (robotMoveInterfaceDecorator == null) {
            Intrinsics.throwNpe();
        }
        DevicesControlHelper devicesControlHelper = deviceControlHelper;
        if (devicesControlHelper == null) {
            Intrinsics.throwNpe();
        }
        MirSdkListenerWrap mirSdkListenerWrap2 = mirSdkListenerWrap;
        MoveByCharge moveByCharge = new MoveByCharge(robotMoveInterfaceDecorator, devicesControlHelper, task, mirSdkListenerWrap2 != null ? mirSdkListenerWrap2.getRobotSpeedListeners() : null);
        MoveByCharge moveByCharge2 = moveByCharge;
        setMoveDestroyListener(moveByCharge2);
        setBaseMove(moveByCharge2);
        return moveByCharge;
    }

    public final MoveBySuspendCharge moveBySuspendCharge() {
        Pdlog.m3273d(TAG, "moveBySuspendCharge");
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator == null) {
            Pdlog.m3274e(TAG, "moveBySuspendCharge : moveActionInterface is null");
            return null;
        }
        if (deviceControlHelper == null) {
            Pdlog.m3274e(TAG, "moveBySuspendCharge : deviceControlHelper is null");
            return null;
        }
        if (robotMoveInterfaceDecorator == null) {
            Intrinsics.throwNpe();
        }
        DevicesControlHelper devicesControlHelper = deviceControlHelper;
        if (devicesControlHelper == null) {
            Intrinsics.throwNpe();
        }
        MirSdkListenerWrap mirSdkListenerWrap2 = mirSdkListenerWrap;
        MoveBySuspendCharge moveBySuspendCharge = new MoveBySuspendCharge(robotMoveInterfaceDecorator, devicesControlHelper, mirSdkListenerWrap2 != null ? mirSdkListenerWrap2.getRobotSpeedListeners() : null);
        MoveBySuspendCharge moveBySuspendCharge2 = moveBySuspendCharge;
        setMoveDestroyListener(moveBySuspendCharge2);
        setBaseMove(moveBySuspendCharge2);
        return moveBySuspendCharge;
    }

    public final RobotState getMoveState() {
        RobotState currentMoveState;
        BaseMove<?> baseMove2 = baseMove;
        return (baseMove2 == null || (currentMoveState = baseMove2.getCurrentMoveState()) == null) ? RobotState.Idle : currentMoveState;
    }

    public final MoveCruise cruise(MoveCruiseTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        BaseMove<?> baseMove2 = baseMove;
        if (baseMove2 != null && baseMove2 != null) {
            baseMove2.destroy$module_robot_mirsdk_wrapper_release();
        }
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator == null) {
            Pdlog.m3274e(TAG, "moveGroup : moveActionInterface is null");
            return null;
        }
        if (deviceControlHelper == null) {
            Pdlog.m3274e(TAG, "moveGroup : deviceControlHelper is null");
            return null;
        }
        if (robotMoveInterfaceDecorator == null) {
            Intrinsics.throwNpe();
        }
        DevicesControlHelper devicesControlHelper = deviceControlHelper;
        if (devicesControlHelper == null) {
            Intrinsics.throwNpe();
        }
        MirSdkListenerWrap mirSdkListenerWrap2 = mirSdkListenerWrap;
        ListenerList<Function0<Unit>> goalCruiseListeners = mirSdkListenerWrap2 != null ? mirSdkListenerWrap2.getGoalCruiseListeners() : null;
        MirSdkListenerWrap mirSdkListenerWrap3 = mirSdkListenerWrap;
        MoveCruise moveCruise = new MoveCruise(robotMoveInterfaceDecorator, devicesControlHelper, task, goalCruiseListeners, mirSdkListenerWrap3 != null ? mirSdkListenerWrap3.getRobotSpeedListeners() : null);
        MoveCruise moveCruise2 = moveCruise;
        setMoveDestroyListener(moveCruise2);
        setBaseMove(moveCruise2);
        return moveCruise;
    }

    public final void rotate(double radians) {
        if (moveActionInterface == null) {
            Pdlog.m3274e(TAG, "rotate : moveActionInterface is null");
            return;
        }
        double yaw = robotPose2D.getYaw();
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator == null) {
            Intrinsics.throwNpe();
        }
        new MoveRotation(robotMoveInterfaceDecorator, radians, yaw).start();
    }

    public final void addSchedulerCountChangeListener(SchedulerCountChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "addSchedulerCountChangeListener : l = " + l + "; ");
        getSchedulerCountChangeListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeSchedulerCountChangeListener(SchedulerCountChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeSchedulerCountChangeListener : l = " + l + "; ");
        getSchedulerCountChangeListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addRobotSpeedListener(RobotSpeedListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "addRobotSpeedListener : l = " + l + "; ");
        getRobotSpeedListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeRobotSpeedListener(RobotSpeedListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeRobotSpeedListener : l = " + l + "; ");
        getRobotSpeedListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addRobotPoseListener(RobotPoseListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "addRobotPoseListener : l = " + l + "; ");
        getRobotPoseListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeRobotPoseListener(RobotPoseListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeRobotPoseListener : l = " + l + "; ");
        getRobotPoseListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addOnRobotMoveTargetChangeListener(OnRobotMoveTargetChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "addOnRobotMoveTargetChangeListener : l = " + l + "; ");
        getRobotMovingTargetChangeListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeOnRobotMoveTargetChangeListener(OnRobotMoveTargetChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeOnRobotMoveTargetChangeListener : l = " + l + "; ");
        getRobotMovingTargetChangeListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addRobotCommonMovingStateChangeListener(OnRobotMoveStateChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getRobotCommonMovingStateChangeListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeRobotCommonMovingStateChangeListener(OnRobotMoveStateChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeRobotCommonMovingStateChangeListener : l = " + l + "; ");
        getRobotCommonMovingStateChangeListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addOnRobotMoveTaskChangeListener(OnRobotMoveTaskChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnRobotMoveTaskChangeListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeOnRobotMoveTaskChangeListener(OnRobotMoveTaskChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeOnRobotMoveTaskChangeListener : l = " + l + "; ");
        getOnRobotMoveTaskChangeListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addOnSecurityFeedBackListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "addOnSecurityFeedBackListener : l = " + l + "; ");
        getOnSecurityFeedBackListeners().add$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void removeOnSecurityFeedBackListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeOnSecurityFeedBackListener : l = " + l + "; ");
        getOnSecurityFeedBackListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addOnSpeedLimitAreaChangeListener(Function2<? super Boolean, ? super Double, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "addOnSpeedLimitAreaChangeListener : l = " + l + "; ");
        getOnSpeedLimitAreaChangeListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void removeOnSpeedLimitAreaChangeListener(Function2<? super Boolean, ? super Double, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnSpeedLimitAreaChangeListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final boolean isWorking() {
        BaseMove<?> baseMove2 = baseMove;
        if (baseMove2 != null) {
            if (!(baseMove2 != null ? baseMove2.getIsDestroy() : true)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMoveManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$Pose2D;", "", "x", "", "y", "yaw", "(DDD)V", "getX", "()D", "setX", "(D)V", "getY", "setY", "getYaw", "setYaw", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class Pose2D {
        private double x;
        private double y;
        private double yaw;

        public static /* synthetic */ Pose2D copy$default(Pose2D pose2D, double d, double d2, double d3, int i, Object obj) {
            if ((i & 1) != 0) {
                d = pose2D.x;
            }
            double d4 = d;
            if ((i & 2) != 0) {
                d2 = pose2D.y;
            }
            double d5 = d2;
            if ((i & 4) != 0) {
                d3 = pose2D.yaw;
            }
            return pose2D.copy(d4, d5, d3);
        }

        /* renamed from: component1, reason: from getter */
        public final double getX() {
            return this.x;
        }

        /* renamed from: component2, reason: from getter */
        public final double getY() {
            return this.y;
        }

        /* renamed from: component3, reason: from getter */
        public final double getYaw() {
            return this.yaw;
        }

        public final Pose2D copy(double x, double y, double yaw) {
            return new Pose2D(x, y, yaw);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pose2D)) {
                return false;
            }
            Pose2D pose2D = (Pose2D) other;
            return Double.compare(this.x, pose2D.x) == 0 && Double.compare(this.y, pose2D.y) == 0 && Double.compare(this.yaw, pose2D.yaw) == 0;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.x);
            long doubleToLongBits2 = Double.doubleToLongBits(this.y);
            int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
            long doubleToLongBits3 = Double.doubleToLongBits(this.yaw);
            return i + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
        }

        public String toString() {
            return "Pose2D(x=" + this.x + ", y=" + this.y + ", yaw=" + this.yaw + ")";
        }

        public Pose2D(double d, double d2, double d3) {
            this.x = d;
            this.y = d2;
            this.yaw = d3;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public final double getYaw() {
            return this.yaw;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final void setYaw(double d) {
            this.yaw = d;
        }
    }

    public final boolean isUpdataDropPic() {
        return isUpdataDropPic;
    }

    public final void setUpdataDropPic(boolean z) {
        isUpdataDropPic = z;
    }

    public final boolean isDangerArea() {
        return isDangerArea;
    }

    public final void setDangerArea(boolean z) {
        isDangerArea = z;
    }

    public final DevicesControlHelper getDevicesControlHelper() {
        return deviceControlHelper;
    }

    public final void enableReplan(boolean enable) {
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator != null) {
            robotMoveInterfaceDecorator.enableStuckReplan(enable);
        }
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator2 = moveActionInterface;
        if (robotMoveInterfaceDecorator2 != null) {
            robotMoveInterfaceDecorator2.enableDynamicRoadblock(enable);
        }
    }

    public final boolean setReplanWaitTime(int time) {
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator != null) {
            return robotMoveInterfaceDecorator.setReplanWaitTime(time);
        }
        return false;
    }

    public final boolean setRoadBlockTime(int time) {
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveActionInterface;
        if (robotMoveInterfaceDecorator != null) {
            return robotMoveInterfaceDecorator.setRoadBlockTime(time);
        }
        return false;
    }
}
