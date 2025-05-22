package com.iflytek.aiui.pro;

import android.content.Context;
import com.iflytek.aiui.data.audio.player.PcmBuffer;
import com.iflytek.aiui.data.audio.player.PcmPlayer;
import com.iflytek.aiui.error.AIUIError;
import com.iflytek.aiui.pro.AbstractC3619i0;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: com.iflytek.aiui.pro.n0 */
/* loaded from: classes4.dex */
public class C3629n0 extends AbstractC3619i0 {

    /* renamed from: c */
    private PcmPlayer f2582c;

    /* renamed from: d */
    private PcmBuffer f2583d;

    /* renamed from: e */
    private int f2584e;

    /* renamed from: f */
    private boolean f2585f;

    /* renamed from: g */
    private boolean f2586g;

    /* renamed from: h */
    private boolean f2587h;

    /* renamed from: i */
    private PcmPlayer.PcmPlayerListener f2588i;

    /* renamed from: com.iflytek.aiui.pro.n0$a */
    /* loaded from: classes4.dex */
    class a implements PcmPlayer.PcmPlayerListener {
        a() {
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onError(AIUIError aIUIError) {
            if (C3629n0.this.f2553b != null) {
                C3629n0.this.f2553b.mo1373a(aIUIError.getErrorCode(), aIUIError.getDes());
            }
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onPaused() {
            if (C3629n0.this.f2553b != null) {
                C3629n0.this.f2553b.mo1376d();
            }
            C3629n0.this.f2586g = false;
            C3629n0.this.m1372a(AbstractC3619i0.b.STATE_PAUSED);
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onPercent(int i, int i2, int i3) {
            if (!C3629n0.this.f2586g) {
                C3629n0.this.f2586g = true;
                C3629n0.this.m1372a(AbstractC3619i0.b.STATE_PLAYING);
            }
            if (C3629n0.this.f2553b != null) {
                C3629n0.this.f2553b.mo1375c(i, i2, i3);
            }
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onResume() {
            if (C3629n0.this.f2553b != null) {
                C3629n0.this.f2553b.mo1377e();
            }
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onStoped(boolean z) {
            if (C3629n0.this.f2553b != null) {
                C3629n0.this.f2553b.mo1374b(z);
            }
            C3629n0.this.f2586g = false;
            C3629n0.this.m1372a(AbstractC3619i0.b.STATE_STOPPED);
        }
    }

    public C3629n0(Context context, AbstractC3619i0.a aVar, int i, int i2, int i3) {
        super(aVar);
        this.f2585f = false;
        this.f2586g = false;
        this.f2587h = false;
        this.f2588i = new a();
        this.f2584e = i2;
        int max = Math.max(1, i3 / 1000);
        this.f2582c = new PcmPlayer(context);
        this.f2583d = new PcmBuffer(context, i, max, "", 100);
    }

    /* renamed from: q */
    public boolean m1441q() {
        return this.f2585f;
    }

    /* renamed from: r */
    public void m1442r() {
        PcmPlayer pcmPlayer = this.f2582c;
        if (pcmPlayer != null) {
            pcmPlayer.pause();
        }
    }

    /* renamed from: s */
    public void m1443s() {
        PcmPlayer pcmPlayer = this.f2582c;
        if (pcmPlayer != null) {
            pcmPlayer.resume();
        }
    }

    /* renamed from: t */
    public void m1444t(boolean z) {
        PcmPlayer pcmPlayer = this.f2582c;
        if (pcmPlayer != null) {
            pcmPlayer.setRequestFocus(z);
        }
    }

    /* renamed from: u */
    public void m1445u(boolean z) {
        this.f2587h = z;
    }

    /* renamed from: v */
    public void m1446v(boolean z) {
        PcmPlayer pcmPlayer = this.f2582c;
        if (pcmPlayer != null) {
            pcmPlayer.setIsFadeOut(z);
        }
    }

    /* renamed from: w */
    public void m1447w(int i) {
        PcmPlayer pcmPlayer = this.f2582c;
        if (pcmPlayer != null) {
            pcmPlayer.setStreamType(i);
        }
    }

    /* renamed from: x */
    public int m1448x() {
        PcmPlayer pcmPlayer = this.f2582c;
        if (pcmPlayer == null || this.f2584e != 0 || this.f2585f || !pcmPlayer.play(this.f2583d, this.f2588i)) {
            return -1;
        }
        this.f2585f = true;
        AbstractC3619i0.a aVar = this.f2553b;
        if (aVar == null) {
            return 0;
        }
        aVar.mo1378f();
        return 0;
    }

    /* renamed from: y */
    public void m1449y() {
        PcmPlayer pcmPlayer = this.f2582c;
        if (pcmPlayer != null) {
            pcmPlayer.stop();
        }
    }

    /* renamed from: z */
    public int m1450z(byte[] bArr, int i, int i2, int i3) {
        PcmPlayer pcmPlayer;
        if (this.f2583d == null) {
            return 0;
        }
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(bArr);
        try {
            this.f2583d.writeStream(arrayList, i, i2, i3);
            int i4 = this.f2584e;
            if (i4 == 0 || !this.f2583d.readyToPlay(i4) || !this.f2587h || (pcmPlayer = this.f2582c) == null || this.f2585f || !pcmPlayer.play(this.f2583d, this.f2588i)) {
                return 0;
            }
            this.f2585f = true;
            AbstractC3619i0.a aVar = this.f2553b;
            if (aVar == null) {
                return 0;
            }
            aVar.mo1378f();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
