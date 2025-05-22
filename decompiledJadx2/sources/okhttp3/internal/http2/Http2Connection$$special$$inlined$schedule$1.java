package okhttp3.internal.http2;

import kotlin.Metadata;
import okhttp3.internal.concurrent.Task;

/* compiled from: TaskQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, m3961d2 = {"okhttp3/internal/concurrent/TaskQueue$schedule$2", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Http2Connection$$special$$inlined$schedule$1 extends Task {
    final /* synthetic */ String $name;
    final /* synthetic */ long $pingIntervalNanos$inlined;
    final /* synthetic */ Http2Connection this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Http2Connection$$special$$inlined$schedule$1(String str, String str2, Http2Connection http2Connection, long j) {
        super(str2, false, 2, null);
        this.$name = str;
        this.this$0 = http2Connection;
        this.$pingIntervalNanos$inlined = j;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        boolean z;
        synchronized (this.this$0) {
            if (Http2Connection.access$getIntervalPongsReceived$p(this.this$0) < Http2Connection.access$getIntervalPingsSent$p(this.this$0)) {
                z = true;
            } else {
                Http2Connection http2Connection = this.this$0;
                Http2Connection.access$setIntervalPingsSent$p(http2Connection, Http2Connection.access$getIntervalPingsSent$p(http2Connection) + 1);
                z = false;
            }
        }
        if (z) {
            Http2Connection.access$failConnection(this.this$0, null);
            return -1L;
        }
        this.this$0.writePing(false, 1, 0);
        return this.$pingIntervalNanos$inlined;
    }
}
