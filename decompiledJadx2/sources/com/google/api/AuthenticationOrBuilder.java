package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface AuthenticationOrBuilder extends MessageOrBuilder {
    AuthProvider getProviders(int i);

    int getProvidersCount();

    List<AuthProvider> getProvidersList();

    AuthProviderOrBuilder getProvidersOrBuilder(int i);

    List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList();

    AuthenticationRule getRules(int i);

    int getRulesCount();

    List<AuthenticationRule> getRulesList();

    AuthenticationRuleOrBuilder getRulesOrBuilder(int i);

    List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList();
}
