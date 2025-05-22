package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SetAgentRequestOrBuilder extends MessageOrBuilder {
    Agent getAgent();

    AgentOrBuilder getAgentOrBuilder();

    FieldMask getUpdateMask();

    FieldMaskOrBuilder getUpdateMaskOrBuilder();

    boolean hasAgent();

    boolean hasUpdateMask();
}
