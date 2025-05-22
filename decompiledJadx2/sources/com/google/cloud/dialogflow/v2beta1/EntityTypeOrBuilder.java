package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.EntityType;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface EntityTypeOrBuilder extends MessageOrBuilder {
    EntityType.AutoExpansionMode getAutoExpansionMode();

    int getAutoExpansionModeValue();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    boolean getEnableFuzzyExtraction();

    EntityType.Entity getEntities(int i);

    int getEntitiesCount();

    List<EntityType.Entity> getEntitiesList();

    EntityType.EntityOrBuilder getEntitiesOrBuilder(int i);

    List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList();

    EntityType.Kind getKind();

    int getKindValue();

    String getName();

    ByteString getNameBytes();
}
