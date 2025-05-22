package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CustomCallBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006 "}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/CustomCallBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "destination", "Lcom/pudutech/robot/opensdk/bean/Destination;", CMSAttributeTableGenerator.CONTENT_TYPE, "", "contentData", "Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;", "(Lcom/pudutech/robot/opensdk/bean/Destination;Ljava/lang/String;Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;)V", "getContentData", "()Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;", "setContentData", "(Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;)V", "getContentType", "()Ljava/lang/String;", "setContentType", "(Ljava/lang/String;)V", "getDestination", "()Lcom/pudutech/robot/opensdk/bean/Destination;", "setDestination", "(Lcom/pudutech/robot/opensdk/bean/Destination;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class CustomCallBody implements IBody {
    private CustomCallContentData contentData;
    private String contentType;
    private Destination destination;

    public static /* synthetic */ CustomCallBody copy$default(CustomCallBody customCallBody, Destination destination, String str, CustomCallContentData customCallContentData, int i, Object obj) {
        if ((i & 1) != 0) {
            destination = customCallBody.destination;
        }
        if ((i & 2) != 0) {
            str = customCallBody.contentType;
        }
        if ((i & 4) != 0) {
            customCallContentData = customCallBody.contentData;
        }
        return customCallBody.copy(destination, str, customCallContentData);
    }

    /* renamed from: component1, reason: from getter */
    public final Destination getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContentType() {
        return this.contentType;
    }

    /* renamed from: component3, reason: from getter */
    public final CustomCallContentData getContentData() {
        return this.contentData;
    }

    public final CustomCallBody copy(Destination destination, String contentType, CustomCallContentData contentData) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        return new CustomCallBody(destination, contentType, contentData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallBody)) {
            return false;
        }
        CustomCallBody customCallBody = (CustomCallBody) other;
        return Intrinsics.areEqual(this.destination, customCallBody.destination) && Intrinsics.areEqual(this.contentType, customCallBody.contentType) && Intrinsics.areEqual(this.contentData, customCallBody.contentData);
    }

    public int hashCode() {
        Destination destination = this.destination;
        int hashCode = (destination != null ? destination.hashCode() : 0) * 31;
        String str = this.contentType;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        CustomCallContentData customCallContentData = this.contentData;
        return hashCode2 + (customCallContentData != null ? customCallContentData.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallBody(destination=" + this.destination + ", contentType=" + this.contentType + ", contentData=" + this.contentData + ")";
    }

    public CustomCallBody(Destination destination, String contentType, CustomCallContentData customCallContentData) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.destination = destination;
        this.contentType = contentType;
        this.contentData = customCallContentData;
    }

    public final Destination getDestination() {
        return this.destination;
    }

    public final void setDestination(Destination destination) {
        Intrinsics.checkParameterIsNotNull(destination, "<set-?>");
        this.destination = destination;
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
}
