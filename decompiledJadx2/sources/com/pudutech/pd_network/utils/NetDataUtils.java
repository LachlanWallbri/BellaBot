package com.pudutech.pd_network.utils;

import android.content.Context;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: NetDataUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0007\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u000e\u0010\u000e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004J\b\u0010\u0013\u001a\u00020\u0004H\u0002J\u0012\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0016\u001a\u00020\u0004H\u0002J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\u0004J\u0006\u0010\u0019\u001a\u00020\u0004J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, m3961d2 = {"Lcom/pudutech/pd_network/utils/NetDataUtils;", "", "()V", "MAC_DEFAULT", "", "TAG", "WIFI_MAC_FILE_PATH", "btMac", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "forceMac", "mMac", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "mac", "getMac", "getMacFromFile", "path", "getWIFIMac", "init", "pid", "pidOrMac", "saveMacFromFile", "waitFor", "", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "process", "Ljava/lang/Process;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NetDataUtils {
    private static final String WIFI_MAC_FILE_PATH = "/sdcard/pudu/config/wifi_mac.config";
    public static Context context;
    public static final NetDataUtils INSTANCE = new NetDataUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String MAC_DEFAULT = "02:00:00:00:00:00";
    private static String forceMac = "";
    private static String mMac = "";
    private static String btMac = "";

    public final String pid() {
        return "";
    }

    private NetDataUtils() {
    }

    public final Context getContext() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "<set-?>");
        context = context2;
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2;
    }

    public final void forceMac(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        forceMac = mac;
    }

    public final String mac() {
        if (forceMac.length() > 0) {
            return forceMac;
        }
        if (mMac.length() == 0) {
            mMac = getMac();
        }
        return mMac;
    }

    public final String pidOrMac() {
        return mac();
    }

    private final String getMac() {
        String wIFIMac = getWIFIMac();
        NetWorkLog.INSTANCE.mo3281w(TAG, "hardwareAddress :" + wIFIMac);
        if ((wIFIMac.length() == 0) || Intrinsics.areEqual(wIFIMac, MAC_DEFAULT)) {
            String macFromFile = getMacFromFile(WIFI_MAC_FILE_PATH);
            NetWorkLog.INSTANCE.mo3281w(TAG, "wifiMac from file read :" + macFromFile);
            return macFromFile;
        }
        saveMacFromFile(wIFIMac, WIFI_MAC_FILE_PATH);
        NetWorkLog.INSTANCE.mo3281w(TAG, "save wifiMac " + wIFIMac + " to file success");
        return wIFIMac;
    }

    private final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb2, "mac_str.toString()");
                    return sb2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private final String getMacFromFile(String path) {
        try {
            File file = new File(path);
            if (file.exists() && !file.isDirectory()) {
                String readText = FilesKt.readText(file, Charsets.UTF_8);
                NetWorkLog.INSTANCE.mo3278d(TAG, "read wifi mac form file :" + readText);
                return readText;
            }
            NetWorkLog.INSTANCE.mo3278d(TAG, "macFile is not exist");
            return "";
        } catch (Exception e) {
            NetWorkLog.INSTANCE.mo3278d(TAG, "readText fail " + e.getMessage());
            return "";
        }
    }

    private final void saveMacFromFile(String mac, String path) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new NetDataUtils$saveMacFromFile$1(path, mac, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object btMac(Continuation<? super String> continuation) {
        NetDataUtils$btMac$1 netDataUtils$btMac$1;
        int i;
        if (continuation instanceof NetDataUtils$btMac$1) {
            netDataUtils$btMac$1 = (NetDataUtils$btMac$1) continuation;
            if ((netDataUtils$btMac$1.label & Integer.MIN_VALUE) != 0) {
                netDataUtils$btMac$1.label -= Integer.MIN_VALUE;
                Object obj = netDataUtils$btMac$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = netDataUtils$btMac$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    String str = btMac;
                    if (!(str.length() == 0)) {
                        return str;
                    }
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    NetDataUtils$btMac$2$1 netDataUtils$btMac$2$1 = new NetDataUtils$btMac$2$1(null);
                    netDataUtils$btMac$1.L$0 = this;
                    netDataUtils$btMac$1.label = 1;
                    obj = BuildersKt.withContext(io2, netDataUtils$btMac$2$1, netDataUtils$btMac$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return (String) obj;
            }
        }
        netDataUtils$btMac$1 = new NetDataUtils$btMac$1(this, continuation);
        Object obj2 = netDataUtils$btMac$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = netDataUtils$btMac$1.label;
        if (i != 0) {
        }
        return (String) obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean waitFor(long timeout, TimeUnit unit, Process process) {
        long nanoTime = System.nanoTime();
        long nanos = unit.toNanos(timeout);
        do {
            try {
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException unused) {
                if (nanos > 0) {
                    Thread.sleep(Math.min(TimeUnit.NANOSECONDS.toMillis(nanos) + 1, 100L));
                }
                nanos = unit.toNanos(timeout) - (System.nanoTime() - nanoTime);
            }
        } while (nanos > 0);
        return false;
    }
}
