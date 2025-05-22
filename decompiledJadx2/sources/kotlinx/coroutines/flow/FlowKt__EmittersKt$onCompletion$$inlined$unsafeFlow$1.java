package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.internal.SafeCollector;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SafeCollector.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, m3961d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", m3970f = "Emitters.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, m3972l = {114, 122, 128}, m3973m = "collect", m3974n = {"this", "collector", "continuation", "$receiver", "this", "collector", "continuation", "$receiver", C3898x.f4338g, "this", "collector", "continuation", "$receiver", "safeCollector", MqttServiceConstants.TRACE_EXCEPTION}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 */
    /* loaded from: classes2.dex */
    public static final class C78671 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C78671(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_onCompletion$inlined = flow;
        this.$action$inlined = function3;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        C78671 c78671;
        Object coroutine_suspended;
        int i;
        C78671 c786712;
        FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
        FlowCollector flowCollector2;
        Throwable th;
        FlowCollector flowCollector3;
        ThrowingCollector throwingCollector;
        Function3 function3;
        Throwable th2;
        SafeCollector safeCollector;
        SafeCollector safeCollector2;
        Function3 function32;
        Throwable th3;
        try {
            if (continuation instanceof C78671) {
                c78671 = (C78671) continuation;
                if ((c78671.label & Integer.MIN_VALUE) != 0) {
                    c78671.label -= Integer.MIN_VALUE;
                    Object obj = c78671.result;
                    coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = c78671.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        c786712 = c78671;
                        try {
                            Flow flow = this.$this_onCompletion$inlined;
                            c78671.L$0 = this;
                            c78671.L$1 = flowCollector;
                            c78671.L$2 = c786712;
                            c78671.L$3 = flowCollector;
                            c78671.label = 1;
                            obj = FlowKt.catchImpl(flow, flowCollector, c78671);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                            flowCollector2 = flowCollector;
                        } catch (Throwable th4) {
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                            flowCollector2 = flowCollector;
                            th = th4;
                            flowCollector3 = flowCollector2;
                            throwingCollector = new ThrowingCollector(th);
                            function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                            c78671.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                            c78671.L$1 = flowCollector2;
                            c78671.L$2 = c786712;
                            c78671.L$3 = flowCollector3;
                            c78671.L$4 = th;
                            c78671.label = 2;
                            if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, null, c78671) != coroutine_suspended) {
                            }
                        }
                    } else {
                        if (i != 1) {
                            if (i == 2) {
                                Throwable th5 = (Throwable) c78671.L$4;
                                ResultKt.throwOnFailure(obj);
                                throw th5;
                            }
                            if (i != 3) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            th3 = (Throwable) c78671.L$5;
                            safeCollector2 = (SafeCollector) c78671.L$4;
                            try {
                                ResultKt.throwOnFailure(obj);
                                safeCollector2.releaseIntercepted();
                                if (th3 != null) {
                                    return Unit.INSTANCE;
                                }
                                throw th3;
                            } catch (Throwable th6) {
                                th = th6;
                                safeCollector2.releaseIntercepted();
                                throw th;
                            }
                        }
                        flowCollector = (FlowCollector) c78671.L$3;
                        c786712 = (Continuation) c78671.L$2;
                        flowCollector2 = (FlowCollector) c78671.L$1;
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = (FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) c78671.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                        } catch (Throwable th7) {
                            flowCollector3 = flowCollector;
                            th = th7;
                            throwingCollector = new ThrowingCollector(th);
                            function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                            c78671.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                            c78671.L$1 = flowCollector2;
                            c78671.L$2 = c786712;
                            c78671.L$3 = flowCollector3;
                            c78671.L$4 = th;
                            c78671.label = 2;
                            if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, null, c78671) != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            throw th;
                        }
                    }
                    th2 = (Throwable) obj;
                    safeCollector = new SafeCollector(flowCollector, c78671.getContext());
                    function32 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                    c78671.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                    c78671.L$1 = flowCollector2;
                    c78671.L$2 = c786712;
                    c78671.L$3 = flowCollector;
                    c78671.L$4 = safeCollector;
                    c78671.L$5 = th2;
                    c78671.label = 3;
                    if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(safeCollector, function32, th2, c78671) != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    th3 = th2;
                    safeCollector2 = safeCollector;
                    safeCollector2.releaseIntercepted();
                    if (th3 != null) {
                    }
                }
            }
            function32 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
            c78671.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
            c78671.L$1 = flowCollector2;
            c78671.L$2 = c786712;
            c78671.L$3 = flowCollector;
            c78671.L$4 = safeCollector;
            c78671.L$5 = th2;
            c78671.label = 3;
            if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(safeCollector, function32, th2, c78671) != coroutine_suspended) {
            }
        } catch (Throwable th8) {
            th = th8;
            safeCollector2 = safeCollector;
            safeCollector2.releaseIntercepted();
            throw th;
        }
        c78671 = new C78671(continuation);
        Object obj2 = c78671.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c78671.label;
        if (i != 0) {
        }
        th2 = (Throwable) obj2;
        safeCollector = new SafeCollector(flowCollector, c78671.getContext());
    }
}
