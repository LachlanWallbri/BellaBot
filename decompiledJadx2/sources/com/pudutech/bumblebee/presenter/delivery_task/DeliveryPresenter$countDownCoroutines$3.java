package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: DeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "cause", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$countDownCoroutines$3", m3970f = "DeliveryPresenter.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class DeliveryPresenter$countDownCoroutines$3 extends SuspendLambda implements Function3<FlowCollector<? super Integer>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $onFinish;
    int label;

    /* renamed from: p$ */
    private FlowCollector f4651p$;
    private Throwable p$0;
    final /* synthetic */ DeliveryPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliveryPresenter$countDownCoroutines$3(DeliveryPresenter deliveryPresenter, Function0 function0, Continuation continuation) {
        super(3, continuation);
        this.this$0 = deliveryPresenter;
        this.$onFinish = function0;
    }

    public final Continuation<Unit> create(FlowCollector<? super Integer> create, Throwable th, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(create, "$this$create");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        DeliveryPresenter$countDownCoroutines$3 deliveryPresenter$countDownCoroutines$3 = new DeliveryPresenter$countDownCoroutines$3(this.this$0, this.$onFinish, continuation);
        deliveryPresenter$countDownCoroutines$3.f4651p$ = create;
        deliveryPresenter$countDownCoroutines$3.p$0 = th;
        return deliveryPresenter$countDownCoroutines$3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super Integer> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        return ((DeliveryPresenter$countDownCoroutines$3) create(flowCollector, th, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function0 function0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector flowCollector = this.f4651p$;
        Throwable th = this.p$0;
        Pdlog.m3275i(this.this$0.getTAG(), "countDownCoroutines onCompletion");
        if (th == null && (function0 = this.$onFinish) != null) {
        }
        return Unit.INSTANCE;
    }
}
