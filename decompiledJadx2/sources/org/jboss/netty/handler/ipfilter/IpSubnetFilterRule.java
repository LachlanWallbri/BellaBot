package org.jboss.netty.handler.ipfilter;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes7.dex */
public class IpSubnetFilterRule extends IpSubnet implements IpFilterRule {
    private boolean isAllowRule;

    public IpSubnetFilterRule(boolean z) {
        this.isAllowRule = true;
        this.isAllowRule = z;
    }

    public IpSubnetFilterRule(boolean z, InetAddress inetAddress, int i) throws UnknownHostException {
        super(inetAddress, i);
        this.isAllowRule = true;
        this.isAllowRule = z;
    }

    public IpSubnetFilterRule(boolean z, InetAddress inetAddress, String str) throws UnknownHostException {
        super(inetAddress, str);
        this.isAllowRule = true;
        this.isAllowRule = z;
    }

    public IpSubnetFilterRule(boolean z, String str) throws UnknownHostException {
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
