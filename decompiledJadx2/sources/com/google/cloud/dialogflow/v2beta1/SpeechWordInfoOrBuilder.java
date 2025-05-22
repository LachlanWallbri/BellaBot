package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SpeechWordInfoOrBuilder extends MessageOrBuilder {
    float getConfidence();

    Duration getEndOffset();

    DurationOrBuilder getEndOffsetOrBuilder();

    Duration getStartOffset();

    DurationOrBuilder getStartOffsetOrBuilder();

    String getWord();

    ByteString getWordBytes();

    boolean hasEndOffset();

    boolean hasStartOffset();
}
