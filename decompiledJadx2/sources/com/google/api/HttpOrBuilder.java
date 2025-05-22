package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface HttpOrBuilder extends MessageOrBuilder {
    boolean getFullyDecodeReservedExpansion();

    HttpRule getRules(int i);

    int getRulesCount();

    List<HttpRule> getRulesList();

    HttpRuleOrBuilder getRulesOrBuilder(int i);

    List<? extends HttpRuleOrBuilder> getRulesOrBuilderList();
}
