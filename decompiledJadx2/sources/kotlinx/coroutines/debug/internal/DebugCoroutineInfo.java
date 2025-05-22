package kotlinx.coroutines.debug.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: DebugCoroutineInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u001a\u001a\u00020\u0000J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\b\u0010\u001c\u001a\u00020\nH\u0016J!\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\n2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0000¢\u0006\u0002\b!J%\u0010\"\u001a\u00020\u001e*\b\u0012\u0004\u0012\u00020\u00110#2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0082Pø\u0001\u0000¢\u0006\u0002\u0010$R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, m3961d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "sequenceNumber", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;J)V", "_state", "", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getCreationStackBottom", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "lastObservedFrame", "lastObservedThread", "Ljava/lang/Thread;", "state", "getState", "()Ljava/lang/String;", "copy", "lastObservedStackTrace", "toString", "updateState", "", TypedValues.Attributes.S_FRAME, "Lkotlin/coroutines/Continuation;", "updateState$kotlinx_coroutines_core", "yieldFrames", "Lkotlin/sequences/SequenceScope;", "(Lkotlin/sequences/SequenceScope;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DebugCoroutineInfo {
    private String _state = DebugCoroutineInfoKt.CREATED;
    private final CoroutineContext context;
    private final CoroutineStackFrame creationStackBottom;
    public CoroutineStackFrame lastObservedFrame;
    public Thread lastObservedThread;
    public final long sequenceNumber;

    public DebugCoroutineInfo(CoroutineContext coroutineContext, CoroutineStackFrame coroutineStackFrame, long j) {
        this.context = coroutineContext;
        this.creationStackBottom = coroutineStackFrame;
        this.sequenceNumber = j;
    }

    public final CoroutineContext getContext() {
        return this.context;
    }

    public final CoroutineStackFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    public final List<StackTraceElement> getCreationStackTrace() {
        return creationStackTrace();
    }

    /* renamed from: getState, reason: from getter */
    public final String get_state() {
        return this._state;
    }

    public final DebugCoroutineInfo copy() {
        DebugCoroutineInfo debugCoroutineInfo = new DebugCoroutineInfo(this.context, this.creationStackBottom, this.sequenceNumber);
        debugCoroutineInfo._state = this._state;
        debugCoroutineInfo.lastObservedFrame = this.lastObservedFrame;
        debugCoroutineInfo.lastObservedThread = this.lastObservedThread;
        return debugCoroutineInfo;
    }

    public final List<StackTraceElement> lastObservedStackTrace() {
        CoroutineStackFrame coroutineStackFrame = this.lastObservedFrame;
        if (coroutineStackFrame == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (coroutineStackFrame != null) {
            StackTraceElement stackTraceElement = coroutineStackFrame.getStackTraceElement();
            if (stackTraceElement != null) {
                arrayList.add(stackTraceElement);
            }
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
        }
        return arrayList;
    }

    private final List<StackTraceElement> creationStackTrace() {
        CoroutineStackFrame coroutineStackFrame = this.creationStackBottom;
        if (coroutineStackFrame == null) {
            return CollectionsKt.emptyList();
        }
        return SequencesKt.toList(SequencesKt.sequence(new DebugCoroutineInfo$creationStackTrace$1(this, coroutineStackFrame, null)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x004f -> B:11:0x0068). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0062 -> B:10:0x0065). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object yieldFrames(SequenceScope<? super StackTraceElement> sequenceScope, CoroutineStackFrame coroutineStackFrame, Continuation<? super Unit> continuation) {
        DebugCoroutineInfo$yieldFrames$1 debugCoroutineInfo$yieldFrames$1;
        int i;
        DebugCoroutineInfo debugCoroutineInfo;
        if (continuation instanceof DebugCoroutineInfo$yieldFrames$1) {
            debugCoroutineInfo$yieldFrames$1 = (DebugCoroutineInfo$yieldFrames$1) continuation;
            if ((debugCoroutineInfo$yieldFrames$1.label & Integer.MIN_VALUE) != 0) {
                debugCoroutineInfo$yieldFrames$1.label -= Integer.MIN_VALUE;
                Object obj = debugCoroutineInfo$yieldFrames$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = debugCoroutineInfo$yieldFrames$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    debugCoroutineInfo = this;
                    if (coroutineStackFrame == null) {
                    }
                } else if (i == 1) {
                    CoroutineStackFrame coroutineStackFrame2 = (CoroutineStackFrame) debugCoroutineInfo$yieldFrames$1.L$2;
                    SequenceScope<? super StackTraceElement> sequenceScope2 = (SequenceScope) debugCoroutineInfo$yieldFrames$1.L$1;
                    debugCoroutineInfo = (DebugCoroutineInfo) debugCoroutineInfo$yieldFrames$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    SequenceScope<? super StackTraceElement> sequenceScope3 = sequenceScope2;
                    coroutineStackFrame = coroutineStackFrame2;
                    sequenceScope = sequenceScope3;
                    coroutineStackFrame = coroutineStackFrame.getCallerFrame();
                    if (coroutineStackFrame == null) {
                        return Unit.INSTANCE;
                    }
                    if (coroutineStackFrame == null) {
                        StackTraceElement stackTraceElement = coroutineStackFrame.getStackTraceElement();
                        if (stackTraceElement != null) {
                            debugCoroutineInfo$yieldFrames$1.L$0 = debugCoroutineInfo;
                            debugCoroutineInfo$yieldFrames$1.L$1 = sequenceScope;
                            debugCoroutineInfo$yieldFrames$1.L$2 = coroutineStackFrame;
                            debugCoroutineInfo$yieldFrames$1.L$3 = stackTraceElement;
                            debugCoroutineInfo$yieldFrames$1.label = 1;
                            if (sequenceScope.yield(stackTraceElement, debugCoroutineInfo$yieldFrames$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            CoroutineStackFrame coroutineStackFrame3 = coroutineStackFrame;
                            sequenceScope2 = sequenceScope;
                            coroutineStackFrame2 = coroutineStackFrame3;
                            SequenceScope<? super StackTraceElement> sequenceScope32 = sequenceScope2;
                            coroutineStackFrame = coroutineStackFrame2;
                            sequenceScope = sequenceScope32;
                        }
                        coroutineStackFrame = coroutineStackFrame.getCallerFrame();
                        if (coroutineStackFrame == null) {
                        }
                        if (coroutineStackFrame == null) {
                            return Unit.INSTANCE;
                        }
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        debugCoroutineInfo$yieldFrames$1 = new DebugCoroutineInfo$yieldFrames$1(this, continuation);
        Object obj2 = debugCoroutineInfo$yieldFrames$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = debugCoroutineInfo$yieldFrames$1.label;
        if (i != 0) {
        }
    }

    public final void updateState$kotlinx_coroutines_core(String state, Continuation<?> frame) {
        if (Intrinsics.areEqual(this._state, state) && Intrinsics.areEqual(state, DebugCoroutineInfoKt.SUSPENDED) && this.lastObservedFrame != null) {
            return;
        }
        this._state = state;
        if (!(frame instanceof CoroutineStackFrame)) {
            frame = null;
        }
        this.lastObservedFrame = (CoroutineStackFrame) frame;
        this.lastObservedThread = Intrinsics.areEqual(state, DebugCoroutineInfoKt.RUNNING) ? Thread.currentThread() : null;
    }

    public String toString() {
        return "DebugCoroutineInfo(state=" + get_state() + ",context=" + this.context + ')';
    }
}
