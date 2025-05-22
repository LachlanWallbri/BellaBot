package com.pudutech.mirsdk.hardware.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: RobotScheduleInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 G2\u00020\u0001:\u0001GB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010B\u001a\u00020\u0007H\u0016J\u0018\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u0007H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001a\u0010'\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001a\u0010*\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0018\"\u0004\b,\u0010\u001aR\u001a\u0010-\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u000f\"\u0004\b/\u0010\u0011R\u001a\u00100\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000f\"\u0004\b2\u0010\u0011R\u001a\u00103\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0018\"\u0004\bA\u0010\u001a¨\u0006H"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "avoid_node_id", "", "getAvoid_node_id", "()I", "setAvoid_node_id", "(I)V", "avoid_robot_id", "", "getAvoid_robot_id", "()Ljava/lang/String;", "setAvoid_robot_id", "(Ljava/lang/String;)V", "avoid_track_id", "getAvoid_track_id", "setAvoid_track_id", "final_goal", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getFinal_goal", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setFinal_goal", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "map_flag", "getMap_flag", "setMap_flag", "msg_id", "", "getMsg_id", "()J", "setMsg_id", "(J)V", "msg_type", "getMsg_type", "setMsg_type", "next_goal", "getNext_goal", "setNext_goal", "pose", "getPose", "setPose", "ref_robot_id", "getRef_robot_id", "setRef_robot_id", "robot_id", "getRobot_id", "setRobot_id", "scheduling_mode", "Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "getScheduling_mode", "()Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "setScheduling_mode", "(Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;)V", "topology_path", "", "getTopology_path", "()[I", "setTopology_path", "([I)V", "virtual_goal", "getVirtual_goal", "setVirtual_goal", "describeContents", "writeToParcel", "", "dest", "flags", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RobotScheduleInfo implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int avoid_node_id;
    private String avoid_robot_id;
    private int avoid_track_id;
    private Vector3d final_goal;
    private String map_flag;
    private long msg_id;
    private String msg_type;
    private Vector3d next_goal;
    private Vector3d pose;
    private String ref_robot_id;
    private String robot_id;
    private SchedulingMode scheduling_mode;
    private int[] topology_path;
    private Vector3d virtual_goal;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final long getMsg_id() {
        return this.msg_id;
    }

    public final void setMsg_id(long j) {
        this.msg_id = j;
    }

    public final String getMsg_type() {
        return this.msg_type;
    }

    public final void setMsg_type(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.msg_type = str;
    }

    public final int getAvoid_node_id() {
        return this.avoid_node_id;
    }

    public final void setAvoid_node_id(int i) {
        this.avoid_node_id = i;
    }

    public final int getAvoid_track_id() {
        return this.avoid_track_id;
    }

    public final void setAvoid_track_id(int i) {
        this.avoid_track_id = i;
    }

    public final SchedulingMode getScheduling_mode() {
        return this.scheduling_mode;
    }

    public final void setScheduling_mode(SchedulingMode schedulingMode) {
        Intrinsics.checkParameterIsNotNull(schedulingMode, "<set-?>");
        this.scheduling_mode = schedulingMode;
    }

    public final Vector3d getNext_goal() {
        return this.next_goal;
    }

    public final void setNext_goal(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.next_goal = vector3d;
    }

    public final Vector3d getFinal_goal() {
        return this.final_goal;
    }

    public final void setFinal_goal(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.final_goal = vector3d;
    }

    public final Vector3d getVirtual_goal() {
        return this.virtual_goal;
    }

    public final void setVirtual_goal(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.virtual_goal = vector3d;
    }

    public final Vector3d getPose() {
        return this.pose;
    }

    public final void setPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.pose = vector3d;
    }

    public final String getRobot_id() {
        return this.robot_id;
    }

    public final void setRobot_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.robot_id = str;
    }

    public final String getAvoid_robot_id() {
        return this.avoid_robot_id;
    }

    public final void setAvoid_robot_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.avoid_robot_id = str;
    }

    public final String getRef_robot_id() {
        return this.ref_robot_id;
    }

    public final void setRef_robot_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ref_robot_id = str;
    }

    public final String getMap_flag() {
        return this.map_flag;
    }

    public final void setMap_flag(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.map_flag = str;
    }

    public final int[] getTopology_path() {
        return this.topology_path;
    }

    public final void setTopology_path(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "<set-?>");
        this.topology_path = iArr;
    }

    public RobotScheduleInfo() {
        this.scheduling_mode = SchedulingMode.Free;
        this.topology_path = new int[]{0};
        this.msg_id = 0L;
        this.msg_type = "";
        this.avoid_node_id = 0;
        this.avoid_track_id = 0;
        this.scheduling_mode = SchedulingMode.Free;
        this.next_goal = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.final_goal = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.virtual_goal = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.pose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.robot_id = "";
        this.avoid_robot_id = "";
        this.ref_robot_id = "";
        this.map_flag = "";
        this.topology_path = new int[]{0};
    }

    public RobotScheduleInfo(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.scheduling_mode = SchedulingMode.Free;
        this.topology_path = new int[]{0};
        this.msg_id = source.readLong();
        String readString = source.readString();
        if (readString == null) {
            Intrinsics.throwNpe();
        }
        this.msg_type = readString;
        this.avoid_node_id = source.readInt();
        this.avoid_track_id = source.readInt();
        Parcelable readParcelable = source.readParcelable(SchedulingMode.class.getClassLoader());
        if (readParcelable == null) {
            Intrinsics.throwNpe();
        }
        this.scheduling_mode = (SchedulingMode) readParcelable;
        Parcelable readParcelable2 = source.readParcelable(Vector3d.class.getClassLoader());
        if (readParcelable2 == null) {
            Intrinsics.throwNpe();
        }
        this.next_goal = (Vector3d) readParcelable2;
        Parcelable readParcelable3 = source.readParcelable(Vector3d.class.getClassLoader());
        if (readParcelable3 == null) {
            Intrinsics.throwNpe();
        }
        this.final_goal = (Vector3d) readParcelable3;
        Parcelable readParcelable4 = source.readParcelable(Vector3d.class.getClassLoader());
        if (readParcelable4 == null) {
            Intrinsics.throwNpe();
        }
        this.virtual_goal = (Vector3d) readParcelable4;
        Parcelable readParcelable5 = source.readParcelable(Vector3d.class.getClassLoader());
        if (readParcelable5 == null) {
            Intrinsics.throwNpe();
        }
        this.pose = (Vector3d) readParcelable5;
        String readString2 = source.readString();
        if (readString2 == null) {
            Intrinsics.throwNpe();
        }
        this.robot_id = readString2;
        String readString3 = source.readString();
        if (readString3 == null) {
            Intrinsics.throwNpe();
        }
        this.avoid_robot_id = readString3;
        String readString4 = source.readString();
        if (readString4 == null) {
            Intrinsics.throwNpe();
        }
        this.ref_robot_id = readString4;
        String readString5 = source.readString();
        if (readString5 == null) {
            Intrinsics.throwNpe();
        }
        this.map_flag = readString5;
        int[] createIntArray = source.createIntArray();
        if (createIntArray == null) {
            Intrinsics.throwNpe();
        }
        this.topology_path = createIntArray;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeLong(this.msg_id);
        dest.writeString(this.msg_type);
        dest.writeInt(this.avoid_node_id);
        dest.writeInt(this.avoid_track_id);
        dest.writeParcelable(this.scheduling_mode, flags);
        dest.writeParcelable(this.next_goal, flags);
        dest.writeParcelable(this.final_goal, flags);
        dest.writeParcelable(this.virtual_goal, flags);
        dest.writeParcelable(this.pose, flags);
        dest.writeString(this.robot_id);
        dest.writeString(this.avoid_robot_id);
        dest.writeString(this.ref_robot_id);
        dest.writeString(this.map_flag);
        dest.writeIntArray(this.topology_path);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: RobotScheduleInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<RobotScheduleInfo> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RobotScheduleInfo createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new RobotScheduleInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RobotScheduleInfo[] newArray(int size) {
            return new RobotScheduleInfo[size];
        }
    }
}
