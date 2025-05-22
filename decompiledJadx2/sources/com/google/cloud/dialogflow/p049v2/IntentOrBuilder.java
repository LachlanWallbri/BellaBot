package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.Intent;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface IntentOrBuilder extends MessageOrBuilder {
    String getAction();

    ByteString getActionBytes();

    Intent.Message.Platform getDefaultResponsePlatforms(int i);

    int getDefaultResponsePlatformsCount();

    List<Intent.Message.Platform> getDefaultResponsePlatformsList();

    int getDefaultResponsePlatformsValue(int i);

    List<Integer> getDefaultResponsePlatformsValueList();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    String getEvents(int i);

    ByteString getEventsBytes(int i);

    int getEventsCount();

    List<String> getEventsList();

    Intent.FollowupIntentInfo getFollowupIntentInfo(int i);

    int getFollowupIntentInfoCount();

    List<Intent.FollowupIntentInfo> getFollowupIntentInfoList();

    Intent.FollowupIntentInfoOrBuilder getFollowupIntentInfoOrBuilder(int i);

    List<? extends Intent.FollowupIntentInfoOrBuilder> getFollowupIntentInfoOrBuilderList();

    String getInputContextNames(int i);

    ByteString getInputContextNamesBytes(int i);

    int getInputContextNamesCount();

    List<String> getInputContextNamesList();

    boolean getIsFallback();

    Intent.Message getMessages(int i);

    int getMessagesCount();

    List<Intent.Message> getMessagesList();

    Intent.MessageOrBuilder getMessagesOrBuilder(int i);

    List<? extends Intent.MessageOrBuilder> getMessagesOrBuilderList();

    boolean getMlDisabled();

    String getName();

    ByteString getNameBytes();

    Context getOutputContexts(int i);

    int getOutputContextsCount();

    List<Context> getOutputContextsList();

    ContextOrBuilder getOutputContextsOrBuilder(int i);

    List<? extends ContextOrBuilder> getOutputContextsOrBuilderList();

    Intent.Parameter getParameters(int i);

    int getParametersCount();

    List<Intent.Parameter> getParametersList();

    Intent.ParameterOrBuilder getParametersOrBuilder(int i);

    List<? extends Intent.ParameterOrBuilder> getParametersOrBuilderList();

    String getParentFollowupIntentName();

    ByteString getParentFollowupIntentNameBytes();

    int getPriority();

    boolean getResetContexts();

    String getRootFollowupIntentName();

    ByteString getRootFollowupIntentNameBytes();

    Intent.TrainingPhrase getTrainingPhrases(int i);

    int getTrainingPhrasesCount();

    List<Intent.TrainingPhrase> getTrainingPhrasesList();

    Intent.TrainingPhraseOrBuilder getTrainingPhrasesOrBuilder(int i);

    List<? extends Intent.TrainingPhraseOrBuilder> getTrainingPhrasesOrBuilderList();

    Intent.WebhookState getWebhookState();

    int getWebhookStateValue();
}
