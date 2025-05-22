package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface WebhookRequestOrBuilder extends MessageOrBuilder {
    QueryResult getAlternativeQueryResults(int i);

    int getAlternativeQueryResultsCount();

    List<QueryResult> getAlternativeQueryResultsList();

    QueryResultOrBuilder getAlternativeQueryResultsOrBuilder(int i);

    List<? extends QueryResultOrBuilder> getAlternativeQueryResultsOrBuilderList();

    OriginalDetectIntentRequest getOriginalDetectIntentRequest();

    OriginalDetectIntentRequestOrBuilder getOriginalDetectIntentRequestOrBuilder();

    QueryResult getQueryResult();

    QueryResultOrBuilder getQueryResultOrBuilder();

    String getResponseId();

    ByteString getResponseIdBytes();

    String getSession();

    ByteString getSessionBytes();

    boolean hasOriginalDetectIntentRequest();

    boolean hasQueryResult();
}
