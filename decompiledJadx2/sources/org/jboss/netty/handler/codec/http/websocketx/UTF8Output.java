package org.jboss.netty.handler.codec.http.websocketx;

import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes7.dex */
final class UTF8Output {
    private static final int UTF8_ACCEPT = 0;
    private static final int UTF8_REJECT = 12;
    private int codep;
    private int state = 0;
    private final StringBuilder stringBuilder;
    private static final byte[] TYPES = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 11, 6, 6, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
    private static final byte[] STATES = {0, 12, 24, BinaryMemcacheOpcodes.GATKQ, 60, 96, 84, 12, 12, 12, TarConstants.LF_NORMAL, PrinterUtils.BarCode.CODE93, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 0, 12, 12, 12, 12, 12, 0, 12, 0, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, BinaryMemcacheOpcodes.GATKQ, 12, BinaryMemcacheOpcodes.GATKQ, 12, 12, 12, BinaryMemcacheOpcodes.GATKQ, 12, 12, 12, 12, 12, BinaryMemcacheOpcodes.GATKQ, 12, BinaryMemcacheOpcodes.GATKQ, 12, 12, 12, BinaryMemcacheOpcodes.GATKQ, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    /* JADX INFO: Access modifiers changed from: package-private */
    public UTF8Output(byte[] bArr) {
        this.stringBuilder = new StringBuilder(bArr.length);
        write(bArr);
    }

    public void write(byte[] bArr) {
        for (byte b : bArr) {
            write(b);
        }
    }

    public void write(int i) {
        byte b = TYPES[i & 255];
        this.codep = this.state != 0 ? (i & 63) | (this.codep << 6) : i & (255 >> b);
        this.state = STATES[this.state + b];
        int i2 = this.state;
        if (i2 == 0) {
            this.stringBuilder.append((char) this.codep);
        } else if (i2 == 12) {
            throw new UTF8Exception("bytes are not UTF-8");
        }
    }

    public String toString() {
        if (this.state != 0) {
            throw new UTF8Exception("bytes are not UTF-8");
        }
        return this.stringBuilder.toString();
    }
}
