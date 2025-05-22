package com.pudutech.mirsdkwrap.lib.move;

import android.os.IBinder;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.CliffDistanceStateListener;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.FillInStateListener;
import com.pudutech.mirsdk.aidl.MapAreaDetectionListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdkwrap.lib.enums.PointType;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotMoveInterfaceDecorator.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\"\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ)\u0010\u001f\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\n2\u000e\u0010\u0019\u001a\n  *\u0004\u0018\u00010!0!H\u0096\u0001J)\u0010\"\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\n2\u000e\u0010\u0019\u001a\n  *\u0004\u0018\u00010#0#H\u0096\u0001J\u0011\u0010$\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020%H\u0096\u0001J)\u0010&\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\n2\u000e\u0010\u0019\u001a\n  *\u0004\u0018\u00010'0'H\u0096\u0001J)\u0010(\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\n2\u000e\u0010\u0019\u001a\n  *\u0004\u0018\u00010)0)H\u0096\u0001J)\u0010*\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\n2\u000e\u0010\u0019\u001a\n  *\u0004\u0018\u00010+0+H\u0096\u0001JF\u0010,\u001a\u00020\u000f2>\u0010-\u001a:\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000f0\u0015j\u0002`\u001aJ\u0011\u0010.\u001a\n  *\u0004\u0018\u00010/0/H\u0096\u0001J\b\u00100\u001a\u00020%H\u0002J\t\u00101\u001a\u00020\u000fH\u0096\u0001Ja\u00102\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010404  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010404\u0018\u000105032*\u0010\u0018\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010\n0\n  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010\n0\n\u0018\u000105032\u0006\u0010\u0019\u001a\u00020%H\u0096\u0001J\u0011\u00106\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020%H\u0096\u0001J\u0011\u00107\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020%H\u0096\u0001J\b\u00108\u001a\u00020\u000fH\u0002J\b\u00109\u001a\u00020\u000fH\u0002J\b\u0010:\u001a\u00020\u000fH\u0002J\t\u0010;\u001a\u00020<H\u0096\u0001J\t\u0010=\u001a\u00020<H\u0096\u0001J-\u0010>\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010?0?  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010?0?\u0018\u00010503H\u0096\u0001J-\u0010@\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010\n0\n  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010\n0\n\u0018\u00010503H\u0096\u0001J\t\u0010A\u001a\u00020%H\u0096\u0001J\u000e\u0010B\u001a\u00020<2\u0006\u0010C\u001a\u00020\nJ-\u0010D\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010E0E  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010E0E\u0018\u00010503H\u0096\u0001J\t\u0010F\u001a\u00020%H\u0096\u0001J\t\u0010G\u001a\u00020HH\u0096\u0001J\u0011\u0010I\u001a\n  *\u0004\u0018\u00010J0JH\u0096\u0001J=\u0010K\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010E0E  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010E0E\u0018\u000105032\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\nH\u0096\u0001J!\u0010L\u001a\n  *\u0004\u0018\u00010\n0\n2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010M0MH\u0096\u0001J4\u0010N\u001a(\u0012\f\u0012\n  *\u0004\u0018\u00010\n0\n  *\u0014\u0012\u000e\b\u0001\u0012\n  *\u0004\u0018\u00010\n0\n\u0018\u00010O0OH\u0096\u0001¢\u0006\u0002\u0010PJ\t\u0010Q\u001a\u00020<H\u0096\u0001J\u0010\u0010R\u001a\u00020\u000f2\u0006\u0010S\u001a\u00020\nH\u0016J*\u0010T\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020H2\b\u0010\u0019\u001a\u0004\u0018\u00010U2\u000e\u0010V\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u000103H\u0016J\u001a\u0010W\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010UH\u0016J$\u0010X\u001a\u00020%2\b\u0010\u0018\u001a\u0004\u0018\u00010\n2\b\u0010\u0019\u001a\u0004\u0018\u00010U2\u0006\u0010V\u001a\u00020%H\u0016J,\u0010Y\u001a\u00020%2\u0006\u0010C\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010U2\u0006\u0010Z\u001a\u00020[2\b\b\u0002\u0010\\\u001a\u00020%H\u0002J\u001a\u0010]\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010UH\u0016J\t\u0010^\u001a\u00020%H\u0096\u0001J=\u0010_\u001a\n  *\u0004\u0018\u000104042*\u0010\u0018\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010\n0\n  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010\n0\n\u0018\u00010503H\u0096\u0001J!\u0010`\u001a\u00020\u000f2\b\u0010a\u001a\u0004\u0018\u00010\u00122\b\u0010b\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0002\bcJ\b\u0010d\u001a\u00020\u000fH\u0002J\t\u0010e\u001a\u00020\u000fH\u0096\u0001J\b\u0010f\u001a\u00020\u000fH\u0016J\t\u0010g\u001a\u00020\u000fH\u0096\u0001J\u0019\u0010h\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\nH\u0096\u0001J\u0019\u0010i\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\nH\u0096\u0001J\u0019\u0010j\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\nH\u0096\u0001J\u0019\u0010k\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\nH\u0096\u0001J\u0019\u0010l\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010\n0\nH\u0096\u0001JF\u0010m\u001a\u00020\u000f2>\u0010-\u001a:\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000f0\u0015j\u0002`\u001aJ\b\u0010n\u001a\u00020\u000fH\u0002J\b\u0010o\u001a\u00020\u000fH\u0016J\u0011\u0010p\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020<H\u0096\u0001J\u0011\u0010q\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020<H\u0096\u0001J\u0011\u0010r\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020<H\u0096\u0001J\u0011\u0010s\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020%H\u0096\u0001J\u0011\u0010t\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020HH\u0096\u0001J\u0011\u0010u\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020HH\u0096\u0001J\u0019\u0010v\u001a\u00020\u000f2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010J0JH\u0096\u0001J\b\u0010w\u001a\u00020\u000fH\u0002J*\u0010x\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010U2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020%H\u0002J\b\u0010y\u001a\u00020\u000fH\u0002J)\u0010z\u001a\u00020%2\u000e\u0010\u0018\u001a\n  *\u0004\u0018\u00010M0M2\u000e\u0010\u0019\u001a\n  *\u0004\u0018\u00010\n0\nH\u0096\u0001J\t\u0010{\u001a\u00020\u000fH\u0096\u0001J\u0011\u0010|\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020<H\u0096\u0001R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000RY\u0010\u0014\u001a@\u0012<\u0012:\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000f0\u0015j\u0002`\u001a0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006}"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "Lcom/pudutech/mirsdk/aidl/MoveActionInterface;", "moveActionInterface", "deviceControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "robotMovingTargetChangeListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$OnRobotMoveTargetChangeListener;", "(Lcom/pudutech/mirsdk/aidl/MoveActionInterface;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;)V", "TAG", "", "checkDeviceJob", "Lkotlinx/coroutines/Job;", "delayMoveTask", "Lkotlin/Function0;", "", "delayResumeMoveTask", "fakeState", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "lastStateContent", "moveStateListeners", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "p0", "p1", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "getMoveStateListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "moveStateListeners$delegate", "Lkotlin/Lazy;", "addAccessDoorStateListener", "kotlin.jvm.PlatformType", "Lcom/pudutech/mirsdk/aidl/AccessDoorListener;", "addCliffDistanceStateListener", "Lcom/pudutech/mirsdk/aidl/CliffDistanceStateListener;", "addCoverAround", "", "addElevetorRequestListener", "Lcom/pudutech/mirsdk/aidl/ElevatorRequestListener;", "addFillInStateListener", "Lcom/pudutech/mirsdk/aidl/FillInStateListener;", "addMapAreaDetectionListener", "Lcom/pudutech/mirsdk/aidl/MapAreaDetectionListener;", "addOnStateChangeListeners", "l", "asBinder", "Landroid/os/IBinder;", "checkDeviceReady", "clearDropEvent", "destinationsOrderWithRange", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "", "enableDynamicRoadblock", "enableStuckReplan", "fakeMoving", "fakePause", "fakeResume", "getCallAccessDoorDistance", "", "getCallElevatorDistance", "getChargingPileInfos", "Lcom/pudutech/mirsdk/aidl/serialize/ChargingPileInfo;", "getChargingPiles", "getCoverAround", "getDestinationRange", TypedValues.Attributes.S_TARGET, "getDestinations", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "getHeavyloadMode", "getRoadBlockTime", "", "getSmoothRunAndStopMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "getSpecifyMapDestinations", "getSpeedLevel", "Lcom/pudutech/mirsdk/mircore/coreparcel/MoveMode;", "getSpeedLevels", "", "()[Ljava/lang/String;", "getTrayDis", "goChargingPile", "point", "goCruisePath", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "p2", "goHome", "goHomeFill", "goTarget", "pointType", "Lcom/pudutech/mirsdkwrap/lib/enums/PointType;", "isFillIn", "goTo", "isNearByChangePile", "nearestDestination", "onStateChange", "state", "description", "onStateChange$module_robot_mirsdk_wrapper_release", "openDeviceIfNeed", "passAccessDoor", "pause", "quitFillIn", "removeAccessDoorStateListener", "removeCliffDistanceStateListener", "removeElevatorRequestListener", "removeFillInStateListener", "removeMapAreaDetectionListener", "removeOnStateChangeListeners", "resetStatus", "resume", "rotate", "setCallAccessDoorDistance", "setCallElevatorDistance", "setHeavyloadMode", "setReplanWaitTime", "setRoadBlockTime", "setSmoothRunAndStopMode", "startCheckDeviceJob", "startGo", "stopFakeStatus", "swithSpeedLevel", "taskStop", "updateTrayDis", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotMoveInterfaceDecorator implements MoveActionInterface {
    private final String TAG;
    private Job checkDeviceJob;
    private Function0<Unit> delayMoveTask;
    private Function0<Unit> delayResumeMoveTask;
    private final DevicesControlHelper deviceControlHelper;
    private RobotState fakeState;
    private String lastStateContent;
    private final MoveActionInterface moveActionInterface;

    /* renamed from: moveStateListeners$delegate, reason: from kotlin metadata */
    private final Lazy moveStateListeners;
    private final ListenerList<RobotMoveManager.OnRobotMoveTargetChangeListener> robotMovingTargetChangeListeners;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PointType.values().length];

        static {
            $EnumSwitchMapping$0[PointType.HOME.ordinal()] = 1;
            $EnumSwitchMapping$0[PointType.OTHER.ordinal()] = 2;
            $EnumSwitchMapping$0[PointType.CHARGE.ordinal()] = 3;
        }
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addAccessDoorStateListener(String p0, AccessDoorListener p1) {
        this.moveActionInterface.addAccessDoorStateListener(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addCliffDistanceStateListener(String p0, CliffDistanceStateListener p1) {
        this.moveActionInterface.addCliffDistanceStateListener(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean addCoverAround(boolean p0) {
        return this.moveActionInterface.addCoverAround(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addElevetorRequestListener(String p0, ElevatorRequestListener p1) {
        this.moveActionInterface.addElevetorRequestListener(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addFillInStateListener(String p0, FillInStateListener p1) {
        this.moveActionInterface.addFillInStateListener(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addMapAreaDetectionListener(String p0, MapAreaDetectionListener p1) {
        this.moveActionInterface.addMapAreaDetectionListener(p0, p1);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.moveActionInterface.asBinder();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void clearDropEvent() {
        this.moveActionInterface.clearDropEvent();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<DestinationWithAccRange> destinationsOrderWithRange(List<String> p0, boolean p1) {
        return this.moveActionInterface.destinationsOrderWithRange(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void enableDynamicRoadblock(boolean p0) {
        this.moveActionInterface.enableDynamicRoadblock(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void enableStuckReplan(boolean p0) {
        this.moveActionInterface.enableStuckReplan(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public double getCallAccessDoorDistance() {
        return this.moveActionInterface.getCallAccessDoorDistance();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public double getCallElevatorDistance() {
        return this.moveActionInterface.getCallElevatorDistance();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<ChargingPileInfo> getChargingPileInfos() {
        return this.moveActionInterface.getChargingPileInfos();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<String> getChargingPiles() {
        return this.moveActionInterface.getChargingPiles();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean getCoverAround() {
        return this.moveActionInterface.getCoverAround();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<Destination> getDestinations() {
        return this.moveActionInterface.getDestinations();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean getHeavyloadMode() {
        return this.moveActionInterface.getHeavyloadMode();
    }

    public final ListenerList<Function2<RobotState, String, Unit>> getMoveStateListeners() {
        return (ListenerList) this.moveStateListeners.getValue();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public int getRoadBlockTime() {
        return this.moveActionInterface.getRoadBlockTime();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public SmoothMode getSmoothRunAndStopMode() {
        return this.moveActionInterface.getSmoothRunAndStopMode();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<Destination> getSpecifyMapDestinations(String p0) {
        return this.moveActionInterface.getSpecifyMapDestinations(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public String getSpeedLevel(MoveMode p0) {
        return this.moveActionInterface.getSpeedLevel(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public String[] getSpeedLevels() {
        return this.moveActionInterface.getSpeedLevels();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public double getTrayDis() {
        return this.moveActionInterface.getTrayDis();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean isNearByChangePile() {
        return this.moveActionInterface.isNearByChangePile();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public DestinationWithAccRange nearestDestination(List<String> p0) {
        return this.moveActionInterface.nearestDestination(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void passAccessDoor() {
        this.moveActionInterface.passAccessDoor();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void quitFillIn() {
        this.moveActionInterface.quitFillIn();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeAccessDoorStateListener(String p0) {
        this.moveActionInterface.removeAccessDoorStateListener(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeCliffDistanceStateListener(String p0) {
        this.moveActionInterface.removeCliffDistanceStateListener(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeElevatorRequestListener(String p0) {
        this.moveActionInterface.removeElevatorRequestListener(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeFillInStateListener(String p0) {
        this.moveActionInterface.removeFillInStateListener(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeMapAreaDetectionListener(String p0) {
        this.moveActionInterface.removeMapAreaDetectionListener(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void rotate(double p0) {
        this.moveActionInterface.rotate(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void setCallAccessDoorDistance(double p0) {
        this.moveActionInterface.setCallAccessDoorDistance(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void setCallElevatorDistance(double p0) {
        this.moveActionInterface.setCallElevatorDistance(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void setHeavyloadMode(boolean p0) {
        this.moveActionInterface.setHeavyloadMode(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean setReplanWaitTime(int p0) {
        return this.moveActionInterface.setReplanWaitTime(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean setRoadBlockTime(int p0) {
        return this.moveActionInterface.setRoadBlockTime(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void setSmoothRunAndStopMode(SmoothMode p0) {
        this.moveActionInterface.setSmoothRunAndStopMode(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean swithSpeedLevel(MoveMode p0, String p1) {
        return this.moveActionInterface.swithSpeedLevel(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void taskStop() {
        this.moveActionInterface.taskStop();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void updateTrayDis(double p0) {
        this.moveActionInterface.updateTrayDis(p0);
    }

    public RobotMoveInterfaceDecorator(MoveActionInterface moveActionInterface, DevicesControlHelper deviceControlHelper, ListenerList<RobotMoveManager.OnRobotMoveTargetChangeListener> robotMovingTargetChangeListeners) {
        Intrinsics.checkParameterIsNotNull(moveActionInterface, "moveActionInterface");
        Intrinsics.checkParameterIsNotNull(deviceControlHelper, "deviceControlHelper");
        Intrinsics.checkParameterIsNotNull(robotMovingTargetChangeListeners, "robotMovingTargetChangeListeners");
        this.moveActionInterface = moveActionInterface;
        this.deviceControlHelper = deviceControlHelper;
        this.robotMovingTargetChangeListeners = robotMovingTargetChangeListeners;
        this.TAG = "RobotMoveInterfaceDecorator";
        this.moveStateListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super RobotState, ? super String, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$moveStateListeners$2
            @Override // kotlin.jvm.functions.Function0
            public final ListenerList<Function2<? super RobotState, ? super String, ? extends Unit>> invoke() {
                return new ListenerList<>();
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean goTo(final String p0, MoveTaskMode p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Pdlog.m3273d(this.TAG, "goTo : p0 = " + p0 + "; p1 = " + p1 + "; ");
        this.robotMovingTargetChangeListeners.forEach(Dispatchers.getMain(), new Function1<RobotMoveManager.OnRobotMoveTargetChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$goTo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotMoveManager.OnRobotMoveTargetChangeListener onRobotMoveTargetChangeListener) {
                invoke2(onRobotMoveTargetChangeListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RobotMoveManager.OnRobotMoveTargetChangeListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onMovingTarget(p0);
            }
        });
        return goTarget$default(this, p0, p1, PointType.OTHER, false, 8, null);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean goHome(final String p0, MoveTaskMode p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Pdlog.m3273d(this.TAG, "goHome : p0 = " + p0 + "; p1 = " + p1 + "; ");
        this.robotMovingTargetChangeListeners.forEach(Dispatchers.getMain(), new Function1<RobotMoveManager.OnRobotMoveTargetChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$goHome$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotMoveManager.OnRobotMoveTargetChangeListener onRobotMoveTargetChangeListener) {
                invoke2(onRobotMoveTargetChangeListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RobotMoveManager.OnRobotMoveTargetChangeListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onMovingTarget(p0);
            }
        });
        return goTarget$default(this, p0, p1, PointType.HOME, false, 8, null);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean goHomeFill(String p0, MoveTaskMode p1, boolean p2) {
        boolean z = true;
        Pdlog.m3273d(this.TAG, "goHomeFill : p0 = " + p0 + "; p1 = " + p1 + "; isFillIn = " + p2 + ' ');
        String str = p0;
        if (str != null && !StringsKt.isBlank(str)) {
            z = false;
        }
        if (z) {
            return false;
        }
        return goTarget(p0, p1, PointType.HOME, p2);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void goChargingPile(final String point) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        Pdlog.m3273d(this.TAG, "goHome : p0 = " + point + ' ');
        Pdlog.m3273d(this.TAG, "goChargingPile : p0 = " + point + ' ');
        this.robotMovingTargetChangeListeners.forEach(Dispatchers.getMain(), new Function1<RobotMoveManager.OnRobotMoveTargetChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$goChargingPile$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotMoveManager.OnRobotMoveTargetChangeListener onRobotMoveTargetChangeListener) {
                invoke2(onRobotMoveTargetChangeListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RobotMoveManager.OnRobotMoveTargetChangeListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onMovingTarget(point);
            }
        });
        goTarget$default(this, point, MoveTaskMode.Normal, PointType.CHARGE, false, 8, null);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void goCruisePath(final int p0, final MoveTaskMode p1, final List<String> p2) {
        resetStatus();
        if (checkDeviceReady()) {
            Pdlog.m3275i(this.TAG, "goCruisePath : p0:" + p0 + " p1:" + p1 + " p2:" + p2 + ' ');
            Pdlog.m3273d(this.TAG, "all devices ready. just go");
            this.moveActionInterface.goCruisePath(p0, p1, p2);
            return;
        }
        Pdlog.m3273d(this.TAG, "goCruisePath : some devices not ready");
        this.delayMoveTask = new Function0<Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$goCruisePath$1
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
                RobotState robotState;
                RobotState robotState2;
                MoveActionInterface moveActionInterface;
                robotState = RobotMoveInterfaceDecorator.this.fakeState;
                if (robotState == RobotState.Moving) {
                    moveActionInterface = RobotMoveInterfaceDecorator.this.moveActionInterface;
                    moveActionInterface.goCruisePath(p0, p1, p2);
                }
                robotState2 = RobotMoveInterfaceDecorator.this.fakeState;
                if (robotState2 == RobotState.Pause) {
                    RobotMoveInterfaceDecorator.this.delayResumeMoveTask = new Function0<Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$goCruisePath$1.1
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
                            String str;
                            MoveActionInterface moveActionInterface2;
                            str = RobotMoveInterfaceDecorator.this.TAG;
                            Pdlog.m3273d(str, "goCruisePath :resume after start");
                            moveActionInterface2 = RobotMoveInterfaceDecorator.this.moveActionInterface;
                            moveActionInterface2.goCruisePath(p0, p1, p2);
                        }
                    };
                }
                RobotMoveInterfaceDecorator.this.stopFakeStatus();
            }
        };
        startCheckDeviceJob();
        fakeMoving();
    }

    private final void resetStatus() {
        this.lastStateContent = (String) null;
        stopFakeStatus();
        this.delayResumeMoveTask = (Function0) null;
    }

    static /* synthetic */ boolean goTarget$default(RobotMoveInterfaceDecorator robotMoveInterfaceDecorator, String str, MoveTaskMode moveTaskMode, PointType pointType, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return robotMoveInterfaceDecorator.goTarget(str, moveTaskMode, pointType, z);
    }

    private final boolean goTarget(final String target, final MoveTaskMode p1, final PointType pointType, final boolean isFillIn) {
        resetStatus();
        if (checkDeviceReady()) {
            Pdlog.m3273d(this.TAG, "goTarget device is ready");
            startGo(target, p1, pointType, isFillIn);
            return true;
        }
        this.delayMoveTask = new Function0<Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$goTarget$1
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
                String str;
                RobotState robotState;
                RobotState robotState2;
                RobotState robotState3;
                str = RobotMoveInterfaceDecorator.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("goTarget delay move task start ");
                robotState = RobotMoveInterfaceDecorator.this.fakeState;
                sb.append(robotState);
                Pdlog.m3273d(str, sb.toString());
                robotState2 = RobotMoveInterfaceDecorator.this.fakeState;
                if (robotState2 == RobotState.Moving) {
                    RobotMoveInterfaceDecorator.this.startGo(target, p1, pointType, isFillIn);
                } else {
                    robotState3 = RobotMoveInterfaceDecorator.this.fakeState;
                    if (robotState3 == RobotState.Pause) {
                        RobotMoveInterfaceDecorator.this.delayResumeMoveTask = new Function0<Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$goTarget$1.1
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
                                RobotMoveInterfaceDecorator.this.startGo(target, p1, pointType, isFillIn);
                            }
                        };
                    }
                }
                RobotMoveInterfaceDecorator.this.stopFakeStatus();
            }
        };
        startCheckDeviceJob();
        fakeMoving();
        return true;
    }

    private final void startCheckDeviceJob() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMoveInterfaceDecorator$startCheckDeviceJob$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopFakeStatus() {
        this.fakeState = (RobotState) null;
        Job job = this.checkDeviceJob;
        Pdlog.m3273d(this.TAG, "stopFakeStatus : " + job);
        if (job != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, BaseMove.INSTANCE.getMoveTask$module_robot_mirsdk_wrapper_release(), null, new RobotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1(job, null, this), 2, null);
        }
        this.checkDeviceJob = (Job) null;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void resume() {
        Pdlog.m3273d(this.TAG, "resume state:" + this.fakeState);
        if (this.fakeState == RobotState.Moving) {
            return;
        }
        if (this.fakeState == RobotState.Pause) {
            fakeResume();
            return;
        }
        if (this.delayResumeMoveTask != null) {
            Pdlog.m3273d(this.TAG, "resume has delayResumeMoveTask");
            Function0<Unit> function0 = this.delayResumeMoveTask;
            if (function0 == null) {
                Intrinsics.throwNpe();
            }
            function0.invoke();
            this.delayResumeMoveTask = (Function0) null;
            return;
        }
        Pdlog.m3273d(this.TAG, "resume no delayResumeMoveTask");
        this.moveActionInterface.resume();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void pause() {
        Pdlog.m3273d(this.TAG, "pause state:" + this.fakeState);
        if (this.fakeState == RobotState.Pause) {
            return;
        }
        if (this.fakeState == RobotState.Moving) {
            fakePause();
        } else {
            this.moveActionInterface.pause();
        }
    }

    private final void fakeResume() {
        Pdlog.m3273d(this.TAG, "fakeResume");
        this.fakeState = RobotState.Moving;
        getMoveStateListeners().forEach(new Function1<Function2<? super RobotState, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$fakeResume$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super RobotState, ? super String, ? extends Unit> function2) {
                invoke2((Function2<? super RobotState, ? super String, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super RobotState, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(RobotState.Resume, "fake");
            }
        });
        getMoveStateListeners().forEach(new Function1<Function2<? super RobotState, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$fakeResume$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super RobotState, ? super String, ? extends Unit> function2) {
                invoke2((Function2<? super RobotState, ? super String, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super RobotState, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(RobotState.Moving, "fake");
            }
        });
    }

    private final void fakeMoving() {
        Pdlog.m3273d(this.TAG, "fakeMoving");
        this.fakeState = RobotState.Moving;
        getMoveStateListeners().forEach(new Function1<Function2<? super RobotState, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$fakeMoving$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super RobotState, ? super String, ? extends Unit> function2) {
                invoke2((Function2<? super RobotState, ? super String, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super RobotState, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(RobotState.Moving, "fake");
            }
        });
    }

    private final void fakePause() {
        Pdlog.m3273d(this.TAG, "fakePause ");
        this.fakeState = RobotState.Pause;
        getMoveStateListeners().forEach(new Function1<Function2<? super RobotState, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$fakePause$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super RobotState, ? super String, ? extends Unit> function2) {
                invoke2((Function2<? super RobotState, ? super String, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super RobotState, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(RobotState.Pause, "fake");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startGo(String target, MoveTaskMode p1, PointType pointType, boolean isFillIn) {
        int i = WhenMappings.$EnumSwitchMapping$0[pointType.ordinal()];
        if (i == 1) {
            this.moveActionInterface.goHomeFill(target, p1, isFillIn);
            Pdlog.m3273d(this.TAG, "startGo  goHomeFill  target = " + target + "  isFillIn = " + isFillIn);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            this.moveActionInterface.goChargingPile(target);
            return;
        }
        boolean goTo = this.moveActionInterface.goTo(target, p1);
        Pdlog.m3273d(this.TAG, "startGo  goTo  target = " + target + "  Result = " + goTo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkDeviceReady() {
        return this.deviceControlHelper.getLidarDeviceControl().getCurrentDeviceSwitch() && this.deviceControlHelper.getRgbdDeviceControl().getCurrentDeviceSwitch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openDeviceIfNeed() {
        if (!this.deviceControlHelper.getLidarDeviceControl().getSetSwitch()) {
            this.deviceControlHelper.getLidarDeviceControl().open();
        }
        if (this.deviceControlHelper.getRgbdDeviceControl().getSetSwitch()) {
            return;
        }
        this.deviceControlHelper.getRgbdDeviceControl().open();
    }

    public final void onStateChange$module_robot_mirsdk_wrapper_release(final RobotState state, final String description) {
        Pdlog.m3273d(this.TAG, "onStateChange : state = " + state + "; description = " + description + "; ");
        getMoveStateListeners().forEach(new Function1<Function2<? super RobotState, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator$onStateChange$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super RobotState, ? super String, ? extends Unit> function2) {
                invoke2((Function2<? super RobotState, ? super String, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super RobotState, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(RobotState.this, description);
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(state);
        sb.append('_');
        sb.append(description);
        String sb2 = sb.toString();
        if (Intrinsics.areEqual(sb2, this.lastStateContent)) {
            return;
        }
        this.lastStateContent = sb2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addOnStateChangeListeners(Function2<? super RobotState, ? super String, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getMoveStateListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void removeOnStateChangeListeners(Function2<? super RobotState, ? super String, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getMoveStateListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final double getDestinationRange(String target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        return this.moveActionInterface.destinationsOrderWithRange(CollectionsKt.listOf(target), false).get(0).getRange();
    }
}
