package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface KnowledgeAnswersOrBuilder extends MessageOrBuilder {
    KnowledgeAnswers.Answer getAnswers(int i);

    int getAnswersCount();

    List<KnowledgeAnswers.Answer> getAnswersList();

    KnowledgeAnswers.AnswerOrBuilder getAnswersOrBuilder(int i);

    List<? extends KnowledgeAnswers.AnswerOrBuilder> getAnswersOrBuilderList();
}
