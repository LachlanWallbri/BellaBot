package com.pudutech.robot.opensdk.aliyun.bean;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowAuthConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\n\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowAuthConfig;", "", "sdk", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowAuthConfigSdkBean;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getSdk", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowAuthConfig {
    private final ArrayList<ShadowAuthConfigSdkBean> sdk;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ShadowAuthConfig copy$default(ShadowAuthConfig shadowAuthConfig, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = shadowAuthConfig.sdk;
        }
        return shadowAuthConfig.copy(arrayList);
    }

    public final ArrayList<ShadowAuthConfigSdkBean> component1() {
        return this.sdk;
    }

    public final ShadowAuthConfig copy(ArrayList<ShadowAuthConfigSdkBean> sdk) {
        Intrinsics.checkParameterIsNotNull(sdk, "sdk");
        return new ShadowAuthConfig(sdk);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof ShadowAuthConfig) && Intrinsics.areEqual(this.sdk, ((ShadowAuthConfig) other).sdk);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<ShadowAuthConfigSdkBean> arrayList = this.sdk;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ShadowAuthConfig(sdk=" + this.sdk + ")";
    }

    public ShadowAuthConfig(ArrayList<ShadowAuthConfigSdkBean> sdk) {
        Intrinsics.checkParameterIsNotNull(sdk, "sdk");
        this.sdk = sdk;
    }

    public final ArrayList<ShadowAuthConfigSdkBean> getSdk() {
        return this.sdk;
    }
}
