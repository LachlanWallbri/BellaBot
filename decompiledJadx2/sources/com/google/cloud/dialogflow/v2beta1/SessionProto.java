package com.google.cloud.dialogflow.v2beta1;

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
/* loaded from: classes3.dex */
public final class SessionProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n-google/cloud/dialogflow/v2beta1/session.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a+google/cloud/dialogflow/v2beta1/agent.proto\u001a2google/cloud/dialogflow/v2beta1/audio_config.proto\u001a-google/cloud/dialogflow/v2beta1/context.proto\u001a,google/cloud/dialogflow/v2beta1/intent.proto\u001a9google/cloud/dialogflow/v2beta1/session_entity_type.proto\u001a\u001egoogle/protobuf/duration.proto\u001a google/protobuf/field_mask.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u0017google/rpc/status.proto\u001a\u0018google/type/latlng.proto\u001a\u0017google/api/client.proto\"ü\u0002\n\u0013DetectIntentRequest\u00127\n\u0007session\u0018\u0001 \u0001(\tB&úA#\n!dialogflow.googleapis.com/Session\u0012F\n\fquery_params\u0018\u0002 \u0001(\u000b20.google.cloud.dialogflow.v2beta1.QueryParameters\u0012@\n\u000bquery_input\u0018\u0003 \u0001(\u000b2+.google.cloud.dialogflow.v2beta1.QueryInput\u0012O\n\u0013output_audio_config\u0018\u0004 \u0001(\u000b22.google.cloud.dialogflow.v2beta1.OutputAudioConfig\u0012<\n\u0018output_audio_config_mask\u0018\u0007 \u0001(\u000b2\u001a.google.protobuf.FieldMask\u0012\u0013\n\u000binput_audio\u0018\u0005 \u0001(\f\"Ó\u0002\n\u0014DetectIntentResponse\u0012\u0013\n\u000bresponse_id\u0018\u0001 \u0001(\t\u0012B\n\fquery_result\u0018\u0002 \u0001(\u000b2,.google.cloud.dialogflow.v2beta1.QueryResult\u0012O\n\u0019alternative_query_results\u0018\u0005 \u0003(\u000b2,.google.cloud.dialogflow.v2beta1.QueryResult\u0012*\n\u000ewebhook_status\u0018\u0003 \u0001(\u000b2\u0012.google.rpc.Status\u0012\u0014\n\foutput_audio\u0018\u0004 \u0001(\f\u0012O\n\u0013output_audio_config\u0018\u0006 \u0001(\u000b22.google.cloud.dialogflow.v2beta1.OutputAudioConfig\"þ\u0004\n\u000fQueryParameters\u0012\u0011\n\ttime_zone\u0018\u0001 \u0001(\t\u0012)\n\fgeo_location\u0018\u0002 \u0001(\u000b2\u0013.google.type.LatLng\u0012:\n\bcontexts\u0018\u0003 \u0003(\u000b2(.google.cloud.dialogflow.v2beta1.Context\u0012\u0016\n\u000ereset_contexts\u0018\u0004 \u0001(\b\u0012P\n\u0014session_entity_types\u0018\u0005 \u0003(\u000b22.google.cloud.dialogflow.v2beta1.SessionEntityType\u0012(\n\u0007payload\u0018\u0006 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012\u001c\n\u0014knowledge_base_names\u0018\f \u0003(\t\u0012j\n!sentiment_analysis_request_config\u0018\n \u0001(\u000b2?.google.cloud.dialogflow.v2beta1.SentimentAnalysisRequestConfig\u0012=\n\nsub_agents\u0018\r \u0003(\u000b2).google.cloud.dialogflow.v2beta1.SubAgent\u0012]\n\u000fwebhook_headers\u0018\u000e \u0003(\u000b2D.google.cloud.dialogflow.v2beta1.QueryParameters.WebhookHeadersEntry\u001a5\n\u0013WebhookHeadersEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"Ú\u0001\n\nQueryInput\u0012I\n\faudio_config\u0018\u0001 \u0001(\u000b21.google.cloud.dialogflow.v2beta1.InputAudioConfigH\u0000\u0012:\n\u0004text\u0018\u0002 \u0001(\u000b2*.google.cloud.dialogflow.v2beta1.TextInputH\u0000\u0012<\n\u0005event\u0018\u0003 \u0001(\u000b2+.google.cloud.dialogflow.v2beta1.EventInputH\u0000B\u0007\n\u0005input\"ò\u0005\n\u000bQueryResult\u0012\u0012\n\nquery_text\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u000f \u0001(\t\u0012%\n\u001dspeech_recognition_confidence\u0018\u0002 \u0001(\u0002\u0012\u000e\n\u0006action\u0018\u0003 \u0001(\t\u0012+\n\nparameters\u0018\u0004 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012#\n\u001ball_required_params_present\u0018\u0005 \u0001(\b\u0012\u0018\n\u0010fulfillment_text\u0018\u0006 \u0001(\t\u0012M\n\u0014fulfillment_messages\u0018\u0007 \u0003(\u000b2/.google.cloud.dialogflow.v2beta1.Intent.Message\u0012\u0016\n\u000ewebhook_source\u0018\b \u0001(\t\u00120\n\u000fwebhook_payload\u0018\t \u0001(\u000b2\u0017.google.protobuf.Struct\u0012A\n\u000foutput_contexts\u0018\n \u0003(\u000b2(.google.cloud.dialogflow.v2beta1.Context\u00127\n\u0006intent\u0018\u000b \u0001(\u000b2'.google.cloud.dialogflow.v2beta1.Intent\u0012#\n\u001bintent_detection_confidence\u0018\f \u0001(\u0002\u00120\n\u000fdiagnostic_info\u0018\u000e \u0001(\u000b2\u0017.google.protobuf.Struct\u0012[\n\u0019sentiment_analysis_result\u0018\u0011 \u0001(\u000b28.google.cloud.dialogflow.v2beta1.SentimentAnalysisResult\u0012L\n\u0011knowledge_answers\u0018\u0012 \u0001(\u000b21.google.cloud.dialogflow.v2beta1.KnowledgeAnswers\"¯\u0003\n\u0010KnowledgeAnswers\u0012I\n\u0007answers\u0018\u0001 \u0003(\u000b28.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.Answer\u001aÏ\u0002\n\u0006Answer\u00127\n\u0006source\u0018\u0001 \u0001(\tB'úA$\n\"dialogflow.googleapis.com/Document\u0012\u0014\n\ffaq_question\u0018\u0002 \u0001(\t\u0012\u000e\n\u0006answer\u0018\u0003 \u0001(\t\u0012m\n\u0016match_confidence_level\u0018\u0004 \u0001(\u000e2M.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.Answer.MatchConfidenceLevel\u0012\u0018\n\u0010match_confidence\u0018\u0005 \u0001(\u0002\"]\n\u0014MatchConfidenceLevel\u0012&\n\"MATCH_CONFIDENCE_LEVEL_UNSPECIFIED\u0010\u0000\u0012\u0007\n\u0003LOW\u0010\u0001\u0012\n\n\u0006MEDIUM\u0010\u0002\u0012\b\n\u0004HIGH\u0010\u0003\"÷\u0002\n\u001cStreamingDetectIntentRequest\u0012\u000f\n\u0007session\u0018\u0001 \u0001(\t\u0012F\n\fquery_params\u0018\u0002 \u0001(\u000b20.google.cloud.dialogflow.v2beta1.QueryParameters\u0012@\n\u000bquery_input\u0018\u0003 \u0001(\u000b2+.google.cloud.dialogflow.v2beta1.QueryInput\u0012\u0018\n\u0010single_utterance\u0018\u0004 \u0001(\b\u0012O\n\u0013output_audio_config\u0018\u0005 \u0001(\u000b22.google.cloud.dialogflow.v2beta1.OutputAudioConfig\u0012<\n\u0018output_audio_config_mask\u0018\u0007 \u0001(\u000b2\u001a.google.protobuf.FieldMask\u0012\u0013\n\u000binput_audio\u0018\u0006 \u0001(\f\"µ\u0003\n\u001dStreamingDetectIntentResponse\u0012\u0013\n\u000bresponse_id\u0018\u0001 \u0001(\t\u0012W\n\u0012recognition_result\u0018\u0002 \u0001(\u000b2;.google.cloud.dialogflow.v2beta1.StreamingRecognitionResult\u0012B\n\fquery_result\u0018\u0003 \u0001(\u000b2,.google.cloud.dialogflow.v2beta1.QueryResult\u0012O\n\u0019alternative_query_results\u0018\u0007 \u0003(\u000b2,.google.cloud.dialogflow.v2beta1.QueryResult\u0012*\n\u000ewebhook_status\u0018\u0004 \u0001(\u000b2\u0012.google.rpc.Status\u0012\u0014\n\foutput_audio\u0018\u0005 \u0001(\f\u0012O\n\u0013output_audio_config\u0018\u0006 \u0001(\u000b22.google.cloud.dialogflow.v2beta1.OutputAudioConfig\"£\u0003\n\u001aStreamingRecognitionResult\u0012]\n\fmessage_type\u0018\u0001 \u0001(\u000e2G.google.cloud.dialogflow.v2beta1.StreamingRecognitionResult.MessageType\u0012\u0012\n\ntranscript\u0018\u0002 \u0001(\t\u0012\u0010\n\bis_final\u0018\u0003 \u0001(\b\u0012\u0012\n\nconfidence\u0018\u0004 \u0001(\u0002\u0012\u0011\n\tstability\u0018\u0006 \u0001(\u0002\u0012I\n\u0010speech_word_info\u0018\u0007 \u0003(\u000b2/.google.cloud.dialogflow.v2beta1.SpeechWordInfo\u00124\n\u0011speech_end_offset\u0018\b \u0001(\u000b2\u0019.google.protobuf.Duration\"X\n\u000bMessageType\u0012\u001c\n\u0018MESSAGE_TYPE_UNSPECIFIED\u0010\u0000\u0012\u000e\n\nTRANSCRIPT\u0010\u0001\u0012\u001b\n\u0017END_OF_SINGLE_UTTERANCE\u0010\u0002\"0\n\tTextInput\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\t\"^\n\nEventInput\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012+\n\nparameters\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012\u0015\n\rlanguage_code\u0018\u0003 \u0001(\t\"F\n\u001eSentimentAnalysisRequestConfig\u0012$\n\u001canalyze_query_text_sentiment\u0018\u0001 \u0001(\b\"c\n\u0017SentimentAnalysisResult\u0012H\n\u0014query_text_sentiment\u0018\u0001 \u0001(\u000b2*.google.cloud.dialogflow.v2beta1.Sentiment\"-\n\tSentiment\u0012\r\n\u0005score\u0018\u0001 \u0001(\u0002\u0012\u0011\n\tmagnitude\u0018\u0002 \u0001(\u00022÷\u0005\n\bSessions\u0012Ñ\u0003\n\fDetectIntent\u00124.google.cloud.dialogflow.v2beta1.DetectIntentRequest\u001a5.google.cloud.dialogflow.v2beta1.DetectIntentResponse\"Ó\u0002\u0082Óä\u0093\u0002Ì\u0002\";/v2beta1/{session=projects/*/agent/sessions/*}:detectIntent:\u0001*ZW\"R/v2beta1/{session=projects/*/agent/environments/*/users/*/sessions/*}:detectIntent:\u0001*ZL\"G/v2beta1/{session=projects/*/locations/*/agent/sessions/*}:detectIntent:\u0001*Zc\"^/v2beta1/{session=projects/*/locations/*/agent/environments/*/users/*/sessions/*}:detectIntent:\u0001*\u0012\u009c\u0001\n\u0015StreamingDetectIntent\u0012=.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequest\u001a>.google.cloud.dialogflow.v2beta1.StreamingDetectIntentResponse\"\u0000(\u00010\u0001\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowBÀ\u0002\n#com.google.cloud.dialogflow.v2beta1B\fSessionProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1êA\u0092\u0001\n!dialogflow.googleapis.com/Session\u0012@projects/{project}/locations/{location}/agent/sessions/{session}\u0012+projects/{project}/agent/sessions/{session}b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), AgentProto.getDescriptor(), AudioConfigProto.getDescriptor(), ContextProto.getDescriptor(), IntentProto.getDescriptor(), SessionEntityTypeProto.getDescriptor(), DurationProto.getDescriptor(), FieldMaskProto.getDescriptor(), StructProto.getDescriptor(), StatusProto.getDescriptor(), LatLngProto.getDescriptor(), ClientProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DetectIntentRequest_descriptor */
    static final Descriptors.Descriptor f1836xe337fb40 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DetectIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1837x997e1be = new GeneratedMessageV3.FieldAccessorTable(f1836xe337fb40, new String[]{"Session", "QueryParams", "QueryInput", "OutputAudioConfig", "OutputAudioConfigMask", "InputAudio"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DetectIntentResponse_descriptor */
    static final Descriptors.Descriptor f1838x9133722c = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DetectIntentResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1839xc6d3acaa = new GeneratedMessageV3.FieldAccessorTable(f1838x9133722c, new String[]{"ResponseId", "QueryResult", "AlternativeQueryResults", "WebhookStatus", "OutputAudio", "OutputAudioConfig"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryParameters_descriptor */
    static final Descriptors.Descriptor f1850x6aaee49e = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryParameters_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1851x75368d1c = new GeneratedMessageV3.FieldAccessorTable(f1850x6aaee49e, new String[]{"TimeZone", "GeoLocation", "Contexts", "ResetContexts", "SessionEntityTypes", "Payload", "KnowledgeBaseNames", "SentimentAnalysisRequestConfig", "SubAgents", "WebhookHeaders"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryParameters_WebhookHeadersEntry_descriptor */
    static final Descriptors.Descriptor f1848xc3b2685a = f1850x6aaee49e.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryParameters_WebhookHeadersEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1849x24894d8 = new GeneratedMessageV3.FieldAccessorTable(f1848xc3b2685a, new String[]{"Key", "Value"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryInput_descriptor */
    static final Descriptors.Descriptor f1846x57840f0a = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1847x84208b88 = new GeneratedMessageV3.FieldAccessorTable(f1846x57840f0a, new String[]{"AudioConfig", "Text", MoveError.LEVEL_EVENT, "Input"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryResult_descriptor */
    static final Descriptors.Descriptor f1852x36effd8b = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_QueryResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1853x1e3a6909 = new GeneratedMessageV3.FieldAccessorTable(f1852x36effd8b, new String[]{"QueryText", "LanguageCode", "SpeechRecognitionConfidence", JsonDocumentFields.ACTION, "Parameters", "AllRequiredParamsPresent", "FulfillmentText", "FulfillmentMessages", "WebhookSource", "WebhookPayload", "OutputContexts", "Intent", "IntentDetectionConfidence", "DiagnosticInfo", "SentimentAnalysisResult", "KnowledgeAnswers"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeAnswers_descriptor */
    static final Descriptors.Descriptor f1844x741e4b35 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeAnswers_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1845x583d6cb3 = new GeneratedMessageV3.FieldAccessorTable(f1844x741e4b35, new String[]{"Answers"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeAnswers_Answer_descriptor */
    static final Descriptors.Descriptor f1842x2fb3e3ea = f1844x741e4b35.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_KnowledgeAnswers_Answer_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1843x251d8068 = new GeneratedMessageV3.FieldAccessorTable(f1842x2fb3e3ea, new String[]{"Source", "FaqQuestion", "Answer", "MatchConfidenceLevel", "MatchConfidence"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_StreamingDetectIntentRequest_descriptor */
    static final Descriptors.Descriptor f1860x89fc013e = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_StreamingDetectIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1861x1e6d09bc = new GeneratedMessageV3.FieldAccessorTable(f1860x89fc013e, new String[]{"Session", "QueryParams", "QueryInput", "SingleUtterance", "OutputAudioConfig", "OutputAudioConfigMask", "InputAudio"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_StreamingDetectIntentResponse_descriptor */
    static final Descriptors.Descriptor f1862xc2f02bee = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_StreamingDetectIntentResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1863x4ca3846c = new GeneratedMessageV3.FieldAccessorTable(f1862xc2f02bee, new String[]{"ResponseId", "RecognitionResult", "QueryResult", "AlternativeQueryResults", "WebhookStatus", "OutputAudio", "OutputAudioConfig"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_StreamingRecognitionResult_descriptor */
    static final Descriptors.Descriptor f1864xb0f95e7a = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_StreamingRecognitionResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1865x94c76af8 = new GeneratedMessageV3.FieldAccessorTable(f1864xb0f95e7a, new String[]{"MessageType", "Transcript", "IsFinal", "Confidence", "Stability", "SpeechWordInfo", "SpeechEndOffset"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_TextInput_descriptor */
    static final Descriptors.Descriptor f1866x4d4b4f73 = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_TextInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1867xaeb952f1 = new GeneratedMessageV3.FieldAccessorTable(f1866x4d4b4f73, new String[]{"Text", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EventInput_descriptor */
    static final Descriptors.Descriptor f1840x26184dc = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_EventInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1841xe8dc0f5a = new GeneratedMessageV3.FieldAccessorTable(f1840x26184dc, new String[]{"Name", "Parameters", "LanguageCode"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SentimentAnalysisRequestConfig_descriptor */
    static final Descriptors.Descriptor f1854x2018d1a6 = getDescriptor().getMessageTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SentimentAnalysisRequestConfig_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1855xae86f224 = new GeneratedMessageV3.FieldAccessorTable(f1854x2018d1a6, new String[]{"AnalyzeQueryTextSentiment"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SentimentAnalysisResult_descriptor */
    static final Descriptors.Descriptor f1856x40ea3508 = getDescriptor().getMessageTypes().get(12);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SentimentAnalysisResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1857xa777d386 = new GeneratedMessageV3.FieldAccessorTable(f1856x40ea3508, new String[]{"QueryTextSentiment"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Sentiment_descriptor */
    static final Descriptors.Descriptor f1858x563a1e21 = getDescriptor().getMessageTypes().get(13);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Sentiment_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1859xc17d939f = new GeneratedMessageV3.FieldAccessorTable(f1858x563a1e21, new String[]{"Score", "Magnitude"});

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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resourceDefinition);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resourceReference);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        AgentProto.getDescriptor();
        AudioConfigProto.getDescriptor();
        ContextProto.getDescriptor();
        IntentProto.getDescriptor();
        SessionEntityTypeProto.getDescriptor();
        DurationProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        StructProto.getDescriptor();
        StatusProto.getDescriptor();
        LatLngProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
