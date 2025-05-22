package okhttp3.internal.connection;

import java.net.Proxy;
import java.net.URI;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;

/* compiled from: RouteSelector.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, m3961d2 = {"selectProxies", "", "Ljava/net/Proxy;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class RouteSelector$resetNextProxy$1 extends Lambda implements Function0<List<? extends Proxy>> {
    final /* synthetic */ Proxy $proxy;
    final /* synthetic */ HttpUrl $url;
    final /* synthetic */ RouteSelector this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteSelector$resetNextProxy$1(RouteSelector routeSelector, Proxy proxy, HttpUrl httpUrl) {
        super(0);
        this.this$0 = routeSelector;
        this.$proxy = proxy;
        this.$url = httpUrl;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends Proxy> invoke() {
        Proxy proxy = this.$proxy;
        if (proxy != null) {
            return CollectionsKt.listOf(proxy);
        }
        URI uri = this.$url.uri();
        if (uri.getHost() == null) {
            return Util.immutableListOf(new Proxy[]{Proxy.NO_PROXY});
        }
        List<Proxy> select = RouteSelector.access$getAddress$p(this.this$0).proxySelector().select(uri);
        List<Proxy> list = select;
        return list == null || list.isEmpty() ? Util.immutableListOf(new Proxy[]{Proxy.NO_PROXY}) : Util.toImmutableList(select);
    }
}
