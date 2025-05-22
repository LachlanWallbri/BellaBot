package com.amazonaws.services.p048s3.internal.crypto;

import com.amazonaws.AmazonClientException;

@Deprecated
/* loaded from: classes.dex */
class MultipartUploadCryptoContext extends MultipartUploadContext {
    private final ContentCryptoMaterial cekMaterial;
    private int partNumber;
    private volatile boolean partUploadInProgress;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultipartUploadCryptoContext(String str, String str2, ContentCryptoMaterial contentCryptoMaterial) {
        super(str, str2);
        this.cekMaterial = contentCryptoMaterial;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite getCipherLite() {
        return this.cekMaterial.getCipherLite();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContentCryptoMaterial getContentCryptoMaterial() {
        return this.cekMaterial;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void beginPartUpload(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("part number must be at least 1");
        }
        if (this.partUploadInProgress) {
            throw new AmazonClientException("Parts are required to be uploaded in series");
        }
        synchronized (this) {
            if (i - this.partNumber <= 1) {
                this.partNumber = i;
                this.partUploadInProgress = true;
            } else {
                throw new AmazonClientException("Parts are required to be uploaded in series (partNumber=" + this.partNumber + ", nextPartNumber=" + i + ")");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endPartUpload() {
        this.partUploadInProgress = false;
    }
}
