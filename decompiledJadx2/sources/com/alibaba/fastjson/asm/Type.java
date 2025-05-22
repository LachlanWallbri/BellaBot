package com.alibaba.fastjson.asm;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amitshekhar.utils.DataType;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Type {
    private final char[] buf;
    private final int len;
    private final int off;
    protected final int sort;
    public static final Type VOID_TYPE = new Type(0, null, 1443168256, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, null, 1509950721, 1);
    public static final Type CHAR_TYPE = new Type(2, null, 1124075009, 1);
    public static final Type BYTE_TYPE = new Type(3, null, 1107297537, 1);
    public static final Type SHORT_TYPE = new Type(4, null, 1392510721, 1);
    public static final Type INT_TYPE = new Type(5, null, 1224736769, 1);
    public static final Type FLOAT_TYPE = new Type(6, null, 1174536705, 1);
    public static final Type LONG_TYPE = new Type(7, null, 1241579778, 1);
    public static final Type DOUBLE_TYPE = new Type(8, null, 1141048066, 1);

    private Type(int i, char[] cArr, int i2, int i3) {
        this.sort = i;
        this.buf = cArr;
        this.off = i2;
        this.len = i3;
    }

    public static Type getType(String str) {
        return getType(str.toCharArray(), 0);
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1;
        while (true) {
            i = i3 + 1;
            char charAt = str.charAt(i3);
            if (charAt == ')') {
                break;
            }
            if (charAt == 'L') {
                while (true) {
                    i3 = i + 1;
                    if (str.charAt(i) == ';') {
                        break;
                    }
                    i = i3;
                }
                i4++;
            } else {
                i4 = (charAt == 'D' || charAt == 'J') ? i4 + 2 : i4 + 1;
                i3 = i;
            }
        }
        char charAt2 = str.charAt(i);
        int i5 = i4 << 2;
        if (charAt2 == 'V') {
            i2 = 0;
        } else if (charAt2 == 'D' || charAt2 == 'J') {
            i2 = 2;
        }
        return i5 | i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x004f, code lost:
    
        if (r6[r4] == 'L') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0051, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0057, code lost:
    
        if (r6[r7 + r0] == ';') goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0062, code lost:
    
        return new com.alibaba.fastjson.asm.Type(9, r6, r7, r0 + 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Type getType(char[] cArr, int i) {
        char c = cArr[i];
        if (c == 'F') {
            return FLOAT_TYPE;
        }
        if (c == 'S') {
            return SHORT_TYPE;
        }
        if (c == 'V') {
            return VOID_TYPE;
        }
        if (c == 'I') {
            return INT_TYPE;
        }
        if (c == 'J') {
            return LONG_TYPE;
        }
        if (c == 'Z') {
            return BOOLEAN_TYPE;
        }
        if (c == '[') {
            int i2 = 1;
            while (true) {
                int i3 = i + i2;
                if (cArr[i3] != '[') {
                    break;
                }
                i2++;
            }
        } else {
            switch (c) {
                case 'B':
                    return BYTE_TYPE;
                case 'C':
                    return CHAR_TYPE;
                case 'D':
                    return DOUBLE_TYPE;
                default:
                    int i4 = 1;
                    while (cArr[i + i4] != ';') {
                        i4++;
                    }
                    return new Type(10, cArr, i + 1, i4 - 1);
            }
        }
    }

    public String getInternalName() {
        return new String(this.buf, this.off, this.len);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getDescriptor() {
        return new String(this.buf, this.off, this.len);
    }

    private int getDimensions() {
        int i = 1;
        while (this.buf[this.off + i] == '[') {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Type[] getArgumentTypes(String str) {
        char[] charArray = str.toCharArray();
        int i = 1;
        int i2 = 0;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char c = charArray[i3];
            if (c == ')') {
                break;
            }
            if (c == 'L') {
                while (true) {
                    i3 = i4 + 1;
                    if (charArray[i4] == ';') {
                        break;
                    }
                    i4 = i3;
                }
                i2++;
            } else {
                if (c != '[') {
                    i2++;
                }
                i3 = i4;
            }
        }
        Type[] typeArr = new Type[i2];
        int i5 = 0;
        while (charArray[i] != ')') {
            typeArr[i5] = getType(charArray, i);
            i += typeArr[i5].len + (typeArr[i5].sort == 10 ? 2 : 0);
            i5++;
        }
        return typeArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getClassName() {
        switch (this.sort) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return "float";
            case 7:
                return DataType.LONG;
            case 8:
                return TmpConstant.TYPE_VALUE_DOUBLE;
            case 9:
                StringBuffer stringBuffer = new StringBuffer(getType(this.buf, this.off + getDimensions()).getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    stringBuffer.append("[]");
                }
                return stringBuffer.toString();
            default:
                return new String(this.buf, this.off, this.len).replace('/', FilenameUtils.EXTENSION_SEPARATOR);
        }
    }
}
