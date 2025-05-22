package com.pudutech.mirsdk.mircore;

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
import kotlinx.coroutines.Job;

/* compiled from: MirCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreBinder$loadLocateMap$1", m3970f = "MirCoreBinder.kt", m3971i = {0}, m3972l = {120}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class MirCoreBinder$loadLocateMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ InitServiceListener $listener;
    final /* synthetic */ String $pdmap;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6160p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirCoreBinder$loadLocateMap$1(String str, InitServiceListener initServiceListener, Continuation continuation) {
        super(2, continuation);
        this.$pdmap = str;
        this.$listener = initServiceListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirCoreBinder$loadLocateMap$1 mirCoreBinder$loadLocateMap$1 = new MirCoreBinder$loadLocateMap$1(this.$pdmap, this.$listener, completion);
        mirCoreBinder$loadLocateMap$1.f6160p$ = (CoroutineScope) obj;
        return mirCoreBinder$loadLocateMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirCoreBinder$loadLocateMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6160p$;
            MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
            String str = this.$pdmap;
            InitServiceListener initServiceListener = this.$listener;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (mirCoreImpl.initLoadLocateMap(str, initServiceListener, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        MirCoreBinder.access$setLoadLocateJob$p(MirCoreBinder.INSTANCE, (Job) null);
        return Unit.INSTANCE;
    }
}
