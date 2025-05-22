package com.google.cloud.texttospeech.p050v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface AudioConfigOrBuilder extends MessageOrBuilder {
    AudioEncoding getAudioEncoding();

    int getAudioEncodingValue();

    String getEffectsProfileId(int i);

    ByteString getEffectsProfileIdBytes(int i);

    int getEffectsProfileIdCount();

    List<String> getEffectsProfileIdList();

    double getPitch();

    int getSampleRateHertz();

    double getSpeakingRate();

    double getVolumeGainDb();
}
