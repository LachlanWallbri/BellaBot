package com.google.protobuf;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public interface FieldMaskOrBuilder extends MessageOrBuilder {
    String getPaths(int i);

    ByteString getPathsBytes(int i);

    int getPathsCount();

    List<String> getPathsList();
}
