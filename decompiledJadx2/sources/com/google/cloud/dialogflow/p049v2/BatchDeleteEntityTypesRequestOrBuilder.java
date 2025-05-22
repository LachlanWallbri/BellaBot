package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BatchDeleteEntityTypesRequestOrBuilder extends MessageOrBuilder {
    String getEntityTypeNames(int i);

    ByteString getEntityTypeNamesBytes(int i);

    int getEntityTypeNamesCount();

    List<String> getEntityTypeNamesList();

    String getParent();

    ByteString getParentBytes();
}
