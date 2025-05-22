package com.pudutech.mirsdkwrap.lib;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
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
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSdkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.MirSdkManager$connectService$3", m3970f = "MirSdkManager.kt", m3971i = {0}, m3972l = {130}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class MirSdkManager$connectService$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6466p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSdkManager$connectService$3(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSdkManager$connectService$3 mirSdkManager$connectService$3 = new MirSdkManager$connectService$3(completion);
        mirSdkManager$connectService$3.f6466p$ = (CoroutineScope) obj;
        return mirSdkManager$connectService$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSdkManager$connectService$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6466p$;
            MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
            aIDLConnection = MirSdkManager.mirsdkConnection;
            if (aIDLConnection != null) {
                Context access$getContext$p = MirSdkManager.access$getContext$p(MirSdkManager.INSTANCE);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = AIDLConnection.connect$default(aIDLConnection, access$getContext$p, null, this, 2, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
            str = MirSdkManager.TAG;
            Pdlog.m3273d(str, "mirsdkConnection.connect()");
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        MirSdkManager mirSdkManager22 = MirSdkManager.INSTANCE;
        str = MirSdkManager.TAG;
        Pdlog.m3273d(str, "mirsdkConnection.connect()");
        return Unit.INSTANCE;
    }
}
