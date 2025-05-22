package com.google.cloud.texttospeech.p050v1;

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
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n,google/cloud/texttospeech/v1/cloud_tts.proto\u0012\u001cgoogle.cloud.texttospeech.v1\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\"/\n\u0011ListVoicesRequest\u0012\u001a\n\rlanguage_code\u0018\u0001 \u0001(\tB\u0003àA\u0001\"I\n\u0012ListVoicesResponse\u00123\n\u0006voices\u0018\u0001 \u0003(\u000b2#.google.cloud.texttospeech.v1.Voice\"\u0094\u0001\n\u0005Voice\u0012\u0016\n\u000elanguage_codes\u0018\u0001 \u0003(\t\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012B\n\u000bssml_gender\u0018\u0003 \u0001(\u000e2-.google.cloud.texttospeech.v1.SsmlVoiceGender\u0012!\n\u0019natural_sample_rate_hertz\u0018\u0004 \u0001(\u0005\"é\u0001\n\u0017SynthesizeSpeechRequest\u0012@\n\u0005input\u0018\u0001 \u0001(\u000b2,.google.cloud.texttospeech.v1.SynthesisInputB\u0003àA\u0002\u0012F\n\u0005voice\u0018\u0002 \u0001(\u000b22.google.cloud.texttospeech.v1.VoiceSelectionParamsB\u0003àA\u0002\u0012D\n\faudio_config\u0018\u0003 \u0001(\u000b2).google.cloud.texttospeech.v1.AudioConfigB\u0003àA\u0002\"@\n\u000eSynthesisInput\u0012\u000e\n\u0004text\u0018\u0001 \u0001(\tH\u0000\u0012\u000e\n\u0004ssml\u0018\u0002 \u0001(\tH\u0000B\u000e\n\finput_source\"\u0084\u0001\n\u0014VoiceSelectionParams\u0012\u001a\n\rlanguage_code\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012B\n\u000bssml_gender\u0018\u0003 \u0001(\u000e2-.google.cloud.texttospeech.v1.SsmlVoiceGender\"ñ\u0001\n\u000bAudioConfig\u0012H\n\u000eaudio_encoding\u0018\u0001 \u0001(\u000e2+.google.cloud.texttospeech.v1.AudioEncodingB\u0003àA\u0002\u0012\u001d\n\rspeaking_rate\u0018\u0002 \u0001(\u0001B\u0006àA\u0004àA\u0001\u0012\u0015\n\u0005pitch\u0018\u0003 \u0001(\u0001B\u0006àA\u0004àA\u0001\u0012\u001e\n\u000evolume_gain_db\u0018\u0004 \u0001(\u0001B\u0006àA\u0004àA\u0001\u0012\u001e\n\u0011sample_rate_hertz\u0018\u0005 \u0001(\u0005B\u0003àA\u0001\u0012\"\n\u0012effects_profile_id\u0018\u0006 \u0003(\tB\u0006àA\u0004àA\u0001\"1\n\u0018SynthesizeSpeechResponse\u0012\u0015\n\raudio_content\u0018\u0001 \u0001(\f*W\n\u000fSsmlVoiceGender\u0012!\n\u001dSSML_VOICE_GENDER_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004MALE\u0010\u0001\u0012\n\n\u0006FEMALE\u0010\u0002\u0012\u000b\n\u0007NEUTRAL\u0010\u0003*T\n\rAudioEncoding\u0012\u001e\n\u001aAUDIO_ENCODING_UNSPECIFIED\u0010\u0000\u0012\f\n\bLINEAR16\u0010\u0001\u0012\u0007\n\u0003MP3\u0010\u0002\u0012\f\n\bOGG_OPUS\u0010\u00032´\u0003\n\fTextToSpeech\u0012\u0093\u0001\n\nListVoices\u0012/.google.cloud.texttospeech.v1.ListVoicesRequest\u001a0.google.cloud.texttospeech.v1.ListVoicesResponse\"\"\u0082Óä\u0093\u0002\f\u0012\n/v1/voicesÚA\rlanguage_code\u0012¼\u0001\n\u0010SynthesizeSpeech\u00125.google.cloud.texttospeech.v1.SynthesizeSpeechRequest\u001a6.google.cloud.texttospeech.v1.SynthesizeSpeechResponse\"9\u0082Óä\u0093\u0002\u0018\"\u0013/v1/text:synthesize:\u0001*ÚA\u0018input,voice,audio_config\u001aOÊA\u001btexttospeech.googleapis.comÒA.https://www.googleapis.com/auth/cloud-platformBä\u0001\n com.google.cloud.texttospeech.v1B\u0011TextToSpeechProtoP\u0001ZHgoogle.golang.org/genproto/googleapis/cloud/texttospeech/v1;texttospeechø\u0001\u0001ª\u0002\u001cGoogle.Cloud.TextToSpeech.V1Ê\u0002\u001cGoogle\\Cloud\\TextToSpeech\\V1ê\u0002\u001fGoogle::Cloud::TextToSpeech::V1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_texttospeech_v1_ListVoicesRequest_descriptor */
    static final Descriptors.Descriptor f1881xa0892bee = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_ListVoicesRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1882x813c846c = new GeneratedMessageV3.FieldAccessorTable(f1881xa0892bee, new String[]{"LanguageCode"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1_ListVoicesResponse_descriptor */
    static final Descriptors.Descriptor f1883x7e08573e = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_ListVoicesResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1884x43c35fbc = new GeneratedMessageV3.FieldAccessorTable(f1883x7e08573e, new String[]{"Voices"});
    static final Descriptors.Descriptor internal_static_google_cloud_texttospeech_v1_Voice_descriptor = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_Voice_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1893x20150dca = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_texttospeech_v1_Voice_descriptor, new String[]{"LanguageCodes", "Name", "SsmlGender", "NaturalSampleRateHertz"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1_SynthesizeSpeechRequest_descriptor */
    static final Descriptors.Descriptor f1887x6c85709b = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_SynthesizeSpeechRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1888xf0f3cc19 = new GeneratedMessageV3.FieldAccessorTable(f1887x6c85709b, new String[]{"Input", "Voice", "AudioConfig"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1_SynthesisInput_descriptor */
    static final Descriptors.Descriptor f1885x3a0ef608 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_SynthesisInput_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1886xd44b9486 = new GeneratedMessageV3.FieldAccessorTable(f1885x3a0ef608, new String[]{"Text", "Ssml", "InputSource"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1_VoiceSelectionParams_descriptor */
    static final Descriptors.Descriptor f1891x59da05be = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_VoiceSelectionParams_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1892x643e8e3c = new GeneratedMessageV3.FieldAccessorTable(f1891x59da05be, new String[]{"LanguageCode", "Name", "SsmlGender"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1_AudioConfig_descriptor */
    static final Descriptors.Descriptor f1879xfb31ae06 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_AudioConfig_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1880x5aad6e84 = new GeneratedMessageV3.FieldAccessorTable(f1879xfb31ae06, new String[]{"AudioEncoding", "SpeakingRate", "Pitch", "VolumeGainDb", "SampleRateHertz", "EffectsProfileId"});

    /* renamed from: internal_static_google_cloud_texttospeech_v1_SynthesizeSpeechResponse_descriptor */
    static final Descriptors.Descriptor f1889x3194a831 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_texttospeech_v1_SynthesizeSpeechResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1890xcaf50daf = new GeneratedMessageV3.FieldAccessorTable(f1889x3194a831, new String[]{"AudioContent"});

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
