package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SafeCollector.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6<T> implements Flow<T> {
    final /* synthetic */ Object[] $this_asFlow$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, m3961d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6", m3970f = "Builders.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {115}, m3973m = "collect", m3974n = {"this", "collector", "continuation", "$receiver", "$this$forEach$iv", "element$iv", ES6Iterator.VALUE_PROPERTY}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$1 */
    /* loaded from: classes2.dex */
    public static final class C78491 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C78491(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6(Object[] objArr) {
        this.$this_asFlow$inlined = objArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x008a -> B:10:0x008d). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        C78491 c78491;
        int i;
        int length;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6<T> flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6;
        Continuation continuation2;
        Object[] objArr;
        Object obj;
        C78491 c784912;
        int i2;
        FlowCollector flowCollector2;
        Object[] objArr2;
        FlowCollector flowCollector3;
        if (continuation instanceof C78491) {
            c78491 = (C78491) continuation;
            if ((c78491.label & Integer.MIN_VALUE) != 0) {
                c78491.label -= Integer.MIN_VALUE;
                Object obj2 = c78491.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78491.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    Object[] objArr3 = this.$this_asFlow$inlined;
                    length = objArr3.length;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6 = this;
                    continuation2 = c78491;
                    objArr = objArr3;
                    FlowCollector flowCollector4 = flowCollector;
                    obj = coroutine_suspended;
                    c784912 = c78491;
                    i2 = 0;
                    flowCollector2 = flowCollector4;
                    objArr2 = objArr;
                    flowCollector3 = flowCollector4;
                    if (i2 < length) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object obj3 = c78491.L$7;
                    Object obj4 = c78491.L$6;
                    i2 = c78491.I$1;
                    int i3 = c78491.I$0;
                    Object[] objArr4 = (Object[]) c78491.L$5;
                    Object[] objArr5 = (Object[]) c78491.L$4;
                    FlowCollector flowCollector5 = (FlowCollector) c78491.L$3;
                    continuation2 = (Continuation) c78491.L$2;
                    FlowCollector flowCollector6 = (FlowCollector) c78491.L$1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6) c78491.L$0;
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector7 = flowCollector5;
                    objArr = objArr5;
                    objArr2 = objArr4;
                    length = i3;
                    obj = coroutine_suspended;
                    c784912 = c78491;
                    flowCollector2 = flowCollector6;
                    i2++;
                    flowCollector3 = flowCollector7;
                    if (i2 < length) {
                        Object obj5 = objArr2[i2];
                        c784912.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6;
                        c784912.L$1 = flowCollector2;
                        c784912.L$2 = continuation2;
                        c784912.L$3 = flowCollector3;
                        c784912.L$4 = objArr;
                        c784912.L$5 = objArr2;
                        c784912.I$0 = length;
                        c784912.I$1 = i2;
                        c784912.L$6 = obj5;
                        c784912.L$7 = obj5;
                        c784912.label = 1;
                        Object emit = flowCollector3.emit(obj5, c784912);
                        flowCollector7 = flowCollector3;
                        if (emit == obj) {
                            return obj;
                        }
                        i2++;
                        flowCollector3 = flowCollector7;
                        if (i2 < length) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        c78491 = new C78491(continuation);
        Object obj22 = c78491.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78491.label;
        if (i != 0) {
        }
    }
}
