package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface StartClientHandshakeReqOrBuilder extends MessageOrBuilder {
    String getApplicationProtocols(int i);

    ByteString getApplicationProtocolsBytes(int i);

    int getApplicationProtocolsCount();

    List<String> getApplicationProtocolsList();

    HandshakeProtocol getHandshakeSecurityProtocol();

    int getHandshakeSecurityProtocolValue();

    Endpoint getLocalEndpoint();

    EndpointOrBuilder getLocalEndpointOrBuilder();

    Identity getLocalIdentity();

    IdentityOrBuilder getLocalIdentityOrBuilder();

    int getMaxFrameSize();

    String getRecordProtocols(int i);

    ByteString getRecordProtocolsBytes(int i);

    int getRecordProtocolsCount();

    List<String> getRecordProtocolsList();

    Endpoint getRemoteEndpoint();

    EndpointOrBuilder getRemoteEndpointOrBuilder();

    RpcProtocolVersions getRpcVersions();

    RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder();

    Identity getTargetIdentities(int i);

    int getTargetIdentitiesCount();

    List<Identity> getTargetIdentitiesList();

    IdentityOrBuilder getTargetIdentitiesOrBuilder(int i);

    List<? extends IdentityOrBuilder> getTargetIdentitiesOrBuilderList();

    String getTargetName();

    ByteString getTargetNameBytes();

    boolean hasLocalEndpoint();

    boolean hasLocalIdentity();

    boolean hasRemoteEndpoint();

    boolean hasRpcVersions();
}
