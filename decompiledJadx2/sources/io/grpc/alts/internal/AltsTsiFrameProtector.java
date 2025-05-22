package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.primitives.Ints;
import io.grpc.alts.internal.TsiFrameProtector;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsTsiFrameProtector implements TsiFrameProtector {
    private static final int HEADER_BYTES = 8;
    private static final int HEADER_LEN_FIELD_BYTES = 4;
    private static final int HEADER_TYPE_DEFAULT = 6;
    private static final int HEADER_TYPE_FIELD_BYTES = 4;
    private static final int LIMIT_MAX_ALLOWED_FRAME_SIZE = 1048576;
    private static final int MAX_FRAME_SIZE = 131072;
    private static final int MIN_FRAME_SIZE = 16384;
    private final Protector protector;
    private final Unprotector unprotector;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum DeframerState {
        READ_HEADER,
        READ_PROTECTED_PAYLOAD
    }

    public static int getHeaderBytes() {
        return 8;
    }

    static int getHeaderLenFieldBytes() {
        return 4;
    }

    static int getHeaderTypeDefault() {
        return 6;
    }

    static int getHeaderTypeFieldBytes() {
        return 4;
    }

    static int getLimitMaxAllowedFrameSize() {
        return 1048576;
    }

    public static int getMaxFrameSize() {
        return 131072;
    }

    public static int getMinFrameSize() {
        return 16384;
    }

    public AltsTsiFrameProtector(int i, ChannelCrypterNetty channelCrypterNetty, ByteBufAllocator byteBufAllocator) {
        Preconditions.checkArgument(i > channelCrypterNetty.getSuffixLength() + 8);
        this.protector = new Protector(Math.min(1048576, i), channelCrypterNetty);
        this.unprotector = new Unprotector(channelCrypterNetty, byteBufAllocator);
    }

    @Override // io.grpc.alts.internal.TsiFrameProtector
    public void protectFlush(List<ByteBuf> list, TsiFrameProtector.Consumer<ByteBuf> consumer, ByteBufAllocator byteBufAllocator) throws GeneralSecurityException {
        this.protector.protectFlush(list, consumer, byteBufAllocator);
    }

    @Override // io.grpc.alts.internal.TsiFrameProtector
    public void unprotect(ByteBuf byteBuf, List<Object> list, ByteBufAllocator byteBufAllocator) throws GeneralSecurityException {
        this.unprotector.unprotect(byteBuf, list, byteBufAllocator);
    }

    @Override // io.grpc.alts.internal.TsiFrameProtector
    public void destroy() {
        try {
            this.unprotector.destroy();
        } finally {
            this.protector.destroy();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class Protector {
        private ChannelCrypterNetty crypter;
        private final int maxUnprotectedBytesPerFrame;
        private final int suffixBytes;

        Protector(int i, ChannelCrypterNetty channelCrypterNetty) {
            this.suffixBytes = channelCrypterNetty.getSuffixLength();
            this.maxUnprotectedBytesPerFrame = (i - 8) - this.suffixBytes;
            this.crypter = channelCrypterNetty;
        }

        void destroy() {
            this.crypter = null;
        }

        void protectFlush(List<ByteBuf> list, TsiFrameProtector.Consumer<ByteBuf> consumer, ByteBufAllocator byteBufAllocator) throws GeneralSecurityException {
            Preconditions.checkState(this.crypter != null, "Cannot protectFlush after destroy.");
            try {
                ByteBuf handleUnprotected = handleUnprotected(list, byteBufAllocator);
                if (handleUnprotected != null) {
                    consumer.accept(handleUnprotected);
                }
            } finally {
                Iterator<ByteBuf> it = list.iterator();
                while (it.hasNext()) {
                    it.next().release();
                }
            }
        }

        private ByteBuf handleUnprotected(List<ByteBuf> list, ByteBufAllocator byteBufAllocator) throws GeneralSecurityException {
            long j = 0;
            while (list.iterator().hasNext()) {
                j += r0.next().readableBytes();
            }
            Preconditions.checkArgument(j > 0);
            int i = this.maxUnprotectedBytesPerFrame;
            long j2 = (j / i) + 1;
            int i2 = (int) (j % i);
            if (i2 == 0) {
                j2--;
            } else {
                i = i2;
            }
            ByteBuf directBuffer = byteBufAllocator.directBuffer(Ints.checkedCast(((this.suffixBytes + 8) * j2) + j));
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j3 = i3;
                if (j3 < j2) {
                    int i5 = j3 == j2 - 1 ? i : this.maxUnprotectedBytesPerFrame;
                    directBuffer.writeIntLE(i5 + 4 + this.suffixBytes);
                    directBuffer.writeIntLE(6);
                    ByteBuf writeSlice = AltsTsiFrameProtector.writeSlice(directBuffer, this.suffixBytes + i5);
                    ArrayList arrayList = new ArrayList();
                    while (i5 > 0) {
                        try {
                            ByteBuf byteBuf = list.get(i4);
                            if (byteBuf.readableBytes() <= i5) {
                                arrayList.add(byteBuf);
                                i4++;
                                i5 -= byteBuf.readableBytes();
                            } else {
                                arrayList.add(byteBuf.readSlice(i5));
                                i5 = 0;
                            }
                        } finally {
                        }
                    }
                    this.crypter.encrypt(writeSlice, arrayList);
                    Verify.verify(!writeSlice.isWritable());
                    i3++;
                } else {
                    directBuffer.readerIndex(0);
                    directBuffer.writerIndex(directBuffer.capacity());
                    return directBuffer.retain();
                }
                directBuffer.release();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class Unprotector {
        private final ChannelCrypterNetty crypter;
        private ByteBuf firstFrameTag;
        private ByteBuf header;
        private int requiredProtectedBytes;
        private final int suffixBytes;
        private DeframerState state = DeframerState.READ_HEADER;
        private int unhandledIdx = 0;
        private long unhandledBytes = 0;
        private List<ByteBuf> unhandledBufs = new ArrayList(16);

        Unprotector(ChannelCrypterNetty channelCrypterNetty, ByteBufAllocator byteBufAllocator) {
            this.crypter = channelCrypterNetty;
            this.suffixBytes = channelCrypterNetty.getSuffixLength();
            this.header = byteBufAllocator.directBuffer(8);
            this.firstFrameTag = byteBufAllocator.directBuffer(this.suffixBytes);
        }

        private void addUnhandled(ByteBuf byteBuf) {
            if (byteBuf.isReadable()) {
                this.unhandledBufs.add(byteBuf.readRetainedSlice(byteBuf.readableBytes()));
                this.unhandledBytes += r5.readableBytes();
            }
        }

        void unprotect(ByteBuf byteBuf, List<Object> list, ByteBufAllocator byteBufAllocator) throws GeneralSecurityException {
            Preconditions.checkState(this.header != null, "Cannot unprotect after destroy.");
            addUnhandled(byteBuf);
            decodeFrame(byteBufAllocator, list);
        }

        private void decodeFrame(ByteBufAllocator byteBufAllocator, List<Object> list) throws GeneralSecurityException {
            int i = C60061.f8309xc9bec037[this.state.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new AssertionError("impossible enum value");
                }
            } else if (this.unhandledBytes < 8) {
                return;
            } else {
                handleHeader();
            }
            if (this.unhandledBytes < this.requiredProtectedBytes) {
                return;
            }
            try {
                ByteBuf handlePayload = handlePayload(byteBufAllocator);
                if (handlePayload != null) {
                    list.add(handlePayload);
                }
            } finally {
                clearState();
            }
        }

        private void handleHeader() {
            while (true) {
                if (!this.header.isWritable()) {
                    break;
                }
                ByteBuf byteBuf = this.unhandledBufs.get(this.unhandledIdx);
                int min = Math.min(byteBuf.readableBytes(), this.header.writableBytes());
                this.header.writeBytes(byteBuf, min);
                this.unhandledBytes -= min;
                if (!byteBuf.isReadable()) {
                    this.unhandledIdx++;
                }
            }
            this.requiredProtectedBytes = this.header.readIntLE() - 4;
            Preconditions.checkArgument(this.requiredProtectedBytes >= this.suffixBytes, "Invalid header field: frame size too small");
            Preconditions.checkArgument(this.requiredProtectedBytes <= 1048568, "Invalid header field: frame size too large");
            Preconditions.checkArgument(this.header.readIntLE() == 6, "Invalid header field: frame type");
            this.state = DeframerState.READ_PROTECTED_PAYLOAD;
        }

        private ByteBuf handlePayload(ByteBufAllocator byteBufAllocator) throws GeneralSecurityException {
            int i = this.requiredProtectedBytes - this.suffixBytes;
            ArrayList arrayList = new ArrayList();
            int i2 = i;
            while (i2 > 0) {
                ByteBuf byteBuf = this.unhandledBufs.get(this.unhandledIdx);
                if (byteBuf.readableBytes() <= i2) {
                    arrayList.add(byteBuf);
                    i2 -= byteBuf.readableBytes();
                    this.unhandledIdx++;
                } else {
                    arrayList.add(byteBuf.readSlice(i2));
                    i2 = 0;
                }
            }
            int i3 = this.suffixBytes;
            while (true) {
                ByteBuf byteBuf2 = this.unhandledBufs.get(this.unhandledIdx);
                if (byteBuf2.readableBytes() <= i3) {
                    i3 -= byteBuf2.readableBytes();
                    this.firstFrameTag.writeBytes(byteBuf2);
                    if (i3 == 0) {
                        break;
                    }
                    this.unhandledIdx++;
                } else {
                    this.firstFrameTag.writeBytes(byteBuf2, i3);
                    break;
                }
            }
            Verify.verify(this.unhandledIdx == this.unhandledBufs.size() - 1);
            ByteBuf byteBuf3 = this.unhandledBufs.get(this.unhandledIdx);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            long j = i;
            while (true) {
                if (byteBuf3.readableBytes() < this.suffixBytes + 8) {
                    break;
                }
                int readIntLE = byteBuf3.readIntLE();
                int i4 = (readIntLE - 4) - this.suffixBytes;
                if (byteBuf3.readableBytes() < readIntLE) {
                    byteBuf3.readerIndex(byteBuf3.readerIndex() - 4);
                    break;
                }
                Preconditions.checkArgument(byteBuf3.readIntLE() == 6);
                arrayList2.add(byteBuf3.readSlice(this.suffixBytes + i4));
                j += i4;
                arrayList3.add(Integer.valueOf(i4));
            }
            ByteBuf directBuffer = byteBufAllocator.directBuffer(Ints.checkedCast(j + this.suffixBytes));
            try {
                ByteBuf writeSlice = AltsTsiFrameProtector.writeSlice(directBuffer, i + this.suffixBytes);
                this.crypter.decrypt(writeSlice, this.firstFrameTag, arrayList);
                Verify.verify(writeSlice.writableBytes() == this.suffixBytes);
                directBuffer.writerIndex(directBuffer.writerIndex() - this.suffixBytes);
                for (int i5 = 0; i5 < arrayList2.size(); i5++) {
                    ByteBuf writeSlice2 = AltsTsiFrameProtector.writeSlice(directBuffer, ((Integer) arrayList3.get(i5)).intValue() + this.suffixBytes);
                    this.crypter.decrypt(writeSlice2, (ByteBuf) arrayList2.get(i5));
                    Verify.verify(writeSlice2.writableBytes() == this.suffixBytes);
                    directBuffer.writerIndex(directBuffer.writerIndex() - this.suffixBytes);
                }
                return directBuffer.retain();
            } finally {
                directBuffer.release();
            }
        }

        private void clearState() {
            int size = this.unhandledBufs.size();
            int i = size - 1;
            ByteBuf byteBuf = this.unhandledBufs.get(i);
            boolean isReadable = byteBuf.isReadable();
            int i2 = 0;
            while (true) {
                if (i2 >= (isReadable ? i : size)) {
                    break;
                }
                this.unhandledBufs.get(i2).release();
                i2++;
            }
            this.unhandledBufs.clear();
            this.unhandledBytes = 0L;
            this.unhandledIdx = 0;
            if (isReadable) {
                this.unhandledBufs.add(byteBuf);
                this.unhandledBytes = byteBuf.readableBytes();
            }
            this.state = DeframerState.READ_HEADER;
            this.requiredProtectedBytes = 0;
            this.header.clear();
            this.firstFrameTag.clear();
        }

        void destroy() {
            Iterator<ByteBuf> it = this.unhandledBufs.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.unhandledBufs.clear();
            ByteBuf byteBuf = this.header;
            if (byteBuf != null) {
                byteBuf.release();
                this.header = null;
            }
            ByteBuf byteBuf2 = this.firstFrameTag;
            if (byteBuf2 != null) {
                byteBuf2.release();
                this.firstFrameTag = null;
            }
            this.crypter.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.alts.internal.AltsTsiFrameProtector$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C60061 {

        /* renamed from: $SwitchMap$io$grpc$alts$internal$AltsTsiFrameProtector$DeframerState */
        static final /* synthetic */ int[] f8309xc9bec037 = new int[DeframerState.values().length];

        static {
            try {
                f8309xc9bec037[DeframerState.READ_HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8309xc9bec037[DeframerState.READ_PROTECTED_PAYLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBuf writeSlice(ByteBuf byteBuf, int i) {
        Preconditions.checkArgument(i <= byteBuf.writableBytes());
        ByteBuf slice = byteBuf.slice(byteBuf.writerIndex(), i);
        byteBuf.writerIndex(byteBuf.writerIndex() + i);
        return slice.writerIndex(0);
    }
}
