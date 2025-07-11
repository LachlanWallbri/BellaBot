package okio;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Pipe.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"okio/Pipe$source$1", "Lokio/Source;", "timeout", "Lokio/Timeout;", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class Pipe$source$1 implements Source {
    final /* synthetic */ Pipe this$0;
    private final Timeout timeout = new Timeout();

    Pipe$source$1(Pipe pipe) {
        this.this$0 = pipe;
    }

    @Override // okio.Source
    public long read(Buffer sink, long byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        synchronized (this.this$0.getBuffer$jvm()) {
            if (!(!this.this$0.getSourceClosed$jvm())) {
                throw new IllegalStateException("closed".toString());
            }
            while (this.this$0.getBuffer$jvm().size() == 0) {
                if (this.this$0.getSinkClosed$jvm()) {
                    return -1L;
                }
                this.timeout.waitUntilNotified(this.this$0.getBuffer$jvm());
            }
            long read = this.this$0.getBuffer$jvm().read(sink, byteCount);
            Buffer buffer$jvm = this.this$0.getBuffer$jvm();
            if (buffer$jvm == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
            }
            buffer$jvm.notifyAll();
            return read;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.this$0.getBuffer$jvm()) {
            this.this$0.setSourceClosed$jvm(true);
            Buffer buffer$jvm = this.this$0.getBuffer$jvm();
            if (buffer$jvm == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
            }
            buffer$jvm.notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // okio.Source
    /* renamed from: timeout, reason: from getter */
    public Timeout getTimeout() {
        return this.timeout;
    }
}
