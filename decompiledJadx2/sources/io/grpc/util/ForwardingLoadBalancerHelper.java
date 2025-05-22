package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ConnectivityState;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.NameResolverRegistry;
import io.grpc.SynchronizationContext;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ForwardingLoadBalancerHelper extends LoadBalancer.Helper {
    protected abstract LoadBalancer.Helper delegate();

    @Override // io.grpc.LoadBalancer.Helper
    public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        return delegate().createSubchannel(createSubchannelArgs);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
        return delegate().createOobChannel(equivalentAddressGroup, str);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String str) {
        return delegate().createOobChannel(list, str);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
        delegate().updateOobChannelAddresses(managedChannel, equivalentAddressGroup);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public void updateOobChannelAddresses(ManagedChannel managedChannel, List<EquivalentAddressGroup> list) {
        delegate().updateOobChannelAddresses(managedChannel, list);
    }

    @Override // io.grpc.LoadBalancer.Helper
    @Deprecated
    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str) {
        return delegate().createResolvingOobChannelBuilder(str);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str, ChannelCredentials channelCredentials) {
        return delegate().createResolvingOobChannelBuilder(str, channelCredentials);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ManagedChannel createResolvingOobChannel(String str) {
        return delegate().createResolvingOobChannel(str);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        delegate().updateBalancingState(connectivityState, subchannelPicker);
    }

    @Override // io.grpc.LoadBalancer.Helper
    public void refreshNameResolution() {
        delegate().refreshNameResolution();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public void ignoreRefreshNameResolutionCheck() {
        delegate().ignoreRefreshNameResolutionCheck();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public String getAuthority() {
        return delegate().getAuthority();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ChannelCredentials getChannelCredentials() {
        return delegate().getChannelCredentials();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ChannelCredentials getUnsafeChannelCredentials() {
        return delegate().getUnsafeChannelCredentials();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public SynchronizationContext getSynchronizationContext() {
        return delegate().getSynchronizationContext();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ScheduledExecutorService getScheduledExecutorService() {
        return delegate().getScheduledExecutorService();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public ChannelLogger getChannelLogger() {
        return delegate().getChannelLogger();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public NameResolver.Args getNameResolverArgs() {
        return delegate().getNameResolverArgs();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public NameResolverRegistry getNameResolverRegistry() {
        return delegate().getNameResolverRegistry();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
