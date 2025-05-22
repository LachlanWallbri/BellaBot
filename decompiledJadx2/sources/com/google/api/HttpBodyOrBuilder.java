package com.google.api;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface HttpBodyOrBuilder extends MessageOrBuilder {
    String getContentType();

    ByteString getContentTypeBytes();

    ByteString getData();

    Any getExtensions(int i);

    int getExtensionsCount();

    List<Any> getExtensionsList();

    AnyOrBuilder getExtensionsOrBuilder(int i);

    List<? extends AnyOrBuilder> getExtensionsOrBuilderList();
}
