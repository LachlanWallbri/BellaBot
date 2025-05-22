package com.pudutech.mirsdk.mapify.util;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.mapify.Robot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CamerConfigHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010h\u001a\u00020#J\u0010\u0010i\u001a\u00020j2\b\u0010k\u001a\u0004\u0018\u00010lJ\u0006\u0010m\u001a\u00020jJ\u0006\u0010n\u001a\u00020jJ\u000e\u0010o\u001a\u00020j2\u0006\u0010p\u001a\u00020)J\u000e\u0010q\u001a\u00020j2\u0006\u0010p\u001a\u00020)J\u0018\u0010r\u001a\u00020j2\b\u0010k\u001a\u0004\u0018\u00010l2\u0006\u0010s\u001a\u00020\u0005J\b\u0010t\u001a\u00020\u0005H\u0002J\u0012\u0010u\u001a\u0004\u0018\u00010v2\b\u0010w\u001a\u0004\u0018\u00010vJ\u0012\u0010x\u001a\u00020j2\b\u0010w\u001a\u0004\u0018\u00010vH\u0002J\u0012\u0010y\u001a\u00020z2\b\u0010{\u001a\u0004\u0018\u00010>H\u0002J\u0010\u0010|\u001a\u00020z2\u0006\u0010{\u001a\u00020}H\u0002J\u0019\u0010~\u001a\u00020v2\u0006\u0010\u007f\u001a\u00020#2\u0007\u0010\u0080\u0001\u001a\u00020#H\u0002J\u0011\u0010\u0081\u0001\u001a\u00020z2\u0006\u0010{\u001a\u00020>H\u0002J\u001a\u0010\u0082\u0001\u001a\u00020v2\u0006\u0010\u007f\u001a\u00020#2\u0007\u0010\u0080\u0001\u001a\u00020#H\u0002J\u0013\u0010\u0083\u0001\u001a\u00020z2\b\u0010{\u001a\u0004\u0018\u00010>H\u0002J\u0013\u0010\u0084\u0001\u001a\u0004\u0018\u00010v2\b\u0010w\u001a\u0004\u0018\u00010vJ\u0013\u0010\u0085\u0001\u001a\u0004\u0018\u00010v2\b\u0010w\u001a\u0004\u0018\u00010vJ\u0013\u0010\u0086\u0001\u001a\u0004\u0018\u00010v2\b\u0010w\u001a\u0004\u0018\u00010vJ\u0013\u0010\u0087\u0001\u001a\u0004\u0018\u00010v2\b\u0010w\u001a\u0004\u0018\u00010vJ\u0011\u0010\u0088\u0001\u001a\u00020j2\b\u0010k\u001a\u0004\u0018\u00010lJ\u0007\u0010\u0089\u0001\u001a\u00020jJ\u0011\u0010\u008a\u0001\u001a\u00020j2\u0006\u0010w\u001a\u00020vH\u0002J\u0012\u0010\u008b\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010vJ\u001d\u0010\u008d\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010v2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u008f\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010vJ\u0012\u0010\u0090\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010vJ\u0012\u0010\u0091\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010vJ\u0012\u0010\u0092\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010vJ\u0012\u0010\u0093\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010vJ&\u0010\u0094\u0001\u001a\u00020j2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00052\u0007\u0010\u0095\u0001\u001a\u00020#2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0005J$\u0010\u0097\u0001\u001a\u00020j2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00052\u0007\u0010\u0098\u0001\u001a\u00020#2\u0007\u0010\u0099\u0001\u001a\u00020#J\u0011\u0010\u009a\u0001\u001a\u00020j2\b\u0010\u009b\u0001\u001a\u00030\u008c\u0001J\u0011\u0010\u009c\u0001\u001a\u00020j2\b\u0010\u009b\u0001\u001a\u00030\u008c\u0001J\u0010\u0010\u009d\u0001\u001a\u00020j2\u0007\u0010\u009e\u0001\u001a\u00020)J\u0011\u0010\u009f\u0001\u001a\u00020j2\b\u0010\u009b\u0001\u001a\u00030\u008c\u0001J/\u0010 \u0001\u001a\u00020j2\b\u0010¡\u0001\u001a\u00030¢\u00012\b\u0010£\u0001\u001a\u00030¢\u00012\b\u0010¤\u0001\u001a\u00030¢\u00012\b\u0010¥\u0001\u001a\u00030¢\u0001J\u0012\u0010¦\u0001\u001a\u00030\u008c\u00012\b\u0010w\u001a\u0004\u0018\u00010vJ\u0010\u0010§\u0001\u001a\u00020j2\u0007\u0010¨\u0001\u001a\u00020eJ\u0011\u0010©\u0001\u001a\u00020j2\b\u0010\u009b\u0001\u001a\u00030\u008c\u0001J\u0014\u0010ª\u0001\u001a\u0004\u0018\u00010v2\t\u0010«\u0001\u001a\u0004\u0018\u00010\u0005J\u0019\u0010¬\u0001\u001a\u0004\u0018\u00010v2\f\b\u0002\u0010\u00ad\u0001\u001a\u0005\u0018\u00010®\u0001H\u0007J\t\u0010¯\u0001\u001a\u0004\u0018\u00010vJ\t\u0010°\u0001\u001a\u0004\u0018\u00010vJ\t\u0010±\u0001\u001a\u0004\u0018\u00010vJ\t\u0010²\u0001\u001a\u0004\u0018\u00010vJ\t\u0010³\u0001\u001a\u0004\u0018\u00010vJ\t\u0010´\u0001\u001a\u0004\u0018\u00010vJ\u0015\u0010µ\u0001\u001a\u00030\u008c\u00012\t\u0010¶\u0001\u001a\u0004\u0018\u00010\u0005H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001c\u00104\u001a\u0004\u0018\u000105X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010+\"\u0004\b<\u0010-R\u001c\u0010=\u001a\u0004\u0018\u00010>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0013\u0010C\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001a\u0010F\u001a\u00020GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001c\u0010L\u001a\u0004\u0018\u00010/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u00101\"\u0004\bN\u00103R\u001c\u0010O\u001a\u0004\u0018\u00010>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010@\"\u0004\bQ\u0010BR\u001a\u0010R\u001a\u00020GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010I\"\u0004\bT\u0010KR\u0011\u0010U\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\bV\u0010%R\u0011\u0010W\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\bX\u0010%R\u001a\u0010Y\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010+\"\u0004\b[\u0010-R\u001a\u0010\\\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010%\"\u0004\b^\u0010'R\u001a\u0010_\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010%\"\u0004\ba\u0010'R\u0011\u0010b\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\bc\u0010%R\u000e\u0010d\u001a\u00020eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010f\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\bg\u0010%¨\u0006·\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/util/CamerConfigHelper;", "", "()V", "CALIBRATION_KEY_SET", "", "", "FRONT_CAMERA_MATRIX", "FRONT_DISTORTION", "FRONT_EXTRINSIC", "FRONT_METHOD", "JSON_MAPPER", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "getJSON_MAPPER", "()Lcom/fasterxml/jackson/databind/ObjectMapper;", "setJSON_MAPPER", "(Lcom/fasterxml/jackson/databind/ObjectMapper;)V", "KEY_CALIBRATION_TIME", "KEY_CAMERA_MATRIX", "KEY_DATA", "KEY_DISTORTION_COEFFICIENTS", "KEY_LASER_EXTRINSIC", "KEY_SCORE_SHOLD", "KEY_SN", "KEY_TYPE_ID", "KEY_VALID_FRAME", "KEY__EXTRINSIC", "MAP_ALIAS", "MAP_VERSION", "NO_MARKER_DISTANCE", "SENSOR_LEFT", "SENSOR_RIGHT", "TAG", "VAL_TYPE_ID", "YAML_MAPPER", "calibrateValidFrame", "", "getCalibrateValidFrame", "()I", "setCalibrateValidFrame", "(I)V", "camReprojectError", "", "getCamReprojectError", "()F", "setCamReprojectError", "(F)V", "cameraCalibrateTime", "", "getCameraCalibrateTime", "()[B", "setCameraCalibrateTime", "([B)V", "cameraFlashNative", "Lcom/pudutech/mirsdk/hardware/CameraInterface;", "getCameraFlashNative", "()Lcom/pudutech/mirsdk/hardware/CameraInterface;", "setCameraFlashNative", "(Lcom/pudutech/mirsdk/hardware/CameraInterface;)V", "cameraFovAngle", "getCameraFovAngle", "setCameraFovAngle", "cameraParams", "", "getCameraParams", "()[F", "setCameraParams", "([F)V", "cameraSN", "getCameraSN", "()Ljava/lang/String;", "cameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "getCameraType", "()Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "setCameraType", "(Lcom/pudutech/mirsdk/aidl/serialize/CameraType;)V", "chars", "getChars", "setChars", "clarity", "getClarity", "setClarity", "currentType", "getCurrentType", "setCurrentType", "detDropSwitch", "getDetDropSwitch", "detDropSwitch2", "getDetDropSwitch2", "focusLength", "getFocusLength", "setFocusLength", CamerConfigHelper.SENSOR_LEFT, "getMagic_sensor_left", "setMagic_sensor_left", CamerConfigHelper.SENSOR_RIGHT, "getMagic_sensor_right", "setMagic_sensor_right", "reflectorSwitch", "getReflectorSwitch", "robotType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "slipControlSwitch", "getSlipControlSwitch", "closeCamera", "copyFileFromAssets", "", "context", "Landroid/content/Context;", "createCameraConfig", "createFrontMatrix", "createLocalizationJson", "typedata", "createMappingJson", "createScheduleJson", "mapName", "currentCalibrationTime", "getCameraSize", "Lcom/fasterxml/jackson/databind/node/ObjectNode;", "objectNode", "loadCameraCalibParams", "makeDistortionDataArray", "Lcom/fasterxml/jackson/databind/node/ArrayNode;", "params", "makeDoubleDataArray", "", "makeExtrinsic", "row", "col", "makeMappDataArray", "makeMatrix", "makeMatrixDataArray", "onGetCameraReprojectError", "onGetCamera_fov", "onGetCurrent_clarity", "onGetFocus_length", "overrideCameraconfigFile", "readCameraParam", "removeUnusedNode", "saveConfig", "", "saveConfigJson", "path", "saveLocalization", "saveMapping", "saveNewPreParam", "savePreParam", "saveReflectorParam", "setConfigJsonParams", "version", "name", "setConfigJsonSensorParams", "sensorleft", "sensorRight", "setDetDropSwitch", "flag", "setDetDropSwitch2", "setNorMarkerDistance", "distance", "setReflectorDropSwitch", "setReflectorParam", "in_brake_dist", "", "follow_line_dist", "slow_down_dist", "out_brake_dist", "setRobotExtrinsicIfNeed", "setRobotType", "type", "setSlipControlSwitch", "tryReadAsConfigJSON", "configPath", "tryReadAsJSON", C3898x.f4339h, "Ljava/io/File;", "tryReadAsLocateJSON", "tryReadAsMapingJSON", "tryReadNewPreAsJSON", "tryReadPreAsJSON", "tryReadPreLineAsJSON", "tryReadReflectorParamAsJSON", "validSN", "sn", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CamerConfigHelper {
    private static final String FRONT_CAMERA_MATRIX = "front_camera_matrix";
    private static final String FRONT_DISTORTION = "front_camera_distortion_coefficients";
    private static final String FRONT_EXTRINSIC = "front_camera_extrinsic";
    private static final String FRONT_METHOD = "fdecodemethod";
    private static final String KEY_DATA = "data";
    private static final String KEY_LASER_EXTRINSIC = "laser_extrinsic";
    private static final String KEY_SCORE_SHOLD = "locator_monitor.lost_particle_score_threshold";
    private static final String KEY_SN = "__sn";
    private static final String KEY_TYPE_ID = "type_id";
    private static final String KEY_VALID_FRAME = "nrOfFrames";
    private static final String KEY__EXTRINSIC = "extrinsic";
    private static final String MAP_ALIAS = "alias";
    private static final String MAP_VERSION = "map_version";
    private static final String NO_MARKER_DISTANCE = "no_marker_dist_th_";
    private static final String SENSOR_LEFT = "magic_sensor_left";
    private static final String SENSOR_RIGHT = "magic_sensor_right";
    private static final String TAG = "CameraConfigHelper";
    private static final String VAL_TYPE_ID = "opencv-matrix";
    private static int calibrateValidFrame;
    private static float camReprojectError;
    private static CameraInterface cameraFlashNative;
    private static float cameraFovAngle;
    private static float[] cameraParams;
    private static byte[] chars;
    private static float focusLength;
    private static int magic_sensor_left;
    private static int magic_sensor_right;
    public static final CamerConfigHelper INSTANCE = new CamerConfigHelper();
    private static ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper();
    private static ProductMachineType robotType = new ProductMachineType(MachineModel.Peanut, 1, 1);
    private static CameraType cameraType = CameraType.MARKER_CAMERA;
    private static CameraType currentType = CameraType.MARKER_CAMERA;
    private static byte[] cameraCalibrateTime = new byte[0];
    private static float[] clarity = new float[0];
    private static final String KEY_CAMERA_MATRIX = "camera_matrix";
    private static final String KEY_DISTORTION_COEFFICIENTS = "distortion_coefficients";
    private static final String KEY_CALIBRATION_TIME = "calibration_time";
    private static final Set<String> CALIBRATION_KEY_SET = new HashSet(Arrays.asList(KEY_CAMERA_MATRIX, KEY_DISTORTION_COEFFICIENTS, KEY_CALIBRATION_TIME));

    public final ObjectNode tryReadAsJSON() {
        return tryReadAsJSON$default(this, null, 1, null);
    }

    private CamerConfigHelper() {
    }

    public final ObjectMapper getJSON_MAPPER() {
        return JSON_MAPPER;
    }

    public final void setJSON_MAPPER(ObjectMapper objectMapper) {
        Intrinsics.checkParameterIsNotNull(objectMapper, "<set-?>");
        JSON_MAPPER = objectMapper;
    }

    public final CameraInterface getCameraFlashNative() {
        return cameraFlashNative;
    }

    public final void setCameraFlashNative(CameraInterface cameraInterface) {
        cameraFlashNative = cameraInterface;
    }

    public final CameraType getCameraType() {
        return cameraType;
    }

    public final void setCameraType(CameraType cameraType2) {
        Intrinsics.checkParameterIsNotNull(cameraType2, "<set-?>");
        cameraType = cameraType2;
    }

    public final CameraType getCurrentType() {
        return currentType;
    }

    public final void setCurrentType(CameraType cameraType2) {
        Intrinsics.checkParameterIsNotNull(cameraType2, "<set-?>");
        currentType = cameraType2;
    }

    public final int getMagic_sensor_left() {
        return magic_sensor_left;
    }

    public final void setMagic_sensor_left(int i) {
        magic_sensor_left = i;
    }

    public final int getMagic_sensor_right() {
        return magic_sensor_right;
    }

    public final void setMagic_sensor_right(int i) {
        magic_sensor_right = i;
    }

    public final void setRobotType(ProductMachineType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        robotType = type;
    }

    public final byte[] getChars() {
        return chars;
    }

    public final void setChars(byte[] bArr) {
        chars = bArr;
    }

    public final float[] getCameraParams() {
        return cameraParams;
    }

    public final void setCameraParams(float[] fArr) {
        cameraParams = fArr;
    }

    public final byte[] getCameraCalibrateTime() {
        return cameraCalibrateTime;
    }

    public final void setCameraCalibrateTime(byte[] bArr) {
        cameraCalibrateTime = bArr;
    }

    public final int getCalibrateValidFrame() {
        return calibrateValidFrame;
    }

    public final void setCalibrateValidFrame(int i) {
        calibrateValidFrame = i;
    }

    public final float getCamReprojectError() {
        return camReprojectError;
    }

    public final void setCamReprojectError(float f) {
        camReprojectError = f;
    }

    public final float getCameraFovAngle() {
        return cameraFovAngle;
    }

    public final void setCameraFovAngle(float f) {
        cameraFovAngle = f;
    }

    public final float getFocusLength() {
        return focusLength;
    }

    public final void setFocusLength(float f) {
        focusLength = f;
    }

    public final float[] getClarity() {
        return clarity;
    }

    public final void setClarity(float[] fArr) {
        clarity = fArr;
    }

    private final String currentCalibrationTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String format = simpleDateFormat.format(new Date());
        Intrinsics.checkExpressionValueIsNotNull(format, "sdf.format(Date())");
        return format;
    }

    private final void removeUnusedNode(ObjectNode objectNode) {
        HashSet hashSet = new HashSet(Arrays.asList("avg_reprojection_error", "extrinsic_parameters", "image_points", "per_view_reprojection_errors", "board_height", "board_width", "flagvalue", "fovx", "fovy", "nrofframes"));
        HashSet<String> hashSet2 = new HashSet();
        Iterator<String> fieldNames = objectNode.fieldNames();
        while (fieldNames.hasNext()) {
            String next = fieldNames.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "it.next()");
            hashSet2.add(next);
        }
        for (String str : hashSet2) {
            if (str != null) {
                String lowerCase = str.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                if (hashSet.contains(lowerCase)) {
                    Pdlog.m3273d(TAG, "drop unwanted key " + str);
                    objectNode.remove(str);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
    }

    public final ObjectNode tryReadPreAsJSON() {
        return tryReadAsJSON(new File(MapFilePathConfig.PRE_PARAM_PATH));
    }

    public final ObjectNode tryReadPreLineAsJSON() {
        return tryReadAsJSON(new File(MapFilePathConfig.PRE_PARAMLINE_PATH));
    }

    public final ObjectNode tryReadNewPreAsJSON() {
        return tryReadAsJSON(new File(MapFilePathConfig.PRE_PARAMLINE_PATH));
    }

    public final ObjectNode tryReadReflectorParamAsJSON() {
        return tryReadAsJSON(new File(MapFilePathConfig.REFLECTOR_PARAM_PATH));
    }

    public final ObjectNode tryReadAsMapingJSON() {
        return tryReadAsJSON(new File(MapFilePathConfig.MAPPING_JSON_PATH));
    }

    public final ObjectNode tryReadAsLocateJSON() {
        return tryReadAsJSON(new File(MapFilePathConfig.LOCATION_CONFIG_PATH));
    }

    public final ObjectNode tryReadAsConfigJSON(String configPath) {
        return tryReadAsJSON(new File(configPath));
    }

    public static /* synthetic */ ObjectNode tryReadAsJSON$default(CamerConfigHelper camerConfigHelper, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            file = new File(MapFilePathConfig.CAMERA_CONFIG_PATH);
        }
        return camerConfigHelper.tryReadAsJSON(file);
    }

    public final ObjectNode tryReadAsJSON(File f) {
        JsonNode jsonNode = (JsonNode) null;
        try {
            jsonNode = JSON_MAPPER.readTree(f);
        } catch (IOException e) {
            Pdlog.m3275i(TAG, "try read config as JSON failed", e);
        }
        if (jsonNode == null) {
            return null;
        }
        return (ObjectNode) jsonNode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0047, code lost:
    
        if (r0 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x004a, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean saveConfig(ObjectNode objectNode) throws IOException {
        JsonGenerator jsonGenerator = (JsonGenerator) null;
        try {
            try {
                jsonGenerator = new JsonFactory().createGenerator(new File(MapFilePathConfig.CAMERA_CONFIG_PATH), JsonEncoding.UTF8).useDefaultPrettyPrinter();
                if (objectNode == null) {
                    Intrinsics.throwNpe();
                }
                objectNode.put("__mtime", System.currentTimeMillis());
                JSON_MAPPER.writeTree(jsonGenerator, (JsonNode) objectNode);
            } catch (IOException e) {
                Pdlog.m3274e(TAG, "saveConfig failed", e);
            }
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0037, code lost:
    
        if (r0 == null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003a, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean saveConfigJson(ObjectNode objectNode, String path) throws IOException {
        JsonGenerator jsonGenerator = (JsonGenerator) null;
        try {
            try {
                jsonGenerator = new JsonFactory().createGenerator(new File(path), JsonEncoding.UTF8).useDefaultPrettyPrinter();
                JSON_MAPPER.writeTree(jsonGenerator, (JsonNode) objectNode);
            } catch (IOException e) {
                Pdlog.m3274e(TAG, "saveConfigjson failed", e);
            }
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        if (r0 == null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003c, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean saveMapping(ObjectNode objectNode) throws IOException {
        JsonGenerator jsonGenerator = (JsonGenerator) null;
        try {
            try {
                jsonGenerator = new JsonFactory().createGenerator(new File(MapFilePathConfig.MAPPING_JSON_PATH), JsonEncoding.UTF8).useDefaultPrettyPrinter();
                JSON_MAPPER.writeTree(jsonGenerator, (JsonNode) objectNode);
            } catch (IOException e) {
                Pdlog.m3274e(TAG, "saveConfig failed", e);
            }
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        if (r0 == null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003c, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean savePreParam(ObjectNode objectNode) throws IOException {
        JsonGenerator jsonGenerator = (JsonGenerator) null;
        try {
            try {
                jsonGenerator = new JsonFactory().createGenerator(new File(MapFilePathConfig.PRE_PARAM_PATH), JsonEncoding.UTF8).useDefaultPrettyPrinter();
                JSON_MAPPER.writeTree(jsonGenerator, (JsonNode) objectNode);
            } catch (IOException e) {
                Pdlog.m3274e(TAG, "saveConfig failed", e);
            }
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }
    }

    public final boolean saveNewPreParam(ObjectNode objectNode) throws IOException {
        try {
            JSON_MAPPER.writeTree(new JsonFactory().createGenerator(new File(MapFilePathConfig.PRE_PARAMLINE_PATH), JsonEncoding.UTF8).useDefaultPrettyPrinter(), (JsonNode) objectNode);
        } catch (IOException e) {
            Pdlog.m3274e(TAG, "saveConfig failed", e);
        }
        return true;
    }

    public final boolean saveReflectorParam(ObjectNode objectNode) throws IOException {
        try {
            JSON_MAPPER.writeTree(new JsonFactory().createGenerator(new File(MapFilePathConfig.REFLECTOR_PARAM_PATH), JsonEncoding.UTF8).useDefaultPrettyPrinter(), (JsonNode) objectNode);
        } catch (IOException e) {
            Pdlog.m3274e(TAG, "saveConfig failed", e);
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        if (r0 == null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003c, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean saveLocalization(ObjectNode objectNode) throws IOException {
        JsonGenerator jsonGenerator = (JsonGenerator) null;
        try {
            try {
                jsonGenerator = new JsonFactory().createGenerator(new File(MapFilePathConfig.LOCATION_CONFIG_PATH), JsonEncoding.UTF8).useDefaultPrettyPrinter();
                JSON_MAPPER.writeTree(jsonGenerator, (JsonNode) objectNode);
            } catch (IOException e) {
                Pdlog.m3274e(TAG, "saveConfig failed", e);
            }
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }
    }

    public final void createCameraConfig() throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        ObjectNode cameraSize = getCameraSize(tryReadAsJSON$default(this, null, 1, null));
        Pdlog.m3274e(TAG, "camera_config_parms=====》》》tmp save camera config");
        saveConfig(cameraSize);
        setRobotExtrinsicIfNeed(cameraSize);
        loadCameraCalibParams(cameraSize);
    }

    public final void readCameraParam() throws IOException, RemoteException {
        CameraInterface cameraInterface = cameraFlashNative;
        if (cameraInterface == null) {
            Intrinsics.throwNpe();
        }
        chars = cameraInterface.onGetCameraIDNative();
        CameraInterface cameraInterface2 = cameraFlashNative;
        if (cameraInterface2 == null) {
            Intrinsics.throwNpe();
        }
        cameraParams = cameraInterface2.onGetCameraParamNative();
        CameraInterface cameraInterface3 = cameraFlashNative;
        if (cameraInterface3 == null) {
            Intrinsics.throwNpe();
        }
        cameraCalibrateTime = cameraInterface3.onGetCameraCalibrateTimeNative();
        CameraInterface cameraInterface4 = cameraFlashNative;
        if (cameraInterface4 == null) {
            Intrinsics.throwNpe();
        }
        calibrateValidFrame = cameraInterface4.onGetCalibrateValidFrameNative();
        CameraInterface cameraInterface5 = cameraFlashNative;
        if (cameraInterface5 == null) {
            Intrinsics.throwNpe();
        }
        camReprojectError = cameraInterface5.onGetCameraReprojectErrorNative();
        if (cameraFlashNative == null) {
            Intrinsics.throwNpe();
        }
        cameraFovAngle = r0.onGetCameraFovNative();
        CameraInterface cameraInterface6 = cameraFlashNative;
        if (cameraInterface6 == null) {
            Intrinsics.throwNpe();
        }
        focusLength = cameraInterface6.onGetFocusLengthNative();
        CameraInterface cameraInterface7 = cameraFlashNative;
        if (cameraInterface7 == null) {
            Intrinsics.throwNpe();
        }
        clarity = cameraInterface7.onGetCameraClarityNative();
    }

    public final int closeCamera() throws RemoteException {
        try {
            CameraInterface cameraInterface = cameraFlashNative;
            if (cameraInterface == null) {
                Intrinsics.throwNpe();
            }
            return cameraInterface.onCloseCameraNative();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public final void setNorMarkerDistance(float distance) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        ObjectNode tryReadAsJSON$default = tryReadAsJSON$default(this, null, 1, null);
        if (tryReadAsJSON$default == null) {
            Intrinsics.throwNpe();
        }
        tryReadAsJSON$default.put(NO_MARKER_DISTANCE, distance);
        saveConfig(tryReadAsJSON$default);
        Pdlog.m3273d(TAG, "camera_config_parms====》》》setNorMarkerDistance " + distance);
    }

    public final void setConfigJsonParams(String path, int version, String name) throws IOException {
        ObjectNode tryReadAsConfigJSON = tryReadAsConfigJSON(path);
        if (tryReadAsConfigJSON == null) {
            Intrinsics.throwNpe();
        }
        tryReadAsConfigJSON.put("map_version", version);
        tryReadAsConfigJSON.put("alias", name);
        saveConfigJson(tryReadAsConfigJSON, path);
        Pdlog.m3273d(TAG, "config.json====》》》setConfigjson version " + version);
    }

    public final void setConfigJsonSensorParams(String path, int sensorleft, int sensorRight) throws IOException {
        ObjectNode tryReadAsConfigJSON = tryReadAsConfigJSON(path);
        if (tryReadAsConfigJSON == null) {
            Intrinsics.throwNpe();
        }
        tryReadAsConfigJSON.put(SENSOR_LEFT, sensorleft);
        tryReadAsConfigJSON.put(SENSOR_RIGHT, sensorRight);
        saveConfigJson(tryReadAsConfigJSON, path);
        Pdlog.m3273d(TAG, "config.json====》》》setConfigjson version left " + sensorleft + " right " + sensorRight);
    }

    public final void overrideCameraconfigFile(Context context) {
        IOHelper.copyFileFromAssets(context, MapFilePathConfig.NAME_CAMERA_CONFIG, MapFilePathConfig.CAMERA_CONFIG_PATH, true);
    }

    public final void copyFileFromAssets(Context context) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        if (!new File(MapFilePathConfig.CAMERA_CONFIG_PATH).exists()) {
            IOHelper.copyFileFromAssets(context, MapFilePathConfig.NAME_CAMERA_CONFIG, MapFilePathConfig.CAMERA_CONFIG_PATH, true);
        }
        Pdlog.m3274e(TAG, "overrideBuiltinConfig");
        ObjectNode tryReadAsJSON$default = tryReadAsJSON$default(this, null, 1, null);
        if (tryReadAsJSON$default == null) {
            Pdlog.m3274e(TAG, "no front_camera_extrinsic overrid old");
            IOHelper.copyFileFromAssets(context, MapFilePathConfig.NAME_CAMERA_CONFIG, MapFilePathConfig.CAMERA_CONFIG_PATH, true);
            tryReadAsJSON$default = tryReadAsJSON$default(this, null, 1, null);
        }
        if (tryReadAsJSON$default == null) {
            Intrinsics.throwNpe();
        }
        if (tryReadAsJSON$default.get(FRONT_EXTRINSIC) == null) {
            createFrontMatrix();
        }
        ObjectNode tryReadAsJSON$default2 = tryReadAsJSON$default(this, null, 1, null);
        if (tryReadAsJSON$default2 == null) {
            Intrinsics.throwNpe();
        }
        JsonNode jsonNode = tryReadAsJSON$default2.get(FRONT_CAMERA_MATRIX).get("data");
        if (Intrinsics.areEqual(jsonNode.get(0).asText(), "1035.44801")) {
            Pdlog.m3274e(TAG, "overrideBuiltinConfig old frontCameradata" + jsonNode);
            ObjectNode makeExtrinsic = makeExtrinsic(3, 3);
            makeExtrinsic.set("data", makeDoubleDataArray(new double[]{517.724005d, 0.0d, 473.95348d, 0.0d, 517.72553d, 270.89661d, 0.0d, 0.0d, 1.0d}));
            tryReadAsJSON$default.set(FRONT_CAMERA_MATRIX, makeExtrinsic);
            saveConfig(tryReadAsJSON$default);
        }
        boolean copyFileFromAssets = IOHelper.copyFileFromAssets(context, MapFilePathConfig.NAME_CAMERA_CONFIG, MapFilePathConfig.CAMERA_CONFIG_PATH, false);
        if (!copyFileFromAssets) {
            throw new IllegalStateException("复制内建camera.config失败".toString());
        }
        Pdlog.m3274e(TAG, String.valueOf(copyFileFromAssets) + "overrideBuiltinConfig==》》》" + MapFilePathConfig.NAME_CAMERA_CONFIG);
        if (tryReadAsMapingJSON() == null) {
            IOHelper.copyFileFromAssets(context, MapFilePathConfig.MAPPING_JSON, MapFilePathConfig.MAPPING_JSON_PATH, true);
        }
        boolean copyFileFromAssets2 = IOHelper.copyFileFromAssets(context, MapFilePathConfig.MAPPING_JSON, MapFilePathConfig.MAPPING_JSON_PATH, false);
        if (!copyFileFromAssets2) {
            throw new IllegalStateException("复制内建mapping.json失败".toString());
        }
        Pdlog.m3274e(TAG, String.valueOf(copyFileFromAssets2) + "overrideBuiltinConfig==》》》" + MapFilePathConfig.MAPPING_JSON);
        ObjectNode tryReadAsLocateJSON = tryReadAsLocateJSON();
        if (tryReadAsLocateJSON == null || tryReadAsLocateJSON.get("marker_stable_min_num") == null) {
            Pdlog.m3274e(TAG, "no fmarker_stable_min_num overrid old");
            IOHelper.copyFileFromAssets(context, MapFilePathConfig.LOC_LIZATION_JSON, MapFilePathConfig.LOCATION_CONFIG_PATH, true);
        }
        boolean copyFileFromAssets3 = IOHelper.copyFileFromAssets(context, MapFilePathConfig.LOC_LIZATION_JSON, MapFilePathConfig.LOCATION_CONFIG_PATH, false);
        if (!copyFileFromAssets3) {
            throw new IllegalStateException("复制内建localization.json失败".toString());
        }
        Pdlog.m3274e(TAG, String.valueOf(copyFileFromAssets3) + "overrideBuiltinConfig==》》》" + MapFilePathConfig.LOC_LIZATION_JSON);
    }

    public final void createScheduleJson(Context context, String mapName) throws IOException {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        boolean copyFileFromAssets = IOHelper.copyFileFromAssets(context, "scheduling_config.json", new File("/sdcard/pudu/map/" + mapName + "/scheduling_config.json").getAbsolutePath(), true);
        if (!copyFileFromAssets) {
            throw new IllegalStateException("复制内建scheduling_config.json失败".toString());
        }
        Pdlog.m3274e(TAG, String.valueOf(copyFileFromAssets) + "overrideBuiltinConfig==》》》scheduling_config.json");
    }

    public final void createMappingJson(float typedata) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        ObjectNode tryReadAsMapingJSON = tryReadAsMapingJSON();
        float[] fArr = {1.0f, 0.0f, 0.0f, 0.176f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        fArr[3] = typedata;
        ObjectNode makeExtrinsic = makeExtrinsic(4, 4);
        makeExtrinsic.set("data", makeMappDataArray(fArr));
        if (tryReadAsMapingJSON == null) {
            Intrinsics.throwNpe();
        }
        tryReadAsMapingJSON.set(KEY_LASER_EXTRINSIC, makeExtrinsic);
        Pdlog.m3273d(TAG, "camera_config_parms====》》》tmp save camera config");
        saveMapping(tryReadAsMapingJSON);
    }

    public final void setDetDropSwitch(boolean flag) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        ObjectNode tryReadPreAsJSON = tryReadPreAsJSON();
        if (tryReadPreAsJSON != null) {
            if (flag) {
                tryReadPreAsJSON.put("det_drop", 1);
            } else {
                tryReadPreAsJSON.put("det_drop", 0);
            }
            Pdlog.m3273d(TAG, "setDetDropSwitch " + flag);
            savePreParam(tryReadPreAsJSON);
            File file2 = new File(MapFilePathConfig.CONFIG_PATH);
            if (!file2.exists()) {
                file2.mkdir();
            }
            ObjectNode tryReadNewPreAsJSON = tryReadNewPreAsJSON();
            if (tryReadNewPreAsJSON != null) {
                if (flag) {
                    tryReadNewPreAsJSON.put("det_drop", 1);
                } else {
                    tryReadNewPreAsJSON.put("det_drop", 0);
                }
                Pdlog.m3273d(TAG, "setDetDropSwitch new " + flag);
                saveNewPreParam(tryReadNewPreAsJSON);
            }
        }
    }

    public final void setDetDropSwitch2(boolean flag) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        ObjectNode tryReadPreLineAsJSON = tryReadPreLineAsJSON();
        if (tryReadPreLineAsJSON != null) {
            if (flag) {
                tryReadPreLineAsJSON.put("det_drop", 1);
            } else {
                tryReadPreLineAsJSON.put("det_drop", 0);
            }
            Pdlog.m3273d(TAG, "setDetDropSwitch " + flag);
            savePreParam(tryReadPreLineAsJSON);
            File file2 = new File(MapFilePathConfig.CONFIG_PATH);
            if (!file2.exists()) {
                file2.mkdir();
            }
            ObjectNode tryReadNewPreAsJSON = tryReadNewPreAsJSON();
            if (tryReadNewPreAsJSON != null) {
                if (flag) {
                    tryReadNewPreAsJSON.put("det_drop", 1);
                } else {
                    tryReadNewPreAsJSON.put("det_drop", 0);
                }
                Pdlog.m3273d(TAG, "setDetDropSwitch new " + flag);
                saveNewPreParam(tryReadNewPreAsJSON);
            }
        }
    }

    public final void setReflectorDropSwitch(boolean flag) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        if (!new File(MapFilePathConfig.REFLECTOR_PARAM_PATH).exists()) {
            FileWriter fileWriter = new FileWriter(new File(MapFilePathConfig.REFLECTOR_PARAM_PATH));
            fileWriter.write("{}");
            fileWriter.close();
        }
        ObjectNode tryReadReflectorParamAsJSON = tryReadReflectorParamAsJSON();
        if (tryReadReflectorParamAsJSON != null) {
            if (flag) {
                tryReadReflectorParamAsJSON.put("reflector_switch", 1);
            } else {
                tryReadReflectorParamAsJSON.put("reflector_switch", 0);
            }
            Pdlog.m3273d(TAG, "setReflectorSwitch " + flag);
            saveReflectorParam(tryReadReflectorParamAsJSON);
        }
    }

    public final void setReflectorParam(double in_brake_dist, double follow_line_dist, double slow_down_dist, double out_brake_dist) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        if (!new File(MapFilePathConfig.REFLECTOR_PARAM_PATH).exists()) {
            FileWriter fileWriter = new FileWriter(new File(MapFilePathConfig.REFLECTOR_PARAM_PATH));
            fileWriter.write("{}");
            fileWriter.close();
        }
        ObjectNode tryReadReflectorParamAsJSON = tryReadReflectorParamAsJSON();
        if (tryReadReflectorParamAsJSON != null) {
            tryReadReflectorParamAsJSON.put("in_brake_dist", in_brake_dist);
            tryReadReflectorParamAsJSON.put("follow_line_dist", follow_line_dist);
            tryReadReflectorParamAsJSON.put("slow_down_dist", slow_down_dist);
            tryReadReflectorParamAsJSON.put("out_brake_dist", out_brake_dist);
            saveReflectorParam(tryReadReflectorParamAsJSON);
        }
    }

    public final int getDetDropSwitch() throws IOException {
        ObjectNode tryReadPreAsJSON = tryReadPreAsJSON();
        if (tryReadPreAsJSON == null || tryReadPreAsJSON.get("det_drop") == null) {
            Pdlog.m3273d(TAG, "getDetDropSwitch null");
            return -1;
        }
        int asInt = tryReadPreAsJSON.get("det_drop").asInt();
        Pdlog.m3273d(TAG, "getDetDropSwitch " + asInt);
        return asInt;
    }

    public final int getDetDropSwitch2() throws IOException {
        ObjectNode tryReadPreLineAsJSON = tryReadPreLineAsJSON();
        if (tryReadPreLineAsJSON == null || tryReadPreLineAsJSON.get("det_drop") == null) {
            Pdlog.m3273d(TAG, "getDetDropSwitch2 null");
            return -1;
        }
        int asInt = tryReadPreLineAsJSON.get("det_drop").asInt();
        Pdlog.m3273d(TAG, "getDetDropSwitch2 " + asInt);
        return asInt;
    }

    public final int getReflectorSwitch() throws IOException {
        ObjectNode tryReadReflectorParamAsJSON = tryReadReflectorParamAsJSON();
        if (tryReadReflectorParamAsJSON == null || tryReadReflectorParamAsJSON.get("reflector_switch") == null) {
            Pdlog.m3273d(TAG, "getReflectorSwitch null");
            return -1;
        }
        int asInt = tryReadReflectorParamAsJSON.get("reflector_switch").asInt();
        Pdlog.m3273d(TAG, "getReflectorSwitch " + asInt);
        return asInt;
    }

    public final void setSlipControlSwitch(boolean flag) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        ObjectNode tryReadAsLocateJSON = tryReadAsLocateJSON();
        if (tryReadAsLocateJSON != null) {
            if (flag) {
                tryReadAsLocateJSON.put("use_sm_opt_angle", 1);
                tryReadAsLocateJSON.put("slip_use_scan_match", 1);
            } else {
                tryReadAsLocateJSON.put("use_sm_opt_angle", 0);
                tryReadAsLocateJSON.put("slip_use_scan_match", 0);
            }
            Pdlog.m3273d(TAG, "setSlipControlSwitch " + flag);
            saveLocalization(tryReadAsLocateJSON);
        }
    }

    public final int getSlipControlSwitch() throws IOException {
        ObjectNode tryReadAsLocateJSON = tryReadAsLocateJSON();
        if (tryReadAsLocateJSON == null || tryReadAsLocateJSON.get("use_sm_opt_angle") == null || tryReadAsLocateJSON.get("slip_use_scan_match") == null) {
            Pdlog.m3273d(TAG, "getSlipControlSwitch null");
            return -1;
        }
        int asInt = tryReadAsLocateJSON.get("use_sm_opt_angle").asInt();
        int asInt2 = tryReadAsLocateJSON.get("slip_use_scan_match").asInt();
        Pdlog.m3273d(TAG, "getSlipControlSwitch  : " + asInt + ", " + asInt2);
        return asInt2 & asInt;
    }

    public final void createFrontMatrix() {
        ObjectNode tryReadAsJSON$default = tryReadAsJSON$default(this, null, 1, null);
        ObjectNode makeExtrinsic = makeExtrinsic(3, 3);
        makeExtrinsic.set("data", makeDoubleDataArray(new double[]{517.724005d, 0.0d, 473.95348d, 0.0d, 517.72553d, 270.89661d, 0.0d, 0.0d, 1.0d}));
        if (tryReadAsJSON$default == null) {
            Intrinsics.throwNpe();
        }
        tryReadAsJSON$default.set(FRONT_CAMERA_MATRIX, makeExtrinsic);
        ObjectNode makeExtrinsic2 = makeExtrinsic(5, 1);
        makeExtrinsic2.set("data", makeDoubleDataArray(new double[]{0.0015012941d, 0.025648942d, 0.0013960065d, -0.0012010657d, -0.081442555d}));
        tryReadAsJSON$default.set(FRONT_DISTORTION, makeExtrinsic2);
        ObjectNode makeExtrinsic3 = makeExtrinsic(4, 4);
        makeExtrinsic3.set("data", makeMappDataArray(new float[]{0.707148f, 0.0f, -0.707066f, 0.19819f, 0.0f, 1.0f, 0.0f, 0.0f, 0.707066f, 0.0f, 0.707066f, 0.707066f, 0.0f, 0.0f, 0.0f, 1.0f}));
        tryReadAsJSON$default.set(FRONT_EXTRINSIC, makeExtrinsic3);
        Pdlog.m3273d(TAG, "camera_config_parms====》》》tmp save createFrontMatrix config");
        tryReadAsJSON$default.put(FRONT_METHOD, 10);
        try {
            saveConfig(tryReadAsJSON$default);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void createLocalizationJson(float typedata) throws IOException {
        File file = new File(MapFilePathConfig.CONFIG_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        ObjectNode tryReadAsLocateJSON = tryReadAsLocateJSON();
        float[] fArr = {1.0f, 0.0f, 0.0f, 0.176f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        fArr[3] = typedata;
        ObjectNode makeExtrinsic = makeExtrinsic(4, 4);
        makeExtrinsic.set("data", makeMappDataArray(fArr));
        if (tryReadAsLocateJSON == null) {
            Intrinsics.throwNpe();
        }
        tryReadAsLocateJSON.set(KEY__EXTRINSIC, makeExtrinsic);
        tryReadAsLocateJSON.put(KEY_SCORE_SHOLD, 0.15d);
        Pdlog.m3273d(TAG, "camera_config_parms====》》》tmp save localiztion config");
        saveLocalization(tryReadAsLocateJSON);
    }

    public final String getCameraSN() {
        byte[] bArr = chars;
        if (bArr != null) {
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            if (bArr.length != 0) {
                byte[] bArr2 = chars;
                if (bArr2 == null) {
                    Intrinsics.throwNpe();
                }
                return new String(bArr2, Charsets.UTF_8);
            }
        }
        return null;
    }

    private final boolean validSN(String sn) {
        String str = sn;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (sn == null) {
            Intrinsics.throwNpe();
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = str.charAt(!z ? i : length) <= ' ';
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return Intrinsics.areEqual(str.subSequence(i, length + 1).toString(), "0") ^ true;
    }

    private final void loadCameraCalibParams(ObjectNode objectNode) {
        boolean z;
        String str;
        Pdlog.m3274e(TAG, "onGetCameraParamNative = " + Arrays.toString(cameraParams));
        float[] fArr = cameraParams;
        if (fArr != null) {
            if (fArr == null) {
                Intrinsics.throwNpe();
            }
            if (fArr.length == 9) {
                float[] fArr2 = cameraParams;
                if (fArr2 == null) {
                    Intrinsics.throwNpe();
                }
                int length = fArr2.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = true;
                        break;
                    }
                    float[] fArr3 = cameraParams;
                    if (fArr3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Float.compare(fArr3[i], 0.0f) != 0) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (z) {
                    Pdlog.m3274e(TAG, "all calibration data is zero, drop, return ");
                    return;
                }
                String cameraSN = getCameraSN();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {cameraSN};
                String format = String.format("loadCameraCalibParams onGetCameraSNNative = '%s'", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                Pdlog.m3275i(TAG, format);
                if (!validSN(cameraSN)) {
                    Pdlog.m3274e(TAG, "camera sn is empty or zero, stop early  so return ");
                    return;
                }
                ObjectNode makeMatrix = makeMatrix(3, 3);
                makeMatrix.set("data", makeMatrixDataArray(cameraParams));
                if (objectNode == null) {
                    Intrinsics.throwNpe();
                }
                objectNode.set(KEY_CAMERA_MATRIX, makeMatrix);
                ObjectNode makeMatrix2 = makeMatrix(5, 1);
                makeMatrix2.set("data", makeDistortionDataArray(cameraParams));
                objectNode.set(KEY_DISTORTION_COEFFICIENTS, makeMatrix2);
                objectNode.set(KEY_SN, new TextNode(cameraSN));
                byte[] bArr = cameraCalibrateTime;
                if (bArr != null) {
                    if (bArr == null) {
                        Intrinsics.throwNpe();
                    }
                    str = new String(bArr, Charsets.UTF_8);
                } else {
                    str = "0";
                }
                if (TextUtils.equals(str, "0")) {
                    str = currentCalibrationTime();
                }
                objectNode.set(KEY_CALIBRATION_TIME, new TextNode(str));
                objectNode.set(KEY_VALID_FRAME, new IntNode(calibrateValidFrame));
                if (saveConfig(objectNode)) {
                    Pdlog.m3274e(TAG, "write camera calibration args from camera so success");
                    return;
                }
                return;
            }
        }
        Pdlog.m3274e(TAG, "cameraParams == null || cameraParams.length != 9  so return ");
    }

    private final ArrayNode makeMatrixDataArray(float[] params) {
        ArrayNode arrayNode = JSON_MAPPER.createArrayNode();
        if (params == null) {
            Intrinsics.throwNpe();
        }
        arrayNode.add(params[0]);
        arrayNode.add(0.0f);
        arrayNode.add(params[1]);
        arrayNode.add(0.0f);
        arrayNode.add(params[2]);
        arrayNode.add(params[3]);
        arrayNode.add(0.0f);
        arrayNode.add(0.0f);
        arrayNode.add(1.0f);
        Intrinsics.checkExpressionValueIsNotNull(arrayNode, "arrayNode");
        return arrayNode;
    }

    private final ArrayNode makeMappDataArray(float[] params) {
        ArrayNode arrayNode = JSON_MAPPER.createArrayNode();
        for (float f : params) {
            arrayNode.add(f);
        }
        Intrinsics.checkExpressionValueIsNotNull(arrayNode, "arrayNode");
        return arrayNode;
    }

    private final ArrayNode makeDoubleDataArray(double[] params) {
        ArrayNode arrayNode = JSON_MAPPER.createArrayNode();
        for (double d : params) {
            arrayNode.add(d);
        }
        Intrinsics.checkExpressionValueIsNotNull(arrayNode, "arrayNode");
        return arrayNode;
    }

    private final ArrayNode makeDistortionDataArray(float[] params) {
        ArrayNode arrayNode = JSON_MAPPER.createArrayNode();
        if (params == null) {
            Intrinsics.throwNpe();
        }
        int length = params.length;
        for (int i = 4; i < length; i++) {
            arrayNode.add(params[i]);
        }
        Intrinsics.checkExpressionValueIsNotNull(arrayNode, "arrayNode");
        return arrayNode;
    }

    private final ObjectNode makeMatrix(int row, int col) {
        ObjectNode node = JSON_MAPPER.createObjectNode();
        node.set(KEY_TYPE_ID, new TextNode(VAL_TYPE_ID));
        node.set("rows", new IntNode(row));
        node.set("cols", new IntNode(col));
        node.set("dt", new TextNode(LinkFormat.DOMAIN));
        Intrinsics.checkExpressionValueIsNotNull(node, "node");
        return node;
    }

    private final ObjectNode makeExtrinsic(int row, int col) {
        ObjectNode node = JSON_MAPPER.createObjectNode();
        node.set(KEY_TYPE_ID, new TextNode(VAL_TYPE_ID));
        node.set("rows", new IntNode(row));
        node.set("cols", new IntNode(col));
        node.set("dt", new TextNode(LinkFormat.DOMAIN));
        Intrinsics.checkExpressionValueIsNotNull(node, "node");
        return node;
    }

    public final ObjectNode getCameraSize(ObjectNode objectNode) throws NullPointerException {
        if (objectNode == null) {
            Intrinsics.throwNpe();
        }
        objectNode.put(ConfigJson.IMAGE_WIDTH, 1280);
        objectNode.put(ConfigJson.IMAGE_HEIGHT, 720);
        return onGetCurrent_clarity(onGetFocus_length(onGetCamera_fov(onGetCameraReprojectError(objectNode))));
    }

    public final ObjectNode onGetCameraReprojectError(ObjectNode objectNode) {
        Pdlog.m3274e(TAG, "camera----->camReprojectError:" + camReprojectError);
        if (objectNode == null) {
            Intrinsics.throwNpe();
        }
        objectNode.put("avg_reprojection_error", camReprojectError);
        return objectNode;
    }

    public final ObjectNode onGetCamera_fov(ObjectNode objectNode) {
        Pdlog.m3274e(TAG, "camera----->cameraFovAngle:" + cameraFovAngle);
        if (objectNode == null) {
            Intrinsics.throwNpe();
        }
        objectNode.put("cameraFovAngle", cameraFovAngle);
        return objectNode;
    }

    public final ObjectNode onGetFocus_length(ObjectNode objectNode) {
        Pdlog.m3274e(TAG, "camera----->focusLength:" + focusLength);
        if (objectNode == null) {
            Intrinsics.throwNpe();
        }
        objectNode.put("focus_length", focusLength);
        return objectNode;
    }

    public final ObjectNode onGetCurrent_clarity(ObjectNode objectNode) {
        if (clarity != null) {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("camera----->current_clarity:");
            float[] fArr = clarity;
            if (fArr == null) {
                Intrinsics.throwNpe();
            }
            sb.append(fArr[0]);
            objArr[0] = sb.toString();
            Pdlog.m3274e(TAG, objArr);
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("camera----->max_clarity:");
            float[] fArr2 = clarity;
            if (fArr2 == null) {
                Intrinsics.throwNpe();
            }
            sb2.append(fArr2[1]);
            objArr2[0] = sb2.toString();
            Pdlog.m3274e(TAG, objArr2);
            if (objectNode == null) {
                Intrinsics.throwNpe();
            }
            float[] fArr3 = clarity;
            if (fArr3 == null) {
                Intrinsics.throwNpe();
            }
            objectNode.put("current_clarity", fArr3[0]);
            float[] fArr4 = clarity;
            if (fArr4 == null) {
                Intrinsics.throwNpe();
            }
            objectNode.put("max_clarity", fArr4[1]);
        }
        return objectNode;
    }

    public final boolean setRobotExtrinsicIfNeed(ObjectNode objectNode) {
        try {
            double[] robotExtrinsic = Robot.getRobotExtrinsic(robotType);
            if (robotExtrinsic == null) {
                Pdlog.m3273d(TAG, "setRobotExtrinsicIfNeed failed ， robotExtrinsic is null");
                return true;
            }
            ObjectNode makeMatrix = makeMatrix(4, 4);
            ArrayNode createArrayNode = JSON_MAPPER.createArrayNode();
            for (double d : robotExtrinsic) {
                createArrayNode.add(d);
            }
            makeMatrix.set("data", createArrayNode);
            if (objectNode == null) {
                Intrinsics.throwNpe();
            }
            objectNode.set(KEY__EXTRINSIC, makeMatrix);
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "setRobotExtrinsicIfNeed false");
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            return false;
        }
    }
}
