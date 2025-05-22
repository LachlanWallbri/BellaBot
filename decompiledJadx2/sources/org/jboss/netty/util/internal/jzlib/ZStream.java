package org.jboss.netty.util.internal.jzlib;

import java.io.PrintStream;
import org.jboss.netty.util.internal.jzlib.JZlib;

/* loaded from: classes7.dex */
public final class ZStream {
    long adler;
    public int avail_in;
    public int avail_out;
    int crc32;
    Deflate dstate;
    Inflate istate;
    public String msg;
    public byte[] next_in;
    public int next_in_index;
    public byte[] next_out;
    public int next_out_index;
    public long total_in;
    public long total_out;

    public int inflateInit() {
        return inflateInit(15);
    }

    public int inflateInit(Enum<?> r2) {
        return inflateInit(15, r2);
    }

    public int inflateInit(int i) {
        return inflateInit(i, JZlib.WrapperType.ZLIB);
    }

    public int inflateInit(int i, Enum r3) {
        this.istate = new Inflate();
        return this.istate.inflateInit(this, i, (JZlib.WrapperType) r3);
    }

    public int inflate(int i) {
        Inflate inflate = this.istate;
        if (inflate == null) {
            return -2;
        }
        return inflate.inflate(this, i);
    }

    public int inflateEnd() {
        Inflate inflate = this.istate;
        if (inflate == null) {
            return -2;
        }
        int inflateEnd = inflate.inflateEnd(this);
        this.istate = null;
        return inflateEnd;
    }

    public int inflateSync() {
        Inflate inflate = this.istate;
        if (inflate == null) {
            return -2;
        }
        return inflate.inflateSync(this);
    }

    public int inflateSetDictionary(byte[] bArr, int i) {
        if (this.istate == null) {
            return -2;
        }
        return Inflate.inflateSetDictionary(this, bArr, i);
    }

    public int deflateInit(int i) {
        return deflateInit(i, 15);
    }

    public int deflateInit(int i, Enum<?> r3) {
        return deflateInit(i, 15, r3);
    }

    public int deflateInit(int i, int i2) {
        return deflateInit(i, i2, JZlib.WrapperType.ZLIB);
    }

    public int deflateInit(int i, int i2, Enum<?> r4) {
        return deflateInit(i, i2, 8, r4);
    }

    public int deflateInit(int i, int i2, int i3, Enum r11) {
        this.dstate = new Deflate();
        return this.dstate.deflateInit(this, i, i2, i3, (JZlib.WrapperType) r11);
    }

    public int deflate(int i) {
        Deflate deflate = this.dstate;
        if (deflate == null) {
            return -2;
        }
        return deflate.deflate(this, i);
    }

    public int deflateEnd() {
        Deflate deflate = this.dstate;
        if (deflate == null) {
            return -2;
        }
        int deflateEnd = deflate.deflateEnd();
        this.dstate = null;
        return deflateEnd;
    }

    public int deflateParams(int i, int i2) {
        Deflate deflate = this.dstate;
        if (deflate == null) {
            return -2;
        }
        return deflate.deflateParams(this, i, i2);
    }

    public int deflateSetDictionary(byte[] bArr, int i) {
        Deflate deflate = this.dstate;
        if (deflate == null) {
            return -2;
        }
        return deflate.deflateSetDictionary(this, bArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flush_pending() {
        int i = this.dstate.pending;
        int i2 = this.avail_out;
        if (i > i2) {
            i = i2;
        }
        if (i == 0) {
            return;
        }
        if (this.dstate.pending_buf.length <= this.dstate.pending_out || this.next_out.length <= this.next_out_index || this.dstate.pending_buf.length < this.dstate.pending_out + i || this.next_out.length < this.next_out_index + i) {
            System.out.println(this.dstate.pending_buf.length + ", " + this.dstate.pending_out + ", " + this.next_out.length + ", " + this.next_out_index + ", " + i);
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("avail_out=");
            sb.append(this.avail_out);
            printStream.println(sb.toString());
        }
        System.arraycopy(this.dstate.pending_buf, this.dstate.pending_out, this.next_out, this.next_out_index, i);
        this.next_out_index += i;
        this.dstate.pending_out += i;
        this.total_out += i;
        this.avail_out -= i;
        this.dstate.pending -= i;
        if (this.dstate.pending == 0) {
            this.dstate.pending_out = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int read_buf(byte[] bArr, int i, int i2) {
        int i3 = this.avail_in;
        if (i3 <= i2) {
            i2 = i3;
        }
        if (i2 == 0) {
            return 0;
        }
        this.avail_in -= i2;
        int i4 = C87461.$SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[this.dstate.wrapperType.ordinal()];
        if (i4 == 1) {
            this.adler = Adler32.adler32(this.adler, this.next_in, this.next_in_index, i2);
        } else if (i4 == 2) {
            this.crc32 = CRC32.crc32(this.crc32, this.next_in, this.next_in_index, i2);
        }
        System.arraycopy(this.next_in, this.next_in_index, bArr, i, i2);
        this.next_in_index += i2;
        this.total_in += i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.util.internal.jzlib.ZStream$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C87461 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType = new int[JZlib.WrapperType.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.ZLIB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.GZIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void free() {
        this.next_in = null;
        this.next_out = null;
        this.msg = null;
    }
}
