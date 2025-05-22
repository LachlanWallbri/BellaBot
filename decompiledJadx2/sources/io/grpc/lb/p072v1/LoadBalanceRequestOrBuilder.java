package io.grpc.lb.p072v1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.lb.p072v1.LoadBalanceRequest;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface LoadBalanceRequestOrBuilder extends MessageOrBuilder {
    ClientStats getClientStats();

    ClientStatsOrBuilder getClientStatsOrBuilder();

    InitialLoadBalanceRequest getInitialRequest();

    InitialLoadBalanceRequestOrBuilder getInitialRequestOrBuilder();

    LoadBalanceRequest.LoadBalanceRequestTypeCase getLoadBalanceRequestTypeCase();

    boolean hasClientStats();

    boolean hasInitialRequest();
}
