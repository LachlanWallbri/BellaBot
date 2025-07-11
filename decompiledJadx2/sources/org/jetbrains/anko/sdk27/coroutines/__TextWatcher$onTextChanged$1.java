package org.jetbrains.anko.sdk27.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
@DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/__TextWatcher$onTextChanged$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {99, 101}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes9.dex */
final class __TextWatcher$onTextChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $before;
    final /* synthetic */ int $count;
    final /* synthetic */ Function6 $handler;

    /* renamed from: $s */
    final /* synthetic */ CharSequence f10185$s;
    final /* synthetic */ int $start;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f10186p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public __TextWatcher$onTextChanged$1(Function6 function6, CharSequence charSequence, int i, int i2, int i3, Continuation continuation) {
        super(2, continuation);
        this.$handler = function6;
        this.f10185$s = charSequence;
        this.$start = i;
        this.$before = i2;
        this.$count = i3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        __TextWatcher$onTextChanged$1 __textwatcher_ontextchanged_1 = new __TextWatcher$onTextChanged$1(this.$handler, this.f10185$s, this.$start, this.$before, this.$count, completion);
        __textwatcher_ontextchanged_1.f10186p$ = (CoroutineScope) obj;
        return __textwatcher_ontextchanged_1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((__TextWatcher$onTextChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else {
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
            CoroutineScope coroutineScope = this.f10186p$;
            Function6 function6 = this.$handler;
            CharSequence charSequence = this.f10185$s;
            Integer boxInt = Boxing.boxInt(this.$start);
            Integer boxInt2 = Boxing.boxInt(this.$before);
            Integer boxInt3 = Boxing.boxInt(this.$count);
            this.label = 1;
            if (function6.invoke(coroutineScope, charSequence, boxInt, boxInt2, boxInt3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
