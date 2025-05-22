package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Document;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface DocumentOrBuilder extends MessageOrBuilder {
    @Deprecated
    String getContent();

    @Deprecated
    ByteString getContentBytes();

    String getContentUri();

    ByteString getContentUriBytes();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    Document.KnowledgeType getKnowledgeTypes(int i);

    int getKnowledgeTypesCount();

    List<Document.KnowledgeType> getKnowledgeTypesList();

    int getKnowledgeTypesValue(int i);

    List<Integer> getKnowledgeTypesValueList();

    String getMimeType();

    ByteString getMimeTypeBytes();

    String getName();

    ByteString getNameBytes();

    ByteString getRawContent();

    Document.SourceCase getSourceCase();
}
