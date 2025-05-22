package com.pudutech.importmusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.importmusic.utils.Constant;
import fi.iki.elonen.NanoHTTPD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class MusicHttpServer extends NanoHTTPD {
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final int PORT = 3344;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String TAG = "MusicHttpServer";
    private Context mContext;
    private String mFileName;
    private int mFileSize;
    private Handler mOutHandler;

    public MusicHttpServer(Context context, Handler handler) throws IOException {
        super(PORT);
        Pdlog.m3273d(TAG, "init MusicHttpServer");
        this.mContext = context;
        this.mOutHandler = handler;
        start();
    }

    public static String getLocalIpStr(Context context) {
        return intToIpAddr(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
    }

    private static String intToIpAddr(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255) + ":3344";
    }

    @Override // fi.iki.elonen.NanoHTTPD
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession iHTTPSession) {
        String str;
        NanoHTTPD.Method method = iHTTPSession.getMethod();
        String uri = iHTTPSession.getUri();
        Pdlog.m3273d(TAG, "method: " + method.toString() + "   uri: " + uri);
        String str2 = "";
        if (NanoHTTPD.Method.GET.equals(method)) {
            String substring = uri.substring(1);
            if (uri.equals("/pdd")) {
                substring = "upload_edition_2.html";
            } else if (uri.equals("/pdd/upload")) {
                Map<String, String> parms = iHTTPSession.getParms();
                this.mFileName = parms.get("filename");
                this.mFileSize = Integer.valueOf(parms.get("filesize")).intValue();
                Pdlog.m3273d(TAG, "received post file [get] request: filename: " + this.mFileName + "  filesize: " + this.mFileSize);
                if (!TextUtils.isEmpty(this.mFileName) && this.mFileSize > 0) {
                    return getResponse(null, null, "Get file info successfully");
                }
                return getResponse(NanoHTTPD.Response.Status.BAD_REQUEST, null, "Error 400: Bad Request");
            }
            String str3 = "text/html";
            if (!substring.contains(".html") && !substring.contains(".htm")) {
                if (substring.contains(".js")) {
                    str3 = "text/javascript";
                } else if (substring.contains(".css")) {
                    str3 = "text/css";
                }
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open(substring)));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str2 = str2 + readLine;
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!isZh(this.mContext).booleanValue()) {
                str2 = ReplayLanguage.INSTANCE.replayEn(str2);
            }
            return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, str3, str2);
        }
        if (NanoHTTPD.Method.POST.equals(method)) {
            Map<String, String> hashMap = new HashMap<>();
            if (uri.equals("/pdd/upload")) {
                try {
                    iHTTPSession.parseBody(hashMap);
                } catch (NanoHTTPD.ResponseException e2) {
                    return getResponse(e2.getStatus(), null, e2.getMessage());
                } catch (IOException e3) {
                    return getResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, null, "Error 500: Internal Server Error, IO Exception: " + e3.getMessage());
                }
            }
            if (TextUtils.isEmpty(this.mFileName)) {
                return getResponse(NanoHTTPD.Response.Status.NOT_FOUND, null, "Error 404: File not found");
            }
            if (!hashMap.isEmpty()) {
                Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().getKey();
                    try {
                        str = URLDecoder.decode(key, "utf-8");
                    } catch (UnsupportedEncodingException e4) {
                        e4.printStackTrace();
                        str = "";
                    }
                    if (str.equals(this.mFileName)) {
                        File file = new File(hashMap.get(key));
                        String str4 = Environment.getExternalStorageDirectory() + File.separator + "pudu/music/";
                        File file2 = new File(str4);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        File file3 = new File(str4, this.mFileName);
                        Pdlog.m3273d(TAG, "copy file to sdcard now, cached file path: " + file.getAbsoluteFile() + ", \n target file path: " + file3.getAbsoluteFile());
                        if (!isAvailableSpaceEnough(file)) {
                            return getResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, null, "Machine has no enough space left.");
                        }
                        copyFileToNative(file, file3);
                        return getResponse(null, null, "Receive music and save to native space successfully");
                    }
                }
            }
            return getResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, null, "Error 500: Internal Server Error, parse file faild.");
        }
        return getResponse(null, null, "Default response");
    }

    private NanoHTTPD.Response getResponse(NanoHTTPD.Response.IStatus iStatus, String str, String str2) {
        if (iStatus == null) {
            iStatus = NanoHTTPD.Response.Status.OK;
        }
        if (str == null) {
            str = "text/html";
        }
        Pdlog.m3275i(TAG, "sendresponse, status: " + iStatus + " mimeType: " + str + " responsetxt: " + str2);
        return newFixedLengthResponse(iStatus, str, str2);
    }

    private void sendScanFileBroadcast(File file) {
        if (file != null) {
            this.mContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            Pdlog.m3273d(TAG, "send media file change braodcast");
        }
    }

    private void refreshMediaStore(File file) {
        if (file != null) {
            Pdlog.m3273d(TAG, "try refresh MediaStore for file: " + file.getAbsolutePath());
            MediaScannerConnection.scanFile(this.mContext, new String[]{file.getAbsolutePath()}, new String[]{"audio/*"}, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.pudutech.importmusic.-$$Lambda$MusicHttpServer$v87Ousc91GSV9rL_-Do5KyKpYSU
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public final void onScanCompleted(String str, Uri uri) {
                    Pdlog.m3273d(MusicHttpServer.TAG, "refresh MediaStore completed");
                }
            });
        }
    }

    private boolean isAvailableSpaceEnough(File file) {
        if (checkAvailableSpaceSize(file.length() / 1048576)) {
            return true;
        }
        Handler handler = this.mOutHandler;
        handler.sendMessage(Message.obtain(handler, Constant.MSG_IMPORT_MUSIC_OUTOF_LEGAL_SIZE));
        return false;
    }

    public void copyFileToNative(File file, File file2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            float available = fileInputStream.available();
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            Pdlog.m3273d(TAG, "copy file to native start");
            float f = 0.0f;
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                    f += read;
                    this.mOutHandler.sendMessageDelayed(Message.obtain(this.mOutHandler, Constant.IMPORT_MUSIC_FLAG, Float.valueOf(f / available)), 100L);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    this.mOutHandler.sendMessageDelayed(Message.obtain(this.mOutHandler, Constant.IMPORT_MUSIC_NAME, this.mFileName), 100L);
                    this.mOutHandler.sendMessageDelayed(Message.obtain(this.mOutHandler, Constant.IMPORT_MUSIC_FLAG, Float.valueOf(1.0f)), 100L);
                    refreshMediaStore(file2);
                    Pdlog.m3273d(TAG, "copy file to native complete");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean isZh(Context context) {
        return Boolean.valueOf(context.getResources().getConfiguration().locale.getLanguage().endsWith("zh"));
    }

    public static void verifyStoragePermissions(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
        }
    }

    private boolean checkAvailableSpaceSize(long j) {
        long folderSize = getFolderSize(new File(Environment.getExternalStorageDirectory() + File.separator + "pudu/music/")) + j;
        Pdlog.m3273d(TAG, "total current music folder size with newly add music file is " + folderSize + "MB");
        return folderSize < PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    private long getFolderSize(File file) {
        File[] listFiles;
        long j = 0;
        try {
            listFiles = file.listFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listFiles == null) {
            return 0L;
        }
        for (int i = 0; i < listFiles.length; i++) {
            if (!listFiles[i].isDirectory()) {
                j += listFiles[i].length();
            }
        }
        return j / 1048576;
    }
}
