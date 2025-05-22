package io.grpc.alts;

import io.grpc.alts.internal.AltsInternalContext;
import io.grpc.alts.internal.HandshakerResult;
import io.grpc.alts.internal.Identity;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsContext {
    private final AltsInternalContext wrapped;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum SecurityLevel {
        UNKNOWN,
        SECURITY_NONE,
        INTEGRITY_ONLY,
        INTEGRITY_AND_PRIVACY
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AltsContext(AltsInternalContext altsInternalContext) {
        this.wrapped = altsInternalContext;
    }

    public static AltsContext createTestInstance(String str, String str2) {
        return new AltsContext(new AltsInternalContext(HandshakerResult.newBuilder().setPeerIdentity(Identity.newBuilder().setServiceAccount(str).build()).setLocalIdentity(Identity.newBuilder().setServiceAccount(str2).build()).build()));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.alts.AltsContext$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C60001 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$alts$internal$SecurityLevel = new int[io.grpc.alts.internal.SecurityLevel.values().length];

        static {
            try {
                $SwitchMap$io$grpc$alts$internal$SecurityLevel[io.grpc.alts.internal.SecurityLevel.SECURITY_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$SecurityLevel[io.grpc.alts.internal.SecurityLevel.INTEGRITY_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$SecurityLevel[io.grpc.alts.internal.SecurityLevel.INTEGRITY_AND_PRIVACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SecurityLevel getSecurityLevel() {
        int i = C60001.$SwitchMap$io$grpc$alts$internal$SecurityLevel[this.wrapped.getSecurityLevel().ordinal()];
        if (i == 1) {
            return SecurityLevel.SECURITY_NONE;
        }
        if (i == 2) {
            return SecurityLevel.INTEGRITY_ONLY;
        }
        if (i == 3) {
            return SecurityLevel.INTEGRITY_AND_PRIVACY;
        }
        return SecurityLevel.UNKNOWN;
    }

    public String getPeerServiceAccount() {
        return this.wrapped.getPeerServiceAccount();
    }

    public String getLocalServiceAccount() {
        return this.wrapped.getLocalServiceAccount();
    }
}
