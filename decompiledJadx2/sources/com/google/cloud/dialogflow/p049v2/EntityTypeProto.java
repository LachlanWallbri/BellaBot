package com.google.cloud.dialogflow.p049v2;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.api.FieldBehaviorProto;
import com.google.api.ResourceProto;
import com.google.longrunning.OperationsProto;
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
public final class EntityTypeProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n,google/cloud/dialogflow/v2/entity_type.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a#google/longrunning/operations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\"ø\u0004\n\nEntityType\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0019\n\fdisplay_name\u0018\u0002 \u0001(\tB\u0003àA\u0002\u0012>\n\u0004kind\u0018\u0003 \u0001(\u000e2+.google.cloud.dialogflow.v2.EntityType.KindB\u0003àA\u0002\u0012Z\n\u0013auto_expansion_mode\u0018\u0004 \u0001(\u000e28.google.cloud.dialogflow.v2.EntityType.AutoExpansionModeB\u0003àA\u0001\u0012D\n\bentities\u0018\u0006 \u0003(\u000b2-.google.cloud.dialogflow.v2.EntityType.EntityB\u0003àA\u0001\u0012$\n\u0017enable_fuzzy_extraction\u0018\u0007 \u0001(\bB\u0003àA\u0001\u001a3\n\u0006Entity\u0012\u0012\n\u0005value\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u0015\n\bsynonyms\u0018\u0002 \u0003(\tB\u0003àA\u0002\"J\n\u0004Kind\u0012\u0014\n\u0010KIND_UNSPECIFIED\u0010\u0000\u0012\f\n\bKIND_MAP\u0010\u0001\u0012\r\n\tKIND_LIST\u0010\u0002\u0012\u000f\n\u000bKIND_REGEXP\u0010\u0003\"Y\n\u0011AutoExpansionMode\u0012#\n\u001fAUTO_EXPANSION_MODE_UNSPECIFIED\u0010\u0000\u0012\u001f\n\u001bAUTO_EXPANSION_MODE_DEFAULT\u0010\u0001:]êAZ\n$dialogflow.googleapis.com/EntityType\u00122projects/{project}/agent/entityTypes/{entity_type}\"\u009e\u0001\n\u0016ListEntityTypesRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u0012\u001a\n\rlanguage_code\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012\u0016\n\tpage_size\u0018\u0003 \u0001(\u0005B\u0003àA\u0001\u0012\u0017\n\npage_token\u0018\u0004 \u0001(\tB\u0003àA\u0001\"p\n\u0017ListEntityTypesResponse\u0012<\n\fentity_types\u0018\u0001 \u0003(\u000b2&.google.cloud.dialogflow.v2.EntityType\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"n\n\u0014GetEntityTypeRequest\u0012:\n\u0004name\u0018\u0001 \u0001(\tB,àA\u0002úA&\n$dialogflow.googleapis.com/EntityType\u0012\u001a\n\rlanguage_code\u0018\u0002 \u0001(\tB\u0003àA\u0001\"°\u0001\n\u0017CreateEntityTypeRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u0012@\n\u000bentity_type\u0018\u0002 \u0001(\u000b2&.google.cloud.dialogflow.v2.EntityTypeB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u0001\"\u00ad\u0001\n\u0017UpdateEntityTypeRequest\u0012@\n\u000bentity_type\u0018\u0001 \u0001(\u000b2&.google.cloud.dialogflow.v2.EntityTypeB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0002 \u0001(\tB\u0003àA\u0001\u00124\n\u000bupdate_mask\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001\"U\n\u0017DeleteEntityTypeRequest\u0012:\n\u0004name\u0018\u0001 \u0001(\tB,àA\u0002úA&\n$dialogflow.googleapis.com/EntityType\"±\u0002\n\u001dBatchUpdateEntityTypesRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u0012\u001f\n\u0015entity_type_batch_uri\u0018\u0002 \u0001(\tH\u0000\u0012O\n\u0018entity_type_batch_inline\u0018\u0003 \u0001(\u000b2+.google.cloud.dialogflow.v2.EntityTypeBatchH\u0000\u0012\u001a\n\rlanguage_code\u0018\u0004 \u0001(\tB\u0003àA\u0001\u00124\n\u000bupdate_mask\u0018\u0005 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001B\u0013\n\u0011entity_type_batch\"^\n\u001eBatchUpdateEntityTypesResponse\u0012<\n\fentity_types\u0018\u0001 \u0003(\u000b2&.google.cloud.dialogflow.v2.EntityType\"x\n\u001dBatchDeleteEntityTypesRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u0012\u001e\n\u0011entity_type_names\u0018\u0002 \u0003(\tB\u0003àA\u0002\"¼\u0001\n\u001aBatchCreateEntitiesRequest\u0012<\n\u0006parent\u0018\u0001 \u0001(\tB,àA\u0002úA&\n$dialogflow.googleapis.com/EntityType\u0012D\n\bentities\u0018\u0002 \u0003(\u000b2-.google.cloud.dialogflow.v2.EntityType.EntityB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u0001\"ò\u0001\n\u001aBatchUpdateEntitiesRequest\u0012<\n\u0006parent\u0018\u0001 \u0001(\tB,àA\u0002úA&\n$dialogflow.googleapis.com/EntityType\u0012D\n\bentities\u0018\u0002 \u0003(\u000b2-.google.cloud.dialogflow.v2.EntityType.EntityB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u0001\u00124\n\u000bupdate_mask\u0018\u0004 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001\"\u0092\u0001\n\u001aBatchDeleteEntitiesRequest\u0012<\n\u0006parent\u0018\u0001 \u0001(\tB,àA\u0002úA&\n$dialogflow.googleapis.com/EntityType\u0012\u001a\n\rentity_values\u0018\u0002 \u0003(\tB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u0001\"O\n\u000fEntityTypeBatch\u0012<\n\fentity_types\u0018\u0001 \u0003(\u000b2&.google.cloud.dialogflow.v2.EntityType2ä\u0013\n\u000bEntityTypes\u0012Í\u0001\n\u000fListEntityTypes\u00122.google.cloud.dialogflow.v2.ListEntityTypesRequest\u001a3.google.cloud.dialogflow.v2.ListEntityTypesResponse\"Q\u0082Óä\u0093\u0002+\u0012)/v2/{parent=projects/*/agent}/entityTypesÚA\u0006parentÚA\u0014parent,language_code\u0012¸\u0001\n\rGetEntityType\u00120.google.cloud.dialogflow.v2.GetEntityTypeRequest\u001a&.google.cloud.dialogflow.v2.EntityType\"M\u0082Óä\u0093\u0002+\u0012)/v2/{name=projects/*/agent/entityTypes/*}ÚA\u0004nameÚA\u0012name,language_code\u0012ç\u0001\n\u0010CreateEntityType\u00123.google.cloud.dialogflow.v2.CreateEntityTypeRequest\u001a&.google.cloud.dialogflow.v2.EntityType\"v\u0082Óä\u0093\u00028\")/v2/{parent=projects/*/agent}/entityTypes:\u000bentity_typeÚA\u0012parent,entity_typeÚA parent,entity_type,language_code\u0012å\u0001\n\u0010UpdateEntityType\u00123.google.cloud.dialogflow.v2.UpdateEntityTypeRequest\u001a&.google.cloud.dialogflow.v2.EntityType\"t\u0082Óä\u0093\u0002D25/v2/{entity_type.name=projects/*/agent/entityTypes/*}:\u000bentity_typeÚA\u000bentity_typeÚA\u0019entity_type,language_code\u0012\u0099\u0001\n\u0010DeleteEntityType\u00123.google.cloud.dialogflow.v2.DeleteEntityTypeRequest\u001a\u0016.google.protobuf.Empty\"8\u0082Óä\u0093\u0002+*)/v2/{name=projects/*/agent/entityTypes/*}ÚA\u0004name\u0012\u008b\u0002\n\u0016BatchUpdateEntityTypes\u00129.google.cloud.dialogflow.v2.BatchUpdateEntityTypesRequest\u001a\u001d.google.longrunning.Operation\"\u0096\u0001\u0082Óä\u0093\u0002:\"5/v2/{parent=projects/*/agent}/entityTypes:batchUpdate:\u0001*ÊAS\n9google.cloud.dialogflow.v2.BatchUpdateEntityTypesResponse\u0012\u0016google.protobuf.Struct\u0012æ\u0001\n\u0016BatchDeleteEntityTypes\u00129.google.cloud.dialogflow.v2.BatchDeleteEntityTypesRequest\u001a\u001d.google.longrunning.Operation\"r\u0082Óä\u0093\u0002:\"5/v2/{parent=projects/*/agent}/entityTypes:batchDelete:\u0001*ÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u0012\u009e\u0002\n\u0013BatchCreateEntities\u00126.google.cloud.dialogflow.v2.BatchCreateEntitiesRequest\u001a\u001d.google.longrunning.Operation\"¯\u0001\u0082Óä\u0093\u0002E\"@/v2/{parent=projects/*/agent/entityTypes/*}/entities:batchCreate:\u0001*ÚA\u000fparent,entitiesÚA\u001dparent,entities,language_codeÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u0012\u009e\u0002\n\u0013BatchUpdateEntities\u00126.google.cloud.dialogflow.v2.BatchUpdateEntitiesRequest\u001a\u001d.google.longrunning.Operation\"¯\u0001\u0082Óä\u0093\u0002E\"@/v2/{parent=projects/*/agent/entityTypes/*}/entities:batchUpdate:\u0001*ÚA\u000fparent,entitiesÚA\u001dparent,entities,language_codeÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u0012¨\u0002\n\u0013BatchDeleteEntities\u00126.google.cloud.dialogflow.v2.BatchDeleteEntitiesRequest\u001a\u001d.google.longrunning.Operation\"¹\u0001\u0082Óä\u0093\u0002E\"@/v2/{parent=projects/*/agent/entityTypes/*}/entities:batchDelete:\u0001*ÚA\u0014parent,entity_valuesÚA\"parent,entity_values,language_codeÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB\u009e\u0001\n\u001ecom.google.cloud.dialogflow.v2B\u000fEntityTypeProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), OperationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_EntityType_descriptor = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_EntityType_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1433x4de7ae54 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_EntityType_descriptor, new String[]{"Name", "DisplayName", "Kind", "AutoExpansionMode", "Entities", "EnableFuzzyExtraction"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_EntityType_Entity_descriptor */
    static final Descriptors.Descriptor f1431x90c52ce4 = internal_static_google_cloud_dialogflow_v2_EntityType_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_EntityType_Entity_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1432xef3b2f62 = new GeneratedMessageV3.FieldAccessorTable(f1431x90c52ce4, new String[]{"Value", "Synonyms"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1436xe83d9b3c = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1437xadebc5ba = new GeneratedMessageV3.FieldAccessorTable(f1436xe83d9b3c, new String[]{"Parent", "LanguageCode", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListEntityTypesResponse_descriptor */
    static final Descriptors.Descriptor f1438x2ce1d1b0 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListEntityTypesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1439xacfc482e = new GeneratedMessageV3.FieldAccessorTable(f1438x2ce1d1b0, new String[]{"EntityTypes", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1434xf8abb297 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1435x4aa65215 = new GeneratedMessageV3.FieldAccessorTable(f1434xf8abb297, new String[]{"Name", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1425x95fd3853 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1426x76e45bd1 = new GeneratedMessageV3.FieldAccessorTable(f1425x95fd3853, new String[]{"Parent", "EntityType", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1440x5e594aa0 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1441x8ba3d11e = new GeneratedMessageV3.FieldAccessorTable(f1440x5e594aa0, new String[]{"EntityType", "LanguageCode", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1427x7c1d5082 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1428xdffcd500 = new GeneratedMessageV3.FieldAccessorTable(f1427x7c1d5082, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1421x2f4b836d = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1422xfdaaeceb = new GeneratedMessageV3.FieldAccessorTable(f1421x2f4b836d, new String[]{"Parent", "EntityTypeBatchUri", "EntityTypeBatchInline", "LanguageCode", "UpdateMask", "EntityTypeBatch"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateEntityTypesResponse_descriptor */
    static final Descriptors.Descriptor f1423xc790ef9f = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateEntityTypesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1424x5522071d = new GeneratedMessageV3.FieldAccessorTable(f1423xc790ef9f, new String[]{"EntityTypes"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchDeleteEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1417xca0839cb = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchDeleteEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1418x34726549 = new GeneratedMessageV3.FieldAccessorTable(f1417xca0839cb, new String[]{"Parent", "EntityTypeNames"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchCreateEntitiesRequest_descriptor */
    static final Descriptors.Descriptor f1413xffdd6adb = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchCreateEntitiesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1414x22cd8659 = new GeneratedMessageV3.FieldAccessorTable(f1413xffdd6adb, new String[]{"Parent", "Entities", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateEntitiesRequest_descriptor */
    static final Descriptors.Descriptor f1419x68c3ac68 = getDescriptor().getMessageTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateEntitiesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1420x4f93eae6 = new GeneratedMessageV3.FieldAccessorTable(f1419x68c3ac68, new String[]{"Parent", "Entities", "LanguageCode", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchDeleteEntitiesRequest_descriptor */
    static final Descriptors.Descriptor f1415x2233c2ca = getDescriptor().getMessageTypes().get(12);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchDeleteEntitiesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1416x3cc07f48 = new GeneratedMessageV3.FieldAccessorTable(f1415x2233c2ca, new String[]{"Parent", "EntityValues", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_EntityTypeBatch_descriptor */
    static final Descriptors.Descriptor f1429x50efd5ec = getDescriptor().getMessageTypes().get(13);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_EntityTypeBatch_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1430xb4d0506a = new GeneratedMessageV3.FieldAccessorTable(f1429x50efd5ec, new String[]{"EntityTypes"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private EntityTypeProto() {
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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) OperationsProto.operationInfo);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        ClientProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        OperationsProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
    }
}
