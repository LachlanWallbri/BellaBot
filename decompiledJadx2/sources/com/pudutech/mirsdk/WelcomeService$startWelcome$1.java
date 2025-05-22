package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WelcomeService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.WelcomeService$startWelcome$1", m3970f = "WelcomeService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class WelcomeService$startWelcome$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5602p$;
    final /* synthetic */ WelcomeService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WelcomeService$startWelcome$1(WelcomeService welcomeService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = welcomeService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        WelcomeService$startWelcome$1 welcomeService$startWelcome$1 = new WelcomeService$startWelcome$1(this.this$0, completion);
        welcomeService$startWelcome$1.f5602p$ = (CoroutineScope) obj;
        return welcomeService$startWelcome$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WelcomeService$startWelcome$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        MirCoreInterface mirCoreInterface;
        MirCoreInterface mirCoreInterface2;
        WelcomeService$mPersonListener$1 welcomeService$mPersonListener$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5602p$;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "startWelcome  enter ");
        aIDLConnection = this.this$0.coreService;
        if (aIDLConnection != null && (mirCoreInterface2 = (MirCoreInterface) aIDLConnection.getInterface()) != null) {
            welcomeService$mPersonListener$1 = this.this$0.mPersonListener;
            mirCoreInterface2.addPersonListener("welcomePersonDetect", welcomeService$mPersonListener$1);
        }
        aIDLConnection2 = this.this$0.coreService;
        if (aIDLConnection2 != null && (mirCoreInterface = (MirCoreInterface) aIDLConnection2.getInterface()) != null) {
            mirCoreInterface.enablePersonDetect(true);
        }
        return Unit.INSTANCE;
    }
}
