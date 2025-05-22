package okhttp3.internal.http2;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Util.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, m3961d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class Http2Connection$writeWindowUpdateLater$$inlined$tryExecute$1 implements Runnable {
    final /* synthetic */ String $name;
    final /* synthetic */ int $streamId$inlined;
    final /* synthetic */ long $unacknowledgedBytesRead$inlined;
    final /* synthetic */ Http2Connection this$0;

    public Http2Connection$writeWindowUpdateLater$$inlined$tryExecute$1(String str, Http2Connection http2Connection, int i, long j) {
        this.$name = str;
        this.this$0 = http2Connection;
        this.$streamId$inlined = i;
        this.$unacknowledgedBytesRead$inlined = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.$name;
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(str);
        try {
            try {
                this.this$0.getWriter().windowUpdate(this.$streamId$inlined, this.$unacknowledgedBytesRead$inlined);
            } catch (IOException e) {
                Http2Connection.access$failConnection(this.this$0, e);
            }
        } finally {
            currentThread.setName(name);
        }
    }
}
