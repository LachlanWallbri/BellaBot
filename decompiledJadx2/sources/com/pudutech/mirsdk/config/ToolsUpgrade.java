package com.pudutech.mirsdk.config;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.AppUpdateContext;
import com.pudutech.lib_update.UpdateManager;
import com.pudutech.lib_update.listener.IShowInstallProgress;
import com.pudutech.lib_update.listener.IShowProgress2;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.SwitchVersionResult;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mirsdk.config.ToolsUpgrade;
import com.pudutech.mirsdk.update.ApiConstants;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ToolsUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u0003\n\u0002\b\u000b\b\u0000\u0018\u0000 62\u00020\u00012\u00020\u0002:\u00016B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\u001b\u001a\u00020\u001cH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010 J\u000e\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\rJ\u0016\u0010#\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\rJ\u000e\u0010$\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\rJ\u0016\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\rJ\u0018\u0010'\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\rH\u0002J\u0006\u0010)\u001a\u00020\u0019J \u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0016J\u0018\u0010/\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0016J\u0018\u00100\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0016J\u0018\u00101\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0016J\u0018\u00102\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0016J\u0018\u00103\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0016J(\u00104\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u00105\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\rH\u0002R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, m3961d2 = {"Lcom/pudutech/mirsdk/config/ToolsUpgrade;", "Lcom/pudutech/lib_update/listener/IShowProgress2;", "Lcom/pudutech/lib_update/listener/IShowInstallProgress;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "MIN_CLICK_DELAY_TIME", "", "getMIN_CLICK_DELAY_TIME", "()I", "setMIN_CLICK_DELAY_TIME", "(I)V", "TAG", "", "kotlin.jvm.PlatformType", "currentMapifyVersionCode", "getCurrentMapifyVersionCode", "setCurrentMapifyVersionCode", "lastClickTime", "", "getLastClickTime", "()J", "setLastClickTime", "(J)V", "mIsSilent", "", "mapifyJobFinished", "chkMapify", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "chkMapifyAndRGBDViewer", "isSilent", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppointVersionCode", "productName", "getCurrentVersionCode", "getProductInfo", "versionCode", "versionName", "isAppInstalled", "uri", "isNeedUpdate", "onFail", C3898x.f4338g, "", "versio_code", "versio_name", "onFailInstall", "onFinish", "onFinishInstall", "onStartInstall", "onStartProgress", "updateChkPack", TmpConstant.DATA_KEY_DEVICENAME, "Companion", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ToolsUpgrade implements IShowProgress2, IShowInstallProgress {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int updateTaskCount;
    private int MIN_CLICK_DELAY_TIME;
    private final String TAG;
    private final Context context;
    private int currentMapifyVersionCode;
    private long lastClickTime;
    private boolean mIsSilent;
    private boolean mapifyJobFinished;

    public ToolsUpgrade(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = ToolsUpgrade.class.getSimpleName();
        this.mIsSilent = true;
        this.MIN_CLICK_DELAY_TIME = 2000;
    }

    public final int getMIN_CLICK_DELAY_TIME() {
        return this.MIN_CLICK_DELAY_TIME;
    }

    public final void setMIN_CLICK_DELAY_TIME(int i) {
        this.MIN_CLICK_DELAY_TIME = i;
    }

    public final long getLastClickTime() {
        return this.lastClickTime;
    }

    public final void setLastClickTime(long j) {
        this.lastClickTime = j;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ToolsUpgrade.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdk/config/ToolsUpgrade$Companion;", "", "()V", "updateTaskCount", "", "getUpdateTaskCount", "()I", "setUpdateTaskCount", "(I)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getUpdateTaskCount() {
            return ToolsUpgrade.updateTaskCount;
        }

        public final void setUpdateTaskCount(int i) {
            ToolsUpgrade.updateTaskCount = i;
        }
    }

    public final int getCurrentMapifyVersionCode() {
        return this.currentMapifyVersionCode;
    }

    public final void setCurrentMapifyVersionCode(int i) {
        this.currentMapifyVersionCode = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object chkMapifyAndRGBDViewer(boolean z, Continuation<? super Unit> continuation) {
        ToolsUpgrade$chkMapifyAndRGBDViewer$1 toolsUpgrade$chkMapifyAndRGBDViewer$1;
        int i;
        if (continuation instanceof ToolsUpgrade$chkMapifyAndRGBDViewer$1) {
            toolsUpgrade$chkMapifyAndRGBDViewer$1 = (ToolsUpgrade$chkMapifyAndRGBDViewer$1) continuation;
            if ((toolsUpgrade$chkMapifyAndRGBDViewer$1.label & Integer.MIN_VALUE) != 0) {
                toolsUpgrade$chkMapifyAndRGBDViewer$1.label -= Integer.MIN_VALUE;
                Object obj = toolsUpgrade$chkMapifyAndRGBDViewer$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = toolsUpgrade$chkMapifyAndRGBDViewer$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    this.currentMapifyVersionCode = getCurrentVersionCode(this.context, ApiConstants.MAPIFY);
                    this.mIsSilent = z;
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.lastClickTime >= this.MIN_CLICK_DELAY_TIME) {
                        this.lastClickTime = currentTimeMillis;
                        File file = new File("/sdcard/TestServer");
                        StringBuilder sb = new StringBuilder();
                        sb.append("当前线程 ");
                        Thread currentThread = Thread.currentThread();
                        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                        sb.append(currentThread.getName());
                        sb.append(" 文件是否存在 ");
                        sb.append(file.exists());
                        Pdlog.m3273d("SDKService", sb.toString());
                        AppUpdateContext.init(this.context);
                        boolean isNeedUpdate = isNeedUpdate();
                        if (!isNeedUpdate) {
                            if (!this.mIsSilent) {
                                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new ToolsUpgrade$chkMapifyAndRGBDViewer$2(this, null), 2, null);
                            }
                        } else if (updateTaskCount == 0) {
                            toolsUpgrade$chkMapifyAndRGBDViewer$1.L$0 = this;
                            toolsUpgrade$chkMapifyAndRGBDViewer$1.Z$0 = z;
                            toolsUpgrade$chkMapifyAndRGBDViewer$1.J$0 = currentTimeMillis;
                            toolsUpgrade$chkMapifyAndRGBDViewer$1.L$1 = file;
                            toolsUpgrade$chkMapifyAndRGBDViewer$1.Z$1 = isNeedUpdate;
                            toolsUpgrade$chkMapifyAndRGBDViewer$1.label = 1;
                            if (chkMapify(toolsUpgrade$chkMapifyAndRGBDViewer$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (!this.mIsSilent) {
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new ToolsUpgrade$chkMapifyAndRGBDViewer$3(this, null), 2, null);
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    boolean z2 = toolsUpgrade$chkMapifyAndRGBDViewer$1.Z$1;
                    long j = toolsUpgrade$chkMapifyAndRGBDViewer$1.J$0;
                    boolean z3 = toolsUpgrade$chkMapifyAndRGBDViewer$1.Z$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        toolsUpgrade$chkMapifyAndRGBDViewer$1 = new ToolsUpgrade$chkMapifyAndRGBDViewer$1(this, continuation);
        Object obj2 = toolsUpgrade$chkMapifyAndRGBDViewer$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = toolsUpgrade$chkMapifyAndRGBDViewer$1.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    public final Object chkMapify(Continuation<? super Unit> continuation) {
        if (this.currentMapifyVersionCode < 171) {
            Pdlog.m3273d(this.TAG, "mapify 需要升级");
            updateChkPack(ApiConstants.MAPIFY_VERSION_NAME, String.valueOf(171), CommonKt.getWIFIMac(), ApiConstants.MAPIFY);
        } else {
            this.mapifyJobFinished = true;
            Pdlog.m3273d(this.TAG, "mapify当前的版本比服务器版本新,或者等于当前版本，不再下载");
        }
        return Unit.INSTANCE;
    }

    private final void updateChkPack(String versionName, final String versionCode, String MAC, final String productName) {
        UpdateManager.switchSoftWareVersion(new CheckUpdateRequestParams(versionName, versionCode, MAC, productName, null, null, null, null, DimensionsKt.HDPI, null), new Function1<SwitchVersionResult, Unit>() { // from class: com.pudutech.mirsdk.config.ToolsUpgrade$updateChkPack$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SwitchVersionResult switchVersionResult) {
                invoke2(switchVersionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SwitchVersionResult switchVersionResult) {
                Context context;
                String str;
                boolean z;
                String str2;
                String str3;
                String str4;
                Intrinsics.checkParameterIsNotNull(switchVersionResult, "switchVersionResult");
                Version[] version_list = switchVersionResult.getVersion_list();
                int appointVersionCode = ToolsUpgrade.this.getAppointVersionCode(productName);
                ToolsUpgrade toolsUpgrade = ToolsUpgrade.this;
                context = toolsUpgrade.context;
                int currentVersionCode = toolsUpgrade.getCurrentVersionCode(context, productName);
                if (version_list == null) {
                    Intrinsics.throwNpe();
                }
                boolean z2 = false;
                for (Version version : version_list) {
                    if (version == null) {
                        Intrinsics.throwNpe();
                    }
                    String version_code = version.getVersion_code();
                    str2 = ToolsUpgrade.this.TAG;
                    Pdlog.m3273d(str2, "服务器版本版本号 " + version_code + "  当前版本号 " + currentVersionCode + " 指定版本号 " + appointVersionCode);
                    if (appointVersionCode == Integer.parseInt(version_code)) {
                        if (currentVersionCode < Integer.parseInt(version_code)) {
                            str4 = ToolsUpgrade.this.TAG;
                            Pdlog.m3273d(str4, "updatePack2 版本名称 " + version.getVersion_name() + "  版本号 " + version.getVersion_code() + "  产品名称 " + productName + ' ');
                            VerionResult verionResult = new VerionResult(false, new Version(version.getUrl(), version.getMd5(), version.getVersion_name(), versionCode, version.getVersion_description(), version.getLevel()));
                            ToolsUpgrade.Companion companion = ToolsUpgrade.INSTANCE;
                            companion.setUpdateTaskCount(companion.getUpdateTaskCount() + 1);
                            UpdateManager updateManager = UpdateManager.INSTANCE;
                            ToolsUpgrade toolsUpgrade2 = ToolsUpgrade.this;
                            updateManager.downloadApkAndInstallSilent2(verionResult, toolsUpgrade2, toolsUpgrade2);
                            z2 = true;
                        } else {
                            ToolsUpgrade.this.mapifyJobFinished = true;
                            str3 = ToolsUpgrade.this.TAG;
                            Pdlog.m3273d(str3, "当前的版本比服务器版本新，不再下载");
                        }
                    }
                }
                if (z2) {
                    return;
                }
                str = ToolsUpgrade.this.TAG;
                Pdlog.m3273d(str, "没有找到指定版本，请检查服务器");
                ToolsUpgrade.this.mapifyJobFinished = true;
                z = ToolsUpgrade.this.mIsSilent;
                if (z) {
                    return;
                }
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48931(ToolsUpgrade.this.getProductInfo(productName), null), 2, null);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: ToolsUpgrade.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.config.ToolsUpgrade$updateChkPack$1$1", m3970f = "ToolsUpgrade.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.config.ToolsUpgrade$updateChkPack$1$1 */
            /* loaded from: classes5.dex */
            public static final class C48931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $productInfo;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5771p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C48931(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$productInfo = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48931 c48931 = new C48931(this.$productInfo, completion);
                    c48931.f5771p$ = (CoroutineScope) obj;
                    return c48931;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Context context;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5771p$;
                    context = ToolsUpgrade.this.context;
                    Toast.makeText(context, "The server is not support " + this.$productInfo + " download", 0).show();
                    return Unit.INSTANCE;
                }
            }
        }, new Function1<Throwable, Unit>() { // from class: com.pudutech.mirsdk.config.ToolsUpgrade$updateChkPack$2
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
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = ToolsUpgrade.this.TAG;
                Pdlog.m3274e(str, "checkSoftWareVersion " + Log.getStackTraceString(it));
            }
        });
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress2
    public void onFail(Throwable e, String versio_code, String versio_name) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        Intrinsics.checkParameterIsNotNull(versio_code, "versio_code");
        Intrinsics.checkParameterIsNotNull(versio_name, "versio_name");
        String productInfo = getProductInfo(versio_code, versio_name);
        this.mapifyJobFinished = true;
        updateTaskCount--;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onFail ");
        sb.append(productInfo);
        StackTraceElement[] stackTrace = e.getStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
        sb.append(ArraysKt.contentDeepToString(stackTrace));
        Pdlog.m3274e(str, sb.toString());
        if (this.mIsSilent) {
            return;
        }
        Toast.makeText(this.context, "download failed " + productInfo + ", Please try again or check the network and try again", 0).show();
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress2
    public void onFinish(String versio_code, String versio_name) {
        Intrinsics.checkParameterIsNotNull(versio_code, "versio_code");
        Intrinsics.checkParameterIsNotNull(versio_name, "versio_name");
        getProductInfo(versio_code, versio_name);
        Pdlog.m3273d(this.TAG, "onFinish  versio_code " + versio_code + " versio_name " + versio_name);
        if (this.mIsSilent) {
            return;
        }
        String productInfo = getProductInfo(versio_code, versio_name);
        Toast.makeText(this.context, "download Successed " + productInfo, 0).show();
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress2
    public void onStartProgress(String versio_code, String versio_name) {
        Intrinsics.checkParameterIsNotNull(versio_code, "versio_code");
        Intrinsics.checkParameterIsNotNull(versio_name, "versio_name");
        String productInfo = getProductInfo(versio_code, versio_name);
        Pdlog.m3273d(this.TAG, "start download  versio_code " + versio_code + " versio_name " + versio_name);
        if (this.mIsSilent) {
            return;
        }
        Toast.makeText(this.context, "start download " + productInfo, 0).show();
    }

    @Override // com.pudutech.lib_update.listener.IShowInstallProgress
    public void onFailInstall(String versio_code, String versio_name) {
        Intrinsics.checkParameterIsNotNull(versio_code, "versio_code");
        Intrinsics.checkParameterIsNotNull(versio_name, "versio_name");
        String productInfo = getProductInfo(versio_code, versio_name);
        updateTaskCount--;
        this.mapifyJobFinished = true;
        Pdlog.m3273d(this.TAG, "onFailInstall " + versio_code + "  " + versio_name + ' ');
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ToolsUpgrade$onFailInstall$1(this, productInfo, null), 2, null);
    }

    @Override // com.pudutech.lib_update.listener.IShowInstallProgress
    public void onFinishInstall(String versio_code, String versio_name) {
        Intrinsics.checkParameterIsNotNull(versio_code, "versio_code");
        Intrinsics.checkParameterIsNotNull(versio_name, "versio_name");
        String productInfo = getProductInfo(versio_code, versio_name);
        Pdlog.m3273d(this.TAG, "onFinishInstall " + versio_code + "  " + versio_name + ' ');
        updateTaskCount = updateTaskCount + (-1);
        this.mapifyJobFinished = true;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ToolsUpgrade$onFinishInstall$1(this, productInfo, null), 2, null);
    }

    @Override // com.pudutech.lib_update.listener.IShowInstallProgress
    public void onStartInstall(String versio_code, String versio_name) {
        Intrinsics.checkParameterIsNotNull(versio_code, "versio_code");
        Intrinsics.checkParameterIsNotNull(versio_name, "versio_name");
        String productInfo = getProductInfo(versio_code, versio_name);
        Pdlog.m3273d(this.TAG, "onStartInstall " + versio_code + "  " + versio_name + ' ');
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ToolsUpgrade$onStartInstall$1(this, productInfo, null), 2, null);
    }

    public final boolean isNeedUpdate() {
        return this.currentMapifyVersionCode < 171;
    }

    public final int getCurrentVersionCode(Context context, String productName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(productName, "productName");
        String str = (productName.hashCode() == -1081367520 && productName.equals(ApiConstants.MAPIFY)) ? "com.pudutech.mapify" : "";
        PackageManager packageManager = context.getPackageManager();
        int i = 0;
        try {
            if (!isAppInstalled(context, str)) {
                Log.d("ljk", "未安装" + productName + "工具");
            } else {
                i = packageManager.getPackageInfo(str, 1).versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }

    private final boolean isAppInstalled(Context context, String uri) {
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkExpressionValueIsNotNull(packageManager, "context.packageManager");
        try {
            packageManager.getPackageInfo(uri, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final int getAppointVersionCode(String productName) {
        Intrinsics.checkParameterIsNotNull(productName, "productName");
        return Intrinsics.areEqual(productName, ApiConstants.MAPIFY) ? 171 : 0;
    }

    public final String getProductInfo(String versionCode, String versionName) {
        Intrinsics.checkParameterIsNotNull(versionCode, "versionCode");
        Intrinsics.checkParameterIsNotNull(versionName, "versionName");
        return (Intrinsics.areEqual(versionCode, String.valueOf(171)) && Intrinsics.areEqual(versionName, ApiConstants.MAPIFY_VERSION_NAME)) ? "mapify_v1.5.3.6" : "";
    }

    public final String getProductInfo(String productName) {
        Intrinsics.checkParameterIsNotNull(productName, "productName");
        return Intrinsics.areEqual(productName, ApiConstants.MAPIFY) ? "mapify_v1.5.3.6" : "";
    }
}
