package com.google.cloud.texttospeech.p050v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface VoiceSelectionParamsOrBuilder extends MessageOrBuilder {
    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getName();

    ByteString getNameBytes();

    SsmlVoiceGender getSsmlGender();

    int getSsmlGenderValue();
}
