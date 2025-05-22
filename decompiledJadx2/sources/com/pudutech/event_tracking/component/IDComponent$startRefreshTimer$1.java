package com.pudutech.event_tracking.component;

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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IDComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.event_tracking.component.IDComponent$startRefreshTimer$1", m3970f = "IDComponent.kt", m3971i = {0}, m3972l = {38}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class IDComponent$startRefreshTimer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5119p$;
    final /* synthetic */ IDComponent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IDComponent$startRefreshTimer$1(IDComponent iDComponent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = iDComponent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        IDComponent$startRefreshTimer$1 iDComponent$startRefreshTimer$1 = new IDComponent$startRefreshTimer$1(this.this$0, completion);
        iDComponent$startRefreshTimer$1.f5119p$ = (CoroutineScope) obj;
        return iDComponent$startRefreshTimer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((IDComponent$startRefreshTimer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0033  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0029 -> B:6:0x002b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x003d -> B:5:0x0040). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        IDComponent$startRefreshTimer$1 iDComponent$startRefreshTimer$1;
        int i;
        int i2;
        int i3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5119p$;
            obj2 = coroutine_suspended;
            iDComponent$startRefreshTimer$1 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
            i = iDComponent$startRefreshTimer$1.this$0.currentRefreshCount;
            if (i <= 0) {
            }
        } else if (i4 == 1) {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope2;
            obj2 = coroutine_suspended;
            iDComponent$startRefreshTimer$1 = this;
            IDComponent iDComponent = iDComponent$startRefreshTimer$1.this$0;
            i3 = iDComponent.currentRefreshCount;
            iDComponent.currentRefreshCount = i3 - 1;
            i = iDComponent$startRefreshTimer$1.this$0.currentRefreshCount;
            if (i <= 0) {
                iDComponent$startRefreshTimer$1.L$0 = coroutineScope;
                iDComponent$startRefreshTimer$1.label = 1;
                if (DelayKt.delay(1000L, iDComponent$startRefreshTimer$1) == obj2) {
                    return obj2;
                }
                IDComponent iDComponent2 = iDComponent$startRefreshTimer$1.this$0;
                i3 = iDComponent2.currentRefreshCount;
                iDComponent2.currentRefreshCount = i3 - 1;
                i = iDComponent$startRefreshTimer$1.this$0.currentRefreshCount;
                if (i <= 0) {
                    iDComponent$startRefreshTimer$1.this$0.sessionId = iDComponent$startRefreshTimer$1.this$0.getBaseId() + '_' + System.currentTimeMillis();
                    IDComponent iDComponent3 = iDComponent$startRefreshTimer$1.this$0;
                    i2 = iDComponent3.BASE_REFRESH_COUNT;
                    iDComponent3.currentRefreshCount = i2;
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                    i = iDComponent$startRefreshTimer$1.this$0.currentRefreshCount;
                    if (i <= 0) {
                    }
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
