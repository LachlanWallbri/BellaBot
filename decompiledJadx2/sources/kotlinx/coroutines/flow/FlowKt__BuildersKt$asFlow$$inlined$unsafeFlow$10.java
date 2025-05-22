package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.ranges.LongRange;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SafeCollector.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10 implements Flow<Long> {
    final /* synthetic */ LongRange $this_asFlow$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, m3961d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10", m3970f = "Builders.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {115}, m3973m = "collect", m3974n = {"this", "collector", "continuation", "$receiver", "$this$forEach$iv", "element$iv", ES6Iterator.VALUE_PROPERTY}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "J$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$1 */
    /* loaded from: classes2.dex */
    public static final class C78441 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C78441(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10(LongRange longRange) {
        this.$this_asFlow$inlined = longRange;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super Long> flowCollector, Continuation continuation) {
        C78441 c78441;
        int i;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10;
        Continuation continuation2;
        FlowCollector flowCollector2;
        Iterator<Long> it;
        Object obj;
        Object obj2;
        C78441 c784412;
        FlowCollector flowCollector3;
        if (continuation instanceof C78441) {
            c78441 = (C78441) continuation;
            if ((c78441.label & Integer.MIN_VALUE) != 0) {
                c78441.label -= Integer.MIN_VALUE;
                Object obj3 = c78441.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78441.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj3);
                    LongRange longRange = this.$this_asFlow$inlined;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10 = this;
                    continuation2 = c78441;
                    flowCollector2 = flowCollector;
                    it = longRange.iterator();
                    obj = longRange;
                    obj2 = coroutine_suspended;
                    c784412 = c78441;
                    flowCollector3 = flowCollector2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    long j = c78441.J$0;
                    Object obj4 = c78441.L$6;
                    it = (Iterator) c78441.L$5;
                    Object obj5 = (Iterable) c78441.L$4;
                    FlowCollector flowCollector4 = (FlowCollector) c78441.L$3;
                    continuation2 = (Continuation) c78441.L$2;
                    FlowCollector flowCollector5 = (FlowCollector) c78441.L$1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10) c78441.L$0;
                    ResultKt.throwOnFailure(obj3);
                    flowCollector2 = flowCollector4;
                    obj = obj5;
                    obj2 = coroutine_suspended;
                    c784412 = c78441;
                    flowCollector3 = flowCollector5;
                }
                while (it.hasNext()) {
                    Long next = it.next();
                    long longValue = next.longValue();
                    Long boxLong = Boxing.boxLong(longValue);
                    c784412.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10;
                    c784412.L$1 = flowCollector3;
                    c784412.L$2 = continuation2;
                    c784412.L$3 = flowCollector2;
                    c784412.L$4 = obj;
                    c784412.L$5 = it;
                    c784412.L$6 = next;
                    c784412.J$0 = longValue;
                    c784412.label = 1;
                    if (flowCollector2.emit(boxLong, c784412) == obj2) {
                        return obj2;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        c78441 = new C78441(continuation);
        Object obj32 = c78441.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78441.label;
        if (i != 0) {
        }
        while (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }
}
