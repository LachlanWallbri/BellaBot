package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ConfigChangeOrBuilder extends MessageOrBuilder {
    Advice getAdvices(int i);

    int getAdvicesCount();

    List<Advice> getAdvicesList();

    AdviceOrBuilder getAdvicesOrBuilder(int i);

    List<? extends AdviceOrBuilder> getAdvicesOrBuilderList();

    ChangeType getChangeType();

    int getChangeTypeValue();

    String getElement();

    ByteString getElementBytes();

    String getNewValue();

    ByteString getNewValueBytes();

    String getOldValue();

    ByteString getOldValueBytes();
}
