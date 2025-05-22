package com.pudutech.mqtt.component.client.netty;

import io.netty.buffer.ByteBuf;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: MessageConverter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/MessageConverter;", "", "()V", "byteBufToString", "", "buf", "Lio/netty/buffer/ByteBuf;", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MessageConverter {
    public static final MessageConverter INSTANCE = new MessageConverter();

    private MessageConverter() {
    }

    public final String byteBufToString(ByteBuf buf) {
        if (buf == null) {
            return null;
        }
        if (buf.hasArray()) {
            byte[] array = buf.array();
            Intrinsics.checkExpressionValueIsNotNull(array, "buf.array()");
            return new String(array, buf.arrayOffset() + buf.readerIndex(), buf.readableBytes(), Charsets.UTF_8);
        }
        byte[] bArr = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bArr);
        return new String(bArr, 0, buf.readableBytes(), Charsets.UTF_8);
    }
}
