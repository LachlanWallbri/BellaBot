package okio;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: AsyncTimeout.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"okio/AsyncTimeout$source$1", "Lokio/Source;", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/AsyncTimeout;", "toString", "", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class AsyncTimeout$source$1 implements Source {
    final /* synthetic */ Source $source;
    final /* synthetic */ AsyncTimeout this$0;

    AsyncTimeout$source$1(AsyncTimeout asyncTimeout, Source source) {
        this.this$0 = asyncTimeout;
        this.$source = source;
    }

    @Override // okio.Source
    public long read(Buffer sink, long byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        this.this$0.enter();
        try {
            try {
                long read = this.$source.read(sink, byteCount);
                this.this$0.exit$jvm(true);
                return read;
            } catch (IOException e) {
                throw this.this$0.exit$jvm(e);
            }
        } catch (Throwable th) {
            this.this$0.exit$jvm(false);
            throw th;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.this$0.enter();
        try {
            try {
                this.$source.close();
                this.this$0.exit$jvm(true);
            } catch (IOException e) {
                throw this.this$0.exit$jvm(e);
            }
        } catch (Throwable th) {
            this.this$0.exit$jvm(false);
            throw th;
        }
    }

    @Override // okio.Source
    /* renamed from: timeout, reason: from getter */
    public AsyncTimeout getThis$0() {
        return this.this$0;
    }

    public String toString() {
        return "AsyncTimeout.source(" + this.$source + ')';
    }
}
