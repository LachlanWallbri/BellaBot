package io.grpc.grpclb;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.SynchronizationContext;
import io.grpc.grpclb.SubchannelPool;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CachedSubchannelPool implements SubchannelPool {
    static final long SHUTDOWN_TIMEOUT_MS = 10000;
    private final HashMap<EquivalentAddressGroup, CacheEntry> cache = new HashMap<>();
    private final LoadBalancer.Helper helper;
    private SubchannelPool.PooledSubchannelStateListener listener;

    public CachedSubchannelPool(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    @Override // io.grpc.grpclb.SubchannelPool
    public void registerListener(SubchannelPool.PooledSubchannelStateListener pooledSubchannelStateListener) {
        this.listener = (SubchannelPool.PooledSubchannelStateListener) Preconditions.checkNotNull(pooledSubchannelStateListener, "listener");
    }

    @Override // io.grpc.grpclb.SubchannelPool
    public LoadBalancer.Subchannel takeOrCreateSubchannel(EquivalentAddressGroup equivalentAddressGroup, Attributes attributes) {
        final CacheEntry remove = this.cache.remove(equivalentAddressGroup);
        if (remove == null) {
            final LoadBalancer.Subchannel createSubchannel = this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(equivalentAddressGroup).setAttributes(attributes).build());
            createSubchannel.start(new LoadBalancer.SubchannelStateListener() { // from class: io.grpc.grpclb.CachedSubchannelPool.1
                @Override // io.grpc.LoadBalancer.SubchannelStateListener
                public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                    CachedSubchannelPool.this.updateCachedSubchannelState(createSubchannel, connectivityStateInfo);
                    CachedSubchannelPool.this.listener.onSubchannelState(createSubchannel, connectivityStateInfo);
                }
            });
            return createSubchannel;
        }
        final LoadBalancer.Subchannel subchannel = remove.subchannel;
        remove.shutdownTimer.cancel();
        this.helper.getSynchronizationContext().execute(new Runnable() { // from class: io.grpc.grpclb.CachedSubchannelPool.2
            @Override // java.lang.Runnable
            public void run() {
                CachedSubchannelPool.this.listener.onSubchannelState(subchannel, remove.state);
            }
        });
        return subchannel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCachedSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        CacheEntry cacheEntry = this.cache.get(subchannel.getAddresses());
        if (cacheEntry == null || cacheEntry.subchannel != subchannel) {
            return;
        }
        cacheEntry.state = connectivityStateInfo;
    }

    @Override // io.grpc.grpclb.SubchannelPool
    public void returnSubchannel(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        CacheEntry cacheEntry = this.cache.get(subchannel.getAddresses());
        if (cacheEntry != null) {
            if (cacheEntry.subchannel != subchannel) {
                subchannel.shutdown();
            }
        } else {
            this.cache.put(subchannel.getAddresses(), new CacheEntry(subchannel, this.helper.getSynchronizationContext().schedule(new ShutdownSubchannelTask(subchannel), SHUTDOWN_TIMEOUT_MS, TimeUnit.MILLISECONDS, this.helper.getScheduledExecutorService()), connectivityStateInfo));
        }
    }

    @Override // io.grpc.grpclb.SubchannelPool
    public void clear() {
        for (CacheEntry cacheEntry : this.cache.values()) {
            cacheEntry.shutdownTimer.cancel();
            cacheEntry.subchannel.shutdown();
        }
        this.cache.clear();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    final class ShutdownSubchannelTask implements Runnable {
        private final LoadBalancer.Subchannel subchannel;

        private ShutdownSubchannelTask(LoadBalancer.Subchannel subchannel) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
        }

        @Override // java.lang.Runnable
        public void run() {
            Preconditions.checkState(((CacheEntry) CachedSubchannelPool.this.cache.remove(this.subchannel.getAddresses())).subchannel == this.subchannel, "Inconsistent state");
            this.subchannel.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class CacheEntry {
        final SynchronizationContext.ScheduledHandle shutdownTimer;
        ConnectivityStateInfo state;
        final LoadBalancer.Subchannel subchannel;

        CacheEntry(LoadBalancer.Subchannel subchannel, SynchronizationContext.ScheduledHandle scheduledHandle, ConnectivityStateInfo connectivityStateInfo) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
            this.shutdownTimer = (SynchronizationContext.ScheduledHandle) Preconditions.checkNotNull(scheduledHandle, "shutdownTimer");
            this.state = (ConnectivityStateInfo) Preconditions.checkNotNull(connectivityStateInfo, "state");
        }
    }
}
