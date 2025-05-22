package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.RestoreAgentRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface RestoreAgentRequestOrBuilder extends MessageOrBuilder {
    RestoreAgentRequest.AgentCase getAgentCase();

    ByteString getAgentContent();

    String getAgentUri();

    ByteString getAgentUriBytes();

    String getParent();

    ByteString getParentBytes();
}
