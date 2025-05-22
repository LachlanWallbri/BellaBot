package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__CountKt$count$$inlined$collect$2<T> implements FlowCollector<T> {
    final /* synthetic */ Ref.IntRef $i$inlined;
    final /* synthetic */ Function2 $predicate$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__CountKt$count$$inlined$collect$2", m3970f = "Count.kt", m3971i = {0, 0, 0, 0}, m3972l = {137}, m3973m = "emit", m3974n = {"this", ES6Iterator.VALUE_PROPERTY, "continuation", ES6Iterator.VALUE_PROPERTY}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__CountKt$count$$inlined$collect$2$1 */
    /* loaded from: classes2.dex */
    public static final class C78541 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C78541(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CountKt$count$$inlined$collect$2.this.emit(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Object obj, Continuation continuation) {
        C78541 c78541;
        Object obj2;
        int i;
        FlowKt__CountKt$count$$inlined$collect$2<T> flowKt__CountKt$count$$inlined$collect$2;
        if (continuation instanceof C78541) {
            c78541 = (C78541) continuation;
            if ((c78541.label & Integer.MIN_VALUE) != 0) {
                c78541.label -= Integer.MIN_VALUE;
                obj2 = c78541.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78541.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    Function2 function2 = this.$predicate$inlined;
                    c78541.L$0 = this;
                    c78541.L$1 = obj;
                    c78541.L$2 = c78541;
                    c78541.L$3 = obj;
                    c78541.label = 1;
                    obj2 = function2.invoke(obj, c78541);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__CountKt$count$$inlined$collect$2 = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object obj3 = c78541.L$3;
                    Object obj4 = c78541.L$1;
                    flowKt__CountKt$count$$inlined$collect$2 = (FlowKt__CountKt$count$$inlined$collect$2) c78541.L$0;
                    ResultKt.throwOnFailure(obj2);
                }
                if (((Boolean) obj2).booleanValue()) {
                    Ref.IntRef intRef = flowKt__CountKt$count$$inlined$collect$2.$i$inlined;
                    intRef.element++;
                    int i2 = intRef.element;
                }
                return Unit.INSTANCE;
            }
        }
        c78541 = new C78541(continuation);
        obj2 = c78541.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78541.label;
        if (i != 0) {
        }
        if (((Boolean) obj2).booleanValue()) {
        }
        return Unit.INSTANCE;
    }

    public FlowKt__CountKt$count$$inlined$collect$2(Function2 function2, Ref.IntRef intRef) {
        this.$predicate$inlined = function2;
        this.$i$inlined = intRef;
    }
}
