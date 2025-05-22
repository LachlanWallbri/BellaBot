package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ListKnowledgeBasesResponseOrBuilder extends MessageOrBuilder {
    KnowledgeBase getKnowledgeBases(int i);

    int getKnowledgeBasesCount();

    List<KnowledgeBase> getKnowledgeBasesList();

    KnowledgeBaseOrBuilder getKnowledgeBasesOrBuilder(int i);

    List<? extends KnowledgeBaseOrBuilder> getKnowledgeBasesOrBuilderList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
