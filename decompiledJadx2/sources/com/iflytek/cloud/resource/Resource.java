package com.iflytek.cloud.resource;

import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes3.dex */
public class Resource {
    public static final int TAG_ERROR_CODE = 0;
    public static final int TAG_ERROR_UNKNOWN = 1;
    public static final int TEXT_AGAIN = 9;
    public static final int TEXT_CANCEL = 4;
    public static final int TEXT_DETAIL = 3;
    public static final int TEXT_HELP_LINK = 1;
    public static final int TEXT_HELP_RECO = 13;
    public static final int TEXT_HELP_SMS = 12;
    public static final int TEXT_KNOW = 2;
    public static final int TEXT_MORE = 7;
    public static final int TEXT_PLAYBACK = 10;
    public static final int TEXT_POWER_LINK = 0;
    public static final int TEXT_RETRIEVE = 11;
    public static final int TEXT_RETRY = 8;
    public static final int TEXT_SET = 6;
    public static final int TEXT_STOP = 5;
    public static final int TITLE_AUDIO_PLAYING = 6;
    public static final int TITLE_AUDIO_REQUEST = 4;
    public static final int TITLE_CONNECTING = 1;
    public static final int TITLE_DATA_UPLOAD = 7;
    public static final int TITLE_ERROR = 5;
    public static final int TITLE_HELP = 0;
    public static final int TITLE_RECOGNIZE_WAITING = 3;
    public static final int TITLE_RECORDING = 2;

    /* renamed from: a */
    private static Locale f2935a = Locale.CHINA;

    private Resource() {
    }

    public static void setUILanguage(Locale locale) {
        if (locale == null) {
            return;
        }
        if (locale.equals(Locale.US) || locale.equals(Locale.CHINA) || locale.equals(Locale.TRADITIONAL_CHINESE)) {
            f2935a = locale;
        }
    }

    public static String getLanguage() {
        return f2935a.toString();
    }

    public static boolean matchLanguage(String str) {
        String trim = str != null ? str.trim() : "";
        if (f2935a.toString().equalsIgnoreCase(trim)) {
            return true;
        }
        return m1758a(trim) && m1758a(f2935a.toString());
    }

    /* renamed from: a */
    private static boolean m1758a(String str) {
        return TextUtils.isEmpty(str) || Locale.CHINA.toString().equalsIgnoreCase(str) || Locale.CHINESE.toString().equalsIgnoreCase(str);
    }

    public static void setTitle(int i, String str) {
        String[] strArr = C3685a.f2937b;
        if (f2935a.equals(Locale.US)) {
            strArr = C3686b.f2941b;
        } else if (f2935a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C3687c.f2945b;
        }
        if (i < 0 || i >= strArr.length) {
            return;
        }
        strArr[i] = str;
    }

    public static String getTitle(int i) {
        String[] strArr = C3685a.f2937b;
        if (f2935a.equals(Locale.US)) {
            strArr = C3686b.f2941b;
        } else if (f2935a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C3687c.f2945b;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }

    public static String getText(int i) {
        String[] strArr = C3685a.f2936a;
        if (f2935a.equals(Locale.US)) {
            strArr = C3686b.f2940a;
        } else if (f2935a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C3687c.f2944a;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }

    public static void setText(int i, String str) {
        String[] strArr = C3685a.f2936a;
        if (f2935a.equals(Locale.US)) {
            strArr = C3686b.f2940a;
        } else if (f2935a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C3687c.f2944a;
        }
        if (i < 0 || i >= strArr.length) {
            return;
        }
        strArr[i] = str;
    }

    public static String getErrorDescription(int i) {
        String[] strArr = C3685a.f2938c;
        if (f2935a.equals(Locale.US)) {
            strArr = C3686b.f2942c;
        } else if (f2935a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C3687c.f2946c;
        }
        if (i > 0 && i < strArr.length) {
            return strArr[i];
        }
        return getErrorTag(1);
    }

    public static void setErrorDescription(int i, String str) {
        String[] strArr = C3685a.f2938c;
        if (f2935a.equals(Locale.US)) {
            strArr = C3686b.f2942c;
        } else if (f2935a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C3687c.f2946c;
        }
        if (i <= 0 || i >= strArr.length) {
            return;
        }
        strArr[i] = str;
    }

    public static String getErrorTag(int i) {
        String[] strArr = C3685a.f2939d;
        if (f2935a.equals(Locale.US)) {
            strArr = C3686b.f2943d;
        } else if (f2935a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C3687c.f2947d;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }
}
