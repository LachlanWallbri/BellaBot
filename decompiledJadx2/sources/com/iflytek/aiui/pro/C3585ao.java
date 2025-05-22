package com.iflytek.aiui.pro;

import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.pro.AbstractC3578ah;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ao */
/* loaded from: classes.dex */
public class C3585ao {

    /* renamed from: com.iflytek.aiui.pro.ao$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 implements AbstractC3578ah.a {
        AnonymousClass1() {
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo985a() {
            C3591au.a("TtsProcessor", "play started.");
            C3585ao.a(C3585ao.this).m853a(new AIUIEvent(15, 1, 0, "", null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo986a(int i, int i2, int i3) {
            Bundle bundle = new Bundle();
            bundle.putInt("percent", i);
            bundle.putInt("begpos", i2 / 2);
            bundle.putInt("endpos", i3 / 2);
            C3585ao.a(C3585ao.this).m853a(new AIUIEvent(15, 4, 0, "", bundle), true);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo987a(int i, String str) {
            C3591au.a("TtsProcessor", "error occured.");
            C3585ao.a(C3585ao.this).m853a(new AIUIEvent(2, i, 0, str, null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: a */
        public void mo988a(boolean z) {
            if (z) {
                C3591au.a("TtsProcessor", "play completed.");
                C3585ao.a(C3585ao.this).m853a(new AIUIEvent(15, 5, 0, "", null), false);
            }
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: b */
        public void mo989b() {
            C3591au.a("TtsProcessor", "play paused.");
            C3585ao.a(C3585ao.this).m853a(new AIUIEvent(15, 2, 0, "", null), false);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3578ah.a
        /* renamed from: c */
        public void mo990c() {
            C3591au.a("TtsProcessor", "play resumed.");
            C3585ao.a(C3585ao.this).m853a(new AIUIEvent(15, 3, 0, "", null), false);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.ao$a */
    /* loaded from: classes3.dex */
    private class a {

        /* renamed from: a */
        public String f2357a;

        /* renamed from: b */
        public int f2358b;

        /* renamed from: c */
        public int f2359c;

        /* renamed from: d */
        public byte[] f2360d;

        /* renamed from: e */
        public int f2361e;

        /* renamed from: f */
        public int f2362f;

        /* renamed from: g */
        public int f2363g;

        /* renamed from: h */
        public boolean f2364h;

        public a(String str, int i, byte[] bArr, int i2, int i3, int i4, int i5, boolean z) {
            this.f2357a = TextUtils.isEmpty(str) ? "" : str;
            this.f2358b = i;
            this.f2360d = bArr;
            this.f2361e = i2;
            this.f2362f = i3;
            this.f2363g = i4;
            this.f2359c = i5;
            this.f2364h = z;
            if (m1052b()) {
                this.f2361e = 100;
            }
        }

        /* renamed from: a */
        public boolean m1051a() {
            int i = this.f2359c;
            return i == 0 || 3 == i;
        }

        /* renamed from: b */
        public boolean m1052b() {
            int i = this.f2359c;
            return 2 == i || 3 == i;
        }
    }

    /* renamed from: a */
    public static int m1050a(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[4]);
        for (int i2 = i; i2 < i + 4; i2++) {
            wrap.put(bArr[i2]);
        }
        wrap.flip();
        return wrap.getInt();
    }
}
