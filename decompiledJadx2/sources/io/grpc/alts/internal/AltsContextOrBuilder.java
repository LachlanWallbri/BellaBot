package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface AltsContextOrBuilder extends MessageOrBuilder {
    boolean containsPeerAttributes(String str);

    String getApplicationProtocol();

    ByteString getApplicationProtocolBytes();

    String getLocalServiceAccount();

    ByteString getLocalServiceAccountBytes();

    @Deprecated
    Map<String, String> getPeerAttributes();

    int getPeerAttributesCount();

    Map<String, String> getPeerAttributesMap();

    String getPeerAttributesOrDefault(String str, String str2);

    String getPeerAttributesOrThrow(String str);

    RpcProtocolVersions getPeerRpcVersions();

    RpcProtocolVersionsOrBuilder getPeerRpcVersionsOrBuilder();

    String getPeerServiceAccount();

    ByteString getPeerServiceAccountBytes();

    String getRecordProtocol();

    ByteString getRecordProtocolBytes();

    SecurityLevel getSecurityLevel();

    int getSecurityLevelValue();

    boolean hasPeerRpcVersions();
}
