package com.iflytek.aiui.vad.sdk;

import com.iflytek.aiui.vad.sdk.Vad;

/* loaded from: classes4.dex */
public class EVad extends Vad {
    private static final String TAG = "EVad";
    private static EVad sINSTANCE;

    private native int evad_create(String str, byte[] bArr, int i);

    private native int evad_destroy(long j);

    private native int evad_detect(long j, byte[] bArr, int i);

    private native int evad_request_auth(long j);

    private native int evad_reset(long j);

    private native int evad_set_parameter(long j, String str, String str2);

    private native String evad_version();

    static {
        System.loadLibrary("evad");
    }

    private EVad(String str, byte[] bArr, int i, Vad.VadListener vadListener) {
        this.mListener = vadListener;
        int evad_create = evad_create(str, bArr, i);
        if (evad_create != 0) {
            this.mListener.onError(evad_create, VadConstant.getDes(evad_create));
        } else {
            setParameter(VadConstant.KEY_BOS_TIMEOUT, "5000");
            setParameter(VadConstant.KEY_EOS_TIMEOUT, "1000");
        }
    }

    public static synchronized EVad createInstance(String str, byte[] bArr, int i, Vad.VadListener vadListener) {
        EVad eVad;
        synchronized (EVad.class) {
            if (sINSTANCE == null) {
                sINSTANCE = new EVad(str, bArr, i, vadListener);
            }
            eVad = sINSTANCE;
        }
        return eVad;
    }

    @Override // com.iflytek.aiui.vad.sdk.Vad
    public synchronized int requestAuth() {
        return evad_request_auth(this.mHandle);
    }

    @Override // com.iflytek.aiui.vad.sdk.Vad
    public synchronized int setParameter(String str, String str2) {
        return evad_set_parameter(this.mHandle, str, str2);
    }

    @Override // com.iflytek.aiui.vad.sdk.Vad
    public synchronized int detect(byte[] bArr, int i) {
        return evad_detect(this.mHandle, bArr, i);
    }

    @Override // com.iflytek.aiui.vad.sdk.Vad
    public synchronized int reset() {
        return evad_reset(this.mHandle);
    }

    @Override // com.iflytek.aiui.vad.sdk.Vad
    public synchronized void destroy() {
        evad_destroy(this.mHandle);
        sINSTANCE = null;
    }

    @Override // com.iflytek.aiui.vad.sdk.Vad
    public String getVersion() {
        return evad_version();
    }
}
