package com.pudutech.schedulerlib;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.schedulerlib.proto.SchedulerInfo;
import com.pudutech.schedulerlib.scheduleInfo.CurrentRobotInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulePackageProcess.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\fJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\fR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/schedulerlib/SchedulePackageProcess;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "closeStream", "", "oStream", "Ljava/io/Closeable;", "compress", "", "bt", "packageScheduleMsg", "robotInfo", "Lcom/pudutech/schedulerlib/scheduleInfo/CurrentRobotInfo;", "unCompress", "unpackageScheduleMsg", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", NotificationCompat.CATEGORY_MESSAGE, "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SchedulePackageProcess {
    public static final SchedulePackageProcess INSTANCE = new SchedulePackageProcess();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private SchedulePackageProcess() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final byte[] packageScheduleMsg(CurrentRobotInfo robotInfo) {
        Intrinsics.checkParameterIsNotNull(robotInfo, "robotInfo");
        SchedulerInfo.RobotScheduleInfo.Builder robotBuilder = SchedulerInfo.RobotScheduleInfo.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(robotBuilder, "robotBuilder");
        robotBuilder.setRobotId(robotInfo.getData().getRobot_id());
        int length = robotInfo.getData().getTopology_path().length;
        int i = length % 2;
        robotBuilder.setTopoOdd(i != 0);
        int length2 = robotInfo.getData().getTopology_path().length;
        for (int i2 = 0; i2 < length2; i2++) {
            if (i2 % 2 == 0 && i2 != length - 1) {
                robotBuilder.addTopologyPath((robotInfo.getData().getTopology_path()[i2] << 16) + robotInfo.getData().getTopology_path()[i2 + 1]);
            } else if (i != 0 && i2 == length - 1) {
                robotBuilder.addTopologyPath(robotInfo.getData().getTopology_path()[i2]);
            }
        }
        robotBuilder.setMsgId(robotInfo.getData().getMsg_id());
        robotBuilder.setMsgType(robotInfo.getData().getMsg_type());
        robotBuilder.setRefRobotId(robotInfo.getData().getRef_robot_id());
        robotBuilder.setAvoidRobotId(robotInfo.getData().getAvoid_robot_id());
        robotBuilder.setAvoidTrackId(robotInfo.getData().getAvoid_track_id());
        robotBuilder.setSchedulingMode(SchedulerInfo.RobotScheduleInfo.SchedulingMode.values()[robotInfo.getData().getScheduling_mode().ordinal()]);
        robotBuilder.setAvoidNodeId(robotInfo.getData().getAvoid_node_id());
        robotBuilder.setNextGoal(SchedulerInfo.Pose2D.newBuilder().setX((float) robotInfo.getData().getNext_goal().getX()).setY((float) robotInfo.getData().getNext_goal().getY()).setYaw((float) robotInfo.getData().getNext_goal().getZ()));
        robotBuilder.setFinalGoal(SchedulerInfo.Pose2D.newBuilder().setX((float) robotInfo.getData().getFinal_goal().getX()).setY((float) robotInfo.getData().getFinal_goal().getY()).setYaw((float) robotInfo.getData().getFinal_goal().getZ()));
        robotBuilder.setVirtualGoal(SchedulerInfo.Pose2D.newBuilder().setX((float) robotInfo.getData().getVirtual_goal().getX()).setY((float) robotInfo.getData().getVirtual_goal().getY()).setYaw((float) robotInfo.getData().getVirtual_goal().getZ()));
        robotBuilder.setPose(SchedulerInfo.Pose2D.newBuilder().setX((float) robotInfo.getData().getPose().getX()).setY((float) robotInfo.getData().getPose().getY()).setYaw((float) robotInfo.getData().getPose().getZ()));
        SchedulerInfo.CurrentRobotInfo.Builder currentInfoBuilder = SchedulerInfo.CurrentRobotInfo.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(currentInfoBuilder, "currentInfoBuilder");
        currentInfoBuilder.setType("robot");
        currentInfoBuilder.setData(robotBuilder);
        SchedulerInfo.ScheduleInfo.Builder ScheduleInfo = SchedulerInfo.ScheduleInfo.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(ScheduleInfo, "ScheduleInfo");
        ScheduleInfo.setMapFlag(robotInfo.getData().getMap_flag());
        ScheduleInfo.setData(currentInfoBuilder);
        byte[] byteArray = ScheduleInfo.build().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "ScheduleInfo.build().toByteArray()");
        return compress(byteArray);
    }

    public final RobotScheduleInfo unpackageScheduleMsg(byte[] msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        try {
            byte[] unCompress = unCompress(msg);
            if (unCompress == null) {
                return null;
            }
            SchedulerInfo.ScheduleInfo.Builder scheduleInfo = SchedulerInfo.ScheduleInfo.newBuilder();
            scheduleInfo.clear();
            scheduleInfo.mergeFrom(unCompress);
            RobotScheduleInfo robotScheduleInfo = new RobotScheduleInfo();
            Intrinsics.checkExpressionValueIsNotNull(scheduleInfo, "scheduleInfo");
            SchedulerInfo.CurrentRobotInfo data = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data2 = data.getData();
            Intrinsics.checkExpressionValueIsNotNull(data2, "scheduleInfo.data.data");
            robotScheduleInfo.setMsg_id(data2.getMsgId());
            SchedulerInfo.CurrentRobotInfo data3 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data3, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data4 = data3.getData();
            Intrinsics.checkExpressionValueIsNotNull(data4, "scheduleInfo.data.data");
            String msgType = data4.getMsgType();
            Intrinsics.checkExpressionValueIsNotNull(msgType, "scheduleInfo.data.data.msgType");
            robotScheduleInfo.setMsg_type(msgType);
            String mapFlag = scheduleInfo.getMapFlag();
            Intrinsics.checkExpressionValueIsNotNull(mapFlag, "scheduleInfo.mapFlag");
            robotScheduleInfo.setMap_flag(mapFlag);
            SchedulerInfo.CurrentRobotInfo data5 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data5, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data6 = data5.getData();
            Intrinsics.checkExpressionValueIsNotNull(data6, "scheduleInfo.data.data");
            String robotId = data6.getRobotId();
            Intrinsics.checkExpressionValueIsNotNull(robotId, "scheduleInfo.data.data.robotId");
            robotScheduleInfo.setRobot_id(robotId);
            SchedulerInfo.CurrentRobotInfo data7 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data7, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data8 = data7.getData();
            Intrinsics.checkExpressionValueIsNotNull(data8, "scheduleInfo.data.data");
            int size = data8.getTopologyPathList().size();
            ArrayList arrayList = new ArrayList();
            SchedulerInfo.CurrentRobotInfo data9 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data9, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data10 = data9.getData();
            Intrinsics.checkExpressionValueIsNotNull(data10, "scheduleInfo.data.data");
            List<Integer> topologyPathList = data10.getTopologyPathList();
            Intrinsics.checkExpressionValueIsNotNull(topologyPathList, "scheduleInfo.data.data.topologyPathList");
            int size2 = topologyPathList.size();
            for (int i = 0; i < size2; i++) {
                if (i < size - 1) {
                    SchedulerInfo.CurrentRobotInfo data11 = scheduleInfo.getData();
                    Intrinsics.checkExpressionValueIsNotNull(data11, "scheduleInfo.data");
                    SchedulerInfo.RobotScheduleInfo data12 = data11.getData();
                    Intrinsics.checkExpressionValueIsNotNull(data12, "scheduleInfo.data.data");
                    arrayList.add(Integer.valueOf(data12.getTopologyPathList().get(i).intValue() >> 16));
                    SchedulerInfo.CurrentRobotInfo data13 = scheduleInfo.getData();
                    Intrinsics.checkExpressionValueIsNotNull(data13, "scheduleInfo.data");
                    SchedulerInfo.RobotScheduleInfo data14 = data13.getData();
                    Intrinsics.checkExpressionValueIsNotNull(data14, "scheduleInfo.data.data");
                    arrayList.add(Integer.valueOf(data14.getTopologyPathList().get(i).intValue() & 65535));
                } else {
                    SchedulerInfo.CurrentRobotInfo data15 = scheduleInfo.getData();
                    Intrinsics.checkExpressionValueIsNotNull(data15, "scheduleInfo.data");
                    SchedulerInfo.RobotScheduleInfo data16 = data15.getData();
                    Intrinsics.checkExpressionValueIsNotNull(data16, "scheduleInfo.data.data");
                    if (data16.getTopoOdd()) {
                        SchedulerInfo.CurrentRobotInfo data17 = scheduleInfo.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data17, "scheduleInfo.data");
                        SchedulerInfo.RobotScheduleInfo data18 = data17.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data18, "scheduleInfo.data.data");
                        arrayList.add(data18.getTopologyPathList().get(i));
                    } else {
                        SchedulerInfo.CurrentRobotInfo data19 = scheduleInfo.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data19, "scheduleInfo.data");
                        SchedulerInfo.RobotScheduleInfo data20 = data19.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data20, "scheduleInfo.data.data");
                        arrayList.add(Integer.valueOf(data20.getTopologyPathList().get(i).intValue() >> 16));
                        SchedulerInfo.CurrentRobotInfo data21 = scheduleInfo.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data21, "scheduleInfo.data");
                        SchedulerInfo.RobotScheduleInfo data22 = data21.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data22, "scheduleInfo.data.data");
                        arrayList.add(Integer.valueOf(data22.getTopologyPathList().get(i).intValue() & 65535));
                    }
                }
            }
            robotScheduleInfo.setTopology_path(CollectionsKt.toIntArray(arrayList));
            SchedulerInfo.CurrentRobotInfo data23 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data23, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data24 = data23.getData();
            Intrinsics.checkExpressionValueIsNotNull(data24, "scheduleInfo.data.data");
            String refRobotId = data24.getRefRobotId();
            Intrinsics.checkExpressionValueIsNotNull(refRobotId, "scheduleInfo.data.data.refRobotId");
            robotScheduleInfo.setRef_robot_id(refRobotId);
            SchedulerInfo.CurrentRobotInfo data25 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data25, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data26 = data25.getData();
            Intrinsics.checkExpressionValueIsNotNull(data26, "scheduleInfo.data.data");
            String avoidRobotId = data26.getAvoidRobotId();
            Intrinsics.checkExpressionValueIsNotNull(avoidRobotId, "scheduleInfo.data.data.avoidRobotId");
            robotScheduleInfo.setAvoid_robot_id(avoidRobotId);
            SchedulingMode[] values = SchedulingMode.values();
            SchedulerInfo.CurrentRobotInfo data27 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data27, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data28 = data27.getData();
            Intrinsics.checkExpressionValueIsNotNull(data28, "scheduleInfo.data.data");
            robotScheduleInfo.setScheduling_mode(values[data28.getSchedulingMode().ordinal()]);
            SchedulerInfo.CurrentRobotInfo data29 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data29, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data30 = data29.getData();
            Intrinsics.checkExpressionValueIsNotNull(data30, "scheduleInfo.data.data");
            robotScheduleInfo.setAvoid_node_id(data30.getAvoidNodeId());
            SchedulerInfo.CurrentRobotInfo data31 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data31, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data32 = data31.getData();
            Intrinsics.checkExpressionValueIsNotNull(data32, "scheduleInfo.data.data");
            robotScheduleInfo.setAvoid_track_id(data32.getAvoidTrackId());
            Vector3d next_goal = robotScheduleInfo.getNext_goal();
            SchedulerInfo.CurrentRobotInfo data33 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data33, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data34 = data33.getData();
            Intrinsics.checkExpressionValueIsNotNull(data34, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D nextGoal = data34.getNextGoal();
            Intrinsics.checkExpressionValueIsNotNull(nextGoal, "scheduleInfo.data.data.nextGoal");
            double x = nextGoal.getX();
            SchedulerInfo.CurrentRobotInfo data35 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data35, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data36 = data35.getData();
            Intrinsics.checkExpressionValueIsNotNull(data36, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D nextGoal2 = data36.getNextGoal();
            Intrinsics.checkExpressionValueIsNotNull(nextGoal2, "scheduleInfo.data.data.nextGoal");
            double y = nextGoal2.getY();
            SchedulerInfo.CurrentRobotInfo data37 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data37, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data38 = data37.getData();
            Intrinsics.checkExpressionValueIsNotNull(data38, "scheduleInfo.data.data");
            Intrinsics.checkExpressionValueIsNotNull(data38.getNextGoal(), "scheduleInfo.data.data.nextGoal");
            next_goal.update(x, y, r11.getYaw());
            Vector3d final_goal = robotScheduleInfo.getFinal_goal();
            SchedulerInfo.CurrentRobotInfo data39 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data39, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data40 = data39.getData();
            Intrinsics.checkExpressionValueIsNotNull(data40, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D finalGoal = data40.getFinalGoal();
            Intrinsics.checkExpressionValueIsNotNull(finalGoal, "scheduleInfo.data.data.finalGoal");
            double x2 = finalGoal.getX();
            SchedulerInfo.CurrentRobotInfo data41 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data41, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data42 = data41.getData();
            Intrinsics.checkExpressionValueIsNotNull(data42, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D finalGoal2 = data42.getFinalGoal();
            Intrinsics.checkExpressionValueIsNotNull(finalGoal2, "scheduleInfo.data.data.finalGoal");
            double y2 = finalGoal2.getY();
            SchedulerInfo.CurrentRobotInfo data43 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data43, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data44 = data43.getData();
            Intrinsics.checkExpressionValueIsNotNull(data44, "scheduleInfo.data.data");
            Intrinsics.checkExpressionValueIsNotNull(data44.getFinalGoal(), "scheduleInfo.data.data.finalGoal");
            final_goal.update(x2, y2, r3.getYaw());
            Vector3d virtual_goal = robotScheduleInfo.getVirtual_goal();
            SchedulerInfo.CurrentRobotInfo data45 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data45, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data46 = data45.getData();
            Intrinsics.checkExpressionValueIsNotNull(data46, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D virtualGoal = data46.getVirtualGoal();
            Intrinsics.checkExpressionValueIsNotNull(virtualGoal, "scheduleInfo.data.data.virtualGoal");
            double x3 = virtualGoal.getX();
            SchedulerInfo.CurrentRobotInfo data47 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data47, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data48 = data47.getData();
            Intrinsics.checkExpressionValueIsNotNull(data48, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D virtualGoal2 = data48.getVirtualGoal();
            Intrinsics.checkExpressionValueIsNotNull(virtualGoal2, "scheduleInfo.data.data.virtualGoal");
            double y3 = virtualGoal2.getY();
            SchedulerInfo.CurrentRobotInfo data49 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data49, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data50 = data49.getData();
            Intrinsics.checkExpressionValueIsNotNull(data50, "scheduleInfo.data.data");
            Intrinsics.checkExpressionValueIsNotNull(data50.getVirtualGoal(), "scheduleInfo.data.data.virtualGoal");
            virtual_goal.update(x3, y3, r6.getYaw());
            Vector3d pose = robotScheduleInfo.getPose();
            SchedulerInfo.CurrentRobotInfo data51 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data51, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data52 = data51.getData();
            Intrinsics.checkExpressionValueIsNotNull(data52, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D pose2 = data52.getPose();
            Intrinsics.checkExpressionValueIsNotNull(pose2, "scheduleInfo.data.data.pose");
            double x4 = pose2.getX();
            SchedulerInfo.CurrentRobotInfo data53 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data53, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data54 = data53.getData();
            Intrinsics.checkExpressionValueIsNotNull(data54, "scheduleInfo.data.data");
            SchedulerInfo.Pose2D pose3 = data54.getPose();
            Intrinsics.checkExpressionValueIsNotNull(pose3, "scheduleInfo.data.data.pose");
            double y4 = pose3.getY();
            SchedulerInfo.CurrentRobotInfo data55 = scheduleInfo.getData();
            Intrinsics.checkExpressionValueIsNotNull(data55, "scheduleInfo.data");
            SchedulerInfo.RobotScheduleInfo data56 = data55.getData();
            Intrinsics.checkExpressionValueIsNotNull(data56, "scheduleInfo.data.data");
            Intrinsics.checkExpressionValueIsNotNull(data56.getPose(), "scheduleInfo.data.data.pose");
            pose.update(x4, y4, r3.getYaw());
            return robotScheduleInfo;
        } catch (Exception e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("unpack msg error: ");
            sb.append(e.getLocalizedMessage());
            sb.append(", ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            sb.append(ArraysKt.contentDeepToString(stackTrace));
            Pdlog.m3277w(str, sb.toString());
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final byte[] compress(byte[] bt) {
        ?? r3;
        GZIPOutputStream gZIPOutputStream;
        Intrinsics.checkParameterIsNotNull(bt, "bt");
        GZIPOutputStream gZIPOutputStream2 = (ByteArrayOutputStream) null;
        Closeable closeable = (GZIPOutputStream) null;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                } catch (Exception e) {
                    e = e;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    gZIPOutputStream.write(bt);
                    gZIPOutputStream2 = gZIPOutputStream;
                    closeStream(gZIPOutputStream2);
                    closeStream(byteArrayOutputStream);
                    r3 = byteArrayOutputStream;
                } catch (Exception e2) {
                    e = e2;
                    closeable = gZIPOutputStream;
                    gZIPOutputStream2 = byteArrayOutputStream;
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("compress exception: ");
                    sb.append(e.getLocalizedMessage());
                    sb.append(": ");
                    StackTraceElement[] stackTrace = e.getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                    sb.append(ArraysKt.contentDeepToString(stackTrace));
                    Pdlog.m3277w(str, sb.toString());
                    closeable = closeable;
                    closeStream(closeable);
                    closeStream(gZIPOutputStream2);
                    r3 = gZIPOutputStream2;
                    if (r3 == 0) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = gZIPOutputStream;
                    gZIPOutputStream2 = byteArrayOutputStream;
                    closeStream(closeable);
                    closeStream(gZIPOutputStream2);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
            if (r3 == 0) {
                return r3.toByteArray();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final byte[] unCompress(byte[] bt) {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        GZIPInputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) null;
        ByteArrayInputStream byteArrayInputStream2 = (ByteArrayInputStream) null;
        GZIPInputStream gZIPInputStream2 = (GZIPInputStream) null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bt);
        } catch (Exception e2) {
            e = e2;
            byteArrayInputStream = byteArrayInputStream2;
        } catch (Throwable th) {
            th = th;
            closeStream(byteArrayOutputStream2);
            closeStream(gZIPInputStream2);
            closeStream(byteArrayInputStream2);
            throw th;
        }
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e3) {
                gZIPInputStream2 = gZIPInputStream;
                byteArrayOutputStream = byteArrayOutputStream2;
                e = e3;
            } catch (Throwable th2) {
                th = th2;
                gZIPInputStream2 = gZIPInputStream;
                byteArrayInputStream2 = byteArrayInputStream;
                closeStream(byteArrayOutputStream2);
                closeStream(gZIPInputStream2);
                closeStream(byteArrayInputStream2);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = byteArrayOutputStream2;
            e = e;
            try {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("uncompress exception: ");
                sb.append(e.getLocalizedMessage());
                sb.append(": ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str, sb.toString());
                closeStream(byteArrayOutputStream);
                closeStream(gZIPInputStream2);
                closeStream(byteArrayInputStream);
                if (byteArrayOutputStream != null) {
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream2 = byteArrayOutputStream;
                byteArrayInputStream2 = byteArrayInputStream;
                closeStream(byteArrayOutputStream2);
                closeStream(gZIPInputStream2);
                closeStream(byteArrayInputStream2);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        try {
            byte[] bArr = new byte[4096];
            for (int read = gZIPInputStream.read(bArr); read > 0; read = gZIPInputStream.read(bArr)) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            closeStream(byteArrayOutputStream);
            closeStream(gZIPInputStream);
        } catch (Exception e5) {
            e = e5;
            gZIPInputStream2 = gZIPInputStream;
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("uncompress exception: ");
            sb2.append(e.getLocalizedMessage());
            sb2.append(": ");
            StackTraceElement[] stackTrace2 = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace2, "e.stackTrace");
            sb2.append(ArraysKt.contentDeepToString(stackTrace2));
            Pdlog.m3277w(str2, sb2.toString());
            closeStream(byteArrayOutputStream);
            closeStream(gZIPInputStream2);
            closeStream(byteArrayInputStream);
            if (byteArrayOutputStream != null) {
            }
        } catch (Throwable th5) {
            th = th5;
            gZIPInputStream2 = gZIPInputStream;
            byteArrayOutputStream2 = byteArrayOutputStream;
            byteArrayInputStream2 = byteArrayInputStream;
            closeStream(byteArrayOutputStream2);
            closeStream(gZIPInputStream2);
            closeStream(byteArrayInputStream2);
            throw th;
        }
        closeStream(byteArrayInputStream);
        if (byteArrayOutputStream != null) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public final void closeStream(Closeable oStream) {
        if (oStream != null) {
            try {
                oStream.close();
            } catch (IOException e) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("close stream: ");
                sb.append(e.getLocalizedMessage());
                sb.append(": ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str, sb.toString());
            }
        }
    }
}
