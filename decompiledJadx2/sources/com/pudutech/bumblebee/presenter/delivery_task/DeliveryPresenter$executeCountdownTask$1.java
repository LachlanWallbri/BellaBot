package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$executeCountdownTask$1", m3970f = "DeliveryPresenter.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class DeliveryPresenter$executeCountdownTask$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $seconds;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4652p$;
    final /* synthetic */ DeliveryPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliveryPresenter$executeCountdownTask$1(DeliveryPresenter deliveryPresenter, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deliveryPresenter;
        this.$seconds = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DeliveryPresenter$executeCountdownTask$1 deliveryPresenter$executeCountdownTask$1 = new DeliveryPresenter$executeCountdownTask$1(this.this$0, this.$seconds, completion);
        deliveryPresenter$executeCountdownTask$1.f4652p$ = (CoroutineScope) obj;
        return deliveryPresenter$executeCountdownTask$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DeliveryPresenter$executeCountdownTask$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        Job countDownCoroutines;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4652p$;
        job = this.this$0.countdownJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        DeliveryPresenter deliveryPresenter = this.this$0;
        countDownCoroutines = deliveryPresenter.countDownCoroutines((int) this.$seconds, coroutineScope, new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$executeCountdownTask$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                DeliveryContract.ViewInterface theView;
                long j = i;
                DeliveryPresenter$executeCountdownTask$1.this.this$0.countdownMillis = j;
                theView = DeliveryPresenter$executeCountdownTask$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showCountdownFinish(j);
                }
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$executeCountdownTask$1.2
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
                theView = DeliveryPresenter$executeCountdownTask$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showDelayAutoFinish();
                }
            }
        });
        deliveryPresenter.countdownJob = countDownCoroutines;
        return Unit.INSTANCE;
    }
}
