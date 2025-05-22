package com.pudutech.pd_network.bean;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: storage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\f\r\u000e\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageIsp;", "", "isp", "", "(Ljava/lang/String;)V", "getIsp", "()Ljava/lang/String;", "COS", "Companion", OSSConfig.TAG, "PD", "S3", "Lcom/pudutech/pd_network/bean/StorageIsp$OSS;", "Lcom/pudutech/pd_network/bean/StorageIsp$COS;", "Lcom/pudutech/pd_network/bean/StorageIsp$S3;", "Lcom/pudutech/pd_network/bean/StorageIsp$PD;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class StorageIsp {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String isp;

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageIsp$OSS;", "Lcom/pudutech/pd_network/bean/StorageIsp;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class OSS extends StorageIsp {
        public static final OSS INSTANCE = new OSS();

        private OSS() {
            super("ali", null);
        }
    }

    private StorageIsp(String str) {
        this.isp = str;
    }

    public /* synthetic */ StorageIsp(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final String getIsp() {
        return this.isp;
    }

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageIsp$COS;", "Lcom/pudutech/pd_network/bean/StorageIsp;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class COS extends StorageIsp {
        public static final COS INSTANCE = new COS();

        private COS() {
            super("tx", null);
        }
    }

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageIsp$S3;", "Lcom/pudutech/pd_network/bean/StorageIsp;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.pd_network.bean.StorageIsp$S3 */
    /* loaded from: classes6.dex */
    public static final class C5473S3 extends StorageIsp {
        public static final C5473S3 INSTANCE = new C5473S3();

        private C5473S3() {
            super("aws", null);
        }
    }

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageIsp$PD;", "Lcom/pudutech/pd_network/bean/StorageIsp;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.pd_network.bean.StorageIsp$PD */
    /* loaded from: classes6.dex */
    public static final class C5472PD extends StorageIsp {
        public static final C5472PD INSTANCE = new C5472PD();

        private C5472PD() {
            super("pd", null);
        }
    }

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageIsp$Companion;", "", "()V", "fromIsp", "Lcom/pudutech/pd_network/bean/StorageIsp;", "isp", "", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final StorageIsp fromIsp(String isp) {
            Intrinsics.checkParameterIsNotNull(isp, "isp");
            if (Intrinsics.areEqual(isp, OSS.INSTANCE.getIsp())) {
                return OSS.INSTANCE;
            }
            if (Intrinsics.areEqual(isp, COS.INSTANCE.getIsp())) {
                return COS.INSTANCE;
            }
            if (Intrinsics.areEqual(isp, C5473S3.INSTANCE.getIsp())) {
                return C5473S3.INSTANCE;
            }
            if (Intrinsics.areEqual(isp, C5472PD.INSTANCE.getIsp())) {
                return C5472PD.INSTANCE;
            }
            throw new Exception("不支持的ISP : " + isp);
        }
    }
}
