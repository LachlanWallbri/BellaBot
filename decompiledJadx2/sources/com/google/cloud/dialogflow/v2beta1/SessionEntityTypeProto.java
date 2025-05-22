package com.google.cloud.dialogflow.v2beta1;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class SessionEntityTypeProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n9google/cloud/dialogflow/v2beta1/session_entity_type.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a1google/cloud/dialogflow/v2beta1/entity_type.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u0017google/api/client.proto\"Ñ\u0002\n\u0011SessionEntityType\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012c\n\u0014entity_override_mode\u0018\u0002 \u0001(\u000e2E.google.cloud.dialogflow.v2beta1.SessionEntityType.EntityOverrideMode\u0012D\n\bentities\u0018\u0003 \u0003(\u000b22.google.cloud.dialogflow.v2beta1.EntityType.Entity\"\u0082\u0001\n\u0012EntityOverrideMode\u0012$\n ENTITY_OVERRIDE_MODE_UNSPECIFIED\u0010\u0000\u0012!\n\u001dENTITY_OVERRIDE_MODE_OVERRIDE\u0010\u0001\u0012#\n\u001fENTITY_OVERRIDE_MODE_SUPPLEMENT\u0010\u0002\"V\n\u001dListSessionEntityTypesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"\u008b\u0001\n\u001eListSessionEntityTypesResponse\u0012P\n\u0014session_entity_types\u0018\u0001 \u0003(\u000b22.google.cloud.dialogflow.v2beta1.SessionEntityType\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"+\n\u001bGetSessionEntityTypeRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\u0081\u0001\n\u001eCreateSessionEntityTypeRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012O\n\u0013session_entity_type\u0018\u0002 \u0001(\u000b22.google.cloud.dialogflow.v2beta1.SessionEntityType\"¢\u0001\n\u001eUpdateSessionEntityTypeRequest\u0012O\n\u0013session_entity_type\u0018\u0001 \u0001(\u000b22.google.cloud.dialogflow.v2beta1.SessionEntityType\u0012/\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMask\".\n\u001eDeleteSessionEntityTypeRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t2\u008f\u0015\n\u0012SessionEntityTypes\u0012Û\u0003\n\u0016ListSessionEntityTypes\u0012>.google.cloud.dialogflow.v2beta1.ListSessionEntityTypesRequest\u001a?.google.cloud.dialogflow.v2beta1.ListSessionEntityTypesResponse\"¿\u0002\u0082Óä\u0093\u0002¸\u0002\u00129/v2beta1/{parent=projects/*/agent/sessions/*}/entityTypesZR\u0012P/v2beta1/{parent=projects/*/agent/environments/*/users/*/sessions/*}/entityTypesZG\u0012E/v2beta1/{parent=projects/*/locations/*/agent/sessions/*}/entityTypesZ^\u0012\\/v2beta1/{parent=projects/*/locations/*/agent/environments/*/users/*/sessions/*}/entityTypes\u0012Ê\u0003\n\u0014GetSessionEntityType\u0012<.google.cloud.dialogflow.v2beta1.GetSessionEntityTypeRequest\u001a2.google.cloud.dialogflow.v2beta1.SessionEntityType\"¿\u0002\u0082Óä\u0093\u0002¸\u0002\u00129/v2beta1/{name=projects/*/agent/sessions/*/entityTypes/*}ZR\u0012P/v2beta1/{name=projects/*/agent/environments/*/users/*/sessions/*/entityTypes/*}ZG\u0012E/v2beta1/{name=projects/*/locations/*/agent/sessions/*/entityTypes/*}Z^\u0012\\/v2beta1/{name=projects/*/locations/*/agent/environments/*/users/*/sessions/*/entityTypes/*}\u0012¤\u0004\n\u0017CreateSessionEntityType\u0012?.google.cloud.dialogflow.v2beta1.CreateSessionEntityTypeRequest\u001a2.google.cloud.dialogflow.v2beta1.SessionEntityType\"\u0093\u0003\u0082Óä\u0093\u0002\u008c\u0003\"9/v2beta1/{parent=projects/*/agent/sessions/*}/entityTypes:\u0013session_entity_typeZg\"P/v2beta1/{parent=projects/*/agent/environments/*/users/*/sessions/*}/entityTypes:\u0013session_entity_typeZ\\\"E/v2beta1/{parent=projects/*/locations/*/agent/sessions/*}/entityTypes:\u0013session_entity_typeZs\"\\/v2beta1/{parent=projects/*/locations/*/agent/environments/*/users/*/sessions/*}/entityTypes:\u0013session_entity_type\u0012õ\u0004\n\u0017UpdateSessionEntityType\u0012?.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequest\u001a2.google.cloud.dialogflow.v2beta1.SessionEntityType\"ä\u0003\u0082Óä\u0093\u0002Ý\u00032M/v2beta1/{session_entity_type.name=projects/*/agent/sessions/*/entityTypes/*}:\u0013session_entity_typeZ{2d/v2beta1/{session_entity_type.name=projects/*/agent/environments/*/users/*/sessions/*/entityTypes/*}:\u0013session_entity_typeZp2Y/v2beta1/{session_entity_type.name=projects/*/locations/*/agent/sessions/*/entityTypes/*}:\u0013session_entity_typeZ\u0087\u00012p/v2beta1/{session_entity_type.name=projects/*/locations/*/agent/environments/*/users/*/sessions/*/entityTypes/*}:\u0013session_entity_type\u0012´\u0003\n\u0017DeleteSessionEntityType\u0012?.google.cloud.dialogflow.v2beta1.DeleteSessionEntityTypeRequest\u001a\u0016.google.protobuf.Empty\"¿\u0002\u0082Óä\u0093\u0002¸\u0002*9/v2beta1/{name=projects/*/agent/sessions/*/entityTypes/*}ZR*P/v2beta1/{name=projects/*/agent/environments/*/users/*/sessions/*/entityTypes/*}ZG*E/v2beta1/{name=projects/*/locations/*/agent/sessions/*/entityTypes/*}Z^*\\/v2beta1/{name=projects/*/locations/*/agent/environments/*/users/*/sessions/*/entityTypes/*}\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB´\u0001\n#com.google.cloud.dialogflow.v2beta1B\u0016SessionEntityTypeProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), EntityTypeProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), ClientProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SessionEntityType_descriptor */
    static final Descriptors.Descriptor f1832x7d94d95d = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SessionEntityType_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1833xabf752db = new GeneratedMessageV3.FieldAccessorTable(f1832x7d94d95d, new String[]{"Name", "EntityOverrideMode", "Entities"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListSessionEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1828x8f0abaff = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListSessionEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1829x714a727d = new GeneratedMessageV3.FieldAccessorTable(f1828x8f0abaff, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListSessionEntityTypesResponse_descriptor */
    static final Descriptors.Descriptor f1830x5fb8aa4d = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListSessionEntityTypesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1831x557333cb = new GeneratedMessageV3.FieldAccessorTable(f1830x5fb8aa4d, new String[]{"SessionEntityTypes", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1826x9c474ade = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1827xe424b35c = new GeneratedMessageV3.FieldAccessorTable(f1826x9c474ade, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1822xd26332f4 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1823x91ba2572 = new GeneratedMessageV3.FieldAccessorTable(f1822xd26332f4, new String[]{"Parent", "SessionEntityType"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1834x3e696007 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1835xa0578f85 = new GeneratedMessageV3.FieldAccessorTable(f1834x3e696007, new String[]{"SessionEntityType", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteSessionEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1824xf6cf57e5 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteSessionEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1825xbe2ec963 = new GeneratedMessageV3.FieldAccessorTable(f1824xf6cf57e5, new String[]{"Name"});

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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        EntityTypeProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
