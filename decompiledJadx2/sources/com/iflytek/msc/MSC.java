package com.iflytek.msc;

import android.graphics.Bitmap;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.FileDescriptor;

/* loaded from: classes3.dex */
public class MSC {

    /* renamed from: a */
    private static boolean f3512a = false;

    public static int AIUIClear(char[] cArr) {
        return 0;
    }

    public static int AIUIDataWrite(char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, MSCSessionInfo mSCSessionInfo) {
        return 0;
    }

    public static int AIUIGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo) {
        return 0;
    }

    public static int AIUIRegisterNotify(char[] cArr, String str, String str2, String str3, String str4, String str5, Object obj) {
        return 0;
    }

    public static int AIUISendLog(char[] cArr, byte[] bArr, byte[] bArr2, int i, MSCSessionInfo mSCSessionInfo) {
        return 0;
    }

    public static final char[] AIUISessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo) {
        return null;
    }

    public static int AIUISessionEnd(char[] cArr, byte[] bArr) {
        return 0;
    }

    public static int AIUISetParam(char[] cArr, byte[] bArr, byte[] bArr2) {
        return 0;
    }

    public static int AIUISyncData(char[] cArr, byte[] bArr, byte[] bArr2, int i, int i2, MSCSessionInfo mSCSessionInfo) {
        return 0;
    }

    public static final native int DebugLog(boolean z);

    public static final native int QHCRDataWrite(char[] cArr, byte[] bArr, byte[] bArr2, int i, int i2);

    public static final int QHCRFini() {
        return 0;
    }

    public static final native byte[] QHCRGetResult(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QHCRInit(byte[] bArr);

    public static final native int QHCRLogEvent(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native char[] QHCRSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final int QHCRSessionEnd(char[] cArr, byte[] bArr) {
        return 0;
    }

    public static native byte[] QIFDFacedetect(Bitmap bitmap, int i, Object obj);

    public static native int QIFDFini();

    public static native void QIFDInit(byte[] bArr, Object obj);

    public static native byte[] QIFDMultitracker(byte[] bArr, int i, int i2, int i3, int i4, int i5, Object obj);

    public static final native int QISEAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final int QISEFini() {
        return 0;
    }

    public static final native int QISEGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISEGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final int QISEInit(byte[] bArr) {
        return 0;
    }

    public static final native char[] QISESessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISESessionEnd(char[] cArr, byte[] bArr);

    public static final native int QISETextPut(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRBuildGrammar(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, String str, Object obj);

    public static final int QISRFini() {
        return 0;
    }

    public static final native int QISRGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISRGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final int QISRGrammarActivate(char[] cArr, byte[] bArr, byte[] bArr2) {
        return 0;
    }

    public static final int QISRInit(byte[] bArr) {
        return 0;
    }

    public static final int QISRLogEvent(char[] cArr, byte[] bArr, byte[] bArr2) {
        return 0;
    }

    public static final native int QISRRegisterNotify(char[] cArr, String str, String str2, String str3, Object obj);

    public static final native char[] QISRSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QISRSetParam(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRUpdateLexicon(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, String str, Object obj);

    public static final byte[] QISRUploadData(char[] cArr, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, MSCSessionInfo mSCSessionInfo) {
        return null;
    }

    public static final native int QISVAudioWrite(char[] cArr, char[] cArr2, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final int QISVFini() {
        return 0;
    }

    public static final native int QISVGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISVGetResult(char[] cArr, char[] cArr2, MSCSessionInfo mSCSessionInfo);

    public static final int QISVInit(byte[] bArr) {
        return 0;
    }

    public static final native char[] QISVQueDelModel(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVQueDelModelRelease(char[] cArr);

    public static final native char[] QISVSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QIVWAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWGetResInfo(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWRegisterNotify(char[] cArr, String str, Object obj);

    public static final native int QIVWResMerge(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static final native char[] QIVWSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QMFVDataWrite(char[] cArr, byte[] bArr, byte[] bArr2, int i, MSCSessionInfo mSCSessionInfo);

    public static final native int QMFVGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final byte[] QMFVGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo) {
        return null;
    }

    public static final native int QMFVRegisterNotify(char[] cArr, String str, String str2, String str3, Object obj);

    public static final native char[] QMFVSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMFVSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QMFVSetParam(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final int QMSPDownload(byte[] bArr, byte[] bArr2, Object obj) {
        return 0;
    }

    public static final native byte[] QMSPDownloadData(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPGetParam(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QMSPGetVersion(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPLogOut();

    public static final native int QMSPLogin(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static final int QMSPRegisterNotify(String str, String str2) {
        return 0;
    }

    public static final native byte[] QMSPSearch(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPSetParam(byte[] bArr, byte[] bArr2);

    public static final native byte[] QMSPUploadData(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QTTSAudioGet(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native char[] QTTSAudioInfo(char[] cArr);

    public static final native int QTTSFini();

    public static final native int QTTSGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QTTSInit(byte[] bArr);

    public static final native char[] QTTSSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QTTSSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QTTSTextPut(char[] cArr, byte[] bArr);

    public static final native int SetLogLevel(int i);

    public static final int UMSPLogin(byte[] bArr, byte[] bArr2, byte[] bArr3, Object obj) {
        return 0;
    }

    public static native boolean doARGB2Gray(Bitmap bitmap, Bitmap bitmap2);

    public static final native int getFileDescriptorFD(FileDescriptor fileDescriptor);

    public static boolean isIflyVersion() {
        DebugLog.LogD("MSC ifly ver: true");
        return true;
    }

    public static boolean loadLibrary(String str) {
        boolean z = f3512a;
        if (z) {
            return z;
        }
        try {
            System.loadLibrary(str);
            f3512a = true;
            DebugLog.LogD("loadLibrary " + str + " success");
        } catch (UnsatisfiedLinkError e) {
            DebugLog.LogE("loadLibrary " + str + " error:" + e);
            f3512a = false;
        }
        return f3512a;
    }

    public static boolean isLoaded() {
        return f3512a;
    }
}
