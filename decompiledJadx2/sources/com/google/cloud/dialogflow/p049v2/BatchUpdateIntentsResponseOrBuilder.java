package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BatchUpdateIntentsResponseOrBuilder extends MessageOrBuilder {
    Intent getIntents(int i);

    int getIntentsCount();

    List<Intent> getIntentsList();

    IntentOrBuilder getIntentsOrBuilder(int i);

    List<? extends IntentOrBuilder> getIntentsOrBuilderList();
}
