package io.grpc.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class RoundRobinLoadBalancer extends LoadBalancer {
    private ConnectivityState currentState;
    private final LoadBalancer.Helper helper;
    static final Attributes.Key<Ref<ConnectivityStateInfo>> STATE_INFO = Attributes.Key.create("state-info");
    private static final Status EMPTY_OK = Status.f8305OK.withDescription("no subchannels ready");
    private final Map<EquivalentAddressGroup, LoadBalancer.Subchannel> subchannels = new HashMap();
    private RoundRobinPicker currentPicker = new EmptyPicker(EMPTY_OK);
    private final Random random = new Random();

    /* JADX INFO: Access modifiers changed from: package-private */
    public RoundRobinLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    @Override // io.grpc.LoadBalancer
    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
        Set<EquivalentAddressGroup> keySet = this.subchannels.keySet();
        Map<EquivalentAddressGroup, EquivalentAddressGroup> stripAttrs = stripAttrs(addresses);
        Set set = setsDifference(keySet, stripAttrs.keySet());
        for (Map.Entry<EquivalentAddressGroup, EquivalentAddressGroup> entry : stripAttrs.entrySet()) {
            EquivalentAddressGroup key = entry.getKey();
            EquivalentAddressGroup value = entry.getValue();
            LoadBalancer.Subchannel subchannel = this.subchannels.get(key);
            if (subchannel != null) {
                subchannel.updateAddresses(Collections.singletonList(value));
            } else {
                final LoadBalancer.Subchannel subchannel2 = (LoadBalancer.Subchannel) Preconditions.checkNotNull(this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(value).setAttributes(Attributes.newBuilder().set(STATE_INFO, new Ref(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE))).build()).build()), "subchannel");
                subchannel2.start(new LoadBalancer.SubchannelStateListener() { // from class: io.grpc.util.RoundRobinLoadBalancer.1
                    @Override // io.grpc.LoadBalancer.SubchannelStateListener
                    public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                        RoundRobinLoadBalancer.this.processSubchannelState(subchannel2, connectivityStateInfo);
                    }
                });
                this.subchannels.put(key, subchannel2);
                subchannel2.requestConnection();
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(this.subchannels.remove((EquivalentAddressGroup) it.next()));
        }
        updateBalancingState();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            shutdownSubchannel((LoadBalancer.Subchannel) it2.next());
        }
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        if (this.currentState != ConnectivityState.READY) {
            updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new EmptyPicker(status));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void processSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        if (this.subchannels.get(stripAttrs(subchannel.getAddresses())) != subchannel) {
            return;
        }
        if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            this.helper.refreshNameResolution();
        }
        if (connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            subchannel.requestConnection();
        }
        Ref<ConnectivityStateInfo> subchannelStateInfoRef = getSubchannelStateInfoRef(subchannel);
        if (subchannelStateInfoRef.value.getState().equals(ConnectivityState.TRANSIENT_FAILURE) && (connectivityStateInfo.getState().equals(ConnectivityState.CONNECTING) || connectivityStateInfo.getState().equals(ConnectivityState.IDLE))) {
            return;
        }
        subchannelStateInfoRef.value = connectivityStateInfo;
        updateBalancingState();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, io.grpc.ConnectivityStateInfo] */
    private void shutdownSubchannel(LoadBalancer.Subchannel subchannel) {
        subchannel.shutdown();
        getSubchannelStateInfoRef(subchannel).value = ConnectivityStateInfo.forNonError(ConnectivityState.SHUTDOWN);
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        Iterator<LoadBalancer.Subchannel> it = getSubchannels().iterator();
        while (it.hasNext()) {
            shutdownSubchannel(it.next());
        }
        this.subchannels.clear();
    }

    private void updateBalancingState() {
        List<LoadBalancer.Subchannel> filterNonFailingSubchannels = filterNonFailingSubchannels(getSubchannels());
        if (filterNonFailingSubchannels.isEmpty()) {
            boolean z = false;
            Status status = EMPTY_OK;
            Iterator<LoadBalancer.Subchannel> it = getSubchannels().iterator();
            while (it.hasNext()) {
                ConnectivityStateInfo connectivityStateInfo = getSubchannelStateInfoRef(it.next()).value;
                if (connectivityStateInfo.getState() == ConnectivityState.CONNECTING || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                    z = true;
                }
                if (status == EMPTY_OK || !status.isOk()) {
                    status = connectivityStateInfo.getStatus();
                }
            }
            updateBalancingState(z ? ConnectivityState.CONNECTING : ConnectivityState.TRANSIENT_FAILURE, new EmptyPicker(status));
            return;
        }
        updateBalancingState(ConnectivityState.READY, new ReadyPicker(filterNonFailingSubchannels, this.random.nextInt(filterNonFailingSubchannels.size())));
    }

    private void updateBalancingState(ConnectivityState connectivityState, RoundRobinPicker roundRobinPicker) {
        if (connectivityState == this.currentState && roundRobinPicker.isEquivalentTo(this.currentPicker)) {
            return;
        }
        this.helper.updateBalancingState(connectivityState, roundRobinPicker);
        this.currentState = connectivityState;
        this.currentPicker = roundRobinPicker;
    }

    private static List<LoadBalancer.Subchannel> filterNonFailingSubchannels(Collection<LoadBalancer.Subchannel> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (LoadBalancer.Subchannel subchannel : collection) {
            if (isReady(subchannel)) {
                arrayList.add(subchannel);
            }
        }
        return arrayList;
    }

    private static Map<EquivalentAddressGroup, EquivalentAddressGroup> stripAttrs(List<EquivalentAddressGroup> list) {
        HashMap hashMap = new HashMap(list.size() * 2);
        for (EquivalentAddressGroup equivalentAddressGroup : list) {
            hashMap.put(stripAttrs(equivalentAddressGroup), equivalentAddressGroup);
        }
        return hashMap;
    }

    private static EquivalentAddressGroup stripAttrs(EquivalentAddressGroup equivalentAddressGroup) {
        return new EquivalentAddressGroup(equivalentAddressGroup.getAddresses());
    }

    Collection<LoadBalancer.Subchannel> getSubchannels() {
        return this.subchannels.values();
    }

    private static Ref<ConnectivityStateInfo> getSubchannelStateInfoRef(LoadBalancer.Subchannel subchannel) {
        return (Ref) Preconditions.checkNotNull(subchannel.getAttributes().get(STATE_INFO), "STATE_INFO");
    }

    static boolean isReady(LoadBalancer.Subchannel subchannel) {
        return getSubchannelStateInfoRef(subchannel).value.getState() == ConnectivityState.READY;
    }

    private static <T> Set<T> setsDifference(Set<T> set, Set<T> set2) {
        HashSet hashSet = new HashSet(set);
        hashSet.removeAll(set2);
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class RoundRobinPicker extends LoadBalancer.SubchannelPicker {
        abstract boolean isEquivalentTo(RoundRobinPicker roundRobinPicker);

        private RoundRobinPicker() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ReadyPicker extends RoundRobinPicker {
        private static final AtomicIntegerFieldUpdater<ReadyPicker> indexUpdater = AtomicIntegerFieldUpdater.newUpdater(ReadyPicker.class, "index");
        private volatile int index;
        private final List<LoadBalancer.Subchannel> list;

        ReadyPicker(List<LoadBalancer.Subchannel> list, int i) {
            super();
            Preconditions.checkArgument(!list.isEmpty(), "empty list");
            this.list = list;
            this.index = i - 1;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withSubchannel(nextSubchannel());
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) ReadyPicker.class).add("list", this.list).toString();
        }

        private LoadBalancer.Subchannel nextSubchannel() {
            int i;
            int size = this.list.size();
            int incrementAndGet = indexUpdater.incrementAndGet(this);
            if (incrementAndGet >= size) {
                i = incrementAndGet % size;
                indexUpdater.compareAndSet(this, incrementAndGet, i);
            } else {
                i = incrementAndGet;
            }
            return this.list.get(i);
        }

        List<LoadBalancer.Subchannel> getList() {
            return this.list;
        }

        @Override // io.grpc.util.RoundRobinLoadBalancer.RoundRobinPicker
        boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            if (!(roundRobinPicker instanceof ReadyPicker)) {
                return false;
            }
            ReadyPicker readyPicker = (ReadyPicker) roundRobinPicker;
            return readyPicker == this || (this.list.size() == readyPicker.list.size() && new HashSet(this.list).containsAll(readyPicker.list));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class EmptyPicker extends RoundRobinPicker {
        private final Status status;

        EmptyPicker(@Nonnull Status status) {
            super();
            this.status = (Status) Preconditions.checkNotNull(status, "status");
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return this.status.isOk() ? LoadBalancer.PickResult.withNoResult() : LoadBalancer.PickResult.withError(this.status);
        }

        @Override // io.grpc.util.RoundRobinLoadBalancer.RoundRobinPicker
        boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            if (roundRobinPicker instanceof EmptyPicker) {
                EmptyPicker emptyPicker = (EmptyPicker) roundRobinPicker;
                if (Objects.equal(this.status, emptyPicker.status) || (this.status.isOk() && emptyPicker.status.isOk())) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) EmptyPicker.class).add("status", this.status).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Ref<T> {
        T value;

        Ref(T t) {
            this.value = t;
        }
    }
}
