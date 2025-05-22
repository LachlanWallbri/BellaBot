package com.pudutech.mirsdk.movetask;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.elements.ElevatorWaiter;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CurrentFloorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#H\u0016J\b\u0010&\u001a\u0004\u0018\u00010'J\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\tH\u0016J\b\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020.H\u0016J\b\u00100\u001a\u00020.H\u0016J\b\u00101\u001a\u00020.H\u0002J\b\u00102\u001a\u00020.H\u0016J\b\u00103\u001a\u00020.H\u0016J\u0006\u00104\u001a\u00020.R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0001X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u00065"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/CurrentFloorTask;", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;)V", "TAG", "", "kotlin.jvm.PlatformType", "accessControlTask", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "getAccessControlTask", "()Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "setAccessControlTask", "(Lcom/pudutech/mirsdk/movetask/AccessControlTask;)V", "gatePassTask", "Lcom/pudutech/mirsdk/movetask/GatePassTask;", "getGatePassTask", "()Lcom/pudutech/mirsdk/movetask/GatePassTask;", "setGatePassTask", "(Lcom/pudutech/mirsdk/movetask/GatePassTask;)V", "generalTask", "Lcom/pudutech/mirsdk/movetask/GeneralTask;", "getGeneralTask", "()Lcom/pudutech/mirsdk/movetask/GeneralTask;", "setGeneralTask", "(Lcom/pudutech/mirsdk/movetask/GeneralTask;)V", "moveTask", "getMoveTask", "()Lcom/pudutech/mirsdk/movetask/MoveTask;", "setMoveTask", "(Lcom/pudutech/mirsdk/movetask/MoveTask;)V", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "", "findDestination", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "state", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "pause", "", "reset", "resume", "sendTaskAgain", "startMoveAction", "taskStop", "updateSchedulePlan", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CurrentFloorTask extends MoveTask {
    private final String TAG;
    public AccessControlTask accessControlTask;
    public GatePassTask gatePassTask;
    public GeneralTask generalTask;
    public MoveTask moveTask;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BusinessType.values().length];

        static {
            $EnumSwitchMapping$0[BusinessType.GoGroup.ordinal()] = 1;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CurrentFloorTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService) {
        super(action, coreService, null, null, null, false, 60, null);
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        this.TAG = getClass().getSimpleName();
    }

    public final GeneralTask getGeneralTask() {
        GeneralTask generalTask = this.generalTask;
        if (generalTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generalTask");
        }
        return generalTask;
    }

    public final void setGeneralTask(GeneralTask generalTask) {
        Intrinsics.checkParameterIsNotNull(generalTask, "<set-?>");
        this.generalTask = generalTask;
    }

    public final GatePassTask getGatePassTask() {
        GatePassTask gatePassTask = this.gatePassTask;
        if (gatePassTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gatePassTask");
        }
        return gatePassTask;
    }

    public final void setGatePassTask(GatePassTask gatePassTask) {
        Intrinsics.checkParameterIsNotNull(gatePassTask, "<set-?>");
        this.gatePassTask = gatePassTask;
    }

    public final AccessControlTask getAccessControlTask() {
        AccessControlTask accessControlTask = this.accessControlTask;
        if (accessControlTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accessControlTask");
        }
        return accessControlTask;
    }

    public final void setAccessControlTask(AccessControlTask accessControlTask) {
        Intrinsics.checkParameterIsNotNull(accessControlTask, "<set-?>");
        this.accessControlTask = accessControlTask;
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
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        RobotState onStateChange = moveTask.onStateChange(state, desc);
        Pdlog.m3275i(this.TAG, "on State change " + state);
        MoveTask moveTask2 = this.moveTask;
        if (moveTask2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        if ((moveTask2 instanceof GatePassTask) && onStateChange == RobotState.Arrive) {
            MoveTask moveTask3 = this.moveTask;
            if (moveTask3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveTask");
            }
            if (moveTask3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.movetask.GatePassTask");
            }
            if (!((GatePassTask) moveTask3).getRealArrive()) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new CurrentFloorTask$onStateChange$1(this, null), 2, null);
            }
        } else {
            MoveTask moveTask4 = this.moveTask;
            if (moveTask4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveTask");
            }
            if ((moveTask4 instanceof GeneralTask) && onStateChange == RobotState.Arrive) {
                MoveTask moveTask5 = this.moveTask;
                if (moveTask5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveTask");
                }
                if (moveTask5 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.movetask.GeneralTask");
                }
                String gateID = ((GeneralTask) moveTask5).getGateID();
                if (gateID != null) {
                    if (!(gateID.length() == 0)) {
                        Pdlog.m3275i(this.TAG, "change to gate pass task");
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new CurrentFloorTask$onStateChange$2(this, null), 2, null);
                    }
                }
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
        if ((moveTask instanceof GeneralTask) || (moveTask instanceof GatePassTask)) {
            MoveTask moveTask2 = this.moveTask;
            if (moveTask2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveTask");
            }
            moveTask2.resume();
            return;
        }
        if (moveTask instanceof AccessControlTask) {
            sendTaskAgain();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendTaskAgain() {
        MirCoreInterface mirCoreInterface = getCoreService().getInterface();
        PathSegments pathSegments = null;
        if (mirCoreInterface != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("{\"floor\":\"");
            PathSegments pathSegments2 = getPathSegments();
            sb.append(pathSegments2 != null ? pathSegments2.getCurFloor() : null);
            sb.append("\", \"table\":\"");
            PathSegments pathSegments3 = getPathSegments();
            sb.append(pathSegments3 != null ? pathSegments3.getFinalGoal() : null);
            sb.append("\"}");
            pathSegments = mirCoreInterface.currentFloorPathSegment(sb.toString());
        }
        setPathSegments(pathSegments);
        startMoveAction();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void taskStop() {
        super.taskStop();
        MoveTask moveTask = this.moveTask;
        if (moveTask == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
        }
        moveTask.taskStop();
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

    public final void updateSchedulePlan() {
        ScheduleInterface scheduler;
        ScheduleInterface scheduler2;
        Pdlog.m3273d(this.TAG, "update schedule plan");
        PathSegments pathSegments = getPathSegments();
        List<PathSegment> segments = pathSegments != null ? pathSegments.getSegments() : null;
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        Vector3d pose = segments.get(0).getPose();
        if (WhenMappings.$EnumSwitchMapping$0[getBusinessType().ordinal()] == 1) {
            com.pudutech.mirsdk.mircore.coreparcel.BusinessType businessType = com.pudutech.mirsdk.mircore.coreparcel.BusinessType.GoHome;
            if (getIsFillIn()) {
                businessType = com.pudutech.mirsdk.mircore.coreparcel.BusinessType.WaitTake;
            }
            Pdlog.m3273d(this.TAG, " go home task status isFillIn: " + getIsFillIn() + "  , business_type : " + businessType.name());
            MirCoreInterface mirCoreInterface = getCoreService().getInterface();
            if (mirCoreInterface == null || (scheduler = mirCoreInterface.getScheduler()) == null) {
                return;
            }
            Destination findDestination = findDestination();
            if (findDestination == null) {
                Intrinsics.throwNpe();
            }
            scheduler.prepareGoHomeTask(findDestination.getGroup(), businessType);
            return;
        }
        MirCoreInterface mirCoreInterface2 = getCoreService().getInterface();
        if (mirCoreInterface2 == null || (scheduler2 = mirCoreInterface2.getScheduler()) == null) {
            return;
        }
        scheduler2.prepareDeliverTask(new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void startMoveAction() {
        List<PathSegment> segments;
        List<PathSegment> segments2;
        List<PathSegment> segments3;
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("path segments ");
        sb.append(getPathSegments());
        sb.append(" size is ");
        PathSegments pathSegments = getPathSegments();
        sb.append((pathSegments == null || (segments3 = pathSegments.getSegments()) == null) ? null : Integer.valueOf(segments3.size()));
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        updateSchedulePlan();
        if (getPathSegments() != null) {
            PathSegments pathSegments2 = getPathSegments();
            if (pathSegments2 == null || (segments2 = pathSegments2.getSegments()) == null || segments2.size() != 0) {
                MirCoreInterface mirCoreInterface = getCoreService().getInterface();
                ScheduleInterface scheduler = mirCoreInterface != null ? mirCoreInterface.getScheduler() : null;
                if (scheduler == null) {
                    Intrinsics.throwNpe();
                }
                DestinationWithAccRange findNextGateOnPath = scheduler.findNextGateOnPath();
                Pdlog.m3273d(this.TAG, "gate dist " + findNextGateOnPath.getRange());
                if (findNextGateOnPath.getRange() > 0) {
                    if (!(findNextGateOnPath.getId().length() == 0)) {
                        Pdlog.m3273d(this.TAG, "Start GatePass Action , Gate " + findNextGateOnPath.getId() + " will be pass， dist " + findNextGateOnPath.getRange());
                        GatePassTask gatePassTask = this.gatePassTask;
                        if (gatePassTask == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("gatePassTask");
                        }
                        gatePassTask.setGateMAC(findNextGateOnPath.getId());
                        GatePassTask gatePassTask2 = this.gatePassTask;
                        if (gatePassTask2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("gatePassTask");
                        }
                        gatePassTask2.setSteadyMode(getSteadyMode());
                        GatePassTask gatePassTask3 = this.gatePassTask;
                        if (gatePassTask3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("gatePassTask");
                        }
                        gatePassTask3.setPathSegments(getPathSegments());
                        GatePassTask gatePassTask4 = this.gatePassTask;
                        if (gatePassTask4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("gatePassTask");
                        }
                        gatePassTask4.setBusinessType(getBusinessType());
                        GatePassTask gatePassTask5 = this.gatePassTask;
                        if (gatePassTask5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("gatePassTask");
                        }
                        this.moveTask = gatePassTask5;
                        MoveTask moveTask = this.moveTask;
                        if (moveTask == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("moveTask");
                        }
                        moveTask.setFillIn(getIsFillIn());
                        GatePassTask gatePassTask6 = this.gatePassTask;
                        if (gatePassTask6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("gatePassTask");
                        }
                        gatePassTask6.startMoveAction();
                        return;
                    }
                }
                PathSegments pathSegments3 = getPathSegments();
                if (pathSegments3 != null && (segments = pathSegments3.getSegments()) != null && segments.size() == 1) {
                    Pdlog.m3273d(this.TAG, "to generalTask");
                    GeneralTask generalTask = this.generalTask;
                    if (generalTask == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("generalTask");
                    }
                    generalTask.setGateID("");
                    GeneralTask generalTask2 = this.generalTask;
                    if (generalTask2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("generalTask");
                    }
                    generalTask2.setSteadyMode(getSteadyMode());
                    GeneralTask generalTask3 = this.generalTask;
                    if (generalTask3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("generalTask");
                    }
                    generalTask3.setPathSegments(getPathSegments());
                    GeneralTask generalTask4 = this.generalTask;
                    if (generalTask4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("generalTask");
                    }
                    generalTask4.setBusinessType(getBusinessType());
                    GeneralTask generalTask5 = this.generalTask;
                    if (generalTask5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("generalTask");
                    }
                    this.moveTask = generalTask5;
                    MoveTask moveTask2 = this.moveTask;
                    if (moveTask2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moveTask");
                    }
                    moveTask2.setFillIn(getIsFillIn());
                    GeneralTask generalTask6 = this.generalTask;
                    if (generalTask6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("generalTask");
                    }
                    generalTask6.startMoveAction();
                    return;
                }
                Pdlog.m3273d(this.TAG, "to accessControlTask");
                AccessControlTask accessControlTask = this.accessControlTask;
                if (accessControlTask == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("accessControlTask");
                }
                accessControlTask.setSteadyMode(getSteadyMode());
                AccessControlTask accessControlTask2 = this.accessControlTask;
                if (accessControlTask2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("accessControlTask");
                }
                accessControlTask2.setPathSegments(getPathSegments());
                AccessControlTask accessControlTask3 = this.accessControlTask;
                if (accessControlTask3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("accessControlTask");
                }
                accessControlTask3.setBusinessType(getBusinessType());
                AccessControlTask accessControlTask4 = this.accessControlTask;
                if (accessControlTask4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("accessControlTask");
                }
                this.moveTask = accessControlTask4;
                MoveTask moveTask3 = this.moveTask;
                if (moveTask3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveTask");
                }
                moveTask3.setFillIn(getIsFillIn());
                MoveTask moveTask4 = this.moveTask;
                if (moveTask4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveTask");
                }
                moveTask4.startMoveAction();
            }
        }
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
