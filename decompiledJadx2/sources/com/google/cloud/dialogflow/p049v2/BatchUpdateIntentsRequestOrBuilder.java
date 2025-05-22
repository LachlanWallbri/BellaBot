package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BatchUpdateIntentsRequestOrBuilder extends MessageOrBuilder {
    BatchUpdateIntentsRequest.IntentBatchCase getIntentBatchCase();

    IntentBatch getIntentBatchInline();

    IntentBatchOrBuilder getIntentBatchInlineOrBuilder();

    String getIntentBatchUri();

    ByteString getIntentBatchUriBytes();

    IntentView getIntentView();

    int getIntentViewValue();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getParent();

    ByteString getParentBytes();

    FieldMask getUpdateMask();

    FieldMaskOrBuilder getUpdateMaskOrBuilder();

    boolean hasIntentBatchInline();

    boolean hasUpdateMask();
}
