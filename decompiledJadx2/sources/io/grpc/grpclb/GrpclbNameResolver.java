package io.grpc.grpclb;

import com.google.common.base.Stopwatch;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.internal.DnsNameResolver;
import io.grpc.internal.SharedResourceHolder;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class GrpclbNameResolver extends DnsNameResolver {
    private static final String GRPCLB_NAME_PREFIX = "_grpclb._tcp.";
    private static final Logger logger = Logger.getLogger(GrpclbNameResolver.class.getName());

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpclbNameResolver(@Nullable String str, String str2, NameResolver.Args args, SharedResourceHolder.Resource<Executor> resource, Stopwatch stopwatch, boolean z) {
        super(str, str2, args, resource, stopwatch, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.DnsNameResolver
    public DnsNameResolver.InternalResolutionResult doResolve(boolean z) {
        List<EquivalentAddressGroup> resolveBalancerAddresses = resolveBalancerAddresses();
        DnsNameResolver.InternalResolutionResult doResolve = super.doResolve(!resolveBalancerAddresses.isEmpty());
        if (!resolveBalancerAddresses.isEmpty()) {
            doResolve.attributes = Attributes.newBuilder().set(GrpclbConstants.ATTR_LB_ADDRS, resolveBalancerAddresses).build();
        }
        return doResolve;
    }

    private List<EquivalentAddressGroup> resolveBalancerAddresses() {
        List<DnsNameResolver.SrvRecord> emptyList = Collections.emptyList();
        DnsNameResolver.ResourceResolver resourceResolver = getResourceResolver();
        Exception exc = null;
        if (resourceResolver != null) {
            try {
                emptyList = resourceResolver.resolveSrv(GRPCLB_NAME_PREFIX + getHost());
            } catch (Exception e) {
                e = e;
            }
        }
        e = null;
        ArrayList arrayList = new ArrayList(emptyList.size());
        Level level = Level.WARNING;
        for (DnsNameResolver.SrvRecord srvRecord : emptyList) {
            try {
                String substring = srvRecord.host.substring(0, srvRecord.host.length() - 1);
                List<InetAddress> resolveAddress = this.addressResolver.resolveAddress(srvRecord.host);
                ArrayList arrayList2 = new ArrayList(resolveAddress.size());
                Iterator<InetAddress> it = resolveAddress.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new InetSocketAddress(it.next(), srvRecord.port));
                }
                arrayList.add(new EquivalentAddressGroup((List<SocketAddress>) Collections.unmodifiableList(arrayList2), Attributes.newBuilder().set(GrpclbConstants.ATTR_LB_ADDR_AUTHORITY, substring).build()));
            } catch (Exception e2) {
                logger.log(level, "Can't find address for SRV record " + srvRecord, (Throwable) e2);
                if (exc == null) {
                    level = Level.FINE;
                    exc = e2;
                }
            }
        }
        if (e != null) {
            logger.log(Level.FINE, "SRV lookup failure", (Throwable) e);
        } else if (exc != null && arrayList.isEmpty()) {
            logger.log(Level.FINE, "SRV-provided hostname lookup failure", (Throwable) exc);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.DnsNameResolver
    public void setAddressResolver(DnsNameResolver.AddressResolver addressResolver) {
        super.setAddressResolver(addressResolver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.DnsNameResolver
    public void setResourceResolver(DnsNameResolver.ResourceResolver resourceResolver) {
        super.setResourceResolver(resourceResolver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.DnsNameResolver
    public String getHost() {
        return super.getHost();
    }

    static void setEnableTxt(boolean z) {
        DnsNameResolver.enableTxt = z;
    }
}
