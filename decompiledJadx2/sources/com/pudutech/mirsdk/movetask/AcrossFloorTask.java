package com.pudutech.mirsdk.movetask;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AcrossFloorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001,B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001fH\u0016J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\tH\u0016J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020(H\u0016J\b\u0010*\u001a\u00020(H\u0016J\b\u0010+\u001a\u00020(H\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0001X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006-"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AcrossFloorTask;", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;)V", "TAG", "", "kotlin.jvm.PlatformType", "currentFloorTask", "Lcom/pudutech/mirsdk/movetask/CurrentFloorTask;", "getCurrentFloorTask", "()Lcom/pudutech/mirsdk/movetask/CurrentFloorTask;", "setCurrentFloorTask", "(Lcom/pudutech/mirsdk/movetask/CurrentFloorTask;)V", "currentNode", "Lcom/pudutech/mirsdk/movetask/AcrossFloorTask$AcrossSegmentNode;", "elevatorTask", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "getElevatorTask", "()Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "setElevatorTask", "(Lcom/pudutech/mirsdk/movetask/ElevatorTask;)V", "moveTask", "getMoveTask", "()Lcom/pudutech/mirsdk/movetask/MoveTask;", "setMoveTask", "(Lcom/pudutech/mirsdk/movetask/MoveTask;)V", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "state", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "pause", "", "reset", "resume", "startMoveAction", "AcrossSegmentNode", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AcrossFloorTask extends MoveTask {
    private final String TAG;
    public CurrentFloorTask currentFloorTask;
    private AcrossSegmentNode currentNode;
    public ElevatorTask elevatorTask;
    public MoveTask moveTask;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AcrossSegmentNode.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[AcrossSegmentNode.ElevatorWaiter.ordinal()] = 1;
            $EnumSwitchMapping$0[AcrossSegmentNode.OutElevator.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[RobotState.values().length];
            $EnumSwitchMapping$1[RobotState.Arrive.ordinal()] = 1;
            $EnumSwitchMapping$1[RobotState.Approaching.ordinal()] = 2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AcrossFloorTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService) {
        super(action, coreService, null, null, null, false, 60, null);
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        this.TAG = getClass().getSimpleName();
        this.currentNode = AcrossSegmentNode.Undefined;
    }

    public final CurrentFloorTask getCurrentFloorTask() {
        CurrentFloorTask currentFloorTask = this.currentFloorTask;
        if (currentFloorTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
        }
        return currentFloorTask;
    }

    public final void setCurrentFloorTask(CurrentFloorTask currentFloorTask) {
        Intrinsics.checkParameterIsNotNull(currentFloorTask, "<set-?>");
        this.currentFloorTask = currentFloorTask;
    }

    public final ElevatorTask getElevatorTask() {
        ElevatorTask elevatorTask = this.elevatorTask;
        if (elevatorTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("elevatorTask");
        }
        return elevatorTask;
    }

    public final void setElevatorTask(ElevatorTask elevatorTask) {
        Intrinsics.checkParameterIsNotNull(elevatorTask, "<set-?>");
        this.elevatorTask = elevatorTask;
    }

    public final MoveTask getMoveTask() {
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        return moveTask;
    }

    public final void setMoveTask(MoveTask moveTask) {
        Intrinsics.checkParameterIsNotNull(moveTask, "<set-?>");
        this.moveTask = moveTask;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AcrossFloorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/AcrossFloorTask$AcrossSegmentNode;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "Undefined", "ElevatorWaiter", "InElevator", "OutElevator", "Goal", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private enum AcrossSegmentNode {
        Undefined(-1),
        ElevatorWaiter(0),
        InElevator(1),
        OutElevator(2),
        Goal(3);

        private final int id;

        AcrossSegmentNode(int i) {
            this.id = i;
        }

        public final int getId() {
            return this.id;
        }
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public Pair<NavigationMode, Boolean> checkNavigationMode() {
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        return moveTask.checkNavigationMode();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public RobotState onStateChange(SDKRobotState state, String desc) {
        PathSegments pathSegments;
        PathSegments pathSegments2;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        RobotState onStateChange = moveTask.onStateChange(state, desc);
        int i = WhenMappings.$EnumSwitchMapping$1[onStateChange.ordinal()];
        if (i == 1 || i == 2) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[this.currentNode.ordinal()];
            if (i2 == 1) {
                onStateChange = RobotState.Moving;
                this.currentNode = AcrossSegmentNode.OutElevator;
                MirCoreInterface mirCoreInterface = getCoreService().getInterface();
                if (mirCoreInterface != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("{\"floor\":\"");
                    PathSegments pathSegments3 = getPathSegments();
                    sb.append(pathSegments3 != null ? pathSegments3.getDstFloor() : null);
                    sb.append("\", \"table\":\"");
                    PathSegments pathSegments4 = getPathSegments();
                    sb.append(pathSegments4 != null ? pathSegments4.getFinalGoal() : null);
                    sb.append("\"}");
                    pathSegments = mirCoreInterface.acrossFloorPathSegment(sb.toString());
                } else {
                    pathSegments = null;
                }
                if (pathSegments == null) {
                    Intrinsics.throwNpe();
                }
                ElevatorTask elevatorTask = this.elevatorTask;
                if (elevatorTask == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("elevatorTask");
                }
                elevatorTask.setSteadyMode(getSteadyMode());
                ElevatorTask elevatorTask2 = this.elevatorTask;
                if (elevatorTask2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("elevatorTask");
                }
                elevatorTask2.setPathSegments(pathSegments);
                ElevatorTask elevatorTask3 = this.elevatorTask;
                if (elevatorTask3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("elevatorTask");
                }
                elevatorTask3.setBusinessType(getBusinessType());
                ElevatorTask elevatorTask4 = this.elevatorTask;
                if (elevatorTask4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("elevatorTask");
                }
                this.moveTask = elevatorTask4;
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AcrossFloorTask$onStateChange$1(this, null), 3, null);
            } else if (i2 == 2) {
                onStateChange = RobotState.Moving;
                this.currentNode = AcrossSegmentNode.Goal;
                MirCoreInterface mirCoreInterface2 = getCoreService().getInterface();
                if (mirCoreInterface2 != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("{\"floor\":\"");
                    PathSegments pathSegments5 = getPathSegments();
                    sb2.append(pathSegments5 != null ? pathSegments5.getDstFloor() : null);
                    sb2.append("\", \"table\":\"");
                    PathSegments pathSegments6 = getPathSegments();
                    sb2.append(pathSegments6 != null ? pathSegments6.getFinalGoal() : null);
                    sb2.append("\"}");
                    pathSegments2 = mirCoreInterface2.currentFloorPathSegment(sb2.toString());
                } else {
                    pathSegments2 = null;
                }
                if (pathSegments2 == null) {
                    Intrinsics.throwNpe();
                }
                CurrentFloorTask currentFloorTask = this.currentFloorTask;
                if (currentFloorTask == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
                }
                currentFloorTask.setSteadyMode(getSteadyMode());
                CurrentFloorTask currentFloorTask2 = this.currentFloorTask;
                if (currentFloorTask2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
                }
                currentFloorTask2.setPathSegments(pathSegments2);
                CurrentFloorTask currentFloorTask3 = this.currentFloorTask;
                if (currentFloorTask3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
                }
                currentFloorTask3.setBusinessType(getBusinessType());
                CurrentFloorTask currentFloorTask4 = this.currentFloorTask;
                if (currentFloorTask4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
                }
                this.moveTask = currentFloorTask4;
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AcrossFloorTask$onStateChange$2(this, null), 3, null);
            }
        }
        return onStateChange;
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void pause() {
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        moveTask.pause();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void resume() {
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        moveTask.resume();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void startMoveAction() {
        PathSegments pathSegments;
        List<PathSegment> segments;
        if (getPathSegments() == null || (pathSegments = getPathSegments()) == null || (segments = pathSegments.getSegments()) == null || segments.size() != 4) {
            return;
        }
        this.currentNode = AcrossSegmentNode.ElevatorWaiter;
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("{\"floor\":\"");
        PathSegments pathSegments2 = getPathSegments();
        PathSegments pathSegments3 = null;
        sb.append(pathSegments2 != null ? pathSegments2.getCurFloor() : null);
        sb.append("\", \"table\":\"");
        PathSegments pathSegments4 = getPathSegments();
        List<PathSegment> segments2 = pathSegments4 != null ? pathSegments4.getSegments() : null;
        if (segments2 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(segments2.get(this.currentNode.getId()).getCurGoal());
        sb.append("\"}");
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        MirCoreInterface mirCoreInterface = getCoreService().getInterface();
        if (mirCoreInterface != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("{\"floor\":\"");
            PathSegments pathSegments5 = getPathSegments();
            sb2.append(pathSegments5 != null ? pathSegments5.getCurFloor() : null);
            sb2.append("\", \"table\":\"");
            PathSegments pathSegments6 = getPathSegments();
            List<PathSegment> segments3 = pathSegments6 != null ? pathSegments6.getSegments() : null;
            if (segments3 == null) {
                Intrinsics.throwNpe();
            }
            sb2.append(segments3.get(this.currentNode.getId()).getCurGoal());
            sb2.append("\"}");
            pathSegments3 = mirCoreInterface.currentFloorPathSegment(sb2.toString());
        }
        if (pathSegments3 == null) {
            Intrinsics.throwNpe();
        }
        CurrentFloorTask currentFloorTask = this.currentFloorTask;
        if (currentFloorTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
        }
        currentFloorTask.setSteadyMode(getSteadyMode());
        CurrentFloorTask currentFloorTask2 = this.currentFloorTask;
        if (currentFloorTask2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
        }
        currentFloorTask2.setPathSegments(pathSegments3);
        CurrentFloorTask currentFloorTask3 = this.currentFloorTask;
        if (currentFloorTask3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
        }
        currentFloorTask3.setBusinessType(BusinessType.Deliver);
        CurrentFloorTask currentFloorTask4 = this.currentFloorTask;
        if (currentFloorTask4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFloorTask");
        }
        this.moveTask = currentFloorTask4;
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        moveTask.startMoveAction();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void reset() {
        Pdlog.m3273d(this.TAG, "execute reset method");
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        moveTask.reset();
    }
}
