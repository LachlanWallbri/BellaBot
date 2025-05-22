package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.http2.Http2Connection;

/* compiled from: TaskQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0006"}, m3961d2 = {"okhttp3/internal/concurrent/TaskQueue$execute$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp", "okhttp3/internal/http2/Http2Connection$ReaderRunnable$$special$$inlined$execute$2", "okhttp3/internal/http2/Http2Connection$ReaderRunnable$$special$$inlined$synchronized$lambda$1"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$synchronized$lambda$1 */
/* loaded from: classes8.dex */
public final class C8033x35c08330 extends Task {
    final /* synthetic */ boolean $cancelable;
    final /* synthetic */ boolean $clearPrevious$inlined;
    final /* synthetic */ Ref.LongRef $delta$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ Ref.ObjectRef $newPeerSettings$inlined;
    final /* synthetic */ Settings $settings$inlined;
    final /* synthetic */ Ref.ObjectRef $streamsToNotify$inlined;
    final /* synthetic */ Http2Connection.ReaderRunnable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C8033x35c08330(String str, boolean z, String str2, boolean z2, Http2Connection.ReaderRunnable readerRunnable, boolean z3, Ref.ObjectRef objectRef, Settings settings, Ref.LongRef longRef, Ref.ObjectRef objectRef2) {
        super(str2, z2);
        this.$name = str;
        this.$cancelable = z;
        this.this$0 = readerRunnable;
        this.$clearPrevious$inlined = z3;
        this.$newPeerSettings$inlined = objectRef;
        this.$settings$inlined = settings;
        this.$delta$inlined = longRef;
        this.$streamsToNotify$inlined = objectRef2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        Http2Connection.this.getListener$okhttp().onSettings(Http2Connection.this, (Settings) this.$newPeerSettings$inlined.element);
        return -1L;
    }
}
