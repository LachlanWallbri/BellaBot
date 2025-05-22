package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.QueryInput;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
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
