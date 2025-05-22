package com.google.cloud.texttospeech.v1beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SynthesizeSpeechResponseOrBuilder extends MessageOrBuilder {
    AudioConfig getAudioConfig();

    AudioConfigOrBuilder getAudioConfigOrBuilder();

    ByteString getAudioContent();

    Timepoint getTimepoints(int i);

    int getTimepointsCount();

    List<Timepoint> getTimepointsList();

    TimepointOrBuilder getTimepointsOrBuilder(int i);

    List<? extends TimepointOrBuilder> getTimepointsOrBuilderList();

    boolean hasAudioConfig();
}
