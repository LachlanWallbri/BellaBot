package io.grpc.alts.internal;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsInternalContext {
    final AltsContext context;

    public AltsInternalContext(HandshakerResult handshakerResult) {
        this.context = AltsContext.newBuilder().setApplicationProtocol(handshakerResult.getApplicationProtocol()).setRecordProtocol(handshakerResult.getRecordProtocol()).setSecurityLevel(SecurityLevel.INTEGRITY_AND_PRIVACY).setPeerServiceAccount(handshakerResult.getPeerIdentity().getServiceAccount()).setLocalServiceAccount(handshakerResult.getLocalIdentity().getServiceAccount()).setPeerRpcVersions(handshakerResult.getPeerRpcVersions()).putAllPeerAttributes(handshakerResult.getPeerIdentity().getAttributesMap()).build();
    }

    public static AltsInternalContext getDefaultInstance() {
        return new AltsInternalContext(HandshakerResult.newBuilder().build());
    }

    public String getApplicationProtocol() {
        return this.context.getApplicationProtocol();
    }

    public String getRecordProtocol() {
        return this.context.getRecordProtocol();
    }

    public SecurityLevel getSecurityLevel() {
        return this.context.getSecurityLevel();
    }

    public String getPeerServiceAccount() {
        return this.context.getPeerServiceAccount();
    }

    public String getLocalServiceAccount() {
        return this.context.getLocalServiceAccount();
    }

    public RpcProtocolVersions getPeerRpcVersions() {
        return this.context.getPeerRpcVersions();
    }

    public Map<String, String> getPeerAttributes() {
        return this.context.getPeerAttributesMap();
    }
}
