package com.iflytek.aiui.pro;

import android.content.Context;
import com.iflytek.aiui.data.audio.player.PcmBuffer;
import com.iflytek.aiui.data.audio.player.PcmPlayer;
import com.iflytek.aiui.error.AIUIError;
import com.iflytek.aiui.pro.AbstractC3578ah;
import java.io.IOException;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.aj */
/* loaded from: classes.dex */
public class C3580aj extends AbstractC3578ah {

    /* renamed from: e */
    private PcmPlayer f2311e;

    /* renamed from: f */
    private PcmBuffer f2312f;

    /* renamed from: g */
    private int f2313g;

    /* renamed from: h */
    private int f2314h;

    /* renamed from: i */
    private boolean f2315i;

    /* renamed from: j */
    private boolean f2316j;

    /* renamed from: k */
    private PcmPlayer.PcmPlayerListener f2317k;

    public C3580aj(Context context, AbstractC3578ah.a aVar, int i, int i2, int i3) {
        super(aVar);
        this.f2315i = false;
        this.f2316j = false;
        this.f2317k = new PcmPlayer.PcmPlayerListener() { // from class: com.iflytek.aiui.pro.aj.1
            @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
            public void onError(AIUIError aIUIError) {
                if (C3580aj.this.f2301d != null) {
                    C3580aj.this.f2301d.mo987a(aIUIError.getErrorCode(), aIUIError.getDes());
                }
            }

            @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
            public void onPaused() {
                if (C3580aj.this.f2301d != null) {
                    C3580aj.this.f2301d.mo989b();
                }
                C3580aj.this.f2316j = false;
                C3580aj.this.m984a(AbstractC3578ah.b.STATE_PAUSED);
            }

            @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
            public void onPercent(int i4, int i5, int i6) {
                if (!C3580aj.this.f2316j) {
                    C3580aj.this.f2316j = true;
                    C3580aj.this.m984a(AbstractC3578ah.b.STATE_PLAYING);
                }
                if (C3580aj.this.f2301d != null) {
                    C3580aj.this.f2301d.mo986a(i4, i5, i6);
                }
            }

            @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
            public void onResume() {
                if (C3580aj.this.f2301d != null) {
                    C3580aj.this.f2301d.mo990c();
                }
            }

            @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
            public void onStoped(boolean z) {
                if (C3580aj.this.f2301d != null) {
                    C3580aj.this.f2301d.mo988a(z);
                }
                C3580aj.this.f2316j = false;
                C3580aj.this.m984a(AbstractC3578ah.b.STATE_STOPPED);
            }
        };
        this.f2300c = context;
        this.f2313g = i;
        this.f2314h = i2;
        int max = Math.max(1, i3 / 1000);
        this.f2311e = new PcmPlayer(context);
        this.f2312f = new PcmBuffer(context, i, max, "", 100);
    }

    /* renamed from: a */
    public int m1007a() {
        PcmPlayer pcmPlayer = this.f2311e;
        if (pcmPlayer == null || this.f2314h != 0 || this.f2315i || !pcmPlayer.play(this.f2312f, this.f2317k)) {
            return -1;
        }
        this.f2315i = true;
        if (this.f2301d == null) {
            return 0;
        }
        this.f2301d.mo985a();
        return 0;
    }

    /* renamed from: a */
    public int m1008a(byte[] bArr, int i, int i2, int i3) {
        if (this.f2312f == null) {
            return 0;
        }
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(bArr);
        try {
            this.f2312f.writeStream(arrayList, i, i2, i3);
            if (this.f2314h == 0 || !this.f2312f.readyToPlay(this.f2314h) || this.f2311e == null || this.f2315i || !this.f2311e.play(this.f2312f, this.f2317k)) {
                return 0;
            }
            this.f2315i = true;
            if (this.f2301d == null) {
                return 0;
            }
            this.f2301d.mo985a();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public void m1009a(int i) {
        PcmPlayer pcmPlayer = this.f2311e;
        if (pcmPlayer != null) {
            pcmPlayer.setStreamType(i);
        }
    }

    /* renamed from: a */
    public void m1010a(boolean z) {
        PcmPlayer pcmPlayer = this.f2311e;
        if (pcmPlayer != null) {
            pcmPlayer.setRequestFocus(z);
        }
    }

    /* renamed from: b */
    public void m1011b() {
        PcmPlayer pcmPlayer = this.f2311e;
        if (pcmPlayer != null) {
            pcmPlayer.stop();
        }
    }

    /* renamed from: b */
    public void m1012b(boolean z) {
        PcmPlayer pcmPlayer = this.f2311e;
        if (pcmPlayer != null) {
            pcmPlayer.setIsFadeOut(z);
        }
    }

    /* renamed from: c */
    public void m1013c() {
        PcmPlayer pcmPlayer = this.f2311e;
        if (pcmPlayer != null) {
            pcmPlayer.pause();
        }
    }

    /* renamed from: d */
    public void m1014d() {
        PcmPlayer pcmPlayer = this.f2311e;
        if (pcmPlayer != null) {
            pcmPlayer.resume();
        }
    }
}
