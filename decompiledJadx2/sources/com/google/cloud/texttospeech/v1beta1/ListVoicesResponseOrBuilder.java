package com.google.cloud.texttospeech.v1beta1;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ListVoicesResponseOrBuilder extends MessageOrBuilder {
    Voice getVoices(int i);

    int getVoicesCount();

    List<Voice> getVoicesList();

    VoiceOrBuilder getVoicesOrBuilder(int i);

    List<? extends VoiceOrBuilder> getVoicesOrBuilderList();
}
