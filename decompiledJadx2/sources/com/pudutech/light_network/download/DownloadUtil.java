package com.pudutech.light_network.download;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.pudutech.light_network.OKHttpClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class DownloadUtil {
    public static final int DOWNLOAD_FAIL = 0;
    public static final int DOWNLOAD_PROGRESS = 1;
    public static final int DOWNLOAD_SUCCESS = 2;
    private static volatile DownloadUtil downloadUtil;
    OnDownloadListener listener;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.pudutech.light_network.download.DownloadUtil.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                DownloadUtil.this.listener.onDownloadFailed((Exception) message.obj);
            } else if (i == 1) {
                DownloadUtil.this.listener.onDownloading(((Integer) message.obj).intValue());
            } else {
                if (i != 2) {
                    return;
                }
                DownloadUtil.this.listener.onDownloadSuccess((String) message.obj);
            }
        }
    };
    private OkHttpClient okHttpClient;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface OnDownloadListener {
        void onDownloadFailed(Exception exc);

        void onDownloadSuccess(String str);

        void onDownloading(int i);
    }

    public static DownloadUtil createInstance(Context context) {
        if (downloadUtil == null) {
            synchronized (DownloadUtil.class) {
                if (downloadUtil == null) {
                    downloadUtil = new DownloadUtil(context);
                }
            }
        }
        return downloadUtil;
    }

    private DownloadUtil(Context context) {
        this.okHttpClient = OKHttpClient.getInstance(context, null, null, false);
    }

    public void download(final String str, final String str2, OnDownloadListener onDownloadListener) {
        this.listener = onDownloadListener;
        this.okHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.pudutech.light_network.download.DownloadUtil.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Message obtain = Message.obtain();
                obtain.what = 0;
                DownloadUtil.this.mHandler.sendMessage(obtain);
            }

            /* JADX WARN: Removed duplicated region for block: B:41:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:47:? A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:48:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onResponse(Call call, Response response) throws IOException {
                FileOutputStream fileOutputStream;
                byte[] bArr = new byte[2048];
                String isExistDir = DownloadUtil.this.isExistDir(str2);
                InputStream inputStream = null;
                try {
                    InputStream byteStream = response.body().byteStream();
                    try {
                        long j = response.body().get$contentLength();
                        File file = new File(isExistDir, DownloadUtil.this.getNameFromUrl(str));
                        fileOutputStream = new FileOutputStream(file);
                        long j2 = 0;
                        while (true) {
                            try {
                                int read = byteStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                                j2 += read;
                                Message obtain = Message.obtain();
                                obtain.what = 1;
                                obtain.obj = Integer.valueOf((int) (((((float) j2) * 1.0f) / ((float) j)) * 100.0f));
                                DownloadUtil.this.mHandler.sendMessage(obtain);
                            } catch (Exception e) {
                                e = e;
                                inputStream = byteStream;
                                try {
                                    Message obtain2 = Message.obtain();
                                    obtain2.what = 0;
                                    obtain2.obj = e;
                                    DownloadUtil.this.mHandler.sendMessage(obtain2);
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException unused) {
                                        }
                                    }
                                    if (fileOutputStream == null) {
                                        return;
                                    }
                                    fileOutputStream.close();
                                } catch (Throwable th) {
                                    th = th;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException unused2) {
                                        }
                                    }
                                    if (fileOutputStream == null) {
                                        try {
                                            fileOutputStream.close();
                                            throw th;
                                        } catch (IOException unused3) {
                                            throw th;
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream = byteStream;
                                if (inputStream != null) {
                                }
                                if (fileOutputStream == null) {
                                }
                            }
                        }
                        fileOutputStream.flush();
                        Message obtain3 = Message.obtain();
                        obtain3.what = 2;
                        obtain3.obj = file.getAbsolutePath();
                        DownloadUtil.this.mHandler.sendMessage(obtain3);
                        if (byteStream != null) {
                            try {
                                byteStream.close();
                            } catch (IOException unused4) {
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = null;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                }
                try {
                    fileOutputStream.close();
                } catch (IOException unused5) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNameFromUrl(String str) {
        return str.substring(str.lastIndexOf("/") + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String isExistDir(String str) throws IOException {
        File file = new File(str);
        if (!file.mkdirs()) {
            file.createNewFile();
        }
        return file.getAbsolutePath();
    }
}
