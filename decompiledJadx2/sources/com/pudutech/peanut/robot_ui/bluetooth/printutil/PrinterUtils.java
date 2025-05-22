package com.pudutech.peanut.robot_ui.bluetooth.printutil;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes5.dex */
public class PrinterUtils {
    public static final byte CAN = 24;

    /* renamed from: CR */
    public static final byte f6980CR = 13;
    public static final byte DLE = 16;
    public static final byte ENQ = 5;
    public static final byte EOT = 4;
    public static final byte ESC = 27;

    /* renamed from: FF */
    public static final byte f6981FF = 12;

    /* renamed from: FS */
    public static final byte f6982FS = 28;

    /* renamed from: GS */
    public static final byte f6983GS = 29;

    /* renamed from: HT */
    public static final byte f6984HT = 9;

    /* renamed from: LF */
    public static final byte f6985LF = 10;

    /* renamed from: SP */
    public static final byte f6986SP = 32;
    private static String[] binaryArray = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    private static String hexStr = "0123456789ABCDEF";

    /* loaded from: classes5.dex */
    public static class BarCode {
        public static final byte CODE128 = 73;
        public static final byte CODE39 = 4;
        public static final byte CODE93 = 72;
        public static final byte EAN13 = 2;
        public static final byte EAN8 = 3;
        public static final byte ITF = 5;
        public static final byte NW7 = 6;
        public static final byte UPC_A = 0;
        public static final byte UPC_E = 1;
    }

    /* loaded from: classes5.dex */
    public static class CodePage {
        public static final byte KATAKANA = 1;
        public static final byte PC437 = 0;
        public static final byte PC850 = 2;
        public static final byte PC852 = 18;
        public static final byte PC858 = 19;
        public static final byte PC860 = 3;
        public static final byte PC863 = 4;
        public static final byte PC865 = 5;
        public static final byte PC866 = 17;
        public static final byte WPC1252 = 16;
    }

    public static byte[] barcode_height(byte b) {
        return new byte[]{29, 104, b};
    }

    public static byte[] fontSize(int i) {
        return new byte[]{29, BinaryMemcacheOpcodes.SASL_AUTH, (byte) i};
    }

    public static byte[] fontSizeSetBig(int i) {
        byte b;
        switch (i) {
            case 0:
            default:
                b = 0;
                break;
            case 1:
                b = 17;
                break;
            case 2:
                b = 34;
                break;
            case 3:
                b = TarConstants.LF_CHR;
                break;
            case 4:
                b = 68;
                break;
            case 5:
                b = 85;
                break;
            case 6:
                b = 102;
                break;
            case 7:
                b = 119;
                break;
        }
        return new byte[]{29, BinaryMemcacheOpcodes.SASL_AUTH, b};
    }

    public static byte[] printAndFeedLines(byte b) {
        return new byte[]{27, 100, b};
    }

    public static byte[] printAndReverseFeedLines(byte b) {
        return new byte[]{27, 101, b};
    }

    public static byte[] printHTNext() {
        return new byte[]{9};
    }

    public static byte[] printLineFeed() {
        return new byte[]{10};
    }

    public static byte[] printLineHeight(byte b) {
        return new byte[]{27, TarConstants.LF_CHR, b};
    }

    public static byte[] selectCodeTab(byte b) {
        return new byte[]{27, 116, b};
    }

    public static byte[] select_font_hri(byte b) {
        return new byte[]{29, 102, b};
    }

    public static byte[] select_position_hri(byte b) {
        return new byte[]{29, BarCode.CODE93, b};
    }

    public static byte[] set_HT_position(byte b) {
        return new byte[]{27, 68, b, 0};
    }

    public static byte[] underLine(boolean z, int i) {
        byte[] bArr = new byte[3];
        bArr[0] = z ? (byte) 28 : (byte) 27;
        bArr[1] = 45;
        if (i == 1) {
            bArr[2] = 1;
        } else if (i != 2) {
            bArr[2] = 0;
        } else {
            bArr[2] = 2;
        }
        return bArr;
    }

    public static byte[] initPrinter() {
        return new byte[]{27, 64};
    }

    public static byte[] emphasizedOn() {
        return new byte[]{27, 69, 15};
    }

    public static byte[] emphasizedOff() {
        return new byte[]{27, 69, 0};
    }

    public static byte[] overlappingOn() {
        return new byte[]{27, 47, 15};
    }

    public static byte[] overlappingOff() {
        return new byte[]{27, 47, 0};
    }

    public static byte[] doubleStrikeOn() {
        return new byte[]{27, 71, 15};
    }

    public static byte[] doubleStrikeOff() {
        return new byte[]{27, 71, 0};
    }

    public static byte[] selectFontA() {
        return new byte[]{27, 77, 0};
    }

    public static byte[] selectFontB() {
        return new byte[]{27, 77, 1};
    }

    public static byte[] selectFontC() {
        return new byte[]{27, 77, 2};
    }

    public static byte[] selectCNFontA() {
        return new byte[]{28, BinaryMemcacheOpcodes.SASL_AUTH, 0};
    }

    public static byte[] selectCNFontB() {
        return new byte[]{28, BinaryMemcacheOpcodes.SASL_AUTH, 1};
    }

    public static byte[] doubleHeightWidthOff() {
        return new byte[]{27, BinaryMemcacheOpcodes.SASL_AUTH, 0};
    }

    public static byte[] doubleHeightOn() {
        return new byte[]{27, BinaryMemcacheOpcodes.SASL_AUTH, 16};
    }

    public static byte[] doubleHeightWidthOn() {
        return new byte[]{27, BinaryMemcacheOpcodes.SASL_AUTH, 56};
    }

    public static byte[] alignLeft() {
        return new byte[]{27, 97, 0};
    }

    public static byte[] alignCenter() {
        return new byte[]{27, 97, 1};
    }

    public static byte[] alignRight() {
        return new byte[]{27, 97, 2};
    }

    public static byte[] printHorizontalTab() {
        return new byte[]{27, 44, 20, 28, 0};
    }

    public static byte[] printLineNormalHeight() {
        return new byte[]{27, TarConstants.LF_SYMLINK};
    }

    public static byte[] drawerKick() {
        return new byte[]{27, 112, 0, 60, TarConstants.LF_PAX_EXTENDED_HEADER_LC};
    }

    public static byte[] selectColor1() {
        return new byte[]{27, 114, 0};
    }

    public static byte[] selectColor2() {
        return new byte[]{27, 114, 1};
    }

    public static byte[] whitePrintingOn() {
        return new byte[]{29, 66, Byte.MIN_VALUE};
    }

    public static byte[] whitePrintingOff() {
        return new byte[]{29, 66, 0};
    }

    public static byte[] print_bar_code(byte b, String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 3 + 1];
        bArr[0] = 29;
        bArr[1] = 107;
        bArr[2] = b;
        int i = 3;
        for (byte b2 : bytes) {
            bArr[i] = b2;
            i++;
        }
        bArr[i] = 0;
        return bArr;
    }

    public static byte[] feedPaperCut() {
        return new byte[]{29, 86, 65, 0};
    }

    public static byte[] feedPaperCutPartial() {
        return new byte[]{29, 86, 66, 0};
    }

    public static ArrayList<byte[]> decodeBitmapToDataList(Bitmap bitmap, int i) {
        int i2;
        int i3;
        String str;
        int i4;
        int red;
        int green;
        int blue;
        Bitmap bitmap2 = bitmap;
        int i5 = (i <= 0 || i > 255) ? 255 : i;
        ArrayList<byte[]> arrayList = null;
        if (bitmap2 == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        if (width > 2040) {
            float f = 2040.0f / width;
            Matrix matrix = new Matrix();
            matrix.postScale(f, f);
            try {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                ArrayList<byte[]> decodeBitmapToDataList = decodeBitmapToDataList(createBitmap, i5);
                createBitmap.recycle();
                return decodeBitmapToDataList;
            } catch (OutOfMemoryError unused) {
                return null;
            }
        }
        int i6 = width % 8;
        int i7 = width / 8;
        if (i6 != 0) {
            i7++;
        }
        String hexString = Integer.toHexString(i7);
        int i8 = 2;
        if (hexString.length() > 2) {
            return null;
        }
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(hexString);
        String str2 = "00";
        sb.append("00");
        String sb2 = sb.toString();
        String str3 = "";
        if (i6 > 0) {
            for (int i9 = 0; i9 < 8 - i6; i9++) {
                str3 = str3 + "0";
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int i10 = height % i5;
        int i11 = height / i5;
        if (i10 != 0) {
            i11++;
        }
        int i12 = 0;
        while (i12 < i11) {
            int i13 = i12 == i11 + (-1) ? i10 : i5;
            String hexString2 = Integer.toHexString(i13);
            int i14 = i11;
            if (hexString2.length() > i8) {
                return arrayList;
            }
            if (hexString2.length() == 1) {
                hexString2 = "0" + hexString2;
            }
            arrayList2.add("1D763000" + sb2 + (hexString2 + str2));
            ArrayList arrayList3 = new ArrayList();
            StringBuilder sb3 = new StringBuilder();
            int i15 = 0;
            while (i15 < i13) {
                String str4 = str2;
                sb3.delete(0, sb3.length());
                int i16 = 0;
                while (i16 < width) {
                    int pixel = bitmap2.getPixel(i16, (i12 * i5) + i15);
                    if (bitmap.hasAlpha()) {
                        int alpha = Color.alpha(pixel);
                        i2 = width;
                        int red2 = Color.red(pixel);
                        i3 = i5;
                        int green2 = Color.green(pixel);
                        int blue2 = Color.blue(pixel);
                        float f2 = alpha / 255.0f;
                        str = sb2;
                        i4 = i15;
                        red = ((int) Math.ceil((red2 - 255) * f2)) + 255;
                        green = ((int) Math.ceil((green2 - 255) * f2)) + 255;
                        blue = ((int) Math.ceil((blue2 - 255) * f2)) + 255;
                    } else {
                        i2 = width;
                        i3 = i5;
                        str = sb2;
                        i4 = i15;
                        red = Color.red(pixel);
                        green = Color.green(pixel);
                        blue = Color.blue(pixel);
                    }
                    if (red > 160 && green > 160 && blue > 160) {
                        sb3.append("0");
                    } else {
                        sb3.append("1");
                    }
                    i16++;
                    bitmap2 = bitmap;
                    i15 = i4;
                    width = i2;
                    i5 = i3;
                    sb2 = str;
                }
                int i17 = width;
                int i18 = i5;
                String str5 = sb2;
                int i19 = i15;
                if (i6 > 0) {
                    sb3.append(str3);
                }
                arrayList3.add(sb3.toString());
                i15 = i19 + 1;
                bitmap2 = bitmap;
                str2 = str4;
                width = i17;
                i5 = i18;
                sb2 = str5;
            }
            int i20 = width;
            String str6 = str2;
            int i21 = i5;
            String str7 = sb2;
            ArrayList arrayList4 = new ArrayList();
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                String str8 = (String) it.next();
                sb3.delete(0, sb3.length());
                int i22 = 0;
                while (i22 < str8.length()) {
                    int i23 = i22 + 8;
                    sb3.append(binaryStrToHexString(str8.substring(i22, i23)));
                    i22 = i23;
                }
                arrayList4.add(sb3.toString());
            }
            arrayList2.addAll(arrayList4);
            i12++;
            bitmap2 = bitmap;
            i11 = i14;
            str2 = str6;
            width = i20;
            i5 = i21;
            sb2 = str7;
            arrayList = null;
            i8 = 2;
        }
        ArrayList<byte[]> arrayList5 = new ArrayList<>();
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList5.add(hexStringToBytes((String) it2.next()));
        }
        return arrayList5;
    }

    public static byte[] decodeBitmap(Bitmap bitmap, int i) {
        ArrayList<byte[]> decodeBitmapToDataList = decodeBitmapToDataList(bitmap, i);
        Iterator<byte[]> it = decodeBitmapToDataList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().length;
        }
        byte[] bArr = new byte[i2];
        Iterator<byte[]> it2 = decodeBitmapToDataList.iterator();
        int i3 = 0;
        while (it2.hasNext()) {
            byte[] next = it2.next();
            System.arraycopy(next, 0, bArr, i3, next.length);
            i3 += next.length;
        }
        return bArr;
    }

    public static byte[] decodeBitmap(Bitmap bitmap) {
        return decodeBitmap(bitmap, 255);
    }

    public static byte[] mergerByteArray(byte[]... bArr) {
        int i = 0;
        for (byte[] bArr2 : bArr) {
            i += bArr2.length;
        }
        byte[] bArr3 = new byte[i];
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3;
            for (byte b : bArr[i2]) {
                bArr3[i4] = b;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        return bArr3;
    }

    public static String binaryStrToHexString(String str) {
        int i = 0;
        String substring = str.substring(0, 4);
        String substring2 = str.substring(4, 8);
        String str2 = "";
        int i2 = 0;
        while (true) {
            String[] strArr = binaryArray;
            if (i2 >= strArr.length) {
                break;
            }
            if (substring.equals(strArr[i2])) {
                str2 = str2 + hexStr.substring(i2, i2 + 1);
            }
            i2++;
        }
        while (true) {
            String[] strArr2 = binaryArray;
            if (i >= strArr2.length) {
                return str2;
            }
            if (substring2.equals(strArr2[i])) {
                str2 = str2 + hexStr.substring(i, i + 1);
            }
            i++;
        }
    }

    public static byte[] hexListToByte(List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(hexStringToBytes(it.next()));
        }
        Iterator it2 = arrayList.iterator();
        int i = 0;
        while (it2.hasNext()) {
            i += ((byte[]) it2.next()).length;
        }
        byte[] bArr = new byte[i];
        Iterator it3 = arrayList.iterator();
        int i2 = 0;
        while (it3.hasNext()) {
            byte[] bArr2 = (byte[]) it3.next();
            System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
            i2 += bArr2.length;
        }
        return bArr;
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte charToByte(char c) {
        return (byte) hexStr.indexOf(c);
    }
}
