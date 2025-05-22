package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface FileUpload extends HttpData {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload copy();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload duplicate();

    String getContentTransferEncoding();

    String getContentType();

    String getFilename();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload retain();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload retainedDuplicate();

    void setContentTransferEncoding(String str);

    void setContentType(String str);

    void setFilename(String str);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload touch();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload touch(Object obj);
}
