package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Build;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.DataUtil;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.cloud.thirdparty.z */
/* loaded from: classes3.dex */
public class HandlerC3747z extends AbstractHandlerC3740s {

    /* renamed from: a */
    public static int f3450a;

    /* renamed from: b */
    public static int f3451b;

    /* renamed from: c */
    private String f3452c;

    /* renamed from: d */
    private C3691ac f3453d;

    /* renamed from: e */
    private InterfaceC3689aa f3454e;

    /* renamed from: f */
    private ArrayList<byte[]> f3455f;

    /* renamed from: g */
    private int f3456g;

    /* renamed from: h */
    private int f3457h;

    /* renamed from: i */
    private boolean f3458i;

    /* renamed from: j */
    private boolean f3459j;

    /* renamed from: k */
    private int f3460k;

    /* renamed from: l */
    private final JSONObject f3461l;

    /* renamed from: m */
    private JSONArray f3462m;

    /* renamed from: n */
    private int f3463n;

    /* renamed from: o */
    private int f3464o;

    /* renamed from: p */
    private String f3465p;

    /* renamed from: q */
    private int f3466q;

    public HandlerC3747z(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3452c = "";
        this.f3453d = null;
        this.f3454e = null;
        this.f3455f = null;
        this.f3456g = 0;
        this.f3457h = 0;
        this.f3458i = false;
        this.f3459j = false;
        this.f3460k = 0;
        this.f3461l = new JSONObject();
        this.f3462m = null;
        this.f3463n = -1;
        this.f3464o = 100;
        this.f3465p = null;
        this.f3466q = 0;
        this.f3453d = new C3691ac();
        this.f3455f = new ArrayList<>();
        setParams(c3692ad);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onParseParam() {
        this.f3463n = getParam().m1816a(SpeechConstant.TTS_BUFFER_TIME, this.f3463n);
        this.f3464o = getParam().m1816a("tts_proc_scale", this.f3464o);
        super.onParseParam();
    }

    /* renamed from: a */
    public void m2276a(String str, InterfaceC3689aa interfaceC3689aa) {
        this.f3452c = str;
        this.f3454e = interfaceC3689aa;
        if (str == null || TextUtils.isEmpty(str)) {
            exit(new SpeechError(20009));
            return;
        }
        this.f3458i = getParam().m1825a(SpeechConstant.TTS_SPELL_INFO, false);
        this.f3459j = getParam().m1825a("audio_info", this.f3459j);
        start();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getTextEncoding() {
        return getParam().m1827b(SpeechConstant.TEXT_ENCODING, DataUtil.UNICODE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        int i = message.what;
        if (i == 0) {
            m2275a();
        } else if (i == 1) {
            m2277b();
        } else {
            if (i != 5) {
                return;
            }
            m2278c();
        }
    }

    /* renamed from: a */
    protected void m2275a() throws Exception {
        DebugLog.LogD("tts msg start:" + System.currentTimeMillis());
        String m1833e = getParam().m1833e("engine_type");
        boolean m1825a = getParam().m1825a(SpeechConstant.NET_CHECK, true);
        if (("cloud".equals(m1833e) || SpeechConstant.TYPE_DISTRIBUTED.equals(m1833e)) && m1825a) {
            NetworkUtil.checkNetwork(this.mContext);
        }
        sendMsg(1);
    }

    /* renamed from: b */
    protected void m2277b() throws Exception {
        PerfLogger.appendInfo(PerfLogger.SDK_SESSION_BIGNE, null);
        int sessionBegin = this.f3453d.sessionBegin(this.mContext, null, this);
        if (sessionBegin == 0) {
            byte[] bytes = this.f3452c.getBytes(getTextEncoding());
            if (DataUtil.UNICODE.equals(getTextEncoding())) {
                if (Build.VERSION.SDK_INT >= 27) {
                    byte[] bArr = new byte[bytes.length];
                    System.arraycopy(bytes, 0, bArr, 0, bytes.length);
                    for (int i = 0; i < bytes.length / 2; i++) {
                        int i2 = i * 2;
                        int i3 = i2 + 1;
                        bArr[i3] = bytes[i2];
                        bArr[i2] = bytes[i3];
                    }
                    this.f3453d.m1805a(bArr);
                } else {
                    byte[] bArr2 = new byte[bytes.length - 2];
                    System.arraycopy(bytes, 2, bArr2, 0, bytes.length - 2);
                    this.f3453d.m1805a(bArr2);
                }
            } else {
                this.f3453d.m1805a(bytes);
            }
            setStatus(AbstractHandlerC3740s.b.waitresult);
            sendMsg(5);
            updateTimeoutMsg();
            return;
        }
        this.f3466q++;
        if (this.f3466q > 40) {
            throw new SpeechError(sessionBegin);
        }
        if (isRunning()) {
            sendMsg(1, AbstractHandlerC3740s.a.normal, false, 15);
        }
    }

    /* renamed from: c */
    protected void m2278c() throws Exception {
        int m1807b;
        int i;
        PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
        if (!this.f3453d.m1810d()) {
            byte[] m1806a = this.f3453d.m1806a();
            getSessionID();
            if (m1806a != null && this.f3454e != null) {
                this.f3460k += m1806a.length;
                if (Build.VERSION.SDK_INT >= 27) {
                    m1807b = (this.f3453d.m1807b() / 2) - 2;
                } else {
                    m1807b = (this.f3453d.m1807b() / 2) - 1;
                }
                if (m1807b < 0) {
                    DebugLog.LogD("get audio index value error: " + m1807b);
                    m1807b = 0;
                }
                if (this.f3458i) {
                    String m1809c = this.f3453d.m1809c();
                    if (!TextUtils.isEmpty(m1809c)) {
                        if (this.f3462m == null) {
                            this.f3462m = new JSONArray();
                        }
                        this.f3462m.put(m1809c);
                    }
                }
                if (this.f3463n < 0 && (i = this.f3456g) != 0 && m1807b != i && this.f3455f.size() > 0) {
                    DebugLog.LogI("tts msc get audio beg=" + this.f3457h + ", end=" + this.f3456g);
                    m2274d();
                }
                updateTimeoutMsg();
                this.f3456g = m1807b;
                this.f3455f.add(m1806a);
                if (this.f3463n >= 0) {
                    m2274d();
                }
                sendMsg(5, AbstractHandlerC3740s.a.normal, false, 0);
                return;
            }
            sendMsg(5, AbstractHandlerC3740s.a.normal, false, 10);
            return;
        }
        DebugLog.LogD("tts msc get last audio");
        if (this.f3454e != null) {
            if (this.f3459j) {
                this.f3461l.put("audio_len", this.f3460k);
            }
            JSONArray jSONArray = this.f3462m;
            if (jSONArray != null) {
                this.f3461l.put("spell_info", jSONArray);
                this.f3462m = null;
            }
            this.f3454e.mo1770a(this.f3455f, this.f3464o, this.f3457h, this.f3452c.length() - 1, this.f3461l.length() > 0 ? this.f3461l.toString() : null);
        }
        exit(null);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void cancel(boolean z) {
        InterfaceC3689aa interfaceC3689aa;
        if (z && isRunning() && (interfaceC3689aa = this.f3454e) != null) {
            interfaceC3689aa.mo1769a(new SpeechError(20017));
        }
        super.cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        f3450a = this.f3453d.m1804a(AbstractHandlerC3740s.TAG_UPFLOW);
        f3451b = this.f3453d.m1804a(AbstractHandlerC3740s.TAG_DOWNFLOW);
        getSessionID();
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        if (this.f3454e == null) {
            this.f3453d.sessionEnd("user abort");
        } else if (speechError != null) {
            this.f3453d.sessionEnd("error" + speechError.getErrorCode());
            DebugLog.LogD("QTts Error Code = " + speechError.getErrorCode());
        } else {
            this.f3453d.sessionEnd("success");
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        super.onEnd(speechError);
        if (this.f3454e != null) {
            if (this.mUserCancel) {
                DebugLog.LogD("MscSynthesizer#onCancel");
            } else {
                DebugLog.LogD("MscSynthesizer#onEnd");
                this.f3454e.mo1769a(speechError);
            }
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.f3453d.getClientID();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        if (TextUtils.isEmpty(this.f3465p)) {
            this.f3465p = this.f3453d.m1811e();
        }
        return this.f3465p;
    }

    /* renamed from: d */
    private void m2274d() throws SpeechError, JSONException {
        int i = this.f3464o;
        int min = Math.min(i - 1, (this.f3456g * i) / this.f3452c.length());
        if (this.f3459j) {
            this.f3461l.put("audio_len", this.f3460k);
        }
        JSONArray jSONArray = this.f3462m;
        if (jSONArray != null) {
            this.f3461l.put("spell_info", jSONArray);
            this.f3462m = null;
        }
        this.f3454e.mo1770a(this.f3455f, min, this.f3457h, this.f3456g, this.f3461l.length() > 0 ? this.f3461l.toString() : null);
        this.f3455f = new ArrayList<>();
        this.f3457h = Math.min(this.f3456g + 1, this.f3452c.length() - 1);
    }
}
