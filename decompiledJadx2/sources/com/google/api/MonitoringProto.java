package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class MonitoringProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bgoogle/api/monitoring.proto\u0012\ngoogle.api\"ì\u0001\n\nMonitoring\u0012K\n\u0015producer_destinations\u0018\u0001 \u0003(\u000b2,.google.api.Monitoring.MonitoringDestination\u0012K\n\u0015consumer_destinations\u0018\u0002 \u0003(\u000b2,.google.api.Monitoring.MonitoringDestination\u001aD\n\u0015MonitoringDestination\u0012\u001a\n\u0012monitored_resource\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007metrics\u0018\u0002 \u0003(\tBq\n\u000ecom.google.apiB\u000fMonitoringProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Monitoring_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Monitoring_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Monitoring_descriptor, new String[]{"ProducerDestinations", "ConsumerDestinations"});

    /* renamed from: internal_static_google_api_Monitoring_MonitoringDestination_descriptor */
    static final Descriptors.Descriptor f1334xc7be603 = internal_static_google_api_Monitoring_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_Monitoring_MonitoringDestination_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1335xbd725981 = new GeneratedMessageV3.FieldAccessorTable(f1334xc7be603, new String[]{"MonitoredResource", "Metrics"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MonitoringProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
