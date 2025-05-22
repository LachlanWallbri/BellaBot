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
import com.google.protobuf.StructProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ContextProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n-google/cloud/dialogflow/v2beta1/context.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u0017google/api/client.proto\"\\\n\u0007Context\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0016\n\u000elifespan_count\u0018\u0002 \u0001(\u0005\u0012+\n\nparameters\u0018\u0003 \u0001(\u000b2\u0017.google.protobuf.Struct\"L\n\u0013ListContextsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"k\n\u0014ListContextsResponse\u0012:\n\bcontexts\u0018\u0001 \u0003(\u000b2(.google.cloud.dialogflow.v2beta1.Context\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"!\n\u0011GetContextRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"a\n\u0014CreateContextRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u00129\n\u0007context\u0018\u0002 \u0001(\u000b2(.google.cloud.dialogflow.v2beta1.Context\"\u0082\u0001\n\u0014UpdateContextRequest\u00129\n\u0007context\u0018\u0001 \u0001(\u000b2(.google.cloud.dialogflow.v2beta1.Context\u0012/\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMask\"$\n\u0014DeleteContextRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"*\n\u0018DeleteAllContextsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t2Ë\u0015\n\bContexts\u0012±\u0003\n\fListContexts\u00124.google.cloud.dialogflow.v2beta1.ListContextsRequest\u001a5.google.cloud.dialogflow.v2beta1.ListContextsResponse\"³\u0002\u0082Óä\u0093\u0002¬\u0002\u00126/v2beta1/{parent=projects/*/agent/sessions/*}/contextsZO\u0012M/v2beta1/{parent=projects/*/agent/environments/*/users/*/sessions/*}/contextsZD\u0012B/v2beta1/{parent=projects/*/locations/*/agent/sessions/*}/contextsZ[\u0012Y/v2beta1/{parent=projects/*/locations/*/agent/environments/*/users/*/sessions/*}/contexts\u0012 \u0003\n\nGetContext\u00122.google.cloud.dialogflow.v2beta1.GetContextRequest\u001a(.google.cloud.dialogflow.v2beta1.Context\"³\u0002\u0082Óä\u0093\u0002¬\u0002\u00126/v2beta1/{name=projects/*/agent/sessions/*/contexts/*}ZO\u0012M/v2beta1/{name=projects/*/agent/environments/*/users/*/sessions/*/contexts/*}ZD\u0012B/v2beta1/{name=projects/*/locations/*/agent/sessions/*/contexts/*}Z[\u0012Y/v2beta1/{name=projects/*/locations/*/agent/environments/*/users/*/sessions/*/contexts/*}\u0012Ê\u0003\n\rCreateContext\u00125.google.cloud.dialogflow.v2beta1.CreateContextRequest\u001a(.google.cloud.dialogflow.v2beta1.Context\"×\u0002\u0082Óä\u0093\u0002Ð\u0002\"6/v2beta1/{parent=projects/*/agent/sessions/*}/contexts:\u0007contextZX\"M/v2beta1/{parent=projects/*/agent/environments/*/users/*/sessions/*}/contexts:\u0007contextZM\"B/v2beta1/{parent=projects/*/locations/*/agent/sessions/*}/contexts:\u0007contextZd\"Y/v2beta1/{parent=projects/*/locations/*/agent/environments/*/users/*/sessions/*}/contexts:\u0007context\u0012ê\u0003\n\rUpdateContext\u00125.google.cloud.dialogflow.v2beta1.UpdateContextRequest\u001a(.google.cloud.dialogflow.v2beta1.Context\"÷\u0002\u0082Óä\u0093\u0002ð\u00022>/v2beta1/{context.name=projects/*/agent/sessions/*/contexts/*}:\u0007contextZ`2U/v2beta1/{context.name=projects/*/agent/environments/*/users/*/sessions/*/contexts/*}:\u0007contextZU2J/v2beta1/{context.name=projects/*/locations/*/agent/sessions/*/contexts/*}:\u0007contextZl2a/v2beta1/{context.name=projects/*/locations/*/agent/environments/*/users/*/sessions/*/contexts/*}:\u0007context\u0012\u0094\u0003\n\rDeleteContext\u00125.google.cloud.dialogflow.v2beta1.DeleteContextRequest\u001a\u0016.google.protobuf.Empty\"³\u0002\u0082Óä\u0093\u0002¬\u0002*6/v2beta1/{name=projects/*/agent/sessions/*/contexts/*}ZO*M/v2beta1/{name=projects/*/agent/environments/*/users/*/sessions/*/contexts/*}ZD*B/v2beta1/{name=projects/*/locations/*/agent/sessions/*/contexts/*}Z[*Y/v2beta1/{name=projects/*/locations/*/agent/environments/*/users/*/sessions/*/contexts/*}\u0012\u009c\u0003\n\u0011DeleteAllContexts\u00129.google.cloud.dialogflow.v2beta1.DeleteAllContextsRequest\u001a\u0016.google.protobuf.Empty\"³\u0002\u0082Óä\u0093\u0002¬\u0002*6/v2beta1/{parent=projects/*/agent/sessions/*}/contextsZO*M/v2beta1/{parent=projects/*/agent/environments/*/users/*/sessions/*}/contextsZD*B/v2beta1/{parent=projects/*/locations/*/agent/sessions/*}/contextsZ[*Y/v2beta1/{parent=projects/*/locations/*/agent/environments/*/users/*/sessions/*}/contexts\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowBª\u0001\n#com.google.cloud.dialogflow.v2beta1B\fContextProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), StructProto.getDescriptor(), ClientProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Context_descriptor */
    static final Descriptors.Descriptor f1617x691591a1 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Context_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1618xa96d871f = new GeneratedMessageV3.FieldAccessorTable(f1617x691591a1, new String[]{"Name", "LifespanCount", "Parameters"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListContextsRequest_descriptor */
    static final Descriptors.Descriptor f1627xffb824c3 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListContextsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1628x7ae3d841 = new GeneratedMessageV3.FieldAccessorTable(f1627xffb824c3, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListContextsResponse_descriptor */
    static final Descriptors.Descriptor f1629x4b87909 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListContextsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1630x7f068687 = new GeneratedMessageV3.FieldAccessorTable(f1629x4b87909, new String[]{"Contexts", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetContextRequest_descriptor */
    static final Descriptors.Descriptor f1625x9047079a = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1626x8ce9f418 = new GeneratedMessageV3.FieldAccessorTable(f1625x9047079a, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateContextRequest_descriptor */
    static final Descriptors.Descriptor f1619xd4bf330 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1620x77ece9ae = new GeneratedMessageV3.FieldAccessorTable(f1619xd4bf330, new String[]{"Parent", "Context"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateContextRequest_descriptor */
    static final Descriptors.Descriptor f1631x5b00cc03 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1632xc3b13f81 = new GeneratedMessageV3.FieldAccessorTable(f1631x5b00cc03, new String[]{"Context", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteContextRequest_descriptor */
    static final Descriptors.Descriptor f1623xfde36161 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteContextRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1624xaa4f96df = new GeneratedMessageV3.FieldAccessorTable(f1623xfde36161, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteAllContextsRequest_descriptor */
    static final Descriptors.Descriptor f1621xd845c617 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteAllContextsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1622x26b4e595 = new GeneratedMessageV3.FieldAccessorTable(f1621xd845c617, new String[]{"Parent"});

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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        StructProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
