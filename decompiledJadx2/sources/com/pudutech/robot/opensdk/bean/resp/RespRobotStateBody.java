package com.pudutech.robot.opensdk.bean.resp;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RespRobotStateBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003JA\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020\u0007HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006'"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/resp/RespRobotStateBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "robotState", "", "moveState", "chargeStage", "robotPower", "", "robotPose", "Lcom/pudutech/robot/opensdk/bean/resp/RobotPose;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/pudutech/robot/opensdk/bean/resp/RobotPose;)V", "getChargeStage", "()Ljava/lang/String;", "setChargeStage", "(Ljava/lang/String;)V", "getMoveState", "setMoveState", "getRobotPose", "()Lcom/pudutech/robot/opensdk/bean/resp/RobotPose;", "setRobotPose", "(Lcom/pudutech/robot/opensdk/bean/resp/RobotPose;)V", "getRobotPower", "()I", "setRobotPower", "(I)V", "getRobotState", "setRobotState", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RespRobotStateBody implements IBody {
    private String chargeStage;
    private String moveState;
    private RobotPose robotPose;
    private int robotPower;
    private String robotState;

    public static /* synthetic */ RespRobotStateBody copy$default(RespRobotStateBody respRobotStateBody, String str, String str2, String str3, int i, RobotPose robotPose, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = respRobotStateBody.robotState;
        }
        if ((i2 & 2) != 0) {
            str2 = respRobotStateBody.moveState;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            str3 = respRobotStateBody.chargeStage;
        }
        String str5 = str3;
        if ((i2 & 8) != 0) {
            i = respRobotStateBody.robotPower;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            robotPose = respRobotStateBody.robotPose;
        }
        return respRobotStateBody.copy(str, str4, str5, i3, robotPose);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRobotState() {
        return this.robotState;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMoveState() {
        return this.moveState;
    }

    /* renamed from: component3, reason: from getter */
    public final String getChargeStage() {
        return this.chargeStage;
    }

    /* renamed from: component4, reason: from getter */
    public final int getRobotPower() {
        return this.robotPower;
    }

    /* renamed from: component5, reason: from getter */
    public final RobotPose getRobotPose() {
        return this.robotPose;
    }

    public final RespRobotStateBody copy(String robotState, String moveState, String chargeStage, int robotPower, RobotPose robotPose) {
        Intrinsics.checkParameterIsNotNull(robotState, "robotState");
        return new RespRobotStateBody(robotState, moveState, chargeStage, robotPower, robotPose);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RespRobotStateBody)) {
            return false;
        }
        RespRobotStateBody respRobotStateBody = (RespRobotStateBody) other;
        return Intrinsics.areEqual(this.robotState, respRobotStateBody.robotState) && Intrinsics.areEqual(this.moveState, respRobotStateBody.moveState) && Intrinsics.areEqual(this.chargeStage, respRobotStateBody.chargeStage) && this.robotPower == respRobotStateBody.robotPower && Intrinsics.areEqual(this.robotPose, respRobotStateBody.robotPose);
    }

    public int hashCode() {
        String str = this.robotState;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.moveState;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.chargeStage;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.hashCode(this.robotPower)) * 31;
        RobotPose robotPose = this.robotPose;
        return hashCode3 + (robotPose != null ? robotPose.hashCode() : 0);
    }

    public String toString() {
        return "RespRobotStateBody(robotState=" + this.robotState + ", moveState=" + this.moveState + ", chargeStage=" + this.chargeStage + ", robotPower=" + this.robotPower + ", robotPose=" + this.robotPose + ")";
    }

    public RespRobotStateBody(String robotState, String str, String str2, int i, RobotPose robotPose) {
        Intrinsics.checkParameterIsNotNull(robotState, "robotState");
        this.robotState = robotState;
        this.moveState = str;
        this.chargeStage = str2;
        this.robotPower = i;
        this.robotPose = robotPose;
    }

    public final String getRobotState() {
        return this.robotState;
    }

    public final void setRobotState(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.robotState = str;
    }

    public /* synthetic */ RespRobotStateBody(String str, String str2, String str3, int i, RobotPose robotPose, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? (String) null : str2, (i2 & 4) != 0 ? (String) null : str3, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? (RobotPose) null : robotPose);
    }

    public final String getMoveState() {
        return this.moveState;
    }

    public final void setMoveState(String str) {
        this.moveState = str;
    }

    public final String getChargeStage() {
        return this.chargeStage;
    }

    public final void setChargeStage(String str) {
        this.chargeStage = str;
    }

    public final int getRobotPower() {
        return this.robotPower;
    }

    public final void setRobotPower(int i) {
        this.robotPower = i;
    }

    public final RobotPose getRobotPose() {
        return this.robotPose;
    }

    public final void setRobotPose(RobotPose robotPose) {
        this.robotPose = robotPose;
    }
}
