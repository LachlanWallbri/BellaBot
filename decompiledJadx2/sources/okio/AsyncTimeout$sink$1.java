package okio;

import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: AsyncTimeout.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, m3961d2 = {"okio/AsyncTimeout$sink$1", "Lokio/Sink;", "close", "", "flush", "timeout", "Lokio/AsyncTimeout;", "toString", "", "write", MapElement.Source.SOURCE, "Lokio/Buffer;", "byteCount", "", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class AsyncTimeout$sink$1 implements Sink {
    final /* synthetic */ Sink $sink;
    final /* synthetic */ AsyncTimeout this$0;

    AsyncTimeout$sink$1(AsyncTimeout asyncTimeout, Sink sink) {
        this.this$0 = asyncTimeout;
        this.$sink = sink;
    }

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        Util.checkOffsetAndCount(source.size(), 0L, byteCount);
        while (true) {
            long j = 0;
            if (byteCount <= 0) {
                return;
            }
            Segment segment = source.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            while (true) {
                if (j >= 65536) {
                    break;
                }
                j += segment.limit - segment.pos;
                if (j >= byteCount) {
                    j = byteCount;
                    break;
                } else {
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                }
            }
            this.this$0.enter();
            try {
                try {
                    this.$sink.write(source, j);
                    byteCount -= j;
                    this.this$0.exit$jvm(true);
                } catch (IOException e) {
                    throw this.this$0.exit$jvm(e);
                }
            } catch (Throwable th) {
                this.this$0.exit$jvm(false);
                throw th;
            }
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.this$0.enter();
        try {
            try {
                this.$sink.flush();
                this.this$0.exit$jvm(true);
            } catch (IOException e) {
                throw this.this$0.exit$jvm(e);
            }
        } catch (Throwable th) {
            this.this$0.exit$jvm(false);
            throw th;
        }
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.this$0.enter();
        try {
            try {
                this.$sink.close();
                this.this$0.exit$jvm(true);
            } catch (IOException e) {
                throw this.this$0.exit$jvm(e);
            }
        } catch (Throwable th) {
            this.this$0.exit$jvm(false);
            throw th;
        }
    }

    @Override // okio.Sink
    /* renamed from: timeout, reason: from getter */
    public AsyncTimeout getThis$0() {
        return this.this$0;
    }

    public String toString() {
        return "AsyncTimeout.sink(" + this.$sink + ')';
    }
}
