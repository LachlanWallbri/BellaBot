package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: CruiseVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00009\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0001\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/CruiseVm;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "_autoReturnState", "Landroidx/lifecycle/MutableLiveData;", "", "autoReturnState", "Landroidx/lifecycle/LiveData;", "getAutoReturnState", "()Landroidx/lifecycle/LiveData;", "batteryListener", "com/pudutech/bumblebee/robot_ui/viewmodel/CruiseVm$batteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CruiseVm$batteryListener$1;", "mAdverVm", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdvertiseVm;", "getMAdverVm", "()Lcom/pudutech/bumblebee/robot_ui/advertise/AdvertiseVm;", "onCleared", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseVm extends BaseViewModel {
    private final String TAG = "CruiseVm";
    private final AdvertiseVm mAdverVm = new AdvertiseVm(ViewModelKt.getViewModelScope(this));
    private final MutableLiveData<Boolean> _autoReturnState = new MutableLiveData<>(false);
    private final LiveData<Boolean> autoReturnState = VMExtKt.toLiveData(this._autoReturnState);
    private final CruiseVm$batteryListener$1 batteryListener = new BatteryContract2.ViewInterface() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.CruiseVm$batteryListener$1
        @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
        public void showChargerEvent(BatteryContract2.ChargerModel model) {
            Intrinsics.checkParameterIsNotNull(model, "model");
        }

        @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
        public void showLowerNotify() {
        }

        @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
        public void showPowerEvent(BatteryContract2.PowerModel model) {
            Intrinsics.checkParameterIsNotNull(model, "model");
        }

        @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
        public void showPowerChange(int i) {
            MutableLiveData mutableLiveData;
            if (i <= 10) {
                mutableLiveData = CruiseVm.this._autoReturnState;
                mutableLiveData.postValue(true);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CruiseVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, m3961d2 = {"<anonymous>", "", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/ViewModelLaunchBuild;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.CruiseVm$1 */
    /* loaded from: classes4.dex */
    public static final class C44111 extends Lambda implements Function1<ViewModelLaunchBuild, Unit> {
        C44111() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ViewModelLaunchBuild viewModelLaunchBuild) {
            invoke2(viewModelLaunchBuild);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CruiseVm.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CruiseVm$1$1", m3970f = "CruiseVm.kt", m3971i = {0, 1, 1}, m3972l = {57, 73}, m3973m = "invokeSuspend", m3974n = {"$this$job", "$this$job", "$this$collect$iv"}, m3975s = {"L$0", "L$0", "L$1"})
        /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.CruiseVm$1$1, reason: invalid class name */
        /* loaded from: classes4.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            Object L$1;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f4985p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f4985p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.f4985p$;
                    CountdownUtil countdownUtil = CountdownUtil.INSTANCE;
                    long customCruiseTime = Constans.INSTANCE.getCustomCruiseTime() * 60;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    obj = countdownUtil.countDown(customCruiseTime, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Flow flow = (Flow) obj;
                FlowCollector<Long> flowCollector = new FlowCollector<Long>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.CruiseVm$1$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Long l, Continuation continuation) {
                        MutableLiveData mutableLiveData;
                        if (l.longValue() == 0) {
                            mutableLiveData = CruiseVm.this._autoReturnState;
                            mutableLiveData.postValue(Boxing.boxBoolean(true));
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.L$0 = coroutineScope;
                this.L$1 = flow;
                this.label = 2;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ViewModelLaunchBuild receiver) {
            Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            receiver.job(new AnonymousClass1(null));
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.pudutech.bumblebee.robot_ui.viewmodel.CruiseVm$batteryListener$1] */
    public CruiseVm() {
        Pdlog.m3273d(this.TAG, "Constans.cruiseReturnSwitch: " + Constans.INSTANCE.getCruiseReturnSwitch());
        if (Constans.INSTANCE.getCruiseReturnSwitch()) {
            if (BatteryInfoManager.INSTANCE.getPower() > 10) {
                BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.batteryListener);
            }
            VMExtKt.launchIO(this, new C44111());
        }
    }

    public final AdvertiseVm getMAdverVm() {
        return this.mAdverVm;
    }

    public final LiveData<Boolean> getAutoReturnState() {
        return this.autoReturnState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        this.mAdverVm.onCleared();
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.batteryListener);
    }
}
