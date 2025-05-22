package com.pudutech.bumblebee.robot_ui.p054ui.view;

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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RecyclePlateArriveLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "cause", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$countDownCoroutines$3", m3970f = "RecyclePlateArriveLayout.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class RecyclePlateArriveLayout$countDownCoroutines$3 extends SuspendLambda implements Function3<FlowCollector<? super Integer>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $onFinish;
    int label;

    /* renamed from: p$ */
    private FlowCollector f4957p$;
    private Throwable p$0;
    final /* synthetic */ RecyclePlateArriveLayout this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclePlateArriveLayout$countDownCoroutines$3(RecyclePlateArriveLayout recyclePlateArriveLayout, Function0 function0, Continuation continuation) {
        super(3, continuation);
        this.this$0 = recyclePlateArriveLayout;
        this.$onFinish = function0;
    }

    public final Continuation<Unit> create(FlowCollector<? super Integer> create, Throwable th, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(create, "$this$create");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        RecyclePlateArriveLayout$countDownCoroutines$3 recyclePlateArriveLayout$countDownCoroutines$3 = new RecyclePlateArriveLayout$countDownCoroutines$3(this.this$0, this.$onFinish, continuation);
        recyclePlateArriveLayout$countDownCoroutines$3.f4957p$ = create;
        recyclePlateArriveLayout$countDownCoroutines$3.p$0 = th;
        return recyclePlateArriveLayout$countDownCoroutines$3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super Integer> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        return ((RecyclePlateArriveLayout$countDownCoroutines$3) create(flowCollector, th, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Function0 function0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector flowCollector = this.f4957p$;
        Throwable th = this.p$0;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "countDownCoroutines onCompletion");
        if (th == null && (function0 = this.$onFinish) != null) {
        }
        return Unit.INSTANCE;
    }
}
