package com.iflytek.cloud;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3692ad;
import com.iflytek.cloud.thirdparty.C3745x;
import com.iflytek.cloud.thirdparty.HandlerC3746y;
import com.iflytek.idata.extension.IFlyCollectorExt;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import com.iflytek.speech.SpeechComponent;
import com.iflytek.speech.UtilityConfig;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class SpeechUtility extends AbstractC3743v {
    public static final AbstractC3743v.a DEF_ENGINE_MODE = AbstractC3743v.a.MSC;
    public static final String TAG_RESOURCE_CONTENT = "tag_rescontent";
    public static final String TAG_RESOURCE_RESULT = "result";
    public static final String TAG_RESOURCE_RET = "ret";

    /* renamed from: b */
    private static SpeechUtility f2815b;

    /* renamed from: a */
    protected AbstractC3743v.a f2816a;

    /* renamed from: e */
    private Context f2819e;

    /* renamed from: c */
    private ArrayList<SpeechComponent> f2817c = new ArrayList<>();

    /* renamed from: d */
    private int f2818d = -1;

    /* renamed from: f */
    private boolean f2820f = false;

    /* renamed from: g */
    private C3669a f2821g = null;

    public static synchronized SpeechUtility createUtility(Context context, String str) {
        SpeechUtility speechUtility;
        synchronized (SpeechUtility.class) {
            synchronized (sSync) {
                if (f2815b == null) {
                    C3692ad c3692ad = new C3692ad();
                    c3692ad.m1828b(str);
                    if (!c3692ad.m1825a(SpeechConstant.FORCE_LOGIN, false) && !m1661a(context.getApplicationContext())) {
                        DebugLog.LogE("init failed, please call this method in your main process!");
                        f2815b = null;
                    } else {
                        try {
                            f2815b = new SpeechUtility(context, str);
                        } catch (SpeechError e) {
                            DebugLog.LogE("init failed");
                            DebugLog.LogE(e);
                        }
                    }
                }
            }
            speechUtility = f2815b;
        }
        return speechUtility;
    }

    public static synchronized SpeechUtility getUtility() {
        SpeechUtility speechUtility;
        synchronized (SpeechUtility.class) {
            speechUtility = f2815b;
        }
        return speechUtility;
    }

    private SpeechUtility(Context context, String str) throws SpeechError {
        this.f2819e = null;
        this.f2816a = DEF_ENGINE_MODE;
        this.f2819e = context.getApplicationContext();
        super.setParameter("params", str);
        MSC.loadLibrary(this.mSessionParams.m1827b(SpeechConstant.LIB_NAME, "msc"));
        DebugLog.updateJniLogStatus();
        setParameter("params", str);
        this.f2816a = AbstractC3743v.a.MSC;
        int m1662b = m1662b();
        if (m1662b != 0) {
            throw new SpeechError(m1662b);
        }
        m1666d();
        String parameter = getParameter("appid");
        try {
            String parameter2 = getParameter("enable_sunflower");
            if (TextUtils.isEmpty(parameter2) || !parameter2.equals("false")) {
                C3670b c3670b = new C3670b();
                c3670b.m1669a(parameter);
                c3670b.m1668a(context.getApplicationContext());
                c3670b.start();
            }
        } catch (Exception unused) {
            DebugLog.LogW("userLog creating failed");
        }
        String parameter3 = getParameter("lxy_tp_dc");
        if (!TextUtils.isEmpty(parameter3)) {
            "false".equals(parameter3);
        }
        DebugLog.LogD("DC init enable=" + parameter3);
    }

    /* renamed from: com.iflytek.cloud.SpeechUtility$b */
    /* loaded from: classes3.dex */
    class C3670b extends Thread {

        /* renamed from: a */
        String f2822a = "";

        /* renamed from: b */
        Context f2823b;

        C3670b() {
        }

        /* renamed from: a */
        public void m1669a(String str) {
            this.f2822a = str;
        }

        /* renamed from: a */
        public void m1668a(Context context) {
            this.f2823b = context;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            IFlyCollectorExt m2309a = IFlyCollectorExt.m2309a(this.f2823b.getApplicationContext());
            m2309a.m2315a("5d2386c983");
            m2309a.m2317b(this.f2822a);
        }
    }

    /* renamed from: b */
    private int m1662b() {
        if (!MSC.isLoaded()) {
            return 21002;
        }
        DebugLog.LogD("SpeechUtility start login");
        SpeechError m2268a = new HandlerC3746y(this.f2819e, this.mSessionParams).m2268a(this.mSessionParams.m1833e("usr"), this.mSessionParams.m1833e("pwd"));
        if (m2268a == null) {
            return 0;
        }
        return m2268a.getErrorCode();
    }

    /* renamed from: c */
    private boolean m1664c() {
        if (MSC.isLoaded()) {
            return C3745x.m2264a();
        }
        return true;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        boolean z;
        if (f2815b != null) {
            super.destroy();
            z = m1664c();
        } else {
            z = true;
        }
        if (z) {
            C3669a c3669a = this.f2821g;
            if (c3669a != null) {
                this.f2819e.unregisterReceiver(c3669a);
            }
            this.f2821g = null;
            DebugLog.LogD("SpeechUtility destory success");
            synchronized (sSync) {
                f2815b = null;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m1667a() {
        try {
            return this.f2819e.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: d */
    private void m1666d() {
        if (checkServiceInstalled()) {
            m1660a(UtilityConfig.ACTION_SPEECH_RECOGNIZER);
            m1660a(UtilityConfig.ACTION_SPEECH_SYNTHESIZER);
            m1660a(UtilityConfig.ACTION_SPEECH_UNDERSTANDER);
            m1660a(UtilityConfig.ACTION_TEXT_UNDERSTANDER);
            m1660a(UtilityConfig.ACTION_SPEECH_WAKEUP);
        }
    }

    /* renamed from: com.iflytek.cloud.SpeechUtility$a */
    /* loaded from: classes3.dex */
    private class C3669a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            String concat = "package:".concat(UtilityConfig.COMPONENT_PKG);
            if (("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) && concat.equals(dataString) && SpeechUtility.getUtility() != null) {
                SpeechUtility.getUtility().checkServiceInstalled();
            }
        }
    }

    public boolean checkServiceInstalled() {
        boolean z = false;
        int i = -1;
        try {
            PackageInfo packageInfo = this.f2819e.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
            if (packageInfo != null) {
                z = true;
                i = packageInfo.versionCode;
            }
        } catch (Throwable unused) {
        }
        if (z != this.f2820f || this.f2818d != i) {
            this.f2820f = z;
            this.f2818d = i;
            if (SpeechRecognizer.getRecognizer() != null) {
                SpeechRecognizer.getRecognizer().m1653a(this.f2819e);
            }
            if (SpeechSynthesizer.getSynthesizer() != null) {
                SpeechSynthesizer.getSynthesizer().m1655a(this.f2819e);
            }
            if (SpeechUnderstander.getUnderstander() != null) {
                SpeechUnderstander.getUnderstander().m1658a(this.f2819e);
            }
            if (TextUnderstander.getTextUnderstander() != null) {
                TextUnderstander.getTextUnderstander().m1671a(this.f2819e);
            }
        }
        return z;
    }

    /* renamed from: a */
    private void m1660a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PackageManager packageManager = this.f2819e.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 224);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            return;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            SpeechComponent m1663b = m1663b(resolveInfo.serviceInfo.packageName);
            if (m1663b != null) {
                try {
                    for (String str2 : resolveInfo.serviceInfo.metaData.getString(UtilityConfig.METADATA_KEY_ENGINE_TYPE).split(",")) {
                        m1663b.addEngine(str2);
                    }
                } catch (Exception e) {
                    DebugLog.LogE(e);
                }
            }
        }
    }

    /* renamed from: b */
    private SpeechComponent m1663b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        boolean z = false;
        Iterator<SpeechComponent> it = this.f2817c.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (str.equals(it.next().getPackageName())) {
                z = true;
                break;
            }
        }
        if (z) {
            return null;
        }
        SpeechComponent speechComponent = new SpeechComponent(str);
        this.f2817c.add(speechComponent);
        return speechComponent;
    }

    public String[] queryAvailableEngines() {
        this.f2817c.clear();
        m1666d();
        ArrayList arrayList = new ArrayList();
        Iterator<SpeechComponent> it = this.f2817c.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().getEngines());
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }

    public int openEngineSettings(String str) {
        String str2 = UtilityConfig.COMPONENT_PKG;
        try {
            Intent intent = new Intent();
            intent.setPackage(UtilityConfig.COMPONENT_PKG);
            if ("tts".equals(str) && m1665c(UtilityConfig.SETTINGS_ACTION_TTS)) {
                str2 = UtilityConfig.SETTINGS_ACTION_TTS;
            } else if ("asr".equals(str) && m1665c(UtilityConfig.SETTINGS_ACTION_ASR)) {
                str2 = UtilityConfig.SETTINGS_ACTION_ASR;
            } else if (m1665c(UtilityConfig.SETTINGS_ACTION_MAIN)) {
                str2 = UtilityConfig.SETTINGS_ACTION_MAIN;
            }
            intent.setAction(str2);
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            this.f2819e.startActivity(intent);
            return 0;
        } catch (Exception e) {
            DebugLog.LogE(e);
            return 21002;
        }
    }

    /* renamed from: c */
    private boolean m1665c(String str) {
        PackageManager packageManager = this.f2819e.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        return packageManager.queryIntentActivities(intent, 1).size() > 0;
    }

    public String getComponentUrl() {
        StringBuffer stringBuffer = new StringBuffer(UtilityConfig.COMPONENT_URL);
        UtilityConfig.appendHttpParam(stringBuffer, TransferTable.COLUMN_KEY, URLEncoder.encode(Base64.encodeToString(UtilityConfig.getComponentUrlParam(this.f2819e).getBytes(), 0)));
        UtilityConfig.appendHttpParam(stringBuffer, "version", "2.0");
        return stringBuffer.toString();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        super.setParameter(str, str2);
        if (!MSC.isLoaded() || "params".equals(str)) {
            return true;
        }
        try {
            return MSC.QMSPSetParam(str.getBytes("utf-8"), str2.getBytes("utf-8")) == 0;
        } catch (Exception e) {
            DebugLog.LogE(e);
            return false;
        } catch (UnsatisfiedLinkError e2) {
            DebugLog.LogE(e2);
            return false;
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public String getParameter(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.mSessionParams.m1834g(str)) {
            return super.getParameter(str);
        }
        if (!MSC.isLoaded()) {
            return null;
        }
        try {
        } catch (Exception e) {
            DebugLog.LogS(e);
        } catch (UnsatisfiedLinkError e2) {
            DebugLog.LogS(e2);
        }
        if (!str.equals("ver_msc") && !str.equals("ver_asr") && !str.equals("ver_tts") && !str.equals("ver_ivw")) {
            byte[] bytes = str.getBytes("utf-8");
            MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
            if (MSC.QMSPGetParam(bytes, mSCSessionInfo) == 0) {
                return new String(mSCSessionInfo.buffer, "utf-8");
            }
            return null;
        }
        byte[] bytes2 = str.getBytes("utf-8");
        MSCSessionInfo mSCSessionInfo2 = new MSCSessionInfo();
        String str2 = new String(MSC.QMSPGetVersion(bytes2, mSCSessionInfo2), "utf-8");
        if (mSCSessionInfo2.errorcode == 0) {
            return str2;
        }
        return null;
    }

    public String getPlusLocalInfo(String str) throws JSONException {
        String str2;
        JSONObject jSONObject = new JSONObject();
        if (!checkServiceInstalled()) {
            jSONObject.put("ret", 21001);
            return jSONObject.toString();
        }
        if (getServiceVersion() < 97) {
            jSONObject.put("ret", 20018);
            return jSONObject.toString();
        }
        if (10000 <= getServiceVersion() && getServiceVersion() <= 11000) {
            jSONObject.put("ret", 20020);
            return jSONObject.toString();
        }
        Cursor query = this.f2819e.getContentResolver().query(Uri.parse("content://com.iflytek.vflynote.providers.LocalResourceProvider"), null, str, null, null);
        int columnIndex = query.getColumnIndex(TAG_RESOURCE_CONTENT);
        if (query == null || !query.moveToFirst()) {
            str2 = "";
        } else {
            str2 = query.getString(columnIndex);
            Log.v("SpeechUtility", str2);
        }
        if (query != null) {
            query.close();
        }
        if (TextUtils.isEmpty(str2)) {
            jSONObject.put("ret", 20004);
            return jSONObject.toString();
        }
        jSONObject.put("ret", 0);
        jSONObject.put(TAG_RESOURCE_RESULT, new JSONObject(str2));
        return jSONObject.toString();
    }

    public AbstractC3743v.a getEngineMode() {
        return this.f2816a;
    }

    public int getServiceVersion() {
        if (this.f2818d < 0) {
            try {
                PackageInfo packageInfo = this.f2819e.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
                if (packageInfo != null) {
                    this.f2818d = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return this.f2818d;
    }

    /* renamed from: a */
    private static boolean m1661a(Context context) {
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        DebugLog.LogD("process name:" + runningAppProcessInfo.processName);
                        if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).packageName.equals(runningAppProcessInfo.processName)) {
                            DebugLog.LogD("process name:" + runningAppProcessInfo.processName + "is own process");
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
        return false;
    }
}
