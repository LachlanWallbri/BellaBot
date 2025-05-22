package io.grpc.grpclb;

import io.grpc.Attributes;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
interface SubchannelPool {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface PooledSubchannelStateListener {
        void onSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo);
    }

    void clear();

    void registerListener(PooledSubchannelStateListener pooledSubchannelStateListener);

    void returnSubchannel(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo);

    LoadBalancer.Subchannel takeOrCreateSubchannel(EquivalentAddressGroup equivalentAddressGroup, Attributes attributes);
}
