package com.pudutech.mirsdk.config;

import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapFilePathConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\"\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000206X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000206X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006X"}, m3961d2 = {"Lcom/pudutech/mirsdk/config/MapFilePathConfig;", "", "()V", "ALTAS_DATA", "", "AUTOEXP_SAMPLE_IMAGE_DIR", "CALIBRATE_CONFIG_DIR", "CALIBRATE_OUT_PATH_JSON", "CALIBRATE_OUT_PATH_YAML", "CALIBRATE_SAMPLE_IMAGE_DIR", "CALIBRATION_SERVER_ENDPOINT", "CAMERA_CONFIG_PATH", "CAMERA_EXPOSURE_TIME_KEY", "CAMERA_LASER_CONFIG_PATH", "CONFIG_PATH", "CREATE_MAPNAME", "getCREATE_MAPNAME", "()Ljava/lang/String;", "setCREATE_MAPNAME", "(Ljava/lang/String;)V", "DEAULT_MAP_NAME_FILE", "DEFAULT_EKF_PATH", "DEFAULT_LOCATE_PATH", "DEFAULT_MAP_LOCATE_PATH", "DEFAULT_PASSWORD", "DEFAULT_PASSWORD_PW", "DEFAULT_PDMAP_DATA_UNZIP_PATH", "EKF_STASH_PATH", "INIT_POINTS_JSON", "INIT_POINT_JSON", "KEY_LOCATE_PATH", "LASER_EXTEND_MAP_PATH", "LASER_MAP_ORIGIN_PATH", "LASER_OPTE_CONFIG_JSON", "LASER_OPTE_MAP_PGM", "LASER_OPTE_MAP_PNG", "LASER_OPTE_MAP_YAML", "LOCATE_MAP_DATA_NAME", "LOCATION_CONFIG_PATH", "LOC_LIZATION_JSON", "LocateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getLocateCase", "()Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "setLocateCase", "(Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;)V", "MAGNETIC_CONFIG", "MAPIFY_EKF_PATH_KEY", "MAPIFY_LOCATE_PATH", "MAPPING_JSON", "MAPPING_JSON_PATH", MapFilePathConfig.MAP_DATA, "MAP_FILE_SUFFIX", "MAP_VERSION", "", "MAX_DELETE_CODE_VALUE", "MAX_IMAGE_BUF_SIZE", "MERGE_MAP", "NAME_CAMERA_CONFIG", "NAME_MAP_CONFIG", "OLD_CAMERA_CONFIG_PATH", "OPTE_MAP_BMP_NAME", "OPTE_MAP_PGM_NAME", "OPTE_MAP_PNG_NAME", "OPTE_MAP_YAML_NAME", "PDROBOT_PD_MAP_NAME", "PDROBOT_PD_MAP_NAME_TEMP", "PRE_PARAMLINE_PATH", "PRE_PARAM_PATH", "REFLECTOR_PARAM_PATH", "SCHEDULING_CONFIG", "SLAM_MAP_DATA_NAME", "SLIP_LOCATE_MAP_DATA_NAME", "SLIP_OPTE_MAP_PGM_NAME", "SLIP_OPTE_MAP_YAML_NAME", "STASH_LOCATE_PATH", "STATIC_MAP_DEPTH", "STATIC_MAP_MAP", "STATIC_MAP_PATH", "STATIC_OBSTACLE_MAP_CFG", "STATIC_OBSTACLE_MAP_DIR", "STATIC_OBSTACLE_MAP_NAME", "STATIC_OBSTACLE_MAP_ORG_CFG", "STATIC_OBSTACLE_MAP_ORG_NAME", "STATIC_PNG", "STATIC_YAML", "TMP_MAP_LOCATE_PATH", "TOPO_DATA", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapFilePathConfig {
    public static final String ALTAS_DATA = "ATLAS_DATA";
    public static final String AUTOEXP_SAMPLE_IMAGE_DIR = "/sdcard/mapify/autoexp-sample-images";
    public static final String CALIBRATE_CONFIG_DIR = "/sdcard/PuduRobotMap/calibrate";
    public static final String CALIBRATE_OUT_PATH_JSON = "/sdcard/mapify/calibrate-sample-images/camera.json";
    public static final String CALIBRATE_OUT_PATH_YAML = "/sdcard/mapify/calibrate-sample-images/camera.yaml";
    public static final String CALIBRATE_SAMPLE_IMAGE_DIR = "/sdcard/mapify/calibrate-sample-images";
    public static final String CALIBRATION_SERVER_ENDPOINT = "http://camera.service.pudutech.com";
    public static final String CAMERA_CONFIG_PATH = "/sdcard/pudu/config/camera.config";
    public static final String CAMERA_EXPOSURE_TIME_KEY = "exposure_time";
    public static final String CAMERA_LASER_CONFIG_PATH = "/sdcard/pudu/lidar_mapping/map/config.json";
    public static final String CONFIG_PATH = "/sdcard/pudu/config";
    public static final String DEAULT_MAP_NAME_FILE = "/sdcard/pudu/config/defaultmap";
    public static final String DEFAULT_EKF_PATH = "/sdcard/PuduRobotLog/EKF/";
    public static final String DEFAULT_LOCATE_PATH = "/sdcard/PuduRobotMap/";
    public static final String DEFAULT_MAP_LOCATE_PATH = "/sdcard/pudu/map";
    public static final String DEFAULT_PASSWORD = "pudu666";
    public static final String DEFAULT_PASSWORD_PW = "pudupw";
    public static final String DEFAULT_PDMAP_DATA_UNZIP_PATH = "/sdcard/PuduRobotMap/pdrobot_tmp/";
    public static final String EKF_STASH_PATH = "/sdcard/PuduRobotLog/EKF-stash/";
    public static final String INIT_POINTS_JSON = "initpoints.json";
    public static final String INIT_POINT_JSON = "initpoint.json";
    public static final String KEY_LOCATE_PATH = "locate_path";
    public static final String LASER_EXTEND_MAP_PATH = "/sdcard/pudu/lidar_mapping";
    public static final String LASER_MAP_ORIGIN_PATH = "/sdcard/pudu/lidar_mapping/map/";
    public static final String LASER_OPTE_CONFIG_JSON = "config.json";
    public static final String LASER_OPTE_MAP_PGM = "optemap.pgm";
    public static final String LASER_OPTE_MAP_PNG = "optemap.png";
    public static final String LASER_OPTE_MAP_YAML = "optemap.yaml";
    public static final String LOCATE_MAP_DATA_NAME = "locate_map.data";
    public static final String LOCATION_CONFIG_PATH = "/sdcard/pudu/config/localization.json";
    public static final String LOC_LIZATION_JSON = "localization.json";
    public static final String MAGNETIC_CONFIG = "/sdcard/pudu/config/geomagneticCalibration.json";
    public static final String MAPIFY_EKF_PATH_KEY = "data_path";
    public static final String MAPIFY_LOCATE_PATH = "locate_path";
    public static final String MAPPING_JSON = "mapping.json";
    public static final String MAPPING_JSON_PATH = "/sdcard/pudu/config/mapping.json";
    public static final String MAP_DATA = "MAP_DATA";
    public static final String MAP_FILE_SUFFIX = ".pdmap";
    public static final int MAP_VERSION = 1;
    public static final int MAX_DELETE_CODE_VALUE = 2047;
    public static final int MAX_IMAGE_BUF_SIZE = 30;
    public static final String MERGE_MAP = "mergemap.pgm";
    public static final String NAME_CAMERA_CONFIG = "camera.config";
    public static final String NAME_MAP_CONFIG = "config.json";
    public static final String OLD_CAMERA_CONFIG_PATH = "/sdcard/PuduRobotMap/camera.config";
    public static final String OPTE_MAP_BMP_NAME = "slam.bmp";
    public static final String OPTE_MAP_PGM_NAME = "optemap.pgm";
    public static final String OPTE_MAP_PNG_NAME = "optemap.png";
    public static final String OPTE_MAP_YAML_NAME = "optemap.yaml";
    public static final String PDROBOT_PD_MAP_NAME = "pdrobot.pdmap";
    public static final String PDROBOT_PD_MAP_NAME_TEMP = "pdrobot.pdmap.temp";
    public static final String PRE_PARAMLINE_PATH = "/sdcard/pudu/config/PreParamInfoLine.json";
    public static final String PRE_PARAM_PATH = "/sdcard/pudu/config/PreParamInfo.json";
    public static final String REFLECTOR_PARAM_PATH = "/sdcard/pudu/config/reflector.config";
    public static final String SCHEDULING_CONFIG = "scheduling_config.json";
    public static final String SLAM_MAP_DATA_NAME = "origin.stcm";
    public static final String SLIP_LOCATE_MAP_DATA_NAME = "locate_map.data.slip";
    public static final String SLIP_OPTE_MAP_PGM_NAME = "slipemap.pgm";
    public static final String SLIP_OPTE_MAP_YAML_NAME = "slipemap.yaml";
    public static final String STASH_LOCATE_PATH = "/sdcard/PuduRobotMap_stash/";
    public static final String STATIC_MAP_DEPTH = "/sdcard/pudu/static_map/depth";
    public static final String STATIC_MAP_MAP = "/sdcard/pudu/static_map/map";
    public static final String STATIC_MAP_PATH = "/sdcard/pudu/static_map/map";
    public static final String STATIC_OBSTACLE_MAP_CFG = "static_obstacle_map.yaml";
    public static final String STATIC_OBSTACLE_MAP_DIR = "/sdcard/pudu/static_map/map";
    public static final String STATIC_OBSTACLE_MAP_NAME = "static_obstacle_map.png";
    public static final String STATIC_OBSTACLE_MAP_ORG_CFG = "map.yaml";
    public static final String STATIC_OBSTACLE_MAP_ORG_NAME = "map.png";
    public static final String STATIC_PNG = "static_map.png";
    public static final String STATIC_YAML = "static_map.yaml";
    public static final String TMP_MAP_LOCATE_PATH = "/sdcard/pudu/log/map";
    public static final String TOPO_DATA = "topodata.json";
    public static final MapFilePathConfig INSTANCE = new MapFilePathConfig();
    private static String CREATE_MAPNAME = "pudumap";
    private static LocateCase LocateCase = LocateCase.Marker;

    private MapFilePathConfig() {
    }

    public final String getCREATE_MAPNAME() {
        return CREATE_MAPNAME;
    }

    public final void setCREATE_MAPNAME(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        CREATE_MAPNAME = str;
    }

    public final LocateCase getLocateCase() {
        return LocateCase;
    }

    public final void setLocateCase(LocateCase locateCase) {
        Intrinsics.checkParameterIsNotNull(locateCase, "<set-?>");
        LocateCase = locateCase;
    }
}
