package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CustomCallContentBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/CustomCallContentBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", CMSAttributeTableGenerator.CONTENT_TYPE, "", "contentData", "Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;", "taskId", "(Ljava/lang/String;Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;Ljava/lang/String;)V", "getContentData", "()Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;", "setContentData", "(Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;)V", "getContentType", "()Ljava/lang/String;", "setContentType", "(Ljava/lang/String;)V", "getTaskId", "setTaskId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class CustomCallContentBody implements IBody {
    private CustomCallContentData contentData;
    private String contentType;
    private String taskId;

    public static /* synthetic */ CustomCallContentBody copy$default(CustomCallContentBody customCallContentBody, String str, CustomCallContentData customCallContentData, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customCallContentBody.contentType;
        }
        if ((i & 2) != 0) {
            customCallContentData = customCallContentBody.contentData;
        }
        if ((i & 4) != 0) {
            str2 = customCallContentBody.taskId;
        }
        return customCallContentBody.copy(str, customCallContentData, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContentType() {
        return this.contentType;
    }

    /* renamed from: component2, reason: from getter */
    public final CustomCallContentData getContentData() {
        return this.contentData;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTaskId() {
        return this.taskId;
    }

    public final CustomCallContentBody copy(String contentType, CustomCallContentData contentData, String taskId) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        return new CustomCallContentBody(contentType, contentData, taskId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallContentBody)) {
            return false;
        }
        CustomCallContentBody customCallContentBody = (CustomCallContentBody) other;
        return Intrinsics.areEqual(this.contentType, customCallContentBody.contentType) && Intrinsics.areEqual(this.contentData, customCallContentBody.contentData) && Intrinsics.areEqual(this.taskId, customCallContentBody.taskId);
    }

    public int hashCode() {
        String str = this.contentType;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        CustomCallContentData customCallContentData = this.contentData;
        int hashCode2 = (hashCode + (customCallContentData != null ? customCallContentData.hashCode() : 0)) * 31;
        String str2 = this.taskId;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallContentBody(contentType=" + this.contentType + ", contentData=" + this.contentData + ", taskId=" + this.taskId + ")";
    }

    public CustomCallContentBody(String contentType, CustomCallContentData customCallContentData, String str) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.contentType = contentType;
        this.contentData = customCallContentData;
        this.taskId = str;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final void setContentType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.contentType = str;
    }

    public final CustomCallContentData getContentData() {
        return this.contentData;
    }

    public final void setContentData(CustomCallContentData customCallContentData) {
        this.contentData = customCallContentData;
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public final void setTaskId(String str) {
        this.taskId = str;
    }
}
