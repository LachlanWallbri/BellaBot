package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Buffer.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"okio/Buffer$inputStream$1", "Ljava/io/InputStream;", "available", "", "close", "", "read", "sink", "", TypedValues.Cycle.S_WAVE_OFFSET, "byteCount", "toString", "", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class Buffer$inputStream$1 extends InputStream {
    final /* synthetic */ Buffer this$0;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    Buffer$inputStream$1(Buffer buffer) {
        this.this$0 = buffer;
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.this$0.size() > 0) {
            return this.this$0.readByte() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] sink, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        return this.this$0.read(sink, offset, byteCount);
    }

    @Override // java.io.InputStream
    public int available() {
        return (int) Math.min(this.this$0.size(), Integer.MAX_VALUE);
    }

    public String toString() {
        return this.this$0 + ".inputStream()";
    }
}
