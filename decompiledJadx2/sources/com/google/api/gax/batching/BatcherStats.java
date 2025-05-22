package com.google.api.gax.batching;

import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.base.MoreObjects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class BatcherStats {
    private int requestPartialFailureCount;
    private final Map<Class, Integer> requestExceptionCounts = new HashMap();
    private final Map<StatusCode.Code, Integer> requestStatusCounts = new HashMap();
    private final Map<Class, Integer> entryExceptionCounts = new HashMap();
    private final Map<StatusCode.Code, Integer> entryStatusCounts = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void recordBatchFailure(Throwable th) {
        Class<?> cls = th.getClass();
        if (th instanceof ApiException) {
            StatusCode.Code code = ((ApiException) th).getStatusCode().getCode();
            cls = ApiException.class;
            this.requestStatusCounts.put(code, Integer.valueOf(((Integer) MoreObjects.firstNonNull(this.requestStatusCounts.get(code), 0)).intValue() + 1));
        }
        this.requestExceptionCounts.put(cls, Integer.valueOf(((Integer) MoreObjects.firstNonNull(this.requestExceptionCounts.get(cls), 0)).intValue() + 1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized <T extends BatchEntry> void recordBatchElementsCompletion(List<T> list) {
        Iterator<T> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            try {
                it.next().getResultFuture().get();
            } catch (Throwable th) {
                if (!z) {
                    this.requestPartialFailureCount++;
                    z = true;
                }
                Throwable cause = th.getCause();
                Class<?> cls = cause.getClass();
                if (cause instanceof ApiException) {
                    StatusCode.Code code = ((ApiException) cause).getStatusCode().getCode();
                    cls = ApiException.class;
                    this.entryStatusCounts.put(code, Integer.valueOf(((Integer) MoreObjects.firstNonNull(this.entryStatusCounts.get(code), 0)).intValue() + 1));
                }
                this.entryExceptionCounts.put(cls, Integer.valueOf(((Integer) MoreObjects.firstNonNull(this.entryExceptionCounts.get(cls), 0)).intValue() + 1));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public synchronized BatchingException asException() {
        if (this.requestExceptionCounts.isEmpty() && this.requestPartialFailureCount == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Batching finished with ");
        if (!this.requestExceptionCounts.isEmpty()) {
            sb.append(String.format("%d batches failed to apply due to: ", Integer.valueOf(this.requestExceptionCounts.size())));
            sb.append(buildExceptionList(this.requestExceptionCounts, this.requestStatusCounts));
            sb.append(" and ");
        }
        sb.append(String.format("%d partial failures.", Integer.valueOf(this.requestPartialFailureCount)));
        if (this.requestPartialFailureCount > 0) {
            Iterator<Integer> it = this.entryExceptionCounts.values().iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().intValue();
            }
            sb.append(String.format(" The %d partial failures contained %d entries that failed with: ", Integer.valueOf(this.requestPartialFailureCount), Integer.valueOf(i)));
            sb.append(buildExceptionList(this.entryExceptionCounts, this.entryStatusCounts));
            sb.append(".");
        }
        return new BatchingException(sb.toString());
    }

    private String buildExceptionList(Map<Class, Integer> map, Map<StatusCode.Code, Integer> map2) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Class, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Class, Integer> next = it.next();
            sb.append(String.format("%d %s", next.getValue(), next.getKey().getSimpleName()));
            if (ApiException.class.equals(next.getKey())) {
                sb.append("(");
                Iterator<Map.Entry<StatusCode.Code, Integer>> it2 = map2.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<StatusCode.Code, Integer> next2 = it2.next();
                    sb.append(String.format("%d %s", next2.getValue(), next2.getKey()));
                    if (it2.hasNext()) {
                        sb.append(", ");
                    }
                }
                sb.append(")");
            }
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
