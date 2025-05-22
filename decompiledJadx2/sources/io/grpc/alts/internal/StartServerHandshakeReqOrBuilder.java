package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface StartServerHandshakeReqOrBuilder extends MessageOrBuilder {
    boolean containsHandshakeParameters(int i);

    String getApplicationProtocols(int i);

    ByteString getApplicationProtocolsBytes(int i);

    int getApplicationProtocolsCount();

    List<String> getApplicationProtocolsList();

    @Deprecated
    Map<Integer, ServerHandshakeParameters> getHandshakeParameters();

    int getHandshakeParametersCount();

    Map<Integer, ServerHandshakeParameters> getHandshakeParametersMap();

    ServerHandshakeParameters getHandshakeParametersOrDefault(int i, ServerHandshakeParameters serverHandshakeParameters);

    ServerHandshakeParameters getHandshakeParametersOrThrow(int i);

    ByteString getInBytes();

    Endpoint getLocalEndpoint();

    EndpointOrBuilder getLocalEndpointOrBuilder();

    int getMaxFrameSize();

    Endpoint getRemoteEndpoint();

    EndpointOrBuilder getRemoteEndpointOrBuilder();

    RpcProtocolVersions getRpcVersions();

    RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder();

    boolean hasLocalEndpoint();

    boolean hasRemoteEndpoint();

    boolean hasRpcVersions();
}
