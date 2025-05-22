package io.grpc.lb.p072v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ClientStatsPerTokenOrBuilder extends MessageOrBuilder {
    String getLoadBalanceToken();

    ByteString getLoadBalanceTokenBytes();

    long getNumCalls();
}
