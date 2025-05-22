package com.pudutech.light_network.download;

import android.os.Build;
import android.os.Environment;
import com.pudutech.base.Pdlog;
import com.pudutech.light_network.OKHttpClient;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class DownloadManager {
    private static final AtomicReference<DownloadManager> INSTANCE = new AtomicReference<>();
    private static final String TAG = "DownloadManager";
    private final String DEFAULT_SAVE_FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "Robot" + File.separator + "download" + File.separator;
    private String SAVE_FILE_PATH = null;
    private HashMap<String, Call> downCalls = new HashMap<>();
    private OkHttpClient mClient = OKHttpClient.getInstance(null, null, null, false);

    public static DownloadManager getInstance() {
        DownloadManager downloadManager;
        do {
            DownloadManager downloadManager2 = INSTANCE.get();
            if (downloadManager2 != null) {
                return downloadManager2;
            }
            downloadManager = new DownloadManager();
        } while (!INSTANCE.compareAndSet(null, downloadManager));
        return downloadManager;
    }

    private DownloadManager() {
    }

    public OkHttpClient getClient() {
        return this.mClient;
    }

    public void download(String str, String str2, DownLoadObserver downLoadObserver) {
        if (str == null || "".equals(str)) {
            str = this.DEFAULT_SAVE_FILE_PATH;
        }
        this.SAVE_FILE_PATH = str;
        Observable.just(str2).filter(new Predicate<String>() { // from class: com.pudutech.light_network.download.DownloadManager.4
            @Override // io.reactivex.functions.Predicate
            public boolean test(String str3) throws Exception {
                return !DownloadManager.this.downCalls.containsKey(str3);
            }
        }).flatMap(new Function<String, ObservableSource<DownloadInfo>>() { // from class: com.pudutech.light_network.download.DownloadManager.3
            @Override // io.reactivex.functions.Function
            public ObservableSource<DownloadInfo> apply(String str3) throws Exception {
                return Observable.just(DownloadManager.this.createDownInfo(str3));
            }
        }).map(new Function<DownloadInfo, DownloadInfo>() { // from class: com.pudutech.light_network.download.DownloadManager.2
            @Override // io.reactivex.functions.Function
            public DownloadInfo apply(DownloadInfo downloadInfo) throws Exception {
                return DownloadManager.this.getRealFileName(downloadInfo);
            }
        }).flatMap(new Function<DownloadInfo, ObservableSource<DownloadInfo>>() { // from class: com.pudutech.light_network.download.DownloadManager.1
            @Override // io.reactivex.functions.Function
            public ObservableSource<DownloadInfo> apply(DownloadInfo downloadInfo) throws Exception {
                return Observable.create(new DownloadSubscribe(downloadInfo));
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.m3958io()).subscribe(downLoadObserver);
    }

    public void cancel(String str) {
        Call call = this.downCalls.get(str);
        if (call != null) {
            call.cancel();
        }
        this.downCalls.remove(str);
    }

    public void cancelAll() {
        Iterator<Map.Entry<String, Call>> it = this.downCalls.entrySet().iterator();
        while (it.hasNext()) {
            Call value = it.next().getValue();
            if (value != null) {
                value.cancel();
            }
        }
        this.downCalls.clear();
    }

    public int size() {
        return this.downCalls.size();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
        private DownloadInfo downloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x0128, code lost:
        
            if (r9 == null) goto L51;
         */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0125  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0143  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0148  */
        @Override // io.reactivex.ObservableOnSubscribe
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void subscribe(ObservableEmitter<DownloadInfo> observableEmitter) throws Exception {
            FileOutputStream fileOutputStream;
            InputStream inputStream;
            String url = this.downloadInfo.getUrl();
            long progress = this.downloadInfo.getProgress();
            long total = this.downloadInfo.getTotal();
            if (total == -1 || progress == -1) {
                observableEmitter.onError(new Throwable("error download length"));
                return;
            }
            observableEmitter.onNext(this.downloadInfo);
            Call newCall = DownloadManager.this.mClient.newCall(new Request.Builder().addHeader("RANGE", "bytes=" + progress + "-" + total).url(url).build());
            DownloadManager.this.downCalls.put(url, newCall);
            Response execute = newCall.execute();
            if (!execute.isSuccessful()) {
                Pdlog.m3274e(DownloadManager.TAG, "response is not success");
                observableEmitter.onError(new IOException(execute.message()));
                return;
            }
            File file = new File(DownloadManager.this.SAVE_FILE_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(DownloadManager.this.SAVE_FILE_PATH, this.downloadInfo.getFileName());
            InputStream inputStream2 = null;
            try {
                Pdlog.m3273d(DownloadManager.TAG, "........begin download.........");
                inputStream = execute.body().byteStream();
            } catch (Exception e) {
                e = e;
                inputStream = null;
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                if (inputStream2 != null) {
                }
                if (fileOutputStream != null) {
                }
                throw th;
            }
            try {
                fileOutputStream = new FileOutputStream(file2, false);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                inputStream2 = inputStream;
                if (inputStream2 != null) {
                }
                if (fileOutputStream != null) {
                }
                throw th;
            }
            try {
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        progress += read;
                        this.downloadInfo.setProgress(progress);
                        observableEmitter.onNext(this.downloadInfo);
                    }
                    fileOutputStream.flush();
                    DownloadManager.this.downCalls.remove(url);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                    }
                    if (fileOutputStream != null) {
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                observableEmitter.onError(e);
                if (Build.VERSION.SDK_INT > 26) {
                    try {
                        Files.delete(file2.toPath());
                    } catch (Exception e4) {
                        Pdlog.m3274e(DownloadManager.TAG, e4);
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } else {
                    try {
                        file2.delete();
                    } catch (SecurityException e5) {
                        Pdlog.m3274e(DownloadManager.TAG, e5);
                    }
                    if (inputStream != null) {
                    }
                }
                th = th3;
                inputStream2 = inputStream;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
            fileOutputStream.close();
            DownloadManager.this.downCalls.remove(url);
            observableEmitter.onComplete();
            DownloadManager.this.SAVE_FILE_PATH = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadInfo createDownInfo(String str) {
        DownloadInfo downloadInfo = new DownloadInfo(str);
        downloadInfo.setTotal(getContentLength(str));
        downloadInfo.setFileName(str.substring(str.lastIndexOf("/")));
        return downloadInfo;
    }

    private long getContentLength(String str) {
        try {
            Response execute = this.mClient.newCall(new Request.Builder().url(str).build()).execute();
            if (execute == null || !execute.isSuccessful()) {
                return -1L;
            }
            long j = execute.body().get$contentLength();
            execute.close();
            if (j == 0) {
                return -1L;
            }
            return j;
        } catch (IOException unused) {
            return -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        downloadInfo.getFileName();
        if (downloadInfo.getTotal() == -1) {
            return downloadInfo;
        }
        downloadInfo.setProgress(0L);
        return downloadInfo;
    }
}
