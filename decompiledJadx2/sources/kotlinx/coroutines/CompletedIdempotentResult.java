package kotlinx.coroutines;

import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: CancellableContinuationImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lkotlinx/coroutines/CompletedIdempotentResult;", "", "idempotentResume", SpeechUtility.TAG_RESOURCE_RESULT, "(Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class CompletedIdempotentResult {
    public final Object idempotentResume;
    public final Object result;

    public CompletedIdempotentResult(Object obj, Object obj2) {
        this.idempotentResume = obj;
        this.result = obj2;
    }

    public String toString() {
        return "CompletedIdempotentResult[" + this.result + ']';
    }
}
