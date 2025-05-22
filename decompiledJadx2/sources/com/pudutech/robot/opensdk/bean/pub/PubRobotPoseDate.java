package com.pudutech.robot.opensdk.bean.pub;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubRobotPoseDate.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0014H\u0016J\t\u0010\u001e\u001a\u00020\u0016HÖ\u0001J\u0018\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u0014H\u0016J\t\u0010\"\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006#"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubRobotPoseDate;", "Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "x", "", "y", "angle", "(DDD)V", "getAngle", "()D", "getX", "getY", "component1", "component2", "component3", "copy", "equals", "", "other", "", "getMsgType", "", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubRobotPoseDate extends BaseNotifyPub {
    private final double angle;
    private final double x;
    private final double y;

    public static /* synthetic */ PubRobotPoseDate copy$default(PubRobotPoseDate pubRobotPoseDate, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = pubRobotPoseDate.x;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = pubRobotPoseDate.y;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = pubRobotPoseDate.angle;
        }
        return pubRobotPoseDate.copy(d4, d5, d3);
    }

    /* renamed from: component1, reason: from getter */
    public final double getX() {
        return this.x;
    }

    /* renamed from: component2, reason: from getter */
    public final double getY() {
        return this.y;
    }

    /* renamed from: component3, reason: from getter */
    public final double getAngle() {
        return this.angle;
    }

    public final PubRobotPoseDate copy(double x, double y, double angle) {
        return new PubRobotPoseDate(x, y, angle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubRobotPoseDate)) {
            return false;
        }
        PubRobotPoseDate pubRobotPoseDate = (PubRobotPoseDate) other;
        return Double.compare(this.x, pubRobotPoseDate.x) == 0 && Double.compare(this.y, pubRobotPoseDate.y) == 0 && Double.compare(this.angle, pubRobotPoseDate.angle) == 0;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public int getQos() {
        return 0;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getReplyTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return "";
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public int getRetryCount() {
        return 3;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public long getTimeout() {
        return 5000L;
    }

    public int hashCode() {
        return (((Double.hashCode(this.x) * 31) + Double.hashCode(this.y)) * 31) + Double.hashCode(this.angle);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        return null;
    }

    public String toString() {
        return "PubRobotPoseDate(x=" + this.x + ", y=" + this.y + ", angle=" + this.angle + ")";
    }

    public PubRobotPoseDate(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.angle = d3;
    }

    public final double getAngle() {
        return this.angle;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_NOTIFY_ROBOT_POSE$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.pubSdkTopicTemplate(pk, dn);
    }
}
