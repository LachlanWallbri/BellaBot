package com.iflytek.speech;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.iflytek.cloud.msc.util.AppInfoUtil;
import com.iflytek.cloud.msc.util.Encrypter;
import com.iflytek.cloud.msc.util.UniqueIDUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.util.HashMap;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public class UtilityConfig {
    public static final String ACTION_SPEAKER_VERIFIER = "com.iflytek.vflynote.speakerverify";
    public static final String ACTION_SPEECH_RECOGNIZER = "com.iflytek.vflynote.recognize";
    public static final String ACTION_SPEECH_SYNTHESIZER = "com.iflytek.vflynote.synthesize";
    public static final String ACTION_SPEECH_UNDERSTANDER = "com.iflytek.vflynote.speechunderstand";
    public static final String ACTION_SPEECH_WAKEUP = "com.iflytek.vflynote.wakeup";
    public static final String ACTION_TEXT_UNDERSTANDER = "com.iflytek.vflynote.textunderstand";
    public static final String CHANNEL_ID = "16010000";
    public static final String CHANNEL_NAME = "dev.voicecloud";
    public static final String COMPONENT_PKG = "com.iflytek.vflynote";
    public static final String COMPONENT_URL = "http://iss.openspeech.cn/v?";
    public static final String KEY_CALLER_APPID = "caller.appid";
    public static final String KEY_CALLER_NAME = "caller.name";
    public static final String KEY_CALLER_PKG_NAME = "caller.pkg";
    public static final String KEY_CALLER_VER_CODE = "caller.ver.code";
    public static final String KEY_CALLER_VER_NAME = "caller.ver.name";
    public static final String KEY_CHANNEL_ID = "channel.id";
    public static final String KEY_CHANNEL_NAME = "channel.name";
    public static final String KEY_DEVICE_INFO = "device";
    public static final String KEY_REQUEST_PACKAGE = "request.package";
    public static final String METADATA_KEY_ENGINE_TYPE = "enginetype";
    public static final String SDK_VER_NAME = "sdk.ver.name";
    public static final String SETTINGS_ACTION_ASR = "com.iflytek.vflynote.settings.asr";
    public static final String SETTINGS_ACTION_MAIN = "com.iflytek.vflynote.settings.main";
    public static final String SETTINGS_ACTION_TTS = "com.iflytek.vflynote.activity.speaker.SpeakerSetting";
    private static HashMap<String, String> callerHashMap = new HashMap<>();

    public static String getCallerInfo(Context context, String str) {
        if (callerHashMap.containsKey(str)) {
            return callerHashMap.get(str);
        }
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            callerHashMap.put(KEY_CALLER_NAME, applicationInfo.loadLabel(context.getPackageManager()).toString());
            callerHashMap.put(KEY_CALLER_PKG_NAME, applicationInfo.packageName);
            callerHashMap.put(KEY_CALLER_VER_NAME, packageInfo.versionName);
            callerHashMap.put(KEY_CALLER_VER_CODE, String.valueOf(packageInfo.versionCode));
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
        return callerHashMap.get(str);
    }

    public static String getComponentUrlParam(Context context) {
        String callerInfo = getCallerInfo(context, KEY_CALLER_NAME);
        String callerInfo2 = getCallerInfo(context, KEY_CALLER_PKG_NAME);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(KEY_CHANNEL_ID);
        stringBuffer.append('=');
        stringBuffer.append(CHANNEL_ID);
        appendHttpParam(stringBuffer, SDK_VER_NAME, Version.getVersionName());
        appendHttpParam(stringBuffer, KEY_CALLER_NAME, callerInfo);
        appendHttpParam(stringBuffer, KEY_CALLER_PKG_NAME, callerInfo2);
        appendHttpParam(stringBuffer, KEY_DEVICE_INFO, new String(Encrypter.lightcode(((("os.imei=" + AppInfoUtil.getAppInfo(context).m1833e(AppInfoUtil.OS_IMEI)) + ";net.mac=" + AppInfoUtil.getAppInfo(context).m1833e(AppInfoUtil.NET_MAC)) + ";unique_id=" + UniqueIDUtil.getUniqueID(context)).getBytes())));
        return stringBuffer.toString();
    }

    public static void appendHttpParam(StringBuffer stringBuffer, String str, String str2) {
        if (stringBuffer == null || str == null || str2 == null || str2.length() <= 0) {
            return;
        }
        stringBuffer.append(Typography.amp);
        stringBuffer.append(str);
        stringBuffer.append('=');
        stringBuffer.append(str2);
    }
}
