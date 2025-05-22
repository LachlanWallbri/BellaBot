package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class ConfigChangeProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/api/config_change.proto\u0012\ngoogle.api\"\u0097\u0001\n\fConfigChange\u0012\u000f\n\u0007element\u0018\u0001 \u0001(\t\u0012\u0011\n\told_value\u0018\u0002 \u0001(\t\u0012\u0011\n\tnew_value\u0018\u0003 \u0001(\t\u0012+\n\u000bchange_type\u0018\u0004 \u0001(\u000e2\u0016.google.api.ChangeType\u0012#\n\u0007advices\u0018\u0005 \u0003(\u000b2\u0012.google.api.Advice\"\u001d\n\u0006Advice\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t*O\n\nChangeType\u0012\u001b\n\u0017CHANGE_TYPE_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005ADDED\u0010\u0001\u0012\u000b\n\u0007REMOVED\u0010\u0002\u0012\f\n\bMODIFIED\u0010\u0003Bq\n\u000ecom.google.apiB\u0011ConfigChangeProtoP\u0001ZCgoogle.golang.org/genproto/googleapis/api/configchange;configchange¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_ConfigChange_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_ConfigChange_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_ConfigChange_descriptor, new String[]{"Element", "OldValue", "NewValue", "ChangeType", "Advices"});
    static final Descriptors.Descriptor internal_static_google_api_Advice_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Advice_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Advice_descriptor, new String[]{"Description"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ConfigChangeProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
