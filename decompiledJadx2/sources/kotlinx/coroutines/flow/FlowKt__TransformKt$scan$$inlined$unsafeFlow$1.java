package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SafeCollector.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__TransformKt$scan$$inlined$unsafeFlow$1<R> implements Flow<R> {
    final /* synthetic */ Object $initial$inlined;
    final /* synthetic */ Function3 $operation$inlined;
    final /* synthetic */ Flow $this_scan$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, m3961d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1", m3970f = "Transform.kt", m3971i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, m3972l = {114, 116}, m3973m = "collect", m3974n = {"this", "collector", "continuation", "$receiver", "accumulator", "this", "collector", "continuation", "$receiver", "accumulator", "$this$collect$iv"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$1 */
    /* loaded from: classes2.dex */
    public static final class C79301 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C79301(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__TransformKt$scan$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r5v0, types: [T, java.lang.Object] */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        C79301 c79301;
        Object coroutine_suspended;
        int i;
        Ref.ObjectRef objectRef;
        FlowKt__TransformKt$scan$$inlined$unsafeFlow$1<R> flowKt__TransformKt$scan$$inlined$unsafeFlow$1;
        FlowCollector flowCollector2;
        Continuation continuation2;
        Flow flow;
        FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1 flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1;
        if (continuation instanceof C79301) {
            c79301 = (C79301) continuation;
            if ((c79301.label & Integer.MIN_VALUE) != 0) {
                c79301.label -= Integer.MIN_VALUE;
                Object obj = c79301.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c79301.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    C79301 c793012 = c79301;
                    objectRef = new Ref.ObjectRef();
                    objectRef.element = this.$initial$inlined;
                    T t = objectRef.element;
                    c79301.L$0 = this;
                    c79301.L$1 = flowCollector;
                    c79301.L$2 = c793012;
                    c79301.L$3 = flowCollector;
                    c79301.L$4 = objectRef;
                    c79301.label = 1;
                    if (flowCollector.emit(t, c79301) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__TransformKt$scan$$inlined$unsafeFlow$1 = this;
                    flowCollector2 = flowCollector;
                    continuation2 = c793012;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Ref.ObjectRef objectRef2 = (Ref.ObjectRef) c79301.L$4;
                    FlowCollector flowCollector3 = (FlowCollector) c79301.L$3;
                    continuation2 = (Continuation) c79301.L$2;
                    flowCollector2 = (FlowCollector) c79301.L$1;
                    flowKt__TransformKt$scan$$inlined$unsafeFlow$1 = (FlowKt__TransformKt$scan$$inlined$unsafeFlow$1) c79301.L$0;
                    ResultKt.throwOnFailure(obj);
                    objectRef = objectRef2;
                    flowCollector = flowCollector3;
                }
                flow = flowKt__TransformKt$scan$$inlined$unsafeFlow$1.$this_scan$inlined;
                flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1 = new FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1(flowCollector, objectRef, flowKt__TransformKt$scan$$inlined$unsafeFlow$1);
                c79301.L$0 = flowKt__TransformKt$scan$$inlined$unsafeFlow$1;
                c79301.L$1 = flowCollector2;
                c79301.L$2 = continuation2;
                c79301.L$3 = flowCollector;
                c79301.L$4 = objectRef;
                c79301.L$5 = flow;
                c79301.label = 2;
                if (flow.collect(flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1, c79301) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        c79301 = new C79301(continuation);
        Object obj2 = c79301.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c79301.label;
        if (i != 0) {
        }
        flow = flowKt__TransformKt$scan$$inlined$unsafeFlow$1.$this_scan$inlined;
        flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1 = new FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1(flowCollector, objectRef, flowKt__TransformKt$scan$$inlined$unsafeFlow$1);
        c79301.L$0 = flowKt__TransformKt$scan$$inlined$unsafeFlow$1;
        c79301.L$1 = flowCollector2;
        c79301.L$2 = continuation2;
        c79301.L$3 = flowCollector;
        c79301.L$4 = objectRef;
        c79301.L$5 = flow;
        c79301.label = 2;
        if (flow.collect(flowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1, c79301) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }

    public FlowKt__TransformKt$scan$$inlined$unsafeFlow$1(Flow flow, Object obj, Function3 function3) {
        this.$this_scan$inlined = flow;
        this.$initial$inlined = obj;
        this.$operation$inlined = function3;
    }
}
