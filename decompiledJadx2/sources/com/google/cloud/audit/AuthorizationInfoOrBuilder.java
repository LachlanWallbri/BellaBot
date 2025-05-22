package com.google.cloud.audit;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.context.AttributeContext;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface AuthorizationInfoOrBuilder extends MessageOrBuilder {
    boolean getGranted();

    String getPermission();

    ByteString getPermissionBytes();

    String getResource();

    AttributeContext.Resource getResourceAttributes();

    AttributeContext.ResourceOrBuilder getResourceAttributesOrBuilder();

    ByteString getResourceBytes();

    boolean hasResourceAttributes();
}
