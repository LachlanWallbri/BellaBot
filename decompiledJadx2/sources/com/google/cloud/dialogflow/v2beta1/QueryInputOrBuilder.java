package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.QueryInput;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface QueryInputOrBuilder extends MessageOrBuilder {
    InputAudioConfig getAudioConfig();

    InputAudioConfigOrBuilder getAudioConfigOrBuilder();

    EventInput getEvent();

    EventInputOrBuilder getEventOrBuilder();

    QueryInput.InputCase getInputCase();

    TextInput getText();

    TextInputOrBuilder getTextOrBuilder();

    boolean hasAudioConfig();

    boolean hasEvent();

    boolean hasText();
}
