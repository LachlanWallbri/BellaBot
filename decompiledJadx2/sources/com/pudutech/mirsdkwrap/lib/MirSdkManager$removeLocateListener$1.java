package com.pudutech.mirsdkwrap.lib;

import com.pudutech.base.architecture.AIDLConnection;
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

/* compiled from: MirSdkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.MirSdkManager$removeLocateListener$1", m3970f = "MirSdkManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class MirSdkManager$removeLocateListener$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $name;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6468p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirSdkManager$removeLocateListener$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$name = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSdkManager$removeLocateListener$1 mirSdkManager$removeLocateListener$1 = new MirSdkManager$removeLocateListener$1(this.$name, completion);
        mirSdkManager$removeLocateListener$1.f6468p$ = (CoroutineScope) obj;
        return mirSdkManager$removeLocateListener$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSdkManager$removeLocateListener$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6468p$;
        MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
        MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
        aIDLConnection = MirSdkManager.mirsdkConnection;
        MirSdkManager.access$setMirSdk$p(mirSdkManager, aIDLConnection != null ? (SDKInterface) aIDLConnection.getInterface() : null);
        SDKInterface access$getMirSdk$p = MirSdkManager.access$getMirSdk$p(MirSdkManager.INSTANCE);
        if (access$getMirSdk$p != null) {
            access$getMirSdk$p.removeLoadCoreListener(this.$name);
        }
        return Unit.INSTANCE;
    }
}
