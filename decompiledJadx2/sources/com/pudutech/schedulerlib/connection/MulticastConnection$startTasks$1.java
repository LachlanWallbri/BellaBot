package com.pudutech.schedulerlib.connection;

import com.pudutech.schedulerlib.connection.MulticastConnection;
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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: MulticastConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$startTasks$1", m3970f = "MulticastConnection.kt", m3971i = {0, 0}, m3972l = {242}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "num"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes2.dex */
public final class MulticastConnection$startTasks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7442p$;
    final /* synthetic */ MulticastConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MulticastConnection$startTasks$1(MulticastConnection multicastConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicastConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MulticastConnection$startTasks$1 multicastConnection$startTasks$1 = new MulticastConnection$startTasks$1(this.this$0, completion);
        multicastConnection$startTasks$1.f7442p$ = (CoroutineScope) obj;
        return multicastConnection$startTasks$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MulticastConnection$startTasks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0041 -> B:5:0x0044). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineScope coroutineScope;
        MulticastConnection$startTasks$1 multicastConnection$startTasks$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 3;
            coroutineScope = this.f7442p$;
            multicastConnection$startTasks$1 = this;
            if (i > 0) {
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            multicastConnection$startTasks$1 = this;
            i--;
            if (i > 0) {
                MulticastConnection.sendMsg$default(multicastConnection$startTasks$1.this$0, MulticastConnection.MSGID.ONLINE.ordinal(), null, 2, null);
                multicastConnection$startTasks$1.L$0 = coroutineScope;
                multicastConnection$startTasks$1.I$0 = i;
                multicastConnection$startTasks$1.label = 1;
                if (DelayKt.delay(100L, multicastConnection$startTasks$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i--;
                if (i > 0) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
