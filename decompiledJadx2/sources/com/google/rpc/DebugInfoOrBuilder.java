package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface DebugInfoOrBuilder extends MessageOrBuilder {
    String getDetail();

    ByteString getDetailBytes();

    String getStackEntries(int i);

    ByteString getStackEntriesBytes(int i);

    int getStackEntriesCount();

    List<String> getStackEntriesList();
}
