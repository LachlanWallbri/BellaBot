package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface UpdateKnowledgeBaseRequestOrBuilder extends MessageOrBuilder {
    KnowledgeBase getKnowledgeBase();

    KnowledgeBaseOrBuilder getKnowledgeBaseOrBuilder();

    FieldMask getUpdateMask();

    FieldMaskOrBuilder getUpdateMaskOrBuilder();

    boolean hasKnowledgeBase();

    boolean hasUpdateMask();
}
