package io.grpc.netty.shaded.io.netty.channel.group;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelId;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultChannelGroup extends AbstractSet<Channel> implements ChannelGroup {
    private static final AtomicInteger nextId = new AtomicInteger();
    private volatile boolean closed;
    private final EventExecutor executor;
    private final String name;
    private final ConcurrentMap<ChannelId, Channel> nonServerChannels;
    private final ChannelFutureListener remover;
    private final ConcurrentMap<ChannelId, Channel> serverChannels;
    private final boolean stayClosed;
    private final VoidChannelGroupFuture voidFuture;

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        return this == obj;
    }

    public DefaultChannelGroup(EventExecutor eventExecutor) {
        this(eventExecutor, false);
    }

    public DefaultChannelGroup(String str, EventExecutor eventExecutor) {
        this(str, eventExecutor, false);
    }

    public DefaultChannelGroup(EventExecutor eventExecutor, boolean z) {
        this("group-0x" + Integer.toHexString(nextId.incrementAndGet()), eventExecutor, z);
    }

    public DefaultChannelGroup(String str, EventExecutor eventExecutor, boolean z) {
        this.serverChannels = PlatformDependent.newConcurrentHashMap();
        this.nonServerChannels = PlatformDependent.newConcurrentHashMap();
        this.remover = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.group.DefaultChannelGroup.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                DefaultChannelGroup.this.remove(channelFuture.channel());
            }
        };
        this.voidFuture = new VoidChannelGroupFuture(this);
        ObjectUtil.checkNotNull(str, "name");
        this.name = str;
        this.executor = eventExecutor;
        this.stayClosed = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public String name() {
        return this.name;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public Channel find(ChannelId channelId) {
        Channel channel = this.nonServerChannels.get(channelId);
        return channel != null ? channel : this.serverChannels.get(channelId);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.nonServerChannels.isEmpty() && this.serverChannels.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.nonServerChannels.size() + this.serverChannels.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        if (obj instanceof ServerChannel) {
            return this.serverChannels.containsValue(obj);
        }
        if (obj instanceof Channel) {
            return this.nonServerChannels.containsValue(obj);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Channel channel) {
        boolean z = (channel instanceof ServerChannel ? this.serverChannels : this.nonServerChannels).putIfAbsent(channel.mo3907id(), channel) == null;
        if (z) {
            channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) this.remover);
        }
        if (this.stayClosed && this.closed) {
            channel.close();
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        Channel channel;
        if (obj instanceof ChannelId) {
            channel = this.nonServerChannels.remove(obj);
            if (channel == null) {
                channel = this.serverChannels.remove(obj);
            }
        } else if (obj instanceof Channel) {
            Channel channel2 = (Channel) obj;
            if (channel2 instanceof ServerChannel) {
                channel = this.serverChannels.remove(channel2.mo3907id());
            } else {
                channel = this.nonServerChannels.remove(channel2.mo3907id());
            }
        } else {
            channel = null;
        }
        if (channel == null) {
            return false;
        }
        channel.closeFuture().removeListener((GenericFutureListener<? extends Future<? super Void>>) this.remover);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.nonServerChannels.clear();
        this.serverChannels.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<Channel> iterator() {
        return new CombinedIterator(this.serverChannels.values().iterator(), this.nonServerChannels.values().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        ArrayList arrayList = new ArrayList(size());
        arrayList.addAll(this.serverChannels.values());
        arrayList.addAll(this.nonServerChannels.values());
        return arrayList.toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        ArrayList arrayList = new ArrayList(size());
        arrayList.addAll(this.serverChannels.values());
        arrayList.addAll(this.nonServerChannels.values());
        return (T[]) arrayList.toArray(tArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture close() {
        return close(ChannelMatchers.all());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture disconnect() {
        return disconnect(ChannelMatchers.all());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture deregister() {
        return deregister(ChannelMatchers.all());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture write(Object obj) {
        return write(obj, ChannelMatchers.all());
    }

    private static Object safeDuplicate(Object obj) {
        if (obj instanceof ByteBuf) {
            return ((ByteBuf) obj).retainedDuplicate();
        }
        if (obj instanceof ByteBufHolder) {
            return ((ByteBufHolder) obj).retainedDuplicate();
        }
        return ReferenceCountUtil.retain(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture write(Object obj, ChannelMatcher channelMatcher) {
        return write(obj, channelMatcher, false);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture write(Object obj, ChannelMatcher channelMatcher, boolean z) {
        ChannelGroupFuture defaultChannelGroupFuture;
        ObjectUtil.checkNotNull(obj, "message");
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        if (z) {
            for (Channel channel : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel)) {
                    channel.write(safeDuplicate(obj), channel.voidPromise());
                }
            }
            defaultChannelGroupFuture = this.voidFuture;
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.nonServerChannels.size());
            for (Channel channel2 : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel2)) {
                    linkedHashMap.put(channel2, channel2.write(safeDuplicate(obj)));
                }
            }
            defaultChannelGroupFuture = new DefaultChannelGroupFuture(this, linkedHashMap, this.executor);
        }
        ReferenceCountUtil.release(obj);
        return defaultChannelGroupFuture;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroup flush() {
        return flush(ChannelMatchers.all());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture flushAndWrite(Object obj) {
        return writeAndFlush(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture writeAndFlush(Object obj) {
        return writeAndFlush(obj, ChannelMatchers.all());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture disconnect(ChannelMatcher channelMatcher) {
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.disconnect());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.disconnect());
            }
        }
        return new DefaultChannelGroupFuture(this, linkedHashMap, this.executor);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture close(ChannelMatcher channelMatcher) {
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        if (this.stayClosed) {
            this.closed = true;
        }
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.close());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.close());
            }
        }
        return new DefaultChannelGroupFuture(this, linkedHashMap, this.executor);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture deregister(ChannelMatcher channelMatcher) {
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.deregister());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.deregister());
            }
        }
        return new DefaultChannelGroupFuture(this, linkedHashMap, this.executor);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroup flush(ChannelMatcher channelMatcher) {
        for (Channel channel : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel)) {
                channel.flush();
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture flushAndWrite(Object obj, ChannelMatcher channelMatcher) {
        return writeAndFlush(obj, channelMatcher);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture writeAndFlush(Object obj, ChannelMatcher channelMatcher) {
        return writeAndFlush(obj, channelMatcher, false);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture writeAndFlush(Object obj, ChannelMatcher channelMatcher, boolean z) {
        ChannelGroupFuture defaultChannelGroupFuture;
        ObjectUtil.checkNotNull(obj, "message");
        if (z) {
            for (Channel channel : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel)) {
                    channel.writeAndFlush(safeDuplicate(obj), channel.voidPromise());
                }
            }
            defaultChannelGroupFuture = this.voidFuture;
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.nonServerChannels.size());
            for (Channel channel2 : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel2)) {
                    linkedHashMap.put(channel2, channel2.writeAndFlush(safeDuplicate(obj)));
                }
            }
            defaultChannelGroupFuture = new DefaultChannelGroupFuture(this, linkedHashMap, this.executor);
        }
        ReferenceCountUtil.release(obj);
        return defaultChannelGroupFuture;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture newCloseFuture() {
        return newCloseFuture(ChannelMatchers.all());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup
    public ChannelGroupFuture newCloseFuture(ChannelMatcher channelMatcher) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.closeFuture());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.closeFuture());
            }
        }
        return new DefaultChannelGroupFuture(this, linkedHashMap, this.executor);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // java.lang.Comparable
    public int compareTo(ChannelGroup channelGroup) {
        int compareTo = name().compareTo(channelGroup.name());
        return compareTo != 0 ? compareTo : System.identityHashCode(this) - System.identityHashCode(channelGroup);
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return StringUtil.simpleClassName(this) + "(name: " + name() + ", size: " + size() + ')';
    }
}
