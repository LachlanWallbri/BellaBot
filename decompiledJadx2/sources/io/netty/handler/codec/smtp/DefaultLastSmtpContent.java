package io.netty.handler.codec.smtp;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DefaultLastSmtpContent extends DefaultSmtpContent implements LastSmtpContent {
    public DefaultLastSmtpContent(ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastSmtpContent copy() {
        return (LastSmtpContent) super.copy();
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastSmtpContent duplicate() {
        return (LastSmtpContent) super.duplicate();
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastSmtpContent retainedDuplicate() {
        return (LastSmtpContent) super.retainedDuplicate();
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastSmtpContent replace(ByteBuf byteBuf) {
        return new DefaultLastSmtpContent(byteBuf);
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public DefaultLastSmtpContent retain() {
        super.retain();
        return this;
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public DefaultLastSmtpContent retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public DefaultLastSmtpContent touch() {
        super.touch();
        return this;
    }

    @Override // io.netty.handler.codec.smtp.DefaultSmtpContent, io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public DefaultLastSmtpContent touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
