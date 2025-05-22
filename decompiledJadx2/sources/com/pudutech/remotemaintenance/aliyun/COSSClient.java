package com.pudutech.remotemaintenance.aliyun;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.App;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: COSSClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\b\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/COSSClient;", "", "()V", "credentialProvider", "Lcom/alibaba/sdk/android/oss/common/auth/OSSCredentialProvider;", "ossClient", "Lcom/alibaba/sdk/android/oss/OSSClient;", "getOSSClient", "init", "", "updateCredentialProvider", "Companion", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class COSSClient {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<COSSClient>() { // from class: com.pudutech.remotemaintenance.aliyun.COSSClient$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final COSSClient invoke() {
            return new COSSClient(null);
        }
    });
    private OSSCredentialProvider credentialProvider;
    private OSSClient ossClient;

    private COSSClient() {
    }

    public /* synthetic */ COSSClient(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: COSSClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/COSSClient$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/remotemaintenance/aliyun/COSSClient;", "getINSTANCE", "()Lcom/pudutech/remotemaintenance/aliyun/COSSClient;", "INSTANCE$delegate", "Lkotlin/Lazy;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        public final COSSClient getINSTANCE() {
            Lazy lazy = COSSClient.INSTANCE$delegate;
            Companion companion = COSSClient.INSTANCE;
            return (COSSClient) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void init() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(15000);
        clientConfiguration.setSocketTimeout(15000);
        clientConfiguration.setMaxConcurrentRequest(5);
        clientConfiguration.setMaxErrorRetry(2);
        Pdlog.m3273d(OSSConfig.TAG, "ENDPOINT--" + OSSConfig.INSTANCE.getENDPOINT());
        this.ossClient = new OSSClient(App.INSTANCE.getInstance(), OSSConfig.INSTANCE.getENDPOINT(), this.credentialProvider, clientConfiguration);
        OSSLog.enableLog();
    }

    public final void updateCredentialProvider(OSSCredentialProvider credentialProvider) {
        Intrinsics.checkParameterIsNotNull(credentialProvider, "credentialProvider");
        this.credentialProvider = credentialProvider;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new COSSClient$updateCredentialProvider$1(this, credentialProvider, null), 3, null);
    }

    public final OSSClient getOSSClient() {
        if (this.ossClient == null) {
            init();
        }
        OSSClient oSSClient = this.ossClient;
        if (oSSClient == null) {
            Intrinsics.throwNpe();
        }
        return oSSClient;
    }
}
