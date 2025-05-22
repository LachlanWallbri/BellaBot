package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Attribute extends HttpData {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Attribute copy();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Attribute duplicate();

    String getValue() throws IOException;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Attribute replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Attribute retain();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Attribute retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Attribute retainedDuplicate();

    void setValue(String str) throws IOException;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Attribute touch();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Attribute touch(Object obj);
}
