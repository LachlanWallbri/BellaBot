package com.pudutech.bumblebee.presenter.custom_call_task;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.movementCallback.DeliverCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.movementInterface.CruiseInterface;
import com.pudutech.bumblebee.business.movementInterface.DeliverInterface;
import com.pudutech.bumblebee.business.movementInterface.GoHomeInterface;
import com.pudutech.bumblebee.business.movementInterface.HangOutInterface;
import com.pudutech.bumblebee.business.movementInterface.IdleInterface;
import com.pudutech.bumblebee.business.movementInterface.RecycleInterface;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.movementInterface.TempMoveInterface;
import com.pudutech.bumblebee.business.movementTask.CruiseTask;
import com.pudutech.bumblebee.business.movementTask.DeliverTask;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.HangoutTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.business.movementTask.RecycleTask;
import com.pudutech.bumblebee.business.movementTask.TempTask;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.utils.ActiveModel2;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.TaskType;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomCallRunPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J0\u0010\u0019\u001a\u00020\u00172\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0014j\b\u0012\u0004\u0012\u00020\t`\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0017H\u0016J\b\u0010\u001f\u001a\u00020\u0017H\u0016J\b\u0010 \u001a\u00020\u0017H\u0016J\u001c\u0010!\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010\tH\u0016J \u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\u0018\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\tH\u0016J\u0018\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020\t2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u00101\u001a\u00020\u00172\u0006\u0010\"\u001a\u000202H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0014j\b\u0012\u0004\u0012\u00020\t`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "()V", "LOCK", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "<set-?>", "", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel2;", "deliverInterface", "Lcom/pudutech/bumblebee/business/movementInterface/DeliverInterface;", "destinations", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "actionActive", "", "actionCancelAll", "actionInitTask", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "performance", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "actionPause", "actionPauseNoTimer", "onDone", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onStatusChanged", "destination", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "range", "", "onTaskSetup", "isLegalTask", "", "info", "reportToCloud", "des", "showViewModelOnUIThread", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CustomCallRunPresenter extends BaseOneViewPresenter<CustomCallRunContract.ViewInterface> implements CustomCallRunContract.PresenterInterface, DeliverCallback {
    private final ActiveModel2 activeModel;
    private DeliverInterface deliverInterface;
    private String TAG = "CustomCallRunPresenter";
    private final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private ArrayList<String> destinations = new ArrayList<>();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TaskStatus.AWAIT.ordinal()] = 1;
            $EnumSwitchMapping$0[TaskStatus.ON_THE_WAY.ordinal()] = 2;
            $EnumSwitchMapping$0[TaskStatus.APPROACHING.ordinal()] = 3;
            $EnumSwitchMapping$0[TaskStatus.ARRIVAL.ordinal()] = 4;
            $EnumSwitchMapping$0[TaskStatus.DONE.ordinal()] = 5;
            $EnumSwitchMapping$0[TaskStatus.CANCEL.ordinal()] = 6;
            $EnumSwitchMapping$0[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 7;
            $EnumSwitchMapping$1 = new int[TaskStatus.values().length];
            $EnumSwitchMapping$1[TaskStatus.ON_THE_WAY.ordinal()] = 1;
            $EnumSwitchMapping$1[TaskStatus.ARRIVAL.ordinal()] = 2;
            $EnumSwitchMapping$1[TaskStatus.DONE.ordinal()] = 3;
            $EnumSwitchMapping$1[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 4;
            $EnumSwitchMapping$1[TaskStatus.CANCEL.ordinal()] = 5;
        }
    }

    public CustomCallRunPresenter() {
        ActiveModel2 activeModel2 = new ActiveModel2();
        activeModel2.setNoNeedPauseShowScene(RobotState.Arrive, RobotState.Idle);
        this.activeModel = activeModel2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    public void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    @Override // com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract.PresenterInterface
    public void actionInitTask(final ArrayList<String> destinations, final SortType sortType, final MoveTaskMode performance) {
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Pdlog.m3273d(getTAG(), "actionInitTask destinations:" + destinations.size() + ' ');
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$actionInitTask$1
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

            /* JADX WARN: Removed duplicated region for block: B:17:0x0197  */
            /* JADX WARN: Removed duplicated region for block: B:9:0x015f A[Catch: all -> 0x01a9, TryCatch #0 {all -> 0x01a9, blocks: (B:3:0x000c, B:5:0x008f, B:7:0x015b, B:9:0x015f, B:11:0x0174, B:12:0x0198, B:16:0x0184, B:18:0x009b, B:20:0x00a8, B:21:0x0125, B:23:0x0134, B:24:0x0139, B:25:0x0140, B:26:0x00b4, B:28:0x00bc, B:29:0x00c7, B:31:0x00cf, B:32:0x00da, B:34:0x00e2, B:35:0x00ed, B:37:0x00f5, B:38:0x0100, B:40:0x0108, B:41:0x0113, B:43:0x011b, B:44:0x0141), top: B:2:0x000c }] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                ReentrantReadWriteLock reentrantReadWriteLock;
                ReentrantReadWriteLock reentrantReadWriteLock2;
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                DeliverInterface deliverInterface;
                DeliverInterface deliverInterface2;
                ArrayList arrayList4;
                ArrayList arrayList5;
                ReportDelivery.INSTANCE.toCloud();
                ReportDelivery.INSTANCE.createNewDelivery();
                try {
                    reentrantReadWriteLock2 = CustomCallRunPresenter.this.LOCK;
                    reentrantReadWriteLock2.writeLock().lock();
                    arrayList = CustomCallRunPresenter.this.destinations;
                    arrayList.clear();
                    arrayList2 = CustomCallRunPresenter.this.destinations;
                    arrayList2.addAll(destinations);
                    arrayList3 = CustomCallRunPresenter.this.destinations;
                    arrayList3.removeIf(new Predicate<String>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$actionInitTask$1.1
                        @Override // java.util.function.Predicate
                        public final boolean test(String it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return !RobotMapManager.INSTANCE.checkDestinationExist(it);
                        }
                    });
                    Peripherals.INSTANCE.getFunctionButton().setMute(false);
                    CustomCallRunPresenter customCallRunPresenter = CustomCallRunPresenter.this;
                    Behavior behavior = Behavior.INSTANCE;
                    TypeToken<DeliverInterface> typeToken = new TypeToken<DeliverInterface>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$actionInitTask$1$$special$$inlined$changeMovementTask$1
                    };
                    Class<? super DeliverInterface> rawType = typeToken.getRawType();
                    Pdlog.m3275i("Behavior", "change task=" + rawType + ' ' + typeToken.getType() + ' ' + typeToken.getClass() + ' ' + typeToken);
                    if (!behavior.getInitDone()) {
                        Pdlog.m3274e("Behavior", "not init yet");
                    } else {
                        DelayResumeActive.INSTANCE.cancelTask();
                        if (Intrinsics.areEqual(rawType, CruiseInterface.class)) {
                            behavior.setMovementTask(new CruiseTask());
                        } else if (Intrinsics.areEqual(rawType, DeliverInterface.class)) {
                            behavior.setMovementTask(new DeliverTask());
                        } else if (Intrinsics.areEqual(rawType, GoHomeInterface.class)) {
                            behavior.setMovementTask(new GoHomeTask());
                        } else if (Intrinsics.areEqual(rawType, HangOutInterface.class)) {
                            behavior.setMovementTask(new HangoutTask());
                        } else if (Intrinsics.areEqual(rawType, RecycleInterface.class)) {
                            behavior.setMovementTask(new RecycleTask());
                        } else if (Intrinsics.areEqual(rawType, TempMoveInterface.class)) {
                            behavior.setMovementTask(new TempTask());
                        } else if (Intrinsics.areEqual(rawType, IdleInterface.class)) {
                            behavior.setMovementTask(new IdleTask());
                        } else {
                            Pdlog.m3274e("Behavior", rawType + " not supported");
                        }
                        Pdlog.m3275i("Behavior", "change task done");
                        BaseTaskInterface movementTask = behavior.getMovementTask();
                        if (movementTask == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.DeliverInterface");
                        }
                        deliverInterface = (DeliverInterface) movementTask;
                        deliverInterface2 = deliverInterface;
                        if (deliverInterface2 == null) {
                            deliverInterface2.setCallback(CustomCallRunPresenter.this);
                            if (performance.ordinal() == MovePerformance.STEADY.ordinal()) {
                                arrayList5 = CustomCallRunPresenter.this.destinations;
                                deliverInterface2.setDestinations(arrayList5, sortType, performance);
                            } else {
                                arrayList4 = CustomCallRunPresenter.this.destinations;
                                DeliverInterface.DefaultImpls.setDestinations$default(deliverInterface2, arrayList4, sortType, null, 4, null);
                            }
                        } else {
                            deliverInterface2 = null;
                        }
                        customCallRunPresenter.deliverInterface = deliverInterface2;
                    }
                    deliverInterface = null;
                    deliverInterface2 = deliverInterface;
                    if (deliverInterface2 == null) {
                    }
                    customCallRunPresenter.deliverInterface = deliverInterface2;
                } finally {
                    reentrantReadWriteLock = CustomCallRunPresenter.this.LOCK;
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$actionActive$1
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
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                deliverInterface = CustomCallRunPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$actionPause$1
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
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                deliverInterface = CustomCallRunPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.pause(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract.PresenterInterface
    public void actionPauseNoTimer() {
        Pdlog.m3275i(getTAG(), "actionPauseNoTimer");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$actionPauseNoTimer$1
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
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                deliverInterface = CustomCallRunPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract.PresenterInterface
    public void actionCancelAll() {
        Pdlog.m3275i(getTAG(), "actionCancelAll");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$actionCancelAll$1
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
                ReentrantReadWriteLock reentrantReadWriteLock;
                ReentrantReadWriteLock reentrantReadWriteLock2;
                ArrayList<String> arrayList;
                ReentrantReadWriteLock reentrantReadWriteLock3;
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                try {
                    reentrantReadWriteLock2 = CustomCallRunPresenter.this.LOCK;
                    reentrantReadWriteLock2.readLock().lock();
                    arrayList = CustomCallRunPresenter.this.destinations;
                    for (String str : arrayList) {
                        deliverInterface = CustomCallRunPresenter.this.deliverInterface;
                        if (deliverInterface != null) {
                            deliverInterface.set(str, TaskStatus.CANCEL);
                        }
                    }
                    reentrantReadWriteLock3 = CustomCallRunPresenter.this.LOCK;
                    reentrantReadWriteLock3.readLock().unlock();
                    CustomCallRunPresenter.this.deliverInterface = (DeliverInterface) null;
                } catch (Throwable th) {
                    reentrantReadWriteLock = CustomCallRunPresenter.this.LOCK;
                    reentrantReadWriteLock.readLock().unlock();
                    throw th;
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.DeliverCallback
    public void onStatusChanged(String destination, TaskStatus status, double range) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(getTAG(), "onStatusChanged destination=" + destination + " status=" + status + " when view=" + getTheView() + " , range = " + range);
        reportToCloud(destination, status);
        switch (status) {
            case AWAIT:
            case CANCEL:
            default:
                return;
            case ON_THE_WAY:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.ON_THE_WAY);
                return;
            case APPROACHING:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.APPROACHING);
                return;
            case ARRIVAL:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.ARRIVAL);
                DeliverInterface deliverInterface = this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.set(destination, TaskStatus.DONE);
                    return;
                }
                return;
            case DONE:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.DONE);
                return;
            case DONE_BEFORE_ARRIVAL:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.DONE_BEFORE_ARRIVAL);
                return;
        }
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onTaskSetup(boolean isLegalTask, String info) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Pdlog.m3273d(getTAG(), "onTaskSetup isLegalTask=" + isLegalTask + " info=" + info);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onMovementChanged(RobotState state, String description) {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("movement changed state: ");
        sb.append(state != null ? state.name() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(tag, objArr);
        ReportDelivery.INSTANCE.setAutoMove((state == RobotState.Pause || state == RobotState.Error) ? false : true);
        if (state != null) {
            Pair<Boolean, Boolean> updateState = this.activeModel.updateState(state);
            if (updateState.getFirst().booleanValue()) {
                showViewModelOnUIThread(updateState.getSecond().booleanValue() ? DeliveryContract.DeliveryEvent.ACTIVE : DeliveryContract.DeliveryEvent.PAUSE);
            }
        }
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        this.deliverInterface = (DeliverInterface) null;
        ReportDelivery.INSTANCE.toCloud();
        showViewModelOnUIThread(DeliveryContract.DeliveryEvent.ALL_DONE);
    }

    private final void reportToCloud(String des, TaskStatus status) {
        int i = WhenMappings.$EnumSwitchMapping$1[status.ordinal()];
        if (i == 1) {
            ReportDelivery.INSTANCE.goNext(des, TaskType.CUSTOM_CALL, new ArrayList<>());
            return;
        }
        if (i == 2) {
            ReportDelivery.INSTANCE.markTaking();
            return;
        }
        if (i == 3 || i == 4) {
            ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(true);
            ReportDelivery.INSTANCE.toCloud();
        } else {
            if (i != 5) {
                return;
            }
            ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(false);
        }
    }

    private final void showViewModelOnUIThread(final DeliveryContract.DeliveryEvent state) {
        Pdlog.m3273d(getTAG(), "showViewModelOnUIThread for event=" + state + " when view=" + getTheView());
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter$showViewModelOnUIThread$1
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
                CustomCallRunContract.ViewInterface theView;
                theView = CustomCallRunPresenter.this.getTheView();
                if (theView != null) {
                    theView.showOnTheWayState(state);
                }
            }
        });
    }
}
