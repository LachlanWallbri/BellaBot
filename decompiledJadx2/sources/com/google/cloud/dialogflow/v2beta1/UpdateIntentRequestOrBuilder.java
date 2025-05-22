package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface UpdateIntentRequestOrBuilder extends MessageOrBuilder {
    Intent getIntent();

    IntentOrBuilder getIntentOrBuilder();

    IntentView getIntentView();

    int getIntentViewValue();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    FieldMask getUpdateMask();

    FieldMaskOrBuilder getUpdateMaskOrBuilder();

    boolean hasIntent();

    boolean hasUpdateMask();
}
