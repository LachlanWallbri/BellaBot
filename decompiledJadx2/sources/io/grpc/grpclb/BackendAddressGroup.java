package io.grpc.grpclb;

import com.google.common.base.Preconditions;
import io.grpc.EquivalentAddressGroup;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class BackendAddressGroup {
    private final EquivalentAddressGroup addresses;

    @Nullable
    private final String token;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackendAddressGroup(EquivalentAddressGroup equivalentAddressGroup, @Nullable String str) {
        this.addresses = (EquivalentAddressGroup) Preconditions.checkNotNull(equivalentAddressGroup, "addresses");
        this.token = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EquivalentAddressGroup getAddresses() {
        return this.addresses;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getToken() {
        return this.token;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addresses);
        if (this.token != null) {
            sb.append("(");
            sb.append(this.token);
            sb.append(")");
        }
        return sb.toString();
    }
}
