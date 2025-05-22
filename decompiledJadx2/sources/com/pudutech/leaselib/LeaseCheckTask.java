package com.pudutech.leaselib;

import android.content.IntentFilter;
import android.util.Log;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import com.google.gson.Gson;
import com.pudutech.leaselib.LeaseCheckTask;
import com.pudutech.leaselib.bean.LeaseData;
import com.pudutech.leaselib.bean.ReqLeaseData;
import com.pudutech.leaselib.net.ApiService;
import com.pudutech.leaselib.net.HttpResult;
import com.pudutech.leaselib.net.NetWorkChangeReceiver;
import com.pudutech.leaselib.utils.EncryptUtils;
import com.pudutech.leaselib.utils.FileIOUtils;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.log.NetWorkLog;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LeaseCheckTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 02\u00020\u0001:\u000201B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u0013J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u0012\u0010$\u001a\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010\u0016H\u0002J\b\u0010&\u001a\u00020\u001fH\u0002J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0016H\u0002J\u001a\u0010(\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u00162\b\b\u0002\u0010)\u001a\u00020\u0013H\u0002J\u000e\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u001aJ\b\u0010,\u001a\u00020\u001fH\u0002J\b\u0010-\u001a\u00020\u001fH\u0002J\b\u0010.\u001a\u00020\u001fH\u0002J\b\u0010/\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"Lcom/pudutech/leaselib/LeaseCheckTask;", "", "()V", "SAVE_SEC_INTERVAL", "", "TAG", "", "kotlin.jvm.PlatformType", "api", "Lcom/pudutech/leaselib/net/ApiService;", "getApi", "()Lcom/pudutech/leaselib/net/ApiService;", "api$delegate", "Lkotlin/Lazy;", "countdownDisposable", "Lio/reactivex/disposables/Disposable;", "gson", "Lcom/google/gson/Gson;", "isInitFromServer", "", "isNeedRestartTask", "leaseData", "Lcom/pudutech/leaselib/bean/LeaseData;", "netWorkChangeReceiverReceiver", "Lcom/pudutech/leaselib/net/NetWorkChangeReceiver;", "onLeaseStatusChangeListener", "Lcom/pudutech/leaselib/OnLeaseStatusChangeListener;", "startCheckTask", "tempCount", "checkIsOverdue", "deleteData", "", "getDataFromLocal", "Lio/reactivex/Observable;", "Lcom/pudutech/leaselib/LeaseCheckTask$LeaseDataWrap;", "getDataFromServer", "notify", "data", "restartIfNeed", "saveData", "saveIfNeed", "isFocus", "startCheck", "listener", "startCountdown", "startNetReceiver", "stopCountdown", "stopNetReceiver", "Companion", "LeaseDataWrap", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LeaseCheckTask {
    private static final String AES_PADDING = "AES/CBC/PKCS5Padding";
    private static final String FILE_PATH = "/sdcard/pdconfig/lease";
    private static final String KEY = "793P3Mj9rUTNWil3";
    private Disposable countdownDisposable;
    private volatile boolean isInitFromServer;
    private volatile boolean isNeedRestartTask;
    private volatile LeaseData leaseData;
    private NetWorkChangeReceiver netWorkChangeReceiverReceiver;
    private OnLeaseStatusChangeListener onLeaseStatusChangeListener;
    private Disposable startCheckTask;
    private int tempCount;

    /* renamed from: IV */
    private static final byte[] f5367IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final String TAG = getClass().getSimpleName();
    private final Gson gson = new Gson();
    private final int SAVE_SEC_INTERVAL = 2;

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private final Lazy api = LazyKt.lazy(new Function0<ApiService>() { // from class: com.pudutech.leaselib.LeaseCheckTask$api$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ApiService invoke() {
            return (ApiService) PdNetworkManager.f10310INSTANCE.createService(ApiService.class);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final ApiService getApi() {
        return (ApiService) this.api.getValue();
    }

    public static final /* synthetic */ OnLeaseStatusChangeListener access$getOnLeaseStatusChangeListener$p(LeaseCheckTask leaseCheckTask) {
        OnLeaseStatusChangeListener onLeaseStatusChangeListener = leaseCheckTask.onLeaseStatusChangeListener;
        if (onLeaseStatusChangeListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onLeaseStatusChangeListener");
        }
        return onLeaseStatusChangeListener;
    }

    public final void startCheck(OnLeaseStatusChangeListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onLeaseStatusChangeListener = listener;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3278d(TAG, "startCheck " + this.leaseData + " , isInitFromServer = " + this.isInitFromServer);
        Disposable disposable = this.startCheckTask;
        if (disposable != null) {
            if (disposable == null) {
                Intrinsics.throwNpe();
            }
            if (!disposable.isDisposed()) {
                NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                String TAG2 = this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                netWorkLog2.mo3278d(TAG2, "startCheck , is check now !!!");
                return;
            }
        }
        if (this.leaseData != null && this.isInitFromServer) {
            LeaseData leaseData = this.leaseData;
            if (leaseData == null) {
                Intrinsics.throwNpe();
            }
            notify(leaseData);
            return;
        }
        this.startCheckTask = getDataFromServer().subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LeaseDataWrap>() { // from class: com.pudutech.leaselib.LeaseCheckTask$startCheck$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(LeaseCheckTask.LeaseDataWrap leaseDataWrap) {
                String TAG3;
                LeaseCheckTask.this.notify(leaseDataWrap.getLeaseData());
                if (leaseDataWrap.getLeaseData() == null) {
                    NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                    TAG3 = LeaseCheckTask.this.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                    netWorkLog3.mo3279e(TAG3, "startCheck data is null");
                    return;
                }
                LeaseData leaseData2 = leaseDataWrap.getLeaseData();
                if (leaseData2 == null) {
                    Intrinsics.throwNpe();
                }
                if (leaseData2.getUseType() != UseType.BUY.getType()) {
                    LeaseData leaseData3 = leaseDataWrap.getLeaseData();
                    if (leaseData3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (leaseData3.getRemainingTime() > 0) {
                        LeaseCheckTask.this.startCountdown();
                    }
                }
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.leaselib.LeaseCheckTask$startCheck$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                String TAG3;
                NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                TAG3 = LeaseCheckTask.this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                String stackTraceString = Log.getStackTraceString(th);
                Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(it)");
                netWorkLog3.mo3279e(TAG3, stackTraceString);
                LeaseCheckTask.this.startCheckTask = (Disposable) null;
                LeaseCheckTask.this.restartIfNeed();
            }
        }, new Action() { // from class: com.pudutech.leaselib.LeaseCheckTask$startCheck$3
            @Override // io.reactivex.functions.Action
            public final void run() {
                LeaseCheckTask.this.startCheckTask = (Disposable) null;
                LeaseCheckTask.this.restartIfNeed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartIfNeed() {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3278d(TAG, "restartIfNeed");
        if (this.isNeedRestartTask) {
            this.isNeedRestartTask = false;
            OnLeaseStatusChangeListener onLeaseStatusChangeListener = this.onLeaseStatusChangeListener;
            if (onLeaseStatusChangeListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onLeaseStatusChangeListener");
            }
            startCheck(onLeaseStatusChangeListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNetReceiver() {
        if (this.netWorkChangeReceiverReceiver == null) {
            this.netWorkChangeReceiverReceiver = new NetWorkChangeReceiver();
            NetWorkChangeReceiver netWorkChangeReceiver = this.netWorkChangeReceiverReceiver;
            if (netWorkChangeReceiver == null) {
                Intrinsics.throwNpe();
            }
            netWorkChangeReceiver.setOnNetWorkConnect(new Function0<Unit>() { // from class: com.pudutech.leaselib.LeaseCheckTask$startNetReceiver$1
                /* JADX INFO: Access modifiers changed from: package-private */
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
                    String TAG;
                    boolean z;
                    boolean z2;
                    Disposable disposable;
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    TAG = LeaseCheckTask.this.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    StringBuilder sb = new StringBuilder();
                    sb.append("is net connect, is server init = ");
                    z = LeaseCheckTask.this.isInitFromServer;
                    sb.append(z);
                    netWorkLog.mo3278d(TAG, sb.toString());
                    z2 = LeaseCheckTask.this.isInitFromServer;
                    if (z2) {
                        return;
                    }
                    disposable = LeaseCheckTask.this.startCheckTask;
                    if (disposable != null && !disposable.isDisposed()) {
                        LeaseCheckTask.this.isNeedRestartTask = true;
                    } else {
                        LeaseCheckTask leaseCheckTask = LeaseCheckTask.this;
                        leaseCheckTask.startCheck(LeaseCheckTask.access$getOnLeaseStatusChangeListener$p(leaseCheckTask));
                    }
                }
            });
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            try {
                LeaseSdk.INSTANCE.getContext().registerReceiver(this.netWorkChangeReceiverReceiver, intentFilter);
            } catch (Throwable th) {
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                String TAG = this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                String stackTraceString = Log.getStackTraceString(th);
                Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
                netWorkLog.mo3279e(TAG, stackTraceString);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopNetReceiver() {
        try {
            NetWorkChangeReceiver netWorkChangeReceiver = this.netWorkChangeReceiverReceiver;
            if (netWorkChangeReceiver != null) {
                LeaseSdk.INSTANCE.getContext().unregisterReceiver(netWorkChangeReceiver);
            }
        } catch (Throwable th) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            String stackTraceString = Log.getStackTraceString(th);
            Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
            netWorkLog.mo3279e(TAG, stackTraceString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notify(LeaseData data) {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3278d(TAG, "notify " + data);
        int useType = data != null ? data.getUseType() : -1;
        if (useType == UseType.BUY.getType()) {
            OnLeaseStatusChangeListener onLeaseStatusChangeListener = this.onLeaseStatusChangeListener;
            if (onLeaseStatusChangeListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onLeaseStatusChangeListener");
            }
            onLeaseStatusChangeListener.onStatusChange(UseType.BUY, 0L, 0L);
            return;
        }
        if (useType == UseType.TRIAL.getType()) {
            OnLeaseStatusChangeListener onLeaseStatusChangeListener2 = this.onLeaseStatusChangeListener;
            if (onLeaseStatusChangeListener2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onLeaseStatusChangeListener");
            }
            UseType useType2 = UseType.TRIAL;
            if (data == null) {
                Intrinsics.throwNpe();
            }
            onLeaseStatusChangeListener2.onStatusChange(useType2, data.getUseEndTime(), data.getRemainingTime());
            return;
        }
        if (useType == UseType.LEASE.getType()) {
            OnLeaseStatusChangeListener onLeaseStatusChangeListener3 = this.onLeaseStatusChangeListener;
            if (onLeaseStatusChangeListener3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onLeaseStatusChangeListener");
            }
            UseType useType3 = UseType.LEASE;
            if (data == null) {
                Intrinsics.throwNpe();
            }
            onLeaseStatusChangeListener3.onStatusChange(useType3, data.getUseEndTime(), data.getRemainingTime());
            return;
        }
        NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
        String TAG2 = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        StringBuilder sb = new StringBuilder();
        sb.append("startCheck data type is not define ");
        sb.append(data != null ? Integer.valueOf(data.getUseType()) : null);
        netWorkLog2.mo3279e(TAG2, sb.toString());
        OnLeaseStatusChangeListener onLeaseStatusChangeListener4 = this.onLeaseStatusChangeListener;
        if (onLeaseStatusChangeListener4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onLeaseStatusChangeListener");
        }
        onLeaseStatusChangeListener4.onStatusChange(UseType.UNKNOW, 0L, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCountdown() {
        if (this.countdownDisposable != null) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3278d(TAG, "startCountdown countdownDisposable is not null , do not need restart");
            return;
        }
        this.countdownDisposable = Observable.interval(0L, 1L, TimeUnit.SECONDS).map((Function) new Function<T, R>() { // from class: com.pudutech.leaselib.LeaseCheckTask$startCountdown$1
            @Override // io.reactivex.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return Boolean.valueOf(apply((Long) obj));
            }

            public final boolean apply(Long it) {
                LeaseData leaseData;
                LeaseData leaseData2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                leaseData = LeaseCheckTask.this.leaseData;
                if (leaseData == null) {
                    LeaseCheckTask.this.stopCountdown();
                } else {
                    leaseData2 = LeaseCheckTask.this.leaseData;
                    if (leaseData2 == null) {
                        Intrinsics.throwNpe();
                    }
                    leaseData2.setRemainingTime(leaseData2.getRemainingTime() - 1);
                    if (leaseData2.getRemainingTime() < 0) {
                        LeaseCheckTask.this.saveIfNeed(leaseData2, true);
                        return true;
                    }
                    LeaseCheckTask.saveIfNeed$default(LeaseCheckTask.this, leaseData2, false, 2, null);
                }
                return false;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.leaselib.LeaseCheckTask$startCountdown$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Boolean it) {
                LeaseData leaseData;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    LeaseCheckTask.this.stopCountdown();
                    LeaseCheckTask leaseCheckTask = LeaseCheckTask.this;
                    leaseData = leaseCheckTask.leaseData;
                    leaseCheckTask.notify(leaseData);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.leaselib.LeaseCheckTask$startCountdown$3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                String TAG2;
                NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                TAG2 = LeaseCheckTask.this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                String stackTraceString = Log.getStackTraceString(th);
                Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(it)");
                netWorkLog2.mo3279e(TAG2, stackTraceString);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void saveIfNeed$default(LeaseCheckTask leaseCheckTask, LeaseData leaseData, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        leaseCheckTask.saveIfNeed(leaseData, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveIfNeed(final LeaseData data, boolean isFocus) {
        if (isFocus) {
            this.tempCount = this.SAVE_SEC_INTERVAL;
        }
        int i = this.tempCount;
        if (i < this.SAVE_SEC_INTERVAL) {
            this.tempCount = i + 1;
        } else {
            this.tempCount = 0;
            Observable.fromCallable(new Callable<T>() { // from class: com.pudutech.leaselib.LeaseCheckTask$saveIfNeed$disposable$1
                @Override // java.util.concurrent.Callable
                public /* bridge */ /* synthetic */ Object call() {
                    return Boolean.valueOf(call());
                }

                @Override // java.util.concurrent.Callable
                public final boolean call() {
                    boolean saveData;
                    saveData = LeaseCheckTask.this.saveData(data);
                    return saveData;
                }
            }).subscribeOn(Schedulers.m3958io()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.leaselib.LeaseCheckTask$saveIfNeed$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Boolean bool) {
                    String TAG;
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    TAG = LeaseCheckTask.this.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    netWorkLog.mo3278d(TAG, "saveData result " + bool);
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.leaselib.LeaseCheckTask$saveIfNeed$disposable$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    String TAG;
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    TAG = LeaseCheckTask.this.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    String stackTraceString = Log.getStackTraceString(th);
                    Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(it)");
                    netWorkLog.mo3279e(TAG, stackTraceString);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopCountdown() {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3278d(TAG, "stopCountdown");
        Disposable disposable = this.countdownDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.countdownDisposable = (Disposable) null;
    }

    private final Observable<LeaseDataWrap> getDataFromServer() {
        Observable flatMap = getDataFromLocal().observeOn(AndroidSchedulers.mainThread()).flatMap((Function) new Function<T, ObservableSource<? extends R>>() { // from class: com.pudutech.leaselib.LeaseCheckTask$getDataFromServer$1
            @Override // io.reactivex.functions.Function
            public final Observable<LeaseCheckTask.LeaseDataWrap> apply(LeaseCheckTask.LeaseDataWrap it) {
                ApiService api;
                Intrinsics.checkParameterIsNotNull(it, "it");
                LeaseCheckTask.this.notify(it.getLeaseData());
                ReqLeaseData reqLeaseData = new ReqLeaseData();
                reqLeaseData.setHardver(LeaseSdk.INSTANCE.getHardVersion());
                reqLeaseData.setSoftver(LeaseSdk.INSTANCE.getSoftVersion());
                reqLeaseData.setMac(LeaseSdk.INSTANCE.getMac());
                reqLeaseData.setTimestamp(System.currentTimeMillis());
                LeaseData leaseData = it.getLeaseData();
                if (leaseData != null) {
                    reqLeaseData.setUseEndTime(leaseData.getUseEndTime());
                    reqLeaseData.setUseType(leaseData.getUseType());
                    reqLeaseData.setRemainingTime(leaseData.getRemainingTime());
                }
                api = LeaseCheckTask.this.getApi();
                return ApiService.DefaultImpls.getLeaseState$default(api, reqLeaseData, null, 2, null).subscribeOn(Schedulers.m3958io()).flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: com.pudutech.leaselib.LeaseCheckTask$getDataFromServer$1.2
                    @Override // io.reactivex.functions.Function
                    public final Observable<LeaseCheckTask.LeaseDataWrap> apply(HttpResult<LeaseData> it2) {
                        String TAG;
                        LeaseData leaseData2;
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                        TAG = LeaseCheckTask.this.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                        netWorkLog.mo3278d(TAG, "getLeaseState from server = " + it2);
                        if (it2.getCode() == 0 && it2.getData() != null) {
                            LeaseCheckTask.this.stopCountdown();
                            LeaseCheckTask.this.leaseData = it2.getData();
                            LeaseCheckTask.this.saveData(it2.getData());
                        }
                        LeaseCheckTask.this.isInitFromServer = true;
                        LeaseCheckTask.this.stopNetReceiver();
                        leaseData2 = LeaseCheckTask.this.leaseData;
                        return Observable.just(new LeaseCheckTask.LeaseDataWrap(leaseData2));
                    }
                }).onErrorReturn(new Function<Throwable, LeaseCheckTask.LeaseDataWrap>() { // from class: com.pudutech.leaselib.LeaseCheckTask$getDataFromServer$1.3
                    @Override // io.reactivex.functions.Function
                    public final LeaseCheckTask.LeaseDataWrap apply(Throwable it2) {
                        String TAG;
                        LeaseData leaseData2;
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                        TAG = LeaseCheckTask.this.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                        netWorkLog.mo3279e(TAG, "get from server error : " + Log.getStackTraceString(it2));
                        LeaseCheckTask.this.startNetReceiver();
                        leaseData2 = LeaseCheckTask.this.leaseData;
                        return new LeaseCheckTask.LeaseDataWrap(leaseData2);
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "getDataFromLocal()\n     …          }\n            }");
        return flatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean saveData(LeaseData data) {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3278d(TAG, "saveData " + data);
        String toJson = this.gson.toJson(data);
        Intrinsics.checkExpressionValueIsNotNull(toJson, "toJson");
        Charset charset = Charsets.UTF_8;
        if (toJson == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = toJson.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] bytes2 = KEY.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        return FileIOUtils.writeFileFromBytesByStream(FILE_PATH, EncryptUtils.encryptAES(bytes, bytes2, "AES/CBC/PKCS5Padding", f5367IV));
    }

    private final void deleteData() {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3278d(TAG, "deleteData");
        new File(FILE_PATH).delete();
    }

    private final Observable<LeaseDataWrap> getDataFromLocal() {
        Observable<LeaseDataWrap> subscribeOn = Observable.fromCallable(new Callable<T>() { // from class: com.pudutech.leaselib.LeaseCheckTask$getDataFromLocal$1
            @Override // java.util.concurrent.Callable
            public final LeaseCheckTask.LeaseDataWrap call() {
                LeaseData leaseData;
                String TAG;
                LeaseData leaseData2;
                byte[] bArr;
                String TAG2;
                String TAG3;
                Gson gson;
                LeaseData leaseData3;
                String TAG4;
                leaseData = LeaseCheckTask.this.leaseData;
                if (leaseData == null) {
                    byte[] readFile2BytesByStream = FileIOUtils.readFile2BytesByStream("/sdcard/pdconfig/lease");
                    byte[] bytes = "793P3Mj9rUTNWil3".getBytes(Charsets.UTF_8);
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                    bArr = LeaseCheckTask.f5367IV;
                    byte[] decryptAES = EncryptUtils.decryptAES(readFile2BytesByStream, bytes, JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD, bArr);
                    String str = decryptAES == null ? null : new String(decryptAES, Charsets.UTF_8);
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    TAG2 = LeaseCheckTask.this.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    netWorkLog.mo3278d(TAG2, "decryptAES = " + str);
                    LeaseData leaseData4 = (LeaseData) null;
                    String str2 = str;
                    if (!(str2 == null || StringsKt.isBlank(str2))) {
                        try {
                            gson = LeaseCheckTask.this.gson;
                            leaseData3 = (LeaseData) gson.fromJson(str, (Class) LeaseData.class);
                        } catch (Exception e) {
                            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                            TAG3 = LeaseCheckTask.this.TAG;
                            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                            String stackTraceString = Log.getStackTraceString(e);
                            Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
                            netWorkLog2.mo3279e(TAG3, stackTraceString);
                        }
                        LeaseCheckTask.this.leaseData = leaseData3;
                        NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                        TAG4 = LeaseCheckTask.this.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                        netWorkLog3.mo3278d(TAG4, "getDataFromLocal " + leaseData3);
                    }
                    leaseData3 = leaseData4;
                    LeaseCheckTask.this.leaseData = leaseData3;
                    NetWorkLog netWorkLog32 = NetWorkLog.INSTANCE;
                    TAG4 = LeaseCheckTask.this.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                    netWorkLog32.mo3278d(TAG4, "getDataFromLocal " + leaseData3);
                } else {
                    NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
                    TAG = LeaseCheckTask.this.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    netWorkLog4.mo3278d(TAG, "leaseData not null ,do not need get from local");
                }
                leaseData2 = LeaseCheckTask.this.leaseData;
                return new LeaseCheckTask.LeaseDataWrap(leaseData2);
            }
        }).onErrorReturn(new Function<Throwable, LeaseDataWrap>() { // from class: com.pudutech.leaselib.LeaseCheckTask$getDataFromLocal$2
            @Override // io.reactivex.functions.Function
            public final LeaseCheckTask.LeaseDataWrap apply(Throwable it) {
                String TAG;
                Intrinsics.checkParameterIsNotNull(it, "it");
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                TAG = LeaseCheckTask.this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                String stackTraceString = Log.getStackTraceString(it);
                Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(it)");
                netWorkLog.mo3279e(TAG, stackTraceString);
                return new LeaseCheckTask.LeaseDataWrap(null, 1, 0 == true ? 1 : 0);
            }
        }).subscribeOn(Schedulers.m3958io());
        Intrinsics.checkExpressionValueIsNotNull(subscribeOn, "Observable.fromCallable …scribeOn(Schedulers.io())");
        return subscribeOn;
    }

    public final boolean checkIsOverdue() {
        LeaseData leaseData;
        if (this.leaseData == null) {
            return false;
        }
        LeaseData leaseData2 = this.leaseData;
        if ((leaseData2 == null || leaseData2.getUseType() != UseType.TRIAL.getType()) && ((leaseData = this.leaseData) == null || leaseData.getUseType() != UseType.LEASE.getType())) {
            return false;
        }
        LeaseData leaseData3 = this.leaseData;
        if (leaseData3 == null) {
            Intrinsics.throwNpe();
        }
        return leaseData3.getRemainingTime() <= 0;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: LeaseCheckTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/leaselib/LeaseCheckTask$LeaseDataWrap;", "", "leaseData", "Lcom/pudutech/leaselib/bean/LeaseData;", "(Lcom/pudutech/leaselib/bean/LeaseData;)V", "getLeaseData", "()Lcom/pudutech/leaselib/bean/LeaseData;", "setLeaseData", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class LeaseDataWrap {
        private LeaseData leaseData;

        public LeaseDataWrap() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ LeaseDataWrap copy$default(LeaseDataWrap leaseDataWrap, LeaseData leaseData, int i, Object obj) {
            if ((i & 1) != 0) {
                leaseData = leaseDataWrap.leaseData;
            }
            return leaseDataWrap.copy(leaseData);
        }

        /* renamed from: component1, reason: from getter */
        public final LeaseData getLeaseData() {
            return this.leaseData;
        }

        public final LeaseDataWrap copy(LeaseData leaseData) {
            return new LeaseDataWrap(leaseData);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof LeaseDataWrap) && Intrinsics.areEqual(this.leaseData, ((LeaseDataWrap) other).leaseData);
            }
            return true;
        }

        public int hashCode() {
            LeaseData leaseData = this.leaseData;
            if (leaseData != null) {
                return leaseData.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "LeaseDataWrap(leaseData=" + this.leaseData + ")";
        }

        public LeaseDataWrap(LeaseData leaseData) {
            this.leaseData = leaseData;
        }

        public /* synthetic */ LeaseDataWrap(LeaseData leaseData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? (LeaseData) null : leaseData);
        }

        public final LeaseData getLeaseData() {
            return this.leaseData;
        }

        public final void setLeaseData(LeaseData leaseData) {
            this.leaseData = leaseData;
        }
    }
}
