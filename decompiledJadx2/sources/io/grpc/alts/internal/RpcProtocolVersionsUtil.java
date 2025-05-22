package io.grpc.alts.internal;

import io.grpc.alts.internal.RpcProtocolVersions;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class RpcProtocolVersionsUtil {
    private static final int MAX_RPC_VERSION_MAJOR = 2;
    private static final int MAX_RPC_VERSION_MINOR = 1;
    private static final int MIN_RPC_VERSION_MAJOR = 2;
    private static final int MIN_RPC_VERSION_MINOR = 1;
    private static final RpcProtocolVersions RPC_PROTOCOL_VERSIONS = RpcProtocolVersions.newBuilder().setMaxRpcVersion(RpcProtocolVersions.Version.newBuilder().setMajor(2).setMinor(1).build()).setMinRpcVersion(RpcProtocolVersions.Version.newBuilder().setMajor(2).setMinor(1).build()).build();

    public static RpcProtocolVersions getRpcProtocolVersions() {
        return RPC_PROTOCOL_VERSIONS;
    }

    static boolean isGreaterThanOrEqualTo(RpcProtocolVersions.Version version, RpcProtocolVersions.Version version2) {
        if (version.getMajor() <= version2.getMajor()) {
            return version.getMajor() == version2.getMajor() && version.getMinor() >= version2.getMinor();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RpcVersionsCheckResult checkRpcProtocolVersions(RpcProtocolVersions rpcProtocolVersions, RpcProtocolVersions rpcProtocolVersions2) {
        RpcProtocolVersions.Version maxRpcVersion;
        RpcProtocolVersions.Version minRpcVersion;
        if (isGreaterThanOrEqualTo(rpcProtocolVersions.getMaxRpcVersion(), rpcProtocolVersions2.getMaxRpcVersion())) {
            maxRpcVersion = rpcProtocolVersions2.getMaxRpcVersion();
        } else {
            maxRpcVersion = rpcProtocolVersions.getMaxRpcVersion();
        }
        if (isGreaterThanOrEqualTo(rpcProtocolVersions.getMinRpcVersion(), rpcProtocolVersions2.getMinRpcVersion())) {
            minRpcVersion = rpcProtocolVersions.getMinRpcVersion();
        } else {
            minRpcVersion = rpcProtocolVersions2.getMinRpcVersion();
        }
        if (isGreaterThanOrEqualTo(maxRpcVersion, minRpcVersion)) {
            return new RpcVersionsCheckResult.Builder().setResult(true).setHighestCommonVersion(maxRpcVersion).build();
        }
        return new RpcVersionsCheckResult.Builder().setResult(false).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class RpcVersionsCheckResult {

        @Nullable
        private final RpcProtocolVersions.Version highestCommonVersion;
        private final boolean result;

        private RpcVersionsCheckResult(Builder builder) {
            this.result = builder.result;
            this.highestCommonVersion = builder.highestCommonVersion;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean getResult() {
            return this.result;
        }

        RpcProtocolVersions.Version getHighestCommonVersion() {
            return this.highestCommonVersion;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Builder {

            @Nullable
            private RpcProtocolVersions.Version highestCommonVersion = null;
            private boolean result;

            Builder() {
            }

            public Builder setResult(boolean z) {
                this.result = z;
                return this;
            }

            public Builder setHighestCommonVersion(RpcProtocolVersions.Version version) {
                this.highestCommonVersion = version;
                return this;
            }

            public RpcVersionsCheckResult build() {
                return new RpcVersionsCheckResult(this);
            }
        }
    }
}
