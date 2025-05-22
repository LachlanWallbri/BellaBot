package com.pudutech.shared.lib_syntime;

import android.content.Context;
import android.content.IntentFilter;
import com.pudutech.base.Pdlog;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: SntpTimeSyncManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\u001b\u0010\u001d\u001a\u00020\u00172\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u0017H\u0016J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0013H\u0016J\b\u0010\"\u001a\u00020\u0017H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m3961d2 = {"Lcom/pudutech/shared/lib_syntime/SntpTimeSyncManager;", "Lcom/pudutech/shared/lib_syntime/SystemTimeSyncManager;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getMContext", "()Landroid/content/Context;", "mJob", "Lkotlinx/coroutines/Job;", "mNetWorkChangeReceiver", "Lcom/pudutech/shared/lib_syntime/SntpTimeSyncManager$NetWorkChangeReceiver;", "mScop", "Lkotlinx/coroutines/CoroutineScope;", "mSntpTimer", "Lcom/pudutech/shared/lib_syntime/SntpClient;", "onTimeSyncSuccess", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "nowMills", "", "getOnTimeSyncSuccess", "()Lkotlin/jvm/functions/Function1;", "setOnTimeSyncSuccess", "(Lkotlin/jvm/functions/Function1;)V", "bindNetStatus", "callMain", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clear", "startSync", "delayMills", "unbindNetStatus", "NetWorkChangeReceiver", "lib_syntime_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SntpTimeSyncManager implements SystemTimeSyncManager {
    private final String TAG;
    private final Context mContext;
    private Job mJob;
    private final NetWorkChangeReceiver mNetWorkChangeReceiver;
    private CoroutineScope mScop;
    private final SntpClient mSntpTimer;
    private Function1<? super Long, Unit> onTimeSyncSuccess;

    public SntpTimeSyncManager(Context mContext) {
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        this.mContext = mContext;
        this.TAG = "SntpTimeSyncManager";
        this.mSntpTimer = new SntpClient();
        this.mNetWorkChangeReceiver = new NetWorkChangeReceiver();
        this.mScop = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        Pdlog.m3273d(this.TAG, "init() CurTime = " + SystemTimeSetting.INSTANCE.formatDateOne(System.currentTimeMillis()) + " CurTimeZone = " + SystemTimeSetting.INSTANCE.getTimeZoneId());
        SystemTimeSetting systemTimeSetting = SystemTimeSetting.INSTANCE;
        Context applicationContext = this.mContext.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "mContext.applicationContext");
        systemTimeSetting.openAutoDateTime(applicationContext);
        bindNetStatus();
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final Function1<Long, Unit> getOnTimeSyncSuccess() {
        return this.onTimeSyncSuccess;
    }

    public final void setOnTimeSyncSuccess(Function1<? super Long, Unit> function1) {
        this.onTimeSyncSuccess = function1;
    }

    @Override // com.pudutech.shared.lib_syntime.SystemTimeSyncManager
    public void startSync(long delayMills) {
        List mutableListOf = CollectionsKt.mutableListOf(SystemTimeSetting.NTP_ALI_SERVER, SystemTimeSetting.NTP_GOOGLE_ANDROID_SERVER, SystemTimeSetting.NTP_ALI_ORG_SERVER, SystemTimeSetting.NTP_GOOGLE_SERVER);
        CoroutineScope coroutineScope = this.mScop;
        this.mJob = coroutineScope != null ? BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new SntpTimeSyncManager$startSync$1(this, delayMills, mutableListOf, null), 3, null) : null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object callMain$default(SntpTimeSyncManager sntpTimeSyncManager, long j, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            j = -1;
        }
        return sntpTimeSyncManager.callMain(j, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object callMain(long j, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new SntpTimeSyncManager$callMain$2(this, j, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private final void bindNetStatus() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            this.mContext.registerReceiver(this.mNetWorkChangeReceiver, intentFilter);
        } catch (Exception unused) {
        }
    }

    private final void unbindNetStatus() {
        try {
            this.mContext.unregisterReceiver(this.mNetWorkChangeReceiver);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SntpTimeSyncManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/shared/lib_syntime/SntpTimeSyncManager$NetWorkChangeReceiver;", "Lcom/pudutech/shared/lib_syntime/NetWorkChangeEvent;", "(Lcom/pudutech/shared/lib_syntime/SntpTimeSyncManager;)V", "onNetworkChange", "", "lib_syntime_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final class NetWorkChangeReceiver extends NetWorkChangeEvent {
        public NetWorkChangeReceiver() {
        }

        @Override // com.pudutech.shared.lib_syntime.NetWorkChangeEvent
        public void onNetworkChange() {
            if (SystemTimeSetting.INSTANCE.isConnected(SntpTimeSyncManager.this.getMContext())) {
                Pdlog.m3273d("SntpTimeSyncManager", "onNetworkChange() success");
                SntpTimeSyncManager.this.startSync(1000L);
            }
        }
    }

    @Override // com.pudutech.shared.lib_syntime.SystemTimeSyncManager
    public void clear() {
        unbindNetStatus();
        Job job = this.mJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        CoroutineScope coroutineScope = this.mScop;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.mJob = (Job) null;
        this.mScop = (CoroutineScope) null;
    }
}
