package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SafeCollector.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2<T> implements Flow<T> {
    final /* synthetic */ Function1 $this_asFlow$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, m3961d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2", m3970f = "Builders.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 1}, m3972l = {113, 113}, m3973m = "collect", m3974n = {"this", "collector", "continuation", "$receiver", "this", "collector", "continuation", "$receiver"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$1 */
    /* loaded from: classes2.dex */
    public static final class C78451 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C78451(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2.this.collect(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        C78451 c78451;
        Object obj;
        Object coroutine_suspended;
        int i;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2<T> flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2;
        FlowCollector flowCollector2;
        Continuation continuation2;
        FlowCollector flowCollector3;
        FlowCollector flowCollector4;
        if (continuation instanceof C78451) {
            c78451 = (C78451) continuation;
            if ((c78451.label & Integer.MIN_VALUE) != 0) {
                c78451.label -= Integer.MIN_VALUE;
                obj = c78451.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78451.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    C78451 c784512 = c78451;
                    Function1 function1 = this.$this_asFlow$inlined;
                    c78451.L$0 = this;
                    c78451.L$1 = flowCollector;
                    c78451.L$2 = c784512;
                    c78451.L$3 = flowCollector;
                    c78451.L$4 = flowCollector;
                    c78451.label = 1;
                    Object invoke = function1.invoke(c78451);
                    if (invoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2 = this;
                    flowCollector2 = flowCollector;
                    continuation2 = c784512;
                    obj = invoke;
                    flowCollector3 = flowCollector2;
                    flowCollector4 = flowCollector;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    FlowCollector flowCollector5 = (FlowCollector) c78451.L$4;
                    flowCollector3 = (FlowCollector) c78451.L$3;
                    continuation2 = (Continuation) c78451.L$2;
                    flowCollector2 = (FlowCollector) c78451.L$1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2) c78451.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowCollector4 = flowCollector5;
                }
                c78451.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2;
                c78451.L$1 = flowCollector2;
                c78451.L$2 = continuation2;
                c78451.L$3 = flowCollector3;
                c78451.label = 2;
                if (flowCollector4.emit(obj, c78451) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        c78451 = new C78451(continuation);
        obj = c78451.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78451.label;
        if (i != 0) {
        }
        c78451.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2;
        c78451.L$1 = flowCollector2;
        c78451.L$2 = continuation2;
        c78451.L$3 = flowCollector3;
        c78451.label = 2;
        if (flowCollector4.emit(obj, c78451) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2(Function1 function1) {
        this.$this_asFlow$inlined = function1;
    }
}
