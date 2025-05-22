package com.google.cloud.dialogflow.v2beta1;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class WebhookProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n-google/cloud/dialogflow/v2beta1/webhook.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a-google/cloud/dialogflow/v2beta1/context.proto\u001a,google/cloud/dialogflow/v2beta1/intent.proto\u001a-google/cloud/dialogflow/v2beta1/session.proto\u001a9google/cloud/dialogflow/v2beta1/session_entity_type.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001cgoogle/api/annotations.proto\"±\u0002\n\u000eWebhookRequest\u0012\u000f\n\u0007session\u0018\u0004 \u0001(\t\u0012\u0013\n\u000bresponse_id\u0018\u0001 \u0001(\t\u0012B\n\fquery_result\u0018\u0002 \u0001(\u000b2,.google.cloud.dialogflow.v2beta1.QueryResult\u0012O\n\u0019alternative_query_results\u0018\u0005 \u0003(\u000b2,.google.cloud.dialogflow.v2beta1.QueryResult\u0012d\n\u001eoriginal_detect_intent_request\u0018\u0003 \u0001(\u000b2<.google.cloud.dialogflow.v2beta1.OriginalDetectIntentRequest\"\u00ad\u0003\n\u000fWebhookResponse\u0012\u0018\n\u0010fulfillment_text\u0018\u0001 \u0001(\t\u0012M\n\u0014fulfillment_messages\u0018\u0002 \u0003(\u000b2/.google.cloud.dialogflow.v2beta1.Intent.Message\u0012\u000e\n\u0006source\u0018\u0003 \u0001(\t\u0012(\n\u0007payload\u0018\u0004 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012A\n\u000foutput_contexts\u0018\u0005 \u0003(\u000b2(.google.cloud.dialogflow.v2beta1.Context\u0012I\n\u0014followup_event_input\u0018\u0006 \u0001(\u000b2+.google.cloud.dialogflow.v2beta1.EventInput\u0012\u0017\n\u000fend_interaction\u0018\b \u0001(\b\u0012P\n\u0014session_entity_types\u0018\n \u0003(\u000b22.google.cloud.dialogflow.v2beta1.SessionEntityType\"h\n\u001bOriginalDetectIntentRequest\u0012\u000e\n\u0006source\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007version\u0018\u0002 \u0001(\t\u0012(\n\u0007payload\u0018\u0003 \u0001(\u000b2\u0017.google.protobuf.StructBª\u0001\n#com.google.cloud.dialogflow.v2beta1B\fWebhookProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{ContextProto.getDescriptor(), IntentProto.getDescriptor(), SessionProto.getDescriptor(), SessionEntityTypeProto.getDescriptor(), StructProto.getDescriptor(), AnnotationsProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_WebhookRequest_descriptor */
    static final Descriptors.Descriptor f1874x2b678e14 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_WebhookRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1875x82c16092 = new GeneratedMessageV3.FieldAccessorTable(f1874x2b678e14, new String[]{"Session", "ResponseId", "QueryResult", "AlternativeQueryResults", "OriginalDetectIntentRequest"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_WebhookResponse_descriptor */
    static final Descriptors.Descriptor f1876x4ef639d8 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_WebhookResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1877x72da0856 = new GeneratedMessageV3.FieldAccessorTable(f1876x4ef639d8, new String[]{"FulfillmentText", "FulfillmentMessages", "Source", "Payload", "OutputContexts", "FollowupEventInput", "EndInteraction", "SessionEntityTypes"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_OriginalDetectIntentRequest_descriptor */
    static final Descriptors.Descriptor f1872x99ab0b71 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_OriginalDetectIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1873x451430ef = new GeneratedMessageV3.FieldAccessorTable(f1872x99ab0b71, new String[]{"Source", JsonDocumentFields.VERSION, "Payload"});

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
