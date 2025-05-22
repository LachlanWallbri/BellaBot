package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.iflytek.cloud.SpeechUtility;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class PickFirstLoadBalancer extends LoadBalancer {
    private final LoadBalancer.Helper helper;
    private LoadBalancer.Subchannel subchannel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PickFirstLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    @Override // io.grpc.LoadBalancer
    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
        LoadBalancer.Subchannel subchannel = this.subchannel;
        if (subchannel == null) {
            final LoadBalancer.Subchannel createSubchannel = this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(addresses).build());
            createSubchannel.start(new LoadBalancer.SubchannelStateListener() { // from class: io.grpc.internal.PickFirstLoadBalancer.1
                @Override // io.grpc.LoadBalancer.SubchannelStateListener
                public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                    PickFirstLoadBalancer.this.processSubchannelState(createSubchannel, connectivityStateInfo);
                }
            });
            this.subchannel = createSubchannel;
            this.helper.updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withSubchannel(createSubchannel)));
            createSubchannel.requestConnection();
            return;
        }
        subchannel.updateAddresses(addresses);
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        LoadBalancer.Subchannel subchannel = this.subchannel;
        if (subchannel != null) {
            subchannel.shutdown();
            this.subchannel = null;
        }
        this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(status)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        LoadBalancer.SubchannelPicker requestConnectionPicker;
        LoadBalancer.SubchannelPicker subchannelPicker;
        ConnectivityState state = connectivityStateInfo.getState();
        if (state == ConnectivityState.SHUTDOWN) {
            return;
        }
        if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            this.helper.refreshNameResolution();
        }
        int i = C61642.$SwitchMap$io$grpc$ConnectivityState[state.ordinal()];
        if (i == 1) {
            requestConnectionPicker = new RequestConnectionPicker(subchannel);
        } else {
            if (i == 2) {
                subchannelPicker = new Picker(LoadBalancer.PickResult.withNoResult());
            } else if (i == 3) {
                requestConnectionPicker = new Picker(LoadBalancer.PickResult.withSubchannel(subchannel));
            } else if (i == 4) {
                subchannelPicker = new Picker(LoadBalancer.PickResult.withError(connectivityStateInfo.getStatus()));
            } else {
                throw new IllegalArgumentException("Unsupported state:" + state);
            }
            this.helper.updateBalancingState(state, subchannelPicker);
        }
        subchannelPicker = requestConnectionPicker;
        this.helper.updateBalancingState(state, subchannelPicker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.internal.PickFirstLoadBalancer$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C61642 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ConnectivityState = new int[ConnectivityState.values().length];

        static {
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.READY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.TRANSIENT_FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        LoadBalancer.Subchannel subchannel = this.subchannel;
        if (subchannel != null) {
            subchannel.shutdown();
        }
    }

    @Override // io.grpc.LoadBalancer
    public void requestConnection() {
        LoadBalancer.Subchannel subchannel = this.subchannel;
        if (subchannel != null) {
            subchannel.requestConnection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Picker extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.PickResult result;

        Picker(LoadBalancer.PickResult pickResult) {
            this.result = (LoadBalancer.PickResult) Preconditions.checkNotNull(pickResult, SpeechUtility.TAG_RESOURCE_RESULT);
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return this.result;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) Picker.class).add(SpeechUtility.TAG_RESOURCE_RESULT, this.result).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class RequestConnectionPicker extends LoadBalancer.SubchannelPicker {
        private final AtomicBoolean connectionRequested = new AtomicBoolean(false);
        private final LoadBalancer.Subchannel subchannel;

        RequestConnectionPicker(LoadBalancer.Subchannel subchannel) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                PickFirstLoadBalancer.this.helper.getSynchronizationContext().execute(new Runnable() { // from class: io.grpc.internal.PickFirstLoadBalancer.RequestConnectionPicker.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RequestConnectionPicker.this.subchannel.requestConnection();
                    }
                });
            }
            return LoadBalancer.PickResult.withNoResult();
        }
    }
}
