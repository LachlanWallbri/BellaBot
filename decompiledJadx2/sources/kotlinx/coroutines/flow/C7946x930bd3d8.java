package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.InlineMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$$special$$inlined$collect$8"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7$lambda$1 */
/* loaded from: classes2.dex */
public final class C7946x930bd3d8<T> implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7 this$0;

    public C7946x930bd3d8(FlowCollector flowCollector, FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7 flowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.this$0 = flowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, Continuation continuation) {
        return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, obj, continuation);
    }

    public Object emit$$forInline(Object obj, Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7$lambda$1.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj2) {
                this.result = obj2;
                this.label |= Integer.MIN_VALUE;
                return C7946x930bd3d8.this.emit(null, this);
            }
        };
        InlineMarker.mark(5);
        return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, obj, continuation);
    }
}
