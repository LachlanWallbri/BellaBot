package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.EntityType;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BatchUpdateEntitiesRequestOrBuilder extends MessageOrBuilder {
    EntityType.Entity getEntities(int i);

    int getEntitiesCount();

    List<EntityType.Entity> getEntitiesList();

    EntityType.EntityOrBuilder getEntitiesOrBuilder(int i);

    List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getParent();

    ByteString getParentBytes();

    FieldMask getUpdateMask();

    FieldMaskOrBuilder getUpdateMaskOrBuilder();

    boolean hasUpdateMask();
}
