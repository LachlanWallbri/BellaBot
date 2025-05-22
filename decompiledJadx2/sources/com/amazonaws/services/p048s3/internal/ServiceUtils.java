package com.amazonaws.services.p048s3.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.Request;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.p048s3.S3ClientOptions;
import com.amazonaws.services.p048s3.model.GetObjectRequest;
import com.amazonaws.services.p048s3.model.ObjectMetadata;
import com.amazonaws.services.p048s3.model.PutObjectRequest;
import com.amazonaws.services.p048s3.model.S3Object;
import com.amazonaws.services.p048s3.model.SSEAlgorithm;
import com.amazonaws.services.p048s3.model.UploadPartRequest;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.Md5Utils;
import com.amazonaws.util.StringUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.net.ssl.SSLProtocolException;

/* loaded from: classes.dex */
public class ServiceUtils {
    public static final boolean APPEND_MODE = true;
    private static final int DEAFAULT_BYTE_SIZE = 10240;
    public static final boolean OVERWRITE_MODE = false;
    private static final Log log = LogFactory.getLog((Class<?>) ServiceUtils.class);

    @Deprecated
    protected static final DateUtils DATE_UTILS = new DateUtils();

    /* loaded from: classes.dex */
    public interface RetryableS3DownloadTask {
        S3Object getS3ObjectStream();

        boolean needIntegrityCheck();
    }

    public static Date parseIso8601Date(String str) {
        return DateUtils.parseISO8601Date(str);
    }

    public static String formatIso8601Date(Date date) {
        return DateUtils.formatISO8601Date(date);
    }

    public static Date parseRfc822Date(String str) {
        return DateUtils.parseRFC822Date(str);
    }

    public static String formatRfc822Date(Date date) {
        return DateUtils.formatRFC822Date(date);
    }

    public static boolean isMultipartUploadETag(String str) {
        return str.contains("-");
    }

    public static byte[] toByteArray(String str) {
        return str.getBytes(StringUtils.UTF8);
    }

    public static String removeQuotes(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("\"")) {
            trim = trim.substring(1);
        }
        return trim.endsWith("\"") ? trim.substring(0, trim.length() - 1) : trim;
    }

    public static URL convertRequestToUrl(Request<?> request) {
        return convertRequestToUrl(request, false);
    }

    public static URL convertRequestToUrl(Request<?> request, boolean z) {
        String str;
        boolean z2 = true;
        String urlEncode = S3HttpUtils.urlEncode(request.getResourcePath(), true);
        if (z && urlEncode.startsWith("/")) {
            urlEncode = urlEncode.substring(1);
        }
        String str2 = request.getEndpoint() + ("/" + urlEncode).replaceAll("(?<=/)/", "%2F");
        for (String str3 : request.getParameters().keySet()) {
            if (z2) {
                str = str2 + "?";
                z2 = false;
            } else {
                str = str2 + "&";
            }
            str2 = str + str3 + "=" + S3HttpUtils.urlEncode(request.getParameters().get(str3), false);
        }
        try {
            return new URL(str2);
        } catch (MalformedURLException e) {
            throw new AmazonClientException("Unable to convert request to well formed URL: " + e.getMessage(), e);
        }
    }

    public static String join(List<String> list) {
        String str = "";
        boolean z = true;
        for (String str2 : list) {
            if (!z) {
                str = str + ", ";
            }
            str = str + str2;
            z = false;
        }
        return str;
    }

    public static void downloadObjectToFile(S3Object s3Object, File file, boolean z, boolean z2) {
        byte[] bArr;
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        BufferedOutputStream bufferedOutputStream = null;
        r1 = null;
        byte[] bArr2 = null;
        bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file, z2));
                try {
                    byte[] bArr3 = new byte[10240];
                    while (true) {
                        int read = s3Object.getObjectContent().read(bArr3);
                        if (read > -1) {
                            bufferedOutputStream2.write(bArr3, 0, read);
                        } else {
                            try {
                                break;
                            } catch (Exception unused) {
                                log.debug("Caught exception. Ignoring.");
                            }
                        }
                    }
                    bufferedOutputStream2.close();
                    try {
                        s3Object.getObjectContent().close();
                    } catch (Exception unused2) {
                        log.debug("Caught exception. Ignoring.");
                    }
                    try {
                        if (isMultipartUploadETag(s3Object.getObjectMetadata().getETag())) {
                            bArr = null;
                        } else {
                            bArr = Md5Utils.computeMD5Hash(new FileInputStream(file));
                            try {
                                bArr2 = BinaryUtils.fromHex(s3Object.getObjectMetadata().getETag());
                            } catch (Exception e) {
                                e = e;
                                log.warn("Unable to calculate MD5 hash to validate download: " + e.getMessage(), e);
                                if (z) {
                                    return;
                                } else {
                                    return;
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        bArr = null;
                    }
                    if (z || bArr == null || bArr2 == null || Arrays.equals(bArr, bArr2)) {
                        return;
                    }
                    throw new AmazonClientException("Unable to verify integrity of data download.  Client calculated content hash didn't match hash calculated by Amazon S3.  The data stored in '" + file.getAbsolutePath() + "' may be corrupt.");
                } catch (IOException e3) {
                    e = e3;
                    bufferedOutputStream = bufferedOutputStream2;
                    s3Object.getObjectContent().abort();
                    throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception unused3) {
                        log.debug("Caught exception. Ignoring.");
                    }
                    try {
                        s3Object.getObjectContent().close();
                        throw th;
                    } catch (Exception unused4) {
                        log.debug("Caught exception. Ignoring.");
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    public static S3Object retryableDownloadS3ObjectToFile(File file, RetryableS3DownloadTask retryableS3DownloadTask, boolean z) {
        S3Object s3ObjectStream;
        boolean z2;
        boolean z3 = false;
        do {
            s3ObjectStream = retryableS3DownloadTask.getS3ObjectStream();
            if (s3ObjectStream == null) {
                return null;
            }
            z2 = true;
            try {
                try {
                    downloadObjectToFile(s3ObjectStream, file, retryableS3DownloadTask.needIntegrityCheck(), z);
                    s3ObjectStream.getObjectContent().abort();
                    z2 = false;
                } catch (AmazonClientException e) {
                    if (!e.isRetryable()) {
                        throw e;
                    }
                    if ((e.getCause() instanceof SocketException) || (e.getCause() instanceof SSLProtocolException)) {
                        throw e;
                    }
                    if (z3) {
                        throw e;
                    }
                    log.info("Retry the download of object " + s3ObjectStream.getKey() + " (bucket " + s3ObjectStream.getBucketName() + ")", e);
                    s3ObjectStream.getObjectContent().abort();
                    z3 = true;
                }
            } catch (Throwable th) {
                s3ObjectStream.getObjectContent().abort();
                throw th;
            }
        } while (z2);
        return s3ObjectStream;
    }

    public static boolean skipMd5CheckPerResponse(ObjectMetadata objectMetadata) {
        return skipMd5CheckPerResponse(objectMetadata, null);
    }

    public static boolean skipMd5CheckPerResponse(ObjectMetadata objectMetadata, S3ClientOptions s3ClientOptions) {
        if (s3ClientOptions != null && s3ClientOptions.isContentMd5CheckSkipped()) {
            return true;
        }
        if (objectMetadata == null) {
            return false;
        }
        return objectMetadata.getSSECustomerAlgorithm() != null || SSEAlgorithm.KMS.toString().equals(objectMetadata.getSSEAlgorithm());
    }

    public static boolean skipMd5CheckPerRequest(AmazonWebServiceRequest amazonWebServiceRequest) {
        return skipMd5CheckPerRequest(amazonWebServiceRequest, null);
    }

    public static boolean skipMd5CheckPerRequest(AmazonWebServiceRequest amazonWebServiceRequest, S3ClientOptions s3ClientOptions) {
        if ((s3ClientOptions != null && s3ClientOptions.isContentMd5CheckSkipped()) || System.getProperty("com.amazonaws.services.s3.disableGetObjectMD5Validation") != null) {
            return true;
        }
        if (amazonWebServiceRequest instanceof GetObjectRequest) {
            GetObjectRequest getObjectRequest = (GetObjectRequest) amazonWebServiceRequest;
            if (getObjectRequest.getRange() != null || getObjectRequest.getSSECustomerKey() != null) {
                return true;
            }
        } else {
            if (!(amazonWebServiceRequest instanceof PutObjectRequest)) {
                return (amazonWebServiceRequest instanceof UploadPartRequest) && ((UploadPartRequest) amazonWebServiceRequest).getSSECustomerKey() != null;
            }
            PutObjectRequest putObjectRequest = (PutObjectRequest) amazonWebServiceRequest;
            ObjectMetadata metadata = putObjectRequest.getMetadata();
            if ((metadata != null && metadata.getSSEAlgorithm() != null) || putObjectRequest.getSSECustomerKey() != null) {
                return true;
            }
            if (putObjectRequest.getSSEAwsKeyManagementParams() != null && (putObjectRequest.getSSEAwsKeyManagementParams().getEncryption() != null || putObjectRequest.getSSEAwsKeyManagementParams().getAwsKmsKeyId() != null)) {
                return true;
            }
        }
        return false;
    }
}
