package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetector;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractByteBufAllocator implements ByteBufAllocator {
    static final int CALCULATE_THRESHOLD = 4194304;
    static final int DEFAULT_INITIAL_CAPACITY = 256;
    static final int DEFAULT_MAX_CAPACITY = Integer.MAX_VALUE;
    static final int DEFAULT_MAX_COMPONENTS = 16;
    private final boolean directByDefault;
    private final ByteBuf emptyBuf;

    protected abstract ByteBuf newDirectBuffer(int i, int i2);

    protected abstract ByteBuf newHeapBuffer(int i, int i2);

    static {
        ResourceLeakDetector.addExclusions(AbstractByteBufAllocator.class, "toLeakAwareBuffer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.buffer.AbstractByteBufAllocator$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C62651 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$util$ResourceLeakDetector$Level = new int[ResourceLeakDetector.Level.values().length];

        static {
            try {
                $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.Level.SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.Level.ADVANCED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.Level.PARANOID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ByteBuf toLeakAwareBuffer(ByteBuf byteBuf) {
        ResourceLeakTracker<ByteBuf> track;
        int i = C62651.$SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.getLevel().ordinal()];
        if (i == 1) {
            ResourceLeakTracker<ByteBuf> track2 = AbstractByteBuf.leakDetector.track(byteBuf);
            if (track2 != null) {
                return new SimpleLeakAwareByteBuf(byteBuf, track2);
            }
        } else if ((i == 2 || i == 3) && (track = AbstractByteBuf.leakDetector.track(byteBuf)) != null) {
            return new AdvancedLeakAwareByteBuf(byteBuf, track);
        }
        return byteBuf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static CompositeByteBuf toLeakAwareBuffer(CompositeByteBuf compositeByteBuf) {
        ResourceLeakTracker<ByteBuf> track;
        int i = C62651.$SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.getLevel().ordinal()];
        if (i == 1) {
            ResourceLeakTracker<ByteBuf> track2 = AbstractByteBuf.leakDetector.track(compositeByteBuf);
            if (track2 != null) {
                return new SimpleLeakAwareCompositeByteBuf(compositeByteBuf, track2);
            }
        } else if ((i == 2 || i == 3) && (track = AbstractByteBuf.leakDetector.track(compositeByteBuf)) != null) {
            return new AdvancedLeakAwareCompositeByteBuf(compositeByteBuf, track);
        }
        return compositeByteBuf;
    }

    protected AbstractByteBufAllocator() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractByteBufAllocator(boolean z) {
        this.directByDefault = z && PlatformDependent.hasUnsafe();
        this.emptyBuf = new EmptyByteBuf(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer() {
        if (this.directByDefault) {
            return directBuffer();
        }
        return heapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int i) {
        if (this.directByDefault) {
            return directBuffer(i);
        }
        return heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int i, int i2) {
        if (this.directByDefault) {
            return directBuffer(i, i2);
        }
        return heapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer() {
        if (PlatformDependent.hasUnsafe() || isDirectBufferPooled()) {
            return directBuffer(256);
        }
        return heapBuffer(256);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int i) {
        if (PlatformDependent.hasUnsafe() || isDirectBufferPooled()) {
            return directBuffer(i);
        }
        return heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int i, int i2) {
        if (PlatformDependent.hasUnsafe() || isDirectBufferPooled()) {
            return directBuffer(i, i2);
        }
        return heapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer() {
        return heapBuffer(256, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int i) {
        return heapBuffer(i, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this.emptyBuf;
        }
        validate(i, i2);
        return newHeapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer() {
        return directBuffer(256, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int i) {
        return directBuffer(i, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this.emptyBuf;
        }
        validate(i, i2);
        return newDirectBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer() {
        if (this.directByDefault) {
            return compositeDirectBuffer();
        }
        return compositeHeapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer(int i) {
        if (this.directByDefault) {
            return compositeDirectBuffer(i);
        }
        return compositeHeapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer() {
        return compositeHeapBuffer(16);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer(int i) {
        return toLeakAwareBuffer(new CompositeByteBuf(this, false, i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer() {
        return compositeDirectBuffer(16);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer(int i) {
        return toLeakAwareBuffer(new CompositeByteBuf(this, true, i));
    }

    private static void validate(int i, int i2) {
        ObjectUtil.checkPositiveOrZero(i, "initialCapacity");
        if (i > i2) {
            throw new IllegalArgumentException(String.format("initialCapacity: %d (expected: not greater than maxCapacity(%d)", Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(directByDefault: " + this.directByDefault + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public int calculateNewCapacity(int i, int i2) {
        ObjectUtil.checkPositiveOrZero(i, "minNewCapacity");
        if (i > i2) {
            throw new IllegalArgumentException(String.format("minNewCapacity: %d (expected: not greater than maxCapacity(%d)", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (i == 4194304) {
            return 4194304;
        }
        if (i > 4194304) {
            int i3 = (i / 4194304) * 4194304;
            return i3 > i2 - 4194304 ? i2 : i3 + 4194304;
        }
        int i4 = 64;
        while (i4 < i) {
            i4 <<= 1;
        }
        return Math.min(i4, i2);
    }
}
