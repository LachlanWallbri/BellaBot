package com.pudutech.bumblebee.business.movementTask;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.movementCallback.DeliverCallback;
import com.pudutech.bumblebee.business.movementInterface.DeliverInterface;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.robotsdk.MoveStateListener;
import com.pudutech.bumblebee.business.utils.ActiveModel;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: DeliverTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001bH\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\"\u0010 \u001a\u0004\u0018\u00010\r2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0012j\b\u0012\u0004\u0012\u00020\u0005`\u0013H\u0002J\u001c\u0010!\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010%\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u000bH\u0016J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u0005H\u0002J\u0018\u0010+\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u0015H\u0016J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\tH\u0016J&\u00100\u001a\u00020\u001d2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u0005012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00102\u001a\u00020\u0017H\u0016J\b\u00103\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0012j\b\u0012\u0004\u0012\u00020\u0005`\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementTask/DeliverTask;", "Lcom/pudutech/bumblebee/business/movementInterface/DeliverInterface;", "Lcom/pudutech/bumblebee/business/robotsdk/MoveStateListener;", "()V", "TAG", "", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "countdown", "", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "destinationWithRange", "setDestinationWithRange", "(Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;)V", "destinations", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "isActive", "", "moveTaskMode", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "changeCurrentStatus", "", "s", "checkActive", "getNextDestination", "onAccessDoorChange", "state", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "destination", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "pause", "keepTime_ms", "remove", TmpConstant.PROPERTY_IDENTIFIER_SET, "setActive", "boolean", "setCallback", "deliverCallback", "setDestinations", "", "performance", "updateFSM", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverTask implements DeliverInterface, MoveStateListener {
    private DeliverCallback callback;
    private volatile long countdown;
    private DestinationWithAccRange destinationWithRange;
    private boolean isActive;
    private final String TAG = "DeliverTask";
    private ActiveModel activeModel = new ActiveModel();
    private ArrayList<String> destinations = new ArrayList<>();
    private SortType sortType = SortType.AUTO;
    private TaskStatus status = TaskStatus.AWAIT;
    private MoveTaskMode moveTaskMode = MoveTaskMode.Normal;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[RobotState.Arrive.ordinal()] = 1;
            $EnumSwitchMapping$0[RobotState.Approaching.ordinal()] = 2;
            $EnumSwitchMapping$0[RobotState.Moving.ordinal()] = 3;
            $EnumSwitchMapping$0[RobotState.Idle.ordinal()] = 4;
            $EnumSwitchMapping$0[RobotState.Pause.ordinal()] = 5;
            $EnumSwitchMapping$0[RobotState.Error.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[TaskStatus.values().length];
            $EnumSwitchMapping$1[TaskStatus.AWAIT.ordinal()] = 1;
            $EnumSwitchMapping$1[TaskStatus.ON_THE_WAY.ordinal()] = 2;
            $EnumSwitchMapping$1[TaskStatus.APPROACHING.ordinal()] = 3;
            $EnumSwitchMapping$1[TaskStatus.ARRIVAL.ordinal()] = 4;
            $EnumSwitchMapping$1[TaskStatus.DONE.ordinal()] = 5;
            $EnumSwitchMapping$1[TaskStatus.CANCEL.ordinal()] = 6;
            $EnumSwitchMapping$2 = new int[SortType.values().length];
            $EnumSwitchMapping$2[SortType.AUTO.ordinal()] = 1;
            $EnumSwitchMapping$2[SortType.FIXED.ordinal()] = 2;
        }
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public MoveActionInterface getMoveAction() {
        return DeliverInterface.DefaultImpls.getMoveAction(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void pauseTask() {
        DeliverInterface.DefaultImpls.pauseTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.TimeTaskContract
    public void resumeTask() {
        DeliverInterface.DefaultImpls.resumeTask(this);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean z, boolean z2) {
        DeliverInterface.DefaultImpls.setActive(this, z, z2);
    }

    private final void setDestinationWithRange(DestinationWithAccRange destinationWithAccRange) {
        this.destinationWithRange = destinationWithAccRange;
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("destination set=");
        DestinationWithAccRange destinationWithAccRange2 = this.destinationWithRange;
        sb.append(destinationWithAccRange2 != null ? destinationWithAccRange2.getId() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.DeliverInterface
    public void setCallback(DeliverCallback deliverCallback) {
        Intrinsics.checkParameterIsNotNull(deliverCallback, "deliverCallback");
        this.callback = deliverCallback;
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onStateChange(RobotState state, String description) {
        if (state != null) {
            this.isActive = this.activeModel.checkActive(state);
        }
        if (state != null) {
            switch (state) {
                case Arrive:
                    changeCurrentStatus(TaskStatus.ARRIVAL);
                    break;
                case Approaching:
                    if (this.status != TaskStatus.APPROACHING) {
                        changeCurrentStatus(TaskStatus.APPROACHING);
                        break;
                    }
                    break;
                case Moving:
                    DelayResumeActive.INSTANCE.cancelTask();
                    break;
                case Idle:
                    DelayResumeActive.INSTANCE.cancelTask();
                    break;
                case Pause:
                    if (this.status != TaskStatus.APPROACHING) {
                        if (this.countdown != 0) {
                            DelayResumeActive.INSTANCE.post$module_bumblebee_business_robotRelease(this, this.countdown);
                            this.countdown = 0L;
                            break;
                        }
                    } else {
                        DelayResumeActive.INSTANCE.cancelTask();
                        changeCurrentStatus(TaskStatus.ARRIVAL);
                        break;
                    }
                    break;
                case Error:
                    DelayResumeActive.INSTANCE.cancelTask();
                    break;
            }
        }
        DeliverCallback deliverCallback = this.callback;
        if (deliverCallback != null) {
            deliverCallback.onMovementChanged(state, description);
        }
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.MoveStateListener
    public void onAccessDoorChange(AccessDoorControlState state, String destination) {
        Pdlog.m3273d(this.TAG, "onAccessDoorChange state: " + state + ",destination:" + destination);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.DeliverInterface
    public void setDestinations(List<String> destinations, SortType sortType, MoveTaskMode performance) {
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Pdlog.m3273d(this.TAG, "setDestinations size=" + destinations.size() + "  sortType=" + sortType + "  performance=" + performance);
        this.destinations = new ArrayList<>(destinations);
        this.sortType = sortType;
        this.moveTaskMode = performance;
        for (String str : destinations) {
            DeliverCallback deliverCallback = this.callback;
            if (deliverCallback != null) {
                deliverCallback.onStatusChanged(str, TaskStatus.AWAIT, 0.0d);
            }
        }
        DestinationWithAccRange nextDestination = getNextDestination(this.destinations);
        if (nextDestination == null) {
            DeliverCallback deliverCallback2 = this.callback;
            if (deliverCallback2 != null) {
                deliverCallback2.onTaskSetup(false, "the nearest destination was null");
            }
        } else {
            setDestinationWithRange(nextDestination);
            DeliverCallback deliverCallback3 = this.callback;
            if (deliverCallback3 != null) {
                deliverCallback3.onTaskSetup(true, "");
            }
        }
        String str2 = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("nearest destination=");
        DestinationWithAccRange destinationWithAccRange = this.destinationWithRange;
        sb.append(destinationWithAccRange != null ? destinationWithAccRange.getId() : null);
        sb.append(" in listeners=");
        sb.append(destinations);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str2, objArr);
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.DeliverInterface
    public void set(String destination, TaskStatus status) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3275i(this.TAG, "set destination=" + destination + " status=" + status);
        if (status == TaskStatus.DONE || status == TaskStatus.CANCEL || status == TaskStatus.DONE_BEFORE_ARRIVAL) {
            DeliverCallback deliverCallback = this.callback;
            if (deliverCallback != null) {
                deliverCallback.onStatusChanged(destination, status, 0.0d);
            }
            remove(destination);
        }
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void setActive(boolean r5) {
        Pdlog.m3275i(this.TAG, "set active=" + r5);
        if (r5) {
            updateFSM();
        } else {
            getMoveAction().pause();
        }
        DelayResumeActive.INSTANCE.cancelTask();
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    /* renamed from: checkActive, reason: from getter */
    public boolean getIsActive() {
        return this.isActive;
    }

    @Override // com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface
    public void pause(long keepTime_ms) {
        Pdlog.m3275i(this.TAG, "pause " + keepTime_ms);
        this.countdown = keepTime_ms;
        setActive(false);
    }

    private final void updateFSM() {
        String id;
        String id2;
        String str;
        String id3;
        String str2 = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("FSM status=");
        sb.append(this.status);
        sb.append(" destination=");
        DestinationWithAccRange destinationWithAccRange = this.destinationWithRange;
        sb.append(destinationWithAccRange != null ? destinationWithAccRange.getId() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str2, objArr);
        String str3 = "";
        switch (this.status) {
            case AWAIT:
                changeCurrentStatus(TaskStatus.ON_THE_WAY);
                MoveActionInterface moveAction = getMoveAction();
                DestinationWithAccRange destinationWithAccRange2 = this.destinationWithRange;
                if (destinationWithAccRange2 != null && (id = destinationWithAccRange2.getId()) != null) {
                    str3 = id;
                }
                moveAction.goTo(str3, this.moveTaskMode);
                return;
            case ON_THE_WAY:
                MoveActionInterface moveAction2 = getMoveAction();
                DestinationWithAccRange destinationWithAccRange3 = this.destinationWithRange;
                if (destinationWithAccRange3 != null && (id2 = destinationWithAccRange3.getId()) != null) {
                    str3 = id2;
                }
                moveAction2.goTo(str3, this.moveTaskMode);
                return;
            case APPROACHING:
                getMoveAction().resume();
                return;
            case ARRIVAL:
                DestinationWithAccRange destinationWithAccRange4 = this.destinationWithRange;
                if (destinationWithAccRange4 == null || (str = destinationWithAccRange4.getId()) == null) {
                    str = "";
                }
                set(str, TaskStatus.DONE);
                if (this.destinations.size() > 0) {
                    changeCurrentStatus(TaskStatus.ON_THE_WAY);
                    MoveActionInterface moveAction3 = getMoveAction();
                    DestinationWithAccRange destinationWithAccRange5 = this.destinationWithRange;
                    if (destinationWithAccRange5 != null && (id3 = destinationWithAccRange5.getId()) != null) {
                        str3 = id3;
                    }
                    moveAction3.goTo(str3, this.moveTaskMode);
                    return;
                }
                return;
            case DONE:
            case CANCEL:
            default:
                return;
        }
    }

    private final void changeCurrentStatus(TaskStatus s) {
        String str;
        Pdlog.m3273d(this.TAG, "changeCurrentStatus " + s);
        this.status = s;
        DeliverCallback deliverCallback = this.callback;
        if (deliverCallback != null) {
            DestinationWithAccRange destinationWithAccRange = this.destinationWithRange;
            if (destinationWithAccRange == null || (str = destinationWithAccRange.getId()) == null) {
                str = "";
            }
            DestinationWithAccRange destinationWithAccRange2 = this.destinationWithRange;
            deliverCallback.onStatusChanged(str, s, destinationWithAccRange2 != null ? destinationWithAccRange2.getRange() : 0.0d);
        }
    }

    private final void remove(final String destination) {
        this.destinations.removeIf(new Predicate<String>() { // from class: com.pudutech.bumblebee.business.movementTask.DeliverTask$remove$1
            @Override // java.util.function.Predicate
            public final boolean test(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it, destination);
            }
        });
        Pdlog.m3273d(this.TAG, "remove destination=" + destination + "  left=" + this.destinations);
        if (this.destinations.size() == 0) {
            DeliverCallback deliverCallback = this.callback;
            if (deliverCallback != null) {
                deliverCallback.onDone();
                return;
            }
            return;
        }
        DestinationWithAccRange destinationWithAccRange = this.destinationWithRange;
        if (Intrinsics.areEqual(destinationWithAccRange != null ? destinationWithAccRange.getId() : null, destination)) {
            DestinationWithAccRange nextDestination = getNextDestination(this.destinations);
            if (nextDestination == null) {
                Intrinsics.throwNpe();
            }
            setDestinationWithRange(nextDestination);
            changeCurrentStatus(TaskStatus.AWAIT);
        }
    }

    private final DestinationWithAccRange getNextDestination(ArrayList<String> destinations) {
        int i = WhenMappings.$EnumSwitchMapping$2[this.sortType.ordinal()];
        if (i == 1) {
            return getMoveAction().nearestDestination(destinations);
        }
        if (i == 2) {
            if (destinations.size() <= 0) {
                return null;
            }
            List<DestinationWithAccRange> results = getMoveAction().destinationsOrderWithRange(CollectionsKt.arrayListOf(destinations.get(0)), false);
            Intrinsics.checkExpressionValueIsNotNull(results, "results");
            if (!results.isEmpty()) {
                return results.get(0);
            }
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }
}
