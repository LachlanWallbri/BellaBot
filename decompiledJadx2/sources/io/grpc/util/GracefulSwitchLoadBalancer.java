package io.grpc.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GracefulSwitchLoadBalancer extends ForwardingLoadBalancer {
    static final LoadBalancer.SubchannelPicker BUFFER_PICKER = new LoadBalancer.SubchannelPicker() { // from class: io.grpc.util.GracefulSwitchLoadBalancer.2
        public String toString() {
            return "BUFFER_PICKER";
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }
    };

    @Nullable
    private LoadBalancer.Factory currentBalancerFactory;
    private LoadBalancer currentLb;
    private boolean currentLbIsReady;
    private final LoadBalancer defaultBalancer = new LoadBalancer() { // from class: io.grpc.util.GracefulSwitchLoadBalancer.1
        @Override // io.grpc.LoadBalancer
        public void shutdown() {
        }

        @Override // io.grpc.LoadBalancer
        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            throw new IllegalStateException("GracefulSwitchLoadBalancer must switch to a load balancing policy before handling ResolvedAddresses");
        }

        @Override // io.grpc.LoadBalancer
        public void handleNameResolutionError(final Status status) {
            GracefulSwitchLoadBalancer.this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new LoadBalancer.SubchannelPicker() { // from class: io.grpc.util.GracefulSwitchLoadBalancer.1.1ErrorPicker
                @Override // io.grpc.LoadBalancer.SubchannelPicker
                public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                    return LoadBalancer.PickResult.withError(status);
                }

                public String toString() {
                    return MoreObjects.toStringHelper((Class<?>) C1ErrorPicker.class).add("error", status).toString();
                }
            });
        }
    };
    private final LoadBalancer.Helper helper;

    @Nullable
    private LoadBalancer.Factory pendingBalancerFactory;
    private LoadBalancer pendingLb;
    private LoadBalancer.SubchannelPicker pendingPicker;
    private ConnectivityState pendingState;

    public GracefulSwitchLoadBalancer(LoadBalancer.Helper helper) {
        LoadBalancer loadBalancer = this.defaultBalancer;
        this.currentLb = loadBalancer;
        this.pendingLb = loadBalancer;
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    public void switchTo(LoadBalancer.Factory factory) {
        Preconditions.checkNotNull(factory, "newBalancerFactory");
        if (factory.equals(this.pendingBalancerFactory)) {
            return;
        }
        this.pendingLb.shutdown();
        this.pendingLb = this.defaultBalancer;
        this.pendingBalancerFactory = null;
        this.pendingState = ConnectivityState.CONNECTING;
        this.pendingPicker = BUFFER_PICKER;
        if (factory.equals(this.currentBalancerFactory)) {
            return;
        }
        C1PendingHelper c1PendingHelper = new C1PendingHelper();
        c1PendingHelper.f8435lb = factory.newLoadBalancer(c1PendingHelper);
        this.pendingLb = c1PendingHelper.f8435lb;
        this.pendingBalancerFactory = factory;
        if (this.currentLbIsReady) {
            return;
        }
        swap();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.util.GracefulSwitchLoadBalancer$1PendingHelper, reason: invalid class name */
    /* loaded from: classes7.dex */
    class C1PendingHelper extends ForwardingLoadBalancerHelper {

        /* renamed from: lb */
        LoadBalancer f8435lb;

        C1PendingHelper() {
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper
        protected LoadBalancer.Helper delegate() {
            return GracefulSwitchLoadBalancer.this.helper;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
        public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
            if (this.f8435lb == GracefulSwitchLoadBalancer.this.pendingLb) {
                Preconditions.checkState(GracefulSwitchLoadBalancer.this.currentLbIsReady, "there's pending lb while current lb has been out of READY");
                GracefulSwitchLoadBalancer.this.pendingState = connectivityState;
                GracefulSwitchLoadBalancer.this.pendingPicker = subchannelPicker;
                if (connectivityState == ConnectivityState.READY) {
                    GracefulSwitchLoadBalancer.this.swap();
                    return;
                }
                return;
            }
            if (this.f8435lb == GracefulSwitchLoadBalancer.this.currentLb) {
                GracefulSwitchLoadBalancer.this.currentLbIsReady = connectivityState == ConnectivityState.READY;
                if (GracefulSwitchLoadBalancer.this.currentLbIsReady || GracefulSwitchLoadBalancer.this.pendingLb == GracefulSwitchLoadBalancer.this.defaultBalancer) {
                    GracefulSwitchLoadBalancer.this.helper.updateBalancingState(connectivityState, subchannelPicker);
                } else {
                    GracefulSwitchLoadBalancer.this.swap();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void swap() {
        this.helper.updateBalancingState(this.pendingState, this.pendingPicker);
        this.currentLb.shutdown();
        this.currentLb = this.pendingLb;
        this.currentBalancerFactory = this.pendingBalancerFactory;
        this.pendingLb = this.defaultBalancer;
        this.pendingBalancerFactory = null;
    }

    @Override // io.grpc.util.ForwardingLoadBalancer
    protected LoadBalancer delegate() {
        LoadBalancer loadBalancer = this.pendingLb;
        return loadBalancer == this.defaultBalancer ? this.currentLb : loadBalancer;
    }

    @Override // io.grpc.util.ForwardingLoadBalancer, io.grpc.LoadBalancer
    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        throw new UnsupportedOperationException("handleSubchannelState() is not supported by " + getClass().getName());
    }

    @Override // io.grpc.util.ForwardingLoadBalancer, io.grpc.LoadBalancer
    public void shutdown() {
        this.pendingLb.shutdown();
        this.currentLb.shutdown();
    }
}
