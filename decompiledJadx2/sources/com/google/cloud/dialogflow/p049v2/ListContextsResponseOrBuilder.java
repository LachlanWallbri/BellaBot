package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ListContextsResponseOrBuilder extends MessageOrBuilder {
    Context getContexts(int i);

    int getContextsCount();

    List<Context> getContextsList();

    ContextOrBuilder getContextsOrBuilder(int i);

    List<? extends ContextOrBuilder> getContextsOrBuilderList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
