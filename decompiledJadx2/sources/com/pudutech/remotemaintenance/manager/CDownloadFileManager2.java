package com.pudutech.remotemaintenance.manager;

import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.config.CConfig;
import com.pudutech.remotemaintenance.interf.IFileDownloadInterface;
import com.pudutech.remotemaintenance.listener.OnDownloadFileListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: CDownloadFileManager2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/manager/CDownloadFileManager2;", "Lcom/pudutech/remotemaintenance/interf/IFileDownloadInterface;", "()V", "okHttpClient", "Lokhttp3/OkHttpClient;", "downloadFile", "", "fileUrl", "", "fileName", "listener", "Lcom/pudutech/remotemaintenance/listener/OnDownloadFileListener;", "getContentLength", "", "Companion", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CDownloadFileManager2 implements IFileDownloadInterface {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<CDownloadFileManager2>() { // from class: com.pudutech.remotemaintenance.manager.CDownloadFileManager2$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CDownloadFileManager2 invoke() {
            return new CDownloadFileManager2(null);
        }
    });
    public static final String TAG = "CDownloadFileManager";
    private OkHttpClient okHttpClient;

    private CDownloadFileManager2() {
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(15000L, TimeUnit.MILLISECONDS).writeTimeout(15000L, TimeUnit.MILLISECONDS).readTimeout(15000L, TimeUnit.MILLISECONDS).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "okHttpClientBuilder.build()");
        this.okHttpClient = build;
    }

    public /* synthetic */ CDownloadFileManager2(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: CDownloadFileManager2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/manager/CDownloadFileManager2$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/remotemaintenance/manager/CDownloadFileManager2;", "getINSTANCE", "()Lcom/pudutech/remotemaintenance/manager/CDownloadFileManager2;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        public final CDownloadFileManager2 getINSTANCE() {
            Lazy lazy = CDownloadFileManager2.INSTANCE$delegate;
            Companion companion = CDownloadFileManager2.INSTANCE;
            return (CDownloadFileManager2) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r10v6, types: [T, java.io.FileOutputStream] */
    @Override // com.pudutech.remotemaintenance.interf.IFileDownloadInterface
    public void downloadFile(final String fileUrl, String fileName, final OnDownloadFileListener listener) {
        String str = fileUrl;
        if (str == null || str.length() == 0) {
            Pdlog.m3274e("CDownloadFileManager", "OSSUploadFileManager#downloadFile() failure, reason: fileUrl is null or empty.");
            return;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (InputStream) 0;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = (File) 0;
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = (FileOutputStream) 0;
        if (listener != null) {
            listener.onStart(fileUrl);
        }
        Observable.create(new ObservableOnSubscribe<String>() { // from class: com.pudutech.remotemaintenance.manager.CDownloadFileManager2$downloadFile$1
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter<String> emitter) {
                String remote_maintenance_file_directory;
                long contentLength;
                OkHttpClient okHttpClient;
                File file;
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                try {
                    try {
                        remote_maintenance_file_directory = CConfig.INSTANCE.getREMOTE_MAINTENANCE_FILE_DIRECTORY();
                    } catch (IOException e) {
                        e.printStackTrace();
                        emitter.onError(new Throwable(e));
                        try {
                            InputStream inputStream = (InputStream) objectRef.element;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        try {
                            FileOutputStream fileOutputStream = (FileOutputStream) objectRef3.element;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e3) {
                            e = e3;
                            e.printStackTrace();
                            emitter.onComplete();
                        }
                    }
                    if (TextUtils.isEmpty(remote_maintenance_file_directory)) {
                        OnDownloadFileListener onDownloadFileListener = listener;
                        if (onDownloadFileListener != null) {
                            onDownloadFileListener.onFailure(fileUrl, "directoryPath is null or empty.");
                        }
                        try {
                            InputStream inputStream2 = (InputStream) objectRef.element;
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        try {
                            FileOutputStream fileOutputStream2 = (FileOutputStream) objectRef3.element;
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        emitter.onComplete();
                        return;
                    }
                    Pdlog.m3273d("CDownloadFileManager", "directoryPath[" + remote_maintenance_file_directory + ']');
                    File file2 = new File(remote_maintenance_file_directory);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    Ref.ObjectRef objectRef4 = objectRef2;
                    String str2 = file2.getPath() + "/";
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    String str3 = fileUrl;
                    int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) fileUrl, "/", 0, false, 6, (Object) null);
                    if (str3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String substring = str3.substring(lastIndexOf$default);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                    sb.append(substring);
                    objectRef4.element = (T) new File(sb.toString());
                    File file3 = (File) objectRef2.element;
                    Boolean valueOf = file3 != null ? Boolean.valueOf(file3.exists()) : null;
                    if (valueOf == null) {
                        Intrinsics.throwNpe();
                    }
                    if (valueOf.booleanValue() && (file = (File) objectRef2.element) != null) {
                        file.delete();
                    }
                    File file4 = (File) objectRef2.element;
                    if (file4 != null) {
                        file4.createNewFile();
                    }
                    contentLength = CDownloadFileManager2.this.getContentLength(fileUrl);
                    if (contentLength == 0) {
                        Pdlog.m3274e("CDownloadFileManager", "download file failure, reason: contentLength = 0");
                        emitter.onError(new Throwable("download file failure, reason: contentLength = 0"));
                    } else {
                        objectRef3.element = (T) new FileOutputStream((File) objectRef2.element);
                        Request build = new Request.Builder().url(fileUrl).build();
                        okHttpClient = CDownloadFileManager2.this.okHttpClient;
                        Response response = okHttpClient.newCall(build).execute();
                        Intrinsics.checkExpressionValueIsNotNull(response, "response");
                        if (response.isSuccessful()) {
                            Ref.ObjectRef objectRef5 = objectRef;
                            ResponseBody body = response.body();
                            T t = body != null ? (T) body.byteStream() : null;
                            if (t == null) {
                                Intrinsics.throwNpe();
                            }
                            objectRef5.element = t;
                            byte[] bArr = new byte[1024];
                            int i = 0;
                            int i2 = 0;
                            while (contentLength != i) {
                                InputStream inputStream3 = (InputStream) objectRef.element;
                                Integer valueOf2 = inputStream3 != null ? Integer.valueOf(inputStream3.read(bArr)) : null;
                                int intValue = valueOf2 != null ? valueOf2.intValue() : 0;
                                if (valueOf2 != null && valueOf2.intValue() == -1) {
                                    break;
                                }
                                i += intValue;
                                FileOutputStream fileOutputStream3 = (FileOutputStream) objectRef3.element;
                                if (fileOutputStream3 != null) {
                                    fileOutputStream3.write(bArr, 0, intValue);
                                }
                                int i3 = (int) (((i * 1.0f) / ((float) contentLength)) * 100);
                                if (i3 % 5 == 0 && i3 != i2) {
                                    Pdlog.m3273d("CDownloadFileManager", "progress[" + i3 + ']');
                                    OnDownloadFileListener onDownloadFileListener2 = listener;
                                    if (onDownloadFileListener2 != null) {
                                        onDownloadFileListener2.onProgress(fileUrl, i3);
                                    }
                                    i2 = i3;
                                }
                            }
                            FileOutputStream fileOutputStream4 = (FileOutputStream) objectRef3.element;
                            if (fileOutputStream4 != null) {
                                fileOutputStream4.flush();
                            }
                            ResponseBody body2 = response.body();
                            if (body2 != null) {
                                body2.close();
                            }
                            emitter.onNext(fileUrl);
                        }
                    }
                    try {
                        InputStream inputStream4 = (InputStream) objectRef.element;
                        if (inputStream4 != null) {
                            inputStream4.close();
                        }
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                    try {
                        FileOutputStream fileOutputStream5 = (FileOutputStream) objectRef3.element;
                        if (fileOutputStream5 != null) {
                            fileOutputStream5.close();
                        }
                    } catch (Exception e7) {
                        e = e7;
                        e.printStackTrace();
                        emitter.onComplete();
                    }
                    emitter.onComplete();
                } catch (Throwable th) {
                    try {
                        InputStream inputStream5 = (InputStream) objectRef.element;
                        if (inputStream5 != null) {
                            inputStream5.close();
                        }
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                    try {
                        FileOutputStream fileOutputStream6 = (FileOutputStream) objectRef3.element;
                        if (fileOutputStream6 != null) {
                            fileOutputStream6.close();
                        }
                    } catch (Exception e9) {
                        e9.printStackTrace();
                    }
                    emitter.onComplete();
                    throw th;
                }
            }
        }).subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.pudutech.remotemaintenance.manager.CDownloadFileManager2$downloadFile$2
            private Disposable disposable;

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkParameterIsNotNull(d, "d");
                this.disposable = d;
                Pdlog.m3273d("CDownloadFileManager", "downloadFile#onSubscribe() disposable[" + this.disposable + ']');
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.Observer
            public void onNext(String fileUrl2) {
                Intrinsics.checkParameterIsNotNull(fileUrl2, "fileUrl");
                Pdlog.m3273d("CDownloadFileManager", "downloadFile#onNext() fileUrl[" + fileUrl2 + ']');
                OnDownloadFileListener onDownloadFileListener = OnDownloadFileListener.this;
                if (onDownloadFileListener != null) {
                    File file = (File) objectRef2.element;
                    onDownloadFileListener.onSuccessful(file != null ? file.getPath() : null, fileUrl2);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                Pdlog.m3273d("CDownloadFileManager", "downloadFile#onError() throwable[" + t + ']');
                Disposable disposable = this.disposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                OnDownloadFileListener onDownloadFileListener = OnDownloadFileListener.this;
                if (onDownloadFileListener != null) {
                    onDownloadFileListener.onFailure(fileUrl, t.getMessage());
                }
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                Pdlog.m3273d("CDownloadFileManager", "downloadFile#onComplete() fileUrl[" + fileUrl + ']');
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getContentLength(String fileUrl) {
        try {
            Response response = this.okHttpClient.newCall(new Request.Builder().url(fileUrl).addHeader("Accept-Encoding", "identity").build()).execute();
            Intrinsics.checkExpressionValueIsNotNull(response, "response");
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                Long valueOf = body != null ? Long.valueOf(body.get$contentLength()) : null;
                ResponseBody body2 = response.body();
                if (body2 != null) {
                    body2.close();
                }
                if (valueOf != null) {
                    return valueOf.longValue();
                }
                return 0L;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
