package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$$special$$inlined$collect$9"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1<T> implements FlowCollector<T> {
    final /* synthetic */ Ref.ObjectRef $accumulator$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__TransformKt$scan$$inlined$unsafeFlow$1 this$0;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\b"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$$special$$inlined$collect$9$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1$1 */
    /* loaded from: classes2.dex */
    public static final class C79311 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C79311(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1.this.emit(null, this);
        }
    }

    public FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Ref.ObjectRef objectRef, FlowKt__TransformKt$scan$$inlined$unsafeFlow$1 flowKt__TransformKt$scan$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$accumulator$inlined = objectRef;
        this.this$0 = flowKt__TransformKt$scan$$inlined$unsafeFlow$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Object obj, Continuation continuation) {
        C79311 c79311;
        Object coroutine_suspended;
        int i;
        FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1<T> flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1;
        Object obj2;
        Ref.ObjectRef objectRef;
        Object obj3;
        Continuation continuation2;
        FlowCollector flowCollector;
        T t;
        if (continuation instanceof C79311) {
            c79311 = (C79311) continuation;
            if ((c79311.label & Integer.MIN_VALUE) != 0) {
                c79311.label -= Integer.MIN_VALUE;
                T t2 = (T) c79311.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c79311.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(t2);
                    C79311 c793112 = c79311;
                    Ref.ObjectRef objectRef2 = this.$accumulator$inlined;
                    Function3 function3 = this.this$0.$operation$inlined;
                    T t3 = this.$accumulator$inlined.element;
                    c79311.L$0 = this;
                    c79311.L$1 = obj;
                    c79311.L$2 = c793112;
                    c79311.L$3 = obj;
                    c79311.L$4 = objectRef2;
                    c79311.label = 1;
                    Object invoke = function3.invoke(t3, obj, c79311);
                    if (invoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1 = this;
                    obj2 = obj;
                    objectRef = objectRef2;
                    obj3 = obj2;
                    continuation2 = c793112;
                    t2 = (T) invoke;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            Object obj4 = c79311.L$3;
                            Object obj5 = c79311.L$1;
                            ResultKt.throwOnFailure(t2);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) c79311.L$4;
                    obj3 = c79311.L$3;
                    continuation2 = (Continuation) c79311.L$2;
                    obj2 = c79311.L$1;
                    flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1 = (FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1) c79311.L$0;
                    ResultKt.throwOnFailure(t2);
                }
                objectRef.element = t2;
                flowCollector = flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined;
                t = flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1.$accumulator$inlined.element;
                c79311.L$0 = flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1;
                c79311.L$1 = obj2;
                c79311.L$2 = continuation2;
                c79311.L$3 = obj3;
                c79311.label = 2;
                if (flowCollector.emit(t, c79311) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        c79311 = new C79311(continuation);
        T t22 = (T) c79311.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c79311.label;
        if (i != 0) {
        }
        objectRef.element = t22;
        flowCollector = flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined;
        t = flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1.$accumulator$inlined.element;
        c79311.L$0 = flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1;
        c79311.L$1 = obj2;
        c79311.L$2 = continuation2;
        c79311.L$3 = obj3;
        c79311.label = 2;
        if (flowCollector.emit(t, c79311) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }
}
