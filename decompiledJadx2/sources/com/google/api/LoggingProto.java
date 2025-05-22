package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class LoggingProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/logging.proto\u0012\ngoogle.api\"×\u0001\n\u0007Logging\u0012E\n\u0015producer_destinations\u0018\u0001 \u0003(\u000b2&.google.api.Logging.LoggingDestination\u0012E\n\u0015consumer_destinations\u0018\u0002 \u0003(\u000b2&.google.api.Logging.LoggingDestination\u001a>\n\u0012LoggingDestination\u0012\u001a\n\u0012monitored_resource\u0018\u0003 \u0001(\t\u0012\f\n\u0004logs\u0018\u0001 \u0003(\tBn\n\u000ecom.google.apiB\fLoggingProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Logging_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Logging_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Logging_descriptor, new String[]{"ProducerDestinations", "ConsumerDestinations"});
    static final Descriptors.Descriptor internal_static_google_api_Logging_LoggingDestination_descriptor = internal_static_google_api_Logging_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_Logging_LoggingDestination_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1324xf0bafe21 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Logging_LoggingDestination_descriptor, new String[]{"MonitoredResource", "Logs"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LoggingProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
