package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class MonitoredResourceProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n#google/api/monitored_resource.proto\u0012\ngoogle.api\u001a\u0016google/api/label.proto\u001a\u001dgoogle/api/launch_stage.proto\u001a\u001cgoogle/protobuf/struct.proto\"À\u0001\n\u001bMonitoredResourceDescriptor\u0012\f\n\u0004name\u0018\u0005 \u0001(\t\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\u0012+\n\u0006labels\u0018\u0004 \u0003(\u000b2\u001b.google.api.LabelDescriptor\u0012-\n\flaunch_stage\u0018\u0007 \u0001(\u000e2\u0017.google.api.LaunchStage\"\u008b\u0001\n\u0011MonitoredResource\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u00129\n\u0006labels\u0018\u0002 \u0003(\u000b2).google.api.MonitoredResource.LabelsEntry\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"Ê\u0001\n\u0019MonitoredResourceMetadata\u0012.\n\rsystem_labels\u0018\u0001 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012J\n\u000buser_labels\u0018\u0002 \u0003(\u000b25.google.api.MonitoredResourceMetadata.UserLabelsEntry\u001a1\n\u000fUserLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001By\n\u000ecom.google.apiB\u0016MonitoredResourceProtoP\u0001ZCgoogle.golang.org/genproto/googleapis/api/monitoredres;monitoredresø\u0001\u0001¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{LabelProto.getDescriptor(), LaunchStageProto.getDescriptor(), StructProto.getDescriptor()});

    /* renamed from: internal_static_google_api_MonitoredResourceDescriptor_descriptor */
    static final Descriptors.Descriptor f1327x25127934 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_api_MonitoredResourceDescriptor_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1328x8cdf2bb2 = new GeneratedMessageV3.FieldAccessorTable(f1327x25127934, new String[]{"Name", "Type", "DisplayName", "Description", "Labels", "LaunchStage"});
    static final Descriptors.Descriptor internal_static_google_api_MonitoredResource_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_MonitoredResource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_MonitoredResource_descriptor, new String[]{"Type", "Labels"});

    /* renamed from: internal_static_google_api_MonitoredResource_LabelsEntry_descriptor */
    static final Descriptors.Descriptor f1332xbbbca48f = internal_static_google_api_MonitoredResource_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_MonitoredResource_LabelsEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1333xc81cc0d = new GeneratedMessageV3.FieldAccessorTable(f1332xbbbca48f, new String[]{"Key", "Value"});
    static final Descriptors.Descriptor internal_static_google_api_MonitoredResourceMetadata_descriptor = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1331xb523e532 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_MonitoredResourceMetadata_descriptor, new String[]{"SystemLabels", "UserLabels"});

    /* renamed from: internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_descriptor */
    static final Descriptors.Descriptor f1329xf517ba8b = internal_static_google_api_MonitoredResourceMetadata_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1330x4b552609 = new GeneratedMessageV3.FieldAccessorTable(f1329xf517ba8b, new String[]{"Key", "Value"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MonitoredResourceProto() {
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
        StructProto.getDescriptor();
    }
}
