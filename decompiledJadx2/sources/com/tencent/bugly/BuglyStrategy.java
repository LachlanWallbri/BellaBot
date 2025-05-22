package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.info.C5873a;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class BuglyStrategy {

    /* renamed from: a */
    private String f7621a;

    /* renamed from: b */
    private String f7622b;

    /* renamed from: c */
    private String f7623c;

    /* renamed from: d */
    private long f7624d;

    /* renamed from: e */
    private String f7625e;

    /* renamed from: f */
    private String f7626f;

    /* renamed from: g */
    private boolean f7627g = true;

    /* renamed from: h */
    private boolean f7628h = true;

    /* renamed from: i */
    private boolean f7629i = true;

    /* renamed from: j */
    private Class<?> f7630j = null;

    /* renamed from: k */
    private boolean f7631k = true;

    /* renamed from: l */
    private boolean f7632l = true;

    /* renamed from: m */
    private boolean f7633m = true;

    /* renamed from: n */
    private boolean f7634n = false;

    /* renamed from: o */
    private C5863a f7635o;

    public synchronized BuglyStrategy setBuglyLogUpload(boolean z) {
        this.f7631k = z;
        return this;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.f7634n = z;
        return this;
    }

    public synchronized BuglyStrategy setUploadProcess(boolean z) {
        this.f7633m = z;
        return this;
    }

    public synchronized boolean isUploadProcess() {
        return this.f7633m;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.f7631k;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.f7634n;
    }

    public boolean isReplaceOldChannel() {
        return this.f7632l;
    }

    public void setReplaceOldChannel(boolean z) {
        this.f7632l = z;
    }

    public synchronized String getAppVersion() {
        if (this.f7621a == null) {
            return C5873a.m3390b().f7762j;
        }
        return this.f7621a;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.f7621a = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.f7630j = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.f7630j;
    }

    public synchronized String getAppChannel() {
        if (this.f7622b == null) {
            return C5873a.m3390b().f7764l;
        }
        return this.f7622b;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.f7622b = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        if (this.f7623c == null) {
            return C5873a.m3390b().f7755c;
        }
        return this.f7623c;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.f7623c = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.f7624d;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.f7624d = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.f7625e;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.f7625e = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f7626f;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f7626f = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.f7627g;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.f7627g = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.f7629i = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.f7629i;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.f7628h;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.f7628h = z;
        return this;
    }

    public synchronized C5863a getCrashHandleCallback() {
        return this.f7635o;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(C5863a c5863a) {
        this.f7635o = c5863a;
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.BuglyStrategy$a */
    /* loaded from: classes7.dex */
    public static class C5863a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }
}
