package okio;

import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Pipe.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"okio/Pipe$sink$1", "Lokio/Sink;", "timeout", "Lokio/Timeout;", "close", "", "flush", "write", MapElement.Source.SOURCE, "Lokio/Buffer;", "byteCount", "", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class Pipe$sink$1 implements Sink {
    final /* synthetic */ Pipe this$0;
    private final Timeout timeout = new Timeout();

    Pipe$sink$1(Pipe pipe) {
        this.this$0 = pipe;
    }

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) {
        boolean hasDeadline;
        Intrinsics.checkParameterIsNotNull(source, "source");
        Sink sink = (Sink) null;
        synchronized (this.this$0.getBuffer$jvm()) {
            if (!(!this.this$0.getSinkClosed$jvm())) {
                throw new IllegalStateException("closed".toString());
            }
            while (true) {
                if (byteCount <= 0) {
                    break;
                }
                Sink foldedSink$jvm = this.this$0.getFoldedSink$jvm();
                if (foldedSink$jvm != null) {
                    sink = foldedSink$jvm;
                    break;
                }
                if (this.this$0.getSourceClosed$jvm()) {
                    throw new IOException("source is closed");
                }
                long maxBufferSize$jvm = this.this$0.getMaxBufferSize$jvm() - this.this$0.getBuffer$jvm().size();
                if (maxBufferSize$jvm == 0) {
                    this.timeout.waitUntilNotified(this.this$0.getBuffer$jvm());
                } else {
                    long min = Math.min(maxBufferSize$jvm, byteCount);
                    this.this$0.getBuffer$jvm().write(source, min);
                    byteCount -= min;
                    Buffer buffer$jvm = this.this$0.getBuffer$jvm();
                    if (buffer$jvm == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer$jvm.notifyAll();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        if (sink != null) {
            Pipe pipe = this.this$0;
            Timeout timeout = sink.getTimeout();
            Timeout timeout2 = pipe.sink().getTimeout();
            long timeoutNanos = timeout.timeoutNanos();
            timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
            if (timeout.hasDeadline()) {
                long deadlineNanoTime = timeout.deadlineNanoTime();
                if (timeout2.hasDeadline()) {
                    timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                }
                try {
                    sink.write(source, byteCount);
                    if (hasDeadline) {
                        return;
                    } else {
                        return;
                    }
                } finally {
                    timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout2.hasDeadline()) {
                        timeout.deadlineNanoTime(deadlineNanoTime);
                    }
                }
            }
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
            }
            try {
                sink.write(source, byteCount);
            } finally {
                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.clearDeadline();
                }
            }
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        boolean hasDeadline;
        Sink sink = (Sink) null;
        synchronized (this.this$0.getBuffer$jvm()) {
            if (!(!this.this$0.getSinkClosed$jvm())) {
                throw new IllegalStateException("closed".toString());
            }
            Sink foldedSink$jvm = this.this$0.getFoldedSink$jvm();
            if (foldedSink$jvm != null) {
                sink = foldedSink$jvm;
            } else if (this.this$0.getSourceClosed$jvm() && this.this$0.getBuffer$jvm().size() > 0) {
                throw new IOException("source is closed");
            }
            Unit unit = Unit.INSTANCE;
        }
        if (sink != null) {
            Pipe pipe = this.this$0;
            Timeout timeout = sink.getTimeout();
            Timeout timeout2 = pipe.sink().getTimeout();
            long timeoutNanos = timeout.timeoutNanos();
            timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
            if (timeout.hasDeadline()) {
                long deadlineNanoTime = timeout.deadlineNanoTime();
                if (timeout2.hasDeadline()) {
                    timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                }
                try {
                    sink.flush();
                    if (hasDeadline) {
                        return;
                    } else {
                        return;
                    }
                } finally {
                    timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout2.hasDeadline()) {
                        timeout.deadlineNanoTime(deadlineNanoTime);
                    }
                }
            }
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
            }
            try {
                sink.flush();
            } finally {
                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.clearDeadline();
                }
            }
        }
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        boolean hasDeadline;
        Sink sink = (Sink) null;
        synchronized (this.this$0.getBuffer$jvm()) {
            if (this.this$0.getSinkClosed$jvm()) {
                return;
            }
            Sink foldedSink$jvm = this.this$0.getFoldedSink$jvm();
            if (foldedSink$jvm != null) {
                sink = foldedSink$jvm;
            } else {
                if (this.this$0.getSourceClosed$jvm() && this.this$0.getBuffer$jvm().size() > 0) {
                    throw new IOException("source is closed");
                }
                this.this$0.setSinkClosed$jvm(true);
                Buffer buffer$jvm = this.this$0.getBuffer$jvm();
                if (buffer$jvm == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                }
                buffer$jvm.notifyAll();
            }
            Unit unit = Unit.INSTANCE;
            if (sink != null) {
                Pipe pipe = this.this$0;
                Timeout timeout = sink.getTimeout();
                Timeout timeout2 = pipe.sink().getTimeout();
                long timeoutNanos = timeout.timeoutNanos();
                timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
                if (timeout.hasDeadline()) {
                    long deadlineNanoTime = timeout.deadlineNanoTime();
                    if (timeout2.hasDeadline()) {
                        timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                    }
                    try {
                        sink.close();
                        if (hasDeadline) {
                            return;
                        } else {
                            return;
                        }
                    } finally {
                        timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(deadlineNanoTime);
                        }
                    }
                }
                if (timeout2.hasDeadline()) {
                    timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                }
                try {
                    sink.close();
                } finally {
                    timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout2.hasDeadline()) {
                        timeout.clearDeadline();
                    }
                }
            }
        }
    }

    @Override // okio.Sink
    /* renamed from: timeout, reason: from getter */
    public Timeout getTimeout() {
        return this.timeout;
    }
}
