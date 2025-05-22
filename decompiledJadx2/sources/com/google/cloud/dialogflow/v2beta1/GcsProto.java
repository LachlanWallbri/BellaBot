package com.google.cloud.dialogflow.v2beta1;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class GcsProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n)google/cloud/dialogflow/v2beta1/gcs.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\"\u0018\n\tGcsSource\u0012\u000b\n\u0003uri\u0018\u0001 \u0001(\tB¦\u0001\n#com.google.cloud.dialogflow.v2beta1B\bGcsProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GcsSource_descriptor */
    static final Descriptors.Descriptor f1683x2dc2e4de = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GcsSource_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1684xf5664d5c = new GeneratedMessageV3.FieldAccessorTable(f1683x2dc2e4de, new String[]{"Uri"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private GcsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AnnotationsProto.getDescriptor();
    }
}
