package io.grpc.netty.shaded.io.netty.channel.group;

import com.pudutech.mirsdk.compat.topo.MapElement;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.BlockingOperationException;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ImmediateEventExecutor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DefaultChannelGroupFuture extends DefaultPromise<Void> implements ChannelGroupFuture {
    private final ChannelFutureListener childListener;
    private int failureCount;
    private final Map<Channel, ChannelFuture> futures;
    private final ChannelGroup group;
    private int successCount;

    static /* synthetic */ int access$008(DefaultChannelGroupFuture defaultChannelGroupFuture) {
        int i = defaultChannelGroupFuture.successCount;
        defaultChannelGroupFuture.successCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$108(DefaultChannelGroupFuture defaultChannelGroupFuture) {
        int i = defaultChannelGroupFuture.failureCount;
        defaultChannelGroupFuture.failureCount = i + 1;
        return i;
    }

    DefaultChannelGroupFuture(ChannelGroup channelGroup, Collection<ChannelFuture> collection, EventExecutor eventExecutor) {
        super(eventExecutor);
        this.childListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.group.DefaultChannelGroupFuture.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                boolean z;
                boolean isSuccess = channelFuture.isSuccess();
                synchronized (DefaultChannelGroupFuture.this) {
                    if (isSuccess) {
                        DefaultChannelGroupFuture.access$008(DefaultChannelGroupFuture.this);
                    } else {
                        DefaultChannelGroupFuture.access$108(DefaultChannelGroupFuture.this);
                    }
                    z = DefaultChannelGroupFuture.this.successCount + DefaultChannelGroupFuture.this.failureCount == DefaultChannelGroupFuture.this.futures.size();
                }
                if (z) {
                    if (DefaultChannelGroupFuture.this.failureCount <= 0) {
                        DefaultChannelGroupFuture.this.setSuccess0();
                        return;
                    }
                    ArrayList arrayList = new ArrayList(DefaultChannelGroupFuture.this.failureCount);
                    for (ChannelFuture channelFuture2 : DefaultChannelGroupFuture.this.futures.values()) {
                        if (!channelFuture2.isSuccess()) {
                            arrayList.add(new DefaultEntry(channelFuture2.channel(), channelFuture2.cause()));
                        }
                    }
                    DefaultChannelGroupFuture.this.setFailure0(new ChannelGroupException(arrayList));
                }
            }
        };
        this.group = (ChannelGroup) ObjectUtil.checkNotNull(channelGroup, MapElement.Key.GROUP);
        ObjectUtil.checkNotNull(collection, "futures");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ChannelFuture channelFuture : collection) {
            linkedHashMap.put(channelFuture.channel(), channelFuture);
        }
        this.futures = Collections.unmodifiableMap(linkedHashMap);
        Iterator<ChannelFuture> it = this.futures.values().iterator();
        while (it.hasNext()) {
            it.next().addListener((GenericFutureListener<? extends Future<? super Void>>) this.childListener);
        }
        if (this.futures.isEmpty()) {
            setSuccess0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultChannelGroupFuture(ChannelGroup channelGroup, Map<Channel, ChannelFuture> map, EventExecutor eventExecutor) {
        super(eventExecutor);
        this.childListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.group.DefaultChannelGroupFuture.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                boolean z;
                boolean isSuccess = channelFuture.isSuccess();
                synchronized (DefaultChannelGroupFuture.this) {
                    if (isSuccess) {
                        DefaultChannelGroupFuture.access$008(DefaultChannelGroupFuture.this);
                    } else {
                        DefaultChannelGroupFuture.access$108(DefaultChannelGroupFuture.this);
                    }
                    z = DefaultChannelGroupFuture.this.successCount + DefaultChannelGroupFuture.this.failureCount == DefaultChannelGroupFuture.this.futures.size();
                }
                if (z) {
                    if (DefaultChannelGroupFuture.this.failureCount <= 0) {
                        DefaultChannelGroupFuture.this.setSuccess0();
                        return;
                    }
                    ArrayList arrayList = new ArrayList(DefaultChannelGroupFuture.this.failureCount);
                    for (ChannelFuture channelFuture2 : DefaultChannelGroupFuture.this.futures.values()) {
                        if (!channelFuture2.isSuccess()) {
                            arrayList.add(new DefaultEntry(channelFuture2.channel(), channelFuture2.cause()));
                        }
                    }
                    DefaultChannelGroupFuture.this.setFailure0(new ChannelGroupException(arrayList));
                }
            }
        };
        this.group = channelGroup;
        this.futures = Collections.unmodifiableMap(map);
        Iterator<ChannelFuture> it = this.futures.values().iterator();
        while (it.hasNext()) {
            it.next().addListener((GenericFutureListener<? extends Future<? super Void>>) this.childListener);
        }
        if (this.futures.isEmpty()) {
            setSuccess0();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroupFuture
    public ChannelGroup group() {
        return this.group;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroupFuture
    public ChannelFuture find(Channel channel) {
        return this.futures.get(channel);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroupFuture, java.lang.Iterable
    public Iterator<ChannelFuture> iterator() {
        return this.futures.values().iterator();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroupFuture
    public synchronized boolean isPartialSuccess() {
        boolean z;
        if (this.successCount != 0) {
            z = this.successCount != this.futures.size();
        }
        return z;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.group.ChannelGroupFuture
    public synchronized boolean isPartialFailure() {
        boolean z;
        if (this.failureCount != 0) {
            z = this.failureCount != this.futures.size();
        }
        return z;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.addListener((GenericFutureListener) genericFutureListener);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.addListeners((GenericFutureListener[]) genericFutureListenerArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.removeListener((GenericFutureListener) genericFutureListener);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.removeListeners((GenericFutureListener[]) genericFutureListenerArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> await() throws InterruptedException {
        super.await();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> awaitUninterruptibly() {
        super.awaitUninterruptibly();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> syncUninterruptibly() {
        super.syncUninterruptibly();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> sync() throws InterruptedException {
        super.sync();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public ChannelGroupException cause() {
        return (ChannelGroupException) super.cause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSuccess0() {
        super.setSuccess((DefaultChannelGroupFuture) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFailure0(ChannelGroupException channelGroupException) {
        super.setFailure((Throwable) channelGroupException);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public DefaultChannelGroupFuture setSuccess(Void r1) {
        throw new IllegalStateException();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean trySuccess(Void r1) {
        throw new IllegalStateException();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public DefaultChannelGroupFuture setFailure(Throwable th) {
        throw new IllegalStateException();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean tryFailure(Throwable th) {
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
    public void checkDeadLock() {
        EventExecutor executor = executor();
        if (executor != null && executor != ImmediateEventExecutor.INSTANCE && executor.inEventLoop()) {
            throw new BlockingOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DefaultEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private final V value;

        DefaultEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("read-only");
        }
    }
}
