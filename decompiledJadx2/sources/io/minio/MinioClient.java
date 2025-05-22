package io.minio;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferService;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.internal.Constants;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.ByteStreams;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import io.minio.S3Base;
import io.minio.credentials.Credentials;
import io.minio.credentials.Provider;
import io.minio.credentials.StaticProvider;
import io.minio.errors.BucketPolicyTooLargeException;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.HttpUtils;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.CopyObjectResult;
import io.minio.messages.CreateBucketConfiguration;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import io.minio.messages.LegalHold;
import io.minio.messages.LifecycleConfiguration;
import io.minio.messages.ListAllMyBucketsResult;
import io.minio.messages.NotificationConfiguration;
import io.minio.messages.NotificationRecords;
import io.minio.messages.ObjectLockConfiguration;
import io.minio.messages.Part;
import io.minio.messages.ReplicationConfiguration;
import io.minio.messages.Retention;
import io.minio.messages.SelectObjectContentRequest;
import io.minio.messages.SseConfiguration;
import io.minio.messages.Tags;
import io.minio.messages.VersioningConfiguration;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.http.HttpHost;
import org.xerial.snappy.SnappyFramedOutputStream;

/* loaded from: classes7.dex */
public class MinioClient extends S3Base {
    private MinioClient(HttpUrl httpUrl, String str, boolean z, boolean z2, boolean z3, boolean z4, Provider provider, OkHttpClient okHttpClient) {
        super(httpUrl, str, z, z2, z3, z4, provider, okHttpClient);
    }

    protected MinioClient(MinioClient minioClient) {
        super(minioClient);
    }

    @Override // io.minio.S3Base
    public StatObjectResponse statObject(StatObjectArgs statObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        return super.statObject(statObjectArgs);
    }

    public GetObjectResponse getObject(GetObjectArgs getObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getObjectArgs);
        getObjectArgs.validateSsec(this.baseUrl);
        Response executeGet = executeGet(getObjectArgs, getObjectArgs.getHeaders(), getObjectArgs.versionId() != null ? newMultimap("versionId", getObjectArgs.versionId()) : null);
        return new GetObjectResponse(executeGet.headers(), getObjectArgs.bucket(), getObjectArgs.region(), getObjectArgs.object(), executeGet.body().byteStream());
    }

    public void downloadObject(DownloadObjectArgs downloadObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        GetObjectResponse getObjectResponse;
        String filename = downloadObjectArgs.filename();
        Path path = Paths.get(filename, new String[0]);
        if (Files.exists(path, new LinkOption[0])) {
            throw new IllegalArgumentException("Destination file " + filename + " already exists");
        }
        StatObjectResponse statObject = statObject(new StatObjectArgs(downloadObjectArgs));
        String str = filename + "." + S3Escaper.encode(statObject.etag()) + ".part.minio";
        Path path2 = Paths.get(str, new String[0]);
        if (Files.exists(path2, new LinkOption[0])) {
            Files.delete(path2);
        }
        OutputStream outputStream = null;
        try {
            getObjectResponse = getObject(new GetObjectArgs(downloadObjectArgs));
        } catch (Throwable th) {
            th = th;
            getObjectResponse = null;
        }
        try {
            OutputStream newOutputStream = Files.newOutputStream(path2, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            long copy = ByteStreams.copy(getObjectResponse, newOutputStream);
            getObjectResponse.close();
            newOutputStream.close();
            if (copy == statObject.size()) {
                Files.move(path2, path, StandardCopyOption.REPLACE_EXISTING);
                if (getObjectResponse != null) {
                    getObjectResponse.close();
                }
                if (newOutputStream != null) {
                    newOutputStream.close();
                    return;
                }
                return;
            }
            throw new IOException(str + ": unexpected data written.  expected = " + statObject.size() + ", written = " + copy);
        } catch (Throwable th2) {
            th = th2;
            if (getObjectResponse != null) {
                getObjectResponse.close();
            }
            if (0 != 0) {
                outputStream.close();
            }
            throw th;
        }
    }

    public ObjectWriteResponse copyObject(CopyObjectArgs copyObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(copyObjectArgs);
        copyObjectArgs.validateSse(this.baseUrl);
        long size = (copyObjectArgs.source().offset() == null || copyObjectArgs.source().length() == null) ? -1L : statObject(new StatObjectArgs(copyObjectArgs.source())).size();
        if (copyObjectArgs.source().offset() != null || copyObjectArgs.source().length() != null || size > 5368709120L) {
            if (copyObjectArgs.metadataDirective() != null && copyObjectArgs.metadataDirective() == Directive.COPY) {
                throw new IllegalArgumentException("COPY metadata directive is not applicable to source object size greater than 5 GiB");
            }
            if (copyObjectArgs.taggingDirective() != null && copyObjectArgs.taggingDirective() == Directive.COPY) {
                throw new IllegalArgumentException("COPY tagging directive is not applicable to source object size greater than 5 GiB");
            }
            return composeObject(new ComposeObjectArgs(copyObjectArgs));
        }
        Multimap<String, String> genHeaders = copyObjectArgs.genHeaders();
        if (copyObjectArgs.metadataDirective() != null) {
            genHeaders.put(Headers.METADATA_DIRECTIVE, copyObjectArgs.metadataDirective().name());
        }
        if (copyObjectArgs.taggingDirective() != null) {
            genHeaders.put(Headers.TAGGING_DIRECTIVE, copyObjectArgs.taggingDirective().name());
        }
        genHeaders.putAll(copyObjectArgs.source().genCopyHeaders());
        Response executePut = executePut(copyObjectArgs, genHeaders, null, null, 0);
        try {
            ObjectWriteResponse objectWriteResponse = new ObjectWriteResponse(executePut.headers(), copyObjectArgs.bucket(), copyObjectArgs.region(), copyObjectArgs.object(), ((CopyObjectResult) Xml.unmarshal(CopyObjectResult.class, executePut.body().charStream())).etag(), executePut.header(Headers.S3_VERSION_ID));
            if (executePut != null) {
                executePut.close();
            }
            return objectWriteResponse;
        } catch (Throwable th) {
            if (executePut != null) {
                try {
                    executePut.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public ObjectWriteResponse composeObject(ComposeObjectArgs composeObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(composeObjectArgs);
        composeObjectArgs.validateSse(this.baseUrl);
        List<ComposeSource> sources = composeObjectArgs.sources();
        int calculatePartCount = calculatePartCount(sources);
        int i = 0;
        if (calculatePartCount == 1 && composeObjectArgs.sources().get(0).offset() == null && composeObjectArgs.sources().get(0).length() == null) {
            return copyObject(new CopyObjectArgs(composeObjectArgs));
        }
        Multimap<String, String> newMultimap = newMultimap(composeObjectArgs.extraHeaders());
        newMultimap.putAll(composeObjectArgs.genHeaders());
        String uploadId = createMultipartUpload(composeObjectArgs.bucket(), composeObjectArgs.region(), composeObjectArgs.object(), newMultimap, composeObjectArgs.extraQueryParams()).result().uploadId();
        HashMultimap create = HashMultimap.create();
        if (composeObjectArgs.sse() != null && (composeObjectArgs.sse() instanceof ServerSideEncryptionCustomerKey)) {
            create.putAll(newMultimap(composeObjectArgs.sse().headers()));
        }
        try {
            Part[] partArr = new Part[calculatePartCount];
            Iterator<ComposeSource> it = sources.iterator();
            while (it.hasNext()) {
                ComposeSource next = it.next();
                long objectSize = next.objectSize();
                if (next.length() != null) {
                    objectSize = next.length().longValue();
                } else if (next.offset() != null) {
                    objectSize -= next.offset().longValue();
                }
                long longValue = next.offset() != null ? next.offset().longValue() : 0L;
                Multimap<String, String> newMultimap2 = newMultimap(next.headers());
                newMultimap2.putAll(create);
                Iterator<ComposeSource> it2 = it;
                String str = Headers.COPY_PART_RANGE;
                if (objectSize <= 5368709120L) {
                    int i2 = i + 1;
                    if (next.length() != null) {
                        newMultimap2.put(Headers.COPY_PART_RANGE, "bytes=" + longValue + "-" + ((longValue + next.length().longValue()) - 1));
                    } else if (next.offset() != null) {
                        newMultimap2.put(Headers.COPY_PART_RANGE, "bytes=" + longValue + "-" + ((longValue + objectSize) - 1));
                    }
                    partArr[i2 - 1] = new Part(i2, uploadPartCopy(composeObjectArgs.bucket(), composeObjectArgs.region(), composeObjectArgs.object(), uploadId, i2, newMultimap2, null).result().etag());
                    i = i2;
                } else {
                    long j = objectSize;
                    while (j > 0) {
                        int i3 = i + 1;
                        long j2 = longValue + 5368709120L;
                        if (j < 5368709120L) {
                            j2 = longValue + j;
                        }
                        long j3 = j2;
                        Multimap<String, String> newMultimap3 = newMultimap(newMultimap2);
                        newMultimap3.put(str, "bytes=" + longValue + "-" + j3);
                        long j4 = longValue;
                        partArr[i3 + (-1)] = new Part(i3, uploadPartCopy(composeObjectArgs.bucket(), composeObjectArgs.region(), composeObjectArgs.object(), uploadId, i3, newMultimap3, null).result().etag());
                        j -= j3 - j4;
                        i = i3;
                        newMultimap2 = newMultimap2;
                        longValue = j4;
                        str = str;
                    }
                }
                it = it2;
            }
            return completeMultipartUpload(composeObjectArgs.bucket(), getRegion(composeObjectArgs.bucket(), composeObjectArgs.region()), composeObjectArgs.object(), uploadId, partArr, null, null);
        } catch (RuntimeException e) {
            abortMultipartUpload(composeObjectArgs.bucket(), composeObjectArgs.region(), composeObjectArgs.object(), uploadId, null, null);
            throw e;
        } catch (Exception e2) {
            abortMultipartUpload(composeObjectArgs.bucket(), composeObjectArgs.region(), composeObjectArgs.object(), uploadId, null, null);
            throw e2;
        }
    }

    public String getPresignedObjectUrl(GetPresignedObjectUrlArgs getPresignedObjectUrlArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, XmlParserException, ServerException {
        checkArgs(getPresignedObjectUrlArgs);
        byte[] bArr = (getPresignedObjectUrlArgs.method() == Method.PUT || getPresignedObjectUrlArgs.method() == Method.POST) ? HttpUtils.EMPTY_BODY : null;
        Multimap<String, String> newMultimap = newMultimap(getPresignedObjectUrlArgs.extraQueryParams());
        if (getPresignedObjectUrlArgs.versionId() != null) {
            newMultimap.put("versionId", getPresignedObjectUrlArgs.versionId());
        }
        String region = getRegion(getPresignedObjectUrlArgs.bucket(), getPresignedObjectUrlArgs.region());
        if (this.provider == null) {
            return buildUrl(getPresignedObjectUrlArgs.method(), getPresignedObjectUrlArgs.bucket(), getPresignedObjectUrlArgs.object(), region, newMultimap).toString();
        }
        Credentials fetch = this.provider.fetch();
        if (fetch.sessionToken() != null) {
            newMultimap.put("X-Amz-Security-Token", fetch.sessionToken());
        }
        return Signer.presignV4(createRequest(buildUrl(getPresignedObjectUrlArgs.method(), getPresignedObjectUrlArgs.bucket(), getPresignedObjectUrlArgs.object(), region, newMultimap), getPresignedObjectUrlArgs.method(), getPresignedObjectUrlArgs.extraHeaders() != null ? httpHeaders(getPresignedObjectUrlArgs.extraHeaders()) : null, bArr, 0, fetch), region, fetch.accessKey(), fetch.secretKey(), getPresignedObjectUrlArgs.expiry()).toString();
    }

    public Map<String, String> getPresignedPostFormData(PostPolicy postPolicy) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        if (this.provider == null) {
            throw new IllegalArgumentException("Anonymous access does not require presigned post form-data");
        }
        return postPolicy.formData(this.provider.fetch(), getRegion(postPolicy.bucket(), null));
    }

    public void removeObject(RemoveObjectArgs removeObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(removeObjectArgs);
        executeDelete(removeObjectArgs, removeObjectArgs.bypassGovernanceMode() ? newMultimap("x-amz-bypass-governance-retention", "true") : null, removeObjectArgs.versionId() != null ? newMultimap("versionId", removeObjectArgs.versionId()) : null);
    }

    public Iterable<Result<DeleteError>> removeObjects(final RemoveObjectsArgs removeObjectsArgs) {
        checkArgs(removeObjectsArgs);
        return new Iterable<Result<DeleteError>>() { // from class: io.minio.MinioClient.1
            @Override // java.lang.Iterable
            public Iterator<Result<DeleteError>> iterator() {
                return new Iterator<Result<DeleteError>>() { // from class: io.minio.MinioClient.1.1
                    private Iterator<DeleteObject> objectIter;
                    private Result<DeleteError> error = null;
                    private Iterator<DeleteError> errorIterator = null;
                    private boolean completed = false;

                    {
                        this.objectIter = removeObjectsArgs.objects().iterator();
                    }

                    private void setError() {
                        this.error = null;
                        while (this.errorIterator.hasNext()) {
                            DeleteError next = this.errorIterator.next();
                            if (!"NoSuchVersion".equals(next.code())) {
                                this.error = new Result<>(next);
                                return;
                            }
                        }
                    }

                    private synchronized void populate() {
                        LinkedList linkedList;
                        if (this.completed) {
                            return;
                        }
                        try {
                            linkedList = new LinkedList();
                            while (this.objectIter.hasNext() && linkedList.size() < 1000) {
                                linkedList.add(this.objectIter.next());
                            }
                            this.completed = linkedList.isEmpty();
                        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
                            this.error = new Result<>(e);
                            this.completed = true;
                        }
                        if (this.completed) {
                            return;
                        }
                        DeleteObjectsResponse deleteObjects = MinioClient.this.deleteObjects(removeObjectsArgs.bucket(), removeObjectsArgs.region(), linkedList, true, removeObjectsArgs.bypassGovernanceMode(), removeObjectsArgs.extraHeaders(), removeObjectsArgs.extraQueryParams());
                        if (!deleteObjects.result().errorList().isEmpty()) {
                            this.errorIterator = deleteObjects.result().errorList().iterator();
                            setError();
                            this.completed = true;
                        }
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        while (this.error == null && this.errorIterator == null && !this.completed) {
                            populate();
                        }
                        if (this.error == null && this.errorIterator != null) {
                            setError();
                        }
                        if (this.error != null) {
                            return true;
                        }
                        if (this.completed) {
                            return false;
                        }
                        this.errorIterator = null;
                        return hasNext();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public Result<DeleteError> next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        Result<DeleteError> result = this.error;
                        if (result != null) {
                            this.error = null;
                            return result;
                        }
                        throw new NoSuchElementException();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<Result<Item>> listObjects(ListObjectsArgs listObjectsArgs) {
        if (listObjectsArgs.includeVersions() || listObjectsArgs.versionIdMarker() != null) {
            return listObjectVersions(listObjectsArgs);
        }
        if (listObjectsArgs.useApiVersion1()) {
            return listObjectsV1(listObjectsArgs);
        }
        return listObjectsV2(listObjectsArgs);
    }

    public List<Bucket> listBuckets() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        return listBuckets(ListBucketsArgs.builder().build());
    }

    public List<Bucket> listBuckets(ListBucketsArgs listBucketsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        Response executeGet = executeGet(listBucketsArgs, null, null);
        try {
            List<Bucket> buckets = ((ListAllMyBucketsResult) Xml.unmarshal(ListAllMyBucketsResult.class, executeGet.body().charStream())).buckets();
            if (executeGet != null) {
                executeGet.close();
            }
            return buckets;
        } catch (Throwable th) {
            if (executeGet != null) {
                try {
                    executeGet.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public boolean bucketExists(BucketExistsArgs bucketExistsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        try {
            executeHead(bucketExistsArgs, null, null);
            return true;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchBucket")) {
                return false;
            }
            throw e;
        }
    }

    public void makeBucket(MakeBucketArgs makeBucketArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(makeBucketArgs);
        String region = makeBucketArgs.region();
        if (this.region != null && !this.region.isEmpty()) {
            if (region != null && !region.equals(this.region)) {
                throw new IllegalArgumentException("region must be " + this.region + ", but passed " + region);
            }
            region = this.region;
        }
        if (region == null) {
            region = "us-east-1";
        }
        Response execute = execute(Method.PUT, makeBucketArgs.bucket(), null, region, httpHeaders(merge(makeBucketArgs.extraHeaders(), makeBucketArgs.objectLock() ? newMultimap("x-amz-bucket-object-lock-enabled", "true") : null)), makeBucketArgs.extraQueryParams(), region.equals("us-east-1") ? null : new CreateBucketConfiguration(region), 0);
        try {
            this.regionCache.put(makeBucketArgs.bucket(), region);
            if (execute != null) {
                execute.close();
            }
        } catch (Throwable th) {
            if (execute != null) {
                try {
                    execute.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void setBucketVersioning(SetBucketVersioningArgs setBucketVersioningArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setBucketVersioningArgs);
        executePut(setBucketVersioningArgs, null, newMultimap("versioning", ""), setBucketVersioningArgs.config(), 0).close();
    }

    public VersioningConfiguration getBucketVersioning(GetBucketVersioningArgs getBucketVersioningArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getBucketVersioningArgs);
        Response executeGet = executeGet(getBucketVersioningArgs, null, newMultimap("versioning", ""));
        try {
            VersioningConfiguration versioningConfiguration = (VersioningConfiguration) Xml.unmarshal(VersioningConfiguration.class, executeGet.body().charStream());
            if (executeGet != null) {
                executeGet.close();
            }
            return versioningConfiguration;
        } catch (Throwable th) {
            if (executeGet != null) {
                try {
                    executeGet.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void setObjectLockConfiguration(SetObjectLockConfigurationArgs setObjectLockConfigurationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setObjectLockConfigurationArgs);
        executePut(setObjectLockConfigurationArgs, null, newMultimap("object-lock", ""), setObjectLockConfigurationArgs.config(), 0).close();
    }

    public void deleteObjectLockConfiguration(DeleteObjectLockConfigurationArgs deleteObjectLockConfigurationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteObjectLockConfigurationArgs);
        executePut(deleteObjectLockConfigurationArgs, null, newMultimap("object-lock", ""), new ObjectLockConfiguration(), 0).close();
    }

    public ObjectLockConfiguration getObjectLockConfiguration(GetObjectLockConfigurationArgs getObjectLockConfigurationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getObjectLockConfigurationArgs);
        Response executeGet = executeGet(getObjectLockConfigurationArgs, null, newMultimap("object-lock", ""));
        try {
            ObjectLockConfiguration objectLockConfiguration = (ObjectLockConfiguration) Xml.unmarshal(ObjectLockConfiguration.class, executeGet.body().charStream());
            if (executeGet != null) {
                executeGet.close();
            }
            return objectLockConfiguration;
        } catch (Throwable th) {
            if (executeGet != null) {
                try {
                    executeGet.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void setObjectRetention(SetObjectRetentionArgs setObjectRetentionArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setObjectRetentionArgs);
        Multimap<String, String> newMultimap = newMultimap("retention", "");
        if (setObjectRetentionArgs.versionId() != null) {
            newMultimap.put("versionId", setObjectRetentionArgs.versionId());
        }
        executePut(setObjectRetentionArgs, setObjectRetentionArgs.bypassGovernanceMode() ? newMultimap("x-amz-bypass-governance-retention", "True") : null, newMultimap, setObjectRetentionArgs.config(), 0).close();
    }

    public Retention getObjectRetention(GetObjectRetentionArgs getObjectRetentionArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getObjectRetentionArgs);
        Multimap<String, String> newMultimap = newMultimap("retention", "");
        if (getObjectRetentionArgs.versionId() != null) {
            newMultimap.put("versionId", getObjectRetentionArgs.versionId());
        }
        try {
            Response executeGet = executeGet(getObjectRetentionArgs, null, newMultimap);
            try {
                Retention retention = (Retention) Xml.unmarshal(Retention.class, executeGet.body().charStream());
                if (executeGet != null) {
                    executeGet.close();
                }
                return retention;
            } finally {
            }
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchObjectLockConfiguration")) {
                return null;
            }
            throw e;
        }
    }

    public void enableObjectLegalHold(EnableObjectLegalHoldArgs enableObjectLegalHoldArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(enableObjectLegalHoldArgs);
        Multimap<String, String> newMultimap = newMultimap("legal-hold", "");
        if (enableObjectLegalHoldArgs.versionId() != null) {
            newMultimap.put("versionId", enableObjectLegalHoldArgs.versionId());
        }
        executePut(enableObjectLegalHoldArgs, null, newMultimap, new LegalHold(true), 0).close();
    }

    public void disableObjectLegalHold(DisableObjectLegalHoldArgs disableObjectLegalHoldArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(disableObjectLegalHoldArgs);
        Multimap<String, String> newMultimap = newMultimap("legal-hold", "");
        if (disableObjectLegalHoldArgs.versionId() != null) {
            newMultimap.put("versionId", disableObjectLegalHoldArgs.versionId());
        }
        executePut(disableObjectLegalHoldArgs, null, newMultimap, new LegalHold(false), 0).close();
    }

    public boolean isObjectLegalHoldEnabled(IsObjectLegalHoldEnabledArgs isObjectLegalHoldEnabledArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(isObjectLegalHoldEnabledArgs);
        Multimap<String, String> newMultimap = newMultimap("legal-hold", "");
        if (isObjectLegalHoldEnabledArgs.versionId() != null) {
            newMultimap.put("versionId", isObjectLegalHoldEnabledArgs.versionId());
        }
        try {
            Response executeGet = executeGet(isObjectLegalHoldEnabledArgs, null, newMultimap);
            try {
                boolean status = ((LegalHold) Xml.unmarshal(LegalHold.class, executeGet.body().charStream())).status();
                if (executeGet != null) {
                    executeGet.close();
                }
                return status;
            } finally {
            }
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchObjectLockConfiguration")) {
                return false;
            }
            throw e;
        }
    }

    public void removeBucket(RemoveBucketArgs removeBucketArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(removeBucketArgs);
        executeDelete(removeBucketArgs, null, null);
        this.regionCache.remove(removeBucketArgs.bucket());
    }

    public ObjectWriteResponse putObject(PutObjectArgs putObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(putObjectArgs);
        putObjectArgs.validateSse(this.baseUrl);
        return putObject(putObjectArgs, putObjectArgs.stream(), putObjectArgs.objectSize(), putObjectArgs.partSize(), putObjectArgs.partCount(), putObjectArgs.contentType());
    }

    public ObjectWriteResponse uploadObject(UploadObjectArgs uploadObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(uploadObjectArgs);
        uploadObjectArgs.validateSse(this.baseUrl);
        RandomAccessFile randomAccessFile = new RandomAccessFile(uploadObjectArgs.filename(), "r");
        try {
            ObjectWriteResponse putObject = putObject(uploadObjectArgs, randomAccessFile, uploadObjectArgs.objectSize(), uploadObjectArgs.partSize(), uploadObjectArgs.partCount(), uploadObjectArgs.contentType());
            randomAccessFile.close();
            return putObject;
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public String getBucketPolicy(GetBucketPolicyArgs getBucketPolicyArgs) throws BucketPolicyTooLargeException, ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getBucketPolicyArgs);
        try {
            Response executeGet = executeGet(getBucketPolicyArgs, null, newMultimap("policy", ""));
            try {
                byte[] bArr = new byte[20480];
                int read = executeGet.body().byteStream().read(bArr, 0, 20480);
                if (read < 0) {
                    throw new IOException("unexpected EOF when reading bucket policy");
                }
                if (read == 20480) {
                    int i = 0;
                    while (i == 0) {
                        i = executeGet.body().byteStream().read();
                        if (i < 0) {
                            break;
                        }
                        if (i > 0) {
                            throw new BucketPolicyTooLargeException(getBucketPolicyArgs.bucket());
                        }
                    }
                }
                String str = new String(bArr, 0, read, StandardCharsets.UTF_8);
                if (executeGet != null) {
                    executeGet.close();
                }
                return str;
            } finally {
            }
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchBucketPolicy")) {
                return "";
            }
            throw e;
        }
    }

    public void setBucketPolicy(SetBucketPolicyArgs setBucketPolicyArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setBucketPolicyArgs);
        executePut(setBucketPolicyArgs, newMultimap("Content-Type", "application/json"), newMultimap("policy", ""), setBucketPolicyArgs.config(), 0).close();
    }

    public void deleteBucketPolicy(DeleteBucketPolicyArgs deleteBucketPolicyArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteBucketPolicyArgs);
        try {
            executeDelete(deleteBucketPolicyArgs, null, newMultimap("policy", ""));
        } catch (ErrorResponseException e) {
            if (!e.errorResponse().code().equals("NoSuchBucketPolicy")) {
                throw e;
            }
        }
    }

    public void setBucketLifecycle(SetBucketLifecycleArgs setBucketLifecycleArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setBucketLifecycleArgs);
        executePut(setBucketLifecycleArgs, null, newMultimap(RequestParameters.SUBRESOURCE_LIFECYCLE, ""), setBucketLifecycleArgs.config(), 0).close();
    }

    public void deleteBucketLifecycle(DeleteBucketLifecycleArgs deleteBucketLifecycleArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteBucketLifecycleArgs);
        executeDelete(deleteBucketLifecycleArgs, null, newMultimap(RequestParameters.SUBRESOURCE_LIFECYCLE, ""));
    }

    public LifecycleConfiguration getBucketLifecycle(GetBucketLifecycleArgs getBucketLifecycleArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getBucketLifecycleArgs);
        try {
            Response executeGet = executeGet(getBucketLifecycleArgs, null, newMultimap(RequestParameters.SUBRESOURCE_LIFECYCLE, ""));
            try {
                LifecycleConfiguration lifecycleConfiguration = (LifecycleConfiguration) Xml.unmarshal(LifecycleConfiguration.class, executeGet.body().charStream());
                if (executeGet != null) {
                    executeGet.close();
                }
                return lifecycleConfiguration;
            } finally {
            }
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchLifecycleConfiguration")) {
                return null;
            }
            throw e;
        }
    }

    public NotificationConfiguration getBucketNotification(GetBucketNotificationArgs getBucketNotificationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getBucketNotificationArgs);
        Response executeGet = executeGet(getBucketNotificationArgs, null, newMultimap(TransferService.INTENT_KEY_NOTIFICATION, ""));
        try {
            NotificationConfiguration notificationConfiguration = (NotificationConfiguration) Xml.unmarshal(NotificationConfiguration.class, executeGet.body().charStream());
            if (executeGet != null) {
                executeGet.close();
            }
            return notificationConfiguration;
        } catch (Throwable th) {
            if (executeGet != null) {
                try {
                    executeGet.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void setBucketNotification(SetBucketNotificationArgs setBucketNotificationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setBucketNotificationArgs);
        executePut(setBucketNotificationArgs, null, newMultimap(TransferService.INTENT_KEY_NOTIFICATION, ""), setBucketNotificationArgs.config(), 0).close();
    }

    public void deleteBucketNotification(DeleteBucketNotificationArgs deleteBucketNotificationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteBucketNotificationArgs);
        executePut(deleteBucketNotificationArgs, null, newMultimap(TransferService.INTENT_KEY_NOTIFICATION, ""), new NotificationConfiguration(), 0).close();
    }

    public ReplicationConfiguration getBucketReplication(GetBucketReplicationArgs getBucketReplicationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getBucketReplicationArgs);
        try {
            Response executeGet = executeGet(getBucketReplicationArgs, null, newMultimap("replication", ""));
            try {
                ReplicationConfiguration replicationConfiguration = (ReplicationConfiguration) Xml.unmarshal(ReplicationConfiguration.class, executeGet.body().charStream());
                if (executeGet != null) {
                    executeGet.close();
                }
                return replicationConfiguration;
            } finally {
            }
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("ReplicationConfigurationNotFoundError")) {
                return null;
            }
            throw e;
        }
    }

    public void setBucketReplication(SetBucketReplicationArgs setBucketReplicationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setBucketReplicationArgs);
        executePut(setBucketReplicationArgs, setBucketReplicationArgs.objectLockToken() != null ? newMultimap("x-amz-bucket-object-lock-token", setBucketReplicationArgs.objectLockToken()) : null, newMultimap("replication", ""), setBucketReplicationArgs.config(), 0).close();
    }

    public void deleteBucketReplication(DeleteBucketReplicationArgs deleteBucketReplicationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteBucketReplicationArgs);
        executeDelete(deleteBucketReplicationArgs, null, newMultimap("replication", ""));
    }

    public CloseableIterator<Result<NotificationRecords>> listenBucketNotification(ListenBucketNotificationArgs listenBucketNotificationArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(listenBucketNotificationArgs);
        Multimap<String, String> newMultimap = newMultimap(RequestParameters.PREFIX, listenBucketNotificationArgs.prefix(), "suffix", listenBucketNotificationArgs.suffix());
        for (String str : listenBucketNotificationArgs.events()) {
            newMultimap.put(TmpConstant.DEVICE_MODEL_EVENTS, str);
        }
        return new S3Base.NotificationResultRecords(executeGet(listenBucketNotificationArgs, null, newMultimap)).closeableIterator();
    }

    public SelectResponseStream selectObjectContent(SelectObjectContentArgs selectObjectContentArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(selectObjectContentArgs);
        selectObjectContentArgs.validateSsec(this.baseUrl);
        return new SelectResponseStream(executePost(selectObjectContentArgs, selectObjectContentArgs.ssec() != null ? newMultimap(selectObjectContentArgs.ssec().headers()) : null, newMultimap("select", "", "select-type", "2"), new SelectObjectContentRequest(selectObjectContentArgs.sqlExpression(), selectObjectContentArgs.requestProgress().booleanValue(), selectObjectContentArgs.inputSerialization(), selectObjectContentArgs.outputSerialization(), selectObjectContentArgs.scanStartRange(), selectObjectContentArgs.scanEndRange())).body().byteStream());
    }

    public void setBucketEncryption(SetBucketEncryptionArgs setBucketEncryptionArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setBucketEncryptionArgs);
        executePut(setBucketEncryptionArgs, null, newMultimap("encryption", ""), setBucketEncryptionArgs.config(), 0).close();
    }

    public SseConfiguration getBucketEncryption(GetBucketEncryptionArgs getBucketEncryptionArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getBucketEncryptionArgs);
        try {
            Response executeGet = executeGet(getBucketEncryptionArgs, null, newMultimap("encryption", ""));
            try {
                SseConfiguration sseConfiguration = (SseConfiguration) Xml.unmarshal(SseConfiguration.class, executeGet.body().charStream());
                if (executeGet != null) {
                    executeGet.close();
                }
                return sseConfiguration;
            } finally {
            }
        } catch (ErrorResponseException e) {
            if (!e.errorResponse().code().equals("ServerSideEncryptionConfigurationNotFoundError")) {
                throw e;
            }
            return new SseConfiguration(null);
        }
    }

    public void deleteBucketEncryption(DeleteBucketEncryptionArgs deleteBucketEncryptionArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteBucketEncryptionArgs);
        try {
            executeDelete(deleteBucketEncryptionArgs, null, newMultimap("encryption", ""));
        } catch (ErrorResponseException e) {
            if (!e.errorResponse().code().equals("ServerSideEncryptionConfigurationNotFoundError")) {
                throw e;
            }
        }
    }

    public Tags getBucketTags(GetBucketTagsArgs getBucketTagsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getBucketTagsArgs);
        try {
            Response executeGet = executeGet(getBucketTagsArgs, null, newMultimap("tagging", ""));
            try {
                Tags tags = (Tags) Xml.unmarshal(Tags.class, executeGet.body().charStream());
                if (executeGet != null) {
                    executeGet.close();
                }
                return tags;
            } finally {
            }
        } catch (ErrorResponseException e) {
            if (!e.errorResponse().code().equals("NoSuchTagSet")) {
                throw e;
            }
            return new Tags();
        }
    }

    public void setBucketTags(SetBucketTagsArgs setBucketTagsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setBucketTagsArgs);
        executePut(setBucketTagsArgs, null, newMultimap("tagging", ""), setBucketTagsArgs.tags(), 0).close();
    }

    public void deleteBucketTags(DeleteBucketTagsArgs deleteBucketTagsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteBucketTagsArgs);
        executeDelete(deleteBucketTagsArgs, null, newMultimap("tagging", ""));
    }

    public Tags getObjectTags(GetObjectTagsArgs getObjectTagsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(getObjectTagsArgs);
        Multimap<String, String> newMultimap = newMultimap("tagging", "");
        if (getObjectTagsArgs.versionId() != null) {
            newMultimap.put("versionId", getObjectTagsArgs.versionId());
        }
        Response executeGet = executeGet(getObjectTagsArgs, null, newMultimap);
        try {
            Tags tags = (Tags) Xml.unmarshal(Tags.class, executeGet.body().charStream());
            if (executeGet != null) {
                executeGet.close();
            }
            return tags;
        } catch (Throwable th) {
            if (executeGet != null) {
                try {
                    executeGet.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void setObjectTags(SetObjectTagsArgs setObjectTagsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(setObjectTagsArgs);
        Multimap<String, String> newMultimap = newMultimap("tagging", "");
        if (setObjectTagsArgs.versionId() != null) {
            newMultimap.put("versionId", setObjectTagsArgs.versionId());
        }
        executePut(setObjectTagsArgs, null, newMultimap, setObjectTagsArgs.tags(), 0).close();
    }

    public void deleteObjectTags(DeleteObjectTagsArgs deleteObjectTagsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(deleteObjectTagsArgs);
        Multimap<String, String> newMultimap = newMultimap("tagging", "");
        if (deleteObjectTagsArgs.versionId() != null) {
            newMultimap.put("versionId", deleteObjectTagsArgs.versionId());
        }
        executeDelete(deleteObjectTagsArgs, null, newMultimap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01a8  */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r17v0, types: [io.minio.MinioClient] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [org.xerial.snappy.SnappyFramedOutputStream] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [org.xerial.snappy.SnappyFramedOutputStream] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ObjectWriteResponse uploadSnowballObjects(UploadSnowballObjectsArgs uploadSnowballObjectsArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        ?? r5;
        TarArchiveOutputStream tarArchiveOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        OutputStream outputStream;
        OutputStream outputStream2;
        checkArgs(uploadSnowballObjectsArgs);
        try {
            if (uploadSnowballObjectsArgs.stagingFilename() != null) {
                fileOutputStream = new FileOutputStream(uploadSnowballObjectsArgs.stagingFilename());
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                    bufferedOutputStream = bufferedOutputStream2;
                    byteArrayOutputStream = null;
                    outputStream = bufferedOutputStream2;
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = null;
                    r5 = bufferedOutputStream;
                    tarArchiveOutputStream = r5;
                    if (tarArchiveOutputStream != null) {
                    }
                    if (r5 != 0) {
                    }
                    if (bufferedOutputStream != null) {
                    }
                    if (fileOutputStream != null) {
                    }
                    if (tarArchiveOutputStream != null) {
                    }
                    if (r5 != 0) {
                    }
                    if (bufferedOutputStream != null) {
                    }
                    if (fileOutputStream != null) {
                    }
                    throw th;
                }
            } else {
                ?? byteArrayOutputStream2 = new ByteArrayOutputStream();
                byteArrayOutputStream = byteArrayOutputStream2;
                fileOutputStream = null;
                bufferedOutputStream = null;
                outputStream = byteArrayOutputStream2;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            bufferedOutputStream = null;
        }
        try {
            if (uploadSnowballObjectsArgs.compression()) {
                OutputStream snappyFramedOutputStream = new SnappyFramedOutputStream(outputStream);
                outputStream2 = snappyFramedOutputStream;
                r5 = snappyFramedOutputStream;
            } else {
                r5 = 0;
                outputStream2 = outputStream;
            }
            try {
                tarArchiveOutputStream = new TarArchiveOutputStream(outputStream2);
                try {
                    for (SnowballObject snowballObject : uploadSnowballObjectsArgs.objects()) {
                        try {
                            if (snowballObject.filename() != null) {
                                Path path = Paths.get(snowballObject.filename(), new String[0]);
                                tarArchiveOutputStream.putArchiveEntry(new TarArchiveEntry(path.toFile(), snowballObject.name()));
                                Files.copy(path, tarArchiveOutputStream);
                            } else {
                                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(snowballObject.name());
                                if (snowballObject.modificationTime() != null) {
                                    tarArchiveEntry.setModTime(Date.from(snowballObject.modificationTime().toInstant()));
                                }
                                tarArchiveEntry.setSize(snowballObject.size());
                                tarArchiveOutputStream.putArchiveEntry(tarArchiveEntry);
                                ByteStreams.copy(snowballObject.stream(), tarArchiveOutputStream);
                            }
                            tarArchiveOutputStream.closeArchiveEntry();
                        } catch (Throwable th3) {
                            th = th3;
                            if (tarArchiveOutputStream != null) {
                                tarArchiveOutputStream.flush();
                            }
                            if (r5 != 0) {
                                r5.flush();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.flush();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                            }
                            if (tarArchiveOutputStream != null) {
                                tarArchiveOutputStream.close();
                            }
                            if (r5 != 0) {
                                r5.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    }
                    tarArchiveOutputStream.finish();
                    tarArchiveOutputStream.flush();
                    if (r5 != 0) {
                        r5.flush();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.flush();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                    }
                    tarArchiveOutputStream.close();
                    if (r5 != 0) {
                        r5.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    Multimap<String, String> newMultimap = newMultimap(uploadSnowballObjectsArgs.extraHeaders());
                    newMultimap.putAll(uploadSnowballObjectsArgs.genHeaders());
                    newMultimap.put("X-Amz-Meta-Snowball-Auto-Extract", "true");
                    if (uploadSnowballObjectsArgs.stagingFilename() == null) {
                        return putObject(uploadSnowballObjectsArgs.bucket(), uploadSnowballObjectsArgs.region(), uploadSnowballObjectsArgs.object(), byteArrayOutputStream.toByteArray(), r12.length, newMultimap, uploadSnowballObjectsArgs.extraQueryParams());
                    }
                    long length = Paths.get(uploadSnowballObjectsArgs.stagingFilename(), new String[0]).toFile().length();
                    if (length > ObjectWriteArgs.MAX_OBJECT_SIZE) {
                        throw new IllegalArgumentException("tarball size " + length + " is more than maximum allowed 5TiB");
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(uploadSnowballObjectsArgs.stagingFilename(), "r");
                    try {
                        ObjectWriteResponse putObject = putObject(uploadSnowballObjectsArgs.bucket(), uploadSnowballObjectsArgs.region(), uploadSnowballObjectsArgs.object(), randomAccessFile, length, newMultimap, uploadSnowballObjectsArgs.extraQueryParams());
                        randomAccessFile.close();
                        return putObject;
                    } finally {
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
                tarArchiveOutputStream = null;
            }
        } catch (Throwable th6) {
            th = th6;
            r5 = 0;
            tarArchiveOutputStream = r5;
            if (tarArchiveOutputStream != null) {
            }
            if (r5 != 0) {
            }
            if (bufferedOutputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            if (tarArchiveOutputStream != null) {
            }
            if (r5 != 0) {
            }
            if (bufferedOutputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            throw th;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder {
        private HttpUrl baseUrl;
        private OkHttpClient httpClient;
        private boolean isAcceleratedHost;
        private boolean isAwsChinaHost;
        private boolean isAwsHost;
        private boolean isDualStackHost;
        private Provider provider;
        private String region;
        private String regionInUrl;
        private boolean useVirtualStyle;

        private boolean isAwsEndpoint(String str) {
            return (str.startsWith("s3.") || isAwsAccelerateEndpoint(str)) && (str.endsWith(".amazonaws.com") || str.endsWith(".amazonaws.com.cn"));
        }

        private boolean isAwsAccelerateEndpoint(String str) {
            return str.startsWith("s3-accelerate.");
        }

        private boolean isAwsDualStackEndpoint(String str) {
            return str.contains(".dualstack.");
        }

        private String extractRegion(String str) {
            String[] split = str.split("\\.");
            String str2 = split[1];
            if (str2.equals(Constants.S3_DUALSTACK_QUALIFIER)) {
                str2 = split[2];
            }
            if (str2.equals("amazonaws")) {
                return null;
            }
            return str2;
        }

        private void setBaseUrl(HttpUrl httpUrl) {
            String host = httpUrl.host();
            this.isAwsHost = isAwsEndpoint(host);
            this.isAwsChinaHost = false;
            if (this.isAwsHost) {
                this.isAwsChinaHost = host.endsWith(".cn");
                httpUrl = httpUrl.newBuilder().host(this.isAwsChinaHost ? "amazonaws.com.cn" : "amazonaws.com").build();
                this.isAcceleratedHost = isAwsAccelerateEndpoint(host);
                this.isDualStackHost = isAwsDualStackEndpoint(host);
                this.regionInUrl = extractRegion(host);
                this.useVirtualStyle = true;
            } else {
                this.useVirtualStyle = host.endsWith("aliyuncs.com");
            }
            this.baseUrl = httpUrl;
        }

        public Builder endpoint(String str) {
            setBaseUrl(HttpUtils.getBaseUrl(str));
            return this;
        }

        public Builder endpoint(String str, int i, boolean z) {
            HttpUrl baseUrl = HttpUtils.getBaseUrl(str);
            if (i < 1 || i > 65535) {
                throw new IllegalArgumentException("port must be in range of 1 to 65535");
            }
            setBaseUrl(baseUrl.newBuilder().port(i).scheme(z ? "https" : HttpHost.DEFAULT_SCHEME_NAME).build());
            return this;
        }

        public Builder endpoint(URL url) {
            HttpUtils.validateNotNull(url, "url");
            return endpoint(HttpUrl.get(url));
        }

        public Builder endpoint(HttpUrl httpUrl) {
            HttpUtils.validateNotNull(httpUrl, "url");
            HttpUtils.validateUrl(httpUrl);
            setBaseUrl(httpUrl);
            return this;
        }

        public Builder region(String str) {
            HttpUtils.validateNullOrNotEmptyString(str, OSSConfig.PARAM_REGION);
            this.regionInUrl = str;
            this.region = str;
            return this;
        }

        public Builder credentials(String str, String str2) {
            this.provider = new StaticProvider(str, str2, null);
            return this;
        }

        public Builder credentialsProvider(Provider provider) {
            this.provider = provider;
            return this;
        }

        public Builder httpClient(OkHttpClient okHttpClient) {
            HttpUtils.validateNotNull(okHttpClient, "http client");
            this.httpClient = okHttpClient;
            return this;
        }

        public MinioClient build() {
            HttpUtils.validateNotNull(this.baseUrl, "endpoint");
            if (this.isAwsChinaHost && this.regionInUrl == null && this.region == null) {
                throw new IllegalArgumentException("Region missing in Amazon S3 China endpoint " + this.baseUrl);
            }
            if (this.httpClient == null) {
                this.httpClient = HttpUtils.newDefaultHttpClient(S3Base.DEFAULT_CONNECTION_TIMEOUT, S3Base.DEFAULT_CONNECTION_TIMEOUT, S3Base.DEFAULT_CONNECTION_TIMEOUT);
                if (this.region == null) {
                    this.region = this.regionInUrl;
                }
            }
            return new MinioClient(this.baseUrl, this.region, this.isAwsHost, this.isAcceleratedHost, this.isDualStackHost, this.useVirtualStyle, this.provider, this.httpClient);
        }
    }
}
