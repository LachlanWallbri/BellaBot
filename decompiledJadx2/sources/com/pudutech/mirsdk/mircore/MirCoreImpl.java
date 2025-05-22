package com.pudutech.mirsdk.mircore;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.linksdk.alcs.AlcsConstant;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.compat.topo.FileName;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICenterRgbdData;
import com.pudutech.mirsdk.hardware.IDownRgbdData;
import com.pudutech.mirsdk.hardware.ILeftRgbdData;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.IRightRgbdData;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.serialize.AccelerationType;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.PuduLocateInterface;
import com.pudutech.mirsdk.mircore.aidl.CliffInfoListener;
import com.pudutech.mirsdk.mircore.aidl.ReflectorInfoListener;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection;
import com.pudutech.mirsdk.mircore.mirnavigation.Navigation;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import com.pudutech.mirsdk.mircore.mirschedulemaster.ScheduleMaster;
import com.pudutech.mirsdk.mircore.module.AreaDetectStub;
import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import com.pudutech.mirsdk.mircore.module.LocalizationStub;
import com.pudutech.mirsdk.mircore.module.NavigationStub;
import com.pudutech.mirsdk.mircore.module.ScheduleStub;
import com.pudutech.mirsdk.mircore.module.cycleparam.CycleParamUtils;
import com.pudutech.mirsdk.mircore.module.speedlevel.PlannerParamUtils;
import com.pudutech.mirsdk.mircore.module.speedlevel.RunParamUtils;
import com.pudutech.mirsdk.update.ApiConstants;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ö\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001:\u0004¾\u0001¿\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020\u0004J\u000e\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020\u0004J\u0006\u0010o\u001a\u00020pJ\r\u0010q\u001a\u0004\u0018\u000104¢\u0006\u0002\u0010rJ\b\u0010s\u001a\u00020mH\u0002J\u0006\u0010t\u001a\u000206J \u0010u\u001a\u00020m2\u0006\u0010v\u001a\u0002062\u0006\u0010h\u001a\u00020\"2\u0006\u0010w\u001a\u00020xH\u0002J\b\u0010y\u001a\u00020mH\u0002J;\u0010z\u001a\u00020m2\u0006\u0010{\u001a\u0002042\u0006\u0010|\u001a\u00020\u00042\u000e\u0010}\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010~2\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u0081\u0001J\t\u0010\u0082\u0001\u001a\u00020\u001dH\u0002J\u001b\u0010\u0083\u0001\u001a\u00020\u001d2\u0007\u0010\u0084\u0001\u001a\u00020\u00042\u0007\u0010\u0085\u0001\u001a\u00020\u0004H\u0002J\t\u0010\u0086\u0001\u001a\u00020\u001dH\u0002J4\u0010\u0087\u0001\u001a\u00020\u001d2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008b\u00012\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J3\u0010\u008d\u0001\u001a\u00020\u001d2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u00042\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008b\u00012\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J4\u0010\u008f\u0001\u001a\u00020\u001d2\u0006\u0010{\u001a\u0002042\u0006\u0010|\u001a\u00020\u00042\u000e\u0010}\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010~2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\u0013\u0010\u0090\u0001\u001a\u00020\u001d2\b\u0010\u0088\u0001\u001a\u00030\u0089\u0001H\u0002J\u001e\u0010\u0091\u0001\u001a\u00020\u001d2\b\u0010\u0092\u0001\u001a\u00030\u008b\u00012\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J6\u0010\u0093\u0001\u001a\u00020\u001d2\u0006\u0010{\u001a\u0002042\b\u0010\u0094\u0001\u001a\u00030\u008b\u00012\u000e\u0010}\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010~2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\u0007\u0010\u0095\u0001\u001a\u00020mJ,\u0010\u0096\u0001\u001a\u00020\u001d2\n\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u008b\u00012\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u008b\u00012\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\u001e\u0010\u0099\u0001\u001a\u00020\u001d2\b\u0010\u0094\u0001\u001a\u00030\u008b\u00012\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\u001a\u0010\u009a\u0001\u001a\u00020.2\t\u0010\u009b\u0001\u001a\u0004\u0018\u000104H\u0002¢\u0006\u0003\u0010\u009c\u0001J#\u0010\u009d\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00040\u00122\f\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00040~H\u0002J\u000f\u0010\u009e\u0001\u001a\u00020\u00042\u0006\u0010|\u001a\u00020\u0004J$\u0010\u009f\u0001\u001a\u00020\"2\t\u0010 \u0001\u001a\u0004\u0018\u00010\u00132\u0007\u0010¡\u0001\u001a\u00020\"2\u0007\u0010¢\u0001\u001a\u00020\"J\"\u0010£\u0001\u001a\u00020m2\u0007\u0010¤\u0001\u001a\u00020\"2\u0007\u0010¥\u0001\u001a\u0002042\u0007\u0010¦\u0001\u001a\u000204J\u0016\u0010§\u0001\u001a\u0005\u0018\u00010\u008b\u00012\b\u0010¨\u0001\u001a\u00030©\u0001H\u0002J'\u0010ª\u0001\u001a\u00020\u001d2\u0006\u0010{\u001a\u0002042\u0006\u0010|\u001a\u00020\u00042\u000e\u0010}\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010~J\"\u0010«\u0001\u001a\u00020\u001d2\f\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00040~2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\u001d\u0010¬\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010®\u0001\u001a\u000204J\u0014\u0010¯\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010°\u0001\u001a\u0004\u0018\u00010\u0004J\t\u0010±\u0001\u001a\u00020\u001dH\u0002J\u001a\u0010²\u0001\u001a\u00020m2\b\u0010³\u0001\u001a\u00030´\u00012\u0007\u0010µ\u0001\u001a\u00020\"J\u0010\u0010¶\u0001\u001a\u00020m2\u0007\u0010·\u0001\u001a\u00020\u001dJ\t\u0010¸\u0001\u001a\u00020mH\u0002J*\u0010¹\u0001\u001a\u00020m2\u0007\u0010º\u0001\u001a\u00020\u00042\u0007\u0010»\u0001\u001a\u00020\u00132\u0007\u0010¼\u0001\u001a\u00020\u0014H\u0000¢\u0006\u0003\b½\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR2\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00120\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000fR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000fR\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010/\u001a\u000200¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00108\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u000e\u0010<\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010?\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u000fR\u000e\u0010G\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010H\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001a\u0010M\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010J\"\u0004\bO\u0010LR\u0011\u0010P\u001a\u00020Q¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u000e\u0010T\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010U\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010V\u001a\u0004\u0018\u00010WX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001a\u0010\\\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u001a\u0010a\u001a\u00020bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u000e\u0010g\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006À\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirCoreImpl;", "", "()V", TmpConstant.DATA_KEY_DEVICENAME, "", "TAG", "kotlin.jvm.PlatformType", "areadetectStub", "Lcom/pudutech/mirsdk/mircore/module/AreaDetectStub;", "getAreadetectStub", "()Lcom/pudutech/mirsdk/mircore/module/AreaDetectStub;", "cliffDisListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/mircore/aidl/CliffInfoListener;", "getCliffDisListener$mircore_packRelease", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "debug_sch_recv", "", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "getDebug_sch_recv$mircore_packRelease", "()Ljava/util/Map;", "setDebug_sch_recv$mircore_packRelease", "(Ljava/util/Map;)V", "dockerEstimateTransformListener", "Lcom/pudutech/mirsdk/mircore/DockerEstimateTransformListener;", "getDockerEstimateTransformListener$mircore_packRelease", "downrgbd", "", "faceDetectListener", "Lcom/pudutech/mirsdk/mircore/FaceDetectListener;", "getFaceDetectListener$mircore_packRelease", "followLineDist", "", "followLineJob", "Lkotlinx/coroutines/Job;", "hardwareLinker", "Lcom/pudutech/mirsdk/mircore/module/HardwareLinker;", "inBrakeDist", "init_modules_status", "getInit_modules_status", "()Z", "setInit_modules_status", "(Z)V", "lidar_type", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarType;", "localization", "Lcom/pudutech/mirsdk/mircore/module/LocalizationStub;", "getLocalization", "()Lcom/pudutech/mirsdk/mircore/module/LocalizationStub;", "locateCameraType", "", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "mapVersion", "navigationer", "Lcom/pudutech/mirsdk/mircore/module/NavigationStub;", "getNavigationer", "()Lcom/pudutech/mirsdk/mircore/module/NavigationStub;", "outBrakeDist", "pdmapBackupPath", "pdmapFullName", RequestParameters.POSITION, "getPosition$mircore_packRelease", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setPosition$mircore_packRelease", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "reflectorDisListener", "Lcom/pudutech/mirsdk/mircore/aidl/ReflectorInfoListener;", "getReflectorDisListener$mircore_packRelease", "reflectorSwitch", "replanWaitTime", "getReplanWaitTime", "()I", "setReplanWaitTime", "(I)V", "roadblockClearTime", "getRoadblockClearTime", "setRoadblockClearTime", "scheduleStub", "Lcom/pudutech/mirsdk/mircore/module/ScheduleStub;", "getScheduleStub", "()Lcom/pudutech/mirsdk/mircore/module/ScheduleStub;", "slowDownDist", "slowDownJob", "smoothMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "getSmoothMode", "()Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "setSmoothMode", "(Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;)V", "speeds", "getSpeeds$mircore_packRelease", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "setSpeeds$mircore_packRelease", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;)V", "timeZero", "", "getTimeZero", "()J", "setTimeZero", "(J)V", "useRGBD", "wheel_space", "createFile", "Ljava/io/File;", "filePath", "depressPdmap", "", "zipFile", "detectChargeDocker", "Lcom/pudutech/mirsdk/mircore/coreparcel/DockerDetectResult;", "getLdsVersion", "()Ljava/lang/Integer;", "getMac", "getMachineType", "initSubmodules", "productType", "rgbdType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType;", "initVisionModel", "initializationCore", "defFloorIndex", "pdmap", "floors_map", "", "initListener", "Lcom/pudutech/mirsdk/mircore/InitServiceListener;", "(ILjava/lang/String;Ljava/util/List;Lcom/pudutech/mirsdk/mircore/InitServiceListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initializer", "loadFileFromAssets", "filename", "dstpath", "loadLaserLocateConfig", "loadLocateMap", "locateCase", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;", "mapBytes", "", "configBytes", "loadLocateMapByPath", "mapPath", "loadMapAndConfig", "loadMarkerLocateConfig", "loadMarkerLocateMap", "markerMapByes", "loadMultiTopoMap", "topoMapBytes", "loadReflectorParam", "loadScheduleConfig", "jsonBytes", "yamlBytes", "loadTopoMap", "parseIntToLidarType", "version", "(Ljava/lang/Integer;)Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarType;", "parseMultiMap", "passFilePathtoPerception", "pathplanForTask", "starter", "end_pose_x", "end_pose_y", "processReflectorResult", "dist", "level", "flag", "readFileBytes", "zipInputStream", "Ljava/util/zip/ZipInputStream;", "reloadMap", "reloadMultiTopoMap", "removeLastNchars", "str", "n", "replaceString", "str1", "savePdMap", "setAccParam", "acc_type", "Lcom/pudutech/mirsdk/hardware/serialize/AccelerationType;", ES6Iterator.VALUE_PROPERTY, "setRgbdParkingMode", "into_park", "unregistListeners", "updateDebugSchRecv", "id", "pose", "vel", "updateDebugSchRecv$mircore_packRelease", "CoreInitModules", "FloorMap", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MirCoreImpl {
    public static final MirCoreImpl INSTANCE;
    private static String MAC = null;
    private static final String TAG;
    private static final AreaDetectStub areadetectStub;
    private static final ThreadSafeListener<CliffInfoListener> cliffDisListener;
    private static Map<String, Pair<Vector3d, Vector2d>> debug_sch_recv = null;
    private static final ThreadSafeListener<DockerEstimateTransformListener> dockerEstimateTransformListener;
    private static boolean downrgbd = false;
    private static final ThreadSafeListener<FaceDetectListener> faceDetectListener;
    private static double followLineDist = 0.0d;
    private static Job followLineJob = null;
    private static final HardwareLinker hardwareLinker;
    private static double inBrakeDist = 0.0d;
    private static boolean init_modules_status = false;
    private static MachineInfo.LidarType lidar_type = null;
    private static final LocalizationStub localization;
    private static int locateCameraType = 0;
    private static MachineModel machineType = null;
    private static int mapVersion = 0;
    private static final NavigationStub navigationer;
    private static double outBrakeDist = 0.0d;
    private static final String pdmapBackupPath;
    private static String pdmapFullName;
    private static Vector3d position;
    private static final ThreadSafeListener<ReflectorInfoListener> reflectorDisListener;
    private static boolean reflectorSwitch;
    private static int replanWaitTime;
    private static int roadblockClearTime;
    private static final ScheduleStub scheduleStub;
    private static double slowDownDist;
    private static Job slowDownJob;
    private static SmoothMode smoothMode;
    private static Vector2d speeds;
    private static long timeZero;
    private static boolean useRGBD;
    private static double wheel_space;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirCoreImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirCoreImpl$CoreInitModules;", "", "(Ljava/lang/String;I)V", "Scheudle", "Locate", "Perception", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum CoreInitModules {
        Scheudle,
        Locate,
        Perception
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineInfo.RGBDType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;

        static {
            $EnumSwitchMapping$0[MachineInfo.RGBDType.NODevice.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineInfo.RGBDType.SingleRealsenseDevice.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineInfo.RGBDType.TwoDevice.ordinal()] = 3;
            $EnumSwitchMapping$0[MachineInfo.RGBDType.ThreeDevice.ordinal()] = 4;
            $EnumSwitchMapping$0[MachineInfo.RGBDType.TwoDeviceD415.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 3;
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$2[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$2[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$2[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$3[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$3[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$4[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$4[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$5 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$5[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$5[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
        }
    }

    static {
        MirCoreImpl mirCoreImpl = new MirCoreImpl();
        INSTANCE = mirCoreImpl;
        TAG = mirCoreImpl.getClass().getSimpleName();
        hardwareLinker = new HardwareLinker();
        navigationer = new NavigationStub();
        localization = new LocalizationStub();
        scheduleStub = new ScheduleStub(hardwareLinker.getScheduleListeners());
        areadetectStub = new AreaDetectStub();
        MAC = ApiConstants.MAC_ADDRESS;
        machineType = MachineModel.Hls;
        wheel_space = 0.3836d;
        pdmapBackupPath = pdmapBackupPath;
        pdmapFullName = "";
        lidar_type = MachineInfo.LidarType.ECHOX;
        position = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        speeds = new Vector2d(0.0d, 0.0d, 3, null);
        debug_sch_recv = new LinkedHashMap();
        replanWaitTime = 30;
        roadblockClearTime = 60;
        faceDetectListener = new ThreadSafeListener<>();
        dockerEstimateTransformListener = new ThreadSafeListener<>();
        cliffDisListener = new ThreadSafeListener<>();
        reflectorDisListener = new ThreadSafeListener<>();
        inBrakeDist = 1.0d;
        followLineDist = 1.5d;
        slowDownDist = 2.0d;
        outBrakeDist = 1.2d;
    }

    private MirCoreImpl() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirCoreImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirCoreImpl$FloorMap;", "", "floor", "", "floor_index", "", "topo", "(Ljava/lang/String;ILjava/lang/String;)V", "getFloor", "()Ljava/lang/String;", "setFloor", "(Ljava/lang/String;)V", "getFloor_index", "()I", "setFloor_index", "(I)V", "getTopo", "setTopo", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class FloorMap {
        private String floor;
        private int floor_index;
        private String topo;

        public FloorMap() {
            this(null, 0, null, 7, null);
        }

        public static /* synthetic */ FloorMap copy$default(FloorMap floorMap, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = floorMap.floor;
            }
            if ((i2 & 2) != 0) {
                i = floorMap.floor_index;
            }
            if ((i2 & 4) != 0) {
                str2 = floorMap.topo;
            }
            return floorMap.copy(str, i, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getFloor() {
            return this.floor;
        }

        /* renamed from: component2, reason: from getter */
        public final int getFloor_index() {
            return this.floor_index;
        }

        /* renamed from: component3, reason: from getter */
        public final String getTopo() {
            return this.topo;
        }

        public final FloorMap copy(String floor, int floor_index, String topo) {
            return new FloorMap(floor, floor_index, topo);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FloorMap)) {
                return false;
            }
            FloorMap floorMap = (FloorMap) other;
            return Intrinsics.areEqual(this.floor, floorMap.floor) && this.floor_index == floorMap.floor_index && Intrinsics.areEqual(this.topo, floorMap.topo);
        }

        public int hashCode() {
            String str = this.floor;
            int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.floor_index) * 31;
            String str2 = this.topo;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "FloorMap(floor=" + this.floor + ", floor_index=" + this.floor_index + ", topo=" + this.topo + ")";
        }

        public FloorMap(String str, int i, String str2) {
            this.floor = str;
            this.floor_index = i;
            this.topo = str2;
        }

        public /* synthetic */ FloorMap(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? (String) null : str, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? (String) null : str2);
        }

        public final String getFloor() {
            return this.floor;
        }

        public final void setFloor(String str) {
            this.floor = str;
        }

        public final int getFloor_index() {
            return this.floor_index;
        }

        public final void setFloor_index(int i) {
            this.floor_index = i;
        }

        public final String getTopo() {
            return this.topo;
        }

        public final void setTopo(String str) {
            this.topo = str;
        }
    }

    public final NavigationStub getNavigationer() {
        return navigationer;
    }

    public final LocalizationStub getLocalization() {
        return localization;
    }

    public final ScheduleStub getScheduleStub() {
        return scheduleStub;
    }

    public final AreaDetectStub getAreadetectStub() {
        return areadetectStub;
    }

    public final boolean getInit_modules_status() {
        return init_modules_status;
    }

    public final void setInit_modules_status(boolean z) {
        init_modules_status = z;
    }

    public final Vector3d getPosition$mircore_packRelease() {
        return position;
    }

    public final void setPosition$mircore_packRelease(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        position = vector3d;
    }

    public final Vector2d getSpeeds$mircore_packRelease() {
        return speeds;
    }

    public final void setSpeeds$mircore_packRelease(Vector2d vector2d) {
        Intrinsics.checkParameterIsNotNull(vector2d, "<set-?>");
        speeds = vector2d;
    }

    public final Map<String, Pair<Vector3d, Vector2d>> getDebug_sch_recv$mircore_packRelease() {
        return debug_sch_recv;
    }

    public final void setDebug_sch_recv$mircore_packRelease(Map<String, Pair<Vector3d, Vector2d>> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        debug_sch_recv = map;
    }

    public final SmoothMode getSmoothMode() {
        return smoothMode;
    }

    public final void setSmoothMode(SmoothMode smoothMode2) {
        smoothMode = smoothMode2;
    }

    public final int getReplanWaitTime() {
        return replanWaitTime;
    }

    public final void setReplanWaitTime(int i) {
        replanWaitTime = i;
    }

    public final int getRoadblockClearTime() {
        return roadblockClearTime;
    }

    public final void setRoadblockClearTime(int i) {
        roadblockClearTime = i;
    }

    public final long getTimeZero() {
        return timeZero;
    }

    public final void setTimeZero(long j) {
        timeZero = j;
    }

    public final ThreadSafeListener<FaceDetectListener> getFaceDetectListener$mircore_packRelease() {
        return faceDetectListener;
    }

    public final ThreadSafeListener<DockerEstimateTransformListener> getDockerEstimateTransformListener$mircore_packRelease() {
        return dockerEstimateTransformListener;
    }

    public final ThreadSafeListener<CliffInfoListener> getCliffDisListener$mircore_packRelease() {
        return cliffDisListener;
    }

    public final ThreadSafeListener<ReflectorInfoListener> getReflectorDisListener$mircore_packRelease() {
        return reflectorDisListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x00d7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object initializationCore(int i, String str, List<String> list, InitServiceListener initServiceListener, Continuation<? super Unit> continuation) {
        MirCoreImpl$initializationCore$1 mirCoreImpl$initializationCore$1;
        Object coroutine_suspended;
        int i2;
        int i3;
        List<String> list2;
        String str2;
        MirCoreImpl mirCoreImpl;
        int i4;
        Object withTimeoutOrNull;
        int i5;
        MachineModel machineModel;
        Integer num;
        boolean z;
        int i6;
        int parseInt;
        boolean z2;
        FileOutputStream fileOutputStream;
        LidarInterface lidarInterface;
        MachineInfo machineInfo;
        HashMap<MachineInfo.FloatInfo, Float> floatData;
        Float f;
        Double boxDouble;
        MachineInfo machineInfo2;
        ProductMachineType productType;
        InitServiceListener initServiceListener2 = initServiceListener;
        if (continuation instanceof MirCoreImpl$initializationCore$1) {
            mirCoreImpl$initializationCore$1 = (MirCoreImpl$initializationCore$1) continuation;
            if ((mirCoreImpl$initializationCore$1.label & Integer.MIN_VALUE) != 0) {
                mirCoreImpl$initializationCore$1.label -= Integer.MIN_VALUE;
                Object obj = mirCoreImpl$initializationCore$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = mirCoreImpl$initializationCore$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    pdmapFullName = str;
                    init_modules_status = false;
                    initServiceListener2.initCoreServiceState(CoreInitStep.LinkHardware, CoreInitState.Running, "");
                    HardwareLinker hardwareLinker2 = hardwareLinker;
                    Context context = MirCoreService.INSTANCE.getContext().get();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "MirCoreService.context.get()!!");
                    mirCoreImpl$initializationCore$1.L$0 = this;
                    i3 = i;
                    mirCoreImpl$initializationCore$1.I$0 = i3;
                    mirCoreImpl$initializationCore$1.L$1 = str;
                    list2 = list;
                    mirCoreImpl$initializationCore$1.L$2 = list2;
                    mirCoreImpl$initializationCore$1.L$3 = initServiceListener2;
                    mirCoreImpl$initializationCore$1.I$1 = 0;
                    mirCoreImpl$initializationCore$1.label = 1;
                    if (hardwareLinker2.createLinker(context, mirCoreImpl$initializationCore$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    str2 = str;
                    mirCoreImpl = this;
                    i4 = 0;
                } else {
                    if (i2 != 1) {
                        if (i2 == 2) {
                            int i7 = mirCoreImpl$initializationCore$1.I$1;
                            initServiceListener2 = (InitServiceListener) mirCoreImpl$initializationCore$1.L$3;
                            List<String> list3 = (List) mirCoreImpl$initializationCore$1.L$2;
                            str2 = (String) mirCoreImpl$initializationCore$1.L$1;
                            i3 = mirCoreImpl$initializationCore$1.I$0;
                            MirCoreImpl mirCoreImpl2 = (MirCoreImpl) mirCoreImpl$initializationCore$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            i5 = i7;
                            mirCoreImpl = mirCoreImpl2;
                            list2 = list3;
                            withTimeoutOrNull = obj;
                            if (!(!Intrinsics.areEqual((Boolean) withTimeoutOrNull, Boxing.boxBoolean(true)))) {
                                Pdlog.m3274e(TAG, "core link to hardware failed");
                                initServiceListener2.initCoreServiceState(CoreInitStep.LinkHardware, CoreInitState.Fail, "link hardware failed");
                            } else {
                                initServiceListener2.initCoreServiceState(CoreInitStep.LinkHardware, CoreInitState.Success, "");
                                mirCoreImpl.getMac();
                                mirCoreImpl.unregistListeners();
                                HardwareInterface hardwareService = hardwareLinker.getHardwareService();
                                if (hardwareService == null || (machineInfo2 = hardwareService.getMachineInfo()) == null || (productType = machineInfo2.getProductType()) == null || (machineModel = productType.getModel()) == null) {
                                    machineModel = MachineModel.Hls;
                                }
                                machineType = machineModel;
                                HardwareInterface hardwareService2 = hardwareLinker.getHardwareService();
                                wheel_space = (hardwareService2 == null || (machineInfo = hardwareService2.getMachineInfo()) == null || (floatData = machineInfo.getFloatData()) == null || (f = floatData.get(MachineInfo.FloatInfo.machineWheelbase)) == null || (boxDouble = Boxing.boxDouble((double) f.floatValue())) == null) ? 0.0d : boxDouble.doubleValue();
                                HardwareInterface hardwareService3 = hardwareLinker.getHardwareService();
                                if (hardwareService3 == null || (lidarInterface = hardwareService3.getLidarInterface()) == null) {
                                    num = null;
                                } else {
                                    HardwareInterface hardwareService4 = hardwareLinker.getHardwareService();
                                    num = Boxing.boxInt(lidarInterface.getNewLidarVersion(hardwareService4 != null ? hardwareService4.getMachineInfo() : null));
                                }
                                lidar_type = mirCoreImpl.parseIntToLidarType(num);
                                Pdlog.m3273d(TAG, "machineType is " + machineType);
                                Pdlog.m3273d(TAG, "robot id " + MAC);
                                Pdlog.m3273d(TAG, "lidar type is " + lidar_type.name());
                                initServiceListener2.initCoreServiceState(CoreInitStep.CoreConfig, CoreInitState.Running, "");
                                MachineModel machineModel2 = machineType;
                                double d = wheel_space;
                                HardwareInterface hardwareService5 = hardwareLinker.getHardwareService();
                                if (hardwareService5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                mirCoreImpl.initSubmodules(machineModel2, d, hardwareService5.getMachineInfo().getRGBDMode());
                                Perception.INSTANCE.setLidarType(lidar_type);
                                Pdlog.m3273d(TAG, "finish create core modules");
                                initServiceListener2.initCoreServiceState(CoreInitStep.CoreConfig, CoreInitState.Success, "");
                                HardwareInterface hardwareService6 = hardwareLinker.getHardwareService();
                                if (hardwareService6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                if (hardwareService6.getMachineInfo().getMonocularDeviceType() != MachineInfo.MonocularType.NoDevice) {
                                    mirCoreImpl.initVisionModel();
                                }
                                if (hardwareLinker.isRGBDEnabled()) {
                                    Pdlog.m3273d(TAG, "Enable RGBD sensor");
                                    if (!new File("/sdcard/pudu/config/rgbd.json").exists()) {
                                        if (machineType == MachineModel.Peanut || machineType == MachineModel.Firefox || machineType == MachineModel.Phoenix) {
                                            z = true;
                                            Pdlog.m3273d(TAG, "single rgbd machine, not need rgbd.json");
                                            if (Perception.INSTANCE.enableRGBD("/sdcard/pudu/config/rgbd.json")) {
                                                useRGBD = z;
                                                navigationer.setUseRGBD(useRGBD);
                                            }
                                            if (new File("sdcard/pudu/config/feasibal_segment.config").exists()) {
                                                File file = new File("sdcard/pudu/config/");
                                                if (file.exists()) {
                                                    z2 = false;
                                                } else {
                                                    z2 = false;
                                                    Pdlog.m3273d(TAG, "create folder: ", file);
                                                    file.mkdir();
                                                }
                                                String str3 = Perception.INSTANCE.switchFeasibalRegionSeg(z2) ? "1" : "0";
                                                FilesKt.writeText$default(new File("sdcard/pudu/config/feasibal_segment.config"), str3, null, 2, null);
                                                parseInt = Integer.parseInt(str3);
                                                i6 = 1;
                                            } else {
                                                i6 = 1;
                                                parseInt = Integer.parseInt(FilesKt.readText$default(new File("sdcard/pudu/config/feasibal_segment.config"), null, 1, null));
                                            }
                                            if (parseInt != i6) {
                                                String str4 = TAG;
                                                Object[] objArr = new Object[i6];
                                                objArr[0] = "feasibal segment use line segment";
                                                Pdlog.m3273d(str4, objArr);
                                                if (!Perception.INSTANCE.switchFeasibalRegionSeg(false)) {
                                                    String str5 = TAG;
                                                    Object[] objArr2 = new Object[i6];
                                                    objArr2[0] = "switchFeasibalRegionSeg error, use cape segment";
                                                    Pdlog.m3273d(str5, objArr2);
                                                    Perception.INSTANCE.switchFeasibalRegionSeg(i6);
                                                    FilesKt.writeText$default(new File("sdcard/pudu/config/feasibal_segment.config"), "0", null, 2, null);
                                                }
                                            } else {
                                                String str6 = TAG;
                                                Object[] objArr3 = new Object[i6];
                                                objArr3[0] = "feasibal segment use cape segment";
                                                Pdlog.m3273d(str6, objArr3);
                                                Perception.INSTANCE.switchFeasibalRegionSeg(i6);
                                            }
                                        } else {
                                            File file2 = new File("/sdcard/pudu/map/");
                                            if (!file2.exists()) {
                                                file2.mkdirs();
                                            }
                                            if (!new File("/sdcard/PuduRobotMap/rgbd.json").exists()) {
                                                initServiceListener2.initCoreServiceState(CoreInitStep.RGBDConfig, CoreInitState.Fail, "Not find RGBD config file");
                                            } else {
                                                FileOutputStream fileOutputStream2 = (FileOutputStream) null;
                                                InputStream inputStream = (InputStream) null;
                                                try {
                                                    fileOutputStream = new FileOutputStream("/sdcard/pudu/config/rgbd.json");
                                                    try {
                                                        FileInputStream fileInputStream = new FileInputStream("/sdcard/PuduRobotMap/rgbd.json");
                                                        try {
                                                            byte[] bArr = new byte[256];
                                                            while (true) {
                                                                int read = fileInputStream.read(bArr);
                                                                if (read <= 0) {
                                                                    break;
                                                                }
                                                                fileOutputStream.write(bArr, 0, read);
                                                            }
                                                            fileOutputStream.close();
                                                            fileInputStream.close();
                                                        } catch (Throwable th) {
                                                            th = th;
                                                            inputStream = fileInputStream;
                                                            if (fileOutputStream != null) {
                                                                fileOutputStream.close();
                                                            }
                                                            if (inputStream != null) {
                                                                inputStream.close();
                                                            }
                                                            throw th;
                                                        }
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                    }
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    fileOutputStream = fileOutputStream2;
                                                }
                                            }
                                        }
                                    }
                                    z = true;
                                    if (Perception.INSTANCE.enableRGBD("/sdcard/pudu/config/rgbd.json")) {
                                    }
                                    if (new File("sdcard/pudu/config/feasibal_segment.config").exists()) {
                                    }
                                    if (parseInt != i6) {
                                    }
                                }
                                initServiceListener2.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Running, "");
                                if (mirCoreImpl.loadMapAndConfig(i3, str2, list2, initServiceListener2)) {
                                    Pdlog.m3273d(TAG, "finish map loading");
                                    if (mirCoreImpl.initializer()) {
                                        navigationer.confirmAccParam(machineType);
                                        navigationer.addCoverAround(RunParamUtils.INSTANCE.getInitRunParam("cover_around"));
                                        navigationer.updateTrayDis(RunParamUtils.INSTANCE.getInitRunParamDouble("tray_dis"));
                                        if (machineType == MachineModel.Hls || machineType == MachineModel.BellaBot) {
                                            smoothMode = RunParamUtils.INSTANCE.getInitSmoothMode(machineType);
                                            NavigationStub navigationStub = navigationer;
                                            SmoothMode smoothMode2 = smoothMode;
                                            if (smoothMode2 == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            navigationStub.updateSmoothMode(smoothMode2);
                                        }
                                        timeZero = System.currentTimeMillis();
                                        localization.addSpeedListener(AlcsConstant.METHOD_DOMAIN_CORE, new Function1<Vector2d, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$2
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Vector2d vector2d) {
                                                invoke2(vector2d);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Vector2d it) {
                                                Intrinsics.checkParameterIsNotNull(it, "it");
                                                MirCoreImpl.INSTANCE.setSpeeds$mircore_packRelease(it);
                                            }
                                        });
                                        localization.addPoseListener(AlcsConstant.METHOD_DOMAIN_CORE, new Function2<Vector3d, Vector3d, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$3
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d, Vector3d vector3d2) {
                                                invoke2(vector3d, vector3d2);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Vector3d p0, Vector3d vector3d) {
                                                Intrinsics.checkParameterIsNotNull(p0, "p0");
                                                Intrinsics.checkParameterIsNotNull(vector3d, "<anonymous parameter 1>");
                                                Perception.INSTANCE.updateRobotPose(p0);
                                                synchronized (MirCoreImpl.INSTANCE.getPosition$mircore_packRelease()) {
                                                    MirCoreImpl.INSTANCE.setPosition$mircore_packRelease(p0);
                                                    Unit unit = Unit.INSTANCE;
                                                }
                                            }
                                        });
                                        localization.addPoseListener("area", new Function2<Vector3d, Vector3d, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$4
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d, Vector3d vector3d2) {
                                                invoke2(vector3d, vector3d2);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Vector3d p0, Vector3d vector3d) {
                                                Intrinsics.checkParameterIsNotNull(p0, "p0");
                                                Intrinsics.checkParameterIsNotNull(vector3d, "<anonymous parameter 1>");
                                                MapAreaDetection.INSTANCE.updateRobotPose(p0);
                                            }
                                        });
                                        localization.addOdomListener(AlcsConstant.METHOD_DOMAIN_CORE, new Function1<Vector3d, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$5
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d) {
                                                invoke2(vector3d);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Vector3d it) {
                                                Intrinsics.checkParameterIsNotNull(it, "it");
                                                Perception.INSTANCE.updateRobotOdom(it);
                                            }
                                        });
                                        localization.addSetExposureInterface(new Function1<Integer, Boolean>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$6
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Boolean invoke(Integer num2) {
                                                return Boolean.valueOf(invoke(num2.intValue()));
                                            }

                                            public final boolean invoke(int i8) {
                                                HardwareLinker hardwareLinker3;
                                                MirCoreImpl mirCoreImpl3 = MirCoreImpl.INSTANCE;
                                                hardwareLinker3 = MirCoreImpl.hardwareLinker;
                                                return hardwareLinker3.setExposure(i8);
                                            }
                                        });
                                        localization.addGetExposureInterface(new Function0<Integer>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$7
                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Integer invoke() {
                                                return Integer.valueOf(invoke2());
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final int invoke2() {
                                                HardwareLinker hardwareLinker3;
                                                MirCoreImpl mirCoreImpl3 = MirCoreImpl.INSTANCE;
                                                hardwareLinker3 = MirCoreImpl.hardwareLinker;
                                                return hardwareLinker3.getExposure();
                                            }
                                        });
                                        MapAreaDetection.INSTANCE.addMapAreaInfoAddresListeners("nav", new Function1<Long, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$8
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                Navigation.INSTANCE.getMapAreaInfo(j);
                                            }
                                        });
                                        MapAreaDetection.INSTANCE.addMapAreaInfoAddresListeners("perception", new Function1<Long, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$9
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                Perception.INSTANCE.updateRobotMapArea(j);
                                            }
                                        });
                                        localization.addSaveMapInterface(new Function0<Boolean>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializationCore$10
                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Boolean invoke() {
                                                return Boolean.valueOf(invoke2());
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final boolean invoke2() {
                                                boolean savePdMap;
                                                savePdMap = MirCoreImpl.INSTANCE.savePdMap();
                                                return savePdMap;
                                            }
                                        });
                                        i5 = 1;
                                    }
                                }
                            }
                            initServiceListener2.initCoreServiceState(CoreInitStep.Finish, i5 == 0 ? CoreInitState.Success : CoreInitState.Fail, i5 == 0 ? "Core Init Failed" : "");
                            mirCoreImpl.loadReflectorParam();
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    i4 = mirCoreImpl$initializationCore$1.I$1;
                    initServiceListener2 = (InitServiceListener) mirCoreImpl$initializationCore$1.L$3;
                    List<String> list4 = (List) mirCoreImpl$initializationCore$1.L$2;
                    String str7 = (String) mirCoreImpl$initializationCore$1.L$1;
                    int i8 = mirCoreImpl$initializationCore$1.I$0;
                    mirCoreImpl = (MirCoreImpl) mirCoreImpl$initializationCore$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    list2 = list4;
                    str2 = str7;
                    i3 = i8;
                }
                MirCoreImpl$initializationCore$result$1 mirCoreImpl$initializationCore$result$1 = new MirCoreImpl$initializationCore$result$1(null);
                mirCoreImpl$initializationCore$1.L$0 = mirCoreImpl;
                mirCoreImpl$initializationCore$1.I$0 = i3;
                mirCoreImpl$initializationCore$1.L$1 = str2;
                mirCoreImpl$initializationCore$1.L$2 = list2;
                mirCoreImpl$initializationCore$1.L$3 = initServiceListener2;
                mirCoreImpl$initializationCore$1.I$1 = i4;
                mirCoreImpl$initializationCore$1.label = 2;
                withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(2000L, mirCoreImpl$initializationCore$result$1, mirCoreImpl$initializationCore$1);
                if (withTimeoutOrNull != coroutine_suspended) {
                    return coroutine_suspended;
                }
                i5 = i4;
                if (!(!Intrinsics.areEqual((Boolean) withTimeoutOrNull, Boxing.boxBoolean(true)))) {
                }
                initServiceListener2.initCoreServiceState(CoreInitStep.Finish, i5 == 0 ? CoreInitState.Success : CoreInitState.Fail, i5 == 0 ? "Core Init Failed" : "");
                mirCoreImpl.loadReflectorParam();
                return Unit.INSTANCE;
            }
        }
        mirCoreImpl$initializationCore$1 = new MirCoreImpl$initializationCore$1(this, continuation);
        Object obj2 = mirCoreImpl$initializationCore$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = mirCoreImpl$initializationCore$1.label;
        if (i2 != 0) {
        }
        MirCoreImpl$initializationCore$result$1 mirCoreImpl$initializationCore$result$12 = new MirCoreImpl$initializationCore$result$1(null);
        mirCoreImpl$initializationCore$1.L$0 = mirCoreImpl;
        mirCoreImpl$initializationCore$1.I$0 = i3;
        mirCoreImpl$initializationCore$1.L$1 = str2;
        mirCoreImpl$initializationCore$1.L$2 = list2;
        mirCoreImpl$initializationCore$1.L$3 = initServiceListener2;
        mirCoreImpl$initializationCore$1.I$1 = i4;
        mirCoreImpl$initializationCore$1.label = 2;
        withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(2000L, mirCoreImpl$initializationCore$result$12, mirCoreImpl$initializationCore$1);
        if (withTimeoutOrNull != coroutine_suspended) {
        }
    }

    public final boolean reloadMap(int defFloorIndex, String pdmap, List<String> floors_map) {
        Intrinsics.checkParameterIsNotNull(pdmap, "pdmap");
        Pdlog.m3273d(TAG, "reloadMap");
        pdmapFullName = pdmap;
        if (init_modules_status) {
            unregistListeners();
            hardwareLinker.resetLocalizationModuleInited(false);
            if (loadMapAndConfig(defFloorIndex, pdmap, floors_map, null)) {
                Perception.INSTANCE.addVirtualWall(ScheduleMaster.INSTANCE.getVirtualWalls());
                Perception.INSTANCE.addCostmapErrorListener("costmap_status", new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        MirCoreImpl.INSTANCE.getNavigationer().updateCostmapStatus(i);
                    }
                });
                Perception.INSTANCE.registerLaserListener(new Function2<String, ILidarData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, ILidarData iLidarData) {
                        invoke2(str, iLidarData);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String name, ILidarData iLidarData) {
                        HardwareLinker hardwareLinker2;
                        LidarInterface lidarInterface;
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
                        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                        hardwareLinker2 = MirCoreImpl.hardwareLinker;
                        HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                        if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.addDataListener(name, iLidarData);
                    }
                });
                Perception.INSTANCE.registerLDLaserListener(new Function2<String, ILidarData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$3
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, ILidarData iLidarData) {
                        invoke2(str, iLidarData);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String name, ILidarData iLidarData) {
                        HardwareLinker hardwareLinker2;
                        LidarInterface lidarInterface;
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
                        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                        hardwareLinker2 = MirCoreImpl.hardwareLinker;
                        HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                        if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.addSecondLidarDataListener(name, iLidarData);
                    }
                });
                Perception.INSTANCE.registerCameraListener(new Function2<String, IMarkerCameraData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$4
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, IMarkerCameraData iMarkerCameraData) {
                        invoke2(str, iMarkerCameraData);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String name, IMarkerCameraData iMarkerCameraData) {
                        HardwareLinker hardwareLinker2;
                        CameraInterface camera;
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        Intrinsics.checkParameterIsNotNull(iMarkerCameraData, "iMarkerCameraData");
                        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                        hardwareLinker2 = MirCoreImpl.hardwareLinker;
                        HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                        if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                            return;
                        }
                        camera.addMonocularCameraListener(name, iMarkerCameraData);
                    }
                });
                if (useRGBD) {
                    Pdlog.m3273d(TAG, "registerCenterRGBDListener");
                    Perception.INSTANCE.registerCenterRGBDListener(new Function2<String, ICenterRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$5
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, ICenterRgbdData iCenterRgbdData) {
                            invoke2(str, iCenterRgbdData);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String name, ICenterRgbdData iCenterRgbdData) {
                            HardwareLinker hardwareLinker2;
                            RGBDInterface rGBDInterface;
                            Intrinsics.checkParameterIsNotNull(name, "name");
                            Intrinsics.checkParameterIsNotNull(iCenterRgbdData, "iCenterRgbdData");
                            MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                            hardwareLinker2 = MirCoreImpl.hardwareLinker;
                            HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                            if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                                return;
                            }
                            rGBDInterface.addCenterRgbdListener(name, iCenterRgbdData);
                        }
                    });
                    Pdlog.m3273d(TAG, "registerDownRGBDListener");
                    Perception.INSTANCE.registerDownRGBDListener(new Function2<String, IDownRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$6
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, IDownRgbdData iDownRgbdData) {
                            invoke2(str, iDownRgbdData);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String name, IDownRgbdData iDownRgbdData) {
                            HardwareLinker hardwareLinker2;
                            RGBDInterface rGBDInterface;
                            Intrinsics.checkParameterIsNotNull(name, "name");
                            Intrinsics.checkParameterIsNotNull(iDownRgbdData, "iDownRgbdData");
                            MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                            hardwareLinker2 = MirCoreImpl.hardwareLinker;
                            HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                            if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                                return;
                            }
                            rGBDInterface.addDownRgbdListener(name, iDownRgbdData);
                        }
                    });
                    Pdlog.m3273d(TAG, "registerLeftRGBDListener");
                    Perception.INSTANCE.registerLeftRGBDListener(new Function2<String, ILeftRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$7
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, ILeftRgbdData iLeftRgbdData) {
                            invoke2(str, iLeftRgbdData);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String name, ILeftRgbdData iLeftRgbdData) {
                            HardwareLinker hardwareLinker2;
                            RGBDInterface rGBDInterface;
                            Intrinsics.checkParameterIsNotNull(name, "name");
                            Intrinsics.checkParameterIsNotNull(iLeftRgbdData, "iLeftRgbdData");
                            MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                            hardwareLinker2 = MirCoreImpl.hardwareLinker;
                            HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                            if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                                return;
                            }
                            rGBDInterface.addLeftRgbdListener(name, iLeftRgbdData);
                        }
                    });
                    Pdlog.m3273d(TAG, "registerRightRGBDListenner");
                    Perception.INSTANCE.registerRightRGBDListenner(new Function2<String, IRightRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$reloadMap$8
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, IRightRgbdData iRightRgbdData) {
                            invoke2(str, iRightRgbdData);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String name, IRightRgbdData iRightRgbdData) {
                            HardwareLinker hardwareLinker2;
                            RGBDInterface rGBDInterface;
                            Intrinsics.checkParameterIsNotNull(name, "name");
                            Intrinsics.checkParameterIsNotNull(iRightRgbdData, "iRightRgbdData");
                            MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                            hardwareLinker2 = MirCoreImpl.hardwareLinker;
                            HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                            if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                                return;
                            }
                            rGBDInterface.addRightRgbdListener(name, iRightRgbdData);
                        }
                    });
                }
                hardwareLinker.addLinker();
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean savePdMap() {
        if (pdmapFullName.equals("")) {
            Pdlog.m3273d(TAG, "not set pdmap name");
            return false;
        }
        try {
            Pdlog.m3273d(TAG, "check map start");
            File file = new File("/sdcard/pudu/log/optemap.pgm");
            if (!file.exists()) {
                Pdlog.m3273d(TAG, "no new optemap.pgm");
                return false;
            }
            File file2 = new File("/sdcard/pudu/log/optemap.yaml");
            if (!file2.exists()) {
                Pdlog.m3273d(TAG, "no new optemap.yaml");
                return false;
            }
            Pdlog.m3273d(TAG, "backup map start");
            File file3 = new File(String.valueOf(pdmapBackupPath));
            if (!file3.exists()) {
                file3.mkdir();
            }
            Tools.execCommand("cp " + pdmapFullName + ' ' + pdmapBackupPath + '/', false);
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
            String str = pdmapFullName;
            int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) pdmapFullName, '/', 0, false, 6, (Object) null) + 1;
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring = str.substring(lastIndexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            Pdlog.m3273d(TAG, "backup map " + substring + " end");
            String str2 = pdmapBackupPath + '/' + substring;
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(pdmapFullName));
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    zipOutputStream.putNextEntry(new ZipEntry(nextEntry.getName()));
                    if (Intrinsics.areEqual(nextEntry.getName(), "optemap.pgm")) {
                        FileInputStream fileInputStream = new FileInputStream("/sdcard/pudu/log/optemap.pgm");
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read);
                        }
                    } else if (Intrinsics.areEqual(nextEntry.getName(), "optemap.yaml")) {
                        FileInputStream fileInputStream2 = new FileInputStream("/sdcard/pudu/log/optemap.yaml");
                        while (true) {
                            int read2 = fileInputStream2.read(bArr);
                            if (read2 <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read2);
                        }
                    } else {
                        while (true) {
                            int read3 = zipInputStream.read(bArr);
                            if (read3 <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read3);
                        }
                    }
                    zipOutputStream.closeEntry();
                } else {
                    zipOutputStream.close();
                    zipInputStream.close();
                    file.delete();
                    file2.delete();
                    Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
                    Pdlog.m3273d(TAG, "updated optemap.pgm");
                    return true;
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "save error " + Log.getStackTraceString(e));
            return false;
        }
    }

    private final void unregistListeners() {
        Perception.INSTANCE.removeCostmapErrorListener("costmap_status");
        Perception.INSTANCE.unregisterLaserListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                LidarInterface lidarInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                    return;
                }
                lidarInterface.removeDataListener(name);
            }
        });
        Perception.INSTANCE.unregisterLDLaserListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                LidarInterface lidarInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                    return;
                }
                lidarInterface.removeSecondLidarDataListener(name);
            }
        });
        Perception.INSTANCE.unregisterCameraListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.removeMonocularCameraListener(name);
            }
        });
        Perception.INSTANCE.unregisterLeftRGBDListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.removeLeftRgbdListener(name);
            }
        });
        Perception.INSTANCE.unregisterRightRGBDListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$5
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.removeRightRgbdListener(name);
            }
        });
        Perception.INSTANCE.unregisterCenterRGBDListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.removeCenterRgbdListener(name);
            }
        });
        Perception.INSTANCE.unregisterDownRGBDListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$7
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.removeDownRgbdListener(name);
            }
        });
        hardwareLinker.removeLinker();
        localization.unregistSensorListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService != null) {
                    hardwareService.removeSensorListener(name);
                }
            }
        });
        localization.unregistMarkerFrameListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$9
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.removeMarkerCameraListener(name);
            }
        });
        localization.unregistMonocularCameraListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$10
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.removeMonocularCameraListener(name);
            }
        });
        localization.unregisterLaserListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$unregistListeners$11
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                LidarInterface lidarInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                    return;
                }
                lidarInterface.removeDataListener(name);
            }
        });
    }

    private final void initSubmodules(MachineModel productType, double wheel_space2, MachineInfo.RGBDType rgbdType) {
        Perception.INSTANCE.initialize(productType);
        int i = WhenMappings.$EnumSwitchMapping$0[rgbdType.ordinal()];
        int i2 = 2;
        if (i == 1) {
            i2 = 0;
        } else if (i == 2) {
            i2 = 1;
        } else if (i != 3 && i == 4) {
            i2 = 3;
        }
        Navigation.INSTANCE.createModule(productType, wheel_space2, i2);
        PlannerParamUtils.INSTANCE.initPlannerParams();
        ScheduleMaster.INSTANCE.createModule(ScheduleMaster.INSTANCE);
        if (Intrinsics.areEqual(MAC, ApiConstants.MAC_ADDRESS)) {
            getMac();
        }
        Pdlog.m3273d(TAG, "init schedule info with robot id " + MAC);
        ScheduleMaster.INSTANCE.setRobotId(MAC);
    }

    private final boolean initializer() {
        Perception.INSTANCE.addVirtualWall(ScheduleMaster.INSTANCE.getVirtualWalls());
        Perception.INSTANCE.addCostmapErrorListener("costmap_status", new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                MirCoreImpl.INSTANCE.getNavigationer().updateCostmapStatus(i);
            }
        });
        Perception.INSTANCE.registerLaserListener(new Function2<String, ILidarData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, ILidarData iLidarData) {
                invoke2(str, iLidarData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, ILidarData iLidarData) {
                HardwareLinker hardwareLinker2;
                LidarInterface lidarInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                    return;
                }
                lidarInterface.addDataListener(name, iLidarData);
            }
        });
        Perception.INSTANCE.registerLDLaserListener(new Function2<String, ILidarData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, ILidarData iLidarData) {
                invoke2(str, iLidarData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, ILidarData iLidarData) {
                HardwareLinker hardwareLinker2;
                LidarInterface lidarInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                    return;
                }
                lidarInterface.addSecondLidarDataListener(name, iLidarData);
            }
        });
        Perception.INSTANCE.registerCameraListener(new Function2<String, IMarkerCameraData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, IMarkerCameraData iMarkerCameraData) {
                invoke2(str, iMarkerCameraData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, IMarkerCameraData iMarkerCameraData) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iMarkerCameraData, "iMarkerCameraData");
                MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                hardwareLinker2 = MirCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.addMonocularCameraListener(name, iMarkerCameraData);
            }
        });
        if (useRGBD) {
            Pdlog.m3273d(TAG, "registerCenterRGBDListener");
            Perception.INSTANCE.registerCenterRGBDListener(new Function2<String, ICenterRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$5
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, ICenterRgbdData iCenterRgbdData) {
                    invoke2(str, iCenterRgbdData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, ICenterRgbdData iCenterRgbdData) {
                    HardwareLinker hardwareLinker2;
                    RGBDInterface rGBDInterface;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(iCenterRgbdData, "iCenterRgbdData");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                        return;
                    }
                    rGBDInterface.addCenterRgbdListener(name, iCenterRgbdData);
                }
            });
            Pdlog.m3273d(TAG, "registerDownRGBDListener");
            Perception.INSTANCE.registerDownRGBDListener(new Function2<String, IDownRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, IDownRgbdData iDownRgbdData) {
                    invoke2(str, iDownRgbdData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, IDownRgbdData iDownRgbdData) {
                    HardwareLinker hardwareLinker2;
                    RGBDInterface rGBDInterface;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(iDownRgbdData, "iDownRgbdData");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                        return;
                    }
                    rGBDInterface.addDownRgbdListener(name, iDownRgbdData);
                }
            });
            Pdlog.m3273d(TAG, "registerLeftRGBDListener");
            Perception.INSTANCE.registerLeftRGBDListener(new Function2<String, ILeftRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$7
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, ILeftRgbdData iLeftRgbdData) {
                    invoke2(str, iLeftRgbdData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, ILeftRgbdData iLeftRgbdData) {
                    HardwareLinker hardwareLinker2;
                    RGBDInterface rGBDInterface;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(iLeftRgbdData, "iLeftRgbdData");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                        return;
                    }
                    rGBDInterface.addLeftRgbdListener(name, iLeftRgbdData);
                }
            });
            Pdlog.m3273d(TAG, "registerRightRGBDListenner");
            Perception.INSTANCE.registerRightRGBDListenner(new Function2<String, IRightRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$8
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, IRightRgbdData iRightRgbdData) {
                    invoke2(str, iRightRgbdData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, IRightRgbdData iRightRgbdData) {
                    HardwareLinker hardwareLinker2;
                    RGBDInterface rGBDInterface;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(iRightRgbdData, "iRightRgbdData");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                        return;
                    }
                    rGBDInterface.addRightRgbdListener(name, iRightRgbdData);
                }
            });
        }
        if (hardwareLinker.addLinker()) {
            localization.addSpeedListener("nav", new Function1<Vector2d, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$9
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Vector2d vector2d) {
                    invoke2(vector2d);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Vector2d it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    MirCoreImpl.INSTANCE.getNavigationer().updateSpeeds$mircore_packRelease(it);
                }
            });
            localization.addPoseListener("nav", new Function2<Vector3d, Vector3d, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$10
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d, Vector3d vector3d2) {
                    invoke2(vector3d, vector3d2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Vector3d pose, Vector3d dir) {
                    Intrinsics.checkParameterIsNotNull(pose, "pose");
                    Intrinsics.checkParameterIsNotNull(dir, "dir");
                    MirCoreImpl.INSTANCE.getNavigationer().updatePose$mircore_packRelease(pose);
                    MirCoreImpl.INSTANCE.getNavigationer().updateDirection$mircore_packRelease(dir);
                }
            });
            localization.addOdomListener("nav", new Function1<Vector3d, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$initializer$11
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d) {
                    invoke2(vector3d);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Vector3d it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    synchronized (it) {
                        MirCoreImpl.INSTANCE.getNavigationer().updateRobotOdom(it);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            });
            Pdlog.m3273d(TAG, "finish create localization, success init LocalImpl");
            return true;
        }
        Pdlog.m3277w(TAG, "link to hardware failed");
        return false;
    }

    public final double pathplanForTask(Vector3d starter, double end_pose_x, double end_pose_y) {
        Vector3d vector3d;
        if (init_modules_status) {
            synchronized (position) {
                vector3d = position;
            }
            if (starter == null) {
                starter = vector3d;
            }
            return ScheduleMaster.INSTANCE.globalPlanWeight(starter.getX(), starter.getY(), end_pose_x, end_pose_y);
        }
        Pdlog.m3274e(TAG, "please first init core");
        return 0.0d;
    }

    private final void getMac() {
        String str;
        HardwareInterface hardwareService = hardwareLinker.getHardwareService();
        if (hardwareService == null || (str = hardwareService.getWifiMac()) == null) {
            str = ApiConstants.MAC_ADDRESS;
        }
        MAC = str;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0147. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [byte[], T] */
    /* JADX WARN: Type inference failed for: r0v17, types: [byte[], T] */
    /* JADX WARN: Type inference failed for: r0v20, types: [byte[], T] */
    /* JADX WARN: Type inference failed for: r0v8, types: [T, kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r2v10, types: [T, kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r2v13, types: [T, kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v39, types: [T] */
    /* JADX WARN: Type inference failed for: r3v43, types: [com.pudutech.mirsdk.mircore.PuduLocateInterface$LocateCase, T] */
    /* JADX WARN: Type inference failed for: r3v49, types: [byte[], T] */
    /* JADX WARN: Type inference failed for: r3v51, types: [byte[], T] */
    /* JADX WARN: Type inference failed for: r3v53, types: [byte[], T] */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v8, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.pudutech.mirsdk.mircore.PuduLocateInterface$LocateCase, T] */
    /* JADX WARN: Type inference failed for: r8v1, types: [byte[], T] */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24, types: [com.pudutech.mirsdk.mircore.InitServiceListener] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v37 */
    /* JADX WARN: Type inference failed for: r8v38 */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.pudutech.mirsdk.mircore.InitServiceListener] */
    /* JADX WARN: Type inference failed for: r8v5 */
    private final boolean loadMapAndConfig(int defFloorIndex, String pdmap, List<String> floors_map, InitServiceListener initListener) {
        ?? r8;
        ?? launch$default;
        ?? launch$default2;
        ?? launch$default3;
        Ref.ObjectRef objectRef;
        String str;
        String str2;
        String str3;
        String str4;
        byte[] readFileBytes;
        ?? r3;
        String str5 = pdmap;
        String str6 = "camera_scheme";
        String str7 = ConfigJson.SENSOR;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = PuduLocateInterface.LocateCase.Marker;
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        ?? r82 = (byte[]) 0;
        objectRef3.element = r82;
        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
        objectRef4.element = r82;
        Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
        objectRef5.element = r82;
        Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
        objectRef6.element = r82;
        Ref.ObjectRef objectRef7 = new Ref.ObjectRef();
        objectRef7.element = r82;
        Ref.ObjectRef objectRef8 = new Ref.ObjectRef();
        objectRef8.element = r82;
        Ref.ObjectRef objectRef9 = new Ref.ObjectRef();
        String str8 = ConfigJson.MAP_VERSION;
        objectRef9.element = (String) 0;
        try {
            objectRef9.element = replaceString(removeLastNchars(str5, 6));
            String str9 = TAG;
            try {
                StringBuilder sb = new StringBuilder();
                String str10 = "The map type read by this machine is a map type that uses a forward-looking camera, but HLS does not have a front-view camera";
                sb.append("optemap.pgm path : ");
                sb.append((String) objectRef9.element);
                Pdlog.m3273d(str9, sb.toString());
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str5));
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        String name = nextEntry.getName();
                        if (name != null) {
                            switch (name.hashCode()) {
                                case -2056954114:
                                    objectRef = objectRef9;
                                    str = str8;
                                    str2 = str10;
                                    str3 = str6;
                                    str4 = str7;
                                    if (name.equals("ATLAS_DATA")) {
                                        objectRef6.element = readFileBytes(zipInputStream);
                                    }
                                    zipInputStream.closeEntry();
                                    str5 = pdmap;
                                    str6 = str3;
                                    str7 = str4;
                                    objectRef9 = objectRef;
                                    str10 = str2;
                                    str8 = str;
                                case -1302856181:
                                    objectRef = objectRef9;
                                    str = str8;
                                    str2 = str10;
                                    str3 = str6;
                                    str4 = str7;
                                    if (name.equals("optemap.yaml")) {
                                        objectRef4.element = readFileBytes(zipInputStream);
                                    }
                                    zipInputStream.closeEntry();
                                    str5 = pdmap;
                                    str6 = str3;
                                    str7 = str4;
                                    objectRef9 = objectRef;
                                    str10 = str2;
                                    str8 = str;
                                case -458704055:
                                    objectRef = objectRef9;
                                    str = str8;
                                    str2 = str10;
                                    str3 = str6;
                                    str4 = str7;
                                    if (name.equals(MapFilePathConfig.LOCATE_MAP_DATA_NAME)) {
                                        objectRef3.element = readFileBytes(zipInputStream);
                                    }
                                    zipInputStream.closeEntry();
                                    str5 = pdmap;
                                    str6 = str3;
                                    str7 = str4;
                                    objectRef9 = objectRef;
                                    str10 = str2;
                                    str8 = str;
                                case -28025836:
                                    if (name.equals("config.json") && (readFileBytes = readFileBytes(zipInputStream)) != null) {
                                        String str11 = new String(readFileBytes, Charsets.UTF_8);
                                        String str12 = TAG;
                                        StringBuilder sb2 = new StringBuilder();
                                        objectRef = objectRef9;
                                        sb2.append("map config: ");
                                        sb2.append(str11);
                                        Pdlog.m3273d(str12, sb2.toString());
                                        JSONObject jSONObject = new JSONObject(str11);
                                        String str13 = "map config json : " + jSONObject;
                                        Pdlog.m3273d(TAG, str13);
                                        Object obj = str13;
                                        if (jSONObject.has(str7)) {
                                            int i = jSONObject.getInt(str7);
                                            if (i == 0 || i == 1) {
                                                r3 = PuduLocateInterface.LocateCase.Marker;
                                            } else if (i == 2) {
                                                r3 = PuduLocateInterface.LocateCase.Laser;
                                            } else if (i == 3) {
                                                r3 = PuduLocateInterface.LocateCase.LaserMark;
                                            } else if (i == 4) {
                                                r3 = PuduLocateInterface.LocateCase.Slamware;
                                            } else {
                                                r3 = PuduLocateInterface.LocateCase.Marker;
                                            }
                                            objectRef2.element = r3;
                                            MachineModel machineModel = machineType;
                                            MachineModel machineModel2 = MachineModel.Hls;
                                            obj = machineModel2;
                                            if (machineModel == machineModel2) {
                                                PuduLocateInterface.LocateCase locateCase = (PuduLocateInterface.LocateCase) objectRef2.element;
                                                PuduLocateInterface.LocateCase locateCase2 = PuduLocateInterface.LocateCase.LaserMark;
                                                obj = locateCase2;
                                                if (locateCase == locateCase2) {
                                                    objectRef2.element = PuduLocateInterface.LocateCase.Marker;
                                                    obj = locateCase2;
                                                }
                                            }
                                        }
                                        r8 = obj;
                                        if (jSONObject.has(str6)) {
                                            int i2 = jSONObject.getInt(str6);
                                            locateCameraType = (i2 == 0 || i2 != 1) ? 0 : 1;
                                            MachineModel machineModel3 = machineType;
                                            MachineModel machineModel4 = MachineModel.Hls;
                                            r8 = machineModel4;
                                            if (machineModel3 == machineModel4) {
                                                r8 = 1;
                                                if (locateCameraType == 1) {
                                                    Pdlog.m3273d(TAG, str10);
                                                    r8 = initListener;
                                                    if (r8 != 0) {
                                                        r8.initCoreServiceState(CoreInitStep.Finish, CoreInitState.Fail, str10);
                                                    }
                                                    return false;
                                                }
                                            }
                                        }
                                        try {
                                            str2 = str10;
                                            str = str8;
                                            if (jSONObject.has(str)) {
                                                mapVersion = jSONObject.getInt(str);
                                                String str14 = TAG;
                                                str3 = str6;
                                                StringBuilder sb3 = new StringBuilder();
                                                str4 = str7;
                                                sb3.append("map version ");
                                                sb3.append(mapVersion);
                                                Pdlog.m3273d(str14, sb3.toString());
                                                zipInputStream.closeEntry();
                                                str5 = pdmap;
                                                str6 = str3;
                                                str7 = str4;
                                                objectRef9 = objectRef;
                                                str10 = str2;
                                                str8 = str;
                                            }
                                            str3 = str6;
                                            str4 = str7;
                                            zipInputStream.closeEntry();
                                            str5 = pdmap;
                                            str6 = str3;
                                            str7 = str4;
                                            objectRef9 = objectRef;
                                            str10 = str2;
                                            str8 = str;
                                        } catch (Exception e) {
                                            e = e;
                                            String str15 = TAG;
                                            StringBuilder sb4 = new StringBuilder();
                                            sb4.append("load map error: ");
                                            sb4.append(e.getLocalizedMessage());
                                            sb4.append(" stackTrace ");
                                            StackTraceElement[] stackTrace = e.getStackTrace();
                                            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                                            sb4.append(ArraysKt.contentDeepToString(stackTrace));
                                            Pdlog.m3274e(str15, sb4.toString());
                                            if (r8 != 0) {
                                                CoreInitStep coreInitStep = CoreInitStep.Finish;
                                                CoreInitState coreInitState = CoreInitState.Fail;
                                                StringBuilder sb5 = new StringBuilder();
                                                sb5.append("read map config fail: ");
                                                sb5.append(e.getLocalizedMessage());
                                                sb5.append("  stackTrace ");
                                                StackTraceElement[] stackTrace2 = e.getStackTrace();
                                                Intrinsics.checkExpressionValueIsNotNull(stackTrace2, "e.stackTrace");
                                                sb5.append(ArraysKt.contentDeepToString(stackTrace2));
                                                r8.initCoreServiceState(coreInitStep, coreInitState, sb5.toString());
                                            }
                                            return false;
                                        }
                                    }
                                    break;
                                case 329081321:
                                    if (name.equals("scheduling_config.json")) {
                                        objectRef8.element = readFileBytes(zipInputStream);
                                        break;
                                    }
                                    break;
                                case 329510824:
                                    if (name.equals(FileName.SCH_CONFIG_YAML)) {
                                        objectRef7.element = readFileBytes(zipInputStream);
                                        break;
                                    }
                                    break;
                                case 1684674643:
                                    if (name.equals(MapFilePathConfig.SLAM_MAP_DATA_NAME)) {
                                        objectRef5.element = readFileBytes(zipInputStream);
                                        break;
                                    }
                                    break;
                            }
                        }
                        objectRef = objectRef9;
                        str = str8;
                        str2 = str10;
                        str3 = str6;
                        str4 = str7;
                        zipInputStream.closeEntry();
                        str5 = pdmap;
                        str6 = str3;
                        str7 = str4;
                        objectRef9 = objectRef;
                        str10 = str2;
                        str8 = str;
                    } else {
                        zipInputStream.close();
                        List mutableListOf = CollectionsKt.mutableListOf(CoreInitModules.Scheudle, CoreInitModules.Locate);
                        Ref.ObjectRef objectRef10 = new Ref.ObjectRef();
                        launch$default = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreImpl$loadMapAndConfig$locateInitJob$1(objectRef2, initListener, objectRef3, objectRef4, objectRef9, objectRef5, mutableListOf, null), 3, null);
                        objectRef10.element = launch$default;
                        Ref.ObjectRef objectRef11 = new Ref.ObjectRef();
                        launch$default2 = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreImpl$loadMapAndConfig$scheduleInitJob$1(objectRef6, initListener, defFloorIndex, floors_map, objectRef8, objectRef7, mutableListOf, null), 3, null);
                        objectRef11.element = launch$default2;
                        Ref.ObjectRef objectRef12 = new Ref.ObjectRef();
                        launch$default3 = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreImpl$loadMapAndConfig$perceptionInitJob$1(str5, mutableListOf, null), 3, null);
                        objectRef12.element = launch$default3;
                        BuildersKt__BuildersKt.runBlocking$default(null, new MirCoreImpl$loadMapAndConfig$1(objectRef11, objectRef10, objectRef12, null), 1, null);
                        init_modules_status = true;
                        return mutableListOf.isEmpty();
                    }
                }
            } catch (Exception e2) {
                e = e2;
                r8 = initListener;
            }
        } catch (Exception e3) {
            e = e3;
            r8 = initListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadMarkerLocateConfig(PuduLocateInterface.LocateCase locateCase) {
        try {
            if (!new File(MapFilePathConfig.CAMERA_CONFIG_PATH).exists()) {
                File file = new File(ConfigUtil.CONFIG_DIR);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (!new File(MapFilePathConfig.OLD_CAMERA_CONFIG_PATH).exists()) {
                    return false;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(MapFilePathConfig.CAMERA_CONFIG_PATH);
                FileInputStream fileInputStream = new FileInputStream(MapFilePathConfig.OLD_CAMERA_CONFIG_PATH);
                byte[] bArr = new byte[256];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
                fileInputStream.close();
            }
            FileInputStream fileInputStream2 = new FileInputStream(MapFilePathConfig.CAMERA_CONFIG_PATH);
            byte[] bArr2 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read2 = fileInputStream2.read(bArr2);
                if (read2 <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read2);
            }
            byte[] tmp = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            if (locateCase == PuduLocateInterface.LocateCase.LaserMark) {
                LocalizationStub localizationStub = localization;
                Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                if (!localizationStub.initMarkerConfig(tmp)) {
                    Pdlog.m3277w(TAG, "load camera config failed");
                    return false;
                }
            } else {
                LocalizationStub localizationStub2 = localization;
                Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                if (!localizationStub2.initConfig(tmp)) {
                    Pdlog.m3277w(TAG, "load camera config failed");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "load camera config file error: " + e.getLocalizedMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadLaserLocateConfig() {
        try {
            if (!new File(MapFilePathConfig.LOCATION_CONFIG_PATH).exists()) {
                return false;
            }
            FileInputStream fileInputStream = new FileInputStream(MapFilePathConfig.LOCATION_CONFIG_PATH);
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                Pdlog.m3273d(TAG, "locate config length : ", Integer.valueOf(read));
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] tmp = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            LocalizationStub localizationStub = localization;
            Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
            if (localizationStub.initConfig(tmp)) {
                return true;
            }
            Pdlog.m3277w(TAG, "load locate config failed");
            return false;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "load locate config file error: " + e.getLocalizedMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadMarkerLocateMap(byte[] markerMapByes, InitServiceListener initListener) {
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Running, "");
        }
        Pdlog.m3273d(TAG, "load map to parse locate map");
        boolean locateMap = localization.setLocateMap(markerMapByes);
        if (locateMap) {
            localization.registSensorListener(new Function2<String, ISensorData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadMarkerLocateMap$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, ISensorData iSensorData) {
                    invoke2(str, iSensorData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, ISensorData sensor) {
                    HardwareLinker hardwareLinker2;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(sensor, "sensor");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService != null) {
                        hardwareService.addSensorListener(name, sensor);
                    }
                }
            });
            localization.registerMarkerFrameListener(new Function2<String, IMarkerCameraData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadMarkerLocateMap$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, IMarkerCameraData iMarkerCameraData) {
                    invoke2(str, iMarkerCameraData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, IMarkerCameraData marker) {
                    HardwareLinker hardwareLinker2;
                    CameraInterface camera;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(marker, "marker");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                        return;
                    }
                    camera.addMarkerCameraListener(name, marker);
                }
            });
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Success, "");
            }
        } else if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization map load failed");
        }
        return locateMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadLocateMap(PuduLocateInterface.LocateCase locateCase, byte[] mapBytes, byte[] configBytes, InitServiceListener initListener) {
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Running, "");
        }
        boolean z = false;
        Pdlog.m3273d(TAG, "load map to parse locate map !!!!!");
        int i = WhenMappings.$EnumSwitchMapping$2[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LocalizationStub localizationStub = localization;
            if (configBytes == null) {
                Intrinsics.throwNpe();
            }
            z = localizationStub.setLocateMap(configBytes, mapBytes);
        } else if (i == 3) {
            try {
                File file = new File("/sdcard/pudu/map/extract");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File("/sdcard/pudu/map/extract/origin.stcm");
                if (!file2.exists()) {
                    file2.createNewFile();
                } else if (file2.isDirectory()) {
                    FilesKt.deleteRecursively(file2);
                    file2.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(mapBytes, 0, mapBytes.length);
                fileOutputStream.flush();
                fileOutputStream.close();
                Pdlog.m3273d(TAG, "parse map stcm from pdmap !!!!!");
                byte[] bytes = "/sdcard/pudu/map/extract/origin.stcm".getBytes(Charsets.UTF_8);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                z = localization.setLocateMap(new byte[0], bytes);
            } catch (Exception e) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("save slamware map exception: ");
                sb.append(e.getLocalizedMessage());
                sb.append(' ');
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str, sb.toString());
            }
        }
        if (z) {
            localization.registSensorListener(new Function2<String, ISensorData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadLocateMap$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str2, ISensorData iSensorData) {
                    invoke2(str2, iSensorData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, ISensorData sensor) {
                    HardwareLinker hardwareLinker2;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(sensor, "sensor");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService != null) {
                        hardwareService.addSensorListener(name, sensor);
                    }
                }
            });
            int i2 = WhenMappings.$EnumSwitchMapping$3[locateCase.ordinal()];
            if (i2 == 1 || i2 == 2) {
                localization.registerLaserListener(new Function2<String, ILidarData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadLocateMap$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str2, ILidarData iLidarData) {
                        invoke2(str2, iLidarData);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String name, ILidarData iLidarData) {
                        HardwareLinker hardwareLinker2;
                        LidarInterface lidarInterface;
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
                        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                        hardwareLinker2 = MirCoreImpl.hardwareLinker;
                        HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                        if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.addDataListener(name, iLidarData);
                    }
                });
            }
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Success, "");
            }
        } else if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization map load failed");
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadLocateMapByPath(PuduLocateInterface.LocateCase locateCase, String mapPath, byte[] configBytes, InitServiceListener initListener) {
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Running, "");
        }
        boolean z = false;
        Pdlog.m3273d(TAG, "load map to parse locate map !!!!!");
        int i = WhenMappings.$EnumSwitchMapping$4[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LocalizationStub localizationStub = localization;
            if (configBytes == null) {
                Intrinsics.throwNpe();
            }
            z = localizationStub.setLocateMap(configBytes, mapPath);
        }
        if (z) {
            localization.registSensorListener(new Function2<String, ISensorData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadLocateMapByPath$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, ISensorData iSensorData) {
                    invoke2(str, iSensorData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String name, ISensorData sensor) {
                    HardwareLinker hardwareLinker2;
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    Intrinsics.checkParameterIsNotNull(sensor, "sensor");
                    MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                    hardwareLinker2 = MirCoreImpl.hardwareLinker;
                    HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                    if (hardwareService != null) {
                        hardwareService.addSensorListener(name, sensor);
                    }
                }
            });
            int i2 = WhenMappings.$EnumSwitchMapping$5[locateCase.ordinal()];
            if (i2 == 1 || i2 == 2) {
                localization.registerLaserListener(new Function2<String, ILidarData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadLocateMapByPath$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, ILidarData iLidarData) {
                        invoke2(str, iLidarData);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String name, ILidarData iLidarData) {
                        HardwareLinker hardwareLinker2;
                        LidarInterface lidarInterface;
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
                        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
                        hardwareLinker2 = MirCoreImpl.hardwareLinker;
                        HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                        if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                            return;
                        }
                        lidarInterface.addDataListener(name, iLidarData);
                    }
                });
            }
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Success, "");
            }
        } else if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization map load failed");
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadTopoMap(byte[] topoMapBytes, InitServiceListener initListener) {
        Pdlog.m3273d(TAG, "load map to parse topoMap");
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Running, "");
        }
        String str = new String(topoMapBytes, 0, topoMapBytes.length, Charsets.UTF_8);
        if (!Intrinsics.areEqual(str, "") && ScheduleMaster.INSTANCE.parseTopoMap(str)) {
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Success, "");
            }
            return true;
        }
        Pdlog.m3277w(TAG, "load topo map error " + str);
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Fail, "TopoMap parsed failed");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadScheduleConfig(byte[] jsonBytes, byte[] yamlBytes, InitServiceListener initListener) {
        Pdlog.m3273d(TAG, "load map for paring schedule config");
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.ScheduleConfig, CoreInitState.Running, "");
        }
        String str = yamlBytes == null ? "" : new String(yamlBytes, 0, yamlBytes.length, Charsets.UTF_8);
        String str2 = jsonBytes == null ? "" : new String(jsonBytes, 0, jsonBytes.length, Charsets.UTF_8);
        if (Intrinsics.areEqual(str, "") && Intrinsics.areEqual(str2, "")) {
            Pdlog.m3277w(TAG, "schedule config file is empty");
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.ScheduleConfig, CoreInitState.Fail, "schedule config file lost");
            }
            return false;
        }
        boolean z = !Intrinsics.areEqual(str2, "");
        Pdlog.m3273d(TAG, "schedule config string: json: " + str2 + ", yaml: " + str + ", use json " + z);
        if (z && ScheduleMaster.INSTANCE.parseScheduleConfig(str2, z)) {
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.ScheduleConfig, CoreInitState.Success, "");
            }
        } else {
            if (str.equals("") || !ScheduleMaster.INSTANCE.parseScheduleConfig(str, z)) {
                Pdlog.m3273d(TAG, "init Schedule and GlobalPlan Config error");
                if (initListener != null) {
                    initListener.initCoreServiceState(CoreInitStep.ScheduleConfig, CoreInitState.Fail, "schedule config file error parsed");
                }
                return false;
            }
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.ScheduleConfig, CoreInitState.Success, "");
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loadMultiTopoMap(int defFloorIndex, byte[] topoMapBytes, List<String> floors_map, InitServiceListener initListener) {
        Pdlog.m3273d(TAG, "load multi topo map");
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Running, "");
        }
        if (floors_map == null) {
            Pdlog.m3273d(TAG, "Use Single Floor");
            String floorMap = new Gson().toJson(new FloorMap(String.valueOf(defFloorIndex), defFloorIndex, new String(topoMapBytes, 0, topoMapBytes.length, Charsets.UTF_8)), FloorMap.class);
            Intrinsics.checkExpressionValueIsNotNull(floorMap, "floorMap");
            for (String str : CollectionsKt.mutableListOf(floorMap)) {
                Pdlog.m3273d(TAG, "Single Floor floorMap is: " + str);
                if (!ScheduleMaster.INSTANCE.parseMultiTopoMap(str, false)) {
                    Pdlog.m3277w(TAG, "load multi topo map error, " + str);
                    if (initListener != null) {
                        initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Fail, "MultiTopoMap parsed failed");
                    }
                    return false;
                }
            }
        } else {
            Pdlog.m3273d(TAG, "Use Multi Floor");
            Pair<Boolean, String> parseMultiMap = parseMultiMap(floors_map);
            if (!parseMultiMap.getFirst().booleanValue()) {
                Pdlog.m3277w(TAG, "load multi topo map error, " + parseMultiMap.getSecond());
                if (initListener != null) {
                    initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Fail, "MultiTopoMap parsed failed");
                }
                return false;
            }
        }
        ScheduleMaster.INSTANCE.setCurrentFloorIndex(defFloorIndex);
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Success, "");
        }
        return true;
    }

    private final boolean reloadMultiTopoMap(List<String> floors_map, InitServiceListener initListener) {
        Pdlog.m3273d(TAG, "reload multi topo map");
        if (initListener != null) {
            initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Running, "");
        }
        try {
            Pdlog.m3273d(TAG, "Reload Multi Floor");
            Pair<Boolean, String> parseMultiMap = parseMultiMap(floors_map);
            if (parseMultiMap.getFirst().booleanValue()) {
                if (initListener != null) {
                    initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Success, "");
                }
                return true;
            }
            Pdlog.m3277w(TAG, "reload multi topo map error, " + parseMultiMap.getSecond());
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.ScheduleConfig, CoreInitState.Fail, "MultiTopoMap parsed failed");
            }
            return false;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "reload multi map error: " + e.getLocalizedMessage());
            if (initListener != null) {
                initListener.initCoreServiceState(CoreInitStep.TopoMap, CoreInitState.Fail, "multi map file cannot parsed topomap");
            }
            return false;
        }
    }

    private final Pair<Boolean, String> parseMultiMap(List<String> floors_map) {
        ListIterator<String> listIterator = floors_map.listIterator();
        Gson gson = new Gson();
        String str = "";
        boolean z = true;
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            FloorMap floorMap = (FloorMap) gson.fromJson(listIterator.next(), FloorMap.class);
            String floor = floorMap.getFloor();
            int floor_index = floorMap.getFloor_index();
            String topo = floorMap.getTopo();
            if (!new File(topo).exists()) {
                str = "Not find map " + topo;
                z = false;
                break;
            }
            FileInputStream fileInputStream = new FileInputStream(topo);
            byte[] bArr = new byte[256];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } catch (Exception unused) {
                        str = "Read map " + topo + " failed";
                        Pdlog.m3274e(TAG, "Parse Multi Topomap err");
                        fileInputStream.close();
                        z = false;
                    }
                } finally {
                    fileInputStream.close();
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "byteStream.toByteArray()");
            String floorMapJson = gson.toJson(new FloorMap(floor, floor_index, new String(byteArray, Charsets.UTF_8)));
            ScheduleMaster scheduleMaster = ScheduleMaster.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(floorMapJson, "floorMapJson");
            scheduleMaster.parseMultiTopoMap(floorMapJson, false);
        }
        return new Pair<>(Boolean.valueOf(z), str);
    }

    private final boolean loadFileFromAssets(String filename, String dstpath) {
        AssetManager assets;
        Pdlog.m3273d(TAG, "try to load file: ", filename, " to ", dstpath);
        Context context = MirCoreService.INSTANCE.getContext().get();
        try {
            File file = new File(dstpath);
            if (!file.exists()) {
                Pdlog.m3273d(TAG, "create folder: ", dstpath);
                file.mkdir();
            }
            InputStream open = (context == null || (assets = context.getAssets()) == null) ? null : assets.open(filename);
            File file2 = new File(dstpath + filename);
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            while (true) {
                if (open == null) {
                    Intrinsics.throwNpe();
                }
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    Pdlog.m3273d(TAG, "model is created!");
                    return true;
                }
            }
        } catch (FileNotFoundException unused) {
            Pdlog.m3273d(TAG, "model can not overwrite!");
            return false;
        } catch (IOException e) {
            Pdlog.m3274e(TAG, "", e);
            e.printStackTrace();
            return false;
        }
    }

    private final byte[] readFileBytes(ZipInputStream zipInputStream) {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                try {
                    int read = zipInputStream.read(bArr);
                    if (read > 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        return byteArrayOutputStream.toByteArray();
                    }
                } catch (Exception e) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("read file exception: ");
                    sb.append(e.getLocalizedMessage());
                    sb.append(", ");
                    sb.append(e.getLocalizedMessage());
                    sb.append(", stackTrace ");
                    StackTraceElement[] stackTrace = e.getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                    sb.append(ArraysKt.contentDeepToString(stackTrace));
                    Pdlog.m3277w(str, sb.toString());
                    byteArrayOutputStream.close();
                    return null;
                }
            } finally {
                byteArrayOutputStream.close();
            }
        }
    }

    private final void initVisionModel() {
        loadFileFromAssets("face_fastest.bin", "sdcard/pudu/config/model/");
        loadFileFromAssets("face_fastest.param.bin", "sdcard/pudu/config/model/");
    }

    public final void setRgbdParkingMode(boolean into_park) {
        hardwareLinker.setRgbdParkingMode(into_park);
    }

    public final void setAccParam(AccelerationType acc_type, double value) {
        Intrinsics.checkParameterIsNotNull(acc_type, "acc_type");
        hardwareLinker.getConnectStatus();
        hardwareLinker.setAccParam(acc_type, value);
    }

    public final Integer getLdsVersion() {
        MachineInfo machineInfo;
        HardwareInterface hardwareService = hardwareLinker.getHardwareService();
        if (hardwareService == null || (machineInfo = hardwareService.getMachineInfo()) == null) {
            return null;
        }
        return machineInfo.getInt(MachineInfo.IntInfo.ldsSensorVersion);
    }

    public final MachineModel getMachineType() {
        return machineType;
    }

    public final DockerDetectResult detectChargeDocker() {
        Perception.INSTANCE.setAutoDockSwitch(true);
        Perception.INSTANCE.resetAutoDockPerception();
        double[] identifyDocker = Perception.INSTANCE.identifyDocker();
        Perception.INSTANCE.setAutoDockSwitch(false);
        DockerDetectResult dockerDetectResult = new DockerDetectResult();
        dockerDetectResult.setDetect_sucess(identifyDocker[0] > 0.0d);
        dockerDetectResult.setX(identifyDocker[1]);
        dockerDetectResult.setY(identifyDocker[2]);
        dockerDetectResult.setTheta(identifyDocker[3]);
        Pdlog.m3275i(TAG, "dockerDetect result is ", Boolean.valueOf(dockerDetectResult.getDetect_sucess()), "pose is ", Double.valueOf(dockerDetectResult.getX()), Double.valueOf(dockerDetectResult.getY()), Double.valueOf(dockerDetectResult.getTheta()));
        return dockerDetectResult;
    }

    public final void processReflectorResult(final double dist, int level, int flag) {
        Job launch$default;
        Job launch$default2;
        Job launch$default3;
        Pdlog.m3273d(TAG, "processReflectorResult dist " + dist + " level " + level + " flag " + flag);
        if ((dist < inBrakeDist && flag == 1) || (dist < outBrakeDist && flag == 0)) {
            navigationer.setReflectBrakeFlag(true);
            reflectorDisListener.notify(new Function2<ReflectorInfoListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$processReflectorResult$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ReflectorInfoListener reflectorInfoListener, String str) {
                    invoke2(reflectorInfoListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ReflectorInfoListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.reflectorDistance((int) (dist * 1000));
                }
            });
            return;
        }
        if (dist < followLineDist) {
            if (followLineJob == null) {
                Navigation.INSTANCE.updateCycleParam(CycleParamUtils.INSTANCE.buidlPassPossibleFallingAreaStr(true));
                launch$default3 = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreImpl$processReflectorResult$2(null), 3, null);
                followLineJob = launch$default3;
            }
            if (slowDownJob == null) {
                navigationer.sendTaskPlannerParam("Slower");
                launch$default2 = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreImpl$processReflectorResult$3(null), 3, null);
                slowDownJob = launch$default2;
                return;
            }
            return;
        }
        if (dist >= slowDownDist || slowDownJob != null) {
            return;
        }
        navigationer.sendTaskPlannerParam("Slower");
        launch$default = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreImpl$processReflectorResult$4(null), 3, null);
        slowDownJob = launch$default;
    }

    public final void loadReflectorParam() {
        if (new File(MapFilePathConfig.REFLECTOR_PARAM_PATH).exists()) {
            try {
                File file = new File(MapFilePathConfig.REFLECTOR_PARAM_PATH);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                String str = new String(cArr);
                Pdlog.m3273d(TAG, "reflector.config content is " + str);
                String str2 = str;
                int length = str2.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = str2.charAt(!z ? i : length) <= ' ';
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                JSONObject jSONObject = new JSONObject(str2.subSequence(i, length + 1).toString());
                if (jSONObject.has("in_brake_dist")) {
                    inBrakeDist = Double.parseDouble(jSONObject.get("in_brake_dist").toString());
                    Pdlog.m3273d(TAG, "file in_brake_dist is " + inBrakeDist);
                }
                if (jSONObject.has("follow_line_dist")) {
                    followLineDist = Double.parseDouble(jSONObject.get("follow_line_dist").toString());
                    Pdlog.m3273d(TAG, "file follow_line_dist is " + followLineDist);
                }
                if (jSONObject.has("slow_down_dist")) {
                    slowDownDist = Double.parseDouble(jSONObject.get("slow_down_dist").toString());
                    Pdlog.m3273d(TAG, "file slow_down_dist is " + slowDownDist);
                }
                if (jSONObject.has("out_brake_dist")) {
                    outBrakeDist = Double.parseDouble(jSONObject.get("out_brake_dist").toString());
                    Pdlog.m3273d(TAG, "file out_brake_dist is " + outBrakeDist);
                }
                if (jSONObject.has("reflector_switch")) {
                    reflectorSwitch = Integer.parseInt(jSONObject.get("reflector_switch").toString()) > 0;
                    Pdlog.m3273d(TAG, "file reflector_switch is " + reflectorSwitch);
                }
                fileReader.close();
                Perception.INSTANCE.enableReflectorDetector(reflectorSwitch);
                Perception.INSTANCE.setReflectorLevel(0, inBrakeDist);
                Perception.INSTANCE.setReflectorLevel(1, followLineDist);
                Perception.INSTANCE.setReflectorLevel(2, slowDownDist);
                return;
            } catch (Exception unused) {
                Pdlog.m3273d(TAG, "exception: reflector.config errorm switch off ReflectorDetector");
                Perception.INSTANCE.enableReflectorDetector(false);
                return;
            }
        }
        Pdlog.m3273d(TAG, "no reflector.config file, switch off ReflectorDetector");
        Perception.INSTANCE.enableReflectorDetector(false);
    }

    private final MachineInfo.LidarType parseIntToLidarType(Integer version) {
        MachineInfo.LidarType lidarType;
        MachineInfo.LidarType[] values = MachineInfo.LidarType.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                lidarType = null;
                break;
            }
            lidarType = values[i];
            if (version != null && ((lidarType.getId() & 255) & 255) == version.intValue()) {
                break;
            }
            i++;
        }
        return lidarType != null ? lidarType : MachineInfo.LidarType.NoDevice;
    }

    public final void updateDebugSchRecv$mircore_packRelease(String id, Vector3d pose, Vector2d vel) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Intrinsics.checkParameterIsNotNull(vel, "vel");
        synchronized (debug_sch_recv) {
            debug_sch_recv.put(id, new Pair<>(pose, vel));
            Unit unit = Unit.INSTANCE;
        }
    }

    public final File createFile(String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            Intrinsics.throwNpe();
        }
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public final void depressPdmap(String zipFile) {
        Intrinsics.checkParameterIsNotNull(zipFile, "zipFile");
        String str = (String) StringsKt.split$default((CharSequence) zipFile, new String[]{".pdmap"}, false, 0, 6, (Object) null).get(0);
        byte[] bArr = new byte[1024];
        OutputStream outputStream = (OutputStream) null;
        InputStream inputStream = (InputStream) null;
        try {
            ZipFile zipFile2 = new ZipFile(zipFile);
            Enumeration<? extends ZipEntry> entries = zipFile2.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                if (nextElement == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.zip.ZipEntry");
                }
                ZipEntry zipEntry = nextElement;
                String name = zipEntry.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "zipEntry.name");
                inputStream = zipFile2.getInputStream(zipEntry);
                FileOutputStream fileOutputStream = new FileOutputStream(createFile(str + File.separator + name));
                try {
                    Ref.IntRef intRef = new Ref.IntRef();
                    while (true) {
                        int read = inputStream.read(bArr);
                        intRef.element = read;
                        if (read <= 0) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, intRef.element);
                        }
                    }
                    inputStream.close();
                    fileOutputStream.close();
                    outputStream = fileOutputStream;
                } catch (Throwable th) {
                    th = th;
                    outputStream = fileOutputStream;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00d3, code lost:
    
        if (new java.io.File(r1 + "/opti_track_info.txt").exists() == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String passFilePathtoPerception(String pdmap) {
        Intrinsics.checkParameterIsNotNull(pdmap, "pdmap");
        final String str = (String) StringsKt.split$default((CharSequence) pdmap, new String[]{".pdmap"}, false, 0, 6, (Object) null).get(0);
        if (!new File(str).exists()) {
            Pdlog.m3273d(TAG, "map_path do not exist, just depress");
            depressPdmap(pdmap);
        } else {
            try {
                String readText$default = FilesKt.readText$default(new File(str + "/config.json"), null, 1, null);
                Pdlog.m3273d(TAG, "decompressed map config.json " + readText$default);
                JSONObject jSONObject = new JSONObject(readText$default);
                if (jSONObject.has(ConfigJson.MAP_VERSION)) {
                    int i = jSONObject.getInt(ConfigJson.MAP_VERSION);
                    Pdlog.m3273d(TAG, "decompressedmap_version " + i);
                    if (i == mapVersion) {
                        if (new File(str + "/OPTI_ATLAS_DATA").exists()) {
                        }
                    }
                    new File(str + '/').list(new FilenameFilter() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$passFilePathtoPerception$1
                        @Override // java.io.FilenameFilter
                        public final boolean accept(File file, String str2) {
                            return new File(str + '/' + str2).delete();
                        }
                    });
                    depressPdmap(pdmap);
                }
            } catch (Exception e) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("depress pdmap map error: ");
                sb.append(e.getLocalizedMessage());
                sb.append(" stackTrace ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3274e(str2, sb.toString());
            }
        }
        return str;
    }

    public final String removeLastNchars(String str, int n) {
        if (str == null || str.length() < n) {
            return str;
        }
        String substring = str.substring(0, str.length() - n);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public final String replaceString(String str1) {
        return str1 == null ? str1 : StringsKt.replace$default(str1, MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, "/sdcard/pudu/map/extract", false, 4, (Object) null);
    }
}
