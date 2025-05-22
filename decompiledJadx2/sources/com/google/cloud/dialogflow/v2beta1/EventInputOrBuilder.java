package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface EventInputOrBuilder extends MessageOrBuilder {
    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getName();

    ByteString getNameBytes();

    Struct getParameters();

    StructOrBuilder getParametersOrBuilder();

    boolean hasParameters();
}
