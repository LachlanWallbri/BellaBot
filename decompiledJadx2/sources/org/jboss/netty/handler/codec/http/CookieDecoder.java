package org.jboss.netty.handler.codec.http;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes7.dex */
public class CookieDecoder {
    private static final String COMMA = ",";

    public CookieDecoder() {
    }

    @Deprecated
    public CookieDecoder(boolean z) {
    }

    public Set<Cookie> decode(String str) {
        int i;
        int i2;
        int i3;
        ArrayList arrayList;
        ArrayList arrayList2;
        boolean z;
        int i4;
        ArrayList arrayList3 = new ArrayList(8);
        ArrayList arrayList4 = new ArrayList(8);
        extractKeyValuePairs(str, arrayList3, arrayList4);
        if (arrayList3.isEmpty()) {
            return Collections.emptySet();
        }
        boolean z2 = false;
        if (((String) arrayList3.get(0)).equalsIgnoreCase(JsonDocumentFields.VERSION)) {
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
            DefaultCookie defaultCookie = new DefaultCookie(str2, str3);
            ArrayList arrayList5 = new ArrayList(2);
            int i5 = i3 + 1;
            int i6 = i3;
            int i7 = i2;
            TreeSet treeSet2 = treeSet;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            boolean z3 = z2;
            boolean z4 = z3;
            int i8 = -1;
            boolean z5 = z4;
            String str7 = null;
            while (true) {
                if (i5 >= arrayList3.size()) {
                    arrayList = arrayList3;
                    arrayList2 = arrayList4;
                    z = z3;
                    break;
                }
                String str8 = (String) arrayList3.get(i5);
                arrayList = arrayList3;
                String str9 = (String) arrayList4.get(i5);
                arrayList2 = arrayList4;
                if ("Discard".equalsIgnoreCase(str8)) {
                    z3 = true;
                } else if ("Secure".equalsIgnoreCase(str8)) {
                    z4 = true;
                } else if ("HTTPOnly".equalsIgnoreCase(str8)) {
                    z5 = true;
                } else if ("Comment".equalsIgnoreCase(str8)) {
                    str4 = str9;
                } else if ("CommentURL".equalsIgnoreCase(str8)) {
                    str5 = str9;
                } else if ("Domain".equalsIgnoreCase(str8)) {
                    str6 = str9;
                } else if ("Path".equalsIgnoreCase(str8)) {
                    str7 = str9;
                } else {
                    if ("Expires".equalsIgnoreCase(str8)) {
                        try {
                            long time = new CookieDateFormat().parse(str9).getTime() - System.currentTimeMillis();
                            if (time <= 0) {
                                z = z3;
                                i4 = 0;
                            } else {
                                z = z3;
                                try {
                                    i4 = ((int) (time / 1000)) + (time % 1000 != 0 ? 1 : 0);
                                } catch (ParseException unused2) {
                                }
                            }
                        } catch (ParseException unused3) {
                            z = z3;
                        }
                    } else {
                        z = z3;
                        if ("Max-Age".equalsIgnoreCase(str8)) {
                            i4 = Integer.parseInt(str9);
                        } else {
                            if (JsonDocumentFields.VERSION.equalsIgnoreCase(str8)) {
                                i7 = Integer.parseInt(str9);
                            } else {
                                if (!"Port".equalsIgnoreCase(str8)) {
                                    break;
                                }
                                for (String str10 : str9.split(COMMA)) {
                                    try {
                                        arrayList5.add(Integer.valueOf(str10));
                                    } catch (NumberFormatException unused4) {
                                    }
                                }
                            }
                            z3 = z;
                        }
                    }
                    i8 = i4;
                    z3 = z;
                }
                i5++;
                i6++;
                arrayList4 = arrayList2;
                arrayList3 = arrayList;
            }
            defaultCookie.setVersion(i7);
            defaultCookie.setMaxAge(i8);
            defaultCookie.setPath(str7);
            defaultCookie.setDomain(str6);
            defaultCookie.setSecure(z4);
            defaultCookie.setHttpOnly(z5);
            if (i7 > 0) {
                defaultCookie.setComment(str4);
            }
            if (i7 > 1) {
                defaultCookie.setCommentUrl(str5);
                defaultCookie.setPorts(arrayList5);
                defaultCookie.setDiscard(z);
            }
            treeSet2.add(defaultCookie);
            i3 = i6 + 1;
            treeSet = treeSet2;
            i2 = i7;
            arrayList4 = arrayList2;
            arrayList3 = arrayList;
            z2 = false;
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
}
