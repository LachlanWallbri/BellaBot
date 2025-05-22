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
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7 implements Flow<Integer> {
    final /* synthetic */ int[] $this_asFlow$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, m3961d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7", m3970f = "Builders.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {115}, m3973m = "collect", m3974n = {"this", "collector", "continuation", "$receiver", "$this$forEach$iv", "element$iv", ES6Iterator.VALUE_PROPERTY}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$2", "I$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$1 */
    /* loaded from: classes2.dex */
    public static final class C78501 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C78501(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7(int[] iArr) {
        this.$this_asFlow$inlined = iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0098 -> B:10:0x009b). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super Integer> flowCollector, Continuation continuation) {
        C78501 c78501;
        int i;
        int length;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7;
        Continuation continuation2;
        int[] iArr;
        FlowCollector flowCollector2;
        Object obj;
        C78501 c785012;
        int i2;
        FlowCollector flowCollector3;
        int[] iArr2;
        if (continuation instanceof C78501) {
            c78501 = (C78501) continuation;
            if ((c78501.label & Integer.MIN_VALUE) != 0) {
                c78501.label -= Integer.MIN_VALUE;
                Object obj2 = c78501.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78501.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    int[] iArr3 = this.$this_asFlow$inlined;
                    length = iArr3.length;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7 = this;
                    continuation2 = c78501;
                    iArr = iArr3;
                    flowCollector2 = flowCollector;
                    obj = coroutine_suspended;
                    c785012 = c78501;
                    i2 = 0;
                    flowCollector3 = flowCollector2;
                    iArr2 = iArr;
                    if (i2 < length) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i3 = c78501.I$3;
                    int i4 = c78501.I$2;
                    i2 = c78501.I$1;
                    int i5 = c78501.I$0;
                    int[] iArr4 = (int[]) c78501.L$5;
                    int[] iArr5 = (int[]) c78501.L$4;
                    FlowCollector flowCollector4 = (FlowCollector) c78501.L$3;
                    continuation2 = (Continuation) c78501.L$2;
                    FlowCollector flowCollector5 = (FlowCollector) c78501.L$1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7) c78501.L$0;
                    ResultKt.throwOnFailure(obj2);
                    flowCollector2 = flowCollector4;
                    iArr = iArr5;
                    iArr2 = iArr4;
                    length = i5;
                    obj = coroutine_suspended;
                    c785012 = c78501;
                    flowCollector3 = flowCollector5;
                    i2++;
                    if (i2 < length) {
                        int i6 = iArr2[i2];
                        int intValue = Boxing.boxInt(i6).intValue();
                        Integer boxInt = Boxing.boxInt(intValue);
                        c785012.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7;
                        c785012.L$1 = flowCollector3;
                        c785012.L$2 = continuation2;
                        c785012.L$3 = flowCollector2;
                        c785012.L$4 = iArr;
                        c785012.L$5 = iArr2;
                        c785012.I$0 = length;
                        c785012.I$1 = i2;
                        c785012.I$2 = i6;
                        c785012.I$3 = intValue;
                        c785012.label = 1;
                        if (flowCollector2.emit(boxInt, c785012) == obj) {
                            return obj;
                        }
                        i2++;
                        if (i2 < length) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        c78501 = new C78501(continuation);
        Object obj22 = c78501.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78501.label;
        if (i != 0) {
        }
    }
}
