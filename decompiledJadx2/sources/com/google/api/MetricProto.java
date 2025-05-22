package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class MetricProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0017google/api/metric.proto\u0012\ngoogle.api\u001a\u0016google/api/label.proto\u001a\u001dgoogle/api/launch_stage.proto\u001a\u001egoogle/protobuf/duration.proto\"\u009f\u0006\n\u0010MetricDescriptor\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\f\n\u0004type\u0018\b \u0001(\t\u0012+\n\u0006labels\u0018\u0002 \u0003(\u000b2\u001b.google.api.LabelDescriptor\u0012<\n\u000bmetric_kind\u0018\u0003 \u0001(\u000e2'.google.api.MetricDescriptor.MetricKind\u0012:\n\nvalue_type\u0018\u0004 \u0001(\u000e2&.google.api.MetricDescriptor.ValueType\u0012\f\n\u0004unit\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0006 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0007 \u0001(\t\u0012G\n\bmetadata\u0018\n \u0001(\u000b25.google.api.MetricDescriptor.MetricDescriptorMetadata\u0012-\n\flaunch_stage\u0018\f \u0001(\u000e2\u0017.google.api.LaunchStage\u0012 \n\u0018monitored_resource_types\u0018\r \u0003(\t\u001a°\u0001\n\u0018MetricDescriptorMetadata\u00121\n\flaunch_stage\u0018\u0001 \u0001(\u000e2\u0017.google.api.LaunchStageB\u0002\u0018\u0001\u00120\n\rsample_period\u0018\u0002 \u0001(\u000b2\u0019.google.protobuf.Duration\u0012/\n\fingest_delay\u0018\u0003 \u0001(\u000b2\u0019.google.protobuf.Duration\"O\n\nMetricKind\u0012\u001b\n\u0017METRIC_KIND_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005GAUGE\u0010\u0001\u0012\t\n\u0005DELTA\u0010\u0002\u0012\u000e\n\nCUMULATIVE\u0010\u0003\"q\n\tValueType\u0012\u001a\n\u0016VALUE_TYPE_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004BOOL\u0010\u0001\u0012\t\n\u0005INT64\u0010\u0002\u0012\n\n\u0006DOUBLE\u0010\u0003\u0012\n\n\u0006STRING\u0010\u0004\u0012\u0010\n\fDISTRIBUTION\u0010\u0005\u0012\t\n\u0005MONEY\u0010\u0006\"u\n\u0006Metric\u0012\f\n\u0004type\u0018\u0003 \u0001(\t\u0012.\n\u0006labels\u0018\u0002 \u0003(\u000b2\u001e.google.api.Metric.LabelsEntry\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001B_\n\u000ecom.google.apiB\u000bMetricProtoP\u0001Z7google.golang.org/genproto/googleapis/api/metric;metric¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{LabelProto.getDescriptor(), LaunchStageProto.getDescriptor(), DurationProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_api_MetricDescriptor_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_MetricDescriptor_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_MetricDescriptor_descriptor, new String[]{"Name", "Type", "Labels", "MetricKind", "ValueType", "Unit", "Description", "DisplayName", "Metadata", "LaunchStage", "MonitoredResourceTypes"});

    /* renamed from: internal_static_google_api_MetricDescriptor_MetricDescriptorMetadata_descriptor */
    static final Descriptors.Descriptor f1325x4c8dfdfc = internal_static_google_api_MetricDescriptor_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_MetricDescriptor_MetricDescriptorMetadata_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1326x280d687a = new GeneratedMessageV3.FieldAccessorTable(f1325x4c8dfdfc, new String[]{"LaunchStage", "SamplePeriod", "IngestDelay"});
    static final Descriptors.Descriptor internal_static_google_api_Metric_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Metric_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Metric_descriptor, new String[]{"Type", "Labels"});
    static final Descriptors.Descriptor internal_static_google_api_Metric_LabelsEntry_descriptor = internal_static_google_api_Metric_descriptor.getNestedTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Metric_LabelsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Metric_LabelsEntry_descriptor, new String[]{"Key", "Value"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MetricProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        LabelProto.getDescriptor();
        LaunchStageProto.getDescriptor();
        DurationProto.getDescriptor();
    }
}
