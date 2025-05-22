package com.google.cloud.audit;

import com.google.cloud.audit.ServiceAccountDelegationInfo;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ServiceAccountDelegationInfoOrBuilder extends MessageOrBuilder {
    ServiceAccountDelegationInfo.AuthorityCase getAuthorityCase();

    ServiceAccountDelegationInfo.FirstPartyPrincipal getFirstPartyPrincipal();

    ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder getFirstPartyPrincipalOrBuilder();

    ServiceAccountDelegationInfo.ThirdPartyPrincipal getThirdPartyPrincipal();

    ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder getThirdPartyPrincipalOrBuilder();

    boolean hasFirstPartyPrincipal();

    boolean hasThirdPartyPrincipal();
}
