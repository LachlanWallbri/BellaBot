package com.pudutech.bumblebee.presenter.delivery_task;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
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
import com.pudutech.bumblebee.business.peripherals_task.lora_notice_task.LoRaNoticeTask;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTaskEvent;
import com.pudutech.bumblebee.business.utils.ActiveModel2;
import com.pudutech.bumblebee.business.utils.FactoryTestHelper;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.TaskType;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager;
import com.pudutech.bumblebee.presenter.robot_open_task.interf.OnCallNaviStateChangeListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteCommandListener;
import com.pudutech.bumblebee.presenter.utils.cloner.cloning.Cloner;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.resources.resource.ResUtil;
import com.pudutech.robot.opensdk.bean.ActionCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: DeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Í\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0003\u001e!$\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020)H\u0016J\b\u0010+\u001a\u00020)H\u0016J\b\u0010,\u001a\u00020)H\u0016J8\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020/2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u00020201j\b\u0012\u0004\u0012\u000202`32\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u00020)H\u0016J\b\u00109\u001a\u00020)H\u0016J\b\u0010:\u001a\u00020)H\u0016J\u001c\u0010;\u001a\u00020)2\u0006\u0010<\u001a\u00020=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0007H\u0002J\u0018\u0010?\u001a\u00020)2\u0006\u0010@\u001a\u00020\u00072\u0006\u0010A\u001a\u00020BH\u0002J>\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020)0I2\u0010\b\u0002\u0010J\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010KH\u0002J\u0006\u0010L\u001a\u00020)J\u0006\u0010M\u001a\u00020)J\b\u0010N\u001a\u00020)H\u0002J\u0010\u0010O\u001a\u00020)2\u0006\u0010P\u001a\u00020\u0016H\u0002J(\u0010Q\u001a\u00020\u001b2\u0016\u0010R\u001a\u0012\u0012\u0004\u0012\u00020201j\b\u0012\u0004\u0012\u000202`32\u0006\u0010S\u001a\u000202H\u0002J\u0010\u0010T\u001a\u00020)2\u0006\u0010A\u001a\u00020BH\u0002J\u0006\u0010U\u001a\u00020)J\b\u0010V\u001a\u00020)H\u0016J\u001c\u0010W\u001a\u00020)2\b\u0010X\u001a\u0004\u0018\u00010Y2\b\u0010Z\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010[\u001a\u00020)2\u0006\u0010@\u001a\u00020\u00072\u0006\u0010A\u001a\u00020B2\u0006\u0010\\\u001a\u00020]H\u0016J\u0018\u0010^\u001a\u00020)2\u0006\u0010_\u001a\u00020\u001b2\u0006\u0010`\u001a\u00020\u0007H\u0016J\b\u0010a\u001a\u00020)H\u0014J\b\u0010b\u001a\u00020)H\u0014J\b\u0010c\u001a\u00020)H\u0002J\u0018\u0010d\u001a\u00020)2\u0006\u0010e\u001a\u00020\u00072\u0006\u0010A\u001a\u00020BH\u0002J\u0018\u0010f\u001a\u00020)2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020EH\u0002J\u0010\u0010j\u001a\u00020)2\u0006\u0010g\u001a\u00020hH\u0002J\b\u0010k\u001a\u00020)H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0010\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006l"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel2;", "callNaviState", "Lcom/pudutech/bumblebee/presenter/robot_open_task/interf/OnCallNaviStateChangeListener;", "getCallNaviState", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/interf/OnCallNaviStateChangeListener;", "setCallNaviState", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/interf/OnCallNaviStateChangeListener;)V", "cls", "countdownJob", "Lkotlinx/coroutines/Job;", "countdownMillis", "", "deliverInterface", "Lcom/pudutech/bumblebee/business/movementInterface/DeliverInterface;", "iotTag", "isManualCancelAll", "", "isStopArrivalNotify", "palletListener", "com/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$palletListener$1", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$palletListener$1;", "qrScanListener", "com/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$qrScanListener$1", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$qrScanListener$1;", "remoteCommandListener", "com/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$remoteCommandListener$1", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$remoteCommandListener$1;", "viewModel", "Lcom/pudutech/bumblebee/presenter/delivery_task/ViewModel;", "actionActive", "", "actionCancelAll", "actionFinish", "actionFinishBeforeArrival", "actionInitTask", "mode", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "allTrays", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "sort", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "performance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "actionModify", "actionPause", "actionPauseNoTimer", "completeDelivery", "type", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel$CompleteType;", "completeEmployeeId", "controlNoticeVIP", "destination", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "countDownCoroutines", "total", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onTick", "Lkotlin/Function1;", "onFinish", "Lkotlin/Function0;", "countdownPause", "countdownResume", "delayAutoDone", "executeCountdownTask", "seconds", "isStopAndPalletChange", "trays", "trayModel", "notifyIot", "onDestroy", "onDone", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onStatusChanged", "range", "", "onTaskSetup", "isLegalTask", "info", "onViewAttach", "onViewRemoved", "recordArrivalPallets", "reportToCloud", "des", "showPalletOnUIThread", "event", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryEvent;", "index", "showViewModelOnUIThread", "stopArrivalNotify", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliveryPresenter extends BaseOneViewPresenter<DeliveryContract.ViewInterface> implements DeliveryContract.PresenterInterface, DeliverCallback {
    private final ActiveModel2 activeModel;
    private OnCallNaviStateChangeListener callNaviState;
    private Job countdownJob;
    private long countdownMillis;
    private DeliverInterface deliverInterface;
    private boolean isManualCancelAll;
    private boolean isStopArrivalNotify;
    private final DeliveryPresenter$palletListener$1 palletListener;
    private final DeliveryPresenter$qrScanListener$1 qrScanListener;
    private final DeliveryPresenter$remoteCommandListener$1 remoteCommandListener;
    private final String TAG = "DeliveryPresenter";
    private final ViewModel viewModel = new ViewModel();
    private final String cls = toString();
    private final String iotTag = "iot";

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

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
            $EnumSwitchMapping$2 = new int[PalletTaskEvent.values().length];
            $EnumSwitchMapping$2[PalletTaskEvent.EMPTY.ordinal()] = 1;
            $EnumSwitchMapping$2[PalletTaskEvent.PLACE.ordinal()] = 2;
            $EnumSwitchMapping$2[PalletTaskEvent.TAKE.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[TaskStatus.values().length];
            $EnumSwitchMapping$3[TaskStatus.APPROACHING.ordinal()] = 1;
            $EnumSwitchMapping$3[TaskStatus.DONE.ordinal()] = 2;
            $EnumSwitchMapping$3[TaskStatus.CANCEL.ordinal()] = 3;
            $EnumSwitchMapping$3[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 4;
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$remoteCommandListener$1] */
    public DeliveryPresenter() {
        ActiveModel2 activeModel2 = new ActiveModel2();
        activeModel2.setNoNeedPauseShowScene(RobotState.Arrive, RobotState.Idle);
        this.activeModel = activeModel2;
        this.palletListener = new DeliveryPresenter$palletListener$1(this);
        this.remoteCommandListener = new RemoteCommandListener() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$remoteCommandListener$1
            @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteCommandListener
            public boolean onAction(ActionCommand action) {
                ViewModel viewModel;
                ViewModel viewModel2;
                DeliverInterface deliverInterface;
                Intrinsics.checkParameterIsNotNull(action, "action");
                Pdlog.m3273d(DeliveryPresenter.this.getTAG(), "onActionCommand : action = " + action + "; ");
                if (action == ActionCommand.COMPLETE) {
                    viewModel2 = DeliveryPresenter.this.viewModel;
                    if (viewModel2.getDestinations(TaskStatus.ARRIVAL).size() > 0) {
                        DeliveryPresenter.completeDelivery$default(DeliveryPresenter.this, DeliveryModel.CompleteType.REMOTE, null, 2, null);
                        deliverInterface = DeliveryPresenter.this.deliverInterface;
                        if (deliverInterface != null) {
                            deliverInterface.setActive(true);
                        }
                        return true;
                    }
                } else if (action == ActionCommand.CANCEL_ALL_DELIVERY) {
                    viewModel = DeliveryPresenter.this.viewModel;
                    if (viewModel.getDestinations(TaskStatus.ON_THE_WAY).size() > 0) {
                        DeliveryPresenter.this.actionCancelAll();
                        return true;
                    }
                }
                return false;
            }
        };
        this.qrScanListener = new DeliveryPresenter$qrScanListener$1(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionInitTask(final DeliveryMode mode, final ArrayList<TrayModel> allTrays, final SortType sort, final MovePerformance performance) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(allTrays, "allTrays");
        Intrinsics.checkParameterIsNotNull(sort, "sort");
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Pdlog.m3275i(getTAG(), "actionInitTask " + mode + ' ' + allTrays + ' ' + sort);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionInitTask$1
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

            /* JADX WARN: Removed duplicated region for block: B:17:0x0211  */
            /* JADX WARN: Removed duplicated region for block: B:23:0x0235  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                Cloner cloner;
                ActiveModel2 activeModel2;
                DeliveryPresenter$palletListener$1 deliveryPresenter$palletListener$1;
                ViewModel viewModel;
                ViewModel viewModel2;
                DeliverInterface deliverInterface;
                DeliverInterface deliverInterface2;
                ReportDelivery.INSTANCE.toCloud();
                ReportDelivery.INSTANCE.createNewDelivery();
                cloner = DeliveryPresenter.this.getCloner();
                ArrayList<TrayModel> replicate = (ArrayList) cloner.deepClone(allTrays);
                Pdlog.m3273d(DeliveryPresenter.this.getTAG(), "replicate start =" + replicate);
                Intrinsics.checkExpressionValueIsNotNull(replicate, "replicate");
                Iterator it = replicate.iterator();
                while (it.hasNext()) {
                    ((TrayModel) it.next()).getAllDestinations().removeIf(new Predicate<DeliveryModel>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionInitTask$1$1$1
                        @Override // java.util.function.Predicate
                        public final boolean test(DeliveryModel it2) {
                            Intrinsics.checkParameterIsNotNull(it2, "it");
                            return !RobotMapManager.INSTANCE.checkDestinationExist(it2.getDestination());
                        }
                    });
                }
                Pdlog.m3273d(DeliveryPresenter.this.getTAG(), "replicate end =" + replicate);
                activeModel2 = DeliveryPresenter.this.activeModel;
                activeModel2.resetLast();
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                PalletTask pallet = Peripherals.INSTANCE.getPallet();
                deliveryPresenter$palletListener$1 = DeliveryPresenter.this.palletListener;
                pallet.addListener(deliveryPresenter$palletListener$1);
                DeliveryPresenter.this.isManualCancelAll = false;
                viewModel = DeliveryPresenter.this.viewModel;
                viewModel.init(mode, replicate);
                viewModel2 = DeliveryPresenter.this.viewModel;
                ArrayList<String> allDestination = viewModel2.getAllDestination();
                if (allDestination.isEmpty()) {
                    Pdlog.m3274e(DeliveryPresenter.this.getTAG(), "no valid destination");
                    DeliveryPresenter.this.showViewModelOnUIThread(DeliveryContract.DeliveryEvent.NOT_FIND_TARGET);
                    return;
                }
                Pdlog.m3275i(DeliveryPresenter.this.getTAG(), "destination=" + allDestination + " will be set up");
                DeliveryPresenter deliveryPresenter = DeliveryPresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<DeliverInterface> typeToken = new TypeToken<DeliverInterface>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionInitTask$1$$special$$inlined$changeMovementTask$1
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
                        deliverInterface2.setCallback(DeliveryPresenter.this);
                        if (performance == MovePerformance.STEADY) {
                            deliverInterface2.setDestinations(allDestination, sort, MoveTaskMode.Steady);
                        } else {
                            DeliverInterface.DefaultImpls.setDestinations$default(deliverInterface2, allDestination, sort, null, 4, null);
                        }
                    } else {
                        deliverInterface2 = null;
                    }
                    deliveryPresenter.deliverInterface = deliverInterface2;
                }
                deliverInterface = null;
                deliverInterface2 = deliverInterface;
                if (deliverInterface2 == null) {
                }
                deliveryPresenter.deliverInterface = deliverInterface2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        Peripherals.INSTANCE.getQrScanTask().addListener(this.qrScanListener);
        RobotOpenManager.INSTANCE.getRemoteCommandListener().addListener(this.remoteCommandListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        Peripherals.INSTANCE.getQrScanTask().removeListener(this.qrScanListener);
        RobotOpenManager.INSTANCE.getRemoteCommandListener().removeListener(this.remoteCommandListener);
        stopArrivalNotify();
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionActive$1
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
                deliverInterface = DeliveryPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void completeDelivery$default(DeliveryPresenter deliveryPresenter, DeliveryModel.CompleteType completeType, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        deliveryPresenter.completeDelivery(completeType, str);
    }

    private final void completeDelivery(final DeliveryModel.CompleteType type, final String completeEmployeeId) {
        Pdlog.m3273d(getTAG(), "completeDelivery : type = " + type + "; completeEmployeeId = " + completeEmployeeId + "; ");
        Peripherals.INSTANCE.getFunctionButton().setMute(false);
        ViewModel viewModel = this.viewModel;
        viewModel.forEachDeliveryModel(viewModel.getDestination(), new Function1<DeliveryModel, Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$completeDelivery$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeliveryModel deliveryModel) {
                invoke2(deliveryModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeliveryModel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(DeliveryPresenter.this.getTAG(), "completeDelivery " + it.getDestination() + " set type = " + type);
                it.setCompleteType$module_bumblebee_presenter_robotRelease(type);
                it.setCompleteEmployeeId$module_bumblebee_presenter_robotRelease(completeEmployeeId);
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionPause$1
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
                deliverInterface = DeliveryPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.pause(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionPauseNoTimer() {
        Pdlog.m3275i(getTAG(), "actionPauseNoTimer");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionPauseNoTimer$1
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
                deliverInterface = DeliveryPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionModify() {
        Pdlog.m3275i(getTAG(), "actionModify");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionModify$1
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
                ViewModel viewModel;
                ViewModel viewModel2;
                ViewModel viewModel3;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                deliverInterface = DeliveryPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(false);
                }
                IdleInterface idleInterface = null;
                DeliveryPresenter.this.deliverInterface = (DeliverInterface) null;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<IdleInterface> typeToken = new TypeToken<IdleInterface>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionModify$1$$special$$inlined$changeMovementTask$1
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
                    DeliveryPresenter.this.showViewModelOnUIThread(DeliveryContract.DeliveryEvent.MODIFY);
                    idleInterface2.setActive(false);
                }
                viewModel = DeliveryPresenter.this.viewModel;
                if (viewModel.getDeliveryMode() == DeliveryMode.CALL_DIRECT) {
                    RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                    viewModel2 = DeliveryPresenter.this.viewModel;
                    DeliveryMode deliveryMode = viewModel2.getDeliveryMode();
                    TaskStatus taskStatus = TaskStatus.CANCEL;
                    viewModel3 = DeliveryPresenter.this.viewModel;
                    robotOpenManager.onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease(deliveryMode, taskStatus, viewModel3);
                }
                ReportDelivery.INSTANCE.toCloud();
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionFinish() {
        Pdlog.m3273d(getTAG(), "actionFinish ");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionFinish$1
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
                DeliveryPresenter.completeDelivery$default(DeliveryPresenter.this, DeliveryModel.CompleteType.MANUAL, null, 2, null);
                deliverInterface = DeliveryPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionFinishBeforeArrival() {
        Pdlog.m3275i(getTAG(), "actionFinishCurrentTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionFinishBeforeArrival$1
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
                DeliverInterface deliverInterface2;
                ViewModel viewModel;
                DeliveryPresenter.completeDelivery$default(DeliveryPresenter.this, DeliveryModel.CompleteType.MANUAL, null, 2, null);
                deliverInterface = DeliveryPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    viewModel = DeliveryPresenter.this.viewModel;
                    deliverInterface.set(viewModel.getDestination(), TaskStatus.DONE_BEFORE_ARRIVAL);
                }
                deliverInterface2 = DeliveryPresenter.this.deliverInterface;
                if (deliverInterface2 != null) {
                    deliverInterface2.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.PresenterInterface
    public void actionCancelAll() {
        Pdlog.m3275i(getTAG(), "actionCancelAll");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$actionCancelAll$1
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
                ViewModel viewModel;
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                DeliveryPresenter.this.isManualCancelAll = true;
                viewModel = DeliveryPresenter.this.viewModel;
                for (String str : viewModel.getDestinations(TaskStatus.AWAIT, TaskStatus.ON_THE_WAY, TaskStatus.APPROACHING, TaskStatus.ARRIVAL)) {
                    deliverInterface = DeliveryPresenter.this.deliverInterface;
                    if (deliverInterface != null) {
                        deliverInterface.set(str, TaskStatus.CANCEL);
                    }
                }
                DeliveryPresenter.this.deliverInterface = (DeliverInterface) null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showViewModelOnUIThread(final DeliveryContract.DeliveryEvent event) {
        Pdlog.m3273d(getTAG(), "showViewModelOnUIThread for event=" + event + " when view=" + getTheView());
        final ViewModel viewModel = (ViewModel) getCloner().deepClone(this.viewModel);
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$showViewModelOnUIThread$1
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
                DeliveryContract.ViewInterface theView;
                theView = DeliveryPresenter.this.getTheView();
                if (theView != null) {
                    DeliveryContract.DeliveryEvent deliveryEvent = event;
                    ViewModel replicate = viewModel;
                    Intrinsics.checkExpressionValueIsNotNull(replicate, "replicate");
                    theView.showViewModelChanged(deliveryEvent, replicate);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPalletOnUIThread(final DeliveryContract.DeliveryEvent event, final int index) {
        Pdlog.m3273d(getTAG(), "showViewModelOnUIThread for event=" + event + " when view=" + getTheView());
        final ViewModel viewModel = (ViewModel) getCloner().deepClone(this.viewModel);
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$showPalletOnUIThread$1
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
                DeliveryContract.ViewInterface theView;
                theView = DeliveryPresenter.this.getTheView();
                if (theView != null) {
                    DeliveryContract.DeliveryEvent deliveryEvent = event;
                    ViewModel replicate = viewModel;
                    Intrinsics.checkExpressionValueIsNotNull(replicate, "replicate");
                    theView.showPalletChanged(deliveryEvent, replicate, index);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.DeliverCallback
    public void onStatusChanged(String destination, final TaskStatus status, final double range) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(getTAG(), "onStatusChanged destination=" + destination + " status=" + status + " when view=" + getTheView() + " , range = " + range);
        this.viewModel.forEachDeliveryModel(destination, new Function1<DeliveryModel, Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$onStatusChanged$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeliveryModel deliveryModel) {
                invoke2(deliveryModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DeliveryModel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.setRange(range);
                it.setStatus(status);
            }
        });
        this.viewModel.setDestination(destination);
        reportToCloud(destination, status);
        notifyIot(status);
        switch (status) {
            case ON_THE_WAY:
                for (TrayModel trayModel : this.viewModel.getTrays()) {
                    DeliveryModel deliveryModel = trayModel.getDeliveryModel(destination);
                    if (deliveryModel != null) {
                        trayModel.setCurrent(deliveryModel);
                        trayModel.setEmpty(false);
                    }
                }
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.ON_THE_WAY);
                break;
            case APPROACHING:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.APPROACHING);
                break;
            case ARRIVAL:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.ARRIVAL);
                this.activeModel.setTaskArrival();
                if (this.viewModel.getDeliveryMode().isOneOf(DeliveryMode.DIRECT, DeliveryMode.CALL_DIRECT)) {
                    DeliverInterface deliverInterface = this.deliverInterface;
                    if (deliverInterface != null) {
                        deliverInterface.set(destination, TaskStatus.DONE);
                    }
                } else if (this.viewModel.getDeliveryMode() != DeliveryMode.BIRTHDAY) {
                    this.countdownMillis = 0L;
                    delayAutoDone();
                }
                recordArrivalPallets();
                break;
            case DONE:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.DONE);
                break;
            case DONE_BEFORE_ARRIVAL:
                showViewModelOnUIThread(DeliveryContract.DeliveryEvent.DONE_BEFORE_ARRIVAL);
                break;
        }
        Peripherals.INSTANCE.getPallet().setArrival(status == TaskStatus.ARRIVAL);
        controlNoticeVIP(destination, status);
    }

    private final void notifyIot(final TaskStatus status) {
        RobotNewOpenManager.INSTANCE.onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease(this.viewModel.getDeliveryMode(), status, this.viewModel);
        RobotOpenManager.INSTANCE.onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease(this.viewModel.getDeliveryMode(), status, this.viewModel);
        OnCallNaviStateChangeListener onCallNaviStateChangeListener = this.callNaviState;
        if (onCallNaviStateChangeListener != null) {
            onCallNaviStateChangeListener.onChange(status, this.viewModel.getDestination());
        }
        if (status == TaskStatus.ARRIVAL) {
            stopArrivalNotify();
            this.isStopArrivalNotify = false;
            TimerThread.INSTANCE.rePost(this.cls, this.iotTag, 15000L, 15000L, false, new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$notifyIot$1
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
                    boolean z;
                    ViewModel viewModel;
                    ViewModel viewModel2;
                    ViewModel viewModel3;
                    ViewModel viewModel4;
                    z = DeliveryPresenter.this.isStopArrivalNotify;
                    if (z) {
                        DeliveryPresenter.this.stopArrivalNotify();
                    }
                    RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                    viewModel = DeliveryPresenter.this.viewModel;
                    DeliveryMode deliveryMode = viewModel.getDeliveryMode();
                    TaskStatus taskStatus = status;
                    viewModel2 = DeliveryPresenter.this.viewModel;
                    robotOpenManager.onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease(deliveryMode, taskStatus, viewModel2);
                    RobotNewOpenManager robotNewOpenManager = RobotNewOpenManager.INSTANCE;
                    viewModel3 = DeliveryPresenter.this.viewModel;
                    DeliveryMode deliveryMode2 = viewModel3.getDeliveryMode();
                    TaskStatus taskStatus2 = status;
                    viewModel4 = DeliveryPresenter.this.viewModel;
                    robotNewOpenManager.onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease(deliveryMode2, taskStatus2, viewModel4);
                }
            });
            return;
        }
        stopArrivalNotify();
    }

    public final OnCallNaviStateChangeListener getCallNaviState() {
        return this.callNaviState;
    }

    public final void setCallNaviState(OnCallNaviStateChangeListener onCallNaviStateChangeListener) {
        this.callNaviState = onCallNaviStateChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopArrivalNotify() {
        this.isStopArrivalNotify = true;
        TimerThread.INSTANCE.remove(this.cls, this.iotTag);
    }

    private final void reportToCloud(String des, TaskStatus status) {
        int i = WhenMappings.$EnumSwitchMapping$1[status.ordinal()];
        if (i == 1) {
            ArrayList<Integer> trayIndexes = this.viewModel.getTrayIndexes(des);
            ReportDelivery.INSTANCE.goNext(des, TaskType.INSTANCE.byValue(this.viewModel.getDeliveryMode().getValue()), trayIndexes);
            return;
        }
        if (i == 2) {
            FactoryTestHelper.INSTANCE.recordDelivery();
            ReportDelivery.INSTANCE.markTaking();
            return;
        }
        if (i != 3 && i != 4) {
            if (i != 5) {
                return;
            }
            ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(false);
        } else {
            ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(true);
            if (this.viewModel.getDeliveryMode() == DeliveryMode.DIRECT || this.viewModel.getDeliveryMode() == DeliveryMode.CALL_DIRECT) {
                ReportDelivery.INSTANCE.toCloud();
            }
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
                if (updateState.getSecond().booleanValue()) {
                    return;
                }
                RobotNewOpenManager.INSTANCE.reportRobotTaskStatus(4);
            }
        }
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        Pdlog.m3273d(getTAG(), "on all Done. isManualCancelAll=" + this.isManualCancelAll);
        Peripherals.INSTANCE.getPallet().removeListener(this.palletListener);
        this.deliverInterface = (DeliverInterface) null;
        if (this.isManualCancelAll) {
            ReportDelivery.INSTANCE.toCloud();
            showViewModelOnUIThread(DeliveryContract.DeliveryEvent.ALL_LEFT_CANCEL);
        } else {
            showViewModelOnUIThread(DeliveryContract.DeliveryEvent.ALL_DONE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isStopAndPalletChange(ArrayList<TrayModel> trays, TrayModel trayModel) {
        boolean z;
        boolean z2;
        DeliveryModel current;
        if (trayModel.getCurrent() != null) {
            DeliveryModel current2 = trayModel.getCurrent();
            if ((current2 != null ? current2.getStatus() : null) == TaskStatus.ARRIVAL) {
                z = false;
                if (trays == null) {
                    z2 = false;
                    for (TrayModel trayModel2 : trays) {
                        if (((trayModel2 == null || (current = trayModel2.getCurrent()) == null) ? null : current.getStatus()) == TaskStatus.ARRIVAL) {
                            z2 = true;
                        }
                    }
                } else {
                    z2 = false;
                }
                return !z2 && z;
            }
        }
        z = true;
        if (trays == null) {
        }
        if (z2) {
        }
    }

    private final void controlNoticeVIP(String destination, TaskStatus status) {
        int i = WhenMappings.$EnumSwitchMapping$3[status.ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3 || i == 4) {
                Peripherals.INSTANCE.getLora().stopNoticeVIP();
                return;
            }
            return;
        }
        LoRaNoticeTask lora = Peripherals.INSTANCE.getLora();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {destination};
        String format = String.format(ResUtil.INSTANCE.getString("pdStr2_31"), Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        lora.noticeVIP(format);
    }

    private final void delayAutoDone() {
        if (BusinessSetting.INSTANCE.getDelayAutoFinishSwitch()) {
            Pdlog.m3275i(getTAG(), "set delay done runnable");
            long j = this.countdownMillis;
            if (j <= 0) {
                j = BusinessSetting.INSTANCE.getDelayAutoFinish_ms() / 1000;
            }
            executeCountdownTask(j);
        }
    }

    private final void executeCountdownTask(long seconds) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DeliveryPresenter$executeCountdownTask$1(this, seconds, null), 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Job countDownCoroutines$default(DeliveryPresenter deliveryPresenter, int i, CoroutineScope coroutineScope, Function1 function1, Function0 function0, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            function0 = (Function0) null;
        }
        return deliveryPresenter.countDownCoroutines(i, coroutineScope, function1, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job countDownCoroutines(int total, CoroutineScope scope, Function1<? super Integer, Unit> onTick, Function0<Unit> onFinish) {
        return FlowKt.launchIn(FlowKt.onCompletion(FlowKt.onEach(FlowKt.flowOn(FlowKt.flow(new DeliveryPresenter$countDownCoroutines$1(total, null)), Dispatchers.getMain()), new DeliveryPresenter$countDownCoroutines$2(onTick, null)), new DeliveryPresenter$countDownCoroutines$3(this, onFinish, null)), scope);
    }

    public final void countdownPause() {
        if (BusinessSetting.INSTANCE.getDelayAutoFinishSwitch() && this.countdownMillis > 0) {
            Job job = this.countdownJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.countdownMillis = 0L;
        }
        Peripherals.INSTANCE.getPallet().pauseEmptyEvent();
    }

    public final void countdownResume() {
        delayAutoDone();
        Peripherals.INSTANCE.getPallet().resumeEmptyEvent();
    }

    private final void recordArrivalPallets() {
        ArrayList<TrayModel> trays = this.viewModel.getTrays();
        ArrayList arrayList = new ArrayList();
        for (Object obj : trays) {
            if (!((TrayModel) obj).getAllDestinations().isEmpty()) {
                arrayList.add(obj);
            }
        }
        int i = 0;
        for (Object obj2 : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TrayModel) obj2).getDeliveryModel(this.viewModel.getDestination()) != null) {
                Peripherals.INSTANCE.getPallet().setTargetPalletId(i2);
            } else {
                Peripherals.INSTANCE.getPallet().addPalletId(i2);
            }
            i = i2;
        }
    }

    public final void onDestroy() {
        Job job = this.countdownJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
