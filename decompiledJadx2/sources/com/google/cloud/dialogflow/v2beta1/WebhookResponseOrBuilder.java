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
public interface WebhookResponseOrBuilder extends MessageOrBuilder {
    boolean getEndInteraction();

    EventInput getFollowupEventInput();

    EventInputOrBuilder getFollowupEventInputOrBuilder();

    Intent.Message getFulfillmentMessages(int i);

    int getFulfillmentMessagesCount();

    List<Intent.Message> getFulfillmentMessagesList();

    Intent.MessageOrBuilder getFulfillmentMessagesOrBuilder(int i);

    List<? extends Intent.MessageOrBuilder> getFulfillmentMessagesOrBuilderList();

    String getFulfillmentText();

    ByteString getFulfillmentTextBytes();

    Context getOutputContexts(int i);

    int getOutputContextsCount();

    List<Context> getOutputContextsList();

    ContextOrBuilder getOutputContextsOrBuilder(int i);

    List<? extends ContextOrBuilder> getOutputContextsOrBuilderList();

    Struct getPayload();

    StructOrBuilder getPayloadOrBuilder();

    SessionEntityType getSessionEntityTypes(int i);

    int getSessionEntityTypesCount();

    List<SessionEntityType> getSessionEntityTypesList();

    SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i);

    List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList();

    String getSource();

    ByteString getSourceBytes();

    boolean hasFollowupEventInput();

    boolean hasPayload();
}
