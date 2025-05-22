package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface SystemParametersOrBuilder extends MessageOrBuilder {
    SystemParameterRule getRules(int i);

    int getRulesCount();

    List<SystemParameterRule> getRulesList();

    SystemParameterRuleOrBuilder getRulesOrBuilder(int i);

    List<? extends SystemParameterRuleOrBuilder> getRulesOrBuilderList();
}
