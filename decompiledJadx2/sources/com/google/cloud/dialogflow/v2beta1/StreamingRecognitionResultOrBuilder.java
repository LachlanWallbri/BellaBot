package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResult;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface StreamingRecognitionResultOrBuilder extends MessageOrBuilder {
    float getConfidence();

    boolean getIsFinal();

    StreamingRecognitionResult.MessageType getMessageType();

    int getMessageTypeValue();

    Duration getSpeechEndOffset();

    DurationOrBuilder getSpeechEndOffsetOrBuilder();

    SpeechWordInfo getSpeechWordInfo(int i);

    int getSpeechWordInfoCount();

    List<SpeechWordInfo> getSpeechWordInfoList();

    SpeechWordInfoOrBuilder getSpeechWordInfoOrBuilder(int i);

    List<? extends SpeechWordInfoOrBuilder> getSpeechWordInfoOrBuilderList();

    float getStability();

    String getTranscript();

    ByteString getTranscriptBytes();

    boolean hasSpeechEndOffset();
}
