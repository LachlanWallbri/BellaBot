package kotlinx.coroutines.test;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.test.TestCoroutineContext;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: TestCoroutineContext.kt */
@Deprecated(level = DeprecationLevel.WARNING, message = "This API has been deprecated to integrate with Structured Concurrency.", replaceWith = @ReplaceWith(expression = "TestCoroutineScope", imports = {"kotlin.coroutines.test"}))
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u0001<B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J$\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J$\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J*\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0018\u0010\u001f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020!0 J$\u0010$\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J\u0006\u0010%\u001a\u00020\u001bJ\u0014\u0010&\u001a\u00020\u001b2\n\u0010'\u001a\u00060(j\u0002`)H\u0002J5\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010+2\u0006\u0010,\u001a\u0002H+2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002H+0.H\u0016¢\u0006\u0002\u00100J(\u00101\u001a\u0004\u0018\u0001H2\"\b\b\u0000\u00102*\u00020/2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H204H\u0096\u0002¢\u0006\u0002\u00105J\u0014\u00106\u001a\u00020\u00012\n\u00103\u001a\u0006\u0012\u0002\b\u000304H\u0016J\u0010\u00107\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u001c\u00108\u001a\u00020\u00122\n\u0010'\u001a\u00060(j\u0002`)2\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\b\u00109\u001a\u00020\u0006H\u0002J\b\u0010:\u001a\u00020\u0003H\u0016J\u0006\u0010;\u001a\u00020\u001bJ\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, m3961d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext;", "Lkotlin/coroutines/CoroutineContext;", "name", "", "(Ljava/lang/String;)V", "counter", "", "ctxDispatcher", "Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "ctxHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "exceptions", "", "", "getExceptions", "()Ljava/util/List;", "queue", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/test/TimedRunnableObsolete;", "time", "uncaughtExceptions", "", "advanceTimeBy", "delayTime", "unit", "Ljava/util/concurrent/TimeUnit;", "advanceTimeTo", "", "targetTime", "assertAllUnhandledExceptions", "message", "predicate", "Lkotlin/Function1;", "", "assertAnyUnhandledException", "assertExceptions", "assertUnhandledException", "cancelAllActions", "enqueue", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", TmpConstant.PROPERTY_IDENTIFIER_GET, ExifInterface.LONGITUDE_EAST, TransferTable.COLUMN_KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "now", "postDelayed", "processNextEvent", "toString", "triggerActions", "Dispatcher", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class TestCoroutineContext implements CoroutineContext {
    private long counter;
    private final Dispatcher ctxDispatcher;
    private final CoroutineExceptionHandler ctxHandler;
    private final String name;
    private final ThreadSafeHeap<TimedRunnable> queue;
    private long time;
    private final List<Throwable> uncaughtExceptions;

    public TestCoroutineContext() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TestCoroutineContext(String str) {
        this.name = str;
        this.uncaughtExceptions = new ArrayList();
        this.ctxDispatcher = new Dispatcher();
        this.ctxHandler = new C7987x96bc1e17(CoroutineExceptionHandler.INSTANCE, this);
        this.queue = new ThreadSafeHeap<>();
    }

    public /* synthetic */ TestCoroutineContext(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }

    public final List<Throwable> getExceptions() {
        return this.uncaughtExceptions;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R initial, Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        return operation.invoke(operation.invoke(initial, this.ctxDispatcher), this.ctxHandler);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        if (key == ContinuationInterceptor.INSTANCE) {
            Dispatcher dispatcher = this.ctxDispatcher;
            if (dispatcher != null) {
                return dispatcher;
            }
            throw new TypeCastException("null cannot be cast to non-null type E");
        }
        if (key != CoroutineExceptionHandler.INSTANCE) {
            return null;
        }
        CoroutineExceptionHandler coroutineExceptionHandler = this.ctxHandler;
        if (coroutineExceptionHandler != null) {
            return coroutineExceptionHandler;
        }
        throw new TypeCastException("null cannot be cast to non-null type E");
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return key == ContinuationInterceptor.INSTANCE ? this.ctxHandler : key == CoroutineExceptionHandler.INSTANCE ? this.ctxDispatcher : this;
    }

    public static /* synthetic */ long now$default(TestCoroutineContext testCoroutineContext, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 1) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.now(timeUnit);
    }

    public final long now(TimeUnit unit) {
        return unit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public static /* synthetic */ long advanceTimeBy$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.advanceTimeBy(j, timeUnit);
    }

    public final long advanceTimeBy(long delayTime, TimeUnit unit) {
        long j = this.time;
        advanceTimeTo(unit.toNanos(delayTime) + j, TimeUnit.NANOSECONDS);
        return unit.convert(this.time - j, TimeUnit.NANOSECONDS);
    }

    public static /* synthetic */ void advanceTimeTo$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        testCoroutineContext.advanceTimeTo(j, timeUnit);
    }

    public final void advanceTimeTo(long targetTime, TimeUnit unit) {
        long nanos = unit.toNanos(targetTime);
        triggerActions(nanos);
        if (nanos > this.time) {
            this.time = nanos;
        }
    }

    public final void triggerActions() {
        triggerActions(this.time);
    }

    public final void cancelAllActions() {
        if (this.queue.isEmpty()) {
            return;
        }
        this.queue.clear();
    }

    public static /* synthetic */ void assertUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertUnhandledException(str, function1);
    }

    public final void assertUnhandledException(String message, Function1<? super Throwable, Boolean> predicate) {
        if (this.uncaughtExceptions.size() != 1 || !predicate.invoke(this.uncaughtExceptions.get(0)).booleanValue()) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    public static /* synthetic */ void assertAllUnhandledExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAllUnhandledExceptions(str, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void assertAllUnhandledExceptions(String message, Function1<? super Throwable, Boolean> predicate) {
        List<Throwable> list = this.uncaughtExceptions;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!predicate.invoke(it.next()).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        if (!z) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    public static /* synthetic */ void assertAnyUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAnyUnhandledException(str, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void assertAnyUnhandledException(String message, Function1<? super Throwable, Boolean> predicate) {
        List<Throwable> list = this.uncaughtExceptions;
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (predicate.invoke(it.next()).booleanValue()) {
                    z = true;
                    break;
                }
            }
        }
        if (!z) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    public static /* synthetic */ void assertExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertExceptions(str, function1);
    }

    public final void assertExceptions(String message, Function1<? super List<? extends Throwable>, Boolean> predicate) {
        if (!predicate.invoke(this.uncaughtExceptions).booleanValue()) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueue(Runnable block) {
        ThreadSafeHeap<TimedRunnable> threadSafeHeap = this.queue;
        long j = this.counter;
        this.counter = 1 + j;
        threadSafeHeap.addLast(new TimedRunnable(block, j, 0L, 4, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TimedRunnable postDelayed(Runnable block, long delayTime) {
        long j = this.counter;
        this.counter = 1 + j;
        TimedRunnable timedRunnable = new TimedRunnable(block, j, this.time + TimeUnit.MILLISECONDS.toNanos(delayTime));
        this.queue.addLast(timedRunnable);
        return timedRunnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long processNextEvent() {
        TimedRunnable peek = this.queue.peek();
        if (peek != null) {
            triggerActions(peek.time);
        }
        return this.queue.isEmpty() ? Long.MAX_VALUE : 0L;
    }

    private final void triggerActions(long targetTime) {
        TimedRunnable timedRunnable;
        while (true) {
            ThreadSafeHeap<TimedRunnable> threadSafeHeap = this.queue;
            synchronized (threadSafeHeap) {
                TimedRunnable firstImpl = threadSafeHeap.firstImpl();
                if (firstImpl != null) {
                    timedRunnable = (firstImpl.time > targetTime ? 1 : (firstImpl.time == targetTime ? 0 : -1)) <= 0 ? threadSafeHeap.removeAtImpl(0) : null;
                }
            }
            TimedRunnable timedRunnable2 = timedRunnable;
            if (timedRunnable2 == null) {
                return;
            }
            if (timedRunnable2.time != 0) {
                this.time = timedRunnable2.time;
            }
            timedRunnable2.run();
        }
    }

    public String toString() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        return "TestCoroutineContext@" + DebugStringsKt.getHexAddress(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: TestCoroutineContext.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u001e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u0017"}, m3961d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/Delay;", "(Lkotlinx/coroutines/test/TestCoroutineContext;)V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "processNextEvent", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "shouldBeProcessedFromContext", "", "toString", "", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    private final class Dispatcher extends EventLoop implements Delay {
        @Override // kotlinx.coroutines.EventLoop
        public boolean shouldBeProcessedFromContext() {
            return true;
        }

        public Dispatcher() {
            EventLoop.incrementUseCount$default(this, false, 1, null);
        }

        @Override // kotlinx.coroutines.Delay
        public Object delay(long j, Continuation<? super Unit> continuation) {
            return Delay.DefaultImpls.delay(this, j, continuation);
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        /* renamed from: dispatch */
        public void mo5558dispatch(CoroutineContext context, Runnable block) {
            TestCoroutineContext.this.enqueue(block);
        }

        @Override // kotlinx.coroutines.Delay
        /* renamed from: scheduleResumeAfterDelay */
        public void mo5559scheduleResumeAfterDelay(long timeMillis, final CancellableContinuation<? super Unit> continuation) {
            TestCoroutineContext.this.postDelayed(new Runnable() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$scheduleResumeAfterDelay$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    continuation.resumeUndispatched(TestCoroutineContext.Dispatcher.this, Unit.INSTANCE);
                }
            }, timeMillis);
        }

        @Override // kotlinx.coroutines.Delay
        public DisposableHandle invokeOnTimeout(long timeMillis, Runnable block) {
            final TimedRunnable postDelayed = TestCoroutineContext.this.postDelayed(block, timeMillis);
            return new DisposableHandle() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$invokeOnTimeout$1
                @Override // kotlinx.coroutines.DisposableHandle
                public void dispose() {
                    ThreadSafeHeap threadSafeHeap;
                    threadSafeHeap = TestCoroutineContext.this.queue;
                    threadSafeHeap.remove(postDelayed);
                }
            };
        }

        @Override // kotlinx.coroutines.EventLoop
        public long processNextEvent() {
            return TestCoroutineContext.this.processNextEvent();
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        public String toString() {
            return "Dispatcher(" + TestCoroutineContext.this + ')';
        }
    }
}
