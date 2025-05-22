package com.google.api;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface SourceInfoOrBuilder extends MessageOrBuilder {
    Any getSourceFiles(int i);

    int getSourceFilesCount();

    List<Any> getSourceFilesList();

    AnyOrBuilder getSourceFilesOrBuilder(int i);

    List<? extends AnyOrBuilder> getSourceFilesOrBuilderList();
}
