package okhttp3.internal.ws;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.concurrent.Task;
import okio.ByteString;

/* compiled from: TaskQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0005"}, m3961d2 = {"okhttp3/internal/concurrent/TaskQueue$execute$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp", "okhttp3/internal/ws/RealWebSocket$$special$$inlined$execute$1"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1 extends Task {
    final /* synthetic */ boolean $cancelable;
    final /* synthetic */ Ref.ObjectRef $messageOrClose$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ ByteString $pong$inlined;
    final /* synthetic */ Ref.ObjectRef $readerToClose$inlined;
    final /* synthetic */ Ref.IntRef $receivedCloseCode$inlined;
    final /* synthetic */ Ref.ObjectRef $receivedCloseReason$inlined;
    final /* synthetic */ Ref.ObjectRef $streamsToClose$inlined;
    final /* synthetic */ WebSocketWriter $writer$inlined;
    final /* synthetic */ Ref.ObjectRef $writerToClose$inlined;
    final /* synthetic */ RealWebSocket this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1(String str, boolean z, String str2, boolean z2, RealWebSocket realWebSocket, WebSocketWriter webSocketWriter, ByteString byteString, Ref.ObjectRef objectRef, Ref.IntRef intRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Ref.ObjectRef objectRef4, Ref.ObjectRef objectRef5) {
        super(str2, z2);
        this.$name = str;
        this.$cancelable = z;
        this.this$0 = realWebSocket;
        this.$writer$inlined = webSocketWriter;
        this.$pong$inlined = byteString;
        this.$messageOrClose$inlined = objectRef;
        this.$receivedCloseCode$inlined = intRef;
        this.$receivedCloseReason$inlined = objectRef2;
        this.$streamsToClose$inlined = objectRef3;
        this.$readerToClose$inlined = objectRef4;
        this.$writerToClose$inlined = objectRef5;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        this.this$0.cancel();
        return -1L;
    }
}
