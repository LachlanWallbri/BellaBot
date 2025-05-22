package com.pudutech.antichannelconflict.escape;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener;
import com.pudutech.antichannelconflict.escape.util.EncryptUtils;
import com.pudutech.antichannelconflict.escape.util.GsonUtils;
import com.pudutech.antichannelconflict.escape.util.MapStatus;
import com.pudutech.antichannelconflict.escape.util.ProductType;
import com.pudutech.antichannelconflict.escape.util.RSAEncrypt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.apache.commons.codec.language.Soundex;

/* compiled from: EscapeManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004JO\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004¢\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010'\u001a\u00020(2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004H\u0002J\u0012\u0010,\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010.\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0004002\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u00101\u001a\u00020\u0015J\u0018\u00102\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u00103\u001a\u00020 H\u0002J\u0010\u00104\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/EscapeManager;", "", "()V", "KEY_LAST_MAP_LIST", "", "LAST_ESCAPE_STATUS", "MAP_FILE_PATH", "getMAP_FILE_PATH", "()Ljava/lang/String;", "setMAP_FILE_PATH", "(Ljava/lang/String;)V", "TAG", "listener", "Lcom/pudutech/antichannelconflict/escape/listener/EscapeDetectListener;", "lockTask", "Lkotlinx/coroutines/Job;", "lockTaskScope", "Lkotlinx/coroutines/CoroutineScope;", "singleThreadContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "addEscapeDetectListener", "", "changMapPath", "path", "checkEscapeStatus", "context", "Landroid/content/Context;", "mac", "longitude", "", "latitude", "autoLock", "", "mbts", "productType", "Lcom/pudutech/antichannelconflict/escape/util/ProductType;", "softVersion", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;ZLjava/lang/String;Lcom/pudutech/antichannelconflict/escape/util/ProductType;Ljava/lang/String;)V", "checkLockStatus", "checkMapIfChange", "Lcom/pudutech/antichannelconflict/escape/util/MapStatus;", "doubleEncrypt", "mapName", "mapMD5", "encryptParam", "param", "getLastEscapeStatus", "getMapListFromSpf", "", "removeEscapeDetectListener", "saveLastEscapeStatus", "status", "saveMapListToSpf", "startCheckLockTask", TypedValues.Cycle.S_WAVE_PERIOD, "", "stopCheckLockTask", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class EscapeManager {
    private static final String KEY_LAST_MAP_LIST = "last_map_list";
    private static final String LAST_ESCAPE_STATUS = "last_escape_status";
    private static EscapeDetectListener listener;
    private static Job lockTask;
    public static final EscapeManager INSTANCE = new EscapeManager();
    private static String MAP_FILE_PATH = "/sdcard/pudu/map/";
    private static final String TAG = "EscapeManager";
    private static final ExecutorCoroutineDispatcher singleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext(TAG);
    private static final CoroutineScope lockTaskScope = CoroutineScopeKt.CoroutineScope(singleThreadContext);

    private EscapeManager() {
    }

    public final String getMAP_FILE_PATH() {
        return MAP_FILE_PATH;
    }

    public final void setMAP_FILE_PATH(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        MAP_FILE_PATH = str;
    }

    private final List<String> getMapListFromSpf(Context context) {
        String str = SpUtils.get(context, KEY_LAST_MAP_LIST, "");
        if (!StringsKt.isBlank(str)) {
            try {
                Object fromJson = GsonUtils.fromJson(str, new TypeToken<List<String>>() { // from class: com.pudutech.antichannelconflict.escape.EscapeManager$getMapListFromSpf$1
                }.getType());
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "GsonUtils.fromJson<Mutab…{}.type\n                )");
                return (List) fromJson;
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "getCustomSoundConfig error=" + e);
            }
        }
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveMapListToSpf(Context context) {
        ArrayList arrayList;
        File[] listFiles = new File(MAP_FILE_PATH).listFiles(new FileFilter() { // from class: com.pudutech.antichannelconflict.escape.EscapeManager$saveMapListToSpf$fileList$1
            @Override // java.io.FileFilter
            public final boolean accept(File it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                return it.isFile() && Intrinsics.areEqual(FilesKt.getExtension(it), "pdmap");
            }
        });
        if (listFiles == null) {
            arrayList = new ArrayList();
        } else {
            ArrayList arrayList2 = new ArrayList(listFiles.length);
            for (File it : listFiles) {
                EscapeManager escapeManager = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                String name = it.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                String encryptMD5File2String = EncryptUtils.encryptMD5File2String(it);
                Intrinsics.checkExpressionValueIsNotNull(encryptMD5File2String, "EncryptUtils.encryptMD5File2String(it)");
                arrayList2.add(escapeManager.doubleEncrypt(name, encryptMD5File2String));
            }
            arrayList = arrayList2;
        }
        Pdlog.m3273d(TAG, "saveMapListToSpf", arrayList);
        String json = new Gson().toJson(arrayList);
        Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(localMapList)");
        SpUtils.set(context, KEY_LAST_MAP_LIST, json);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveLastEscapeStatus(Context context, boolean status) {
        SpUtils.set(context, LAST_ESCAPE_STATUS, status);
        Pdlog.m3273d(TAG, "saveLastEscapeStatus(true is lock)  status:" + status + "  ");
    }

    public final boolean getLastEscapeStatus(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return SpUtils.get(context, LAST_ESCAPE_STATUS, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MapStatus checkMapIfChange(Context context) {
        ArrayList arrayList;
        List list = CollectionsKt.toList(getMapListFromSpf(context));
        File[] listFiles = new File(MAP_FILE_PATH).listFiles(new FileFilter() { // from class: com.pudutech.antichannelconflict.escape.EscapeManager$checkMapIfChange$fileList$1
            @Override // java.io.FileFilter
            public final boolean accept(File it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                return it.isFile() && Intrinsics.areEqual(FilesKt.getExtension(it), "pdmap");
            }
        });
        if (listFiles == null) {
            arrayList = new ArrayList();
        } else {
            ArrayList arrayList2 = new ArrayList(listFiles.length);
            for (File it : listFiles) {
                EscapeManager escapeManager = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                String name = it.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                String encryptMD5File2String = EncryptUtils.encryptMD5File2String(it);
                Intrinsics.checkExpressionValueIsNotNull(encryptMD5File2String, "EncryptUtils.encryptMD5File2String(it)");
                arrayList2.add(escapeManager.doubleEncrypt(name, encryptMD5File2String));
            }
            arrayList = arrayList2;
        }
        Pdlog.m3273d(TAG, "checkMapIfChange localMapList:  " + arrayList + ", saveMapList:" + list);
        if (list.isEmpty()) {
            return MapStatus.MAP_CREATED;
        }
        if (!list.containsAll(arrayList) || !arrayList.containsAll(list)) {
            return MapStatus.MAP_UPDATED;
        }
        return MapStatus.NO_CHANGE;
    }

    public final void changMapPath(String path) {
        if (path != null) {
            MAP_FILE_PATH = path;
        }
    }

    private final String doubleEncrypt(String mapName, String mapMD5) {
        return mapName + Soundex.SILENT_MARKER + mapMD5;
    }

    public final void checkLockStatus(String mac, Context context) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(TAG, "checkLockStatus begin");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EscapeManager$checkLockStatus$1(mac, context, null), 3, null);
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v10, types: [T, java.lang.String] */
    public final void checkEscapeStatus(Context context, String mac, Double longitude, Double latitude, boolean autoLock, String mbts, ProductType productType, String softVersion) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(mbts, "mbts");
        Intrinsics.checkParameterIsNotNull(productType, "productType");
        Intrinsics.checkParameterIsNotNull(softVersion, "softVersion");
        Pdlog.m3273d(TAG, "checkEscapeStatus", latitude, longitude);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "null";
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = "null";
        if (Intrinsics.areEqual(latitude, 0.0d) || Intrinsics.areEqual(longitude, 0.0d) || latitude == null || longitude == null) {
            Pdlog.m3273d(TAG, "定位失败", (String) objectRef.element, (String) objectRef2.element);
        } else {
            objectRef.element = encryptParam(String.valueOf(latitude.doubleValue()));
            objectRef2.element = encryptParam(String.valueOf(longitude.doubleValue()));
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EscapeManager$checkEscapeStatus$1(mac, objectRef2, objectRef, autoLock, checkMapIfChange(context), mbts, productType, softVersion, context, null), 3, null);
    }

    public final void addEscapeDetectListener(EscapeDetectListener listener2) {
        Intrinsics.checkParameterIsNotNull(listener2, "listener");
        listener = listener2;
    }

    public final void removeEscapeDetectListener() {
        listener = (EscapeDetectListener) null;
    }

    private final String encryptParam(String param) {
        RSAEncrypt rSAEncrypt = RSAEncrypt.INSTANCE;
        if (param == null) {
            param = "";
        }
        return rSAEncrypt.encryptByPublicKey(param);
    }

    public final void startCheckLockTask(long period) {
        Job launch$default;
        try {
            Pdlog.m3273d(TAG, "startEscapeTask period:" + period);
            Job job = lockTask;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(lockTaskScope, null, null, new EscapeManager$startCheckLockTask$1(period, null), 3, null);
            lockTask = launch$default;
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "startCheckLockTask Exception ", e);
            startCheckLockTask(period);
        }
    }

    public final void stopCheckLockTask() {
        Job job = lockTask;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
