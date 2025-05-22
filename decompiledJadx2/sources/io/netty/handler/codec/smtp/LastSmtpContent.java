package io.netty.handler.codec.smtp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface LastSmtpContent extends SmtpContent {
    public static final LastSmtpContent EMPTY_LAST_CONTENT = new LastSmtpContent() { // from class: io.netty.handler.codec.smtp.LastSmtpContent.1
        @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
        public LastSmtpContent copy() {
            return this;
        }

        @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
        public LastSmtpContent duplicate() {
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public int refCnt() {
            return 1;
        }

        @Override // io.netty.util.ReferenceCounted
        public boolean release() {
            return false;
        }

        @Override // io.netty.util.ReferenceCounted
        public boolean release(int i) {
            return false;
        }

        @Override // io.netty.util.ReferenceCounted
        public LastSmtpContent retain() {
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public LastSmtpContent retain(int i) {
            return this;
        }

        @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
        public LastSmtpContent retainedDuplicate() {
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public LastSmtpContent touch() {
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public LastSmtpContent touch(Object obj) {
            return this;
        }

        @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
        public LastSmtpContent replace(ByteBuf byteBuf) {
            return new DefaultLastSmtpContent(byteBuf);
        }

        @Override // io.netty.buffer.ByteBufHolder
        public ByteBuf content() {
            return Unpooled.EMPTY_BUFFER;
        }
    };

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
    LastSmtpContent copy();

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
    LastSmtpContent duplicate();

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
    LastSmtpContent replace(ByteBuf byteBuf);

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    LastSmtpContent retain();

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    LastSmtpContent retain(int i);

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder
    LastSmtpContent retainedDuplicate();

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    LastSmtpContent touch();

    @Override // io.netty.handler.codec.smtp.SmtpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    LastSmtpContent touch(Object obj);
}
