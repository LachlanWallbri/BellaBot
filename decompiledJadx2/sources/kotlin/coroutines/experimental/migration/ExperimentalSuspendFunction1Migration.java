package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: CoroutinesMigration.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u001c\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003B'\u0012 \u0010\u0006\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\bJ&\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00028\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H\u0096\u0002¢\u0006\u0002\u0010\u000eR+\u0010\u0006\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, m3961d2 = {"Lkotlin/coroutines/experimental/migration/ExperimentalSuspendFunction1Migration;", "T1", "R", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "", "function", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;)V", "getFunction", "()Lkotlin/jvm/functions/Function2;", "invoke", "t1", "continuation", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib-coroutines"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class ExperimentalSuspendFunction1Migration<T1, R> implements Function2<T1, Continuation<? super R>, Object> {
    private final Function2<T1, kotlin.coroutines.Continuation<? super R>, Object> function;

    /* JADX WARN: Multi-variable type inference failed */
    public ExperimentalSuspendFunction1Migration(Function2<? super T1, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.function = function;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((ExperimentalSuspendFunction1Migration<T1, R>) obj, (Continuation) obj2);
    }

    public final Function2<T1, kotlin.coroutines.Continuation<? super R>, Object> getFunction() {
        return this.function;
    }

    public Object invoke(T1 t1, Continuation<? super R> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return this.function.invoke(t1, CoroutinesMigrationKt.toContinuation(continuation));
    }
}
