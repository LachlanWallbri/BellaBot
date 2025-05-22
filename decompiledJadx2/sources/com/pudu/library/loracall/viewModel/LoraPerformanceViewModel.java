package com.pudu.library.loracall.viewModel;

import androidx.lifecycle.MutableLiveData;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.LoraSignalStrength;
import com.pudu.library.loracall.MsgReceiveHandle;
import com.pudu.library.loracall.base.BaseViewModel;
import com.pudu.library.loracall.bean.TestPerformanceParam;
import com.pudu.loracall.library.C3965R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
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

/* compiled from: LoraPerformanceViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\u0006\u0010\u001a\u001a\u00020\u0018R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, m3961d2 = {"Lcom/pudu/library/loracall/viewModel/LoraPerformanceViewModel;", "Lcom/pudu/library/loracall/base/BaseViewModel;", "()V", "listener", "Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "getListener", "()Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "openMode", "", "getOpenMode", "()I", "setOpenMode", "(I)V", "rfModule", "", "getRfModule", "()Ljava/lang/String;", "setRfModule", "(Ljava/lang/String;)V", "signalState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudu/library/loracall/LoraSignalStrength;", "getSignalState", "()Landroidx/lifecycle/MutableLiveData;", "", "onCleared", "test", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoraPerformanceViewModel extends BaseViewModel {
    private int openMode;
    private final MutableLiveData<LoraSignalStrength> signalState;
    private String rfModule = "0";
    private final MsgReceiveHandle.Listener listener = new LoraPerformanceViewModel$listener$1(this);

    public LoraPerformanceViewModel() {
        BuildersKt__Builders_commonKt.launch$default(getScope(), null, null, new C39621(null), 3, null);
        this.signalState = new MutableLiveData<>();
    }

    public final int getOpenMode() {
        return this.openMode;
    }

    public final void setOpenMode(int i) {
        this.openMode = i;
    }

    public final String getRfModule() {
        return this.rfModule;
    }

    public final void setRfModule(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.rfModule = str;
    }

    public final MsgReceiveHandle.Listener getListener() {
        return this.listener;
    }

    /* compiled from: LoraPerformanceViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudu.library.loracall.viewModel.LoraPerformanceViewModel$1", m3970f = "LoraPerformanceViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudu.library.loracall.viewModel.LoraPerformanceViewModel$1 */
    /* loaded from: classes4.dex */
    static final class C39621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4394p$;

        C39621(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C39621 c39621 = new C39621(completion);
            c39621.f4394p$ = (CoroutineScope) obj;
            return c39621;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C39621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4394p$;
            LoRaClient companion = LoRaClient.INSTANCE.getInstance();
            String name = coroutineScope.getClass().getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "this::class.java.name");
            companion.addListener(name, LoraPerformanceViewModel.this.getListener());
            return Unit.INSTANCE;
        }
    }

    public final void test() {
        int parseInt = Integer.parseInt(this.rfModule);
        if (parseInt < 0 || parseInt > 255) {
            KotlinUtilsKt.showToast("请在正确范围填写射频模块数");
        } else {
            LoRaClient.INSTANCE.getInstance().testPerformance(new TestPerformanceParam((byte) parseInt, this.openMode == 1), new Function1<Boolean, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoraPerformanceViewModel$test$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        KotlinUtilsKt.showToast(KotlinUtilsKt.getString(LoraPerformanceViewModel.this, C3965R.string.lora_send_success_in) + (LoraPerformanceViewModel.this.getOpenMode() == 1 ? "性能测试" : "非性能测试"));
                        return;
                    }
                    KotlinUtilsKt.showToast(KotlinUtilsKt.getString(LoraPerformanceViewModel.this, C3965R.string.lora_send_fail));
                }
            });
        }
    }

    public final MutableLiveData<LoraSignalStrength> getSignalState() {
        return this.signalState;
    }

    /* renamed from: getSignalState, reason: collision with other method in class */
    public final void m4292getSignalState() {
        LoRaClient.INSTANCE.getInstance().getSignalStrength(new Function1<LoraSignalStrength, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoraPerformanceViewModel$getSignalState$1
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
            /* compiled from: LoraPerformanceViewModel.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudu.library.loracall.viewModel.LoraPerformanceViewModel$getSignalState$1$1", m3970f = "LoraPerformanceViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudu.library.loracall.viewModel.LoraPerformanceViewModel$getSignalState$1$1 */
            /* loaded from: classes4.dex */
            public static final class C39631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LoraSignalStrength $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4395p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C39631(LoraSignalStrength loraSignalStrength, Continuation continuation) {
                    super(2, continuation);
                    this.$it = loraSignalStrength;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C39631 c39631 = new C39631(this.$it, completion);
                    c39631.f4395p$ = (CoroutineScope) obj;
                    return c39631;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C39631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4395p$;
                    LoraPerformanceViewModel.this.getSignalState().setValue(this.$it);
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoraSignalStrength it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(LoraPerformanceViewModel.this.getScope(), Dispatchers.getMain(), null, new C39631(it, null), 2, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudu.library.loracall.base.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        LoRaClient companion = LoRaClient.INSTANCE.getInstance();
        String name = getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "this::class.java.name");
        companion.removeListener(name);
        this.openMode = 0;
        test();
        super.onCleared();
    }
}
