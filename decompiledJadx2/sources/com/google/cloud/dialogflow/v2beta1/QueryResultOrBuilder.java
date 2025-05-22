package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Intent;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface QueryResultOrBuilder extends MessageOrBuilder {
    String getAction();

    ByteString getActionBytes();

    boolean getAllRequiredParamsPresent();

    Struct getDiagnosticInfo();

    StructOrBuilder getDiagnosticInfoOrBuilder();

    Intent.Message getFulfillmentMessages(int i);

    int getFulfillmentMessagesCount();

    List<Intent.Message> getFulfillmentMessagesList();

    Intent.MessageOrBuilder getFulfillmentMessagesOrBuilder(int i);

    List<? extends Intent.MessageOrBuilder> getFulfillmentMessagesOrBuilderList();

    String getFulfillmentText();

    ByteString getFulfillmentTextBytes();

    Intent getIntent();

    float getIntentDetectionConfidence();

    IntentOrBuilder getIntentOrBuilder();

    KnowledgeAnswers getKnowledgeAnswers();

    KnowledgeAnswersOrBuilder getKnowledgeAnswersOrBuilder();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    Context getOutputContexts(int i);

    int getOutputContextsCount();

    List<Context> getOutputContextsList();

    ContextOrBuilder getOutputContextsOrBuilder(int i);

    List<? extends ContextOrBuilder> getOutputContextsOrBuilderList();

    Struct getParameters();

    StructOrBuilder getParametersOrBuilder();

    String getQueryText();

    ByteString getQueryTextBytes();

    SentimentAnalysisResult getSentimentAnalysisResult();

    SentimentAnalysisResultOrBuilder getSentimentAnalysisResultOrBuilder();

    float getSpeechRecognitionConfidence();

    Struct getWebhookPayload();

    StructOrBuilder getWebhookPayloadOrBuilder();

    String getWebhookSource();

    ByteString getWebhookSourceBytes();

    boolean hasDiagnosticInfo();

    boolean hasIntent();

    boolean hasKnowledgeAnswers();

    boolean hasParameters();

    boolean hasSentimentAnalysisResult();

    boolean hasWebhookPayload();
}
