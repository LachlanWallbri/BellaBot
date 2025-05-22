package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Agent;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface AgentOrBuilder extends MessageOrBuilder {
    Agent.ApiVersion getApiVersion();

    int getApiVersionValue();

    String getAvatarUri();

    ByteString getAvatarUriBytes();

    float getClassificationThreshold();

    String getDefaultLanguageCode();

    ByteString getDefaultLanguageCodeBytes();

    String getDescription();

    ByteString getDescriptionBytes();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    boolean getEnableLogging();

    Agent.MatchMode getMatchMode();

    int getMatchModeValue();

    String getParent();

    ByteString getParentBytes();

    String getSupportedLanguageCodes(int i);

    ByteString getSupportedLanguageCodesBytes(int i);

    int getSupportedLanguageCodesCount();

    List<String> getSupportedLanguageCodesList();

    Agent.Tier getTier();

    int getTierValue();

    String getTimeZone();

    ByteString getTimeZoneBytes();
}
