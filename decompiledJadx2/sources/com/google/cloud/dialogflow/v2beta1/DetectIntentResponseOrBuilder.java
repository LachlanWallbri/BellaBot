package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface DetectIntentResponseOrBuilder extends MessageOrBuilder {
    QueryResult getAlternativeQueryResults(int i);

    int getAlternativeQueryResultsCount();

    List<QueryResult> getAlternativeQueryResultsList();

    QueryResultOrBuilder getAlternativeQueryResultsOrBuilder(int i);

    List<? extends QueryResultOrBuilder> getAlternativeQueryResultsOrBuilderList();

    ByteString getOutputAudio();

    OutputAudioConfig getOutputAudioConfig();

    OutputAudioConfigOrBuilder getOutputAudioConfigOrBuilder();

    QueryResult getQueryResult();

    QueryResultOrBuilder getQueryResultOrBuilder();

    String getResponseId();

    ByteString getResponseIdBytes();

    Status getWebhookStatus();

    StatusOrBuilder getWebhookStatusOrBuilder();

    boolean hasOutputAudioConfig();

    boolean hasQueryResult();

    boolean hasWebhookStatus();
}
