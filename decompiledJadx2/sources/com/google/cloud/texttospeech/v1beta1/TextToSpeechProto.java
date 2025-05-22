package com.google.cloud.texttospeech.v1beta1;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.api.FieldBehaviorProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class TextToSpeechProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n1google/cloud/texttospeech/v1beta1/cloud_tts.proto\u0012!google.cloud.texttospeech.v1beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\"/\n\u0011ListVoicesRequest\u0012\u001a\n\rlanguage_code\u0018\u0001 \u0001(\tB\u0003àA\u0001\"N\n\u0012ListVoicesResponse\u00128\n\u0006voices\u0018\u0001 \u0003(\u000b2(.google.cloud.texttospeech.v1beta1.Voice\"\u0099\u0001\n\u0005Voice\u0012\u0016\n\u000elanguage_codes\u0018\u0001 \u0003(\t\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012G\n\u000bssml_gender\u0018\u0003 \u0001(\u000e22.google.cloud.texttospeech.v1beta1.SsmlVoiceGender\u0012!\n\u0019natural_sample_rate_hertz\u0018\u0004 \u0001(\u0005\" \u0003\n\u0017SynthesizeSpeechRequest\u0012E\n\u0005input\u0018\u0001 \u0001(\u000b21.google.cloud.texttospeech.v1beta1.SynthesisInputB\u0003àA\u0002\u0012K\n\u0005voice\u0018\u0002 \u0001(\u000b27.google.cloud.texttospeech.v1beta1.VoiceSelectionParamsB\u0003àA\u0002\u0012I\n\faudio_config\u0018\u0003 \u0001(\u000b2..google.cloud.texttospeech.v1beta1.AudioConfigB\u0003àA\u0002\u0012f\n\u0014enable_time_pointing\u0018\u0004 \u0003(\u000e2H.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest.TimepointType\">\n\rTimepointType\u0012\u001e\n\u001aTIMEPOINT_TYPE_UNSPECIFIED\u0010\u0000\u0012\r\n\tSSML_MARK\u0010\u0001\"@\n\u000eSynthesisInput\u0012\u000e\n\u0004text\u0018\u0001 \u0001(\tH\u0000\u0012\u000e\n\u0004ssml\u0018\u0002 \u0001(\tH\u0000B\u000e\n\finput_source\"\u0089\u0001\n\u0014VoiceSelectionParams\u0012\u001a\n\rlanguage_code\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012G\n\u000bssml_gender\u0018\u0003 \u0001(\u000e22.google.cloud.texttospeech.v1beta1.SsmlVoiceGender\"ö\u0001\n\u000bAudioConfig\u0012M\n\u000eaudio_encoding\u0018\u0001 \u0001(\u000e20.google.cloud.texttospeech.v1beta1.AudioEncodingB\u0003àA\u0002\u0012\u001d\n\rspeaking_rate\u0018\u0002 \u0001(\u0001B\u0006àA\u0004àA\u0001\u0012\u0015\n\u0005pitch\u0018\u0003 \u0001(\u0001B\u0006àA\u0004àA\u0001\u0012\u001e\n\u000evolume_gain_db\u0018\u0004 \u0001(\u0001B\u0006àA\u0004àA\u0001\u0012\u001e\n\u0011sample_rate_hertz\u0018\u0005 \u0001(\u0005B\u0003àA\u0001\u0012\"\n\u0012effects_profile_id\u0018\u0006 \u0003(\tB\u0006àA\u0004àA\u0001\"¹\u0001\n\u0018SynthesizeSpeechResponse\u0012\u0015\n\raudio_content\u0018\u0001 \u0001(\f\u0012@\n\ntimepoints\u0018\u0002 \u0003(\u000b2,.google.cloud.texttospeech.v1beta1.Timepoint\u0012D\n\faudio_config\u0018\u0004 \u0001(\u000b2..google.cloud.texttospeech.v1beta1.AudioConfig\"4\n\tTimepoint\u0012\u0011\n\tmark_name\u0018\u0004 \u0001(\t\u0012\u0014\n\ftime_seconds\u0018\u0003 \u0001(\u0001*W\n\u000fSsmlVoiceGender\u0012!\n\u001dSSML_VOICE_GENDER_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004MALE\u0010\u0001\u0012\n\n\u0006FEMALE\u0010\u0002\u0012\u000b\n\u0007NEUTRAL\u0010\u0003*z\n\rAudioEncoding\u0012\u001e\n\u001aAUDIO_ENCODING_UNSPECIFIED\u0010\u0000\u0012\f\n\bLINEAR16\u0010\u0001\u0012\u0007\n\u0003MP3\u0010\u0002\u0012\u000f\n\u000bMP3_64_KBPS\u0010\u0004\u0012\f\n\bOGG_OPUS\u0010\u0003\u0012\t\n\u0005MULAW\u0010\u0005\u0012\b\n\u0004ALAW\u0010\u00062Ò\u0003\n\fTextToSpeech\u0012¢\u0001\n\nListVoices\u00124.google.cloud.texttospeech.v1beta1.ListVoicesRequest\u001a5.google.cloud.texttospeech.v1beta1.ListVoicesResponse\"'\u0082Óä\u0093\u0002\u0011\u0012\u000f/v1beta1/voicesÚA\rlanguage_code\u0012Ë\u0001\n\u0010SynthesizeSpeech\u0012:.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest\u001a;.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponse\">\u0082Óä\u0093\u0002\u001d\"\u0018/v1beta1/text:synthesize:\u0001*ÚA\u0018input,voice,audio_config\u001aOÊA\u001btexttospeech.googleapis.comÒA.https://www.googleapis.com/auth/cloud-platformBý\u0001\n%com.google.cloud.texttospeech.v1beta1B\u0011TextToSpeechProtoP\u0001ZMgoogle.golang.org/genproto/googleapis/cloud/texttospeech/v1beta1;texttospeechø\u0001\u0001ª\u0002!Google.Cloud.TextToSpeech.V1Beta1Ê\u0002!Google\\Cloud\\TextToSpeech\\V1beta1ê\u0002$Google::Cloud::TextToSpeech::V1beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_ListVoicesRequest_descriptor */
    static final Descriptors.Descriptor f1897x1f67708b = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_ListVoicesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1898x5d8edc09 = new GeneratedMessageV3.FieldAccessorTable(f1897x1f67708b, new String[]{"LanguageCode"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_ListVoicesResponse_descriptor */
    static final Descriptors.Descriptor f1899xdaf2a641 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_ListVoicesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1900xf1bbfbbf = new GeneratedMessageV3.FieldAccessorTable(f1899xdaf2a641, new String[]{"Voices"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_Voice_descriptor */
    static final Descriptors.Descriptor f1911x938a7b69 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_Voice_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1912x3e6028e7 = new GeneratedMessageV3.FieldAccessorTable(f1911x938a7b69, new String[]{"LanguageCodes", "Name", "SsmlGender", "NaturalSampleRateHertz"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_SynthesizeSpeechRequest_descriptor */
    static final Descriptors.Descriptor f1903x65d30b78 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_SynthesizeSpeechRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1904x771b39f6 = new GeneratedMessageV3.FieldAccessorTable(f1903x65d30b78, new String[]{"Input", "Voice", "AudioConfig", "EnableTimePointing"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_SynthesisInput_descriptor */
    static final Descriptors.Descriptor f1901xe5273e8b = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_SynthesisInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1902x5a0aa09 = new GeneratedMessageV3.FieldAccessorTable(f1901xe5273e8b, new String[]{"Text", "Ssml", "InputSource"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_VoiceSelectionParams_descriptor */
    static final Descriptors.Descriptor f1909x256ca001 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_VoiceSelectionParams_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1910x7680357f = new GeneratedMessageV3.FieldAccessorTable(f1909x256ca001, new String[]{"LanguageCode", "Name", "SsmlGender"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_AudioConfig_descriptor */
    static final Descriptors.Descriptor f1895xfcd2ec63 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_AudioConfig_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1896x9b0cffe1 = new GeneratedMessageV3.FieldAccessorTable(f1895xfcd2ec63, new String[]{"AudioEncoding", "SpeakingRate", "Pitch", "VolumeGainDb", "SampleRateHertz", "EffectsProfileId"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_SynthesizeSpeechResponse_descriptor */
    static final Descriptors.Descriptor f1905x61fa68f4 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_SynthesizeSpeechResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1906x9bb5b72 = new GeneratedMessageV3.FieldAccessorTable(f1905x61fa68f4, new String[]{"AudioContent", "Timepoints", "AudioConfig"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_Timepoint_descriptor */
    static final Descriptors.Descriptor f1907x77182a98 = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_texttospeech_v1beta1_Timepoint_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1908x455f3916 = new GeneratedMessageV3.FieldAccessorTable(f1907x77182a98, new String[]{"MarkName", "TimeSeconds"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private TextToSpeechProto() {
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
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        ClientProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
    }
}
