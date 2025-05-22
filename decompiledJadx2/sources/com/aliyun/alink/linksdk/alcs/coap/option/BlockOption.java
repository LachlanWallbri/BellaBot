package com.aliyun.alink.linksdk.alcs.coap.option;

import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class BlockOption {
    private static final String TAG = "BlockOption";

    /* renamed from: m */
    private final boolean f706m;
    private final int num;
    private final int szx;

    public static int szx2Size(int i) {
        if (i <= 0) {
            return 16;
        }
        if (i >= 6) {
            return 1024;
        }
        return 1 << (i + 4);
    }

    public BlockOption(int i, boolean z, int i2) {
        if (i < 0 || 7 < i) {
            ALog.m480e(TAG, "Block option's szx must be between 0 and 7 inclusive");
        } else if (i2 < 0 || 1048575 < i2) {
            ALog.m480e(TAG, "Block option's num must be between 0 and 524288 inclusive");
        }
        this.szx = i;
        this.f706m = z;
        this.num = i2;
    }

    public BlockOption(BlockOption blockOption) {
        if (blockOption == null) {
            ALog.m480e(TAG, "origin empty");
            this.szx = 0;
            this.f706m = false;
            this.num = 0;
            return;
        }
        this.szx = blockOption.getSzx();
        this.f706m = blockOption.isM();
        this.num = blockOption.getNum();
    }

    public BlockOption(byte[] bArr) {
        if (bArr == null || bArr.length > 3) {
            ALog.m480e(TAG, "value emtpy");
            byte b = bArr[bArr.length - 1];
            this.szx = b & 7;
            this.f706m = ((b >> 3) & 1) == 1;
            int i = (b & 255) >> 4;
            for (int i2 = 1; i2 < bArr.length; i2++) {
                i += (bArr[(bArr.length - i2) - 1] & 255) << ((i2 * 8) - 4);
            }
            this.num = i;
            return;
        }
        if (bArr.length > 3) {
            ALog.m480e(TAG, "Block option's length must at most 3 bytes inclusive");
            byte b2 = bArr[bArr.length - 1];
            this.szx = b2 & 7;
            this.f706m = ((b2 >> 3) & 1) == 1;
            int i3 = (b2 & 255) >> 4;
            for (int i4 = 1; i4 < bArr.length; i4++) {
                i3 += (bArr[(bArr.length - i4) - 1] & 255) << ((i4 * 8) - 4);
            }
            this.num = i3;
            return;
        }
        if (bArr.length == 0) {
            this.szx = 0;
            this.f706m = false;
            this.num = 0;
            return;
        }
        byte b3 = bArr[bArr.length - 1];
        this.szx = b3 & 7;
        this.f706m = ((b3 >> 3) & 1) == 1;
        int i5 = (b3 & 255) >> 4;
        for (int i6 = 1; i6 < bArr.length; i6++) {
            i5 += (bArr[(bArr.length - i6) - 1] & 255) << ((i6 * 8) - 4);
        }
        this.num = i5;
    }

    public int getSzx() {
        return this.szx;
    }

    public int getSize() {
        return 1 << (this.szx + 4);
    }

    public boolean isM() {
        return this.f706m;
    }

    public int getNum() {
        return this.num;
    }

    public byte[] getValue() {
        int i = this.szx | (this.f706m ? 8 : 0);
        if (this.num == 0 && !this.f706m && this.szx == 0) {
            return new byte[0];
        }
        int i2 = this.num;
        return i2 < 16 ? new byte[]{(byte) (i | (i2 << 4))} : i2 < 4096 ? new byte[]{(byte) (i2 >> 4), (byte) (i | (i2 << 4))} : new byte[]{(byte) (i2 >> 12), (byte) (i2 >> 4), (byte) (i | (i2 << 4))};
    }

    public int getOffset() {
        return this.num * szx2Size(this.szx);
    }

    public String toString() {
        return String.format("(szx=%d/%d, m=%b, num=%d)", Integer.valueOf(this.szx), Integer.valueOf(szx2Size(this.szx)), Boolean.valueOf(this.f706m), Integer.valueOf(this.num));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BlockOption)) {
            return false;
        }
        BlockOption blockOption = (BlockOption) obj;
        return this.szx == blockOption.szx && this.num == blockOption.num && this.f706m == blockOption.f706m;
    }

    public int hashCode() {
        return (((this.szx * 31) + (this.f706m ? 1 : 0)) * 31) + this.num;
    }

    public static int size2Szx(int i) {
        if (i >= 1024) {
            return 6;
        }
        if (i <= 16) {
            return 0;
        }
        return Integer.numberOfTrailingZeros(Integer.highestOneBit(i)) - 4;
    }
}
