package com.pudutech.voiceinteraction.component.utils;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.storage.ExtKt;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import okhttp3.OkHttpClient;

/* compiled from: PuduAudioReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\b\u0010\u0017\u001a\u0004\u0018\u00010\nJ\u0012\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u001b\u001a\u00020\u0015J\u0018\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u001d\u001a\u00020\u0015J\u0018\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/PuduAudioReport;", "", "()V", "TAG", "", "byteArray", "", "fileOutputStream", "Ljava/io/OutputStream;", "filename", "Ljava/io/File;", "macStr", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "setOkHttpClient", "(Lokhttp3/OkHttpClient;)V", "outPath", "recodePath", "deleteFile", "", "path", "getCurrentPcmName", "getRecodePath", "macStrt", "getVoiceFile", "init", "recodeDate", "release", "startReport", "asr", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PuduAudioReport {
    private static byte[] byteArray = null;
    private static OutputStream fileOutputStream = null;
    private static File filename = null;
    private static String macStr = null;
    private static OkHttpClient okHttpClient = null;
    private static String outPath = null;
    public static final PuduAudioReport INSTANCE = new PuduAudioReport();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String recodePath = recodePath;
    private static final String recodePath = recodePath;

    private PuduAudioReport() {
    }

    public final OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public final void setOkHttpClient(OkHttpClient okHttpClient2) {
        okHttpClient = okHttpClient2;
    }

    public final synchronized void init() {
        if (okHttpClient != null) {
            return;
        }
        try {
            okHttpClient = new OkHttpClient().newBuilder().callTimeout(10000L, TimeUnit.MILLISECONDS).build();
            File file = new File(recodePath);
            if (file.exists()) {
                Tools.execCommand("rm -r " + recodePath, false);
            }
            boolean mkdirs = file.mkdirs();
            Pdlog.m3273d(TAG, "init: mkdir " + mkdirs);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "init: " + Log.getStackTraceString(e));
        }
    }

    public final synchronized void recodeDate(byte[] byteArray2, String macStrt) {
        Intrinsics.checkParameterIsNotNull(byteArray2, "byteArray");
        try {
            if (fileOutputStream == null) {
                File recodePath2 = getRecodePath(macStrt);
                filename = recodePath2;
                fileOutputStream = new FileOutputStream(recodePath2);
                outPath = recodePath2.getAbsolutePath();
                Pdlog.m3273d(TAG, "recodeDate: outPath=" + outPath);
            }
            OutputStream outputStream = fileOutputStream;
            if (outputStream != null) {
                outputStream.write(byteArray2);
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "recodeDate: e=" + Log.getStackTraceString(e));
        }
    }

    private final File getRecodePath(String macStrt) {
        macStr = macStrt != null ? StringsKt.replace$default(macStrt, ":", "", false, 4, (Object) null) : null;
        return new File(recodePath, StringsKt.replace$default(macStr + '_' + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + "_beforevad.pcm", ":", "", false, 4, (Object) null));
    }

    public final synchronized String getVoiceFile() {
        String str;
        str = outPath;
        OutputStream outputStream = fileOutputStream;
        fileOutputStream = (OutputStream) null;
        if (outputStream != null) {
            outputStream.close();
        }
        return str;
    }

    public final synchronized void startReport(String asr, final String path) {
        String str;
        String str2;
        Intrinsics.checkParameterIsNotNull(asr, "asr");
        try {
            str = path;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "startReport: " + Log.getStackTraceString(e));
        }
        if (str == null || str.length() == 0) {
            return;
        }
        if (Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot")) {
            str2 = "beila_box/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        } else if (Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "KettyBot")) {
            str2 = "hulu_box/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        } else if (Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "SwiftBot")) {
            str2 = "fenghuang_box/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        } else {
            str2 = "custom_box/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        ExtKt.upload(PdNetworkManager.f10310INSTANCE, new PdUploadConfig.Builder().file(new File(path)).bucketType(StorageBucketType.Media.INSTANCE).fileType(str2).builder(), new OssCallback() { // from class: com.pudutech.voiceinteraction.component.utils.PuduAudioReport$startReport$1
            @Override // com.pudutech.pd_network.OssCallback
            public void onProgressChanged(long bytesCurrent, long bytesTotal) {
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onStateChanged(OssState state) {
                Intrinsics.checkParameterIsNotNull(state, "state");
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onCompleted(String url) {
                String str3;
                Intrinsics.checkParameterIsNotNull(url, "url");
                PuduAudioReport puduAudioReport = PuduAudioReport.INSTANCE;
                str3 = PuduAudioReport.TAG;
                Pdlog.m3273d(str3, "success: call=" + url + ' ' + path);
                PuduAudioReport.INSTANCE.deleteFile(path);
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onError(Exception ex) {
                String str3;
                Intrinsics.checkParameterIsNotNull(ex, "ex");
                PuduAudioReport puduAudioReport = PuduAudioReport.INSTANCE;
                str3 = PuduAudioReport.TAG;
                Pdlog.m3273d(str3, "onError: call=" + ex + ' ' + path);
                PuduAudioReport.INSTANCE.deleteFile(path);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteFile(String path) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PuduAudioReport$deleteFile$1(path, null), 3, null);
    }

    public final synchronized void release() {
        try {
            OutputStream outputStream = fileOutputStream;
            if (outputStream != null) {
                outputStream.close();
            }
            String str = outPath;
            if (str != null) {
                INSTANCE.deleteFile(str);
            }
            fileOutputStream = (OutputStream) null;
            outPath = (String) null;
            filename = (File) null;
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "release: " + Log.getStackTraceString(e));
        }
    }

    public final File getCurrentPcmName() {
        return filename;
    }
}
