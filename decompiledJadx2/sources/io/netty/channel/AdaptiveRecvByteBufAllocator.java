package io.netty.channel;

import io.netty.channel.DefaultMaxMessagesRecvByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class AdaptiveRecvByteBufAllocator extends DefaultMaxMessagesRecvByteBufAllocator {

    @Deprecated
    public static final AdaptiveRecvByteBufAllocator DEFAULT;
    static final int DEFAULT_INITIAL = 1024;
    static final int DEFAULT_MAXIMUM = 65536;
    static final int DEFAULT_MINIMUM = 64;
    private static final int INDEX_DECREMENT = 1;
    private static final int INDEX_INCREMENT = 4;
    private static final int[] SIZE_TABLE;
    private final int initial;
    private final int maxIndex;
    private final int minIndex;

    static {
        int i;
        ArrayList arrayList = new ArrayList();
        int i2 = 16;
        while (true) {
            if (i2 >= 512) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
            i2 += 16;
        }
        for (i = 512; i > 0; i <<= 1) {
            arrayList.add(Integer.valueOf(i));
        }
        SIZE_TABLE = new int[arrayList.size()];
        int i3 = 0;
        while (true) {
            int[] iArr = SIZE_TABLE;
            if (i3 < iArr.length) {
                iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
                i3++;
            } else {
                DEFAULT = new AdaptiveRecvByteBufAllocator();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getSizeTableIndex(int i) {
        int length = SIZE_TABLE.length - 1;
        int i2 = 0;
        while (length >= i2) {
            if (length == i2) {
                return length;
            }
            int i3 = (i2 + length) >>> 1;
            int[] iArr = SIZE_TABLE;
            int i4 = iArr[i3];
            int i5 = i3 + 1;
            if (i > iArr[i5]) {
                i2 = i5;
            } else {
                if (i >= i4) {
                    return i == i4 ? i3 : i5;
                }
                length = i3 - 1;
            }
        }
        return i2;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    private final class HandleImpl extends DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle {
        private boolean decreaseNow;
        private int index;
        private final int maxIndex;
        private final int minIndex;
        private int nextReceiveBufferSize;

        public HandleImpl(int i, int i2, int i3) {
            super();
            this.minIndex = i;
            this.maxIndex = i2;
            this.index = AdaptiveRecvByteBufAllocator.getSizeTableIndex(i3);
            this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index];
        }

        @Override // io.netty.channel.DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle, io.netty.channel.RecvByteBufAllocator.Handle
        public void lastBytesRead(int i) {
            if (i == attemptedBytesRead()) {
                record(i);
            }
            super.lastBytesRead(i);
        }

        @Override // io.netty.channel.RecvByteBufAllocator.Handle
        public int guess() {
            return this.nextReceiveBufferSize;
        }

        private void record(int i) {
            if (i <= AdaptiveRecvByteBufAllocator.SIZE_TABLE[Math.max(0, (this.index - 1) - 1)]) {
                if (this.decreaseNow) {
                    this.index = Math.max(this.index - 1, this.minIndex);
                    this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index];
                    this.decreaseNow = false;
                    return;
                }
                this.decreaseNow = true;
                return;
            }
            if (i >= this.nextReceiveBufferSize) {
                this.index = Math.min(this.index + 4, this.maxIndex);
                this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index];
                this.decreaseNow = false;
            }
        }

        @Override // io.netty.channel.DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle, io.netty.channel.RecvByteBufAllocator.Handle
        public void readComplete() {
            record(totalBytesRead());
        }
    }

    public AdaptiveRecvByteBufAllocator() {
        this(64, 1024, 65536);
    }

    public AdaptiveRecvByteBufAllocator(int i, int i2, int i3) {
        if (i <= 0) {
            throw new IllegalArgumentException("minimum: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("initial: " + i2);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("maximum: " + i3);
        }
        int sizeTableIndex = getSizeTableIndex(i);
        if (SIZE_TABLE[sizeTableIndex] < i) {
            this.minIndex = sizeTableIndex + 1;
        } else {
            this.minIndex = sizeTableIndex;
        }
        int sizeTableIndex2 = getSizeTableIndex(i3);
        if (SIZE_TABLE[sizeTableIndex2] > i3) {
            this.maxIndex = sizeTableIndex2 - 1;
        } else {
            this.maxIndex = sizeTableIndex2;
        }
        this.initial = i2;
    }

    @Override // io.netty.channel.RecvByteBufAllocator
    public RecvByteBufAllocator.Handle newHandle() {
        return new HandleImpl(this.minIndex, this.maxIndex, this.initial);
    }

    @Override // io.netty.channel.DefaultMaxMessagesRecvByteBufAllocator
    public AdaptiveRecvByteBufAllocator respectMaybeMoreData(boolean z) {
        super.respectMaybeMoreData(z);
        return this;
    }
}
