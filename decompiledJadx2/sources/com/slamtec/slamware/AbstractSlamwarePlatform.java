package com.slamtec.slamware;

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
import com.slamtec.slamware.log.customer.ICustomerLogReceiver;
import com.slamtec.slamware.message.DepthCameraFrame;
import com.slamtec.slamware.robot.ArtifactUsage;
import com.slamtec.slamware.robot.CompositeMap;
import com.slamtec.slamware.robot.HealthInfo;
import com.slamtec.slamware.robot.ImpactSensorInfo;
import com.slamtec.slamware.robot.ImpactSensorValue;
import com.slamtec.slamware.robot.LaserScan;
import com.slamtec.slamware.robot.Location;
import com.slamtec.slamware.robot.Map;
import com.slamtec.slamware.robot.MapKind;
import com.slamtec.slamware.robot.MapType;
import com.slamtec.slamware.robot.MoveOption;
import com.slamtec.slamware.robot.OperationAuditLog;
import com.slamtec.slamware.robot.PointPDF;
import com.slamtec.slamware.robot.Pose;
import com.slamtec.slamware.robot.PowerStatus;
import com.slamtec.slamware.robot.RecoverLocalizationOptions;
import com.slamtec.slamware.robot.RestartMode;
import com.slamtec.slamware.robot.Rotation;
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
public abstract class AbstractSlamwarePlatform {
    public abstract boolean addLine(ArtifactUsage artifactUsage, Line line) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract boolean addLines(ArtifactUsage artifactUsage, List<Line> list) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    @Deprecated
    public abstract boolean addScheduledTask(ScheduledTask scheduledTask);

    public abstract void addWall(Line line) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void addWalls(List<Line> list) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract boolean clearLines(ArtifactUsage artifactUsage) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void clearMap() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void clearRobotHealth(int i) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract void clearWallById(int i) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void clearWalls() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract boolean configureNetwork(int i, HashMap<String, String> hashMap) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract ICustomerLogReceiver createCustomerLogReceiver() throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    @Deprecated
    public abstract boolean deleteScheduledTask(int i);

    public abstract void disconnect();

    public abstract PointPDF getAuxLocation() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract List<MapType> getAvailableMaps();

    public abstract boolean getBatteryIsCharging() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract int getBatteryPercentage() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract CompositeMap getCompositeMap() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract IMoveAction getCurrentAction() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract boolean getDCIsConnected() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract String getDeviceId() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract FirmwareUpdateInfo getFirmwareUpdateInfo() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract FirmwareUpdateProgress getFirmwareUpdateProgress() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract String getHardwareVersion() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract Pose getHomePose() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract RectF getKnownArea(MapType mapType, MapKind mapKind) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract LaserScan getLaserScan() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract List<Line> getLines(ArtifactUsage artifactUsage) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract int getLocalizationQuality() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract Location getLocation() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract String getManuFacturerName() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract int getManufacturerId() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract Map getMap(MapType mapType, MapKind mapKind, RectF rectF) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, InvalidArgumentException, OperationFailException;

    public abstract boolean getMapLocalization() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract boolean getMapUpdate() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract int getModelId() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract String getModelName() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract HashMap<String, String> getNetworkStatus() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract double getOdometry() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    public abstract List<OperationAuditLog> getOperationAuditLogs() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract Pose getPose() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract PowerStatus getPowerStatus() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract HealthInfo getRobotHealth() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract String getSDKVersion();

    @Deprecated
    public abstract ScheduledTask getScheduledTask(int i);

    @Deprecated
    public abstract List<ScheduledTask> getScheduledTasks();

    public abstract ImpactSensorValue getSensorValue(Integer num) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract HashMap<Integer, ImpactSensorValue> getSensorValues() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract List<ImpactSensorValue> getSensorValues(List<Integer> list) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract List<ImpactSensorInfo> getSensors() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract String getSlamwareVersion() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract String getSoftwareVersion() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract String getSystemParameter(String str) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract Vector<Line> getWalls() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract IMoveAction goHome() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract IMoveAction moveBy(float f, MoveOption moveOption) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract IMoveAction moveBy(MoveDirection moveDirection) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract IMoveAction moveTo(Location location, MoveOption moveOption, float f) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    @Deprecated
    public abstract IMoveAction moveTo(Location location, boolean z, boolean z2) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract IMoveAction moveTo(List<Location> list, MoveOption moveOption, float f) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    @Deprecated
    public abstract IMoveAction moveTo(List<Location> list, boolean z, boolean z2) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract void publishDepthCamFrame(int i, DepthCameraFrame depthCameraFrame) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract IMoveAction recoverLocalization(RectF rectF, RecoverLocalizationOptions recoverLocalizationOptions) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract boolean removeLineById(ArtifactUsage artifactUsage, int i) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void restartModule(RestartMode restartMode) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract IMoveAction rotate(Rotation rotation) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract IMoveAction rotateTo(Rotation rotation) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract Path searchPath(Location location) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract SendAndRecvUserDefinedCBusMsgResult sendAndRecvUserDefinedCBUSMessage(byte[] bArr) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract void setCompositeMap(CompositeMap compositeMap, Pose pose) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract boolean setHomePose(Pose pose) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract void setMap(Map map, MapType mapType, MapKind mapKind) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void setMapLocalization(boolean z) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void setMapUpdate(boolean z) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void setPose(Pose pose) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract void setSystemParameter(String str, String str2) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract boolean shutdownSlamcore(SlamcoreShutdownParam slamcoreShutdownParam) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    public abstract boolean startFirmwareUpdate() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    public abstract ISweepMoveAction startSweep() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract ISweepMoveAction sweepSpot(Location location) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException;

    public abstract boolean updateBinaryConfig(String str) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException;

    @Deprecated
    public abstract ScheduledTask updateScheduledTask(ScheduledTask scheduledTask);

    public abstract void wakeUp() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    public Map getMap(MapType mapType, RectF rectF) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, InvalidArgumentException, OperationFailException {
        return getMap(mapType, MapKind.EXPLORE_MAP, rectF);
    }

    public void setMap(Map map) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        setMap(map, MapType.BITMAP_8BIT);
    }

    public void setMap(Map map, MapType mapType) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        setMap(map, mapType, MapKind.EXPLORE_MAP);
    }

    public RectF getKnownArea(MapType mapType) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return getKnownArea(mapType, MapKind.EXPLORE_MAP);
    }

    @Deprecated
    public IMoveAction moveTo(List<Location> list) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return moveTo(list, false);
    }

    @Deprecated
    public IMoveAction moveTo(List<Location> list, boolean z) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return moveTo(list, z, true);
    }

    @Deprecated
    public IMoveAction moveTo(Location location) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return moveTo(location, false);
    }

    @Deprecated
    public IMoveAction moveTo(Location location, boolean z) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return moveTo(location, z, true);
    }

    public void restartModule() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        restartModule(RestartMode.SOFT);
    }
}
