package kotlinx.coroutines;

import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.Job;

/* compiled from: Job.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u001a\u0019\u0010\u0004\u001a\u00020\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0087\b\u001a\u0012\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a\u0019\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\b\t\u001a\f\u0010\u000e\u001a\u00020\b*\u00020\u0002H\u0007\u001a\u0018\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007\u001a\u001c\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u001a\u0015\u0010\u0015\u001a\u00020\b*\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\f\u0010\u0017\u001a\u00020\b*\u00020\u0002H\u0007\u001a\u0018\u0010\u0017\u001a\u00020\b*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007\u001a\u001c\u0010\u0017\u001a\u00020\b*\u00020\u00022\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u001a\f\u0010\u0017\u001a\u00020\b*\u00020\fH\u0007\u001a\u0018\u0010\u0017\u001a\u00020\b*\u00020\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007\u001a\u001c\u0010\u0017\u001a\u00020\b*\u00020\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u001a\u0014\u0010\u0018\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0005H\u0000\u001a\n\u0010\u001a\u001a\u00020\b*\u00020\u0002\u001a\n\u0010\u001a\u001a\u00020\b*\u00020\f\u001a\u001b\u0010\u001b\u001a\u00020\u0010*\u0004\u0018\u00010\u00102\u0006\u0010\u001c\u001a\u00020\fH\u0002¢\u0006\u0002\b\u001d\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m3961d2 = {"isActive", "", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)Z", "DisposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "block", "Lkotlin/Function0;", "", "Job", "Lkotlinx/coroutines/CompletableJob;", "parent", "Lkotlinx/coroutines/Job;", "Job0", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelChildren", "disposeOnCompletion", "handle", "ensureActive", "orCancellation", "job", "orCancellation$JobKt__JobKt", "kotlinx-coroutines-core"}, m3962k = 5, m3963mv = {1, 1, 16}, m3966xs = "kotlinx/coroutines/JobKt")
/* loaded from: classes2.dex */
public final /* synthetic */ class JobKt__JobKt {
    public static final CompletableJob Job(Job job) {
        return new JobImpl(job);
    }

    public static /* synthetic */ CompletableJob Job$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = (Job) null;
        }
        return JobKt.Job(job);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    /* renamed from: Job */
    public static final /* synthetic */ Job m5531Job(Job job) {
        return JobKt.Job(job);
    }

    /* renamed from: Job$default */
    public static /* synthetic */ Job m5532Job$default(Job job, int i, Object obj) {
        Job m5531Job;
        if ((i & 1) != 0) {
            job = (Job) null;
        }
        m5531Job = m5531Job(job);
        return m5531Job;
    }

    public static final DisposableHandle DisposableHandle(final Function0<Unit> function0) {
        return new DisposableHandle() { // from class: kotlinx.coroutines.JobKt__JobKt$DisposableHandle$1
            @Override // kotlinx.coroutines.DisposableHandle
            public void dispose() {
                Function0.this.invoke();
            }
        };
    }

    public static final DisposableHandle disposeOnCompletion(Job job, DisposableHandle disposableHandle) {
        return job.invokeOnCompletion(new DisposeOnCompletion(job, disposableHandle));
    }

    public static final Object cancelAndJoin(Job job, Continuation<? super Unit> continuation) {
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        Object join = job.join(continuation);
        return join == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? join : Unit.INSTANCE;
    }

    public static /* synthetic */ void cancelChildren$default(Job job, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = (CancellationException) null;
        }
        JobKt.cancelChildren(job, cancellationException);
    }

    public static final void cancelChildren(Job job, CancellationException cancellationException) {
        Iterator<Job> it = job.getChildren().iterator();
        while (it.hasNext()) {
            it.next().cancel(cancellationException);
        }
    }

    public static /* synthetic */ void cancelChildren$default(Job job, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = (Throwable) null;
        }
        cancelChildren(job, th);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(Job job, Throwable th) {
        for (Job job2 : job.getChildren()) {
            if (!(job2 instanceof JobSupport)) {
                job2 = null;
            }
            JobSupport jobSupport = (JobSupport) job2;
            if (jobSupport != null) {
                jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, job));
            }
        }
    }

    public static final boolean isActive(CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        return job != null && job.isActive();
    }

    public static /* synthetic */ void cancel$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = (CancellationException) null;
        }
        JobKt.cancel(coroutineContext, cancellationException);
    }

    public static final void cancel(CoroutineContext coroutineContext, CancellationException cancellationException) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job != null) {
            job.cancel(cancellationException);
        }
    }

    public static final void ensureActive(Job job) {
        if (!job.isActive()) {
            throw job.getCancellationException();
        }
    }

    public static final void ensureActive(CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job == null) {
            throw new IllegalStateException(("Context cannot be checked for liveness because it does not have a job: " + coroutineContext).toString());
        }
        JobKt.ensureActive(job);
    }

    public static final void cancel(Job job, String str, Throwable th) {
        job.cancel(ExceptionsKt.CancellationException(str, th));
    }

    public static /* synthetic */ void cancel$default(Job job, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = (Throwable) null;
        }
        JobKt.cancel(job, str, th);
    }

    public static /* synthetic */ boolean cancel$default(CoroutineContext coroutineContext, Throwable th, int i, Object obj) {
        boolean cancel;
        if ((i & 1) != 0) {
            th = (Throwable) null;
        }
        cancel = cancel(coroutineContext, th);
        return cancel;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ boolean cancel(CoroutineContext coroutineContext, Throwable th) {
        CoroutineContext.Element element = coroutineContext.get(Job.INSTANCE);
        if (!(element instanceof JobSupport)) {
            element = null;
        }
        JobSupport jobSupport = (JobSupport) element;
        if (jobSupport == null) {
            return false;
        }
        jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, jobSupport));
        return true;
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = (CancellationException) null;
        }
        JobKt.cancelChildren(coroutineContext, cancellationException);
    }

    public static final void cancelChildren(CoroutineContext coroutineContext, CancellationException cancellationException) {
        Sequence<Job> children;
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job == null || (children = job.getChildren()) == null) {
            return;
        }
        Iterator<Job> it = children.iterator();
        while (it.hasNext()) {
            it.next().cancel(cancellationException);
        }
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = (Throwable) null;
        }
        cancelChildren(coroutineContext, th);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(CoroutineContext coroutineContext, Throwable th) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job != null) {
            for (Job job2 : job.getChildren()) {
                if (!(job2 instanceof JobSupport)) {
                    job2 = null;
                }
                JobSupport jobSupport = (JobSupport) job2;
                if (jobSupport != null) {
                    jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, job));
                }
            }
        }
    }

    private static final Throwable orCancellation$JobKt__JobKt(Throwable th, Job job) {
        return th != null ? th : new JobCancellationException("Job was cancelled", null, job);
    }
}
