package com.amazonaws.mobileconnectors.p047s3.transferutility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.p048s3.model.CannedAccessControlList;
import com.amazonaws.services.p048s3.model.ObjectMetadata;
import com.amazonaws.services.p048s3.model.PartETag;
import com.amazonaws.services.p048s3.model.UploadPartRequest;
import com.amazonaws.util.json.JsonUtils;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TransferDBUtil {
    private static final String QUERY_PLACE_HOLDER_STRING = ",?";
    private static TransferDBBase transferDBBase;
    private Gson gson = new Gson();
    private static final Log LOGGER = LogFactory.getLog((Class<?>) TransferDBUtil.class);
    private static final Object LOCK = new Object();

    public TransferDBUtil(Context context) {
        synchronized (LOCK) {
            if (transferDBBase == null) {
                transferDBBase = new TransferDBBase(context);
            }
        }
    }

    public void closeDB() {
        synchronized (LOCK) {
            if (transferDBBase != null) {
                transferDBBase.closeDBHelper();
            }
        }
    }

    public Uri insertMultipartUploadRecord(String str, String str2, File file, long j, int i, String str3, long j2, int i2, TransferUtilityOptions transferUtilityOptions) {
        ContentValues generateContentValuesForMultiPartUpload = generateContentValuesForMultiPartUpload(str, str2, file, j, i, str3, j2, i2, new ObjectMetadata(), null, transferUtilityOptions);
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.insert(transferDBBase2.getContentUri(), generateContentValuesForMultiPartUpload);
    }

    public Uri insertSingleTransferRecord(TransferType transferType, String str, String str2, File file, ObjectMetadata objectMetadata, TransferUtilityOptions transferUtilityOptions) {
        return insertSingleTransferRecord(transferType, str, str2, file, objectMetadata, null, transferUtilityOptions);
    }

    public Uri insertSingleTransferRecord(TransferType transferType, String str, String str2, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferUtilityOptions transferUtilityOptions) {
        ContentValues generateContentValuesForSinglePartTransfer = generateContentValuesForSinglePartTransfer(transferType, str, str2, file, objectMetadata, cannedAccessControlList, transferUtilityOptions);
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.insert(transferDBBase2.getContentUri(), generateContentValuesForSinglePartTransfer);
    }

    public Uri insertSingleTransferRecord(TransferType transferType, String str, String str2, File file, TransferUtilityOptions transferUtilityOptions) {
        return insertSingleTransferRecord(transferType, str, str2, file, new ObjectMetadata(), transferUtilityOptions);
    }

    public int bulkInsertTransferRecords(ContentValues[] contentValuesArr) {
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.bulkInsert(transferDBBase2.getContentUri(), contentValuesArr);
    }

    public int updateTransferRecord(TransferRecord transferRecord) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_ID, Integer.valueOf(transferRecord.f1158id));
        contentValues.put("state", transferRecord.state.toString());
        contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(transferRecord.bytesTotal));
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, Long.valueOf(transferRecord.bytesCurrent));
        return transferDBBase.update(getRecordUri(transferRecord.f1158id), contentValues, null, null);
    }

    public int updateBytesTransferred(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, Long.valueOf(j));
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateBytesTotalForDownload(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(j));
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateState(int i, TransferState transferState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", transferState.toString());
        if (TransferState.FAILED.equals(transferState)) {
            return transferDBBase.update(getRecordUri(i), contentValues, "state not in (?,?,?,?,?) ", new String[]{TransferState.COMPLETED.toString(), TransferState.PENDING_NETWORK_DISCONNECT.toString(), TransferState.PAUSED.toString(), TransferState.CANCELED.toString(), TransferState.WAITING_FOR_NETWORK.toString()});
        }
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateStateAndNotifyUpdate(int i, TransferState transferState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", transferState.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, "_id=" + i, null);
    }

    public int updateMultipartId(int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_MULTIPART_ID, str);
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateETag(int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_ETAG, str);
        return transferDBBase.update(getRecordUri(i), contentValues, null, null);
    }

    public int updateNetworkDisconnected() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PENDING_NETWORK_DISCONNECT.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, "state in (?,?,?)", new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString()});
    }

    public int updateNetworkConnected() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.RESUMED_WAITING.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, "state in (?,?)", new String[]{TransferState.PENDING_NETWORK_DISCONNECT.toString(), TransferState.WAITING_FOR_NETWORK.toString()});
    }

    public int setAllRunningRecordsToPausedBeforeShutdownService() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PAUSED.toString());
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, "state in (?,?,?,?)", new String[]{TransferState.IN_PROGRESS.toString(), TransferState.PENDING_PAUSE.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString()});
    }

    public int pauseAllWithType(TransferType transferType) {
        String[] strArr;
        String str;
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PENDING_PAUSE.toString());
        if (transferType == TransferType.ANY) {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString()};
            str = "state in (?,?,?)";
        } else {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString(), transferType.toString()};
            str = "state in (?,?,?) and type=?";
        }
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, str, strArr);
    }

    public int cancelAllWithType(TransferType transferType) {
        String[] strArr;
        String str;
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", TransferState.PENDING_CANCEL.toString());
        if (transferType == TransferType.ANY) {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString(), TransferState.PAUSED.toString(), TransferState.WAITING_FOR_NETWORK.toString()};
            str = "state in (?,?,?,?,?)";
        } else {
            strArr = new String[]{TransferState.IN_PROGRESS.toString(), TransferState.RESUMED_WAITING.toString(), TransferState.WAITING.toString(), TransferState.PAUSED.toString(), TransferState.WAITING_FOR_NETWORK.toString(), transferType.toString()};
            str = "state in (?,?,?,?,?) and type=?";
        }
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.update(transferDBBase2.getContentUri(), contentValues, str, strArr);
    }

    public Cursor queryAllTransfersWithType(TransferType transferType) {
        if (transferType == TransferType.ANY) {
            TransferDBBase transferDBBase2 = transferDBBase;
            return transferDBBase2.query(transferDBBase2.getContentUri(), null, null, null, null);
        }
        TransferDBBase transferDBBase3 = transferDBBase;
        return transferDBBase3.query(transferDBBase3.getContentUri(), null, "type=?", new String[]{transferType.toString()}, null);
    }

    public Cursor queryTransfersWithTypeAndState(TransferType transferType, TransferState transferState) {
        if (transferType == TransferType.ANY) {
            return transferDBBase.query(getStateUri(transferState), null, null, null, null);
        }
        return transferDBBase.query(getStateUri(transferState), null, "type=?", new String[]{transferType.toString()}, null);
    }

    public Cursor queryTransfersWithTypeAndStates(TransferType transferType, TransferState[] transferStateArr) {
        String str;
        String[] strArr;
        int length = transferStateArr.length;
        String createPlaceholders = createPlaceholders(length);
        int i = 0;
        if (transferType == TransferType.ANY) {
            String str2 = "state in (" + createPlaceholders + ")";
            String[] strArr2 = new String[length];
            while (i < length) {
                strArr2[i] = transferStateArr[i].toString();
                i++;
            }
            str = str2;
            strArr = strArr2;
        } else {
            String str3 = "state in (" + createPlaceholders + ") and type=?";
            String[] strArr3 = new String[length + 1];
            while (i < length) {
                strArr3[i] = transferStateArr[i].toString();
                i++;
            }
            strArr3[i] = transferType.toString();
            str = str3;
            strArr = strArr3;
        }
        TransferDBBase transferDBBase2 = transferDBBase;
        return transferDBBase2.query(transferDBBase2.getContentUri(), null, str, strArr, null);
    }

    public Cursor queryTransferById(int i) {
        return transferDBBase.query(getRecordUri(i), null, null, null, null);
    }

    public long queryBytesTransferredByMainUploadId(int i) {
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, null, null, null);
            long j = 0;
            while (cursor.moveToNext()) {
                if (TransferState.PART_COMPLETED.equals(TransferState.getState(cursor.getString(cursor.getColumnIndexOrThrow("state"))))) {
                    j += cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_TOTAL));
                }
            }
            return j;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
    
        r8 = r0.getLong(r0.getColumnIndexOrThrow(com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable.COLUMN_BYTES_CURRENT));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long queryBytesTransferredOfPartNum(int i, int i2) {
        long j;
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, null, null, null);
            while (true) {
                if (!cursor.moveToNext()) {
                    j = 0;
                    break;
                }
                int i3 = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM));
                String string = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                if (i3 == i2 && !TransferState.PART_COMPLETED.equals(TransferState.getState(string))) {
                    break;
                }
            }
            return j;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public int deleteTransferRecords(int i) {
        return transferDBBase.delete(getRecordUri(i), null, null);
    }

    public List<PartETag> queryPartETagsOfUpload(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, null, null, null);
            while (cursor.moveToNext()) {
                arrayList.add(new PartETag(cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM)), cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_ETAG))));
            }
            return arrayList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public List<UploadPartRequest> getNonCompletedPartRequestsFromDB(int i, String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, null, null, null);
            while (cursor.moveToNext()) {
                if (!TransferState.PART_COMPLETED.equals(TransferState.getState(cursor.getString(cursor.getColumnIndexOrThrow("state"))))) {
                    UploadPartRequest withPartSize = new UploadPartRequest().withId(cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_ID))).withMainUploadId(cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_MAIN_UPLOAD_ID))).withBucketName(cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BUCKET_NAME))).withKey(cursor.getString(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_KEY))).withUploadId(str).withFile(new File(cursor.getString(cursor.getColumnIndexOrThrow("file")))).withFileOffset(cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_FILE_OFFSET))).withPartNumber(cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_PART_NUM))).withPartSize(cursor.getLong(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_TOTAL)));
                    boolean z = true;
                    if (1 != cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_IS_LAST_PART))) {
                        z = false;
                    }
                    arrayList.add(withPartSize.withLastPart(z));
                }
            }
            return arrayList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public boolean checkWaitingForNetworkPartRequestsFromDB(int i) {
        Cursor cursor = null;
        try {
            cursor = transferDBBase.query(getPartUri(i), null, "state=?", new String[]{TransferState.WAITING_FOR_NETWORK.toString()}, null);
            return cursor.moveToNext();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String createPlaceholders(int i) {
        if (i <= 0) {
            LOGGER.error("Cannot create a string of 0 or less placeholders.");
            return null;
        }
        StringBuilder sb = new StringBuilder((i * 2) - 1);
        sb.append("?");
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(QUERY_PLACE_HOLDER_STRING);
        }
        return sb.toString();
    }

    public ContentValues generateContentValuesForMultiPartUpload(String str, String str2, File file, long j, int i, String str3, long j2, int i2, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferUtilityOptions transferUtilityOptions) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", TransferType.UPLOAD.toString());
        contentValues.put("state", TransferState.WAITING.toString());
        contentValues.put(TransferTable.COLUMN_BUCKET_NAME, str);
        contentValues.put(TransferTable.COLUMN_KEY, str2);
        contentValues.put("file", file.getAbsolutePath());
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, (Long) 0L);
        contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(j2));
        contentValues.put(TransferTable.COLUMN_IS_MULTIPART, (Integer) 1);
        contentValues.put(TransferTable.COLUMN_PART_NUM, Integer.valueOf(i));
        contentValues.put(TransferTable.COLUMN_FILE_OFFSET, Long.valueOf(j));
        contentValues.put(TransferTable.COLUMN_MULTIPART_ID, str3);
        contentValues.put(TransferTable.COLUMN_IS_LAST_PART, Integer.valueOf(i2));
        contentValues.put(TransferTable.COLUMN_IS_ENCRYPTED, (Integer) 0);
        contentValues.putAll(generateContentValuesForObjectMetadata(objectMetadata));
        if (cannedAccessControlList != null) {
            contentValues.put(TransferTable.COLUMN_CANNED_ACL, cannedAccessControlList.toString());
        }
        if (transferUtilityOptions != null) {
            contentValues.put(TransferTable.COLUMN_TRANSFER_UTILITY_OPTIONS, this.gson.toJson(transferUtilityOptions));
        }
        return contentValues;
    }

    private ContentValues generateContentValuesForObjectMetadata(ObjectMetadata objectMetadata) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransferTable.COLUMN_USER_METADATA, JsonUtils.mapToString(objectMetadata.getUserMetadata()));
        contentValues.put(TransferTable.COLUMN_HEADER_CONTENT_TYPE, objectMetadata.getContentType());
        contentValues.put(TransferTable.COLUMN_HEADER_CONTENT_ENCODING, objectMetadata.getContentEncoding());
        contentValues.put(TransferTable.COLUMN_HEADER_CACHE_CONTROL, objectMetadata.getCacheControl());
        contentValues.put(TransferTable.COLUMN_CONTENT_MD5, objectMetadata.getContentMD5());
        contentValues.put(TransferTable.COLUMN_HEADER_CONTENT_DISPOSITION, objectMetadata.getContentDisposition());
        contentValues.put(TransferTable.COLUMN_SSE_ALGORITHM, objectMetadata.getSSEAlgorithm());
        contentValues.put(TransferTable.COLUMN_SSE_KMS_KEY, objectMetadata.getSSEAwsKmsKeyId());
        contentValues.put(TransferTable.COLUMN_EXPIRATION_TIME_RULE_ID, objectMetadata.getExpirationTimeRuleId());
        if (objectMetadata.getHttpExpiresDate() != null) {
            contentValues.put(TransferTable.COLUMN_HTTP_EXPIRES_DATE, String.valueOf(objectMetadata.getHttpExpiresDate().getTime()));
        }
        if (objectMetadata.getStorageClass() != null) {
            contentValues.put(TransferTable.COLUMN_HEADER_STORAGE_CLASS, objectMetadata.getStorageClass());
        }
        return contentValues;
    }

    private ContentValues generateContentValuesForSinglePartTransfer(TransferType transferType, String str, String str2, File file, ObjectMetadata objectMetadata, CannedAccessControlList cannedAccessControlList, TransferUtilityOptions transferUtilityOptions) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", transferType.toString());
        contentValues.put("state", TransferState.WAITING.toString());
        contentValues.put(TransferTable.COLUMN_BUCKET_NAME, str);
        contentValues.put(TransferTable.COLUMN_KEY, str2);
        contentValues.put("file", file.getAbsolutePath());
        contentValues.put(TransferTable.COLUMN_BYTES_CURRENT, (Long) 0L);
        if (transferType.equals(TransferType.UPLOAD)) {
            contentValues.put(TransferTable.COLUMN_BYTES_TOTAL, Long.valueOf(file != null ? file.length() : 0L));
        }
        contentValues.put(TransferTable.COLUMN_IS_MULTIPART, (Integer) 0);
        contentValues.put(TransferTable.COLUMN_PART_NUM, (Integer) 0);
        contentValues.put(TransferTable.COLUMN_IS_ENCRYPTED, (Integer) 0);
        contentValues.putAll(generateContentValuesForObjectMetadata(objectMetadata));
        if (cannedAccessControlList != null) {
            contentValues.put(TransferTable.COLUMN_CANNED_ACL, cannedAccessControlList.toString());
        }
        if (transferUtilityOptions != null) {
            contentValues.put(TransferTable.COLUMN_TRANSFER_UTILITY_OPTIONS, this.gson.toJson(transferUtilityOptions));
        }
        return contentValues;
    }

    public Uri getContentUri() {
        return transferDBBase.getContentUri();
    }

    public Uri getRecordUri(int i) {
        return Uri.parse(transferDBBase.getContentUri() + "/" + i);
    }

    public Uri getPartUri(int i) {
        return Uri.parse(transferDBBase.getContentUri() + "/part/" + i);
    }

    public Uri getStateUri(TransferState transferState) {
        return Uri.parse(transferDBBase.getContentUri() + "/state/" + transferState.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransferRecord getTransferById(int i) {
        Cursor cursor;
        TransferRecord transferRecord = null;
        try {
            cursor = queryTransferById(i);
        } catch (Throwable th) {
            th = th;
            cursor = null;
        }
        try {
            if (cursor.moveToFirst()) {
                transferRecord = new TransferRecord(i);
                transferRecord.updateFromDB(cursor);
            }
            if (cursor != null) {
                cursor.close();
            }
            return transferRecord;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    static TransferDBBase getTransferDBBase(Context context) {
        TransferDBBase transferDBBase2;
        synchronized (LOCK) {
            if (transferDBBase == null) {
                transferDBBase = new TransferDBBase(context);
            }
            transferDBBase2 = transferDBBase;
        }
        return transferDBBase2;
    }
}
