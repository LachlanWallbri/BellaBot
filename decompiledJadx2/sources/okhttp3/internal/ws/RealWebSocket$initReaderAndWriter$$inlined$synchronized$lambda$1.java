package okhttp3.internal.ws;

import kotlin.Metadata;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.ws.RealWebSocket;

/* compiled from: TaskQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0005"}, m3961d2 = {"okhttp3/internal/concurrent/TaskQueue$schedule$2", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp", "okhttp3/internal/ws/RealWebSocket$$special$$inlined$schedule$1"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RealWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1 extends Task {
    final /* synthetic */ WebSocketExtensions $extensions$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ String $name$inlined;
    final /* synthetic */ long $pingIntervalNanos$inlined;
    final /* synthetic */ RealWebSocket.Streams $streams$inlined;
    final /* synthetic */ RealWebSocket this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1(String str, String str2, long j, RealWebSocket realWebSocket, String str3, RealWebSocket.Streams streams, WebSocketExtensions webSocketExtensions) {
        super(str2, false, 2, null);
        this.$name = str;
        this.$pingIntervalNanos$inlined = j;
        this.this$0 = realWebSocket;
        this.$name$inlined = str3;
        this.$streams$inlined = streams;
        this.$extensions$inlined = webSocketExtensions;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        this.this$0.writePingFrame$okhttp();
        return this.$pingIntervalNanos$inlined;
    }
}
