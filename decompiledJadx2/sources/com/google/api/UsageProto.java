package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class UsageProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016google/api/usage.proto\u0012\ngoogle.api\"j\n\u0005Usage\u0012\u0014\n\frequirements\u0018\u0001 \u0003(\t\u0012$\n\u0005rules\u0018\u0006 \u0003(\u000b2\u0015.google.api.UsageRule\u0012%\n\u001dproducer_notification_channel\u0018\u0007 \u0001(\t\"]\n\tUsageRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012 \n\u0018allow_unregistered_calls\u0018\u0002 \u0001(\b\u0012\u001c\n\u0014skip_service_control\u0018\u0003 \u0001(\bBl\n\u000ecom.google.apiB\nUsageProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Usage_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Usage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Usage_descriptor, new String[]{"Requirements", "Rules", "ProducerNotificationChannel"});
    static final Descriptors.Descriptor internal_static_google_api_UsageRule_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_UsageRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_UsageRule_descriptor, new String[]{"Selector", "AllowUnregisteredCalls", "SkipServiceControl"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private UsageProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
