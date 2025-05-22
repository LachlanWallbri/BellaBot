package com.google.api;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SourceInfoProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cgoogle/api/source_info.proto\u0012\ngoogle.api\u001a\u0019google/protobuf/any.proto\"8\n\nSourceInfo\u0012*\n\fsource_files\u0018\u0001 \u0003(\u000b2\u0014.google.protobuf.AnyBq\n\u000ecom.google.apiB\u000fSourceInfoProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_api_SourceInfo_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_SourceInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_SourceInfo_descriptor, new String[]{"SourceFiles"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SourceInfoProto() {
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
