package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.http2.Http2Connection;

/* compiled from: Util.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0004"}, m3961d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1", "okhttp3/internal/http2/Http2Connection$ReaderRunnable$$special$$inlined$execute$2"}, m3962k = 3, m3963mv = {1, 1, 15})
/* renamed from: okhttp3.internal.http2.Http2Connection$ReaderRunnable$settings$$inlined$synchronized$lambda$1 */
/* loaded from: classes2.dex */
public final class RunnableC8036x381885c0 implements Runnable {
    final /* synthetic */ boolean $clearPrevious$inlined;
    final /* synthetic */ Ref.LongRef $delta$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ Settings $settings$inlined;
    final /* synthetic */ Ref.ObjectRef $streamsToNotify$inlined;
    final /* synthetic */ Http2Connection.ReaderRunnable this$0;

    public RunnableC8036x381885c0(String str, Http2Connection.ReaderRunnable readerRunnable, boolean z, Settings settings, Ref.LongRef longRef, Ref.ObjectRef objectRef) {
        this.$name = str;
        this.this$0 = readerRunnable;
        this.$clearPrevious$inlined = z;
        this.$settings$inlined = settings;
        this.$delta$inlined = longRef;
        this.$streamsToNotify$inlined = objectRef;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.$name;
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(str);
        try {
            Http2Connection.this.getListener$okhttp().onSettings(Http2Connection.this);
        } finally {
            currentThread.setName(name);
        }
    }
}
