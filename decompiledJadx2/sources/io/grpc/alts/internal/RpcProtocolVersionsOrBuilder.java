package io.grpc.alts.internal;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.alts.internal.RpcProtocolVersions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface RpcProtocolVersionsOrBuilder extends MessageOrBuilder {
    RpcProtocolVersions.Version getMaxRpcVersion();

    RpcProtocolVersions.VersionOrBuilder getMaxRpcVersionOrBuilder();

    RpcProtocolVersions.Version getMinRpcVersion();

    RpcProtocolVersions.VersionOrBuilder getMinRpcVersionOrBuilder();

    boolean hasMaxRpcVersion();

    boolean hasMinRpcVersion();
}
