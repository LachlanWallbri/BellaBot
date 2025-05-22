package com.pudutech.bumblebee.robot_ui.roomdata;

import com.pudutech.bumblebee.robot_ui.bean.CallHistoryData;
import java.util.List;
import kotlin.Metadata;

/* compiled from: CallHistoryDao.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH'¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/CallHistoryDao;", "", "addCallHistory", "", "callHistoryData", "Lcom/pudutech/bumblebee/robot_ui/bean/CallHistoryData;", "deleteCallHistory", "limitTime", "", "getCallHistoryList", "", "limitItemSize", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface CallHistoryDao {
    void addCallHistory(CallHistoryData callHistoryData);

    void deleteCallHistory(long limitTime);

    void deleteCallHistory(CallHistoryData callHistoryData);

    List<CallHistoryData> getCallHistoryList(long limitTime, int limitItemSize);
}
