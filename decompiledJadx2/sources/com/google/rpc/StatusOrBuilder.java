package com.google.rpc;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface StatusOrBuilder extends MessageOrBuilder {
    int getCode();

    Any getDetails(int i);

    int getDetailsCount();

    List<Any> getDetailsList();

    AnyOrBuilder getDetailsOrBuilder(int i);

    List<? extends AnyOrBuilder> getDetailsOrBuilderList();

    String getMessage();

    ByteString getMessageBytes();
}
