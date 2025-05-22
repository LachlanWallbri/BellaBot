package com.iflytek.cloud.util;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.C3692ad;
import com.iflytek.cloud.thirdparty.C3711aw;
import com.iflytek.cloud.thirdparty.C3712ax;
import java.util.LinkedHashMap;

/* loaded from: classes3.dex */
public abstract class AudioDetector {
    public static final int DEF_BOS = 2000;
    public static final String DEF_ENGINE_TYPE = "fixfront";
    public static final int DEF_EOS = 700;
    public static final int DEF_RATE = 16000;
    public static final String EARLY_START = "early_start";
    public static final int MAX_BUF_LEN = 32768;
    public static final String REDUCE_FLOW = "vad_reduce_flow";
    public static final String RESET_SENTENCE = "reset_sentence";
    public static final String RES_PATH = "vad_res_path";
    public static final String SUB_TIMEOUT = "sub_timeout";
    public static final String THRESHOLD = "threshold";
    public static final String TYPE_FIXFRONT = "fixfront";
    public static final String TYPE_META = "meta";
    public static final String VAD_ENGINE = "vad_engine";

    /* renamed from: a */
    protected static AudioDetector f3486a;

    /* renamed from: b */
    protected static Object f3487b = new Object();

    /* loaded from: classes3.dex */
    public static class DetectorResult {
        public static final int STATUS_BOS = 3;
        public static final int STATUS_EOS = 2;
        public static final int STATUS_OK = 0;
        public static final int STATUS_START = 1;
        public static final int STATUS_TIMEOUT = 4;
        public static final int SUB_END = 2;
        public static final int SUB_OK = 0;
        public static final int SUB_START = 1;
        public static final int SUB_STARTEND = 3;
        public byte[] buffer = null;
        public int offset = 0;
        public int length = 0;
        public int status = 0;
        public int sub = 0;
        public final LinkedHashMap<Integer, Integer> subs = new LinkedHashMap<>();
        public int volume = 0;
        public boolean voice = false;
        public int quality = 0;
        public int start = 0;
        public int end = 0;
        public int error = 0;
        public float confidence = 1.0f;
    }

    public abstract boolean destroy();

    public abstract DetectorResult detect(byte[] bArr, int i, int i2, boolean z);

    public abstract void reset();

    public abstract void setParameter(String str, String str2);

    /* JADX INFO: Access modifiers changed from: protected */
    public AudioDetector(Context context, String str) {
    }

    public static AudioDetector createDetector(Context context, String str) {
        DebugLog.LogD("createDetector enter, context: " + context + ", param: " + str);
        synchronized (f3487b) {
            if (f3486a == null) {
                f3486a = m2299a(context, str);
            }
        }
        DebugLog.LogD("createDetector leave");
        return f3486a;
    }

    public static AudioDetector getDetector() {
        synchronized (f3487b) {
            DebugLog.LogD("getDetector enter");
        }
        return f3486a;
    }

    /* renamed from: a */
    private static AudioDetector m2299a(Context context, String str) {
        C3692ad c3692ad = new C3692ad();
        c3692ad.m1821a(str);
        String m1833e = c3692ad.m1833e(SpeechConstant.LIB_NAME);
        if (TextUtils.isEmpty(m1833e) || m2300a(m1833e)) {
            String m1827b = c3692ad.m1827b(VAD_ENGINE, "fixfront");
            if ("fixfront".equalsIgnoreCase(m1827b)) {
                return new C3711aw(context, str);
            }
            if (TYPE_META.equalsIgnoreCase(m1827b)) {
                return new C3712ax(context, str);
            }
            DebugLog.LogE("detector factory unmatched engine type: " + m1827b);
            return null;
        }
        DebugLog.LogE("detector factory load library failed: " + m1833e);
        return null;
    }

    /* renamed from: a */
    private static boolean m2300a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                System.loadLibrary(str);
            } catch (Throwable th) {
                DebugLog.LogE("Load library failed.");
                th.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
