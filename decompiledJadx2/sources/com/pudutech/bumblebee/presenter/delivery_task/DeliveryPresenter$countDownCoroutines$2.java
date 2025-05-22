package com.pudutech.bumblebee.presenter.delivery_task;

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

/* compiled from: DeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$countDownCoroutines$2", m3970f = "DeliveryPresenter.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class DeliveryPresenter$countDownCoroutines$2 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $onTick;
    int label;
    private int p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliveryPresenter$countDownCoroutines$2(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$onTick = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DeliveryPresenter$countDownCoroutines$2 deliveryPresenter$countDownCoroutines$2 = new DeliveryPresenter$countDownCoroutines$2(this.$onTick, completion);
        Number number = (Number) obj;
        number.intValue();
        deliveryPresenter$countDownCoroutines$2.p$0 = number.intValue();
        return deliveryPresenter$countDownCoroutines$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Integer num, Continuation<? super Unit> continuation) {
        return ((DeliveryPresenter$countDownCoroutines$2) create(num, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$onTick.invoke(Boxing.boxInt(this.p$0));
        return Unit.INSTANCE;
    }
}
