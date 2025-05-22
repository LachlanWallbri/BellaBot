package com.aliyun.alink.linksdk.alcs.coap.option;

import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPConstant;
import com.aliyun.alink.linksdk.alcs.coap.MediaTypeRegistry;
import com.aliyun.alink.linksdk.alcs.coap.option.OptionNumberRegistry;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Option implements Comparable<Option> {
    private static final String TAG = "Option";
    private int number;
    private byte[] value;

    public Option() {
        this.value = new byte[0];
    }

    public Option(int i) {
        this.number = i;
        this.value = new byte[0];
    }

    public Option(int i, String str) {
        this.number = i;
        setStringValue(str);
    }

    public Option(int i, int i2) {
        this.number = i;
        setIntegerValue(i2);
    }

    public Option(int i, long j) {
        this.number = i;
        setLongValue(j);
    }

    public Option(int i, byte[] bArr) {
        this.number = i;
        setValue(bArr);
    }

    public int getLength() {
        return this.value.length;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int i) {
        this.number = i;
    }

    public byte[] getValue() {
        return this.value;
    }

    public String getStringValue() {
        return new String(this.value, AlcsCoAPConstant.UTF8_CHARSET);
    }

    public int getIntegerValue() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.value;
            if (i >= bArr.length) {
                return i2;
            }
            i2 += (bArr[(bArr.length - i) - 1] & 255) << (i * 8);
            i++;
        }
    }

    public long getLongValue() {
        long j = 0;
        int i = 0;
        while (true) {
            if (i >= this.value.length) {
                return j;
            }
            j += (r3[(r3.length - i) - 1] & 255) << (i * 8);
            i++;
        }
    }

    public void setValue(byte[] bArr) {
        if (bArr == null) {
            ALog.m480e(TAG, "value empty");
        } else {
            this.value = bArr;
        }
    }

    public void setStringValue(String str) {
        if (str == null) {
            ALog.m480e(TAG, "str null");
        } else {
            this.value = str.getBytes(AlcsCoAPConstant.UTF8_CHARSET);
        }
    }

    public void setIntegerValue(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 4 && (i >= (1 << (i3 * 8)) || i < 0); i3++) {
            i2++;
        }
        this.value = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            this.value[(i2 - i4) - 1] = (byte) (i >> (i4 * 8));
        }
    }

    public void setLongValue(long j) {
        int i = 0;
        for (int i2 = 0; i2 < 8 && (j >= (1 << (i2 * 8)) || j < 0); i2++) {
            i++;
        }
        this.value = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            this.value[(i - i3) - 1] = (byte) (j >> (i3 * 8));
        }
    }

    public boolean isCritical() {
        return (this.number & 1) != 0;
    }

    public boolean isUnSafe() {
        return (this.number & 2) != 0;
    }

    public boolean isNoCacheKey() {
        return (this.number & 30) == 28;
    }

    @Override // java.lang.Comparable
    public int compareTo(Option option) {
        return this.number - option.number;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Option)) {
            return false;
        }
        Option option = (Option) obj;
        return this.number == option.number && Arrays.equals(this.value, option.value);
    }

    public int hashCode() {
        return (this.number * 31) + this.value.hashCode();
    }

    public String toString() {
        return OptionNumberRegistry.toString(this.number) + ": " + toValueString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.alcs.coap.option.Option$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C09141 {

        /* renamed from: $SwitchMap$com$aliyun$alink$linksdk$alcs$coap$option$OptionNumberRegistry$optionFormats */
        static final /* synthetic */ int[] f707x9f4c3613;

        static {
            int[] iArr = new int[OptionNumberRegistry.optionFormats.values().length];
            f707x9f4c3613 = iArr;
            try {
                iArr[OptionNumberRegistry.optionFormats.INTEGER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f707x9f4c3613[OptionNumberRegistry.optionFormats.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public String toValueString() {
        int i = C09141.f707x9f4c3613[OptionNumberRegistry.getFormatByNr(this.number).ordinal()];
        if (i != 1) {
            if (i == 2) {
                return "\"" + getStringValue() + "\"";
            }
            return toHexString(getValue());
        }
        int i2 = this.number;
        if (i2 == 17 || i2 == 12) {
            return "\"" + MediaTypeRegistry.toString(getIntegerValue()) + "\"";
        }
        if (i2 == 27 || i2 == 23) {
            return "\"" + new BlockOption(this.value) + "\"";
        }
        return Integer.toString(getIntegerValue());
    }

    private String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : bArr) {
            sb.append(String.format("%02x", Integer.valueOf(b & 255)));
        }
        return sb.toString();
    }
}
