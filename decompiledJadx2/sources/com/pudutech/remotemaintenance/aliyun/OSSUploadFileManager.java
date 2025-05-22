package com.pudutech.remotemaintenance.aliyun;

import android.text.TextUtils;
import android.util.ArrayMap;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import com.pudutech.remotemaintenance.interf.IFileUploadInterface;
import com.pudutech.remotemaintenance.listener.OnUploadFileListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: OSSUploadFileManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J2\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J:\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u001c\u0010\u0012\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016Jp\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u001a\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aj\n\u0012\u0004\u0012\u00020\u001b\u0018\u0001`\u001c2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/OSSUploadFileManager;", "Lcom/pudutech/remotemaintenance/interf/IFileUploadInterface;", "Lcom/pudutech/remotemaintenance/aliyun/OSSFile;", "()V", "uploadIdMap", "Landroid/util/ArrayMap;", "", "normalUpload", "", "fileUrl", "file", "Ljava/io/File;", OSSConfig.PARAM_BUCKET, "objectKey", "listener", "Lcom/pudutech/remotemaintenance/listener/OnUploadFileListener;", "renewalUpload", RequestParameters.UPLOAD_ID, "uploadFile", "uploadMultipart", "partSize", "", "uploadedLength", "partNumberMarketIndex", "", "partETagList", "Ljava/util/ArrayList;", "Lcom/alibaba/sdk/android/oss/model/PartETag;", "Lkotlin/collections/ArrayList;", "Companion", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class OSSUploadFileManager implements IFileUploadInterface<OSSFile> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<OSSUploadFileManager>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final OSSUploadFileManager invoke() {
            return new OSSUploadFileManager(null);
        }
    });
    private final ArrayMap<String, String> uploadIdMap;

    private OSSUploadFileManager() {
        this.uploadIdMap = new ArrayMap<>();
    }

    public /* synthetic */ OSSUploadFileManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: OSSUploadFileManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/OSSUploadFileManager$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/remotemaintenance/aliyun/OSSUploadFileManager;", "getINSTANCE", "()Lcom/pudutech/remotemaintenance/aliyun/OSSUploadFileManager;", "INSTANCE$delegate", "Lkotlin/Lazy;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        public final OSSUploadFileManager getINSTANCE() {
            Lazy lazy = OSSUploadFileManager.INSTANCE$delegate;
            Companion companion = OSSUploadFileManager.INSTANCE;
            return (OSSUploadFileManager) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.remotemaintenance.interf.IFileUploadInterface
    public void uploadFile(OSSFile file, OnUploadFileListener listener) {
        if (file == null) {
            Pdlog.m3274e(OSSConfig.TAG, "OSSUploadFileManager#uploadFile() failure, reason: file is null.");
            if (listener != null) {
                listener.onFailure("", "", "OSSUploadFileManager#uploadFile() failure, reason: file is null.");
                return;
            }
            return;
        }
        String fileUri = file.getFileUri();
        String region = file.getRegion();
        String accessKeyId = file.getAccessKeyId();
        String accessKeySecret = file.getAccessKeySecret();
        String stsToken = file.getStsToken();
        String bucket = file.getBucket();
        String ossFileDir = file.getOssFileDir();
        String str = fileUri;
        if (!(str == null || str.length() == 0)) {
            String str2 = region;
            if (!(str2 == null || str2.length() == 0)) {
                String str3 = accessKeyId;
                if (!(str3 == null || str3.length() == 0)) {
                    String str4 = accessKeySecret;
                    if (!(str4 == null || str4.length() == 0)) {
                        String str5 = stsToken;
                        if (!(str5 == null || str5.length() == 0)) {
                            String str6 = bucket;
                            if (!(str6 == null || str6.length() == 0)) {
                                String str7 = ossFileDir;
                                if (!(str7 == null || str7.length() == 0)) {
                                    COSSClient.INSTANCE.getINSTANCE().updateCredentialProvider(new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, stsToken));
                                    File file2 = new File(fileUri);
                                    if (!file2.exists()) {
                                        Pdlog.m3274e(OSSConfig.TAG, "文件不存在, path[" + file2.getPath() + ']');
                                        if (listener != null) {
                                            if (fileUri == null) {
                                                fileUri = "";
                                            }
                                            listener.onFailure(fileUri, "", "OSSUploadFileManager#uploadFile() failure, reason: file not exit, Less storage?.");
                                            return;
                                        }
                                        return;
                                    }
                                    String str8 = (ossFileDir + "/") + file2.getName();
                                    String str9 = (((((OSSConfig.PREFIX_HTTPS + bucket) + ".") + region) + ".aliyuncs.com") + "/") + str8;
                                    long length = file2.length();
                                    if (length <= OSSConfig.DEFAULT_MULTI_PART_UPLOAD_SIZE) {
                                        Pdlog.m3273d(OSSConfig.TAG, "文件大小为[" + length + "]，启动普通上传");
                                        normalUpload(str9, file2, bucket, str8, listener);
                                        return;
                                    }
                                    Pdlog.m3273d(OSSConfig.TAG, "文件大小为[" + length + "]，启动分片/断点续传");
                                    String str10 = this.uploadIdMap.get(str9);
                                    String str11 = str10;
                                    if (str11 == null || str11.length() == 0) {
                                        InitiateMultipartUploadResult result = COSSClient.INSTANCE.getINSTANCE().getOSSClient().initMultipartUpload(new InitiateMultipartUploadRequest(bucket, str8));
                                        Intrinsics.checkExpressionValueIsNotNull(result, "result");
                                        String uploadId = result.getUploadId();
                                        this.uploadIdMap.put(str9, uploadId);
                                        Pdlog.m3273d("freddy", "uploadId[" + uploadId + ']');
                                        uploadMultipart(str9, file2, bucket, str8, uploadId, 2097152L, 0L, 1, null, listener);
                                        return;
                                    }
                                    renewalUpload(str9, file2, bucket, str8, str10, listener);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        Pdlog.m3274e(OSSConfig.TAG, "OSSUploadFileManager#downloadFile() failure, reason: fileUrl|region|accessKeyId|accessKeySecret|stsToken|bucket|ossFileDir is null or empty.");
        if (listener != null) {
            if (fileUri == null) {
                fileUri = "";
            }
            listener.onFailure(fileUri, "", "OSSUploadFileManager#uploadFile() failure, reason: fileUrl|region|accessKeyId|accessKeySecret|stsToken|bucket|ossFileDir is null or empty.");
        }
    }

    private final void normalUpload(final String fileUrl, final File file, final String bucket, final String objectKey, final OnUploadFileListener listener) {
        final OSSClient oSSClient = COSSClient.INSTANCE.getINSTANCE().getOSSClient();
        if (listener != null) {
            String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "file.path");
            listener.onStart(path, fileUrl);
        }
        Observable.create(new ObservableOnSubscribe<String>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$normalUpload$1
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(final ObservableEmitter<String> emitter) {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, objectKey, file.getPath());
                final Ref.IntRef intRef = new Ref.IntRef();
                intRef.element = 0;
                putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$normalUpload$1.1
                    @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
                    public final void onProgress(PutObjectRequest putObjectRequest2, long j, long j2) {
                        Pdlog.m3273d(OSSConfig.TAG, "currentSize[" + j + "], totalSize[" + j2 + ']');
                        int i = (int) ((j * ((long) 100)) / j2);
                        if (i % 2 != 0 || i == intRef.element) {
                            return;
                        }
                        Pdlog.m3273d(OSSConfig.TAG, "progress[" + i + ']');
                        intRef.element = i;
                        OnUploadFileListener onUploadFileListener = listener;
                        if (onUploadFileListener != null) {
                            String path2 = file.getPath();
                            Intrinsics.checkExpressionValueIsNotNull(path2, "file.path");
                            onUploadFileListener.onProgress(path2, fileUrl, i);
                        }
                    }
                });
                oSSClient.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$normalUpload$1.2
                    @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                        emitter.onNext(fileUrl);
                        emitter.onComplete();
                    }

                    @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                    public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                        Throwable th;
                        String str = (String) null;
                        if (clientException != null) {
                            str = clientException.getMessage();
                            clientException.printStackTrace();
                        } else if (serviceException != null) {
                            str = serviceException.getMessage();
                            serviceException.printStackTrace();
                            Pdlog.m3274e(OSSConfig.TAG, "asyncPutObject() onFailure[errorCode=" + serviceException.getErrorCode() + "]");
                            Pdlog.m3274e(OSSConfig.TAG, "asyncPutObject() onFailure[requestId=" + serviceException.getRequestId() + "]");
                            Pdlog.m3274e(OSSConfig.TAG, "asyncPutObject() onFailure[hostId=" + serviceException.getHostId() + "]");
                            Pdlog.m3274e(OSSConfig.TAG, "asyncPutObject() onFailure[rawMessage=" + serviceException.getRawMessage() + "]");
                        }
                        if (TextUtils.isEmpty(str)) {
                            th = new Throwable("unknown exception.");
                        } else {
                            th = new Throwable(str);
                        }
                        emitter.onError(th);
                        emitter.onComplete();
                    }
                });
            }
        }).subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$normalUpload$2
            private Disposable disposable;

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkParameterIsNotNull(d, "d");
                this.disposable = d;
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onSubscribe() disposable[" + this.disposable + ']');
            }

            @Override // io.reactivex.Observer
            public void onNext(String t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onNext() fileUrl[" + fileUrl + ']');
                OnUploadFileListener onUploadFileListener = listener;
                if (onUploadFileListener != null) {
                    String path2 = file.getPath();
                    Intrinsics.checkExpressionValueIsNotNull(path2, "file.path");
                    onUploadFileListener.onSuccessful(path2, fileUrl);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onError() throwable[" + t + ']');
                Disposable disposable = this.disposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                OnUploadFileListener onUploadFileListener = listener;
                if (onUploadFileListener != null) {
                    String path2 = file.getPath();
                    Intrinsics.checkExpressionValueIsNotNull(path2, "file.path");
                    onUploadFileListener.onFailure(path2, fileUrl, t.getMessage());
                }
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onComplete() fileUrl[" + fileUrl + ']');
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003b A[Catch: Exception -> 0x008c, TryCatch #0 {Exception -> 0x008c, blocks: (B:3:0x0002, B:5:0x002f, B:10:0x003b, B:11:0x0045, B:13:0x004b, B:15:0x006c), top: B:2:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void renewalUpload(String fileUrl, File file, String bucket, String objectKey, String uploadId, OnUploadFileListener listener) {
        boolean z;
        try {
            ListPartsResult result = COSSClient.INSTANCE.getINSTANCE().getOSSClient().listParts(new ListPartsRequest(bucket, objectKey, uploadId));
            Intrinsics.checkExpressionValueIsNotNull(result, "result");
            List<PartSummary> parts = result.getParts();
            List<PartSummary> list = parts;
            if (list != null && !list.isEmpty()) {
                z = false;
                if (z) {
                    ArrayList<PartETag> arrayList = new ArrayList<>();
                    long j = 0;
                    for (PartSummary summary : parts) {
                        Intrinsics.checkExpressionValueIsNotNull(summary, "summary");
                        j += summary.getSize();
                        arrayList.add(new PartETag(summary.getPartNumber(), summary.getETag()));
                    }
                    uploadMultipart(fileUrl, file, bucket, objectKey, uploadId, 2097152L, j, result.getNextPartNumberMarker().intValue() + 1, arrayList, listener);
                    return;
                }
                return;
            }
            z = true;
            if (z) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) {
                String path = file.getPath();
                Intrinsics.checkExpressionValueIsNotNull(path, "file.path");
                listener.onFailure(path, fileUrl, e.getMessage());
            }
        }
    }

    private final void uploadMultipart(final String fileUrl, final File file, final String bucket, final String objectKey, final String uploadId, final long partSize, final long uploadedLength, final int partNumberMarketIndex, final ArrayList<PartETag> partETagList, final OnUploadFileListener listener) {
        final long length = file.length();
        final FileInputStream fileInputStream = new FileInputStream(file);
        final OSSClient oSSClient = COSSClient.INSTANCE.getINSTANCE().getOSSClient();
        if (listener != null) {
            String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "file.path");
            listener.onStart(path, fileUrl);
        }
        Observable.create(new ObservableOnSubscribe<String>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$uploadMultipart$1
            /* JADX WARN: Removed duplicated region for block: B:10:0x001e A[Catch: all -> 0x00d5, Exception -> 0x00d7, TryCatch #1 {Exception -> 0x00d7, blocks: (B:3:0x0005, B:5:0x0012, B:10:0x001e, B:11:0x0025, B:12:0x0037, B:14:0x003f, B:16:0x0094), top: B:2:0x0005, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[Catch: all -> 0x00d5, Exception -> 0x00d7, LOOP:0: B:12:0x0037->B:14:0x003f, LOOP_END, TryCatch #1 {Exception -> 0x00d7, blocks: (B:3:0x0005, B:5:0x0012, B:10:0x001e, B:11:0x0025, B:12:0x0037, B:14:0x003f, B:16:0x0094), top: B:2:0x0005, outer: #0 }] */
            @Override // io.reactivex.ObservableOnSubscribe
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void subscribe(ObservableEmitter<String> emitter) {
                ArrayList arrayList;
                ArrayList arrayList2;
                boolean z;
                final Ref.LongRef longRef;
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                try {
                    try {
                        arrayList = new ArrayList();
                        arrayList2 = partETagList;
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                    if (arrayList2 != null && !arrayList2.isEmpty()) {
                        z = false;
                        if (!z) {
                            arrayList.addAll(partETagList);
                        }
                        longRef = new Ref.LongRef();
                        longRef.element = uploadedLength;
                        int i = partNumberMarketIndex;
                        final Ref.IntRef intRef = new Ref.IntRef();
                        intRef.element = 0;
                        while (longRef.element < length) {
                            byte[] readStreamAsBytesArray = IOUtils.readStreamAsBytesArray(fileInputStream, (int) Math.min(partSize, length - longRef.element));
                            UploadPartRequest uploadPartRequest = new UploadPartRequest(bucket, objectKey, uploadId, i);
                            final Ref.LongRef longRef2 = new Ref.LongRef();
                            longRef2.element = 0L;
                            uploadPartRequest.setProgressCallback(new OSSProgressCallback<UploadPartRequest>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$uploadMultipart$1.1
                                @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
                                public final void onProgress(UploadPartRequest uploadPartRequest2, long j, long j2) {
                                    longRef.element += j - longRef2.element;
                                    Pdlog.m3273d(OSSConfig.TAG, "uploadedSize[" + longRef.element + "], currentSize[" + j + "], lastSize[" + longRef2.element + "], totalSize[" + j2 + "], fileLength[" + length + ']');
                                    int i2 = (int) ((longRef.element * ((long) 100)) / length);
                                    if (i2 % 2 == 0 && i2 != intRef.element) {
                                        Pdlog.m3273d(OSSConfig.TAG, "progress[" + i2 + ']');
                                        intRef.element = i2;
                                        OnUploadFileListener onUploadFileListener = listener;
                                        if (onUploadFileListener != null) {
                                            String path2 = file.getPath();
                                            Intrinsics.checkExpressionValueIsNotNull(path2, "file.path");
                                            onUploadFileListener.onProgress(path2, fileUrl, i2);
                                        }
                                    }
                                    Ref.LongRef longRef3 = longRef2;
                                    if (j == j2) {
                                        j = 0;
                                    }
                                    longRef3.element = j;
                                }
                            });
                            uploadPartRequest.setPartContent(readStreamAsBytesArray);
                            UploadPartResult result = oSSClient.uploadPart(uploadPartRequest);
                            Intrinsics.checkExpressionValueIsNotNull(result, "result");
                            arrayList.add(new PartETag(i, result.getETag()));
                            uploadPartRequest.setProgressCallback((OSSProgressCallback) null);
                            i++;
                        }
                        CompleteMultipartUploadResult completeResult = oSSClient.completeMultipartUpload(new CompleteMultipartUploadRequest(bucket, objectKey, uploadId, arrayList));
                        StringBuilder sb = new StringBuilder();
                        sb.append("multipart upload successful, location[");
                        Intrinsics.checkExpressionValueIsNotNull(completeResult, "completeResult");
                        sb.append(completeResult.getLocation());
                        sb.append(']');
                        Pdlog.m3273d(OSSConfig.TAG, sb.toString());
                        emitter.onNext(fileUrl);
                    }
                    z = true;
                    if (!z) {
                    }
                    longRef = new Ref.LongRef();
                    longRef.element = uploadedLength;
                    int i2 = partNumberMarketIndex;
                    final Ref.IntRef intRef2 = new Ref.IntRef();
                    intRef2.element = 0;
                    while (longRef.element < length) {
                    }
                    CompleteMultipartUploadResult completeResult2 = oSSClient.completeMultipartUpload(new CompleteMultipartUploadRequest(bucket, objectKey, uploadId, arrayList));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("multipart upload successful, location[");
                    Intrinsics.checkExpressionValueIsNotNull(completeResult2, "completeResult");
                    sb2.append(completeResult2.getLocation());
                    sb2.append(']');
                    Pdlog.m3273d(OSSConfig.TAG, sb2.toString());
                    emitter.onNext(fileUrl);
                } finally {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.pudutech.remotemaintenance.aliyun.OSSUploadFileManager$uploadMultipart$2
            private Disposable disposable;

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkParameterIsNotNull(d, "d");
                this.disposable = d;
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onSubscribe() disposable[" + this.disposable + ']');
            }

            @Override // io.reactivex.Observer
            public void onNext(String fileUrl2) {
                ArrayMap arrayMap;
                Intrinsics.checkParameterIsNotNull(fileUrl2, "fileUrl");
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onNext() fileUrl[" + fileUrl2 + ']');
                OnUploadFileListener onUploadFileListener = listener;
                if (onUploadFileListener != null) {
                    String path2 = file.getPath();
                    Intrinsics.checkExpressionValueIsNotNull(path2, "file.path");
                    onUploadFileListener.onSuccessful(path2, fileUrl2);
                }
                arrayMap = OSSUploadFileManager.this.uploadIdMap;
                arrayMap.remove(fileUrl2);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onError() throwable[" + t + ']');
                Disposable disposable = this.disposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                OnUploadFileListener onUploadFileListener = listener;
                if (onUploadFileListener != null) {
                    String path2 = file.getPath();
                    Intrinsics.checkExpressionValueIsNotNull(path2, "file.path");
                    onUploadFileListener.onFailure(path2, fileUrl, t.getMessage());
                }
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                Pdlog.m3273d(OSSConfig.TAG, "uploadFile#onComplete() fileUrl[" + fileUrl + ']');
            }
        });
    }
}
