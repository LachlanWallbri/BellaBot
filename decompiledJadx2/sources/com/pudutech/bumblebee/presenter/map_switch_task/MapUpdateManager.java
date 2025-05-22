package com.pudutech.bumblebee.presenter.map_switch_task;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.oss.OssService;
import com.pudutech.bumblebee.business.oss.UpdateErrorSealed;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.utils.UtilsKt;
import com.pudutech.bumblebee.presenter.BusinessContext;
import com.pudutech.bumblebee.presenter.utils.StringExtKt;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.bumblebee.presenter.utils.ext.ListExtKt;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.ReqGetMapList;
import com.pudutech.disinfect.baselib.network.req.RobotMapReqV2;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.MapUploadResponse;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.disinfect.baselib.network.response.RobotMapRespWrap;
import com.pudutech.leaselib.utils.EncryptUtils;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u00107\u001a\u00020 J\u000e\u00108\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\rJ8\u0010;\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\f2\u0006\u0010?\u001a\u00020\u000fH\u0002J\u0018\u0010@\u001a\u00020 2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020BH\u0002J\u0006\u0010D\u001a\u00020 J\u000e\u0010E\u001a\u00020 2\u0006\u0010:\u001a\u00020\rJ\u0010\u0010F\u001a\u00020 2\u0006\u0010G\u001a\u00020\u0004H\u0002J1\u0010H\u001a\u00020 2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010IJ\u0006\u0010J\u001a\u00020 J7\u0010K\u001a\u00020 2\u0006\u0010L\u001a\u00020M2\u0006\u0010:\u001a\u00020\r2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010O\u001a\u00020PH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010QJ)\u0010R\u001a\u00020 2\u000e\u0010N\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010O\u001a\u00020PH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010SJ\u0019\u0010T\u001a\u00020 2\u0006\u0010N\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010UJ\u001e\u0010V\u001a\b\u0012\u0004\u0012\u00020B0W2\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Y\u001a\u00020\u0004H\u0002J\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\fJ\u000e\u0010[\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010\\\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u0019\u0010]\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010^J<\u0010]\u001a\u00020 2)\u0010_\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020 0`H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010aJ\u000e\u0010b\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u0006\u0010c\u001a\u00020\u000fJ1\u0010d\u001a\u00020\u000f2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010IJ1\u0010e\u001a\u00020\u000f2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010IJ)\u0010f\u001a\u00020M2\u0006\u0010g\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010h\u001a\u00020MH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010iJ)\u0010j\u001a\u00020 2!\u0010k\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(l\u0012\u0004\u0012\u00020 0`J<\u0010m\u001a\u00020 2\u0006\u0010G\u001a\u00020\u00042!\u0010k\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(l\u0012\u0004\u0012\u00020 0`H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010nJ\u0006\u0010o\u001a\u00020 J\u0010\u0010p\u001a\u00020 2\u0006\u0010G\u001a\u00020\u0004H\u0002J\u0019\u0010q\u001a\u00020 2\u0006\u0010O\u001a\u00020PH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010rJ\u0019\u0010s\u001a\u00020 2\u0006\u0010O\u001a\u00020PH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010rJ\u0019\u0010t\u001a\u00020 2\u0006\u0010u\u001a\u00020>H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010vJ)\u0010t\u001a\u00020 2\u0006\u0010w\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010x\u001a\u00020MH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010iJ!\u0010y\u001a\u00020 2\u000e\u0010N\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010zJ7\u0010{\u001a\u00020 2\b\b\u0002\u0010\u001b\u001a\u00020\u00042%\b\u0002\u0010k\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(|\u0012\u0004\u0012\u00020 0`J\u001a\u0010}\u001a\u00020 2\u0006\u0010~\u001a\u00020\u007fH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0080\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014Ra\u0010\u0018\u001aI\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$RL\u0010%\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010+\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000Ra\u00103\u001aI\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\"\"\u0004\b6\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0081\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdateManager;", "", "()V", "DOWNLOAD_TEMP_FILE_PATH", "", "MAP_FILE_PATH", "getMAP_FILE_PATH", "()Ljava/lang/String;", "TAG", "TYPE_DOWNLOAD", "TYPE_UPLOAD", "errorData", "", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "isGetMapListError", "", "needUpdateData", "getNeedUpdateData", "()Ljava/util/List;", "setNeedUpdateData", "(Ljava/util/List;)V", "needUploadData", "getNeedUploadData", "setNeedUploadData", "onErrorListener", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "url", "Lcom/pudutech/bumblebee/business/oss/UpdateErrorSealed;", "errorType", "operateType", "", "getOnErrorListener", "()Lkotlin/jvm/functions/Function3;", "setOnErrorListener", "(Lkotlin/jvm/functions/Function3;)V", "onSuccessListener", "Lkotlin/Function2;", "getOnSuccessListener", "()Lkotlin/jvm/functions/Function2;", "setOnSuccessListener", "(Lkotlin/jvm/functions/Function2;)V", "onSyncFinishListener", "Lkotlin/Function0;", "getOnSyncFinishListener", "()Lkotlin/jvm/functions/Function0;", "setOnSyncFinishListener", "(Lkotlin/jvm/functions/Function0;)V", "ossService", "Lcom/pudutech/bumblebee/business/oss/OssService;", "updateProgressListener", "progress", "getUpdateProgressListener", "setUpdateProgressListener", "asyncComplete", "checkMapRule", "checkTempMapRule", "it", "compareLocalAndCloud", "cloudData", "localData", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "isUpdate", "copyFileUsingFileChannels", MapElement.Source.SOURCE, "Ljava/io/File;", "dest", "deleteAllMapFile", "deleteFailureDownloadFile", "deleteLocalMap", "mapName", "deleteMap", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTempFile", "download", "currentIndex", "", "data", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(ILcom/pudutech/disinfect/baselib/network/response/RobotMapResp;Ljava/util/List;Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadAll", "(Ljava/util/List;Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadOne", "(Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFiles", "Ljava/util/Vector;", "dirPath", "fileType", "getLocalMapList", "getMapBase64Name", "getMapFile", "getMapList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "list", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTempMapName", "hasMapOnMapPath", "isShouldUpdate", "isShouldUpload", "mapCompare", "md5", "lv", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moveMapFileAndDelTem", SpeechUtility.TAG_RESOURCE_RESULT, "isFinish", "moveMapFileAndDelTemp", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "release", "reloadMap", "startSyncMap", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncMap", "upload", "mapInfo", "(Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filePath", "version", "uploadAll", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadMapUse", "error", "uploadServiceV2", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/RobotMapReqV2;", "(Lcom/pudutech/disinfect/baselib/network/req/RobotMapReqV2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapUpdateManager {
    private static final String DOWNLOAD_TEMP_FILE_PATH;
    public static final MapUpdateManager INSTANCE;
    private static final String MAP_FILE_PATH;
    private static final String TAG;
    public static final String TYPE_DOWNLOAD = "TYPE_DOWNLOAD";
    public static final String TYPE_UPLOAD = "TYPE_UPLOAD";
    private static final List<RobotMapResp> errorData;
    private static boolean isGetMapListError;
    private static List<RobotMapResp> needUpdateData;
    private static List<RobotMapResp> needUploadData;
    private static Function3<? super String, ? super UpdateErrorSealed, ? super String, Unit> onErrorListener;
    private static Function2<? super String, ? super String, Unit> onSuccessListener;
    private static Function0<Unit> onSyncFinishListener;
    private static final OssService ossService;
    private static Function3<? super String, ? super String, ? super String, Unit> updateProgressListener;

    static {
        MapUpdateManager mapUpdateManager = new MapUpdateManager();
        INSTANCE = mapUpdateManager;
        String simpleName = mapUpdateManager.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        TAG = simpleName;
        DOWNLOAD_TEMP_FILE_PATH = DOWNLOAD_TEMP_FILE_PATH;
        MAP_FILE_PATH = MAP_FILE_PATH;
        ossService = new OssService();
        errorData = new ArrayList();
    }

    private MapUpdateManager() {
    }

    public final String getMAP_FILE_PATH() {
        return MAP_FILE_PATH;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:1|(2:3|(10:5|6|7|(1:(1:(4:11|12|13|14)(2:16|17))(3:18|19|20))(3:33|34|(2:36|37)(4:38|(1:40)(1:45)|41|(1:43)(1:44)))|21|(1:23)(1:30)|24|(2:26|(1:28))(1:29)|13|14))|48|6|7|(0)(0)|21|(0)(0)|24|(0)(0)|13|14|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00f5, code lost:
    
        r13 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f6, code lost:
    
        r7 = r12;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c5 A[Catch: Exception -> 0x0058, TryCatch #0 {Exception -> 0x0058, blocks: (B:20:0x0054, B:21:0x009d, B:23:0x00c5, B:24:0x00cf, B:26:0x00d5, B:29:0x00f1), top: B:19:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d5 A[Catch: Exception -> 0x0058, TryCatch #0 {Exception -> 0x0058, blocks: (B:20:0x0054, B:21:0x009d, B:23:0x00c5, B:24:0x00cf, B:26:0x00d5, B:29:0x00f1), top: B:19:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f1 A[Catch: Exception -> 0x0058, TRY_LEAVE, TryCatch #0 {Exception -> 0x0058, blocks: (B:20:0x0054, B:21:0x009d, B:23:0x00c5, B:24:0x00cf, B:26:0x00d5, B:29:0x00f1), top: B:19:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /* JADX WARN: Type inference failed for: r13v11, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
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
        ApiResponse apiResponse;
        if (continuation instanceof MapUpdateManager$getMapList$1) {
            mapUpdateManager$getMapList$1 = (MapUpdateManager$getMapList$1) continuation;
            if ((mapUpdateManager$getMapList$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$getMapList$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$getMapList$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$getMapList$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (WifiUtil.INSTANCE.getMac() == null) {
                        function1.invoke(null);
                        return Unit.INSTANCE;
                    }
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
                        Pdlog.m3273d(TAG, "getMapList() " + e.getMessage());
                        function12.invoke(null);
                        return Unit.INSTANCE;
                    }
                }
                objectRef.element = (ApiResponse) obj;
                Pdlog.m3273d(TAG, "getMapList: " + ((ApiResponse) objectRef2.element));
                apiResponse = (ApiResponse) objectRef2.element;
                if ((apiResponse == null ? Boxing.boxInt(apiResponse.getCode()) : null).intValue() != 0) {
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
        Pdlog.m3273d(TAG, "getMapList: " + ((ApiResponse) objectRef2.element));
        apiResponse = (ApiResponse) objectRef2.element;
        if ((apiResponse == null ? Boxing.boxInt(apiResponse.getCode()) : null).intValue() != 0) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0083 A[Catch: Exception -> 0x009f, TryCatch #0 {Exception -> 0x009f, blocks: (B:11:0x002c, B:12:0x0065, B:14:0x0083, B:15:0x008d, B:17:0x0093, B:27:0x003b, B:30:0x0044, B:33:0x0057), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0093 A[Catch: Exception -> 0x009f, TRY_LEAVE, TryCatch #0 {Exception -> 0x009f, blocks: (B:11:0x002c, B:12:0x0065, B:14:0x0083, B:15:0x008d, B:17:0x0093, B:27:0x003b, B:30:0x0044, B:33:0x0057), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0038  */
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
                        if (WifiUtil.INSTANCE.getMac() == null) {
                            return null;
                        }
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
                    Pdlog.m3273d(TAG, "getMapList: " + apiResponse);
                    if ((apiResponse == null ? Boxing.boxInt(apiResponse.getCode()) : null).intValue() != 0) {
                        return ((RobotMapRespWrap) apiResponse.getResponseData()).getItem();
                    }
                    return null;
                }
            }
            if (i != 0) {
            }
            apiResponse = (ApiResponse) obj;
            Pdlog.m3273d(TAG, "getMapList: " + apiResponse);
            if ((apiResponse == null ? Boxing.boxInt(apiResponse.getCode()) : null).intValue() != 0) {
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "getMapList() " + e.getMessage());
            isGetMapListError = true;
            return null;
        }
        mapUpdateManager$getMapList$3 = new MapUpdateManager$getMapList$3(this, continuation);
        Object obj2 = mapUpdateManager$getMapList$3.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$getMapList$3.label;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object downloadAll(List<RobotMapResp> list, CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        MapUpdateManager$downloadAll$1 mapUpdateManager$downloadAll$1;
        int i;
        if (continuation instanceof MapUpdateManager$downloadAll$1) {
            mapUpdateManager$downloadAll$1 = (MapUpdateManager$downloadAll$1) continuation;
            if ((mapUpdateManager$downloadAll$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$downloadAll$1.label -= Integer.MIN_VALUE;
                MapUpdateManager$downloadAll$1 mapUpdateManager$downloadAll$12 = mapUpdateManager$downloadAll$1;
                Object obj = mapUpdateManager$downloadAll$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$downloadAll$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (list != null) {
                        RobotMapResp robotMapResp = list.get(0);
                        MapUpdateManager mapUpdateManager = INSTANCE;
                        mapUpdateManager$downloadAll$12.L$0 = this;
                        mapUpdateManager$downloadAll$12.L$1 = list;
                        mapUpdateManager$downloadAll$12.L$2 = coroutineScope;
                        mapUpdateManager$downloadAll$12.L$3 = list;
                        mapUpdateManager$downloadAll$12.I$0 = 0;
                        mapUpdateManager$downloadAll$12.L$4 = robotMapResp;
                        mapUpdateManager$downloadAll$12.label = 1;
                        if (mapUpdateManager.download(0, robotMapResp, list, coroutineScope, mapUpdateManager$downloadAll$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i2 = mapUpdateManager$downloadAll$12.I$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$downloadAll$1 = new MapUpdateManager$downloadAll$1(this, continuation);
        MapUpdateManager$downloadAll$1 mapUpdateManager$downloadAll$122 = mapUpdateManager$downloadAll$1;
        Object obj2 = mapUpdateManager$downloadAll$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$downloadAll$122.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object download(final int i, RobotMapResp robotMapResp, final List<RobotMapResp> list, final CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        MapUpdateManager$download$1 mapUpdateManager$download$1;
        int i2;
        if (continuation instanceof MapUpdateManager$download$1) {
            mapUpdateManager$download$1 = (MapUpdateManager$download$1) continuation;
            if ((mapUpdateManager$download$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$download$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$download$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = mapUpdateManager$download$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    OssService ossService2 = ossService;
                    String name = robotMapResp.getName();
                    String downloadPath = ossService2.getDownloadPath(name != null ? UtilsKt.encodeMapName(name) : null);
                    String realObjectKey = ossService.getRealObjectKey(robotMapResp.getUrl());
                    OssService ossService3 = ossService;
                    String url = robotMapResp.getUrl();
                    mapUpdateManager$download$1.L$0 = this;
                    mapUpdateManager$download$1.I$0 = i;
                    mapUpdateManager$download$1.L$1 = robotMapResp;
                    mapUpdateManager$download$1.L$2 = list;
                    mapUpdateManager$download$1.L$3 = coroutineScope;
                    mapUpdateManager$download$1.L$4 = downloadPath;
                    mapUpdateManager$download$1.L$5 = realObjectKey;
                    mapUpdateManager$download$1.label = 1;
                    if (ossService3.asyncDownloadFile(realObjectKey, downloadPath, url, mapUpdateManager$download$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    coroutineScope = (CoroutineScope) mapUpdateManager$download$1.L$3;
                    list = (List) mapUpdateManager$download$1.L$2;
                    i = mapUpdateManager$download$1.I$0;
                    ResultKt.throwOnFailure(obj);
                }
                ossService.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                        invoke2(str, str2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: MapUpdateManager.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$2$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$2$1 */
                    /* loaded from: classes4.dex */
                    public static final class C40771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ String $progress;
                        final /* synthetic */ String $url;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4659p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40771(String str, String str2, Continuation continuation) {
                            super(2, continuation);
                            this.$url = str;
                            this.$progress = str2;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40771 c40771 = new C40771(this.$url, this.$progress, completion);
                            c40771.f4659p$ = (CoroutineScope) obj;
                            return c40771;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.f4659p$;
                            Function3<String, String, String, Unit> updateProgressListener = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                            if (updateProgressListener != null) {
                                updateProgressListener.invoke(this.$url, this.$progress, "TYPE_DOWNLOAD");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String url2, String progress) {
                        Intrinsics.checkParameterIsNotNull(url2, "url");
                        Intrinsics.checkParameterIsNotNull(progress, "progress");
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40771(url2, progress, null), 2, null);
                    }
                });
                ossService.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3
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
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$1 */
                    /* loaded from: classes4.dex */
                    public static final class C40781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ String $it;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4660p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40781(String str, Continuation continuation) {
                            super(2, continuation);
                            this.$it = str;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40781 c40781 = new C40781(this.$it, completion);
                            c40781.f4660p$ = (CoroutineScope) obj;
                            return c40781;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.f4660p$;
                            Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                            if (onSuccessListener != null) {
                                onSuccessListener.invoke(this.$it, "TYPE_DOWNLOAD");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it) {
                        String str;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40781(it, null), 2, null);
                        if (i < list.size() && i != list.size() - 1) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C40792(i + 1, null), 3, null);
                        }
                        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                        str = MapUpdateManager.TAG;
                        Thread currentThread = Thread.currentThread();
                        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                        Pdlog.m3274e(str, currentThread.getName(), Integer.valueOf(i), Integer.valueOf(list.size()));
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: MapUpdateManager.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$2", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {121}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$2 */
                    /* loaded from: classes4.dex */
                    public static final class C40792 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                        /* renamed from: $i */
                        final /* synthetic */ int f4661$i;
                        Object L$0;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4662p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40792(int i, Continuation continuation) {
                            super(2, continuation);
                            this.f4661$i = i;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40792 c40792 = new C40792(this.f4661$i, completion);
                            c40792.f4662p$ = (CoroutineScope) obj;
                            return c40792;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40792) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                CoroutineScope coroutineScope = this.f4662p$;
                                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                                int i2 = this.f4661$i;
                                RobotMapResp robotMapResp = (RobotMapResp) list.get(this.f4661$i);
                                List<RobotMapResp> list = list;
                                CoroutineScope coroutineScope2 = coroutineScope;
                                this.L$0 = coroutineScope;
                                this.label = 1;
                                if (mapUpdateManager.download(i2, robotMapResp, list, coroutineScope2, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                });
                ossService.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4
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
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$1 */
                    /* loaded from: classes4.dex */
                    public static final class C40801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ String $url;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4663p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40801(String str, Continuation continuation) {
                            super(2, continuation);
                            this.$url = str;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40801 c40801 = new C40801(this.$url, completion);
                            c40801.f4663p$ = (CoroutineScope) obj;
                            return c40801;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.f4663p$;
                            Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                            if (onErrorListener != null) {
                                onErrorListener.invoke(this.$url, UpdateErrorSealed.OSS_SERVER_ERROR, "TYPE_DOWNLOAD");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String url2, String str) {
                        String str2;
                        Intrinsics.checkParameterIsNotNull(url2, "url");
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40801(url2, null), 2, null);
                        if (i < list.size() && i != list.size() - 1) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C40812(i + 1, null), 3, null);
                        }
                        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                        str2 = MapUpdateManager.TAG;
                        Pdlog.m3274e(str2, "onErrorListener", Integer.valueOf(i), str);
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: MapUpdateManager.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$2", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {134}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$2 */
                    /* loaded from: classes4.dex */
                    public static final class C40812 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                        /* renamed from: $i */
                        final /* synthetic */ int f4664$i;
                        Object L$0;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4665p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40812(int i, Continuation continuation) {
                            super(2, continuation);
                            this.f4664$i = i;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40812 c40812 = new C40812(this.f4664$i, completion);
                            c40812.f4665p$ = (CoroutineScope) obj;
                            return c40812;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40812) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                CoroutineScope coroutineScope = this.f4665p$;
                                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                                int i2 = this.f4664$i;
                                RobotMapResp robotMapResp = (RobotMapResp) list.get(this.f4664$i);
                                List<RobotMapResp> list = list;
                                CoroutineScope coroutineScope2 = coroutineScope;
                                this.L$0 = coroutineScope;
                                this.label = 1;
                                if (mapUpdateManager.download(i2, robotMapResp, list, coroutineScope2, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                });
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$download$1 = new MapUpdateManager$download$1(this, continuation);
        Object obj2 = mapUpdateManager$download$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = mapUpdateManager$download$1.label;
        if (i2 != 0) {
        }
        ossService.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$2$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$2$1 */
            /* loaded from: classes4.dex */
            public static final class C40771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $progress;
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4659p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40771(String str, String str2, Continuation continuation) {
                    super(2, continuation);
                    this.$url = str;
                    this.$progress = str2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40771 c40771 = new C40771(this.$url, this.$progress, completion);
                    c40771.f4659p$ = (CoroutineScope) obj;
                    return c40771;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4659p$;
                    Function3<String, String, String, Unit> updateProgressListener = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                    if (updateProgressListener != null) {
                        updateProgressListener.invoke(this.$url, this.$progress, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url2, String progress) {
                Intrinsics.checkParameterIsNotNull(url2, "url");
                Intrinsics.checkParameterIsNotNull(progress, "progress");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40771(url2, progress, null), 2, null);
            }
        });
        ossService.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3
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
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$1 */
            /* loaded from: classes4.dex */
            public static final class C40781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4660p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40781(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$it = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40781 c40781 = new C40781(this.$it, completion);
                    c40781.f4660p$ = (CoroutineScope) obj;
                    return c40781;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4660p$;
                    Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                    if (onSuccessListener != null) {
                        onSuccessListener.invoke(this.$it, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40781(it, null), 2, null);
                if (i < list.size() && i != list.size() - 1) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C40792(i + 1, null), 3, null);
                }
                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                str = MapUpdateManager.TAG;
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                Pdlog.m3274e(str, currentThread.getName(), Integer.valueOf(i), Integer.valueOf(list.size()));
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$2", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {121}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$3$2 */
            /* loaded from: classes4.dex */
            public static final class C40792 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $i */
                final /* synthetic */ int f4661$i;
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4662p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40792(int i, Continuation continuation) {
                    super(2, continuation);
                    this.f4661$i = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40792 c40792 = new C40792(this.f4661$i, completion);
                    c40792.f4662p$ = (CoroutineScope) obj;
                    return c40792;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40792) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f4662p$;
                        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                        int i2 = this.f4661$i;
                        RobotMapResp robotMapResp = (RobotMapResp) list.get(this.f4661$i);
                        List<RobotMapResp> list = list;
                        CoroutineScope coroutineScope2 = coroutineScope;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (mapUpdateManager.download(i2, robotMapResp, list, coroutineScope2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        });
        ossService.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4
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
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$1 */
            /* loaded from: classes4.dex */
            public static final class C40801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4663p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40801(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$url = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40801 c40801 = new C40801(this.$url, completion);
                    c40801.f4663p$ = (CoroutineScope) obj;
                    return c40801;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4663p$;
                    Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.invoke(this.$url, UpdateErrorSealed.OSS_SERVER_ERROR, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url2, String str) {
                String str2;
                Intrinsics.checkParameterIsNotNull(url2, "url");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40801(url2, null), 2, null);
                if (i < list.size() && i != list.size() - 1) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C40812(i + 1, null), 3, null);
                }
                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                str2 = MapUpdateManager.TAG;
                Pdlog.m3274e(str2, "onErrorListener", Integer.valueOf(i), str);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$2", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {134}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$download$4$2 */
            /* loaded from: classes4.dex */
            public static final class C40812 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $i */
                final /* synthetic */ int f4664$i;
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4665p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40812(int i, Continuation continuation) {
                    super(2, continuation);
                    this.f4664$i = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40812 c40812 = new C40812(this.f4664$i, completion);
                    c40812.f4665p$ = (CoroutineScope) obj;
                    return c40812;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40812) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f4665p$;
                        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                        int i2 = this.f4664$i;
                        RobotMapResp robotMapResp = (RobotMapResp) list.get(this.f4664$i);
                        List<RobotMapResp> list = list;
                        CoroutineScope coroutineScope2 = coroutineScope;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (mapUpdateManager.download(i2, robotMapResp, list, coroutineScope2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object downloadOne(final RobotMapResp robotMapResp, Continuation<? super Unit> continuation) {
        MapUpdateManager$downloadOne$1 mapUpdateManager$downloadOne$1;
        int i;
        if (continuation instanceof MapUpdateManager$downloadOne$1) {
            mapUpdateManager$downloadOne$1 = (MapUpdateManager$downloadOne$1) continuation;
            if ((mapUpdateManager$downloadOne$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$downloadOne$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$downloadOne$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$downloadOne$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    OssService ossService2 = ossService;
                    String name = robotMapResp.getName();
                    String downloadPath = ossService2.getDownloadPath(name != null ? UtilsKt.encodeMapName(name) : null);
                    String realObjectKey = ossService.getRealObjectKey(robotMapResp.getUrl());
                    Pdlog.m3273d(TAG, "downloadOne data: " + robotMapResp + ",realObjectKey:" + realObjectKey + ",downloadPath:" + downloadPath);
                    OssService ossService3 = ossService;
                    String url = robotMapResp.getUrl();
                    mapUpdateManager$downloadOne$1.L$0 = this;
                    mapUpdateManager$downloadOne$1.L$1 = robotMapResp;
                    mapUpdateManager$downloadOne$1.L$2 = downloadPath;
                    mapUpdateManager$downloadOne$1.L$3 = realObjectKey;
                    mapUpdateManager$downloadOne$1.label = 1;
                    if (ossService3.asyncDownloadFile(realObjectKey, downloadPath, url, mapUpdateManager$downloadOne$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    robotMapResp = (RobotMapResp) mapUpdateManager$downloadOne$1.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                ossService.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                        invoke2(str, str2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: MapUpdateManager.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$2$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$2$1 */
                    /* loaded from: classes4.dex */
                    public static final class C40821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ String $progress;
                        final /* synthetic */ String $url;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4666p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40821(String str, String str2, Continuation continuation) {
                            super(2, continuation);
                            this.$progress = str;
                            this.$url = str2;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40821 c40821 = new C40821(this.$progress, this.$url, completion);
                            c40821.f4666p$ = (CoroutineScope) obj;
                            return c40821;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            String str;
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.f4666p$;
                            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                            str = MapUpdateManager.TAG;
                            Pdlog.m3273d(str, "downloadOne updateProgressListener: progress:" + this.$progress + " , url:" + this.$url);
                            Function3<String, String, String, Unit> updateProgressListener = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                            if (updateProgressListener != null) {
                                updateProgressListener.invoke(this.$url, this.$progress, "TYPE_DOWNLOAD");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String url2, String progress) {
                        Intrinsics.checkParameterIsNotNull(url2, "url");
                        Intrinsics.checkParameterIsNotNull(progress, "progress");
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40821(progress, url2, null), 2, null);
                    }
                });
                ossService.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$3
                    /* JADX INFO: Access modifiers changed from: package-private */
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
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$3$1", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {156}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$3$1 */
                    /* loaded from: classes4.dex */
                    public static final class C40831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ String $it;
                        Object L$0;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4667p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40831(String str, Continuation continuation) {
                            super(2, continuation);
                            this.$it = str;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40831 c40831 = new C40831(this.$it, completion);
                            c40831.f4667p$ = (CoroutineScope) obj;
                            return c40831;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            String str;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                CoroutineScope coroutineScope = this.f4667p$;
                                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                                str = MapUpdateManager.TAG;
                                Pdlog.m3273d(str, "downloadOne onSuccessListener url:" + this.$it);
                                MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
                                StringBuilder sb = new StringBuilder();
                                String name = RobotMapResp.this.getName();
                                sb.append(name != null ? UtilsKt.encodeMapName(name) : null);
                                sb.append(".pdmap");
                                String sb2 = sb.toString();
                                Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager.downloadOne.3.1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                        invoke(bool.booleanValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(boolean z) {
                                        String str2;
                                        MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
                                        str2 = MapUpdateManager.TAG;
                                        Pdlog.m3273d(str2, "downloadOne onSuccessListener moveMapFileAndDelTemp url:" + C40831.this.$it + ",b:" + z);
                                        if (z) {
                                            Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                                            if (onSuccessListener != null) {
                                                onSuccessListener.invoke(C40831.this.$it, "TYPE_DOWNLOAD");
                                                return;
                                            }
                                            return;
                                        }
                                        Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                                        if (onErrorListener != null) {
                                            onErrorListener.invoke(C40831.this.$it, UpdateErrorSealed.OSS_SERVER_ERROR, "TYPE_DOWNLOAD");
                                        }
                                    }
                                };
                                this.L$0 = coroutineScope;
                                this.label = 1;
                                if (mapUpdateManager2.moveMapFileAndDelTemp(sb2, function1, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40831(it, null), 2, null);
                    }
                });
                ossService.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$4
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                        invoke2(str, str2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: MapUpdateManager.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$4$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$4$1 */
                    /* loaded from: classes4.dex */
                    public static final class C40841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ String $url;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4668p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C40841(String str, Continuation continuation) {
                            super(2, continuation);
                            this.$url = str;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C40841 c40841 = new C40841(this.$url, completion);
                            c40841.f4668p$ = (CoroutineScope) obj;
                            return c40841;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C40841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            String str;
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.f4668p$;
                            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                            str = MapUpdateManager.TAG;
                            Pdlog.m3273d(str, "downloadOne onErrorListener url:" + this.$url);
                            Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                            if (onErrorListener != null) {
                                onErrorListener.invoke(this.$url, UpdateErrorSealed.OSS_SERVER_ERROR, "TYPE_DOWNLOAD");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String url2, String str) {
                        Intrinsics.checkParameterIsNotNull(url2, "url");
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40841(url2, null), 2, null);
                    }
                });
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$downloadOne$1 = new MapUpdateManager$downloadOne$1(this, continuation);
        Object obj2 = mapUpdateManager$downloadOne$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$downloadOne$1.label;
        if (i != 0) {
        }
        ossService.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$2$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$2$1 */
            /* loaded from: classes4.dex */
            public static final class C40821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $progress;
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4666p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40821(String str, String str2, Continuation continuation) {
                    super(2, continuation);
                    this.$progress = str;
                    this.$url = str2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40821 c40821 = new C40821(this.$progress, this.$url, completion);
                    c40821.f4666p$ = (CoroutineScope) obj;
                    return c40821;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4666p$;
                    MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                    str = MapUpdateManager.TAG;
                    Pdlog.m3273d(str, "downloadOne updateProgressListener: progress:" + this.$progress + " , url:" + this.$url);
                    Function3<String, String, String, Unit> updateProgressListener = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                    if (updateProgressListener != null) {
                        updateProgressListener.invoke(this.$url, this.$progress, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url2, String progress) {
                Intrinsics.checkParameterIsNotNull(url2, "url");
                Intrinsics.checkParameterIsNotNull(progress, "progress");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40821(progress, url2, null), 2, null);
            }
        });
        ossService.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$3
            /* JADX INFO: Access modifiers changed from: package-private */
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
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$3$1", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {156}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$3$1 */
            /* loaded from: classes4.dex */
            public static final class C40831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $it;
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4667p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40831(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$it = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40831 c40831 = new C40831(this.$it, completion);
                    c40831.f4667p$ = (CoroutineScope) obj;
                    return c40831;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f4667p$;
                        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                        str = MapUpdateManager.TAG;
                        Pdlog.m3273d(str, "downloadOne onSuccessListener url:" + this.$it);
                        MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
                        StringBuilder sb = new StringBuilder();
                        String name = RobotMapResp.this.getName();
                        sb.append(name != null ? UtilsKt.encodeMapName(name) : null);
                        sb.append(".pdmap");
                        String sb2 = sb.toString();
                        Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager.downloadOne.3.1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z) {
                                String str2;
                                MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
                                str2 = MapUpdateManager.TAG;
                                Pdlog.m3273d(str2, "downloadOne onSuccessListener moveMapFileAndDelTemp url:" + C40831.this.$it + ",b:" + z);
                                if (z) {
                                    Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                                    if (onSuccessListener != null) {
                                        onSuccessListener.invoke(C40831.this.$it, "TYPE_DOWNLOAD");
                                        return;
                                    }
                                    return;
                                }
                                Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                                if (onErrorListener != null) {
                                    onErrorListener.invoke(C40831.this.$it, UpdateErrorSealed.OSS_SERVER_ERROR, "TYPE_DOWNLOAD");
                                }
                            }
                        };
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (mapUpdateManager2.moveMapFileAndDelTemp(sb2, function1, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40831(it, null), 2, null);
            }
        });
        ossService.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$4$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$downloadOne$4$1 */
            /* loaded from: classes4.dex */
            public static final class C40841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $url;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4668p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40841(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$url = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40841 c40841 = new C40841(this.$url, completion);
                    c40841.f4668p$ = (CoroutineScope) obj;
                    return c40841;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4668p$;
                    MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                    str = MapUpdateManager.TAG;
                    Pdlog.m3273d(str, "downloadOne onErrorListener url:" + this.$url);
                    Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.invoke(this.$url, UpdateErrorSealed.OSS_SERVER_ERROR, "TYPE_DOWNLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url2, String str) {
                Intrinsics.checkParameterIsNotNull(url2, "url");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40841(url2, null), 2, null);
            }
        });
        return Unit.INSTANCE;
    }

    public final Object upload(MapInfo mapInfo, Continuation<? super Unit> continuation) {
        String mapName = mapInfo.getMapName();
        if (mapName == null || mapName.length() == 0) {
            Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new MapUpdateManager$upload$2(mapInfo, null), continuation);
            return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
        }
        String encodeMapName = UtilsKt.encodeMapName(mapInfo.getMapName());
        String str = MAP_FILE_PATH + encodeMapName + ".pdmap";
        if (!FileUtils.isFileExists(str)) {
            Object withContext2 = BuildersKt.withContext(Dispatchers.getMain(), new MapUpdateManager$upload$3(mapInfo, null), continuation);
            return withContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext2 : Unit.INSTANCE;
        }
        if (!checkMapRule(mapInfo.getMapName())) {
            Object withContext3 = BuildersKt.withContext(Dispatchers.getMain(), new MapUpdateManager$upload$4(mapInfo, null), continuation);
            return withContext3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext3 : Unit.INSTANCE;
        }
        Pdlog.m3274e(TAG, "upload mapPath: " + str + ",decodeMapName:" + encodeMapName);
        Object upload = upload(str, mapInfo.getMapName(), mapInfo.getMapVersion(), continuation);
        return upload == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? upload : Unit.INSTANCE;
    }

    public final Object upload(String str, final String str2, final int i, Continuation<? super Unit> continuation) {
        if (!new File(str).exists()) {
            Pdlog.m3274e(TAG, str + " 不存在");
        }
        final String encryptMD5File2String = EncryptUtils.encryptMD5File2String(str);
        final String str3 = "pudu_cloud_platform/production/map/" + System.currentTimeMillis() + ".pdmap";
        ossService.setUpdateProgressListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$upload$6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str4, String str5) {
                invoke2(str4, str5);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String progress) {
                String str4;
                Intrinsics.checkParameterIsNotNull(url, "url");
                Intrinsics.checkParameterIsNotNull(progress, "progress");
                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                str4 = MapUpdateManager.TAG;
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                Pdlog.m3273d(str4, "upload =", currentThread.getName(), str3, progress);
                Function3<String, String, String, Unit> updateProgressListener2 = MapUpdateManager.INSTANCE.getUpdateProgressListener();
                if (updateProgressListener2 != null) {
                    updateProgressListener2.invoke(url, progress, "TYPE_UPLOAD");
                }
            }
        });
        ossService.setOnSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$upload$7
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str4) {
                invoke2(str4);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$upload$7$1", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 0}, m3972l = {DimensionsKt.HDPI}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "pointInfo", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ}, m3975s = {"L$0", "L$1", "L$2"})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$upload$7$1 */
            /* loaded from: classes4.dex */
            public static final class C40871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $it;
                Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4681p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40871(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$it = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40871 c40871 = new C40871(this.$it, completion);
                    c40871.f4681p$ = (CoroutineScope) obj;
                    return c40871;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    String str2;
                    String str3;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f4681p$;
                        List<Destination> specifyMapDestinations = RobotMapManager.INSTANCE.getSpecifyMapDestinations(str2);
                        if (specifyMapDestinations != null) {
                            ArrayList arrayList = new ArrayList();
                            Iterator<T> it = specifyMapDestinations.iterator();
                            while (it.hasNext()) {
                                CollectionsKt.addAll(arrayList, CollectionsKt.arrayListOf(((Destination) it.next()).getName()));
                            }
                            str = ListExtKt.toCompactString((List) arrayList);
                        } else {
                            str = null;
                        }
                        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                        str2 = MapUpdateManager.TAG;
                        Pdlog.m3273d(str2, "upload onSuccessListener pointInfo:" + str + ' ');
                        RobotMapReqV2 robotMapReqV2 = new RobotMapReqV2(null, null, 0, null, null, 0, null, 127, null);
                        robotMapReqV2.setUrl(this.$it);
                        String md5 = encryptMD5File2String;
                        Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
                        robotMapReqV2.setMd5(md5);
                        robotMapReqV2.setName(str2);
                        robotMapReqV2.setLv(i);
                        String mac = WifiUtil.INSTANCE.getMac();
                        if (mac == null) {
                            mac = "";
                        }
                        robotMapReqV2.setMac(mac);
                        robotMapReqV2.set_use(Intrinsics.areEqual(RobotMapManager.INSTANCE.getDefaultPdmap(), str2) ? 1 : 0);
                        robotMapReqV2.setPoint_info(str != null ? str : "");
                        MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
                        str3 = MapUpdateManager.TAG;
                        Pdlog.m3273d(str3, "upload req: " + robotMapReqV2);
                        MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
                        this.L$0 = coroutineScope;
                        this.L$1 = str;
                        this.L$2 = robotMapReqV2;
                        this.label = 1;
                        if (mapUpdateManager3.uploadServiceV2(robotMapReqV2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                String str4;
                Intrinsics.checkParameterIsNotNull(it, "it");
                MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                str4 = MapUpdateManager.TAG;
                Pdlog.m3273d(str4, "upload onSuccessListener it:" + it);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C40871(it, null), 2, null);
            }
        });
        ossService.setOnErrorListener(new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$upload$8
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str4, String str5) {
                invoke2(str4, str5);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapUpdateManager.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$upload$8$1", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$upload$8$1 */
            /* loaded from: classes4.dex */
            public static final class C40881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $errorMsg;
                final /* synthetic */ String $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4682p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40881(String str, String str2, Continuation continuation) {
                    super(2, continuation);
                    this.$it = str;
                    this.$errorMsg = str2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40881 c40881 = new C40881(this.$it, this.$errorMsg, completion);
                    c40881.f4682p$ = (CoroutineScope) obj;
                    return c40881;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4682p$;
                    MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                    str = MapUpdateManager.TAG;
                    Pdlog.m3274e(str, "upload onErrorListener it:" + this.$it + ",errorMsg:" + this.$errorMsg);
                    Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.invoke(this.$it, UpdateErrorSealed.OSS_SERVER_ERROR, "TYPE_UPLOAD");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it, String str4) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C40881(it, str4, null), 2, null);
            }
        });
        Object asyncUploadLocalFile = ossService.asyncUploadLocalFile(str, str3, continuation);
        return asyncUploadLocalFile == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? asyncUploadLocalFile : Unit.INSTANCE;
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
                    Function1<? super List<RobotMapResp>, Unit> function1 = new Function1<List<RobotMapResp>, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$mapCompare$2
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
                            } else if (robotMapResp.getLv() == i) {
                                intRef2.element++;
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
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00dd, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00de, code lost:
    
        r10 = r18;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00dc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.pudutech.disinfect.baselib.network.response.MapUploadResponse, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object uploadServiceV2(RobotMapReqV2 robotMapReqV2, Continuation<? super Unit> continuation) {
        MapUpdateManager$uploadServiceV2$1 mapUpdateManager$uploadServiceV2$1;
        Object coroutine_suspended;
        int i;
        RobotMapReqV2 robotMapReqV22;
        MapUpdateManager mapUpdateManager;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        MainCoroutineDispatcher main;
        MapUpdateManager$uploadServiceV2$2 mapUpdateManager$uploadServiceV2$2;
        if (continuation instanceof MapUpdateManager$uploadServiceV2$1) {
            mapUpdateManager$uploadServiceV2$1 = (MapUpdateManager$uploadServiceV2$1) continuation;
            if ((mapUpdateManager$uploadServiceV2$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$uploadServiceV2$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$uploadServiceV2$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapUpdateManager$uploadServiceV2$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(TAG, "uploadServiceV2 req:" + robotMapReqV2);
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
                    mapUpdateManager$uploadServiceV2$1.L$0 = this;
                    mapUpdateManager$uploadServiceV2$1.L$1 = robotMapReqV2;
                    mapUpdateManager$uploadServiceV2$1.L$2 = objectRef3;
                    mapUpdateManager$uploadServiceV2$1.L$3 = objectRef3;
                    mapUpdateManager$uploadServiceV2$1.label = 1;
                    Object uploadMap = map.uploadMap(robotMapReqV2, mapUpdateManager$uploadServiceV2$1);
                    if (uploadMap == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mapUpdateManager = this;
                    robotMapReqV22 = robotMapReqV2;
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
                    objectRef = (Ref.ObjectRef) mapUpdateManager$uploadServiceV2$1.L$3;
                    objectRef2 = (Ref.ObjectRef) mapUpdateManager$uploadServiceV2$1.L$2;
                    robotMapReqV22 = (RobotMapReqV2) mapUpdateManager$uploadServiceV2$1.L$1;
                    mapUpdateManager = (MapUpdateManager) mapUpdateManager$uploadServiceV2$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e) {
                        e = e;
                        Pdlog.m3274e(TAG, "uploadServiceV2:" + e);
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapUpdateManager$uploadServiceV2$3(robotMapReqV22, null), 2, null);
                        return Unit.INSTANCE;
                    }
                }
                objectRef.element = (MapUploadResponse) obj;
                Pdlog.m3274e(TAG, "uploadServiceV2  response:" + ((MapUploadResponse) objectRef2.element));
                main = Dispatchers.getMain();
                mapUpdateManager$uploadServiceV2$2 = new MapUpdateManager$uploadServiceV2$2(objectRef2, robotMapReqV22, null);
                mapUpdateManager$uploadServiceV2$1.L$0 = mapUpdateManager;
                mapUpdateManager$uploadServiceV2$1.L$1 = robotMapReqV22;
                mapUpdateManager$uploadServiceV2$1.L$2 = objectRef2;
                mapUpdateManager$uploadServiceV2$1.label = 2;
                if (BuildersKt.withContext(main, mapUpdateManager$uploadServiceV2$2, mapUpdateManager$uploadServiceV2$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        mapUpdateManager$uploadServiceV2$1 = new MapUpdateManager$uploadServiceV2$1(this, continuation);
        Object obj2 = mapUpdateManager$uploadServiceV2$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapUpdateManager$uploadServiceV2$1.label;
        if (i != 0) {
        }
        objectRef.element = (MapUploadResponse) obj2;
        Pdlog.m3274e(TAG, "uploadServiceV2  response:" + ((MapUploadResponse) objectRef2.element));
        main = Dispatchers.getMain();
        mapUpdateManager$uploadServiceV2$2 = new MapUpdateManager$uploadServiceV2$2(objectRef2, robotMapReqV22, null);
        mapUpdateManager$uploadServiceV2$1.L$0 = mapUpdateManager;
        mapUpdateManager$uploadServiceV2$1.L$1 = robotMapReqV22;
        mapUpdateManager$uploadServiceV2$1.L$2 = objectRef2;
        mapUpdateManager$uploadServiceV2$1.label = 2;
        if (BuildersKt.withContext(main, mapUpdateManager$uploadServiceV2$2, mapUpdateManager$uploadServiceV2$1) == coroutine_suspended) {
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

    public final Object isShouldUpdate(List<RobotMapResp> list, List<MapInfo> list2, Continuation<? super Boolean> continuation) {
        needUpdateData = (List) null;
        needUpdateData = compareLocalAndCloud(list, list2, true);
        Pdlog.m3273d(TAG, "isShouldUpdate: " + needUpdateData);
        List<RobotMapResp> list3 = needUpdateData;
        return Boxing.boxBoolean(!(list3 == null || list3.isEmpty()));
    }

    public final Object isShouldUpload(List<RobotMapResp> list, List<MapInfo> list2, Continuation<? super Boolean> continuation) {
        needUploadData = (List) null;
        needUploadData = compareLocalAndCloud(list, list2, false);
        Pdlog.m3273d(TAG, "isShouldUpload: " + needUploadData);
        List<RobotMapResp> list3 = needUploadData;
        return Boxing.boxBoolean(!(list3 == null || list3.isEmpty()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.lang.Object] */
    public final Object deleteMap(List<RobotMapResp> list, List<MapInfo> list2, Continuation<? super Unit> continuation) {
        ArrayList<RobotMapResp> arrayList;
        MapInfo mapInfo;
        MapInfo mapInfo2;
        Unit unit = null;
        if (list != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (Boxing.boxBoolean(((RobotMapResp) obj).getStatus() == 0).booleanValue()) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        Pdlog.json(TAG + ":cloudDeletes ", StringExtKt.toJson(arrayList));
        if (arrayList != null) {
            for (RobotMapResp robotMapResp : arrayList) {
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            mapInfo2 = 0;
                            break;
                        }
                        mapInfo2 = it.next();
                        if (Boxing.boxBoolean(Intrinsics.areEqual(((MapInfo) mapInfo2).getMapName(), robotMapResp.getName())).booleanValue()) {
                            break;
                        }
                    }
                    mapInfo = mapInfo2;
                } else {
                    mapInfo = null;
                }
                Pdlog.json(TAG + ":cloudDeletes: filter-- ", StringExtKt.toJson(mapInfo));
                if (mapInfo != null) {
                    list2.remove(mapInfo);
                    INSTANCE.deleteLocalMap(mapInfo.getMapName());
                    if (Intrinsics.areEqual(RobotMapManager.INSTANCE.getDefaultPdmap(), mapInfo.getMapName())) {
                        Pdlog.m3273d(TAG, "delete--defaultMap " + mapInfo + ".mapName");
                        RobotMapManager.INSTANCE.reloadMap();
                    }
                }
            }
            unit = Unit.INSTANCE;
        }
        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
    }

    public final List<MapInfo> getLocalMapList() {
        List<MapInfo> mapList = RobotMapManager.INSTANCE.getMapList();
        Pdlog.m3273d(TAG, "getLocalMapList =", mapList);
        return mapList;
    }

    private final List<RobotMapResp> compareLocalAndCloud(List<RobotMapResp> cloudData, List<MapInfo> localData, boolean isUpdate) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        if (isUpdate) {
            List<RobotMapResp> list = cloudData;
            if (list == null || list.isEmpty()) {
                return null;
            }
        } else {
            List<RobotMapResp> list2 = cloudData;
            if (list2 == null || list2.isEmpty()) {
                List<MapInfo> list3 = localData;
                if (!(list3 == null || list3.isEmpty())) {
                    for (MapInfo mapInfo : localData) {
                        String mapName = mapInfo.getMapName();
                        int mapVersion = mapInfo.getMapVersion();
                        String encryptMD5ToString = EncryptUtils.encryptMD5ToString(mapInfo.getMd5());
                        Intrinsics.checkExpressionValueIsNotNull(encryptMD5ToString, "EncryptUtils.encryptMD5ToString(it.md5)");
                        arrayList3.add(new RobotMapResp(mapName, "", mapVersion, encryptMD5ToString, false, null, INSTANCE.getMapFile(mapInfo.getMapName()), null, 0, 432, null));
                    }
                    return arrayList3;
                }
            }
        }
        if (isUpdate) {
            List<MapInfo> list4 = localData;
            if (list4 == null || list4.isEmpty()) {
                if (cloudData != null) {
                    for (RobotMapResp robotMapResp : cloudData) {
                        if (robotMapResp.getStatus() != 0) {
                            arrayList3.add(robotMapResp);
                        }
                    }
                }
                Pdlog.m3273d(TAG, "compareLocalAndCloud cloudDataNotDelete: " + arrayList3);
                return arrayList3;
            }
            if (cloudData != null) {
                for (RobotMapResp robotMapResp2 : cloudData) {
                    if (localData != null) {
                        ArrayList arrayList4 = new ArrayList();
                        for (Object obj : localData) {
                            if (Intrinsics.areEqual(robotMapResp2.getName(), ((MapInfo) obj).getMapName())) {
                                arrayList4.add(obj);
                            }
                        }
                        arrayList2 = arrayList4;
                    } else {
                        arrayList2 = null;
                    }
                    ArrayList arrayList5 = arrayList2;
                    if ((arrayList5 == null || arrayList5.isEmpty()) && robotMapResp2.getStatus() != 0) {
                        arrayList3.add(robotMapResp2);
                    } else if (robotMapResp2.getStatus() != 0) {
                        MapInfo mapInfo2 = (MapInfo) arrayList2.get(0);
                        if ((!Intrinsics.areEqual(robotMapResp2.getMd5(), EncryptUtils.encryptMD5ToString(mapInfo2.getMd5()))) && mapInfo2.getMapVersion() < robotMapResp2.getLv()) {
                            arrayList3.add(robotMapResp2);
                        }
                    }
                }
            }
        } else if (localData != null) {
            for (MapInfo mapInfo3 : localData) {
                if (cloudData != null) {
                    ArrayList arrayList6 = new ArrayList();
                    for (Object obj2 : cloudData) {
                        if (Intrinsics.areEqual(mapInfo3.getMapName(), ((RobotMapResp) obj2).getName())) {
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
                    arrayList3.add(new RobotMapResp(mapName2, "", mapVersion2, encryptMD5ToString2, false, null, INSTANCE.getMapFile(mapInfo3.getMapName()), null, 0, 432, null));
                } else {
                    RobotMapResp robotMapResp3 = (RobotMapResp) arrayList.get(0);
                    String encryptMD5ToString3 = EncryptUtils.encryptMD5ToString(mapInfo3.getMd5());
                    robotMapResp3.setLocalPath(INSTANCE.getMapFile(mapInfo3.getMapName()));
                    if ((!Intrinsics.areEqual(robotMapResp3.getMd5(), encryptMD5ToString3)) && robotMapResp3.getStatus() != 0 && mapInfo3.getMapVersion() > robotMapResp3.getLv()) {
                        String mapName3 = mapInfo3.getMapName();
                        int mapVersion3 = mapInfo3.getMapVersion();
                        String encryptMD5ToString4 = EncryptUtils.encryptMD5ToString(mapInfo3.getMd5());
                        Intrinsics.checkExpressionValueIsNotNull(encryptMD5ToString4, "EncryptUtils.encryptMD5ToString(mapInfo.md5)");
                        arrayList3.add(new RobotMapResp(mapName3, "", mapVersion3, encryptMD5ToString4, false, null, INSTANCE.getMapFile(mapInfo3.getMapName()), null, 0, 432, null));
                    }
                }
            }
        }
        return arrayList3;
    }

    public final Object syncMap(final CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Job launch$default;
        Pdlog.m3273d(TAG, "syncMap");
        if (!WifiUtil.INSTANCE.isNetworkAvailable(BusinessContext.INSTANCE.getContext())) {
            Pdlog.m3274e(TAG, "syncMap 网络未连接");
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapUpdateManager$syncMap$2(null), 2, null);
            return launch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? launch$default : Unit.INSTANCE;
        }
        uploadMapUse$default(this, null, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$syncMap$3
            /* JADX INFO: Access modifiers changed from: package-private */
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
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$syncMap$3$1", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {473}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$syncMap$3$1 */
            /* loaded from: classes4.dex */
            public static final class C40861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4677p$;

                C40861(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40861 c40861 = new C40861(completion);
                    c40861.f4677p$ = (CoroutineScope) obj;
                    return c40861;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f4677p$;
                        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
                        CoroutineScope coroutineScope2 = CoroutineScope.this;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (mapUpdateManager.startSyncMap(coroutineScope2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new C40861(null), 3, null);
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0028. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0196 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x017a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object startSyncMap(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        MapUpdateManager$startSyncMap$1 mapUpdateManager$startSyncMap$1;
        CoroutineScope coroutineScope2;
        MapUpdateManager mapUpdateManager;
        MapUpdateManager mapUpdateManager2;
        List<MapInfo> list;
        List<RobotMapResp> list2;
        CoroutineScope coroutineScope3;
        boolean booleanValue;
        MapUpdateManager mapUpdateManager3;
        CoroutineScope coroutineScope4;
        List<RobotMapResp> list3;
        List<MapInfo> list4;
        boolean z;
        boolean z2;
        if (continuation instanceof MapUpdateManager$startSyncMap$1) {
            mapUpdateManager$startSyncMap$1 = (MapUpdateManager$startSyncMap$1) continuation;
            if ((mapUpdateManager$startSyncMap$1.label & Integer.MIN_VALUE) != 0) {
                mapUpdateManager$startSyncMap$1.label -= Integer.MIN_VALUE;
                Object obj = mapUpdateManager$startSyncMap$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (mapUpdateManager$startSyncMap$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        mapUpdateManager$startSyncMap$1.L$0 = this;
                        coroutineScope2 = coroutineScope;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope2;
                        mapUpdateManager$startSyncMap$1.label = 1;
                        obj = getMapList(mapUpdateManager$startSyncMap$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mapUpdateManager = this;
                        List<RobotMapResp> list5 = (List) obj;
                        String str = TAG;
                        Object[] objArr = new Object[2];
                        StringBuilder sb = new StringBuilder();
                        sb.append("startSyncMap: ");
                        sb.append(list5 == null ? Boxing.boxInt(list5.size()) : null);
                        objArr[0] = sb.toString();
                        objArr[1] = Boxing.boxBoolean(isGetMapListError);
                        Pdlog.m3273d(str, objArr);
                        if (!isGetMapListError) {
                            isGetMapListError = false;
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapUpdateManager$startSyncMap$2(null), 2, null);
                            return Unit.INSTANCE;
                        }
                        List<MapInfo> localMapList = mapUpdateManager.getLocalMapList();
                        String str2 = TAG;
                        Object[] objArr2 = new Object[1];
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("startSyncMap localMapList: ");
                        sb2.append(localMapList != null ? Boxing.boxInt(localMapList.size()) : null);
                        objArr2[0] = sb2.toString();
                        Pdlog.m3273d(str2, objArr2);
                        mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope2;
                        mapUpdateManager$startSyncMap$1.L$2 = list5;
                        mapUpdateManager$startSyncMap$1.L$3 = localMapList;
                        mapUpdateManager$startSyncMap$1.label = 2;
                        Object isShouldUpdate = mapUpdateManager.isShouldUpdate(list5, localMapList, mapUpdateManager$startSyncMap$1);
                        if (isShouldUpdate == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mapUpdateManager2 = mapUpdateManager;
                        list = localMapList;
                        list2 = list5;
                        obj = isShouldUpdate;
                        coroutineScope3 = coroutineScope2;
                        booleanValue = ((Boolean) obj).booleanValue();
                        mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager2;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope3;
                        mapUpdateManager$startSyncMap$1.L$2 = list2;
                        mapUpdateManager$startSyncMap$1.L$3 = list;
                        mapUpdateManager$startSyncMap$1.Z$0 = booleanValue;
                        mapUpdateManager$startSyncMap$1.label = 3;
                        obj = mapUpdateManager2.isShouldUpload(list2, list, mapUpdateManager$startSyncMap$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        boolean booleanValue2 = ((Boolean) obj).booleanValue();
                        mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager2;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope3;
                        mapUpdateManager$startSyncMap$1.L$2 = list2;
                        mapUpdateManager$startSyncMap$1.L$3 = list;
                        mapUpdateManager$startSyncMap$1.Z$0 = booleanValue;
                        mapUpdateManager$startSyncMap$1.Z$1 = booleanValue2;
                        mapUpdateManager$startSyncMap$1.label = 4;
                        if (mapUpdateManager2.deleteMap(list2, list, mapUpdateManager$startSyncMap$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mapUpdateManager3 = mapUpdateManager2;
                        coroutineScope4 = coroutineScope3;
                        list3 = list2;
                        list4 = list;
                        z = booleanValue;
                        z2 = booleanValue2;
                        onSuccessListener = new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$startSyncMap$3
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(String str3, String str4) {
                                invoke2(str3, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String url, String operateType) {
                                String str3;
                                Intrinsics.checkParameterIsNotNull(url, "url");
                                Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                                MapUpdateManager mapUpdateManager4 = MapUpdateManager.INSTANCE;
                                str3 = MapUpdateManager.TAG;
                                Pdlog.m3274e(str3, "startSyncMap onSuccessListener", url);
                                List<RobotMapResp> needUpdateData2 = MapUpdateManager.INSTANCE.getNeedUpdateData();
                                if (needUpdateData2 != null) {
                                    for (RobotMapResp robotMapResp : needUpdateData2) {
                                        if (Intrinsics.areEqual(robotMapResp.getUrl(), url)) {
                                            robotMapResp.setState("1");
                                            if (!MapUpdateManager.INSTANCE.checkTempMapRule(robotMapResp)) {
                                                MapUpdateManager.INSTANCE.deleteFailureDownloadFile(robotMapResp);
                                            }
                                        }
                                    }
                                }
                                List<RobotMapResp> needUploadData2 = MapUpdateManager.INSTANCE.getNeedUploadData();
                                if (needUploadData2 != null) {
                                    for (RobotMapResp robotMapResp2 : needUploadData2) {
                                        if (Intrinsics.areEqual(robotMapResp2.getName(), url)) {
                                            robotMapResp2.setState("1");
                                        }
                                    }
                                }
                                MapUpdateManager.INSTANCE.asyncComplete();
                            }
                        };
                        onErrorListener = new Function3<String, UpdateErrorSealed, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$startSyncMap$4
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(String str3, UpdateErrorSealed updateErrorSealed, String str4) {
                                invoke2(str3, updateErrorSealed, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String url, UpdateErrorSealed errorReason, String operateType) {
                                String str3;
                                Intrinsics.checkParameterIsNotNull(url, "url");
                                Intrinsics.checkParameterIsNotNull(errorReason, "errorReason");
                                Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                                MapUpdateManager mapUpdateManager4 = MapUpdateManager.INSTANCE;
                                str3 = MapUpdateManager.TAG;
                                Pdlog.m3274e(str3, "startSyncMap onErrorListener", url);
                                List<RobotMapResp> needUpdateData2 = MapUpdateManager.INSTANCE.getNeedUpdateData();
                                if (needUpdateData2 != null) {
                                    for (RobotMapResp robotMapResp : needUpdateData2) {
                                        if (Intrinsics.areEqual(robotMapResp.getUrl(), url)) {
                                            robotMapResp.setState("-1");
                                            FileUtils.delete(MapUpdateManager.INSTANCE.getTempMapName(robotMapResp.getName()));
                                        }
                                    }
                                }
                                List<RobotMapResp> needUploadData2 = MapUpdateManager.INSTANCE.getNeedUploadData();
                                if (needUploadData2 != null) {
                                    for (RobotMapResp robotMapResp2 : needUploadData2) {
                                        if (Intrinsics.areEqual(robotMapResp2.getName(), url)) {
                                            robotMapResp2.setState("-1");
                                        }
                                    }
                                }
                                MapUpdateManager.INSTANCE.asyncComplete();
                            }
                        };
                        if (z2) {
                            if (!z) {
                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapUpdateManager$startSyncMap$5(null), 2, null);
                            }
                            List<RobotMapResp> list6 = needUploadData;
                            mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager3;
                            mapUpdateManager$startSyncMap$1.L$1 = coroutineScope4;
                            mapUpdateManager$startSyncMap$1.L$2 = list3;
                            mapUpdateManager$startSyncMap$1.L$3 = list4;
                            mapUpdateManager$startSyncMap$1.Z$0 = z;
                            mapUpdateManager$startSyncMap$1.Z$1 = z2;
                            mapUpdateManager$startSyncMap$1.label = 5;
                            if (mapUpdateManager3.uploadAll(list6, mapUpdateManager$startSyncMap$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (!z) {
                                return Unit.INSTANCE;
                            }
                        }
                        Pdlog.m3274e(TAG, "startSyncMap " + z + needUpdateData + " upload=" + z2 + needUploadData + ' ');
                        if (!z) {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapUpdateManager$startSyncMap$6(null), 2, null);
                            return Unit.INSTANCE;
                        }
                        List<RobotMapResp> list7 = needUpdateData;
                        mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager3;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope4;
                        mapUpdateManager$startSyncMap$1.L$2 = list3;
                        mapUpdateManager$startSyncMap$1.L$3 = list4;
                        mapUpdateManager$startSyncMap$1.Z$0 = z;
                        mapUpdateManager$startSyncMap$1.Z$1 = z2;
                        mapUpdateManager$startSyncMap$1.label = 6;
                        if (mapUpdateManager3.downloadAll(list7, coroutineScope4, mapUpdateManager$startSyncMap$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        coroutineScope2 = (CoroutineScope) mapUpdateManager$startSyncMap$1.L$1;
                        mapUpdateManager = (MapUpdateManager) mapUpdateManager$startSyncMap$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        List<RobotMapResp> list52 = (List) obj;
                        String str3 = TAG;
                        Object[] objArr3 = new Object[2];
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("startSyncMap: ");
                        sb3.append(list52 == null ? Boxing.boxInt(list52.size()) : null);
                        objArr3[0] = sb3.toString();
                        objArr3[1] = Boxing.boxBoolean(isGetMapListError);
                        Pdlog.m3273d(str3, objArr3);
                        if (!isGetMapListError) {
                        }
                        break;
                    case 2:
                        List<MapInfo> list8 = (List) mapUpdateManager$startSyncMap$1.L$3;
                        List<RobotMapResp> list9 = (List) mapUpdateManager$startSyncMap$1.L$2;
                        CoroutineScope coroutineScope5 = (CoroutineScope) mapUpdateManager$startSyncMap$1.L$1;
                        MapUpdateManager mapUpdateManager4 = (MapUpdateManager) mapUpdateManager$startSyncMap$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        mapUpdateManager2 = mapUpdateManager4;
                        coroutineScope3 = coroutineScope5;
                        list2 = list9;
                        list = list8;
                        booleanValue = ((Boolean) obj).booleanValue();
                        mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager2;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope3;
                        mapUpdateManager$startSyncMap$1.L$2 = list2;
                        mapUpdateManager$startSyncMap$1.L$3 = list;
                        mapUpdateManager$startSyncMap$1.Z$0 = booleanValue;
                        mapUpdateManager$startSyncMap$1.label = 3;
                        obj = mapUpdateManager2.isShouldUpload(list2, list, mapUpdateManager$startSyncMap$1);
                        if (obj == coroutine_suspended) {
                        }
                        boolean booleanValue22 = ((Boolean) obj).booleanValue();
                        mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager2;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope3;
                        mapUpdateManager$startSyncMap$1.L$2 = list2;
                        mapUpdateManager$startSyncMap$1.L$3 = list;
                        mapUpdateManager$startSyncMap$1.Z$0 = booleanValue;
                        mapUpdateManager$startSyncMap$1.Z$1 = booleanValue22;
                        mapUpdateManager$startSyncMap$1.label = 4;
                        if (mapUpdateManager2.deleteMap(list2, list, mapUpdateManager$startSyncMap$1) == coroutine_suspended) {
                        }
                        break;
                    case 3:
                        booleanValue = mapUpdateManager$startSyncMap$1.Z$0;
                        list = (List) mapUpdateManager$startSyncMap$1.L$3;
                        list2 = (List) mapUpdateManager$startSyncMap$1.L$2;
                        coroutineScope3 = (CoroutineScope) mapUpdateManager$startSyncMap$1.L$1;
                        mapUpdateManager2 = (MapUpdateManager) mapUpdateManager$startSyncMap$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        boolean booleanValue222 = ((Boolean) obj).booleanValue();
                        mapUpdateManager$startSyncMap$1.L$0 = mapUpdateManager2;
                        mapUpdateManager$startSyncMap$1.L$1 = coroutineScope3;
                        mapUpdateManager$startSyncMap$1.L$2 = list2;
                        mapUpdateManager$startSyncMap$1.L$3 = list;
                        mapUpdateManager$startSyncMap$1.Z$0 = booleanValue;
                        mapUpdateManager$startSyncMap$1.Z$1 = booleanValue222;
                        mapUpdateManager$startSyncMap$1.label = 4;
                        if (mapUpdateManager2.deleteMap(list2, list, mapUpdateManager$startSyncMap$1) == coroutine_suspended) {
                        }
                        break;
                    case 4:
                        z2 = mapUpdateManager$startSyncMap$1.Z$1;
                        z = mapUpdateManager$startSyncMap$1.Z$0;
                        list4 = (List) mapUpdateManager$startSyncMap$1.L$3;
                        list3 = (List) mapUpdateManager$startSyncMap$1.L$2;
                        coroutineScope4 = (CoroutineScope) mapUpdateManager$startSyncMap$1.L$1;
                        mapUpdateManager3 = (MapUpdateManager) mapUpdateManager$startSyncMap$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        onSuccessListener = new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$startSyncMap$3
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(String str32, String str4) {
                                invoke2(str32, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String url, String operateType) {
                                String str32;
                                Intrinsics.checkParameterIsNotNull(url, "url");
                                Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                                MapUpdateManager mapUpdateManager42 = MapUpdateManager.INSTANCE;
                                str32 = MapUpdateManager.TAG;
                                Pdlog.m3274e(str32, "startSyncMap onSuccessListener", url);
                                List<RobotMapResp> needUpdateData2 = MapUpdateManager.INSTANCE.getNeedUpdateData();
                                if (needUpdateData2 != null) {
                                    for (RobotMapResp robotMapResp : needUpdateData2) {
                                        if (Intrinsics.areEqual(robotMapResp.getUrl(), url)) {
                                            robotMapResp.setState("1");
                                            if (!MapUpdateManager.INSTANCE.checkTempMapRule(robotMapResp)) {
                                                MapUpdateManager.INSTANCE.deleteFailureDownloadFile(robotMapResp);
                                            }
                                        }
                                    }
                                }
                                List<RobotMapResp> needUploadData2 = MapUpdateManager.INSTANCE.getNeedUploadData();
                                if (needUploadData2 != null) {
                                    for (RobotMapResp robotMapResp2 : needUploadData2) {
                                        if (Intrinsics.areEqual(robotMapResp2.getName(), url)) {
                                            robotMapResp2.setState("1");
                                        }
                                    }
                                }
                                MapUpdateManager.INSTANCE.asyncComplete();
                            }
                        };
                        onErrorListener = new Function3<String, UpdateErrorSealed, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$startSyncMap$4
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(String str32, UpdateErrorSealed updateErrorSealed, String str4) {
                                invoke2(str32, updateErrorSealed, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String url, UpdateErrorSealed errorReason, String operateType) {
                                String str32;
                                Intrinsics.checkParameterIsNotNull(url, "url");
                                Intrinsics.checkParameterIsNotNull(errorReason, "errorReason");
                                Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                                MapUpdateManager mapUpdateManager42 = MapUpdateManager.INSTANCE;
                                str32 = MapUpdateManager.TAG;
                                Pdlog.m3274e(str32, "startSyncMap onErrorListener", url);
                                List<RobotMapResp> needUpdateData2 = MapUpdateManager.INSTANCE.getNeedUpdateData();
                                if (needUpdateData2 != null) {
                                    for (RobotMapResp robotMapResp : needUpdateData2) {
                                        if (Intrinsics.areEqual(robotMapResp.getUrl(), url)) {
                                            robotMapResp.setState("-1");
                                            FileUtils.delete(MapUpdateManager.INSTANCE.getTempMapName(robotMapResp.getName()));
                                        }
                                    }
                                }
                                List<RobotMapResp> needUploadData2 = MapUpdateManager.INSTANCE.getNeedUploadData();
                                if (needUploadData2 != null) {
                                    for (RobotMapResp robotMapResp2 : needUploadData2) {
                                        if (Intrinsics.areEqual(robotMapResp2.getName(), url)) {
                                            robotMapResp2.setState("-1");
                                        }
                                    }
                                }
                                MapUpdateManager.INSTANCE.asyncComplete();
                            }
                        };
                        if (z2) {
                        }
                        Pdlog.m3274e(TAG, "startSyncMap " + z + needUpdateData + " upload=" + z2 + needUploadData + ' ');
                        if (!z) {
                        }
                        break;
                    case 5:
                        z2 = mapUpdateManager$startSyncMap$1.Z$1;
                        z = mapUpdateManager$startSyncMap$1.Z$0;
                        list4 = (List) mapUpdateManager$startSyncMap$1.L$3;
                        list3 = (List) mapUpdateManager$startSyncMap$1.L$2;
                        coroutineScope4 = (CoroutineScope) mapUpdateManager$startSyncMap$1.L$1;
                        mapUpdateManager3 = (MapUpdateManager) mapUpdateManager$startSyncMap$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        if (!z) {
                        }
                        Pdlog.m3274e(TAG, "startSyncMap " + z + needUpdateData + " upload=" + z2 + needUploadData + ' ');
                        if (!z) {
                        }
                        break;
                    case 6:
                        boolean z3 = mapUpdateManager$startSyncMap$1.Z$1;
                        boolean z4 = mapUpdateManager$startSyncMap$1.Z$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        mapUpdateManager$startSyncMap$1 = new MapUpdateManager$startSyncMap$1(this, continuation);
        Object obj2 = mapUpdateManager$startSyncMap$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (mapUpdateManager$startSyncMap$1.label) {
        }
    }

    public final void asyncComplete() {
        Pdlog.m3273d(TAG, "asyncComplete " + needUpdateData);
        List<RobotMapResp> list = needUpdateData;
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((RobotMapResp) obj).getState() == null) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            Pdlog.m3274e(TAG, "asyncComplete", "needUploadData=" + needUploadData, "needUpdateData=" + needUpdateData, "filter=" + arrayList2);
            if (arrayList2.isEmpty()) {
                INSTANCE.moveMapFileAndDelTem(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$asyncComplete$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        Function0<Unit> onSyncFinishListener2 = MapUpdateManager.INSTANCE.getOnSyncFinishListener();
                        if (onSyncFinishListener2 != null) {
                            onSyncFinishListener2.invoke();
                        }
                    }
                });
            }
        }
    }

    public final Object moveMapFileAndDelTemp(String str, Function1<? super Boolean, Unit> function1, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MapUpdateManager$moveMapFileAndDelTemp$2(str, function1, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void moveMapFileAndDelTem(Function1<? super Boolean, Unit> result) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        Pdlog.m3273d(TAG, "moveMapFileAndDelTem");
        if (FileUtils.isFileExists(DOWNLOAD_TEMP_FILE_PATH)) {
            if (!FileUtils.isFileExists(MAP_FILE_PATH)) {
                Pdlog.m3273d(TAG, "moveMapFileAndDelTem", Boolean.valueOf(FileUtils.createOrExistsDir(MAP_FILE_PATH)));
            }
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
                Pdlog.m3274e(TAG, "copyFileUsingFileChannels moveFinish");
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

    public final boolean checkTempMapRule(RobotMapResp it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        String tempMapName = getTempMapName(it.getName());
        if (!FileUtils.isFileExists(tempMapName)) {
            Pdlog.m3274e(TAG, "checkTempMapRule: " + tempMapName + " 不存在");
            return false;
        }
        boolean checkMapRule = SDK.INSTANCE.checkMapRule(tempMapName);
        if (!checkMapRule) {
            errorData.add(it);
        }
        Pdlog.m3274e(TAG, "checkTempMapRule checkMapRuleAndDel=" + checkMapRule + " path=" + tempMapName, errorData);
        return checkMapRule;
    }

    public final void deleteFailureDownloadFile(RobotMapResp it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        String tempMapName = getTempMapName(it.getName());
        if (!FileUtils.isFileExists(tempMapName)) {
            Pdlog.m3274e(TAG, "deleteFailureDownloadFile:" + tempMapName + " 不存在");
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
        Pdlog.m3274e(TAG, "checkMapRule = ", mapFile);
        boolean checkMapRule = SDK.INSTANCE.checkMapRule(mapFile);
        Pdlog.m3274e(TAG, "checkMapRule " + checkMapRule + ", " + mapFile);
        return checkMapRule;
    }

    public final String getTempMapName(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3274e(TAG, name);
        return DOWNLOAD_TEMP_FILE_PATH + getMapBase64Name(name);
    }

    public final String getMapBase64Name(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return UtilsKt.encodeMapName(name) + ".pdmap";
    }

    public final String getMapFile(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String str = MAP_FILE_PATH + getMapBase64Name(name);
        Pdlog.m3273d(TAG, "getMapFile path:" + str);
        return str;
    }

    private final void reloadMap(String mapName) {
        SDK.INSTANCE.reloadMap(mapName);
        Pdlog.m3274e(TAG, "reloadMap");
    }

    private final void deleteLocalMap(String mapName) {
        String str = MAP_FILE_PATH + getMapBase64Name(mapName);
        if (!FileUtils.isFileExists(str)) {
            Pdlog.m3274e(TAG, str + " 不存在");
            return;
        }
        boolean delete = FileUtils.delete(str);
        Pdlog.m3274e(TAG, "deleteLocalMap=" + delete + " path=" + str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void uploadMapUse$default(MapUpdateManager mapUpdateManager, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = RobotMapManager.INSTANCE.getDefaultPdmap();
        }
        if ((i & 2) != 0) {
            function1 = new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$uploadMapUse$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str2) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                    invoke2(str2);
                    return Unit.INSTANCE;
                }
            };
        }
        mapUpdateManager.uploadMapUse(str, function1);
    }

    public final void uploadMapUse(String name, Function1<? super String, Unit> result) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(result, "result");
        if (!(name.length() == 0)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MapUpdateManager$uploadMapUse$2(name, result, null), 3, null);
        } else {
            Pdlog.m3273d(TAG, "uploadMapUse= name isEmpty");
            result.invoke(null);
        }
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

    public final void deleteAllMapFile() {
        try {
            if (FileUtils.isDir(MAP_FILE_PATH)) {
                FileUtils.deleteDir(MAP_FILE_PATH);
                Pdlog.m3274e(TAG, "deleteAllMapFile");
            }
        } catch (Exception e) {
            String str = TAG;
            e.printStackTrace();
            Pdlog.m3274e(str, Unit.INSTANCE);
        }
    }

    public final boolean hasMapOnMapPath() {
        return getAllFiles(MAP_FILE_PATH, "pdmap").size() != 0;
    }

    private final Vector<File> getAllFiles(String dirPath, String fileType) {
        Vector<File> vector = new Vector<>();
        File file = new File(dirPath);
        if (!file.exists()) {
            return vector;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            vector = new Vector<>();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        String name = file2.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                        if (StringsKt.endsWith$default(name, fileType, false, 2, (Object) null)) {
                            vector.add(file2);
                        }
                    }
                    if (file2.isDirectory()) {
                        MapUpdateManager mapUpdateManager = INSTANCE;
                        String absolutePath = file2.getAbsolutePath();
                        Intrinsics.checkExpressionValueIsNotNull(absolutePath, "it.absolutePath");
                        mapUpdateManager.getAllFiles(absolutePath, fileType);
                    }
                }
            }
        }
        return vector;
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
