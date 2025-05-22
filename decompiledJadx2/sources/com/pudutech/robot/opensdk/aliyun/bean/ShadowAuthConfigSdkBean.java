package com.pudutech.robot.opensdk.aliyun.bean;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowAuthConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0006HÆ\u0003J1\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR%\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowAuthConfigSdkBean;", "", "id", "", "listener", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/util/ArrayList;)V", "getId", "()Ljava/lang/String;", "getListener", "()Ljava/util/ArrayList;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowAuthConfigSdkBean {
    private final String id;
    private final ArrayList<String> listener;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ShadowAuthConfigSdkBean copy$default(ShadowAuthConfigSdkBean shadowAuthConfigSdkBean, String str, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = shadowAuthConfigSdkBean.id;
        }
        if ((i & 2) != 0) {
            arrayList = shadowAuthConfigSdkBean.listener;
        }
        return shadowAuthConfigSdkBean.copy(str, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final ArrayList<String> component2() {
        return this.listener;
    }

    public final ShadowAuthConfigSdkBean copy(String id, ArrayList<String> listener) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new ShadowAuthConfigSdkBean(id, listener);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowAuthConfigSdkBean)) {
            return false;
        }
        ShadowAuthConfigSdkBean shadowAuthConfigSdkBean = (ShadowAuthConfigSdkBean) other;
        return Intrinsics.areEqual(this.id, shadowAuthConfigSdkBean.id) && Intrinsics.areEqual(this.listener, shadowAuthConfigSdkBean.listener);
    }

    public int hashCode() {
        String str = this.id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ArrayList<String> arrayList = this.listener;
        return hashCode + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "ShadowAuthConfigSdkBean(id=" + this.id + ", listener=" + this.listener + ")";
    }

    public ShadowAuthConfigSdkBean(String id, ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.id = id;
        this.listener = arrayList;
    }

    public final String getId() {
        return this.id;
    }

    public final ArrayList<String> getListener() {
        return this.listener;
    }
}
