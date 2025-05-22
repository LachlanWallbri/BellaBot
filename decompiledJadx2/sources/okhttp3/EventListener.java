package okhttp3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class EventListener {
    public static final EventListener NONE = new EventListener() { // from class: okhttp3.EventListener.1
    };

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public interface Factory {
        EventListener create(Call call);
    }

    public void callEnd(Call call) {
    }

    public void callFailed(Call call, IOException iOException) {
    }

    public void callStart(Call call) {
    }

    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
    }

    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException iOException) {
    }

    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
    }

    public void connectionAcquired(Call call, Connection connection) {
    }

    public void connectionReleased(Call call, Connection connection) {
    }

    public void dnsEnd(Call call, String str, List<InetAddress> list) {
    }

    public void dnsStart(Call call, String str) {
    }

    public void requestBodyEnd(Call call, long j) {
    }

    public void requestBodyStart(Call call) {
    }

    public void requestHeadersEnd(Call call, Request request) {
    }

    public void requestHeadersStart(Call call) {
    }

    public void responseBodyEnd(Call call, long j) {
    }

    public void responseBodyStart(Call call) {
    }

    public void responseHeadersEnd(Call call, Response response) {
    }

    public void responseHeadersStart(Call call) {
    }

    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
    }

    public void secureConnectStart(Call call) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Factory factory(EventListener eventListener) {
        return new Factory() { // from class: okhttp3.EventListener.2
            @Override // okhttp3.EventListener.Factory
            public EventListener create(Call call) {
                return EventListener.this;
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* compiled from: EventListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m3961d2 = {"Lokhttp3/EventListener$Companion;", "", "()V", "NONE", "Lokhttp3/EventListener;", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
