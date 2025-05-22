package com.pudutech.event_tracking.p055db;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.event_tracking.bean.TrackEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: TrackEventDao.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH§@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0011\u0010\r\u001a\u00020\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0011\u0010\u0011\u001a\u00020\u0012H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H'J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\u0006\u0010\u0016\u001a\u00020\u0012H'J\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0018\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH§@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0019\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0019\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH§@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/event_tracking/db/TrackEventDao;", "", RequestParameters.SUBRESOURCE_DELETE, "", "event", "Lcom/pudutech/event_tracking/bean/TrackEvent;", "(Lcom/pudutech/event_tracking/bean/TrackEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordTime", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "list", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllUpload", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldest", "count", "getCount", "", "getCountFlow", "Lkotlinx/coroutines/flow/Flow;", "getCountFlowByUpload", "upload", "getNewestList", "insert", "update", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface TrackEventDao {
    Object delete(long j, Continuation<? super Unit> continuation);

    Object delete(TrackEvent trackEvent, Continuation<? super Unit> continuation);

    Object delete(List<TrackEvent> list, Continuation<? super Unit> continuation);

    Object deleteAllUpload(Continuation<? super Unit> continuation);

    Object deleteOldest(long j, Continuation<? super Unit> continuation);

    Object getCount(Continuation<? super Integer> continuation);

    Flow<Integer> getCountFlow();

    Flow<Integer> getCountFlowByUpload(int upload);

    Object getNewestList(Continuation<? super List<TrackEvent>> continuation);

    Object insert(TrackEvent trackEvent, Continuation<? super Unit> continuation);

    Object insert(List<TrackEvent> list, Continuation<? super Unit> continuation);

    Object update(TrackEvent trackEvent, Continuation<? super Unit> continuation);

    Object update(List<TrackEvent> list, Continuation<? super Unit> continuation);
}
