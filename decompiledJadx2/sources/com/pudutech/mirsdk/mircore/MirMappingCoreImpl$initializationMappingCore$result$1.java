package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MirMappingCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializationMappingCore$result$1", m3970f = "MirMappingCoreImpl.kt", m3971i = {0}, m3972l = {27}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class MirMappingCoreImpl$initializationMappingCore$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6180p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirMappingCoreImpl$initializationMappingCore$result$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirMappingCoreImpl$initializationMappingCore$result$1 mirMappingCoreImpl$initializationMappingCore$result$1 = new MirMappingCoreImpl$initializationMappingCore$result$1(completion);
        mirMappingCoreImpl$initializationMappingCore$result$1.f6180p$ = (CoroutineScope) obj;
        return mirMappingCoreImpl$initializationMappingCore$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MirMappingCoreImpl$initializationMappingCore$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        HardwareLinker hardwareLinker;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6180p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
            hardwareLinker = MirMappingCoreImpl.hardwareLinker;
            if (!hardwareLinker.getConnectStatus()) {
                this.L$0 = coroutineScope;
                this.label = 1;
            } else {
                return Boxing.boxBoolean(true);
            }
        } while (DelayKt.delay(50L, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
