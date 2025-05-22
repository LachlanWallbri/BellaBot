package org.conscrypt;

import com.iflytek.cloud.SpeechEvent;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public abstract class AllocatedBuffer {
    public abstract ByteBuffer nioBuffer();

    public abstract AllocatedBuffer release();

    @Deprecated
    public AllocatedBuffer retain() {
        return this;
    }

    public static AllocatedBuffer wrap(final ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        return new AllocatedBuffer() { // from class: org.conscrypt.AllocatedBuffer.1
            @Override // org.conscrypt.AllocatedBuffer
            public AllocatedBuffer release() {
                return this;
            }

            @Override // org.conscrypt.AllocatedBuffer
            public ByteBuffer nioBuffer() {
                return byteBuffer;
            }
        };
    }
}
