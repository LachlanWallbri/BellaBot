package io.grpc;

import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class BinaryLog implements Closeable {
    public abstract Channel wrapChannel(Channel channel);

    public abstract <ReqT, RespT> ServerMethodDefinition<?, ?> wrapMethodDefinition(ServerMethodDefinition<ReqT, RespT> serverMethodDefinition);
}
