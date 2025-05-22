package com.pudutech.module_robot_selfcheck.oss;

import android.os.Environment;
import android.util.Log;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: OssService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004J\u0016\u0010%\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004J\u0016\u0010&\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004J\u0010\u0010'\u001a\u00020(2\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0006\u0010)\u001a\u00020\u0013J\u000e\u0010*\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0004J)\u0010+\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004J\u0018\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200H\u0002J\u000e\u00102\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u0004J\u0010\u00105\u001a\u00020\u00132\b\u00106\u001a\u0004\u0018\u00010\u0004J\u0016\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u0002092\u0006\u0010#\u001a\u00020\u0004J\u001b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010$\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010;J#\u0010<\u001a\u0004\u0018\u00010\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010=R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fRN\u0010\r\u001a6\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R7\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dRL\u0010\u001e\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0015\"\u0004\b!\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/oss/OssService;", "", "()V", "TAG", "", "mBucket", "mCallbackAddress", "mOss", "Lcom/alibaba/sdk/android/oss/OSSClient;", "getMOss", "()Lcom/alibaba/sdk/android/oss/OSSClient;", "setMOss", "(Lcom/alibaba/sdk/android/oss/OSSClient;)V", "onErrorListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "url", "errorReason", "", "getOnErrorListener", "()Lkotlin/jvm/functions/Function2;", "setOnErrorListener", "(Lkotlin/jvm/functions/Function2;)V", "onSuccessListener", "Lkotlin/Function1;", "getOnSuccessListener", "()Lkotlin/jvm/functions/Function1;", "setOnSuccessListener", "(Lkotlin/jvm/functions/Function1;)V", "updateProgressListener", "progress", "getUpdateProgressListener", "setUpdateProgressListener", "asyncDownloadFile", "objectName", "filePath", "asyncDownloadSteam", "asyncUploadLocalFile", "checkOSSFileExist", "", "clearAll", "delFile", "downloadFile", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDownloadPath", "getProgress", "totalSize", "", "currentSize", "getRealObjectKey", "setBucketName", OSSConfig.PARAM_BUCKET, "setCallbackAddress", "callbackAddress", "uploadByteArray", "uploadData", "", "uploadFile", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadFileOnMain", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class OssService {
    private String mCallbackAddress;
    private Function2<? super String, ? super String, Unit> onErrorListener;
    private Function1<? super String, Unit> onSuccessListener;
    private Function2<? super String, ? super String, Unit> updateProgressListener;
    private final String TAG = "OssService";
    private String mBucket = Config.INSTANCE.getBUCKET_NAME();
    private OSSClient mOss = new OssManage().getInstance();

    public final OSSClient getMOss() {
        return this.mOss;
    }

    public final void setMOss(OSSClient oSSClient) {
        Intrinsics.checkParameterIsNotNull(oSSClient, "<set-?>");
        this.mOss = oSSClient;
    }

    public final void setBucketName(String bucket) {
        Intrinsics.checkParameterIsNotNull(bucket, "bucket");
        this.mBucket = bucket;
    }

    public final void setCallbackAddress(String callbackAddress) {
        this.mCallbackAddress = callbackAddress;
    }

    public final Object uploadFile(String str, Continuation<? super String> continuation) {
        Pdlog.m3274e(getClass().getName(), "uploadFile");
        if (!new File(str).exists()) {
            Pdlog.m3274e(getClass().getName(), str + "不存在");
            return null;
        }
        return uploadFileOnMain(str, "pudu_cloud_platform/production/map/" + System.currentTimeMillis() + ".pdmap", continuation);
    }

    final /* synthetic */ Object uploadFileOnMain(String str, String str2, Continuation<? super String> continuation) {
        try {
            Intrinsics.checkExpressionValueIsNotNull(this.mOss.putObject(new PutObjectRequest(this.mBucket, str2, str)), "mOss.putObject(put)");
            String presignPublicObjectURL = this.mOss.presignPublicObjectURL(this.mBucket, str2);
            Log.d("PutObject", "UploadSuccess = " + presignPublicObjectURL);
            return presignPublicObjectURL;
        } catch (ClientException e) {
            Object[] objArr = new Object[1];
            String message = e.getMessage();
            if (message == null) {
                message = "本地异常，如网络异常";
            }
            objArr[0] = message;
            Pdlog.m3274e("uploadFileOnMain", objArr);
            return null;
        } catch (ServiceException e2) {
            Object[] objArr2 = new Object[1];
            String message2 = e2.getMessage();
            if (message2 == null) {
                message2 = "服务异常";
            }
            objArr2[0] = message2;
            Pdlog.m3274e("uploadFileOnMain", objArr2);
            return null;
        }
    }

    public final void asyncUploadLocalFile(final String filePath, final String objectName) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(objectName, "objectName");
        OSSClient oSSClient = this.mOss;
        if (oSSClient != null) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.mBucket, objectName, filePath);
            putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$asyncUploadLocalFile$$inlined$let$lambda$1
                @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
                public final void onProgress(PutObjectRequest putObjectRequest2, long j, long j2) {
                    String progress;
                    Function2<String, String, Unit> updateProgressListener = OssService.this.getUpdateProgressListener();
                    if (updateProgressListener != null) {
                        String str = filePath;
                        progress = OssService.this.getProgress(j2, j);
                        updateProgressListener.invoke(str, progress);
                    }
                }
            });
            putObjectRequest.setCRC64(OSSRequest.CRC64Config.YES);
            Intrinsics.checkExpressionValueIsNotNull(oSSClient.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$asyncUploadLocalFile$$inlined$let$lambda$2
                @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                public void onSuccess(PutObjectRequest request, PutObjectResult esul) {
                    String presignPublicObjectURL;
                    String str;
                    Intrinsics.checkParameterIsNotNull(esul, "result");
                    OSSClient mOss = OssService.this.getMOss();
                    if (mOss != null) {
                        str = OssService.this.mBucket;
                        presignPublicObjectURL = mOss.presignPublicObjectURL(str, objectName);
                    } else {
                        presignPublicObjectURL = null;
                    }
                    Function1<String, Unit> onSuccessListener = OssService.this.getOnSuccessListener();
                    if (onSuccessListener != null) {
                        Intrinsics.checkExpressionValueIsNotNull(presignPublicObjectURL, "presignPublicObjectURL");
                        onSuccessListener.invoke(presignPublicObjectURL);
                    }
                }

                @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                public void onFailure(PutObjectRequest putObjectRequest2, ClientException clientException, ServiceException serviceException) {
                    if (clientException != null) {
                        clientException.printStackTrace();
                        Function2<String, String, Unit> onErrorListener = OssService.this.getOnErrorListener();
                        if (onErrorListener != null) {
                            onErrorListener.invoke(filePath, clientException.getMessage());
                        }
                    }
                    if (serviceException != null) {
                        Function2<String, String, Unit> onErrorListener2 = OssService.this.getOnErrorListener();
                        if (onErrorListener2 != null) {
                            onErrorListener2.invoke(filePath, serviceException.getMessage());
                        }
                        Log.e("ErrorCode", serviceException.getErrorCode());
                        Log.e("RequestId", serviceException.getRequestId());
                        Log.e("HostId", serviceException.getHostId());
                        Log.e("RawMessage", serviceException.getRawMessage());
                    }
                }
            }), "it.asyncPutObject(put,\n …     }\n                })");
        }
    }

    public final void uploadByteArray(byte[] uploadData, String objectName) {
        Intrinsics.checkParameterIsNotNull(uploadData, "uploadData");
        Intrinsics.checkParameterIsNotNull(objectName, "objectName");
        if (this.mOss == null) {
            return;
        }
        PutObjectRequest putObjectRequest = new PutObjectRequest(this.mBucket, objectName, uploadData);
        try {
            OSSClient oSSClient = this.mOss;
            if (oSSClient == null) {
                Intrinsics.throwNpe();
            }
            PutObjectResult putObject = oSSClient.putObject(putObjectRequest);
            Intrinsics.checkExpressionValueIsNotNull(putObject, "mOss!!.putObject(put)");
            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putObject.getETag());
            Log.d("RequestId", putObject.getRequestId());
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServiceException e2) {
            Log.e("RequestId", e2.getRequestId());
            Log.e("ErrorCode", e2.getErrorCode());
            Log.e("HostId", e2.getHostId());
            Log.e("RawMessage", e2.getRawMessage());
        }
    }

    public final void asyncDownloadFile(String objectName, final String filePath, final String url) {
        Intrinsics.checkParameterIsNotNull(objectName, "objectName");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(url, "url");
        if (this.mOss == null) {
            Function2<? super String, ? super String, Unit> function2 = this.onErrorListener;
            if (function2 != null) {
                function2.invoke(url, "mOss 对象不能为空");
                return;
            }
            return;
        }
        if (!new File(filePath).exists()) {
            boolean createOrExistsFile = FileUtils.createOrExistsFile(filePath);
            Pdlog.m3274e("mkdirs", Boolean.valueOf(createOrExistsFile), filePath);
            if (!createOrExistsFile) {
                Function2<? super String, ? super String, Unit> function22 = this.onErrorListener;
                if (function22 != null) {
                    function22.invoke(url, "本地地图文件不存在");
                    return;
                }
                return;
            }
        }
        GetObjectRequest getObjectRequest = new GetObjectRequest(this.mBucket, objectName);
        getObjectRequest.setCRC64(OSSRequest.CRC64Config.YES);
        getObjectRequest.setProgressListener(new OSSProgressCallback<GetObjectRequest>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$asyncDownloadFile$1
            @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
            public final void onProgress(GetObjectRequest getObjectRequest2, long j, long j2) {
                String progress;
                String str;
                progress = OssService.this.getProgress(j2, j);
                str = OssService.this.TAG;
                Pdlog.m3274e(str, Long.valueOf(j), Long.valueOf(j2), progress);
                Function2<String, String, Unit> updateProgressListener = OssService.this.getUpdateProgressListener();
                if (updateProgressListener != null) {
                    updateProgressListener.invoke(url, progress);
                }
            }
        });
        OSSClient oSSClient = this.mOss;
        if (oSSClient == null) {
            Intrinsics.throwNpe();
        }
        oSSClient.asyncGetObject(getObjectRequest, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$asyncDownloadFile$2
            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                String str;
                String str2;
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull(result, "result");
                str = OssService.this.TAG;
                Pdlog.m3274e(str, "onSuccess  filePath= " + filePath);
                long contentLength = result.getContentLength();
                int i = (int) contentLength;
                byte[] bArr = new byte[i];
                int i2 = 0;
                while (i2 < contentLength) {
                    try {
                        i2 += result.getObjectContent().read(bArr, i2, i - i2);
                    } catch (Exception e) {
                        Function2<String, String, Unit> onErrorListener = OssService.this.getOnErrorListener();
                        if (onErrorListener != null) {
                            onErrorListener.invoke(url, e.toString());
                        }
                        OSSLog.logInfo(e.toString());
                    }
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                    fileOutputStream.write(bArr);
                    fileOutputStream.close();
                    Function1<String, Unit> onSuccessListener = OssService.this.getOnSuccessListener();
                    if (onSuccessListener != null) {
                        onSuccessListener.invoke(url);
                    }
                } catch (Exception e2) {
                    str2 = OssService.this.TAG;
                    Pdlog.m3274e(str2, "onFailure  filePath= " + filePath + ' ');
                    Function2<String, String, Unit> onErrorListener2 = OssService.this.getOnErrorListener();
                    if (onErrorListener2 != null) {
                        onErrorListener2.invoke(url, e2.toString());
                    }
                }
            }

            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                String str;
                String message;
                Intrinsics.checkParameterIsNotNull(request, "request");
                str = OssService.this.TAG;
                Pdlog.m3274e(str, "onFailure  filePath= " + filePath + " clientException=" + clientException + " serviceException= " + serviceException);
                Function2<String, String, Unit> onErrorListener = OssService.this.getOnErrorListener();
                if (onErrorListener != null) {
                    String str2 = url;
                    if (clientException == null || (message = clientException.getMessage()) == null) {
                        message = serviceException != null ? serviceException.getMessage() : null;
                    }
                    onErrorListener.invoke(str2, message);
                }
            }
        });
    }

    public final Object downloadFile(String str, String str2, final String str3, Continuation<? super Unit> continuation) {
        if (this.mOss == null) {
            return Unit.INSTANCE;
        }
        checkOSSFileExist(str);
        if (!new File(str2).exists()) {
            boolean createOrExistsFile = FileUtils.createOrExistsFile(str2);
            Pdlog.m3274e("mkdirs", Boxing.boxBoolean(createOrExistsFile), str2);
            if (!createOrExistsFile) {
                return Unit.INSTANCE;
            }
        }
        GetObjectRequest getObjectRequest = new GetObjectRequest(this.mBucket, str);
        getObjectRequest.setCRC64(OSSRequest.CRC64Config.YES);
        getObjectRequest.setProgressListener(new OSSProgressCallback<GetObjectRequest>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$downloadFile$2
            @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
            public final void onProgress(GetObjectRequest getObjectRequest2, long j, long j2) {
                String str4;
                String progress;
                String progress2;
                str4 = OssService.this.TAG;
                progress = OssService.this.getProgress(j2, j);
                Pdlog.m3274e(str4, Long.valueOf(j), Long.valueOf(j2), progress);
                Function2<String, String, Unit> updateProgressListener = OssService.this.getUpdateProgressListener();
                if (updateProgressListener != null) {
                    String str5 = str3;
                    progress2 = OssService.this.getProgress(j2, j);
                    updateProgressListener.invoke(str5, progress2);
                }
            }
        });
        OSSClient oSSClient = this.mOss;
        if (oSSClient == null) {
            Intrinsics.throwNpe();
        }
        GetObjectResult object = oSSClient.getObject(getObjectRequest);
        if (object == null || object.getContentLength() == 0 || object.getObjectContent() == null) {
            Function2<? super String, ? super String, Unit> function2 = this.onErrorListener;
            if (function2 != null) {
                function2.invoke(str3, str + " 下载失败");
            }
        } else {
            long contentLength = object.getContentLength();
            int i = (int) contentLength;
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < contentLength) {
                try {
                    i2 += object.getObjectContent().read(bArr, i2, i - i2);
                } catch (Exception e) {
                    Function2<? super String, ? super String, Unit> function22 = this.onErrorListener;
                    if (function22 != null) {
                        function22.invoke(str3, e.toString());
                    }
                    OSSLog.logInfo(e.toString());
                }
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                Pdlog.m3274e(this.TAG, "downloadFile = onSuccessListener");
                Function1<? super String, Unit> function1 = this.onSuccessListener;
                if (function1 != null) {
                    function1.invoke(str3);
                }
            } catch (Exception e2) {
                Pdlog.m3274e(this.TAG, "downloadFile = onErrorListener");
                Function2<? super String, ? super String, Unit> function23 = this.onErrorListener;
                if (function23 != null) {
                    function23.invoke(str3, e2.toString());
                }
            }
        }
        return Unit.INSTANCE;
    }

    public final void asyncDownloadSteam(final String objectName, String filePath) {
        Intrinsics.checkParameterIsNotNull(objectName, "objectName");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        if (this.mOss == null) {
            return;
        }
        GetObjectRequest getObjectRequest = new GetObjectRequest(this.mBucket, objectName);
        getObjectRequest.setProgressListener(new OSSProgressCallback<GetObjectRequest>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$asyncDownloadSteam$1
            @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
            public final void onProgress(GetObjectRequest getObjectRequest2, long j, long j2) {
                String progress;
                OSSLog.logDebug("getobj_progress: " + j + "  total_size: " + j2, false);
                Function2<String, String, Unit> updateProgressListener = OssService.this.getUpdateProgressListener();
                if (updateProgressListener != null) {
                    String str = objectName;
                    progress = OssService.this.getProgress(j2, j);
                    updateProgressListener.invoke(str, progress);
                }
            }
        });
        OSSClient oSSClient = this.mOss;
        if (oSSClient == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(oSSClient.asyncGetObject(getObjectRequest, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$asyncDownloadSteam$task$1
            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                int read;
                Intrinsics.checkParameterIsNotNull(result, "result");
                InputStream objectContent = result.getObjectContent();
                Intrinsics.checkExpressionValueIsNotNull(objectContent, "result.objectContent");
                byte[] bArr = new byte[2048];
                Ref.IntRef intRef = new Ref.IntRef();
                do {
                    try {
                        read = objectContent.read(bArr);
                        intRef.element = read;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                } while (read != -1);
            }

            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                if (clientExcepion != null) {
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        }), "mOss!!.asyncGetObject(ge…         }\n            })");
    }

    public final void delFile(String objectName) {
        Intrinsics.checkParameterIsNotNull(objectName, "objectName");
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.mBucket, objectName);
        OSSClient oSSClient = this.mOss;
        Intrinsics.checkExpressionValueIsNotNull(oSSClient != null ? oSSClient.asyncDeleteObject(deleteObjectRequest, new OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult>() { // from class: com.pudutech.module_robot_selfcheck.oss.OssService$delFile$deleteTask$1
            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onSuccess(DeleteObjectRequest request, DeleteObjectResult result) {
                Log.d("asyncDeleteObject", "success!");
            }

            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onFailure(DeleteObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                if (clientExcepion != null) {
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        }) : null, "mOss?.asyncDeleteObject(…         }\n            })");
    }

    private final boolean checkOSSFileExist(String objectName) {
        OSSClient oSSClient;
        if (this.mOss == null) {
            return false;
        }
        try {
            Pdlog.m3273d("doesObjectExist", objectName, this.mBucket);
            oSSClient = this.mOss;
            if (oSSClient == null) {
                Intrinsics.throwNpe();
            }
        } catch (ClientException e) {
            e.printStackTrace();
            Pdlog.m3274e("ClientException", e.getMessage());
        } catch (ServiceException e2) {
            Pdlog.m3274e("ServiceException", e2.getMessage());
        }
        if (oSSClient.doesObjectExist(this.mBucket, objectName)) {
            Pdlog.m3273d("doesObjectExist", "object exist.");
            return true;
        }
        Pdlog.m3273d("doesObjectExist", "object does not exist.");
        return false;
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
            File externalFilesDir = KtxKt.getAppContext().getExternalFilesDir(null);
            sb2.append(externalFilesDir != null ? externalFilesDir.getAbsolutePath() : null);
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
        String str = url;
        if (str.length() == 0) {
            throw new NullPointerException("url is not empty");
        }
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ("http://" + Config.INSTANCE.getBUCKET_NAME() + FilenameUtils.EXTENSION_SEPARATOR + Config.INSTANCE.getREGION() + ".aliyuncs.com"), false, 2, (Object) null)) {
            return url;
        }
        String substring = url.substring(StringsKt.indexOf$default((CharSequence) str, "com", 0, false, 6, (Object) null) + 4, url.length());
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
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
