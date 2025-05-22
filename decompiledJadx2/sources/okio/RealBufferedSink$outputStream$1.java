package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: RealBufferedSink.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000bH\u0016¨\u0006\u000e"}, m3961d2 = {"okio/RealBufferedSink$outputStream$1", "Ljava/io/OutputStream;", "close", "", "flush", "toString", "", "write", "data", "", TypedValues.Cycle.S_WAVE_OFFSET, "", "byteCount", "b", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class RealBufferedSink$outputStream$1 extends OutputStream {
    final /* synthetic */ RealBufferedSink this$0;

    RealBufferedSink$outputStream$1(RealBufferedSink realBufferedSink) {
        this.this$0 = realBufferedSink;
    }

    @Override // java.io.OutputStream
    public void write(int b) {
        if (this.this$0.closed) {
            throw new IOException("closed");
        }
        this.this$0.bufferField.writeByte((int) ((byte) b));
        this.this$0.emitCompleteSegments();
    }

    @Override // java.io.OutputStream
    public void write(byte[] data, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (this.this$0.closed) {
            throw new IOException("closed");
        }
        this.this$0.bufferField.write(data, offset, byteCount);
        this.this$0.emitCompleteSegments();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        if (this.this$0.closed) {
            return;
        }
        this.this$0.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.this$0.close();
    }

    public String toString() {
        return this.this$0 + ".outputStream()";
    }
}
