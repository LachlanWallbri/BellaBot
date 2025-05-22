package com.iflytek.aiui.pro;

import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.aiui.Version;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.aiui.impl.AIUIAgentImpl;
import com.iflytek.aiui.pro.AbstractC3619i0;
import com.iflytek.cloud.SpeechConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.aiui.pro.q0 */
/* loaded from: classes4.dex */
public class C3635q0 {

    /* renamed from: l */
    private AIUIAgentImpl f2620l;

    /* renamed from: m */
    private C3629n0 f2621m;

    /* renamed from: a */
    private String f2609a = AIUIConstant.TTS_CLOUD_ENGINE_TYPE_XTTS;

    /* renamed from: b */
    private String f2610b = "x_chongchong";

    /* renamed from: c */
    private String f2611c = "50";

    /* renamed from: d */
    private String f2612d = "50";

    /* renamed from: e */
    private String f2613e = "50";

    /* renamed from: f */
    private String f2614f = "sdk";

    /* renamed from: g */
    private int f2615g = 1000;

    /* renamed from: h */
    private int f2616h = 60000;

    /* renamed from: i */
    private int f2617i = 3;

    /* renamed from: j */
    private boolean f2618j = false;

    /* renamed from: k */
    private String f2619k = "";

    /* renamed from: n */
    private Object f2622n = new Object();

    /* renamed from: o */
    private boolean f2623o = false;

    /* renamed from: p */
    private boolean f2624p = true;

    /* renamed from: q */
    private boolean f2625q = false;

    /* renamed from: r */
    private AbstractC3619i0.a f2626r = new a();

    /* renamed from: com.iflytek.aiui.pro.q0$a */
    /* loaded from: classes4.dex */
    class a implements AbstractC3619i0.a {
        a() {
        }

        @Override // com.iflytek.aiui.pro.AbstractC3619i0.a
        /* renamed from: a */
        public void mo1373a(int i, String str) {
            C3651y0.m1620b("TtsProcessor", "error occured.");
            C3635q0.this.f2620l.F(new AIUIEvent(2, i, 0, str, null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3619i0.a
        /* renamed from: b */
        public void mo1374b(boolean z) {
            if (z) {
                C3651y0.m1620b("TtsProcessor", "play completed.");
                C3635q0.this.f2620l.F(new AIUIEvent(15, 5, 0, "", null), false);
            }
        }

        @Override // com.iflytek.aiui.pro.AbstractC3619i0.a
        /* renamed from: c */
        public void mo1375c(int i, int i2, int i3) {
            Bundle bundle = new Bundle();
            bundle.putInt("percent", i);
            bundle.putInt("begpos", i2 / 2);
            bundle.putInt("endpos", i3 / 2);
            C3635q0.this.f2620l.F(new AIUIEvent(15, 4, 0, "", bundle), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3619i0.a
        /* renamed from: d */
        public void mo1376d() {
            C3651y0.m1620b("TtsProcessor", "play paused.");
            C3635q0.this.f2620l.F(new AIUIEvent(15, 2, 0, "", null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3619i0.a
        /* renamed from: e */
        public void mo1377e() {
            C3651y0.m1620b("TtsProcessor", "play resumed.");
            C3635q0.this.f2620l.F(new AIUIEvent(15, 3, 0, "", null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3619i0.a
        /* renamed from: f */
        public void mo1378f() {
            C3651y0.m1620b("TtsProcessor", "play started.");
            C3635q0.this.f2620l.F(new AIUIEvent(15, 1, 0, "", null), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.aiui.pro.q0$b */
    /* loaded from: classes4.dex */
    public class b {

        /* renamed from: a */
        public String f2628a;

        /* renamed from: b */
        public int f2629b;

        /* renamed from: c */
        public byte[] f2630c;

        /* renamed from: d */
        public int f2631d;

        /* renamed from: e */
        public int f2632e;

        public b(C3635q0 c3635q0, String str, int i, byte[] bArr, int i2, int i3, int i4, int i5, boolean z) {
            this.f2628a = TextUtils.isEmpty(str) ? "" : str;
            this.f2630c = bArr;
            this.f2631d = i3;
            this.f2632e = i4;
            this.f2629b = i5;
            m1501b();
        }

        /* renamed from: a */
        public boolean m1500a() {
            int i = this.f2629b;
            return i == 0 || 3 == i;
        }

        /* renamed from: b */
        public boolean m1501b() {
            int i = this.f2629b;
            return 2 == i || 3 == i;
        }
    }

    public C3635q0(AIUIAgentImpl aIUIAgentImpl) {
        this.f2620l = aIUIAgentImpl;
        m1498k();
    }

    /* renamed from: d */
    private b m1487d(AIUIEvent aIUIEvent) {
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
            return new b(this, string, i2, byteArray, i3, i4, i5, i, equals);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    private boolean m1488e() {
        return "control".equals(this.f2614f);
    }

    /* renamed from: f */
    private boolean m1489f() {
        return "sdk".equals(this.f2614f) || "control".equals(this.f2614f);
    }

    /* renamed from: g */
    private void m1490g(AIUIEvent aIUIEvent) {
        String str = TextUtils.isEmpty(aIUIEvent.info) ? "" : aIUIEvent.info;
        if (str.contains("sub=tts") || str.contains("IFLYTEK.tts")) {
            synchronized (this.f2622n) {
                C3629n0 c3629n0 = this.f2621m;
                if (c3629n0 != null) {
                    c3629n0.m1449y();
                }
            }
        }
    }

    /* renamed from: j */
    private void m1491j(AIUIEvent aIUIEvent) {
        try {
            if (new JSONObject(aIUIEvent.info).getJSONArray("data").getJSONObject(0).getJSONArray(AIUIConstant.KEY_CONTENT).getJSONObject(0).optInt("url", 0) == 1) {
                this.f2620l.F(aIUIEvent, false);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b m1487d = m1487d(aIUIEvent);
        if (m1487d == null) {
            C3651y0.m1622d("TtsProcessor", "audio is null.");
            return;
        }
        if (m1487d.m1500a()) {
            this.f2619k = m1487d.f2628a;
            if (m1489f()) {
                m1492l();
            }
        }
        if (m1487d.f2628a.equals(this.f2619k)) {
            if (this.f2621m != null && m1489f()) {
                this.f2621m.m1450z(m1487d.f2630c, m1487d.m1501b() ? 100 : 10, m1487d.f2631d, m1487d.f2632e);
            }
            if (Version.VersionType.MOBILE_PHONE == Version.getVersionType()) {
                this.f2620l.F(aIUIEvent, false);
            }
        }
    }

    /* renamed from: l */
    private void m1492l() {
        synchronized (this.f2622n) {
            C3629n0 c3629n0 = this.f2621m;
            if (c3629n0 != null) {
                c3629n0.m1449y();
                this.f2621m = null;
            }
            C3629n0 c3629n02 = new C3629n0(this.f2620l.z(), this.f2626r, 16000, this.f2615g, this.f2616h);
            this.f2621m = c3629n02;
            c3629n02.m1447w(this.f2617i);
            this.f2621m.m1444t(this.f2618j);
            boolean z = false;
            this.f2621m.m1446v(false);
            this.f2621m.m1445u(this.f2625q);
            if (m1489f() && !m1488e()) {
                z = true;
            }
            this.f2625q = z;
            this.f2621m.m1448x();
        }
    }

    /* renamed from: m */
    private void m1493m(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z = "1".equals(str) || "true".equals(str);
        this.f2618j = z;
        C3629n0 c3629n0 = this.f2621m;
        if (c3629n0 != null) {
            c3629n0.m1444t(z);
        }
    }

    /* renamed from: b */
    public void m1494b() {
        this.f2623o = true;
        synchronized (this.f2622n) {
            C3629n0 c3629n0 = this.f2621m;
            if (c3629n0 != null) {
                c3629n0.m1449y();
                this.f2621m = null;
            }
        }
    }

    /* renamed from: c */
    public String m1495c(String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            sb.append("vcn=");
            sb.append(this.f2610b);
            sb.append(",speed=");
            sb.append(this.f2611c);
            sb.append(",pitch=");
            sb.append(this.f2612d);
            sb.append(",volume=");
            sb.append(this.f2613e);
            if (this.f2609a.equals(AIUIConstant.TTS_CLOUD_ENGINE_TYPE_XTTS)) {
                str2 = ",ent=xtts";
                sb.append(str2);
            }
        } else {
            sb.append(str);
            if (!str.contains("vcn")) {
                sb.append(",vcn=");
                sb.append(this.f2610b);
            }
            if (!str.contains("speed")) {
                sb.append(",speed=");
                sb.append(this.f2611c);
            }
            if (!str.contains("pitch")) {
                sb.append(",pitch=");
                sb.append(this.f2612d);
            }
            if (!str.contains("volume")) {
                sb.append(",volume=");
                sb.append(this.f2613e);
            }
            if (!str.contains(AIUIConstant.KEY_TTS_CLOUD_ENGINE_TYPE)) {
                sb.append(",ent=");
                str2 = this.f2609a;
                sb.append(str2);
            }
        }
        C3651y0.m1620b("TtsProcessor", sb.toString());
        return sb.toString();
    }

    /* renamed from: h */
    public void m1496h(AIUIEvent aIUIEvent) {
        int i = aIUIEvent.eventType;
        if (i == 1) {
            m1491j(aIUIEvent);
        } else {
            if (i != 2) {
                return;
            }
            m1490g(aIUIEvent);
        }
    }

    /* renamed from: i */
    public void m1497i(AIUIMessage aIUIMessage) {
        if (this.f2623o) {
            return;
        }
        int i = aIUIMessage.arg1;
        if (i == 1) {
            m1493m(C3633p0.m1478g(aIUIMessage.params).m1465e("audio_focus"));
        } else {
            if (i == 2) {
                synchronized (this.f2622n) {
                    C3629n0 c3629n0 = this.f2621m;
                    if (c3629n0 != null) {
                        c3629n0.m1442r();
                    }
                }
                return;
            }
            if (i == 3) {
                synchronized (this.f2622n) {
                    C3629n0 c3629n02 = this.f2621m;
                    if (c3629n02 != null) {
                        c3629n02.m1443s();
                    }
                }
                return;
            }
            if (i != 4) {
                return;
            }
        }
        m1492l();
    }

    /* renamed from: k */
    public void m1498k() {
        C3651y0.m1620b("TtsProcessor", "TtsProcessor readParamsToLocal.");
        C3633p0.m1477f("tts", "engine_type", "cloud");
        this.f2610b = C3633p0.m1477f("tts", "voice_name", "x_chongchong");
        this.f2609a = C3633p0.m1477f("tts", AIUIConstant.KEY_TTS_CLOUD_ENGINE_TYPE, AIUIConstant.TTS_CLOUD_ENGINE_TYPE_XTTS);
        this.f2611c = C3633p0.m1477f("tts", "speed", "50");
        this.f2612d = C3633p0.m1477f("tts", "pitch", "50");
        this.f2613e = C3633p0.m1477f("tts", "volume", "50");
        this.f2614f = C3633p0.m1477f("tts", "play_mode", "sdk");
        this.f2625q = m1489f() && !m1488e();
        this.f2615g = C3633p0.m1473b("tts", "buffer_time", this.f2615g);
        this.f2616h = C3633p0.m1473b("tts", "min_audio_len", this.f2616h);
        this.f2617i = C3633p0.m1473b("tts", SpeechConstant.STREAM_TYPE, 3);
        this.f2618j = C3633p0.m1472a("tts", "audio_focus", false);
    }

    /* renamed from: n */
    public void m1499n() {
        C3629n0 c3629n0 = this.f2621m;
        if (c3629n0 == null || c3629n0.m1441q()) {
            this.f2625q = true;
        } else {
            this.f2621m.m1445u(true);
            this.f2621m.m1448x();
        }
    }
}
