package com.pudutech.module_robot_selfcheck.oss;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.ReqCheckAuth;
import com.pudutech.disinfect.baselib.network.req.ReqGetMapList;
import com.pudutech.disinfect.baselib.network.req.RobotMapReq;
import com.pudutech.disinfect.baselib.network.req.RobotPermissionReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.MapPermissionResponse;
import com.pudutech.disinfect.baselib.network.response.MapUploadResponse;
import com.pudutech.disinfect.baselib.network.response.ResAuth;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.disinfect.baselib.util.EncryptUtils;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_robot_selfcheck.ExtandsKt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u00107\u001a\u00020 J6\u00108\u001a\u00020 2#\u00109\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010;¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020 0:H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010<J\u000e\u0010=\u001a\u00020>2\u0006\u0010\u001b\u001a\u00020\u0004J>\u0010?\u001a\u00020 2\u0006\u0010@\u001a\u00020\u00042#\u00109\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020 0:H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ\u000e\u0010B\u001a\u00020>2\u0006\u0010C\u001a\u00020\rJA\u0010D\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010F\u001a\n\u0012\u0004\u0012\u00020G\u0018\u00010\f2\u0006\u0010H\u001a\u00020>H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010IJ\u0018\u0010J\u001a\u00020 2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020LH\u0002J\u000e\u0010N\u001a\u00020 2\u0006\u0010C\u001a\u00020\rJ\u0006\u0010O\u001a\u00020 J&\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u00020R2\u0006\u0010C\u001a\u00020\r2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0016\u0010T\u001a\u00020 2\u000e\u0010S\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fJ\u0006\u0010U\u001a\u00020 J\u000e\u0010V\u001a\u00020 2\u0006\u0010S\u001a\u00020\rJ\u000e\u0010W\u001a\n\u0012\u0004\u0012\u00020G\u0018\u00010\fJ\u000e\u0010X\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010Y\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u0019\u0010Z\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010[J<\u0010Z\u001a\u00020 2)\u0010\\\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020 0:H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010<J\u000e\u0010]\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J1\u0010^\u001a\u00020>2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010F\u001a\n\u0012\u0004\u0012\u00020G\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010_J1\u0010`\u001a\u00020>2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010F\u001a\n\u0012\u0004\u0012\u00020G\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010_J3\u0010a\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u00042#\u00109\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020 0:J)\u0010b\u001a\u00020R2\u0006\u0010c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010d\u001a\u00020RH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010eJF\u0010f\u001a\u00020 2\u0006\u0010g\u001a\u00020G2#\u00109\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010G¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(g\u0012\u0004\u0012\u00020 0:2\u0006\u0010\u001b\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010hJ)\u0010i\u001a\u00020 2!\u00109\u001a\u001d\u0012\u0013\u0012\u00110>¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(j\u0012\u0004\u0012\u00020 0:J\u0006\u0010k\u001a\u00020 J3\u0010l\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u00042#\u00109\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020 0:J\u0016\u0010m\u001a\u00020 2\u000e\u0010S\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fJ\u0011\u0010n\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010[J#\u0010o\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010d\u001a\u00020RH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010pJ+\u0010o\u001a\u00020 2\u0006\u0010q\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010d\u001a\u00020RH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010eJ!\u0010r\u001a\u00020 2\u000e\u0010S\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010sJ\u0019\u0010t\u001a\u00020 2\u0006\u0010u\u001a\u00020vH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010wR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0010\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0014Ra\u0010\u0018\u001aI\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$RL\u0010%\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010+\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000Ra\u00103\u001aI\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\"\"\u0004\b6\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006x"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/oss/MapUpdateManager;", "", "()V", "DOWNLOAD_TEMP_FILE_PATH", "", "MAP_FILE_PATH", "getMAP_FILE_PATH", "()Ljava/lang/String;", "TAG", "TYPE_DOWNLOAD", "TYPE_UPLOAD", "downloadData", "", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "errorData", "getErrorData", "()Ljava/util/List;", "needUpdateData", "getNeedUpdateData", "setNeedUpdateData", "(Ljava/util/List;)V", "needUploadData", "getNeedUploadData", "setNeedUploadData", "onErrorListener", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "url", "Lcom/pudutech/module_robot_selfcheck/oss/UpdateErrorSealed;", "errorType", "operateType", "", "getOnErrorListener", "()Lkotlin/jvm/functions/Function3;", "setOnErrorListener", "(Lkotlin/jvm/functions/Function3;)V", "onSuccessListener", "Lkotlin/Function2;", "getOnSuccessListener", "()Lkotlin/jvm/functions/Function2;", "setOnSuccessListener", "(Lkotlin/jvm/functions/Function2;)V", "onSyncFinishListener", "Lkotlin/Function0;", "getOnSyncFinishListener", "()Lkotlin/jvm/functions/Function0;", "setOnSyncFinishListener", "(Lkotlin/jvm/functions/Function0;)V", "ossService", "Lcom/pudutech/module_robot_selfcheck/oss/OssService;", "updateProgressListener", "progress", "getUpdateProgressListener", "setUpdateProgressListener", "asyncComplete", "checkEditAuth", SpeechUtility.TAG_RESOURCE_RESULT, "Lkotlin/Function1;", "Lcom/pudutech/disinfect/baselib/network/response/ResAuth;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkMapRule", "", "checkPermissionCode", "code", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkTempMapRule", "it", "compareLocalAndCloud", "cloudData", "localData", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "isUpdate", "(Ljava/util/List;Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyFileUsingFileChannels", MapElement.Source.SOURCE, "Ljava/io/File;", "dest", "deleteFailureDownloadFile", "deleteTempFile", "download", "currentIndex", "", "data", "downloadAll", "downloadMap", "downloadOne", "getLocalMapList", "getMapBase64Name", "getMapFile", "getMapList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "list", "getTempMapName", "isShouldUpdate", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isShouldUpload", "loadTopMap", "mapCompare", "md5", "lv", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapCopy", "item", "(Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moveMapFileAndDelTem", "isFinish", "release", "reloadMap", "syncDownloadAll", "syncMap", "upload", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filePath", "uploadAll", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadService", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/RobotMapReq;", "(Lcom/pudutech/disinfect/baselib/network/req/RobotMapReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapUpdateManager {
    public static final String TYPE_DOWNLOAD = "TYPE_DOWNLOAD";
    public static final String TYPE_UPLOAD = "TYPE_UPLOAD";
    private static List<RobotMapResp> needUpdateData;
    private static List<RobotMapResp> needUploadData;
    private static Function3<? super String, ? super UpdateErrorSealed, ? super String, Unit> onErrorListener;
    private static Function2<? super String, ? super String, Unit> onSuccessListener;
    private static Function0<Unit> onSyncFinishListener;
    private static Function3<? super String, ? super String, ? super String, Unit> updateProgressListener;
    public static final MapUpdateManager INSTANCE = new MapUpdateManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String DOWNLOAD_TEMP_FILE_PATH = DOWNLOAD_TEMP_FILE_PATH;
    private static final String DOWNLOAD_TEMP_FILE_PATH = DOWNLOAD_TEMP_FILE_PATH;
    private static final String MAP_FILE_PATH = MAP_FILE_PATH;
    private static final String MAP_FILE_PATH = MAP_FILE_PATH;
    private static final OssService ossService = new OssService();
    private static final List<RobotMapResp> downloadData = new ArrayList();
    private static final List<RobotMapResp> errorData = new ArrayList();

    private MapUpdateManager() {
    }

    public final String getMAP_FILE_PATH() {
        return MAP_FILE_PATH;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(8:5|6|7|(1:(1:(4:11|12|13|14)(2:16|17))(3:18|19|20))(5:29|30|(1:32)(1:37)|33|(1:35)(1:36))|21|(2:23|(1:25))(1:26)|13|14))|40|6|7|(0)(0)|21|(0)(0)|13|14|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00db, code lost:
    
        r13 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00dc, code lost:
    
        r7 = r12;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00bb A[Catch: Exception -> 0x0058, TryCatch #1 {Exception -> 0x0058, blocks: (B:20:0x0054, B:21:0x008f, B:23:0x00bb, B:26:0x00d7), top: B:19:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d7 A[Catch: Exception -> 0x0058, TRY_LEAVE, TryCatch #1 {Exception -> 0x0058, blocks: (B:20:0x0054, B:21:0x008f, B:23:0x00bb, B:26:0x00d7), top: B:19:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /* JADX WARN: Type inference failed for: r13v9, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object getMapList(Function1<? super List<RobotMapResp>, Unit> function1, Continuation<? super Unit> continuation) {
        MapUpdateManager$getMapList$1 mapUpdateManager$getMapList$1;
        int i;
        Function1<? super List<RobotMapResp>, Unit> function12;
        MapUpdateManager mapUpdateManager;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        if (continuation instanceof MapUpdateManager$getMapList$1) {
            mapUpdateManager$getMapList$1 = (MapUpdateManager$getMapList$1) continuation;
            if ((mapUpdateManager$getMapList$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$getMapList$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$getMapList$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$getMapList$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
                    String mac = WifiUtil.INSTANCE.getMac();
                    if (mac == null) {
                        mac = "";
                    }
                    ReqGetMapList reqGetMapList = new ReqGetMapList(mac);
                    mapUpdateManager$getMapList$1.L$0 = this;
                    mapUpdateManager$getMapList$1.L$1 = function1;
                    mapUpdateManager$getMapList$1.L$2 = objectRef3;
                    mapUpdateManager$getMapList$1.L$3 = objectRef3;
                    mapUpdateManager$getMapList$1.label = 1;
                    Object mapList = map.getMapList(reqGetMapList, mapUpdateManager$getMapList$1);
                    if (mapList == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mapUpdateManager = this;
                    function12 = function1;
                    objectRef = objectRef3;
                    obj = mapList;
                    objectRef2 = objectRef;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) mapUpdateManager$getMapList$1.L$3;
                    objectRef2 = (Ref.ObjectRef) mapUpdateManager$getMapList$1.L$2;
                    function12 = (Function1) mapUpdateManager$getMapList$1.L$1;
                    mapUpdateManager = (MapUpdateManager) mapUpdateManager$getMapList$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e) {
                        e = e;
                        Pdlog.m3273d(TAG, "getMapList=Error:" + e.getMessage());
                        function12.invoke(null);
                        return Unit.INSTANCE;
                    }
                }
                objectRef.element = (ApiResponse) obj;
                Pdlog.m3273d(TAG, "getMapList=" + ((ApiResponse) objectRef2.element));
                if (((ApiResponse) objectRef2.element).getCode() != 0) {
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    MapUpdateManager$getMapList$2 mapUpdateManager$getMapList$2 = new MapUpdateManager$getMapList$2(function12, objectRef2, null);
                    mapUpdateManager$getMapList$1.L$0 = mapUpdateManager;
                    mapUpdateManager$getMapList$1.L$1 = function12;
                    mapUpdateManager$getMapList$1.L$2 = objectRef2;
                    mapUpdateManager$getMapList$1.label = 2;
                    if (BuildersKt.withContext(main, mapUpdateManager$getMapList$2, mapUpdateManager$getMapList$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    function12.invoke(null);
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$getMapList$1 = new MapUpdateManager$getMapList$1(this, continuation);
        Object obj2 = mapUpdateManager$getMapList$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$getMapList$1.label;
        if (i != 0) {
        }
        objectRef.element = (ApiResponse) obj2;
        Pdlog.m3273d(TAG, "getMapList=" + ((ApiResponse) objectRef2.element));
        if (((ApiResponse) objectRef2.element).getCode() != 0) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0071 A[Catch: Exception -> 0x0079, TRY_LEAVE, TryCatch #0 {Exception -> 0x0079, blocks: (B:11:0x002c, B:12:0x005c, B:14:0x0071, B:23:0x003b, B:26:0x004e), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object getMapList(Continuation<? super List<RobotMapResp>> continuation) {
        MapUpdateManager$getMapList$3 mapUpdateManager$getMapList$3;
        int i;
        ApiResponse apiResponse;
        try {
            if (continuation instanceof MapUpdateManager$getMapList$3) {
                mapUpdateManager$getMapList$3 = (MapUpdateManager$getMapList$3) continuation;
                if ((mapUpdateManager$getMapList$3.label & Integer.MIN_VALUE) != 0) {
                    mapUpdateManager$getMapList$3.label -= Integer.MIN_VALUE;
                    Object obj = mapUpdateManager$getMapList$3.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = mapUpdateManager$getMapList$3.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
                        String mac = WifiUtil.INSTANCE.getMac();
                        if (mac == null) {
                            mac = "";
                        }
                        ReqGetMapList reqGetMapList = new ReqGetMapList(mac);
                        mapUpdateManager$getMapList$3.L$0 = this;
                        mapUpdateManager$getMapList$3.label = 1;
                        obj = map.getMapList(reqGetMapList, mapUpdateManager$getMapList$3);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    apiResponse = (ApiResponse) obj;
                    Pdlog.m3273d(TAG, String.valueOf(apiResponse));
                    if (apiResponse.getCode() != 0) {
                        return (List) apiResponse.getResponseData();
                    }
                    return null;
                }
            }
            if (i != 0) {
            }
            apiResponse = (ApiResponse) obj;
            Pdlog.m3273d(TAG, String.valueOf(apiResponse));
            if (apiResponse.getCode() != 0) {
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, String.valueOf(e.getMessage()));
            return null;
        }
        mapUpdateManager$getMapList$3 = new MapUpdateManager$getMapList$3(this, continuation);
        Object obj2 = mapUpdateManager$getMapList$3.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$getMapList$3.label;
    }

    public final void downloadAll(List<RobotMapResp> data) {
        if (data != null) {
            downloadData.clear();
            downloadData.addAll(data);
            INSTANCE.download(0, downloadData.get(0), downloadData);
        }
    }

    public final void syncDownloadAll(List<RobotMapResp> data) {
        if (data != null) {
            Iterator<T> it = data.iterator();
            while (it.hasNext()) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MapUpdateManager$syncDownloadAll$1$1((RobotMapResp) it.next(), null), 2, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void download(final int currentIndex, RobotMapResp it, final List<RobotMapResp> data) {
        OssService ossService2 = ossService;
        String name = it.getName();
        ossService.asyncDownloadFile(ossService.getRealObjectKey(it.getUrl()), ossService2.getDownloadPath(name != null ? ExtandsKt.encodeMapName(name) : null), it.getUrl());
        ossService.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$1$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$1$1 */
            /* loaded from: classes5.dex */
            public static final class C53761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $progress;
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6748p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C53761(String str, String str2, Continuation continuation) {
                    super(2, continuation);
                    this.$url = str;
                    this.$progress = str2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53761 c53761 = new C53761(this.$url, this.$progress, completion);
                    c53761.f6748p$ = (CoroutineScope) obj;
                    return c53761;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6748p$;
                    Function3<String, String, String, Unit> updateProgressListener = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                    if (updateProgressListener != null) {
                        updateProgressListener.invoke(this.$url, this.$progress, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String progress) {
                Intrinsics.checkParameterIsNotNull(url, "url");
                Intrinsics.checkParameterIsNotNull(progress, "progress");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53761(url, progress, null), 2, null);
            }
        });
        ossService.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$2$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$2$1 */
            /* loaded from: classes5.dex */
            public static final class C53771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6749p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C53771(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$it = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53771 c53771 = new C53771(this.$it, completion);
                    c53771.f6749p$ = (CoroutineScope) obj;
                    return c53771;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6749p$;
                    Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                    if (onSuccessListener != null) {
                        onSuccessListener.invoke(this.$it, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it2) {
                String str;
                Intrinsics.checkParameterIsNotNull(it2, "it");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53771(it2, null), 2, null);
                if (currentIndex < data.size() && currentIndex != data.size() - 1) {
                    int i = currentIndex + 1;
                    MapUpdateManager.INSTANCE.download(i, (RobotMapResp) data.get(i), data);
                }
                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                str = MapUpdateManager.TAG;
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                Pdlog.m3274e(str, currentThread.getName(), Integer.valueOf(currentIndex), Integer.valueOf(data.size()));
            }
        });
        ossService.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$3$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$download$3$1 */
            /* loaded from: classes5.dex */
            public static final class C53781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6750p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C53781(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$url = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53781 c53781 = new C53781(this.$url, completion);
                    c53781.f6750p$ = (CoroutineScope) obj;
                    return c53781;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6750p$;
                    Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.invoke(this.$url, UpdateErrorSealed.UNKNOW_ERROR, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String str) {
                String str2;
                Intrinsics.checkParameterIsNotNull(url, "url");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53781(url, null), 2, null);
                if (currentIndex < data.size() && currentIndex != data.size() - 1) {
                    int i = currentIndex + 1;
                    MapUpdateManager.INSTANCE.download(i, (RobotMapResp) data.get(i), data);
                }
                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                str2 = MapUpdateManager.TAG;
                Pdlog.m3274e(str2, "onErrorListener", Integer.valueOf(currentIndex), str);
            }
        });
    }

    public final void downloadOne(RobotMapResp data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        OssService ossService2 = ossService;
        String name = data.getName();
        ossService.asyncDownloadFile(ossService.getRealObjectKey(data.getUrl()), ossService2.getDownloadPath(name != null ? ExtandsKt.encodeMapName(name) : null), data.getUrl());
        ossService.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$1$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$1$1 */
            /* loaded from: classes5.dex */
            public static final class C53791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $progress;
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6751p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C53791(String str, String str2, Continuation continuation) {
                    super(2, continuation);
                    this.$url = str;
                    this.$progress = str2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53791 c53791 = new C53791(this.$url, this.$progress, completion);
                    c53791.f6751p$ = (CoroutineScope) obj;
                    return c53791;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6751p$;
                    Function3<String, String, String, Unit> updateProgressListener = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                    if (updateProgressListener != null) {
                        updateProgressListener.invoke(this.$url, this.$progress, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String progress) {
                Intrinsics.checkParameterIsNotNull(url, "url");
                Intrinsics.checkParameterIsNotNull(progress, "progress");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53791(url, progress, null), 2, null);
            }
        });
        ossService.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$2$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$2$1 */
            /* loaded from: classes5.dex */
            public static final class C53801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6752p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C53801(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$it = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53801 c53801 = new C53801(this.$it, completion);
                    c53801.f6752p$ = (CoroutineScope) obj;
                    return c53801;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6752p$;
                    Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                    if (onSuccessListener != null) {
                        onSuccessListener.invoke(this.$it, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53801(it, null), 2, null);
            }
        });
        ossService.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$3$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$downloadOne$3$1 */
            /* loaded from: classes5.dex */
            public static final class C53811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6753p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C53811(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$url = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C53811 c53811 = new C53811(this.$url, completion);
                    c53811.f6753p$ = (CoroutineScope) obj;
                    return c53811;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C53811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6753p$;
                    Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.invoke(this.$url, UpdateErrorSealed.UNKNOW_ERROR, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String str) {
                Intrinsics.checkParameterIsNotNull(url, "url");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53811(url, null), 2, null);
            }
        });
    }

    public static /* synthetic */ Object upload$default(MapUpdateManager mapUpdateManager, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        return mapUpdateManager.upload(str, i, continuation);
    }

    public final Object upload(String str, int i, Continuation<? super Unit> continuation) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new MapUpdateManager$upload$2(str, null), continuation);
            return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
        }
        String str3 = MAP_FILE_PATH + ExtandsKt.encodeMapName(str) + ".pdmap";
        if (!FileUtils.isFileExists(str3)) {
            Object withContext2 = BuildersKt.withContext(Dispatchers.getMain(), new MapUpdateManager$upload$3(str, null), continuation);
            return withContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext2 : Unit.INSTANCE;
        }
        if (!checkMapRule(str)) {
            Object withContext3 = BuildersKt.withContext(Dispatchers.getMain(), new MapUpdateManager$upload$4(str, null), continuation);
            return withContext3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext3 : Unit.INSTANCE;
        }
        Pdlog.m3274e(TAG, str3, Boxing.boxInt(i));
        Object upload = upload(str3, str, i, continuation);
        return upload == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? upload : Unit.INSTANCE;
    }

    public static /* synthetic */ Object upload$default(MapUpdateManager mapUpdateManager, String str, String str2, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 1;
        }
        return mapUpdateManager.upload(str, str2, i, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0107 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object upload(String str, String str2, int i, Continuation<? super Unit> continuation) {
        MapUpdateManager$upload$5 mapUpdateManager$upload$5;
        Object coroutine_suspended;
        int i2;
        MapUpdateManager mapUpdateManager;
        String str3;
        String md5;
        int intValue;
        Object uploadFile;
        String str4;
        String str5 = str;
        int i3 = i;
        if (continuation instanceof MapUpdateManager$upload$5) {
            mapUpdateManager$upload$5 = (MapUpdateManager$upload$5) continuation;
            if ((mapUpdateManager$upload$5.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$upload$5.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$upload$5.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = mapUpdateManager$upload$5.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!new File(str5).exists()) {
                        Pdlog.m3274e(TAG, str5 + " 不存在");
                    }
                    String md52 = EncryptUtils.encryptMD5File2String(str);
                    Intrinsics.checkExpressionValueIsNotNull(md52, "md5");
                    mapUpdateManager$upload$5.L$0 = this;
                    mapUpdateManager$upload$5.L$1 = str5;
                    mapUpdateManager$upload$5.L$2 = str2;
                    mapUpdateManager$upload$5.I$0 = i3;
                    mapUpdateManager$upload$5.L$3 = md52;
                    mapUpdateManager$upload$5.label = 1;
                    Object mapCompare = mapCompare(md52, str2, i3, mapUpdateManager$upload$5);
                    if (mapCompare == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mapUpdateManager = this;
                    str3 = str2;
                    md5 = md52;
                    obj = mapCompare;
                } else {
                    if (i2 != 1) {
                        if (i2 == 2) {
                            int i4 = mapUpdateManager$upload$5.I$1;
                            md5 = (String) mapUpdateManager$upload$5.L$3;
                            i3 = mapUpdateManager$upload$5.I$0;
                            str3 = (String) mapUpdateManager$upload$5.L$2;
                            String str6 = (String) mapUpdateManager$upload$5.L$1;
                            mapUpdateManager = (MapUpdateManager) mapUpdateManager$upload$5.L$0;
                            ResultKt.throwOnFailure(obj);
                            intValue = i4;
                            str5 = str6;
                            uploadFile = obj;
                            String str7 = (String) uploadFile;
                            Pdlog.m3274e(TAG, str7);
                            str4 = str7;
                            if (!(str4 != null || str4.length() == 0)) {
                                RobotMapReq robotMapReq = new RobotMapReq();
                                robotMapReq.setUrl(str7);
                                Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
                                robotMapReq.setMd5(md5);
                                robotMapReq.setName(str3);
                                robotMapReq.setLv(intValue);
                                String mac = WifiUtil.INSTANCE.getMac();
                                if (mac == null) {
                                    mac = "";
                                }
                                robotMapReq.setMac(mac);
                                mapUpdateManager$upload$5.L$0 = mapUpdateManager;
                                mapUpdateManager$upload$5.L$1 = str5;
                                mapUpdateManager$upload$5.L$2 = str3;
                                mapUpdateManager$upload$5.I$0 = i3;
                                mapUpdateManager$upload$5.L$3 = md5;
                                mapUpdateManager$upload$5.I$1 = intValue;
                                mapUpdateManager$upload$5.L$4 = str7;
                                mapUpdateManager$upload$5.L$5 = robotMapReq;
                                mapUpdateManager$upload$5.label = 3;
                                if (mapUpdateManager.uploadService(robotMapReq, mapUpdateManager$upload$5) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                MainCoroutineDispatcher main = Dispatchers.getMain();
                                MapUpdateManager$upload$6 mapUpdateManager$upload$6 = new MapUpdateManager$upload$6(null);
                                mapUpdateManager$upload$5.L$0 = mapUpdateManager;
                                mapUpdateManager$upload$5.L$1 = str5;
                                mapUpdateManager$upload$5.L$2 = str3;
                                mapUpdateManager$upload$5.I$0 = i3;
                                mapUpdateManager$upload$5.L$3 = md5;
                                mapUpdateManager$upload$5.I$1 = intValue;
                                mapUpdateManager$upload$5.L$4 = str7;
                                mapUpdateManager$upload$5.label = 4;
                                if (BuildersKt.withContext(main, mapUpdateManager$upload$6, mapUpdateManager$upload$5) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        if (i2 == 3) {
                        } else if (i2 != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        int i5 = mapUpdateManager$upload$5.I$1;
                        int i6 = mapUpdateManager$upload$5.I$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    String str8 = (String) mapUpdateManager$upload$5.L$3;
                    int i7 = mapUpdateManager$upload$5.I$0;
                    String str9 = (String) mapUpdateManager$upload$5.L$2;
                    String str10 = (String) mapUpdateManager$upload$5.L$1;
                    mapUpdateManager = (MapUpdateManager) mapUpdateManager$upload$5.L$0;
                    ResultKt.throwOnFailure(obj);
                    md5 = str8;
                    str5 = str10;
                    str3 = str9;
                    i3 = i7;
                }
                intValue = ((Number) obj).intValue();
                OssService ossService2 = ossService;
                mapUpdateManager$upload$5.L$0 = mapUpdateManager;
                mapUpdateManager$upload$5.L$1 = str5;
                mapUpdateManager$upload$5.L$2 = str3;
                mapUpdateManager$upload$5.I$0 = i3;
                mapUpdateManager$upload$5.L$3 = md5;
                mapUpdateManager$upload$5.I$1 = intValue;
                mapUpdateManager$upload$5.label = 2;
                uploadFile = ossService2.uploadFile(str5, mapUpdateManager$upload$5);
                if (uploadFile == coroutine_suspended) {
                    return coroutine_suspended;
                }
                String str72 = (String) uploadFile;
                Pdlog.m3274e(TAG, str72);
                str4 = str72;
                if (!(str4 != null || str4.length() == 0)) {
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$upload$5 = new MapUpdateManager$upload$5(this, continuation);
        Object obj2 = mapUpdateManager$upload$5.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = mapUpdateManager$upload$5.label;
        if (i2 != 0) {
        }
        intValue = ((Number) obj2).intValue();
        OssService ossService22 = ossService;
        mapUpdateManager$upload$5.L$0 = mapUpdateManager;
        mapUpdateManager$upload$5.L$1 = str5;
        mapUpdateManager$upload$5.L$2 = str3;
        mapUpdateManager$upload$5.I$0 = i3;
        mapUpdateManager$upload$5.L$3 = md5;
        mapUpdateManager$upload$5.I$1 = intValue;
        mapUpdateManager$upload$5.label = 2;
        uploadFile = ossService22.uploadFile(str5, mapUpdateManager$upload$5);
        if (uploadFile == coroutine_suspended) {
        }
        String str722 = (String) uploadFile;
        Pdlog.m3274e(TAG, str722);
        str4 = str722;
        if (!(str4 != null || str4.length() == 0)) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object uploadAll(List<RobotMapResp> list, Continuation<? super Unit> continuation) {
        MapUpdateManager$uploadAll$1 mapUpdateManager$uploadAll$1;
        int i;
        MapUpdateManager mapUpdateManager;
        List<RobotMapResp> list2;
        Iterator it;
        Iterable iterable;
        if (continuation instanceof MapUpdateManager$uploadAll$1) {
            mapUpdateManager$uploadAll$1 = (MapUpdateManager$uploadAll$1) continuation;
            if ((mapUpdateManager$uploadAll$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$uploadAll$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$uploadAll$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$uploadAll$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (list != null) {
                        List<RobotMapResp> list3 = list;
                        mapUpdateManager = this;
                        list2 = list;
                        it = list3.iterator();
                        iterable = list3;
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj2 = mapUpdateManager$uploadAll$1.L$4;
                it = (Iterator) mapUpdateManager$uploadAll$1.L$3;
                iterable = (Iterable) mapUpdateManager$uploadAll$1.L$2;
                list2 = (List) mapUpdateManager$uploadAll$1.L$1;
                mapUpdateManager = (MapUpdateManager) mapUpdateManager$uploadAll$1.L$0;
                ResultKt.throwOnFailure(obj);
                while (it.hasNext()) {
                    Object next = it.next();
                    RobotMapResp robotMapResp = (RobotMapResp) next;
                    String localPath = robotMapResp.getLocalPath();
                    if (localPath != null) {
                        MapUpdateManager mapUpdateManager2 = INSTANCE;
                        String name = robotMapResp.getName();
                        int lv = robotMapResp.getLv();
                        mapUpdateManager$uploadAll$1.L$0 = mapUpdateManager;
                        mapUpdateManager$uploadAll$1.L$1 = list2;
                        mapUpdateManager$uploadAll$1.L$2 = iterable;
                        mapUpdateManager$uploadAll$1.L$3 = it;
                        mapUpdateManager$uploadAll$1.L$4 = next;
                        mapUpdateManager$uploadAll$1.L$5 = robotMapResp;
                        mapUpdateManager$uploadAll$1.L$6 = localPath;
                        mapUpdateManager$uploadAll$1.label = 1;
                        if (mapUpdateManager2.upload(localPath, name, lv, mapUpdateManager$uploadAll$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$uploadAll$1 = new MapUpdateManager$uploadAll$1(this, continuation);
        Object obj3 = mapUpdateManager$uploadAll$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$uploadAll$1.label;
        if (i != 0) {
        }
        while (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object mapCompare(final String str, final String str2, final int i, Continuation<? super Integer> continuation) {
        MapUpdateManager$mapCompare$1 mapUpdateManager$mapCompare$1;
        int i2;
        Ref.IntRef intRef;
        if (continuation instanceof MapUpdateManager$mapCompare$1) {
            mapUpdateManager$mapCompare$1 = (MapUpdateManager$mapCompare$1) continuation;
            if ((mapUpdateManager$mapCompare$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$mapCompare$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$mapCompare$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = mapUpdateManager$mapCompare$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.IntRef intRef2 = new Ref.IntRef();
                    intRef2.element = i;
                    Function1<? super List<RobotMapResp>, Unit> function1 = new Function1<List<RobotMapResp>, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$mapCompare$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(List<RobotMapResp> list) {
                            invoke2(list);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(List<RobotMapResp> list) {
                            ArrayList arrayList;
                            RobotMapResp robotMapResp;
                            String str3;
                            List<RobotMapResp> list2 = list;
                            if (list2 == null || list2.isEmpty()) {
                                return;
                            }
                            if (list != null) {
                                ArrayList arrayList2 = new ArrayList();
                                for (Object obj2 : list) {
                                    if (Intrinsics.areEqual(((RobotMapResp) obj2).getName(), str2)) {
                                        arrayList2.add(obj2);
                                    }
                                }
                                arrayList = arrayList2;
                            } else {
                                arrayList = null;
                            }
                            ArrayList arrayList3 = arrayList;
                            if ((arrayList3 == null || arrayList3.isEmpty()) || (robotMapResp = (RobotMapResp) arrayList.get(0)) == null || !(!Intrinsics.areEqual(str, robotMapResp.getMd5()))) {
                                return;
                            }
                            if (robotMapResp.getLv() > i) {
                                intRef2.element = robotMapResp.getLv();
                                return;
                            }
                            if (robotMapResp.getLv() == i) {
                                intRef2.element++;
                                LocateMappingManager.INSTANCE.updateMapVersion(str2, intRef2.element);
                                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                                str3 = MapUpdateManager.TAG;
                                Pdlog.m3275i(str3, MapUpdateManager.INSTANCE.getLocalMapList(), str2, Integer.valueOf(intRef2.element));
                            }
                        }
                    };
                    mapUpdateManager$mapCompare$1.L$0 = this;
                    mapUpdateManager$mapCompare$1.L$1 = str;
                    mapUpdateManager$mapCompare$1.L$2 = str2;
                    mapUpdateManager$mapCompare$1.I$0 = i;
                    mapUpdateManager$mapCompare$1.L$3 = intRef2;
                    mapUpdateManager$mapCompare$1.label = 1;
                    if (getMapList(function1, mapUpdateManager$mapCompare$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    intRef = intRef2;
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    intRef = (Ref.IntRef) mapUpdateManager$mapCompare$1.L$3;
                    int i3 = mapUpdateManager$mapCompare$1.I$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxInt(intRef.element);
            }
        }
        mapUpdateManager$mapCompare$1 = new MapUpdateManager$mapCompare$1(this, continuation);
        Object obj2 = mapUpdateManager$mapCompare$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = mapUpdateManager$mapCompare$1.label;
        if (i2 != 0) {
        }
        return Boxing.boxInt(intRef.element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(8:5|6|7|(1:(1:(4:11|12|13|14)(2:16|17))(3:18|19|20))(3:27|28|(1:30)(1:31))|21|(1:23)|13|14))|34|6|7|(0)(0)|21|(0)|13|14|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ba, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00bb, code lost:
    
        r10 = r18;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.pudutech.disinfect.baselib.network.response.MapUploadResponse, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object uploadService(RobotMapReq robotMapReq, Continuation<? super Unit> continuation) {
        MapUpdateManager$uploadService$1 mapUpdateManager$uploadService$1;
        Object coroutine_suspended;
        int i;
        RobotMapReq robotMapReq2;
        MapUpdateManager mapUpdateManager;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        MainCoroutineDispatcher main;
        MapUpdateManager$uploadService$2 mapUpdateManager$uploadService$2;
        if (continuation instanceof MapUpdateManager$uploadService$1) {
            mapUpdateManager$uploadService$1 = (MapUpdateManager$uploadService$1) continuation;
            if ((mapUpdateManager$uploadService$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$uploadService$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$uploadService$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$uploadService$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3274e(TAG, robotMapReq);
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
                    mapUpdateManager$uploadService$1.L$0 = this;
                    mapUpdateManager$uploadService$1.L$1 = robotMapReq;
                    mapUpdateManager$uploadService$1.L$2 = objectRef3;
                    mapUpdateManager$uploadService$1.L$3 = objectRef3;
                    mapUpdateManager$uploadService$1.label = 1;
                    Object uploadMap = map.uploadMap(robotMapReq, mapUpdateManager$uploadService$1);
                    if (uploadMap == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mapUpdateManager = this;
                    robotMapReq2 = robotMapReq;
                    objectRef = objectRef3;
                    obj = uploadMap;
                    objectRef2 = objectRef;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) mapUpdateManager$uploadService$1.L$3;
                    objectRef2 = (Ref.ObjectRef) mapUpdateManager$uploadService$1.L$2;
                    robotMapReq2 = (RobotMapReq) mapUpdateManager$uploadService$1.L$1;
                    mapUpdateManager = (MapUpdateManager) mapUpdateManager$uploadService$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e) {
                        e = e;
                        Pdlog.m3274e(TAG, e.toString());
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapUpdateManager$uploadService$3(robotMapReq2, null), 2, null);
                        return Unit.INSTANCE;
                    }
                }
                objectRef.element = (MapUploadResponse) obj;
                Pdlog.m3274e(TAG, (MapUploadResponse) objectRef2.element);
                main = Dispatchers.getMain();
                mapUpdateManager$uploadService$2 = new MapUpdateManager$uploadService$2(objectRef2, robotMapReq2, null);
                mapUpdateManager$uploadService$1.L$0 = mapUpdateManager;
                mapUpdateManager$uploadService$1.L$1 = robotMapReq2;
                mapUpdateManager$uploadService$1.L$2 = objectRef2;
                mapUpdateManager$uploadService$1.label = 2;
                if (BuildersKt.withContext(main, mapUpdateManager$uploadService$2, mapUpdateManager$uploadService$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$uploadService$1 = new MapUpdateManager$uploadService$1(this, continuation);
        Object obj2 = mapUpdateManager$uploadService$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$uploadService$1.label;
        if (i != 0) {
        }
        objectRef.element = (MapUploadResponse) obj2;
        Pdlog.m3274e(TAG, (MapUploadResponse) objectRef2.element);
        main = Dispatchers.getMain();
        mapUpdateManager$uploadService$2 = new MapUpdateManager$uploadService$2(objectRef2, robotMapReq2, null);
        mapUpdateManager$uploadService$1.L$0 = mapUpdateManager;
        mapUpdateManager$uploadService$1.L$1 = robotMapReq2;
        mapUpdateManager$uploadService$1.L$2 = objectRef2;
        mapUpdateManager$uploadService$1.label = 2;
        if (BuildersKt.withContext(main, mapUpdateManager$uploadService$2, mapUpdateManager$uploadService$1) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(8:5|6|7|(1:(1:(4:11|12|13|14)(2:16|17))(3:18|19|20))(5:33|34|(1:36)(1:41)|37|(1:39)(1:40))|21|(1:23)|13|14))|43|6|7|(0)(0)|21|(0)|13|14|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00bc, code lost:
    
        r9 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Type inference failed for: r11v6, types: [com.pudutech.disinfect.baselib.network.response.MapPermissionResponse, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkPermissionCode(String str, Function1<? super String, Unit> function1, Continuation<? super Unit> continuation) {
        MapUpdateManager$checkPermissionCode$1 mapUpdateManager$checkPermissionCode$1;
        Object coroutine_suspended;
        int i;
        MapUpdateManager mapUpdateManager;
        String str2;
        Ref.ObjectRef objectRef;
        Function1<? super String, Unit> function12;
        Ref.ObjectRef objectRef2;
        MainCoroutineDispatcher main;
        MapUpdateManager$checkPermissionCode$2 mapUpdateManager$checkPermissionCode$2;
        if (continuation instanceof MapUpdateManager$checkPermissionCode$1) {
            mapUpdateManager$checkPermissionCode$1 = (MapUpdateManager$checkPermissionCode$1) continuation;
            if ((mapUpdateManager$checkPermissionCode$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$checkPermissionCode$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$checkPermissionCode$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$checkPermissionCode$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
                    String mac = WifiUtil.INSTANCE.getMac();
                    if (mac == null) {
                        mac = "";
                    }
                    RobotPermissionReq robotPermissionReq = new RobotPermissionReq(str, mac);
                    mapUpdateManager$checkPermissionCode$1.L$0 = this;
                    mapUpdateManager$checkPermissionCode$1.L$1 = str;
                    mapUpdateManager$checkPermissionCode$1.L$2 = function1;
                    mapUpdateManager$checkPermissionCode$1.L$3 = objectRef3;
                    mapUpdateManager$checkPermissionCode$1.L$4 = objectRef3;
                    mapUpdateManager$checkPermissionCode$1.label = 1;
                    Object checkPermissionCode = map.checkPermissionCode(robotPermissionReq, mapUpdateManager$checkPermissionCode$1);
                    if (checkPermissionCode == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mapUpdateManager = this;
                    str2 = str;
                    objectRef = objectRef3;
                    obj = checkPermissionCode;
                    function12 = function1;
                    objectRef2 = objectRef;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) mapUpdateManager$checkPermissionCode$1.L$4;
                    objectRef2 = (Ref.ObjectRef) mapUpdateManager$checkPermissionCode$1.L$3;
                    function12 = (Function1) mapUpdateManager$checkPermissionCode$1.L$2;
                    str2 = (String) mapUpdateManager$checkPermissionCode$1.L$1;
                    mapUpdateManager = (MapUpdateManager) mapUpdateManager$checkPermissionCode$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e) {
                        e = e;
                        function1 = function12;
                        Pdlog.m3274e(TAG, e.getMessage());
                        if (function1 != null) {
                            String message = e.getMessage();
                            if (message == null) {
                                Intrinsics.throwNpe();
                            }
                            function1.invoke(message);
                        }
                        return Unit.INSTANCE;
                    }
                }
                objectRef.element = (MapPermissionResponse) obj;
                main = Dispatchers.getMain();
                mapUpdateManager$checkPermissionCode$2 = new MapUpdateManager$checkPermissionCode$2(objectRef2, function12, null);
                mapUpdateManager$checkPermissionCode$1.L$0 = mapUpdateManager;
                mapUpdateManager$checkPermissionCode$1.L$1 = str2;
                mapUpdateManager$checkPermissionCode$1.L$2 = function12;
                mapUpdateManager$checkPermissionCode$1.L$3 = objectRef2;
                mapUpdateManager$checkPermissionCode$1.label = 2;
                if (BuildersKt.withContext(main, mapUpdateManager$checkPermissionCode$2, mapUpdateManager$checkPermissionCode$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$checkPermissionCode$1 = new MapUpdateManager$checkPermissionCode$1(this, continuation);
        Object obj2 = mapUpdateManager$checkPermissionCode$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$checkPermissionCode$1.label;
        if (i != 0) {
        }
        objectRef.element = (MapPermissionResponse) obj2;
        main = Dispatchers.getMain();
        mapUpdateManager$checkPermissionCode$2 = new MapUpdateManager$checkPermissionCode$2(objectRef2, function12, null);
        mapUpdateManager$checkPermissionCode$1.L$0 = mapUpdateManager;
        mapUpdateManager$checkPermissionCode$1.L$1 = str2;
        mapUpdateManager$checkPermissionCode$1.L$2 = function12;
        mapUpdateManager$checkPermissionCode$1.L$3 = objectRef2;
        mapUpdateManager$checkPermissionCode$1.label = 2;
        if (BuildersKt.withContext(main, mapUpdateManager$checkPermissionCode$2, mapUpdateManager$checkPermissionCode$1) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(8:5|6|7|(1:(1:(4:11|12|13|14)(2:16|17))(3:18|19|20))(5:30|31|(1:33)(1:38)|34|(1:36)(1:37))|21|(1:23)|13|14))|40|6|7|(0)(0)|21|(0)|13|14|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ae, code lost:
    
        r10 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r10v9, types: [T, com.pudutech.disinfect.baselib.network.response.ResAuth] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkEditAuth(Function1<? super ResAuth, Unit> function1, Continuation<? super Unit> continuation) {
        MapUpdateManager$checkEditAuth$1 mapUpdateManager$checkEditAuth$1;
        Object coroutine_suspended;
        int i;
        MapUpdateManager mapUpdateManager;
        Function1<? super ResAuth, Unit> function12;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        MainCoroutineDispatcher main;
        MapUpdateManager$checkEditAuth$2 mapUpdateManager$checkEditAuth$2;
        if (continuation instanceof MapUpdateManager$checkEditAuth$1) {
            mapUpdateManager$checkEditAuth$1 = (MapUpdateManager$checkEditAuth$1) continuation;
            if ((mapUpdateManager$checkEditAuth$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$checkEditAuth$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$checkEditAuth$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$checkEditAuth$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
                    String mac = WifiUtil.INSTANCE.getMac();
                    if (mac == null) {
                        mac = "";
                    }
                    ReqCheckAuth reqCheckAuth = new ReqCheckAuth(mac);
                    mapUpdateManager$checkEditAuth$1.L$0 = this;
                    mapUpdateManager$checkEditAuth$1.L$1 = function1;
                    mapUpdateManager$checkEditAuth$1.L$2 = objectRef3;
                    mapUpdateManager$checkEditAuth$1.L$3 = objectRef3;
                    mapUpdateManager$checkEditAuth$1.label = 1;
                    Object checkAuth = map.checkAuth(reqCheckAuth, mapUpdateManager$checkEditAuth$1);
                    if (checkAuth == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mapUpdateManager = this;
                    function12 = function1;
                    objectRef = objectRef3;
                    obj = checkAuth;
                    objectRef2 = objectRef;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) mapUpdateManager$checkEditAuth$1.L$3;
                    objectRef2 = (Ref.ObjectRef) mapUpdateManager$checkEditAuth$1.L$2;
                    function12 = (Function1) mapUpdateManager$checkEditAuth$1.L$1;
                    mapUpdateManager = (MapUpdateManager) mapUpdateManager$checkEditAuth$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e) {
                        e = e;
                        function1 = function12;
                        Pdlog.m3274e(TAG, e.getMessage());
                        if (function1 != null) {
                            function1.invoke(null);
                        }
                        return Unit.INSTANCE;
                    }
                }
                objectRef.element = (ResAuth) obj;
                main = Dispatchers.getMain();
                mapUpdateManager$checkEditAuth$2 = new MapUpdateManager$checkEditAuth$2(function12, objectRef2, null);
                mapUpdateManager$checkEditAuth$1.L$0 = mapUpdateManager;
                mapUpdateManager$checkEditAuth$1.L$1 = function12;
                mapUpdateManager$checkEditAuth$1.L$2 = objectRef2;
                mapUpdateManager$checkEditAuth$1.label = 2;
                if (BuildersKt.withContext(main, mapUpdateManager$checkEditAuth$2, mapUpdateManager$checkEditAuth$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$checkEditAuth$1 = new MapUpdateManager$checkEditAuth$1(this, continuation);
        Object obj2 = mapUpdateManager$checkEditAuth$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$checkEditAuth$1.label;
        if (i != 0) {
        }
        objectRef.element = (ResAuth) obj2;
        main = Dispatchers.getMain();
        mapUpdateManager$checkEditAuth$2 = new MapUpdateManager$checkEditAuth$2(function12, objectRef2, null);
        mapUpdateManager$checkEditAuth$1.L$0 = mapUpdateManager;
        mapUpdateManager$checkEditAuth$1.L$1 = function12;
        mapUpdateManager$checkEditAuth$1.L$2 = objectRef2;
        mapUpdateManager$checkEditAuth$1.label = 2;
        if (BuildersKt.withContext(main, mapUpdateManager$checkEditAuth$2, mapUpdateManager$checkEditAuth$1) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }

    public final Object mapCopy(MapInfo mapInfo, final Function1<? super MapInfo, Unit> function1, final String str, Continuation<? super Unit> continuation) {
        String mapFile = getMapFile(mapInfo.getMapName());
        if (!FileUtils.isFileExists(mapFile) && !FileUtils.isFile(mapFile)) {
            if (function1 != null) {
                function1.invoke(null);
            }
            Pdlog.m3274e(TAG, "文件不存在或者不是文件");
            return Unit.INSTANCE;
        }
        final String mapFile2 = getMapFile(str);
        boolean copyFile = FileUtils.copyFile(mapFile, mapFile2);
        Pdlog.m3274e(TAG, mapFile2, mapFile);
        if (copyFile) {
            LocateMappingManager.INSTANCE.updateMapVersion(str, 1);
            reloadMap(RobotMapManager.INSTANCE.getDefaultPdmap(), new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$mapCopy$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                    invoke2(str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str2) {
                    String str3 = str2;
                    if (str3 == null || str3.length() == 0) {
                        FileUtils.delete(mapFile2);
                        Function1 function12 = function1;
                        if (function12 != null) {
                            return;
                        }
                        return;
                    }
                    byte[] encryptMD5File = EncryptUtils.encryptMD5File(mapFile2);
                    Intrinsics.checkExpressionValueIsNotNull(encryptMD5File, "EncryptUtils.encryptMD5File(copyPath)");
                    MapInfo mapInfo2 = new MapInfo(1, encryptMD5File, str, "");
                    Function1 function13 = function1;
                    if (function13 != null) {
                    }
                }
            });
        } else if (function1 != null) {
            function1.invoke(null);
        }
        return Unit.INSTANCE;
    }

    public final List<RobotMapResp> getNeedUpdateData() {
        return needUpdateData;
    }

    public final void setNeedUpdateData(List<RobotMapResp> list) {
        needUpdateData = list;
    }

    public final List<RobotMapResp> getNeedUploadData() {
        return needUploadData;
    }

    public final void setNeedUploadData(List<RobotMapResp> list) {
        needUploadData = list;
    }

    public final void downloadMap() {
        List<RobotMapResp> list = needUpdateData;
        if (list == null || list.isEmpty()) {
            return;
        }
        List<RobotMapResp> list2 = needUpdateData;
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        downloadAll(list2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object isShouldUpdate(List<RobotMapResp> list, List<MapInfo> list2, Continuation<? super Boolean> continuation) {
        MapUpdateManager$isShouldUpdate$1 mapUpdateManager$isShouldUpdate$1;
        int i;
        if (continuation instanceof MapUpdateManager$isShouldUpdate$1) {
            mapUpdateManager$isShouldUpdate$1 = (MapUpdateManager$isShouldUpdate$1) continuation;
            if ((mapUpdateManager$isShouldUpdate$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$isShouldUpdate$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$isShouldUpdate$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$isShouldUpdate$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    needUpdateData = (List) null;
                    mapUpdateManager$isShouldUpdate$1.L$0 = this;
                    mapUpdateManager$isShouldUpdate$1.L$1 = list;
                    mapUpdateManager$isShouldUpdate$1.L$2 = list2;
                    mapUpdateManager$isShouldUpdate$1.label = 1;
                    obj = compareLocalAndCloud(list, list2, true, mapUpdateManager$isShouldUpdate$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                needUpdateData = (List) obj;
                Pdlog.m3274e(TAG, "isShouldUpdate: " + needUpdateData);
                List<RobotMapResp> list3 = needUpdateData;
                return Boxing.boxBoolean(!(list3 != null || list3.isEmpty()));
            }
        }
        mapUpdateManager$isShouldUpdate$1 = new MapUpdateManager$isShouldUpdate$1(this, continuation);
        Object obj2 = mapUpdateManager$isShouldUpdate$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$isShouldUpdate$1.label;
        if (i != 0) {
        }
        needUpdateData = (List) obj2;
        Pdlog.m3274e(TAG, "isShouldUpdate: " + needUpdateData);
        List<RobotMapResp> list32 = needUpdateData;
        return Boxing.boxBoolean(!(list32 != null || list32.isEmpty()));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object isShouldUpload(List<RobotMapResp> list, List<MapInfo> list2, Continuation<? super Boolean> continuation) {
        MapUpdateManager$isShouldUpload$1 mapUpdateManager$isShouldUpload$1;
        int i;
        if (continuation instanceof MapUpdateManager$isShouldUpload$1) {
            mapUpdateManager$isShouldUpload$1 = (MapUpdateManager$isShouldUpload$1) continuation;
            if ((mapUpdateManager$isShouldUpload$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$isShouldUpload$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$isShouldUpload$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$isShouldUpload$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    needUploadData = (List) null;
                    mapUpdateManager$isShouldUpload$1.L$0 = this;
                    mapUpdateManager$isShouldUpload$1.L$1 = list;
                    mapUpdateManager$isShouldUpload$1.L$2 = list2;
                    mapUpdateManager$isShouldUpload$1.label = 1;
                    obj = compareLocalAndCloud(list, list2, false, mapUpdateManager$isShouldUpload$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                needUploadData = (List) obj;
                Pdlog.m3274e(TAG, "isShouldUpload: " + needUploadData);
                List<RobotMapResp> list3 = needUploadData;
                return Boxing.boxBoolean(!(list3 != null || list3.isEmpty()));
            }
        }
        mapUpdateManager$isShouldUpload$1 = new MapUpdateManager$isShouldUpload$1(this, continuation);
        Object obj2 = mapUpdateManager$isShouldUpload$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$isShouldUpload$1.label;
        if (i != 0) {
        }
        needUploadData = (List) obj2;
        Pdlog.m3274e(TAG, "isShouldUpload: " + needUploadData);
        List<RobotMapResp> list32 = needUploadData;
        return Boxing.boxBoolean(!(list32 != null || list32.isEmpty()));
    }

    public final List<MapInfo> getLocalMapList() {
        List<MapInfo> asMutableList = TypeIntrinsics.asMutableList(MapingFuntionManager.INSTANCE.getMapListVersion());
        Pdlog.m3274e(TAG, "getLocalMapList =", asMutableList);
        return asMutableList;
    }

    final /* synthetic */ Object compareLocalAndCloud(List<RobotMapResp> list, List<MapInfo> list2, boolean z, Continuation<? super List<RobotMapResp>> continuation) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        if (z) {
            List<RobotMapResp> list3 = list;
            if (list3 == null || list3.isEmpty()) {
                return null;
            }
        } else {
            List<RobotMapResp> list4 = list;
            if (list4 == null || list4.isEmpty()) {
                List<MapInfo> list5 = list2;
                if (!(list5 == null || list5.isEmpty())) {
                    if (list2 != null) {
                        for (MapInfo mapInfo : list2) {
                            String mapName = mapInfo.getMapName();
                            int mapVersion = mapInfo.getMapVersion();
                            String encryptMD5ToString = EncryptUtils.encryptMD5ToString(mapInfo.getMd5());
                            Intrinsics.checkExpressionValueIsNotNull(encryptMD5ToString, "EncryptUtils.encryptMD5ToString(it.md5)");
                            arrayList3.add(new RobotMapResp(mapName, "", mapVersion, encryptMD5ToString, false, null, null, null, DimensionsKt.HDPI, null));
                        }
                    }
                    return arrayList3;
                }
            }
        }
        if (z) {
            List<MapInfo> list6 = list2;
            if (list6 == null || list6.isEmpty()) {
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        arrayList3.add((RobotMapResp) it.next());
                    }
                }
                Pdlog.m3273d(TAG, "localData " + arrayList3);
                return arrayList3;
            }
            if (list != null) {
                for (RobotMapResp robotMapResp : list) {
                    if (list2 != null) {
                        ArrayList arrayList4 = new ArrayList();
                        for (Object obj : list2) {
                            if (Boxing.boxBoolean(Intrinsics.areEqual(robotMapResp.getName(), ((MapInfo) obj).getMapName())).booleanValue()) {
                                arrayList4.add(obj);
                            }
                        }
                        arrayList2 = arrayList4;
                    } else {
                        arrayList2 = null;
                    }
                    ArrayList arrayList5 = arrayList2;
                    if (arrayList5 == null || arrayList5.isEmpty()) {
                        arrayList3.add(robotMapResp);
                    } else {
                        MapInfo mapInfo2 = (MapInfo) arrayList2.get(0);
                        if ((!Intrinsics.areEqual(robotMapResp.getMd5(), EncryptUtils.encryptMD5ToString(mapInfo2.getMd5()))) && mapInfo2.getMapVersion() < robotMapResp.getLv()) {
                            arrayList3.add(robotMapResp);
                        }
                    }
                }
            }
        } else if (list2 != null) {
            for (MapInfo mapInfo3 : list2) {
                if (list != null) {
                    ArrayList arrayList6 = new ArrayList();
                    for (Object obj2 : list) {
                        if (Boxing.boxBoolean(Intrinsics.areEqual(mapInfo3.getMapName(), ((RobotMapResp) obj2).getName())).booleanValue()) {
                            arrayList6.add(obj2);
                        }
                    }
                    arrayList = arrayList6;
                } else {
                    arrayList = null;
                }
                ArrayList arrayList7 = arrayList;
                if (arrayList7 == null || arrayList7.isEmpty()) {
                    String mapName2 = mapInfo3.getMapName();
                    int mapVersion2 = mapInfo3.getMapVersion();
                    String encryptMD5ToString2 = EncryptUtils.encryptMD5ToString(mapInfo3.getMd5());
                    Intrinsics.checkExpressionValueIsNotNull(encryptMD5ToString2, "EncryptUtils.encryptMD5ToString(mapInfo.md5)");
                    arrayList3.add(new RobotMapResp(mapName2, "", mapVersion2, encryptMD5ToString2, false, null, null, null, DimensionsKt.HDPI, null));
                } else {
                    RobotMapResp robotMapResp2 = (RobotMapResp) arrayList.get(0);
                    if ((!Intrinsics.areEqual(robotMapResp2.getMd5(), EncryptUtils.encryptMD5ToString(mapInfo3.getMd5()))) && mapInfo3.getMapVersion() > robotMapResp2.getLv()) {
                        arrayList3.add(robotMapResp2);
                    }
                }
            }
        }
        return arrayList3;
    }

    public final Object syncMap(Continuation<? super Unit> continuation) {
        Function0<Unit> function0 = onSyncFinishListener;
        Unit invoke = function0 != null ? function0.invoke() : null;
        return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }

    public final void asyncComplete() {
        Pdlog.m3273d(TAG, "needUpdateData " + needUpdateData);
        List<RobotMapResp> list = needUpdateData;
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((RobotMapResp) obj).getState() == null) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            StepState state = MirSdkManager.INSTANCE.getState();
            if (MirSdkManager.INSTANCE.getStep() == null || state == null) {
                return;
            }
            InitStep initStep = InitStep.Finish;
            Pdlog.m3274e(TAG, "asyncComplete", "needUploadData=" + needUploadData, "needUpdateData=" + needUpdateData, "filter=" + arrayList2);
            if (arrayList2.isEmpty()) {
                INSTANCE.moveMapFileAndDelTem(new Function1<Boolean, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$asyncComplete$1$1$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        String str;
                        if (RobotMapManager.INSTANCE.getDefaultPdmap().length() == 0) {
                            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                            str = MapUpdateManager.TAG;
                            Pdlog.m3273d(str, "defaultPdmap is null");
                            Function0<Unit> onSyncFinishListener2 = MapUpdateManager.INSTANCE.getOnSyncFinishListener();
                            if (onSyncFinishListener2 != null) {
                                onSyncFinishListener2.invoke();
                                return;
                            }
                            return;
                        }
                        MapUpdateManager.INSTANCE.reloadMap(RobotMapManager.INSTANCE.getDefaultPdmap(), new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$asyncComplete$1$1$1$1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                                invoke2(str2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String str2) {
                                String str3;
                                MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
                                str3 = MapUpdateManager.TAG;
                                Pdlog.m3274e(str3, "async2", str2);
                                RobotMapManager.INSTANCE.reloadMap();
                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
                            }

                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* compiled from: MapUpdateManager.kt */
                            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                            @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$asyncComplete$1$1$1$1$1$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                            /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$asyncComplete$1$1$1$1$1$1, reason: invalid class name */
                            /* loaded from: classes5.dex */
                            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                int label;

                                /* renamed from: p$ */
                                private CoroutineScope f6745p$;

                                AnonymousClass1(Continuation continuation) {
                                    super(2, continuation);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                                    anonymousClass1.f6745p$ = (CoroutineScope) obj;
                                    return anonymousClass1;
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    if (this.label != 0) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                    CoroutineScope coroutineScope = this.f6745p$;
                                    Function0<Unit> onSyncFinishListener = MapUpdateManager.INSTANCE.getOnSyncFinishListener();
                                    if (onSyncFinishListener != null) {
                                        onSyncFinishListener.invoke();
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    public final void moveMapFileAndDelTem(Function1<? super Boolean, Unit> result) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        if (FileUtils.isFileExists(DOWNLOAD_TEMP_FILE_PATH)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MapUpdateManager$moveMapFileAndDelTem$1(result, null), 2, null);
        }
    }

    private final void copyFileUsingFileChannels(File source, File dest) {
        FileChannel fileChannel;
        Throwable th;
        FileChannel fileChannel2;
        FileChannel fileChannel3 = (FileChannel) null;
        try {
            fileChannel2 = new FileInputStream(source).getChannel();
            try {
                fileChannel = new FileOutputStream(dest).getChannel();
            } catch (Throwable th2) {
                th = th2;
                fileChannel = fileChannel3;
            }
            try {
                fileChannel.transferFrom(fileChannel2, 0L, fileChannel2.size());
                Pdlog.m3274e(TAG, "moveFinish");
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (Throwable th3) {
                th = th3;
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            fileChannel = fileChannel3;
            th = th4;
            fileChannel2 = fileChannel;
        }
    }

    public final List<RobotMapResp> getErrorData() {
        return errorData;
    }

    public final boolean checkTempMapRule(RobotMapResp it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        String tempMapName = getTempMapName(it.getName());
        if (!FileUtils.isFileExists(tempMapName)) {
            Pdlog.m3274e(TAG, tempMapName + " 不存在");
            return false;
        }
        Boolean checkMapRule = MapingFuntionManager.INSTANCE.checkMapRule(tempMapName);
        if (checkMapRule == null || !checkMapRule.booleanValue()) {
            errorData.add(it);
        }
        Pdlog.m3274e(TAG, "checkMapRuleAndDel=" + checkMapRule + " path=" + tempMapName, errorData);
        if (checkMapRule != null) {
            return checkMapRule.booleanValue();
        }
        return false;
    }

    public final void deleteFailureDownloadFile(RobotMapResp it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        String tempMapName = getTempMapName(it.getName());
        if (!FileUtils.isFileExists(tempMapName)) {
            Pdlog.m3274e(TAG, tempMapName + " 不存在");
            return;
        }
        boolean delete = FileUtils.delete(tempMapName);
        errorData.add(it);
        Pdlog.m3274e(TAG, "deleteFailureDownloadFile=" + delete + " path=" + tempMapName);
    }

    public final boolean checkMapRule(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String mapFile = getMapFile(name);
        if (!FileUtils.isFileExists(mapFile)) {
            Pdlog.m3274e(TAG, "checkMapRule", mapFile + " 不存在");
            return false;
        }
        Pdlog.m3274e(TAG, "checkMapRule=", mapFile);
        Boolean checkMapRule = MapingFuntionManager.INSTANCE.checkMapRule(mapFile);
        Pdlog.m3274e(TAG, checkMapRule, mapFile);
        if (checkMapRule != null) {
            return checkMapRule.booleanValue();
        }
        return false;
    }

    public final String getTempMapName(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3274e(TAG, name);
        return DOWNLOAD_TEMP_FILE_PATH + getMapBase64Name(name);
    }

    public final String getMapBase64Name(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return ExtandsKt.encodeMapName(name) + ".pdmap";
    }

    public final String getMapFile(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String str = MAP_FILE_PATH + getMapBase64Name(name);
        Pdlog.m3274e(TAG, str);
        return str;
    }

    public final void reloadMap(String name, Function1<? super String, Unit> result) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(result, "result");
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        MirSdkManager.INSTANCE.addLocateListener(name, new MapUpdateManager$reloadMap$1(result, name, booleanRef));
        Pdlog.m3274e(TAG, "reloadCore");
        RobotMapManager.INSTANCE.loadLocateCore(name);
    }

    public final void loadTopMap(String name, Function1<? super String, Unit> result) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(result, "result");
        MirSdkManager.INSTANCE.addLocateListener(name, new MapUpdateManager$loadTopMap$1(result, name));
        Pdlog.m3274e(TAG, "loadTopMap");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MapUpdateManager$loadTopMap$2(name, null), 3, null);
    }

    public final void release() {
        List<RobotMapResp> list = (List) null;
        needUpdateData = list;
        needUploadData = list;
        errorData.clear();
        onSuccessListener = (Function2) null;
        Function3 function3 = (Function3) null;
        updateProgressListener = function3;
        onErrorListener = function3;
        onSyncFinishListener = (Function0) null;
        ossService.clearAll();
        deleteTempFile();
    }

    public final void deleteTempFile() {
        try {
            if (FileUtils.isDir(DOWNLOAD_TEMP_FILE_PATH)) {
                FileUtils.deleteDir(DOWNLOAD_TEMP_FILE_PATH);
            }
        } catch (Exception unused) {
            Pdlog.m3274e(TAG, "deleteTempFile");
        }
    }

    public final Function2<String, String, Unit> getOnSuccessListener() {
        return onSuccessListener;
    }

    public final void setOnSuccessListener(Function2<? super String, ? super String, Unit> function2) {
        onSuccessListener = function2;
    }

    public final Function0<Unit> getOnSyncFinishListener() {
        return onSyncFinishListener;
    }

    public final void setOnSyncFinishListener(Function0<Unit> function0) {
        onSyncFinishListener = function0;
    }

    public final Function3<String, String, String, Unit> getUpdateProgressListener() {
        return updateProgressListener;
    }

    public final void setUpdateProgressListener(Function3<? super String, ? super String, ? super String, Unit> function3) {
        updateProgressListener = function3;
    }

    public final Function3<String, UpdateErrorSealed, String, Unit> getOnErrorListener() {
        return onErrorListener;
    }

    public final void setOnErrorListener(Function3<? super String, ? super UpdateErrorSealed, ? super String, Unit> function3) {
        onErrorListener = function3;
    }
}
