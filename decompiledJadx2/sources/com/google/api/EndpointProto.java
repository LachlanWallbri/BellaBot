package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class EndpointProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0019google/api/endpoint.proto\u0012\ngoogle.api\"Q\n\bEndpoint\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u0007aliases\u0018\u0002 \u0003(\tB\u0002\u0018\u0001\u0012\u000e\n\u0006target\u0018e \u0001(\t\u0012\u0012\n\nallow_cors\u0018\u0005 \u0001(\bBo\n\u000ecom.google.apiB\rEndpointProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Endpoint_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Endpoint_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Endpoint_descriptor, new String[]{"Name", "Aliases", "Target", "AllowCors"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private EndpointProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
