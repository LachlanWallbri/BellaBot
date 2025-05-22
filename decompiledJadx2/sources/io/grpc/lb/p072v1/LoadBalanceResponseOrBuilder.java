package io.grpc.lb.p072v1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.lb.p072v1.LoadBalanceResponse;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface LoadBalanceResponseOrBuilder extends MessageOrBuilder {
    FallbackResponse getFallbackResponse();

    FallbackResponseOrBuilder getFallbackResponseOrBuilder();

    InitialLoadBalanceResponse getInitialResponse();

    InitialLoadBalanceResponseOrBuilder getInitialResponseOrBuilder();

    LoadBalanceResponse.LoadBalanceResponseTypeCase getLoadBalanceResponseTypeCase();

    ServerList getServerList();

    ServerListOrBuilder getServerListOrBuilder();

    boolean hasFallbackResponse();

    boolean hasInitialResponse();

    boolean hasServerList();
}
