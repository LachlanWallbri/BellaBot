package com.google.cloud.dialogflow.p049v2;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.api.FieldBehaviorProto;
import com.google.api.ResourceProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.rpc.StatusProto;
import com.google.type.LatLngProto;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SessionProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n(google/cloud/dialogflow/v2/session.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a-google/cloud/dialogflow/v2/audio_config.proto\u001a(google/cloud/dialogflow/v2/context.proto\u001a'google/cloud/dialogflow/v2/intent.proto\u001a4google/cloud/dialogflow/v2/session_entity_type.proto\u001a\u001egoogle/protobuf/duration.proto\u001a google/protobuf/field_mask.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u0017google/rpc/status.proto\u001a\u0018google/type/latlng.proto\"õ\u0002\n\u0013DetectIntentRequest\u0012:\n\u0007session\u0018\u0001 \u0001(\tB)àA\u0002úA#\n!dialogflow.googleapis.com/Session\u0012A\n\fquery_params\u0018\u0002 \u0001(\u000b2+.google.cloud.dialogflow.v2.QueryParameters\u0012@\n\u000bquery_input\u0018\u0003 \u0001(\u000b2&.google.cloud.dialogflow.v2.QueryInputB\u0003àA\u0002\u0012J\n\u0013output_audio_config\u0018\u0004 \u0001(\u000b2-.google.cloud.dialogflow.v2.OutputAudioConfig\u0012<\n\u0018output_audio_config_mask\u0018\u0007 \u0001(\u000b2\u001a.google.protobuf.FieldMask\u0012\u0013\n\u000binput_audio\u0018\u0005 \u0001(\f\"ø\u0001\n\u0014DetectIntentResponse\u0012\u0013\n\u000bresponse_id\u0018\u0001 \u0001(\t\u0012=\n\fquery_result\u0018\u0002 \u0001(\u000b2'.google.cloud.dialogflow.v2.QueryResult\u0012*\n\u000ewebhook_status\u0018\u0003 \u0001(\u000b2\u0012.google.rpc.Status\u0012\u0014\n\foutput_audio\u0018\u0004 \u0001(\f\u0012J\n\u0013output_audio_config\u0018\u0006 \u0001(\u000b2-.google.cloud.dialogflow.v2.OutputAudioConfig\"ü\u0002\n\u000fQueryParameters\u0012\u0011\n\ttime_zone\u0018\u0001 \u0001(\t\u0012)\n\fgeo_location\u0018\u0002 \u0001(\u000b2\u0013.google.type.LatLng\u00125\n\bcontexts\u0018\u0003 \u0003(\u000b2#.google.cloud.dialogflow.v2.Context\u0012\u0016\n\u000ereset_contexts\u0018\u0004 \u0001(\b\u0012K\n\u0014session_entity_types\u0018\u0005 \u0003(\u000b2-.google.cloud.dialogflow.v2.SessionEntityType\u0012(\n\u0007payload\u0018\u0006 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012e\n!sentiment_analysis_request_config\u0018\n \u0001(\u000b2:.google.cloud.dialogflow.v2.SentimentAnalysisRequestConfig\"Ë\u0001\n\nQueryInput\u0012D\n\faudio_config\u0018\u0001 \u0001(\u000b2,.google.cloud.dialogflow.v2.InputAudioConfigH\u0000\u00125\n\u0004text\u0018\u0002 \u0001(\u000b2%.google.cloud.dialogflow.v2.TextInputH\u0000\u00127\n\u0005event\u0018\u0003 \u0001(\u000b2&.google.cloud.dialogflow.v2.EventInputH\u0000B\u0007\n\u0005input\"\u0090\u0005\n\u000bQueryResult\u0012\u0012\n\nquery_text\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u000f \u0001(\t\u0012%\n\u001dspeech_recognition_confidence\u0018\u0002 \u0001(\u0002\u0012\u000e\n\u0006action\u0018\u0003 \u0001(\t\u0012+\n\nparameters\u0018\u0004 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012#\n\u001ball_required_params_present\u0018\u0005 \u0001(\b\u0012\u0018\n\u0010fulfillment_text\u0018\u0006 \u0001(\t\u0012H\n\u0014fulfillment_messages\u0018\u0007 \u0003(\u000b2*.google.cloud.dialogflow.v2.Intent.Message\u0012\u0016\n\u000ewebhook_source\u0018\b \u0001(\t\u00120\n\u000fwebhook_payload\u0018\t \u0001(\u000b2\u0017.google.protobuf.Struct\u0012<\n\u000foutput_contexts\u0018\n \u0003(\u000b2#.google.cloud.dialogflow.v2.Context\u00122\n\u0006intent\u0018\u000b \u0001(\u000b2\".google.cloud.dialogflow.v2.Intent\u0012#\n\u001bintent_detection_confidence\u0018\f \u0001(\u0002\u00120\n\u000fdiagnostic_info\u0018\u000e \u0001(\u000b2\u0017.google.protobuf.Struct\u0012V\n\u0019sentiment_analysis_result\u0018\u0011 \u0001(\u000b23.google.cloud.dialogflow.v2.SentimentAnalysisResult\"ö\u0002\n\u001cStreamingDetectIntentRequest\u0012\u0014\n\u0007session\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012A\n\fquery_params\u0018\u0002 \u0001(\u000b2+.google.cloud.dialogflow.v2.QueryParameters\u0012@\n\u000bquery_input\u0018\u0003 \u0001(\u000b2&.google.cloud.dialogflow.v2.QueryInputB\u0003àA\u0002\u0012\u001c\n\u0010single_utterance\u0018\u0004 \u0001(\bB\u0002\u0018\u0001\u0012J\n\u0013output_audio_config\u0018\u0005 \u0001(\u000b2-.google.cloud.dialogflow.v2.OutputAudioConfig\u0012<\n\u0018output_audio_config_mask\u0018\u0007 \u0001(\u000b2\u001a.google.protobuf.FieldMask\u0012\u0013\n\u000binput_audio\u0018\u0006 \u0001(\f\"Õ\u0002\n\u001dStreamingDetectIntentResponse\u0012\u0013\n\u000bresponse_id\u0018\u0001 \u0001(\t\u0012R\n\u0012recognition_result\u0018\u0002 \u0001(\u000b26.google.cloud.dialogflow.v2.StreamingRecognitionResult\u0012=\n\fquery_result\u0018\u0003 \u0001(\u000b2'.google.cloud.dialogflow.v2.QueryResult\u0012*\n\u000ewebhook_status\u0018\u0004 \u0001(\u000b2\u0012.google.rpc.Status\u0012\u0014\n\foutput_audio\u0018\u0005 \u0001(\f\u0012J\n\u0013output_audio_config\u0018\u0006 \u0001(\u000b2-.google.cloud.dialogflow.v2.OutputAudioConfig\"\u0086\u0003\n\u001aStreamingRecognitionResult\u0012X\n\fmessage_type\u0018\u0001 \u0001(\u000e2B.google.cloud.dialogflow.v2.StreamingRecognitionResult.MessageType\u0012\u0012\n\ntranscript\u0018\u0002 \u0001(\t\u0012\u0010\n\bis_final\u0018\u0003 \u0001(\b\u0012\u0012\n\nconfidence\u0018\u0004 \u0001(\u0002\u0012D\n\u0010speech_word_info\u0018\u0007 \u0003(\u000b2*.google.cloud.dialogflow.v2.SpeechWordInfo\u00124\n\u0011speech_end_offset\u0018\b \u0001(\u000b2\u0019.google.protobuf.Duration\"X\n\u000bMessageType\u0012\u001c\n\u0018MESSAGE_TYPE_UNSPECIFIED\u0010\u0000\u0012\u000e\n\nTRANSCRIPT\u0010\u0001\u0012\u001b\n\u0017END_OF_SINGLE_UTTERANCE\u0010\u0002\":\n\tTextInput\u0012\u0011\n\u0004text\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0002 \u0001(\tB\u0003àA\u0002\"h\n\nEventInput\u0012\u0011\n\u0004name\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012+\n\nparameters\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u0002\"F\n\u001eSentimentAnalysisRequestConfig\u0012$\n\u001canalyze_query_text_sentiment\u0018\u0001 \u0001(\b\"^\n\u0017SentimentAnalysisResult\u0012C\n\u0014query_text_sentiment\u0018\u0001 \u0001(\u000b2%.google.cloud.dialogflow.v2.Sentiment\"-\n\tSentiment\u0012\r\n\u0005score\u0018\u0001 \u0001(\u0002\u0012\u0011\n\tmagnitude\u0018\u0002 \u0001(\u00022æ\u0003\n\bSessions\u0012Ê\u0001\n\fDetectIntent\u0012/.google.cloud.dialogflow.v2.DetectIntentRequest\u001a0.google.cloud.dialogflow.v2.DetectIntentResponse\"W\u0082Óä\u0093\u0002;\"6/v2/{session=projects/*/agent/sessions/*}:detectIntent:\u0001*ÚA\u0013session,query_input\u0012\u0092\u0001\n\u0015StreamingDetectIntent\u00128.google.cloud.dialogflow.v2.StreamingDetectIntentRequest\u001a9.google.cloud.dialogflow.v2.StreamingDetectIntentResponse\"\u0000(\u00010\u0001\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB±\u0002\n\u001ecom.google.cloud.dialogflow.v2B\fSessionProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2êA\u0092\u0001\n!dialogflow.googleapis.com/Session\u0012@projects/{project}/locations/{location}/agent/sessions/{session}\u0012+projects/{project}/agent/sessions/{session}b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), AudioConfigProto.getDescriptor(), ContextProto.getDescriptor(), IntentProto.getDescriptor(), SessionEntityTypeProto.getDescriptor(), DurationProto.getDescriptor(), FieldMaskProto.getDescriptor(), StructProto.getDescriptor(), StatusProto.getDescriptor(), LatLngProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DetectIntentRequest_descriptor */
    static final Descriptors.Descriptor f1546xfd63fb99 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DetectIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1547xc38e7917 = new GeneratedMessageV3.FieldAccessorTable(f1546xfd63fb99, new String[]{"Session", "QueryParams", "QueryInput", "OutputAudioConfig", "OutputAudioConfigMask", "InputAudio"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DetectIntentResponse_descriptor */
    static final Descriptors.Descriptor f1548xbc877cf3 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DetectIntentResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1549x4bb00071 = new GeneratedMessageV3.FieldAccessorTable(f1548xbc877cf3, new String[]{"ResponseId", "QueryResult", "WebhookStatus", "OutputAudio", "OutputAudioConfig"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_QueryParameters_descriptor */
    static final Descriptors.Descriptor f1552x3112f977 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_QueryParameters_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1553x9e48b8f5 = new GeneratedMessageV3.FieldAccessorTable(f1552x3112f977, new String[]{"TimeZone", "GeoLocation", "Contexts", "ResetContexts", "SessionEntityTypes", "Payload", "SentimentAnalysisRequestConfig"});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_QueryInput_descriptor = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_QueryInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1551x7cb69c0f = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_QueryInput_descriptor, new String[]{"AudioConfig", "Text", MoveError.LEVEL_EVENT, "Input"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_QueryResult_descriptor */
    static final Descriptors.Descriptor f1554x2dca66e4 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_QueryResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1555x38666962 = new GeneratedMessageV3.FieldAccessorTable(f1554x2dca66e4, new String[]{"QueryText", "LanguageCode", "SpeechRecognitionConfidence", JsonDocumentFields.ACTION, "Parameters", "AllRequiredParamsPresent", "FulfillmentText", "FulfillmentMessages", "WebhookSource", "WebhookPayload", "OutputContexts", "Intent", "IntentDetectionConfidence", "DiagnosticInfo", "SentimentAnalysisResult"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_StreamingDetectIntentRequest_descriptor */
    static final Descriptors.Descriptor f1561xed85505 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_StreamingDetectIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1562x7878a683 = new GeneratedMessageV3.FieldAccessorTable(f1561xed85505, new String[]{"Session", "QueryParams", "QueryInput", "SingleUtterance", "OutputAudioConfig", "OutputAudioConfigMask", "InputAudio"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_StreamingDetectIntentResponse_descriptor */
    static final Descriptors.Descriptor f1563xd99e5107 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_StreamingDetectIntentResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1564x340b8085 = new GeneratedMessageV3.FieldAccessorTable(f1563xd99e5107, new String[]{"ResponseId", "RecognitionResult", "QueryResult", "WebhookStatus", "OutputAudio", "OutputAudioConfig"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_StreamingRecognitionResult_descriptor */
    static final Descriptors.Descriptor f1565x2a95f801 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_StreamingRecognitionResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1566xc8d18d7f = new GeneratedMessageV3.FieldAccessorTable(f1565x2a95f801, new String[]{"MessageType", "Transcript", "IsFinal", "Confidence", "SpeechWordInfo", "SpeechEndOffset"});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_TextInput_descriptor = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_TextInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1567x6c6b958a = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_TextInput_descriptor, new String[]{"Text", "LanguageCode"});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_EventInput_descriptor = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_EventInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1550xe1721fe1 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_EventInput_descriptor, new String[]{"Name", "Parameters", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SentimentAnalysisRequestConfig_descriptor */
    static final Descriptors.Descriptor f1556xdf2f4fad = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SentimentAnalysisRequestConfig_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1557xb41e792b = new GeneratedMessageV3.FieldAccessorTable(f1556xdf2f4fad, new String[]{"AnalyzeQueryTextSentiment"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SentimentAnalysisResult_descriptor */
    static final Descriptors.Descriptor f1558x69fc60e1 = getDescriptor().getMessageTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SentimentAnalysisResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1559xcb31165f = new GeneratedMessageV3.FieldAccessorTable(f1558x69fc60e1, new String[]{"QueryTextSentiment"});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_Sentiment_descriptor = getDescriptor().getMessageTypes().get(12);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Sentiment_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1560x7f2fd638 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_Sentiment_descriptor, new String[]{"Score", "Magnitude"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SessionProto() {
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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resourceDefinition);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resourceReference);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        ClientProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        AudioConfigProto.getDescriptor();
        ContextProto.getDescriptor();
        IntentProto.getDescriptor();
        SessionEntityTypeProto.getDescriptor();
        DurationProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        StructProto.getDescriptor();
        StatusProto.getDescriptor();
        LatLngProto.getDescriptor();
    }
}
