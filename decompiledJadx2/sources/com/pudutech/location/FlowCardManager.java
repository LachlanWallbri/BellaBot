package com.pudutech.location;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.location.net.FlowCardStatusRes;
import com.pudutech.location.net.NetWorkManager;
import com.pudutech.location.utils.FlowCardInfo;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* compiled from: FlowCardManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\f\u001a\u00020\r2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u000fJ)\u0010\u0014\u001a\u00020\r2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u000fJ\u0006\u0010\u0015\u001a\u00020\rJ5\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\u00182#\u0010\u000e\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\r0\u000fJ\b\u0010\u001b\u001a\u00020\rH\u0016J\b\u0010\u001c\u001a\u00020\rH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016J\u0010\u0010\u001e\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/location/FlowCardManager;", "Lcom/pudutech/location/LifecycleManager;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "TAG", "", "kotlin.jvm.PlatformType", "mContext", "Landroid/content/Context;", "mNetWorkManager", "Lcom/pudutech/location/net/NetWorkManager;", "activateFlowCard", "", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "success", "activateFlowCardSingle", "cancelNetTask", "getStatusFlowCardSingle", "delay", "", "Lcom/pudutech/location/net/FlowCardStatusRes;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "onCreate", "onDestroy", "onStart", "printStatusInfo", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FlowCardManager extends LifecycleManager {
    private final String TAG;
    private Context mContext;
    private NetWorkManager mNetWorkManager;

    @Override // com.pudutech.location.LifecycleManager
    public void onCreate() {
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onDestroy() {
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onStart() {
    }

    public FlowCardManager(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.TAG = getClass().getSimpleName();
        addLifecycle(activity);
        createScope();
        Context applicationContext = activity.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "activity.applicationContext");
        this.mContext = applicationContext;
        Context applicationContext2 = activity.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "activity.applicationContext");
        this.mNetWorkManager = new NetWorkManager(applicationContext2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    public final void activateFlowCard(Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = FlowCardInfo.INSTANCE.getSimSerialNumber(this.mContext);
        if (!TextUtils.isEmpty((String) objectRef.element)) {
            CoroutineScope mScope = getMScope();
            setMJob(mScope != null ? BuildersKt__Builders_commonKt.launch$default(mScope, null, null, new FlowCardManager$activateFlowCard$1(this, objectRef, callback, null), 3, null) : null);
        } else {
            callback.invoke(false);
            Pdlog.m3273d(this.TAG, "activateFlowCard simSerialNumber is null");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    public final void activateFlowCardSingle(Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = FlowCardInfo.INSTANCE.getSimSerialNumber(this.mContext);
        if (!TextUtils.isEmpty((String) objectRef.element)) {
            CoroutineScope mScope = getMScope();
            setMJob(mScope != null ? BuildersKt__Builders_commonKt.launch$default(mScope, null, null, new FlowCardManager$activateFlowCardSingle$1(this, objectRef, callback, null), 3, null) : null);
        } else {
            Pdlog.m3273d(this.TAG, "activateFlowCardSingle simSerialNumber is null");
            callback.invoke(false);
        }
    }

    public static /* synthetic */ void getStatusFlowCardSingle$default(FlowCardManager flowCardManager, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        flowCardManager.getStatusFlowCardSingle(j, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.String] */
    public final void getStatusFlowCardSingle(long delay, Function1<? super FlowCardStatusRes, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = FlowCardInfo.INSTANCE.getSimSerialNumber(this.mContext);
        if (!TextUtils.isEmpty((String) objectRef.element)) {
            CoroutineScope mScope = getMScope();
            setMJob(mScope != null ? BuildersKt__Builders_commonKt.launch$default(mScope, null, null, new FlowCardManager$getStatusFlowCardSingle$1(this, delay, objectRef, callback, null), 3, null) : null);
        } else {
            Pdlog.m3273d(this.TAG, "getStatusFlowCardSingle simSerialNumber is null");
            callback.invoke(null);
        }
    }

    public final void printStatusInfo(FlowCardStatusRes res) {
        String str;
        if (res != null && res.getData() != null) {
            switch (res.getData().getStatus()) {
                case 1:
                    str = "可激活";
                    break;
                case 2:
                    str = "已激活";
                    break;
                case 3:
                    str = "已停用";
                    break;
                case 4:
                    str = "违章停机";
                    break;
                case 5:
                    str = "已失效";
                    break;
                case 6:
                    str = "可测试";
                    break;
                case 7:
                    str = "运营商注销";
                    break;
                case 8:
                    str = "用户注销";
                    break;
                case 9:
                    str = "欠停";
                    break;
                case 10:
                    str = "挂失";
                    break;
                case 11:
                    str = "库存";
                    break;
                case 12:
                    str = "故障卡";
                    break;
                case 13:
                    str = "已断网";
                    break;
            }
            Pdlog.m3273d(this.TAG, "printStatusInfo = " + str);
        }
        str = "卡状态获取出错了";
        Pdlog.m3273d(this.TAG, "printStatusInfo = " + str);
    }

    public final void cancelNetTask() {
        Job mJob = getMJob();
        if (mJob != null) {
            Job.DefaultImpls.cancel$default(mJob, (CancellationException) null, 1, (Object) null);
        }
        setMJob((Job) null);
        CoroutineScope mScope = getMScope();
        if (mScope != null) {
            CoroutineScopeKt.cancel$default(mScope, null, 1, null);
        }
        setMScope((CoroutineScope) null);
    }
}
