package com.google.cloud.texttospeech.v1beta1;

import com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SynthesizeSpeechRequestOrBuilder extends MessageOrBuilder {
    AudioConfig getAudioConfig();

    AudioConfigOrBuilder getAudioConfigOrBuilder();

    SynthesizeSpeechRequest.TimepointType getEnableTimePointing(int i);

    int getEnableTimePointingCount();

    List<SynthesizeSpeechRequest.TimepointType> getEnableTimePointingList();

    int getEnableTimePointingValue(int i);

    List<Integer> getEnableTimePointingValueList();

    SynthesisInput getInput();

    SynthesisInputOrBuilder getInputOrBuilder();

    VoiceSelectionParams getVoice();

    VoiceSelectionParamsOrBuilder getVoiceOrBuilder();

    boolean hasAudioConfig();

    boolean hasInput();

    boolean hasVoice();
}
