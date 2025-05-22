package com.pudutech.antichannelconflict.upgrade;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.util.Pair;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener;
import com.pudutech.antichannelconflict.upgrade.util.UpgradeStatus;
import com.pudutech.base.Pdlog;
import com.pudutech.getusbdev.lib.UsbDeviceHelper;
import com.pudutech.getusbdev.lib.UsbDeviceType;
import com.pudutech.lib_update.UpdateManager;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.ExtKt;
import com.pudutech.pd_network.utils.NetDataUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.apache.commons.codec.language.Soundex;

/* compiled from: PeriodOf4GManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0004J+\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010JJ\u0006\u0010K\u001a\u00020\u0006J\u0011\u0010L\u001a\u00020CH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010MJ \u0010N\u001a\u00020C2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00132\b\u0010O\u001a\u0004\u0018\u00010\u0004J\u0010\u0010P\u001a\u00020C2\b\u0010Q\u001a\u0004\u0018\u00010\u0004J\u0006\u0010R\u001a\u00020\u0004J\u0006\u0010S\u001a\u00020\u0004J\u001a\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00040U2\u0006\u0010V\u001a\u00020\u0004J\u0018\u0010W\u001a\u00020\u00042\u0006\u0010X\u001a\u00020+2\u0006\u0010Y\u001a\u00020+H\u0002J\u001a\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00040U2\u0006\u0010V\u001a\u00020\u0004J\u0018\u0010[\u001a\u0004\u0018\u00010\u00042\u0006\u0010\\\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u0004J\u0016\u0010^\u001a\u00020C2\u0006\u0010\u0011\u001a\u00020\u00132\u0006\u0010_\u001a\u00020\u0006J\u0011\u0010`\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0006\u0010a\u001a\u00020CJ\u0010\u0010b\u001a\u00020C2\u0006\u0010c\u001a\u00020\u0004H\u0002J\b\u0010d\u001a\u00020CH\u0002J\u0010\u0010e\u001a\u00020C2\b\u0010f\u001a\u0004\u0018\u00010$J\u0016\u0010g\u001a\u00020C2\u0006\u0010\\\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u0004J)\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00042\u0006\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010oJ\u0006\u0010p\u001a\u00020CJ\b\u0010q\u001a\u00020CH\u0002J\u0006\u0010r\u001a\u00020CJ\u0010\u0010s\u001a\u00020C2\b\b\u0002\u0010t\u001a\u00020\u0006J\u001e\u0010u\u001a\u00020C2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u0004J\u001c\u0010x\u001a\u00020C2\n\b\u0002\u0010y\u001a\u0004\u0018\u00010\u00042\b\u0010Q\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\r\"\u0004\b\u001c\u0010\u000fR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\r\"\u0004\b\"\u0010\u000fR\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\r\"\u0004\b'\u0010\u000fR\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\r\"\u0004\b0\u0010\u000fR\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00105\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R*\u0010:\u001a\u0012\u0012\u0004\u0012\u00020\u00040;j\b\u0012\u0004\u0012\u00020\u0004`<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u000e\u0010A\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006z"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/upgrade/PeriodOf4GManager;", "", "()V", "TAG", "", "autoUpdate", "", "getAutoUpdate", "()Z", "setAutoUpdate", "(Z)V", "bts", "getBts$AntiChannelConflict_release", "()Ljava/lang/String;", "setBts$AntiChannelConflict_release", "(Ljava/lang/String;)V", "cmd", "context", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "copyTimes", "", "getCopyTimes", "()I", "setCopyTimes", "(I)V", "firmware_download_url", "getFirmware_download_url$AntiChannelConflict_release", "setFirmware_download_url$AntiChannelConflict_release", "jobRead", "Lkotlinx/coroutines/Job;", "jobSet", "model_current", "getModel_current$AntiChannelConflict_release", "setModel_current$AntiChannelConflict_release", "myStatusListener", "Lcom/pudutech/antichannelconflict/upgrade/listener/PeriodStatusListener;", "period_4g_usb", "getPeriod_4g_usb$AntiChannelConflict_release", "setPeriod_4g_usb$AntiChannelConflict_release", "readThreadContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "readTime", "", "retryTimesBTS", "retryTimesVersion", "revision_current", "getRevision_current$AntiChannelConflict_release", "setRevision_current$AntiChannelConflict_release", "scope", "Lkotlinx/coroutines/CoroutineScope;", "scopeRead", "scopeWrite", "statusListener", "getStatusListener", "()Lcom/pudutech/antichannelconflict/upgrade/listener/PeriodStatusListener;", "setStatusListener", "(Lcom/pudutech/antichannelconflict/upgrade/listener/PeriodStatusListener;)V", "supportBTS", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getSupportBTS", "()Ljava/util/ArrayList;", "setSupportBTS", "(Ljava/util/ArrayList;)V", "writelThreadContext", "changeSupportBTS", "", "model", "checkFirmwareVersion", "Lcom/pudutech/lib_update/module/model/Version;", "mac", "product_name", "language", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkIfPeriod4GSupportBTS", "checkPeriodVersion", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyFirmware", "zipPath", "deleteUpdateFile", "path", "get4GDevice", "getBTS", "getBTSFromShell", "Landroid/util/Pair;", UtilityConfig.KEY_DEVICE_INFO, "getProgress", "totalSize", "currentSize", "getSimComModelAndRevision", "getSimComUSBDevice", "pid", SpeechConstant.ISV_VID, "init", "auto", "readFromShell", "resetBTS", "sendBTSShell", "mDevice", "sendObtainRevisionCmd", "setPeriodStatusListener", "listener", "startCheckPeriod", "startDownload", "Lcom/pudutech/pd_network/IOssTaskController;", "url", "outputFile", "Ljava/io/File;", "callback", "Lcom/pudutech/pd_network/OssCallback;", "(Ljava/lang/String;Ljava/io/File;Lcom/pudutech/pd_network/OssCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startObtainBTS", "startObtainPeriodRevision", "startUpdate", "startUpdatePeriod4G", "force", "unZip", "zipFile", "descDir", "updateFirmware", "simCom", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PeriodOf4GManager {
    private static WeakReference<Context> context;
    private static int copyTimes;
    private static Job jobRead;
    private static Job jobSet;
    private static PeriodStatusListener statusListener;
    public static final PeriodOf4GManager INSTANCE = new PeriodOf4GManager();
    private static final String cmd = "";
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static final ExecutorCoroutineDispatcher readThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("PDEscapeManager-Read");
    private static final ExecutorCoroutineDispatcher writelThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("PDEscapeManager-Write");
    private static final CoroutineScope scopeRead = CoroutineScopeKt.CoroutineScope(readThreadContext);
    private static final CoroutineScope scopeWrite = CoroutineScopeKt.CoroutineScope(writelThreadContext);
    private static long readTime = 300000;
    private static int retryTimesBTS = 50;
    private static int retryTimesVersion = 10;
    private static String period_4g_usb = "";
    private static String revision_current = "";
    private static String model_current = "";
    private static String firmware_download_url = "";
    private static ArrayList<String> supportBTS = CollectionsKt.arrayListOf("SIMCOM_SIM7600G-H-LE20B02SIM7600G22_CUS_PD01", "SIMCOM_SIM7600CE-L1S-LE20B02SIM7600M11_OTA_CUS_PD");
    private static String bts = "";
    private static boolean autoUpdate = true;
    private static PeriodStatusListener myStatusListener = new PeriodStatusListener() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$myStatusListener$1
        @Override // com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener
        public void onUpdateProgressCB(String res, String progress, UpgradeStatus status) {
            Intrinsics.checkParameterIsNotNull(status, "status");
            Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "onUpdateProgressCB " + res + ' ' + progress + ' ' + status + ' ');
            PeriodStatusListener statusListener2 = PeriodOf4GManager.INSTANCE.getStatusListener();
            if (statusListener2 != null) {
                statusListener2.onUpdateProgressCB(res, progress, status);
            }
        }

        @Override // com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener
        public void onCheckPeriodCallback(UpgradeStatus status) {
            Intrinsics.checkParameterIsNotNull(status, "status");
            Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "onCheckPeriodCallback " + status);
            PeriodStatusListener statusListener2 = PeriodOf4GManager.INSTANCE.getStatusListener();
            if (statusListener2 != null) {
                statusListener2.onCheckPeriodCallback(status);
            }
        }

        @Override // com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener
        public void onObtainBTS(String bts2) {
            Intrinsics.checkParameterIsNotNull(bts2, "bts");
            Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "onObtainBTS " + bts2);
            PeriodStatusListener statusListener2 = PeriodOf4GManager.INSTANCE.getStatusListener();
            if (statusListener2 != null) {
                statusListener2.onObtainBTS(bts2);
            }
        }
    };

    private PeriodOf4GManager() {
    }

    public static final /* synthetic */ WeakReference access$getContext$p(PeriodOf4GManager periodOf4GManager) {
        WeakReference<Context> weakReference = context;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return weakReference;
    }

    public static final /* synthetic */ String access$getTAG$p(PeriodOf4GManager periodOf4GManager) {
        return TAG;
    }

    public final String getPeriod_4g_usb$AntiChannelConflict_release() {
        return period_4g_usb;
    }

    public final void setPeriod_4g_usb$AntiChannelConflict_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        period_4g_usb = str;
    }

    public final String getRevision_current$AntiChannelConflict_release() {
        return revision_current;
    }

    public final void setRevision_current$AntiChannelConflict_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        revision_current = str;
    }

    public final String getModel_current$AntiChannelConflict_release() {
        return model_current;
    }

    public final void setModel_current$AntiChannelConflict_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        model_current = str;
    }

    public final String getFirmware_download_url$AntiChannelConflict_release() {
        return firmware_download_url;
    }

    public final void setFirmware_download_url$AntiChannelConflict_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        firmware_download_url = str;
    }

    public final ArrayList<String> getSupportBTS() {
        return supportBTS;
    }

    public final void setSupportBTS(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        supportBTS = arrayList;
    }

    public final String getBts$AntiChannelConflict_release() {
        return bts;
    }

    public final void setBts$AntiChannelConflict_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        bts = str;
    }

    public final boolean getAutoUpdate() {
        return autoUpdate;
    }

    public final void setAutoUpdate(boolean z) {
        autoUpdate = z;
    }

    public final PeriodStatusListener getStatusListener() {
        return statusListener;
    }

    public final void setStatusListener(PeriodStatusListener periodStatusListener) {
        statusListener = periodStatusListener;
    }

    public final void init(Context context2, boolean auto) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = new WeakReference<>(context2);
        autoUpdate = auto;
    }

    public final String getSimComUSBDevice(String pid, String vid) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        String usbDevice = UsbDeviceHelper.INSTANCE.getUsbDevice(pid, vid, UsbDeviceType.USB);
        String str = (String) null;
        String str2 = usbDevice;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            CharSequence subSequence = usbDevice.subSequence(6, usbDevice.length());
            int length = usbDevice.length() - subSequence.length();
            if (usbDevice == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring = usbDevice.substring(0, length);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String str3 = substring + (Integer.parseInt(subSequence.toString()) + 2);
            Pdlog.m3273d(TAG, "  startCheckUSBOutPort :mUsbDevice:" + usbDevice + "  port:" + subSequence + "  mDevice:" + str3);
            str = str3;
        }
        Pdlog.m3273d(TAG, "  startCheckUSBOutPort :mUsbDevice:" + usbDevice + "    mDevice:" + str);
        return str;
    }

    public final Pair<Integer, String> getBTSFromShell(String device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        String replace$default = StringsKt.replace$default(CmdConfig.requireBTS, UtilityConfig.KEY_DEVICE_INFO, device, false, 4, (Object) null);
        Pair<Integer, String> execCommandForCat = ToolCommand.INSTANCE.execCommandForCat(replace$default, false, new Function1<String, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$getBTSFromShell$result$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        });
        Pdlog.m3273d(TAG, "getBTSFromShell", replace$default, execCommandForCat);
        return execCommandForCat;
    }

    public final Pair<Integer, String> getSimComModelAndRevision(String device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        String replace$default = StringsKt.replace$default(CmdConfig.getRevision, UtilityConfig.KEY_DEVICE_INFO, device, false, 4, (Object) null);
        Pair<Integer, String> execCommandForCat = ToolCommand.INSTANCE.execCommandForCat(replace$default, false, new Function1<String, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$getSimComModelAndRevision$result$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        });
        Pdlog.m3273d(TAG, "getSimComModal", replace$default, execCommandForCat);
        return execCommandForCat;
    }

    public static /* synthetic */ void updateFirmware$default(PeriodOf4GManager periodOf4GManager, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        periodOf4GManager.updateFirmware(str, str2);
    }

    public final void updateFirmware(String simCom, String path) {
        String str = CmdConfig.excuteUpdate;
        if (simCom != null) {
            str = StringsKt.replace$default(CmdConfig.excuteUpdate, "sim79xx_upgrade_C", simCom, false, 4, (Object) null);
        }
        Pair<Integer, String> execCommand = ToolCommand.INSTANCE.execCommand(str, true, new Function1<String, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$updateFirmware$result$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                PeriodStatusListener periodStatusListener;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (StringsKt.startsWith$default(it, "progress:", false, 2, (Object) null)) {
                    PeriodOf4GManager periodOf4GManager = PeriodOf4GManager.INSTANCE;
                    periodStatusListener = PeriodOf4GManager.myStatusListener;
                    periodStatusListener.onUpdateProgressCB("", (String) StringsKt.split$default((CharSequence) it, new String[]{":"}, false, 0, 6, (Object) null).get(1), UpgradeStatus.UPDATING);
                }
            }
        });
        Integer num = (Integer) execCommand.first;
        if (num != null && num.intValue() == 0) {
            deleteUpdateFile(path);
            myStatusListener.onUpdateProgressCB((String) execCommand.second, "100", UpgradeStatus.UPDATE_SUCCESS);
        } else {
            myStatusListener.onUpdateProgressCB((String) execCommand.second, "0", UpgradeStatus.UPDATE_FAIL);
        }
        Pdlog.m3273d(TAG, "getSimComModal", str, execCommand);
    }

    public final void deleteUpdateFile(String path) {
        Pdlog.m3273d(TAG, "deleteUpdateFile:" + path);
        File file = new File(path);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "file.listFiles()");
            ArrayList arrayList = new ArrayList(listFiles.length);
            for (File it : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                arrayList.add(it.getName());
            }
            String joinToString$default = CollectionsKt.joinToString$default(arrayList, " ", null, null, 0, null, null, 62, null);
            CmdConfig cmdConfig = CmdConfig.INSTANCE;
            if (path == null) {
                Intrinsics.throwNpe();
            }
            String deleteUpdateFile = cmdConfig.getDeleteUpdateFile(joinToString$default, path);
            if (deleteUpdateFile != null) {
                Pdlog.m3273d(TAG, "deleteUpdateFile", ToolCommand.INSTANCE.execCommand(deleteUpdateFile, true, new Function1<String, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$deleteUpdateFile$1$exitValue$1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it2) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }
                }));
            }
        }
    }

    public final Object startDownload(String str, File file, OssCallback ossCallback, Continuation<? super IOssTaskController> continuation) {
        return ExtKt.download(PdNetworkManager.f10310INSTANCE, str, file, ossCallback);
    }

    public final void unZip(String model, String zipFile, String descDir) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(zipFile, "zipFile");
        Intrinsics.checkParameterIsNotNull(descDir, "descDir");
        Pdlog.m3273d(TAG, "unZip", zipFile, descDir);
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PeriodOf4GManager$unZip$1(zipFile, descDir, model, null), 3, null);
    }

    public final int getCopyTimes() {
        return copyTimes;
    }

    public final void setCopyTimes(int i) {
        copyTimes = i;
    }

    public final void copyFirmware(String model, Context context2, String zipPath) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Pdlog.m3273d(TAG, "copyFirmware", model, Integer.valueOf(copyTimes));
        File file = new File(zipPath);
        StringBuilder sb = new StringBuilder();
        File parentFile = file.getParentFile();
        Intrinsics.checkExpressionValueIsNotNull(parentFile, "file.parentFile");
        sb.append(parentFile.getPath());
        sb.append(File.separator);
        ZipEntry nextElement = new ZipFile(zipPath).entries().nextElement();
        Intrinsics.checkExpressionValueIsNotNull(nextElement, "ZipFile(zipPath).entries…           .nextElement()");
        sb.append(nextElement.getName());
        String sb2 = sb.toString();
        String copyFirmwareCmd = CmdConfig.INSTANCE.getCopyFirmwareCmd(context2, sb2);
        Pair<Integer, String> execCommand = ToolCommand.INSTANCE.execCommand(copyFirmwareCmd, false, new Function1<String, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$copyFirmware$result$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        });
        Integer num = (Integer) execCommand.first;
        if (num != null && num.intValue() == 0) {
            if (file.exists()) {
                file.delete();
            }
            myStatusListener.onUpdateProgressCB("copyFirmware to /data/ success", "10", UpgradeStatus.UPDATING);
            if (Intrinsics.areEqual(model, "SIMCOM_SIM7066G-H")) {
                updateFirmware("sim76xx_upgrade_G", sb2);
            } else {
                updateFirmware$default(this, null, sb2, 1, null);
            }
        } else {
            int i = copyTimes;
            copyTimes = i + 1;
            if (i < 5) {
                BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PeriodOf4GManager$copyFirmware$1(model, context2, zipPath, null), 3, null);
            } else {
                myStatusListener.onUpdateProgressCB("copyFirmware fail  ", "0", UpgradeStatus.UPDATE_FAIL);
                copyTimes = 0;
            }
        }
        Pdlog.m3273d(TAG, "copyFirmware " + sb2, copyFirmwareCmd, execCommand);
    }

    public final void startCheckPeriod(String pid, String vid) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        String simComUSBDevice = getSimComUSBDevice(pid, vid);
        String str = simComUSBDevice;
        if (str == null || StringsKt.isBlank(str)) {
            myStatusListener.onCheckPeriodCallback(UpgradeStatus.SIM_COM_NO_FOUND);
            Pdlog.m3273d(TAG, "init can not get SimCom USB Device stop get simcom upgrade");
        } else {
            period_4g_usb = simComUSBDevice;
            INSTANCE.startObtainPeriodRevision();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startObtainPeriodRevision() {
        Job launch$default;
        Pdlog.m3273d(TAG, "  startObtainPeriodRevision : mDevice:" + period_4g_usb);
        if (!Intrinsics.areEqual(revision_current, "")) {
            Pdlog.m3273d(TAG, "revision_4g obtain", revision_current);
            BuildersKt__Builders_commonKt.launch$default(scopeRead, null, null, new PeriodOf4GManager$startObtainPeriodRevision$1(null), 3, null);
            return;
        }
        retryTimesVersion--;
        if (retryTimesVersion < 0) {
            myStatusListener.onCheckPeriodCallback(UpgradeStatus.SIM_COM_GET_REVISION_FAIL);
            Pdlog.m3273d(TAG, "obtain Revision timeout,retryTimesVersion:" + retryTimesVersion);
            return;
        }
        sendObtainRevisionCmd();
        Job job = jobRead;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(scopeRead, null, null, new PeriodOf4GManager$startObtainPeriodRevision$2(null), 3, null);
        jobRead = launch$default;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkPeriodVersion(Continuation<? super Unit> continuation) {
        PeriodOf4GManager$checkPeriodVersion$1 periodOf4GManager$checkPeriodVersion$1;
        int i;
        Resources resources;
        Configuration configuration;
        Version version;
        if (continuation instanceof PeriodOf4GManager$checkPeriodVersion$1) {
            periodOf4GManager$checkPeriodVersion$1 = (PeriodOf4GManager$checkPeriodVersion$1) continuation;
            if ((periodOf4GManager$checkPeriodVersion$1.label & Integer.MIN_VALUE) != 0) {
                periodOf4GManager$checkPeriodVersion$1.label -= Integer.MIN_VALUE;
                Object obj = periodOf4GManager$checkPeriodVersion$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = periodOf4GManager$checkPeriodVersion$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d("checkPeriodVersion", period_4g_usb);
                    WeakReference<Context> weakReference = context;
                    if (weakReference == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    Context context2 = weakReference.get();
                    Locale locale = (context2 == null || (resources = context2.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? null : configuration.locale;
                    String stringPlus = Intrinsics.stringPlus(locale != null ? locale.getLanguage() : null, "-");
                    StringBuilder sb = new StringBuilder();
                    sb.append(stringPlus);
                    sb.append(locale != null ? locale.getCountry() : null);
                    String sb2 = sb.toString();
                    String replace$default = StringsKt.replace$default(NetDataUtils.INSTANCE.mac(), ":", "", false, 4, (Object) null);
                    String str = model_current;
                    periodOf4GManager$checkPeriodVersion$1.L$0 = this;
                    periodOf4GManager$checkPeriodVersion$1.L$1 = locale;
                    periodOf4GManager$checkPeriodVersion$1.L$2 = sb2;
                    periodOf4GManager$checkPeriodVersion$1.label = 1;
                    obj = checkFirmwareVersion(replace$default, str, sb2, periodOf4GManager$checkPeriodVersion$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                version = (Version) obj;
                if (version == null) {
                    Pdlog.m3273d(TAG, "checkFirmwareVersion", version.getVersion_name(), version.getVersion_code(), version.getVersion_description(), version.getUrl());
                    firmware_download_url = version.getUrl();
                    if (!Intrinsics.areEqual(version.getVersion_name(), revision_current)) {
                        myStatusListener.onCheckPeriodCallback(UpgradeStatus.NEED_UPDATE);
                        if (autoUpdate) {
                            INSTANCE.startUpdatePeriod4G(true);
                        }
                    } else {
                        if (INSTANCE.checkIfPeriod4GSupportBTS()) {
                            INSTANCE.startObtainBTS();
                        }
                        myStatusListener.onCheckPeriodCallback(UpgradeStatus.D0_NOT_NEED_UPDATE);
                    }
                } else {
                    myStatusListener.onCheckPeriodCallback(UpgradeStatus.D0_NOT_NEED_UPDATE);
                    if (INSTANCE.checkIfPeriod4GSupportBTS()) {
                        INSTANCE.startObtainBTS();
                    }
                }
                return Unit.INSTANCE;
            }
        }
        periodOf4GManager$checkPeriodVersion$1 = new PeriodOf4GManager$checkPeriodVersion$1(this, continuation);
        Object obj2 = periodOf4GManager$checkPeriodVersion$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = periodOf4GManager$checkPeriodVersion$1.label;
        if (i != 0) {
        }
        version = (Version) obj2;
        if (version == null) {
        }
        return Unit.INSTANCE;
    }

    public final void startObtainBTS() {
        Job launch$default;
        Pdlog.m3273d(TAG, "  startObtainBTS : mDevice:" + period_4g_usb);
        if (!Intrinsics.areEqual(bts, "")) {
            Job job = jobSet;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            Pdlog.m3273d(TAG, "bts obtain", bts);
            myStatusListener.onObtainBTS(bts);
            return;
        }
        retryTimesBTS--;
        if (retryTimesBTS < 0) {
            Pdlog.m3273d(TAG, "obtain BTS timeout,retryTimesBTS:" + retryTimesBTS);
            return;
        }
        sendBTSShell(period_4g_usb);
        Job job2 = jobRead;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(scopeRead, null, null, new PeriodOf4GManager$startObtainBTS$1(null), 3, null);
        jobRead = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendBTSShell(String mDevice) {
        Job launch$default;
        Pdlog.m3273d(TAG, "sendBTSShell  " + mDevice);
        Job job = jobSet;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(scopeWrite, null, null, new PeriodOf4GManager$sendBTSShell$1(mDevice, null), 3, null);
        jobSet = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendObtainRevisionCmd() {
        Job launch$default;
        Pdlog.m3273d(TAG, "sendObtainRevisionCmd " + period_4g_usb);
        Job job = jobSet;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(scopeWrite, null, null, new PeriodOf4GManager$sendObtainRevisionCmd$1(null), 3, null);
        jobSet = launch$default;
    }

    public static /* synthetic */ void startUpdatePeriod4G$default(PeriodOf4GManager periodOf4GManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        periodOf4GManager.startUpdatePeriod4G(z);
    }

    public final void startUpdatePeriod4G(boolean force) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(scopeWrite, null, null, new PeriodOf4GManager$startUpdatePeriod4G$1(force, null), 3, null);
        jobSet = launch$default;
    }

    public final void startUpdate() {
        Pdlog.m3273d(TAG, "startUpdate unZip begin");
        File file = new File("/sdcard/" + StringsKt.substringAfterLast$default(firmware_download_url, "/", (String) null, 2, (Object) null));
        if (file.exists()) {
            PeriodOf4GManager periodOf4GManager = INSTANCE;
            String str = model_current;
            String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "it.path");
            File parentFile = file.getParentFile();
            Intrinsics.checkExpressionValueIsNotNull(parentFile, "it.parentFile");
            String path2 = parentFile.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path2, "it.parentFile.path");
            periodOf4GManager.unZip(str, path, path2);
            return;
        }
        myStatusListener.onUpdateProgressCB("File " + file.getPath() + " is not exists", "0", UpgradeStatus.UPDATE_FAIL_TOOL_NO_FOUND);
    }

    public final boolean checkIfPeriod4GSupportBTS() {
        Pdlog.m3273d(TAG, "checkIfPeriod4GSupportBTS " + model_current + Soundex.SILENT_MARKER + revision_current);
        for (String str : supportBTS) {
            if (Intrinsics.areEqual(str, model_current + Soundex.SILENT_MARKER + revision_current)) {
                Pdlog.m3273d(TAG, "checkIfPeriod4GSupportBTS", str);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getProgress(long totalSize, long currentSize) {
        int i = (int) (100 * (((float) currentSize) / ((float) totalSize)));
        return String.valueOf(i <= 100 ? i < 0 ? 0 : i : 100);
    }

    public final void setPeriodStatusListener(PeriodStatusListener listener) {
        Pdlog.m3273d(TAG, "setPeriodStatusListener", listener);
        statusListener = listener;
    }

    public final void resetBTS() {
        bts = "";
        Pdlog.m3273d(TAG, "resetBTS");
    }

    public final void changeSupportBTS(String model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(TAG, "changeSupportBTS mode:" + model);
        supportBTS = CollectionsKt.arrayListOf(model);
    }

    public final String getBTS() {
        return bts;
    }

    public final String get4GDevice() {
        return period_4g_usb;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
    
        if (r5.intValue() != (-1)) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
    
        r7 = kotlin.Result.INSTANCE;
        r1.resumeWith(kotlin.Result.m4510constructorimpl(""));
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
    
        if (r5.intValue() != 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readFromShell(Continuation<? super String> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        String replace$default = StringsKt.replace$default(CmdConfig.readBTS, UtilityConfig.KEY_DEVICE_INFO, INSTANCE.getPeriod_4g_usb$AntiChannelConflict_release(), false, 4, (Object) null);
        try {
            Pair<Integer, String> execCommandForCat = ToolCommand.INSTANCE.execCommandForCat(replace$default, false, new Function1<String, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$readFromShell$2$res$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), " scc resume :", it);
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(it));
                }
            });
            Integer num = (Integer) execCommandForCat.first;
            if (num != null) {
            }
            Integer num2 = (Integer) execCommandForCat.first;
            if (num2 != null) {
            }
            Pdlog.m3273d(access$getTAG$p(INSTANCE), "readFromShell", replace$default, execCommandForCat);
        } catch (Exception unused) {
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object checkFirmwareVersion(String str, String str2, String str3, Continuation<? super Version> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3273d(access$getTAG$p(INSTANCE), "checkFirmwareVersion");
        UpdateManager.INSTANCE.checkVersion(new CheckUpdateRequestParams(null, null, str, str2, null, null, null, str3, 115, null), new Function1<VerionResult, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$checkFirmwareVersion$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VerionResult verionResult) {
                invoke2(verionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VerionResult it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                NetWorkLog.INSTANCE.mo3280i(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "UpdateManager.checkVersion " + it);
                if (it.getAvailable() && it.getVersion() != null) {
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Version version = it.getVersion();
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(version));
                    return;
                }
                CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m4510constructorimpl(null));
            }
        }, new Function1<Throwable, Unit>() { // from class: com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$checkFirmwareVersion$2$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                NetWorkLog.INSTANCE.mo3279e(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "checkFirmwareVersion > " + Log.getStackTraceString(it));
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(null));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
