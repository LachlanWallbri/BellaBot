package com.pudutech.pd_network.storage.minio;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProgressInputStream.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\"\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\tH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/minio/ProgressInputStream;", "Ljava/io/FilterInputStream;", "in", "Ljava/io/InputStream;", "listener", "Lcom/pudutech/pd_network/storage/minio/UploadListener;", "(Ljava/io/InputStream;Lcom/pudutech/pd_network/storage/minio/UploadListener;)V", "monitor", "nread", "", "size", "close", "", "getListener", "notifyProcess", "read", "", "b", "", "off", "len", "reset", "skip", "n", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ProgressInputStream extends FilterInputStream {
    private final UploadListener monitor;
    private long nread;
    private long size;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgressInputStream(InputStream in, UploadListener uploadListener) {
        super(in);
        long j;
        Intrinsics.checkParameterIsNotNull(in, "in");
        try {
            j = in.available();
        } catch (IOException unused) {
            j = 0;
        }
        this.size = j;
        this.monitor = uploadListener;
    }

    /* renamed from: getListener, reason: from getter */
    public final UploadListener getMonitor() {
        return this.monitor;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = this.in.read();
        if (read >= 0) {
            this.nread++;
            notifyProcess(this.nread);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b) throws IOException {
        int read = this.in.read(b);
        if (read > 0) {
            this.nread += read;
            notifyProcess(this.nread);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int read = this.in.read(b, off, len);
        if (read > 0) {
            this.nread += read;
            notifyProcess(this.nread);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        long skip = this.in.skip(n);
        if (skip > 0) {
            this.nread += skip;
            notifyProcess(this.nread);
        }
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        this.in.reset();
        this.nread = this.size - this.in.available();
    }

    private final void notifyProcess(long read) {
        UploadListener uploadListener = this.monitor;
        if (uploadListener != null) {
            uploadListener.onProgress(read, this.size);
        }
    }
}
