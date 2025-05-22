package com.pudutech.mirsdk;

import android.content.Context;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.cloud.SpeechUtility;
import com.loc.C3898x;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.ProcStat;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.CliffDistanceStateListener;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.FillInStateListener;
import com.pudutech.mirsdk.aidl.MapAreaDetectionListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessControlServer;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapListConfig;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.charge.ChargeFinder;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.Atlas;
import com.pudutech.mirsdk.map.FloorsMapList;
import com.pudutech.mirsdk.map.MapConfig;
import com.pudutech.mirsdk.map.MapDecode;
import com.pudutech.mirsdk.map.RelocatePoint;
import com.pudutech.mirsdk.map.ScheduleConfig;
import com.pudutech.mirsdk.map.StringBase64Kt;
import com.pudutech.mirsdk.map.elements.AccessControlPoint;
import com.pudutech.mirsdk.map.elements.ChargingPile;
import com.pudutech.mirsdk.map.elements.CircleMode;
import com.pudutech.mirsdk.map.elements.ElevatorSource;
import com.pudutech.mirsdk.map.elements.ElevatorWaiter;
import com.pudutech.mirsdk.map.elements.Source;
import com.pudutech.mirsdk.map.elements.ZonesData;
import com.pudutech.mirsdk.mircore.AreaDetectInterface;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.LocalizationListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ReloadMapResultListener;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.ScheduleListener;
import com.pudutech.mirsdk.mircore.aidl.CliffInfoListener;
import com.pudutech.mirsdk.mircore.aidl.ReflectorInfoListener;
import com.pudutech.mirsdk.mircore.aidl.SpeedLimitListener;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusInfo;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.mircore.coreparcel.ScheduleFillInState;
import com.pudutech.mirsdk.mircore.coreparcel.SlamStatus;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import com.pudutech.mirsdk.movetask.AccessControlTask;
import com.pudutech.mirsdk.movetask.AcrossFloorTask;
import com.pudutech.mirsdk.movetask.BusinessType;
import com.pudutech.mirsdk.movetask.CurrentFloorTask;
import com.pudutech.mirsdk.movetask.ElevatorTask;
import com.pudutech.mirsdk.movetask.GatePassTask;
import com.pudutech.mirsdk.movetask.GeneralTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.TimeoutKt;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ù\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0005/jtx~\u0018\u0000 ´\u00022\u00020\u0001:\u0004´\u0002µ\u0002Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00126\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\u0010\u0016J\u0007\u0010\u0082\u0001\u001a\u00020\rJ\u0007\u0010\u0083\u0001\u001a\u00020\rJ\u001e\u0010\u0084\u0001\u001a\u00020\r2\t\b\u0002\u0010\u0085\u0001\u001a\u00020\"H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u0086\u0001J \u0010\u0087\u0001\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00142\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001H\u0016J2\u0010\u008b\u0001\u001a\u000f\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00140\u008c\u00012\u0007\u0010\u008d\u0001\u001a\u00020\u00142\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\t\b\u0002\u0010\u0090\u0001\u001a\u00020\u0014J\u001e\u0010\u0091\u0001\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00142\t\u0010\u0092\u0001\u001a\u0004\u0018\u000102H\u0016J\u0012\u0010\u0093\u0001\u001a\u00020\"2\u0007\u0010\u0088\u0001\u001a\u00020\"H\u0016J \u0010\u0094\u0001\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00142\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0095\u0001H\u0016J\u001e\u0010\u0096\u0001\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00142\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010QH\u0016J\u001f\u0010\u0097\u0001\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00142\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010mH\u0016J\u0007\u0010\u0098\u0001\u001a\u00020\rJ\u001d\u0010\u0099\u0001\u001a\u000f\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00140\u008c\u00012\u0007\u0010\u008d\u0001\u001a\u00020\u0014J\u0007\u0010\u009a\u0001\u001a\u00020\rJ\u0011\u0010\u009b\u0001\u001a\u00020\r2\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001J\u0007\u0010\u009e\u0001\u001a\u00020\rJ\u0007\u0010\u009f\u0001\u001a\u00020\rJ\t\u0010 \u0001\u001a\u00020\rH\u0002J\u0013\u0010¡\u0001\u001a\u00020\"H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¢\u0001J(\u0010£\u0001\u001a\u0004\u0018\u00010\u00142\u001d\u0010¤\u0001\u001a\u0018\u0012\u0004\u0012\u00020?\u0018\u00010¥\u0001j\u000b\u0012\u0004\u0012\u00020?\u0018\u0001`¦\u0001J\t\u0010§\u0001\u001a\u00020\"H\u0002J\t\u0010¨\u0001\u001a\u00020\rH\u0016J\u0007\u0010©\u0001\u001a\u00020\rJ\u001c\u0010ª\u0001\u001a\u00020\r2\u0007\u0010«\u0001\u001a\u00020\"H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u0086\u0001J,\u0010¬\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u00ad\u0001\u0018\u00010\u00192\u000f\u0010®\u0001\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00192\u0007\u0010¯\u0001\u001a\u00020\"H\u0016J\u0012\u0010°\u0001\u001a\u00020\r2\u0007\u0010\u0088\u0001\u001a\u00020\"H\u0016J\u0012\u0010±\u0001\u001a\u00020\r2\u0007\u0010\u0088\u0001\u001a\u00020\"H\u0016J\u0010\u0010²\u0001\u001a\u00020\r2\u0007\u0010³\u0001\u001a\u00020\u0014J\u0007\u0010´\u0001\u001a\u00020\"J\t\u0010µ\u0001\u001a\u0004\u0018\u00010=J\b\u0010¶\u0001\u001a\u00030·\u0001J\t\u0010¸\u0001\u001a\u000206H\u0016J\t\u0010¹\u0001\u001a\u000206H\u0016J\u0010\u0010º\u0001\u001a\t\u0012\u0005\u0012\u00030»\u00010\u0019H\u0016J\u000f\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019H\u0016J\r\u0010½\u0001\u001a\b\u0012\u0004\u0012\u00020-0\u0019J\t\u0010¾\u0001\u001a\u00020\"H\u0016J\u0007\u0010¿\u0001\u001a\u000209J\u0011\u0010À\u0001\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010\u0019H\u0016J\b\u0010Á\u0001\u001a\u00030Â\u0001J\u0014\u0010Ã\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u0002090Ä\u0001J\t\u0010Å\u0001\u001a\u00020\"H\u0017J\b\u0010Æ\u0001\u001a\u00030Ç\u0001J\n\u0010È\u0001\u001a\u0005\u0018\u00010É\u0001J\u000f\u0010Ê\u0001\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0019J\t\u0010Ë\u0001\u001a\u00020?H\u0016J\f\u0010Ì\u0001\u001a\u0005\u0018\u00010Í\u0001H\u0016J\u001d\u0010Î\u0001\u001a\u000b\u0012\u0004\u0012\u00020=\u0018\u00010Ï\u00012\t\u0010Ð\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\u0015\u0010Ñ\u0001\u001a\u00020\u00142\n\u0010\u0088\u0001\u001a\u0005\u0018\u00010Ò\u0001H\u0016J\u0016\u0010Ó\u0001\u001a\t\u0012\u0004\u0012\u00020\u00140Ô\u0001H\u0016¢\u0006\u0003\u0010Õ\u0001J\t\u0010Ö\u0001\u001a\u000206H\u0016J\u0014\u0010×\u0001\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J-\u0010Ø\u0001\u001a\u00020\r2\u0007\u0010Ù\u0001\u001a\u00020?2\b\u0010Ú\u0001\u001a\u00030Û\u00012\u000f\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0019H\u0016J\u001c\u0010Ü\u0001\u001a\u00020\"2\u0007\u0010Ý\u0001\u001a\u00020\u00142\b\u0010Ú\u0001\u001a\u00030Û\u0001H\u0016J%\u0010Þ\u0001\u001a\u00020\"2\u0007\u0010Ý\u0001\u001a\u00020\u00142\b\u0010Ú\u0001\u001a\u00030Û\u00012\u0007\u0010ß\u0001\u001a\u00020\"H\u0016J'\u0010à\u0001\u001a\u00020\"2\u0007\u0010Ý\u0001\u001a\u00020\u00142\b\u0010Ú\u0001\u001a\u00030Û\u00012\t\b\u0002\u0010ß\u0001\u001a\u00020\"H\u0002J\u001c\u0010á\u0001\u001a\u00020\"2\u0007\u0010â\u0001\u001a\u00020\u00142\b\u0010Ú\u0001\u001a\u00030Û\u0001H\u0016J\u0010\u0010ã\u0001\u001a\u00020\r2\u0007\u0010ä\u0001\u001a\u00020\"J\u0017\u0010å\u0001\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0014J\u0007\u0010æ\u0001\u001a\u00020\"J\t\u0010ç\u0001\u001a\u00020\rH\u0002J\t\u0010è\u0001\u001a\u00020\rH\u0002J\t\u0010é\u0001\u001a\u00020\"H\u0002J\u0007\u0010ê\u0001\u001a\u00020\"J\t\u0010ë\u0001\u001a\u00020\"H\u0002J\t\u0010ì\u0001\u001a\u00020\"H\u0016J\u001c\u0010í\u0001\u001a\u00020\r2\u0007\u0010î\u0001\u001a\u00020\u00142\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001H\u0002J\u001c\u0010ï\u0001\u001a\u00020\r2\u0007\u0010î\u0001\u001a\u00020\u00142\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001H\u0002J#\u0010ð\u0001\u001a\t\u0012\u0004\u0012\u00020=0Ï\u00012\u0007\u0010î\u0001\u001a\u00020\u00142\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001H\u0002J\u0010\u0010ñ\u0001\u001a\u00020\r2\u0007\u0010ò\u0001\u001a\u00020\"J\"\u0010ó\u0001\u001a\u00020\r2\n\b\u0002\u0010ô\u0001\u001a\u00030Û\u00012\r\u0010õ\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\fJ\u0013\u0010ö\u0001\u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¢\u0001J\u001d\u0010÷\u0001\u001a\u0005\u0018\u00010\u00ad\u00012\u000f\u0010®\u0001\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0019H\u0016J\u0010\u0010ø\u0001\u001a\u00020\r2\u0007\u0010ù\u0001\u001a\u00020\"J\u0012\u0010ú\u0001\u001a\u00020\r2\u0007\u0010û\u0001\u001a\u00020\"H\u0002J\u0019\u0010ü\u0001\u001a\u00020\r2\u0007\u0010ý\u0001\u001a\u00020?2\u0007\u0010þ\u0001\u001a\u00020\\J\u0007\u0010ÿ\u0001\u001a\u00020\rJ\u0019\u0010\u0080\u0002\u001a\u00020\r2\u0007\u0010\u0081\u0002\u001a\u0002062\u0007\u0010\u0082\u0002\u001a\u000206J\u001a\u0010\u0083\u0002\u001a\u00020\r2\u0007\u0010\u0013\u001a\u00030\u0084\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\t\u0010\u0085\u0002\u001a\u00020\rH\u0016J\t\u0010\u0086\u0002\u001a\u00020\rH\u0016J\t\u0010\u0087\u0002\u001a\u00020\rH\u0016J\u0012\u0010\u0088\u0002\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0014J\t\u0010\u0089\u0002\u001a\u00020\rH\u0002J\u0013\u0010\u008a\u0002\u001a\u00020\r2\n\u0010\u008b\u0002\u001a\u0005\u0018\u00010»\u0001J\u001f\u0010\u008c\u0002\u001a\u00020\r2\u0007\u0010î\u0001\u001a\u00020\u00142\r\u0010\u008d\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0007\u0010\u008e\u0002\u001a\u00020\rJ\u0014\u0010\u008f\u0002\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\u0013\u0010\u0090\u0002\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0014H\u0016J\u0014\u0010\u0091\u0002\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\u0013\u0010\u0092\u0002\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0014H\u0016J\u0014\u0010\u0093\u0002\u001a\u00020\r2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\t\u0010\u0094\u0002\u001a\u00020\rH\u0016J\t\u0010\u0095\u0002\u001a\u00020\rH\u0002J\u0012\u0010\u0096\u0002\u001a\u00020\r2\u0007\u0010\u0088\u0001\u001a\u000206H\u0016J\u0012\u0010\u0097\u0002\u001a\u00020\r2\u0007\u0010\u0098\u0002\u001a\u000206H\u0002J\u001b\u0010\u0099\u0002\u001a\u00020\r2\b\u0010\u009a\u0002\u001a\u00030·\u00012\b\u0010\u009b\u0002\u001a\u00030\u009c\u0002J\u0012\u0010\u009d\u0002\u001a\u00020\r2\u0007\u0010\u0088\u0001\u001a\u000206H\u0016J\u0012\u0010\u009e\u0002\u001a\u00020\r2\u0007\u0010\u0088\u0001\u001a\u000206H\u0016J\u001b\u0010\u009f\u0002\u001a\u00020\r2\b\u0010 \u0002\u001a\u00030Â\u00012\b\u0010\u009b\u0002\u001a\u00030\u009c\u0002J\u0012\u0010¡\u0002\u001a\u00020\r2\u0007\u0010\u0088\u0001\u001a\u00020\"H\u0017J\u0012\u0010¢\u0002\u001a\u00020\"2\u0007\u0010\u0088\u0001\u001a\u00020?H\u0016J\u0012\u0010£\u0002\u001a\u00020\"2\u0007\u0010¤\u0002\u001a\u00020?H\u0016J\u0015\u0010¥\u0002\u001a\u00020\r2\n\u0010\u0088\u0001\u001a\u0005\u0018\u00010Í\u0001H\u0016J\u001e\u0010¦\u0002\u001a\u00020\r2\t\b\u0002\u0010\u0085\u0001\u001a\u00020\"H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0086\u0001J\u001e\u0010§\u0002\u001a\u00020\r2\t\b\u0002\u0010\u0085\u0001\u001a\u00020\"H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0086\u0001J\u001f\u0010¨\u0002\u001a\u00020\r2\u0016\u0010©\u0002\u001a\u0011\u0012\u0004\u0012\u00020\"\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u008c\u0001J\u001c\u0010ª\u0002\u001a\u00020\"2\b\u0010Ú\u0001\u001a\u00030Ò\u00012\u0007\u0010«\u0002\u001a\u00020\u0014H\u0016J\t\u0010¬\u0002\u001a\u00020\rH\u0016J\u0010\u0010\u00ad\u0002\u001a\u00020\r2\u0007\u0010®\u0002\u001a\u00020\u0014J\u0019\u0010¯\u0002\u001a\u00020\"2\u0007\u0010î\u0001\u001a\u00020\u00142\u0007\u0010°\u0002\u001a\u00020\u0014J\u0012\u0010±\u0002\u001a\u00020\r2\u0007\u0010«\u0002\u001a\u000206H\u0016J\u0017\u0010²\u0002\u001a\u0010\u0012\u0005\u0012\u00030³\u0002\u0012\u0004\u0012\u00020\u00140\u008c\u0001H\u0002R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R>\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\r0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\r0!0 ¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100R\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002020 X\u0082\u0004¢\u0006\u0002\n\u0000R \u00103\u001a\u0014\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020604X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020?X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020?X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020F0\u00198F¢\u0006\u0006\u001a\u0004\bH\u0010IR\u000e\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020M0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020?X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020SX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010X\u001a\u00020\"2\u0006\u0010W\u001a\u00020\"@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u000e\u0010Z\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010^\u001a\u0004\u0018\u00010_X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010b\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010e\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010i\u001a\u00020jX\u0082\u0004¢\u0006\u0004\n\u0002\u0010kR\u0014\u0010l\u001a\b\u0012\u0004\u0012\u00020m0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010n\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020pX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010s\u001a\u00020tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010uR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010w\u001a\u00020xX\u0082\u0004¢\u0006\u0004\n\u0002\u0010yR\u000e\u0010z\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020|X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010}\u001a\u00020~X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u007fR\u0017\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0081\u0001\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006¶\u0002"}, m3961d2 = {"Lcom/pudutech/mirsdk/MoveAction;", "Lcom/pudutech/mirsdk/aidl/MoveActionInterface$Stub;", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "_onStuckReplan", "Lkotlin/Function0;", "", "_onStateChange", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "state", "", TmpConstant.SERVICE_DESC, "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/mirhardware/RobotStatus;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)V", "TAG", "accessControlPoints", "", "Lcom/pudutech/mirsdk/map/elements/AccessControlPoint;", "accessControlTask", "Lcom/pudutech/mirsdk/movetask/AccessControlTask;", "acrossFloorTask", "Lcom/pudutech/mirsdk/movetask/AcrossFloorTask;", "arrivedOnGoalCruise", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function1;", "", "getArrivedOnGoalCruise", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "atlas", "Lcom/pudutech/mirsdk/map/Atlas;", "getAtlas", "()Lcom/pudutech/mirsdk/map/Atlas;", "brakeJob", "Lkotlinx/coroutines/Job;", "brakedWhenMoving", "chargingPiles", "Lcom/pudutech/mirsdk/map/elements/ChargingPile;", "cliffDistanceListener", "com/pudutech/mirsdk/MoveAction$cliffDistanceListener$1", "Lcom/pudutech/mirsdk/MoveAction$cliffDistanceListener$1;", "cliffDistanceStateListener", "Lcom/pudutech/mirsdk/aidl/CliffDistanceStateListener;", "controlWatchDog", "Lkotlin/Triple;", "", "", "cruiseTask", "curRobotPose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "currentFloorTask", "Lcom/pudutech/mirsdk/movetask/CurrentFloorTask;", "destinations", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "distancebrake", "", "distancelimit", "dorpDetectFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "dropLevel", "dropOccurflag", "eleWaiters", "Lcom/pudutech/mirsdk/map/elements/ElevatorWaiter;", "elevWaiters", "getElevWaiters", "()Ljava/util/List;", "elevatorTask", "Lcom/pudutech/mirsdk/movetask/ElevatorTask;", "elevators", "Lcom/pudutech/mirsdk/map/elements/ElevatorSource;", "errorJob", "fallDropIrUploadNum", "fillInStateListener", "Lcom/pudutech/mirsdk/aidl/FillInStateListener;", "gatePassTask", "Lcom/pudutech/mirsdk/movetask/GatePassTask;", "generalTask", "Lcom/pudutech/mirsdk/movetask/GeneralTask;", "hasAddCliffInfoListener", "<set-?>", "isLockWheel", "()Z", "isRegisterBluetooth", "lastChargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "lastSensorError", "localizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "lockWheelContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "lockWheelJob", "mActionStopFlag", "mJobBreakFlag", "moveJob", "moveOnGoalCruisePath", "moveTask", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "navigationTask", "com/pudutech/mirsdk/MoveAction$navigationTask$1", "Lcom/pudutech/mirsdk/MoveAction$navigationTask$1;", "onMapAreaDetectionListener", "Lcom/pudutech/mirsdk/aidl/MapAreaDetectionListener;", "operatorjob", "procStat", "Lcom/pudutech/base/ProcStat;", "productType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "reflectorInfoListener", "com/pudutech/mirsdk/MoveAction$reflectorInfoListener$1", "Lcom/pudutech/mirsdk/MoveAction$reflectorInfoListener$1;", "robotState", "scheduleListener", "com/pudutech/mirsdk/MoveAction$scheduleListener$1", "Lcom/pudutech/mirsdk/MoveAction$scheduleListener$1;", "scheduleListenerName", "speedLimit", "", "speedLimitListener", "com/pudutech/mirsdk/MoveAction$speedLimitListener$1", "Lcom/pudutech/mirsdk/MoveAction$speedLimitListener$1;", "stayPoints", "uploadIrjob", "actionPause", "actionResume", "actionStop", "resetSchedule", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addAccessDoorStateListener", "p0", "p1", "Lcom/pudutech/mirsdk/aidl/AccessDoorListener;", "addChargePoint", "Lkotlin/Pair;", "id", "dockerDetectResult", "Lcom/pudutech/mirsdk/mircore/coreparcel/DockerDetectResult;", "mac", "addCliffDistanceStateListener", "listener", "addCoverAround", "addElevetorRequestListener", "Lcom/pudutech/mirsdk/aidl/ElevatorRequestListener;", "addFillInStateListener", "addMapAreaDetectionListener", "addPoseListener", "addRelocationPoint", "addSchedulerListener", "analyZonesData", MapElement.Key.MAP, "Lcom/pudutech/mirsdk/map/MapDecode;", "backToPile", "bindAreaDetectListener", "brakeUntilStop", "checkAndClearWheelError", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkLocateFloors", "markers", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "checkNoRedundancyDestination", "clearDropEvent", "clearTopoData", "controlWheelCmd", "loop", "destinationsOrderWithRange", "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "destinationNames", "need_order", "enableDynamicRoadblock", "enableStuckReplan", "fallDropOccurEvent", "info", "findAccessControl", "foundNearChargingPiles", "getAccessControlServer", "Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;", "getCallAccessDoorDistance", "getCallElevatorDistance", "getChargingPileInfos", "Lcom/pudutech/mirsdk/aidl/serialize/ChargingPileInfo;", "getChargingPiles", "getChargingPilesList", "getCoverAround", "getCurRobotPose", "getDestinations", "getElevatorConnection", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;", "getElevatorPose", "", "getHeavyloadMode", "getLocateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getLocationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "getRelocationPoints", "getRoadBlockTime", "getSmoothRunAndStopMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "getSpecifyMapDestinations", "", "pdmap", "getSpeedLevel", "Lcom/pudutech/mirsdk/mircore/coreparcel/MoveMode;", "getSpeedLevels", "", "()[Ljava/lang/String;", "getTrayDis", "goChargingPile", "goCruisePath", "path_id", "mode", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "goHome", "groupId", "goHomeFill", "isFillIn", "goHomeTask", "goTo", "destination", "hardwareEmergency", "brake", "informStateChange", "init", "initAccCommunicateScheme", "initElvCommuicateScheme", "isGoChargingTask", "isInLockWheel", "isLeaveChargingTask", "isNearByChangePile", "loadMapDestinations", "floor", "loadMapNunDestinationElements", "loadSpecifyMapDestinations", "lockWheel", "isLock", "moveToPosition", "moveTaskMode", "task", NotificationCompat.CATEGORY_NAVIGATION, "nearestDestination", "notifyBTChargePileArrivedState", "isArrived", "notifyChargingPile", "shouldNotify", "onBattery", "percent", "chargeState", "onConnectedChargingPile", "onSpeed", "line", "angular", "onStateChange", "Lcom/pudutech/mirsdk/base/SDKRobotState;", "passAccessDoor", "pause", "quitFillIn", "realGoCharging", "registerBluetoothChargePileCan", "reloadLocalizationByChargingPile", "pile", "reloadMap", SpeechUtility.TAG_RESOURCE_RESULT, "relocateAtPoints", "removeAccessDoorStateListener", "removeCliffDistanceStateListener", "removeElevatorRequestListener", "removeFillInStateListener", "removeMapAreaDetectionListener", "resume", "retriveMCU", "rotate", "rotateTask", "direction", "setAccessControlServer", "server", "context", "Landroid/content/Context;", "setCallAccessDoorDistance", "setCallElevatorDistance", "setElevatorConnection", "type", "setHeavyloadMode", "setReplanWaitTime", "setRoadBlockTime", "time", "setSmoothRunAndStopMode", "stop", "stopAndWaitBrake", "suspendWarningWelfunction", "warning", "swithSpeedLevel", "level", "taskStop", "triggerError", "error", "updateSpecifyDefaultMap", "mapname", "updateTrayDis", "watchDogCheck", "Lcom/pudutech/mirsdk/MoveAction$WatchLevel;", "Companion", "WatchLevel", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MoveAction extends MoveActionInterface.Stub {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<String> cruiseIDs = new ArrayList();
    private final String TAG;
    private final Function2<RobotState, String, Unit> _onStateChange;
    private final Function0<Unit> _onStuckReplan;
    private final List<AccessControlPoint> accessControlPoints;
    private final AccessControlTask accessControlTask;
    private final AcrossFloorTask acrossFloorTask;
    private final ThreadSafeListener<Function1<Boolean, Unit>> arrivedOnGoalCruise;
    private final Atlas atlas;
    private volatile Job brakeJob;
    private boolean brakedWhenMoving;
    private final List<ChargingPile> chargingPiles;
    private final MoveAction$cliffDistanceListener$1 cliffDistanceListener;
    private final ThreadSafeListener<CliffDistanceStateListener> cliffDistanceStateListener;
    private Triple<Long, Double, Double> controlWatchDog;
    private final AIDLConnection<MirCoreInterface> coreService;
    private boolean cruiseTask;
    private final Vector3d curRobotPose;
    private final CurrentFloorTask currentFloorTask;
    private List<Destination> destinations;
    private final int distancebrake;
    private final int distancelimit;
    private AtomicBoolean dorpDetectFlag;
    private final int dropLevel;
    private int dropOccurflag;
    private List<ElevatorWaiter> eleWaiters;
    private final ElevatorTask elevatorTask;
    private final List<ElevatorSource> elevators;
    private Job errorJob;
    private final int fallDropIrUploadNum;
    private final ThreadSafeListener<FillInStateListener> fillInStateListener;
    private final GatePassTask gatePassTask;
    private final GeneralTask generalTask;
    private boolean hasAddCliffInfoListener;
    private volatile boolean isLockWheel;
    private boolean isRegisterBluetooth;
    private ChargeState lastChargeState;
    private final List<String> lastSensorError;
    private LocalizationStatus localizationStatus;
    private final ExecutorCoroutineDispatcher lockWheelContext;
    private Job lockWheelJob;
    private AtomicBoolean mActionStopFlag;
    private AtomicBoolean mJobBreakFlag;
    private volatile Job moveJob;
    private boolean moveOnGoalCruisePath;
    private com.pudutech.mirsdk.movetask.MoveTask moveTask;
    private final MoveAction$navigationTask$1 navigationTask;
    private final ThreadSafeListener<MapAreaDetectionListener> onMapAreaDetectionListener;
    private volatile Job operatorjob;
    private final ProcStat procStat;
    private ProductMachineType productType;
    private final MoveAction$reflectorInfoListener$1 reflectorInfoListener;
    private final RobotHardware robotHardware;
    private RobotState robotState;
    private final RobotStatus robotStatus;
    private final MoveAction$scheduleListener$1 scheduleListener;
    private final String scheduleListenerName;
    private final float speedLimit;
    private final MoveAction$speedLimitListener$1 speedLimitListener;
    private List<String> stayPoints;
    private Job uploadIrjob;
    private final WatchDog watchDog;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MoveTaskMode.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$10;
        public static final /* synthetic */ int[] $EnumSwitchMapping$11;
        public static final /* synthetic */ int[] $EnumSwitchMapping$12;
        public static final /* synthetic */ int[] $EnumSwitchMapping$13;
        public static final /* synthetic */ int[] $EnumSwitchMapping$14;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;
        public static final /* synthetic */ int[] $EnumSwitchMapping$9;

        static {
            $EnumSwitchMapping$0[MoveTaskMode.Steady.ordinal()] = 1;
            $EnumSwitchMapping$0[MoveTaskMode.Normal.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[RobotState.values().length];
            $EnumSwitchMapping$1[RobotState.Approaching.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[LocateCase.values().length];
            $EnumSwitchMapping$2[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$3 = new int[MoveTaskMode.values().length];
            $EnumSwitchMapping$3[MoveTaskMode.Steady.ordinal()] = 1;
            $EnumSwitchMapping$3[MoveTaskMode.Normal.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[MachineModel.values().length];
            $EnumSwitchMapping$4[MachineModel.BellaBot.ordinal()] = 1;
            $EnumSwitchMapping$5 = new int[LocateCase.values().length];
            $EnumSwitchMapping$5[LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$6 = new int[LocateCase.values().length];
            $EnumSwitchMapping$6[LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$7 = new int[LocateCase.values().length];
            $EnumSwitchMapping$7[LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$7[LocateCase.Slamware.ordinal()] = 2;
            $EnumSwitchMapping$8 = new int[LocateCase.values().length];
            $EnumSwitchMapping$8[LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$8[LocateCase.Slamware.ordinal()] = 2;
            $EnumSwitchMapping$9 = new int[NavigationStatus.values().length];
            $EnumSwitchMapping$9[NavigationStatus.Finished.ordinal()] = 1;
            $EnumSwitchMapping$9[NavigationStatus.FailTrack.ordinal()] = 2;
            $EnumSwitchMapping$9[NavigationStatus.FailOverTime.ordinal()] = 3;
            $EnumSwitchMapping$9[NavigationStatus.FailStuck.ordinal()] = 4;
            $EnumSwitchMapping$9[NavigationStatus.Navigating.ordinal()] = 5;
            $EnumSwitchMapping$9[NavigationStatus.Stuck.ordinal()] = 6;
            $EnumSwitchMapping$9[NavigationStatus.Arrived.ordinal()] = 7;
            $EnumSwitchMapping$9[NavigationStatus.Rotating.ordinal()] = 8;
            $EnumSwitchMapping$9[NavigationStatus.Avoid.ordinal()] = 9;
            $EnumSwitchMapping$10 = new int[WatchLevel.values().length];
            $EnumSwitchMapping$10[WatchLevel.Over.ordinal()] = 1;
            $EnumSwitchMapping$10[WatchLevel.Pause.ordinal()] = 2;
            $EnumSwitchMapping$11 = new int[MachineModel.values().length];
            $EnumSwitchMapping$11[MachineModel.Puductor.ordinal()] = 1;
            $EnumSwitchMapping$11[MachineModel.Ninetales.ordinal()] = 2;
            $EnumSwitchMapping$12 = new int[LocalizationStatusInfo.values().length];
            $EnumSwitchMapping$12[LocalizationStatusInfo.NoMarker.ordinal()] = 1;
            $EnumSwitchMapping$12[LocalizationStatusInfo.MarkerError.ordinal()] = 2;
            $EnumSwitchMapping$12[LocalizationStatusInfo.LaserLostRecovering.ordinal()] = 3;
            $EnumSwitchMapping$13 = new int[LocalizationStatusInfo.values().length];
            $EnumSwitchMapping$13[LocalizationStatusInfo.NoParam.ordinal()] = 1;
            $EnumSwitchMapping$13[LocalizationStatusInfo.NoInit.ordinal()] = 2;
            $EnumSwitchMapping$13[LocalizationStatusInfo.ParamError.ordinal()] = 3;
            $EnumSwitchMapping$13[LocalizationStatusInfo.MapError.ordinal()] = 4;
            $EnumSwitchMapping$13[LocalizationStatusInfo.NoMarker.ordinal()] = 5;
            $EnumSwitchMapping$13[LocalizationStatusInfo.MarkerError.ordinal()] = 6;
            $EnumSwitchMapping$13[LocalizationStatusInfo.LaserLocateLose.ordinal()] = 7;
            $EnumSwitchMapping$14 = new int[LocalizationStatusLevel.values().length];
            $EnumSwitchMapping$14[LocalizationStatusLevel.Normal.ordinal()] = 1;
            $EnumSwitchMapping$14[LocalizationStatusLevel.Warning.ordinal()] = 2;
            $EnumSwitchMapping$14[LocalizationStatusLevel.Error.ordinal()] = 3;
        }
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    @Deprecated(level = DeprecationLevel.WARNING, message = "getHeavyloadMode is deprecated", replaceWith = @ReplaceWith(expression = "getSmoothRunAndStopMode", imports = {}))
    public boolean getHeavyloadMode() {
        return false;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    @Deprecated(level = DeprecationLevel.WARNING, message = "setHeavyloadMode is deprecated", replaceWith = @ReplaceWith(expression = "setSmoothRunAndStopMode", imports = {}))
    public void setHeavyloadMode(boolean p0) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v20, types: [com.pudutech.mirsdk.MoveAction$scheduleListener$1] */
    /* JADX WARN: Type inference failed for: r10v7, types: [com.pudutech.mirsdk.MoveAction$speedLimitListener$1] */
    /* JADX WARN: Type inference failed for: r10v8, types: [com.pudutech.mirsdk.MoveAction$cliffDistanceListener$1] */
    /* JADX WARN: Type inference failed for: r10v9, types: [com.pudutech.mirsdk.MoveAction$reflectorInfoListener$1] */
    public MoveAction(RobotHardware robotHardware, AIDLConnection<MirCoreInterface> coreService, RobotStatus robotStatus, WatchDog watchDog, Function0<Unit> _onStuckReplan, Function2<? super RobotState, ? super String, Unit> _onStateChange) {
        Intrinsics.checkParameterIsNotNull(robotHardware, "robotHardware");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(robotStatus, "robotStatus");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(_onStuckReplan, "_onStuckReplan");
        Intrinsics.checkParameterIsNotNull(_onStateChange, "_onStateChange");
        this.robotHardware = robotHardware;
        this.coreService = coreService;
        this.robotStatus = robotStatus;
        this.watchDog = watchDog;
        this._onStuckReplan = _onStuckReplan;
        this._onStateChange = _onStateChange;
        this.mActionStopFlag = new AtomicBoolean(false);
        this.mJobBreakFlag = new AtomicBoolean(false);
        this.dropOccurflag = -1;
        this.dropLevel = -1;
        this.speedLimit = 0.6f;
        this.distancelimit = 5000;
        this.distancebrake = 5000;
        this.fallDropIrUploadNum = 2;
        this.speedLimitListener = new SpeedLimitListener.Stub() { // from class: com.pudutech.mirsdk.MoveAction$speedLimitListener$1
            @Override // com.pudutech.mirsdk.mircore.aidl.SpeedLimitListener
            public void callback(final boolean p0, final double p1) {
                String str;
                ThreadSafeListener threadSafeListener;
                str = MoveAction.this.TAG;
                Pdlog.m3273d(str, "SpeedLimitListener isInArea = " + p0 + "  maxLimitSpeed = " + p1);
                threadSafeListener = MoveAction.this.onMapAreaDetectionListener;
                threadSafeListener.notify(new Function2<MapAreaDetectionListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$speedLimitListener$1$callback$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(MapAreaDetectionListener mapAreaDetectionListener, String str2) {
                        invoke2(mapAreaDetectionListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MapAreaDetectionListener it, String str2) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it.OnSpeedLimitListener(p0, p1);
                    }
                });
            }
        };
        this.cliffDistanceListener = new CliffInfoListener.Stub() { // from class: com.pudutech.mirsdk.MoveAction$cliffDistanceListener$1
            @Override // com.pudutech.mirsdk.mircore.aidl.CliffInfoListener
            public void cliffDistance(final int p0) {
                String str;
                AtomicBoolean atomicBoolean;
                ThreadSafeListener threadSafeListener;
                str = MoveAction.this.TAG;
                Pdlog.m3273d(str, "cliffDistance is " + p0);
                MoveAction.this.dropOccurflag = p0;
                if (p0 == -1) {
                    return;
                }
                atomicBoolean = MoveAction.this.dorpDetectFlag;
                atomicBoolean.set(true);
                threadSafeListener = MoveAction.this.cliffDistanceStateListener;
                threadSafeListener.notify(new Function2<CliffDistanceStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$cliffDistanceListener$1$cliffDistance$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(CliffDistanceStateListener cliffDistanceStateListener, String str2) {
                        invoke2(cliffDistanceStateListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(CliffDistanceStateListener it, String str2) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it.cliffDistance(p0);
                    }
                });
                MoveAction.this.fallDropOccurEvent("RGBD drop event occur");
            }

            @Override // com.pudutech.mirsdk.mircore.aidl.CliffInfoListener
            public void cliffIrImg(String p0) {
                String str;
                String str2;
                ThreadSafeListener threadSafeListener;
                String str3;
                str = MoveAction.this.TAG;
                Pdlog.m3273d(str, "cliffIrImg is " + p0);
                if (p0 != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(p0);
                        Object obj = jSONObject.get("fd");
                        if (obj == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                        }
                        int intValue = ((Integer) obj).intValue();
                        if (intValue == -1) {
                            str3 = MoveAction.this.TAG;
                            Pdlog.m3274e(str3, "cliffIrImg fd is -1");
                            return;
                        }
                        Object obj2 = jSONObject.get("size");
                        if (obj2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                        }
                        final int intValue2 = ((Integer) obj2).intValue();
                        final ParcelFileDescriptor fromFd = ParcelFileDescriptor.fromFd(intValue);
                        threadSafeListener = MoveAction.this.cliffDistanceStateListener;
                        threadSafeListener.notify(new Function2<CliffDistanceStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$cliffDistanceListener$1$cliffIrImg$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(CliffDistanceStateListener cliffDistanceStateListener, String str4) {
                                invoke2(cliffDistanceStateListener, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CliffDistanceStateListener it, String str4) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                                it.cliffIrImg(fromFd, intValue2);
                            }
                        });
                    } catch (Exception e) {
                        str2 = MoveAction.this.TAG;
                        Pdlog.m3274e(str2, "cliffIrImg error : " + e.getMessage());
                    }
                }
            }
        };
        this.reflectorInfoListener = new ReflectorInfoListener.Stub() { // from class: com.pudutech.mirsdk.MoveAction$reflectorInfoListener$1
            @Override // com.pudutech.mirsdk.mircore.aidl.ReflectorInfoListener
            public void reflectorDistance(int p0) {
                AtomicBoolean atomicBoolean;
                MoveAction.this.fallDropOccurEvent("Reflector drop event occur " + p0);
                atomicBoolean = MoveAction.this.dorpDetectFlag;
                atomicBoolean.set(true);
            }
        };
        this.lockWheelContext = ThreadPoolDispatcherKt.newSingleThreadContext("lockWheel");
        this.lastChargeState = ChargeState.Idle;
        this.TAG = "move";
        this.atlas = new Atlas();
        this.procStat = new ProcStat();
        this.robotState = RobotState.Idle;
        this.destinations = new ArrayList();
        this.elevators = new ArrayList();
        this.eleWaiters = new ArrayList();
        this.accessControlPoints = new ArrayList();
        this.chargingPiles = new ArrayList();
        this.scheduleListenerName = "move";
        this.fillInStateListener = new ThreadSafeListener<>();
        this.cliffDistanceStateListener = new ThreadSafeListener<>();
        this.onMapAreaDetectionListener = new ThreadSafeListener<>();
        this.arrivedOnGoalCruise = new ThreadSafeListener<>();
        this.productType = new ProductMachineType(MachineModel.Hls, 0, 0);
        this.localizationStatus = new LocalizationStatus(null, null, null, 7, null);
        this.scheduleListener = new ScheduleListener.Stub() { // from class: com.pudutech.mirsdk.MoveAction$scheduleListener$1
            @Override // com.pudutech.mirsdk.mircore.ScheduleListener
            public void onScheduleMode(SchedulingMode p0) {
                Intrinsics.checkParameterIsNotNull(p0, "p0");
            }

            /* JADX WARN: Removed duplicated region for block: B:24:0x007b A[EDGE_INSN: B:24:0x007b->B:25:0x007b BREAK  A[LOOP:0: B:9:0x002c->B:31:?], SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:31:? A[LOOP:0: B:9:0x002c->B:31:?, LOOP_END, SYNTHETIC] */
            @Override // com.pudutech.mirsdk.mircore.ScheduleListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onScheduleFillIn(ScheduleFillInState p0) {
                ThreadSafeListener threadSafeListener;
                List list;
                Object obj;
                ThreadSafeListener threadSafeListener2;
                String str;
                ThreadSafeListener threadSafeListener3;
                boolean z;
                ThreadSafeListener threadSafeListener4;
                Intrinsics.checkParameterIsNotNull(p0, "p0");
                if (!p0.getChanged()) {
                    threadSafeListener = MoveAction.this.fillInStateListener;
                    threadSafeListener.notify(new Function2<FillInStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$scheduleListener$1$onScheduleFillIn$4
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(FillInStateListener fillInStateListener, String str2) {
                            invoke2(fillInStateListener, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FillInStateListener it, String str2) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            it.onFillIn(false, null);
                        }
                    });
                    return;
                }
                if (p0.getFinal_goal() == null) {
                    threadSafeListener4 = MoveAction.this.fillInStateListener;
                    threadSafeListener4.notify(new Function2<FillInStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$scheduleListener$1$onScheduleFillIn$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(FillInStateListener fillInStateListener, String str2) {
                            invoke2(fillInStateListener, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FillInStateListener it, String str2) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            it.onFillIn(true, null);
                        }
                    });
                    return;
                }
                list = MoveAction.this.destinations;
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    Destination destination = (Destination) obj;
                    double x = destination.getX();
                    Vector3d final_goal = p0.getFinal_goal();
                    if (final_goal == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Math.abs(x - final_goal.getX()) < 0.01d) {
                        double y = destination.getY();
                        Vector3d final_goal2 = p0.getFinal_goal();
                        if (final_goal2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Math.abs(y - final_goal2.getY()) < 0.01d) {
                            z = true;
                            if (!z) {
                                break;
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                }
                final Destination destination2 = (Destination) obj;
                if (destination2 == null) {
                    str = MoveAction.this.TAG;
                    Pdlog.m3274e(str, "Schedule Fill In Goal not exits in destinations list");
                    threadSafeListener3 = MoveAction.this.fillInStateListener;
                    threadSafeListener3.notify(new Function2<FillInStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$scheduleListener$1$onScheduleFillIn$2
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(FillInStateListener fillInStateListener, String str2) {
                            invoke2(fillInStateListener, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FillInStateListener it2, String str2) {
                            Intrinsics.checkParameterIsNotNull(it2, "it");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            it2.onFillIn(false, null);
                        }
                    });
                    return;
                }
                threadSafeListener2 = MoveAction.this.fillInStateListener;
                threadSafeListener2.notify(new Function2<FillInStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$scheduleListener$1$onScheduleFillIn$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(FillInStateListener fillInStateListener, String str2) {
                        invoke2(fillInStateListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FillInStateListener it2, String str2) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it2.onFillIn(true, Destination.this);
                    }
                });
            }
        };
        this.generalTask = new GeneralTask(this, this.coreService);
        this.elevatorTask = new ElevatorTask(this, this.coreService);
        this.moveTask = this.generalTask;
        this.accessControlTask = new AccessControlTask(this, this.coreService);
        this.currentFloorTask = new CurrentFloorTask(this, this.coreService);
        this.acrossFloorTask = new AcrossFloorTask(this, this.coreService);
        this.gatePassTask = new GatePassTask(this, this.coreService);
        this.curRobotPose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C47851(null), 3, null);
        this.currentFloorTask.setAccessControlTask(this.accessControlTask);
        this.currentFloorTask.setGeneralTask(this.generalTask);
        this.currentFloorTask.setGatePassTask(this.gatePassTask);
        this.acrossFloorTask.setCurrentFloorTask(this.currentFloorTask);
        this.acrossFloorTask.setElevatorTask(this.elevatorTask);
        ChargeFinder.INSTANCE.setMoveAction(this);
        this.dorpDetectFlag = new AtomicBoolean(false);
        this.lastSensorError = new ArrayList();
        this.navigationTask = new MoveAction$navigationTask$1(this);
        Double valueOf = Double.valueOf(0.0d);
        this.controlWatchDog = new Triple<>(0L, valueOf, valueOf);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void goCruisePath(int path_id, MoveTaskMode mode, List<String> stayPoints) {
        ProductMachineType productMachineType;
        Object obj;
        Job launch$default;
        MachineInfo machineInfo;
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Pdlog.m3275i(this.TAG, "goCruisePath " + path_id);
        this.stayPoints = stayPoints;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null || (productMachineType = machineInfo.getProductMachineType()) == null) {
            productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
        }
        this.productType = productMachineType;
        this.cruiseTask = true;
        this.generalTask.setSteadyMode(mode);
        this.generalTask.setPathSegments((PathSegments) null);
        this.generalTask.setBusinessType(BusinessType.Deliver);
        this.generalTask.setCruiseMode();
        this.moveTask = this.generalTask;
        Iterator<T> it = cruiseIDs.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual((String) obj, String.valueOf(path_id))) {
                    break;
                }
            }
        }
        if (obj != null) {
            BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$goCruisePath$2(this, null), 1, null);
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$goCruisePath$3(this, path_id, stayPoints, mode, null), 3, null);
            this.operatorjob = launch$default;
        } else {
            Pdlog.m3277w(this.TAG, "error cruise path id " + path_id + ", it not contain in current topo map");
        }
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<DestinationWithAccRange> destinationsOrderWithRange(List<String> destinationNames, boolean need_order) {
        Object obj;
        if (destinationNames == null) {
            Pdlog.m3277w(this.TAG, "nearestDestination error destinations:" + destinationNames);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : destinationNames) {
            Iterator<T> it = this.destinations.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((Destination) obj).getName(), str)) {
                    break;
                }
            }
            Destination destination = (Destination) obj;
            if (destination != null) {
                arrayList.add("{\"floor\":\"" + destination.getFloor() + "\", \"table\":\"" + str + "\"}");
            }
        }
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface != null) {
            return mirCoreInterface.orderDestinationWithRange(arrayList, need_order);
        }
        return null;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public DestinationWithAccRange nearestDestination(List<String> destinationNames) {
        DestinationWithAccRange destinationWithAccRange;
        Object obj;
        if (destinationNames == null) {
            Pdlog.m3277w(this.TAG, "nearestDestination error destinations:" + destinationNames);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : destinationNames) {
            Pdlog.m3273d(this.TAG, "find dest " + str + " in destination list");
            Iterator<T> it = this.destinations.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((Destination) obj).getName(), str)) {
                    break;
                }
            }
            Destination destination = (Destination) obj;
            if (destination != null) {
                Pdlog.m3273d(this.TAG, "In Destination list find dest: " + destination.getName() + " in floor " + destination.getFloor());
                arrayList.add("{\"floor\":\"" + destination.getFloor() + "\", \"table\":\"" + str + "\"}");
            }
        }
        if (arrayList.size() == 0) {
            Pdlog.m3277w(this.TAG, "check destinations is empty");
            return null;
        }
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        List<DestinationWithAccRange> orderDestinationWithRange = mirCoreInterface != null ? mirCoreInterface.orderDestinationWithRange(arrayList, true) : null;
        String str2 = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("nearest dest segments size ");
        sb.append(orderDestinationWithRange != null ? Integer.valueOf(orderDestinationWithRange.size()) : null);
        sb.append(" goal ");
        sb.append((orderDestinationWithRange == null || (destinationWithAccRange = (DestinationWithAccRange) CollectionsKt.last((List) orderDestinationWithRange)) == null) ? null : destinationWithAccRange.getId());
        objArr[0] = sb.toString();
        Pdlog.m3273d(str2, objArr);
        if (orderDestinationWithRange == null) {
            return null;
        }
        return new DestinationWithAccRange(orderDestinationWithRange.get(0).getFloor(), orderDestinationWithRange.get(0).getId(), orderDestinationWithRange.get(0).getRange());
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void pause() {
        this.moveTask.pause();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void resume() {
        this.moveTask.resume();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void rotate(double p0) {
        Job launch$default;
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$rotate$1(this, null), 1, null);
        Pdlog.m3273d(this.TAG, "recv rotate angular " + CommonKt.format(p0, 2) + "deg");
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$rotate$2(this, p0, null), 3, null);
        this.operatorjob = launch$default;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<Destination> getDestinations() {
        return this.destinations;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<Destination> getSpecifyMapDestinations(String pdmap) {
        Object obj;
        String def;
        if (TextUtils.isEmpty(pdmap)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(pdmap);
        Object obj2 = jSONObject.get("floor");
        if (obj2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        String str = (String) obj2;
        if (jSONObject.has("name")) {
            Object obj3 = jSONObject.get("name");
            if (obj3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            def = (String) obj3;
        } else {
            List<MapListConfig> list = this.atlas.getMapPackageConfig().getList();
            if (list == null) {
                Intrinsics.throwNpe();
            }
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), str)) {
                    break;
                }
            }
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            def = ((MapListConfig) obj).getDef();
            if (def == null) {
                Intrinsics.throwNpe();
            }
        }
        MapDecode load = this.atlas.load(def);
        if (load == null) {
            return null;
        }
        return loadSpecifyMapDestinations(str, load);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean goTo(String destination, MoveTaskMode mode) {
        Integer num;
        Object obj;
        PathSegments pathSegments;
        List<PathSegment> segments;
        List<PathSegment> segments2;
        PathSegments pathSegments2;
        List<PathSegment> segments3;
        List<PathSegment> segments4;
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Pdlog.m3273d(this.TAG, "goTo " + destination);
        this.cruiseTask = false;
        Iterator<T> it = this.destinations.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Destination) obj).getName(), destination)) {
                break;
            }
        }
        Destination destination2 = (Destination) obj;
        if (destination2 == null) {
            Pdlog.m3277w(this.TAG, "unknown destination:" + destination);
            return false;
        }
        Pdlog.m3273d(this.TAG, "find destination name: floor " + destination2.getFloor() + " id " + destination2.getName() + " mode:" + destination2.getMode() + " default floor " + this.atlas.getDefaultFloor());
        if (!Intrinsics.areEqual(destination2.getFloor(), this.atlas.getDefaultFloor())) {
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null) {
                pathSegments2 = mirCoreInterface.acrossFloorPathSegment("{\"floor\":\"" + destination2.getFloor() + "\", \"table\":\"" + destination + "\"}");
            } else {
                pathSegments2 = null;
            }
            if (pathSegments2 == null || (segments4 = pathSegments2.getSegments()) == null || !(!segments4.isEmpty())) {
                String str = this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("can not reach to destination: ");
                sb.append(destination);
                sb.append(" segments size is ");
                if (pathSegments2 != null && (segments3 = pathSegments2.getSegments()) != null) {
                    num = Integer.valueOf(segments3.size());
                }
                sb.append(num);
                objArr[0] = sb.toString();
                Pdlog.m3277w(str, objArr);
                return false;
            }
            String str2 = this.TAG;
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("task segments size ");
            List<PathSegment> segments5 = pathSegments2.getSegments();
            sb2.append(segments5 != null ? Integer.valueOf(segments5.size()) : null);
            objArr2[0] = sb2.toString();
            Pdlog.m3273d(str2, objArr2);
            this.acrossFloorTask.setSteadyMode(mode);
            this.acrossFloorTask.setPathSegments(pathSegments2);
            this.acrossFloorTask.setBusinessType(BusinessType.Deliver);
            this.moveTask = this.acrossFloorTask;
        } else {
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 != null) {
                pathSegments = mirCoreInterface2.currentFloorPathSegment("{\"floor\":\"" + destination2.getFloor() + "\", \"table\":\"" + destination + "\"}");
            } else {
                pathSegments = null;
            }
            if (pathSegments == null || (segments2 = pathSegments.getSegments()) == null || !(!segments2.isEmpty())) {
                String str3 = this.TAG;
                Object[] objArr3 = new Object[1];
                StringBuilder sb3 = new StringBuilder();
                sb3.append("can not reach to destination: ");
                sb3.append(destination);
                sb3.append(" segments size is ");
                if (pathSegments != null && (segments = pathSegments.getSegments()) != null) {
                    num = Integer.valueOf(segments.size());
                }
                sb3.append(num);
                objArr3[0] = sb3.toString();
                Pdlog.m3277w(str3, objArr3);
                return false;
            }
            String str4 = this.TAG;
            Object[] objArr4 = new Object[1];
            StringBuilder sb4 = new StringBuilder();
            sb4.append("task segments size ");
            List<PathSegment> segments6 = pathSegments.getSegments();
            sb4.append(segments6 != null ? Integer.valueOf(segments6.size()) : null);
            sb4.append(", final goal ");
            sb4.append(pathSegments.getFinalGoal());
            objArr4[0] = sb4.toString();
            Pdlog.m3273d(str4, objArr4);
            this.currentFloorTask.setSteadyMode(mode);
            this.currentFloorTask.setPathSegments(pathSegments);
            this.currentFloorTask.setBusinessType(BusinessType.Deliver);
            this.moveTask = this.currentFloorTask;
        }
        this.moveTask.startMoveAction();
        return true;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean goHome(String groupId, MoveTaskMode mode) {
        Intrinsics.checkParameterIsNotNull(groupId, "groupId");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append("]move to group ");
        sb.append(groupId);
        Pdlog.m3273d(str, sb.toString());
        return goHomeTask$default(this, groupId, mode, false, 4, null);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean goHomeFill(String groupId, MoveTaskMode mode, boolean isFillIn) {
        Intrinsics.checkParameterIsNotNull(groupId, "groupId");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append("]move to group: ");
        sb.append(groupId);
        sb.append(" , mode: ");
        sb.append(mode);
        sb.append(", isFillIn: ");
        sb.append(isFillIn);
        Pdlog.m3273d(str, sb.toString());
        return goHomeTask(groupId, mode, isFillIn);
    }

    static /* synthetic */ boolean goHomeTask$default(MoveAction moveAction, String str, MoveTaskMode moveTaskMode, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return moveAction.goHomeTask(str, moveTaskMode, z);
    }

    private final boolean goHomeTask(String groupId, MoveTaskMode mode, boolean isFillIn) {
        Integer num;
        Object obj;
        PathSegments pathSegments;
        List<PathSegment> segments;
        List<PathSegment> segments2;
        PathSegments pathSegments2;
        List<PathSegment> segments3;
        List<PathSegment> segments4;
        this.cruiseTask = false;
        Iterator<T> it = this.destinations.iterator();
        while (true) {
            num = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Destination destination = (Destination) obj;
            if (Intrinsics.areEqual(destination.getGroup(), groupId) && (Intrinsics.areEqual(destination.getMode(), Constants.POINT_TYPE_TABLE) ^ true)) {
                break;
            }
        }
        Destination destination2 = (Destination) obj;
        if (destination2 == null) {
            Pdlog.m3277w(this.TAG, "unknown group:" + groupId);
            return false;
        }
        Pdlog.m3273d(this.TAG, "find destination name: floor " + destination2.getFloor() + " id " + destination2.getName() + " mode:" + destination2.getMode());
        if (!Intrinsics.areEqual(destination2.getFloor(), this.atlas.getDefaultFloor())) {
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null) {
                pathSegments2 = mirCoreInterface.acrossFloorPathSegment("{\"floor\":\"" + destination2.getFloor() + "\", \"table\":\"" + destination2.getName() + "\"}");
            } else {
                pathSegments2 = null;
            }
            if (pathSegments2 == null || (segments4 = pathSegments2.getSegments()) == null || !(!segments4.isEmpty())) {
                String str = this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("can not reach to destination: ");
                sb.append(destination2.getName());
                sb.append(" segments size is ");
                if (pathSegments2 != null && (segments3 = pathSegments2.getSegments()) != null) {
                    num = Integer.valueOf(segments3.size());
                }
                sb.append(num);
                objArr[0] = sb.toString();
                Pdlog.m3277w(str, objArr);
                return false;
            }
            this.acrossFloorTask.setSteadyMode(mode);
            this.acrossFloorTask.setPathSegments(pathSegments2);
            this.acrossFloorTask.setBusinessType(BusinessType.GoGroup);
            this.acrossFloorTask.setFillIn(isFillIn);
            this.moveTask = this.acrossFloorTask;
        } else {
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 != null) {
                pathSegments = mirCoreInterface2.currentFloorPathSegment("{\"floor\":\"" + destination2.getFloor() + "\", \"table\":\"" + destination2.getName() + "\"}");
            } else {
                pathSegments = null;
            }
            if (pathSegments == null || (segments2 = pathSegments.getSegments()) == null || !(!segments2.isEmpty())) {
                String str2 = this.TAG;
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("can not reach to destination: ");
                sb2.append(destination2.getName());
                sb2.append(" segments size is ");
                if (pathSegments != null && (segments = pathSegments.getSegments()) != null) {
                    num = Integer.valueOf(segments.size());
                }
                sb2.append(num);
                objArr2[0] = sb2.toString();
                Pdlog.m3277w(str2, objArr2);
                return false;
            }
            this.currentFloorTask.setSteadyMode(mode);
            this.currentFloorTask.setPathSegments(pathSegments);
            this.currentFloorTask.setBusinessType(BusinessType.GoGroup);
            this.currentFloorTask.setFillIn(isFillIn);
            this.moveTask = this.currentFloorTask;
        }
        this.moveTask.startMoveAction();
        return true;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void goChargingPile(String p0) {
        NavigationInterface navigator;
        if (this.chargingPiles.size() <= 0 || p0 == null) {
            return;
        }
        this.moveTask.reset();
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface != null && (navigator = mirCoreInterface.getNavigator()) != null) {
            navigator.resetChargeTimes();
        }
        realGoCharging(p0);
    }

    public final void realGoCharging(String p0) {
        Integer num;
        Object obj;
        PathSegments pathSegments;
        List<PathSegment> segments;
        List<PathSegment> segments2;
        PathSegments pathSegments2;
        List<PathSegment> segments3;
        List<PathSegment> segments4;
        Pdlog.m3275i(this.TAG, "go charging to " + p0);
        this.cruiseTask = false;
        Iterator<T> it = this.chargingPiles.iterator();
        while (true) {
            num = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((ChargingPile) obj).getGroup(), p0)) {
                    break;
                }
            }
        }
        ChargingPile chargingPile = (ChargingPile) obj;
        if (chargingPile == null) {
            Pdlog.m3277w(this.TAG, "unknown charging pile:" + p0);
            return;
        }
        Pdlog.m3273d(this.TAG, "find charging pile:" + chargingPile.getId());
        if (!Intrinsics.areEqual(chargingPile.getFloor(), this.atlas.getDefaultFloor())) {
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null) {
                pathSegments2 = mirCoreInterface.acrossFloorPathSegment("{\"floor\":\"" + chargingPile.getFloor() + "\", \"table\":\"" + chargingPile.getId() + "\"}");
            } else {
                pathSegments2 = null;
            }
            if (pathSegments2 == null || (segments4 = pathSegments2.getSegments()) == null || !(!segments4.isEmpty())) {
                String str = this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("can not reach to charging pile: ");
                sb.append(p0);
                sb.append(" segments size is ");
                if (pathSegments2 != null && (segments3 = pathSegments2.getSegments()) != null) {
                    num = Integer.valueOf(segments3.size());
                }
                sb.append(num);
                objArr[0] = sb.toString();
                Pdlog.m3277w(str, objArr);
                return;
            }
            this.acrossFloorTask.setSteadyMode(MoveTaskMode.Normal);
            this.acrossFloorTask.setPathSegments(pathSegments2);
            this.acrossFloorTask.setBusinessType(BusinessType.GoCharging);
            this.moveTask = this.acrossFloorTask;
        } else {
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 != null) {
                pathSegments = mirCoreInterface2.currentFloorPathSegment("{\"floor\":\"" + chargingPile.getFloor() + "\", \"table\":\"" + chargingPile.getId() + "\"}");
            } else {
                pathSegments = null;
            }
            if (pathSegments == null || (segments2 = pathSegments.getSegments()) == null || !(!segments2.isEmpty())) {
                String str2 = this.TAG;
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("can not reach to charging pile: ");
                sb2.append(p0);
                sb2.append(" segments size is ");
                if (pathSegments != null && (segments = pathSegments.getSegments()) != null) {
                    num = Integer.valueOf(segments.size());
                }
                sb2.append(num);
                objArr2[0] = sb2.toString();
                Pdlog.m3277w(str2, objArr2);
                return;
            }
            this.currentFloorTask.setSteadyMode(MoveTaskMode.Normal);
            this.currentFloorTask.setPathSegments(pathSegments);
            this.currentFloorTask.setBusinessType(BusinessType.GoCharging);
            this.moveTask = this.currentFloorTask;
        }
        this.moveTask.startMoveAction();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addFillInStateListener(String name, FillInStateListener listener) {
        if (name == null || listener == null) {
            return;
        }
        this.fillInStateListener.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeFillInStateListener(String name) {
        if (name != null) {
            this.fillInStateListener.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addCliffDistanceStateListener(String name, CliffDistanceStateListener listener) {
        if (name == null || listener == null) {
            return;
        }
        this.cliffDistanceStateListener.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeCliffDistanceStateListener(String name) {
        if (name != null) {
            this.cliffDistanceStateListener.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addMapAreaDetectionListener(String p0, MapAreaDetectionListener p1) {
        boolean z = true;
        Pdlog.m3273d(this.TAG, "addMapAreaDetectionListener p0 = " + p0 + " p1 = " + p1);
        String str = p0;
        if (str != null && !StringsKt.isBlank(str)) {
            z = false;
        }
        if (z || p1 == null) {
            return;
        }
        this.onMapAreaDetectionListener.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeMapAreaDetectionListener(String p0) {
        String str = p0;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        this.onMapAreaDetectionListener.remove(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void updateTrayDis(double level) {
        NavigationInterface navigator;
        Pdlog.m3273d(this.TAG, "addAroundLevel called, level: " + level);
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return;
        }
        navigator.updateTrayDis(level);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public double getTrayDis() {
        NavigationInterface navigator;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        Double valueOf = (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) ? null : Double.valueOf(navigator.getTrayDis());
        Pdlog.m3273d(this.TAG, "getAroundLevel called, aroundLevel: " + valueOf);
        if (valueOf != null) {
            return valueOf.doubleValue();
        }
        return 0.0d;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void quitFillIn() {
        ScheduleInterface scheduler;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (scheduler = mirCoreInterface.getScheduler()) == null) {
            return;
        }
        scheduler.quitFillIn();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addElevetorRequestListener(String p0, ElevatorRequestListener p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.elevatorTask.getElevatorListener().add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeElevatorRequestListener(String p0) {
        if (p0 == null) {
            return;
        }
        this.elevatorTask.getElevatorListener().remove(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public double getCallElevatorDistance() {
        return this.elevatorTask.getCallDistance();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void setCallElevatorDistance(double p0) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$setCallElevatorDistance$1(this, p0, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void addAccessDoorStateListener(String p0, AccessDoorListener p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.accessControlTask.getAccessControlListener().add(p0, p1);
        this.gatePassTask.getAccessControlListener().add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void removeAccessDoorStateListener(String p0) {
        if (p0 == null) {
            return;
        }
        this.accessControlTask.getAccessControlListener().remove(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void setCallAccessDoorDistance(double p0) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$setCallAccessDoorDistance$1(this, p0, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public double getCallAccessDoorDistance() {
        return this.accessControlTask.getCallDistance();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean swithSpeedLevel(MoveMode mode, String level) {
        NavigationInterface navigator;
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(level, "level");
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return false;
        }
        return navigator.switchSpeedLevel(mode, level);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public String[] getSpeedLevels() {
        String[] strArr;
        NavigationInterface navigator;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null || (strArr = navigator.getSpeedLevels()) == null) {
            IntRange intRange = new IntRange(1, 8);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator<Integer> it = intRange.iterator();
            while (it.hasNext()) {
                arrayList.add(CommonKt.format((((IntIterator) it).nextInt() * 0.1d) + 0.4d, 2));
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        }
        Pdlog.m3273d(this.TAG, "speed list " + Arrays.toString(strArr));
        return strArr;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public String getSpeedLevel(MoveMode p0) {
        NavigationInterface navigator;
        String speedLevel;
        if (p0 == null) {
            return String.valueOf(0.0d);
        }
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        return (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null || (speedLevel = navigator.getSpeedLevel(p0)) == null) ? String.valueOf(0.0d) : speedLevel;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void enableStuckReplan(boolean p0) {
        NavigationInterface navigator;
        Pdlog.m3273d(this.TAG, "enableStuckReplan " + p0);
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return;
        }
        navigator.enableStuckReplan(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void enableDynamicRoadblock(boolean p0) {
        NavigationInterface navigator;
        Pdlog.m3273d(this.TAG, "enableDynamicRoadblock " + p0);
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return;
        }
        navigator.enableDynamicRoadblock(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean setReplanWaitTime(int p0) {
        NavigationInterface navigator;
        Pdlog.m3273d(this.TAG, "setReplanWaitTime " + p0);
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return false;
        }
        return navigator.setReplanWaitTime(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean setRoadBlockTime(int time) {
        NavigationInterface navigator;
        Pdlog.m3273d(this.TAG, "setRoadBlockTime " + time);
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return false;
        }
        return navigator.setRoadblockTimeout(time);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public int getRoadBlockTime() {
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null) {
            Intrinsics.throwNpe();
        }
        NavigationInterface navigator = mirCoreInterface.getNavigator();
        if (navigator == null) {
            Intrinsics.throwNpe();
        }
        return navigator.getRoadblockTimeout();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean addCoverAround(boolean p0) {
        NavigationInterface navigator;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return false;
        }
        return navigator.addCoverAround(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean getCoverAround() {
        NavigationInterface navigator;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return false;
        }
        return navigator.getCoverAround();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void setSmoothRunAndStopMode(SmoothMode p0) {
        MirCoreInterface mirCoreInterface;
        NavigationInterface navigator;
        if (p0 == null || (mirCoreInterface = this.coreService.getInterface()) == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return;
        }
        navigator.updateSmoothMode(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public SmoothMode getSmoothRunAndStopMode() {
        NavigationInterface navigator;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
            return null;
        }
        return navigator.getSmoothMode();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<ChargingPileInfo> getChargingPileInfos() {
        ArrayList arrayList = new ArrayList();
        for (ChargingPile chargingPile : this.chargingPiles) {
            arrayList.add(new ChargingPileInfo(chargingPile.getFloor(), chargingPile.getId(), chargingPile.getPose(), chargingPile.getAlignRange(), chargingPile.getGroup(), chargingPile.getMac()));
        }
        return arrayList;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void taskStop() {
        this.moveTask.taskStop();
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public boolean isNearByChangePile() {
        Pdlog.m3273d(this.TAG, "isNearByChangePile ChargeState = " + this.lastChargeState);
        if (this.lastChargeState == ChargeState.ChargeFullUsePile || this.lastChargeState == ChargeState.CharingUsePile || this.lastChargeState == ChargeState.StopChargeUsePile) {
            return true;
        }
        Destination foundNearChargingPiles = foundNearChargingPiles();
        if (foundNearChargingPiles == null) {
            return false;
        }
        Pdlog.m3273d(this.TAG, "isNearByChangePile found floor " + foundNearChargingPiles.getFloor() + " pile " + foundNearChargingPiles.getName());
        return true;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void passAccessDoor() {
        this.gatePassTask.passAccessDoor();
    }

    public final boolean isLockWheel() {
        return this.isLockWheel;
    }

    public final void lockWheel(boolean isLock) {
        Job launch$default;
        if (isLock == this.isLockWheel) {
            return;
        }
        if (this.dorpDetectFlag.get() && !isLock) {
            Pdlog.m3274e(this.TAG, "can not unlock wheel when drop detecting");
            return;
        }
        this.isLockWheel = isLock;
        if (isLock) {
            Job job = this.lockWheelJob;
            if (job != null && job.isActive()) {
                Pdlog.m3273d(this.TAG, "is In lock wheel");
                return;
            }
            Pdlog.m3273d(this.TAG, "start lock wheel");
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, this.lockWheelContext, null, new MoveAction$lockWheel$1(this, null), 2, null);
            this.lockWheelJob = launch$default;
            return;
        }
        if (this.lockWheelJob == null) {
            return;
        }
        Pdlog.m3273d(this.TAG, "stop lock wheel");
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$lockWheel$2(this, null), 1, null);
    }

    /* renamed from: isInLockWheel, reason: from getter */
    public final boolean getIsLockWheel() {
        return this.isLockWheel;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public List<String> getChargingPiles() {
        ArrayList arrayList = new ArrayList();
        Iterator<ChargingPile> it = this.chargingPiles.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getGroup());
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x013c A[EDGE_INSN: B:16:0x013c->B:17:0x013c BREAK  A[LOOP:0: B:2:0x000a->B:24:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[LOOP:0: B:2:0x000a->B:24:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Destination foundNearChargingPiles() {
        Object obj;
        boolean z;
        Iterator<T> it = this.chargingPiles.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            ChargingPile chargingPile = (ChargingPile) obj;
            Pdlog.m3273d(this.TAG, "foundNearChargingPiles floor:" + chargingPile.getFloor() + " defaultFloor:" + this.atlas.getDefaultFloor());
            if (Intrinsics.areEqual(chargingPile.getFloor(), this.atlas.getDefaultFloor())) {
                double x = this.curRobotPose.getX() - chargingPile.getPose().getX();
                double y = this.curRobotPose.getY() - chargingPile.getPose().getY();
                double abs = Math.abs(this.curRobotPose.getZ() - chargingPile.getPose().getZ());
                if (abs > 3.141592653589793d) {
                    abs -= 6.283185307179586d;
                }
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("foundNearChargingPiles cur[");
                sb.append(this.curRobotPose.getX());
                sb.append(", ");
                sb.append(this.curRobotPose.getY());
                sb.append(',');
                sb.append(this.curRobotPose.getZ());
                sb.append("] pose[");
                sb.append(chargingPile.getPose().getX());
                sb.append(',');
                sb.append(chargingPile.getPose().getY());
                sb.append(",,");
                sb.append(chargingPile.getPose().getZ());
                sb.append("] ");
                sb.append("result ");
                double d = (x * x) + (y * y);
                sb.append(Math.sqrt(d));
                sb.append(" z: ");
                sb.append(abs);
                Pdlog.m3273d(str, sb.toString());
                if (Math.sqrt(d) < 0.5d && Math.abs(abs) > 1.5707963267948966d) {
                    z = true;
                    if (!z) {
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
            }
        }
        ChargingPile chargingPile2 = (ChargingPile) obj;
        if (chargingPile2 != null) {
            Vector3d vector3d = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
            vector3d.setX(chargingPile2.getPose().getX() + Math.cos(chargingPile2.getPose().getZ()));
            vector3d.setY(chargingPile2.getPose().getY() + Math.sin(chargingPile2.getPose().getZ()));
            vector3d.setZ(chargingPile2.getPose().getZ());
            Pdlog.m3273d(this.TAG, "foundNearChargingPiles success id:" + chargingPile2.getId() + ",floor:" + chargingPile2.getFloor() + ',' + vector3d.getX() + ',' + vector3d.getY() + ',' + vector3d.getZ());
            return new Destination(chargingPile2.getId(), chargingPile2.getFloor(), vector3d.getX(), vector3d.getY(), vector3d.getZ(), false, "", "", false);
        }
        Pdlog.m3273d(this.TAG, "foundNearChargingPiles fail");
        return null;
    }

    public final void onBattery(int percent, ChargeState chargeState) {
        Intrinsics.checkParameterIsNotNull(chargeState, "chargeState");
        this.lastChargeState = chargeState;
    }

    public final Atlas getAtlas() {
        return this.atlas;
    }

    public final List<ElevatorWaiter> getElevWaiters() {
        return this.eleWaiters;
    }

    public final ThreadSafeListener<Function1<Boolean, Unit>> getArrivedOnGoalCruise() {
        return this.arrivedOnGoalCruise;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MoveAction.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/MoveAction$Companion;", "", "()V", "cruiseIDs", "", "", "getCruiseIDs", "()Ljava/util/List;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<String> getCruiseIDs() {
            return MoveAction.cruiseIDs;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MoveAction.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u001f\u0010\u0002\u001a\u00020\u0003X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/MoveAction$WatchLevel;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "setId-7apg3OU", "(B)V", "B", "Normal", "Pause", "Over", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum WatchLevel {
        Normal((byte) 0),
        Pause((byte) 1),
        Over((byte) 2);

        private byte id;

        WatchLevel(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* renamed from: setId-7apg3OU, reason: not valid java name */
        public final void m4408setId7apg3OU(byte b) {
            this.id = b;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MoveAction.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$1", m3970f = "MoveAction.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4}, m3972l = {811, 814, 817, 822, 825}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "lastProcTime", "$this$launch", "lastProcTime", C3898x.f4338g, "$this$launch", "lastProcTime", "$this$launch", "lastProcTime", "$this$launch", "lastProcTime"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
    /* renamed from: com.pudutech.mirsdk.MoveAction$1 */
    /* loaded from: classes5.dex */
    static final class C47851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5519p$;

        C47851(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C47851 c47851 = new C47851(completion);
            c47851.f5519p$ = (CoroutineScope) obj;
            return c47851;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C47851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00df A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0098 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00be  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00e0  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00dd -> B:10:0x005d). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00b4 -> B:10:0x005d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            int i;
            C47851 c47851;
            Exception e;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5519p$;
                i = 0;
            } else if (i2 == 1) {
                i = this.I$0;
                coroutineScope = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    c47851 = this;
                } catch (Exception e2) {
                    e = e2;
                    c47851 = this;
                    c47851.L$0 = coroutineScope;
                    c47851.I$0 = i;
                    c47851.L$1 = e;
                    c47851.label = 2;
                    if (DelayKt.delay(60000L, c47851) == coroutine_suspended) {
                    }
                    c47851.L$0 = coroutineScope;
                    c47851.I$0 = i;
                    c47851.label = 5;
                    if (DelayKt.delay(100L, c47851) == coroutine_suspended) {
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                }
                i = 0;
                c47851.L$0 = coroutineScope;
                c47851.I$0 = i;
                c47851.label = 5;
                if (DelayKt.delay(100L, c47851) == coroutine_suspended) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else {
                if (i2 == 2) {
                } else if (i2 == 3) {
                    i = this.I$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c47851 = this;
                    Job job = MoveAction.this.moveJob;
                    if (job != null || !job.isActive()) {
                        i += 1000;
                        if (i >= 60000) {
                            ProcStat procStat = MoveAction.this.procStat;
                            c47851.L$0 = coroutineScope;
                            c47851.I$0 = 0;
                            c47851.label = 4;
                            if (procStat.procState(c47851) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            i = 0;
                        }
                        c47851.L$0 = coroutineScope;
                        c47851.I$0 = i;
                        c47851.label = 5;
                        if (DelayKt.delay(100L, c47851) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        Job job2 = MoveAction.this.moveJob;
                        if (job2 != null && job2.isActive()) {
                            try {
                            } catch (Exception e3) {
                                e = e3;
                                c47851.L$0 = coroutineScope;
                                c47851.I$0 = i;
                                c47851.L$1 = e;
                                c47851.label = 2;
                                if (DelayKt.delay(60000L, c47851) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                c47851.L$0 = coroutineScope;
                                c47851.I$0 = i;
                                c47851.label = 5;
                                if (DelayKt.delay(100L, c47851) == coroutine_suspended) {
                                }
                                if (CoroutineScopeKt.isActive(coroutineScope)) {
                                }
                            }
                            ProcStat procStat2 = MoveAction.this.procStat;
                            c47851.L$0 = coroutineScope;
                            c47851.I$0 = i;
                            c47851.label = 1;
                            if (procStat2.procState(c47851) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            i = 0;
                            c47851.L$0 = coroutineScope;
                            c47851.I$0 = i;
                            c47851.label = 5;
                            if (DelayKt.delay(100L, c47851) == coroutine_suspended) {
                            }
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                            }
                        } else {
                            c47851.L$0 = coroutineScope;
                            c47851.I$0 = i;
                            c47851.label = 3;
                            if (DelayKt.delay(1000L, c47851) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Job job3 = MoveAction.this.moveJob;
                            if (job3 != null) {
                            }
                            i += 1000;
                            if (i >= 60000) {
                            }
                            c47851.L$0 = coroutineScope;
                            c47851.I$0 = i;
                            c47851.label = 5;
                            if (DelayKt.delay(100L, c47851) == coroutine_suspended) {
                            }
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                            }
                        }
                    } else {
                        return Unit.INSTANCE;
                    }
                } else if (i2 != 4) {
                    if (i2 != 5) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    i = this.I$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                i = this.I$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c47851 = this;
                c47851.L$0 = coroutineScope;
                c47851.I$0 = i;
                c47851.label = 5;
                if (DelayKt.delay(100L, c47851) == coroutine_suspended) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
            c47851 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
    }

    private final void registerBluetoothChargePileCan() {
        HardwareInterface hardwareInterface;
        if (this.isRegisterBluetooth || (hardwareInterface = this.robotHardware.getInterface()) == null) {
            return;
        }
        hardwareInterface.addCANDataListener("bluetooth_charge", new byte[]{(byte) 167}, new ICANData.Stub() { // from class: com.pudutech.mirsdk.MoveAction$registerBluetoothChargePileCan$$inlined$apply$lambda$1
            @Override // com.pudutech.mirsdk.hardware.ICANData
            public void onData(int p0, byte[] p1) {
                String str;
                RobotHardware robotHardware;
                if (p1 != null) {
                    str = MoveAction.this.TAG;
                    Pdlog.m3273d(str, "bluetooth_charge onData " + com.pudutech.mirsdk.base.CommonKt.toHexString(p1));
                    if (p1[1] == ((byte) 1)) {
                        robotHardware = MoveAction.this.robotHardware;
                        HardwareInterface hardwareInterface2 = robotHardware.getInterface();
                        if (hardwareInterface2 != null) {
                            hardwareInterface2.notifyBTChargePileArrivedState(true);
                            return;
                        }
                        return;
                    }
                    if (p1[1] == ((byte) 2)) {
                        ChargeFinder.INSTANCE.foundAndConnectChargePile(p1);
                    }
                }
            }
        });
        this.isRegisterBluetooth = true;
    }

    public final boolean init() {
        MapDecode loadSpecifyFloorMap;
        this.destinations.clear();
        cruiseIDs.clear();
        this.elevators.clear();
        this.eleWaiters.clear();
        this.accessControlPoints.clear();
        this.chargingPiles.clear();
        registerBluetoothChargePileCan();
        if (!this.atlas.load()) {
            return false;
        }
        Pdlog.m3273d(this.TAG, "chk atlas default floor " + this.atlas.getDefaultFloor());
        loadMapDestinations(this.atlas.getDefaultFloor(), this.atlas.getMap());
        loadMapNunDestinationElements(this.atlas.getDefaultFloor(), this.atlas.getMap());
        analyZonesData(this.atlas.getMap());
        Map<String, FloorsMapList> multiFloorsTopoMapList = this.atlas.getMultiFloorsTopoMapList();
        initElvCommuicateScheme();
        initAccCommunicateScheme();
        this.gatePassTask.initGateController(SDKConfig.INSTANCE.getProcessContext());
        Pdlog.m3273d(this.TAG, "chk default topo list size " + multiFloorsTopoMapList.size());
        if (multiFloorsTopoMapList.size() > 1) {
            for (Map.Entry<String, FloorsMapList> entry : multiFloorsTopoMapList.entrySet()) {
                if ((!Intrinsics.areEqual(entry.getKey(), this.atlas.getDefaultFloor())) && (loadSpecifyFloorMap = this.atlas.loadSpecifyFloorMap(entry.getValue().getPath())) != null) {
                    loadMapDestinations(entry.getKey(), loadSpecifyFloorMap);
                }
            }
        }
        Pdlog.m3273d(this.TAG, "destinations size:" + this.destinations.size());
        return checkNoRedundancyDestination();
    }

    public final void bindAreaDetectListener() {
        AreaDetectInterface areaDetect;
        if (this.coreService.getInterface() != null) {
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if ((mirCoreInterface != null ? mirCoreInterface.getAreaDetect() : null) != null) {
                MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
                if (mirCoreInterface2 != null && (areaDetect = mirCoreInterface2.getAreaDetect()) != null) {
                    areaDetect.addSpeedLimitListener("speedLimit", this.speedLimitListener);
                }
                Pdlog.m3273d(this.TAG, "bindAreaDetectListener is success");
                return;
            }
        }
        Pdlog.m3273d(this.TAG, "bindAreaDetectListener  is null");
    }

    public final boolean updateSpecifyDefaultMap(String floor, String mapname) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(mapname, "mapname");
        int i = 1;
        if (!(!Intrinsics.areEqual(floor, this.atlas.getDefaultFloor())) || !this.atlas.updateSpecifyDefaultMap(floor, mapname)) {
            return false;
        }
        Map<String, FloorsMapList> multiFloorsTopoMapList = this.atlas.getMultiFloorsTopoMapList();
        if (multiFloorsTopoMapList.size() > 1) {
            this.destinations.clear();
            for (Map.Entry<String, FloorsMapList> entry : multiFloorsTopoMapList.entrySet()) {
                MapDecode loadSpecifyFloorMap = this.atlas.loadSpecifyFloorMap(entry.getValue().getPath());
                if (loadSpecifyFloorMap != null) {
                    Iterator<Source> it = loadSpecifyFloorMap.getSource().iterator();
                    while (it.hasNext()) {
                        Source next = it.next();
                        String str = this.TAG;
                        Object[] objArr = new Object[i];
                        objArr[0] = "chk source id " + next.getId() + ' ' + next.getHigh_precision();
                        Pdlog.m3273d(str, objArr);
                        this.destinations.add(new Destination(next.getId(), entry.getKey(), next.getPosition()[0], next.getPosition()[i], next.getDir(), next.getDoubleDir(), next.getMode(), next.getGroup(), next.getHigh_precision()));
                        i = 1;
                    }
                }
                i = 1;
            }
        }
        return true;
    }

    public final void clearTopoData() {
        this.destinations.clear();
        cruiseIDs.clear();
        this.elevators.clear();
        this.eleWaiters.clear();
        this.accessControlPoints.clear();
        this.chargingPiles.clear();
    }

    public final void addSchedulerListener() {
        ScheduleInterface scheduler;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (scheduler = mirCoreInterface.getScheduler()) == null) {
            return;
        }
        scheduler.addScheduleListener(this.scheduleListenerName, this.scheduleListener);
    }

    public final void hardwareEmergency(boolean brake) {
        Job launch$default;
        Job launch$default2;
        if (brake) {
            Job job = this.moveJob;
            if (job == null || !job.isActive()) {
                return;
            }
            launch$default2 = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$hardwareEmergency$1(this, null), 3, null);
            this.operatorjob = launch$default2;
            return;
        }
        if (this.brakedWhenMoving) {
            this.brakedWhenMoving = false;
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$hardwareEmergency$2(this, null), 3, null);
            this.operatorjob = launch$default;
        }
    }

    public final void fallDropOccurEvent(String info) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(info, "info");
        Job job = this.moveJob;
        if (job == null || !job.isActive()) {
            return;
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$fallDropOccurEvent$1(this, null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$fallDropOccurEvent$2(this, info, null), 3, null);
        this.operatorjob = launch$default;
    }

    @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
    public void clearDropEvent() {
        Pdlog.m3273d(this.TAG, "clearDropEvent ");
        this.dorpDetectFlag.set(false);
        lockWheel(false);
    }

    public final void triggerError(String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        Job job = this.errorJob;
        if (job != null && job.isActive()) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$triggerError$1(this, error, null), 3, null);
            return;
        }
        Job job2 = this.moveJob;
        if (job2 == null || !job2.isActive()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$triggerError$2(this, error, null), 3, null);
    }

    public final void suspendWarningWelfunction(Pair<Boolean, String> warning) {
        Job job;
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(warning, "warning");
        if (warning.getFirst().booleanValue()) {
            Job job2 = this.errorJob;
            if ((job2 == null || !job2.isActive()) && (job = this.moveJob) != null && job.isActive()) {
                if (WhenMappings.$EnumSwitchMapping$1[this.robotState.ordinal()] != 1) {
                    launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$suspendWarningWelfunction$2(this, warning, null), 3, null);
                    this.errorJob = launch$default;
                    return;
                } else {
                    Pdlog.m3273d(this.TAG, "arrived when error suspend");
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$suspendWarningWelfunction$1(this, null), 3, null);
                    return;
                }
            }
            return;
        }
        Job job3 = this.errorJob;
        if (job3 == null || !job3.isActive()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$suspendWarningWelfunction$3(this, null), 3, null);
    }

    public final void addPoseListener() {
        LocalizationInterface localizer;
        Pdlog.m3273d(this.TAG, "add pose listener for elevator task, ele count " + this.elevators.size() + " or access control task, acc count " + this.accessControlPoints.size());
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (localizer = mirCoreInterface.getLocalizer()) == null) {
            return;
        }
        localizer.setLocateListener("mqtt", new LocalizationListener.Stub() { // from class: com.pudutech.mirsdk.MoveAction$addPoseListener$1
            @Override // com.pudutech.mirsdk.mircore.LocalizationListener
            public void updateRobotDirection(Vector3d p0) {
            }

            @Override // com.pudutech.mirsdk.mircore.LocalizationListener
            public void updateRobotPosition(Vector3d p0) {
                List list;
                List list2;
                Vector3d vector3d;
                Vector3d vector3d2;
                Vector3d vector3d3;
                AccessControlTask accessControlTask;
                ElevatorTask elevatorTask;
                list = MoveAction.this.elevators;
                if (!list.isEmpty()) {
                    elevatorTask = MoveAction.this.elevatorTask;
                    elevatorTask.updatePose(p0);
                }
                list2 = MoveAction.this.accessControlPoints;
                if (!list2.isEmpty()) {
                    accessControlTask = MoveAction.this.accessControlTask;
                    accessControlTask.updatePose(p0);
                }
                if (p0 != null) {
                    vector3d = MoveAction.this.curRobotPose;
                    vector3d.setX(p0.getX());
                    vector3d2 = MoveAction.this.curRobotPose;
                    vector3d2.setY(p0.getY());
                    vector3d3 = MoveAction.this.curRobotPose;
                    vector3d3.setZ(p0.getZ());
                }
            }
        });
    }

    public final LocateCase getLocateCase() {
        return this.atlas.getLocateCase();
    }

    public final Vector3d getCurRobotPose() {
        return this.curRobotPose;
    }

    public final List<ChargingPile> getChargingPilesList() {
        return this.chargingPiles;
    }

    public final void reloadMap(String floor, Function0<Unit> result) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(result, "result");
        Atlas atlas = this.atlas;
        List<MapListConfig> list = atlas.getMapPackageConfig().getList();
        if (list == null) {
            Intrinsics.throwNpe();
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), floor)) {
                    break;
                }
            }
        }
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        String def = ((MapListConfig) obj).getDef();
        if (def == null) {
            Intrinsics.throwNpe();
        }
        atlas.setMapFileName(def);
        String pdmapFullName = this.atlas.getPdmapFullName();
        boolean init = init();
        Pdlog.m3273d(this.TAG, "switch result " + init);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        if (!init) {
            booleanRef.element = false;
            clearTopoData();
            onStateChange(SDKRobotState.Error, "{\"error_type\":\"InternalError\",\"level\":\"Error\",\"detail\":\"reload map failed before out of elevator\"}");
        } else {
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null) {
                mirCoreInterface.switchAutoExposure(false);
            }
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 != null) {
                mirCoreInterface2.reloadPdmap(this.atlas.getDefaultFloorIndex(), pdmapFullName, null, new ReloadMapResultListener.Stub() { // from class: com.pudutech.mirsdk.MoveAction$reloadMap$2
                    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                    public void reloadMapFail() {
                        MoveAction.this.clearTopoData();
                        booleanRef.element = false;
                        MoveAction.this.onStateChange(SDKRobotState.Error, "{\"error_type\":\"InternalError\",\"level\":\"Error\",\"detail\":\"reload map failed before out of elevator\"}");
                    }

                    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                    public void reloadMapSuccess() {
                        booleanRef.element = false;
                    }
                });
            } else {
                booleanRef.element = false;
            }
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$reloadMap$3(this, booleanRef, floor, result, null), 3, null);
    }

    public static /* synthetic */ void moveToPosition$default(MoveAction moveAction, MoveTaskMode moveTaskMode, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            moveTaskMode = MoveTaskMode.Normal;
        }
        moveAction.moveToPosition(moveTaskMode, function0);
    }

    public final void moveToPosition(MoveTaskMode moveTaskMode, Function0<Boolean> task) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(moveTaskMode, "moveTaskMode");
        Intrinsics.checkParameterIsNotNull(task, "task");
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$moveToPosition$1(this, null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$moveToPosition$2(this, task, moveTaskMode, null), 3, null);
        this.operatorjob = launch$default;
    }

    public final void actionPause() {
        Job launch$default;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append("]pause");
        Pdlog.m3275i(str, sb.toString());
        this.brakedWhenMoving = false;
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$actionPause$1(this, null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$actionPause$2(this, null), 3, null);
        this.operatorjob = launch$default;
    }

    public final void actionResume() {
        Job launch$default;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append("]resume");
        Pdlog.m3275i(str, sb.toString());
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$actionResume$1(this, null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$actionResume$2(this, null), 3, null);
        this.operatorjob = launch$default;
    }

    public static /* synthetic */ Object actionStop$default(MoveAction moveAction, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return moveAction.actionStop(z, continuation);
    }

    public final synchronized Object actionStop(boolean z, Continuation<? super Unit> continuation) {
        Object stop = stop(z, continuation);
        if (stop == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return stop;
        }
        return Unit.INSTANCE;
    }

    public final void informStateChange(RobotState state, String desc) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        this._onStateChange.invoke(state, desc);
    }

    public final Object controlWheelCmd(boolean z, Continuation<? super Unit> continuation) {
        Unit unit;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlWheel(0.0d, 0.0d, z);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
    }

    public final String checkLocateFloors(HashSet<Integer> markers) {
        return this.atlas.checkMarkersFloor(markers);
    }

    public final Map<String, Vector3d> getElevatorPose() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ElevatorSource elevatorSource : this.elevators) {
            linkedHashMap.put(elevatorSource.getId(), elevatorSource.getPose());
        }
        return linkedHashMap;
    }

    public final ElevatorConnectionType getElevatorConnection() {
        return ElevatorTask.INSTANCE.getElevatorConnectionType();
    }

    public final void setElevatorConnection(ElevatorConnectionType type, Context context) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(context, "context");
        ElevatorTask.INSTANCE.setElevatorConnectionType(type);
        if (!this.elevators.isEmpty()) {
            this.elevatorTask.initClient(context);
        }
    }

    public final SlamStatus getLocationStatus() {
        LocalizationInterface localizer;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface == null || (localizer = mirCoreInterface.getLocalizer()) == null) {
            return null;
        }
        return localizer.getSlamStatus();
    }

    public final AccessControlServer getAccessControlServer() {
        return AccessControlTask.INSTANCE.getAccessConnectionType();
    }

    public final void setAccessControlServer(AccessControlServer server, Context context) {
        Intrinsics.checkParameterIsNotNull(server, "server");
        Intrinsics.checkParameterIsNotNull(context, "context");
        AccessControlTask.INSTANCE.setAccessConnectionType(server);
        Pdlog.m3273d(this.TAG, "current map has access control " + this.accessControlPoints.size() + " acc server " + server.name());
        if (!this.accessControlPoints.isEmpty()) {
            this.accessControlTask.initClient(context, this.accessControlPoints);
        }
    }

    public final boolean findAccessControl() {
        return this.accessControlPoints.size() >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isGoChargingTask() {
        if (!this.generalTask.isGoChargingTask()) {
            return false;
        }
        if ((this.moveTask instanceof AcrossFloorTask) && (this.acrossFloorTask.getMoveTask() instanceof CurrentFloorTask) && (this.currentFloorTask.getMoveTask() instanceof GeneralTask)) {
            return true;
        }
        return (this.moveTask instanceof CurrentFloorTask) && (this.currentFloorTask.getMoveTask() instanceof GeneralTask);
    }

    private final boolean isLeaveChargingTask() {
        if (!this.generalTask.isLeaveChargingTask()) {
            return false;
        }
        if ((this.moveTask instanceof AcrossFloorTask) && (this.acrossFloorTask.getMoveTask() instanceof CurrentFloorTask) && (this.currentFloorTask.getMoveTask() instanceof GeneralTask)) {
            return true;
        }
        return (this.moveTask instanceof CurrentFloorTask) && (this.currentFloorTask.getMoveTask() instanceof GeneralTask);
    }

    public final void notifyBTChargePileArrivedState(boolean isArrived) {
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.notifyBTChargePileArrivedState(isArrived);
        }
    }

    public final void onConnectedChargingPile() {
        Job job = this.moveJob;
        if (job != null && job.isActive()) {
            if (isLeaveChargingTask()) {
                Pdlog.m3277w(this.TAG, "onConnectedChargingPile is Leave charge task");
                return;
            } else {
                this.moveTask.reset();
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$onConnectedChargingPile$1(this, null), 3, null);
                return;
            }
        }
        notifyChargingPile(false);
        Pdlog.m3273d(this.TAG, "onConnectedChargingPile moveJob is not active");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyChargingPile(boolean shouldNotify) {
        ScheduleInterface scheduler;
        NavigationInterface navigator;
        NavigationInterface navigator2;
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface != null && (navigator2 = mirCoreInterface.getNavigator()) != null) {
            navigator2.pauseNavigation();
        }
        MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
        if (mirCoreInterface2 != null && (navigator = mirCoreInterface2.getNavigator()) != null) {
            navigator.resetNavigationFlag();
        }
        MirCoreInterface mirCoreInterface3 = this.coreService.getInterface();
        if (mirCoreInterface3 != null && (scheduler = mirCoreInterface3.getScheduler()) != null) {
            scheduler.noticeTaskStuck();
        }
        Pdlog.m3273d(this.TAG, "notifyChargingPile notify" + shouldNotify);
        if (shouldNotify) {
            this._onStateChange.invoke(RobotState.Arrive, "");
        }
    }

    public final void backToPile() {
        GeneralTask generalTask = this.generalTask;
        this.moveTask = generalTask;
        generalTask.setBusinessType(BusinessType.GoCharging);
        this.generalTask.assignChargingPile();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object stopAndWaitBrake$default(MoveAction moveAction, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return moveAction.stopAndWaitBrake(z, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00af A[Catch: all -> 0x00bb, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000f, B:8:0x001a, B:11:0x0027, B:12:0x0062, B:14:0x006c, B:16:0x0072, B:17:0x0075, B:19:0x0092, B:21:0x009c, B:23:0x00a2, B:24:0x00a5, B:26:0x00af, B:27:0x00b4, B:31:0x0031, B:32:0x0038, B:33:0x0039, B:35:0x0046, B:37:0x004c, B:38:0x004f, B:40:0x0053, B:45:0x0015), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0039 A[Catch: all -> 0x00bb, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000f, B:8:0x001a, B:11:0x0027, B:12:0x0062, B:14:0x006c, B:16:0x0072, B:17:0x0075, B:19:0x0092, B:21:0x009c, B:23:0x00a2, B:24:0x00a5, B:26:0x00af, B:27:0x00b4, B:31:0x0031, B:32:0x0038, B:33:0x0039, B:35:0x0046, B:37:0x004c, B:38:0x004f, B:40:0x0053, B:45:0x0015), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized /* synthetic */ Object stopAndWaitBrake(boolean z, Continuation<? super Unit> continuation) {
        MoveAction$stopAndWaitBrake$1 moveAction$stopAndWaitBrake$1;
        int i;
        MoveAction moveAction;
        NavigationInterface navigator;
        MirCoreInterface mirCoreInterface;
        MirCoreInterface mirCoreInterface2;
        MirCoreInterface mirCoreInterface3;
        ScheduleInterface scheduler;
        NavigationInterface navigator2;
        if (continuation instanceof MoveAction$stopAndWaitBrake$1) {
            moveAction$stopAndWaitBrake$1 = (MoveAction$stopAndWaitBrake$1) continuation;
            if ((moveAction$stopAndWaitBrake$1.label & Integer.MIN_VALUE) != 0) {
                moveAction$stopAndWaitBrake$1.label -= Integer.MIN_VALUE;
                Object obj = moveAction$stopAndWaitBrake$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveAction$stopAndWaitBrake$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    MirCoreInterface mirCoreInterface4 = this.coreService.getInterface();
                    if (mirCoreInterface4 != null && (navigator = mirCoreInterface4.getNavigator()) != null) {
                        navigator.pauseNavigation();
                    }
                    Job job = this.moveJob;
                    if (job != null) {
                        moveAction$stopAndWaitBrake$1.L$0 = this;
                        moveAction$stopAndWaitBrake$1.Z$0 = z;
                        moveAction$stopAndWaitBrake$1.label = 1;
                        if (JobKt.cancelAndJoin(job, moveAction$stopAndWaitBrake$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    moveAction = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    z = moveAction$stopAndWaitBrake$1.Z$0;
                    moveAction = (MoveAction) moveAction$stopAndWaitBrake$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                mirCoreInterface = moveAction.coreService.getInterface();
                if (mirCoreInterface != null && (navigator2 = mirCoreInterface.getNavigator()) != null) {
                    navigator2.resetNavigationFlag();
                }
                Pdlog.m3275i(moveAction.TAG, "stopAndWaitBrake resetSchedule flag = " + z);
                if (z && (mirCoreInterface3 = moveAction.coreService.getInterface()) != null && (scheduler = mirCoreInterface3.getScheduler()) != null) {
                    scheduler.noticeTaskStuck();
                }
                mirCoreInterface2 = moveAction.coreService.getInterface();
                if (mirCoreInterface2 != null) {
                    mirCoreInterface2.removeReflectorDistanceListener("reflectorditance");
                }
                moveAction.brakeUntilStop();
                return Unit.INSTANCE;
            }
        }
        moveAction$stopAndWaitBrake$1 = new MoveAction$stopAndWaitBrake$1(this, continuation);
        Object obj2 = moveAction$stopAndWaitBrake$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveAction$stopAndWaitBrake$1.label;
        if (i != 0) {
        }
        mirCoreInterface = moveAction.coreService.getInterface();
        if (mirCoreInterface != null) {
            navigator2.resetNavigationFlag();
        }
        Pdlog.m3275i(moveAction.TAG, "stopAndWaitBrake resetSchedule flag = " + z);
        if (z) {
            scheduler.noticeTaskStuck();
        }
        mirCoreInterface2 = moveAction.coreService.getInterface();
        if (mirCoreInterface2 != null) {
        }
        moveAction.brakeUntilStop();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onStateChange(SDKRobotState state, String desc) {
        this.robotState = this.moveTask.onStateChange(state, desc);
        if (state != SDKRobotState.Error || Build.VERSION.SDK_INT < 26) {
            return;
        }
        DmesgWorker.INSTANCE.dmesgWork();
    }

    private final void initAccCommunicateScheme() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$initAccCommunicateScheme$1(this, null), 3, null);
    }

    private final void initElvCommuicateScheme() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$initElvCommuicateScheme$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object stop$default(MoveAction moveAction, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return moveAction.stop(z, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d3 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0049 A[Catch: all -> 0x00de, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000f, B:8:0x001a, B:12:0x002b, B:13:0x00d5, B:17:0x0036, B:18:0x003d, B:19:0x003e, B:21:0x00c7, B:25:0x0049, B:27:0x0054, B:30:0x0063, B:32:0x009d, B:33:0x00a3, B:35:0x00b3, B:42:0x0015), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized /* synthetic */ Object stop(boolean z, Continuation<? super Unit> continuation) {
        MoveAction$stop$1 moveAction$stop$1;
        Object coroutine_suspended;
        int i;
        boolean z2;
        MoveAction moveAction;
        MoveAction moveAction2;
        if (continuation instanceof MoveAction$stop$1) {
            moveAction$stop$1 = (MoveAction$stop$1) continuation;
            if ((moveAction$stop$1.label & Integer.MIN_VALUE) != 0) {
                moveAction$stop$1.label -= Integer.MIN_VALUE;
                Object obj = moveAction$stop$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveAction$stop$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.mActionStopFlag.get()) {
                        Pdlog.m3273d(this.TAG, "has called stop ");
                        return Unit.INSTANCE;
                    }
                    lockWheel(false);
                    this.mActionStopFlag.set(true);
                    String str = this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb.append(currentThread.getName());
                    sb.append(" ]stop, ");
                    Thread currentThread2 = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread2, "Thread.currentThread()");
                    StackTraceElement[] stackTrace = currentThread2.getStackTrace();
                    sb.append(stackTrace != null ? ArraysKt.contentDeepToString(stackTrace) : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3275i(str, objArr);
                    Job job = this.errorJob;
                    if (job == null) {
                        z2 = z;
                        moveAction = this;
                        moveAction$stop$1.L$0 = moveAction;
                        moveAction$stop$1.Z$0 = z2;
                        moveAction$stop$1.label = 2;
                        if (moveAction.stopAndWaitBrake(z2, moveAction$stop$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        moveAction.mActionStopFlag.set(false);
                        return Unit.INSTANCE;
                    }
                    moveAction$stop$1.L$0 = this;
                    moveAction$stop$1.Z$0 = z;
                    moveAction$stop$1.label = 1;
                    if (JobKt.cancelAndJoin(job, moveAction$stop$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    moveAction2 = this;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        boolean z3 = moveAction$stop$1.Z$0;
                        moveAction = (MoveAction) moveAction$stop$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        moveAction.mActionStopFlag.set(false);
                        return Unit.INSTANCE;
                    }
                    z = moveAction$stop$1.Z$0;
                    moveAction2 = (MoveAction) moveAction$stop$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                z2 = z;
                moveAction = moveAction2;
                moveAction$stop$1.L$0 = moveAction;
                moveAction$stop$1.Z$0 = z2;
                moveAction$stop$1.label = 2;
                if (moveAction.stopAndWaitBrake(z2, moveAction$stop$1) == coroutine_suspended) {
                }
                moveAction.mActionStopFlag.set(false);
                return Unit.INSTANCE;
            }
        }
        moveAction$stop$1 = new MoveAction$stop$1(this, continuation);
        Object obj2 = moveAction$stop$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveAction$stop$1.label;
        if (i != 0) {
        }
        z2 = z;
        moveAction = moveAction2;
        moveAction$stop$1.L$0 = moveAction;
        moveAction$stop$1.Z$0 = z2;
        moveAction$stop$1.label = 2;
        if (moveAction.stopAndWaitBrake(z2, moveAction$stop$1) == coroutine_suspended) {
        }
        moveAction.mActionStopFlag.set(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void brakeUntilStop() {
        Job launch$default;
        if (this.mJobBreakFlag.get()) {
            Pdlog.m3273d(this.TAG, "has called brakeUntilStop ");
            return;
        }
        this.mJobBreakFlag.set(true);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$brakeUntilStop$1(this, null), 3, null);
        this.brakeJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rotateTask(double direction) {
        Job launch$default;
        double z = direction + this.robotStatus.getPose().getValue().getZ();
        Vector3d vector3d = new Vector3d(Math.cos(z), Math.sin(z), 0.0d);
        Pdlog.m3273d(this.TAG, "start rotate with goal direction " + CommonKt.format(z, 2) + "rad current direction " + CommonKt.format(this.robotStatus.getPose().getValue().getZ(), 2) + "rad");
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getMoveActionWorker(), null, new MoveAction$rotateTask$1(this, z, vector3d, direction, null), 2, null);
        this.moveJob = launch$default;
    }

    private final void loadMapDestinations(String floor, MapDecode map) {
        Integer use_parking_find;
        ArrayList<Source> source = map.getSource();
        ScheduleConfig parseScheduleConfig = this.atlas.parseScheduleConfig();
        int intValue = (parseScheduleConfig == null || (use_parking_find = parseScheduleConfig.getUse_parking_find()) == null) ? 1 : use_parking_find.intValue();
        ArrayList arrayList = new ArrayList();
        if (intValue == 0) {
            Iterator<Source> it = source.iterator();
            while (it.hasNext()) {
                Source next = it.next();
                if (Intrinsics.areEqual(next.getMode(), "dining_outlet") && Intrinsics.areEqual(next.getGroup(), "default")) {
                    arrayList.add(next);
                }
            }
            if (arrayList.size() > 0) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    Source source2 = (Source) it2.next();
                    source2.setGroup(source2.getId());
                }
            }
        }
        Iterator<Source> it3 = source.iterator();
        while (it3.hasNext()) {
            Source next2 = it3.next();
            Pdlog.m3273d(this.TAG, "chk source id " + next2.getId() + ' ' + next2.getHigh_precision());
            this.destinations.add(new Destination(next2.getId(), floor, next2.getPosition()[0], next2.getPosition()[1], next2.getDir(), next2.getDoubleDir(), next2.getMode(), next2.getGroup(), next2.getHigh_precision()));
        }
        Pdlog.m3273d(this.TAG, "destinations size:" + this.destinations.size());
    }

    private final List<Destination> loadSpecifyMapDestinations(String floor, MapDecode map) {
        ArrayList arrayList = new ArrayList();
        Iterator<Source> it = map.getSource().iterator();
        while (it.hasNext()) {
            Source next = it.next();
            Pdlog.m3273d(this.TAG, "specify chk source id " + next.getId() + ' ' + next.getHigh_precision());
            arrayList.add(new Destination(next.getId(), floor, next.getPosition()[0], next.getPosition()[1], next.getDir(), next.getDoubleDir(), next.getMode(), next.getGroup(), next.getHigh_precision()));
        }
        Pdlog.m3273d(this.TAG, "specifyDestinations size:" + arrayList.size());
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x00ec. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void analyZonesData(MapDecode map) {
        int i;
        MirCoreInterface mirCoreInterface;
        AreaDetectInterface areaDetect;
        int i2;
        AreaDetectInterface areaDetect2;
        Intrinsics.checkParameterIsNotNull(map, "map");
        if (this.coreService.getInterface() != null) {
            MapConfig mapConfig$default = Atlas.getMapConfig$default(this.atlas, null, 1, null);
            Integer valueOf = mapConfig$default != null ? Integer.valueOf(mapConfig$default.getImage_width()) : null;
            Integer valueOf2 = mapConfig$default != null ? Integer.valueOf(mapConfig$default.getImage_height()) : null;
            this.atlas.parseOptemapYaml();
            String str = (this.atlas.getMapExtractPath() + "/") + StringBase64Kt.encodeMapName(this.atlas.getMapFileName());
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 != null && (areaDetect2 = mirCoreInterface2.getAreaDetect()) != null) {
                areaDetect2.setMap(str);
            }
            Pdlog.m3273d(this.TAG, "analyZonesData : image_width = " + valueOf + ", image_height = " + valueOf2 + " and mapname is " + str + "  ");
            for (ZonesData zonesData : map.getZonesDataList()) {
                ArrayList arrayList = new ArrayList();
                double[][] nodes = zonesData.getNodes();
                if (nodes != null) {
                    for (double[] dArr : nodes) {
                        arrayList.add(new Vector2d(dArr[0], dArr[1]));
                    }
                }
                String type = zonesData.getType();
                if (type != null) {
                    switch (type.hashCode()) {
                        case -1687073836:
                            if (type.equals("SpeedLimit")) {
                                i = 1;
                                break;
                            }
                            break;
                        case -1653941107:
                            if (type.equals("RGBDFuncLimitArea")) {
                                i2 = 3;
                                i = i2;
                                break;
                            }
                            break;
                        case -1620477877:
                            if (type.equals("ExemptZone")) {
                                i = 6;
                                break;
                            }
                            break;
                        case -1468852930:
                            if (type.equals("elevator_area")) {
                                i2 = 4;
                                i = i2;
                                break;
                            }
                            break;
                        case -521088727:
                            if (type.equals("danger_area")) {
                                i2 = 7;
                                i = i2;
                                break;
                            }
                            break;
                        case 272537498:
                            if (type.equals("NoDetour")) {
                                i2 = 2;
                                i = i2;
                                break;
                            }
                            break;
                    }
                    if (i == 6) {
                        zonesData.setValue(zonesData.getShieldMode());
                        zonesData.setValue(0);
                        Pdlog.m3273d(this.TAG, "it.value " + zonesData.getValue() + " ExemptZone");
                    }
                    Pdlog.m3273d(this.TAG, "update zone type " + zonesData.getType() + ' ' + zonesData.getNodes());
                    mirCoreInterface = this.coreService.getInterface();
                    if (mirCoreInterface != null && (areaDetect = mirCoreInterface.getAreaDetect()) != null) {
                        String id = zonesData.getId();
                        Double maxSpeed = zonesData.getMaxSpeed();
                        double doubleValue = maxSpeed == null ? maxSpeed.doubleValue() : 0.0d;
                        Integer value = zonesData.getValue();
                        areaDetect.receiveAreaData(i, arrayList, id, doubleValue, value == null ? value.intValue() : -1);
                    }
                }
                i = 0;
                if (i == 6) {
                }
                Pdlog.m3273d(this.TAG, "update zone type " + zonesData.getType() + ' ' + zonesData.getNodes());
                mirCoreInterface = this.coreService.getInterface();
                if (mirCoreInterface != null) {
                    String id2 = zonesData.getId();
                    Double maxSpeed2 = zonesData.getMaxSpeed();
                    if (maxSpeed2 == null) {
                    }
                    Integer value2 = zonesData.getValue();
                    areaDetect.receiveAreaData(i, arrayList, id2, doubleValue, value2 == null ? value2.intValue() : -1);
                }
            }
        }
    }

    public final List<String> getRelocationPoints() {
        Pdlog.m3273d(this.TAG, "debug relocate starter: locate case " + this.atlas.getLocateCase().name());
        if (WhenMappings.$EnumSwitchMapping$5[this.atlas.getLocateCase().ordinal()] != 1) {
            return null;
        }
        ArrayList<RelocatePoint> relocatePoints = this.atlas.getMapDecode().getRelocatePoints();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = relocatePoints.iterator();
        while (it.hasNext()) {
            arrayList.add(((RelocatePoint) it.next()).getId());
        }
        return arrayList;
    }

    public final Pair<Boolean, String> addRelocationPoint(String id) {
        LocalizationInterface localizer;
        Intrinsics.checkParameterIsNotNull(id, "id");
        if (WhenMappings.$EnumSwitchMapping$6[this.atlas.getLocateCase().ordinal()] == 1) {
            ArrayList<RelocatePoint> relocatePoints = this.atlas.getMapDecode().getRelocatePoints();
            if (relocatePoints.size() >= 16) {
                Pdlog.m3273d(this.TAG, "Failed to add, no more than 16 boot points");
                return new Pair<>(false, "Failed to add, no more than 16 boot points");
            }
            ArrayList arrayList = new ArrayList();
            Iterator<RelocatePoint> it = relocatePoints.iterator();
            while (it.hasNext()) {
                RelocatePoint next = it.next();
                arrayList.add(new Vector3d(next.getPosition()[0], next.getPosition()[1], next.getPosition()[2]));
            }
            Vector3d vector3d = new Vector3d(this.robotStatus.getPose().getValue().getX(), this.robotStatus.getPose().getValue().getY(), this.robotStatus.getPose().getValue().getZ());
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null && (localizer = mirCoreInterface.getLocalizer()) != null && localizer.relocalizationPointIsValid(vector3d, arrayList)) {
                Pdlog.m3273d(this.TAG, "relocalizationPointIsValid success,id:" + id + " pose:{" + CommonKt.format(vector3d.getX(), 3) + ',' + CommonKt.format(vector3d.getY(), 3) + ',' + CommonKt.format(vector3d.getZ(), 3) + '}');
                this.atlas.getMapDecode().addRelocatePose(id, new double[]{vector3d.getX(), vector3d.getY(), vector3d.getZ()});
                if (this.atlas.save()) {
                    return new Pair<>(true, "");
                }
                return new Pair<>(false, "save atlas error");
            }
            Pdlog.m3273d(this.TAG, "relocalizationPointIsValid fail ,id:" + id + " pose:{" + CommonKt.format(vector3d.getX(), 3) + ',' + CommonKt.format(vector3d.getY(), 3) + ',' + CommonKt.format(vector3d.getZ(), 3) + '}');
            return new Pair<>(false, "relocalizationPointIsValid fail ,id:" + id + " pose:{" + CommonKt.format(vector3d.getX(), 3) + ',' + CommonKt.format(vector3d.getY(), 3) + ',' + CommonKt.format(vector3d.getZ(), 3) + '}');
        }
        return new Pair<>(false, "");
    }

    public static /* synthetic */ Pair addChargePoint$default(MoveAction moveAction, String str, DockerDetectResult dockerDetectResult, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return moveAction.addChargePoint(str, dockerDetectResult, str2);
    }

    public final Pair<Boolean, String> addChargePoint(String id, DockerDetectResult dockerDetectResult, String mac) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(dockerDetectResult, "dockerDetectResult");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Pdlog.m3273d(this.TAG, "addChargePoint ==> id:" + id + " mac:" + mac);
        ChargingPile chargingPile = new ChargingPile(this.atlas.getDefaultFloor(), id, new Vector3d(dockerDetectResult.getX(), dockerDetectResult.getY(), dockerDetectResult.getTheta()), 1.0d, id, mac);
        this.atlas.getMapDecode().addCharge(chargingPile);
        if (this.atlas.saveCharge()) {
            this.chargingPiles.add(chargingPile);
            return new Pair<>(true, "");
        }
        return new Pair<>(false, "save Charge error");
    }

    public final void relocateAtPoints() {
        LocalizationInterface localizer;
        LocalizationInterface localizer2;
        MirCoreInterface mirCoreInterface;
        LocalizationInterface localizer3;
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$relocateAtPoints$1(this, null), 1, null);
        int i = WhenMappings.$EnumSwitchMapping$7[this.atlas.getLocateCase().ordinal()];
        if (i != 1) {
            if (i != 2 || (mirCoreInterface = this.coreService.getInterface()) == null || (localizer3 = mirCoreInterface.getLocalizer()) == null) {
                return;
            }
            localizer3.relocalizationByPoints(null, null);
            return;
        }
        ArrayList<RelocatePoint> relocatePoints = this.atlas.getMapDecode().getRelocatePoints();
        if (relocatePoints.size() == 0) {
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 == null || (localizer2 = mirCoreInterface2.getLocalizer()) == null) {
                return;
            }
            localizer2.relocalizationByPoints(null, null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<RelocatePoint> it = relocatePoints.iterator();
        while (it.hasNext()) {
            RelocatePoint next = it.next();
            arrayList.add(next.getId());
            arrayList2.add(new Vector3d(next.getPosition()[0], next.getPosition()[1], next.getPosition()[2]));
        }
        MirCoreInterface mirCoreInterface3 = this.coreService.getInterface();
        if (mirCoreInterface3 == null || (localizer = mirCoreInterface3.getLocalizer()) == null) {
            return;
        }
        localizer.relocalizationByPoints(arrayList, arrayList2);
    }

    public final void reloadLocalizationByChargingPile(ChargingPileInfo pile) {
        LocalizationInterface localizer;
        MirCoreInterface mirCoreInterface;
        LocalizationInterface localizer2;
        BuildersKt__BuildersKt.runBlocking$default(null, new MoveAction$reloadLocalizationByChargingPile$1(this, null), 1, null);
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("reloadLocalizationByChargingPile floor: ");
        sb.append(pile != null ? pile.getFloor() : null);
        sb.append(" id: ");
        sb.append(pile != null ? pile.getId() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        int i = WhenMappings.$EnumSwitchMapping$8[this.atlas.getLocateCase().ordinal()];
        if (i != 1) {
            if (i != 2 || (mirCoreInterface = this.coreService.getInterface()) == null || (localizer2 = mirCoreInterface.getLocalizer()) == null) {
                return;
            }
            localizer2.relocalizationByPoints(null, null);
            return;
        }
        if ((pile != null ? pile.getPose() : null) == null) {
            Pdlog.m3273d(this.TAG, "reloadLocalizationByChargingPile pile pose is null");
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 == null || (localizer = mirCoreInterface2.getLocalizer()) == null) {
                return;
            }
            localizer.relocalizationByPoints(null, null);
            return;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        if (!Intrinsics.areEqual(pile.getFloor(), this.atlas.getDefaultFloor())) {
            Pdlog.m3273d(this.TAG, "pile floor" + pile.getFloor() + " not in defaultFloor " + this.atlas.getDefaultFloor());
            booleanRef.element = true;
            reloadMap(pile.getFloor(), new Function0<Unit>() { // from class: com.pudutech.mirsdk.MoveAction$reloadLocalizationByChargingPile$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str2;
                    str2 = MoveAction.this.TAG;
                    Pdlog.m3273d(str2, "reload Map over");
                    booleanRef.element = false;
                }
            });
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$reloadLocalizationByChargingPile$3(this, booleanRef, pile, null), 3, null);
    }

    private final void loadMapNunDestinationElements(String floor, MapDecode map) {
        Iterator<CircleMode> it = map.getCircleModes().iterator();
        while (it.hasNext()) {
            cruiseIDs.add(String.valueOf(it.next().f6137id));
        }
        Iterator<ElevatorSource> it2 = map.getElevators().iterator();
        while (it2.hasNext()) {
            ElevatorSource next = it2.next();
            Pdlog.m3273d(this.TAG, "floor " + floor + ": elevator: " + next.getId() + ", " + next.getWaiter());
            this.elevators.add(new ElevatorSource(floor, next.getId(), next.getPose(), next.getWaiter()));
        }
        Iterator<ElevatorWaiter> it3 = map.getElevWaiters().iterator();
        while (it3.hasNext()) {
            ElevatorWaiter next2 = it3.next();
            Pdlog.m3273d(this.TAG, "floor " + floor + ": waiter: " + next2.getId());
            this.eleWaiters.add(new ElevatorWaiter(floor, next2.getId(), next2.getPose()));
        }
        this.accessControlPoints.addAll(map.getAccessPoints());
        Iterator<ChargingPile> it4 = map.getChargingPiles().iterator();
        while (it4.hasNext()) {
            ChargingPile next3 = it4.next();
            Pdlog.m3273d(this.TAG, "add charge pile " + next3.getId());
            this.chargingPiles.add(new ChargingPile(floor, next3.getId(), next3.getPose(), next3.getAlignRange(), next3.getGroup(), next3.getMac()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<WatchLevel, String> watchDogCheck() {
        boolean z;
        String str;
        String str2;
        DeviceType deviceType;
        Iterator<String> it;
        String str3;
        String str4;
        DeviceType deviceType2;
        Pair<List<String>, List<String>> check = this.watchDog.check();
        String str5 = "[";
        if (!check.getSecond().isEmpty()) {
            brakeUntilStop();
            this.lastSensorError.clear();
            Pdlog.m3274e(this.TAG, "navigation fail, Watch dog timeout:" + CollectionsKt.joinToString$default(check.getSecond(), null, null, null, 0, null, null, 63, null));
            Iterator<String> it2 = check.getSecond().iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                this.lastSensorError.add(next);
                HardwareInterface hardwareInterface = this.robotHardware.getInterface();
                if (hardwareInterface != null) {
                    DeviceType[] deviceTypeArr = new DeviceType[1];
                    int hashCode = next.hashCode();
                    it = it2;
                    if (hashCode == -2018805884) {
                        if (next.equals("Lindar")) {
                            deviceType2 = DeviceType.Lidar;
                            deviceTypeArr[0] = deviceType2;
                            str3 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str3 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    } else if (hashCode != 2513207) {
                        if (hashCode == 2011082565 && next.equals("Camera")) {
                            deviceType2 = DeviceType.Camera;
                            deviceTypeArr[0] = deviceType2;
                            str3 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str3 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    } else {
                        if (next.equals("RGBD")) {
                            deviceType2 = DeviceType.RGBD;
                            deviceTypeArr[0] = deviceType2;
                            str3 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str3 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    }
                } else {
                    it = it2;
                    str3 = null;
                }
                if (str3 == null) {
                    str4 = null;
                } else {
                    if (str3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    str4 = StringsKt.trim((CharSequence) str3).toString();
                }
                String replace$default = str4 != null ? StringsKt.replace$default(str4, "\\", "\\\\", false, 4, (Object) null) : null;
                String replace$default2 = replace$default != null ? StringsKt.replace$default(replace$default, "'", "\\'", false, 4, (Object) null) : null;
                str5 = str5 + "{\"error_type\":\"Lost" + next + "\", \"level\":\"Error\",\"detail\":\"" + (replace$default2 != null ? StringsKt.replace$default(replace$default2, "\"", "\\\"", false, 4, (Object) null) : null) + "\"},";
                it2 = it;
            }
            if (!check.getSecond().isEmpty()) {
                CharSequence subSequence = str5.subSequence(0, str5.length() - 1);
                if (subSequence == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                str5 = (String) subSequence;
            }
            String str6 = str5 + "]";
            if (check.getSecond().contains("CAN")) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$watchDogCheck$1(this, null), 3, null);
            }
            return new Pair<>(WatchLevel.Over, str6);
        }
        if (!check.getFirst().isEmpty()) {
            if (check.getFirst().contains("Encoder") || check.getFirst().contains("IMU") || check.getFirst().contains("CAN")) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MoveAction$watchDogCheck$2(this, null), 3, null);
            }
            Iterator<String> it3 = check.getFirst().iterator();
            String str7 = "[";
            boolean z2 = false;
            while (it3.hasNext()) {
                String next2 = it3.next();
                HardwareInterface hardwareInterface2 = this.robotHardware.getInterface();
                Iterator<String> it4 = it3;
                if (hardwareInterface2 != null) {
                    DeviceType[] deviceTypeArr2 = new DeviceType[1];
                    int hashCode2 = next2.hashCode();
                    z = z2;
                    if (hashCode2 == -2018805884) {
                        if (next2.equals("Lindar")) {
                            deviceType = DeviceType.Lidar;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    } else if (hashCode2 != 2513207) {
                        if (hashCode2 == 2011082565 && next2.equals("Camera")) {
                            deviceType = DeviceType.Camera;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    } else {
                        if (next2.equals("RGBD")) {
                            deviceType = DeviceType.RGBD;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    }
                } else {
                    z = z2;
                    str = null;
                }
                if (str == null) {
                    str2 = null;
                } else {
                    if (str == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    str2 = StringsKt.trim((CharSequence) str).toString();
                }
                String replace$default3 = str2 != null ? StringsKt.replace$default(str2, "\\", "\\\\", false, 4, (Object) null) : null;
                String replace$default4 = replace$default3 != null ? StringsKt.replace$default(replace$default3, "'", "\\'", false, 4, (Object) null) : null;
                String replace$default5 = replace$default4 != null ? StringsKt.replace$default(replace$default4, "\"", "\\\"", false, 4, (Object) null) : null;
                str5 = str5 + "{\"error_type\":\"Lost" + next2 + "\", \"level\":\"Warning\",\"detail\":\"" + replace$default5 + "\"},";
                if (this.lastSensorError.contains(next2)) {
                    str7 = str7 + "{\"error_type\":\"Lost" + next2 + "\", \"level\":\"Error\",\"detail\":\"" + replace$default5 + "\"},";
                    z = true;
                }
                it3 = it4;
                z2 = z;
            }
            boolean z3 = z2;
            if (!check.getFirst().isEmpty()) {
                CharSequence subSequence2 = str5.subSequence(0, str5.length() - 1);
                if (subSequence2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                str5 = (String) subSequence2;
            }
            String str8 = str5 + "]";
            if (z3) {
                CharSequence subSequence3 = str7.subSequence(0, str7.length() - 1);
                if (subSequence3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                return new Pair<>(WatchLevel.Over, ((String) subSequence3) + "]");
            }
            return new Pair<>(WatchLevel.Pause, str8);
        }
        this.lastSensorError.clear();
        return new Pair<>(WatchLevel.Normal, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retriveMCU() {
        LidarInterface lidarInterface;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.sendCAN(new byte[]{22, 2, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface2 = this.robotHardware.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.sendCAN(new byte[]{22, 4, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface3 = this.robotHardware.getInterface();
        if (hardwareInterface3 != null) {
            hardwareInterface3.sendCAN(new byte[]{22, 6, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface4 = this.robotHardware.getInterface();
        if (hardwareInterface4 != null) {
            hardwareInterface4.sendCAN(new byte[]{22, (byte) 255, (byte) 225, 4, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface5 = this.robotHardware.getInterface();
        if (hardwareInterface5 == null || (lidarInterface = hardwareInterface5.getLidarInterface()) == null) {
            return;
        }
        lidarInterface.open();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object navigation(Continuation<? super Unit> continuation) {
        Job launch$default;
        Job job;
        Pdlog.m3273d(this.TAG, "navigation enter");
        Job job2 = this.moveJob;
        if (job2 != null && job2.isActive() && (job = this.moveJob) != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getMoveActionWorker(), null, new MoveAction$navigation$2(this, null), 2, null);
        this.moveJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object checkAndClearWheelError(Continuation<? super Boolean> continuation) {
        MoveAction$checkAndClearWheelError$1 moveAction$checkAndClearWheelError$1;
        int i;
        MoveAction moveAction;
        Boolean bool;
        HardwareInterface hardwareInterface;
        if (continuation instanceof MoveAction$checkAndClearWheelError$1) {
            moveAction$checkAndClearWheelError$1 = (MoveAction$checkAndClearWheelError$1) continuation;
            if ((moveAction$checkAndClearWheelError$1.label & Integer.MIN_VALUE) != 0) {
                moveAction$checkAndClearWheelError$1.label -= Integer.MIN_VALUE;
                Object obj = moveAction$checkAndClearWheelError$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = moveAction$checkAndClearWheelError$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.robotHardware.getWheelMulfunctionState()) {
                        MoveAction$checkAndClearWheelError$result$1 moveAction$checkAndClearWheelError$result$1 = new MoveAction$checkAndClearWheelError$result$1(this, null);
                        moveAction$checkAndClearWheelError$1.L$0 = this;
                        moveAction$checkAndClearWheelError$1.label = 1;
                        obj = TimeoutKt.withTimeoutOrNull(500L, moveAction$checkAndClearWheelError$result$1, moveAction$checkAndClearWheelError$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        moveAction = this;
                    } else {
                        return Boxing.boxBoolean(true);
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    moveAction = (MoveAction) moveAction$checkAndClearWheelError$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                bool = (Boolean) obj;
                hardwareInterface = moveAction.robotHardware.getInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.controlWheel(0.0d, 0.0d, false);
                }
                boolean z = false;
                if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
                    Pdlog.m3277w(moveAction.TAG, "clear wheel error fail");
                    moveAction.onStateChange(SDKRobotState.Error, moveAction.robotHardware.getWheelInWarning() ? moveAction.robotHardware.getLastWarnMulfunction() : moveAction.robotHardware.getLastErrorMulfunction());
                } else {
                    moveAction.robotHardware.setWheelInError(false);
                    moveAction.robotHardware.setWheelInWarning(false);
                    Pdlog.m3273d(moveAction.TAG, "wheel error being cleared");
                    z = true;
                }
                return Boxing.boxBoolean(z);
            }
        }
        moveAction$checkAndClearWheelError$1 = new MoveAction$checkAndClearWheelError$1(this, continuation);
        Object obj2 = moveAction$checkAndClearWheelError$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = moveAction$checkAndClearWheelError$1.label;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        hardwareInterface = moveAction.robotHardware.getInterface();
        if (hardwareInterface != null) {
        }
        boolean z2 = false;
        if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
        }
        return Boxing.boxBoolean(z2);
    }

    private final boolean checkNoRedundancyDestination() {
        HashSet hashSet = new HashSet();
        for (Destination destination : this.destinations) {
            if (hashSet.contains(destination.getName())) {
                Pdlog.m3277w(this.TAG, "destination " + destination.getName() + " has existed in destination list");
                this.atlas.setErrorReason("destination " + destination.getName() + " is redundancy");
                return false;
            }
            hashSet.add(destination.getName());
        }
        return true;
    }

    public final void onSpeed(double line, double angular) {
        this.generalTask.onSpeed(line, angular);
    }
}
