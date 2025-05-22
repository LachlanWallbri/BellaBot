package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: StateFlow.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`&2\b\u0012\u0004\u0012\u00028\u00000'2\b\u0012\u0004\u0012\u00028\u00000\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\f\u001a\u00020\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u001e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR*\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b#\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, m3961d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", ExifInterface.GPS_DIRECTION_TRUE, "", "initialValue", "<init>", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/flow/StateFlowSlot;", "allocateSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "slot", "freeSlot", "(Lkotlinx/coroutines/flow/StateFlowSlot;)V", "Lkotlin/coroutines/CoroutineContext;", "context", "", "capacity", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;I)Lkotlinx/coroutines/flow/internal/FusibleFlow;", "nSlots", "I", "nextIndex", "sequence", "", "slots", "[Lkotlinx/coroutines/flow/StateFlowSlot;", ES6Iterator.VALUE_PROPERTY, "getValue", "()Ljava/lang/Object;", "setValue", "value$annotations", "()V", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/flow/MutableStateFlow;"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class StateFlowImpl<T> implements MutableStateFlow<T>, FusibleFlow<T> {
    private volatile Object _state;
    private int nSlots;
    private int nextIndex;
    private int sequence;
    private StateFlowSlot[] slots = new StateFlowSlot[2];

    public static /* synthetic */ void value$annotations() {
    }

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow, kotlinx.coroutines.flow.StateFlow
    public T getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        T t = (T) this._state;
        if (t == symbol) {
            return null;
        }
        return t;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(T t) {
        if (t == null) {
            t = (T) NullSurrogateKt.NULL;
        }
        synchronized (this) {
            if (Intrinsics.areEqual(this._state, t)) {
                return;
            }
            this._state = t;
            int i = this.sequence;
            if ((i & 1) == 0) {
                int i2 = i + 1;
                this.sequence = i2;
                StateFlowSlot[] stateFlowSlotArr = this.slots;
                Unit unit = Unit.INSTANCE;
                while (true) {
                    for (StateFlowSlot stateFlowSlot : stateFlowSlotArr) {
                        if (stateFlowSlot != null) {
                            stateFlowSlot.makePending();
                        }
                    }
                    synchronized (this) {
                        if (this.sequence == i2) {
                            this.sequence = i2 + 1;
                            return;
                        } else {
                            i2 = this.sequence;
                            stateFlowSlotArr = this.slots;
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
            } else {
                this.sequence = i + 2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0071, code lost:
    
        if ((!kotlin.jvm.internal.Intrinsics.areEqual(r6, r12)) != false) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006c A[Catch: all -> 0x005c, TryCatch #0 {all -> 0x005c, blocks: (B:12:0x003a, B:14:0x0068, B:16:0x006c, B:18:0x0093, B:20:0x0099, B:24:0x0073, B:27:0x007a, B:37:0x0058), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0099 A[Catch: all -> 0x005c, TRY_LEAVE, TryCatch #0 {all -> 0x005c, blocks: (B:12:0x003a, B:14:0x0068, B:16:0x006c, B:18:0x0093, B:20:0x0099, B:24:0x0073, B:27:0x007a, B:37:0x0058), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.flow.StateFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0097 -> B:14:0x0068). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00a9 -> B:14:0x0068). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        StateFlowImpl$collect$1 stateFlowImpl$collect$1;
        Object coroutine_suspended;
        ?? r2;
        StateFlowImpl<T> stateFlowImpl;
        Object obj;
        Object obj2;
        FlowCollector<? super T> flowCollector2;
        StateFlowSlot stateFlowSlot;
        Object obj3;
        StateFlowSlot stateFlowSlot2;
        boolean takePending;
        Object obj4;
        try {
            if (continuation instanceof StateFlowImpl$collect$1) {
                stateFlowImpl$collect$1 = (StateFlowImpl$collect$1) continuation;
                if ((stateFlowImpl$collect$1.label & Integer.MIN_VALUE) != 0) {
                    stateFlowImpl$collect$1.label -= Integer.MIN_VALUE;
                    Object obj5 = stateFlowImpl$collect$1.result;
                    coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    r2 = stateFlowImpl$collect$1.label;
                    if (r2 != 0) {
                        ResultKt.throwOnFailure(obj5);
                        stateFlowImpl = this;
                        r2 = allocateSlot();
                        obj = null;
                    } else if (r2 == 1) {
                        obj2 = stateFlowImpl$collect$1.L$4;
                        Object obj6 = stateFlowImpl$collect$1.L$3;
                        StateFlowSlot stateFlowSlot3 = (StateFlowSlot) stateFlowImpl$collect$1.L$2;
                        flowCollector2 = (FlowCollector) stateFlowImpl$collect$1.L$1;
                        stateFlowImpl = (StateFlowImpl) stateFlowImpl$collect$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        stateFlowSlot = stateFlowSlot3;
                        obj = obj2;
                        flowCollector = flowCollector2;
                        obj3 = obj;
                        stateFlowSlot2 = stateFlowSlot;
                        takePending = stateFlowSlot2.takePending();
                        r2 = stateFlowSlot2;
                        if (!takePending) {
                            stateFlowImpl$collect$1.L$0 = stateFlowImpl;
                            stateFlowImpl$collect$1.L$1 = flowCollector;
                            stateFlowImpl$collect$1.L$2 = stateFlowSlot2;
                            stateFlowImpl$collect$1.L$3 = obj;
                            stateFlowImpl$collect$1.L$4 = obj3;
                            stateFlowImpl$collect$1.label = 2;
                            Object awaitPending = stateFlowSlot2.awaitPending(stateFlowImpl$collect$1);
                            r2 = stateFlowSlot2;
                            if (awaitPending == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (r2 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        Object obj7 = stateFlowImpl$collect$1.L$4;
                        Object obj8 = stateFlowImpl$collect$1.L$3;
                        StateFlowSlot stateFlowSlot4 = (StateFlowSlot) stateFlowImpl$collect$1.L$2;
                        FlowCollector<? super T> flowCollector3 = (FlowCollector) stateFlowImpl$collect$1.L$1;
                        stateFlowImpl = (StateFlowImpl) stateFlowImpl$collect$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        obj = obj8;
                        flowCollector = flowCollector3;
                        r2 = stateFlowSlot4;
                    }
                    obj3 = stateFlowImpl._state;
                    if (obj != null) {
                        stateFlowSlot2 = r2;
                    }
                    obj4 = obj3 == NullSurrogateKt.NULL ? null : obj3;
                    stateFlowImpl$collect$1.L$0 = stateFlowImpl;
                    stateFlowImpl$collect$1.L$1 = flowCollector;
                    stateFlowImpl$collect$1.L$2 = r2;
                    stateFlowImpl$collect$1.L$3 = obj;
                    stateFlowImpl$collect$1.L$4 = obj3;
                    stateFlowImpl$collect$1.label = 1;
                    if (flowCollector.emit(obj4, stateFlowImpl$collect$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowCollector2 = flowCollector;
                    obj2 = obj3;
                    stateFlowSlot = r2;
                    obj = obj2;
                    flowCollector = flowCollector2;
                    obj3 = obj;
                    stateFlowSlot2 = stateFlowSlot;
                    takePending = stateFlowSlot2.takePending();
                    r2 = stateFlowSlot2;
                    if (!takePending) {
                    }
                    obj3 = stateFlowImpl._state;
                    if (obj != null) {
                    }
                    if (obj3 == NullSurrogateKt.NULL) {
                    }
                    stateFlowImpl$collect$1.L$0 = stateFlowImpl;
                    stateFlowImpl$collect$1.L$1 = flowCollector;
                    stateFlowImpl$collect$1.L$2 = r2;
                    stateFlowImpl$collect$1.L$3 = obj;
                    stateFlowImpl$collect$1.L$4 = obj3;
                    stateFlowImpl$collect$1.label = 1;
                    if (flowCollector.emit(obj4, stateFlowImpl$collect$1) == coroutine_suspended) {
                    }
                }
            }
            if (r2 != 0) {
            }
            obj3 = stateFlowImpl._state;
            if (obj != null) {
            }
            if (obj3 == NullSurrogateKt.NULL) {
            }
            stateFlowImpl$collect$1.L$0 = stateFlowImpl;
            stateFlowImpl$collect$1.L$1 = flowCollector;
            stateFlowImpl$collect$1.L$2 = r2;
            stateFlowImpl$collect$1.L$3 = obj;
            stateFlowImpl$collect$1.L$4 = obj3;
            stateFlowImpl$collect$1.label = 1;
            if (flowCollector.emit(obj4, stateFlowImpl$collect$1) == coroutine_suspended) {
            }
        } catch (Throwable th) {
            stateFlowImpl.freeSlot(r2);
            throw th;
        }
        stateFlowImpl$collect$1 = new StateFlowImpl$collect$1(this, continuation);
        Object obj52 = stateFlowImpl$collect$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = stateFlowImpl$collect$1.label;
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public FusibleFlow<T> fuse(CoroutineContext context, int capacity) {
        if (capacity == -1 || capacity == 0) {
            return this;
        }
        return (FusibleFlow) new ChannelFlowOperatorImpl(this, context, capacity);
    }

    private final StateFlowSlot allocateSlot() {
        StateFlowSlot stateFlowSlot;
        synchronized (this) {
            int length = this.slots.length;
            if (this.nSlots >= length) {
                Object[] copyOf = Arrays.copyOf(this.slots, length * 2);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                this.slots = (StateFlowSlot[]) copyOf;
            }
            int i = this.nextIndex;
            do {
                stateFlowSlot = this.slots[i];
                if (stateFlowSlot == null) {
                    stateFlowSlot = new StateFlowSlot();
                    this.slots[i] = stateFlowSlot;
                }
                i++;
                if (i >= this.slots.length) {
                    i = 0;
                }
            } while (!stateFlowSlot.allocate());
            this.nextIndex = i;
            this.nSlots++;
        }
        return stateFlowSlot;
    }

    private final void freeSlot(StateFlowSlot slot) {
        synchronized (this) {
            slot.free();
            this.nSlots--;
            Unit unit = Unit.INSTANCE;
        }
    }
}
