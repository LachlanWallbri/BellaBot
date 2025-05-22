package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface StreamingDetectIntentRequestOrBuilder extends MessageOrBuilder {
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

    boolean getSingleUtterance();

    boolean hasOutputAudioConfig();

    boolean hasOutputAudioConfigMask();

    boolean hasQueryInput();

    boolean hasQueryParams();
}
