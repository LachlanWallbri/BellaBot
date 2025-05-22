package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface SystemParameterRuleOrBuilder extends MessageOrBuilder {
    SystemParameter getParameters(int i);

    int getParametersCount();

    List<SystemParameter> getParametersList();

    SystemParameterOrBuilder getParametersOrBuilder(int i);

    List<? extends SystemParameterOrBuilder> getParametersOrBuilderList();

    String getSelector();

    ByteString getSelectorBytes();
}
