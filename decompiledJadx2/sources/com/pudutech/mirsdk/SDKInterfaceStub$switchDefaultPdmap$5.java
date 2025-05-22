package com.pudutech.mirsdk;

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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$switchDefaultPdmap$5", m3970f = "SDKService.kt", m3971i = {0}, m3972l = {461}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class SDKInterfaceStub$switchDefaultPdmap$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.BooleanRef $waitCoreLoadMap;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5580p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKInterfaceStub$switchDefaultPdmap$5(Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.$waitCoreLoadMap = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$switchDefaultPdmap$5 sDKInterfaceStub$switchDefaultPdmap$5 = new SDKInterfaceStub$switchDefaultPdmap$5(this.$waitCoreLoadMap, completion);
        sDKInterfaceStub$switchDefaultPdmap$5.f5580p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$switchDefaultPdmap$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$switchDefaultPdmap$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5580p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope) && this.$waitCoreLoadMap.element) {
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(10L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        z = SDKInterfaceStub.reloadingMap;
        synchronized (Boxing.boxBoolean(z)) {
            SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
            SDKInterfaceStub.reloadingMap = false;
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
