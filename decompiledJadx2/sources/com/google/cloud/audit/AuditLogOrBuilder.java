package com.google.cloud.audit;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface AuditLogOrBuilder extends MessageOrBuilder {
    AuthenticationInfo getAuthenticationInfo();

    AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder();

    AuthorizationInfo getAuthorizationInfo(int i);

    int getAuthorizationInfoCount();

    List<AuthorizationInfo> getAuthorizationInfoList();

    AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i);

    List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList();

    Struct getMetadata();

    StructOrBuilder getMetadataOrBuilder();

    String getMethodName();

    ByteString getMethodNameBytes();

    long getNumResponseItems();

    Struct getRequest();

    RequestMetadata getRequestMetadata();

    RequestMetadataOrBuilder getRequestMetadataOrBuilder();

    StructOrBuilder getRequestOrBuilder();

    ResourceLocation getResourceLocation();

    ResourceLocationOrBuilder getResourceLocationOrBuilder();

    String getResourceName();

    ByteString getResourceNameBytes();

    Struct getResourceOriginalState();

    StructOrBuilder getResourceOriginalStateOrBuilder();

    Struct getResponse();

    StructOrBuilder getResponseOrBuilder();

    Any getServiceData();

    AnyOrBuilder getServiceDataOrBuilder();

    String getServiceName();

    ByteString getServiceNameBytes();

    Status getStatus();

    StatusOrBuilder getStatusOrBuilder();

    boolean hasAuthenticationInfo();

    boolean hasMetadata();

    boolean hasRequest();

    boolean hasRequestMetadata();

    boolean hasResourceLocation();

    boolean hasResourceOriginalState();

    boolean hasResponse();

    boolean hasServiceData();

    boolean hasStatus();
}
