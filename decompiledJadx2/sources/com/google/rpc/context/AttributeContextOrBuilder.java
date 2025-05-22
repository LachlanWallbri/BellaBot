package com.google.rpc.context;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.context.AttributeContext;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface AttributeContextOrBuilder extends MessageOrBuilder {
    AttributeContext.Api getApi();

    AttributeContext.ApiOrBuilder getApiOrBuilder();

    AttributeContext.Peer getDestination();

    AttributeContext.PeerOrBuilder getDestinationOrBuilder();

    Any getExtensions(int i);

    int getExtensionsCount();

    List<Any> getExtensionsList();

    AnyOrBuilder getExtensionsOrBuilder(int i);

    List<? extends AnyOrBuilder> getExtensionsOrBuilderList();

    AttributeContext.Peer getOrigin();

    AttributeContext.PeerOrBuilder getOriginOrBuilder();

    AttributeContext.Request getRequest();

    AttributeContext.RequestOrBuilder getRequestOrBuilder();

    AttributeContext.Resource getResource();

    AttributeContext.ResourceOrBuilder getResourceOrBuilder();

    AttributeContext.Response getResponse();

    AttributeContext.ResponseOrBuilder getResponseOrBuilder();

    AttributeContext.Peer getSource();

    AttributeContext.PeerOrBuilder getSourceOrBuilder();

    boolean hasApi();

    boolean hasDestination();

    boolean hasOrigin();

    boolean hasRequest();

    boolean hasResource();

    boolean hasResponse();

    boolean hasSource();
}
