package io.grpc.alts.internal;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class TransportSecurityCommonProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n(grpc/gcp/transport_security_common.proto\u0012\bgrpc.gcp\"¾\u0001\n\u0013RpcProtocolVersions\u0012>\n\u000fmax_rpc_version\u0018\u0001 \u0001(\u000b2%.grpc.gcp.RpcProtocolVersions.Version\u0012>\n\u000fmin_rpc_version\u0018\u0002 \u0001(\u000b2%.grpc.gcp.RpcProtocolVersions.Version\u001a'\n\u0007Version\u0012\r\n\u0005major\u0018\u0001 \u0001(\r\u0012\r\n\u0005minor\u0018\u0002 \u0001(\r*Q\n\rSecurityLevel\u0012\u0011\n\rSECURITY_NONE\u0010\u0000\u0012\u0012\n\u000eINTEGRITY_ONLY\u0010\u0001\u0012\u0019\n\u0015INTEGRITY_AND_PRIVACY\u0010\u0002Bx\n\u0015io.grpc.alts.internalB\u001cTransportSecurityCommonProtoP\u0001Z?google.golang.org/grpc/credentials/alts/internal/proto/grpc_gcpb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_grpc_gcp_RpcProtocolVersions_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_grpc_gcp_RpcProtocolVersions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_grpc_gcp_RpcProtocolVersions_descriptor, new String[]{"MaxRpcVersion", "MinRpcVersion"});
    static final Descriptors.Descriptor internal_static_grpc_gcp_RpcProtocolVersions_Version_descriptor = internal_static_grpc_gcp_RpcProtocolVersions_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_grpc_gcp_RpcProtocolVersions_Version_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f8317xc49d9678 = new GeneratedMessageV3.FieldAccessorTable(internal_static_grpc_gcp_RpcProtocolVersions_Version_descriptor, new String[]{"Major", "Minor"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private TransportSecurityCommonProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
