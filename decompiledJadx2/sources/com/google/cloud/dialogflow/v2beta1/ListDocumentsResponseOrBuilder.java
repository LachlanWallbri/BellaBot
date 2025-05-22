package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ListDocumentsResponseOrBuilder extends MessageOrBuilder {
    Document getDocuments(int i);

    int getDocumentsCount();

    List<Document> getDocumentsList();

    DocumentOrBuilder getDocumentsOrBuilder(int i);

    List<? extends DocumentOrBuilder> getDocumentsOrBuilderList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
