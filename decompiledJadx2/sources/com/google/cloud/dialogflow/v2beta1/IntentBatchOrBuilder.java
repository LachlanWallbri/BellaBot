package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface IntentBatchOrBuilder extends MessageOrBuilder {
    Intent getIntents(int i);

    int getIntentsCount();

    List<Intent> getIntentsList();

    IntentOrBuilder getIntentsOrBuilder(int i);

    List<? extends IntentOrBuilder> getIntentsOrBuilderList();
}
