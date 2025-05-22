package okhttp3.internal.http2;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2Connection;

/* compiled from: Util.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, m3961d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, m3962k = 3, m3963mv = {1, 1, 15})
/* renamed from: okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$tryExecute$1 */
/* loaded from: classes2.dex */
public final class RunnableC8034x92fc2bb implements Runnable {
    final /* synthetic */ String $name;
    final /* synthetic */ Settings $peerSettings$inlined;
    final /* synthetic */ Http2Connection.ReaderRunnable this$0;

    public RunnableC8034x92fc2bb(String str, Http2Connection.ReaderRunnable readerRunnable, Settings settings) {
        this.$name = str;
        this.this$0 = readerRunnable;
        this.$peerSettings$inlined = settings;
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
                Http2Connection.this.getWriter().applyAndAckSettings(this.$peerSettings$inlined);
            } catch (IOException e) {
                Http2Connection.access$failConnection(Http2Connection.this, e);
            }
        } finally {
            currentThread.setName(name);
        }
    }
}
