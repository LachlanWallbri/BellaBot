package io.minio.errors;

/* loaded from: classes7.dex */
public class BucketPolicyTooLargeException extends MinioException {
    private static final long serialVersionUID = -4589481215582512545L;

    public BucketPolicyTooLargeException(String str) {
        super("Bucket policy is larger than 20KiB size for bucket " + str);
    }
}
