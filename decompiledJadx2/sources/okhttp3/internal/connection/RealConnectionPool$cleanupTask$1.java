package okhttp3.internal.connection;

import kotlin.Metadata;
import okhttp3.internal.concurrent.Task;

/* compiled from: RealConnectionPool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"okhttp3/internal/connection/RealConnectionPool$cleanupTask$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RealConnectionPool$cleanupTask$1 extends Task {
    final /* synthetic */ RealConnectionPool this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RealConnectionPool$cleanupTask$1(RealConnectionPool realConnectionPool, String str) {
        super(str, false, 2, null);
        this.this$0 = realConnectionPool;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        return this.this$0.cleanup(System.nanoTime());
    }
}
