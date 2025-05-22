package io.grpc.grpclb;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.grpclb.GrpclbState;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class GrpclbConfig {
    private final GrpclbState.Mode mode;

    @Nullable
    private final String serviceName;

    private GrpclbConfig(GrpclbState.Mode mode, @Nullable String str) {
        this.mode = (GrpclbState.Mode) Preconditions.checkNotNull(mode, "mode");
        this.serviceName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GrpclbConfig create(GrpclbState.Mode mode) {
        return create(mode, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GrpclbConfig create(GrpclbState.Mode mode, @Nullable String str) {
        return new GrpclbConfig(mode, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpclbState.Mode getMode() {
        return this.mode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getServiceName() {
        return this.serviceName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GrpclbConfig grpclbConfig = (GrpclbConfig) obj;
        return this.mode == grpclbConfig.mode && Objects.equal(this.serviceName, grpclbConfig.serviceName);
    }

    public int hashCode() {
        return Objects.hashCode(this.mode, this.serviceName);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mode", this.mode).add("serviceName", this.serviceName).toString();
    }
}
