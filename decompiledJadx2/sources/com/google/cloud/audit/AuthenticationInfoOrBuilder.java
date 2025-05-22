package com.google.cloud.audit;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface AuthenticationInfoOrBuilder extends MessageOrBuilder {
    String getAuthoritySelector();

    ByteString getAuthoritySelectorBytes();

    String getPrincipalEmail();

    ByteString getPrincipalEmailBytes();

    String getPrincipalSubject();

    ByteString getPrincipalSubjectBytes();

    ServiceAccountDelegationInfo getServiceAccountDelegationInfo(int i);

    int getServiceAccountDelegationInfoCount();

    List<ServiceAccountDelegationInfo> getServiceAccountDelegationInfoList();

    ServiceAccountDelegationInfoOrBuilder getServiceAccountDelegationInfoOrBuilder(int i);

    List<? extends ServiceAccountDelegationInfoOrBuilder> getServiceAccountDelegationInfoOrBuilderList();

    String getServiceAccountKeyName();

    ByteString getServiceAccountKeyNameBytes();

    Struct getThirdPartyPrincipal();

    StructOrBuilder getThirdPartyPrincipalOrBuilder();

    boolean hasThirdPartyPrincipal();
}
