package com.iflytek.cloud.thirdparty;

/* renamed from: com.iflytek.cloud.thirdparty.bb */
/* loaded from: classes3.dex */
public class C3717bb {

    /* renamed from: a */
    public static final String[] f3159a = {"134", "135", "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "182", "187", "188"};

    /* renamed from: b */
    public static final String[] f3160b = {"130", "131", "132", "155", "156", "185", "186"};

    /* renamed from: c */
    public static final String[] f3161c = {"133", "153", "180", "189"};

    /* renamed from: a */
    public static String m2022a(String str) {
        if (str == null) {
            return str;
        }
        String replaceAll = str.replaceAll(" ", "").replaceAll("-", "");
        StringBuilder sb = new StringBuilder(replaceAll);
        if (sb.length() <= 5) {
            return replaceAll;
        }
        if (sb.substring(0, 3).equals("+86") || sb.substring(0, 3).equals("086")) {
            return sb.substring(3, sb.length());
        }
        if (sb.substring(0, 2).equals("86")) {
            return sb.substring(2, sb.length());
        }
        return (sb.substring(0, 5).equals("12530") || sb.substring(0, 5).equals("12520") || sb.substring(0, 5).equals("17951") || sb.substring(0, 5).equals("17911") || sb.subSequence(0, 5).equals("12593")) ? sb.substring(5, sb.length()) : replaceAll;
    }

    /* renamed from: a */
    public static String m2023a(String[] strArr, char c) {
        StringBuilder sb = new StringBuilder();
        if (strArr != null) {
            for (String str : strArr) {
                if (str != null) {
                    sb.append(str);
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
