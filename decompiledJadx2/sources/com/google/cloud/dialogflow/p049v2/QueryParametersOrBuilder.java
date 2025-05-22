package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.type.LatLng;
import com.google.type.LatLngOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface QueryParametersOrBuilder extends MessageOrBuilder {
    Context getContexts(int i);

    int getContextsCount();

    List<Context> getContextsList();

    ContextOrBuilder getContextsOrBuilder(int i);

    List<? extends ContextOrBuilder> getContextsOrBuilderList();

    LatLng getGeoLocation();

    LatLngOrBuilder getGeoLocationOrBuilder();

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

    String getTimeZone();

    ByteString getTimeZoneBytes();

    boolean hasGeoLocation();

    boolean hasPayload();

    boolean hasSentimentAnalysisRequestConfig();
}
