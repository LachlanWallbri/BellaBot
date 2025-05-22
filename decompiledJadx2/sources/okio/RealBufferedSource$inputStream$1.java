package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: RealBufferedSource.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"okio/RealBufferedSource$inputStream$1", "Ljava/io/InputStream;", "available", "", "close", "", "read", "data", "", TypedValues.Cycle.S_WAVE_OFFSET, "byteCount", "toString", "", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class RealBufferedSource$inputStream$1 extends InputStream {
    final /* synthetic */ RealBufferedSource this$0;

    RealBufferedSource$inputStream$1(RealBufferedSource realBufferedSource) {
        this.this$0 = realBufferedSource;
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.this$0.closed) {
            throw new IOException("closed");
        }
        if (this.this$0.bufferField.size() == 0 && this.this$0.source.read(this.this$0.bufferField, 8192) == -1) {
            return -1;
        }
        return this.this$0.bufferField.readByte() & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] data, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (this.this$0.closed) {
            throw new IOException("closed");
        }
        Util.checkOffsetAndCount(data.length, offset, byteCount);
        if (this.this$0.bufferField.size() == 0 && this.this$0.source.read(this.this$0.bufferField, 8192) == -1) {
            return -1;
        }
        return this.this$0.bufferField.read(data, offset, byteCount);
    }

    @Override // java.io.InputStream
    public int available() {
        if (this.this$0.closed) {
            throw new IOException("closed");
        }
        return (int) Math.min(this.this$0.bufferField.size(), Integer.MAX_VALUE);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.this$0.close();
    }

    public String toString() {
        return this.this$0 + ".inputStream()";
    }
}
