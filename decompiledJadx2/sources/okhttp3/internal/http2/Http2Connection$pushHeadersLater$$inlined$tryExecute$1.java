package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Util.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, m3961d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class Http2Connection$pushHeadersLater$$inlined$tryExecute$1 implements Runnable {
    final /* synthetic */ boolean $inFinished$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ List $requestHeaders$inlined;
    final /* synthetic */ int $streamId$inlined;
    final /* synthetic */ Http2Connection this$0;

    public Http2Connection$pushHeadersLater$$inlined$tryExecute$1(String str, Http2Connection http2Connection, int i, List list, boolean z) {
        this.$name = str;
        this.this$0 = http2Connection;
        this.$streamId$inlined = i;
        this.$requestHeaders$inlined = list;
        this.$inFinished$inlined = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.$name;
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(str);
        try {
            boolean onHeaders = Http2Connection.access$getPushObserver$p(this.this$0).onHeaders(this.$streamId$inlined, this.$requestHeaders$inlined, this.$inFinished$inlined);
            if (onHeaders) {
                try {
                    this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                } catch (IOException unused) {
                }
            }
            if (onHeaders || this.$inFinished$inlined) {
                synchronized (this.this$0) {
                    Http2Connection.access$getCurrentPushRequests$p(this.this$0).remove(Integer.valueOf(this.$streamId$inlined));
                }
            }
        } finally {
            currentThread.setName(name);
        }
    }
}
