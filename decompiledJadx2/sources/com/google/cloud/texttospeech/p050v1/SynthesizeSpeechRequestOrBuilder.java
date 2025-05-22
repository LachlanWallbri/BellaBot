package com.google.cloud.texttospeech.p050v1;

import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SynthesizeSpeechRequestOrBuilder extends MessageOrBuilder {
    AudioConfig getAudioConfig();

    AudioConfigOrBuilder getAudioConfigOrBuilder();

    SynthesisInput getInput();

    SynthesisInputOrBuilder getInputOrBuilder();

    VoiceSelectionParams getVoice();

    VoiceSelectionParamsOrBuilder getVoiceOrBuilder();

    boolean hasAudioConfig();

    boolean hasInput();

    boolean hasVoice();
}
