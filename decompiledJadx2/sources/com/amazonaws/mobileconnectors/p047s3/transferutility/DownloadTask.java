package com.amazonaws.mobileconnectors.p047s3.transferutility;

import com.amazonaws.AmazonClientException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.retry.RetryUtils;
import com.amazonaws.services.p048s3.AmazonS3;
import com.amazonaws.services.p048s3.model.GetObjectRequest;
import com.amazonaws.services.p048s3.model.S3Object;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DownloadTask implements Callable<Boolean> {
    private static final Log LOGGER = LogFactory.getLog((Class<?>) DownloadTask.class);
    private static final int SIXTEEN_KB = 16384;
    private final TransferRecord download;

    /* renamed from: s3 */
    private final AmazonS3 f1156s3;
    private final TransferStatusUpdater updater;

    public DownloadTask(TransferRecord transferRecord, AmazonS3 amazonS3, TransferStatusUpdater transferStatusUpdater) {
        this.download = transferRecord;
        this.f1156s3 = amazonS3;
        this.updater = transferStatusUpdater;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() {
        try {
            if (TransferNetworkLossHandler.getInstance() != null && !TransferNetworkLossHandler.getInstance().isNetworkConnected()) {
                LOGGER.info("Thread:[" + Thread.currentThread().getId() + "]: Network wasn't available.");
                this.updater.updateState(this.download.f1158id, TransferState.WAITING_FOR_NETWORK);
                return false;
            }
        } catch (TransferUtilityException e) {
            LOGGER.error("TransferUtilityException: [" + e + "]");
        }
        this.updater.updateState(this.download.f1158id, TransferState.IN_PROGRESS);
        ProgressListener newProgressListener = this.updater.newProgressListener(this.download.f1158id);
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(this.download.bucketName, this.download.key);
            TransferUtility.appendTransferServiceUserAgentString(getObjectRequest);
            File file = new File(this.download.file);
            long length = file.length();
            if (length > 0) {
                LOGGER.debug(String.format("Resume transfer %d from %d bytes", Integer.valueOf(this.download.f1158id), Long.valueOf(length)));
                getObjectRequest.setRange(length, -1L);
            }
            getObjectRequest.setGeneralProgressListener(newProgressListener);
            S3Object object = this.f1156s3.getObject(getObjectRequest);
            if (object == null) {
                this.updater.throwError(this.download.f1158id, new IllegalStateException("AmazonS3.getObject returns null"));
                this.updater.updateState(this.download.f1158id, TransferState.FAILED);
                return false;
            }
            long instanceLength = object.getObjectMetadata().getInstanceLength();
            this.updater.updateProgress(this.download.f1158id, length, instanceLength, true);
            saveToFile(object.getObjectContent(), file);
            this.updater.updateProgress(this.download.f1158id, instanceLength, instanceLength, true);
            this.updater.updateState(this.download.f1158id, TransferState.COMPLETED);
            return true;
        } catch (Exception e2) {
            if (TransferState.PENDING_CANCEL.equals(this.download.state)) {
                this.updater.updateState(this.download.f1158id, TransferState.CANCELED);
                LOGGER.info("Transfer is " + TransferState.CANCELED);
                return false;
            }
            if (TransferState.PENDING_PAUSE.equals(this.download.state)) {
                this.updater.updateState(this.download.f1158id, TransferState.PAUSED);
                LOGGER.info("Transfer is " + TransferState.PAUSED);
                new ProgressEvent(0L).setEventCode(32);
                newProgressListener.progressChanged(new ProgressEvent(0L));
                return false;
            }
            try {
                if (TransferNetworkLossHandler.getInstance() != null && !TransferNetworkLossHandler.getInstance().isNetworkConnected()) {
                    LOGGER.info("Thread:[" + Thread.currentThread().getId() + "]: Network wasn't available.");
                    this.updater.updateState(this.download.f1158id, TransferState.WAITING_FOR_NETWORK);
                    LOGGER.debug("Network Connection Interrupted: Moving the TransferState to WAITING_FOR_NETWORK");
                    new ProgressEvent(0L).setEventCode(32);
                    newProgressListener.progressChanged(new ProgressEvent(0L));
                    return false;
                }
            } catch (TransferUtilityException e3) {
                LOGGER.error("TransferUtilityException: [" + e3 + "]");
            }
            if (RetryUtils.isInterrupted(e2)) {
                LOGGER.info("Transfer is interrupted. " + e2);
                this.updater.updateState(this.download.f1158id, TransferState.FAILED);
                return false;
            }
            LOGGER.debug("Failed to download: " + this.download.f1158id + " due to " + e2.getMessage());
            this.updater.throwError(this.download.f1158id, e2);
            this.updater.updateState(this.download.f1158id, TransferState.FAILED);
            return false;
        }
    }

    private void saveToFile(InputStream inputStream, File file) {
        BufferedOutputStream bufferedOutputStream;
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, file.length() > 0));
            } catch (SocketTimeoutException e) {
                e = e;
            } catch (IOException e2) {
                e = e2;
            }
            try {
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        bufferedOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e3) {
                            LOGGER.warn("got exception", e3);
                        }
                    }
                }
                bufferedOutputStream.close();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        LOGGER.warn("got exception", e4);
                    }
                }
            } catch (SocketTimeoutException e5) {
                e = e5;
                String str = "SocketTimeoutException: Unable to retrieve contents over network: " + e.getMessage();
                LOGGER.error(str);
                throw new AmazonClientException(str, e);
            } catch (IOException e6) {
                e = e6;
                throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream2 = bufferedOutputStream;
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e7) {
                        LOGGER.warn("got exception", e7);
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                        throw th;
                    } catch (IOException e8) {
                        LOGGER.warn("got exception", e8);
                        throw th;
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
