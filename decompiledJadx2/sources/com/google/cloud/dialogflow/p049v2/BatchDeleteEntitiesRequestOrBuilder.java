package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BatchDeleteEntitiesRequestOrBuilder extends MessageOrBuilder {
    String getEntityValues(int i);

    ByteString getEntityValuesBytes(int i);

    int getEntityValuesCount();

    List<String> getEntityValuesList();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getParent();

    ByteString getParentBytes();
}
