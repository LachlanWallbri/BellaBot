package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.Intent;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface WebhookResponseOrBuilder extends MessageOrBuilder {
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
