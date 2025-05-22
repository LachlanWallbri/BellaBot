package io.grpc.alts.internal;

import com.google.common.collect.ImmutableList;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsClientOptions extends AltsHandshakerOptions {

    @Nullable
    private final String targetName;
    private final ImmutableList<String> targetServiceAccounts;

    private AltsClientOptions(Builder builder) {
        super(builder.rpcProtocolVersions);
        this.targetName = builder.targetName;
        this.targetServiceAccounts = builder.targetServiceAccounts;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public ImmutableList<String> getTargetServiceAccounts() {
        return this.targetServiceAccounts;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {

        @Nullable
        private RpcProtocolVersions rpcProtocolVersions;

        @Nullable
        private String targetName;
        private ImmutableList<String> targetServiceAccounts = ImmutableList.m634of();

        public Builder setTargetName(String str) {
            this.targetName = str;
            return this;
        }

        public Builder setRpcProtocolVersions(RpcProtocolVersions rpcProtocolVersions) {
            this.rpcProtocolVersions = rpcProtocolVersions;
            return this;
        }

        public Builder setTargetServiceAccounts(ImmutableList<String> immutableList) {
            this.targetServiceAccounts = immutableList;
            return this;
        }

        public AltsClientOptions build() {
            return new AltsClientOptions(this);
        }
    }
}
