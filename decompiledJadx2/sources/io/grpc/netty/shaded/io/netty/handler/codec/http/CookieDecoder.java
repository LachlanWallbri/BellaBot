package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.handler.codec.DateFormatter;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public final class CookieDecoder {
    private static final String COMMENT = "Comment";
    private static final String COMMENTURL = "CommentURL";
    private static final String DISCARD = "Discard";
    private static final String PORT = "Port";
    private static final String VERSION = "Version";
    private final InternalLogger logger = InternalLoggerFactory.getInstance(getClass());
    private final boolean strict;
    private static final CookieDecoder STRICT = new CookieDecoder(true);
    private static final CookieDecoder LAX = new CookieDecoder(false);

    public static Set<Cookie> decode(String str) {
        return decode(str, true);
    }

    public static Set<Cookie> decode(String str, boolean z) {
        return (z ? STRICT : LAX).doDecode(str);
    }

    private Set<Cookie> doDecode(String str) {
        int i;
        int i2;
        int i3;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList(8);
        ArrayList arrayList4 = new ArrayList(8);
        extractKeyValuePairs(str, arrayList3, arrayList4);
        if (arrayList3.isEmpty()) {
            return Collections.emptySet();
        }
        boolean z = false;
        if (((String) arrayList3.get(0)).equalsIgnoreCase("Version")) {
            try {
                i = Integer.parseInt((String) arrayList4.get(0));
            } catch (NumberFormatException unused) {
                i = 0;
            }
            i2 = i;
            i3 = 1;
        } else {
            i3 = 0;
            i2 = 0;
        }
        if (arrayList3.size() <= i3) {
            return Collections.emptySet();
        }
        TreeSet treeSet = new TreeSet();
        while (i3 < arrayList3.size()) {
            String str2 = (String) arrayList3.get(i3);
            String str3 = (String) arrayList4.get(i3);
            if (str3 == null) {
                str3 = "";
            }
            DefaultCookie initCookie = initCookie(str2, str3);
            if (initCookie == null) {
                break;
            }
            ArrayList arrayList5 = new ArrayList(2);
            int i4 = i3 + 1;
            int i5 = i3;
            TreeSet treeSet2 = treeSet;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            boolean z2 = z;
            long j = Long.MIN_VALUE;
            boolean z3 = z2;
            boolean z4 = z3;
            String str7 = null;
            while (true) {
                if (i4 >= arrayList3.size()) {
                    arrayList = arrayList3;
                    arrayList2 = arrayList4;
                    break;
                }
                String str8 = (String) arrayList3.get(i4);
                arrayList = arrayList3;
                String str9 = (String) arrayList4.get(i4);
                arrayList2 = arrayList4;
                if (!DISCARD.equalsIgnoreCase(str8)) {
                    if (!"Secure".equalsIgnoreCase(str8)) {
                        if (!"HTTPOnly".equalsIgnoreCase(str8)) {
                            if (!COMMENT.equalsIgnoreCase(str8)) {
                                if (!COMMENTURL.equalsIgnoreCase(str8)) {
                                    if (!"Domain".equalsIgnoreCase(str8)) {
                                        if (!"Path".equalsIgnoreCase(str8)) {
                                            if ("Expires".equalsIgnoreCase(str8)) {
                                                Date parseHttpDate = DateFormatter.parseHttpDate(str9);
                                                if (parseHttpDate != null) {
                                                    long time = parseHttpDate.getTime() - System.currentTimeMillis();
                                                    j = (time / 1000) + (time % 1000 != 0 ? 1 : 0);
                                                }
                                            } else if ("Max-Age".equalsIgnoreCase(str8)) {
                                                j = Integer.parseInt(str9);
                                            } else if ("Version".equalsIgnoreCase(str8)) {
                                                i2 = Integer.parseInt(str9);
                                            } else {
                                                if (!PORT.equalsIgnoreCase(str8)) {
                                                    break;
                                                }
                                                String[] split = str9.split(",");
                                                int length = split.length;
                                                int i6 = 0;
                                                while (i6 < length) {
                                                    String[] strArr = split;
                                                    try {
                                                        arrayList5.add(Integer.valueOf(split[i6]));
                                                    } catch (NumberFormatException unused2) {
                                                    }
                                                    i6++;
                                                    split = strArr;
                                                }
                                            }
                                        } else {
                                            str5 = str9;
                                        }
                                    } else {
                                        str4 = str9;
                                    }
                                } else {
                                    str6 = str9;
                                }
                            } else {
                                str7 = str9;
                            }
                        } else {
                            z4 = true;
                        }
                    } else {
                        z3 = true;
                    }
                } else {
                    z2 = true;
                }
                i4++;
                i5++;
                arrayList4 = arrayList2;
                arrayList3 = arrayList;
            }
            initCookie.setVersion(i2);
            initCookie.setMaxAge(j);
            initCookie.setPath(str5);
            initCookie.setDomain(str4);
            initCookie.setSecure(z3);
            initCookie.setHttpOnly(z4);
            if (i2 > 0) {
                initCookie.setComment(str7);
            }
            if (i2 > 1) {
                initCookie.setCommentUrl(str6);
                initCookie.setPorts(arrayList5);
                initCookie.setDiscard(z2);
            }
            treeSet2.add(initCookie);
            i3 = i5 + 1;
            treeSet = treeSet2;
            arrayList4 = arrayList2;
            arrayList3 = arrayList;
            z = false;
        }
        return treeSet;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0019. Please report as an issue. */
    private static void extractKeyValuePairs(String str, List<String> list, List<String> list2) {
        String substring;
        String str2;
        int length = str.length();
        int i = 0;
        while (i != length) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != ',' && charAt != ';') {
                switch (charAt) {
                    case '\t':
                    case '\n':
                    case 11:
                    case '\f':
                    case '\r':
                        break;
                    default:
                        while (i != length) {
                            if (str.charAt(i) == '$') {
                                i++;
                            } else {
                                String str3 = null;
                                if (i == length) {
                                    str2 = null;
                                } else {
                                    int i2 = i;
                                    while (true) {
                                        char charAt2 = str.charAt(i2);
                                        if (charAt2 == ';') {
                                            substring = str.substring(i, i2);
                                        } else if (charAt2 != '=') {
                                            i2++;
                                            if (i2 == length) {
                                                substring = str.substring(i);
                                            }
                                        } else {
                                            str2 = str.substring(i, i2);
                                            i2++;
                                            if (i2 == length) {
                                                str3 = "";
                                            } else {
                                                char charAt3 = str.charAt(i2);
                                                if (charAt3 == '\"' || charAt3 == '\'') {
                                                    StringBuilder sb = new StringBuilder(str.length() - i2);
                                                    i2++;
                                                    while (true) {
                                                        boolean z = false;
                                                        while (i2 != length) {
                                                            if (z) {
                                                                int i3 = i2 + 1;
                                                                char charAt4 = str.charAt(i2);
                                                                if (charAt4 == '\"' || charAt4 == '\'' || charAt4 == '\\') {
                                                                    sb.setCharAt(sb.length() - 1, charAt4);
                                                                } else {
                                                                    sb.append(charAt4);
                                                                }
                                                                i2 = i3;
                                                            } else {
                                                                int i4 = i2 + 1;
                                                                char charAt5 = str.charAt(i2);
                                                                if (charAt5 == charAt3) {
                                                                    str3 = sb.toString();
                                                                    i = i4;
                                                                } else {
                                                                    sb.append(charAt5);
                                                                    if (charAt5 == '\\') {
                                                                        z = true;
                                                                    }
                                                                    i2 = i4;
                                                                }
                                                            }
                                                        }
                                                        str3 = sb.toString();
                                                    }
                                                } else {
                                                    i = str.indexOf(59, i2);
                                                    if (i > 0) {
                                                        str3 = str.substring(i2, i);
                                                    } else {
                                                        str3 = str.substring(i2);
                                                        i = length;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    str2 = substring;
                                    i = i2;
                                }
                                list.add(str2);
                                list2.add(str3);
                            }
                        }
                        return;
                }
            }
            i++;
        }
    }

    private CookieDecoder(boolean z) {
        this.strict = z;
    }

    private DefaultCookie initCookie(String str, String str2) {
        int firstInvalidCookieValueOctet;
        int firstInvalidCookieNameOctet;
        if (str == null || str.length() == 0) {
            this.logger.debug("Skipping cookie with null name");
            return null;
        }
        if (str2 == null) {
            this.logger.debug("Skipping cookie with null value");
            return null;
        }
        CharSequence unwrapValue = CookieUtil.unwrapValue(str2);
        if (unwrapValue == null) {
            this.logger.debug("Skipping cookie because starting quotes are not properly balanced in '{}'", unwrapValue);
            return null;
        }
        if (this.strict && (firstInvalidCookieNameOctet = CookieUtil.firstInvalidCookieNameOctet(str)) >= 0) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Skipping cookie because name '{}' contains invalid char '{}'", str, Character.valueOf(str.charAt(firstInvalidCookieNameOctet)));
            }
            return null;
        }
        boolean z = unwrapValue.length() != str2.length();
        if (this.strict && (firstInvalidCookieValueOctet = CookieUtil.firstInvalidCookieValueOctet(unwrapValue)) >= 0) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Skipping cookie because value '{}' contains invalid char '{}'", unwrapValue, Character.valueOf(unwrapValue.charAt(firstInvalidCookieValueOctet)));
            }
            return null;
        }
        DefaultCookie defaultCookie = new DefaultCookie(str, unwrapValue.toString());
        defaultCookie.setWrap(z);
        return defaultCookie;
    }
}
