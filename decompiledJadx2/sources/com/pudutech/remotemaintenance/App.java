package com.pudutech.remotemaintenance;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.util.Log;
import com.pudutech.light_network.HttpConfig;
import com.pudutech.remotemaintenance.net.IoTServerApiManager;
import com.pudutech.remotemaintenance.net.NetworkCallbackImpl;
import com.pudutech.remotemaintenance.net.ServerApiManager;
import com.pudutech.remotemaintenance.utils.CCrashHandler;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: App.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/App;", "Landroid/app/Application;", "()V", "hasTestFile", "", "getHasTestFile", "()Z", "hasTestFile$delegate", "Lkotlin/Lazy;", "testServerFile", "", "initIoTServerApiManager", "", "initPdlog", "initServerApiManager", "onCreate", "registerNetworkCallback", "Companion", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class App extends Application {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static App instance;
    private final String testServerFile = "/sdcard/TestServer";

    /* renamed from: hasTestFile$delegate, reason: from kotlin metadata */
    private final Lazy hasTestFile = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.remotemaintenance.App$hasTestFile$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            String str;
            str = App.this.testServerFile;
            return new File(str).exists();
        }
    });

    private final boolean getHasTestFile() {
        return ((Boolean) this.hasTestFile.getValue()).booleanValue();
    }

    public static final App getInstance() {
        App app = instance;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("instance");
        }
        return app;
    }

    private static final void setInstance(App app) {
        instance = app;
    }

    /* compiled from: App.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R,\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048\u0006@BX\u0087.¢\u0006\u0014\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/App$Companion;", "", "()V", "<set-?>", "Lcom/pudutech/remotemaintenance/App;", "instance", "instance$annotations", "getInstance", "()Lcom/pudutech/remotemaintenance/App;", "setInstance", "(Lcom/pudutech/remotemaintenance/App;)V", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void instance$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setInstance(App app) {
            App.instance = app;
        }

        public final App getInstance() {
            App app = App.instance;
            if (app == null) {
                Intrinsics.throwUninitializedPropertyAccessException("instance");
            }
            return app;
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerNetworkCallback();
        initPdlog();
        CCrashHandler.INSTANCE.getINSTANCE().init();
        initServerApiManager();
        initIoTServerApiManager();
    }

    private final void initPdlog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new App$initPdlog$1(null), 2, null);
    }

    private final void initServerApiManager() {
        Log.d("App", "isDebug = false testServerFile=" + getHasTestFile());
        ServerApiManager.INSTANCE.init(this);
        ServerApiManager.INSTANCE.setTestServer(getHasTestFile());
        HttpConfig.INSTANCE.setDebug(getHasTestFile());
    }

    private final void initIoTServerApiManager() {
        IoTServerApiManager.INSTANCE.init(this);
        IoTServerApiManager.INSTANCE.setTestServer(getHasTestFile());
    }

    private final void registerNetworkCallback() {
        NetworkCallbackImpl networkCallbackImpl = new NetworkCallbackImpl();
        NetworkRequest build = new NetworkRequest.Builder().build();
        Object systemService = getSystemService("connectivity");
        if (systemService != null) {
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
            }
            ((ConnectivityManager) systemService).registerNetworkCallback(build, networkCallbackImpl);
        }
    }
}
