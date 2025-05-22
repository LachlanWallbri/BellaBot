package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ListSessionEntityTypesResponseOrBuilder extends MessageOrBuilder {
    String getNextPageToken();

    ByteString getNextPageTokenBytes();

    SessionEntityType getSessionEntityTypes(int i);

    int getSessionEntityTypesCount();

    List<SessionEntityType> getSessionEntityTypesList();

    SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i);

    List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList();
}
