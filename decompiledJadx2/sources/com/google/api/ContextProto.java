package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class ContextProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/context.proto\u0012\ngoogle.api\"1\n\u0007Context\u0012&\n\u0005rules\u0018\u0001 \u0003(\u000b2\u0017.google.api.ContextRule\"\u008d\u0001\n\u000bContextRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\u0011\n\trequested\u0018\u0002 \u0003(\t\u0012\u0010\n\bprovided\u0018\u0003 \u0003(\t\u0012\"\n\u001aallowed_request_extensions\u0018\u0004 \u0003(\t\u0012#\n\u001ballowed_response_extensions\u0018\u0005 \u0003(\tBn\n\u000ecom.google.apiB\fContextProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Context_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Context_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Context_descriptor, new String[]{"Rules"});
    static final Descriptors.Descriptor internal_static_google_api_ContextRule_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_ContextRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_ContextRule_descriptor, new String[]{"Selector", "Requested", "Provided", "AllowedRequestExtensions", "AllowedResponseExtensions"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ContextProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
