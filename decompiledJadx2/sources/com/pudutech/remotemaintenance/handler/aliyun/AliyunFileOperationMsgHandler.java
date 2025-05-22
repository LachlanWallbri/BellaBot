package com.pudutech.remotemaintenance.handler.aliyun;

import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTManager;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import com.pudutech.remotemaintenance.aliyun.OSSFile;
import com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager;
import com.pudutech.remotemaintenance.aliyun.config.AliyunMsgType;
import com.pudutech.remotemaintenance.aliyun.config.MsgType;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import com.pudutech.remotemaintenance.config.IoTConfig;
import com.pudutech.remotemaintenance.listener.OnDownloadFileListener;
import com.pudutech.remotemaintenance.listener.OnUploadFileListener;
import com.pudutech.remotemaintenance.manager.CDownloadFileManager2;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AliyunFileOperationMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunFileOperationMsgHandler;", "Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunAbstractMsgHandler;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "action", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "iotInterface", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AliyunFileOperationMsgHandler extends AliyunAbstractMsgHandler {
    private final String TAG;

    public AliyunFileOperationMsgHandler() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.remotemaintenance.handler.AbstractMsgHandler
    public void action(AliyunIoTMsg msg, final AliyunIoTManager iotInterface) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(iotInterface, "iotInterface");
        Pdlog.m3273d(this.TAG, "msg[" + msg + ']');
        final String sessionId = msg.getSessionId();
        JSONObject parseObject = JSON.parseObject(msg.getContent());
        String string = parseObject.getString(IoTConfig.PARAM_FILE_URI);
        final String string2 = parseObject.getString("type");
        if (Intrinsics.areEqual(string2, IoTConfig.PARAM_PULL_FILE)) {
            String string3 = parseObject.getString(OSSConfig.PARAM_REGION);
            OSSFile oSSFile = new OSSFile(string3, parseObject.getString(OSSConfig.PARAM_ACCESS_KEY_ID), parseObject.getString(OSSConfig.PARAM_ACCESS_KEY_SECRET), parseObject.getString("stsToken"), parseObject.getString(OSSConfig.PARAM_BUCKET), parseObject.getString(OSSConfig.PARAM_OSS_FILE_DIR));
            oSSFile.setFileUri(string);
            OSSConfig.INSTANCE.setENDPOINT(string3 + ".aliyuncs.com");
            OSSUploadFileManager.INSTANCE.getINSTANCE().uploadFile(oSSFile, new OnUploadFileListener() { // from class: com.pudutech.remotemaintenance.handler.aliyun.AliyunFileOperationMsgHandler$action$1
                private String fileName;

                @Override // com.pudutech.remotemaintenance.listener.OnUploadFileListener
                public void onStart(String filePath, String fileUrl) {
                    Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                    Intrinsics.checkParameterIsNotNull(fileUrl, "fileUrl");
                    String substring = filePath.substring(StringsKt.lastIndexOf$default((CharSequence) filePath, "/", 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                    this.fileName = substring;
                    Pdlog.m3273d(OSSConfig.TAG, "开始上传" + filePath + ' ' + fileUrl);
                    AliyunIoTMsg aliyunIoTMsg = new AliyunIoTMsg();
                    aliyunIoTMsg.setSessionId(sessionId);
                    aliyunIoTMsg.setType(AliyunMsgType.RRPC.getType());
                    aliyunIoTMsg.setInstruct(MsgType.FILE_OPERATION.getType());
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = jSONObject;
                    jSONObject2.put((JSONObject) "type", string2);
                    jSONObject2.put((JSONObject) "filename", this.fileName);
                    jSONObject2.put((JSONObject) "progress", (String) 1);
                    aliyunIoTMsg.setContent(jSONObject.toString());
                    iotInterface.sendMsg(aliyunIoTMsg);
                }

                @Override // com.pudutech.remotemaintenance.listener.OnUploadFileListener
                public void onProgress(String filePath, String fileUrl, int progress) {
                    Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                    Intrinsics.checkParameterIsNotNull(fileUrl, "fileUrl");
                    Pdlog.m3273d(OSSConfig.TAG, "上传进度" + progress + ' ' + filePath + ' ' + fileUrl);
                    AliyunIoTMsg aliyunIoTMsg = new AliyunIoTMsg();
                    aliyunIoTMsg.setSessionId(sessionId);
                    aliyunIoTMsg.setType(AliyunMsgType.RRPC.getType());
                    aliyunIoTMsg.setInstruct(MsgType.FILE_OPERATION.getType());
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = jSONObject;
                    jSONObject2.put((JSONObject) "type", string2);
                    jSONObject2.put((JSONObject) "filename", this.fileName);
                    jSONObject2.put((JSONObject) "progress", (String) Integer.valueOf(progress));
                    aliyunIoTMsg.setContent(jSONObject.toString());
                    iotInterface.sendMsg(aliyunIoTMsg);
                }

                @Override // com.pudutech.remotemaintenance.listener.OnUploadFileListener
                public void onSuccessful(String filePath, String fileUrl) {
                    Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                    Intrinsics.checkParameterIsNotNull(fileUrl, "fileUrl");
                    Pdlog.m3273d(OSSConfig.TAG, "上传成功" + filePath + ' ' + fileUrl);
                    AliyunIoTMsg aliyunIoTMsg = new AliyunIoTMsg();
                    aliyunIoTMsg.setSessionId(sessionId);
                    aliyunIoTMsg.setType(AliyunMsgType.RRPC.getType());
                    aliyunIoTMsg.setInstruct(MsgType.FILE_OPERATION.getType());
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = jSONObject;
                    jSONObject2.put((JSONObject) "type", string2);
                    jSONObject2.put((JSONObject) "filename", this.fileName);
                    jSONObject2.put((JSONObject) IoTConfig.PARAM_FILE_URI, fileUrl);
                    aliyunIoTMsg.setContent(jSONObject.toString());
                    iotInterface.sendMsg(aliyunIoTMsg);
                }

                @Override // com.pudutech.remotemaintenance.listener.OnUploadFileListener
                public void onFailure(String filePath, String fileUrl, String errMsg) {
                    Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                    Intrinsics.checkParameterIsNotNull(fileUrl, "fileUrl");
                    Pdlog.m3273d(OSSConfig.TAG, "上传失败" + filePath + ' ' + fileUrl + ' ' + errMsg);
                    AliyunIoTMsg aliyunIoTMsg = new AliyunIoTMsg();
                    aliyunIoTMsg.setSessionId(sessionId);
                    aliyunIoTMsg.setType(AliyunMsgType.RRPC.getType());
                    aliyunIoTMsg.setInstruct(MsgType.FILE_OPERATION.getType());
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = jSONObject;
                    jSONObject2.put((JSONObject) "type", string2);
                    jSONObject2.put((JSONObject) "filename", this.fileName);
                    jSONObject2.put((JSONObject) IoTConfig.PARAM_ERROR_MSG, errMsg);
                    aliyunIoTMsg.setContent(jSONObject.toString());
                    iotInterface.sendMsg(aliyunIoTMsg);
                }
            });
            return;
        }
        if (Intrinsics.areEqual(string2, IoTConfig.PARAM_PUSH_FILE)) {
            final String string4 = parseObject.getString("filename");
            CDownloadFileManager2.INSTANCE.getINSTANCE().downloadFile(string, string4, new OnDownloadFileListener() { // from class: com.pudutech.remotemaintenance.handler.aliyun.AliyunFileOperationMsgHandler$action$2
                @Override // com.pudutech.remotemaintenance.listener.OnDownloadFileListener
                public void onStart(String fileUrl) {
                    Pdlog.m3273d(OSSConfig.TAG, "开始下载" + fileUrl);
                }

                @Override // com.pudutech.remotemaintenance.listener.OnDownloadFileListener
                public void onProgress(String fileUrl, int progress) {
                    Pdlog.m3273d(OSSConfig.TAG, "下载进度" + progress + ' ' + fileUrl);
                    AliyunIoTMsg aliyunIoTMsg = new AliyunIoTMsg();
                    aliyunIoTMsg.setSessionId(sessionId);
                    aliyunIoTMsg.setType(AliyunMsgType.RRPC.getType());
                    aliyunIoTMsg.setInstruct(MsgType.FILE_OPERATION.getType());
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = jSONObject;
                    jSONObject2.put((JSONObject) "type", string2);
                    jSONObject2.put((JSONObject) "filename", string4);
                    jSONObject2.put((JSONObject) "progress", (String) Integer.valueOf(progress));
                    aliyunIoTMsg.setContent(jSONObject.toString());
                    iotInterface.sendMsg(aliyunIoTMsg);
                }

                @Override // com.pudutech.remotemaintenance.listener.OnDownloadFileListener
                public void onSuccessful(String filePath, String fileUrl) {
                    Pdlog.m3273d(OSSConfig.TAG, "下载成功" + filePath + ' ' + fileUrl);
                }

                @Override // com.pudutech.remotemaintenance.listener.OnDownloadFileListener
                public void onFailure(String fileUrl, String errMsg) {
                    Pdlog.m3273d(OSSConfig.TAG, "下载失败" + fileUrl);
                }
            });
        }
    }
}
