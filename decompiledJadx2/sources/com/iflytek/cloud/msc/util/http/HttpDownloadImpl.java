package com.iflytek.cloud.msc.util.http;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.iflytek.cloud.msc.util.HttpRequest;
import com.iflytek.cloud.msc.util.SDCardHelper;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class HttpDownloadImpl implements HttpTransListener {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final String DEFAULT_DL_BINARY_EXTENSION = ".bin";
    private static final String DEFAULT_DL_FILENAME = "downloadfile";
    private static final String DEFAULT_DL_HTML_EXTENSION = ".html";
    private static final String DEFAULT_DL_TEXT_EXTENSION = ".txt";
    private static final String DOWNLOAD_TEMP_EXTENSION = ".tmp";
    private static final String FILENAME_SEQUENCE_SEPARATOR = "-";
    private static final int MIN_PROGRESS_CALLBACK_TIME = 40;
    private long mBytesRead;
    private long mBytesTotal;
    private boolean mCancel;
    private Context mContext;
    private boolean mCover;
    private FileOutputStream mDownloadFileStream;
    private String mExtension;
    private String mFileDir;
    private String mFilename;
    private long mId;
    private long mLastBytesReadWhenChangeProgress;
    private long mLastTimeChangeProgress;
    private HttpDownloadListener mListener;
    private long mMinBytesStepToChangeProgress;
    private String mSpecifiedPath;
    private int mType;
    private HttpRequest request;
    private static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");
    private static Random sRandom = new Random(SystemClock.uptimeMillis());

    public HttpDownloadImpl() {
        this(System.currentTimeMillis(), 0, null);
    }

    public HttpDownloadImpl(int i, Context context) {
        this(System.currentTimeMillis(), 0, context);
        this.mContext = context;
    }

    public HttpDownloadImpl(long j, int i, Context context) {
        this.mId = j;
        this.mType = i;
        this.mContext = context;
        this.request = new HttpRequest();
    }

    public void setHttpDownloadListener(HttpDownloadListener httpDownloadListener) {
        this.mListener = httpDownloadListener;
    }

    public void start(String str, String str2, String str3, boolean z, String str4) {
        long j;
        DebugLog.LogS("currentPath : " + str2 + ", specifiedPath : " + str3 + ", url : " + str);
        this.mFilename = null;
        String str5 = str2 != null ? str2 : str3;
        this.mSpecifiedPath = str3;
        this.mCover = z;
        File file = new File(str5);
        if (file.exists()) {
            if (file.isDirectory()) {
                this.mFileDir = str5;
                this.mCover = false;
            } else {
                this.mFileDir = file.getParentFile().getAbsolutePath();
                if (str2 != null) {
                    j = file.length();
                    this.mFilename = str5;
                    this.mBytesRead = j;
                    DebugLog.LogS("mFileDir : " + this.mFileDir + ", mBytesRead : " + this.mBytesRead + ", mSpecifiedPath : " + this.mSpecifiedPath);
                    this.request.setRequest(str, null, null);
                    this.request.run();
                }
            }
        } else if (str5.endsWith(File.separator)) {
            this.mFileDir = str5;
            this.mCover = false;
        } else {
            this.mFileDir = str5.substring(0, str5.lastIndexOf(File.separator));
        }
        j = 0;
        this.mBytesRead = j;
        DebugLog.LogS("mFileDir : " + this.mFileDir + ", mBytesRead : " + this.mBytesRead + ", mSpecifiedPath : " + this.mSpecifiedPath);
        this.request.setRequest(str, null, null);
        this.request.run();
    }

    public synchronized void cancel() {
        this.mCancel = true;
        this.request.cancel();
        if (!TextUtils.isEmpty(this.mFilename)) {
            File file = new File(this.mFilename);
            if (file.exists()) {
                file.delete();
            }
        }
        closeDownloadFileStream();
    }

    public long getId() {
        return this.mId;
    }

    private static String parseContentDisposition(String str) {
        try {
            Matcher matcher = CONTENT_DISPOSITION_PATTERN.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    private static String[] generateSaveFile(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7;
        String chooseFilename = chooseFilename(str2, str3, str4);
        int lastIndexOf = chooseFilename.lastIndexOf(46);
        if (lastIndexOf < 0) {
            str7 = chooseExtensionFromMimeType(str5, true);
        } else {
            String chooseExtensionFromFilename = chooseExtensionFromFilename(str5, chooseFilename, lastIndexOf);
            chooseFilename = chooseFilename.substring(0, lastIndexOf);
            str7 = chooseExtensionFromFilename;
        }
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        return new String[]{chooseUniqueFilename(str + chooseFilename, str7, str6), str7};
    }

    private static String chooseFilename(String str, String str2, String str3) {
        String str4;
        String decode;
        int lastIndexOf;
        String decode2;
        int lastIndexOf2;
        if (str2 != null) {
            str4 = parseContentDisposition(str2);
            if (str4 != null && (lastIndexOf2 = str4.lastIndexOf(47) + 1) > 0) {
                str4 = str4.substring(lastIndexOf2);
            }
        } else {
            str4 = null;
        }
        if (str4 == null && str3 != null && (decode2 = Uri.decode(str3)) != null && !decode2.endsWith("/") && decode2.indexOf(63) < 0) {
            int lastIndexOf3 = decode2.lastIndexOf(47) + 1;
            str4 = lastIndexOf3 > 0 ? decode2.substring(lastIndexOf3) : decode2;
        }
        if (str4 == null && (decode = Uri.decode(str)) != null && !decode.endsWith("/") && decode.indexOf(63) < 0 && (lastIndexOf = decode.lastIndexOf(47) + 1) > 0) {
            str4 = decode.substring(lastIndexOf);
        }
        if (str4 == null) {
            str4 = DEFAULT_DL_FILENAME;
        }
        return str4.replaceAll("[^a-zA-Z0-9\\.\\-_]+", "_");
    }

    private static String chooseExtensionFromMimeType(String str, boolean z) {
        String str2;
        if (str != null) {
            str2 = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
            if (str2 != null) {
                str2 = "." + str2;
            }
        } else {
            str2 = null;
        }
        return str2 == null ? (str == null || !str.toLowerCase().startsWith("text/")) ? z ? DEFAULT_DL_BINARY_EXTENSION : str2 : str.equalsIgnoreCase("text/html") ? DEFAULT_DL_HTML_EXTENSION : z ? DEFAULT_DL_TEXT_EXTENSION : str2 : str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String chooseExtensionFromFilename(String str, String str2, int i) {
        String str3;
        if (str != null) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str2.substring(str2.lastIndexOf(46) + 1));
            if (mimeTypeFromExtension == null || !mimeTypeFromExtension.equalsIgnoreCase(str)) {
                str3 = chooseExtensionFromMimeType(str, false);
                return str3 != null ? str2.substring(i) : str3;
            }
        }
        str3 = null;
        if (str3 != null) {
        }
    }

    private static String chooseUniqueFilename(String str, String str2, String str3) {
        String str4 = str + str3;
        if (!new File(str + str2).exists() && !new File(str4).exists()) {
            return str;
        }
        String str5 = str + FILENAME_SEQUENCE_SEPARATOR;
        int i = 1;
        for (int i2 = 1; i2 < 1000000000; i2 *= 10) {
            for (int i3 = 0; i3 < 9; i3++) {
                String str6 = str5 + i;
                String str7 = str6 + str3;
                if (!new File(str6 + str2).exists() && !new File(str7).exists()) {
                    return str6;
                }
                i += sRandom.nextInt(i2) + 1;
            }
        }
        return null;
    }

    private void closeDownloadFileStream() {
        FileOutputStream fileOutputStream = this.mDownloadFileStream;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
            }
            this.mDownloadFileStream = null;
        }
        this.mFilename = null;
    }

    @Override // com.iflytek.cloud.msc.util.http.HttpTransListener
    public int onStart(long j, String str, String str2, String str3, String str4, String str5) {
        long avaliableMemSpace;
        DebugLog.LogS("onStart, url = " + str2);
        if (this.mCancel) {
            return -2;
        }
        String[] generateSaveFile = generateSaveFile(this.mFileDir, str2, str4, str5, str, ".tmp");
        if (this.mFilename == null) {
            this.mFilename = generateSaveFile[0];
            String str6 = this.mFilename;
            if (str6 == null) {
                return 20010;
            }
            this.mFilename = str6.concat(".tmp");
        }
        this.mExtension = generateSaveFile[1];
        DebugLog.LogS("onStart, bytesRead : " + this.mBytesRead + ", length : " + j);
        DebugLog.LogS("onStart, filename : " + this.mFilename + ", extension : " + this.mExtension);
        if (SDCardHelper.checkSDCardStatus()) {
            avaliableMemSpace = SDCardHelper.getAvailableSpace(SDCardHelper.getExternalStorageDirectory());
        } else {
            avaliableMemSpace = SDCardHelper.getAvaliableMemSpace(this.mContext);
        }
        if (avaliableMemSpace < j - this.mBytesRead) {
            return 20010;
        }
        File file = new File(this.mFileDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (this.mCancel) {
            return -2;
        }
        DebugLog.LogS("create file success");
        try {
            this.mBytesTotal = j;
            this.mMinBytesStepToChangeProgress = this.mBytesTotal / 100;
            this.mLastTimeChangeProgress = SystemClock.elapsedRealtime();
            this.mLastBytesReadWhenChangeProgress = 0L;
            this.mDownloadFileStream = new FileOutputStream(this.mFilename, true);
            if (this.mCancel) {
                return -2;
            }
            if (this.mListener != null) {
                this.mListener.onStart(j, str, this.mFilename, str3, this);
            }
            return 0;
        } catch (FileNotFoundException e) {
            DebugLog.LogE("onStart, create file error", e.toString());
            return 20010;
        }
    }

    @Override // com.iflytek.cloud.msc.util.http.HttpTransListener
    public int onBuffer(byte[] bArr, int i) {
        if (this.mCancel) {
            return -2;
        }
        try {
            this.mDownloadFileStream.write(bArr, 0, i);
            if (this.mCancel) {
                return -2;
            }
            this.mDownloadFileStream.flush();
            if (this.mCancel) {
                return -2;
            }
            this.mBytesRead += i;
            if (this.mListener != null && this.mBytesRead - this.mLastBytesReadWhenChangeProgress >= this.mMinBytesStepToChangeProgress) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime - this.mLastTimeChangeProgress >= 40) {
                    this.mListener.onProgress(this.mBytesRead, (int) ((this.mBytesRead * 100) / this.mBytesTotal), this);
                    this.mLastBytesReadWhenChangeProgress = this.mBytesRead;
                    this.mLastTimeChangeProgress = elapsedRealtime;
                }
            }
            return 0;
        } catch (IOException unused) {
            return 20010;
        }
    }

    @Override // com.iflytek.cloud.msc.util.http.HttpTransListener
    public void onFinish() {
        String concat;
        DebugLog.LogS("onFinish | cover = " + this.mCover + " mFilename = " + this.mFilename + " mSpecifiedPath = " + this.mSpecifiedPath);
        try {
            if (this.mCover) {
                concat = this.mSpecifiedPath;
            } else {
                concat = this.mFilename.substring(0, this.mFilename.lastIndexOf(46)).concat(this.mExtension);
            }
            File file = new File(concat);
            if (file.exists()) {
                file.delete();
            }
            new File(this.mFilename).renameTo(file);
            if (!this.mCancel && this.mListener != null) {
                this.mListener.onFinish(concat, this);
            }
            closeDownloadFileStream();
        } catch (Exception e) {
            e.printStackTrace();
            onError(20014);
        }
    }

    @Override // com.iflytek.cloud.msc.util.http.HttpTransListener
    public void onError(int i) {
        HttpDownloadListener httpDownloadListener;
        DebugLog.LogE("onError errorCode = " + i);
        if (!this.mCancel && (httpDownloadListener = this.mListener) != null) {
            httpDownloadListener.onError(i, this);
        }
        closeDownloadFileStream();
    }

    public int getType() {
        return this.mType;
    }

    @Override // com.iflytek.cloud.msc.util.http.HttpTransListener
    public void onCancel() {
        DebugLog.LogD("onCancel");
        closeDownloadFileStream();
    }
}
