package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface DetectIntentRequestOrBuilder extends MessageOrBuilder {
    ByteString getInputAudio();

    OutputAudioConfig getOutputAudioConfig();

    FieldMask getOutputAudioConfigMask();

    FieldMaskOrBuilder getOutputAudioConfigMaskOrBuilder();

    OutputAudioConfigOrBuilder getOutputAudioConfigOrBuilder();

    QueryInput getQueryInput();

    QueryInputOrBuilder getQueryInputOrBuilder();

    QueryParameters getQueryParams();

    QueryParametersOrBuilder getQueryParamsOrBuilder();

    String getSession();

    ByteString getSessionBytes();

    boolean hasOutputAudioConfig();

    boolean hasOutputAudioConfigMask();

    boolean hasQueryInput();

    boolean hasQueryParams();
}
