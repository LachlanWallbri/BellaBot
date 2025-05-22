package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SpeechContextOrBuilder extends MessageOrBuilder {
    float getBoost();

    String getPhrases(int i);

    ByteString getPhrasesBytes(int i);

    int getPhrasesCount();

    List<String> getPhrasesList();
}
