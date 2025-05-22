package com.pudutech.voiceinteraction.component.utils;

import android.os.SystemClock;
import android.util.Log;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.cloud.SpeechConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* compiled from: ShengxiDateReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0018\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/ShengxiDateReport;", "", "()V", "TAG", "", "byteArray", "", "fileOutputStream", "Ljava/io/OutputStream;", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "setOkHttpClient", "(Lokhttp3/OkHttpClient;)V", "outPath", "recodePath", "deleteFile", "", "path", "getRecodePath", "Ljava/io/File;", "init", "recodeDate", "release", "startReport", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ShengxiDateReport {
    private static byte[] byteArray = null;
    private static OutputStream fileOutputStream = null;
    private static OkHttpClient okHttpClient = null;
    private static String outPath = null;
    public static final ShengxiDateReport INSTANCE = new ShengxiDateReport();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String recodePath = recodePath;
    private static final String recodePath = recodePath;

    private ShengxiDateReport() {
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

    public final synchronized void recodeDate(byte[] byteArray2) {
        Intrinsics.checkParameterIsNotNull(byteArray2, "byteArray");
        try {
            if (fileOutputStream == null) {
                File recodePath2 = getRecodePath();
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

    private final File getRecodePath() {
        return new File(recodePath, "recode_" + SystemClock.elapsedRealtime() + TtsVoiceHelper.FLIE_MARK);
    }

    public final synchronized void startReport() {
        final String str;
        String str2;
        Call newCall;
        Pdlog.m3273d(TAG, "startReport: " + outPath);
        if (fileOutputStream == null) {
            return;
        }
        try {
            OutputStream outputStream = fileOutputStream;
            str = outPath;
            fileOutputStream = (OutputStream) null;
            outPath = (String) null;
            if (outputStream != null) {
                outputStream.close();
            }
            str2 = str;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "startReport: " + Log.getStackTraceString(e));
        }
        if (str2 == null || str2.length() == 0) {
            return;
        }
        Request build = new Request.Builder().url("http://d01.speechx.cn:8710/collect").post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(InternalConstant.DTYPE_AUDIO, str, RequestBody.create(MediaType.parse("multipart/form-data"), new File(str))).addFormDataPart(SpeechConstant.AUTH_ID, "tb4gm6ho").addFormDataPart("scene", "kettybot").addFormDataPart("model", "kettybot").build()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Request.Builder()\n      …\n                .build()");
        OkHttpClient okHttpClient2 = okHttpClient;
        if (okHttpClient2 != null && (newCall = okHttpClient2.newCall(build)) != null) {
            newCall.enqueue(new Callback() { // from class: com.pudutech.voiceinteraction.component.utils.ShengxiDateReport$startReport$1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e2) {
                    String str3;
                    Intrinsics.checkParameterIsNotNull(call, "call");
                    Intrinsics.checkParameterIsNotNull(e2, "e");
                    ShengxiDateReport shengxiDateReport = ShengxiDateReport.INSTANCE;
                    str3 = ShengxiDateReport.TAG;
                    Pdlog.m3273d(str3, "onFailure: call=" + call + "  e=" + e2);
                    ShengxiDateReport.INSTANCE.deleteFile(str);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    String str3;
                    Intrinsics.checkParameterIsNotNull(call, "call");
                    Intrinsics.checkParameterIsNotNull(response, "response");
                    ShengxiDateReport shengxiDateReport = ShengxiDateReport.INSTANCE;
                    str3 = ShengxiDateReport.TAG;
                    Pdlog.m3273d(str3, "success: call=" + call + ' ' + str);
                    ShengxiDateReport.INSTANCE.deleteFile(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteFile(String path) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ShengxiDateReport$deleteFile$1(path, null), 3, null);
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
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "release: " + Log.getStackTraceString(e));
        }
    }
}
