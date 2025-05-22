package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ContextOrBuilder extends MessageOrBuilder {
    ContextRule getRules(int i);

    int getRulesCount();

    List<ContextRule> getRulesList();

    ContextRuleOrBuilder getRulesOrBuilder(int i);

    List<? extends ContextRuleOrBuilder> getRulesOrBuilderList();
}
