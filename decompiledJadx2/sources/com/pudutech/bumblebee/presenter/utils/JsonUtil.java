package com.pudutech.bumblebee.presenter.utils;

/* loaded from: classes4.dex */
public class JsonUtil {
    public static String formatJson(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char c = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != ',') {
                if (charAt != '[') {
                    if (charAt != ']') {
                        if (charAt != '{') {
                            if (charAt != '}') {
                                sb.append(charAt);
                            }
                        }
                    }
                    sb.append('\n');
                    i2--;
                    addIndentBlank(sb, i2);
                    sb.append(charAt);
                }
                sb.append(charAt);
                sb.append('\n');
                i2++;
                addIndentBlank(sb, i2);
            } else {
                sb.append(charAt);
                if (c != '\\') {
                    sb.append('\n');
                    addIndentBlank(sb, i2);
                }
            }
            i++;
            c = charAt;
        }
        return sb.toString();
    }

    private static void addIndentBlank(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append('\t');
        }
    }

    public static String decodeUnicode(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                i = i2 + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 == 'u') {
                    int i3 = 0;
                    int i4 = i;
                    int i5 = 0;
                    while (i5 < 4) {
                        int i6 = i4 + 1;
                        char charAt3 = str.charAt(i4);
                        switch (charAt3) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                i3 = ((i3 << 4) + charAt3) - 48;
                                break;
                            default:
                                switch (charAt3) {
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        i3 = (((i3 << 4) + 10) + charAt3) - 65;
                                        break;
                                    default:
                                        switch (charAt3) {
                                            case 'a':
                                            case 'b':
                                            case 'c':
                                            case 'd':
                                            case 'e':
                                            case 'f':
                                                i3 = (((i3 << 4) + 10) + charAt3) - 97;
                                                break;
                                            default:
                                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                                        }
                                }
                        }
                        i5++;
                        i4 = i6;
                    }
                    stringBuffer.append((char) i3);
                    i = i4;
                } else {
                    if (charAt2 == 't') {
                        charAt2 = '\t';
                    } else if (charAt2 == 'r') {
                        charAt2 = '\r';
                    } else if (charAt2 == 'n') {
                        charAt2 = '\n';
                    } else if (charAt2 == 'f') {
                        charAt2 = '\f';
                    }
                    stringBuffer.append(charAt2);
                }
            } else {
                stringBuffer.append(charAt);
                i = i2;
            }
        }
        return stringBuffer.toString();
    }
}
