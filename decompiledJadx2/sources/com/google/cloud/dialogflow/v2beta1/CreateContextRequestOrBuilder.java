package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface CreateContextRequestOrBuilder extends MessageOrBuilder {
    Context getContext();

    ContextOrBuilder getContextOrBuilder();

    String getParent();

    ByteString getParentBytes();

    boolean hasContext();
}
