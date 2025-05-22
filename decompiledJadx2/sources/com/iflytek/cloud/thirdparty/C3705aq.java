package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.msc.util.Config;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.util.FileDownloadListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.File;
import org.json.JSONObject;

/* renamed from: com.iflytek.cloud.thirdparty.aq */
/* loaded from: classes3.dex */
public class C3705aq extends AbstractC3744w {

    /* renamed from: m */
    private static String f3042m = "respath";

    /* renamed from: e */
    private boolean f3043e;

    /* renamed from: f */
    private String f3044f;

    /* renamed from: g */
    private String f3045g;

    /* renamed from: h */
    private String f3046h;

    /* renamed from: i */
    private String f3047i;

    /* renamed from: j */
    private String f3048j;

    /* renamed from: k */
    private String f3049k;

    /* renamed from: l */
    private boolean f3050l;

    /* renamed from: n */
    private C3733l f3051n;

    /* renamed from: o */
    private Config f3052o;

    public C3705aq(Context context) {
        super(context);
        this.f3043e = false;
        this.f3044f = null;
        this.f3045g = null;
        this.f3046h = null;
        this.f3047i = null;
        this.f3048j = null;
        this.f3049k = null;
        this.f3050l = false;
        this.f3051n = null;
        this.f3052o = null;
        this.f3052o = Config.getConfig(this.f3434a);
    }

    /* renamed from: a */
    public int m1962a(String str, boolean z, RequestListener requestListener) {
        synchronized (this.f3435b) {
            if (TextUtils.isEmpty(str)) {
                DebugLog.LogE("respath is null. please set respath before startlisten");
                return 25107;
            }
            String generateResourcePath = !z ? ResourceUtil.generateResourcePath(this.f3434a, ResourceUtil.RESOURCE_TYPE.path, this.f3052o.getString("ivw_config_path", null)) : null;
            if (this.f3051n != null) {
                this.f3051n.m2145a();
                this.f3051n = null;
            }
            this.f3051n = new C3733l(this.f3434a);
            JSONObject m2137a = C3733l.m2137a(str, generateResourcePath);
            if (m2137a == null) {
                DebugLog.LogE("ivw invalid resource");
                return 25107;
            }
            String str2 = (String) m2137a.remove(f3042m);
            if (!TextUtils.isEmpty(str2) && !str2.equals(str)) {
                this.f3048j = str2;
                this.f3049k = this.f3052o.getString("cfg_threshold", null);
            } else {
                this.f3048j = null;
                this.f3052o.removeBean("ivw_config_path");
                this.f3052o.removeBean("cfg_threshold");
            }
            PerfLogger.appendInfo(PerfLogger.SENT_REQUEST, null);
            DebugLog.LogD(m2137a.toString());
            return this.f3051n.m2144a(m2137a, new c(z, requestListener));
        }
    }

    /* renamed from: a */
    public int m1961a(String str, String str2, String str3, boolean z, FileDownloadListener fileDownloadListener) {
        synchronized (this.f3435b) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                PerfLogger.appendInfo(PerfLogger.CREATE_DOWNLOAD, null);
                if (this.f3051n != null) {
                    this.f3051n.m2145a();
                    this.f3051n = null;
                }
                this.f3051n = new C3733l(this.f3434a);
                return this.f3051n.m2143a(str, str2, str3, new a(z, fileDownloadListener));
            }
            return 20012;
        }
    }

    /* renamed from: a */
    public int m1960a(WakeuperListener wakeuperListener) {
        int i;
        String str;
        synchronized (this.f3435b) {
            try {
                this.f3044f = this.mSessionParams.m1831d("ivw_res_path");
                this.f3047i = this.mSessionParams.m1831d("ivw_threshold");
                this.f3045g = null;
                if (!TextUtils.isEmpty(this.f3044f)) {
                    int indexOf = this.f3044f.indexOf(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    if (indexOf > 0 && this.f3044f.length() > indexOf) {
                        this.f3045g = this.f3044f.substring(0, indexOf);
                        this.f3046h = this.f3044f.substring(indexOf + 1);
                    } else {
                        this.f3046h = this.f3044f;
                    }
                }
                if (m1958h()) {
                    this.f3048j = ResourceUtil.generateResourcePath(this.f3434a, ResourceUtil.RESOURCE_TYPE.path, this.f3052o.getString("ivw_config_path", null));
                    Config config = this.f3052o;
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = config.getLong("ivw_query_last_time", 0L);
                    long m1817a = this.mSessionParams.m1817a("ivw_query_period", 86400000L);
                    DebugLog.LogD("query ivw res period: " + m1817a);
                    if (currentTimeMillis - j >= m1817a) {
                        if (m1959i()) {
                            str = this.f3048j;
                        } else {
                            str = this.f3046h;
                        }
                        DebugLog.LogD("begin resource query res path: " + str);
                        m1962a(str, false, (RequestListener) null);
                        config.putLong("ivw_query_last_time", currentTimeMillis);
                    } else if (currentTimeMillis == j) {
                        config.putLong("ivw_query_last_time", currentTimeMillis);
                    }
                }
                i = m1949b(wakeuperListener);
            } finally {
                return i;
            }
        }
        return i;
    }

    /* renamed from: b */
    private int m1949b(WakeuperListener wakeuperListener) {
        int i;
        String str;
        synchronized (this.f3435b) {
            i = 0;
            try {
                if (m1958h() && m1959i()) {
                    DebugLog.LogD("ivw use resource from server");
                    if (TextUtils.isEmpty(this.f3045g)) {
                        str = this.f3048j;
                    } else {
                        str = this.f3045g + VoiceWakeuperAidl.PARAMS_SEPARATE + this.f3048j;
                    }
                    this.mSessionParams.m1822a("ivw_res_path", str);
                    this.mSessionParams.m1822a("ivw_threshold", (String) null);
                    m1951b(false);
                } else {
                    this.mSessionParams.m1822a("ivw_res_path", this.f3044f);
                    this.mSessionParams.m1822a("ivw_threshold", this.f3047i);
                    m1951b(true);
                }
                this.f3043e = this.mSessionParams.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, false);
                if (this.f3436c != null && this.f3436c.isRunning()) {
                    ((HandlerC3732k) this.f3436c).cancel(false);
                }
                if (this.mSessionParams.m1816a(SpeechConstant.IVW_CHANNEL_NUM, 0) == 0) {
                    this.f3436c = new HandlerC3732k(this.f3434a, this.mSessionParams, m2259a("wakeuper"));
                } else {
                    this.f3436c = new HandlerC3730i(this.f3434a, this.mSessionParams, m2259a("wakeuper"));
                }
                FuncAdapter.Lock(this.f3434a, Boolean.valueOf(this.f3043e), null);
                ((HandlerC3732k) this.f3436c).m2129a(new b(wakeuperListener));
            } catch (SpeechError e) {
                i = e.getErrorCode();
                DebugLog.LogE(e);
            } catch (Throwable th) {
                i = 20999;
                DebugLog.LogE(th);
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1947a(boolean z) {
        DebugLog.LogD("restart wake ,isError:" + z);
        synchronized (this.f3435b) {
            if (z) {
                this.f3048j = null;
                m1949b(((HandlerC3732k) this.f3436c).m2135e());
            } else if (this.f3436c.isRunning()) {
                this.f3048j = ResourceUtil.generateResourcePath(this.f3434a, ResourceUtil.RESOURCE_TYPE.path, this.f3052o.getString("ivw_config_path", null));
                this.f3049k = this.f3052o.getString("cfg_threshold", null);
                m1949b(((HandlerC3732k) this.f3436c).m2135e());
            }
        }
    }

    /* renamed from: a */
    public int m1963a(byte[] bArr, int i, int i2) {
        synchronized (this.f3435b) {
            if (this.f3436c == null) {
                DebugLog.LogD("writeAudio error, no active session.");
                return 21004;
            }
            if (bArr != null && bArr.length > 0) {
                if (bArr.length < i2 + i) {
                    DebugLog.LogD("writeAudio error,buffer length < length.");
                    return 10109;
                }
                if (-1 != ((HandlerC3732k) this.f3436c).m2133c()) {
                    return 10106;
                }
                return ((HandlerC3732k) this.f3436c).mo2113a(bArr, i, i2);
            }
            DebugLog.LogD("writeAudio error,buffer is null.");
            return 10109;
        }
    }

    /* renamed from: e */
    public void m1964e() {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((HandlerC3732k) this.f3436c).m2131a(true);
            }
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    public void cancel(boolean z) {
        synchronized (this.f3435b) {
            if (this.f3051n != null) {
                this.f3051n.m2145a();
                this.f3051n = null;
            }
            FuncAdapter.UnLock(this.f3434a, Boolean.valueOf(this.f3043e), null);
            super.cancel(z);
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w, com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        boolean destroy;
        synchronized (this.f3435b) {
            if (this.f3051n != null) {
                this.f3051n.m2145a();
                this.f3051n = null;
            }
            destroy = super.destroy();
        }
        return destroy;
    }

    /* renamed from: f */
    public boolean m1965f() {
        boolean d;
        synchronized (this.f3435b) {
            d = mo1797d();
        }
        return d;
    }

    /* renamed from: b */
    private synchronized void m1951b(boolean z) {
        this.f3050l = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public synchronized boolean m1956g() {
        return this.f3050l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public boolean m1958h() {
        int m1816a = this.mSessionParams.m1816a(SpeechConstant.IVW_NET_MODE, 0);
        return 2 == m1816a || (4 == m1816a && NetworkUtil.isWifiConnect(this.f3434a));
    }

    /* renamed from: i */
    private boolean m1959i() {
        if (TextUtils.isEmpty(this.f3048j)) {
            return false;
        }
        if (TextUtils.isEmpty(this.f3044f)) {
            return true;
        }
        return C3733l.m2138a(C3733l.m2140b(this.f3048j, ""), C3733l.m2140b(this.f3046h, ""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.aq$c */
    /* loaded from: classes3.dex */
    public class c implements RequestListener {

        /* renamed from: b */
        private RequestListener f3067b;

        /* renamed from: f */
        private boolean f3071f;

        /* renamed from: c */
        private final int f3068c = 0;

        /* renamed from: d */
        private final int f3069d = 1;

        /* renamed from: e */
        private final int f3070e = 2;

        /* renamed from: g */
        private Handler f3072g = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.aq.c.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (c.this.f3067b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    c.this.f3067b.onEvent(message.arg1, (Bundle) message.obj);
                } else if (i == 1) {
                    c.this.f3067b.onBufferReceived((byte[]) message.obj);
                } else if (i == 2) {
                    c.this.f3067b.onCompleted((SpeechError) message.obj);
                }
                super.handleMessage(message);
            }
        };

        public c(boolean z, RequestListener requestListener) {
            this.f3067b = null;
            this.f3071f = false;
            this.f3071f = z;
            this.f3067b = requestListener;
        }

        @Override // com.iflytek.cloud.RequestListener
        public void onEvent(int i, Bundle bundle) {
            PerfLogger.appendInfo(PerfLogger.REQUEST_RESULT, null);
            this.f3072g.sendMessage(this.f3072g.obtainMessage(0, i, 0, bundle));
        }

        @Override // com.iflytek.cloud.RequestListener
        public void onCompleted(SpeechError speechError) {
            PerfLogger.appendInfo(PerfLogger.REQUEST_RESULT, null);
            this.f3072g.sendMessage(this.f3072g.obtainMessage(2, speechError));
        }

        @Override // com.iflytek.cloud.RequestListener
        public void onBufferReceived(byte[] bArr) {
            DebugLog.LogD("onCompleted");
            try {
                if (!this.f3071f && C3705aq.this.m1958h()) {
                    JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                    String string = jSONObject.getString("dlurl");
                    String string2 = jSONObject.getString("md5");
                    String substring = string.substring(string.lastIndexOf(File.separator) + 1, string.lastIndexOf("."));
                    DebugLog.LogD("resName:" + substring);
                    String resFilePath = FileUtil.getResFilePath(C3705aq.this.f3434a, substring);
                    DebugLog.LogD("auto download path:" + resFilePath);
                    C3705aq.this.m1961a(string, resFilePath, string2, this.f3071f, null);
                    C3705aq.this.f3052o.putString("cfg_threstemp", jSONObject.getString("thresholder"));
                }
            } catch (Exception unused) {
                onCompleted(new SpeechError(20014));
            }
            this.f3072g.sendMessage(this.f3072g.obtainMessage(1, bArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.aq$a */
    /* loaded from: classes3.dex */
    public final class a implements FileDownloadListener {

        /* renamed from: b */
        private FileDownloadListener f3054b;

        /* renamed from: g */
        private boolean f3059g;

        /* renamed from: c */
        private final int f3055c = 0;

        /* renamed from: d */
        private final int f3056d = 1;

        /* renamed from: e */
        private final int f3057e = 2;

        /* renamed from: f */
        private final int f3058f = 3;

        /* renamed from: h */
        private Handler f3060h = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.aq.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f3054b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    a.this.f3054b.onStart();
                } else if (i == 1) {
                    a.this.f3054b.onProgress(message.arg1);
                } else if (i == 2) {
                    a.this.f3054b.onCompleted((String) message.obj, null);
                } else if (i == 3) {
                    a.this.f3054b.onCompleted(null, (SpeechError) message.obj);
                }
                super.handleMessage(message);
            }
        };

        public a(boolean z, FileDownloadListener fileDownloadListener) {
            this.f3054b = null;
            this.f3059g = false;
            this.f3059g = z;
            this.f3054b = fileDownloadListener;
        }

        @Override // com.iflytek.cloud.util.FileDownloadListener
        public void onStart() {
            PerfLogger.appendInfo(PerfLogger.DOWNLOAD_ONSTART, null);
            DebugLog.LogD("onStart");
            this.f3060h.sendMessage(this.f3060h.obtainMessage(0, null));
        }

        @Override // com.iflytek.cloud.util.FileDownloadListener
        public void onProgress(int i) {
            this.f3060h.sendMessage(this.f3060h.obtainMessage(1, i, 0, null));
        }

        @Override // com.iflytek.cloud.util.FileDownloadListener
        public void onCompleted(String str, SpeechError speechError) {
            PerfLogger.appendInfo(PerfLogger.DOWNLOAD_ONFINISH, null);
            if (speechError == null) {
                DebugLog.LogD("onCompleted:filePath:" + str);
                if (!this.f3059g) {
                    if (!TextUtils.isEmpty(str)) {
                        C3705aq.this.f3052o.putString("ivw_config_path", str);
                        C3705aq.this.f3052o.putString("cfg_threshold", C3705aq.this.f3052o.getString("cfg_threstemp", null));
                    }
                    C3705aq.this.m1947a(false);
                }
                this.f3060h.sendMessage(this.f3060h.obtainMessage(2, str));
                return;
            }
            DebugLog.LogD("onCompleted:errorcode:" + speechError.getErrorCode());
            this.f3060h.sendMessage(this.f3060h.obtainMessage(3, speechError));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.aq$b */
    /* loaded from: classes3.dex */
    public final class b implements WakeuperListener {

        /* renamed from: b */
        private WakeuperListener f3063b;

        /* renamed from: c */
        private Handler f3064c = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.aq.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (b.this.f3063b == null) {
                    return;
                }
                switch (message.what) {
                    case 0:
                        b.this.f3063b.onError((SpeechError) message.obj);
                        break;
                    case 2:
                        if (!(b.this.f3063b instanceof b)) {
                            b.this.f3063b.onBeginOfSpeech();
                            break;
                        }
                        break;
                    case 4:
                        b.this.f3063b.onResult((WakeuperResult) message.obj);
                        break;
                    case 5:
                        b.this.f3063b.onVolumeChanged(message.arg1);
                        break;
                    case 6:
                        Message message2 = (Message) message.obj;
                        if (message2 != null) {
                            b.this.f3063b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                            break;
                        }
                        break;
                }
                super.handleMessage(message);
            }
        };

        public b(WakeuperListener wakeuperListener) {
            this.f3063b = null;
            this.f3063b = wakeuperListener;
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onResult(WakeuperResult wakeuperResult) {
            if (!C3705aq.this.mSessionParams.m1825a(SpeechConstant.KEEP_ALIVE, true)) {
                m1968a();
            }
            this.f3064c.sendMessage(this.f3064c.obtainMessage(4, 1, 0, wakeuperResult));
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onBeginOfSpeech() {
            DebugLog.LogD("onBeginOfSpeech");
            this.f3064c.sendMessage(this.f3064c.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onError(SpeechError speechError) {
            DebugLog.LogE("error:" + speechError.getErrorCode());
            if (!C3705aq.this.m1956g()) {
                C3705aq.this.m1947a(true);
                return;
            }
            m1968a();
            this.f3064c.sendMessage(this.f3064c.obtainMessage(0, speechError));
        }

        /* renamed from: a */
        protected void m1968a() {
            FuncAdapter.UnLock(C3705aq.this.f3434a, Boolean.valueOf(C3705aq.this.f3043e), null);
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = i2;
            obtain.arg2 = i3;
            obtain.obj = bundle;
            this.f3064c.sendMessage(this.f3064c.obtainMessage(6, 0, 0, obtain));
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onVolumeChanged(int i) {
            DebugLog.LogD("onVolumeChanged");
            this.f3064c.sendMessage(this.f3064c.obtainMessage(5, i, 0, null));
        }
    }
}
