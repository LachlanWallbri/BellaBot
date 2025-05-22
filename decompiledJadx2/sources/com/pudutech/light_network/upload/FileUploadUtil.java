package com.pudutech.light_network.upload;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.pudutech.light_network.OKHttpClient;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class FileUploadUtil {
    private static final String FILE = "file";
    private static final String TAG = "FileUploadUtils";
    private static IUploadMultipleFile mUploadMultipleFile;
    private static IUploadSingleFile mUploadSingleFile;
    private static final MediaType MEDIA_TYPE_FILE = MediaType.parse("application/octet-stream");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface IUploadMultipleFile {
        void onError();

        void onSuccess(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface IUploadSingleFile {
        void onError();

        void onSuccess(String str);
    }

    public static void setUploadSingleFile(IUploadSingleFile iUploadSingleFile) {
        mUploadSingleFile = iUploadSingleFile;
    }

    public static final void onUploadSingleFile(String str, Map map, String str2) {
        if (str == null) {
            throw new NullPointerException("uploadUrl---》文件上传的url不能为空！");
        }
        if (str2 == null) {
            throw new NullPointerException("filePath---》文件路径不能为空！");
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null && map.size() > 0) {
            type.addPart(RequestBody.create(JSON_TYPE, JSON.toJSONString(map)));
        }
        type.addFormDataPart("file", str2.substring(str2.lastIndexOf("/") + 1), RequestBody.create(MEDIA_TYPE_FILE, new File(str2)));
        OKHttpClient.getInstance(null, null, null, false).newCall(new Request.Builder().url(str).post(type.build()).build()).enqueue(new Callback() { // from class: com.pudutech.light_network.upload.FileUploadUtil.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                try {
                    Log.e(FileUploadUtil.TAG, "上传失败=" + iOException.getLocalizedMessage());
                    if (FileUploadUtil.mUploadSingleFile != null) {
                        FileUploadUtil.mUploadSingleFile.onError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    try {
                        if (!response.isSuccessful()) {
                            if (FileUploadUtil.mUploadSingleFile != null) {
                                FileUploadUtil.mUploadSingleFile.onError();
                                return;
                            }
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                String string = response.body().string();
                Log.e(FileUploadUtil.TAG, "result=" + string);
                if (FileUploadUtil.mUploadSingleFile != null) {
                    FileUploadUtil.mUploadSingleFile.onSuccess(string);
                }
            }
        });
    }

    public static void setUploadMultipleFile(IUploadMultipleFile iUploadMultipleFile) {
        mUploadMultipleFile = iUploadMultipleFile;
    }

    public static final void onUploadMultipleFile(String str, Map map, String... strArr) {
        if (str == null) {
            throw new NullPointerException("uploadUrl---》文件上传的url不能为空！");
        }
        if (strArr == null || strArr.length == 0) {
            throw new NullPointerException("filePaths---》文件路径不能为空！");
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null && map.size() > 0) {
            type.addPart(RequestBody.create(JSON_TYPE, JSON.toJSONString(map)));
        }
        for (String str2 : strArr) {
            type.addFormDataPart("file", str2.substring(str2.lastIndexOf("/") + 1), RequestBody.create(MEDIA_TYPE_FILE, new File(str2)));
        }
        OKHttpClient.getInstance(null, null, null, false).newCall(new Request.Builder().url(str).post(type.build()).build()).enqueue(new Callback() { // from class: com.pudutech.light_network.upload.FileUploadUtil.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                try {
                    Log.e(FileUploadUtil.TAG, "上传失败=" + iOException.getLocalizedMessage());
                    if (FileUploadUtil.mUploadMultipleFile != null) {
                        FileUploadUtil.mUploadMultipleFile.onError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    try {
                        if (!response.isSuccessful()) {
                            if (FileUploadUtil.mUploadMultipleFile != null) {
                                FileUploadUtil.mUploadMultipleFile.onError();
                                return;
                            }
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                String string = response.body().string();
                Log.e(FileUploadUtil.TAG, "result=" + string);
                if (FileUploadUtil.mUploadMultipleFile != null) {
                    FileUploadUtil.mUploadMultipleFile.onSuccess(string);
                }
            }
        });
    }
}
