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

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SessionEntityTypeProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n4google/cloud/dialogflow/v2/session_entity_type.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a,google/cloud/dialogflow/v2/entity_type.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\"Ï\u0003\n\u0011SessionEntityType\u0012\u0011\n\u0004name\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012c\n\u0014entity_override_mode\u0018\u0002 \u0001(\u000e2@.google.cloud.dialogflow.v2.SessionEntityType.EntityOverrideModeB\u0003àA\u0002\u0012D\n\bentities\u0018\u0003 \u0003(\u000b2-.google.cloud.dialogflow.v2.EntityType.EntityB\u0003àA\u0002\"\u0082\u0001\n\u0012EntityOverrideMode\u0012$\n ENTITY_OVERRIDE_MODE_UNSPECIFIED\u0010\u0000\u0012!\n\u001dENTITY_OVERRIDE_MODE_OVERRIDE\u0010\u0001\u0012#\n\u001fENTITY_OVERRIDE_MODE_SUPPLEMENT\u0010\u0002:wêAt\n+dialogflow.googleapis.com/SessionEntityType\u0012Eprojects/{project}/agent/sessions/{session}/entityTypes/{entity_type}\"e\n\u001dListSessionEntityTypesRequest\u0012\u0013\n\u0006parent\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u0016\n\tpage_size\u0018\u0002 \u0001(\u0005B\u0003àA\u0001\u0012\u0017\n\npage_token\u0018\u0003 \u0001(\tB\u0003àA\u0001\"\u0086\u0001\n\u001eListSessionEntityTypesResponse\u0012K\n\u0014session_entity_types\u0018\u0001 \u0003(\u000b2-.google.cloud.dialogflow.v2.SessionEntityType\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"`\n\u001bGetSessionEntityTypeRequest\u0012A\n\u0004name\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+dialogflow.googleapis.com/SessionEntityType\"\u0086\u0001\n\u001eCreateSessionEntityTypeRequest\u0012\u0013\n\u0006parent\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012O\n\u0013session_entity_type\u0018\u0002 \u0001(\u000b2-.google.cloud.dialogflow.v2.SessionEntityTypeB\u0003àA\u0002\"§\u0001\n\u001eUpdateSessionEntityTypeRequest\u0012O\n\u0013session_entity_type\u0018\u0001 \u0001(\u000b2-.google.cloud.dialogflow.v2.SessionEntityTypeB\u0003àA\u0002\u00124\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001\"Y\n\u001eDeleteSessionEntityTypeRequest\u00127\n\u0004name\u0018\u0001 \u0001(\tB)àA\u0002úA#\n!dialogflow.googleapis.com/Context2Ö\t\n\u0012SessionEntityTypes\u0012Ö\u0001\n\u0016ListSessionEntityTypes\u00129.google.cloud.dialogflow.v2.ListSessionEntityTypesRequest\u001a:.google.cloud.dialogflow.v2.ListSessionEntityTypesResponse\"E\u0082Óä\u0093\u00026\u00124/v2/{parent=projects/*/agent/sessions/*}/entityTypesÚA\u0006parent\u0012¼\u0001\n\u0014GetSessionEntityType\u00127.google.cloud.dialogflow.v2.GetSessionEntityTypeRequest\u001a-.google.cloud.dialogflow.v2.SessionEntityType\"<\u0082Óä\u0093\u00026\u00124/v2/{name=projects/*/agent/sessions/*/entityTypes/*}\u0012ô\u0001\n\u0017CreateSessionEntityType\u0012:.google.cloud.dialogflow.v2.CreateSessionEntityTypeRequest\u001a-.google.cloud.dialogflow.v2.SessionEntityType\"n\u0082Óä\u0093\u0002K\"4/v2/{parent=projects/*/agent/sessions/*}/entityTypes:\u0013session_entity_typeÚA\u001aparent,session_entity_type\u0012\u0081\u0002\n\u0017UpdateSessionEntityType\u0012:.google.cloud.dialogflow.v2.UpdateSessionEntityTypeRequest\u001a-.google.cloud.dialogflow.v2.SessionEntityType\"{\u0082Óä\u0093\u0002_2H/v2/{session_entity_type.name=projects/*/agent/sessions/*/entityTypes/*}:\u0013session_entity_typeÚA\u0013session_entity_type\u0012²\u0001\n\u0017DeleteSessionEntityType\u0012:.google.cloud.dialogflow.v2.DeleteSessionEntityTypeRequest\u001a\u0016.google.protobuf.Empty\"C\u0082Óä\u0093\u00026*4/v2/{name=projects/*/agent/sessions/*/entityTypes/*}ÚA\u0004name\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB¥\u0001\n\u001ecom.google.cloud.dialogflow.v2B\u0016SessionEntityTypeProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), EntityTypeProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SessionEntityType_descriptor */
    static final Descriptors.Descriptor f1542x3b471bf6 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SessionEntityType_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1543xd92dec74 = new GeneratedMessageV3.FieldAccessorTable(f1542x3b471bf6, new String[]{"Name", "EntityOverrideMode", "Entities"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListSessionEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1538xa5b8e018 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListSessionEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1539x58b26e96 = new GeneratedMessageV3.FieldAccessorTable(f1538xa5b8e018, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListSessionEntityTypesResponse_descriptor */
    static final Descriptors.Descriptor f1540x1ecf2854 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListSessionEntityTypesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1541x5b0abad2 = new GeneratedMessageV3.FieldAccessorTable(f1540x1ecf2854, new String[]{"SessionEntityTypes", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1536x563de237 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1537x315ee1b5 = new GeneratedMessageV3.FieldAccessorTable(f1536x563de237, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1532x9179b0fb = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1533x9751ac79 = new GeneratedMessageV3.FieldAccessorTable(f1532x9179b0fb, new String[]{"Parent", "SessionEntityType"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1544xfd7fde0e = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1545xa5ef168c = new GeneratedMessageV3.FieldAccessorTable(f1544xfd7fde0e, new String[]{"SessionEntityType", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1534xb5e5d5ec = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1535xc3c6506a = new GeneratedMessageV3.FieldAccessorTable(f1534xb5e5d5ec, new String[]{"Name"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SessionEntityTypeProto() {
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
        EntityTypeProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
    }
}
