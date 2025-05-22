package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2Connection;

/* compiled from: Util.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, m3961d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class Http2Connection$ReaderRunnable$ping$$inlined$tryExecute$1 implements Runnable {
    final /* synthetic */ String $name;
    final /* synthetic */ int $payload1$inlined;
    final /* synthetic */ int $payload2$inlined;
    final /* synthetic */ Http2Connection.ReaderRunnable this$0;

    public Http2Connection$ReaderRunnable$ping$$inlined$tryExecute$1(String str, Http2Connection.ReaderRunnable readerRunnable, int i, int i2) {
        this.$name = str;
        this.this$0 = readerRunnable;
        this.$payload1$inlined = i;
        this.$payload2$inlined = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.$name;
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(str);
        try {
            Http2Connection.this.writePing(true, this.$payload1$inlined, this.$payload2$inlined);
        } finally {
            currentThread.setName(name);
        }
    }
}
