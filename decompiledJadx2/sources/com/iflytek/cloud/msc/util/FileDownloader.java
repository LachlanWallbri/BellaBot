package com.iflytek.cloud.msc.util;

import android.content.Context;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.http.HttpDownloadImpl;
import com.iflytek.cloud.msc.util.http.HttpDownloadListener;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.C3692ad;
import com.iflytek.cloud.util.FileDownloadListener;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class FileDownloader {
    private static FileDownloader mFileDownload;
    private Config config;
    private Context mContext;
    private HttpDownloadImpl mHttpRequestImpl = null;
    private final String DLOAD_URL = "download_uri";
    private final String DLOAD_PATH = "file_path";
    private final String DLOAD_MD5 = "file_md5";
    protected Object mSynObj = new Object();
    private HttpDownloadListener httpDownloadListener = new HttpDownloadListener() { // from class: com.iflytek.cloud.msc.util.FileDownloader.1
        @Override // com.iflytek.cloud.msc.util.http.HttpDownloadListener
        public void onStart(long j, String str, String str2, String str3, HttpDownloadImpl httpDownloadImpl) {
            DebugLog.LogD("httpdownload onStart:length:" + j + " mimeType:" + str + " newPath:" + str2);
            if (FileDownloader.this.mDownloadInfo != null) {
                FileDownloader.this.mFileTempPath.put(Long.valueOf(httpDownloadImpl.getId()), str2);
                FileDownloader.this.config.putString(((C3692ad) FileDownloader.this.mDownloadInfo.get(Long.valueOf(httpDownloadImpl.getId()))).m1833e("download_uri"), str2);
                if (FileDownloader.this.mDownloadListener.size() <= 0 || FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId())) == null) {
                    return;
                }
                ((FileDownloadListener) FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId()))).onStart();
                return;
            }
            httpDownloadImpl.cancel();
        }

        @Override // com.iflytek.cloud.msc.util.http.HttpDownloadListener
        public void onProgress(long j, int i, HttpDownloadImpl httpDownloadImpl) {
            DebugLog.LogD("httpdownload onProgress:currentBytes:" + j + " percent:" + i);
            if (FileDownloader.this.mDownloadInfo != null) {
                if (FileDownloader.this.mDownloadListener.size() <= 0 || FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId())) == null) {
                    return;
                }
                ((FileDownloadListener) FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId()))).onProgress(i);
                return;
            }
            httpDownloadImpl.cancel();
        }

        @Override // com.iflytek.cloud.msc.util.http.HttpDownloadListener
        public void onFinish(String str, HttpDownloadImpl httpDownloadImpl) {
            DebugLog.LogD("httpdownload onFinish:fileName:" + str);
            if (FileDownloader.this.mDownloadInfo != null) {
                if (FileDownloader.this.mFileTempPath.size() > 0) {
                    FileDownloader.this.mFileTempPath.remove(Long.valueOf(httpDownloadImpl.getId()));
                }
                String m1833e = ((C3692ad) FileDownloader.this.mDownloadInfo.get(Long.valueOf(httpDownloadImpl.getId()))).m1833e("file_md5");
                if (FileDownloader.this.mDownloadInfo.size() > 0) {
                    FileDownloader.this.config.removeBean(((C3692ad) FileDownloader.this.mDownloadInfo.get(Long.valueOf(httpDownloadImpl.getId()))).m1833e("download_uri"));
                    FileDownloader.this.mDownloadInfo.remove(Long.valueOf(httpDownloadImpl.getId()));
                }
                DebugLog.LogD("path=" + str);
                if (FileDownloader.this.mDownloadListener.size() <= 0 || FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId())) == null) {
                    return;
                }
                FileDownloadListener fileDownloadListener = (FileDownloadListener) FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId()));
                if (FileUtil.checkFileMD5(m1833e, str)) {
                    DebugLog.LogD("this file calculate md5 success！");
                    fileDownloadListener.onCompleted(str, null);
                } else {
                    fileDownloadListener.onCompleted(null, new SpeechError(20014));
                    DebugLog.LogE("this file calculate md5 error!");
                }
                FileDownloader.this.mDownloadListener.remove(Long.valueOf(httpDownloadImpl.getId()));
                return;
            }
            httpDownloadImpl.cancel();
        }

        @Override // com.iflytek.cloud.msc.util.http.HttpDownloadListener
        public void onError(int i, HttpDownloadImpl httpDownloadImpl) {
            DebugLog.LogE("httpdownload onError:errorCode:" + i);
            if (FileDownloader.this.mDownloadInfo != null) {
                if (FileDownloader.this.mFileTempPath.size() > 0) {
                    FileDownloader.this.mFileTempPath.remove(Long.valueOf(httpDownloadImpl.getId()));
                }
                if (FileDownloader.this.mDownloadInfo.size() > 0) {
                    FileDownloader.this.mDownloadInfo.remove(Long.valueOf(httpDownloadImpl.getId()));
                }
                if (FileDownloader.this.mDownloadListener.size() <= 0 || FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId())) == null) {
                    return;
                }
                ((FileDownloadListener) FileDownloader.this.mDownloadListener.get(Long.valueOf(httpDownloadImpl.getId()))).onCompleted(null, new SpeechError(i));
                FileDownloader.this.mDownloadListener.remove(Long.valueOf(httpDownloadImpl.getId()));
                return;
            }
            httpDownloadImpl.cancel();
        }
    };
    private HashMap<Long, C3692ad> mDownloadInfo = new HashMap<>();
    private HashMap<Long, String> mFileTempPath = new HashMap<>();
    private HashMap<Long, FileDownloadListener> mDownloadListener = new HashMap<>();

    public static FileDownloader getDownLoadManager(Context context) {
        if (mFileDownload == null) {
            mFileDownload = new FileDownloader(context);
        }
        return mFileDownload;
    }

    private FileDownloader(Context context) {
        this.config = null;
        this.mContext = context;
        this.config = Config.getConfig(this.mContext);
    }

    public int startDownload(String str, String str2, String str3, FileDownloadListener fileDownloadListener) {
        long taskId = getTaskId(str, str2, str3);
        if (this.mDownloadInfo.size() > 0 && taskId != 0) {
            this.mDownloadListener.put(Long.valueOf(taskId), fileDownloadListener);
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (fileDownloadListener != null) {
            this.mDownloadListener.put(Long.valueOf(currentTimeMillis), fileDownloadListener);
        }
        C3692ad c3692ad = new C3692ad();
        c3692ad.m1822a("download_uri", str);
        c3692ad.m1822a("file_path", str2);
        c3692ad.m1822a("file_md5", str3);
        this.mDownloadInfo.put(Long.valueOf(currentTimeMillis), c3692ad);
        DebugLog.LogD("tempFile:" + this.config.getString(str, null));
        return 0;
    }

    private long getTaskId(String str, String str2, String str3) {
        synchronized (this.mSynObj) {
            for (Map.Entry<Long, C3692ad> entry : this.mDownloadInfo.entrySet()) {
                long longValue = entry.getKey().longValue();
                C3692ad value = entry.getValue();
                if (value.m1833e("download_uri").equals(str) && value.m1833e("file_path").equals(str2) && value.m1833e("file_md5").equals(str3)) {
                    return longValue;
                }
            }
            return 0L;
        }
    }
}
