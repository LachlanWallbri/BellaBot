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
import com.slamtec.slamware.robot.PointMap;
import com.slamtec.slamware.robot.PointPDF;
import com.slamtec.slamware.robot.Pose;
import com.slamtec.slamware.robot.PowerStatus;
import com.slamtec.slamware.robot.RecoverLocalizationOptions;
import com.slamtec.slamware.robot.RestartMode;
import com.slamtec.slamware.robot.Rotation;
import com.slamtec.slamware.robot.ScheduledTask;
import com.slamtec.slamware.robot.SendAndRecvUserDefinedCBusMsgResult;
import com.slamtec.slamware.robot.SlamcoreShutdownParam;
import com.slamtec.slamware.sdp.SlamwareSdpPlatform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class SlamwareCorePlatform extends AbstractSlamwarePlatform {
    private SlamwareSdpPlatform platformImp_;

    public static SlamwareCorePlatform connect(String str, int i) {
        SlamwareCorePlatform slamwareCorePlatform = new SlamwareCorePlatform();
        slamwareCorePlatform.platformImp_ = SlamwareSdpPlatform.connect(str, i);
        return slamwareCorePlatform;
    }

    public SlamwareSdpPlatform getPlatformImpl() {
        return this.platformImp_;
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void disconnect() {
        this.platformImp_.disconnect();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getDeviceId() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getDeviceId();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public int getManufacturerId() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getManufacturerId();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getManuFacturerName() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getManufacturerName();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public int getModelId() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getModelId();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getModelName() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getModelName();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getHardwareVersion() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getHardwareVersion();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getSoftwareVersion() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getSoftwareVersion();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    @Deprecated
    public List<ScheduledTask> getScheduledTasks() {
        return this.platformImp_.getScheduledTasks();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    @Deprecated
    public boolean addScheduledTask(ScheduledTask scheduledTask) {
        return this.platformImp_.addScheduledTask(scheduledTask);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    @Deprecated
    public ScheduledTask getScheduledTask(int i) {
        return this.platformImp_.getScheduledTask(i);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    @Deprecated
    public boolean deleteScheduledTask(int i) {
        return this.platformImp_.deleteScheduledTask(i);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    @Deprecated
    public ScheduledTask updateScheduledTask(ScheduledTask scheduledTask) {
        return this.platformImp_.updateScheduledTask(scheduledTask);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public List<MapType> getAvailableMaps() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(MapType.BITMAP_8BIT);
        return arrayList;
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public Map getMap(MapType mapType, MapKind mapKind, RectF rectF) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.getMap(mapType, rectF, mapKind);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void setMap(Map map, MapType mapType, MapKind mapKind) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        if (mapType == MapType.PointMap && mapKind == MapKind.UWB_MAP) {
            this.platformImp_.setPointMap((PointMap) map);
        } else {
            this.platformImp_.setMap(mapType, map.getMapArea(), map.getDimension(), mapKind, map.getData());
        }
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public RectF getKnownArea(MapType mapType, MapKind mapKind) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getKnownArea(mapType, mapKind);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void clearMap() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.clearMap();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public Location getLocation() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getLocation();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public Pose getPose() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getPose();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void setPose(Pose pose) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.setPose(pose);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean getMapLocalization() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getMapLocalization();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void setMapLocalization(boolean z) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.setMapLocalization(z);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean getMapUpdate() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getMapUpdate();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void setMapUpdate(boolean z) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.setMapUpdate(z);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public int getLocalizationQuality() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getLocalizationQuality();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public Pose getHomePose() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getHomePose();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean setHomePose(Pose pose) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.setHomePose(pose);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction moveTo(List<Location> list, boolean z, boolean z2) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        Location[] locationArr = new Location[list.size()];
        Iterator<Location> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            locationArr[i] = it.next();
            i++;
        }
        return this.platformImp_.moveTo(locationArr, z, z2);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction moveTo(Location location, boolean z, boolean z2) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.moveTo(new Location[]{location}, z, z2);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction moveTo(Location location, MoveOption moveOption, float f) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.moveTo(new Location[]{location}, moveOption, f);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction moveTo(List<Location> list, MoveOption moveOption, float f) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        Location[] locationArr = new Location[list.size()];
        Iterator<Location> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            locationArr[i] = it.next();
            i++;
        }
        return this.platformImp_.moveTo(locationArr, moveOption, f);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction moveBy(MoveDirection moveDirection) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.moveBy(moveDirection);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction moveBy(float f, MoveOption moveOption) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.moveBy(f, moveOption);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction rotateTo(Rotation rotation) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.rotateTo(rotation.getYaw(), rotation.getPitch(), rotation.getRoll());
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction rotate(Rotation rotation) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.rotate(rotation.getYaw(), rotation.getPitch(), rotation.getRoll());
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction getCurrentAction() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getCurrentAction();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public Path searchPath(Location location) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.searchPath(location);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public int getBatteryPercentage() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getBatteryPercentage();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean getBatteryIsCharging() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getBatteryIsCharging();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean getDCIsConnected() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getDCIsConnected();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getSlamwareVersion() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getSDPVersion();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getSDKVersion() {
        return this.platformImp_.getSDKVersion();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public LaserScan getLaserScan() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getLaserScan();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public Vector<Line> getWalls() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getWalls();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void addWall(Line line) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.addWall(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(), line.getEndPoint().getY(), line.getSegmentId());
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void addWalls(List<Line> list) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        addLines(ArtifactUsage.ArtifactUsageVirutalWall, list);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void clearWallById(int i) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.removeWallById(i);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void clearWalls() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.clearWalls();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public ISweepMoveAction startSweep() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.startSweep();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public ISweepMoveAction sweepSpot(Location location) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.sweepSpot(location);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction goHome() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.goHome();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void restartModule(RestartMode restartMode) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.restartModule(restartMode);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void setSystemParameter(String str, String str2) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.setSystemParameter(str, str2);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public String getSystemParameter(String str) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getSystemParameter(str);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean updateBinaryConfig(String str) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.updateBinaryConfig(str);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public SendAndRecvUserDefinedCBusMsgResult sendAndRecvUserDefinedCBUSMessage(byte[] bArr) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.sendAndRecvUserDefinedCBUSMessage(bArr);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public FirmwareUpdateInfo getFirmwareUpdateInfo() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getFirmwareUpdateInfo();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public FirmwareUpdateProgress getFirmwareUpdateProgress() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getFirmwareUpdateProgress();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean startFirmwareUpdate() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.startFirmwareUpdate();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public HealthInfo getRobotHealth() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getRobotHealth();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void clearRobotHealth(int i) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        this.platformImp_.clearRobotHealth(i);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public HashMap<String, String> getNetworkStatus() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getNetworkStatus();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean configureNetwork(int i, HashMap<String, String> hashMap) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.configureNetwork(i, hashMap);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public List<Line> getLines(ArtifactUsage artifactUsage) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getLines(getArtifactUsageValue(artifactUsage));
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean addLine(ArtifactUsage artifactUsage, Line line) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.addLine(getArtifactUsageValue(artifactUsage), line);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean addLines(ArtifactUsage artifactUsage, List<Line> list) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.addLines(getArtifactUsageValue(artifactUsage), list);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean removeLineById(ArtifactUsage artifactUsage, int i) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.removeLineById(getArtifactUsageValue(artifactUsage), i);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean clearLines(ArtifactUsage artifactUsage) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.clearLines(getArtifactUsageValue(artifactUsage));
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public IMoveAction recoverLocalization(RectF rectF, RecoverLocalizationOptions recoverLocalizationOptions) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.recoverLocalization(rectF, recoverLocalizationOptions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* renamed from: com.slamtec.slamware.SlamwareCorePlatform$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C57951 {
        static final /* synthetic */ int[] $SwitchMap$com$slamtec$slamware$robot$ArtifactUsage;

        static {
            int[] iArr = new int[ArtifactUsage.values().length];
            $SwitchMap$com$slamtec$slamware$robot$ArtifactUsage = iArr;
            try {
                iArr[ArtifactUsage.ArtifactUsageVirutalWall.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$slamtec$slamware$robot$ArtifactUsage[ArtifactUsage.ArtifactUsageVirtualTrack.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private int getArtifactUsageValue(ArtifactUsage artifactUsage) {
        int i = C57951.$SwitchMap$com$slamtec$slamware$robot$ArtifactUsage[artifactUsage.ordinal()];
        return (i == 1 || i != 2) ? 0 : 1;
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public PowerStatus getPowerStatus() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.getPowerStatus();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void wakeUp() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException {
        this.platformImp_.wakeUp();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public PointPDF getAuxLocation() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.getAuxLocation();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public CompositeMap getCompositeMap() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, OperationFailException {
        return this.platformImp_.getCompositeMap();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void setCompositeMap(CompositeMap compositeMap, Pose pose) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.setCompositeMap(compositeMap, pose);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public List<ImpactSensorInfo> getSensors() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getSensors();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public HashMap<Integer, ImpactSensorValue> getSensorValues() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getSensorValues();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public List<ImpactSensorValue> getSensorValues(List<Integer> list) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getSensorValues(list);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public ImpactSensorValue getSensorValue(Integer num) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getSensorValue(num);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public void publishDepthCamFrame(int i, DepthCameraFrame depthCameraFrame) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        this.platformImp_.publishDepthCamFrame(i, depthCameraFrame);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public List<OperationAuditLog> getOperationAuditLogs() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException {
        return this.platformImp_.getOperationAuditLogs();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public double getOdometry() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException {
        return this.platformImp_.getOdometry();
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public boolean shutdownSlamcore(SlamcoreShutdownParam slamcoreShutdownParam) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.shutdownSlamcore(slamcoreShutdownParam);
    }

    @Override // com.slamtec.slamware.AbstractSlamwarePlatform
    public ICustomerLogReceiver createCustomerLogReceiver() throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException {
        return this.platformImp_.createCustomerLogReceiver();
    }
}
