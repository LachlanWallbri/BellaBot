package com.google.cloud.dialogflow.v2beta1;

import androidx.exifinterface.media.ExifInterface;
import com.google.api.AnnotationsProto;
import com.google.api.FieldBehaviorProto;
import com.google.api.ResourceProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class AudioConfigProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n2google/cloud/dialogflow/v2beta1/audio_config.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a\u001egoogle/protobuf/duration.proto\u001a google/protobuf/field_mask.proto\u001a\u001cgoogle/api/annotations.proto\"/\n\rSpeechContext\u0012\u000f\n\u0007phrases\u0018\u0001 \u0003(\t\u0012\r\n\u0005boost\u0018\u0002 \u0001(\u0002\"\u0092\u0001\n\u000eSpeechWordInfo\u0012\f\n\u0004word\u0018\u0003 \u0001(\t\u0012/\n\fstart_offset\u0018\u0001 \u0001(\u000b2\u0019.google.protobuf.Duration\u0012-\n\nend_offset\u0018\u0002 \u0001(\u000b2\u0019.google.protobuf.Duration\u0012\u0012\n\nconfidence\u0018\u0004 \u0001(\u0002\"\u0081\u0003\n\u0010InputAudioConfig\u0012F\n\u000eaudio_encoding\u0018\u0001 \u0001(\u000e2..google.cloud.dialogflow.v2beta1.AudioEncoding\u0012\u0019\n\u0011sample_rate_hertz\u0018\u0002 \u0001(\u0005\u0012\u0015\n\rlanguage_code\u0018\u0003 \u0001(\t\u0012\u0018\n\u0010enable_word_info\u0018\r \u0001(\b\u0012\u001b\n\fphrase_hints\u0018\u0004 \u0003(\tB\u0005\u0018\u0001àA\u0001\u0012G\n\u000fspeech_contexts\u0018\u000b \u0003(\u000b2..google.cloud.dialogflow.v2beta1.SpeechContext\u0012\r\n\u0005model\u0018\u0007 \u0001(\t\u0012J\n\rmodel_variant\u0018\n \u0001(\u000e23.google.cloud.dialogflow.v2beta1.SpeechModelVariant\u0012\u0018\n\u0010single_utterance\u0018\b \u0001(\b\"k\n\u0014VoiceSelectionParams\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012E\n\u000bssml_gender\u0018\u0002 \u0001(\u000e20.google.cloud.dialogflow.v2beta1.SsmlVoiceGender\"¸\u0001\n\u0016SynthesizeSpeechConfig\u0012\u0015\n\rspeaking_rate\u0018\u0001 \u0001(\u0001\u0012\r\n\u0005pitch\u0018\u0002 \u0001(\u0001\u0012\u0016\n\u000evolume_gain_db\u0018\u0003 \u0001(\u0001\u0012\u001a\n\u0012effects_profile_id\u0018\u0005 \u0003(\t\u0012D\n\u0005voice\u0018\u0004 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.VoiceSelectionParams\"Ü\u0001\n\u0011OutputAudioConfig\u0012Q\n\u000eaudio_encoding\u0018\u0001 \u0001(\u000e24.google.cloud.dialogflow.v2beta1.OutputAudioEncodingB\u0003àA\u0002\u0012\u0019\n\u0011sample_rate_hertz\u0018\u0002 \u0001(\u0005\u0012Y\n\u0018synthesize_speech_config\u0018\u0003 \u0001(\u000b27.google.cloud.dialogflow.v2beta1.SynthesizeSpeechConfig*û\u0001\n\rAudioEncoding\u0012\u001e\n\u001aAUDIO_ENCODING_UNSPECIFIED\u0010\u0000\u0012\u001c\n\u0018AUDIO_ENCODING_LINEAR_16\u0010\u0001\u0012\u0017\n\u0013AUDIO_ENCODING_FLAC\u0010\u0002\u0012\u0018\n\u0014AUDIO_ENCODING_MULAW\u0010\u0003\u0012\u0016\n\u0012AUDIO_ENCODING_AMR\u0010\u0004\u0012\u0019\n\u0015AUDIO_ENCODING_AMR_WB\u0010\u0005\u0012\u001b\n\u0017AUDIO_ENCODING_OGG_OPUS\u0010\u0006\u0012)\n%AUDIO_ENCODING_SPEEX_WITH_HEADER_BYTE\u0010\u0007*v\n\u0012SpeechModelVariant\u0012$\n SPEECH_MODEL_VARIANT_UNSPECIFIED\u0010\u0000\u0012\u0016\n\u0012USE_BEST_AVAILABLE\u0010\u0001\u0012\u0010\n\fUSE_STANDARD\u0010\u0002\u0012\u0010\n\fUSE_ENHANCED\u0010\u0003*\u008d\u0001\n\u000fSsmlVoiceGender\u0012!\n\u001dSSML_VOICE_GENDER_UNSPECIFIED\u0010\u0000\u0012\u001a\n\u0016SSML_VOICE_GENDER_MALE\u0010\u0001\u0012\u001c\n\u0018SSML_VOICE_GENDER_FEMALE\u0010\u0002\u0012\u001d\n\u0019SSML_VOICE_GENDER_NEUTRAL\u0010\u0003*¤\u0001\n\u0013OutputAudioEncoding\u0012%\n!OUTPUT_AUDIO_ENCODING_UNSPECIFIED\u0010\u0000\u0012#\n\u001fOUTPUT_AUDIO_ENCODING_LINEAR_16\u0010\u0001\u0012\u001d\n\u0019OUTPUT_AUDIO_ENCODING_MP3\u0010\u0002\u0012\"\n\u001eOUTPUT_AUDIO_ENCODING_OGG_OPUS\u0010\u0003B®\u0001\n#com.google.cloud.dialogflow.v2beta1B\u0010AudioConfigProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), DurationProto.getDescriptor(), FieldMaskProto.getDescriptor(), AnnotationsProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SpeechContext_descriptor */
    static final Descriptors.Descriptor f1607x38d160a3 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SpeechContext_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1608xc733421 = new GeneratedMessageV3.FieldAccessorTable(f1607x38d160a3, new String[]{"Phrases", "Boost"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SpeechWordInfo_descriptor */
    static final Descriptors.Descriptor f1609x6087fb12 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SpeechWordInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1610x929bef90 = new GeneratedMessageV3.FieldAccessorTable(f1609x6087fb12, new String[]{"Word", "StartOffset", "EndOffset", "Confidence"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_InputAudioConfig_descriptor */
    static final Descriptors.Descriptor f1603x83f3285e = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_InputAudioConfig_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1604x26db10dc = new GeneratedMessageV3.FieldAccessorTable(f1603x83f3285e, new String[]{"AudioEncoding", "SampleRateHertz", "LanguageCode", "EnableWordInfo", "PhraseHints", "SpeechContexts", ExifInterface.TAG_MODEL, "ModelVariant", "SingleUtterance"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_VoiceSelectionParams_descriptor */
    static final Descriptors.Descriptor f1613x6d6dc00c = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_VoiceSelectionParams_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1614x1a521a8a = new GeneratedMessageV3.FieldAccessorTable(f1613x6d6dc00c, new String[]{"Name", "SsmlGender"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SynthesizeSpeechConfig_descriptor */
    static final Descriptors.Descriptor f1611xe3d68e5e = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SynthesizeSpeechConfig_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1612x37f876dc = new GeneratedMessageV3.FieldAccessorTable(f1611xe3d68e5e, new String[]{"SpeakingRate", "Pitch", "VolumeGainDb", "EffectsProfileId", "Voice"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_OutputAudioConfig_descriptor */
    static final Descriptors.Descriptor f1605x1e12a0d9 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_OutputAudioConfig_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1606x82e3de57 = new GeneratedMessageV3.FieldAccessorTable(f1605x1e12a0d9, new String[]{"AudioEncoding", "SampleRateHertz", "SynthesizeSpeechConfig"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AudioConfigProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        ExtensionRegistry newInstance = ExtensionRegistry.newInstance();
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) FieldBehaviorProto.fieldBehavior);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        DurationProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        AnnotationsProto.getDescriptor();
    }
}
