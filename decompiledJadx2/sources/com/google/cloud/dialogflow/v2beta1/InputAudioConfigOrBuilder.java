package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface InputAudioConfigOrBuilder extends MessageOrBuilder {
    AudioEncoding getAudioEncoding();

    int getAudioEncodingValue();

    boolean getEnableWordInfo();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getModel();

    ByteString getModelBytes();

    SpeechModelVariant getModelVariant();

    int getModelVariantValue();

    @Deprecated
    String getPhraseHints(int i);

    @Deprecated
    ByteString getPhraseHintsBytes(int i);

    @Deprecated
    int getPhraseHintsCount();

    @Deprecated
    List<String> getPhraseHintsList();

    int getSampleRateHertz();

    boolean getSingleUtterance();

    SpeechContext getSpeechContexts(int i);

    int getSpeechContextsCount();

    List<SpeechContext> getSpeechContextsList();

    SpeechContextOrBuilder getSpeechContextsOrBuilder(int i);

    List<? extends SpeechContextOrBuilder> getSpeechContextsOrBuilderList();
}
