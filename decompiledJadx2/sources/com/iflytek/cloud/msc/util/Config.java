package com.iflytek.cloud.msc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class Config {
    public static final String KEY_AD_LAST_UPDATE = "ad_last_update";
    public static final String KEY_FIRST_START = "first_start";
    public static final String KEY_LATITUDE = "msc.lat";
    public static final String KEY_LOCTION_LAST_UPDATE = "loction_last_update";
    public static final String KEY_LONGITUDE = "msc.lng";
    public static final String PREFERENCES_NAME = "com.iflytek.msc";
    public static Config mUserConfig;
    private boolean hasGpsPermission;
    private long loction_Update_Time = 0;
    private Context mContext;
    private SharedPreferences mLocalPreferences;

    private static synchronized Config initInstance(Context context) {
        Config config;
        synchronized (Config.class) {
            if (mUserConfig == null) {
                mUserConfig = new Config(context);
            }
            FileUtil.getUserPath(context);
            AppInfoUtil.getAppInfo(context);
            config = mUserConfig;
        }
        return config;
    }

    public static Config getConfig(Context context) {
        if (mUserConfig == null && context != null) {
            initInstance(context);
        }
        return mUserConfig;
    }

    private Config(Context context) {
        this.mLocalPreferences = null;
        this.mContext = null;
        this.hasGpsPermission = true;
        this.mContext = context;
        this.mLocalPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
        this.hasGpsPermission = hasGPSPermission(context);
    }

    public void putBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = this.mLocalPreferences.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public void putLong(String str, long j) {
        SharedPreferences.Editor edit = this.mLocalPreferences.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public void putString(String str, String str2) {
        SharedPreferences.Editor edit = this.mLocalPreferences.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void putInt(String str, int i) {
        SharedPreferences.Editor edit = this.mLocalPreferences.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void putBean(String str, Object obj) {
        if (obj instanceof Serializable) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
                String str2 = new String(android.util.Base64.encode(byteArrayOutputStream.toByteArray(), 0));
                SharedPreferences.Editor edit = this.mLocalPreferences.edit();
                edit.putString(str, str2);
                edit.commit();
                return;
            } catch (IOException e) {
                DebugLog.LogE("put data to preference has exception:" + e.getMessage());
                return;
            }
        }
        DebugLog.LogE("the obj must implement Serializble");
    }

    public void putLocation(Location location) {
        if (location == null) {
            return;
        }
        SharedPreferences.Editor edit = this.mLocalPreferences.edit();
        edit.putFloat(KEY_LATITUDE, (float) location.getLatitude());
        edit.putFloat(KEY_LONGITUDE, (float) location.getLongitude());
        edit.commit();
    }

    public void removeBean(String str) {
        try {
            this.mLocalPreferences.edit().remove(str).commit();
        } catch (Exception e) {
            DebugLog.LogE("remove data from preference has exception:" + e.getMessage());
        }
    }

    public Object getBean(String str, String str2) {
        try {
            String string = this.mLocalPreferences.getString(str, str2);
            if (string.equals("")) {
                return null;
            }
            return new ObjectInputStream(new ByteArrayInputStream(android.util.Base64.decode(string.getBytes(), 1))).readObject();
        } catch (Exception e) {
            DebugLog.LogE("get data from preference has exception:" + e.getMessage());
            return null;
        }
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mLocalPreferences.getBoolean(str, z);
    }

    public long getLong(String str, long j) {
        return this.mLocalPreferences.getLong(str, j);
    }

    public String getString(String str, String str2) {
        return this.mLocalPreferences.getString(str, str2);
    }

    public int getInt(String str, int i) {
        return this.mLocalPreferences.getInt(str, i);
    }

    public synchronized float getLocation(String str) {
        try {
            if (this.hasGpsPermission && System.currentTimeMillis() - this.loction_Update_Time > 216000) {
                LocationManager locationManager = (LocationManager) this.mContext.getApplicationContext().getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
                this.loction_Update_Time = System.currentTimeMillis();
                putLong(KEY_LOCTION_LAST_UPDATE, this.loction_Update_Time);
                DebugLog.LogS("getLocation begin:" + System.currentTimeMillis());
                Criteria criteria = new Criteria();
                criteria.setAccuracy(1);
                DebugLog.LogS("bestProvider:" + locationManager.getBestProvider(criteria, true));
                try {
                    Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                    if (lastKnownLocation != null) {
                        DebugLog.LogD(lastKnownLocation.toString());
                        putLocation(lastKnownLocation);
                    } else {
                        Location lastKnownLocation2 = locationManager.getLastKnownLocation("network");
                        if (lastKnownLocation2 != null) {
                            DebugLog.LogD(lastKnownLocation2.toString());
                            putLocation(lastKnownLocation2);
                        }
                    }
                } catch (Exception e) {
                    DebugLog.LogE(e.getMessage());
                }
                DebugLog.LogS("getLocation end:" + System.currentTimeMillis());
            }
        } catch (Exception unused) {
        }
        return this.mLocalPreferences.getFloat(str, -0.1f);
    }

    public static boolean hasGPSPermission(Context context) {
        if (!Setting.getLocationEnable() || context == null) {
            return false;
        }
        String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        for (int i = 0; i < strArr.length; i++) {
            if ("android.permission.ACCESS_FINE_LOCATION".equalsIgnoreCase(strArr[i]) || "android.permission.ACCESS_COARSE_LOCATION".equalsIgnoreCase(strArr[i])) {
                return true;
            }
        }
        return false;
    }
}
