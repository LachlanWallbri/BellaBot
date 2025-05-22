package com.google.longrunning;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ListOperationsResponseOrBuilder extends MessageOrBuilder {
    String getNextPageToken();

    ByteString getNextPageTokenBytes();

    Operation getOperations(int i);

    int getOperationsCount();

    List<Operation> getOperationsList();

    OperationOrBuilder getOperationsOrBuilder(int i);

    List<? extends OperationOrBuilder> getOperationsOrBuilderList();
}
