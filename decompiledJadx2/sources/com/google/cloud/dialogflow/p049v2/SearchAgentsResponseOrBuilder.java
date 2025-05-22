package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface SearchAgentsResponseOrBuilder extends MessageOrBuilder {
    Agent getAgents(int i);

    int getAgentsCount();

    List<Agent> getAgentsList();

    AgentOrBuilder getAgentsOrBuilder(int i);

    List<? extends AgentOrBuilder> getAgentsOrBuilderList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
