package com.google.cloud.dialogflow.v2beta1;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.api.FieldBehaviorProto;
import com.google.longrunning.OperationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;
import com.google.rpc.StatusProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class DocumentProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n.google/cloud/dialogflow/v2beta1/document.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a)google/cloud/dialogflow/v2beta1/gcs.proto\u001a#google/longrunning/operations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u001fgoogle/protobuf/timestamp.proto\u001a\u0017google/rpc/status.proto\u001a\u0017google/api/client.proto\"¯\u0002\n\bDocument\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012\u0011\n\tmime_type\u0018\u0003 \u0001(\t\u0012P\n\u000fknowledge_types\u0018\u0004 \u0003(\u000e27.google.cloud.dialogflow.v2beta1.Document.KnowledgeType\u0012\u0015\n\u000bcontent_uri\u0018\u0005 \u0001(\tH\u0000\u0012\u0015\n\u0007content\u0018\u0006 \u0001(\tB\u0002\u0018\u0001H\u0000\u0012\u0015\n\u000braw_content\u0018\t \u0001(\fH\u0000\"K\n\rKnowledgeType\u0012\u001e\n\u001aKNOWLEDGE_TYPE_UNSPECIFIED\u0010\u0000\u0012\u0007\n\u0003FAQ\u0010\u0001\u0012\u0011\n\rEXTRACTIVE_QA\u0010\u0002B\b\n\u0006source\"M\n\u0014ListDocumentsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"n\n\u0015ListDocumentsResponse\u0012<\n\tdocuments\u0018\u0001 \u0003(\u000b2).google.cloud.dialogflow.v2beta1.Document\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"\"\n\u0012GetDocumentRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"d\n\u0015CreateDocumentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012;\n\bdocument\u0018\u0002 \u0001(\u000b2).google.cloud.dialogflow.v2beta1.Document\"%\n\u0015DeleteDocumentRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\u0085\u0001\n\u0015UpdateDocumentRequest\u0012;\n\bdocument\u0018\u0001 \u0001(\u000b2).google.cloud.dialogflow.v2beta1.Document\u0012/\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMask\"²\u0001\n\u001aKnowledgeOperationMetadata\u0012P\n\u0005state\u0018\u0001 \u0001(\u000e2A.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadata.State\"B\n\u0005State\u0012\u0015\n\u0011STATE_UNSPECIFIED\u0010\u0000\u0012\u000b\n\u0007PENDING\u0010\u0001\u0012\u000b\n\u0007RUNNING\u0010\u0002\u0012\b\n\u0004DONE\u0010\u0003\"q\n\u0015ReloadDocumentRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012@\n\ngcs_source\u0018\u0003 \u0001(\u000b2*.google.cloud.dialogflow.v2beta1.GcsSourceH\u0000B\b\n\u0006source2\u0081\r\n\tDocuments\u0012\u0081\u0002\n\rListDocuments\u00125.google.cloud.dialogflow.v2beta1.ListDocumentsRequest\u001a6.google.cloud.dialogflow.v2beta1.ListDocumentsResponse\"\u0080\u0001\u0082Óä\u0093\u0002z\u00127/v2beta1/{parent=projects/*/knowledgeBases/*}/documentsZ?\u0012=/v2beta1/{parent=projects/*/agent/knowledgeBases/*}/documents\u0012ð\u0001\n\u000bGetDocument\u00123.google.cloud.dialogflow.v2beta1.GetDocumentRequest\u001a).google.cloud.dialogflow.v2beta1.Document\"\u0080\u0001\u0082Óä\u0093\u0002z\u00127/v2beta1/{name=projects/*/knowledgeBases/*/documents/*}Z?\u0012=/v2beta1/{name=projects/*/agent/knowledgeBases/*/documents/*}\u0012ÿ\u0001\n\u000eCreateDocument\u00126.google.cloud.dialogflow.v2beta1.CreateDocumentRequest\u001a\u001d.google.longrunning.Operation\"\u0095\u0001\u0082Óä\u0093\u0002\u008e\u0001\"7/v2beta1/{parent=projects/*/knowledgeBases/*}/documents:\bdocumentZI\"=/v2beta1/{parent=projects/*/agent/knowledgeBases/*}/documents:\bdocument\u0012ê\u0001\n\u000eDeleteDocument\u00126.google.cloud.dialogflow.v2beta1.DeleteDocumentRequest\u001a\u001d.google.longrunning.Operation\"\u0080\u0001\u0082Óä\u0093\u0002z*7/v2beta1/{name=projects/*/knowledgeBases/*/documents/*}Z?*=/v2beta1/{name=projects/*/agent/knowledgeBases/*/documents/*}\u0012\u0091\u0002\n\u000eUpdateDocument\u00126.google.cloud.dialogflow.v2beta1.UpdateDocumentRequest\u001a\u001d.google.longrunning.Operation\"§\u0001\u0082Óä\u0093\u0002 \u00012@/v2beta1/{document.name=projects/*/knowledgeBases/*/documents/*}:\bdocumentZR2F/v2beta1/{document.name=projects/*/agent/knowledgeBases/*/documents/*}:\bdocument\u0012ÿ\u0001\n\u000eReloadDocument\u00126.google.cloud.dialogflow.v2beta1.ReloadDocumentRequest\u001a\u001d.google.longrunning.Operation\"\u0095\u0001\u0082Óä\u0093\u0002\u008e\u0001\">/v2beta1/{name=projects/*/knowledgeBases/*/documents/*}:reload:\u0001*ZI\"D/v2beta1/{name=projects/*/agent/knowledgeBases/*/documents/*}:reload:\u0001*\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB«\u0001\n#com.google.cloud.dialogflow.v2beta1B\rDocumentProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), GcsProto.getDescriptor(), OperationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), TimestampProto.getDescriptor(), StatusProto.getDescriptor(), ClientProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Document_descriptor */
    static final Descriptors.Descriptor f1638x827fabb1 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Document_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1639xf064912f = new GeneratedMessageV3.FieldAccessorTable(f1638x827fabb1, new String[]{"Name", "DisplayName", "MimeType", "KnowledgeTypes", "ContentUri", "Content", "RawContent", "Source"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListDocumentsRequest_descriptor */
    static final Descriptors.Descriptor f1644x7fcf7217 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListDocumentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1645x2fd29195 = new GeneratedMessageV3.FieldAccessorTable(f1644x7fcf7217, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListDocumentsResponse_descriptor */
    static final Descriptors.Descriptor f1646x878ad635 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListDocumentsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1647x67eef7b3 = new GeneratedMessageV3.FieldAccessorTable(f1646x878ad635, new String[]{"Documents", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetDocumentRequest_descriptor */
    static final Descriptors.Descriptor f1640xa4de54ae = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetDocumentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1641x453ced2c = new GeneratedMessageV3.FieldAccessorTable(f1640xa4de54ae, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateDocumentRequest_descriptor */
    static final Descriptors.Descriptor f1634xc876dbd8 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateDocumentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1635xba98aa56 = new GeneratedMessageV3.FieldAccessorTable(f1634xc876dbd8, new String[]{"Parent", "Document"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteDocumentRequest_descriptor */
    static final Descriptors.Descriptor f1636xeacd33c7 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteDocumentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1637xd48ba345 = new GeneratedMessageV3.FieldAccessorTable(f1636xeacd33c7, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateDocumentRequest_descriptor */
    static final Descriptors.Descriptor f1650x315d1d65 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateDocumentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1651xe75f0ee3 = new GeneratedMessageV3.FieldAccessorTable(f1650x315d1d65, new String[]{"Document", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeOperationMetadata_descriptor */
    static final Descriptors.Descriptor f1642x18a88854 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeOperationMetadata_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1643x15841ad2 = new GeneratedMessageV3.FieldAccessorTable(f1642x18a88854, new String[]{"State"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ReloadDocumentRequest_descriptor */
    static final Descriptors.Descriptor f1648xe1bb0235 = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ReloadDocumentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1649x553323b3 = new GeneratedMessageV3.FieldAccessorTable(f1648xe1bb0235, new String[]{"Name", "GcsSource", "Source"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DocumentProto() {
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
        GcsProto.getDescriptor();
        OperationsProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        TimestampProto.getDescriptor();
        StatusProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
