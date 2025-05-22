package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SynthesizeSpeechConfigOrBuilder extends MessageOrBuilder {
    String getEffectsProfileId(int i);

    ByteString getEffectsProfileIdBytes(int i);

    int getEffectsProfileIdCount();

    List<String> getEffectsProfileIdList();

    double getPitch();

    double getSpeakingRate();

    VoiceSelectionParams getVoice();

    VoiceSelectionParamsOrBuilder getVoiceOrBuilder();

    double getVolumeGainDb();

    boolean hasVoice();
}
