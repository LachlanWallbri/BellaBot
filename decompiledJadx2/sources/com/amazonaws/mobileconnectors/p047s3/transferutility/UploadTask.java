package com.amazonaws.mobileconnectors.p047s3.transferutility;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.retry.RetryUtils;
import com.amazonaws.services.p048s3.AmazonS3;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.p048s3.model.CannedAccessControlList;
import com.amazonaws.services.p048s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.p048s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.p048s3.model.ObjectMetadata;
import com.amazonaws.services.p048s3.model.ObjectTagging;
import com.amazonaws.services.p048s3.model.PutObjectRequest;
import com.amazonaws.services.p048s3.model.SSEAwsKeyManagementParams;
import com.amazonaws.services.p048s3.model.Tag;
import com.amazonaws.services.p048s3.model.UploadPartRequest;
import com.amazonaws.services.p048s3.util.Mimetypes;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class UploadTask implements Callable<Boolean> {
    private static final String OBJECT_TAGS_DELIMITER = "&";
    private static final String OBJECT_TAG_KEY_VALUE_SEPARATOR = "=";
    private static final String REQUESTER_PAYS = "requester";
    private final TransferDBUtil dbUtil;
    private List<UploadPartRequest> requestList;

    /* renamed from: s3 */
    private final AmazonS3 f1162s3;
    private final TransferStatusUpdater updater;
    private final TransferRecord upload;
    Map<Integer, UploadPartTaskMetadata> uploadPartTasks = new HashMap();
    private static final Log LOGGER = LogFactory.getLog((Class<?>) UploadTask.class);
    private static final Map<String, CannedAccessControlList> CANNED_ACL_MAP = new HashMap();

    static {
        for (CannedAccessControlList cannedAccessControlList : CannedAccessControlList.values()) {
            CANNED_ACL_MAP.put(cannedAccessControlList.toString(), cannedAccessControlList);
        }
    }

    public UploadTask(TransferRecord transferRecord, AmazonS3 amazonS3, TransferDBUtil transferDBUtil, TransferStatusUpdater transferStatusUpdater) {
        this.upload = transferRecord;
        this.f1162s3 = amazonS3;
        this.dbUtil = transferDBUtil;
        this.updater = transferStatusUpdater;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() throws Exception {
        try {
            if (TransferNetworkLossHandler.getInstance() != null && !TransferNetworkLossHandler.getInstance().isNetworkConnected()) {
                LOGGER.info("Network not connected. Setting the state to WAITING_FOR_NETWORK.");
                this.updater.updateState(this.upload.f1158id, TransferState.WAITING_FOR_NETWORK);
                return false;
            }
        } catch (TransferUtilityException e) {
            LOGGER.error("TransferUtilityException: [" + e + "]");
        }
        this.updater.updateState(this.upload.f1158id, TransferState.IN_PROGRESS);
        if (this.upload.isMultipart == 1 && this.upload.partNumber == 0) {
            return uploadMultipartAndWaitForCompletion();
        }
        if (this.upload.isMultipart == 0) {
            return uploadSinglePartAndWaitForCompletion();
        }
        return false;
    }

    private Boolean uploadMultipartAndWaitForCompletion() throws ExecutionException {
        long j;
        if (this.upload.multipartId == null || this.upload.multipartId.isEmpty()) {
            PutObjectRequest createPutObjectRequest = createPutObjectRequest(this.upload);
            TransferUtility.appendMultipartTransferServiceUserAgentString(createPutObjectRequest);
            try {
                this.upload.multipartId = initiateMultipartUpload(createPutObjectRequest);
                this.dbUtil.updateMultipartId(this.upload.f1158id, this.upload.multipartId);
                j = 0;
            } catch (AmazonClientException e) {
                LOGGER.error("Error initiating multipart upload: " + this.upload.f1158id + " due to " + e.getMessage(), e);
                this.updater.throwError(this.upload.f1158id, e);
                this.updater.updateState(this.upload.f1158id, TransferState.FAILED);
                return false;
            }
        } else {
            long queryBytesTransferredByMainUploadId = this.dbUtil.queryBytesTransferredByMainUploadId(this.upload.f1158id);
            if (queryBytesTransferredByMainUploadId > 0) {
                LOGGER.info(String.format("Resume transfer %d from %d bytes", Integer.valueOf(this.upload.f1158id), Long.valueOf(queryBytesTransferredByMainUploadId)));
            }
            j = queryBytesTransferredByMainUploadId;
        }
        UploadTaskProgressListener uploadTaskProgressListener = new UploadTaskProgressListener(this.upload);
        this.updater.updateProgress(this.upload.f1158id, j, this.upload.bytesTotal, false);
        this.requestList = this.dbUtil.getNonCompletedPartRequestsFromDB(this.upload.f1158id, this.upload.multipartId);
        LOGGER.info("Multipart upload " + this.upload.f1158id + " in " + this.requestList.size() + " parts.");
        for (UploadPartRequest uploadPartRequest : this.requestList) {
            TransferUtility.appendMultipartTransferServiceUserAgentString(uploadPartRequest);
            UploadPartTaskMetadata uploadPartTaskMetadata = new UploadPartTaskMetadata();
            uploadPartTaskMetadata.uploadPartRequest = uploadPartRequest;
            uploadPartTaskMetadata.bytesTransferredSoFar = 0L;
            uploadPartTaskMetadata.state = TransferState.WAITING;
            this.uploadPartTasks.put(Integer.valueOf(uploadPartRequest.getPartNumber()), uploadPartTaskMetadata);
            uploadPartTaskMetadata.uploadPartTask = TransferThreadPool.submitTask(new UploadPartTask(uploadPartTaskMetadata, uploadTaskProgressListener, uploadPartRequest, this.f1162s3, this.dbUtil));
        }
        try {
            Iterator<UploadPartTaskMetadata> it = this.uploadPartTasks.values().iterator();
            boolean z = true;
            while (it.hasNext()) {
                z &= it.next().uploadPartTask.get().booleanValue();
            }
            if (!z) {
                try {
                    if (TransferNetworkLossHandler.getInstance() != null && !TransferNetworkLossHandler.getInstance().isNetworkConnected()) {
                        LOGGER.info("Network not connected. Setting the state to WAITING_FOR_NETWORK.");
                        this.updater.updateState(this.upload.f1158id, TransferState.WAITING_FOR_NETWORK);
                        return false;
                    }
                } catch (TransferUtilityException e2) {
                    LOGGER.error("TransferUtilityException: [" + e2 + "]");
                }
            }
            LOGGER.info("Completing the multi-part upload transfer for " + this.upload.f1158id);
            try {
                completeMultiPartUpload(this.upload.f1158id, this.upload.bucketName, this.upload.key, this.upload.multipartId);
                this.updater.updateProgress(this.upload.f1158id, this.upload.bytesTotal, this.upload.bytesTotal, true);
                this.updater.updateState(this.upload.f1158id, TransferState.COMPLETED);
                return true;
            } catch (AmazonClientException e3) {
                LOGGER.error("Failed to complete multipart: " + this.upload.f1158id + " due to " + e3.getMessage(), e3);
                abortMultiPartUpload(this.upload.f1158id, this.upload.bucketName, this.upload.key, this.upload.multipartId);
                this.updater.throwError(this.upload.f1158id, e3);
                this.updater.updateState(this.upload.f1158id, TransferState.FAILED);
                return false;
            }
        } catch (Exception e4) {
            LOGGER.error("Upload resulted in an exception. " + e4);
            Iterator<UploadPartTaskMetadata> it2 = this.uploadPartTasks.values().iterator();
            while (it2.hasNext()) {
                it2.next().uploadPartTask.cancel(true);
            }
            if (TransferState.PENDING_CANCEL.equals(this.upload.state)) {
                this.updater.updateState(this.upload.f1158id, TransferState.CANCELED);
                LOGGER.info("Transfer is " + TransferState.CANCELED);
                return false;
            }
            if (TransferState.PENDING_PAUSE.equals(this.upload.state)) {
                this.updater.updateState(this.upload.f1158id, TransferState.PAUSED);
                LOGGER.info("Transfer is " + TransferState.PAUSED);
                return false;
            }
            Iterator<UploadPartTaskMetadata> it3 = this.uploadPartTasks.values().iterator();
            while (it3.hasNext()) {
                if (TransferState.WAITING_FOR_NETWORK.equals(it3.next().state)) {
                    LOGGER.info("Individual part is WAITING_FOR_NETWORK.");
                    this.updater.updateState(this.upload.f1158id, TransferState.WAITING_FOR_NETWORK);
                    return false;
                }
            }
            try {
                if (TransferNetworkLossHandler.getInstance() != null && !TransferNetworkLossHandler.getInstance().isNetworkConnected()) {
                    LOGGER.info("Network not connected. Setting the state to WAITING_FOR_NETWORK.");
                    this.updater.updateState(this.upload.f1158id, TransferState.WAITING_FOR_NETWORK);
                    return false;
                }
            } catch (TransferUtilityException e5) {
                LOGGER.error("TransferUtilityException: [" + e5 + "]");
            }
            if (RetryUtils.isInterrupted(e4)) {
                LOGGER.info("Transfer is interrupted. " + e4);
                this.updater.updateState(this.upload.f1158id, TransferState.FAILED);
                return false;
            }
            LOGGER.error("Error encountered during multi-part upload: " + this.upload.f1158id + " due to " + e4.getMessage(), e4);
            this.updater.throwError(this.upload.f1158id, e4);
            this.updater.updateState(this.upload.f1158id, TransferState.FAILED);
            return false;
        }
    }

    private Boolean uploadSinglePartAndWaitForCompletion() {
        PutObjectRequest createPutObjectRequest = createPutObjectRequest(this.upload);
        ProgressListener newProgressListener = this.updater.newProgressListener(this.upload.f1158id);
        long length = createPutObjectRequest.getFile().length();
        TransferUtility.appendTransferServiceUserAgentString(createPutObjectRequest);
        createPutObjectRequest.setGeneralProgressListener(newProgressListener);
        try {
            this.f1162s3.putObject(createPutObjectRequest);
            this.updater.updateProgress(this.upload.f1158id, length, length, true);
            this.updater.updateState(this.upload.f1158id, TransferState.COMPLETED);
            return true;
        } catch (Exception e) {
            if (TransferState.PENDING_CANCEL.equals(this.upload.state)) {
                this.updater.updateState(this.upload.f1158id, TransferState.CANCELED);
                LOGGER.info("Transfer is " + TransferState.CANCELED);
                return false;
            }
            if (TransferState.PENDING_PAUSE.equals(this.upload.state)) {
                this.updater.updateState(this.upload.f1158id, TransferState.PAUSED);
                LOGGER.info("Transfer is " + TransferState.PAUSED);
                new ProgressEvent(0L).setEventCode(32);
                newProgressListener.progressChanged(new ProgressEvent(0L));
                return false;
            }
            try {
                if (TransferNetworkLossHandler.getInstance() != null && !TransferNetworkLossHandler.getInstance().isNetworkConnected()) {
                    LOGGER.info("Thread:[" + Thread.currentThread().getId() + "]: Network wasn't available.");
                    this.updater.updateState(this.upload.f1158id, TransferState.WAITING_FOR_NETWORK);
                    LOGGER.debug("Network Connection Interrupted: Moving the TransferState to WAITING_FOR_NETWORK");
                    new ProgressEvent(0L).setEventCode(32);
                    newProgressListener.progressChanged(new ProgressEvent(0L));
                    return false;
                }
            } catch (TransferUtilityException e2) {
                LOGGER.error("TransferUtilityException: [" + e2 + "]");
            }
            if (RetryUtils.isInterrupted(e)) {
                LOGGER.info("Transfer is interrupted. " + e);
                this.updater.updateState(this.upload.f1158id, TransferState.FAILED);
                return false;
            }
            LOGGER.debug("Failed to upload: " + this.upload.f1158id + " due to " + e.getMessage());
            this.updater.throwError(this.upload.f1158id, e);
            this.updater.updateState(this.upload.f1158id, TransferState.FAILED);
            return false;
        }
    }

    private void completeMultiPartUpload(int i, String str, String str2, String str3) throws AmazonClientException, AmazonServiceException {
        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(str, str2, str3, this.dbUtil.queryPartETagsOfUpload(i));
        TransferUtility.appendMultipartTransferServiceUserAgentString(completeMultipartUploadRequest);
        this.f1162s3.completeMultipartUpload(completeMultipartUploadRequest);
    }

    private void abortMultiPartUpload(int i, String str, String str2, String str3) {
        LOGGER.info("Aborting the multipart since complete multipart failed.");
        try {
            this.f1162s3.abortMultipartUpload(new AbortMultipartUploadRequest(str, str2, str3));
            LOGGER.debug("Successfully aborted multipart upload: " + i);
        } catch (AmazonClientException e) {
            LOGGER.debug("Failed to abort the multipart upload: " + i, e);
        }
    }

    private String initiateMultipartUpload(PutObjectRequest putObjectRequest) {
        InitiateMultipartUploadRequest withTagging = new InitiateMultipartUploadRequest(putObjectRequest.getBucketName(), putObjectRequest.getKey()).withCannedACL(putObjectRequest.getCannedAcl()).withObjectMetadata(putObjectRequest.getMetadata()).withSSEAwsKeyManagementParams(putObjectRequest.getSSEAwsKeyManagementParams()).withTagging(putObjectRequest.getTagging());
        TransferUtility.appendMultipartTransferServiceUserAgentString(withTagging);
        return this.f1162s3.initiateMultipartUpload(withTagging).getUploadId();
    }

    private PutObjectRequest createPutObjectRequest(TransferRecord transferRecord) {
        File file = new File(transferRecord.file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(transferRecord.bucketName, transferRecord.key, file);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.length());
        if (transferRecord.headerCacheControl != null) {
            objectMetadata.setCacheControl(transferRecord.headerCacheControl);
        }
        if (transferRecord.headerContentDisposition != null) {
            objectMetadata.setContentDisposition(transferRecord.headerContentDisposition);
        }
        if (transferRecord.headerContentEncoding != null) {
            objectMetadata.setContentEncoding(transferRecord.headerContentEncoding);
        }
        if (transferRecord.headerContentType != null) {
            objectMetadata.setContentType(transferRecord.headerContentType);
        } else {
            objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(file));
        }
        if (transferRecord.headerStorageClass != null) {
            putObjectRequest.setStorageClass(transferRecord.headerStorageClass);
        }
        if (transferRecord.expirationTimeRuleId != null) {
            objectMetadata.setExpirationTimeRuleId(transferRecord.expirationTimeRuleId);
        }
        if (transferRecord.httpExpires != null) {
            objectMetadata.setHttpExpiresDate(new Date(Long.valueOf(transferRecord.httpExpires).longValue()));
        }
        if (transferRecord.sseAlgorithm != null) {
            objectMetadata.setSSEAlgorithm(transferRecord.sseAlgorithm);
        }
        if (transferRecord.userMetadata != null) {
            objectMetadata.setUserMetadata(transferRecord.userMetadata);
            String str = transferRecord.userMetadata.get(Headers.S3_TAGGING);
            if (str != null) {
                try {
                    String[] split = str.split(OBJECT_TAGS_DELIMITER);
                    ArrayList arrayList = new ArrayList();
                    for (String str2 : split) {
                        String[] split2 = str2.split(OBJECT_TAG_KEY_VALUE_SEPARATOR);
                        arrayList.add(new Tag(split2[0], split2[1]));
                    }
                    putObjectRequest.setTagging(new ObjectTagging(arrayList));
                } catch (Exception e) {
                    LOGGER.error("Error in passing the object tags as request headers.", e);
                }
            }
            String str3 = transferRecord.userMetadata.get(Headers.REDIRECT_LOCATION);
            if (str3 != null) {
                putObjectRequest.setRedirectLocation(str3);
            }
            String str4 = transferRecord.userMetadata.get(Headers.REQUESTER_PAYS_HEADER);
            if (str4 != null) {
                putObjectRequest.setRequesterPays("requester".equals(str4));
            }
        }
        if (transferRecord.md5 != null) {
            objectMetadata.setContentMD5(transferRecord.md5);
        }
        if (transferRecord.sseKMSKey != null) {
            putObjectRequest.setSSEAwsKeyManagementParams(new SSEAwsKeyManagementParams(transferRecord.sseKMSKey));
        }
        putObjectRequest.setMetadata(objectMetadata);
        putObjectRequest.setCannedAcl(getCannedAclFromString(transferRecord.cannedAcl));
        return putObjectRequest;
    }

    private static CannedAccessControlList getCannedAclFromString(String str) {
        if (str == null) {
            return null;
        }
        return CANNED_ACL_MAP.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class UploadTaskProgressListener implements ProgressListener {
        private long prevTotalBytesTransferredOfAllParts;

        @Override // com.amazonaws.event.ProgressListener
        public void progressChanged(ProgressEvent progressEvent) {
        }

        UploadTaskProgressListener(TransferRecord transferRecord) {
            this.prevTotalBytesTransferredOfAllParts = transferRecord.bytesCurrent;
        }

        public synchronized void onProgressChanged(int i, long j) {
            UploadPartTaskMetadata uploadPartTaskMetadata = UploadTask.this.uploadPartTasks.get(Integer.valueOf(i));
            if (uploadPartTaskMetadata == null) {
                UploadTask.LOGGER.info("Update received for unknown part. Ignoring.");
                return;
            }
            uploadPartTaskMetadata.bytesTransferredSoFar = j;
            long j2 = 0;
            Iterator<Map.Entry<Integer, UploadPartTaskMetadata>> it = UploadTask.this.uploadPartTasks.entrySet().iterator();
            while (it.hasNext()) {
                j2 += it.next().getValue().bytesTransferredSoFar;
            }
            if (j2 > this.prevTotalBytesTransferredOfAllParts) {
                UploadTask.this.updater.updateProgress(UploadTask.this.upload.f1158id, j2, UploadTask.this.upload.bytesTotal, true);
                this.prevTotalBytesTransferredOfAllParts = j2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class UploadPartTaskMetadata {
        long bytesTransferredSoFar;
        TransferState state;
        UploadPartRequest uploadPartRequest;
        Future<Boolean> uploadPartTask;

        UploadPartTaskMetadata() {
        }
    }
}
