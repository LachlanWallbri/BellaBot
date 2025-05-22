package org.jboss.netty.handler.ipfilter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class IpFilterRuleHandler extends IpFilteringHandlerImpl {
    private final CopyOnWriteArrayList<IpFilterRule> ipFilterRuleList = new CopyOnWriteArrayList<>();

    public IpFilterRuleHandler(List<IpFilterRule> list) {
        if (list != null) {
            this.ipFilterRuleList.addAll(list);
        }
    }

    public IpFilterRuleHandler() {
    }

    public void add(IpFilterRule ipFilterRule) {
        if (ipFilterRule == null) {
            throw new NullPointerException("IpFilterRule can not be null");
        }
        this.ipFilterRuleList.add(ipFilterRule);
    }

    public void add(int i, IpFilterRule ipFilterRule) {
        if (ipFilterRule == null) {
            throw new NullPointerException("IpFilterRule can not be null");
        }
        this.ipFilterRuleList.add(i, ipFilterRule);
    }

    public void addAll(Collection<IpFilterRule> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can not be null");
        }
        this.ipFilterRuleList.addAll(collection);
    }

    public void addAll(int i, Collection<IpFilterRule> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can not be null");
        }
        this.ipFilterRuleList.addAll(i, collection);
    }

    public int addAllAbsent(Collection<IpFilterRule> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can not be null");
        }
        return this.ipFilterRuleList.addAllAbsent(collection);
    }

    public boolean addIfAbsent(IpFilterRule ipFilterRule) {
        if (ipFilterRule == null) {
            throw new NullPointerException("IpFilterRule can not be null");
        }
        return this.ipFilterRuleList.addIfAbsent(ipFilterRule);
    }

    public void clear() {
        this.ipFilterRuleList.clear();
    }

    public boolean contains(IpFilterRule ipFilterRule) {
        if (ipFilterRule == null) {
            throw new NullPointerException("IpFilterRule can not be null");
        }
        return this.ipFilterRuleList.contains(ipFilterRule);
    }

    public boolean containsAll(Collection<IpFilterRule> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can not be null");
        }
        return this.ipFilterRuleList.containsAll(collection);
    }

    public IpFilterRule get(int i) {
        return this.ipFilterRuleList.get(i);
    }

    public boolean isEmpty() {
        return this.ipFilterRuleList.isEmpty();
    }

    public void remove(IpFilterRule ipFilterRule) {
        if (ipFilterRule == null) {
            throw new NullPointerException("IpFilterRule can not be null");
        }
        this.ipFilterRuleList.remove(ipFilterRule);
    }

    public IpFilterRule remove(int i) {
        return this.ipFilterRuleList.remove(i);
    }

    public void removeAll(Collection<IpFilterRule> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can not be null");
        }
        this.ipFilterRuleList.removeAll(collection);
    }

    public void retainAll(Collection<IpFilterRule> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can not be null");
        }
        this.ipFilterRuleList.retainAll(collection);
    }

    public IpFilterRule set(int i, IpFilterRule ipFilterRule) {
        if (ipFilterRule == null) {
            throw new NullPointerException("IpFilterRule can not be null");
        }
        return this.ipFilterRuleList.set(i, ipFilterRule);
    }

    public int size() {
        return this.ipFilterRuleList.size();
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilteringHandlerImpl
    protected boolean accept(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, InetSocketAddress inetSocketAddress) throws Exception {
        if (this.ipFilterRuleList.isEmpty()) {
            return true;
        }
        InetAddress address = inetSocketAddress.getAddress();
        Iterator<IpFilterRule> it = this.ipFilterRuleList.iterator();
        while (it.hasNext()) {
            IpFilterRule next = it.next();
            if (next.contains(address)) {
                return next.isAllowRule();
            }
        }
        return true;
    }
}
