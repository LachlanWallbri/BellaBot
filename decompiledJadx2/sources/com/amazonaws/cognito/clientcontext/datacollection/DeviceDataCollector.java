package com.amazonaws.cognito.clientcontext.datacollection;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;
import android.view.WindowManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class DeviceDataCollector extends DataCollector {
    protected static final String LOCAL_STORAGE_DEVICE_ID_KEY = "CognitoDeviceId";
    protected static final String LOCAL_STORAGE_PATH = "AWS.Cognito.ContextData";
    private static final String PLATFORM = "ANDROID";

    protected String getThirdPartyDeviceAgent() {
        return "android_id";
    }

    @Override // com.amazonaws.cognito.clientcontext.datacollection.DataCollector
    public Map<String, String> collect(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put(DataRecordKey.TIMEZONE, getTimezoneOffset());
        hashMap.put(DataRecordKey.PLATFORM, PLATFORM);
        hashMap.put(DataRecordKey.THIRD_PARTY_DEVICE_AGENT, getThirdPartyDeviceAgent());
        hashMap.put(DataRecordKey.DEVICE_AGENT, getCognitoDeviceAgent(context));
        hashMap.put(DataRecordKey.DEVICE_LANGUAGE, getLanguage());
        Display display = getDisplay(context);
        hashMap.put(DataRecordKey.DEVICE_HEIGHT, String.valueOf(display.getHeight()));
        hashMap.put(DataRecordKey.DEVICE_WIDTH, String.valueOf(display.getWidth()));
        return hashMap;
    }

    private Display getDisplay(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    protected String getCognitoDeviceAgent(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOCAL_STORAGE_PATH, 0);
        if (sharedPreferences == null) {
            return null;
        }
        String string = sharedPreferences.getString(LOCAL_STORAGE_DEVICE_ID_KEY, null);
        if (string != null) {
            return string;
        }
        String str = UUID.randomUUID().toString() + ":" + new Date().getTime();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(LOCAL_STORAGE_DEVICE_ID_KEY, str);
        edit.apply();
        return str;
    }

    protected String getLanguage() {
        return Locale.getDefault().toString();
    }

    private String getTimezoneOffset() {
        long rawOffset = getTimezone().getRawOffset();
        long hours = TimeUnit.MILLISECONDS.toHours(rawOffset);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(rawOffset) - TimeUnit.HOURS.toMinutes(hours);
        StringBuilder sb = new StringBuilder();
        sb.append(hours < 0 ? "-" : "");
        sb.append(String.format(Locale.US, "%02d:%02d", Long.valueOf(Math.abs(hours)), Long.valueOf(minutes)));
        return sb.toString();
    }

    protected TimeZone getTimezone() {
        return TimeZone.getDefault();
    }
}
