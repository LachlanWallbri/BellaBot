package com.google.cloud.dialogflow.p049v2;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.api.FieldBehaviorProto;
import com.google.api.ResourceProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class ContextProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n(google/cloud/dialogflow/v2/context.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u001cgoogle/protobuf/struct.proto\"Ó\u0001\n\u0007Context\u0012\u0011\n\u0004name\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u001b\n\u000elifespan_count\u0018\u0002 \u0001(\u0005B\u0003àA\u0001\u00120\n\nparameters\u0018\u0003 \u0001(\u000b2\u0017.google.protobuf.StructB\u0003àA\u0001:fêAc\n!dialogflow.googleapis.com/Context\u0012>projects/{project}/agent/sessions/{session}/contexts/{context}\"[\n\u0013ListContextsRequest\u0012\u0013\n\u0006parent\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u0016\n\tpage_size\u0018\u0002 \u0001(\u0005B\u0003àA\u0001\u0012\u0017\n\npage_token\u0018\u0003 \u0001(\tB\u0003àA\u0001\"f\n\u0014ListContextsResponse\u00125\n\bcontexts\u0018\u0001 \u0003(\u000b2#.google.cloud.dialogflow.v2.Context\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"L\n\u0011GetContextRequest\u00127\n\u0004name\u0018\u0001 \u0001(\tB)àA\u0002úA#\n!dialogflow.googleapis.com/Context\"f\n\u0014CreateContextRequest\u0012\u0013\n\u0006parent\u0018\u0001 \u0001(\tB\u0003àA\u0002\u00129\n\u0007context\u0018\u0002 \u0001(\u000b2#.google.cloud.dialogflow.v2.ContextB\u0003àA\u0002\"\u0087\u0001\n\u0014UpdateContextRequest\u00129\n\u0007context\u0018\u0001 \u0001(\u000b2#.google.cloud.dialogflow.v2.ContextB\u0003àA\u0002\u00124\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001\"O\n\u0014DeleteContextRequest\u00127\n\u0004name\u0018\u0001 \u0001(\tB)àA\u0002úA#\n!dialogflow.googleapis.com/Context\"/\n\u0018DeleteAllContextsRequest\u0012\u0013\n\u0006parent\u0018\u0001 \u0001(\tB\u0003àA\u00022ò\b\n\bContexts\u0012µ\u0001\n\fListContexts\u0012/.google.cloud.dialogflow.v2.ListContextsRequest\u001a0.google.cloud.dialogflow.v2.ListContextsResponse\"B\u0082Óä\u0093\u00023\u00121/v2/{parent=projects/*/agent/sessions/*}/contextsÚA\u0006parent\u0012\u009b\u0001\n\nGetContext\u0012-.google.cloud.dialogflow.v2.GetContextRequest\u001a#.google.cloud.dialogflow.v2.Context\"9\u0082Óä\u0093\u00023\u00121/v2/{name=projects/*/agent/sessions/*/contexts/*}\u0012ª\u0001\n\rCreateContext\u00120.google.cloud.dialogflow.v2.CreateContextRequest\u001a#.google.cloud.dialogflow.v2.Context\"B\u0082Óä\u0093\u0002<\"1/v2/{parent=projects/*/agent/sessions/*}/contexts:\u0007context\u0012²\u0001\n\rUpdateContext\u00120.google.cloud.dialogflow.v2.UpdateContextRequest\u001a#.google.cloud.dialogflow.v2.Context\"J\u0082Óä\u0093\u0002D29/v2/{context.name=projects/*/agent/sessions/*/contexts/*}:\u0007context\u0012\u0094\u0001\n\rDeleteContext\u00120.google.cloud.dialogflow.v2.DeleteContextRequest\u001a\u0016.google.protobuf.Empty\"9\u0082Óä\u0093\u00023*1/v2/{name=projects/*/agent/sessions/*/contexts/*}\u0012\u009c\u0001\n\u0011DeleteAllContexts\u00124.google.cloud.dialogflow.v2.DeleteAllContextsRequest\u001a\u0016.google.protobuf.Empty\"9\u0082Óä\u0093\u00023*1/v2/{parent=projects/*/agent/sessions/*}/contexts\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB\u009b\u0001\n\u001ecom.google.cloud.dialogflow.v2B\fContextProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), StructProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_Context_descriptor = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Context_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1398x6fd19bf8 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_Context_descriptor, new String[]{"Name", "LifespanCount", "Parameters"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListContextsRequest_descriptor */
    static final Descriptors.Descriptor f1407x19e4251c = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListContextsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1408x34da6f9a = new GeneratedMessageV3.FieldAccessorTable(f1407x19e4251c, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListContextsResponse_descriptor */
    static final Descriptors.Descriptor f1409x300c83d0 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListContextsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1410x3e2da4e = new GeneratedMessageV3.FieldAccessorTable(f1409x300c83d0, new String[]{"Contexts", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetContextRequest_descriptor */
    static final Descriptors.Descriptor f1405x4df94a33 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1406xba208db1 = new GeneratedMessageV3.FieldAccessorTable(f1405x4df94a33, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateContextRequest_descriptor */
    static final Descriptors.Descriptor f1399x389ffdf7 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1400xfcc93d75 = new GeneratedMessageV3.FieldAccessorTable(f1399x389ffdf7, new String[]{"Parent", "Context"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateContextRequest_descriptor */
    static final Descriptors.Descriptor f1411x8654d6ca = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1412x488d9348 = new GeneratedMessageV3.FieldAccessorTable(f1411x8654d6ca, new String[]{"Context", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteContextRequest_descriptor */
    static final Descriptors.Descriptor f1403x29376c28 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1404x2f2beaa6 = new GeneratedMessageV3.FieldAccessorTable(f1403x29376c28, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteAllContextsRequest_descriptor */
    static final Descriptors.Descriptor f1401xd179155e = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteAllContextsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1402x7a23fddc = new GeneratedMessageV3.FieldAccessorTable(f1401xd179155e, new String[]{"Parent"});

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

    static {
        ExtensionRegistry newInstance = ExtensionRegistry.newInstance();
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.defaultHost);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) FieldBehaviorProto.fieldBehavior);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.methodSignature);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resource);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resourceReference);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        ClientProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        StructProto.getDescriptor();
    }
}
