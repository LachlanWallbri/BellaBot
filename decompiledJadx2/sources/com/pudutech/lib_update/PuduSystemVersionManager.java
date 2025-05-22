package com.pudutech.lib_update;

import android.content.Context;
import android.os.RecoverySystem;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.listener.SystemDownloadCallback;
import com.pudutech.lib_update.listener.SystemInstallCallback;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.lib_update.module.service.UpdateRequestManager;
import com.pudutech.lib_update.util.FileUtil;
import com.pudutech.lib_update.util.SystemCmdUtils;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.ExtKt;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u0015J!\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J_\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\b\b\u0002\u0010#\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010$J^\u0010%\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\b\b\u0002\u0010#\u001a\u00020\u00042\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'J\u0016\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020*J\u0010\u0010+\u001a\u0004\u0018\u00010\u00042\u0006\u0010,\u001a\u00020\u0004J\u0016\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0004J\u0016\u00101\u001a\u00020\n2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\n03H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, m3961d2 = {"Lcom/pudutech/lib_update/PuduSystemVersionManager;", "", "()V", "TAG", "", "checkSystemUpdateRunning", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "checkFile", "", "outputFile", "Ljava/io/File;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "Lcom/pudutech/lib_update/module/model/Version;", "sysInstallCallback", "Lcom/pudutech/lib_update/listener/SystemInstallCallback;", "checkLocalSystemFile", "currentSysVersionCode", "", "callback", "Lkotlin/Function1;", "checkMd5", "file", "md5", "(Ljava/io/File;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkNewVersion", "mac", "sys_ver_name", "sys_ver_code", "product_name", "request_ver_name", "request_ver_code", "app_version_name", "sys_build_id", "channel_name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkSystemUpdate", "checkSystemListener", "Lcom/pudutech/lib_update/listener/IShowProgress;", "downloadSystem", "version", "Lcom/pudutech/lib_update/listener/SystemDownloadCallback;", "getVersionCodeFormFileName", "name", "installSystemUpdate", "context", "Landroid/content/Context;", "path", "toMain", "block", "Lkotlin/Function0;", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PuduSystemVersionManager {
    private static final String TAG = "PuduSystemVersionManager";
    private static boolean checkSystemUpdateRunning;
    public static final PuduSystemVersionManager INSTANCE = new PuduSystemVersionManager();
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    private PuduSystemVersionManager() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(9:5|6|7|(1:(2:10|11)(2:44|45))(3:46|47|(1:49))|12|13|(1:15)(1:42)|(4:17|(1:19)|20|(3:22|(1:24)(1:40)|(5:26|(1:28)(1:39)|(1:30)|31|(4:33|(1:35)|36|37))))|41))|52|6|7|(0)(0)|12|13|(0)(0)|(0)|41) */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x010a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x010b, code lost:
    
        com.pudutech.pd_network.log.NetWorkLog.INSTANCE.mo3280i(com.pudutech.lib_update.PuduSystemVersionManager.TAG, "checkNewVersion > " + r0.getMessage());
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkNewVersion(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Continuation<? super Version> continuation) {
        PuduSystemVersionManager$checkNewVersion$1 puduSystemVersionManager$checkNewVersion$1;
        int i;
        BaseResponse baseResponse;
        if (continuation instanceof PuduSystemVersionManager$checkNewVersion$1) {
            puduSystemVersionManager$checkNewVersion$1 = (PuduSystemVersionManager$checkNewVersion$1) continuation;
            if ((puduSystemVersionManager$checkNewVersion$1.label & Integer.MIN_VALUE) != 0) {
                puduSystemVersionManager$checkNewVersion$1.label -= Integer.MIN_VALUE;
                PuduSystemVersionManager$checkNewVersion$1 puduSystemVersionManager$checkNewVersion$12 = puduSystemVersionManager$checkNewVersion$1;
                Object obj = puduSystemVersionManager$checkNewVersion$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = puduSystemVersionManager$checkNewVersion$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkLog.INSTANCE.mo3280i(TAG, "checkSystemUpdate > mac:" + str + " sys_ver_name:" + str2 + " sys_ver_code:" + str3 + " product_name:" + str4 + " request_ver_name:" + str5 + " request_ver_code:" + str6 + " app_version_name:" + str7 + " sys_build_id:" + str8 + " channel_name:" + str9 + ' ');
                    UpdateRequestManager updateRequestManager = UpdateRequestManager.INSTANCE;
                    puduSystemVersionManager$checkNewVersion$12.L$0 = this;
                    puduSystemVersionManager$checkNewVersion$12.L$1 = str;
                    puduSystemVersionManager$checkNewVersion$12.L$2 = str2;
                    puduSystemVersionManager$checkNewVersion$12.L$3 = str3;
                    puduSystemVersionManager$checkNewVersion$12.L$4 = str4;
                    puduSystemVersionManager$checkNewVersion$12.L$5 = str5;
                    puduSystemVersionManager$checkNewVersion$12.L$6 = str6;
                    puduSystemVersionManager$checkNewVersion$12.L$7 = str7;
                    puduSystemVersionManager$checkNewVersion$12.L$8 = str8;
                    puduSystemVersionManager$checkNewVersion$12.L$9 = str9;
                    puduSystemVersionManager$checkNewVersion$12.label = 1;
                    obj = updateRequestManager.checkSystemVersionUpdate(str2, str3, str, str4, str5, str6, str7, str8, str9, puduSystemVersionManager$checkNewVersion$12);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                baseResponse = (BaseResponse) obj;
                NetWorkLog.INSTANCE.mo3280i(TAG, "checkSystemUpdate > " + baseResponse);
                if ((baseResponse == null ? (VerionResult) baseResponse.getData() : null) != null) {
                    Object data = baseResponse.getData();
                    if (data == null) {
                        Intrinsics.throwNpe();
                    }
                    if (((VerionResult) data).getAvailable()) {
                        VerionResult verionResult = (VerionResult) baseResponse.getData();
                        if ((verionResult != null ? verionResult.getVersion() : null) != null) {
                            VerionResult verionResult2 = (VerionResult) baseResponse.getData();
                            Version version = verionResult2 != null ? verionResult2.getVersion() : null;
                            if (version == null) {
                                Intrinsics.throwNpe();
                            }
                            if (!StringsKt.isBlank(version.getMd5())) {
                                Object data2 = baseResponse.getData();
                                if (data2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                return ((VerionResult) data2).getVersion();
                            }
                        }
                    }
                }
                return null;
            }
        }
        puduSystemVersionManager$checkNewVersion$1 = new PuduSystemVersionManager$checkNewVersion$1(this, continuation);
        PuduSystemVersionManager$checkNewVersion$1 puduSystemVersionManager$checkNewVersion$122 = puduSystemVersionManager$checkNewVersion$1;
        Object obj2 = puduSystemVersionManager$checkNewVersion$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = puduSystemVersionManager$checkNewVersion$122.label;
        if (i != 0) {
        }
        baseResponse = (BaseResponse) obj2;
        NetWorkLog.INSTANCE.mo3280i(TAG, "checkSystemUpdate > " + baseResponse);
        if ((baseResponse == null ? (VerionResult) baseResponse.getData() : null) != null) {
        }
        return null;
    }

    public final void checkSystemUpdate(String mac, String sys_ver_name, String sys_ver_code, String product_name, String request_ver_name, String request_ver_code, String app_version_name, String sys_build_id, String channel_name, IShowProgress checkSystemListener) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(sys_ver_name, "sys_ver_name");
        Intrinsics.checkParameterIsNotNull(sys_ver_code, "sys_ver_code");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(request_ver_name, "request_ver_name");
        Intrinsics.checkParameterIsNotNull(request_ver_code, "request_ver_code");
        Intrinsics.checkParameterIsNotNull(app_version_name, "app_version_name");
        Intrinsics.checkParameterIsNotNull(sys_build_id, "sys_build_id");
        Intrinsics.checkParameterIsNotNull(channel_name, "channel_name");
        NetWorkLog.INSTANCE.mo3280i(TAG, "checkSystemUpdate > checkSystemUpdateRunning:" + checkSystemUpdateRunning);
        if (checkSystemUpdateRunning) {
            return;
        }
        checkSystemUpdateRunning = true;
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PuduSystemVersionManager$checkSystemUpdate$1(sys_ver_name, sys_ver_code, mac, product_name, request_ver_name, request_ver_code, app_version_name, sys_build_id, channel_name, checkSystemListener, null), 3, null);
    }

    public final void downloadSystem(Version version, SystemDownloadCallback callback) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String url = version.getUrl();
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) url, "/", 0, false, 6, (Object) null);
        if (url == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = url.substring(lastIndexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        File file = new File("/data/media/0/", substring);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        ExtKt.download(PdNetworkManager.f10310INSTANCE, url, file, new PuduSystemVersionManager$downloadSystem$1(file, version, callback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkFile(File outputFile, Version res, SystemInstallCallback sysInstallCallback) {
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PuduSystemVersionManager$checkFile$1(outputFile, res, sysInstallCallback, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object checkMd5(File file, String str, Continuation<? super Boolean> continuation) {
        PuduSystemVersionManager$checkMd5$1 puduSystemVersionManager$checkMd5$1;
        int i;
        String str2;
        if (continuation instanceof PuduSystemVersionManager$checkMd5$1) {
            puduSystemVersionManager$checkMd5$1 = (PuduSystemVersionManager$checkMd5$1) continuation;
            if ((puduSystemVersionManager$checkMd5$1.label & Integer.MIN_VALUE) != 0) {
                puduSystemVersionManager$checkMd5$1.label -= Integer.MIN_VALUE;
                Object obj = puduSystemVersionManager$checkMd5$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = puduSystemVersionManager$checkMd5$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!file.exists()) {
                        NetWorkLog.INSTANCE.mo3278d(TAG, "checkDownloadInfo downInfo file not exists ,try wait and check ");
                        SystemCmdUtils.INSTANCE.execCommand(InvokeServiceData.CALL_TYPE_SYNC, false);
                        puduSystemVersionManager$checkMd5$1.L$0 = this;
                        puduSystemVersionManager$checkMd5$1.L$1 = file;
                        puduSystemVersionManager$checkMd5$1.L$2 = str;
                        puduSystemVersionManager$checkMd5$1.label = 1;
                        if (DelayKt.delay(2000L, puduSystemVersionManager$checkMd5$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    String calculateFileMD5 = FileUtil.INSTANCE.calculateFileMD5(file);
                    NetWorkLog.INSTANCE.mo3278d(TAG, "checkDownInfo start check md5 , file md5 = " + str + " , fileMd5 = " + calculateFileMD5);
                    str2 = calculateFileMD5;
                    if (str2 != null || StringsKt.isBlank(str2)) {
                        return Boxing.boxBoolean(false);
                    }
                    if (!Intrinsics.areEqual(str, calculateFileMD5)) {
                        return Boxing.boxBoolean(false);
                    }
                    return Boxing.boxBoolean(true);
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                str = (String) puduSystemVersionManager$checkMd5$1.L$2;
                file = (File) puduSystemVersionManager$checkMd5$1.L$1;
                ResultKt.throwOnFailure(obj);
                if (!file.exists()) {
                    NetWorkLog.INSTANCE.mo3279e(TAG, "checkDownloadInfo downInfo file not exists ??");
                    return Boxing.boxBoolean(false);
                }
                String calculateFileMD52 = FileUtil.INSTANCE.calculateFileMD5(file);
                NetWorkLog.INSTANCE.mo3278d(TAG, "checkDownInfo start check md5 , file md5 = " + str + " , fileMd5 = " + calculateFileMD52);
                str2 = calculateFileMD52;
                if (str2 != null || StringsKt.isBlank(str2)) {
                }
            }
        }
        puduSystemVersionManager$checkMd5$1 = new PuduSystemVersionManager$checkMd5$1(this, continuation);
        Object obj2 = puduSystemVersionManager$checkMd5$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = puduSystemVersionManager$checkMd5$1.label;
        if (i != 0) {
        }
        if (!file.exists()) {
        }
        String calculateFileMD522 = FileUtil.INSTANCE.calculateFileMD5(file);
        NetWorkLog.INSTANCE.mo3278d(TAG, "checkDownInfo start check md5 , file md5 = " + str + " , fileMd5 = " + calculateFileMD522);
        str2 = calculateFileMD522;
        if (str2 != null || StringsKt.isBlank(str2)) {
        }
    }

    public final void checkLocalSystemFile(int currentSysVersionCode, Function1<? super String, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        NetWorkLog.INSTANCE.mo3280i(TAG, "checkLocalSystemFile > currentSysVersionCode:" + currentSysVersionCode + " callback:" + callback + ' ');
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PuduSystemVersionManager$checkLocalSystemFile$1(callback, currentSysVersionCode, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toMain(Function0<Unit> block) {
        BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getMain(), null, new PuduSystemVersionManager$toMain$1(block, null), 2, null);
    }

    public final void installSystemUpdate(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        NetWorkLog.INSTANCE.mo3278d(TAG, "installSystemUpdate " + path);
        File file = new File(path);
        if (file.exists()) {
            try {
                RecoverySystem.installPackage(context, file);
            } catch (IOException e) {
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                String localizedMessage = e.getLocalizedMessage();
                Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "e.localizedMessage");
                netWorkLog.mo3279e(TAG, localizedMessage);
            }
        }
    }

    public final String getVersionCodeFormFileName(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        NetWorkLog.INSTANCE.mo3278d(TAG, "getVersionCodeFormFileName " + name);
        Matcher matcher = Pattern.compile("^system_update_([\\d]+)\\.pkg$").matcher(name);
        if (!matcher.find()) {
            return null;
        }
        String group = matcher.group(1);
        NetWorkLog.INSTANCE.mo3278d(TAG, "getVersionCodeFormFileName is " + group);
        return group;
    }
}
