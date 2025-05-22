package io.grpc.netty.shaded.io.netty.handler.ipfilter;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.ipfilter.IpSubnetFilterRule;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class IpSubnetFilter extends AbstractRemoteAddressFilter<InetSocketAddress> {
    private final boolean acceptIfNotFound;
    private final IpFilterRuleType ipFilterRuleTypeIPv4;
    private final IpFilterRuleType ipFilterRuleTypeIPv6;
    private final List<IpSubnetFilterRule> ipv4Rules;
    private final List<IpSubnetFilterRule> ipv6Rules;

    public IpSubnetFilter(IpSubnetFilterRule... ipSubnetFilterRuleArr) {
        this(true, (List<IpSubnetFilterRule>) Arrays.asList((Object[]) ObjectUtil.checkNotNull(ipSubnetFilterRuleArr, "rules")));
    }

    public IpSubnetFilter(boolean z, IpSubnetFilterRule... ipSubnetFilterRuleArr) {
        this(z, (List<IpSubnetFilterRule>) Arrays.asList((Object[]) ObjectUtil.checkNotNull(ipSubnetFilterRuleArr, "rules")));
    }

    public IpSubnetFilter(List<IpSubnetFilterRule> list) {
        this(true, list);
    }

    public IpSubnetFilter(boolean z, List<IpSubnetFilterRule> list) {
        ObjectUtil.checkNotNull(list, "rules");
        this.acceptIfNotFound = z;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (IpSubnetFilterRule ipSubnetFilterRule : list) {
            ObjectUtil.checkNotNull(ipSubnetFilterRule, "rule");
            if (ipSubnetFilterRule.getFilterRule() instanceof IpSubnetFilterRule.Ip4SubnetFilterRule) {
                arrayList.add(ipSubnetFilterRule);
                if (ipSubnetFilterRule.ruleType() == IpFilterRuleType.ACCEPT) {
                    i++;
                } else {
                    i2++;
                }
            } else {
                arrayList2.add(ipSubnetFilterRule);
                if (ipSubnetFilterRule.ruleType() == IpFilterRuleType.ACCEPT) {
                    i4++;
                } else {
                    i3++;
                }
            }
        }
        if (i == 0 && i2 > 0) {
            this.ipFilterRuleTypeIPv4 = IpFilterRuleType.REJECT;
        } else if (i > 0 && i2 == 0) {
            this.ipFilterRuleTypeIPv4 = IpFilterRuleType.ACCEPT;
        } else {
            this.ipFilterRuleTypeIPv4 = null;
        }
        if (i4 == 0 && i3 > 0) {
            this.ipFilterRuleTypeIPv6 = IpFilterRuleType.REJECT;
        } else if (i4 > 0 && i3 == 0) {
            this.ipFilterRuleTypeIPv6 = IpFilterRuleType.ACCEPT;
        } else {
            this.ipFilterRuleTypeIPv6 = null;
        }
        this.ipv4Rules = sortAndFilter(arrayList);
        this.ipv6Rules = sortAndFilter(arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.ipfilter.AbstractRemoteAddressFilter
    public boolean accept(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress.getAddress() instanceof Inet4Address) {
            int binarySearch = Collections.binarySearch(this.ipv4Rules, inetSocketAddress, IpSubnetFilterRuleComparator.INSTANCE);
            if (binarySearch >= 0) {
                IpFilterRuleType ipFilterRuleType = this.ipFilterRuleTypeIPv4;
                return ipFilterRuleType == null ? this.ipv4Rules.get(binarySearch).ruleType() == IpFilterRuleType.ACCEPT : ipFilterRuleType == IpFilterRuleType.ACCEPT;
            }
        } else {
            int binarySearch2 = Collections.binarySearch(this.ipv6Rules, inetSocketAddress, IpSubnetFilterRuleComparator.INSTANCE);
            if (binarySearch2 >= 0) {
                IpFilterRuleType ipFilterRuleType2 = this.ipFilterRuleTypeIPv6;
                return ipFilterRuleType2 == null ? this.ipv6Rules.get(binarySearch2).ruleType() == IpFilterRuleType.ACCEPT : ipFilterRuleType2 == IpFilterRuleType.ACCEPT;
            }
        }
        return this.acceptIfNotFound;
    }

    private static List<IpSubnetFilterRule> sortAndFilter(List<IpSubnetFilterRule> list) {
        Collections.sort(list);
        Iterator<IpSubnetFilterRule> it = list.iterator();
        ArrayList arrayList = new ArrayList();
        IpSubnetFilterRule next = it.hasNext() ? it.next() : null;
        if (next != null) {
            arrayList.add(next);
        }
        while (it.hasNext()) {
            IpSubnetFilterRule next2 = it.next();
            if (!next.matches(new InetSocketAddress(next2.getIpAddress(), 1))) {
                arrayList.add(next2);
                next = next2;
            }
        }
        return arrayList;
    }
}
