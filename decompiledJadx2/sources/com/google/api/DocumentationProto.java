package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class DocumentationProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/api/documentation.proto\u0012\ngoogle.api\"»\u0001\n\rDocumentation\u0012\u000f\n\u0007summary\u0018\u0001 \u0001(\t\u0012\u001f\n\u0005pages\u0018\u0005 \u0003(\u000b2\u0010.google.api.Page\u0012,\n\u0005rules\u0018\u0003 \u0003(\u000b2\u001d.google.api.DocumentationRule\u0012\u001e\n\u0016documentation_root_url\u0018\u0004 \u0001(\t\u0012\u0018\n\u0010service_root_url\u0018\u0006 \u0001(\t\u0012\u0010\n\boverview\u0018\u0002 \u0001(\t\"[\n\u0011DocumentationRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\u001f\n\u0017deprecation_description\u0018\u0003 \u0001(\t\"I\n\u0004Page\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007content\u0018\u0002 \u0001(\t\u0012\"\n\bsubpages\u0018\u0003 \u0003(\u000b2\u0010.google.api.PageBt\n\u000ecom.google.apiB\u0012DocumentationProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Documentation_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Documentation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Documentation_descriptor, new String[]{"Summary", "Pages", "Rules", "DocumentationRootUrl", "ServiceRootUrl", "Overview"});
    static final Descriptors.Descriptor internal_static_google_api_DocumentationRule_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_DocumentationRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_DocumentationRule_descriptor, new String[]{"Selector", "Description", "DeprecationDescription"});
    static final Descriptors.Descriptor internal_static_google_api_Page_descriptor = getDescriptor().getMessageTypes().get(2);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Page_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Page_descriptor, new String[]{"Name", "Content", "Subpages"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DocumentationProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
