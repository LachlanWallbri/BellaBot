package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.internal.SafeCollector;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Builders.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B7\u0012-\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tø\u0001\u0000¢\u0006\u0002\u0010\nJ\u001f\u0010\f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR:\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m3961d2 = {"Lkotlinx/coroutines/flow/SafeFlow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "collect", "collector", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SafeFlow<T> implements Flow<T> {
    private final Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> block;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeFlow(Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.block = function2;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        SafeFlow$collect$1 safeFlow$collect$1;
        int i;
        SafeCollector safeCollector;
        if (continuation instanceof SafeFlow$collect$1) {
            safeFlow$collect$1 = (SafeFlow$collect$1) continuation;
            if ((safeFlow$collect$1.label & Integer.MIN_VALUE) != 0) {
                safeFlow$collect$1.label -= Integer.MIN_VALUE;
                Object obj = safeFlow$collect$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = safeFlow$collect$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeCollector safeCollector2 = new SafeCollector(flowCollector, safeFlow$collect$1.getContext());
                    try {
                        Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> function2 = this.block;
                        safeFlow$collect$1.L$0 = this;
                        safeFlow$collect$1.L$1 = flowCollector;
                        safeFlow$collect$1.L$2 = safeCollector2;
                        safeFlow$collect$1.label = 1;
                        if (function2.invoke(safeCollector2, safeFlow$collect$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        safeCollector = safeCollector2;
                    } catch (Throwable th) {
                        th = th;
                        safeCollector = safeCollector2;
                        safeCollector.releaseIntercepted();
                        throw th;
                    }
                } else if (i == 1) {
                    safeCollector = (SafeCollector) safeFlow$collect$1.L$2;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th2) {
                        th = th2;
                        safeCollector.releaseIntercepted();
                        throw th;
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                safeCollector.releaseIntercepted();
                return Unit.INSTANCE;
            }
        }
        safeFlow$collect$1 = new SafeFlow$collect$1(this, continuation);
        Object obj2 = safeFlow$collect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = safeFlow$collect$1.label;
        if (i != 0) {
        }
        safeCollector.releaseIntercepted();
        return Unit.INSTANCE;
    }
}
