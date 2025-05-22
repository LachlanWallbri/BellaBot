package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.internal.concurrent.Task;
import okio.Okio;

/* compiled from: DiskLruCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DiskLruCache$cleanupTask$1 extends Task {
    final /* synthetic */ DiskLruCache this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DiskLruCache$cleanupTask$1(DiskLruCache diskLruCache, String str) {
        super(str, false, 2, null);
        this.this$0 = diskLruCache;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        synchronized (this.this$0) {
            if (!DiskLruCache.access$getInitialized$p(this.this$0) || this.this$0.getClosed$okhttp()) {
                return -1L;
            }
            try {
                this.this$0.trimToSize();
            } catch (IOException unused) {
                DiskLruCache.access$setMostRecentTrimFailed$p(this.this$0, true);
            }
            try {
                if (DiskLruCache.access$journalRebuildRequired(this.this$0)) {
                    this.this$0.rebuildJournal$okhttp();
                    DiskLruCache.access$setRedundantOpCount$p(this.this$0, 0);
                }
            } catch (IOException unused2) {
                DiskLruCache.access$setMostRecentRebuildFailed$p(this.this$0, true);
                DiskLruCache.access$setJournalWriter$p(this.this$0, Okio.buffer(Okio.blackhole()));
            }
            return -1L;
        }
    }
}
