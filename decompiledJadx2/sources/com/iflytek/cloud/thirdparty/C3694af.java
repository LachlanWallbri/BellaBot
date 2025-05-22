package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.Version;
import com.iflytek.cloud.msc.util.AppInfoUtil;
import com.iflytek.cloud.msc.util.Config;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.UniqueIDUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* renamed from: com.iflytek.cloud.thirdparty.af */
/* loaded from: classes3.dex */
public class C3694af {

    /* renamed from: a */
    private static String f2986a = "xiaoyan";

    /* renamed from: b */
    private static String f2987b;

    /* renamed from: a */
    public static String m1838a(Context context, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError {
        if (context == null) {
            throw new SpeechError(20012);
        }
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        f2987b = clone.m1827b(SpeechConstant.NET_TYPE, f2987b);
        m1840a(context, clone);
        clone.m1823a("timeout", "20000", false);
        clone.m1823a("auth", "1", false);
        clone.m1822a("msc.ver", Version.getVersion());
        clone.m1823a("mac", AppInfoUtil.getAppInfo(context).m1833e(AppInfoUtil.NET_MAC), false);
        clone.m1823a("dvc", m1837a(context), false);
        clone.m1822a("unique_id", UniqueIDUtil.getUniqueID(context));
        clone.m1823a(Config.KEY_LATITUDE, "" + Config.getConfig(context).getLocation(Config.KEY_LATITUDE), false);
        clone.m1823a(Config.KEY_LONGITUDE, "" + Config.getConfig(context).getLocation(Config.KEY_LONGITUDE), false);
        clone.m1819a(AppInfoUtil.getShortAppInfo(context));
        m1841a(clone);
        m1846b(context, clone);
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: a */
    private static void m1841a(C3692ad c3692ad) {
        if (c3692ad == null || Setting.getLogLevel() == Setting.LOG_LEVEL.none) {
            return;
        }
        String logPath = Setting.getLogPath();
        if (TextUtils.isEmpty(logPath)) {
            logPath = "/sdcard/msc/msc.log";
        }
        int i = -1;
        if (Setting.getLogLevel() == Setting.LOG_LEVEL.detail) {
            i = 31;
        } else if (Setting.getLogLevel() == Setting.LOG_LEVEL.normal) {
            i = 15;
        } else if (Setting.getLogLevel() == Setting.LOG_LEVEL.low) {
            i = 7;
        }
        FileUtil.makeDir(logPath);
        c3692ad.m1822a("log", logPath);
        c3692ad.m1822a("lvl", "" + i);
        c3692ad.m1823a("output", "1", false);
    }

    /* renamed from: a */
    public static void m1840a(Context context, C3692ad c3692ad) {
        if (TextUtils.isEmpty(c3692ad.m1833e(SpeechConstant.NET_TYPE)) && !TextUtils.isEmpty(f2987b)) {
            c3692ad.m1823a(SpeechConstant.NET_TYPE, f2987b, false);
            return;
        }
        if (context == null) {
            c3692ad.m1823a(SpeechConstant.NET_TYPE, "none", false);
            return;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                c3692ad.m1823a(SpeechConstant.NET_TYPE, "none", false);
            } else {
                c3692ad.m1823a(SpeechConstant.NET_TYPE, NetworkUtil.getNetType(activeNetworkInfo), false);
                c3692ad.m1823a("net_subtype", C3692ad.m1815f(NetworkUtil.getNetSubType(activeNetworkInfo)), false);
            }
        } catch (SecurityException e) {
            DebugLog.LogI("appendNetProxyParam exceptoin: " + e.getLocalizedMessage());
        } catch (Throwable th) {
            DebugLog.LogE(th);
        }
    }

    /* renamed from: b */
    public static void m1846b(Context context, C3692ad c3692ad) {
        int lac;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (context != null && Config.hasGPSPermission(context)) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                int phoneType = telephonyManager.getPhoneType();
                String networkOperator = telephonyManager.getNetworkOperator();
                int i = 0;
                int parseInt = Integer.parseInt(networkOperator.substring(0, 3));
                int parseInt2 = Integer.parseInt(networkOperator.substring(3));
                if (phoneType == 1) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                    i = gsmCellLocation.getCid();
                    lac = gsmCellLocation.getLac();
                } else if (phoneType != 2) {
                    lac = 0;
                } else {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                    i = cdmaCellLocation.getBaseStationId();
                    lac = cdmaCellLocation.getNetworkId();
                }
                c3692ad.m1822a("mmlc", parseInt + "|" + parseInt2 + "|" + lac + "|" + i);
                DebugLog.LogS("MCC = " + parseInt + "\t MNC = " + parseInt2 + "\t phoneType = " + phoneType + "\t LAC = " + lac + "\t CID = " + i);
            } catch (Exception unused) {
                DebugLog.LogS("get mmlc failed");
            }
            DebugLog.LogS("get mmlc time cost:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    /* renamed from: c */
    public static void m1850c(Context context, C3692ad c3692ad) {
        c3692ad.m1822a("act_name", AppInfoUtil.getActivityMsg(context));
    }

    /* renamed from: a */
    public static String m1839a(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) {
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        clone.m1829c(SpeechConstant.CLOUD_GRAMMAR);
        m1840a(context, clone);
        m1846b(context, clone);
        m1850c(context, clone);
        clone.m1823a("language", "zh_cn", false);
        clone.m1823a("accent", "mandarin", false);
        clone.m1823a(SpeechConstant.RESULT_TYPE, "json", false);
        clone.m1823a("rse", abstractHandlerC3740s.getResultEncoding(), false);
        clone.m1823a(SpeechConstant.TEXT_ENCODING, abstractHandlerC3740s.getTextEncoding(), false);
        clone.m1823a("ssm", "1", false);
        clone.m1823a("msc.skin", "0", false);
        if (TextUtils.isEmpty(str)) {
            clone.m1823a(SpeechConstant.SUBJECT, InternalConstant.SUB_IAT, false);
        } else {
            clone.m1823a(SpeechConstant.SUBJECT, "asr", false);
        }
        int sampleRate = abstractHandlerC3740s.getSampleRate();
        clone.m1823a("auf=audio/L16;rate", Integer.toString(sampleRate), false);
        if (sampleRate == 16000) {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX_WB, false);
        } else {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX, false);
        }
        clone.m1823a("vad_bos", Integer.toString(m1835a(abstractHandlerC3740s)), false);
        clone.m1823a("vad_eos", Integer.toString(m1843b(abstractHandlerC3740s)), false);
        if (clone.m1825a(SpeechConstant.DVC_INFO, false)) {
            m1840a(context, clone);
            clone.m1823a("dvc", m1837a(context), false);
            clone.m1822a("unique_id", UniqueIDUtil.getUniqueID(context));
            clone.m1823a(Config.KEY_LATITUDE, "" + Config.getConfig(context).getLocation(Config.KEY_LATITUDE), false);
            clone.m1823a(Config.KEY_LONGITUDE, "" + Config.getConfig(context).getLocation(Config.KEY_LONGITUDE), false);
            String checkAndReplaceIllegalSymbols = AppInfoUtil.checkAndReplaceIllegalSymbols(AppInfoUtil.getBrowserUserAgent(context));
            String deviceType = AppInfoUtil.getDeviceType(context);
            clone.m1823a(AppInfoUtil.BROWSER_UA, checkAndReplaceIllegalSymbols, false);
            clone.m1823a(AppInfoUtil.DVC_TYPE, deviceType, false);
            clone.m1819a(AppInfoUtil.getShortAppInfo(context));
        }
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: b */
    public static String m1845b(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) {
        if (str.equals(AIUIConstant.INTERACT_MODE_ONESHOT)) {
            return m1839a(context, abstractHandlerC3740s.getParam().m1833e(SpeechConstant.CLOUD_GRAMMAR), abstractHandlerC3740s);
        }
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        int m1816a = clone.m1816a(SpeechConstant.IVW_NET_MODE, 0);
        if (m1816a == 0) {
            clone.m1823a(SpeechConstant.IVW_NET_MODE, "0", true);
        } else if (2 == m1816a || 1 == m1816a) {
            clone.m1823a(SpeechConstant.IVW_NET_MODE, "1", true);
        } else if (NetworkUtil.isWifiConnect(context)) {
            clone.m1823a(SpeechConstant.IVW_NET_MODE, "1", true);
        } else {
            clone.m1823a(SpeechConstant.IVW_NET_MODE, "0", true);
        }
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: c */
    public static String m1849c(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) {
        C3692ad param = abstractHandlerC3740s.getParam();
        m1840a(context, param);
        m1846b(context, param);
        param.m1823a(SpeechConstant.RESULT_TYPE, "json", false);
        param.m1823a("rse", "gb2312", false);
        param.m1823a(SpeechConstant.TEXT_ENCODING, abstractHandlerC3740s.getTextEncoding(), false);
        param.m1823a("vad_enable", "false", false);
        param.m1823a("ssm", "1", false);
        param.m1823a("sub", "ist", false);
        int sampleRate = abstractHandlerC3740s.getSampleRate();
        param.m1823a("auf=audio/L16;rate", Integer.toString(sampleRate), false);
        if (sampleRate == 16000) {
            param.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX_WB, false);
        } else {
            param.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX, false);
        }
        return param.toString();
    }

    /* renamed from: b */
    public static String m1844b(Context context, AbstractHandlerC3740s abstractHandlerC3740s) {
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        m1840a(context, clone);
        m1846b(context, clone);
        clone.m1822a(SpeechConstant.RESULT_TYPE, "json");
        clone.m1822a("rse", abstractHandlerC3740s.getResultEncoding());
        clone.m1822a(SpeechConstant.TEXT_ENCODING, abstractHandlerC3740s.getTextEncoding());
        clone.m1823a("ssm", "1", false);
        clone.m1823a(SpeechConstant.SUBJECT, SpeechConstant.ENG_IVP, false);
        int sampleRate = abstractHandlerC3740s.getSampleRate();
        clone.m1823a("auf=audio/L16;rate", Integer.toString(sampleRate), false);
        if (sampleRate == 16000) {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, "speex-wb;10", false);
        } else {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX, false);
        }
        clone.m1823a("vad_bos", "3000", false);
        clone.m1823a("vad_eos", "700", false);
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: d */
    public static String m1851d(Context context, C3692ad c3692ad) {
        C3692ad clone = c3692ad.clone();
        m1850c(context, clone);
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null) {
            clone.m1822a("appid", utility.getParameter("appid"));
        }
        clone.m1819a(AppInfoUtil.getShortAppInfo(context));
        clone.m1823a("dvc", m1837a(context), false);
        clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, "raw", false);
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: e */
    public static String m1854e(Context context, C3692ad c3692ad) {
        C3692ad clone = c3692ad.clone();
        clone.m1822a("appid", SpeechUtility.getUtility().getParameter("appid"));
        clone.m1819a(AppInfoUtil.getShortAppInfo(context));
        clone.m1823a("dvc", m1837a(context), false);
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: d */
    public static String m1853d(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) {
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        m1840a(context, clone);
        m1846b(context, clone);
        clone.m1823a("sub", "mfv", false);
        clone.m1823a("prot_ver", "50", false);
        clone.m1823a("mver", "2.0", false);
        if ("verify".equals(clone.m1833e("sst"))) {
            clone.m1823a("scene_mode", "vfy", false);
        } else {
            clone.m1823a("scene_mode", "gen", false);
        }
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: c */
    public static String m1848c(Context context, AbstractHandlerC3740s abstractHandlerC3740s) {
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        m1840a(context, clone);
        m1846b(context, clone);
        m1850c(context, clone);
        clone.m1823a("ssm", "1", false);
        clone.m1823a(SpeechConstant.RESULT_TYPE, "json", false);
        clone.m1823a("rse", abstractHandlerC3740s.getResultEncoding(), false);
        clone.m1823a(SpeechConstant.TEXT_ENCODING, abstractHandlerC3740s.getTextEncoding(), false);
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: d */
    public static String m1852d(Context context, AbstractHandlerC3740s abstractHandlerC3740s) {
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        m1840a(context, clone);
        m1846b(context, clone);
        m1850c(context, clone);
        clone.m1823a("ssm", "1", false);
        int sampleRate = abstractHandlerC3740s.getSampleRate();
        clone.m1822a("auf=audio/L16;rate", Integer.toString(sampleRate));
        if (sampleRate == 16000) {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX_WB, false);
        } else {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX, false);
        }
        clone.m1823a("voice_name", clone.m1827b("voice_name", f2986a), true);
        clone.m1823a(SpeechConstant.TEXT_ENCODING, abstractHandlerC3740s.getTextEncoding(), false);
        m1847b(clone);
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: e */
    public static String m1855e(Context context, AbstractHandlerC3740s abstractHandlerC3740s) {
        C3692ad clone = abstractHandlerC3740s.getParam().clone();
        m1840a(context, clone);
        m1846b(context, clone);
        m1850c(context, clone);
        clone.m1823a("ssm", "1", false);
        int sampleRate = abstractHandlerC3740s.getSampleRate();
        clone.m1823a("auf=audio/L16;rate", Integer.toString(sampleRate), false);
        if (sampleRate == 16000) {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX_WB, false);
        } else {
            clone.m1823a(SpeechConstant.AUDIO_FORMAT_AUE, InternalConstant.AUE_SPEEX, false);
        }
        clone.m1823a(SpeechConstant.TEXT_ENCODING, abstractHandlerC3740s.getTextEncoding(), false);
        clone.m1823a("plev", "1", false);
        clone.m1823a("accent", "mandarin", false);
        clone.m1823a("domain", "ise", false);
        clone.m1823a(SpeechConstant.SUBJECT, "ise", false);
        clone.m1823a(SpeechConstant.RESULT_TYPE, "xml", false);
        clone.m1823a("vad_bos", "5000", false);
        clone.m1823a("vad_eos", "1800", false);
        clone.m1823a("vad_speech_timeout", "2147483647", false);
        clone.m1824a(C3693ae.f2978c);
        return clone.toString();
    }

    /* renamed from: a */
    public static String m1837a(Context context) {
        if (context == null) {
            return "null";
        }
        C3692ad appInfo = AppInfoUtil.getAppInfo(context);
        String str = appInfo.m1833e(AppInfoUtil.OS_IMSI) + "|" + appInfo.m1833e(AppInfoUtil.OS_IMEI);
        if (str.length() < 10) {
            str = appInfo.m1833e(AppInfoUtil.NET_MAC);
        }
        if (TextUtils.isEmpty(str) || str.length() <= 0) {
            return null;
        }
        return str;
    }

    /* renamed from: a */
    public static String m1836a() {
        return SpeechUtility.getUtility().getParameter("appid");
    }

    /* renamed from: a */
    public static boolean m1842a(String str) {
        return str != null && (str.contains("sms") || str.contains(InternalConstant.SUB_IAT));
    }

    /* renamed from: b */
    private static void m1847b(C3692ad c3692ad) {
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility == null) {
            return;
        }
        String parameter = utility.getParameter("ver_tts");
        if (c3692ad.m1834g("speed_increase")) {
            return;
        }
        int m1816a = c3692ad.m1816a("speed", 50);
        if (m1816a <= 100) {
            c3692ad.m1822a("speed", "" + m1816a);
            c3692ad.m1822a("speed_increase", "1");
            return;
        }
        if (100 < m1816a && m1816a <= 150 && (TextUtils.isEmpty(parameter) || parameter.contains("5.5."))) {
            c3692ad.m1822a("speed", "" + (m1816a - 50));
            c3692ad.m1822a("speed_increase", "2");
            return;
        }
        if (100 >= m1816a || m1816a > 200) {
            return;
        }
        c3692ad.m1822a("speed", "" + (m1816a - 100));
        c3692ad.m1822a("speed_increase", TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
    }

    /* renamed from: a */
    public static int m1835a(AbstractHandlerC3740s abstractHandlerC3740s) {
        return (abstractHandlerC3740s == null || !abstractHandlerC3740s.isLongInput()) ? 4000 : 5000;
    }

    /* renamed from: b */
    public static int m1843b(AbstractHandlerC3740s abstractHandlerC3740s) {
        return (abstractHandlerC3740s == null || !abstractHandlerC3740s.isLongInput()) ? 700 : 1800;
    }
}
