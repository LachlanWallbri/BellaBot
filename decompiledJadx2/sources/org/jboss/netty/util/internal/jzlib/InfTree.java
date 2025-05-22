package org.jboss.netty.util.internal.jzlib;

import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.hoho.android.usbserial.driver.UsbId;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.slamtec.slamware.robot.HealthInfo;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.jetbrains.anko.DimensionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class InfTree {
    static final int BMAX = 15;
    static final int fixed_bd = 5;
    static final int fixed_bl = 9;

    /* renamed from: c */
    private int[] f10059c;

    /* renamed from: hn */
    private int[] f10060hn;

    /* renamed from: r */
    private int[] f10061r;

    /* renamed from: u */
    private int[] f10062u;

    /* renamed from: v */
    private int[] f10063v;

    /* renamed from: x */
    private int[] f10064x;
    static final int[] fixed_tl = {96, 7, 256, 0, 8, 80, 0, 8, 16, 84, 8, 115, 82, 7, 31, 0, 8, 112, 0, 8, 48, 0, 9, 192, 80, 7, 10, 0, 8, 96, 0, 8, 32, 0, 9, 160, 0, 8, 0, 0, 8, 128, 0, 8, 64, 0, 9, 224, 80, 7, 6, 0, 8, 88, 0, 8, 24, 0, 9, 144, 83, 7, 59, 0, 8, 120, 0, 8, 56, 0, 9, 208, 81, 7, 17, 0, 8, 104, 0, 8, 40, 0, 9, 176, 0, 8, 8, 0, 8, 136, 0, 8, 72, 0, 9, DimensionsKt.HDPI, 80, 7, 4, 0, 8, 84, 0, 8, 20, 85, 8, 227, 83, 7, 43, 0, 8, 116, 0, 8, 52, 0, 9, 200, 81, 7, 13, 0, 8, 100, 0, 8, 36, 0, 9, 168, 0, 8, 4, 0, 8, 132, 0, 8, 68, 0, 9, 232, 80, 7, 8, 0, 8, 92, 0, 8, 28, 0, 9, 152, 84, 7, 83, 0, 8, 124, 0, 8, 60, 0, 9, 216, 82, 7, 23, 0, 8, 108, 0, 8, 44, 0, 9, 184, 0, 8, 12, 0, 8, 140, 0, 8, 76, 0, 9, GateControllerMsg.ControlCode.Error, 80, 7, 3, 0, 8, 82, 0, 8, 18, 85, 8, 163, 83, 7, 35, 0, 8, 114, 0, 8, 50, 0, 9, 196, 81, 7, 11, 0, 8, 98, 0, 8, 34, 0, 9, 164, 0, 8, 2, 0, 8, 130, 0, 8, 66, 0, 9, 228, 80, 7, 7, 0, 8, 90, 0, 8, 26, 0, 9, 148, 84, 7, 67, 0, 8, 122, 0, 8, 58, 0, 9, 212, 82, 7, 19, 0, 8, 106, 0, 8, 42, 0, 9, 180, 0, 8, 10, 0, 8, 138, 0, 8, 74, 0, 9, 244, 80, 7, 5, 0, 8, 86, 0, 8, 22, 192, 8, 0, 83, 7, 51, 0, 8, 118, 0, 8, 54, 0, 9, 204, 81, 7, 15, 0, 8, 102, 0, 8, 38, 0, 9, 172, 0, 8, 6, 0, 8, 134, 0, 8, 70, 0, 9, 236, 80, 7, 9, 0, 8, 94, 0, 8, 30, 0, 9, 156, 84, 7, 99, 0, 8, 126, 0, 8, 62, 0, 9, 220, 82, 7, 27, 0, 8, 110, 0, 8, 46, 0, 9, 188, 0, 8, 14, 0, 8, 142, 0, 8, 78, 0, 9, 252, 96, 7, 256, 0, 8, 81, 0, 8, 17, 85, 8, 131, 82, 7, 31, 0, 8, 113, 0, 8, 49, 0, 9, 194, 80, 7, 10, 0, 8, 97, 0, 8, 33, 0, 9, 162, 0, 8, 1, 0, 8, 129, 0, 8, 65, 0, 9, 226, 80, 7, 6, 0, 8, 89, 0, 8, 25, 0, 9, 146, 83, 7, 59, 0, 8, 121, 0, 8, 57, 0, 9, 210, 81, 7, 17, 0, 8, 105, 0, 8, 41, 0, 9, 178, 0, 8, 9, 0, 8, 137, 0, 8, 73, 0, 9, 242, 80, 7, 4, 0, 8, 85, 0, 8, 21, 80, 8, 258, 83, 7, 43, 0, 8, 117, 0, 8, 53, 0, 9, 202, 81, 7, 13, 0, 8, 101, 0, 8, 37, 0, 9, 170, 0, 8, 5, 0, 8, 133, 0, 8, 69, 0, 9, 234, 80, 7, 8, 0, 8, 93, 0, 8, 29, 0, 9, 154, 84, 7, 83, 0, 8, 125, 0, 8, 61, 0, 9, 218, 82, 7, 23, 0, 8, 109, 0, 8, 45, 0, 9, 186, 0, 8, 13, 0, 8, 141, 0, 8, 77, 0, 9, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 80, 7, 3, 0, 8, 83, 0, 8, 19, 85, 8, 195, 83, 7, 35, 0, 8, 115, 0, 8, 51, 0, 9, 198, 81, 7, 11, 0, 8, 99, 0, 8, 35, 0, 9, 166, 0, 8, 3, 0, 8, 131, 0, 8, 67, 0, 9, 230, 80, 7, 7, 0, 8, 91, 0, 8, 27, 0, 9, 150, 84, 7, 67, 0, 8, 123, 0, 8, 59, 0, 9, 214, 82, 7, 19, 0, 8, 107, 0, 8, 43, 0, 9, 182, 0, 8, 11, 0, 8, 139, 0, 8, 75, 0, 9, 246, 80, 7, 5, 0, 8, 87, 0, 8, 23, 192, 8, 0, 83, 7, 51, 0, 8, 119, 0, 8, 55, 0, 9, HttpStatus.SC_PARTIAL_CONTENT, 81, 7, 15, 0, 8, 103, 0, 8, 39, 0, 9, 174, 0, 8, 7, 0, 8, 135, 0, 8, 71, 0, 9, 238, 80, 7, 9, 0, 8, 95, 0, 8, 31, 0, 9, 158, 84, 7, 99, 0, 8, 127, 0, 8, 63, 0, 9, 222, 82, 7, 27, 0, 8, 111, 0, 8, 47, 0, 9, 190, 0, 8, 15, 0, 8, 143, 0, 8, 79, 0, 9, 254, 96, 7, 256, 0, 8, 80, 0, 8, 16, 84, 8, 115, 82, 7, 31, 0, 8, 112, 0, 8, 48, 0, 9, 193, 80, 7, 10, 0, 8, 96, 0, 8, 32, 0, 9, 161, 0, 8, 0, 0, 8, 128, 0, 8, 64, 0, 9, 225, 80, 7, 6, 0, 8, 88, 0, 8, 24, 0, 9, 145, 83, 7, 59, 0, 8, 120, 0, 8, 56, 0, 9, 209, 81, 7, 17, 0, 8, 104, 0, 8, 40, 0, 9, 177, 0, 8, 8, 0, 8, 136, 0, 8, 72, 0, 9, 241, 80, 7, 4, 0, 8, 84, 0, 8, 20, 85, 8, 227, 83, 7, 43, 0, 8, 116, 0, 8, 52, 0, 9, 201, 81, 7, 13, 0, 8, 100, 0, 8, 36, 0, 9, 169, 0, 8, 4, 0, 8, 132, 0, 8, 68, 0, 9, 233, 80, 7, 8, 0, 8, 92, 0, 8, 28, 0, 9, 153, 84, 7, 83, 0, 8, 124, 0, 8, 60, 0, 9, 217, 82, 7, 23, 0, 8, 108, 0, 8, 44, 0, 9, 185, 0, 8, 12, 0, 8, 140, 0, 8, 76, 0, 9, 249, 80, 7, 3, 0, 8, 82, 0, 8, 18, 85, 8, 163, 83, 7, 35, 0, 8, 114, 0, 8, 50, 0, 9, 197, 81, 7, 11, 0, 8, 98, 0, 8, 34, 0, 9, 165, 0, 8, 2, 0, 
    8, 130, 0, 8, 66, 0, 9, 229, 80, 7, 7, 0, 8, 90, 0, 8, 26, 0, 9, 149, 84, 7, 67, 0, 8, 122, 0, 8, 58, 0, 9, DimensionsKt.TVDPI, 82, 7, 19, 0, 8, 106, 0, 8, 42, 0, 9, 181, 0, 8, 10, 0, 8, 138, 0, 8, 74, 0, 9, 245, 80, 7, 5, 0, 8, 86, 0, 8, 22, 192, 8, 0, 83, 7, 51, 0, 8, 118, 0, 8, 54, 0, 9, HttpStatus.SC_RESET_CONTENT, 81, 7, 15, 0, 8, 102, 0, 8, 38, 0, 9, 173, 0, 8, 6, 0, 8, 134, 0, 8, 70, 0, 9, 237, 80, 7, 9, 0, 8, 94, 0, 8, 30, 0, 9, 157, 84, 7, 99, 0, 8, 126, 0, 8, 62, 0, 9, 221, 82, 7, 27, 0, 8, 110, 0, 8, 46, 0, 9, 189, 0, 8, 14, 0, 8, 142, 0, 8, 78, 0, 9, 253, 96, 7, 256, 0, 8, 81, 0, 8, 17, 85, 8, 131, 82, 7, 31, 0, 8, 113, 0, 8, 49, 0, 9, 195, 80, 7, 10, 0, 8, 97, 0, 8, 33, 0, 9, 163, 0, 8, 1, 0, 8, 129, 0, 8, 65, 0, 9, 227, 80, 7, 6, 0, 8, 89, 0, 8, 25, 0, 9, 147, 83, 7, 59, 0, 8, 121, 0, 8, 57, 0, 9, Primes.SMALL_FACTOR_LIMIT, 81, 7, 17, 0, 8, 105, 0, 8, 41, 0, 9, 179, 0, 8, 9, 0, 8, 137, 0, 8, 73, 0, 9, 243, 80, 7, 4, 0, 8, 85, 0, 8, 21, 80, 8, 258, 83, 7, 43, 0, 8, 117, 0, 8, 53, 0, 9, HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, 81, 7, 13, 0, 8, 101, 0, 8, 37, 0, 9, 171, 0, 8, 5, 0, 8, 133, 0, 8, 69, 0, 9, 235, 80, 7, 8, 0, 8, 93, 0, 8, 29, 0, 9, 155, 84, 7, 83, 0, 8, 125, 0, 8, 61, 0, 9, 219, 82, 7, 23, 0, 8, 109, 0, 8, 45, 0, 9, 187, 0, 8, 13, 0, 8, 141, 0, 8, 77, 0, 9, 251, 80, 7, 3, 0, 8, 83, 0, 8, 19, 85, 8, 195, 83, 7, 35, 0, 8, 115, 0, 8, 51, 0, 9, 199, 81, 7, 11, 0, 8, 99, 0, 8, 35, 0, 9, 167, 0, 8, 3, 0, 8, 131, 0, 8, 67, 0, 9, 231, 80, 7, 7, 0, 8, 91, 0, 8, 27, 0, 9, 151, 84, 7, 67, 0, 8, 123, 0, 8, 59, 0, 9, 215, 82, 7, 19, 0, 8, 107, 0, 8, 43, 0, 9, 183, 0, 8, 11, 0, 8, 139, 0, 8, 75, 0, 9, 247, 80, 7, 5, 0, 8, 87, 0, 8, 23, 192, 8, 0, 83, 7, 51, 0, 8, 119, 0, 8, 55, 0, 9, HttpStatus.SC_MULTI_STATUS, 81, 7, 15, 0, 8, 103, 0, 8, 39, 0, 9, 175, 0, 8, 7, 0, 8, 135, 0, 8, 71, 0, 9, 239, 80, 7, 9, 0, 8, 95, 0, 8, 31, 0, 9, 159, 84, 7, 99, 0, 8, 127, 0, 8, 63, 0, 9, 223, 82, 7, 27, 0, 8, 111, 0, 8, 47, 0, 9, 191, 0, 8, 15, 0, 8, 143, 0, 8, 79, 0, 9, 255};
    static final int[] fixed_td = {80, 5, 1, 87, 5, 257, 83, 5, 17, 91, 5, 4097, 81, 5, 5, 89, 5, InputDeviceCompat.SOURCE_GAMEPAD, 85, 5, 65, 93, 5, 16385, 80, 5, 3, 88, 5, InputDeviceCompat.SOURCE_DPAD, 84, 5, 33, 92, 5, 8193, 82, 5, 9, 90, 5, HealthInfo.BaseError.BaseComponentErrorTypeSystemEmergencyStop, 86, 5, 129, 192, 5, UsbId.FTDI_FT232R, 80, 5, 2, 87, 5, 385, 83, 5, 25, 91, 5, 6145, 81, 5, 7, 89, 5, 1537, 85, 5, 97, 93, 5, UsbId.FTDI_FT232R, 80, 5, 4, 88, 5, 769, 84, 5, 49, 92, 5, 12289, 82, 5, 13, 90, 5, HealthInfo.BaseError.BaseComponentErrorTypePowerControllerDown, 86, 5, 193, 192, 5, UsbId.FTDI_FT232R};
    static final int[] cplens = {3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 23, 27, 31, 35, 43, 51, 59, 67, 83, 99, 115, 131, 163, 195, 227, 258, 0, 0};
    static final int[] cplext = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0, 112, 112};
    static final int[] cpdist = {1, 2, 3, 4, 5, 7, 9, 13, 17, 25, 33, 49, 65, 97, 129, 193, 257, 385, InputDeviceCompat.SOURCE_DPAD, 769, InputDeviceCompat.SOURCE_GAMEPAD, 1537, HealthInfo.BaseError.BaseComponentErrorTypeSystemEmergencyStop, HealthInfo.BaseError.BaseComponentErrorTypePowerControllerDown, 4097, 6145, 8193, 12289, 16385, UsbId.FTDI_FT232R};
    static final int[] cpdext = {0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13};

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01c0, code lost:
    
        if ((((1 << r14) - 1) & r1) == r22.f10064x[r17]) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01c2, code lost:
    
        r17 = r17 - 1;
        r14 = r14 - r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0151, code lost:
    
        if (r33[r6] >= r2) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0153, code lost:
    
        r24 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0159, code lost:
    
        if (r33[r6] >= 256) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x015b, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0160, code lost:
    
        r10[0] = (byte) r4;
        r22.f10061r[2] = r33[r6];
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x018b, code lost:
    
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x015e, code lost:
    
        r4 = 96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0170, code lost:
    
        r24 = r5;
        r10[0] = (byte) ((r28[r33[r6] - r2] + 16) + 64);
        r10[2] = r27[r33[r6] - r2];
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01d0, code lost:
    
        r7 = r7 + 1;
        r4 = r4;
        r2 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0135, code lost:
    
        r10 = r22.f10061r;
        r12 = r7 - r14;
        r10[1] = (byte) r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x013f, code lost:
    
        if (r6 < r5) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0141, code lost:
    
        r10[0] = 192;
        r24 = r5;
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x018c, code lost:
    
        r5 = r4 << r12;
        r4 = r1 >>> r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0190, code lost:
    
        if (r4 >= r15) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0192, code lost:
    
        java.lang.System.arraycopy(r22.f10061r, 0, r31, (r8 + r4) * 3, 3);
        r4 = r4 + r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01a4, code lost:
    
        r5 = 1 << (r7 - 1);
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01b1, code lost:
    
        if ((r1 & r5) == 0) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01b3, code lost:
    
        r1 = r1 ^ r5;
        r5 = r5 >>> 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01b7, code lost:
    
        r1 = r1 ^ r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int huft_build(int[] iArr, int i, int i2, int i3, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, int[] iArr7, int[] iArr8) {
        int[] iArr9;
        int i4;
        int i5;
        int i6;
        boolean z;
        int i7 = i3;
        int i8 = 0;
        int i9 = i2;
        int i10 = 0;
        while (true) {
            iArr9 = this.f10059c;
            int i11 = iArr[i + i10];
            i4 = 1;
            iArr9[i11] = iArr9[i11] + 1;
            i10++;
            i5 = -1;
            i9--;
            if (i9 == 0) {
                break;
            }
            i7 = i3;
        }
        if (iArr9[0] == i2) {
            iArr4[0] = -1;
            iArr5[0] = 0;
            return 0;
        }
        int i12 = iArr5[0];
        int i13 = 1;
        while (i13 <= 15 && this.f10059c[i13] == 0) {
            i13++;
        }
        if (i12 < i13) {
            i12 = i13;
        }
        int i14 = 15;
        while (i14 != 0 && this.f10059c[i14] == 0) {
            i14--;
        }
        int i15 = i12 > i14 ? i14 : i12;
        iArr5[0] = i15;
        int i16 = 1 << i13;
        int i17 = i13;
        while (i17 < i14) {
            int i18 = i16 - this.f10059c[i17];
            if (i18 < 0) {
                return -3;
            }
            i17++;
            i16 = i18 << 1;
        }
        int[] iArr10 = this.f10059c;
        int i19 = i16 - iArr10[i14];
        if (i19 < 0) {
            return -3;
        }
        iArr10[i14] = iArr10[i14] + i19;
        this.f10064x[1] = 0;
        int i20 = 0;
        int i21 = i14;
        int i22 = 1;
        int i23 = 2;
        while (true) {
            i21 += i5;
            if (i21 == 0) {
                break;
            }
            int[] iArr11 = this.f10064x;
            i20 += this.f10059c[i22];
            iArr11[i23] = i20;
            i23++;
            i22++;
            i5 = -1;
        }
        int i24 = 0;
        int i25 = 0;
        while (true) {
            int i26 = iArr[i + i24];
            if (i26 != 0) {
                int[] iArr12 = this.f10064x;
                int i27 = iArr12[i26];
                iArr12[i26] = i27 + 1;
                iArr8[i27] = i25;
            }
            i24++;
            i25++;
            if (i25 >= i2) {
                break;
            }
            i7 = i3;
        }
        int[] iArr13 = this.f10064x;
        int i28 = iArr13[i14];
        iArr13[0] = 0;
        this.f10062u[0] = 0;
        int i29 = -i15;
        int i30 = 0;
        int i31 = 0;
        int i32 = 0;
        int i33 = 0;
        int i34 = -1;
        while (i13 <= i14) {
            int i35 = this.f10059c[i13];
            while (true) {
                int i36 = i35 - 1;
                if (i35 != 0) {
                    while (true) {
                        int i37 = i29 + i15;
                        if (i13 <= i37) {
                            break;
                        }
                        i34++;
                        int i38 = i14 - i37;
                        if (i38 > i15) {
                            i38 = i15;
                        }
                        int i39 = i13 - i37;
                        int i40 = i4 << i39;
                        int i41 = i36 + 1;
                        if (i40 > i41) {
                            int i42 = i40 - i41;
                            if (i39 < i38) {
                                int i43 = i13;
                                while (true) {
                                    i39 += i4;
                                    if (i39 >= i38) {
                                        break;
                                    }
                                    int i44 = i42 << i4;
                                    int i45 = i38;
                                    int[] iArr14 = this.f10059c;
                                    i43 += i4;
                                    if (i44 <= iArr14[i43]) {
                                        break;
                                    }
                                    i42 = i44 - iArr14[i43];
                                    i38 = i45;
                                    i4 = 1;
                                }
                            }
                        }
                        i33 = 1 << i39;
                        if (iArr7[0] + i33 > 1440) {
                            return -3;
                        }
                        int[] iArr15 = this.f10062u;
                        int i46 = iArr7[0];
                        iArr15[i34] = i46;
                        iArr7[0] = iArr7[0] + i33;
                        if (i34 != 0) {
                            this.f10064x[i34] = i30;
                            int[] iArr16 = this.f10061r;
                            iArr16[0] = (byte) i39;
                            iArr16[1] = (byte) i15;
                            int i47 = i30 >>> (i37 - i15);
                            int i48 = i34 - 1;
                            iArr16[2] = (i46 - iArr15[i48]) - i47;
                            int i49 = (iArr15[i48] + i47) * 3;
                            z = false;
                            System.arraycopy(iArr16, 0, iArr6, i49, 3);
                        } else {
                            z = false;
                            iArr4[0] = i46;
                        }
                        i32 = i46;
                        i4 = 1;
                        i29 = i37;
                    }
                }
                i28 = i6;
                i4 = 1;
                i35 = i36;
                i8 = 0;
                i7 = i3;
            }
        }
        int i50 = i8;
        int i51 = i4;
        if (i19 == 0 || i14 == i51) {
            return i50;
        }
        return -5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int inflate_trees_bits(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, ZStream zStream) {
        initWorkArea(19);
        int[] iArr5 = this.f10060hn;
        iArr5[0] = 0;
        int huft_build = huft_build(iArr, 0, 19, 19, null, null, iArr3, iArr2, iArr4, iArr5, this.f10063v);
        if (huft_build == -3) {
            zStream.msg = "oversubscribed dynamic bit lengths tree";
            return huft_build;
        }
        if (huft_build != -5 && iArr2[0] != 0) {
            return huft_build;
        }
        zStream.msg = "incomplete dynamic bit lengths tree";
        return -3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int inflate_trees_dynamic(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, ZStream zStream) {
        initWorkArea(288);
        int[] iArr7 = this.f10060hn;
        iArr7[0] = 0;
        int huft_build = huft_build(iArr, 0, i, 257, cplens, cplext, iArr4, iArr2, iArr6, iArr7, this.f10063v);
        if (huft_build != 0 || iArr2[0] == 0) {
            if (huft_build == -3) {
                zStream.msg = "oversubscribed literal/length tree";
                return huft_build;
            }
            if (huft_build == -4) {
                return huft_build;
            }
            zStream.msg = "incomplete literal/length tree";
            return -3;
        }
        initWorkArea(288);
        int huft_build2 = huft_build(iArr, i, i2, 0, cpdist, cpdext, iArr5, iArr3, iArr6, this.f10060hn, this.f10063v);
        if (huft_build2 == 0 && (iArr3[0] != 0 || i <= 257)) {
            return 0;
        }
        if (huft_build2 == -3) {
            zStream.msg = "oversubscribed distance tree";
        } else {
            if (huft_build2 == -5) {
                zStream.msg = "incomplete distance tree";
                return -3;
            }
            if (huft_build2 != -4) {
                zStream.msg = "empty distance tree with lengths";
                return -3;
            }
        }
        return huft_build2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int inflate_trees_fixed(int[] iArr, int[] iArr2, int[][] iArr3, int[][] iArr4) {
        iArr[0] = 9;
        iArr2[0] = 5;
        iArr3[0] = fixed_tl;
        iArr4[0] = fixed_td;
        return 0;
    }

    private void initWorkArea(int i) {
        if (this.f10060hn == null) {
            this.f10060hn = new int[1];
            this.f10063v = new int[i];
            this.f10059c = new int[16];
            this.f10061r = new int[3];
            this.f10062u = new int[15];
            this.f10064x = new int[16];
            return;
        }
        if (this.f10063v.length < i) {
            this.f10063v = new int[i];
        } else {
            for (int i2 = 0; i2 < i; i2++) {
                this.f10063v[i2] = 0;
            }
        }
        for (int i3 = 0; i3 < 16; i3++) {
            this.f10059c[i3] = 0;
        }
        for (int i4 = 0; i4 < 3; i4++) {
            this.f10061r[i4] = 0;
        }
        System.arraycopy(this.f10059c, 0, this.f10062u, 0, 15);
        System.arraycopy(this.f10059c, 0, this.f10064x, 0, 16);
    }
}
