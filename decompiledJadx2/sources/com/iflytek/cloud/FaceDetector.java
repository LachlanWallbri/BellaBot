package com.iflytek.cloud;

import android.content.Context;
import android.graphics.Bitmap;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3695ag;

/* loaded from: classes3.dex */
public class FaceDetector extends AbstractC3743v {

    /* renamed from: a */
    private static FaceDetector f2761a;

    /* renamed from: b */
    private C3695ag f2762b;

    private FaceDetector(Context context) throws SpeechError {
        try {
            this.f2762b = new C3695ag(context, null);
        } catch (UnsatisfiedLinkError unused) {
            throw new SpeechError(20021);
        }
    }

    public static synchronized FaceDetector createDetector(Context context, String str) throws SpeechError {
        FaceDetector faceDetector;
        synchronized (FaceDetector.class) {
            synchronized (sSync) {
                if (f2761a == null && SpeechUtility.getUtility() != null) {
                    f2761a = new FaceDetector(context);
                }
                faceDetector = f2761a;
            }
        }
        return faceDetector;
    }

    public static synchronized FaceDetector getDetector() {
        FaceDetector faceDetector;
        synchronized (FaceDetector.class) {
            faceDetector = f2761a;
        }
        return faceDetector;
    }

    public synchronized String detectARGB(Bitmap bitmap) {
        String m1858a;
        synchronized (this) {
            m1858a = this.f2762b != null ? this.f2762b.m1858a(bitmap) : null;
        }
        return m1858a;
        return m1858a;
    }

    public synchronized String detectGray(Bitmap bitmap) {
        String m1861b;
        synchronized (this) {
            m1861b = this.f2762b != null ? this.f2762b.m1861b(bitmap) : null;
        }
        return m1861b;
        return m1861b;
    }

    public synchronized String trackNV21(byte[] bArr, int i, int i2, int i3, int i4) {
        String m1859a;
        synchronized (this) {
            m1859a = this.f2762b != null ? this.f2762b.m1859a(bArr, i, i2, i3, i4) : null;
        }
        return m1859a;
        return m1859a;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public synchronized boolean destroy() {
        C3695ag c3695ag;
        c3695ag = this.f2762b;
        synchronized (this) {
            this.f2762b = null;
        }
        return r0;
        if (c3695ag != null) {
            c3695ag.m1860a();
        }
        boolean destroy = super.destroy();
        if (destroy) {
            synchronized (sSync) {
                f2761a = null;
            }
        }
        return destroy;
    }
}
