package com.pudutech.mirsdk.movetask;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.base.SDKRobotStateKt;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: CruiseTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002-.B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f¢\u0006\u0002\u0010\u000eJ\u0014\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#H\u0016J\u0018\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0010H\u0016J\b\u0010*\u001a\u00020\u001cH\u0016J\b\u0010+\u001a\u00020\u001cH\u0016J\b\u0010,\u001a\u00020\u001cH\u0016R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00060\u0018R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006/"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/CruiseTask;", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "businessType", "Lcom/pudutech/mirsdk/movetask/BusinessType;", "index", "", "targets", "", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/movetask/BusinessType;I[Lcom/pudutech/mirsdk/aidl/serialize/Destination;)V", "TAG", "", "getIndex", "()I", "setIndex", "(I)V", "mCurrentRobotState", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "moveState", "Lcom/pudutech/mirsdk/movetask/CruiseTask$MoveState;", "stateNoticeQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lkotlin/Function0;", "", "getTargets", "()[Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "setTargets", "([Lcom/pudutech/mirsdk/aidl/serialize/Destination;)V", "[Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "", "onStateChange", "state", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "pause", "resume", "startMoveAction", "GeneralState", "MoveState", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseTask extends MoveTask {
    private final String TAG;
    private int index;
    private RobotState mCurrentRobotState;
    private MoveState moveState;
    private final ArrayBlockingQueue<Function0<Unit>> stateNoticeQueue;
    private Destination[] targets;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

        static {
            $EnumSwitchMapping$0[SDKRobotState.Approaching.ordinal()] = 1;
            $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService, BusinessType businessType, int i, Destination[] targets) {
        super(action, coreService, businessType, null, null, 24, null);
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(businessType, "businessType");
        Intrinsics.checkParameterIsNotNull(targets, "targets");
        this.index = i;
        this.targets = targets;
        this.TAG = "CruiseTask";
        this.mCurrentRobotState = RobotState.Idle;
        this.stateNoticeQueue = new ArrayBlockingQueue<>(10);
    }

    public final int getIndex() {
        return this.index;
    }

    public final Destination[] getTargets() {
        return this.targets;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final void setTargets(Destination[] destinationArr) {
        Intrinsics.checkParameterIsNotNull(destinationArr, "<set-?>");
        this.targets = destinationArr;
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void startMoveAction() {
        this.moveState = new GeneralState();
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
        Pair<MoveState, RobotState> mo4465switch = moveState.mo4465switch(state);
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i == 1 || i == 2) {
            this.mCurrentRobotState = RobotState.Moving;
            MoveState moveState2 = this.moveState;
            if (moveState2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            moveState2.start();
        } else {
            this.mCurrentRobotState = mo4465switch.getSecond();
        }
        this.stateNoticeQueue.put(new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.CruiseTask$onStateChange$1
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
                MoveAction action = CruiseTask.this.getAction();
                robotState = CruiseTask.this.mCurrentRobotState;
                action.informStateChange(robotState, desc);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new CruiseTask$onStateChange$2(this, null), 2, null);
        return this.mCurrentRobotState;
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public Pair<NavigationMode, Boolean> checkNavigationMode() {
        return new Pair<>(NavigationMode.Normal, true);
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void pause() {
        if (this.mCurrentRobotState == RobotState.Pause) {
            return;
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.pause();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void resume() {
        if (this.mCurrentRobotState != RobotState.Pause) {
            return;
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.resume();
    }

    /* compiled from: CruiseTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J \u0010\b\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/CruiseTask$GeneralState;", "Lcom/pudutech/mirsdk/movetask/CruiseTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/CruiseTask;", "(Lcom/pudutech/mirsdk/movetask/CruiseTask;)V", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class GeneralState extends MoveState {
        public GeneralState() {
            super();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0019  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0018 A[RETURN] */
        @Override // com.pudutech.mirsdk.movetask.CruiseTask.MoveState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void start() {
            byte b;
            Destination[] targets = CruiseTask.this.getTargets();
            if (targets != null) {
                if ((targets.length == 0) == false) {
                    b = false;
                    if (b == true) {
                        if (CruiseTask.this.getIndex() >= CruiseTask.this.getTargets().length) {
                            CruiseTask cruiseTask = CruiseTask.this;
                            Destination destination = cruiseTask.getTargets()[0];
                            String name = destination != null ? destination.getName() : null;
                            Destination destination2 = CruiseTask.this.getTargets()[CruiseTask.this.getTargets().length - 1];
                            cruiseTask.setIndex(Intrinsics.areEqual(name, destination2 != null ? destination2.getName() : null) ? 1 : 0);
                        }
                        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        Destination[] targets2 = CruiseTask.this.getTargets();
                        CruiseTask cruiseTask2 = CruiseTask.this;
                        int index = cruiseTask2.getIndex();
                        cruiseTask2.setIndex(index + 1);
                        objectRef.element = targets2[index];
                        String str = CruiseTask.this.TAG;
                        Object[] objArr = new Object[1];
                        StringBuilder sb = new StringBuilder();
                        sb.append("GeneralState start target ");
                        Destination destination3 = (Destination) objectRef.element;
                        sb.append(destination3 != null ? destination3.getName() : null);
                        sb.append(" , index=");
                        sb.append(CruiseTask.this.getIndex());
                        sb.append(",steadyMode=");
                        sb.append(CruiseTask.this.getSteadyMode());
                        objArr[0] = sb.toString();
                        Pdlog.m3273d(str, objArr);
                        CruiseTask.this.getAction().moveToPosition(MoveTaskMode.Normal, new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.CruiseTask$GeneralState$start$1
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
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2() {
                                ScheduleInterface scheduler;
                                MirCoreInterface mirCoreInterface = CruiseTask.this.getCoreService().getInterface();
                                NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                                if (navigator != null) {
                                    Destination destination4 = (Destination) objectRef.element;
                                    if (destination4 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    navigator.prepareDeliverTask(destination4.getDoubleDirection());
                                }
                                MirCoreInterface mirCoreInterface2 = CruiseTask.this.getCoreService().getInterface();
                                if (mirCoreInterface2 == null || (scheduler = mirCoreInterface2.getScheduler()) == null) {
                                    return true;
                                }
                                Destination destination5 = (Destination) objectRef.element;
                                if (destination5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                scheduler.prepareDeliverTask(new Vector3d(destination5.getX(), ((Destination) objectRef.element).getY(), ((Destination) objectRef.element).getDirection()));
                                return true;
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            b = true;
            if (b == true) {
            }
        }

        @Override // com.pudutech.mirsdk.movetask.CruiseTask.MoveState
        public void pause() {
            CruiseTask.this.getAction().actionPause();
        }

        @Override // com.pudutech.mirsdk.movetask.CruiseTask.MoveState
        public void resume() {
            CruiseTask.this.getAction().actionResume();
        }

        @Override // com.pudutech.mirsdk.movetask.CruiseTask.MoveState
        /* renamed from: switch, reason: not valid java name */
        public Pair<MoveState, RobotState> mo4465switch(SDKRobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }

    /* compiled from: CruiseTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H\u0016J \u0010\u0007\u001a\u0012\u0012\b\u0012\u00060\u0000R\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/CruiseTask$MoveState;", "", "(Lcom/pudutech/mirsdk/movetask/CruiseTask;)V", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/movetask/CruiseTask;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public abstract class MoveState {
        public abstract void pause();

        public abstract void resume();

        /* renamed from: switch */
        public abstract Pair<MoveState, RobotState> mo4465switch(SDKRobotState robotState);

        public MoveState() {
        }

        public void start() {
            BuildersKt__BuildersKt.runBlocking$default(null, new CruiseTask$MoveState$start$1(this, null), 1, null);
        }
    }
}
