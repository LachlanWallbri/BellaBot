package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface HttpContent extends HttpObject, ByteBufHolder {
    HttpContent copy();

    HttpContent duplicate();

    HttpContent replace(ByteBuf byteBuf);

    HttpContent retain();

    HttpContent retain(int i);

    HttpContent retainedDuplicate();

    HttpContent touch();

    HttpContent touch(Object obj);
}
