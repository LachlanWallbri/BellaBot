package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.BatchUpdateEntityTypesRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BatchUpdateEntityTypesRequestOrBuilder extends MessageOrBuilder {
    BatchUpdateEntityTypesRequest.EntityTypeBatchCase getEntityTypeBatchCase();

    EntityTypeBatch getEntityTypeBatchInline();

    EntityTypeBatchOrBuilder getEntityTypeBatchInlineOrBuilder();

    String getEntityTypeBatchUri();

    ByteString getEntityTypeBatchUriBytes();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getParent();

    ByteString getParentBytes();

    FieldMask getUpdateMask();

    FieldMaskOrBuilder getUpdateMaskOrBuilder();

    boolean hasEntityTypeBatchInline();

    boolean hasUpdateMask();
}
