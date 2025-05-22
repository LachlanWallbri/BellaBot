package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class BackendProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/backend.proto\u0012\ngoogle.api\"1\n\u0007Backend\u0012&\n\u0005rules\u0018\u0001 \u0003(\u000b2\u0017.google.api.BackendRule\"ò\u0002\n\u000bBackendRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007address\u0018\u0002 \u0001(\t\u0012\u0010\n\bdeadline\u0018\u0003 \u0001(\u0001\u0012\u0014\n\fmin_deadline\u0018\u0004 \u0001(\u0001\u0012\u001a\n\u0012operation_deadline\u0018\u0005 \u0001(\u0001\u0012A\n\u0010path_translation\u0018\u0006 \u0001(\u000e2'.google.api.BackendRule.PathTranslation\u0012\u0016\n\fjwt_audience\u0018\u0007 \u0001(\tH\u0000\u0012\u0016\n\fdisable_auth\u0018\b \u0001(\bH\u0000\u0012\u0010\n\bprotocol\u0018\t \u0001(\t\"e\n\u000fPathTranslation\u0012 \n\u001cPATH_TRANSLATION_UNSPECIFIED\u0010\u0000\u0012\u0014\n\u0010CONSTANT_ADDRESS\u0010\u0001\u0012\u001a\n\u0016APPEND_PATH_TO_ADDRESS\u0010\u0002B\u0010\n\u000eauthenticationBn\n\u000ecom.google.apiB\fBackendProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Backend_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Backend_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Backend_descriptor, new String[]{"Rules"});
    static final Descriptors.Descriptor internal_static_google_api_BackendRule_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_BackendRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_BackendRule_descriptor, new String[]{"Selector", "Address", "Deadline", "MinDeadline", "OperationDeadline", "PathTranslation", "JwtAudience", "DisableAuth", "Protocol", "Authentication"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private BackendProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
