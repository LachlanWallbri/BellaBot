package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ListEntityTypesResponseOrBuilder extends MessageOrBuilder {
    EntityType getEntityTypes(int i);

    int getEntityTypesCount();

    List<EntityType> getEntityTypesList();

    EntityTypeOrBuilder getEntityTypesOrBuilder(int i);

    List<? extends EntityTypeOrBuilder> getEntityTypesOrBuilderList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
