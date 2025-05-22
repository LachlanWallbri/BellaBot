package com.pudutech.bumblebee.presenter.recycle_task;

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
import com.pudutech.bumblebee.business.utils.ActiveModel;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.recycle_task.RecycleContract;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecyclePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u001bH\u0016J\b\u0010\u001f\u001a\u00020\u001bH\u0016J,\u0010 \u001a\u00020\u001b2\u001a\u0010!\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00070\"j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0007`#2\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u001bH\u0016J\b\u0010'\u001a\u00020\u001bH\u0016J\b\u0010(\u001a\u00020\u001bH\u0016J\b\u0010)\u001a\u00020\u001bH\u0016J\u001c\u0010*\u001a\u00020\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010.\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016J\u0018\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u0007H\u0016J\u0010\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u000208H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e0\rj\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/RecyclePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "all", "Ljava/util/LinkedHashMap;", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleViewModel;", "Lkotlin/collections/LinkedHashMap;", "autoMoveActive", "", "Ljava/lang/Boolean;", "deliverInterface", "Lcom/pudutech/bumblebee/business/movementInterface/RecycleInterface;", "destination", "getDestination", "setDestination", "(Ljava/lang/String;)V", "isManualFinishAll", "actionActive", "", "actionArrived", "actionCancelAllTask", "actionFinish", "actionFinishBeforeArrival", "actionGo", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "actionModify", "actionPause", "actionPauseNoTimer", "onDone", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onStatusChanged", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "range", "", "onTaskSetup", "isLegalTask", "info", "showOnUI", "event", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecyclePresenter extends BaseOneViewPresenter<RecycleContract.ViewInterface> implements RecycleContract.PresenterInterface, DeliverCallback {
    private Boolean autoMoveActive;
    private RecycleInterface deliverInterface;
    private boolean isManualFinishAll;
    private final String TAG = "RecyclePresenter";
    private LinkedHashMap<String, RecycleContract.RecycleViewModel> all = new LinkedHashMap<>();
    private String destination = "";
    private final ActiveModel activeModel = new ActiveModel();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final void setDestination(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.destination = str;
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionGo(final ArrayList<String> list, final SortType sortType) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Pdlog.m3275i(getTAG(), "actionGo " + list);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionGo$1
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
                LinkedHashMap linkedHashMap;
                RecycleInterface recycleInterface;
                RecycleInterface recycleInterface2;
                LinkedHashMap linkedHashMap2;
                ReportDelivery.INSTANCE.toCloud();
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                Peripherals.INSTANCE.getFunctionButton().setRecyclePauseKeepTime_ms(Constant.INSTANCE.getRecyclePauseTime());
                RecycleInterface recycleInterface3 = null;
                RecyclePresenter.this.autoMoveActive = (Boolean) null;
                RecyclePresenter.this.isManualFinishAll = false;
                ArrayList arrayList = new ArrayList();
                linkedHashMap = RecyclePresenter.this.all;
                linkedHashMap.clear();
                for (String str : list) {
                    if (str != null) {
                        arrayList.add(str);
                        linkedHashMap2 = RecyclePresenter.this.all;
                    }
                }
                RecyclePresenter recyclePresenter = RecyclePresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<RecycleInterface> typeToken = new TypeToken<RecycleInterface>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionGo$1$$special$$inlined$changeMovementTask$1
                };
                Class<? super RecycleInterface> rawType = typeToken.getRawType();
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
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.RecycleInterface");
                    }
                    recycleInterface3 = (RecycleInterface) movementTask;
                }
                recyclePresenter.deliverInterface = recycleInterface3;
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setCallback(RecyclePresenter.this);
                }
                recycleInterface2 = RecyclePresenter.this.deliverInterface;
                if (recycleInterface2 != null) {
                    RecycleInterface.DefaultImpls.setDestinations$default(recycleInterface2, arrayList, sortType, null, 4, null);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionActive$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionPause$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.pause(Constant.INSTANCE.getRecyclePauseTime());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionArrived() {
        Pdlog.m3275i(getTAG(), "actionArrived");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionArrived$1
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
                RecycleInterface recycleInterface;
                RecycleInterface recycleInterface2;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.set(RecyclePresenter.this.getDestination(), TaskStatus.ARRIVAL);
                }
                recycleInterface2 = RecyclePresenter.this.deliverInterface;
                if (recycleInterface2 != null) {
                    recycleInterface2.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionPauseNoTimer() {
        Pdlog.m3275i(getTAG(), "actionPauseNoTimer");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionPauseNoTimer$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionModify() {
        Pdlog.m3275i(getTAG(), "actionModify");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionModify$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setActive(false);
                }
                IdleInterface idleInterface = null;
                RecyclePresenter.this.deliverInterface = (RecycleInterface) null;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<IdleInterface> typeToken = new TypeToken<IdleInterface>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionModify$1$$special$$inlined$changeMovementTask$1
                };
                Class<? super IdleInterface> rawType = typeToken.getRawType();
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
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.IdleInterface");
                    }
                    idleInterface = (IdleInterface) movementTask;
                }
                IdleInterface idleInterface2 = idleInterface;
                if (idleInterface2 != null) {
                    RecyclePresenter.this.showOnUI(RecycleContract.RecycleEvent.MODIFY);
                    idleInterface2.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionFinish() {
        Pdlog.m3273d(getTAG(), "actionFinish ");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionFinish$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionFinishBeforeArrival() {
        Pdlog.m3275i(getTAG(), "actionFinishCurrentTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionFinishBeforeArrival$1
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
                RecycleInterface recycleInterface;
                RecycleInterface recycleInterface2;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = RecyclePresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.set(RecyclePresenter.this.getDestination(), TaskStatus.DONE_BEFORE_ARRIVAL);
                }
                recycleInterface2 = RecyclePresenter.this.deliverInterface;
                if (recycleInterface2 != null) {
                    recycleInterface2.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.PresenterInterface
    public void actionCancelAllTask() {
        Pdlog.m3275i(getTAG(), "actionFinishAllTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$actionCancelAllTask$1
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
                LinkedHashMap linkedHashMap;
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                RecyclePresenter.this.isManualFinishAll = true;
                linkedHashMap = RecyclePresenter.this.all;
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    recycleInterface = RecyclePresenter.this.deliverInterface;
                    if (recycleInterface != null) {
                        recycleInterface.set((String) entry.getKey(), TaskStatus.DONE_BEFORE_ARRIVAL);
                    }
                }
                RecyclePresenter.this.deliverInterface = (RecycleInterface) null;
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.DeliverCallback
    public void onStatusChanged(String destination, TaskStatus status, double range) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3275i(getTAG(), "onStatusChanged destination=" + destination + "  status=" + status);
        this.destination = destination;
        switch (status) {
            case AWAIT:
            case CANCEL:
            default:
                return;
            case ON_THE_WAY:
                showOnUI(RecycleContract.RecycleEvent.ON_THE_WAY);
                return;
            case APPROACHING:
                showOnUI(RecycleContract.RecycleEvent.APPROACHING);
                return;
            case ARRIVAL:
                showOnUI(RecycleContract.RecycleEvent.ARRIVAL);
                this.autoMoveActive = false;
                return;
            case DONE:
                this.all.put(destination, new RecycleContract.RecycleViewModel(destination, true));
                showOnUI(RecycleContract.RecycleEvent.DONE);
                return;
            case DONE_BEFORE_ARRIVAL:
                showOnUI(RecycleContract.RecycleEvent.DONE_BEFORE_ARRIVAL);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUI(final RecycleContract.RecycleEvent event) {
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.all);
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter$showOnUI$1
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
                RecycleContract.ViewInterface theView;
                theView = RecyclePresenter.this.getTheView();
                if (theView != null) {
                    theView.showRecycleEvent(RecyclePresenter.this.getDestination(), event, linkedHashMap);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onTaskSetup(boolean isLegalTask, String info) {
        RecycleInterface recycleInterface;
        Intrinsics.checkParameterIsNotNull(info, "info");
        Pdlog.m3275i(getTAG(), "onTaskSetup " + isLegalTask + " info=" + info);
        if (!isLegalTask || (recycleInterface = this.deliverInterface) == null) {
            return;
        }
        recycleInterface.setActive(true);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onMovementChanged(RobotState state, String description) {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("movement changed state: ");
        sb.append(state != null ? state.name() : null);
        sb.append(" autoMoveActive=");
        sb.append(this.autoMoveActive);
        objArr[0] = sb.toString();
        Pdlog.m3273d(tag, objArr);
        boolean checkActive = state != null ? this.activeModel.checkActive(state) : false;
        if (Intrinsics.areEqual(Boolean.valueOf(checkActive), this.autoMoveActive)) {
            return;
        }
        this.autoMoveActive = Boolean.valueOf(checkActive);
        if ((state == RobotState.Arrive || state == RobotState.Idle) && !checkActive) {
            return;
        }
        showOnUI(checkActive ? RecycleContract.RecycleEvent.ACTIVE : RecycleContract.RecycleEvent.PAUSE);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        Pdlog.m3273d(getTAG(), "on all Done. isManualFinishAll=" + this.isManualFinishAll);
        this.deliverInterface = (RecycleInterface) null;
        if (this.isManualFinishAll) {
            showOnUI(RecycleContract.RecycleEvent.ALL_LEFT_CANCEL);
        } else {
            showOnUI(RecycleContract.RecycleEvent.ALL_DONE);
        }
    }
}
