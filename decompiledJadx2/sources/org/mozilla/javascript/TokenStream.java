package org.mozilla.javascript;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amitshekhar.utils.DataType;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.IOException;
import java.io.Reader;
import org.mozilla.javascript.Token;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class TokenStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final char BYTE_ORDER_MARK = 65279;
    private static final int EOF_CHAR = -1;
    Token.CommentType commentType;
    int cursor;
    private boolean dirtyLine;
    private boolean isBinary;
    private boolean isHex;
    private boolean isOctal;
    private boolean isOldOctal;
    int lineno;
    private double number;
    private Parser parser;
    private int quoteChar;
    String regExpFlags;
    private char[] sourceBuffer;
    int sourceCursor;
    private int sourceEnd;
    private Reader sourceReader;
    private String sourceString;
    private int stringBufferTop;
    int tokenBeg;
    int tokenEnd;
    private int ungetCursor;
    private boolean xmlIsAttribute;
    private boolean xmlIsTagContent;
    private int xmlOpenTagsCount;
    private String string = "";
    private char[] stringBuffer = new char[128];
    private ObjToIntMap allStrings = new ObjToIntMap(50);
    private final int[] ungetBuffer = new int[3];
    private boolean hitEOF = false;
    private int lineStart = 0;
    private int lineEndChar = -1;
    private String commentPrefix = "";
    private int commentCursor = -1;

    private static boolean isAlpha(int i) {
        return i <= 90 ? 65 <= i : 97 <= i && i <= 122;
    }

    static boolean isDigit(int i) {
        return 48 <= i && i <= 57;
    }

    String tokenToString(int i) {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenStream(Parser parser, Reader reader, String str, int i) {
        this.parser = parser;
        this.lineno = i;
        if (reader != null) {
            if (str != null) {
                Kit.codeBug();
            }
            this.sourceReader = reader;
            this.sourceBuffer = new char[512];
            this.sourceEnd = 0;
        } else {
            if (str == null) {
                Kit.codeBug();
            }
            this.sourceString = str;
            this.sourceEnd = str.length();
        }
        this.cursor = 0;
        this.sourceCursor = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isKeyword(String str, int i, boolean z) {
        return stringToKeyword(str, i, z) != 0;
    }

    private static int stringToKeyword(String str, int i, boolean z) {
        if (i < 200) {
            return stringToKeywordForJS(str);
        }
        return stringToKeywordForES(str, z);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0204, code lost:
    
        if (r17.charAt(1) == 'n') goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x029e, code lost:
    
        r6 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x021c, code lost:
    
        if (r17.charAt(1) == 'a') goto L225;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x022e, code lost:
    
        if (r17.charAt(1) == 'h') goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x029c, code lost:
    
        if (r17.charAt(1) == 'n') goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x02d3, code lost:
    
        if (r17.charAt(0) == 'd') goto L225;
     */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x02da A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int stringToKeywordForJS(String str) {
        String str2;
        int i;
        int i2 = 116;
        switch (str.length()) {
            case 2:
                i2 = 119;
                char charAt = str.charAt(1);
                if (charAt == 'f') {
                    if (str.charAt(0) == 'i') {
                        i = 113;
                        break;
                    }
                    str2 = null;
                    i2 = 0;
                } else {
                    if (charAt != 'n') {
                        if (charAt == 'o') {
                            break;
                        }
                    } else if (str.charAt(0) == 'i') {
                        i = 52;
                        break;
                    }
                    str2 = null;
                    i2 = 0;
                }
                if (str2 != null && str2 != str && !str2.equals(str)) {
                    i = 0;
                    break;
                }
                i = i2;
                break;
            case 3:
                char charAt2 = str.charAt(0);
                if (charAt2 != 'f') {
                    if (charAt2 == 'i') {
                        if (str.charAt(2) == 't') {
                            break;
                        }
                    } else if (charAt2 == 'l') {
                        if (str.charAt(2) == 't' && str.charAt(1) == 'e') {
                            i = 154;
                            break;
                        }
                    } else if (charAt2 == 'n') {
                        if (str.charAt(2) == 'w' && str.charAt(1) == 'e') {
                            i = 30;
                            break;
                        }
                    } else if (charAt2 == 't') {
                        if (str.charAt(2) == 'y' && str.charAt(1) == 'r') {
                            i = 82;
                            break;
                        }
                    } else if (charAt2 == 'v' && str.charAt(2) == 'r' && str.charAt(1) == 'a') {
                        i = 123;
                        break;
                    }
                    str2 = null;
                    i2 = 0;
                } else {
                    if (str.charAt(2) == 'r' && str.charAt(1) == 'o') {
                        i = 120;
                        break;
                    }
                    str2 = null;
                    i2 = 0;
                }
                if (str2 != null) {
                    i = 0;
                    break;
                }
                i = i2;
                break;
            case 4:
                char charAt3 = str.charAt(0);
                if (charAt3 == 'b') {
                    str2 = "byte";
                } else if (charAt3 != 'c') {
                    if (charAt3 == 'e') {
                        char charAt4 = str.charAt(3);
                        if (charAt4 != 'e') {
                            if (charAt4 == 'm') {
                                if (str.charAt(2) == 'u') {
                                    break;
                                }
                            }
                        } else if (str.charAt(2) == 's' && str.charAt(1) == 'l') {
                            i = 114;
                            break;
                        }
                        str2 = null;
                        i2 = 0;
                    } else if (charAt3 == 'g') {
                        str2 = "goto";
                    } else if (charAt3 == 'l') {
                        str2 = DataType.LONG;
                    } else if (charAt3 != 'n') {
                        if (charAt3 == 't') {
                            char charAt5 = str.charAt(3);
                            if (charAt5 == 'e') {
                                if (str.charAt(2) == 'u' && str.charAt(1) == 'r') {
                                    i = 45;
                                    break;
                                }
                            } else if (charAt5 == 's' && str.charAt(2) == 'i' && str.charAt(1) == 'h') {
                                i = 43;
                                break;
                            }
                        } else if (charAt3 == 'v') {
                            i2 = 127;
                            str2 = "void";
                        } else if (charAt3 == 'w') {
                            i2 = 124;
                            str2 = JsonPOJOBuilder.DEFAULT_WITH_PREFIX;
                        }
                        str2 = null;
                        i2 = 0;
                    } else {
                        i2 = 42;
                        str2 = "null";
                    }
                    if (str2 != null) {
                    }
                    i = i2;
                    break;
                } else {
                    char charAt6 = str.charAt(3);
                    if (charAt6 != 'e') {
                        if (charAt6 == 'r') {
                            if (str.charAt(2) == 'a') {
                                break;
                            }
                        }
                    } else if (str.charAt(2) == 's') {
                        break;
                    }
                    str2 = null;
                    i2 = 0;
                    if (str2 != null) {
                    }
                    i = i2;
                }
                i2 = 128;
                if (str2 != null) {
                }
                i = i2;
                break;
            case 5:
                char charAt7 = str.charAt(2);
                if (charAt7 == 'a') {
                    str2 = Name.LABEL;
                } else if (charAt7 != 'e') {
                    if (charAt7 == 'i') {
                        str2 = "while";
                        i2 = 118;
                    } else if (charAt7 == 'l') {
                        i2 = 44;
                        str2 = "false";
                    } else if (charAt7 == 'r') {
                        i2 = 50;
                        str2 = "throw";
                    } else if (charAt7 != 't') {
                        switch (charAt7) {
                            case 'n':
                                char charAt8 = str.charAt(0);
                                if (charAt8 != 'c') {
                                    if (charAt8 == 'f') {
                                        str2 = "final";
                                        break;
                                    }
                                    str2 = null;
                                    i2 = 0;
                                    break;
                                } else {
                                    i2 = 155;
                                    str2 = "const";
                                    break;
                                }
                            case 'o':
                                char charAt9 = str.charAt(0);
                                if (charAt9 != 'f') {
                                    if (charAt9 == 's') {
                                        str2 = "short";
                                        break;
                                    }
                                    str2 = null;
                                    i2 = 0;
                                    break;
                                } else {
                                    str2 = "float";
                                    break;
                                }
                            case 'p':
                                str2 = "super";
                                break;
                            default:
                                str2 = null;
                                i2 = 0;
                                break;
                        }
                    } else {
                        i2 = 125;
                        str2 = "catch";
                    }
                    if (str2 != null) {
                    }
                    i = i2;
                    break;
                } else {
                    char charAt10 = str.charAt(0);
                    if (charAt10 == 'b') {
                        str2 = "break";
                        i2 = 121;
                    } else {
                        if (charAt10 == 'y') {
                            i2 = 73;
                            str2 = "yield";
                        }
                        str2 = null;
                        i2 = 0;
                    }
                    if (str2 != null) {
                    }
                    i = i2;
                }
                i2 = 128;
                if (str2 != null) {
                }
                i = i2;
                break;
            case 6:
                char charAt11 = str.charAt(1);
                if (charAt11 == 'a') {
                    str2 = "native";
                } else if (charAt11 == 'e') {
                    char charAt12 = str.charAt(0);
                    if (charAt12 == 'd') {
                        i2 = 31;
                        str2 = RequestParameters.SUBRESOURCE_DELETE;
                    } else {
                        if (charAt12 == 'r') {
                            i2 = 4;
                            str2 = "return";
                        }
                        str2 = null;
                        i2 = 0;
                    }
                    if (str2 != null) {
                    }
                    i = i2;
                } else if (charAt11 == 'h') {
                    str2 = "throws";
                } else if (charAt11 == 'm') {
                    str2 = "import";
                } else if (charAt11 == 'o') {
                    str2 = TmpConstant.TYPE_VALUE_DOUBLE;
                } else if (charAt11 == 't') {
                    str2 = "static";
                } else if (charAt11 != 'u') {
                    switch (charAt11) {
                        case 'w':
                            str2 = "switch";
                            i2 = 115;
                            break;
                        case 'x':
                            str2 = "export";
                            break;
                        case 'y':
                            i2 = 32;
                            str2 = "typeof";
                            break;
                    }
                    if (str2 != null) {
                    }
                    i = i2;
                    break;
                } else {
                    str2 = "public";
                }
                i2 = 128;
                if (str2 != null) {
                }
                i = i2;
                break;
            case 7:
                char charAt13 = str.charAt(1);
                if (charAt13 != 'a') {
                    if (charAt13 == 'e') {
                        str2 = "default";
                        i2 = 117;
                    } else if (charAt13 == 'i') {
                        i2 = 126;
                        str2 = "finally";
                    } else if (charAt13 == 'o') {
                        str2 = "boolean";
                    } else if (charAt13 != 'r') {
                        if (charAt13 == 'x') {
                            str2 = "extends";
                        }
                        str2 = null;
                        i2 = 0;
                    } else {
                        str2 = "private";
                    }
                    if (str2 != null) {
                    }
                    i = i2;
                    break;
                } else {
                    str2 = "package";
                }
                i2 = 128;
                if (str2 != null) {
                }
                i = i2;
                break;
            case 8:
                char charAt14 = str.charAt(0);
                if (charAt14 != 'a') {
                    if (charAt14 == 'f') {
                        str2 = "function";
                        i2 = 110;
                    } else if (charAt14 == 'v') {
                        str2 = "volatile";
                    } else if (charAt14 != 'c') {
                        if (charAt14 == 'd') {
                            i2 = 161;
                            str2 = "debugger";
                        }
                        str2 = null;
                        i2 = 0;
                    } else {
                        i2 = 122;
                        str2 = "continue";
                    }
                    if (str2 != null) {
                    }
                    i = i2;
                    break;
                } else {
                    str2 = "abstract";
                }
                i2 = 128;
                if (str2 != null) {
                }
                i = i2;
                break;
            case 9:
                char charAt15 = str.charAt(0);
                if (charAt15 != 'i') {
                    if (charAt15 != 'p') {
                        if (charAt15 == 't') {
                            str2 = "transient";
                        }
                        str2 = null;
                        i2 = 0;
                        if (str2 != null) {
                        }
                        i = i2;
                        break;
                    } else {
                        str2 = "protected";
                    }
                } else {
                    str2 = "interface";
                }
                i2 = 128;
                if (str2 != null) {
                }
                i = i2;
                break;
            case 10:
                char charAt16 = str.charAt(1);
                if (charAt16 == 'm') {
                    str2 = "implements";
                    i2 = 128;
                    if (str2 != null) {
                    }
                    i = i2;
                    break;
                } else {
                    if (charAt16 == 'n') {
                        i2 = 53;
                        str2 = "instanceof";
                        if (str2 != null) {
                        }
                        i = i2;
                    }
                    str2 = null;
                    i2 = 0;
                    if (str2 != null) {
                    }
                    i = i2;
                }
                break;
            case 12:
                str2 = "synchronized";
                i2 = 128;
                if (str2 != null) {
                }
                i = i2;
                break;
        }
        if (i == 0) {
            return 0;
        }
        return i & 255;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01a8, code lost:
    
        if (r17.charAt(1) == 'l') goto L193;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007a, code lost:
    
        if (r1 != 'x') goto L186;
     */
    /* JADX WARN: Removed duplicated region for block: B:188:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x026f A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int stringToKeywordForES(String str, boolean z) {
        String str2;
        int i;
        int i2 = 114;
        switch (str.length()) {
            case 2:
                char charAt = str.charAt(1);
                if (charAt == 'f') {
                    if (str.charAt(0) == 'i') {
                        i2 = 113;
                        break;
                    }
                    str2 = null;
                    i = 0;
                } else if (charAt == 'n') {
                    if (str.charAt(0) == 'i') {
                        i2 = 52;
                        break;
                    }
                    str2 = null;
                    i = 0;
                } else {
                    if (charAt == 'o' && str.charAt(0) == 'd') {
                        i2 = 119;
                        break;
                    }
                    str2 = null;
                    i = 0;
                }
                if (str2 == null && str2 != str && !str2.equals(str)) {
                    i2 = 0;
                    break;
                } else {
                    i2 = i;
                    break;
                }
                break;
            case 3:
                char charAt2 = str.charAt(0);
                if (charAt2 == 'f') {
                    if (str.charAt(2) == 'r' && str.charAt(1) == 'o') {
                        i2 = 120;
                        break;
                    }
                    str2 = null;
                    i = 0;
                } else if (charAt2 == 'l') {
                    if (str.charAt(2) == 't' && str.charAt(1) == 'e') {
                        i2 = 154;
                        break;
                    }
                    str2 = null;
                    i = 0;
                } else if (charAt2 == 'n') {
                    if (str.charAt(2) == 'w' && str.charAt(1) == 'e') {
                        i2 = 30;
                        break;
                    }
                    str2 = null;
                    i = 0;
                } else if (charAt2 == 't') {
                    if (str.charAt(2) == 'y' && str.charAt(1) == 'r') {
                        i2 = 82;
                        break;
                    }
                    str2 = null;
                    i = 0;
                } else {
                    if (charAt2 == 'v' && str.charAt(2) == 'r' && str.charAt(1) == 'a') {
                        i2 = 123;
                        break;
                    }
                    str2 = null;
                    i = 0;
                }
                if (str2 == null) {
                }
                i2 = i;
                break;
            case 4:
                char charAt3 = str.charAt(0);
                if (charAt3 == 'c') {
                    if (str.charAt(3) == 'e' && str.charAt(2) == 's' && str.charAt(1) == 'a') {
                        i2 = 116;
                        break;
                    }
                    str2 = null;
                    i = 0;
                } else if (charAt3 == 'e') {
                    char charAt4 = str.charAt(3);
                    if (charAt4 == 'e') {
                        if (str.charAt(2) == 's') {
                            break;
                        }
                        str2 = null;
                        i = 0;
                    } else {
                        if (charAt4 == 'm' && str.charAt(2) == 'u' && str.charAt(1) == 'n') {
                            i2 = 128;
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt3 != 'n') {
                    if (charAt3 == 't') {
                        char charAt5 = str.charAt(3);
                        if (charAt5 == 'e') {
                            if (str.charAt(2) == 'u' && str.charAt(1) == 'r') {
                                i2 = 45;
                                break;
                            }
                        } else if (charAt5 == 's' && str.charAt(2) == 'i' && str.charAt(1) == 'h') {
                            i2 = 43;
                            break;
                        }
                    } else if (charAt3 == 'v') {
                        i = 127;
                        str2 = "void";
                    } else if (charAt3 == 'w') {
                        i = 124;
                        str2 = JsonPOJOBuilder.DEFAULT_WITH_PREFIX;
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 42;
                    str2 = "null";
                }
                if (str2 == null) {
                }
                i2 = i;
                break;
            case 5:
                char charAt6 = str.charAt(2);
                if (charAt6 == 'a') {
                    char charAt7 = str.charAt(0);
                    if (charAt7 == 'c') {
                        str2 = Name.LABEL;
                    } else {
                        if (charAt7 == 'a') {
                            str2 = "await";
                        }
                        str2 = null;
                        i = 0;
                    }
                    i = 128;
                } else if (charAt6 == 'e') {
                    char charAt8 = str.charAt(0);
                    if (charAt8 == 'b') {
                        str2 = "break";
                        i = 121;
                    } else {
                        if (charAt8 == 'y') {
                            i = 73;
                            str2 = "yield";
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt6 == 'i') {
                    str2 = "while";
                    i = 118;
                } else if (charAt6 == 'l') {
                    i = 44;
                    str2 = "false";
                } else if (charAt6 == 'n') {
                    i = 155;
                    str2 = "const";
                } else if (charAt6 == 'p') {
                    str2 = "super";
                    i = 128;
                } else if (charAt6 != 'r') {
                    if (charAt6 == 't') {
                        i = 125;
                        str2 = "catch";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 50;
                    str2 = "throw";
                }
                if (str2 == null) {
                }
                i2 = i;
                break;
            case 6:
                char charAt9 = str.charAt(1);
                if (charAt9 != 'e') {
                    if (charAt9 == 'm') {
                        str2 = "import";
                    } else if (charAt9 != 't') {
                        if (charAt9 != 'u') {
                            switch (charAt9) {
                                case 'w':
                                    str2 = "switch";
                                    i = 115;
                                    break;
                                case 'x':
                                    str2 = "export";
                                    break;
                                case 'y':
                                    i = 32;
                                    str2 = "typeof";
                                    break;
                                default:
                                    str2 = null;
                                    i = 0;
                                    break;
                            }
                            if (str2 == null) {
                            }
                            i2 = i;
                            break;
                        }
                        if (z) {
                            str2 = "public";
                        }
                        str2 = "switch";
                        i = 115;
                        if (str2 == null) {
                        }
                        i2 = i;
                    } else {
                        if (z) {
                            str2 = "static";
                        }
                        if (z) {
                        }
                        str2 = "switch";
                        i = 115;
                        if (str2 == null) {
                        }
                        i2 = i;
                    }
                    i = 128;
                    if (str2 == null) {
                    }
                    i2 = i;
                } else {
                    char charAt10 = str.charAt(0);
                    if (charAt10 == 'd') {
                        i = 31;
                        str2 = RequestParameters.SUBRESOURCE_DELETE;
                    } else {
                        if (charAt10 == 'r') {
                            i = 4;
                            str2 = "return";
                        }
                        str2 = null;
                        i = 0;
                    }
                    if (str2 == null) {
                    }
                    i2 = i;
                }
                break;
            case 7:
                char charAt11 = str.charAt(1);
                if (charAt11 != 'a') {
                    if (charAt11 != 'e') {
                        if (charAt11 != 'i') {
                            if (charAt11 != 'r') {
                                break;
                            } else if (z) {
                                str2 = "private";
                                i = 128;
                            }
                            str2 = "extends";
                            i = 128;
                        } else {
                            i = 126;
                            str2 = "finally";
                        }
                    }
                    str2 = "default";
                    i = 117;
                } else {
                    if (z) {
                        str2 = "package";
                        i = 128;
                    }
                    str2 = "default";
                    i = 117;
                }
                if (str2 == null) {
                }
                i2 = i;
                break;
            case 8:
                char charAt12 = str.charAt(0);
                if (charAt12 == 'c') {
                    i = 122;
                    str2 = "continue";
                } else if (charAt12 != 'd') {
                    if (charAt12 == 'f') {
                        str2 = "function";
                        i = 110;
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 161;
                    str2 = "debugger";
                }
                if (str2 == null) {
                }
                i2 = i;
                break;
            case 9:
                char charAt13 = str.charAt(0);
                if (charAt13 != 'i' || !z) {
                    if (charAt13 == 'p' && z) {
                        str2 = "protected";
                    }
                    str2 = null;
                    i = 0;
                    if (str2 == null) {
                    }
                    i2 = i;
                    break;
                } else {
                    str2 = "interface";
                }
                i = 128;
                if (str2 == null) {
                }
                i2 = i;
                break;
            case 10:
                char charAt14 = str.charAt(1);
                if (charAt14 == 'm' && z) {
                    str2 = "implements";
                    i = 128;
                    if (str2 == null) {
                    }
                    i2 = i;
                } else {
                    if (charAt14 == 'n') {
                        i = 53;
                        str2 = "instanceof";
                        if (str2 == null) {
                        }
                        i2 = i;
                        break;
                    }
                    str2 = null;
                    i = 0;
                    if (str2 == null) {
                    }
                    i2 = i;
                }
                break;
        }
        if (i2 == 0) {
            return 0;
        }
        return i2 & 255;
    }

    final String getSourceString() {
        return this.sourceString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getLineno() {
        return this.lineno;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getString() {
        return this.string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char getQuoteChar() {
        return (char) this.quoteChar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final double getNumber() {
        return this.number;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isNumberBinary() {
        return this.isBinary;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isNumberOldOctal() {
        return this.isOldOctal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isNumberOctal() {
        return this.isOctal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isNumberHex() {
        return this.isHex;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean eof() {
        return this.hitEOF;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Failed to find 'out' block for switch in B:315:0x039f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:192:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0290 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x04d8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:204:0x029f -> B:198:0x0283). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final int getToken() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1500
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.getToken():int");
    }

    static boolean isJSSpace(int i) {
        return i <= 127 ? i == 32 || i == 9 || i == 12 || i == 11 : i == 160 || i == 65279 || Character.getType((char) i) == 12;
    }

    private static boolean isJSFormatChar(int i) {
        return i > 127 && Character.getType((char) i) == 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a7, code lost:
    
        ungetChar(r2);
        r5.tokenEnd = r5.cursor - 1;
        r5.string = new java.lang.String(r5.stringBuffer, 0, r5.stringBufferTop);
        r5.parser.reportError("msg.unterminated.re.lit");
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00c1, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void readRegExp(int i) throws IOException {
        int i2 = this.tokenBeg;
        this.stringBufferTop = 0;
        if (i == 101) {
            addToString(61);
        } else if (i != 24) {
            Kit.codeBug();
        }
        boolean z = false;
        while (true) {
            int i3 = getChar();
            if (i3 == 47 && !z) {
                int i4 = this.stringBufferTop;
                while (true) {
                    if (matchChar(103)) {
                        addToString(103);
                    } else if (matchChar(105)) {
                        addToString(105);
                    } else if (matchChar(109)) {
                        addToString(109);
                    } else if (!matchChar(121)) {
                        break;
                    } else {
                        addToString(121);
                    }
                }
                this.tokenEnd = i2 + this.stringBufferTop + 2;
                if (isAlpha(peekChar())) {
                    this.parser.reportError("msg.invalid.re.flag");
                }
                this.string = new String(this.stringBuffer, 0, i4);
                this.regExpFlags = new String(this.stringBuffer, i4, this.stringBufferTop - i4);
                return;
            }
            if (i3 == 10 || i3 == -1) {
                break;
            }
            if (i3 == 92) {
                addToString(i3);
                i3 = getChar();
            } else if (i3 == 91) {
                z = true;
            } else if (i3 == 93) {
                z = false;
            }
            addToString(i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String readAndClearRegExpFlags() {
        String str = this.regExpFlags;
        this.regExpFlags = null;
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isXMLAttribute() {
        return this.xmlIsAttribute;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFirstXMLToken() throws IOException {
        this.xmlOpenTagsCount = 0;
        this.xmlIsAttribute = false;
        this.xmlIsTagContent = false;
        if (!canUngetChar()) {
            return -1;
        }
        ungetChar(60);
        return getNextXMLToken();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x015d, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getNextXMLToken() throws IOException {
        this.tokenBeg = this.cursor;
        this.stringBufferTop = 0;
        while (true) {
            int i = getChar();
            if (i != -1) {
                if (this.xmlIsTagContent) {
                    if (i == 9 || i == 10 || i == 13 || i == 32) {
                        addToString(i);
                    } else if (i == 34 || i == 39) {
                        addToString(i);
                        if (!readQuotedString(i)) {
                            return -1;
                        }
                    } else if (i == 47) {
                        addToString(i);
                        if (peekChar() == 62) {
                            addToString(getChar());
                            this.xmlIsTagContent = false;
                            this.xmlOpenTagsCount--;
                        }
                    } else {
                        if (i == 123) {
                            ungetChar(i);
                            this.string = getStringFromBuffer();
                            return 146;
                        }
                        if (i == 61) {
                            addToString(i);
                            this.xmlIsAttribute = true;
                        } else if (i == 62) {
                            addToString(i);
                            this.xmlIsTagContent = false;
                            this.xmlIsAttribute = false;
                        } else {
                            addToString(i);
                            this.xmlIsAttribute = false;
                        }
                    }
                    if (!this.xmlIsTagContent && this.xmlOpenTagsCount == 0) {
                        this.string = getStringFromBuffer();
                        return 149;
                    }
                } else if (i == 60) {
                    addToString(i);
                    int peekChar = peekChar();
                    if (peekChar == 33) {
                        addToString(getChar());
                        int peekChar2 = peekChar();
                        if (peekChar2 == 45) {
                            addToString(getChar());
                            int i2 = getChar();
                            if (i2 == 45) {
                                addToString(i2);
                                if (!readXmlComment()) {
                                    return -1;
                                }
                            } else {
                                this.stringBufferTop = 0;
                                this.string = null;
                                this.parser.addError("msg.XML.bad.form");
                                return -1;
                            }
                        } else if (peekChar2 == 91) {
                            addToString(getChar());
                            if (getChar() != 67 || getChar() != 68 || getChar() != 65 || getChar() != 84 || getChar() != 65 || getChar() != 91) {
                                break;
                            }
                            addToString(67);
                            addToString(68);
                            addToString(65);
                            addToString(84);
                            addToString(65);
                            addToString(91);
                            if (!readCDATA()) {
                                return -1;
                            }
                        } else if (!readEntity()) {
                            return -1;
                        }
                    } else if (peekChar == 47) {
                        addToString(getChar());
                        int i3 = this.xmlOpenTagsCount;
                        if (i3 == 0) {
                            this.stringBufferTop = 0;
                            this.string = null;
                            this.parser.addError("msg.XML.bad.form");
                            return -1;
                        }
                        this.xmlIsTagContent = true;
                        this.xmlOpenTagsCount = i3 - 1;
                    } else if (peekChar == 63) {
                        addToString(getChar());
                        if (!readPI()) {
                            return -1;
                        }
                    } else {
                        this.xmlIsTagContent = true;
                        this.xmlOpenTagsCount++;
                    }
                } else {
                    if (i == 123) {
                        ungetChar(i);
                        this.string = getStringFromBuffer();
                        return 146;
                    }
                    addToString(i);
                }
            } else {
                this.tokenEnd = this.cursor;
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return -1;
            }
        }
    }

    private boolean readQuotedString(int i) throws IOException {
        int i2;
        do {
            i2 = getChar();
            if (i2 != -1) {
                addToString(i2);
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        } while (i2 != i);
        return true;
    }

    private boolean readXmlComment() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 45 && peekChar() == 45) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readCDATA() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 93 && peekChar() == 93) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readEntity() throws IOException {
        int i = getChar();
        int i2 = 1;
        while (i != -1) {
            addToString(i);
            if (i == 60) {
                i2++;
            } else if (i == 62 && i2 - 1 == 0) {
                return true;
            }
            i = getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readPI() throws IOException {
        while (true) {
            int i = getChar();
            if (i != -1) {
                addToString(i);
                if (i == 63 && peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        }
    }

    private String getStringFromBuffer() {
        this.tokenEnd = this.cursor;
        return new String(this.stringBuffer, 0, this.stringBufferTop);
    }

    private void addToString(int i) {
        int i2 = this.stringBufferTop;
        char[] cArr = this.stringBuffer;
        if (i2 == cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, i2);
            this.stringBuffer = cArr2;
        }
        this.stringBuffer[i2] = (char) i;
        this.stringBufferTop = i2 + 1;
    }

    private boolean canUngetChar() {
        int i = this.ungetCursor;
        return i == 0 || this.ungetBuffer[i - 1] != 10;
    }

    private void ungetChar(int i) {
        int i2 = this.ungetCursor;
        if (i2 != 0 && this.ungetBuffer[i2 - 1] == 10) {
            Kit.codeBug();
        }
        int[] iArr = this.ungetBuffer;
        int i3 = this.ungetCursor;
        this.ungetCursor = i3 + 1;
        iArr[i3] = i;
        this.cursor--;
    }

    private boolean matchChar(int i) throws IOException {
        int charIgnoreLineEnd = getCharIgnoreLineEnd();
        if (charIgnoreLineEnd == i) {
            this.tokenEnd = this.cursor;
            return true;
        }
        ungetCharIgnoreLineEnd(charIgnoreLineEnd);
        return false;
    }

    private int peekChar() throws IOException {
        int i = getChar();
        ungetChar(i);
        return i;
    }

    private int getChar() throws IOException {
        return getChar(true);
    }

    private int getChar(boolean z) throws IOException {
        char c;
        int i = this.ungetCursor;
        if (i != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i2 = i - 1;
            this.ungetCursor = i2;
            return iArr[i2];
        }
        while (true) {
            String str = this.sourceString;
            if (str != null) {
                int i3 = this.sourceCursor;
                if (i3 == this.sourceEnd) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                this.sourceCursor = i3 + 1;
                c = str.charAt(i3);
            } else {
                if (this.sourceCursor == this.sourceEnd && !fillSourceBuffer()) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                char[] cArr = this.sourceBuffer;
                int i4 = this.sourceCursor;
                this.sourceCursor = i4 + 1;
                c = cArr[i4];
            }
            int i5 = this.lineEndChar;
            if (i5 >= 0) {
                if (i5 == 13 && c == '\n') {
                    this.lineEndChar = 10;
                } else {
                    this.lineEndChar = -1;
                    this.lineStart = this.sourceCursor - 1;
                    this.lineno++;
                }
            }
            if (c <= 127) {
                if (c != '\n' && c != '\r') {
                    return c;
                }
                this.lineEndChar = c;
            } else {
                if (c == 65279) {
                    return c;
                }
                if (!z || !isJSFormatChar(c)) {
                    break;
                }
            }
        }
        if (!ScriptRuntime.isJSLineTerminator(c)) {
            return c;
        }
        this.lineEndChar = c;
        return 10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:?, code lost:
    
        return 10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int getCharIgnoreLineEnd() throws IOException {
        char c;
        int i = this.ungetCursor;
        if (i != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i2 = i - 1;
            this.ungetCursor = i2;
            return iArr[i2];
        }
        while (true) {
            String str = this.sourceString;
            if (str != null) {
                int i3 = this.sourceCursor;
                if (i3 == this.sourceEnd) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                this.sourceCursor = i3 + 1;
                c = str.charAt(i3);
            } else {
                if (this.sourceCursor == this.sourceEnd && !fillSourceBuffer()) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                char[] cArr = this.sourceBuffer;
                int i4 = this.sourceCursor;
                this.sourceCursor = i4 + 1;
                c = cArr[i4];
            }
            if (c <= 127) {
                if (c != '\n' && c != '\r') {
                    return c;
                }
                this.lineEndChar = c;
            } else {
                if (c == 65279) {
                    return c;
                }
                if (!isJSFormatChar(c)) {
                    if (!ScriptRuntime.isJSLineTerminator(c)) {
                        return c;
                    }
                    this.lineEndChar = c;
                }
            }
        }
    }

    private void ungetCharIgnoreLineEnd(int i) {
        int[] iArr = this.ungetBuffer;
        int i2 = this.ungetCursor;
        this.ungetCursor = i2 + 1;
        iArr[i2] = i;
        this.cursor--;
    }

    private void skipLine() throws IOException {
        int i;
        do {
            i = getChar();
            if (i == -1) {
                break;
            }
        } while (i != 10);
        ungetChar(i);
        this.tokenEnd = this.cursor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getOffset() {
        int i = this.sourceCursor - this.lineStart;
        return this.lineEndChar >= 0 ? i - 1 : i;
    }

    private final int charAt(int i) {
        if (i < 0) {
            return -1;
        }
        String str = this.sourceString;
        if (str != null) {
            if (i >= this.sourceEnd) {
                return -1;
            }
            return str.charAt(i);
        }
        if (i >= this.sourceEnd) {
            int i2 = this.sourceCursor;
            try {
                if (!fillSourceBuffer()) {
                    return -1;
                }
                i -= i2 - this.sourceCursor;
            } catch (IOException unused) {
                return -1;
            }
        }
        return this.sourceBuffer[i];
    }

    private final String substring(int i, int i2) {
        String str = this.sourceString;
        if (str != null) {
            return str.substring(i, i2);
        }
        return new String(this.sourceBuffer, i, i2 - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getLine() {
        int i;
        int i2 = this.sourceCursor;
        int i3 = this.lineEndChar;
        if (i3 >= 0) {
            i = i2 - 1;
            if (i3 == 10 && charAt(i - 1) == 13) {
                i--;
            }
        } else {
            int i4 = i2 - this.lineStart;
            while (true) {
                int charAt = charAt(this.lineStart + i4);
                if (charAt == -1 || ScriptRuntime.isJSLineTerminator(charAt)) {
                    break;
                }
                i4++;
            }
            i = i4 + this.lineStart;
        }
        return substring(this.lineStart, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getLine(int i, int[] iArr) {
        int i2 = (this.cursor + this.ungetCursor) - i;
        int i3 = this.sourceCursor;
        if (i2 > i3) {
            return null;
        }
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            int charAt = charAt(i3 - 1);
            if (ScriptRuntime.isJSLineTerminator(charAt)) {
                if (charAt == 10 && charAt(i3 - 2) == 13) {
                    i2--;
                    i3--;
                }
                i4++;
                i5 = i3 - 1;
            }
            i2--;
            i3--;
        }
        int i6 = 0;
        while (true) {
            if (i3 <= 0) {
                i3 = 0;
                break;
            }
            if (ScriptRuntime.isJSLineTerminator(charAt(i3 - 1))) {
                break;
            }
            i3--;
            i6++;
        }
        iArr[0] = (this.lineno - i4) + (this.lineEndChar >= 0 ? 1 : 0);
        iArr[1] = i6;
        if (i4 == 0) {
            return getLine();
        }
        return substring(i3, i5);
    }

    private boolean fillSourceBuffer() throws IOException {
        if (this.sourceString != null) {
            Kit.codeBug();
        }
        if (this.sourceEnd == this.sourceBuffer.length) {
            if (this.lineStart != 0 && !isMarkingComment()) {
                char[] cArr = this.sourceBuffer;
                int i = this.lineStart;
                System.arraycopy(cArr, i, cArr, 0, this.sourceEnd - i);
                int i2 = this.sourceEnd;
                int i3 = this.lineStart;
                this.sourceEnd = i2 - i3;
                this.sourceCursor -= i3;
                this.lineStart = 0;
            } else {
                char[] cArr2 = this.sourceBuffer;
                char[] cArr3 = new char[cArr2.length * 2];
                System.arraycopy(cArr2, 0, cArr3, 0, this.sourceEnd);
                this.sourceBuffer = cArr3;
            }
        }
        Reader reader = this.sourceReader;
        char[] cArr4 = this.sourceBuffer;
        int i4 = this.sourceEnd;
        int read = reader.read(cArr4, i4, cArr4.length - i4);
        if (read < 0) {
            return false;
        }
        this.sourceEnd += read;
        return true;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getTokenBeg() {
        return this.tokenBeg;
    }

    public int getTokenEnd() {
        return this.tokenEnd;
    }

    public int getTokenLength() {
        return this.tokenEnd - this.tokenBeg;
    }

    public Token.CommentType getCommentType() {
        return this.commentType;
    }

    private void markCommentStart() {
        markCommentStart("");
    }

    private void markCommentStart(String str) {
        if (!this.parser.compilerEnv.isRecordingComments() || this.sourceReader == null) {
            return;
        }
        this.commentPrefix = str;
        this.commentCursor = this.sourceCursor - 1;
    }

    private boolean isMarkingComment() {
        return this.commentCursor != -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getAndResetCurrentComment() {
        if (this.sourceString != null) {
            if (isMarkingComment()) {
                Kit.codeBug();
            }
            return this.sourceString.substring(this.tokenBeg, this.tokenEnd);
        }
        if (!isMarkingComment()) {
            Kit.codeBug();
        }
        StringBuilder sb = new StringBuilder(this.commentPrefix);
        sb.append(this.sourceBuffer, this.commentCursor, getTokenLength() - this.commentPrefix.length());
        this.commentCursor = -1;
        return sb.toString();
    }

    private String convertLastCharToHex(String str) {
        int length = str.length() - 1;
        StringBuilder sb = new StringBuilder(str.substring(0, length));
        sb.append("\\u");
        String hexString = Integer.toHexString(str.charAt(length));
        for (int i = 0; i < 4 - hexString.length(); i++) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }
}
