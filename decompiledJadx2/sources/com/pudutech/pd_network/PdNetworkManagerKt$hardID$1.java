package com.pudutech.pd_network;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PdNetworkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.PdNetworkManagerKt$hardID$1", m3970f = "PdNetworkManager.kt", m3971i = {0}, m3972l = {188}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class PdNetworkManagerKt$hardID$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ IPdNetworkManager $this_hardID;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6834p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PdNetworkManagerKt$hardID$1(IPdNetworkManager iPdNetworkManager, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$this_hardID = iPdNetworkManager;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PdNetworkManagerKt$hardID$1 pdNetworkManagerKt$hardID$1 = new PdNetworkManagerKt$hardID$1(this.$this_hardID, this.$block, completion);
        pdNetworkManagerKt$hardID$1.f6834p$ = (CoroutineScope) obj;
        return pdNetworkManagerKt$hardID$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PdNetworkManagerKt$hardID$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function1 function1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6834p$;
            Function1 function12 = this.$block;
            IPdNetworkManager iPdNetworkManager = this.$this_hardID;
            this.L$0 = coroutineScope;
            this.L$1 = function12;
            this.label = 1;
            obj = iPdNetworkManager.hardID(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            function1 = function12;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function1 = (Function1) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        function1.invoke(obj);
        return Unit.INSTANCE;
    }
}
