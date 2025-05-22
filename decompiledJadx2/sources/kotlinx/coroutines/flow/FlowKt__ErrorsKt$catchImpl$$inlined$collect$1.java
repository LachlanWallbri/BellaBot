package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
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
public final class FlowKt__ErrorsKt$catchImpl$$inlined$collect$1<T> implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $collector$inlined;
    final /* synthetic */ Ref.ObjectRef $fromDownstream$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1", m3970f = "Errors.kt", m3971i = {0, 0, 0, 0}, m3972l = {138}, m3973m = "emit", m3974n = {"this", ES6Iterator.VALUE_PROPERTY, "continuation", "it"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1$1 */
    /* loaded from: classes2.dex */
    public static final class C78741 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C78741(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__ErrorsKt$catchImpl$$inlined$collect$1.this.emit(null, this);
        }
    }

    public FlowKt__ErrorsKt$catchImpl$$inlined$collect$1(FlowCollector flowCollector, Ref.ObjectRef objectRef) {
        this.$collector$inlined = flowCollector;
        this.$fromDownstream$inlined = objectRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Object obj, Continuation continuation) {
        C78741 c78741;
        int i;
        FlowKt__ErrorsKt$catchImpl$$inlined$collect$1<T> flowKt__ErrorsKt$catchImpl$$inlined$collect$1;
        if (continuation instanceof C78741) {
            c78741 = (C78741) continuation;
            if ((c78741.label & Integer.MIN_VALUE) != 0) {
                c78741.label -= Integer.MIN_VALUE;
                Object obj2 = c78741.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c78741.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    C78741 c787412 = c78741;
                    try {
                        FlowCollector flowCollector = this.$collector$inlined;
                        c78741.L$0 = this;
                        c78741.L$1 = obj;
                        c78741.L$2 = c787412;
                        c78741.L$3 = obj;
                        c78741.label = 1;
                        if (flowCollector.emit(obj, c78741) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } catch (Throwable 
                    /*  JADX ERROR: Method code generation error
                        java.lang.NullPointerException
                        */
                    /*
                        this = this;
                        boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1.C78741
                        if (r0 == 0) goto L14
                        r0 = r6
                        kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1.C78741) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r6 = r0.label
                        int r6 = r6 - r2
                        r0.label = r6
                        goto L19
                    L14:
                        kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1$1
                        r0.<init>(r6)
                    L19:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L40
                        if (r2 != r3) goto L38
                        java.lang.Object r5 = r0.L$3
                        java.lang.Object r5 = r0.L$2
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        java.lang.Object r5 = r0.L$1
                        java.lang.Object r5 = r0.L$0
                        kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1 r5 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1) r5
                        kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L36
                        goto L59
                    L36:
                        r6 = move-exception
                        goto L5e
                    L38:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L40:
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r0
                        kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                        kotlinx.coroutines.flow.FlowCollector r2 = r4.$collector$inlined     // Catch: java.lang.Throwable -> L5c
                        r0.L$0 = r4     // Catch: java.lang.Throwable -> L5c
                        r0.L$1 = r5     // Catch: java.lang.Throwable -> L5c
                        r0.L$2 = r6     // Catch: java.lang.Throwable -> L5c
                        r0.L$3 = r5     // Catch: java.lang.Throwable -> L5c
                        r0.label = r3     // Catch: java.lang.Throwable -> L5c
                        java.lang.Object r5 = r2.emit(r5, r0)     // Catch: java.lang.Throwable -> L5c
                        if (r5 != r1) goto L59
                        return r1
                    L59:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    L5c:
                        r6 = move-exception
                        r5 = r4
                    L5e:
                        kotlin.jvm.internal.Ref$ObjectRef r5 = r5.$fromDownstream$inlined
                        r5.element = r6
                        throw r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
