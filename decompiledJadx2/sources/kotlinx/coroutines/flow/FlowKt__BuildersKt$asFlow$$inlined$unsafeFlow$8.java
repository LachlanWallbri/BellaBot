package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SafeCollector.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 implements Flow<Long> {
    final /* synthetic */ long[] $this_asFlow$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, m3961d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8", m3970f = "Builders.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {115}, m3973m = "collect", m3974n = {"this", "collector", "continuation", "$receiver", "$this$forEach$iv", "element$iv", ES6Iterator.VALUE_PROPERTY}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "J$1"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1 */
    /* loaded from: classes2.dex */
    public static final class C78511 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C78511(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8(long[] jArr) {
        this.$this_asFlow$inlined = jArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00a4 -> B:10:0x00a6). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super Long> flowCollector, Continuation continuation) {
        C78511 c78511;
        int i;
        int length;
        int i2;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8;
        Continuation continuation2;
        long[] jArr;
        long[] jArr2;
        FlowCollector flowCollector2;
        Object obj;
        C78511 c785112;
        FlowCollector flowCollector3;
        if (continuation instanceof C78511) {
            c78511 = (C78511) continuation;
            if ((c78511.label & Integer.MIN_VALUE) != 0) {
                c78511.label -= Integer.MIN_VALUE;
                Object obj2 = c78511.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78511.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    long[] jArr3 = this.$this_asFlow$inlined;
                    length = jArr3.length;
                    i2 = 0;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 = this;
                    continuation2 = c78511;
                    jArr = jArr3;
                    jArr2 = jArr;
                    flowCollector2 = flowCollector;
                    obj = coroutine_suspended;
                    c785112 = c78511;
                    flowCollector3 = flowCollector2;
                    if (i2 < length) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    long j = c78511.J$1;
                    long j2 = c78511.J$0;
                    int i3 = c78511.I$1;
                    int i4 = c78511.I$0;
                    long[] jArr4 = (long[]) c78511.L$5;
                    long[] jArr5 = (long[]) c78511.L$4;
                    FlowCollector flowCollector4 = (FlowCollector) c78511.L$3;
                    continuation2 = (Continuation) c78511.L$2;
                    FlowCollector flowCollector5 = (FlowCollector) c78511.L$1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8) c78511.L$0;
                    ResultKt.throwOnFailure(obj2);
                    int i5 = i4;
                    flowCollector2 = flowCollector4;
                    Object obj3 = coroutine_suspended;
                    jArr2 = jArr5;
                    c785112 = c78511;
                    jArr = jArr4;
                    flowCollector3 = flowCollector5;
                    i2 = i3 + 1;
                    obj = obj3;
                    length = i5;
                    if (i2 < length) {
                        long j3 = jArr[i2];
                        long longValue = Boxing.boxLong(j3).longValue();
                        Long boxLong = Boxing.boxLong(longValue);
                        c785112.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8;
                        c785112.L$1 = flowCollector3;
                        c785112.L$2 = continuation2;
                        c785112.L$3 = flowCollector2;
                        c785112.L$4 = jArr2;
                        c785112.L$5 = jArr;
                        i5 = length;
                        c785112.I$0 = i5;
                        c785112.I$1 = i2;
                        c785112.J$0 = j3;
                        c785112.J$1 = longValue;
                        c785112.label = 1;
                        if (flowCollector2.emit(boxLong, c785112) == obj) {
                            return obj;
                        }
                        obj3 = obj;
                        i3 = i2;
                        i2 = i3 + 1;
                        obj = obj3;
                        length = i5;
                        if (i2 < length) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        c78511 = new C78511(continuation);
        Object obj22 = c78511.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78511.label;
        if (i != 0) {
        }
    }
}
