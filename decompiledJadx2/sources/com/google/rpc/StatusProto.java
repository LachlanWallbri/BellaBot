package com.google.rpc;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class StatusProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0017google/rpc/status.proto\u0012\ngoogle.rpc\u001a\u0019google/protobuf/any.proto\"N\n\u0006Status\u0012\f\n\u0004code\u0018\u0001 \u0001(\u0005\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\u0012%\n\u0007details\u0018\u0003 \u0003(\u000b2\u0014.google.protobuf.AnyBa\n\u000ecom.google.rpcB\u000bStatusProtoP\u0001Z7google.golang.org/genproto/googleapis/rpc/status;statusø\u0001\u0001¢\u0002\u0003RPCb\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_rpc_Status_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_Status_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_Status_descriptor, new String[]{AttributeLayout.ATTRIBUTE_CODE, "Message", "Details"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private StatusProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AnyProto.getDescriptor();
    }
}
