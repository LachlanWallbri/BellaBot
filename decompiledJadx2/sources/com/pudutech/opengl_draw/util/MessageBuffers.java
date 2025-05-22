package com.pudutech.opengl_draw.util;

import java.nio.ByteOrder;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/* loaded from: classes5.dex */
public class MessageBuffers {
    static final int ESTIMATED_LENGTH = 256;

    private MessageBuffers() {
    }

    public static ChannelBuffer dynamicBuffer() {
        return ChannelBuffers.dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 256);
    }
}
