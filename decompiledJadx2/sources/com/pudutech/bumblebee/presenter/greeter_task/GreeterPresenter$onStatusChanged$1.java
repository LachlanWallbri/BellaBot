package com.pudutech.bumblebee.presenter.greeter_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.greeter_task.GreeterContract;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: GreeterPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$onStatusChanged$1", m3970f = "GreeterPresenter.kt", m3971i = {0}, m3972l = {136}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class GreeterPresenter$onStatusChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4654p$;
    final /* synthetic */ GreeterPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GreeterPresenter$onStatusChanged$1(GreeterPresenter greeterPresenter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = greeterPresenter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GreeterPresenter$onStatusChanged$1 greeterPresenter$onStatusChanged$1 = new GreeterPresenter$onStatusChanged$1(this.this$0, completion);
        greeterPresenter$onStatusChanged$1.f4654p$ = (CoroutineScope) obj;
        return greeterPresenter$onStatusChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GreeterPresenter$onStatusChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4654p$;
            long autoBackOrNextSize = BusinessSetting.INSTANCE.getAutoBackOrNextSize();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(autoBackOrNextSize, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Pdlog.m3275i(this.this$0.getTAG(), "delay timeout");
        this.this$0.showEvent(GreeterContract.ViewEvent.ARRIVAL_DESTINATION_COUNTDOWN_DONE);
        this.this$0.actionUsherDone();
        return Unit.INSTANCE;
    }
}
