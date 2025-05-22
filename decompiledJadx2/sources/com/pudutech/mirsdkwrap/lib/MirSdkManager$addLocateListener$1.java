package com.pudutech.mirsdkwrap.lib;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.mapify.LoadCoreListener;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.MirSdkManager$addLocateListener$1", m3970f = "MirSdkManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class MirSdkManager$addLocateListener$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadCoreListener $listener;
    final /* synthetic */ String $name;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6465p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirSdkManager$addLocateListener$1(String str, LoadCoreListener loadCoreListener, Continuation continuation) {
        super(2, continuation);
        this.$name = str;
        this.$listener = loadCoreListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSdkManager$addLocateListener$1 mirSdkManager$addLocateListener$1 = new MirSdkManager$addLocateListener$1(this.$name, this.$listener, completion);
        mirSdkManager$addLocateListener$1.f6465p$ = (CoroutineScope) obj;
        return mirSdkManager$addLocateListener$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSdkManager$addLocateListener$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        String str;
        String str2;
        String str3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6465p$;
        MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
        aIDLConnection = MirSdkManager.mirsdkConnection;
        if (aIDLConnection == null) {
            MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
            str3 = MirSdkManager.TAG;
            Pdlog.m3274e(str3, "addLocateListener mirsdkConnection is null");
            return Unit.INSTANCE;
        }
        MirSdkManager mirSdkManager3 = MirSdkManager.INSTANCE;
        MirSdkManager mirSdkManager4 = MirSdkManager.INSTANCE;
        aIDLConnection2 = MirSdkManager.mirsdkConnection;
        MirSdkManager.access$setMirSdk$p(mirSdkManager3, aIDLConnection2 != null ? (SDKInterface) aIDLConnection2.getInterface() : null);
        if (MirSdkManager.access$getMirSdk$p(MirSdkManager.INSTANCE) == null) {
            MirSdkManager mirSdkManager5 = MirSdkManager.INSTANCE;
            str2 = MirSdkManager.TAG;
            Pdlog.m3274e(str2, "addLocateListener mirSdk is null");
            return Unit.INSTANCE;
        }
        MirSdkManager mirSdkManager6 = MirSdkManager.INSTANCE;
        str = MirSdkManager.TAG;
        Pdlog.m3274e(str, "addLocateListener mirSdk ");
        SDKInterface access$getMirSdk$p = MirSdkManager.access$getMirSdk$p(MirSdkManager.INSTANCE);
        if (access$getMirSdk$p != null) {
            access$getMirSdk$p.addLoadCoreListener(this.$name, this.$listener);
        }
        return Unit.INSTANCE;
    }
}
