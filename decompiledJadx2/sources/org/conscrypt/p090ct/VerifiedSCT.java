package org.conscrypt.p090ct;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class VerifiedSCT {
    public final SignedCertificateTimestamp sct;
    public final Status status;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum Status {
        VALID,
        INVALID_SIGNATURE,
        UNKNOWN_LOG,
        INVALID_SCT
    }

    public VerifiedSCT(SignedCertificateTimestamp signedCertificateTimestamp, Status status) {
        this.sct = signedCertificateTimestamp;
        this.status = status;
    }
}
