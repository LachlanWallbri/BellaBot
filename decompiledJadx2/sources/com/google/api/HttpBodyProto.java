package com.google.api;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class HttpBodyProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0019google/api/httpbody.proto\u0012\ngoogle.api\u001a\u0019google/protobuf/any.proto\"X\n\bHttpBody\u0012\u0014\n\fcontent_type\u0018\u0001 \u0001(\t\u0012\f\n\u0004data\u0018\u0002 \u0001(\f\u0012(\n\nextensions\u0018\u0003 \u0003(\u000b2\u0014.google.protobuf.AnyBh\n\u000ecom.google.apiB\rHttpBodyProtoP\u0001Z;google.golang.org/genproto/googleapis/api/httpbody;httpbodyø\u0001\u0001¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_api_HttpBody_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_HttpBody_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_HttpBody_descriptor, new String[]{"ContentType", "Data", "Extensions"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private HttpBodyProto() {
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
