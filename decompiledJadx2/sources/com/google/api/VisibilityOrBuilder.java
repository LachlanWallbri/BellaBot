package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface VisibilityOrBuilder extends MessageOrBuilder {
    VisibilityRule getRules(int i);

    int getRulesCount();

    List<VisibilityRule> getRulesList();

    VisibilityRuleOrBuilder getRulesOrBuilder(int i);

    List<? extends VisibilityRuleOrBuilder> getRulesOrBuilderList();
}
