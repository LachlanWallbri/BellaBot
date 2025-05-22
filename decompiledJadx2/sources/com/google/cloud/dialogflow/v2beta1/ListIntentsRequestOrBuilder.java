package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ListIntentsRequestOrBuilder extends MessageOrBuilder {
    IntentView getIntentView();

    int getIntentViewValue();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    int getPageSize();

    String getPageToken();

    ByteString getPageTokenBytes();

    String getParent();

    ByteString getParentBytes();
}
