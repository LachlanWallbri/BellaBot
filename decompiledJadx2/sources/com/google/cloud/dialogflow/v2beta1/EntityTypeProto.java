package com.google.cloud.dialogflow.v2beta1;

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
/* loaded from: classes3.dex */
public final class EntityTypeProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n1google/cloud/dialogflow/v2beta1/entity_type.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a#google/longrunning/operations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u0017google/api/client.proto\"\u0085\u0004\n\nEntityType\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012>\n\u0004kind\u0018\u0003 \u0001(\u000e20.google.cloud.dialogflow.v2beta1.EntityType.Kind\u0012Z\n\u0013auto_expansion_mode\u0018\u0004 \u0001(\u000e2=.google.cloud.dialogflow.v2beta1.EntityType.AutoExpansionMode\u0012D\n\bentities\u0018\u0006 \u0003(\u000b22.google.cloud.dialogflow.v2beta1.EntityType.Entity\u0012\u001f\n\u0017enable_fuzzy_extraction\u0018\u0007 \u0001(\b\u001a)\n\u0006Entity\u0012\r\n\u0005value\u0018\u0001 \u0001(\t\u0012\u0010\n\bsynonyms\u0018\u0002 \u0003(\t\"J\n\u0004Kind\u0012\u0014\n\u0010KIND_UNSPECIFIED\u0010\u0000\u0012\f\n\bKIND_MAP\u0010\u0001\u0012\r\n\tKIND_LIST\u0010\u0002\u0012\u000f\n\u000bKIND_REGEXP\u0010\u0003\"Y\n\u0011AutoExpansionMode\u0012#\n\u001fAUTO_EXPANSION_MODE_UNSPECIFIED\u0010\u0000\u0012\u001f\n\u001bAUTO_EXPANSION_MODE_DEFAULT\u0010\u0001\"f\n\u0016ListEntityTypesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0003 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0004 \u0001(\t\"u\n\u0017ListEntityTypesResponse\u0012A\n\fentity_types\u0018\u0001 \u0003(\u000b2+.google.cloud.dialogflow.v2beta1.EntityType\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\";\n\u0014GetEntityTypeRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\t\"\u0082\u0001\n\u0017CreateEntityTypeRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012@\n\u000bentity_type\u0018\u0002 \u0001(\u000b2+.google.cloud.dialogflow.v2beta1.EntityType\u0012\u0015\n\rlanguage_code\u0018\u0003 \u0001(\t\"£\u0001\n\u0017UpdateEntityTypeRequest\u0012@\n\u000bentity_type\u0018\u0001 \u0001(\u000b2+.google.cloud.dialogflow.v2beta1.EntityType\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\t\u0012/\n\u000bupdate_mask\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.FieldMask\"'\n\u0017DeleteEntityTypeRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\u0083\u0002\n\u001dBatchUpdateEntityTypesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u001f\n\u0015entity_type_batch_uri\u0018\u0002 \u0001(\tH\u0000\u0012T\n\u0018entity_type_batch_inline\u0018\u0003 \u0001(\u000b20.google.cloud.dialogflow.v2beta1.EntityTypeBatchH\u0000\u0012\u0015\n\rlanguage_code\u0018\u0004 \u0001(\t\u0012/\n\u000bupdate_mask\u0018\u0005 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0013\n\u0011entity_type_batch\"c\n\u001eBatchUpdateEntityTypesResponse\u0012A\n\fentity_types\u0018\u0001 \u0003(\u000b2+.google.cloud.dialogflow.v2beta1.EntityType\"J\n\u001dBatchDeleteEntityTypesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0019\n\u0011entity_type_names\u0018\u0002 \u0003(\t\"\u0089\u0001\n\u001aBatchCreateEntitiesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012D\n\bentities\u0018\u0002 \u0003(\u000b22.google.cloud.dialogflow.v2beta1.EntityType.Entity\u0012\u0015\n\rlanguage_code\u0018\u0003 \u0001(\t\"º\u0001\n\u001aBatchUpdateEntitiesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012D\n\bentities\u0018\u0002 \u0003(\u000b22.google.cloud.dialogflow.v2beta1.EntityType.Entity\u0012\u0015\n\rlanguage_code\u0018\u0003 \u0001(\t\u0012/\n\u000bupdate_mask\u0018\u0004 \u0001(\u000b2\u001a.google.protobuf.FieldMask\"Z\n\u001aBatchDeleteEntitiesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0015\n\rentity_values\u0018\u0002 \u0003(\t\u0012\u0015\n\rlanguage_code\u0018\u0003 \u0001(\t\"T\n\u000fEntityTypeBatch\u0012A\n\fentity_types\u0018\u0001 \u0003(\u000b2+.google.cloud.dialogflow.v2beta1.EntityType2\u0081\u0016\n\u000bEntityTypes\u0012ú\u0001\n\u000fListEntityTypes\u00127.google.cloud.dialogflow.v2beta1.ListEntityTypesRequest\u001a8.google.cloud.dialogflow.v2beta1.ListEntityTypesResponse\"t\u0082Óä\u0093\u0002n\u0012./v2beta1/{parent=projects/*/agent}/entityTypesZ<\u0012:/v2beta1/{parent=projects/*/locations/*/agent}/entityTypes\u0012é\u0001\n\rGetEntityType\u00125.google.cloud.dialogflow.v2beta1.GetEntityTypeRequest\u001a+.google.cloud.dialogflow.v2beta1.EntityType\"t\u0082Óä\u0093\u0002n\u0012./v2beta1/{name=projects/*/agent/entityTypes/*}Z<\u0012:/v2beta1/{name=projects/*/locations/*/agent/entityTypes/*}\u0012\u008b\u0002\n\u0010CreateEntityType\u00128.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequest\u001a+.google.cloud.dialogflow.v2beta1.EntityType\"\u008f\u0001\u0082Óä\u0093\u0002\u0088\u0001\"./v2beta1/{parent=projects/*/agent}/entityTypes:\u000bentity_typeZI\":/v2beta1/{parent=projects/*/locations/*/agent}/entityTypes:\u000bentity_type\u0012£\u0002\n\u0010UpdateEntityType\u00128.google.cloud.dialogflow.v2beta1.UpdateEntityTypeRequest\u001a+.google.cloud.dialogflow.v2beta1.EntityType\"§\u0001\u0082Óä\u0093\u0002 \u00012:/v2beta1/{entity_type.name=projects/*/agent/entityTypes/*}:\u000bentity_typeZU2F/v2beta1/{entity_type.name=projects/*/locations/*/agent/entityTypes/*}:\u000bentity_type\u0012Ú\u0001\n\u0010DeleteEntityType\u00128.google.cloud.dialogflow.v2beta1.DeleteEntityTypeRequest\u001a\u0016.google.protobuf.Empty\"t\u0082Óä\u0093\u0002n*./v2beta1/{name=projects/*/agent/entityTypes/*}Z<*:/v2beta1/{name=projects/*/locations/*/agent/entityTypes/*}\u0012\u008d\u0002\n\u0016BatchUpdateEntityTypes\u0012>.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequest\u001a\u001d.google.longrunning.Operation\"\u0093\u0001\u0082Óä\u0093\u0002\u008c\u0001\":/v2beta1/{parent=projects/*/agent}/entityTypes:batchUpdate:\u0001*ZK\"F/v2beta1/{parent=projects/*/locations/*/agent}/entityTypes:batchUpdate:\u0001*\u0012\u008d\u0002\n\u0016BatchDeleteEntityTypes\u0012>.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequest\u001a\u001d.google.longrunning.Operation\"\u0093\u0001\u0082Óä\u0093\u0002\u008c\u0001\":/v2beta1/{parent=projects/*/agent}/entityTypes:batchDelete:\u0001*ZK\"F/v2beta1/{parent=projects/*/locations/*/agent}/entityTypes:batchDelete:\u0001*\u0012\u009d\u0002\n\u0013BatchCreateEntities\u0012;.google.cloud.dialogflow.v2beta1.BatchCreateEntitiesRequest\u001a\u001d.google.longrunning.Operation\"©\u0001\u0082Óä\u0093\u0002¢\u0001\"E/v2beta1/{parent=projects/*/agent/entityTypes/*}/entities:batchCreate:\u0001*ZV\"Q/v2beta1/{parent=projects/*/locations/*/agent/entityTypes/*}/entities:batchCreate:\u0001*\u0012\u009d\u0002\n\u0013BatchUpdateEntities\u0012;.google.cloud.dialogflow.v2beta1.BatchUpdateEntitiesRequest\u001a\u001d.google.longrunning.Operation\"©\u0001\u0082Óä\u0093\u0002¢\u0001\"E/v2beta1/{parent=projects/*/agent/entityTypes/*}/entities:batchUpdate:\u0001*ZV\"Q/v2beta1/{parent=projects/*/locations/*/agent/entityTypes/*}/entities:batchUpdate:\u0001*\u0012\u009d\u0002\n\u0013BatchDeleteEntities\u0012;.google.cloud.dialogflow.v2beta1.BatchDeleteEntitiesRequest\u001a\u001d.google.longrunning.Operation\"©\u0001\u0082Óä\u0093\u0002¢\u0001\"E/v2beta1/{parent=projects/*/agent/entityTypes/*}/entities:batchDelete:\u0001*ZV\"Q/v2beta1/{parent=projects/*/locations/*/agent/entityTypes/*}/entities:batchDelete:\u0001*\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB\u00ad\u0001\n#com.google.cloud.dialogflow.v2beta1B\u000fEntityTypeProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), OperationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), ClientProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EntityType_descriptor */
    static final Descriptors.Descriptor f1672xfb5a364f = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EntityType_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1673x55519dcd = new GeneratedMessageV3.FieldAccessorTable(f1672xfb5a364f, new String[]{"Name", "DisplayName", "Kind", "AutoExpansionMode", "Entities", "EnableFuzzyExtraction"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EntityType_Entity_descriptor */
    static final Descriptors.Descriptor f1670xd312ea4b = f1672xfb5a364f.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EntityType_Entity_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1671xc20495c9 = new GeneratedMessageV3.FieldAccessorTable(f1670xd312ea4b, new String[]{"Value", "Synonyms"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1676x41c12635 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1677xeed547b3 = new GeneratedMessageV3.FieldAccessorTable(f1676x41c12635, new String[]{"Parent", "LanguageCode", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListEntityTypesResponse_descriptor */
    static final Descriptors.Descriptor f1678x3cfa5d7 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListEntityTypesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1679x89430555 = new GeneratedMessageV3.FieldAccessorTable(f1678x3cfa5d7, new String[]{"EntityTypes", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1674xcd57a7d0 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1675xc5c9fe4e = new GeneratedMessageV3.FieldAccessorTable(f1674xcd57a7d0, new String[]{"Name", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1664x6ceb0c7a = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1665x532b18f8 = new GeneratedMessageV3.FieldAccessorTable(f1664x6ceb0c7a, new String[]{"Parent", "EntityType", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1680x35471ec7 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1681x67ea8e45 = new GeneratedMessageV3.FieldAccessorTable(f1680x35471ec7, new String[]{"EntityType", "LanguageCode", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteEntityTypeRequest_descriptor */
    static final Descriptors.Descriptor f1666x530b24a9 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteEntityTypeRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1667xbc439227 = new GeneratedMessageV3.FieldAccessorTable(f1666x530b24a9, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1660x189d5e54 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1661x1642f0d2 = new GeneratedMessageV3.FieldAccessorTable(f1660x189d5e54, new String[]{"Parent", "EntityTypeBatchUri", "EntityTypeBatchInline", "LanguageCode", "UpdateMask", "EntityTypeBatch"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateEntityTypesResponse_descriptor */
    static final Descriptors.Descriptor f1662x87a7198 = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateEntityTypesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1663x4f8a8016 = new GeneratedMessageV3.FieldAccessorTable(f1662x87a7198, new String[]{"EntityTypes"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchDeleteEntityTypesRequest_descriptor */
    static final Descriptors.Descriptor f1656xb35a14b2 = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchDeleteEntityTypesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1657x4d0a6930 = new GeneratedMessageV3.FieldAccessorTable(f1656xb35a14b2, new String[]{"Parent", "EntityTypeNames"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchCreateEntitiesRequest_descriptor */
    static final Descriptors.Descriptor f1652x8640d154 = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchCreateEntitiesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1653xeec363d2 = new GeneratedMessageV3.FieldAccessorTable(f1652x8640d154, new String[]{"Parent", "Entities", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateEntitiesRequest_descriptor */
    static final Descriptors.Descriptor f1658xef2712e1 = getDescriptor().getMessageTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateEntitiesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1659x1b89c85f = new GeneratedMessageV3.FieldAccessorTable(f1658xef2712e1, new String[]{"Parent", "Entities", "LanguageCode", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchDeleteEntitiesRequest_descriptor */
    static final Descriptors.Descriptor f1654xa8972943 = getDescriptor().getMessageTypes().get(12);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchDeleteEntitiesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1655x8b65cc1 = new GeneratedMessageV3.FieldAccessorTable(f1654xa8972943, new String[]{"Parent", "EntityValues", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EntityTypeBatch_descriptor */
    static final Descriptors.Descriptor f1668x8a8bc113 = getDescriptor().getMessageTypes().get(13);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EntityTypeBatch_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1669x8bbe2491 = new GeneratedMessageV3.FieldAccessorTable(f1668x8a8bc113, new String[]{"EntityTypes"});

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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        OperationsProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
