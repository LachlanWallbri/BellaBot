package org.jboss.netty.channel;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import org.jboss.netty.util.internal.ConcurrentIdentityWeakKeyHashMap;

/* loaded from: classes7.dex */
public class ChannelLocal<T> implements Iterable<Map.Entry<Channel, T>> {
    private final ConcurrentMap<Channel, T> map;
    private final boolean removeOnClose;
    private final ChannelFutureListener remover;

    protected T initialValue(Channel channel) {
        return null;
    }

    public ChannelLocal() {
        this(false);
    }

    public ChannelLocal(boolean z) {
        this.map = new ConcurrentIdentityWeakKeyHashMap();
        this.remover = new ChannelFutureListener() { // from class: org.jboss.netty.channel.ChannelLocal.1
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                ChannelLocal.this.remove(channelFuture.getChannel());
            }
        };
        this.removeOnClose = z;
    }

    public T get(Channel channel) {
        T ifAbsent;
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        T t = this.map.get(channel);
        if (t != null) {
            return t;
        }
        T initialValue = initialValue(channel);
        return (initialValue == null || (ifAbsent = setIfAbsent(channel, initialValue)) == null) ? initialValue : ifAbsent;
    }

    public T set(Channel channel, T t) {
        if (t == null) {
            return remove(channel);
        }
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        T put = this.map.put(channel, t);
        if (this.removeOnClose) {
            channel.getCloseFuture().addListener(this.remover);
        }
        return put;
    }

    public T setIfAbsent(Channel channel, T t) {
        if (t == null) {
            return get(channel);
        }
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        T putIfAbsent = this.map.putIfAbsent(channel, t);
        if (this.removeOnClose && putIfAbsent == null) {
            channel.getCloseFuture().addListener(this.remover);
        }
        return putIfAbsent;
    }

    public T remove(Channel channel) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        T remove = this.map.remove(channel);
        if (remove == null) {
            return initialValue(channel);
        }
        if (this.removeOnClose) {
            channel.getCloseFuture().removeListener(this.remover);
        }
        return remove;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<Channel, T>> iterator() {
        return Collections.unmodifiableSet(this.map.entrySet()).iterator();
    }
}
