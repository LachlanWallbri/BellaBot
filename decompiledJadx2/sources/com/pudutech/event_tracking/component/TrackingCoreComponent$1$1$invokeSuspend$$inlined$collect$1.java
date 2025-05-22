package com.pudutech.event_tracking.component;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.event_tracking.component.TrackingCoreComponent;
import com.pudutech.pd_network.log.NetWorkLog;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowCollector;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TrackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<Integer> {
    final /* synthetic */ TrackingCoreComponent.C44581.AnonymousClass1 this$0;

    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.event_tracking.component.TrackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1", m3970f = "TrackingCoreComponent.kt", m3971i = {0, 0, 0, 0}, m3972l = {140}, m3973m = "emit", m3974n = {"this", ES6Iterator.VALUE_PROPERTY, "continuation", "it"}, m3975s = {"L$0", "L$1", "L$2", "I$0"})
    /* renamed from: com.pudutech.event_tracking.component.TrackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1$1 */
    /* loaded from: classes5.dex */
    public static final class C44571 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C44571(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TrackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Integer num, Continuation continuation) {
        C44571 c44571;
        int i;
        AtomicBoolean atomicBoolean;
        if (continuation instanceof C44571) {
            c44571 = (C44571) continuation;
            if ((c44571.label & Integer.MIN_VALUE) != 0) {
                c44571.label -= Integer.MIN_VALUE;
                Object obj = c44571.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c44571.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    C44571 c445712 = c44571;
                    int intValue = num.intValue();
                    NetWorkLog.INSTANCE.mo3280i(TrackingCoreComponent.this.TAG, "getAllCount " + intValue);
                    if (intValue > TrackingCoreComponent.this.workRuler.getMaxNum()) {
                        atomicBoolean = TrackingCoreComponent.this.isHandlerRemove;
                        if (!atomicBoolean.get()) {
                            TrackingCoreComponent trackingCoreComponent = TrackingCoreComponent.this;
                            c44571.L$0 = this;
                            c44571.L$1 = num;
                            c44571.L$2 = c445712;
                            c44571.I$0 = intValue;
                            c44571.label = 1;
                            if (trackingCoreComponent.handlerDelete(c44571) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i2 = c44571.I$0;
                    Object obj2 = c44571.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        c44571 = new C44571(continuation);
        Object obj3 = c44571.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c44571.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    public TrackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1(TrackingCoreComponent.C44581.AnonymousClass1 anonymousClass1) {
        this.this$0 = anonymousClass1;
    }
}
