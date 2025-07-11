package kotlin.coroutines.jvm.internal;

import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: RunSuspend.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u0002J\u001e\u0010\u000f\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R%\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m3961d2 = {"Lkotlin/coroutines/jvm/internal/RunSuspend;", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", SpeechUtility.TAG_RESOURCE_RESULT, "Lkotlin/Result;", "getResult", "()Lkotlin/Result;", "setResult", "(Lkotlin/Result;)V", "await", "resumeWith", "(Ljava/lang/Object;)V", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class RunSuspend implements Continuation<Unit> {
    private Result<Unit> result;

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    public final Result<Unit> getResult() {
        return this.result;
    }

    public final void setResult(Result<Unit> result) {
        this.result = result;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        synchronized (this) {
            this.result = Result.m4509boximpl(result);
            notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void await() {
        synchronized (this) {
            while (true) {
                Result<Unit> result = this.result;
                if (result == null) {
                    wait();
                } else {
                    ResultKt.throwOnFailure(result.getValue());
                }
            }
        }
    }
}
