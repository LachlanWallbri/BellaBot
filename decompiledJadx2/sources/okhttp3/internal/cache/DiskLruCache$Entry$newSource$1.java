package okhttp3.internal.cache;

import kotlin.Metadata;
import kotlin.Unit;
import okhttp3.internal.cache.DiskLruCache;
import okio.ForwardingSource;
import okio.Source;

/* compiled from: DiskLruCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m3961d2 = {"okhttp3/internal/cache/DiskLruCache$Entry$newSource$1", "Lokio/ForwardingSource;", "closed", "", "close", "", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DiskLruCache$Entry$newSource$1 extends ForwardingSource {
    final /* synthetic */ Source $fileSource;
    private boolean closed;
    final /* synthetic */ DiskLruCache.Entry this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DiskLruCache$Entry$newSource$1(DiskLruCache.Entry entry, Source source, Source source2) {
        super(source2);
        this.this$0 = entry;
        this.$fileSource = source;
    }

    @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        if (this.closed) {
            return;
        }
        this.closed = true;
        synchronized (DiskLruCache.this) {
            this.this$0.setLockingSourceCount$okhttp(r1.getLockingSourceCount$okhttp() - 1);
            if (this.this$0.getLockingSourceCount$okhttp() == 0 && this.this$0.getZombie$okhttp()) {
                DiskLruCache.this.removeEntry$okhttp(this.this$0);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
