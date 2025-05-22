package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.bean.CallHistoryData;
import com.pudutech.bumblebee.robot_ui.roomdata.TtsDataBaseManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: CallHistoryManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J/\u0010\r\u001a\u00020\u000b2'\u0010\u000e\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000b0\u000fJ\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u000e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/CallHistoryManager;", "", "()V", "HISTORY_SIZE", "", "LIMIT_TIME", "TAG", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "addHistory", "", "destination", "asyncGetHistory", "callback", "Lkotlin/Function1;", "", "Lcom/pudutech/bumblebee/robot_ui/bean/CallHistoryData;", "Lkotlin/ParameterName;", "name", "history", "getHistory", "removeHistory", "data", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CallHistoryManager {
    private static final int HISTORY_SIZE = 6;
    private static final int LIMIT_TIME = 86400000;
    public static final CallHistoryManager INSTANCE = new CallHistoryManager();
    public static final String TAG = "CallHistoryManager";
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(ThreadPoolDispatcherKt.newSingleThreadContext(TAG));

    private CallHistoryManager() {
    }

    public final void addHistory(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Pdlog.m3273d(TAG, "addHistory=" + destination);
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new CallHistoryManager$addHistory$1(destination, null), 3, null);
    }

    public final void removeHistory(CallHistoryData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d(TAG, "removeHistory=" + data);
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new CallHistoryManager$removeHistory$1(data, null), 3, null);
    }

    public final void asyncGetHistory(Function1<? super List<CallHistoryData>, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "asyncGetHistory");
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new CallHistoryManager$asyncGetHistory$1(callback, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<CallHistoryData> getHistory() {
        long currentTimeMillis = System.currentTimeMillis() - LIMIT_TIME;
        Pdlog.m3273d(TAG, "query timestamp=" + currentTimeMillis);
        List<CallHistoryData> callHistoryList = TtsDataBaseManager.INSTANCE.getInstance().getMCallHistoryDao().getCallHistoryList(currentTimeMillis, 6);
        TtsDataBaseManager.INSTANCE.getInstance().getMCallHistoryDao().deleteCallHistory(currentTimeMillis);
        Pdlog.m3273d(TAG, "query result=" + callHistoryList);
        return callHistoryList;
    }
}
