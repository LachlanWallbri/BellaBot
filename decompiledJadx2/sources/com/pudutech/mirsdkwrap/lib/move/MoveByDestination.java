package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.move.bean.CurrentDestinationTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveDestinationTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveMode;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B{\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012B\u0010\r\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u0016\u0018\u00010\u000e¢\u0006\u0002\u0010\u0017J\u0006\u0010$\u001a\u00020\u0015J\u0006\u0010%\u001a\u00020\u0015J\u0006\u0010&\u001a\u00020\u0015J\u0011\u0010'\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\u001a\u0010)\u001a\u00020\u00152\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020+J\u0011\u0010-\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001f0!J\u0016\u0010/\u001a\u0012\u0012\u0004\u0012\u00020#0\bj\b\u0012\u0004\u0012\u00020#`\nJ\b\u00100\u001a\u00020\u0015H\u0002J\u001b\u00101\u001a\u00020\u00152\b\u00102\u001a\u0004\u0018\u00010\u0019H\u0094@ø\u0001\u0000¢\u0006\u0002\u00103J\u0011\u00104\u001a\u00020\u0015H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0006\u00105\u001a\u00020\u0015J\u0006\u00106\u001a\u00020\u0015J\b\u00107\u001a\u00020\u0015H\u0002R\u001a\u0010\u0018\u001a\u00020\u0019X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020#0\bj\b\u0012\u0004\u0012\u00020#`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000RJ\u0010\r\u001a>\u00128\u00126\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u0016\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveByDestination;", "Lcom/pudutech/mirsdkwrap/lib/move/BaseMove;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByDestinationStateListener;", "moveInterfaceDecorator", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "destinationTasks", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveDestinationTask;", "Lkotlin/collections/ArrayList;", "moveSortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "speedListener", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSpeedListener;", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;Ljava/util/ArrayList;Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "currentTask", "Lcom/pudutech/mirsdkwrap/lib/move/bean/CurrentDestinationTask;", "historyTask", "Ljava/util/concurrent/CopyOnWriteArrayList;", "moveReport", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveReportData;", "active", "cancelAll", "cancelOne", "doNextTask", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finishOne", "needRemove", "", "doNext", "genMoveReport", "getHistoryTask", "getMoveReport", "makeNextTask", "onArrive", "s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPause", "pause", "resume", "updateActive", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveByDestination extends BaseMove<MoveByDestinationStateListener> {
    private String TAG;
    private CurrentDestinationTask currentTask;
    private final ArrayList<MoveDestinationTask> destinationTasks;
    private final DevicesControlHelper devicesControlHelper;
    private CopyOnWriteArrayList<CurrentDestinationTask> historyTask;
    private final RobotMoveInterfaceDecorator moveInterfaceDecorator;
    private ArrayList<MoveReportData> moveReport;
    private final MoveSortType moveSortType;
    private final ListenerList<Function2<Double, Double, Unit>> speedListener;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RobotState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[RobotState.Idle.ordinal()] = 1;
            $EnumSwitchMapping$0[RobotState.Arrive.ordinal()] = 2;
            $EnumSwitchMapping$0[RobotState.Moving.ordinal()] = 3;
            $EnumSwitchMapping$0[RobotState.Approaching.ordinal()] = 4;
            $EnumSwitchMapping$0[RobotState.Stuck.ordinal()] = 5;
            $EnumSwitchMapping$0[RobotState.Resume.ordinal()] = 6;
            $EnumSwitchMapping$0[RobotState.Avoid.ordinal()] = 7;
            $EnumSwitchMapping$0[RobotState.Pause.ordinal()] = 8;
            $EnumSwitchMapping$0[RobotState.Error.ordinal()] = 9;
            $EnumSwitchMapping$1 = new int[MoveSortType.values().length];
            $EnumSwitchMapping$1[MoveSortType.AUTO.ordinal()] = 1;
            $EnumSwitchMapping$1[MoveSortType.FIXED.ordinal()] = 2;
        }
    }

    public /* synthetic */ MoveByDestination(RobotMoveInterfaceDecorator robotMoveInterfaceDecorator, DevicesControlHelper devicesControlHelper, ArrayList arrayList, MoveSortType moveSortType, ListenerList listenerList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(robotMoveInterfaceDecorator, devicesControlHelper, arrayList, (i & 8) != 0 ? MoveSortType.AUTO : moveSortType, listenerList);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByDestination(RobotMoveInterfaceDecorator moveInterfaceDecorator, DevicesControlHelper devicesControlHelper, ArrayList<MoveDestinationTask> destinationTasks, MoveSortType moveSortType, ListenerList<Function2<Double, Double, Unit>> listenerList) {
        super(moveInterfaceDecorator, devicesControlHelper, listenerList);
        Intrinsics.checkParameterIsNotNull(moveInterfaceDecorator, "moveInterfaceDecorator");
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        Intrinsics.checkParameterIsNotNull(destinationTasks, "destinationTasks");
        Intrinsics.checkParameterIsNotNull(moveSortType, "moveSortType");
        this.moveInterfaceDecorator = moveInterfaceDecorator;
        this.devicesControlHelper = devicesControlHelper;
        this.destinationTasks = destinationTasks;
        this.moveSortType = moveSortType;
        this.speedListener = listenerList;
        this.TAG = "MoveByDestination";
        this.moveReport = new ArrayList<>();
        this.historyTask = new CopyOnWriteArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    protected void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    public final void active() {
        Pdlog.m3273d(getTAG(), "active isDestroy:" + getIsDestroy());
        if (getIsDestroy()) {
            return;
        }
        updateActive();
    }

    public final void pause() {
        Pdlog.m3273d(getTAG(), "pause isDestroy:" + getIsDestroy());
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByDestination$pause$1(this, null));
    }

    public final void resume() {
        Pdlog.m3273d(getTAG(), "resume isDestroy:" + getIsDestroy());
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByDestination$resume$1(this, null));
    }

    public final void cancelOne() {
        Pdlog.m3273d(getTAG(), "cancelOne isDestroy:" + getIsDestroy());
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByDestination$cancelOne$1(this, null));
    }

    public static /* synthetic */ void finishOne$default(MoveByDestination moveByDestination, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        moveByDestination.finishOne(z, z2);
    }

    public final void finishOne(boolean needRemove, boolean doNext) {
        Pdlog.m3273d(getTAG(), "finishOne ");
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByDestination$finishOne$1(this, needRemove, doNext, null));
    }

    public final void cancelAll() {
        if (getIsDestroy()) {
            return;
        }
        runAsyn(new MoveByDestination$cancelAll$1(this, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:12:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object onArrive(String str, Continuation<? super Unit> continuation) {
        MoveByDestination$onArrive$1 moveByDestination$onArrive$1;
        int i;
        MoveByDestination moveByDestination;
        if (continuation instanceof MoveByDestination$onArrive$1) {
            moveByDestination$onArrive$1 = (MoveByDestination$onArrive$1) continuation;
            if ((moveByDestination$onArrive$1.label & Integer.MIN_VALUE) != 0) {
                moveByDestination$onArrive$1.label -= Integer.MIN_VALUE;
                Object obj = moveByDestination$onArrive$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveByDestination$onArrive$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    moveByDestination$onArrive$1.L$0 = this;
                    moveByDestination$onArrive$1.L$1 = str;
                    moveByDestination$onArrive$1.label = 1;
                    if (genMoveReport(moveByDestination$onArrive$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    moveByDestination = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    moveByDestination = (MoveByDestination) moveByDestination$onArrive$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Pdlog.m3273d(moveByDestination.getTAG(), "onArrive " + moveByDestination.currentTask);
                if (moveByDestination.currentTask != null) {
                    return Unit.INSTANCE;
                }
                moveByDestination.runOnUi(new MoveByDestination$onArrive$2(moveByDestination, null));
                return Unit.INSTANCE;
            }
        }
        moveByDestination$onArrive$1 = new MoveByDestination$onArrive$1(this, continuation);
        Object obj2 = moveByDestination$onArrive$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveByDestination$onArrive$1.label;
        if (i != 0) {
        }
        Pdlog.m3273d(moveByDestination.getTAG(), "onArrive " + moveByDestination.currentTask);
        if (moveByDestination.currentTask != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.move.BaseMove
    public Object onPause(Continuation<? super Unit> continuation) {
        Pdlog.m3273d(getTAG(), "onPause " + this.currentTask);
        if (this.currentTask == null) {
            return Unit.INSTANCE;
        }
        runOnUi(new MoveByDestination$onPause$2(this, null));
        return Unit.INSTANCE;
    }

    private final void updateActive() {
        Pdlog.m3273d(getTAG(), "updateActive " + getCurrentMoveState());
        runAsyn(new MoveByDestination$updateActive$1(this, null));
    }

    public final ArrayList<MoveReportData> getMoveReport() {
        return this.moveReport;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object genMoveReport(Continuation<? super Unit> continuation) {
        MoveByDestination$genMoveReport$1 moveByDestination$genMoveReport$1;
        int i;
        if (continuation instanceof MoveByDestination$genMoveReport$1) {
            moveByDestination$genMoveReport$1 = (MoveByDestination$genMoveReport$1) continuation;
            if ((moveByDestination$genMoveReport$1.label & Integer.MIN_VALUE) != 0) {
                moveByDestination$genMoveReport$1.label -= Integer.MIN_VALUE;
                Object obj = moveByDestination$genMoveReport$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveByDestination$genMoveReport$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = endRecodeReport();
                    if (((MoveReportData) objectRef.element) == null) {
                        Pdlog.m3274e(getTAG(), "genMoveReport : report is null");
                    } else {
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        MoveByDestination$genMoveReport$2 moveByDestination$genMoveReport$2 = new MoveByDestination$genMoveReport$2(this, objectRef, null);
                        moveByDestination$genMoveReport$1.L$0 = this;
                        moveByDestination$genMoveReport$1.L$1 = objectRef;
                        moveByDestination$genMoveReport$1.label = 1;
                        if (BuildersKt.withContext(main, moveByDestination$genMoveReport$2, moveByDestination$genMoveReport$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        moveByDestination$genMoveReport$1 = new MoveByDestination$genMoveReport$1(this, continuation);
        Object obj2 = moveByDestination$genMoveReport$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveByDestination$genMoveReport$1.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object doNextTask(Continuation<? super Unit> continuation) {
        MoveByDestination$doNextTask$1 moveByDestination$doNextTask$1;
        int i;
        MoveByDestination moveByDestination;
        Destination copy;
        CurrentDestinationTask currentDestinationTask;
        MoveTaskMode moveTaskMode;
        if (continuation instanceof MoveByDestination$doNextTask$1) {
            moveByDestination$doNextTask$1 = (MoveByDestination$doNextTask$1) continuation;
            if ((moveByDestination$doNextTask$1.label & Integer.MIN_VALUE) != 0) {
                moveByDestination$doNextTask$1.label -= Integer.MIN_VALUE;
                Object obj = moveByDestination$doNextTask$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveByDestination$doNextTask$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    CurrentDestinationTask currentDestinationTask2 = this.currentTask;
                    if (currentDestinationTask2 != null) {
                        if (currentDestinationTask2 == null) {
                            Intrinsics.throwNpe();
                        }
                        copy = r7.copy((r18 & 1) != 0 ? r7.name : null, (r18 & 2) != 0 ? r7.group : null, (r18 & 4) != 0 ? r7.floor : null, (r18 & 8) != 0 ? r7.type : null, (r18 & 16) != 0 ? r7.x : 0.0d, (r18 & 32) != 0 ? currentDestinationTask2.getTask().getDestination().y : 0.0d);
                        moveByDestination$doNextTask$1.L$0 = this;
                        moveByDestination$doNextTask$1.L$1 = copy;
                        moveByDestination$doNextTask$1.label = 1;
                        if (genMoveReport(moveByDestination$doNextTask$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        moveByDestination = this;
                    } else {
                        moveByDestination = this;
                        moveByDestination.makeNextTask();
                        currentDestinationTask = moveByDestination.currentTask;
                        if (currentDestinationTask == null) {
                            Pdlog.m3273d(moveByDestination.getTAG(), "updateActive not has task");
                            moveByDestination.runOnUi(new MoveByDestination$doNextTask$3(moveByDestination, null));
                        } else {
                            if (currentDestinationTask == null) {
                                Intrinsics.throwNpe();
                            }
                            if (currentDestinationTask.getTask().getMoveMode() == MoveMode.Steady) {
                                moveTaskMode = MoveTaskMode.Steady;
                            } else {
                                moveTaskMode = MoveTaskMode.Normal;
                            }
                            moveByDestination.runOnUi(new MoveByDestination$doNextTask$4(moveByDestination, null));
                            CurrentDestinationTask currentDestinationTask3 = moveByDestination.currentTask;
                            if (currentDestinationTask3 == null) {
                                Intrinsics.throwNpe();
                            }
                            moveByDestination.startRecodeReport(currentDestinationTask3.getDwr().getId());
                            String tag = moveByDestination.getTAG();
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("goto id  ");
                            CurrentDestinationTask currentDestinationTask4 = moveByDestination.currentTask;
                            if (currentDestinationTask4 == null) {
                                Intrinsics.throwNpe();
                            }
                            sb.append(currentDestinationTask4.getDwr().getId());
                            objArr[0] = sb.toString();
                            Pdlog.m3273d(tag, objArr);
                            RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = moveByDestination.moveInterfaceDecorator;
                            CurrentDestinationTask currentDestinationTask5 = moveByDestination.currentTask;
                            if (currentDestinationTask5 == null) {
                                Intrinsics.throwNpe();
                            }
                            robotMoveInterfaceDecorator.goTo(currentDestinationTask5.getDwr().getId(), moveTaskMode);
                        }
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Destination destination = (Destination) moveByDestination$doNextTask$1.L$1;
                    moveByDestination = (MoveByDestination) moveByDestination$doNextTask$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    copy = destination;
                }
                moveByDestination.runOnUi(new MoveByDestination$doNextTask$2(moveByDestination, copy, null));
                moveByDestination.makeNextTask();
                currentDestinationTask = moveByDestination.currentTask;
                if (currentDestinationTask == null) {
                }
                return Unit.INSTANCE;
            }
        }
        moveByDestination$doNextTask$1 = new MoveByDestination$doNextTask$1(this, continuation);
        Object obj2 = moveByDestination$doNextTask$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveByDestination$doNextTask$1.label;
        if (i != 0) {
        }
        moveByDestination.runOnUi(new MoveByDestination$doNextTask$2(moveByDestination, copy, null));
        moveByDestination.makeNextTask();
        currentDestinationTask = moveByDestination.currentTask;
        if (currentDestinationTask == null) {
        }
        return Unit.INSTANCE;
    }

    private final void makeNextTask() {
        DestinationWithAccRange nearestDestination;
        MoveDestinationTask moveDestinationTask;
        CurrentDestinationTask currentDestinationTask = null;
        if (this.destinationTasks.isEmpty()) {
            this.currentTask = (CurrentDestinationTask) null;
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[this.moveSortType.ordinal()];
        if (i == 1) {
            HashMap hashMap = new HashMap();
            for (MoveDestinationTask moveDestinationTask2 : this.destinationTasks) {
                hashMap.put(moveDestinationTask2.getDestination().getName(), moveDestinationTask2);
            }
            RobotMoveInterfaceDecorator robotMoveInterfaceDecorator = this.moveInterfaceDecorator;
            Set keySet = hashMap.keySet();
            Intrinsics.checkExpressionValueIsNotNull(keySet, "map.keys");
            nearestDestination = robotMoveInterfaceDecorator.nearestDestination(CollectionsKt.toList(keySet));
            moveDestinationTask = (MoveDestinationTask) hashMap.get(nearestDestination != null ? nearestDestination.getId() : null);
        } else if (i == 2) {
            moveDestinationTask = this.destinationTasks.get(0);
            RobotMoveInterfaceDecorator robotMoveInterfaceDecorator2 = this.moveInterfaceDecorator;
            String[] strArr = new String[1];
            if (moveDestinationTask == null) {
                Intrinsics.throwNpe();
            }
            strArr[0] = moveDestinationTask.getDestination().getName();
            List<DestinationWithAccRange> destinationsOrderWithRange = robotMoveInterfaceDecorator2.destinationsOrderWithRange(CollectionsKt.arrayListOf(strArr), false);
            List<DestinationWithAccRange> list = destinationsOrderWithRange;
            nearestDestination = !(list == null || list.isEmpty()) ? destinationsOrderWithRange.get(0) : null;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (nearestDestination != null && moveDestinationTask != null) {
            Iterator<MoveDestinationTask> it = this.destinationTasks.iterator();
            Intrinsics.checkExpressionValueIsNotNull(it, "destinationTasks.iterator()");
            while (it.hasNext()) {
                MoveDestinationTask next = it.next();
                Intrinsics.checkExpressionValueIsNotNull(next, "iterator.next()");
                if (Intrinsics.areEqual(next.getDestination().getName(), moveDestinationTask.getDestination().getName())) {
                    it.remove();
                }
            }
            currentDestinationTask = new CurrentDestinationTask(nearestDestination, moveDestinationTask);
        } else {
            Pdlog.m3273d(getTAG(), "makeNextTask not has task");
        }
        this.currentTask = currentDestinationTask;
        CurrentDestinationTask currentDestinationTask2 = this.currentTask;
        if (currentDestinationTask2 != null) {
            this.historyTask.add(currentDestinationTask2);
        }
    }

    public final CopyOnWriteArrayList<CurrentDestinationTask> getHistoryTask() {
        return this.historyTask;
    }
}
