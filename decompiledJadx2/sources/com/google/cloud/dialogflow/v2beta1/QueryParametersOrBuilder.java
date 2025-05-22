package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.type.LatLng;
import com.google.type.LatLngOrBuilder;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface QueryParametersOrBuilder extends MessageOrBuilder {
    boolean containsWebhookHeaders(String str);

    Context getContexts(int i);

    int getContextsCount();

    List<Context> getContextsList();

    ContextOrBuilder getContextsOrBuilder(int i);

    List<? extends ContextOrBuilder> getContextsOrBuilderList();

    LatLng getGeoLocation();

    LatLngOrBuilder getGeoLocationOrBuilder();

    String getKnowledgeBaseNames(int i);

    ByteString getKnowledgeBaseNamesBytes(int i);

    int getKnowledgeBaseNamesCount();

    List<String> getKnowledgeBaseNamesList();

    Struct getPayload();

    StructOrBuilder getPayloadOrBuilder();

    boolean getResetContexts();

    SentimentAnalysisRequestConfig getSentimentAnalysisRequestConfig();

    SentimentAnalysisRequestConfigOrBuilder getSentimentAnalysisRequestConfigOrBuilder();

    SessionEntityType getSessionEntityTypes(int i);

    int getSessionEntityTypesCount();

    List<SessionEntityType> getSessionEntityTypesList();

    SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i);

    List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList();

    SubAgent getSubAgents(int i);

    int getSubAgentsCount();

    List<SubAgent> getSubAgentsList();

    SubAgentOrBuilder getSubAgentsOrBuilder(int i);

    List<? extends SubAgentOrBuilder> getSubAgentsOrBuilderList();

    String getTimeZone();

    ByteString getTimeZoneBytes();

    @Deprecated
    Map<String, String> getWebhookHeaders();

    int getWebhookHeadersCount();

    Map<String, String> getWebhookHeadersMap();

    String getWebhookHeadersOrDefault(String str, String str2);

    String getWebhookHeadersOrThrow(String str);

    boolean hasGeoLocation();

    boolean hasPayload();

    boolean hasSentimentAnalysisRequestConfig();
}
