package com.pudu.library.loracall.viewModel;

import androidx.lifecycle.MutableLiveData;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.LoraApproveState;
import com.pudu.library.loracall.LoraSignalStrength;
import com.pudu.library.loracall.MsgReceiveHandle;
import com.pudu.library.loracall.base.BaseViewModel;
import com.pudu.library.loracall.bean.LoraCertificationParam;
import com.pudu.loracall.library.C3965R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: LoRaApproveViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001a\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u001eH\u0014J\u0006\u0010 \u001a\u00020\u001eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR9\u0010\r\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000ej\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R9\u0010\u0015\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000ej\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR9\u0010\u001c\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000ej\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014¨\u0006!"}, m3961d2 = {"Lcom/pudu/library/loracall/viewModel/LoRaApproveViewModel;", "Lcom/pudu/library/loracall/base/BaseViewModel;", "()V", "listener", "Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "getListener", "()Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "param", "Lcom/pudu/library/loracall/bean/LoraCertificationParam;", "getParam", "()Lcom/pudu/library/loracall/bean/LoraCertificationParam;", "setParam", "(Lcom/pudu/library/loracall/bean/LoraCertificationParam;)V", "sendModelList", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "", "", "Lkotlin/collections/ArrayList;", "getSendModelList", "()Ljava/util/ArrayList;", "sendTypeList", "getSendTypeList", "signalState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudu/library/loracall/LoraSignalStrength;", "getSignalState", "()Landroidx/lifecycle/MutableLiveData;", "waveList", "getWaveList", "", "onCleared", "sendParam", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaApproveViewModel extends BaseViewModel {
    private final MutableLiveData<LoraSignalStrength> signalState;
    private LoraCertificationParam param = new LoraCertificationParam();
    private final ArrayList<Pair<String, Integer>> sendTypeList = CollectionsKt.arrayListOf(TuplesKt.m3968to("定频发射", 1), TuplesKt.m3968to("跳频发射", 2), TuplesKt.m3968to("仅接收", 3));
    private final ArrayList<Pair<String, Integer>> waveList = CollectionsKt.arrayListOf(TuplesKt.m3968to("调制波", 0), TuplesKt.m3968to("单载波", 1));
    private final ArrayList<Pair<String, Integer>> sendModelList = CollectionsKt.arrayListOf(TuplesKt.m3968to("收发可同时", 0), TuplesKt.m3968to("发射不接收", 1));
    private final MsgReceiveHandle.Listener listener = new LoRaApproveViewModel$listener$1(this);

    public LoRaApproveViewModel() {
        BuildersKt__Builders_commonKt.launch$default(getScope(), null, null, new C39551(null), 3, null);
        this.signalState = new MutableLiveData<>();
    }

    public final LoraCertificationParam getParam() {
        return this.param;
    }

    public final void setParam(LoraCertificationParam loraCertificationParam) {
        Intrinsics.checkParameterIsNotNull(loraCertificationParam, "<set-?>");
        this.param = loraCertificationParam;
    }

    public final ArrayList<Pair<String, Integer>> getSendTypeList() {
        return this.sendTypeList;
    }

    public final ArrayList<Pair<String, Integer>> getWaveList() {
        return this.waveList;
    }

    public final ArrayList<Pair<String, Integer>> getSendModelList() {
        return this.sendModelList;
    }

    public final MsgReceiveHandle.Listener getListener() {
        return this.listener;
    }

    /* compiled from: LoRaApproveViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudu.library.loracall.viewModel.LoRaApproveViewModel$1", m3970f = "LoRaApproveViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudu.library.loracall.viewModel.LoRaApproveViewModel$1 */
    /* loaded from: classes4.dex */
    static final class C39551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4389p$;

        C39551(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C39551 c39551 = new C39551(completion);
            c39551.f4389p$ = (CoroutineScope) obj;
            return c39551;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C39551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4389p$;
            LoRaClient companion = LoRaClient.INSTANCE.getInstance();
            String name = coroutineScope.getClass().getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "this::class.java.name");
            companion.addListener(name, LoRaApproveViewModel.this.getListener());
            return Unit.INSTANCE;
        }
    }

    public final void sendParam() {
        LoRaClient.INSTANCE.getInstance().sendApprove(this.param, new Function1<LoraApproveState, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoRaApproveViewModel$sendParam$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoraApproveState loraApproveState) {
                invoke2(loraApproveState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoraApproveState loraApproveState) {
                LoRaApproveViewModel loRaApproveViewModel;
                int i;
                if (loraApproveState == null) {
                    KotlinUtilsKt.showToast(KotlinUtilsKt.getString(LoRaApproveViewModel.this, C3965R.string.lora_send_fail));
                    return;
                }
                if (loraApproveState.getState() == 1) {
                    loRaApproveViewModel = LoRaApproveViewModel.this;
                    i = C3965R.string.lora_certification_model;
                } else {
                    loRaApproveViewModel = LoRaApproveViewModel.this;
                    i = C3965R.string.lora_no_certification_model;
                }
                KotlinUtilsKt.showToast(KotlinUtilsKt.getString(LoRaApproveViewModel.this, C3965R.string.lora_send_success_in) + KotlinUtilsKt.getString(loRaApproveViewModel, i));
            }
        });
    }

    public final MutableLiveData<LoraSignalStrength> getSignalState() {
        return this.signalState;
    }

    /* renamed from: getSignalState, reason: collision with other method in class */
    public final void m4289getSignalState() {
        LoRaClient.INSTANCE.getInstance().getSignalStrength(new Function1<LoraSignalStrength, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoRaApproveViewModel$getSignalState$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoraSignalStrength loraSignalStrength) {
                invoke2(loraSignalStrength);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: LoRaApproveViewModel.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudu.library.loracall.viewModel.LoRaApproveViewModel$getSignalState$1$1", m3970f = "LoRaApproveViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudu.library.loracall.viewModel.LoRaApproveViewModel$getSignalState$1$1 */
            /* loaded from: classes4.dex */
            public static final class C39561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LoraSignalStrength $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4390p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C39561(LoraSignalStrength loraSignalStrength, Continuation continuation) {
                    super(2, continuation);
                    this.$it = loraSignalStrength;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C39561 c39561 = new C39561(this.$it, completion);
                    c39561.f4390p$ = (CoroutineScope) obj;
                    return c39561;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C39561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4390p$;
                    LoRaApproveViewModel.this.getSignalState().setValue(this.$it);
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoraSignalStrength it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(LoRaApproveViewModel.this.getScope(), Dispatchers.getMain(), null, new C39561(it, null), 2, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudu.library.loracall.base.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        LoRaClient companion = LoRaClient.INSTANCE.getInstance();
        String name = getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "this::class.java.name");
        companion.removeListener(name);
    }
}
