package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveErrorHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper$cancelAutoFix$1", m3970f = "MoveErrorHelper.kt", m3971i = {0, 0, 0, 0}, m3972l = {186}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$forEach$iv", "element$iv", "it"}, m3975s = {"L$0", "L$1", "L$3", "L$4"})
/* loaded from: classes6.dex */
public final class MoveErrorHelper$cancelAutoFix$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6606p$;
    final /* synthetic */ MoveErrorHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveErrorHelper$cancelAutoFix$1(MoveErrorHelper moveErrorHelper, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveErrorHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveErrorHelper$cancelAutoFix$1 moveErrorHelper$cancelAutoFix$1 = new MoveErrorHelper$cancelAutoFix$1(this.this$0, completion);
        moveErrorHelper$cancelAutoFix$1.f6606p$ = (CoroutineScope) obj;
        return moveErrorHelper$cancelAutoFix$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveErrorHelper$cancelAutoFix$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        HashMap hashMap;
        CoroutineScope coroutineScope;
        MoveErrorHelper$cancelAutoFix$1 moveErrorHelper$cancelAutoFix$1;
        Map map;
        Iterator it;
        String str2;
        HashMap hashMap2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6606p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "cancelAutoFix cancelAndJoin start");
            hashMap = this.this$0.autoFixMap;
            HashMap hashMap3 = hashMap;
            coroutineScope = coroutineScope2;
            moveErrorHelper$cancelAutoFix$1 = this;
            map = hashMap3;
            it = hashMap3.entrySet().iterator();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) this.L$2;
            map = (Map) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            moveErrorHelper$cancelAutoFix$1 = this;
        }
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Job job = (Job) entry.getValue();
            moveErrorHelper$cancelAutoFix$1.L$0 = coroutineScope;
            moveErrorHelper$cancelAutoFix$1.L$1 = map;
            moveErrorHelper$cancelAutoFix$1.L$2 = it;
            moveErrorHelper$cancelAutoFix$1.L$3 = entry;
            moveErrorHelper$cancelAutoFix$1.L$4 = entry;
            moveErrorHelper$cancelAutoFix$1.label = 1;
            if (JobKt.cancelAndJoin(job, moveErrorHelper$cancelAutoFix$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        str2 = moveErrorHelper$cancelAutoFix$1.this$0.TAG;
        Pdlog.m3273d(str2, "cancelAutoFix cancelAndJoin end");
        hashMap2 = moveErrorHelper$cancelAutoFix$1.this$0.autoFixMap;
        hashMap2.clear();
        return Unit.INSTANCE;
    }
}
