package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ListSessionEntityTypesResponseOrBuilder extends MessageOrBuilder {
    String getNextPageToken();

    ByteString getNextPageTokenBytes();

    SessionEntityType getSessionEntityTypes(int i);

    int getSessionEntityTypesCount();

    List<SessionEntityType> getSessionEntityTypesList();

    SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i);

    List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList();
}
