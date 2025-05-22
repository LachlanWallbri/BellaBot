package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collect$2"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1<T> implements FlowCollector<T> {
    final /* synthetic */ Ref.BooleanRef $matched$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 this$0;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\b"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collect$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1$1 */
    /* loaded from: classes2.dex */
    public static final class C78761 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C78761(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.this.emit(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Object obj, Continuation continuation) {
        C78761 c78761;
        int i;
        C78761 c787612;
        FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1<T> flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1;
        Object obj2;
        if (continuation instanceof C78761) {
            c78761 = (C78761) continuation;
            if ((c78761.label & Integer.MIN_VALUE) != 0) {
                c78761.label -= Integer.MIN_VALUE;
                Object obj3 = c78761.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78761.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj3);
                    c787612 = c78761;
                    if (this.$matched$inlined.element) {
                        FlowCollector flowCollector = this.$this_unsafeFlow$inlined;
                        c78761.L$0 = this;
                        c78761.L$1 = obj;
                        c78761.L$2 = c787612;
                        c78761.L$3 = obj;
                        c78761.label = 1;
                        if (flowCollector.emit(obj, c78761) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    Function2 function2 = this.this$0.$predicate$inlined;
                    c78761.L$0 = this;
                    c78761.L$1 = obj;
                    c78761.L$2 = c787612;
                    c78761.L$3 = obj;
                    c78761.label = 2;
                    obj3 = function2.invoke(obj, c78761);
                    if (obj3 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1 = this;
                    obj2 = obj;
                    if (!((Boolean) obj3).booleanValue()) {
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    if (i == 2) {
                        obj = c78761.L$3;
                        c787612 = (Continuation) c78761.L$2;
                        obj2 = c78761.L$1;
                        flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1 = (FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1) c78761.L$0;
                        ResultKt.throwOnFailure(obj3);
                        if (!((Boolean) obj3).booleanValue()) {
                            flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.$matched$inlined.element = true;
                            FlowCollector flowCollector2 = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined;
                            c78761.L$0 = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1;
                            c78761.L$1 = obj2;
                            c78761.L$2 = c787612;
                            c78761.L$3 = obj;
                            c78761.label = 3;
                            if (flowCollector2.emit(obj, c78761) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
                Object obj4 = c78761.L$3;
                Object obj5 = c78761.L$1;
                ResultKt.throwOnFailure(obj3);
                return Unit.INSTANCE;
            }
        }
        c78761 = new C78761(continuation);
        Object obj32 = c78761.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78761.label;
        if (i != 0) {
        }
    }

    public FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Ref.BooleanRef booleanRef, FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$matched$inlined = booleanRef;
        this.this$0 = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1;
    }
}
