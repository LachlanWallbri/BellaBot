package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ReloadDocumentRequestOrBuilder extends MessageOrBuilder {
    GcsSource getGcsSource();

    GcsSourceOrBuilder getGcsSourceOrBuilder();

    String getName();

    ByteString getNameBytes();

    ReloadDocumentRequest.SourceCase getSourceCase();

    boolean hasGcsSource();
}
