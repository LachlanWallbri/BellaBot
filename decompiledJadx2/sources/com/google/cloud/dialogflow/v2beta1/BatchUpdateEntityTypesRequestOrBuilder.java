package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
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
