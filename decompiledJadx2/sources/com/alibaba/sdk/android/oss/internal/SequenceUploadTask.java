package com.alibaba.sdk.android.oss.internal;

import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.TaskCancelException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.common.utils.CRC64;
import com.alibaba.sdk.android.oss.common.utils.OSSSharedPreferences;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.CheckedInputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SequenceUploadTask extends BaseMultipartUploadTask<ResumableUploadRequest, ResumableUploadResult> implements Callable<ResumableUploadResult> {
    private List<Integer> mAlreadyUploadIndex;
    private File mCRC64RecordFile;
    private long mFirstPartSize;
    private File mRecordFile;
    private OSSSharedPreferences mSp;

    public SequenceUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext, InternalRequestOperation internalRequestOperation) {
        super(internalRequestOperation, resumableUploadRequest, oSSCompletedCallback, executionContext);
        this.mAlreadyUploadIndex = new ArrayList();
        this.mSp = OSSSharedPreferences.instance(this.mContext.getApplicationContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0182 A[Catch: ClientException -> 0x01ed, ServiceException -> 0x01ef, TryCatch #6 {ClientException -> 0x01ed, ServiceException -> 0x01ef, blocks: (B:34:0x0169, B:35:0x017c, B:37:0x0182, B:39:0x019e, B:41:0x01a4, B:43:0x01b2, B:44:0x01c7, B:46:0x01e4), top: B:33:0x0169 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x023d  */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void initMultipartUploadId() throws IOException, ClientException, ServiceException {
        String calculateMd5Str;
        Map map;
        int i;
        boolean z;
        List<PartSummary> parts;
        int i2;
        if (!OSSUtils.isEmptyString(((ResumableUploadRequest) this.mRequest).getRecordDirectory())) {
            if (this.mUploadUri != null) {
                ParcelFileDescriptor openFileDescriptor = this.mContext.getApplicationContext().getContentResolver().openFileDescriptor(this.mUploadUri, "r");
                try {
                    calculateMd5Str = BinaryUtil.calculateMd5Str(openFileDescriptor.getFileDescriptor());
                } finally {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                }
            } else {
                calculateMd5Str = BinaryUtil.calculateMd5Str(this.mUploadFilePath);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(calculateMd5Str);
            sb.append(((ResumableUploadRequest) this.mRequest).getBucketName());
            sb.append(((ResumableUploadRequest) this.mRequest).getObjectKey());
            sb.append(String.valueOf(((ResumableUploadRequest) this.mRequest).getPartSize()));
            sb.append(this.mCheckCRC64 ? "-crc64" : "");
            sb.append("-sequence");
            this.mRecordFile = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + BinaryUtil.calculateMd5Str(sb.toString().getBytes()));
            if (this.mRecordFile.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(this.mRecordFile));
                this.mUploadId = bufferedReader.readLine();
                bufferedReader.close();
                OSSLog.logDebug("sequence [initUploadId] - Found record file, uploadid: " + this.mUploadId);
            }
            if (!OSSUtils.isEmptyString(this.mUploadId)) {
                if (this.mCheckCRC64) {
                    File file = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + this.mUploadId);
                    if (file.exists()) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                        try {
                            try {
                                map = (Map) objectInputStream.readObject();
                            } catch (ClassNotFoundException e) {
                                e = e;
                                map = null;
                            }
                            try {
                                file.delete();
                            } catch (ClassNotFoundException e2) {
                                e = e2;
                                OSSLog.logThrowable2Local(e);
                                i = 0;
                                do {
                                    ListPartsRequest listPartsRequest = new ListPartsRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId);
                                    if (i > 0) {
                                    }
                                    OSSAsyncTask<ListPartsResult> listParts = this.mApiOperation.listParts(listPartsRequest, null);
                                    try {
                                        ListPartsResult result = listParts.getResult();
                                        z = result.isTruncated();
                                        i = result.getNextPartNumberMarker();
                                        parts = result.getParts();
                                        while (i2 < parts.size()) {
                                        }
                                    } catch (ClientException e3) {
                                        throw e3;
                                    } catch (ServiceException e4) {
                                        if (e4.getStatusCode() == 404) {
                                            this.mUploadId = null;
                                            z = false;
                                        } else {
                                            throw e4;
                                        }
                                    }
                                    listParts.waitUntilFinished();
                                } while (z);
                                if (!this.mRecordFile.exists()) {
                                    throw new ClientException("Can't create file at path: " + this.mRecordFile.getAbsolutePath() + "\nPlease make sure the directory exist!");
                                }
                                if (OSSUtils.isEmptyString(this.mUploadId)) {
                                }
                                ((ResumableUploadRequest) this.mRequest).setUploadId(this.mUploadId);
                            }
                            i = 0;
                            do {
                                ListPartsRequest listPartsRequest2 = new ListPartsRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId);
                                if (i > 0) {
                                    listPartsRequest2.setPartNumberMarker(Integer.valueOf(i));
                                }
                                OSSAsyncTask<ListPartsResult> listParts2 = this.mApiOperation.listParts(listPartsRequest2, null);
                                ListPartsResult result2 = listParts2.getResult();
                                z = result2.isTruncated();
                                i = result2.getNextPartNumberMarker();
                                parts = result2.getParts();
                                for (i2 = 0; i2 < parts.size(); i2++) {
                                    PartSummary partSummary = parts.get(i2);
                                    PartETag partETag = new PartETag(partSummary.getPartNumber(), partSummary.getETag());
                                    partETag.setPartSize(partSummary.getSize());
                                    if (map != null && map.size() > 0 && map.containsKey(Integer.valueOf(partETag.getPartNumber()))) {
                                        partETag.setCRC64(((Long) map.get(Integer.valueOf(partETag.getPartNumber()))).longValue());
                                    }
                                    this.mPartETags.add(partETag);
                                    this.mUploadedLength += partSummary.getSize();
                                    this.mAlreadyUploadIndex.add(Integer.valueOf(partSummary.getPartNumber()));
                                    if (i2 == 0) {
                                        this.mFirstPartSize = partSummary.getSize();
                                    }
                                }
                                listParts2.waitUntilFinished();
                            } while (z);
                        } finally {
                            objectInputStream.close();
                            file.delete();
                        }
                    }
                }
                map = null;
                i = 0;
                do {
                    ListPartsRequest listPartsRequest22 = new ListPartsRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId);
                    if (i > 0) {
                    }
                    OSSAsyncTask<ListPartsResult> listParts22 = this.mApiOperation.listParts(listPartsRequest22, null);
                    ListPartsResult result22 = listParts22.getResult();
                    z = result22.isTruncated();
                    i = result22.getNextPartNumberMarker();
                    parts = result22.getParts();
                    while (i2 < parts.size()) {
                    }
                    listParts22.waitUntilFinished();
                } while (z);
            }
            if (!this.mRecordFile.exists() && !this.mRecordFile.createNewFile()) {
                throw new ClientException("Can't create file at path: " + this.mRecordFile.getAbsolutePath() + "\nPlease make sure the directory exist!");
            }
        }
        if (OSSUtils.isEmptyString(this.mUploadId)) {
            InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), ((ResumableUploadRequest) this.mRequest).getMetadata());
            initiateMultipartUploadRequest.isSequential = true;
            this.mUploadId = this.mApiOperation.initMultipartUpload(initiateMultipartUploadRequest, null).getResult().getUploadId();
            File file2 = this.mRecordFile;
            if (file2 != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
                bufferedWriter.write(this.mUploadId);
                bufferedWriter.close();
            }
        }
        ((ResumableUploadRequest) this.mRequest).setUploadId(this.mUploadId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    public ResumableUploadResult doMultipartUpload() throws IOException, ClientException, ServiceException, InterruptedException {
        long j = this.mUploadedLength;
        checkCancel();
        int i = this.mPartAttr[0];
        int i2 = this.mPartAttr[1];
        if (this.mPartETags.size() > 0 && this.mAlreadyUploadIndex.size() > 0) {
            if (this.mUploadedLength > this.mFileLength) {
                throw new ClientException("The uploading file is inconsistent with before");
            }
            if (this.mFirstPartSize != i) {
                throw new ClientException("The part size setting is inconsistent with before");
            }
            long j2 = this.mUploadedLength;
            if (!TextUtils.isEmpty(this.mSp.getStringValue(this.mUploadId))) {
                j2 = Long.valueOf(this.mSp.getStringValue(this.mUploadId)).longValue();
            }
            long j3 = j2;
            if (this.mProgressCallback != null) {
                this.mProgressCallback.onProgress(this.mRequest, j3, this.mFileLength);
            }
            this.mSp.removeKey(this.mUploadId);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mAlreadyUploadIndex.size() == 0 || !this.mAlreadyUploadIndex.contains(Integer.valueOf(i3 + 1))) {
                if (i3 == i2 - 1) {
                    i = (int) (this.mFileLength - j);
                }
                OSSLog.logDebug("upload part readByte : " + i);
                j += (long) i;
                uploadPart(i3, i, i2);
                if (this.mUploadException != null) {
                    break;
                }
            }
        }
        checkException();
        CompleteMultipartUploadResult completeMultipartUploadResult = completeMultipartUploadResult();
        ResumableUploadResult resumableUploadResult = completeMultipartUploadResult != null ? new ResumableUploadResult(completeMultipartUploadResult) : null;
        File file = this.mRecordFile;
        if (file != null) {
            file.delete();
        }
        File file2 = this.mCRC64RecordFile;
        if (file2 != null) {
            file2.delete();
        }
        return resumableUploadResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0164 A[Catch: all -> 0x01bb, TryCatch #16 {all -> 0x01bb, blocks: (B:57:0x013e, B:38:0x015c, B:40:0x0164, B:49:0x0168, B:51:0x0182, B:52:0x01a0), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01ad A[Catch: IOException -> 0x0147, TRY_ENTER, TryCatch #9 {IOException -> 0x0147, blocks: (B:27:0x00f5, B:29:0x00fa, B:31:0x00ff, B:59:0x0143, B:61:0x014b, B:63:0x0150, B:42:0x01ad, B:44:0x01b2, B:46:0x01b7), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01b2 A[Catch: IOException -> 0x0147, TryCatch #9 {IOException -> 0x0147, blocks: (B:27:0x00f5, B:29:0x00fa, B:31:0x00ff, B:59:0x0143, B:61:0x014b, B:63:0x0150, B:42:0x01ad, B:44:0x01b2, B:46:0x01b7), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01b7 A[Catch: IOException -> 0x0147, TRY_LEAVE, TryCatch #9 {IOException -> 0x0147, blocks: (B:27:0x00f5, B:29:0x00fa, B:31:0x00ff, B:59:0x0143, B:61:0x014b, B:63:0x0150, B:42:0x01ad, B:44:0x01b2, B:46:0x01b7), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0168 A[Catch: all -> 0x01bb, TryCatch #16 {all -> 0x01bb, blocks: (B:57:0x013e, B:38:0x015c, B:40:0x0164, B:49:0x0168, B:51:0x0182, B:52:0x01a0), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0143 A[Catch: IOException -> 0x0147, TRY_ENTER, TryCatch #9 {IOException -> 0x0147, blocks: (B:27:0x00f5, B:29:0x00fa, B:31:0x00ff, B:59:0x0143, B:61:0x014b, B:63:0x0150, B:42:0x01ad, B:44:0x01b2, B:46:0x01b7), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014b A[Catch: IOException -> 0x0147, TryCatch #9 {IOException -> 0x0147, blocks: (B:27:0x00f5, B:29:0x00fa, B:31:0x00ff, B:59:0x0143, B:61:0x014b, B:63:0x0150, B:42:0x01ad, B:44:0x01b2, B:46:0x01b7), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0150 A[Catch: IOException -> 0x0147, TRY_LEAVE, TryCatch #9 {IOException -> 0x0147, blocks: (B:27:0x00f5, B:29:0x00fa, B:31:0x00ff, B:59:0x0143, B:61:0x014b, B:63:0x0150, B:42:0x01ad, B:44:0x01b2, B:46:0x01b7), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01c9 A[Catch: IOException -> 0x01c5, TryCatch #11 {IOException -> 0x01c5, blocks: (B:81:0x01c1, B:70:0x01c9, B:72:0x01ce), top: B:80:0x01c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01ce A[Catch: IOException -> 0x01c5, TRY_LEAVE, TryCatch #11 {IOException -> 0x01c5, blocks: (B:81:0x01c1, B:70:0x01c9, B:72:0x01ce), top: B:80:0x01c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void uploadPart(int i, int i2, int i3) {
        RandomAccessFile randomAccessFile;
        BufferedInputStream bufferedInputStream;
        InputStream inputStream;
        Throwable th;
        UploadPartRequest uploadPartRequest;
        BufferedInputStream bufferedInputStream2;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                try {
                    if (this.mContext.getCancellationHandler().isCancelled()) {
                        return;
                    }
                    this.mRunPartTaskCount++;
                    preUploadPart(i, i2, i3);
                    long partSize = i * ((ResumableUploadRequest) this.mRequest).getPartSize();
                    byte[] bArr = new byte[i2];
                    if (this.mUploadUri != null) {
                        InputStream openInputStream = this.mContext.getApplicationContext().getContentResolver().openInputStream(this.mUploadUri);
                        try {
                            bufferedInputStream2 = new BufferedInputStream(openInputStream);
                        } catch (ServiceException e) {
                            e = e;
                            bufferedInputStream = null;
                            uploadPartRequest = null;
                        } catch (Exception e2) {
                            e = e2;
                            bufferedInputStream = null;
                        } catch (Throwable th2) {
                            th = th2;
                            randomAccessFile = null;
                            bufferedInputStream = null;
                            inputStream = openInputStream;
                            th = th;
                            if (randomAccessFile != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (bufferedInputStream != null) {
                            }
                        }
                        try {
                            bufferedInputStream2.skip(partSize);
                            bufferedInputStream2.read(bArr, 0, i2);
                            randomAccessFile = null;
                            bufferedInputStream = bufferedInputStream2;
                            inputStream = openInputStream;
                        } catch (ServiceException e3) {
                            e = e3;
                            uploadPartRequest = null;
                            bufferedInputStream = bufferedInputStream2;
                            inputStream = openInputStream;
                            if (e.getStatusCode() != 409) {
                            }
                            if (randomAccessFile2 != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (bufferedInputStream != null) {
                            }
                        } catch (Exception e4) {
                            e = e4;
                            bufferedInputStream = bufferedInputStream2;
                            inputStream = openInputStream;
                            processException(e);
                            if (randomAccessFile2 != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (bufferedInputStream != null) {
                            }
                        } catch (Throwable th3) {
                            randomAccessFile = null;
                            bufferedInputStream = bufferedInputStream2;
                            th = th3;
                            inputStream = openInputStream;
                            if (randomAccessFile != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (bufferedInputStream != null) {
                            }
                        }
                    } else {
                        RandomAccessFile randomAccessFile3 = new RandomAccessFile(this.mUploadFile, "r");
                        try {
                            randomAccessFile3.seek(partSize);
                            randomAccessFile3.readFully(bArr, 0, i2);
                            inputStream = null;
                            bufferedInputStream = null;
                            randomAccessFile = randomAccessFile3;
                        } catch (ServiceException e5) {
                            e = e5;
                            inputStream = null;
                            bufferedInputStream = null;
                            uploadPartRequest = null;
                            randomAccessFile2 = randomAccessFile3;
                            if (e.getStatusCode() != 409) {
                            }
                            if (randomAccessFile2 != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (bufferedInputStream != null) {
                            }
                        } catch (Exception e6) {
                            e = e6;
                            inputStream = null;
                            bufferedInputStream = null;
                            randomAccessFile2 = randomAccessFile3;
                            processException(e);
                            if (randomAccessFile2 != null) {
                                randomAccessFile2.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                                return;
                            }
                            return;
                        } catch (Throwable th4) {
                            th = th4;
                            inputStream = null;
                            bufferedInputStream = null;
                            randomAccessFile = randomAccessFile3;
                            th = th;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e7) {
                                    OSSLog.logThrowable2Local(e7);
                                    throw th;
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (bufferedInputStream != null) {
                                throw th;
                            }
                            bufferedInputStream.close();
                            throw th;
                        }
                    }
                    try {
                        try {
                            uploadPartRequest = new UploadPartRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId, i + 1);
                            try {
                                uploadPartRequest.setPartContent(bArr);
                                uploadPartRequest.setMd5Digest(BinaryUtil.calculateBase64Md5(bArr));
                                uploadPartRequest.setCRC64(((ResumableUploadRequest) this.mRequest).getCRC64());
                                UploadPartResult syncUploadPart = this.mApiOperation.syncUploadPart(uploadPartRequest);
                                PartETag partETag = new PartETag(uploadPartRequest.getPartNumber(), syncUploadPart.getETag());
                                long j = i2;
                                partETag.setPartSize(j);
                                if (this.mCheckCRC64) {
                                    partETag.setCRC64(syncUploadPart.getClientCRC().longValue());
                                }
                                this.mPartETags.add(partETag);
                                this.mUploadedLength += j;
                                uploadPartFinish(partETag);
                                if (this.mContext.getCancellationHandler().isCancelled()) {
                                    TaskCancelException taskCancelException = new TaskCancelException("sequence upload task cancel");
                                    throw new ClientException(taskCancelException.getMessage(), taskCancelException, true);
                                }
                                onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                            } catch (ServiceException e8) {
                                e = e8;
                                randomAccessFile2 = randomAccessFile;
                                if (e.getStatusCode() != 409) {
                                    processException(e);
                                } else {
                                    PartETag partETag2 = new PartETag(uploadPartRequest.getPartNumber(), e.getPartEtag());
                                    partETag2.setPartSize(uploadPartRequest.getPartContent().length);
                                    if (this.mCheckCRC64) {
                                        partETag2.setCRC64(new CheckedInputStream(new ByteArrayInputStream(uploadPartRequest.getPartContent()), new CRC64()).getChecksum().getValue());
                                    }
                                    this.mPartETags.add(partETag2);
                                    this.mUploadedLength += i2;
                                }
                                if (randomAccessFile2 != null) {
                                    randomAccessFile2.close();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                            }
                        } catch (Exception e9) {
                            e = e9;
                            randomAccessFile2 = randomAccessFile;
                            processException(e);
                            if (randomAccessFile2 != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (bufferedInputStream != null) {
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            th = th;
                            if (randomAccessFile != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (bufferedInputStream != null) {
                            }
                        }
                    } catch (ServiceException e10) {
                        e = e10;
                        uploadPartRequest = null;
                    }
                } catch (ServiceException e11) {
                    e = e11;
                    inputStream = null;
                    bufferedInputStream = null;
                    uploadPartRequest = null;
                } catch (Exception e12) {
                    e = e12;
                    inputStream = null;
                    bufferedInputStream = null;
                } catch (Throwable th6) {
                    th = th6;
                    randomAccessFile = null;
                    inputStream = null;
                    bufferedInputStream = null;
                }
            } catch (IOException e13) {
                OSSLog.logThrowable2Local(e13);
            }
        } catch (Throwable th7) {
            th = th7;
            randomAccessFile = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    public void checkException() throws IOException, ServiceException, ClientException {
        if (this.mContext.getCancellationHandler().isCancelled()) {
            if (((ResumableUploadRequest) this.mRequest).deleteUploadOnCancelling().booleanValue()) {
                abortThisUpload();
                File file = this.mRecordFile;
                if (file != null) {
                    file.delete();
                }
            } else if (this.mPartETags != null && this.mPartETags.size() > 0 && this.mCheckCRC64 && ((ResumableUploadRequest) this.mRequest).getRecordDirectory() != null) {
                HashMap hashMap = new HashMap();
                for (PartETag partETag : this.mPartETags) {
                    hashMap.put(Integer.valueOf(partETag.getPartNumber()), Long.valueOf(partETag.getCRC64()));
                }
                ObjectOutputStream objectOutputStream = null;
                try {
                    try {
                        this.mCRC64RecordFile = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + this.mUploadId);
                        if (!this.mCRC64RecordFile.exists()) {
                            this.mCRC64RecordFile.createNewFile();
                        }
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(this.mCRC64RecordFile));
                        try {
                            objectOutputStream2.writeObject(hashMap);
                            objectOutputStream2.close();
                        } catch (IOException e) {
                            e = e;
                            objectOutputStream = objectOutputStream2;
                            OSSLog.logThrowable2Local(e);
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            super.checkException();
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            }
        }
        super.checkException();
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void abortThisUpload() {
        if (this.mUploadId != null) {
            this.mApiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId), null).waitUntilFinished();
        }
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void processException(Exception exc) {
        if (this.mUploadException == null || !exc.getMessage().equals(this.mUploadException.getMessage())) {
            this.mUploadException = exc;
        }
        OSSLog.logThrowable2Local(exc);
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mIsCancel) {
            return;
        }
        this.mIsCancel = true;
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void uploadPartFinish(PartETag partETag) throws Exception {
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mSp.contains(this.mUploadId)) {
            return;
        }
        this.mSp.setStringValue(this.mUploadId, String.valueOf(this.mUploadedLength));
        onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
    }
}
