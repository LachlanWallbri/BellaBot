package com.alibaba.sdk.android.oss.internal;

import android.text.TextUtils;
import android.util.Xml;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.utils.CRC64;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteMultipleObjectResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetBucketInfoResult;
import com.alibaba.sdk.android.oss.model.GetBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.GetBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.GetBucketRefererResult;
import com.alibaba.sdk.android.oss.model.GetObjectACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.GetSymlinkResult;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.ImagePersistResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListBucketsResult;
import com.alibaba.sdk.android.oss.model.ListMultipartUploadsResult;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.Owner;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.PutBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.PutBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.PutBucketRefererResult;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.PutSymlinkResult;
import com.alibaba.sdk.android.oss.model.RestoreObjectResult;
import com.alibaba.sdk.android.oss.model.TriggerCallbackResult;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.amazonaws.services.p048s3.util.Mimetypes;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class ResponseParsers {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class AbortMultipartUploadResponseParser extends AbstractResponseParser<AbortMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public AbortMultipartUploadResult parseData(ResponseMessage responseMessage, AbortMultipartUploadResult abortMultipartUploadResult) throws IOException {
            return abortMultipartUploadResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class DeleteBucketLifecycleResponseParser extends AbstractResponseParser<DeleteBucketLifecycleResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteBucketLifecycleResult parseData(ResponseMessage responseMessage, DeleteBucketLifecycleResult deleteBucketLifecycleResult) throws Exception {
            return deleteBucketLifecycleResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class DeleteBucketLoggingResponseParser extends AbstractResponseParser<DeleteBucketLoggingResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteBucketLoggingResult parseData(ResponseMessage responseMessage, DeleteBucketLoggingResult deleteBucketLoggingResult) throws Exception {
            return deleteBucketLoggingResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class DeleteBucketResponseParser extends AbstractResponseParser<DeleteBucketResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteBucketResult parseData(ResponseMessage responseMessage, DeleteBucketResult deleteBucketResult) throws IOException {
            return deleteBucketResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class DeleteObjectResponseParser extends AbstractResponseParser<DeleteObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteObjectResult parseData(ResponseMessage responseMessage, DeleteObjectResult deleteObjectResult) throws IOException {
            return deleteObjectResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class ImagePersistResponseParser extends AbstractResponseParser<ImagePersistResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ImagePersistResult parseData(ResponseMessage responseMessage, ImagePersistResult imagePersistResult) throws Exception {
            return imagePersistResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class PutBucketLifecycleResponseParser extends AbstractResponseParser<PutBucketLifecycleResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutBucketLifecycleResult parseData(ResponseMessage responseMessage, PutBucketLifecycleResult putBucketLifecycleResult) throws Exception {
            return putBucketLifecycleResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class PutBucketLoggingResponseParser extends AbstractResponseParser<PutBucketLoggingResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutBucketLoggingResult parseData(ResponseMessage responseMessage, PutBucketLoggingResult putBucketLoggingResult) throws Exception {
            return putBucketLoggingResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class PutBucketRefererResponseParser extends AbstractResponseParser<PutBucketRefererResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutBucketRefererResult parseData(ResponseMessage responseMessage, PutBucketRefererResult putBucketRefererResult) throws Exception {
            return putBucketRefererResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class PutSymlinkResponseParser extends AbstractResponseParser<PutSymlinkResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutSymlinkResult parseData(ResponseMessage responseMessage, PutSymlinkResult putSymlinkResult) throws Exception {
            return putSymlinkResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class RestoreObjectResponseParser extends AbstractResponseParser<RestoreObjectResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public RestoreObjectResult parseData(ResponseMessage responseMessage, RestoreObjectResult restoreObjectResult) throws Exception {
            return restoreObjectResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class PutObjectResponseParser extends AbstractResponseParser<PutObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutObjectResult parseData(ResponseMessage responseMessage, PutObjectResult putObjectResult) throws IOException {
            putObjectResult.setETag(ResponseParsers.trimQuotes((String) responseMessage.getHeaders().get("ETag")));
            if (responseMessage.getContentLength() > 0) {
                putObjectResult.setServerCallbackReturnBody(responseMessage.getResponse().body().string());
            }
            return putObjectResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class AppendObjectResponseParser extends AbstractResponseParser<AppendObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public AppendObjectResult parseData(ResponseMessage responseMessage, AppendObjectResult appendObjectResult) throws IOException {
            String str = (String) responseMessage.getHeaders().get(OSSHeaders.OSS_NEXT_APPEND_POSITION);
            if (str != null) {
                appendObjectResult.setNextPosition(Long.valueOf(str));
            }
            appendObjectResult.setObjectCRC64((String) responseMessage.getHeaders().get(OSSHeaders.OSS_HASH_CRC64_ECMA));
            return appendObjectResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class HeadObjectResponseParser extends AbstractResponseParser<HeadObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public HeadObjectResult parseData(ResponseMessage responseMessage, HeadObjectResult headObjectResult) throws IOException {
            headObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(headObjectResult.getResponseHeader()));
            return headObjectResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetObjectResponseParser extends AbstractResponseParser<GetObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public boolean needCloseResponse() {
            return false;
        }

        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetObjectResult parseData(ResponseMessage responseMessage, GetObjectResult getObjectResult) throws IOException {
            getObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(getObjectResult.getResponseHeader()));
            getObjectResult.setContentLength(responseMessage.getContentLength());
            if (responseMessage.getRequest().isCheckCRC64()) {
                getObjectResult.setObjectContent(new CheckCRC64DownloadInputStream(responseMessage.getContent(), new CRC64(), responseMessage.getContentLength(), getObjectResult.getServerCRC().longValue(), getObjectResult.getRequestId()));
            } else {
                getObjectResult.setObjectContent(responseMessage.getContent());
            }
            return getObjectResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class CopyObjectResponseParser extends AbstractResponseParser<CopyObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CopyObjectResult parseData(ResponseMessage responseMessage, CopyObjectResult copyObjectResult) throws Exception {
            return ResponseParsers.parseCopyObjectResponseXML(responseMessage.getContent(), copyObjectResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class CreateBucketResponseParser extends AbstractResponseParser<CreateBucketResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CreateBucketResult parseData(ResponseMessage responseMessage, CreateBucketResult createBucketResult) throws IOException {
            if (createBucketResult.getResponseHeader().containsKey("Location")) {
                createBucketResult.bucketLocation = createBucketResult.getResponseHeader().get("Location");
            }
            return createBucketResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetBucketACLResponseParser extends AbstractResponseParser<GetBucketACLResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketACLResult parseData(ResponseMessage responseMessage, GetBucketACLResult getBucketACLResult) throws Exception {
            return ResponseParsers.parseGetBucketACLResponse(responseMessage.getContent(), getBucketACLResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class ListObjectsResponseParser extends AbstractResponseParser<ListObjectsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListObjectsResult parseData(ResponseMessage responseMessage, ListObjectsResult listObjectsResult) throws Exception {
            return ResponseParsers.parseObjectListResponse(responseMessage.getContent(), listObjectsResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class InitMultipartResponseParser extends AbstractResponseParser<InitiateMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public InitiateMultipartUploadResult parseData(ResponseMessage responseMessage, InitiateMultipartUploadResult initiateMultipartUploadResult) throws Exception {
            return ResponseParsers.parseInitMultipartResponseXML(responseMessage.getContent(), initiateMultipartUploadResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class UploadPartResponseParser extends AbstractResponseParser<UploadPartResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public UploadPartResult parseData(ResponseMessage responseMessage, UploadPartResult uploadPartResult) throws IOException {
            uploadPartResult.setETag(ResponseParsers.trimQuotes((String) responseMessage.getHeaders().get("ETag")));
            return uploadPartResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class CompleteMultipartUploadResponseParser extends AbstractResponseParser<CompleteMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CompleteMultipartUploadResult parseData(ResponseMessage responseMessage, CompleteMultipartUploadResult completeMultipartUploadResult) throws Exception {
            if (((String) responseMessage.getHeaders().get("Content-Type")).equals(Mimetypes.MIMETYPE_XML)) {
                return ResponseParsers.parseCompleteMultipartUploadResponseXML(responseMessage.getContent(), completeMultipartUploadResult);
            }
            if (responseMessage.getResponse().body() == null) {
                return completeMultipartUploadResult;
            }
            completeMultipartUploadResult.setServerCallbackReturnBody(responseMessage.getResponse().body().string());
            return completeMultipartUploadResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class ListPartsResponseParser extends AbstractResponseParser<ListPartsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListPartsResult parseData(ResponseMessage responseMessage, ListPartsResult listPartsResult) throws Exception {
            return ResponseParsers.parseListPartsResponseXML(responseMessage.getContent(), listPartsResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CopyObjectResult parseCopyObjectResponseXML(InputStream inputStream, CopyObjectResult copyObjectResult) throws XmlPullParserException, IOException, ParseException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("LastModified".equals(name)) {
                    copyObjectResult.setLastModified(DateUtil.parseIso8601Date(newPullParser.nextText()));
                } else if ("ETag".equals(name)) {
                    copyObjectResult.setEtag(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return copyObjectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListPartsResult parseListPartsResponseXML(InputStream inputStream, ListPartsResult listPartsResult) throws IOException, XmlPullParserException, ParseException {
        ArrayList arrayList = new ArrayList();
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        PartSummary partSummary = null;
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Bucket".equals(name)) {
                    listPartsResult.setBucketName(newPullParser.nextText());
                } else if ("Key".equals(name)) {
                    listPartsResult.setKey(newPullParser.nextText());
                } else if ("UploadId".equals(name)) {
                    listPartsResult.setUploadId(newPullParser.nextText());
                } else if ("PartNumberMarker".equals(name)) {
                    String nextText = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText)) {
                        listPartsResult.setPartNumberMarker(Integer.valueOf(nextText).intValue());
                    }
                } else if ("NextPartNumberMarker".equals(name)) {
                    String nextText2 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText2)) {
                        listPartsResult.setNextPartNumberMarker(Integer.valueOf(nextText2).intValue());
                    }
                } else if ("MaxParts".equals(name)) {
                    String nextText3 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText3)) {
                        listPartsResult.setMaxParts(Integer.valueOf(nextText3).intValue());
                    }
                } else if ("IsTruncated".equals(name)) {
                    String nextText4 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText4)) {
                        listPartsResult.setTruncated(Boolean.valueOf(nextText4).booleanValue());
                    }
                } else if ("StorageClass".equals(name)) {
                    listPartsResult.setStorageClass(newPullParser.nextText());
                } else if ("Part".equals(name)) {
                    partSummary = new PartSummary();
                } else if ("PartNumber".equals(name)) {
                    String nextText5 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText5)) {
                        partSummary.setPartNumber(Integer.valueOf(nextText5).intValue());
                    }
                } else if ("LastModified".equals(name)) {
                    partSummary.setLastModified(DateUtil.parseIso8601Date(newPullParser.nextText()));
                } else if ("ETag".equals(name)) {
                    partSummary.setETag(newPullParser.nextText());
                } else if ("Size".equals(name)) {
                    String nextText6 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText6)) {
                        partSummary.setSize(Long.valueOf(nextText6).longValue());
                    }
                }
            } else if (eventType == 3 && "Part".equals(newPullParser.getName())) {
                arrayList.add(partSummary);
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        if (arrayList.size() > 0) {
            listPartsResult.setParts(arrayList);
        }
        return listPartsResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CompleteMultipartUploadResult parseCompleteMultipartUploadResponseXML(InputStream inputStream, CompleteMultipartUploadResult completeMultipartUploadResult) throws IOException, XmlPullParserException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Location".equals(name)) {
                    completeMultipartUploadResult.setLocation(newPullParser.nextText());
                } else if ("Bucket".equals(name)) {
                    completeMultipartUploadResult.setBucketName(newPullParser.nextText());
                } else if ("Key".equals(name)) {
                    completeMultipartUploadResult.setObjectKey(newPullParser.nextText());
                } else if ("ETag".equals(name)) {
                    completeMultipartUploadResult.setETag(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return completeMultipartUploadResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InitiateMultipartUploadResult parseInitMultipartResponseXML(InputStream inputStream, InitiateMultipartUploadResult initiateMultipartUploadResult) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Bucket".equals(name)) {
                    initiateMultipartUploadResult.setBucketName(newPullParser.nextText());
                } else if ("Key".equals(name)) {
                    initiateMultipartUploadResult.setObjectKey(newPullParser.nextText());
                } else if ("UploadId".equals(name)) {
                    initiateMultipartUploadResult.setUploadId(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return initiateMultipartUploadResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketACLResult parseGetBucketACLResponse(InputStream inputStream, GetBucketACLResult getBucketACLResult) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Grant".equals(name)) {
                    getBucketACLResult.setBucketACL(newPullParser.nextText());
                } else if ("ID".equals(name)) {
                    getBucketACLResult.setBucketOwnerID(newPullParser.nextText());
                } else if ("DisplayName".equals(name)) {
                    getBucketACLResult.setBucketOwner(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return getBucketACLResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListObjectsResult parseObjectListResponse(InputStream inputStream, ListObjectsResult listObjectsResult) throws XmlPullParserException, IOException, ParseException {
        listObjectsResult.clearCommonPrefixes();
        listObjectsResult.clearObjectSummaries();
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        Owner owner = null;
        boolean z = false;
        OSSObjectSummary oSSObjectSummary = null;
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Name".equals(name)) {
                    listObjectsResult.setBucketName(newPullParser.nextText());
                } else if ("Prefix".equals(name)) {
                    if (z) {
                        String nextText = newPullParser.nextText();
                        if (!OSSUtils.isEmptyString(nextText)) {
                            listObjectsResult.addCommonPrefix(nextText);
                        }
                    } else {
                        listObjectsResult.setPrefix(newPullParser.nextText());
                    }
                } else if ("Marker".equals(name)) {
                    listObjectsResult.setMarker(newPullParser.nextText());
                } else if ("Delimiter".equals(name)) {
                    listObjectsResult.setDelimiter(newPullParser.nextText());
                } else if ("EncodingType".equals(name)) {
                    listObjectsResult.setEncodingType(newPullParser.nextText());
                } else if ("MaxKeys".equals(name)) {
                    String nextText2 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText2)) {
                        listObjectsResult.setMaxKeys(Integer.valueOf(nextText2).intValue());
                    }
                } else if ("NextMarker".equals(name)) {
                    listObjectsResult.setNextMarker(newPullParser.nextText());
                } else if ("IsTruncated".equals(name)) {
                    String nextText3 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText3)) {
                        listObjectsResult.setTruncated(Boolean.valueOf(nextText3).booleanValue());
                    }
                } else if ("Contents".equals(name)) {
                    oSSObjectSummary = new OSSObjectSummary();
                } else if ("Key".equals(name)) {
                    oSSObjectSummary.setKey(newPullParser.nextText());
                } else if ("LastModified".equals(name)) {
                    oSSObjectSummary.setLastModified(DateUtil.parseIso8601Date(newPullParser.nextText()));
                } else if ("Size".equals(name)) {
                    String nextText4 = newPullParser.nextText();
                    if (!OSSUtils.isEmptyString(nextText4)) {
                        oSSObjectSummary.setSize(Long.valueOf(nextText4).longValue());
                    }
                } else if ("ETag".equals(name)) {
                    oSSObjectSummary.setETag(newPullParser.nextText());
                } else if ("Type".equals(name)) {
                    oSSObjectSummary.setType(newPullParser.nextText());
                } else if ("StorageClass".equals(name)) {
                    oSSObjectSummary.setStorageClass(newPullParser.nextText());
                } else if ("Owner".equals(name)) {
                    owner = new Owner();
                } else if ("ID".equals(name)) {
                    owner.setId(newPullParser.nextText());
                } else if ("DisplayName".equals(name)) {
                    owner.setDisplayName(newPullParser.nextText());
                } else if ("CommonPrefixes".equals(name)) {
                    z = true;
                }
            } else if (eventType == 3) {
                String name2 = newPullParser.getName();
                if ("Owner".equals(newPullParser.getName())) {
                    if (owner != null) {
                        oSSObjectSummary.setOwner(owner);
                    }
                } else if ("Contents".equals(name2)) {
                    if (oSSObjectSummary != null) {
                        oSSObjectSummary.setBucketName(listObjectsResult.getBucketName());
                        listObjectsResult.addObjectSummary(oSSObjectSummary);
                    }
                } else if ("CommonPrefixes".equals(name2)) {
                    z = false;
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return listObjectsResult;
    }

    public static String trimQuotes(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("\"")) {
            trim = trim.substring(1);
        }
        return trim.endsWith("\"") ? trim.substring(0, trim.length() - 1) : trim;
    }

    public static ObjectMetadata parseObjectMetadata(Map<String, String> map) throws IOException {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            for (String str : map.keySet()) {
                if (str.indexOf(OSSHeaders.OSS_USER_METADATA_PREFIX) >= 0) {
                    objectMetadata.addUserMetadata(str, map.get(str));
                } else {
                    if (!str.equals("Last-Modified") && !str.equals("Date")) {
                        if (str.equals("Content-Length")) {
                            objectMetadata.setHeader(str, Long.valueOf(map.get(str)));
                        } else if (str.equals("ETag")) {
                            objectMetadata.setHeader(str, trimQuotes(map.get(str)));
                        } else {
                            objectMetadata.setHeader(str, map.get(str));
                        }
                    }
                    try {
                        objectMetadata.setHeader(str, DateUtil.parseRfc822Date(map.get(str)));
                    } catch (ParseException e) {
                        throw new IOException(e.getMessage(), e);
                    }
                }
            }
            return objectMetadata;
        } catch (Exception e2) {
            throw new IOException(e2.getMessage(), e2);
        }
    }

    public static ServiceException parseResponseErrorXML(ResponseMessage responseMessage, boolean z) throws ClientException {
        String str;
        String str2;
        String str3;
        String str4;
        int statusCode = responseMessage.getStatusCode();
        String header = responseMessage.getResponse().header(OSSHeaders.OSS_HEADER_REQUEST_ID);
        String str5 = null;
        if (z) {
            str4 = header;
            str3 = null;
            str = null;
            str2 = null;
        } else {
            try {
                String string = responseMessage.getResponse().body().string();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string.getBytes());
                XmlPullParser newPullParser = Xml.newPullParser();
                newPullParser.setInput(byteArrayInputStream, "utf-8");
                int eventType = newPullParser.getEventType();
                String str6 = null;
                str = null;
                while (eventType != 1) {
                    if (eventType == 2) {
                        if (AttributeLayout.ATTRIBUTE_CODE.equals(newPullParser.getName())) {
                            str5 = newPullParser.nextText();
                        } else if ("Message".equals(newPullParser.getName())) {
                            str6 = newPullParser.nextText();
                        } else if ("RequestId".equals(newPullParser.getName())) {
                            header = newPullParser.nextText();
                        } else if ("HostId".equals(newPullParser.getName())) {
                            str = newPullParser.nextText();
                        }
                    }
                    eventType = newPullParser.next();
                    if (eventType == 4) {
                        eventType = newPullParser.next();
                    }
                }
                str2 = string;
                str3 = str5;
                str5 = str6;
                str4 = header;
            } catch (IOException e) {
                throw new ClientException(e);
            } catch (XmlPullParserException e2) {
                throw new ClientException(e2);
            }
        }
        return new ServiceException(statusCode, str5, str3, str4, str, str2);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetObjectACLResponseParser extends AbstractResponseParser<GetObjectACLResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetObjectACLResult parseData(ResponseMessage responseMessage, GetObjectACLResult getObjectACLResult) throws Exception {
            return ResponseParsers.access$000(responseMessage.getContent(), getObjectACLResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetBucketInfoResponseParser extends AbstractResponseParser<GetBucketInfoResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketInfoResult parseData(ResponseMessage responseMessage, GetBucketInfoResult getBucketInfoResult) throws Exception {
            return ResponseParsers.access$200(responseMessage.getContent(), getBucketInfoResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetBucketRefererResponseParser extends AbstractResponseParser<GetBucketRefererResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketRefererResult parseData(ResponseMessage responseMessage, GetBucketRefererResult getBucketRefererResult) throws Exception {
            return ResponseParsers.access$400(responseMessage.getContent(), getBucketRefererResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetBucketLoggingResponseParser extends AbstractResponseParser<GetBucketLoggingResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketLoggingResult parseData(ResponseMessage responseMessage, GetBucketLoggingResult getBucketLoggingResult) throws Exception {
            return ResponseParsers.access$500(responseMessage.getContent(), getBucketLoggingResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetBucketLifecycleResponseParser extends AbstractResponseParser<GetBucketLifecycleResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketLifecycleResult parseData(ResponseMessage responseMessage, GetBucketLifecycleResult getBucketLifecycleResult) throws Exception {
            return ResponseParsers.access$600(responseMessage.getContent(), getBucketLifecycleResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class DeleteMultipleObjectResponseParser extends AbstractResponseParser<DeleteMultipleObjectResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteMultipleObjectResult parseData(ResponseMessage responseMessage, DeleteMultipleObjectResult deleteMultipleObjectResult) throws Exception {
            return ResponseParsers.access$700(responseMessage.getContent(), deleteMultipleObjectResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class ListBucketResponseParser extends AbstractResponseParser<ListBucketsResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListBucketsResult parseData(ResponseMessage responseMessage, ListBucketsResult listBucketsResult) throws Exception {
            return ResponseParsers.access$900(responseMessage.getContent(), listBucketsResult);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class ListMultipartUploadsResponseParser extends AbstractResponseParser<ListMultipartUploadsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListMultipartUploadsResult parseData(ResponseMessage responseMessage, ListMultipartUploadsResult listMultipartUploadsResult) throws Exception {
            return listMultipartUploadsResult.parseData(responseMessage);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class TriggerCallbackResponseParser extends AbstractResponseParser<TriggerCallbackResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public TriggerCallbackResult parseData(ResponseMessage responseMessage, TriggerCallbackResult triggerCallbackResult) throws Exception {
            String string = responseMessage.getResponse().body().string();
            if (!TextUtils.isEmpty(string)) {
                triggerCallbackResult.setServerCallbackReturnBody(string);
            }
            return triggerCallbackResult;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class GetSymlinkResponseParser extends AbstractResponseParser<GetSymlinkResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetSymlinkResult parseData(ResponseMessage responseMessage, GetSymlinkResult getSymlinkResult) throws Exception {
            getSymlinkResult.setTargetObjectName((String) responseMessage.getHeaders().get("x-oss-symlink-target"));
            return getSymlinkResult;
        }
    }
}
