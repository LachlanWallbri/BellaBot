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
public final class KnowledgeBaseProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n4google/cloud/dialogflow/v2beta1/knowledge_base.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u0017google/api/client.proto\"J\n\rKnowledgeBase\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u0004 \u0001(\t\"R\n\u0019ListKnowledgeBasesRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"~\n\u001aListKnowledgeBasesResponse\u0012G\n\u000fknowledge_bases\u0018\u0001 \u0003(\u000b2..google.cloud.dialogflow.v2beta1.KnowledgeBase\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"'\n\u0017GetKnowledgeBaseRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"t\n\u001aCreateKnowledgeBaseRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012F\n\u000eknowledge_base\u0018\u0002 \u0001(\u000b2..google.cloud.dialogflow.v2beta1.KnowledgeBase\"9\n\u001aDeleteKnowledgeBaseRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\r\n\u0005force\u0018\u0002 \u0001(\b\"\u0095\u0001\n\u001aUpdateKnowledgeBaseRequest\u0012F\n\u000eknowledge_base\u0018\u0001 \u0001(\u000b2..google.cloud.dialogflow.v2beta1.KnowledgeBase\u0012/\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMask2\u0084\u000b\n\u000eKnowledgeBases\u0012÷\u0001\n\u0012ListKnowledgeBases\u0012:.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesRequest\u001a;.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponse\"h\u0082Óä\u0093\u0002b\u0012+/v2beta1/{parent=projects/*}/knowledgeBasesZ3\u00121/v2beta1/{parent=projects/*/agent}/knowledgeBases\u0012æ\u0001\n\u0010GetKnowledgeBase\u00128.google.cloud.dialogflow.v2beta1.GetKnowledgeBaseRequest\u001a..google.cloud.dialogflow.v2beta1.KnowledgeBase\"h\u0082Óä\u0093\u0002b\u0012+/v2beta1/{name=projects/*/knowledgeBases/*}Z3\u00121/v2beta1/{name=projects/*/agent/knowledgeBases/*}\u0012\u008e\u0002\n\u0013CreateKnowledgeBase\u0012;.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequest\u001a..google.cloud.dialogflow.v2beta1.KnowledgeBase\"\u0089\u0001\u0082Óä\u0093\u0002\u0082\u0001\"+/v2beta1/{parent=projects/*}/knowledgeBases:\u000eknowledge_baseZC\"1/v2beta1/{parent=projects/*/agent}/knowledgeBases:\u000eknowledge_base\u0012Ô\u0001\n\u0013DeleteKnowledgeBase\u0012;.google.cloud.dialogflow.v2beta1.DeleteKnowledgeBaseRequest\u001a\u0016.google.protobuf.Empty\"h\u0082Óä\u0093\u0002b*+/v2beta1/{name=projects/*/knowledgeBases/*}Z3*1/v2beta1/{name=projects/*/agent/knowledgeBases/*}\u0012¬\u0002\n\u0013UpdateKnowledgeBase\u0012;.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequest\u001a..google.cloud.dialogflow.v2beta1.KnowledgeBase\"§\u0001\u0082Óä\u0093\u0002 \u00012:/v2beta1/{knowledge_base.name=projects/*/knowledgeBases/*}:\u000eknowledge_baseZR2@/v2beta1/{knowledge_base.name=projects/*/agent/knowledgeBases/*}:\u000eknowledge_base\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB°\u0001\n#com.google.cloud.dialogflow.v2beta1B\u0012KnowledgeBaseProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), ClientProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeBase_descriptor */
    static final Descriptors.Descriptor f1811x57225961 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeBase_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1812x31168edf = new GeneratedMessageV3.FieldAccessorTable(f1811x57225961, new String[]{"Name", "DisplayName", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListKnowledgeBasesRequest_descriptor */
    static final Descriptors.Descriptor f1813x8f9a0603 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListKnowledgeBasesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1814xd0707981 = new GeneratedMessageV3.FieldAccessorTable(f1813x8f9a0603, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListKnowledgeBasesResponse_descriptor */
    static final Descriptors.Descriptor f1815x7112bfc9 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListKnowledgeBasesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1816xdb0e0d47 = new GeneratedMessageV3.FieldAccessorTable(f1815x7112bfc9, new String[]{"KnowledgeBases", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetKnowledgeBaseRequest_descriptor */
    static final Descriptors.Descriptor f1809x2612155a = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetKnowledgeBaseRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1810xd4ab41d8 = new GeneratedMessageV3.FieldAccessorTable(f1809x2612155a, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateKnowledgeBaseRequest_descriptor */
    static final Descriptors.Descriptor f1805x27617870 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateKnowledgeBaseRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1806x4bc92eee = new GeneratedMessageV3.FieldAccessorTable(f1805x27617870, new String[]{"Parent", "KnowledgeBase"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteKnowledgeBaseRequest_descriptor */
    static final Descriptors.Descriptor f1807x183fbde1 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteKnowledgeBaseRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1808x62c7735f = new GeneratedMessageV3.FieldAccessorTable(f1807x183fbde1, new String[]{"Name", "Force"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateKnowledgeBaseRequest_descriptor */
    static final Descriptors.Descriptor f1817x39d72703 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateKnowledgeBaseRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1818xbffc9a81 = new GeneratedMessageV3.FieldAccessorTable(f1817x39d72703, new String[]{"KnowledgeBase", "UpdateMask"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private KnowledgeBaseProto() {
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
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
