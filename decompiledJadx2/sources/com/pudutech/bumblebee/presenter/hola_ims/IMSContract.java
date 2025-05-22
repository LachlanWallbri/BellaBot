package com.pudutech.bumblebee.presenter.hola_ims;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.business.ims.lora.Channel;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: IMSContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/hola_ims/IMSContract;", "", "()V", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSContract {

    /* compiled from: IMSContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/hola_ims/IMSContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
    }

    /* compiled from: IMSContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\u008d\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&J\u0085\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&Jq\u0010\u0012\u001a\u00020\u00032'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&J\b\u0010\u0013\u001a\u00020\u0003H&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H&Jq\u0010\u0017\u001a\u00020\u00032'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&J\b\u0010\u0018\u001a\u00020\u0003H&Jy\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&Jq\u0010\u001a\u001a\u00020\u00032'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&J4\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00052\u0010\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001e2\u0010\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001eH&J\u001c\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\"\u0018\u00010!j\n\u0012\u0004\u0012\u00020\"\u0018\u0001`#H&J\u0010\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&H&J\n\u0010'\u001a\u0004\u0018\u00010\u0007H&J\b\u0010(\u001a\u00020\u0005H&Jq\u0010)\u001a\u00020\u00032'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&Jq\u0010*\u001a\u00020\u00032'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&Jq\u0010,\u001a\u00020\u00032'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&J\u0010\u0010-\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0081\u0001\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&Jq\u00103\u001a\u00020\u00032'\b\u0002\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n2>\b\u0002\u0010\u000f\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010H&J\u0010\u00104\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000bH&J\u0012\u00105\u001a\u00020\u00032\b\u00106\u001a\u0004\u0018\u00010\u0007H&J\u001c\u00107\u001a\u00020\u00032\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010;H&J\u001c\u0010<\u001a\u00020\u00032\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010;H&Jy\u0010=\u001a\u00020\u00032\u0006\u0010>\u001a\u00020\u00072\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\"0!j\b\u0012\u0004\u0012\u00020\"`#2\u0012\b\u0002\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001e2\u0012\b\u0002\u0010A\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001e2'\b\u0002\u0010B\u001a!\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(C\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\nH&Jy\u0010D\u001a\u00020\u00032\u0006\u0010>\u001a\u00020\u00072\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\"0!j\b\u0012\u0004\u0012\u00020\"`#2\u0012\b\u0002\u0010E\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001e2\u0012\b\u0002\u0010F\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001e2'\b\u0002\u0010G\u001a!\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(C\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\nH&¨\u0006H"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/hola_ims/IMSContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "addBeeperBroadcastControl", "", "isOpen", "", "msgId", "", "centralControlCode", "onSentSucceed", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_MESSAGE, "onSentFailed", "Lkotlin/Function2;", "reason", "addDevice", "cancelSyncMap", "changeReportInterval", "delayTime", "", "checkMapSyncStatus", "checkTaskPartitionTableSyncStatus", "controlCentralControlBroadcast", "deleteDevice", "enableTaskPartition", "isEnable", "onSucceed", "Lkotlin/Function0;", "onFailed", "getCallPointList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "Lkotlin/collections/ArrayList;", "getMac", "context", "Landroid/content/Context;", "getMapVersionMD5", "isReportingRobotStatus", "queryHasUnallocatedTask", "queryMapVersion", "removeMsg", "reportCurrentTime", "reportRobotStatus", "reportTaskStatus", "task", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Task;", "callPoint", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$CallPoint;", "resetCentralControl", "sendWatchMsg", "setCentralControlMac", "mac", "setupChannel", "chl", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "listener", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$OnSetupChannelListener;", "setupLocalChannel", "syncMap", "version", "callPointList", "onSyncMapSucceed", "onSyncMapFailed", "onSyncMapPercent", "percent", "syncTaskPartitionTable", "onSyncTaskPartitionTableSucceed", "onSyncTaskPartitionTableFailed", "onSyncTaskPartitionTablePercent", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void addBeeperBroadcastControl(boolean isOpen, String msgId, String centralControlCode, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void addBeeperBroadcastControl(boolean isOpen, String centralControlCode, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void addDevice(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void cancelSyncMap();

        void changeReportInterval(int delayTime);

        void checkMapSyncStatus(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void checkTaskPartitionTableSyncStatus();

        void controlCentralControlBroadcast(boolean isOpen, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void deleteDevice(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void enableTaskPartition(boolean isEnable, Function0<Unit> onSucceed, Function0<Unit> onFailed);

        ArrayList<CallPoint> getCallPointList();

        String getMac(Context context);

        String getMapVersionMD5();

        boolean isReportingRobotStatus();

        void queryHasUnallocatedTask(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void queryMapVersion(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void removeMsg(String msgId);

        void reportCurrentTime(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void reportRobotStatus(boolean isOpen);

        void reportTaskStatus(MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void resetCentralControl(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed);

        void sendWatchMsg(MessageProtobuf.Msg msg);

        void setCentralControlMac(String mac);

        void setupChannel(Channel chl, LoRaChannelManager2.OnSetupChannelListener listener);

        void setupLocalChannel(Channel chl, LoRaChannelManager2.OnSetupChannelListener listener);

        void syncMap(String version, ArrayList<CallPoint> callPointList, Function0<Unit> onSyncMapSucceed, Function0<Unit> onSyncMapFailed, Function1<? super Integer, Unit> onSyncMapPercent);

        void syncTaskPartitionTable(String version, ArrayList<CallPoint> callPointList, Function0<Unit> onSyncTaskPartitionTableSucceed, Function0<Unit> onSyncTaskPartitionTableFailed, Function1<? super Integer, Unit> onSyncTaskPartitionTablePercent);

        /* compiled from: IMSContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void controlCentralControlBroadcast$default(PresenterInterface presenterInterface, boolean z, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: controlCentralControlBroadcast");
                }
                if ((i & 2) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 4) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.controlCentralControlBroadcast(z, function1, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void queryMapVersion$default(PresenterInterface presenterInterface, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: queryMapVersion");
                }
                if ((i & 1) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 2) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.queryMapVersion(function1, function2);
            }

            public static /* synthetic */ void syncMap$default(PresenterInterface presenterInterface, String str, ArrayList arrayList, Function0 function0, Function0 function02, Function1 function1, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: syncMap");
                }
                if ((i & 4) != 0) {
                    function0 = (Function0) null;
                }
                Function0 function03 = function0;
                if ((i & 8) != 0) {
                    function02 = (Function0) null;
                }
                Function0 function04 = function02;
                if ((i & 16) != 0) {
                    function1 = (Function1) null;
                }
                presenterInterface.syncMap(str, arrayList, function03, function04, function1);
            }

            public static /* synthetic */ void syncTaskPartitionTable$default(PresenterInterface presenterInterface, String str, ArrayList arrayList, Function0 function0, Function0 function02, Function1 function1, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: syncTaskPartitionTable");
                }
                if ((i & 4) != 0) {
                    function0 = (Function0) null;
                }
                Function0 function03 = function0;
                if ((i & 8) != 0) {
                    function02 = (Function0) null;
                }
                Function0 function04 = function02;
                if ((i & 16) != 0) {
                    function1 = (Function1) null;
                }
                presenterInterface.syncTaskPartitionTable(str, arrayList, function03, function04, function1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void reportTaskStatus$default(PresenterInterface presenterInterface, MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reportTaskStatus");
                }
                if ((i & 4) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 8) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.reportTaskStatus(task, callPoint, function1, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void resetCentralControl$default(PresenterInterface presenterInterface, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resetCentralControl");
                }
                if ((i & 1) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 2) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.resetCentralControl(function1, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void queryHasUnallocatedTask$default(PresenterInterface presenterInterface, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: queryHasUnallocatedTask");
                }
                if ((i & 1) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 2) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.queryHasUnallocatedTask(function1, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void addBeeperBroadcastControl$default(PresenterInterface presenterInterface, boolean z, String str, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addBeeperBroadcastControl");
                }
                if ((i & 2) != 0) {
                    str = (String) null;
                }
                if ((i & 4) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 8) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.addBeeperBroadcastControl(z, str, function1, function2);
            }

            public static /* synthetic */ void addBeeperBroadcastControl$default(PresenterInterface presenterInterface, boolean z, String str, String str2, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addBeeperBroadcastControl");
                }
                if ((i & 4) != 0) {
                    str2 = (String) null;
                }
                String str3 = str2;
                if ((i & 8) != 0) {
                    function1 = (Function1) null;
                }
                Function1 function12 = function1;
                if ((i & 16) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.addBeeperBroadcastControl(z, str, str3, function12, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void checkMapSyncStatus$default(PresenterInterface presenterInterface, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkMapSyncStatus");
                }
                if ((i & 1) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 2) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.checkMapSyncStatus(function1, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void addDevice$default(PresenterInterface presenterInterface, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addDevice");
                }
                if ((i & 1) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 2) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.addDevice(function1, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void deleteDevice$default(PresenterInterface presenterInterface, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteDevice");
                }
                if ((i & 1) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 2) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.deleteDevice(function1, function2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void reportCurrentTime$default(PresenterInterface presenterInterface, Function1 function1, Function2 function2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reportCurrentTime");
                }
                if ((i & 1) != 0) {
                    function1 = (Function1) null;
                }
                if ((i & 2) != 0) {
                    function2 = (Function2) null;
                }
                presenterInterface.reportCurrentTime(function1, function2);
            }
        }
    }
}
