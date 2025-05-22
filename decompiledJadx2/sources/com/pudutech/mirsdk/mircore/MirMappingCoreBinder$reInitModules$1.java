package com.pudutech.mirsdk.mircore;

import com.pudutech.base.Pdlog;
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

/* compiled from: MirMappingCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirMappingCoreBinder$reInitModules$1", m3970f = "MirMappingCoreBinder.kt", m3971i = {0}, m3972l = {48}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class MirMappingCoreBinder$reInitModules$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ InitMappingServiceListener $listener;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6179p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirMappingCoreBinder$reInitModules$1(InitMappingServiceListener initMappingServiceListener, Continuation continuation) {
        super(2, continuation);
        this.$listener = initMappingServiceListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirMappingCoreBinder$reInitModules$1 mirMappingCoreBinder$reInitModules$1 = new MirMappingCoreBinder$reInitModules$1(this.$listener, completion);
        mirMappingCoreBinder$reInitModules$1.f6179p$ = (CoroutineScope) obj;
        return mirMappingCoreBinder$reInitModules$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirMappingCoreBinder$reInitModules$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6179p$;
            MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
            InitMappingServiceListener initMappingServiceListener = this.$listener;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (mirMappingCoreImpl.reInitModules(initMappingServiceListener, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        MirMappingCoreBinder mirMappingCoreBinder = MirMappingCoreBinder.INSTANCE;
        MirMappingCoreBinder.reInitJob = (Job) null;
        MirMappingCoreBinder mirMappingCoreBinder2 = MirMappingCoreBinder.INSTANCE;
        str = MirMappingCoreBinder.TAG;
        Pdlog.m3273d(str, "reInitModules set reInitJob null");
        return Unit.INSTANCE;
    }
}
