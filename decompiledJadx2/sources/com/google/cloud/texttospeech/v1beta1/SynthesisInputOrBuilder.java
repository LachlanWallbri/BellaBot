package com.google.cloud.texttospeech.v1beta1;

import com.google.cloud.texttospeech.v1beta1.SynthesisInput;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface SynthesisInputOrBuilder extends MessageOrBuilder {
    SynthesisInput.InputSourceCase getInputSourceCase();

    String getSsml();

    ByteString getSsmlBytes();

    String getText();

    ByteString getTextBytes();

    boolean hasSsml();

    boolean hasText();
}
