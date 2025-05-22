package com.pudutech.mirsdkwrap.lib.move;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener;
import com.pudutech.mirsdkwrap.lib.interf.MotorDirectionListener;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: BaseSolicitMove.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¯\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0018\b&\u0018\u0000 h*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002hiB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010J\u001a\u000205H\u0002J\u0011\u0010K\u001a\u000205H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LJ\r\u0010M\u001a\u000205H\u0010¢\u0006\u0002\bNJ\n\u0010O\u001a\u0004\u0018\u00010.H\u0004J\u001c\u0010P\u001a\u00020$2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001a\u001a\u0004\u0018\u00010\nH\u0002J\u001b\u0010Q\u001a\u0002052\b\u0010R\u001a\u0004\u0018\u00010\nH¤@ø\u0001\u0000¢\u0006\u0002\u0010SJ\b\u0010T\u001a\u000205H\u0002J\u0010\u0010U\u001a\u0002052\u0006\u0010V\u001a\u00020,H\u0002J\u0010\u0010W\u001a\u0002052\u0006\u0010X\u001a\u00020YH\u0014J\u0011\u0010Z\u001a\u000205H¤@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0014\u0010[\u001a\u0004\u0018\u00010\\2\b\u0010R\u001a\u0004\u0018\u00010\nH\u0002J\n\u0010]\u001a\u0004\u0018\u00010.H\u0004J9\u0010^\u001a\u0002052'\u0010_\u001a#\b\u0001\u0012\u0004\u0012\u00020`\u0012\n\u0012\b\u0012\u0004\u0012\u0002050a\u0012\u0006\u0012\u0004\u0018\u00010\u000300¢\u0006\u0002\bbH\u0004ø\u0001\u0000¢\u0006\u0002\u0010cJ9\u0010d\u001a\u0002052'\u0010_\u001a#\b\u0001\u0012\u0004\u0012\u00020`\u0012\n\u0012\b\u0012\u0004\u0012\u0002050a\u0012\u0006\u0012\u0004\u0018\u00010\u000300¢\u0006\u0002\bbH\u0004ø\u0001\u0000¢\u0006\u0002\u0010cJ\u0010\u0010e\u001a\u0002052\u0006\u0010f\u001a\u00020\nH\u0004J\u001c\u0010g\u001a\u0002052\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001a\u001a\u0004\u0018\u00010\nH\u0002R\u001c\u0010\t\u001a\u00020\nX\u0094\u000e¢\u0006\u0010\n\u0002\b\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@DX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\u001e8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010%\u001a\u00020$2\u0006\u0010\u0010\u001a\u00020$@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000RF\u0010/\u001a:\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020500j\u0002`6X\u0082\u0004¢\u0006\u0002\n\u0000R(\u00109\u001a\u0004\u0018\u0001082\b\u00107\u001a\u0004\u0018\u000108@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001e\u0010>\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010C\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u000e\u0010D\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020IX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006j"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/BaseSolicitMove;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "TAG$1", "<set-?>", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "currentMoveState", "getCurrentMoveState", "()Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "setCurrentMoveState", "(Lcom/pudutech/mirsdk/aidl/serialize/RobotState;)V", "elevatorRequestListener", "com/pudutech/mirsdkwrap/lib/move/BaseSolicitMove$elevatorRequestListener$1", "Lcom/pudutech/mirsdkwrap/lib/move/BaseSolicitMove$elevatorRequestListener$1;", "elevatorUtilizeParam", "elevatorUtilizeState", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorUtilizeState;", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "gson$delegate", "Lkotlin/Lazy;", "isCancel", "", "isDestroy", "()Z", "setDestroy", "(Z)V", "motorDirectionHelper", "Lcom/pudutech/mirsdkwrap/lib/move/MotorDirectionHelper;", "moveErrorHelper", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "moveReportData", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveReportData;", "moveStateListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirectionListener;", "onMotorDirectionListener", "getOnMotorDirectionListener", "()Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirectionListener;", "setOnMotorDirectionListener", "(Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirectionListener;)V", "onMoveStateListener", "getOnMoveStateListener", "()Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "setOnMoveStateListener", "(Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;)V", "Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "stuckPosition", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$Pose2D;", "stuckStateDelayedPostHelper", "Lcom/pudutech/mirsdkwrap/lib/move/StuckStateDelayedPostHelper;", "stuckTimeOutEntity", "Lcom/pudutech/mirsdkwrap/lib/move/BaseSolicitMove$StuckTimeOutEntity;", "activeStatus", "cancelStatus", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "destroy", "destroy$module_robot_mirsdk_wrapper_release", "endRecodeReport", "isElevatorSame", "onArrive", "s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAvoid", "onError", C3898x.f4338g, "onMovingEvent", "event", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotMoveEvent;", "onPause", "parserElevatorJson", "Lcom/pudutech/mirsdkwrap/lib/move/bean/ElevatorEventParam;", "recodeReport", "runAsyn", "block", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)V", "runOnUi", "startRecodeReport", "goalId", "updateElevatorData", "Companion", "StuckTimeOutEntity", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BaseSolicitMove<T extends BaseRobotMoveStateListener> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "BaseSolicitMove";
    public static final double coordinateRangeBetweenXY = 1.0d;
    private static final ExecutorCoroutineDispatcher moveTask;
    public static final long stuckTimeOutInternal = 60000;
    private static volatile long stuckTimeStamp;

    /* renamed from: TAG$1, reason: from kotlin metadata */
    private String TAG;
    private RobotState currentMoveState;
    private final DevicesControlHelper devicesControlHelper;
    private final BaseSolicitMove$elevatorRequestListener$1 elevatorRequestListener;
    private String elevatorUtilizeParam;
    private ElevatorUtilizeState elevatorUtilizeState;

    /* renamed from: gson$delegate, reason: from kotlin metadata */
    private final Lazy gson;
    private boolean isCancel;
    private volatile boolean isDestroy;
    private final MotorDirectionHelper motorDirectionHelper;
    private final MoveErrorHelper moveErrorHelper;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;
    private MoveReportData moveReportData;
    private final Function2<RobotState, String, Unit> moveStateListener;
    private MotorDirectionListener onMotorDirectionListener;
    private T onMoveStateListener;
    private volatile RobotMoveManager.Pose2D stuckPosition;
    private final StuckStateDelayedPostHelper stuckStateDelayedPostHelper;
    private volatile StuckTimeOutEntity stuckTimeOutEntity;

    protected Object cancelStatus(Continuation<? super Unit> continuation) {
        return cancelStatus$suspendImpl(this, continuation);
    }

    protected final Gson getGson() {
        return (Gson) this.gson.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object onArrive(String str, Continuation<? super Unit> continuation);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object onPause(Continuation<? super Unit> continuation);

    public BaseSolicitMove(RobotMoveInterfaceDecorator moveInterfaceDecorator, DevicesControlHelper devicesControlHelper) {
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.devicesControlHelper = devicesControlHelper;
        this.currentMoveState = RobotState.Idle;
        this.TAG = TAG;
        this.gson = LazyKt.lazy(new Function0<Gson>() { // from class: com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove$gson$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Gson invoke() {
                return new Gson();
            }
        });
        this.moveErrorHelper = new MoveErrorHelper(this.devicesControlHelper);
        this.motorDirectionHelper = new MotorDirectionHelper();
        this.stuckPosition = new RobotMoveManager.Pose2D(0.0d, 0.0d, 0.0d);
        this.stuckTimeOutEntity = new StuckTimeOutEntity(0L, new RobotMoveManager.Pose2D(0.0d, 0.0d, 0.0d));
        this.stuckStateDelayedPostHelper = new StuckStateDelayedPostHelper();
        this.elevatorRequestListener = new BaseSolicitMove$elevatorRequestListener$1(this);
        this.moveStateListener = new Function2<RobotState, String, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove$moveStateListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
                invoke2(robotState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RobotState robotState, String str) {
                StuckStateDelayedPostHelper stuckStateDelayedPostHelper;
                Pdlog.m3273d(BaseSolicitMove.this.getTAG(), "RobotStateListener : RobotState = " + robotState + "; msg = " + str + "; ");
                stuckStateDelayedPostHelper = BaseSolicitMove.this.stuckStateDelayedPostHelper;
                if (robotState == null) {
                    Intrinsics.throwNpe();
                }
                if (stuckStateDelayedPostHelper.needDelayedPost(robotState)) {
                    Pdlog.m3273d(BaseSolicitMove.this.getTAG(), " stuckStateDelayedPost ");
                } else {
                    BaseSolicitMove.this.runAsyn(new C53351(robotState, str, null));
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: BaseSolicitMove.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove$moveStateListener$1$1", m3970f = "BaseSolicitMove.kt", m3971i = {0, 1}, m3972l = {150, 160}, m3973m = "invokeSuspend", m3974n = {"$this$runAsyn", "$this$runAsyn"}, m3975s = {"L$0", "L$0"})
            /* renamed from: com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove$moveStateListener$1$1 */
            /* loaded from: classes4.dex */
            public static final class C53351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ RobotState $p0;
                final /* synthetic */ String $p1;
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6539p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C53351(RobotState robotState, String str, Continuation continuation) {
                    super(2, continuation);
                    this.$p0 = robotState;
                    this.$p1 = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53351 c53351 = new C53351(this.$p0, this.$p1, completion);
                    c53351.f6539p$ = (CoroutineScope) obj;
                    return c53351;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                /* JADX WARN: Failed to find 'out' block for switch in B:33:0x007c. Please report as an issue. */
                /* JADX WARN: Removed duplicated region for block: B:24:0x017f  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    BaseSolicitMove.StuckTimeOutEntity stuckTimeOutEntity;
                    BaseSolicitMove.StuckTimeOutEntity stuckTimeOutEntity2;
                    BaseSolicitMove.StuckTimeOutEntity stuckTimeOutEntity3;
                    BaseSolicitMove.StuckTimeOutEntity stuckTimeOutEntity4;
                    BaseSolicitMove.StuckTimeOutEntity stuckTimeOutEntity5;
                    BaseSolicitMove.StuckTimeOutEntity stuckTimeOutEntity6;
                    BaseSolicitMove.StuckTimeOutEntity stuckTimeOutEntity7;
                    boolean z;
                    MoveReportData moveReportData;
                    MoveErrorHelper moveErrorHelper;
                    MoveReportData moveReportData2;
                    MoveErrorHelper moveErrorHelper2;
                    MoveReportData moveReportData3;
                    MotorDirectionHelper motorDirectionHelper;
                    MoveErrorHelper moveErrorHelper3;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f6539p$;
                        if (BaseSolicitMove.this.getIsDestroy()) {
                            Pdlog.m3274e(BaseSolicitMove.this.getTAG(), "is destroy ?????? " + this.$p0);
                            return Unit.INSTANCE;
                        }
                        RobotState robotState = this.$p0;
                        if (robotState != null) {
                            switch (robotState) {
                                case Moving:
                                    BaseSolicitMove.this.activeStatus();
                                    BaseSolicitMove.this.onMovingEvent(RobotMoveEvent.NORMAL);
                                    break;
                                case Stuck:
                                    if (BaseSolicitMove.this.getCurrentMoveState() != RobotState.Stuck) {
                                        stuckTimeOutEntity = BaseSolicitMove.this.stuckTimeOutEntity;
                                        if (!stuckTimeOutEntity.checkPosCoordinateInRange()) {
                                            stuckTimeOutEntity5 = BaseSolicitMove.this.stuckTimeOutEntity;
                                            stuckTimeOutEntity5.setPos(RobotMoveManager.INSTANCE.get2DPosition());
                                            stuckTimeOutEntity6 = BaseSolicitMove.this.stuckTimeOutEntity;
                                            stuckTimeOutEntity6.setTime(System.currentTimeMillis());
                                            BaseSolicitMove.this.onMovingEvent(RobotMoveEvent.STUCK);
                                            break;
                                        } else {
                                            stuckTimeOutEntity2 = BaseSolicitMove.this.stuckTimeOutEntity;
                                            if (stuckTimeOutEntity2.checkTime()) {
                                                BaseSolicitMove.this.onMovingEvent(RobotMoveEvent.STUCK_TIME_OUT);
                                                BaseSolicitMove.Companion companion = BaseSolicitMove.INSTANCE;
                                                stuckTimeOutEntity4 = BaseSolicitMove.this.stuckTimeOutEntity;
                                                companion.setStuckTimeStamp$module_robot_mirsdk_wrapper_release(stuckTimeOutEntity4.getTime());
                                                break;
                                            } else {
                                                BaseSolicitMove.this.onMovingEvent(RobotMoveEvent.STUCK);
                                                stuckTimeOutEntity3 = BaseSolicitMove.this.stuckTimeOutEntity;
                                                stuckTimeOutEntity3.setTime(System.currentTimeMillis());
                                                break;
                                            }
                                        }
                                    } else {
                                        return Unit.INSTANCE;
                                    }
                                case Approaching:
                                    BaseSolicitMove.this.onMovingEvent(RobotMoveEvent.APPROACHING);
                                    break;
                                case Arrive:
                                    BaseSolicitMove.this.activeStatus();
                                    BaseSolicitMove baseSolicitMove = BaseSolicitMove.this;
                                    String str = this.$p1;
                                    this.L$0 = coroutineScope;
                                    this.label = 1;
                                    if (baseSolicitMove.onArrive(str, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    moveReportData3 = BaseSolicitMove.this.moveReportData;
                                    if (moveReportData3 != null) {
                                    }
                                    break;
                                case Pause:
                                    BaseSolicitMove.this.updateElevatorData(null, null);
                                    stuckTimeOutEntity7 = BaseSolicitMove.this.stuckTimeOutEntity;
                                    stuckTimeOutEntity7.reset();
                                    z = BaseSolicitMove.this.isCancel;
                                    if (!z) {
                                        moveReportData = BaseSolicitMove.this.moveReportData;
                                        if (moveReportData != null) {
                                            moveReportData.startPause$module_robot_mirsdk_wrapper_release();
                                        }
                                        BaseSolicitMove baseSolicitMove2 = BaseSolicitMove.this;
                                        this.L$0 = coroutineScope;
                                        this.label = 2;
                                        if (baseSolicitMove2.onPause(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    break;
                                case Resume:
                                    BaseSolicitMove.this.activeStatus();
                                    BaseSolicitMove.this.onMovingEvent(RobotMoveEvent.NORMAL);
                                    break;
                                case Avoid:
                                    BaseSolicitMove.this.onAvoid();
                                    break;
                                case Error:
                                    Pdlog.m3274e(BaseSolicitMove.this.getTAG(), "Error : " + this.$p1);
                                    String str2 = this.$p1;
                                    if (!(str2 == null || str2.length() == 0)) {
                                        moveErrorHelper = BaseSolicitMove.this.moveErrorHelper;
                                        if (moveErrorHelper.setErrors$module_robot_mirsdk_wrapper_release(this.$p1)) {
                                            moveReportData2 = BaseSolicitMove.this.moveReportData;
                                            if (moveReportData2 != null) {
                                                moveReportData2.startPause$module_robot_mirsdk_wrapper_release();
                                            }
                                            BaseSolicitMove baseSolicitMove3 = BaseSolicitMove.this;
                                            moveErrorHelper2 = BaseSolicitMove.this.moveErrorHelper;
                                            baseSolicitMove3.onError(moveErrorHelper2);
                                            break;
                                        } else {
                                            Pdlog.m3274e(BaseSolicitMove.this.getTAG(), "move error set errors fail ????");
                                            break;
                                        }
                                    } else {
                                        Pdlog.m3274e(BaseSolicitMove.this.getTAG(), "Error : but string is empty");
                                        break;
                                    }
                                    break;
                            }
                        } else {
                            Pdlog.m3274e(BaseSolicitMove.this.getTAG(), "RobotStateListener :  robot state is null");
                        }
                    } else if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                        moveReportData3 = BaseSolicitMove.this.moveReportData;
                        if (moveReportData3 != null) {
                            moveReportData3.recodeArrive$module_robot_mirsdk_wrapper_release();
                        }
                    } else {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (BaseSolicitMove.this.getCurrentMoveState() == RobotState.Error && this.$p0 != RobotState.Error) {
                        moveErrorHelper3 = BaseSolicitMove.this.moveErrorHelper;
                        moveErrorHelper3.clearErrors();
                    }
                    RobotState robotState2 = this.$p0;
                    BaseSolicitMove.this.setCurrentMoveState(robotState2);
                    motorDirectionHelper = BaseSolicitMove.this.motorDirectionHelper;
                    motorDirectionHelper.setMoving(robotState2 == RobotState.Moving || robotState2 == RobotState.Stuck);
                    return Unit.INSTANCE;
                }
            }
        };
        this.moveInterfaceDecorator.getMoveStateListeners().addNotSame$module_robot_mirsdk_wrapper_release(this.moveStateListener);
        this.moveInterfaceDecorator.addElevetorRequestListener(getTAG(), this.elevatorRequestListener);
        this.stuckStateDelayedPostHelper.init(this.moveStateListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final RobotState getCurrentMoveState() {
        return this.currentMoveState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized void setCurrentMoveState(RobotState robotState) {
        Intrinsics.checkParameterIsNotNull(robotState, "<set-?>");
        this.currentMoveState = robotState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTAG() {
        return this.TAG;
    }

    protected void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    public final T getOnMoveStateListener() {
        return this.onMoveStateListener;
    }

    public final void setOnMoveStateListener(T t) {
        this.onMoveStateListener = t;
    }

    public final MotorDirectionListener getOnMotorDirectionListener() {
        return this.onMotorDirectionListener;
    }

    public final void setOnMotorDirectionListener(MotorDirectionListener motorDirectionListener) {
        this.onMotorDirectionListener = motorDirectionListener;
        this.motorDirectionHelper.setOnMotorDirectionListener(motorDirectionListener);
    }

    /* renamed from: isDestroy, reason: from getter */
    public final boolean getIsDestroy() {
        return this.isDestroy;
    }

    protected final void setDestroy(boolean z) {
        this.isDestroy = z;
    }

    /* compiled from: BaseSolicitMove.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/BaseSolicitMove$Companion;", "", "()V", "TAG", "", "coordinateRangeBetweenXY", "", "moveTask", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getMoveTask$module_robot_mirsdk_wrapper_release", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "stuckTimeOutInternal", "", "stuckTimeStamp", "getStuckTimeStamp$module_robot_mirsdk_wrapper_release", "()J", "setStuckTimeStamp$module_robot_mirsdk_wrapper_release", "(J)V", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ExecutorCoroutineDispatcher getMoveTask$module_robot_mirsdk_wrapper_release() {
            return BaseSolicitMove.moveTask;
        }

        public final long getStuckTimeStamp$module_robot_mirsdk_wrapper_release() {
            return BaseSolicitMove.stuckTimeStamp;
        }

        public final void setStuckTimeStamp$module_robot_mirsdk_wrapper_release(long j) {
            BaseSolicitMove.stuckTimeStamp = j;
        }
    }

    static {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove$Companion$moveTask$1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("MoveTask");
                return thread;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(newSingleThreadExecutor, "Executors.newSingleThrea…\"\n            }\n        }");
        moveTask = ExecutorsKt.from(newSingleThreadExecutor);
        stuckTimeStamp = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateElevatorData(ElevatorUtilizeState elevatorUtilizeState, String elevatorUtilizeParam) {
        this.elevatorUtilizeState = elevatorUtilizeState;
        this.elevatorUtilizeParam = elevatorUtilizeParam;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isElevatorSame(ElevatorUtilizeState elevatorUtilizeState, String elevatorUtilizeParam) {
        return this.elevatorUtilizeState == elevatorUtilizeState && Intrinsics.areEqual(this.elevatorUtilizeParam, elevatorUtilizeParam);
    }

    /* compiled from: BaseSolicitMove.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/BaseSolicitMove$StuckTimeOutEntity;", "", "time", "", "pos", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$Pose2D;", "(JLcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$Pose2D;)V", "getPos", "()Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$Pose2D;", "setPos", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveManager$Pose2D;)V", "getTime", "()J", "setTime", "(J)V", "checkPosCoordinateInRange", "", "checkTime", "component1", "component2", "copy", "equals", "other", "hashCode", "", "reset", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class StuckTimeOutEntity {
        private RobotMoveManager.Pose2D pos;
        private long time;

        public StuckTimeOutEntity() {
            this(0L, null, 3, null);
        }

        public static /* synthetic */ StuckTimeOutEntity copy$default(StuckTimeOutEntity stuckTimeOutEntity, long j, RobotMoveManager.Pose2D pose2D, int i, Object obj) {
            if ((i & 1) != 0) {
                j = stuckTimeOutEntity.time;
            }
            if ((i & 2) != 0) {
                pose2D = stuckTimeOutEntity.pos;
            }
            return stuckTimeOutEntity.copy(j, pose2D);
        }

        /* renamed from: component1, reason: from getter */
        public final long getTime() {
            return this.time;
        }

        /* renamed from: component2, reason: from getter */
        public final RobotMoveManager.Pose2D getPos() {
            return this.pos;
        }

        public final StuckTimeOutEntity copy(long time, RobotMoveManager.Pose2D pos) {
            Intrinsics.checkParameterIsNotNull(pos, "pos");
            return new StuckTimeOutEntity(time, pos);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StuckTimeOutEntity)) {
                return false;
            }
            StuckTimeOutEntity stuckTimeOutEntity = (StuckTimeOutEntity) other;
            return this.time == stuckTimeOutEntity.time && Intrinsics.areEqual(this.pos, stuckTimeOutEntity.pos);
        }

        public int hashCode() {
            long j = this.time;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            RobotMoveManager.Pose2D pose2D = this.pos;
            return i + (pose2D != null ? pose2D.hashCode() : 0);
        }

        public String toString() {
            return "StuckTimeOutEntity(time=" + this.time + ", pos=" + this.pos + ")";
        }

        public StuckTimeOutEntity(long j, RobotMoveManager.Pose2D pos) {
            Intrinsics.checkParameterIsNotNull(pos, "pos");
            this.time = j;
            this.pos = pos;
        }

        public /* synthetic */ StuckTimeOutEntity(long j, RobotMoveManager.Pose2D pose2D, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? System.currentTimeMillis() : j, (i & 2) != 0 ? RobotMoveManager.INSTANCE.get2DPosition() : pose2D);
        }

        public final long getTime() {
            return this.time;
        }

        public final void setTime(long j) {
            this.time = j;
        }

        public final RobotMoveManager.Pose2D getPos() {
            return this.pos;
        }

        public final void setPos(RobotMoveManager.Pose2D pose2D) {
            Intrinsics.checkParameterIsNotNull(pose2D, "<set-?>");
            this.pos = pose2D;
        }

        public final void reset() {
            this.pos.setX(0.0d);
            this.pos.setY(0.0d);
            this.pos.setYaw(0.0d);
            this.time = 0L;
            BaseSolicitMove.INSTANCE.setStuckTimeStamp$module_robot_mirsdk_wrapper_release(System.currentTimeMillis());
        }

        public final boolean checkTime() {
            Pdlog.m3273d(BaseSolicitMove.TAG, "current stuck time is " + this.time + " init StuckTimeStamp " + BaseSolicitMove.INSTANCE.getStuckTimeStamp$module_robot_mirsdk_wrapper_release());
            return this.time - BaseSolicitMove.INSTANCE.getStuckTimeStamp$module_robot_mirsdk_wrapper_release() > 60000;
        }

        public final boolean checkPosCoordinateInRange() {
            RobotMoveManager.Pose2D pose2D = RobotMoveManager.INSTANCE.get2DPosition();
            Pdlog.m3273d(BaseSolicitMove.TAG, "current position x " + this.pos.getX() + " y " + this.pos.getY() + "  _2DCoordinate x " + pose2D.getX() + " _2DCoordinate y " + pose2D.getY());
            return Math.sqrt(Math.pow(Math.abs(pose2D.getX() - this.pos.getX()), 2.0d) + Math.pow(Math.abs(pose2D.getY() - this.pos.getY()), 2.0d)) < 1.0d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ElevatorEventParam parserElevatorJson(String s) {
        try {
            return (ElevatorEventParam) getGson().fromJson(s, ElevatorEventParam.class);
        } catch (Exception e) {
            Pdlog.m3274e(getTAG(), "parserJson : " + Log.getStackTraceString(e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onMovingEvent(RobotMoveEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        runOnUi(new BaseSolicitMove$onMovingEvent$1(this, event, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAvoid() {
        runOnUi(new BaseSolicitMove$onAvoid$1(this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onError(MoveErrorHelper e) {
        runOnUi(new BaseSolicitMove$onError$1(this, e, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void runOnUi(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, block, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void runAsyn(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, moveTask, null, block, 2, null);
    }

    static /* synthetic */ Object cancelStatus$suspendImpl(BaseSolicitMove baseSolicitMove, Continuation continuation) {
        baseSolicitMove.isCancel = true;
        MoveReportData moveReportData = baseSolicitMove.moveReportData;
        if (moveReportData != null) {
            moveReportData.end$module_robot_mirsdk_wrapper_release(true);
        }
        baseSolicitMove.moveInterfaceDecorator.pause();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void activeStatus() {
        this.isCancel = false;
        MoveReportData moveReportData = this.moveReportData;
        if (moveReportData != null) {
            moveReportData.endPause$module_robot_mirsdk_wrapper_release();
        }
    }

    protected final void startRecodeReport(String goalId) {
        Intrinsics.checkParameterIsNotNull(goalId, "goalId");
        this.moveReportData = new MoveReportData(null, false, 0L, 0, 0L, 0.0d, 0.0d, 0L, 255, null);
        MoveReportData moveReportData = this.moveReportData;
        if (moveReportData == null) {
            Intrinsics.throwNpe();
        }
        moveReportData.start$module_robot_mirsdk_wrapper_release(goalId);
    }

    protected final MoveReportData endRecodeReport() {
        MoveReportData moveReportData = this.moveReportData;
        if (moveReportData != null) {
            MoveReportData.end$module_robot_mirsdk_wrapper_release$default(moveReportData, false, 1, null);
        }
        return this.moveReportData;
    }

    protected final MoveReportData recodeReport() {
        MoveReportData moveReportData = this.moveReportData;
        if (moveReportData != null) {
            moveReportData.recode$module_robot_mirsdk_wrapper_release();
        }
        return this.moveReportData;
    }

    public void destroy$module_robot_mirsdk_wrapper_release() {
        Pdlog.m3273d(getTAG(), "destroy " + this.isDestroy);
        if (this.isDestroy) {
            return;
        }
        this.isDestroy = true;
        this.onMoveStateListener = (T) null;
        this.moveInterfaceDecorator.getMoveStateListeners().remove$module_robot_mirsdk_wrapper_release(this.moveStateListener);
        this.moveInterfaceDecorator.removeElevatorRequestListener(getTAG());
        this.moveErrorHelper.destroy$module_robot_mirsdk_wrapper_release();
        setOnMotorDirectionListener((MotorDirectionListener) null);
        this.motorDirectionHelper.destroy();
        runOnUi(new BaseSolicitMove$destroy$1(this, null));
    }
}
