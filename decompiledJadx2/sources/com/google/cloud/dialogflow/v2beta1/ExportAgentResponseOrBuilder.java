package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.ExportAgentResponse;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ExportAgentResponseOrBuilder extends MessageOrBuilder {
    ExportAgentResponse.AgentCase getAgentCase();

    ByteString getAgentContent();

    String getAgentUri();

    ByteString getAgentUriBytes();
}
