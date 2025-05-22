package org.xerial.snappy;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class BitShuffleNative {
    public native int shuffle(Object obj, int i, int i2, int i3, Object obj2, int i4) throws IOException;

    public native int shuffleDirectBuffer(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4) throws IOException;

    public native int unshuffle(Object obj, int i, int i2, int i3, Object obj2, int i4) throws IOException;

    public native int unshuffleDirectBuffer(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4) throws IOException;
}
