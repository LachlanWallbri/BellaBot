package com.pudutech.bumblebee.business.oss;

import android.os.Environment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.storage.ExtKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: OssService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J)\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ!\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0006\u0010!\u001a\u00020\fJ)\u0010\"\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u000e\u0010#\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u0018\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0002J\u000e\u0010(\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000RN\u0010\u0006\u001a6\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R7\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016RL\u0010\u0017\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/oss/OssService;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onErrorListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "url", "errorReason", "", "getOnErrorListener", "()Lkotlin/jvm/functions/Function2;", "setOnErrorListener", "(Lkotlin/jvm/functions/Function2;)V", "onSuccessListener", "Lkotlin/Function1;", "getOnSuccessListener", "()Lkotlin/jvm/functions/Function1;", "setOnSuccessListener", "(Lkotlin/jvm/functions/Function1;)V", "updateProgressListener", "progress", "getUpdateProgressListener", "setUpdateProgressListener", "asyncDownloadFile", "objectName", "filePath", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asyncUploadLocalFile", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAll", "downloadFile", "getDownloadPath", "getProgress", "totalSize", "", "currentSize", "getRealObjectKey", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class OssService {
    private final String TAG = getClass().getSimpleName();
    private Function2<? super String, ? super String, Unit> onErrorListener;
    private Function1<? super String, Unit> onSuccessListener;
    private Function2<? super String, ? super String, Unit> updateProgressListener;

    public final Object asyncUploadLocalFile(final String str, String str2, Continuation<? super Unit> continuation) {
        File file = new File(str);
        PdUploadConfig builder = new PdUploadConfig.Builder().file(file).fileType("production/map").builder();
        Pdlog.m3273d(this.TAG, "asyncUploadLocalFile  fileName =" + file.getName() + " ## file = " + file);
        IOssTaskController upload = ExtKt.upload(PdNetworkManager.f10310INSTANCE, builder, new OssCallback() { // from class: com.pudutech.bumblebee.business.oss.OssService$asyncUploadLocalFile$2
            @Override // com.pudutech.pd_network.OssCallback
            public void onCompleted(String url) {
                String str3;
                Intrinsics.checkParameterIsNotNull(url, "url");
                str3 = OssService.this.TAG;
                Pdlog.m3273d(str3, "asyncUploadLocalFile onCompleted url = " + url);
                Function1<String, Unit> onSuccessListener = OssService.this.getOnSuccessListener();
                if (onSuccessListener != null) {
                    onSuccessListener.invoke(url);
                }
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onError(Exception ex) {
                String str3;
                Intrinsics.checkParameterIsNotNull(ex, "ex");
                Function2<String, String, Unit> onErrorListener = OssService.this.getOnErrorListener();
                if (onErrorListener != null) {
                    onErrorListener.invoke(str, ex.getMessage());
                }
                str3 = OssService.this.TAG;
                Pdlog.m3273d(str3, "asyncUploadLocalFile onError = " + ex);
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onProgressChanged(long bytesCurrent, long bytesTotal) {
                String progress;
                String str3;
                progress = OssService.this.getProgress(bytesTotal, bytesCurrent);
                Function2<String, String, Unit> updateProgressListener = OssService.this.getUpdateProgressListener();
                if (updateProgressListener != null) {
                    updateProgressListener.invoke(str, progress);
                }
                str3 = OssService.this.TAG;
                Pdlog.m3273d(str3, "asyncUploadLocalFile onProgressChanged = " + progress);
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onStateChanged(OssState state) {
                String str3;
                Intrinsics.checkParameterIsNotNull(state, "state");
                str3 = OssService.this.TAG;
                Pdlog.m3273d(str3, "asyncUploadLocalFile onStateChanged = " + state);
            }
        });
        return upload == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? upload : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.io.File] */
    public final Object asyncDownloadFile(String str, String str2, final String str3, Continuation<? super Unit> continuation) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new File(str2);
        IOssTaskController download = ExtKt.download(PdNetworkManager.f10310INSTANCE, str3, (File) objectRef.element, new OssCallback() { // from class: com.pudutech.bumblebee.business.oss.OssService$asyncDownloadFile$2
            @Override // com.pudutech.pd_network.OssCallback
            public void onCompleted(String url) {
                String str4;
                Intrinsics.checkParameterIsNotNull(url, "url");
                Function1<String, Unit> onSuccessListener = OssService.this.getOnSuccessListener();
                if (onSuccessListener != null) {
                    onSuccessListener.invoke(url);
                }
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "asyncDownloadFile onCompleted url =" + url + " file = " + ((File) objectRef.element));
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onError(Exception ex) {
                String str4;
                Intrinsics.checkParameterIsNotNull(ex, "ex");
                Function2<String, String, Unit> onErrorListener = OssService.this.getOnErrorListener();
                if (onErrorListener != null) {
                    onErrorListener.invoke(str3, String.valueOf(ex.getMessage()));
                }
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "asyncDownloadFile onError =" + ex + " url =" + str3 + " file = " + ((File) objectRef.element));
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onProgressChanged(long bytesCurrent, long bytesTotal) {
                String str4;
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "asyncDownloadFile onProgressChanged url =" + str3 + " file = " + ((File) objectRef.element));
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onStateChanged(OssState state) {
                String str4;
                Intrinsics.checkParameterIsNotNull(state, "state");
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "asyncDownloadFile onStateChanged state = " + state + " url =" + str3 + " file = " + ((File) objectRef.element));
            }
        });
        return download == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? download : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.io.File] */
    public final Object downloadFile(String str, String str2, final String str3, Continuation<? super Unit> continuation) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new File(str2);
        IOssTaskController download = ExtKt.download(PdNetworkManager.f10310INSTANCE, str3, (File) objectRef.element, new OssCallback() { // from class: com.pudutech.bumblebee.business.oss.OssService$downloadFile$2
            @Override // com.pudutech.pd_network.OssCallback
            public void onCompleted(String url) {
                String str4;
                Intrinsics.checkParameterIsNotNull(url, "url");
                Function1<String, Unit> onSuccessListener = OssService.this.getOnSuccessListener();
                if (onSuccessListener != null) {
                    onSuccessListener.invoke(url);
                }
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "downloadFile onCompleted url =" + url + " file = " + ((File) objectRef.element));
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onError(Exception ex) {
                String str4;
                Intrinsics.checkParameterIsNotNull(ex, "ex");
                Function2<String, String, Unit> onErrorListener = OssService.this.getOnErrorListener();
                if (onErrorListener != null) {
                    onErrorListener.invoke(str3, String.valueOf(ex.getMessage()));
                }
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "downloadFile onError =" + ex + " url =" + str3 + " file = " + ((File) objectRef.element));
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onProgressChanged(long bytesCurrent, long bytesTotal) {
                String progress;
                String str4;
                progress = OssService.this.getProgress(bytesTotal, bytesCurrent);
                Function2<String, String, Unit> updateProgressListener = OssService.this.getUpdateProgressListener();
                if (updateProgressListener != null) {
                    updateProgressListener.invoke(str3, progress);
                }
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "downloadFile onProgressChanged progress = " + progress + " url =" + str3);
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onStateChanged(OssState state) {
                String str4;
                Intrinsics.checkParameterIsNotNull(state, "state");
                str4 = OssService.this.TAG;
                Pdlog.m3273d(str4, "downloadFile onStateChanged state = " + state + " url =" + str3 + ' ');
            }
        });
        return download == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? download : Unit.INSTANCE;
    }

    public final String getDownloadPath(String name) {
        String sb;
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (Environment.getExternalStorageState().equals("mounted")) {
            sb = "/sdcard/pudu/tempMap/" + name + ".pdmap";
            if (FileUtils.isFileExists(sb)) {
                FileUtils.delete(sb);
            }
            Pdlog.m3274e("getDown", sb, name);
        } else {
            StringBuilder sb2 = new StringBuilder();
            File externalFilesDir = BaseApplication.INSTANCE.getInstance().getExternalFilesDir(null);
            Intrinsics.checkExpressionValueIsNotNull(externalFilesDir, "BaseApplication.instance.getExternalFilesDir(null)");
            sb2.append(externalFilesDir.getAbsolutePath());
            sb2.append("/map/download/");
            sb = sb2.toString();
            if (FileUtils.isFileExists(sb)) {
                FileUtils.delete(sb);
            }
        }
        return sb;
    }

    public final String getRealObjectKey(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        if (url.length() == 0) {
            throw new NullPointerException("url is not empty");
        }
        return StringsKt.substringAfter$default(url, "aliyuncs.com/", (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getProgress(long totalSize, long currentSize) {
        int i = (int) (100 * (((float) currentSize) / ((float) totalSize)));
        return String.valueOf(i <= 100 ? i < 0 ? 0 : i : 100);
    }

    public final void clearAll() {
        this.onSuccessListener = (Function1) null;
        Function2<? super String, ? super String, Unit> function2 = (Function2) null;
        this.updateProgressListener = function2;
        this.onErrorListener = function2;
    }

    public final Function1<String, Unit> getOnSuccessListener() {
        return this.onSuccessListener;
    }

    public final void setOnSuccessListener(Function1<? super String, Unit> function1) {
        this.onSuccessListener = function1;
    }

    public final Function2<String, String, Unit> getUpdateProgressListener() {
        return this.updateProgressListener;
    }

    public final void setUpdateProgressListener(Function2<? super String, ? super String, Unit> function2) {
        this.updateProgressListener = function2;
    }

    public final Function2<String, String, Unit> getOnErrorListener() {
        return this.onErrorListener;
    }

    public final void setOnErrorListener(Function2<? super String, ? super String, Unit> function2) {
        this.onErrorListener = function2;
    }
}
