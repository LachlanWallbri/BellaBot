package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Job.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"}, m3961d2 = {"Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/Job;", "parentCancelled", "", "parentJob", "Lkotlinx/coroutines/ParentJob;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface ChildJob extends Job {

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: Job.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static <R> R fold(ChildJob childJob, R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Job.DefaultImpls.fold(childJob, r, function2);
        }

        public static <E extends CoroutineContext.Element> E get(ChildJob childJob, CoroutineContext.Key<E> key) {
            return (E) Job.DefaultImpls.get(childJob, key);
        }

        public static CoroutineContext minusKey(ChildJob childJob, CoroutineContext.Key<?> key) {
            return Job.DefaultImpls.minusKey(childJob, key);
        }

        public static CoroutineContext plus(ChildJob childJob, CoroutineContext coroutineContext) {
            return Job.DefaultImpls.plus(childJob, coroutineContext);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(ChildJob childJob, Job job) {
            return Job.DefaultImpls.plus((Job) childJob, job);
        }
    }

    void parentCancelled(ParentJob parentJob);
}
