package com.aliyun.alink.dm.p010d;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DMConstants.java */
/* renamed from: com.aliyun.alink.dm.d.a */
/* loaded from: classes.dex */
public class C0852a {

    /* renamed from: a */
    public static String f372a = "/sys/{productKey}/{deviceName}/thing/config/push";

    /* renamed from: b */
    public static String f373b = "/sys/{productKey}/{deviceName}/thing/config/get";

    /* renamed from: c */
    public static String f374c = "/sys/{productKey}/{deviceName}/thing/deviceinfo/update";

    /* renamed from: d */
    public static String f375d = "/sys/{productKey}/{deviceName}/thing/deviceinfo/delete";

    /* renamed from: e */
    public static String f376e = "/sys/{productKey}/{deviceName}/thing/enable";

    /* renamed from: f */
    public static String f377f = "/sys/{productKey}/{deviceName}/thing/disable";

    /* renamed from: g */
    public static String f378g = "/sys/{productKey}/{deviceName}/thing/delete";

    /* renamed from: h */
    public static String f379h = "/shadow/update/{productKey}/{deviceName}";

    /* renamed from: i */
    public static String f380i = "/shadow/get/{productKey}/{deviceName}";

    /* renamed from: j */
    public static String f381j = "/sys/{productKey}/{deviceName}/thing/dsltemplate/get";

    /* renamed from: k */
    public static String f382k = "/sys/{productKey}/{deviceName}/thing/dsltemplate/get_reply";

    /* renamed from: l */
    public static String f383l = "/sys/{productKey}/{deviceName}/thing/awss/enrollee/match";

    /* renamed from: m */
    public static String f384m = "thing.awss.enrollee.match";

    /* renamed from: n */
    public static String f385n = "thing.dsltemplate.get";

    /* renamed from: o */
    public static String f386o = "/sys/{productKey}/{deviceName}/thing/reset";

    /* renamed from: p */
    public static String f387p = "thing.reset";

    /* renamed from: x */
    public static String f395x = "control";

    /* renamed from: y */
    public static String f396y = "reply";

    /* renamed from: q */
    public static String f388q = "{id}";

    /* renamed from: r */
    public static String f389r = "{productKey}";

    /* renamed from: s */
    public static String f390s = "{deviceName}";

    /* renamed from: t */
    public static String f391t = "{mac}";

    /* renamed from: u */
    public static String f392u = "{ip}";

    /* renamed from: v */
    public static String f393v = "{token}";

    /* renamed from: w */
    public static String f394w = "{remainTime}";

    /* renamed from: z */
    public static String f397z = "{\"id\":\"" + f388q + "\",\"version\":\"1.0\",\"method\":\"device.info.notify\",\"params\":{\"awssVer\":{\"smartconfig\":\"2.0\",\"zconfig\":\"2.0\",\"router\":\"2.0\",\"ap\":\"2.0\"},\"productKey\":\"" + f389r + "\",\"deviceName\":\"" + f390s + "\",\"mac\":\"" + f391t + "\",\"ip\":\"" + f392u + "\",\"cipherType\":4,\"token\":\"" + f393v + "\",\"remainTime\":" + f394w + ",\"type\":0}}";

    /* renamed from: A */
    public static String f370A = "{\"id\":\"" + f388q + "\",\"code\":\"200\",\"data\":{\"awssVer\":{\"smartconfig\":\"2.0\",\"zconfig\":\"2.0\",\"router\":\"2.0\",\"ap\":\"2.0\"},\"productKey\":\"" + f389r + "\",\"deviceName\":\"" + f390s + "\",\"mac\":\"" + f391t + "\",\"ip\":\"" + f392u + "\",\"cipherType\":4,\"token\":\"" + f393v + "\",\"remainTime\":" + f394w + ",\"type\":0}}";

    /* renamed from: B */
    public static String f371B = "/sys/{productKey}/{deviceName}/device/info/get";
}
