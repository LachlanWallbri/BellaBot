package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface SpeechContextOrBuilder extends MessageOrBuilder {
    float getBoost();

    String getPhrases(int i);

    ByteString getPhrasesBytes(int i);

    int getPhrasesCount();

    List<String> getPhrasesList();
}
