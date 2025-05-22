package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface StreamingDetectIntentResponseOrBuilder extends MessageOrBuilder {
    ByteString getOutputAudio();

    OutputAudioConfig getOutputAudioConfig();

    OutputAudioConfigOrBuilder getOutputAudioConfigOrBuilder();

    QueryResult getQueryResult();

    QueryResultOrBuilder getQueryResultOrBuilder();

    StreamingRecognitionResult getRecognitionResult();

    StreamingRecognitionResultOrBuilder getRecognitionResultOrBuilder();

    String getResponseId();

    ByteString getResponseIdBytes();

    Status getWebhookStatus();

    StatusOrBuilder getWebhookStatusOrBuilder();

    boolean hasOutputAudioConfig();

    boolean hasQueryResult();

    boolean hasRecognitionResult();

    boolean hasWebhookStatus();
}
