package io.grpc.alts.internal;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class AltsHandshakerOptions {

    @Nullable
    private final RpcProtocolVersions rpcProtocolVersions;

    public AltsHandshakerOptions(RpcProtocolVersions rpcProtocolVersions) {
        this.rpcProtocolVersions = rpcProtocolVersions;
    }

    public RpcProtocolVersions getRpcProtocolVersions() {
        return this.rpcProtocolVersions;
    }
}
