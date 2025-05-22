package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collect$4"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1<T> implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1 this$0;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\b"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collect$4$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1$1 */
    /* loaded from: classes2.dex */
    public static final class C78791 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C78791(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1.this.emit(null, this);
        }
    }

    public FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1 flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.this$0 = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Object obj, Continuation continuation) {
        C78791 c78791;
        Object obj2;
        int i;
        C78791 c787912;
        FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1<T> flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1;
        Object obj3;
        if (continuation instanceof C78791) {
            c78791 = (C78791) continuation;
            if ((c78791.label & Integer.MIN_VALUE) != 0) {
                c78791.label -= Integer.MIN_VALUE;
                obj2 = c78791.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78791.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    c787912 = c78791;
                    Function2 function2 = this.this$0.$predicate$inlined;
                    c78791.L$0 = this;
                    c78791.L$1 = obj;
                    c78791.L$2 = c787912;
                    c78791.L$3 = obj;
                    c78791.label = 1;
                    obj2 = function2.invoke(obj, c78791);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1 = this;
                    obj3 = obj;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            Object obj4 = c78791.L$3;
                            Object obj5 = c78791.L$1;
                            ResultKt.throwOnFailure(obj2);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    obj = c78791.L$3;
                    c787912 = (Continuation) c78791.L$2;
                    obj3 = c78791.L$1;
                    flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1 = (FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1) c78791.L$0;
                    ResultKt.throwOnFailure(obj2);
                }
                if (((Boolean) obj2).booleanValue()) {
                    throw new AbortFlowException(flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined);
                }
                FlowCollector flowCollector = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined;
                c78791.L$0 = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1;
                c78791.L$1 = obj3;
                c78791.L$2 = c787912;
                c78791.L$3 = obj;
                c78791.label = 2;
                if (flowCollector.emit(obj, c78791) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        c78791 = new C78791(continuation);
        obj2 = c78791.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78791.label;
        if (i != 0) {
        }
        if (((Boolean) obj2).booleanValue()) {
        }
    }
}
