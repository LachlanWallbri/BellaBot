package com.google.cloud.dialogflow.p049v2;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class WebhookProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n(google/cloud/dialogflow/v2/webhook.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a(google/cloud/dialogflow/v2/context.proto\u001a'google/cloud/dialogflow/v2/intent.proto\u001a(google/cloud/dialogflow/v2/session.proto\u001a4google/cloud/dialogflow/v2/session_entity_type.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001cgoogle/api/annotations.proto\"Ö\u0001\n\u000eWebhookRequest\u0012\u000f\n\u0007session\u0018\u0004 \u0001(\t\u0012\u0013\n\u000bresponse_id\u0018\u0001 \u0001(\t\u0012=\n\fquery_result\u0018\u0002 \u0001(\u000b2'.google.cloud.dialogflow.v2.QueryResult\u0012_\n\u001eoriginal_detect_intent_request\u0018\u0003 \u0001(\u000b27.google.cloud.dialogflow.v2.OriginalDetectIntentRequest\"\u0080\u0003\n\u000fWebhookResponse\u0012\u0018\n\u0010fulfillment_text\u0018\u0001 \u0001(\t\u0012H\n\u0014fulfillment_messages\u0018\u0002 \u0003(\u000b2*.google.cloud.dialogflow.v2.Intent.Message\u0012\u000e\n\u0006source\u0018\u0003 \u0001(\t\u0012(\n\u0007payload\u0018\u0004 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012<\n\u000foutput_contexts\u0018\u0005 \u0003(\u000b2#.google.cloud.dialogflow.v2.Context\u0012D\n\u0014followup_event_input\u0018\u0006 \u0001(\u000b2&.google.cloud.dialogflow.v2.EventInput\u0012K\n\u0014session_entity_types\u0018\n \u0003(\u000b2-.google.cloud.dialogflow.v2.SessionEntityType\"h\n\u001bOriginalDetectIntentRequest\u0012\u000e\n\u0006source\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007version\u0018\u0002 \u0001(\t\u0012(\n\u0007payload\u0018\u0003 \u0001(\u000b2\u0017.google.protobuf.StructB\u009b\u0001\n\u001ecom.google.cloud.dialogflow.v2B\fWebhookProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2b\u0006proto3"}, new Descriptors.FileDescriptor[]{ContextProto.getDescriptor(), IntentProto.getDescriptor(), SessionProto.getDescriptor(), SessionEntityTypeProto.getDescriptor(), StructProto.getDescriptor(), AnnotationsProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_WebhookRequest_descriptor */
    static final Descriptors.Descriptor f1574xbe30fa1b = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_WebhookRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1575x293dd599 = new GeneratedMessageV3.FieldAccessorTable(f1574xbe30fa1b, new String[]{"Session", "ResponseId", "QueryResult", "OriginalDetectIntentRequest"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_WebhookResponse_descriptor */
    static final Descriptors.Descriptor f1576x155a4eb1 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_WebhookResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1577x9bec342f = new GeneratedMessageV3.FieldAccessorTable(f1576x155a4eb1, new String[]{"FulfillmentText", "FulfillmentMessages", "Source", "Payload", "OutputContexts", "FollowupEventInput", "SessionEntityTypes"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_OriginalDetectIntentRequest_descriptor */
    static final Descriptors.Descriptor f1572x53a1a2ca = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_OriginalDetectIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1573x924e5f48 = new GeneratedMessageV3.FieldAccessorTable(f1572x53a1a2ca, new String[]{"Source", JsonDocumentFields.VERSION, "Payload"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private WebhookProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        ContextProto.getDescriptor();
        IntentProto.getDescriptor();
        SessionProto.getDescriptor();
        SessionEntityTypeProto.getDescriptor();
        StructProto.getDescriptor();
        AnnotationsProto.getDescriptor();
    }
}
