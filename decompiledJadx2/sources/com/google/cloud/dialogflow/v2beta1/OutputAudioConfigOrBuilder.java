package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface OutputAudioConfigOrBuilder extends MessageOrBuilder {
    OutputAudioEncoding getAudioEncoding();

    int getAudioEncodingValue();

    int getSampleRateHertz();

    SynthesizeSpeechConfig getSynthesizeSpeechConfig();

    SynthesizeSpeechConfigOrBuilder getSynthesizeSpeechConfigOrBuilder();

    boolean hasSynthesizeSpeechConfig();
}
