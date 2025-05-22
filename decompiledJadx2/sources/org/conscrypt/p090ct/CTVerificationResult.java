package org.conscrypt.p090ct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.conscrypt.p090ct.VerifiedSCT;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class CTVerificationResult {
    private final ArrayList<VerifiedSCT> validSCTs = new ArrayList<>();
    private final ArrayList<VerifiedSCT> invalidSCTs = new ArrayList<>();

    public void add(VerifiedSCT verifiedSCT) {
        if (verifiedSCT.status == VerifiedSCT.Status.VALID) {
            this.validSCTs.add(verifiedSCT);
        } else {
            this.invalidSCTs.add(verifiedSCT);
        }
    }

    public List<VerifiedSCT> getValidSCTs() {
        return Collections.unmodifiableList(this.validSCTs);
    }

    public List<VerifiedSCT> getInvalidSCTs() {
        return Collections.unmodifiableList(this.invalidSCTs);
    }
}
