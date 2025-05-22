package org.jboss.netty.handler.ipfilter;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes7.dex */
public class IpV4SubnetFilterRule extends IpV4Subnet implements IpFilterRule {
    private boolean isAllowRule;

    public IpV4SubnetFilterRule(boolean z) {
        this.isAllowRule = true;
        this.isAllowRule = z;
    }

    public IpV4SubnetFilterRule(boolean z, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        this.isAllowRule = true;
        this.isAllowRule = z;
    }

    public IpV4SubnetFilterRule(boolean z, InetAddress inetAddress, String str) {
        super(inetAddress, str);
        this.isAllowRule = true;
        this.isAllowRule = z;
    }

    public IpV4SubnetFilterRule(boolean z, String str) throws UnknownHostException {
        super(str);
        this.isAllowRule = true;
        this.isAllowRule = z;
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilterRule
    public boolean isAllowRule() {
        return this.isAllowRule;
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilterRule
    public boolean isDenyRule() {
        return !this.isAllowRule;
    }
}
