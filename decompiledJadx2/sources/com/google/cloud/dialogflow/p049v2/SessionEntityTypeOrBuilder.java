package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.EntityType;
import com.google.cloud.dialogflow.p049v2.SessionEntityType;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface SessionEntityTypeOrBuilder extends MessageOrBuilder {
    EntityType.Entity getEntities(int i);

    int getEntitiesCount();

    List<EntityType.Entity> getEntitiesList();

    EntityType.EntityOrBuilder getEntitiesOrBuilder(int i);

    List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList();

    SessionEntityType.EntityOverrideMode getEntityOverrideMode();

    int getEntityOverrideModeValue();

    String getName();

    ByteString getNameBytes();
}
