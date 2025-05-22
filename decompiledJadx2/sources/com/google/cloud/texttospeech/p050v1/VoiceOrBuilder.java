package com.google.cloud.texttospeech.p050v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface VoiceOrBuilder extends MessageOrBuilder {
    String getLanguageCodes(int i);

    ByteString getLanguageCodesBytes(int i);

    int getLanguageCodesCount();

    List<String> getLanguageCodesList();

    String getName();

    ByteString getNameBytes();

    int getNaturalSampleRateHertz();

    SsmlVoiceGender getSsmlGender();

    int getSsmlGenderValue();
}
