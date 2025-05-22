package com.pudutech.location;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.pudutech.location.MapLifecycleObserver;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: LifecycleManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0010H&J\b\u0010\u0015\u001a\u00020\u0010H&J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0010H&R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/location/LifecycleManager;", "", "()V", "mJob", "Lkotlinx/coroutines/Job;", "getMJob", "()Lkotlinx/coroutines/Job;", "setMJob", "(Lkotlinx/coroutines/Job;)V", "mScope", "Lkotlinx/coroutines/CoroutineScope;", "getMScope", "()Lkotlinx/coroutines/CoroutineScope;", "setMScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "addLifecycle", "", "activity", "Landroid/app/Activity;", "createScope", "onCreate", "onDestroy", "onResume", "onStart", "DefLifecycle", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class LifecycleManager {
    private Job mJob;
    private CoroutineScope mScope;

    public abstract void onCreate();

    public abstract void onDestroy();

    public void onResume() {
    }

    public abstract void onStart();

    /* JADX INFO: Access modifiers changed from: protected */
    public final CoroutineScope getMScope() {
        return this.mScope;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMScope(CoroutineScope coroutineScope) {
        this.mScope = coroutineScope;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Job getMJob() {
        return this.mJob;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMJob(Job job) {
        this.mJob = job;
    }

    public final void addLifecycle(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (activity instanceof AppCompatActivity) {
            ((AppCompatActivity) activity).getLifecycle().addObserver(new DefLifecycle());
        }
    }

    public final void createScope() {
        if (this.mScope == null) {
            this.mScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        }
    }

    /* compiled from: LifecycleManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/location/LifecycleManager$DefLifecycle;", "Lcom/pudutech/location/MapLifecycleObserver;", "(Lcom/pudutech/location/LifecycleManager;)V", "create", "", "destroy", "resume", "start", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final class DefLifecycle implements MapLifecycleObserver {
        public DefLifecycle() {
        }

        @Override // com.pudutech.location.MapLifecycleObserver
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public void any() {
            MapLifecycleObserver.DefaultImpls.any(this);
        }

        @Override // com.pudutech.location.MapLifecycleObserver
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void pause() {
            MapLifecycleObserver.DefaultImpls.pause(this);
        }

        @Override // com.pudutech.location.MapLifecycleObserver
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void stop() {
            MapLifecycleObserver.DefaultImpls.stop(this);
        }

        @Override // com.pudutech.location.MapLifecycleObserver
        public void create() {
            MapLifecycleObserver.DefaultImpls.create(this);
            LifecycleManager.this.onCreate();
        }

        @Override // com.pudutech.location.MapLifecycleObserver
        public void start() {
            MapLifecycleObserver.DefaultImpls.start(this);
            LifecycleManager.this.onStart();
        }

        @Override // com.pudutech.location.MapLifecycleObserver
        public void resume() {
            MapLifecycleObserver.DefaultImpls.resume(this);
            LifecycleManager.this.onResume();
        }

        @Override // com.pudutech.location.MapLifecycleObserver
        public void destroy() {
            MapLifecycleObserver.DefaultImpls.destroy(this);
            LifecycleManager.this.onDestroy();
            Job mJob = LifecycleManager.this.getMJob();
            if (mJob != null) {
                Job.DefaultImpls.cancel$default(mJob, (CancellationException) null, 1, (Object) null);
            }
            LifecycleManager.this.setMJob((Job) null);
            CoroutineScope mScope = LifecycleManager.this.getMScope();
            if (mScope != null) {
                CoroutineScopeKt.cancel$default(mScope, null, 1, null);
            }
            LifecycleManager.this.setMScope((CoroutineScope) null);
        }
    }
}
