package com.amazonaws.services.p048s3.internal.crypto;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.p048s3.internal.S3Direct;
import com.amazonaws.services.p048s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.p048s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.p048s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.p048s3.model.CopyPartRequest;
import com.amazonaws.services.p048s3.model.CopyPartResult;
import com.amazonaws.services.p048s3.model.CryptoConfiguration;
import com.amazonaws.services.p048s3.model.CryptoMode;
import com.amazonaws.services.p048s3.model.EncryptionMaterialsProvider;
import com.amazonaws.services.p048s3.model.GetObjectRequest;
import com.amazonaws.services.p048s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.p048s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.p048s3.model.ObjectMetadata;
import com.amazonaws.services.p048s3.model.PutInstructionFileRequest;
import com.amazonaws.services.p048s3.model.PutObjectRequest;
import com.amazonaws.services.p048s3.model.PutObjectResult;
import com.amazonaws.services.p048s3.model.S3Object;
import com.amazonaws.services.p048s3.model.UploadObjectRequest;
import com.amazonaws.services.p048s3.model.UploadPartRequest;
import com.amazonaws.services.p048s3.model.UploadPartResult;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Deprecated
/* loaded from: classes.dex */
public class CryptoModuleDispatcher extends S3CryptoModule<MultipartUploadContext> {

    /* renamed from: ae */
    private final S3CryptoModuleAE f1176ae;
    private final CryptoMode defaultCryptoMode;

    /* renamed from: eo */
    private final S3CryptoModuleEO f1177eo;

    public CryptoModuleDispatcher(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        CryptoConfiguration mo4271clone = cryptoConfiguration.mo4271clone();
        if (mo4271clone.getCryptoMode() == null) {
            mo4271clone.setCryptoMode(CryptoMode.EncryptionOnly);
        }
        CryptoConfiguration readOnly = mo4271clone.readOnly();
        this.defaultCryptoMode = readOnly.getCryptoMode();
        int i = C13091.$SwitchMap$com$amazonaws$services$s3$model$CryptoMode[this.defaultCryptoMode.ordinal()];
        if (i == 1) {
            this.f1176ae = new S3CryptoModuleAEStrict(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, readOnly);
            this.f1177eo = null;
        } else if (i == 2) {
            this.f1176ae = new S3CryptoModuleAE(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, readOnly);
            this.f1177eo = null;
        } else {
            if (i == 3) {
                this.f1177eo = new S3CryptoModuleEO(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, readOnly);
                CryptoConfiguration mo4271clone2 = readOnly.mo4271clone();
                try {
                    mo4271clone2.setCryptoMode(CryptoMode.AuthenticatedEncryption);
                } catch (UnsupportedOperationException unused) {
                }
                this.f1176ae = new S3CryptoModuleAE(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, mo4271clone2.readOnly());
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: com.amazonaws.services.s3.internal.crypto.CryptoModuleDispatcher$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C13091 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$services$s3$model$CryptoMode = new int[CryptoMode.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.StrictAuthenticatedEncryption.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.AuthenticatedEncryption.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.EncryptionOnly.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public PutObjectResult putObjectSecurely(PutObjectRequest putObjectRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.f1177eo.putObjectSecurely(putObjectRequest);
        }
        return this.f1176ae.putObjectSecurely(putObjectRequest);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public S3Object getObjectSecurely(GetObjectRequest getObjectRequest) {
        return this.f1176ae.getObjectSecurely(getObjectRequest);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public ObjectMetadata getObjectSecurely(GetObjectRequest getObjectRequest, File file) {
        return this.f1176ae.getObjectSecurely(getObjectRequest, file);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public CompleteMultipartUploadResult completeMultipartUploadSecurely(CompleteMultipartUploadRequest completeMultipartUploadRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.f1177eo.completeMultipartUploadSecurely(completeMultipartUploadRequest);
        }
        return this.f1176ae.completeMultipartUploadSecurely(completeMultipartUploadRequest);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public void abortMultipartUploadSecurely(AbortMultipartUploadRequest abortMultipartUploadRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            this.f1177eo.abortMultipartUploadSecurely(abortMultipartUploadRequest);
        } else {
            this.f1176ae.abortMultipartUploadSecurely(abortMultipartUploadRequest);
        }
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public InitiateMultipartUploadResult initiateMultipartUploadSecurely(InitiateMultipartUploadRequest initiateMultipartUploadRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.f1177eo.initiateMultipartUploadSecurely(initiateMultipartUploadRequest);
        }
        return this.f1176ae.initiateMultipartUploadSecurely(initiateMultipartUploadRequest);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public UploadPartResult uploadPartSecurely(UploadPartRequest uploadPartRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.f1177eo.uploadPartSecurely(uploadPartRequest);
        }
        return this.f1176ae.uploadPartSecurely(uploadPartRequest);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public CopyPartResult copyPartSecurely(CopyPartRequest copyPartRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.f1177eo.copyPartSecurely(copyPartRequest);
        }
        return this.f1176ae.copyPartSecurely(copyPartRequest);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public PutObjectResult putInstructionFileSecurely(PutInstructionFileRequest putInstructionFileRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.f1177eo.putInstructionFileSecurely(putInstructionFileRequest);
        }
        return this.f1176ae.putInstructionFileSecurely(putInstructionFileRequest);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.S3CryptoModule
    public void putLocalObjectSecurely(UploadObjectRequest uploadObjectRequest, String str, OutputStream outputStream) throws IOException {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            this.f1177eo.putLocalObjectSecurely(uploadObjectRequest, str, outputStream);
        } else {
            this.f1176ae.putLocalObjectSecurely(uploadObjectRequest, str, outputStream);
        }
    }
}
