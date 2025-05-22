package io.netty.resolver.dns;

import io.netty.util.NetUtil;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DefaultDnsServerAddressStreamProvider implements DnsServerAddressStreamProvider {
    private static final DnsServerAddresses DEFAULT_NAME_SERVERS;
    private static final InetSocketAddress[] DEFAULT_NAME_SERVER_ARRAY;
    private static final List<InetSocketAddress> DEFAULT_NAME_SERVER_LIST;
    static final int DNS_PORT = 53;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultDnsServerAddressStreamProvider.class);
    public static final DefaultDnsServerAddressStreamProvider INSTANCE = new DefaultDnsServerAddressStreamProvider();

    static {
        URI uri;
        String host;
        ArrayList arrayList = new ArrayList(2);
        Hashtable hashtable = new Hashtable();
        hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        hashtable.put("java.naming.provider.url", "dns://");
        try {
            String str = (String) new InitialDirContext(hashtable).getEnvironment().get("java.naming.provider.url");
            if (str != null && !str.isEmpty()) {
                for (String str2 : str.split(" ")) {
                    try {
                        uri = new URI(str2);
                        host = new URI(str2).getHost();
                    } catch (URISyntaxException e) {
                        logger.debug("Skipping a malformed nameserver URI: {}", str2, e);
                    }
                    if (host != null && !host.isEmpty()) {
                        int port = uri.getPort();
                        String host2 = uri.getHost();
                        if (port == -1) {
                            port = 53;
                        }
                        arrayList.add(SocketUtils.socketAddress(host2, port));
                    }
                    logger.debug("Skipping a nameserver URI as host portion could not be extracted: {}", str2);
                }
            }
        } catch (NamingException unused) {
        }
        if (arrayList.isEmpty()) {
            try {
                Class<?> cls = Class.forName("sun.net.dns.ResolverConfiguration");
                for (String str3 : (List) cls.getMethod("nameservers", new Class[0]).invoke(cls.getMethod("open", new Class[0]).invoke(null, new Object[0]), new Object[0])) {
                    if (str3 != null) {
                        arrayList.add(new InetSocketAddress(SocketUtils.addressByName(str3), 53));
                    }
                }
            } catch (Exception unused2) {
            }
        }
        if (!arrayList.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Default DNS servers: {} (sun.net.dns.ResolverConfiguration)", arrayList);
            }
        } else {
            if (NetUtil.isIpV6AddressesPreferred() || ((NetUtil.LOCALHOST instanceof Inet6Address) && !NetUtil.isIpV4StackPreferred())) {
                Collections.addAll(arrayList, SocketUtils.socketAddress("2001:4860:4860::8888", 53), SocketUtils.socketAddress("2001:4860:4860::8844", 53));
            } else {
                Collections.addAll(arrayList, SocketUtils.socketAddress("8.8.8.8", 53), SocketUtils.socketAddress("8.8.4.4", 53));
            }
            if (logger.isWarnEnabled()) {
                logger.warn("Default DNS servers: {} (Google Public DNS as a fallback)", arrayList);
            }
        }
        DEFAULT_NAME_SERVER_LIST = Collections.unmodifiableList(arrayList);
        InetSocketAddress[] inetSocketAddressArr = (InetSocketAddress[]) arrayList.toArray(new InetSocketAddress[arrayList.size()]);
        DEFAULT_NAME_SERVER_ARRAY = inetSocketAddressArr;
        DEFAULT_NAME_SERVERS = DnsServerAddresses.sequential(inetSocketAddressArr);
    }

    private DefaultDnsServerAddressStreamProvider() {
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStreamProvider
    public DnsServerAddressStream nameServerAddressStream(String str) {
        return DEFAULT_NAME_SERVERS.stream();
    }

    public static List<InetSocketAddress> defaultAddressList() {
        return DEFAULT_NAME_SERVER_LIST;
    }

    public static DnsServerAddresses defaultAddresses() {
        return DEFAULT_NAME_SERVERS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InetSocketAddress[] defaultAddressArray() {
        return (InetSocketAddress[]) DEFAULT_NAME_SERVER_ARRAY.clone();
    }
}
