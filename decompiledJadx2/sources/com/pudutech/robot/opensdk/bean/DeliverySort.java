package com.pudutech.robot.opensdk.bean;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeliveryTaskBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/DeliverySort;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "AUTO", "FIXED", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum DeliverySort {
    AUTO("auto"),
    FIXED("fixed");

    private final String id;

    DeliverySort(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }
}
