package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.OnLifecycleEvent;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdapterLifecycleObserver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004J\b\u0010\u0012\u001a\u00020\u0010H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/AdapterLifecycleObserver;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BaseLifecycleObserver;", "()V", "_activity", "Landroidx/appcompat/app/AppCompatActivity;", "get_activity", "()Landroidx/appcompat/app/AppCompatActivity;", "set_activity", "(Landroidx/appcompat/app/AppCompatActivity;)V", "_lifecycleScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "get_lifecycleScope", "()Landroidx/lifecycle/LifecycleCoroutineScope;", "set_lifecycleScope", "(Landroidx/lifecycle/LifecycleCoroutineScope;)V", "bindLifecycle", "", "activity", "destroy", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class AdapterLifecycleObserver implements BaseLifecycleObserver {
    private AppCompatActivity _activity;
    private LifecycleCoroutineScope _lifecycleScope;

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void any() {
        BaseLifecycleObserver.DefaultImpls.any(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        BaseLifecycleObserver.DefaultImpls.create(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        BaseLifecycleObserver.DefaultImpls.pause(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        BaseLifecycleObserver.DefaultImpls.resume(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        BaseLifecycleObserver.DefaultImpls.start(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        BaseLifecycleObserver.DefaultImpls.stop(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final AppCompatActivity get_activity() {
        return this._activity;
    }

    protected final void set_activity(AppCompatActivity appCompatActivity) {
        this._activity = appCompatActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LifecycleCoroutineScope get_lifecycleScope() {
        return this._lifecycleScope;
    }

    protected final void set_lifecycleScope(LifecycleCoroutineScope lifecycleCoroutineScope) {
        this._lifecycleScope = lifecycleCoroutineScope;
    }

    public final void bindLifecycle(AppCompatActivity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this._activity = activity;
        this._lifecycleScope = LifecycleOwnerKt.getLifecycleScope(activity);
        activity.getLifecycle().addObserver(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void destroy() {
        Lifecycle lifecycle;
        BaseLifecycleObserver.DefaultImpls.destroy(this);
        AppCompatActivity appCompatActivity = this._activity;
        if (appCompatActivity == null || (lifecycle = appCompatActivity.getLifecycle()) == null) {
            return;
        }
        lifecycle.removeObserver(this);
    }
}
