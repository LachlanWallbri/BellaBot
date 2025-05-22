package com.iflytek.msc;

/* loaded from: classes3.dex */
public class MetaVAD {
    public static native int VADAppendPCM(Instance instance, byte[] bArr, int i, int i2, int i3);

    public static native int VADCreateSession(Instance instance);

    public static native int VADDelResource(int i);

    public static native int VADDestroySession(Instance instance);

    public static native int VADGetSeg(Instance instance);

    public static native float VADGetSentConfidence(Instance instance);

    public static native int VADInitialize(byte[] bArr);

    public static native int VADLoadResource(int i, byte[] bArr);

    public static native int VADResetSentence(Instance instance);

    public static native int VADResetSession(Instance instance);

    public static native int VADSetParam(Instance instance, byte[] bArr, byte[] bArr2);

    public static native int VADUninitialize();

    /* loaded from: classes3.dex */
    public static class Instance {
        public static final long INVALID_HANDLE = 0;
        public static final byte SEG = 2;
        public static final byte SEG_FIRST = 1;
        public static final byte SEG_LAST = 3;
        public static final byte SEG_NONE = 0;
        public long handle = 0;
        public int rate = 0;
        public int begin = 0;
        public int end = 0;
        public int seg = 0;
        public int volume = 0;

        /* renamed from: a */
        public void m2346a() {
            this.begin = 0;
            this.end = 0;
            this.seg = 0;
            this.volume = 0;
        }
    }
}
