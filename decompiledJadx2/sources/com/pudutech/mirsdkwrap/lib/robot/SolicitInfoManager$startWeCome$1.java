package com.pudutech.mirsdkwrap.lib.robot;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.IReceptionService;
import com.pudutech.mirsdk.aidl.SDKInterface;
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
/* compiled from: SolicitInfoManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$startWeCome$1", m3970f = "SolicitInfoManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class SolicitInfoManager$startWeCome$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6638p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SolicitInfoManager$startWeCome$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitInfoManager$startWeCome$1 solicitInfoManager$startWeCome$1 = new SolicitInfoManager$startWeCome$1(completion);
        solicitInfoManager$startWeCome$1.f6638p$ = (CoroutineScope) obj;
        return solicitInfoManager$startWeCome$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitInfoManager$startWeCome$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        SDKInterface sDKInterface;
        SDKInterface sDKInterface2;
        String str2;
        IReceptionService recepionService;
        IReceptionService recepionService2;
        SolicitInfoManager$welcomeListener$1 solicitInfoManager$welcomeListener$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6638p$;
        long currentTimeMillis = System.currentTimeMillis();
        SolicitInfoManager solicitInfoManager = SolicitInfoManager.INSTANCE;
        str = SolicitInfoManager.TAG;
        Pdlog.m3273d(str, "startWeCome = startWeCome = " + currentTimeMillis);
        SolicitInfoManager solicitInfoManager2 = SolicitInfoManager.INSTANCE;
        sDKInterface = SolicitInfoManager.mirSdk;
        if (sDKInterface != null && (recepionService2 = sDKInterface.getRecepionService()) != null) {
            SolicitInfoManager solicitInfoManager3 = SolicitInfoManager.INSTANCE;
            solicitInfoManager$welcomeListener$1 = SolicitInfoManager.welcomeListener;
            recepionService2.addWelcomeListener("welcome", solicitInfoManager$welcomeListener$1);
        }
        SolicitInfoManager solicitInfoManager4 = SolicitInfoManager.INSTANCE;
        sDKInterface2 = SolicitInfoManager.mirSdk;
        if (sDKInterface2 != null && (recepionService = sDKInterface2.getRecepionService()) != null) {
            recepionService.startWelcome();
        }
        SolicitInfoManager solicitInfoManager5 = SolicitInfoManager.INSTANCE;
        str2 = SolicitInfoManager.TAG;
        Pdlog.m3273d(str2, String.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return Unit.INSTANCE;
    }
}
