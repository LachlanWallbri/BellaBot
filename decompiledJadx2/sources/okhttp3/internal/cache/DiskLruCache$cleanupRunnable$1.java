package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import okio.Okio;

/* compiled from: DiskLruCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
final class DiskLruCache$cleanupRunnable$1 implements Runnable {
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$cleanupRunnable$1(DiskLruCache diskLruCache) {
        this.this$0 = diskLruCache;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.this$0) {
            if (!DiskLruCache.access$getInitialized$p(this.this$0) || this.this$0.getClosed$okhttp()) {
                return;
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
            Unit unit = Unit.INSTANCE;
        }
    }
}
