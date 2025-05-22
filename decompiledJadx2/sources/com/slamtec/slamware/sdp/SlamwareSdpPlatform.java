package com.slamtec.slamware.sdp;

import android.graphics.RectF;
import com.slamtec.slamware.FirmwareUpdate.FirmwareUpdateInfo;
import com.slamtec.slamware.FirmwareUpdate.FirmwareUpdateProgress;
import com.slamtec.slamware.action.IMoveAction;
import com.slamtec.slamware.action.ISweepMoveAction;
import com.slamtec.slamware.action.MoveDirection;
import com.slamtec.slamware.action.Path;
import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionLostException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.InvalidArgumentException;
import com.slamtec.slamware.exceptions.OperationFailException;
import com.slamtec.slamware.exceptions.ParseInvalidException;
import com.slamtec.slamware.exceptions.RequestFailException;
import com.slamtec.slamware.exceptions.UnauthorizedRequestException;
import com.slamtec.slamware.exceptions.UnsupportedCommandException;
import com.slamtec.slamware.geometry.Line;
import com.slamtec.slamware.geometry.Size;
import com.slamtec.slamware.log.customer.ICustomerLogReceiver;
import com.slamtec.slamware.message.DepthCameraFrame;
import com.slamtec.slamware.robot.CompositeMap;
import com.slamtec.slamware.robot.HealthInfo;
import com.slamtec.slamware.robot.ImpactSensorInfo;
import com.slamtec.slamware.robot.ImpactSensorValue;
import com.slamtec.slamware.robot.LaserScan;
import com.slamtec.slamware.robot.Location;
import com.slamtec.slamware.robot.MapKind;
import com.slamtec.slamware.robot.MapType;
import com.slamtec.slamware.robot.MoveOption;
import com.slamtec.slamware.robot.OperationAuditLog;
import com.slamtec.slamware.robot.PointMap;
import com.slamtec.slamware.robot.PointPDF;
import com.slamtec.slamware.robot.Pose;
import com.slamtec.slamware.robot.PowerStatus;
import com.slamtec.slamware.robot.RecoverLocalizationOptions;
import com.slamtec.slamware.robot.RestartMode;
import com.slamtec.slamware.robot.ScheduledTask;
import com.slamtec.slamware.robot.SendAndRecvUserDefinedCBusMsgResult;
import com.slamtec.slamware.robot.SlamcoreShutdownParam;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class SlamwareSdpPlatform {
    private long _cPointer = 0;

    public native boolean addLine(int i, Line line) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native boolean addLines(int i, List<Line> list) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    @Deprecated
    public native boolean addScheduledTask(ScheduledTask scheduledTask);

    public native boolean addWall(float f, float f2, float f3, float f4, int i) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native boolean clearLines(int i) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void clearMap() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void clearRobotHealth(int i) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native boolean clearWalls() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native boolean configureNetwork(int i, HashMap<String, String> hashMap) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native ICustomerLogReceiver createCustomerLogReceiver() throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    @Deprecated
    public native boolean deleteScheduledTask(int i);

    public native void disconnect();

    public native PointPDF getAuxLocation() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native MapType getAvailableMaps();

    public native boolean getBatteryIsCharging() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native int getBatteryPercentage() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native CompositeMap getCompositeMap() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native IMoveAction getCurrentAction() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native boolean getDCIsConnected() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native String getDeviceId() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native FirmwareUpdateInfo getFirmwareUpdateInfo() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native FirmwareUpdateProgress getFirmwareUpdateProgress() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native String getHardwareVersion() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native Pose getHomePose() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native RectF getKnownArea(MapType mapType, MapKind mapKind) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native LaserScan getLaserScan() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native List<Line> getLines(int i) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native int getLocalizationQuality() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native Location getLocation() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native int getManufacturerId() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native String getManufacturerName() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native com.slamtec.slamware.robot.Map getMap(MapType mapType, RectF rectF, MapKind mapKind) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, InvalidArgumentException, OperationFailException;

    public native boolean getMapLocalization() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native boolean getMapUpdate() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native int getModelId() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native String getModelName() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native HashMap<String, String> getNetworkStatus() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native double getOdometry() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    public native List<OperationAuditLog> getOperationAuditLogs() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public native Pose getPose() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native PowerStatus getPowerStatus() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native HealthInfo getRobotHealth() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native String getSDKVersion();

    public native String getSDPVersion() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    @Deprecated
    public native ScheduledTask getScheduledTask(int i);

    @Deprecated
    public native List<ScheduledTask> getScheduledTasks();

    public native ImpactSensorValue getSensorValue(Integer num) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native HashMap<Integer, ImpactSensorValue> getSensorValues() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native List<ImpactSensorValue> getSensorValues(List<Integer> list) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native List<ImpactSensorInfo> getSensors() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native String getSoftwareVersion() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native String getSystemParameter(String str) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native Vector<Line> getWalls() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native IMoveAction goHome() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native IMoveAction moveBy(float f, MoveOption moveOption) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native IMoveAction moveBy(MoveDirection moveDirection) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native IMoveAction moveTo(Location[] locationArr, MoveOption moveOption, float f) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native IMoveAction moveTo(Location[] locationArr, boolean z, boolean z2) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native void nativeConnect(String str, int i);

    public native void publishDepthCamFrame(int i, DepthCameraFrame depthCameraFrame) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native IMoveAction recoverLocalization(RectF rectF, RecoverLocalizationOptions recoverLocalizationOptions) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native void releaseCPointer();

    public native boolean removeLineById(int i, int i2) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native boolean removeWallById(int i) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void restartModule(RestartMode restartMode) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native IMoveAction rotate(float f, float f2, float f3) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native IMoveAction rotateTo(float f, float f2, float f3) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native Path searchPath(Location location) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native SendAndRecvUserDefinedCBusMsgResult sendAndRecvUserDefinedCBUSMessage(byte[] bArr) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public native void setCompositeMap(CompositeMap compositeMap, Pose pose) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native boolean setHomePose(Pose pose) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException;

    public native void setMap(MapType mapType, RectF rectF, Size size, MapKind mapKind, byte[] bArr) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void setMapLocalization(boolean z) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void setMapUpdate(boolean z) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void setPointMap(PointMap pointMap) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void setPose(Pose pose) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native void setSystemParameter(String str, String str2) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native boolean shutdownSlamcore(SlamcoreShutdownParam slamcoreShutdownParam) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public native boolean startFirmwareUpdate() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    public native ISweepMoveAction startSweep() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native ISweepMoveAction sweepSpot(Location location) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public native boolean updateBinaryConfig(String str) throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException, ParseInvalidException;

    @Deprecated
    public native ScheduledTask updateScheduledTask(ScheduledTask scheduledTask);

    public native void wakeUp() throws RequestFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ConnectionFailException;

    static {
        System.loadLibrary("rpsdk");
    }

    public static SlamwareSdpPlatform connect(String str, int i) {
        SlamwareSdpPlatform slamwareSdpPlatform = new SlamwareSdpPlatform();
        slamwareSdpPlatform.nativeConnect(str, i);
        return slamwareSdpPlatform;
    }

    protected void finalize() {
        releaseCPointer();
    }
}
