package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: JobSupport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b \u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0012\u0010\u0006\u001a\u00028\u00008\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, m3961d2 = {"Lkotlinx/coroutines/JobNode;", "J", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CompletionHandlerBase;", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/Incomplete;", "job", "(Lkotlinx/coroutines/Job;)V", "isActive", "", "()Z", "Lkotlinx/coroutines/Job;", "list", "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "dispose", "", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class JobNode<J extends Job> extends CompletionHandlerBase implements DisposableHandle, Incomplete {
    public final J job;

    @Override // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    /* renamed from: isActive */
    public boolean getIsActive() {
        return true;
    }

    public JobNode(J j) {
        this.job = j;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        J j = this.job;
        if (j == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.JobSupport");
        }
        ((JobSupport) j).removeNode$kotlinx_coroutines_core(this);
    }
}
