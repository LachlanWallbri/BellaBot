package com.iflytek.aiui.pro;

import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.aiui.Version;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.aiui.impl.AIUIAgentImpl;
import com.iflytek.aiui.pro.AbstractC3578ah;
import com.iflytek.cloud.SpeechConstant;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.am */
/* loaded from: classes.dex */
public class C3583am {

    /* renamed from: m */
    private AIUIAgentImpl f2335m;

    /* renamed from: n */
    private C3580aj f2336n;

    /* renamed from: a */
    private String f2323a = "cloud";

    /* renamed from: b */
    private String f2324b = AIUIConstant.TTS_CLOUD_ENGINE_TYPE_XTTS;

    /* renamed from: c */
    private String f2325c = "x_chongchong";

    /* renamed from: d */
    private String f2326d = "50";

    /* renamed from: e */
    private String f2327e = "50";

    /* renamed from: f */
    private String f2328f = "50";

    /* renamed from: g */
    private String f2329g = "sdk";

    /* renamed from: h */
    private int f2330h = 1000;

    /* renamed from: i */
    private int f2331i = 60000;

    /* renamed from: j */
    private int f2332j = 3;

    /* renamed from: k */
    private boolean f2333k = false;

    /* renamed from: l */
    private String f2334l = "";

    /* renamed from: o */
    private Object f2337o = new Object();

    /* renamed from: p */
    private boolean f2338p = false;

    /* renamed from: q */
    private AbstractC3578ah.a f2339q = new AbstractC3578ah.a() { // from class: com.iflytek.aiui.pro.am.1
        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo985a() {
            C3589as.m1059a("TtsProcessor", "play started.");
            C3583am.this.f2335m.m853a(new AIUIEvent(15, 1, 0, "", null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo986a(int i, int i2, int i3) {
            Bundle bundle = new Bundle();
            bundle.putInt("percent", i);
            bundle.putInt("begpos", i2 / 2);
            bundle.putInt("endpos", i3 / 2);
            C3583am.this.f2335m.m853a(new AIUIEvent(15, 4, 0, "", bundle), true);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo987a(int i, String str) {
            C3589as.m1059a("TtsProcessor", "error occured.");
            C3583am.this.f2335m.m853a(new AIUIEvent(2, i, 0, str, null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo988a(boolean z) {
            if (z) {
                C3589as.m1059a("TtsProcessor", "play completed.");
                C3583am.this.f2335m.m853a(new AIUIEvent(15, 5, 0, "", null), false);
            }
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: b */
        public void mo989b() {
            C3589as.m1059a("TtsProcessor", "play paused.");
            C3583am.this.f2335m.m853a(new AIUIEvent(15, 2, 0, "", null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: c */
        public void mo990c() {
            C3589as.m1059a("TtsProcessor", "play resumed.");
            C3583am.this.f2335m.m853a(new AIUIEvent(15, 3, 0, "", null), false);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.aiui.pro.am$a */
    /* loaded from: classes.dex */
    public class a {

        /* renamed from: a */
        public String f2341a;

        /* renamed from: b */
        public int f2342b;

        /* renamed from: c */
        public int f2343c;

        /* renamed from: d */
        public byte[] f2344d;

        /* renamed from: e */
        public int f2345e;

        /* renamed from: f */
        public int f2346f;

        /* renamed from: g */
        public int f2347g;

        /* renamed from: h */
        public boolean f2348h;

        public a(String str, int i, byte[] bArr, int i2, int i3, int i4, int i5, boolean z) {
            this.f2341a = TextUtils.isEmpty(str) ? "" : str;
            this.f2342b = i;
            this.f2344d = bArr;
            this.f2345e = i2;
            this.f2346f = i3;
            this.f2347g = i4;
            this.f2343c = i5;
            this.f2348h = z;
            if (m1045b()) {
                this.f2345e = 100;
            }
        }

        /* renamed from: a */
        public boolean m1044a() {
            int i = this.f2343c;
            return i == 0 || 3 == i;
        }

        /* renamed from: b */
        public boolean m1045b() {
            int i = this.f2343c;
            return 2 == i || 3 == i;
        }
    }

    public C3583am(AIUIAgentImpl aIUIAgentImpl) {
        this.f2335m = aIUIAgentImpl;
        m1039a();
    }

    /* renamed from: b */
    private void m1034b(AIUIEvent aIUIEvent) {
        String str = TextUtils.isEmpty(aIUIEvent.info) ? "" : aIUIEvent.info;
        if (str.contains("sub=tts") || str.contains("IFLYTEK.tts")) {
            synchronized (this.f2337o) {
                if (this.f2336n != null) {
                    this.f2336n.m1011b();
                }
            }
        }
    }

    /* renamed from: b */
    private void m1035b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z = "1".equals(str) || "true".equals(str);
        this.f2333k = z;
        C3580aj c3580aj = this.f2336n;
        if (c3580aj != null) {
            c3580aj.m1010a(z);
        }
    }

    /* renamed from: c */
    private void m1036c(AIUIEvent aIUIEvent) {
        a m1037d = m1037d(aIUIEvent);
        if (m1037d == null) {
            C3589as.m1063b("TtsProcessor", "audio is null.");
            return;
        }
        if (m1037d.m1044a()) {
            this.f2334l = m1037d.f2341a;
            if ("sdk".equals(this.f2329g)) {
                synchronized (this.f2337o) {
                    if (this.f2336n != null) {
                        this.f2336n.m1011b();
                        this.f2336n = null;
                    }
                    C3580aj c3580aj = new C3580aj(this.f2335m.m855b(), this.f2339q, 16000, this.f2330h, this.f2331i);
                    this.f2336n = c3580aj;
                    c3580aj.m1009a(this.f2332j);
                    this.f2336n.m1010a(this.f2333k);
                    this.f2336n.m1012b(false);
                    this.f2336n.m1007a();
                }
            }
        }
        if (m1037d.f2341a.equals(this.f2334l)) {
            if (this.f2336n != null && "sdk".equals(this.f2329g)) {
                this.f2336n.m1008a(m1037d.f2344d, m1037d.f2345e, m1037d.f2346f, m1037d.f2347g);
            }
            if (Version.VersionType.MOBILE_PHONE == Version.getVersionType()) {
                this.f2335m.m853a(aIUIEvent, false);
            }
        }
    }

    /* renamed from: d */
    private a m1037d(AIUIEvent aIUIEvent) {
        try {
            JSONObject jSONObject = new JSONObject(aIUIEvent.info).getJSONArray("data").getJSONObject(0);
            JSONObject jSONObject2 = jSONObject.getJSONObject("params");
            JSONObject jSONObject3 = jSONObject.getJSONArray(AIUIConstant.KEY_CONTENT).getJSONObject(0);
            if (!"tts".equals(jSONObject2.optString("sub")) || !jSONObject3.has(InternalConstant.KEY_CONTENT_ID)) {
                return null;
            }
            String string = aIUIEvent.data.getString("sid");
            byte[] byteArray = aIUIEvent.data.getByteArray(jSONObject3.getString(InternalConstant.KEY_CONTENT_ID));
            int i = jSONObject3.getInt(InternalConstant.KEY_DTS);
            int i2 = jSONObject3.getInt("frame_id");
            int i3 = jSONObject3.getInt("text_percent");
            int i4 = jSONObject3.getInt("text_start");
            int i5 = jSONObject3.getInt("text_end");
            boolean equals = "1".equals(jSONObject3.getString("cancel"));
            jSONObject3.remove("text_percent");
            jSONObject3.remove("text_start");
            jSONObject3.remove("text_end");
            aIUIEvent.data.putInt("percent", i3);
            aIUIEvent.data.putInt("begpos", i4 / 2);
            aIUIEvent.data.putInt("endpos", i5 / 2);
            return new a(string, i2, byteArray, i3, i4, i5, i, equals);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public String m1038a(String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            sb.append("vcn=");
            sb.append(this.f2325c);
            sb.append(",speed=");
            sb.append(this.f2326d);
            sb.append(",pitch=");
            sb.append(this.f2327e);
            sb.append(",volume=");
            sb.append(this.f2328f);
            if (this.f2324b.equals(AIUIConstant.TTS_CLOUD_ENGINE_TYPE_XTTS)) {
                str2 = ",ent=xtts";
                sb.append(str2);
            }
        } else {
            sb.append(str);
            if (!str.contains("vcn")) {
                sb.append(",vcn=");
                sb.append(this.f2325c);
            }
            if (!str.contains("speed")) {
                sb.append(",speed=");
                sb.append(this.f2326d);
            }
            if (!str.contains("pitch")) {
                sb.append(",pitch=");
                sb.append(this.f2327e);
            }
            if (!str.contains("volume")) {
                sb.append(",volume=");
                sb.append(this.f2328f);
            }
            if (!str.contains(AIUIConstant.KEY_TTS_CLOUD_ENGINE_TYPE)) {
                sb.append(",ent=");
                str2 = this.f2324b;
                sb.append(str2);
            }
        }
        C3589as.m1059a("TtsProcessor", sb.toString());
        return sb.toString();
    }

    /* renamed from: a */
    public void m1039a() {
        C3589as.m1059a("TtsProcessor", "TtsProcessor readParamsToLocal.");
        this.f2323a = C3582al.m1025a("tts", "engine_type", "cloud");
        this.f2325c = C3582al.m1025a("tts", "voice_name", "x_chongchong");
        this.f2324b = C3582al.m1025a("tts", AIUIConstant.KEY_TTS_CLOUD_ENGINE_TYPE, AIUIConstant.TTS_CLOUD_ENGINE_TYPE_XTTS);
        this.f2326d = C3582al.m1025a("tts", "speed", "50");
        this.f2327e = C3582al.m1025a("tts", "pitch", "50");
        this.f2328f = C3582al.m1025a("tts", "volume", "50");
        this.f2329g = C3582al.m1025a("tts", "play_mode", "sdk");
        this.f2330h = C3582al.m1022a("tts", "buffer_time", this.f2330h);
        this.f2331i = C3582al.m1022a("tts", "min_audio_len", this.f2331i);
        this.f2332j = C3582al.m1022a("tts", SpeechConstant.STREAM_TYPE, 3);
        this.f2333k = C3582al.m1027a("tts", "audio_focus", false);
    }

    /* renamed from: a */
    public void m1040a(AIUIEvent aIUIEvent) {
        int i = aIUIEvent.eventType;
        if (i == 1) {
            m1036c(aIUIEvent);
        } else {
            if (i != 2) {
                return;
            }
            m1034b(aIUIEvent);
        }
    }

    /* renamed from: a */
    public void m1041a(AIUIMessage aIUIMessage) {
        if (this.f2338p) {
            return;
        }
        int i = aIUIMessage.arg1;
        if (i == 1) {
            m1035b(C3582al.m1032d(aIUIMessage.params).m1015a("audio_focus"));
        } else {
            if (i == 2) {
                synchronized (this.f2337o) {
                    if (this.f2336n != null) {
                        this.f2336n.m1013c();
                    }
                }
                return;
            }
            if (i == 3) {
                synchronized (this.f2337o) {
                    if (this.f2336n != null) {
                        this.f2336n.m1014d();
                    }
                }
                return;
            }
            if (i != 4) {
                return;
            }
        }
        synchronized (this.f2337o) {
            if (this.f2336n != null) {
                this.f2336n.m1011b();
            }
        }
    }

    /* renamed from: b */
    public boolean m1042b() {
        return this.f2323a.equals("local");
    }

    /* renamed from: c */
    public void m1043c() {
        this.f2338p = true;
        synchronized (this.f2337o) {
            if (this.f2336n != null) {
                this.f2336n.m1011b();
                this.f2336n = null;
            }
        }
    }
}
