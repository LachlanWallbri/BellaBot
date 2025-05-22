package com.pudutech.event_tracking.component;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.loc.C3898x;
import com.pudutech.event_tracking.IEventTrackingParams;
import com.pudutech.event_tracking.bean.Event;
import com.pudutech.event_tracking.click.ViewClickHookManager;
import com.pudutech.pd_network.log.NetWorkLog;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LifeCycleComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010*\u001a\u00020\u00162\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00070,J\u0014\u0010-\u001a\u00020\u00162\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00070,J\b\u0010\r\u001a\u0004\u0018\u00010\u000bJ\u001a\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u00020\u000b2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0010\u00102\u001a\u00020&2\u0006\u0010/\u001a\u00020\u000bH\u0016J\u0010\u00103\u001a\u00020&2\u0006\u0010/\u001a\u00020\u000bH\u0016J\u0010\u00104\u001a\u00020&2\u0006\u0010/\u001a\u00020\u000bH\u0016J\u0018\u00105\u001a\u00020&2\u0006\u0010/\u001a\u00020\u000b2\u0006\u00106\u001a\u000201H\u0016J\u0010\u00107\u001a\u00020&2\u0006\u0010/\u001a\u00020\u000bH\u0016J\u0010\u00108\u001a\u00020&2\u0006\u0010/\u001a\u00020\u000bH\u0016J\u0018\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u0014H\u0016J\u0018\u0010=\u001a\u00020&2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u0014H\u0016J,\u0010>\u001a\u00020&2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00072\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020$0#H\u0002Jc\u0010?\u001a\u00020&2[\u0010@\u001aW\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(!\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020$0#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&0\u001ej\u0002`'J\u0010\u0010A\u001a\u0004\u0018\u00010;*\u0004\u0018\u00010\u000bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000Rg\u0010\u001d\u001a[\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(!\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020$0#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&\u0018\u00010\u001ej\u0004\u0018\u0001`'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/LifeCycleComponent;", "Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "EVENT_UNIQUE_ID", "", "TAG", "activityMap", "", "Landroid/app/Activity;", ES6Iterator.VALUE_PROPERTY, "crtResumeActivity", "setCrtResumeActivity", "(Landroid/app/Activity;)V", "filterActivity", "", "filterFragment", "fragmentMap", "Landroidx/fragment/app/Fragment;", "hookClick", "", "getHookClick", "()Z", "setHookClick", "(Z)V", "mMonitorJob", "Lkotlinx/coroutines/Job;", "onBrowseEvent", "Lkotlin/Function3;", "Lcom/pudutech/event_tracking/bean/Event;", "Lkotlin/ParameterName;", "name", "event", "", "", "params", "", "Lcom/pudutech/event_tracking/component/OnBrowseEvent;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "addFilterActivity", "list", "", "addFilterFragment", "onActivityCreated", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onFragmentPaused", "fm", "Landroidx/fragment/app/FragmentManager;", C3898x.f4339h, "onFragmentResumed", "onPageChange", "setOnBrowseEventLis", "block", "supportFragmentManager", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LifeCycleComponent extends FragmentManager.FragmentLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private final String EVENT_UNIQUE_ID;
    private final String TAG;
    private final Map<Activity, String> activityMap;
    private final Context context;
    private Activity crtResumeActivity;
    private List<String> filterActivity;
    private List<String> filterFragment;
    private final Map<Fragment, String> fragmentMap;
    private boolean hookClick;
    private Job mMonitorJob;
    private Function3<? super Event, ? super String, ? super Map<String, ? extends Object>, Unit> onBrowseEvent;
    private final CoroutineScope scope;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    public LifeCycleComponent(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.EVENT_UNIQUE_ID = "event_unique_id";
        this.TAG = "PuduUserActionCore";
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        Context applicationContext = this.context.getApplicationContext();
        Application application = (Application) (applicationContext instanceof Application ? applicationContext : null);
        if (application == null) {
            throw new Exception("获取不到Application");
        }
        application.registerActivityLifecycleCallbacks(this);
        this.filterActivity = new ArrayList();
        this.filterFragment = new ArrayList();
        this.activityMap = new LinkedHashMap();
        this.fragmentMap = new LinkedHashMap();
    }

    public final boolean getHookClick() {
        return this.hookClick;
    }

    public final void setHookClick(boolean z) {
        this.hookClick = z;
    }

    private final void setCrtResumeActivity(Activity activity) {
        Job launch$default;
        Job job = this.mMonitorJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (activity == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new LifeCycleComponent$crtResumeActivity$1(this, null), 3, null);
            this.mMonitorJob = launch$default;
        }
        this.crtResumeActivity = activity;
    }

    /* renamed from: crtResumeActivity, reason: from getter */
    public final Activity getCrtResumeActivity() {
        return this.crtResumeActivity;
    }

    public final boolean addFilterActivity(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        return this.filterActivity.addAll(list);
    }

    public final boolean addFilterFragment(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        return this.filterFragment.addAll(list);
    }

    public final void setOnBrowseEventLis(Function3<? super Event, ? super String, ? super Map<String, ? extends Object>, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.onBrowseEvent = block;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPageChange(Event event, String name, Map<String, ? extends Object> params) {
        Function3<? super Event, ? super String, ? super Map<String, ? extends Object>, Unit> function3;
        if (this.filterFragment.contains(name) || this.filterActivity.contains(name) || (function3 = this.onBrowseEvent) == null) {
            return;
        }
        function3.invoke(event, name, params);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onActivityCreated " + activity.getClass().getSimpleName());
        if (this.hookClick) {
            ViewClickHookManager.INSTANCE.registerHook(activity);
        }
        FragmentManager supportFragmentManager = supportFragmentManager(activity);
        if (supportFragmentManager != null) {
            supportFragmentManager.registerFragmentLifecycleCallbacks(this, true);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Map<String, Object> emptyMap;
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onActivityResumed " + activity.getClass().getSimpleName());
        setCrtResumeActivity(activity);
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        String replace$default = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
        this.activityMap.put(activity, replace$default);
        IEventTrackingParams iEventTrackingParams = (IEventTrackingParams) (!(activity instanceof IEventTrackingParams) ? null : activity);
        if (iEventTrackingParams == null || (emptyMap = iEventTrackingParams.eventParams(Event.PageResume.INSTANCE)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        Map<String, ? extends Object> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m3968to(this.EVENT_UNIQUE_ID, replace$default));
        mutableMapOf.putAll(emptyMap);
        Event.PageResume pageResume = Event.PageResume.INSTANCE;
        String name = activity.getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "activity.javaClass.name");
        onPageChange(pageResume, name, mutableMapOf);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Map<String, Object> emptyMap;
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onActivityPaused " + activity.getClass().getSimpleName());
        setCrtResumeActivity((Activity) null);
        IEventTrackingParams iEventTrackingParams = (IEventTrackingParams) (activity instanceof IEventTrackingParams ? activity : null);
        if (iEventTrackingParams == null || (emptyMap = iEventTrackingParams.eventParams(Event.PagePause.INSTANCE)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        String remove = this.activityMap.remove(activity);
        if (remove != null) {
            if (remove.length() > 0) {
                Map<String, Object> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m3968to(this.EVENT_UNIQUE_ID, remove));
                mutableMapOf.putAll(emptyMap);
                emptyMap = mutableMapOf;
            }
        }
        Event.PagePause pagePause = Event.PagePause.INSTANCE;
        String name = activity.getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "activity.javaClass.name");
        onPageChange(pagePause, name, emptyMap);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onActivityDestroyed " + activity.getClass().getSimpleName());
        FragmentManager supportFragmentManager = supportFragmentManager(activity);
        if (supportFragmentManager != null) {
            supportFragmentManager.unregisterFragmentLifecycleCallbacks(this);
        }
        if (this.hookClick) {
            ViewClickHookManager.INSTANCE.unregisterHook(activity);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        Map<String, Object> emptyMap;
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        super.onFragmentResumed(fm, f);
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        String replace$default = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
        this.fragmentMap.put(f, replace$default);
        IEventTrackingParams iEventTrackingParams = (IEventTrackingParams) (!(f instanceof IEventTrackingParams) ? null : f);
        if (iEventTrackingParams == null || (emptyMap = iEventTrackingParams.eventParams(Event.PageResume.INSTANCE)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        Map<String, ? extends Object> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m3968to(this.EVENT_UNIQUE_ID, replace$default));
        mutableMapOf.putAll(emptyMap);
        Event.PageResume pageResume = Event.PageResume.INSTANCE;
        String name = f.getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "f.javaClass.name");
        onPageChange(pageResume, name, mutableMapOf);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        Map<String, Object> emptyMap;
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        super.onFragmentPaused(fm, f);
        IEventTrackingParams iEventTrackingParams = (IEventTrackingParams) (!(f instanceof IEventTrackingParams) ? null : f);
        if (iEventTrackingParams == null || (emptyMap = iEventTrackingParams.eventParams(Event.PagePause.INSTANCE)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        String remove = this.fragmentMap.remove(f);
        if (remove != null) {
            if (remove.length() > 0) {
                Map<String, Object> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m3968to(this.EVENT_UNIQUE_ID, remove));
                mutableMapOf.putAll(emptyMap);
                emptyMap = mutableMapOf;
            }
        }
        Event.PagePause pagePause = Event.PagePause.INSTANCE;
        String name = f.getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "f.javaClass.name");
        onPageChange(pagePause, name, emptyMap);
    }

    private final FragmentManager supportFragmentManager(Activity activity) {
        if (!(activity instanceof AppCompatActivity)) {
            activity = null;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        if (appCompatActivity != null) {
            return appCompatActivity.getSupportFragmentManager();
        }
        return null;
    }
}
