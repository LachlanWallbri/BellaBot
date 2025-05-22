package com.pudutech.schedulerlib.p065ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.easynodelib.EasyNode;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.SchedulePackageProcess;
import com.pudutech.schedulerlib.connection.ESPScheduleNative;
import com.pudutech.schedulerlib.scheduleInfo.CurrentRobotInfo;
import com.pudutech.schedulerlib.utils.LimitLinkedBlockingDeque;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\u000bH\u0002J\b\u0010(\u001a\u00020#H\u0002J\b\u0010)\u001a\u00020\u0006H\u0002J\u0012\u0010*\u001a\u00020#2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020#H\u0014J\u000e\u0010.\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u0010\u0010/\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u000bH\u0002J\u000e\u00100\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u000e\u00101\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u000e\u00102\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u0012\u00103\u001a\u00020\u00062\b\b\u0002\u00104\u001a\u00020\u0006H\u0002J\u001c\u00105\u001a\u00020#2\b\u00106\u001a\u0004\u0018\u0001072\b\b\u0002\u00108\u001a\u00020\u0013H\u0002J\b\u00109\u001a\u00020#H\u0002J\u0010\u0010:\u001a\u00020#2\u0006\u0010'\u001a\u00020\u000bH\u0002J\u0010\u0010;\u001a\u00020#2\u0006\u0010'\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/SchedulerPressTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "ackCheckButtonFlag", "", "ackModeSelectButtonFlag", "espTestChannel", "", "espType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$ESP32Type;", "format", "Ljava/text/DecimalFormat;", "mac", "msgQueue", "Lcom/pudutech/schedulerlib/utils/LimitLinkedBlockingDeque;", "", "recvAverageCnt", "", "recvMsgId", "Ljava/util/concurrent/atomic/AtomicLong;", "recvMsgs", "sendAverageCnt", "sendMsgId", "sendMsgs", "shakeHandButtonFlag", "shakeHandCnt", "shakeHandSuccessCnt", "switchButtonFlag", "switchOpenCnt", "switchOpenSuccess", "timeCnt", "transmitRateButtonFlag", "ackModeSelect", "", "view", "Landroid/view/View;", "ackTestStart", "esp", "closeEspForPressTest", "espIsRunning", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "openAckTest", "openEspForPressTest", "openShakeHandTest", "openSwitchTest", "openTransmitRateTest", "recvMsgForTest", "flag", "sendMsgForTest", "info", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "msgId", "shakeHandTestStart", "switchTestStart", "transmitRateTestStart", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SchedulerPressTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private long recvAverageCnt;
    private long sendAverageCnt;
    private long timeCnt;
    private MachineInfo.ESP32Type espType = MachineInfo.ESP32Type.IntegrationFactory;
    private int espTestChannel = 8;
    private String TAG = "Esp Press Test";
    private boolean switchButtonFlag = true;
    private boolean shakeHandButtonFlag = true;
    private boolean transmitRateButtonFlag = true;
    private boolean ackCheckButtonFlag = true;
    private boolean ackModeSelectButtonFlag = true;
    private AtomicLong switchOpenCnt = new AtomicLong(0);
    private AtomicLong switchOpenSuccess = new AtomicLong(0);
    private AtomicLong shakeHandCnt = new AtomicLong(0);
    private AtomicLong shakeHandSuccessCnt = new AtomicLong(0);
    private final String mac = "00:00:00:00:00:00";
    private final LimitLinkedBlockingDeque<byte[]> msgQueue = new LimitLinkedBlockingDeque<>(10);
    private AtomicLong recvMsgs = new AtomicLong(0);
    private AtomicLong sendMsgs = new AtomicLong(0);
    private AtomicLong sendMsgId = new AtomicLong(0);
    private AtomicLong recvMsgId = new AtomicLong(0);
    private final DecimalFormat format = new DecimalFormat("0.##");

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineInfo.ESP32Type.values().length];

        static {
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.EasyNode.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.SingleDevice.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.IntegrationSample.ordinal()] = 3;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.IntegrationFactory.ordinal()] = 4;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.UartTtyS4.ordinal()] = 5;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Serializable serializableExtra;
        super.onCreate(savedInstanceState);
        setContentView(C5725R.layout.schedulerlib_activity_scheduler_press_test);
        if (getIntent() == null || (serializableExtra = getIntent().getSerializableExtra("espType")) == null) {
            return;
        }
        this.espType = (MachineInfo.ESP32Type) serializableExtra;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (!this.switchButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.switchTestKey)).performClick();
            return;
        }
        if (!this.shakeHandButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.shakeHandKey)).performClick();
        } else if (!this.transmitRateButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.transmitRateKey)).performClick();
        } else {
            if (this.ackCheckButtonFlag) {
                return;
            }
            ((Button) _$_findCachedViewById(C5725R.id.ackTestKey)).performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean openEspForPressTest(MachineInfo.ESP32Type esp) {
        Pdlog.m3273d(this.TAG, "openEspForPressTest " + UByte.m4563toStringimpl(esp.getId()));
        int i = (esp.getId() & 255) == 2 ? 230400 : 576000;
        int i2 = WhenMappings.$EnumSwitchMapping$0[esp.ordinal()];
        if (i2 == 1) {
            return EasyNode.INSTANCE.initEasyNode(this) != 0;
        }
        if (i2 == 2 || i2 == 3 || i2 == 4) {
            return ESPScheduleNative.INSTANCE.openESP(i, esp.getId() & 255, 1);
        }
        if (i2 != 5) {
            return false;
        }
        return ESPScheduleNative.INSTANCE.openESP(i, esp.getId() & 255, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeEspForPressTest() {
        ESPScheduleNative.INSTANCE.closeESP();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchTestStart(MachineInfo.ESP32Type esp) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$switchTestStart$1(this, esp, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$switchTestStart$2(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void shakeHandTestStart() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$shakeHandTestStart$1(this, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$shakeHandTestStart$2(this, null), 2, null);
    }

    private final boolean espIsRunning() {
        return ESPScheduleNative.INSTANCE.hasHardwareResponsed() && ESPScheduleNative.INSTANCE.connectStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void sendMsgForTest$default(SchedulerPressTestActivity schedulerPressTestActivity, RobotScheduleInfo robotScheduleInfo, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        schedulerPressTestActivity.sendMsgForTest(robotScheduleInfo, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMsgForTest(RobotScheduleInfo info, long msgId) {
        if (info == null) {
            return;
        }
        CurrentRobotInfo currentRobotInfo = new CurrentRobotInfo();
        currentRobotInfo.setData(info);
        currentRobotInfo.getData().setMsg_id(msgId);
        if (espIsRunning()) {
            currentRobotInfo.getData().setMsg_type(C3898x.f4338g);
            byte[] bArr = (byte[]) null;
            try {
                bArr = SchedulePackageProcess.INSTANCE.packageScheduleMsg(currentRobotInfo);
            } catch (Exception e) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Encode Schedule Info error: ");
                sb.append(e.getLocalizedMessage());
                sb.append(' ');
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str, sb.toString());
            }
            if (bArr != null) {
                ESPScheduleNative.INSTANCE.sendMsg(bArr);
                return;
            }
            return;
        }
        Pdlog.m3273d(this.TAG, "esp is not running, please restart now");
    }

    static /* synthetic */ boolean recvMsgForTest$default(SchedulerPressTestActivity schedulerPressTestActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return schedulerPressTestActivity.recvMsgForTest(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean recvMsgForTest(boolean flag) {
        byte[] readMsg = ESPScheduleNative.INSTANCE.readMsg(flag);
        if (readMsg != null) {
            if (!(readMsg.length == 0)) {
                this.msgQueue.putFirst(readMsg);
                if (!this.msgQueue.isEmpty()) {
                    byte[] takeFirst = this.msgQueue.takeFirst();
                    Intrinsics.checkExpressionValueIsNotNull(takeFirst, "msgQueue.takeFirst()");
                    RobotScheduleInfo unpackageScheduleMsg = SchedulePackageProcess.INSTANCE.unpackageScheduleMsg(takeFirst);
                    if (unpackageScheduleMsg != null) {
                        if (!this.transmitRateButtonFlag && unpackageScheduleMsg.getMap_flag().equals("transmitRateTest")) {
                            this.recvMsgs.getAndIncrement();
                            return true;
                        }
                        if (!this.ackCheckButtonFlag && unpackageScheduleMsg.getMap_flag().equals("ackTest")) {
                            this.recvMsgId.set(unpackageScheduleMsg.getMsg_id());
                            return true;
                        }
                        Pdlog.m3273d(this.TAG, "recvMsgForTest 收到的数据不是测试专用数据...");
                    }
                }
                return false;
            }
        }
        Pdlog.m3273d(this.TAG, "recvmsg is empty");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo, T] */
    public final void transmitRateTestStart(MachineInfo.ESP32Type esp) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new RobotScheduleInfo();
        ((RobotScheduleInfo) objectRef.element).setRobot_id(this.mac);
        RobotScheduleInfo robotScheduleInfo = (RobotScheduleInfo) objectRef.element;
        IntRange intRange = new IntRange(1, 25);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            arrayList.add(Integer.valueOf(nextInt * nextInt));
        }
        robotScheduleInfo.setTopology_path(CollectionsKt.toIntArray(new ArrayList(arrayList)));
        ((RobotScheduleInfo) objectRef.element).setRef_robot_id("r03");
        ((RobotScheduleInfo) objectRef.element).setAvoid_robot_id("r05");
        ((RobotScheduleInfo) objectRef.element).setScheduling_mode(SchedulingMode.Free);
        ((RobotScheduleInfo) objectRef.element).setAvoid_node_id(1);
        ((RobotScheduleInfo) objectRef.element).setAvoid_track_id(1);
        ((RobotScheduleInfo) objectRef.element).getNext_goal().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).getFinal_goal().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).getVirtual_goal().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).setMap_flag("transmitRateTest");
        ((RobotScheduleInfo) objectRef.element).getPose().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).getVelocity().update(1.1d, 1.1d, 1.1d);
        ESPScheduleNative.INSTANCE.resetChannel(this.espTestChannel);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$transmitRateTestStart$2(this, objectRef, null), 2, null);
        this.msgQueue.clear();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$transmitRateTestStart$3(this, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$transmitRateTestStart$4(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo, T] */
    public final void ackTestStart(MachineInfo.ESP32Type esp) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new RobotScheduleInfo();
        ((RobotScheduleInfo) objectRef.element).setRobot_id(this.mac);
        RobotScheduleInfo robotScheduleInfo = (RobotScheduleInfo) objectRef.element;
        IntRange intRange = new IntRange(1, 25);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            arrayList.add(Integer.valueOf(nextInt * nextInt));
        }
        robotScheduleInfo.setTopology_path(CollectionsKt.toIntArray(new ArrayList(arrayList)));
        ((RobotScheduleInfo) objectRef.element).setRef_robot_id("r03");
        ((RobotScheduleInfo) objectRef.element).setAvoid_robot_id("r05");
        ((RobotScheduleInfo) objectRef.element).setScheduling_mode(SchedulingMode.Free);
        ((RobotScheduleInfo) objectRef.element).setAvoid_node_id(1);
        ((RobotScheduleInfo) objectRef.element).setAvoid_track_id(1);
        ((RobotScheduleInfo) objectRef.element).getNext_goal().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).getFinal_goal().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).getVirtual_goal().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).setMap_flag("ackTest");
        ((RobotScheduleInfo) objectRef.element).getPose().update(0.1d, 0.1d, 0.1d);
        ((RobotScheduleInfo) objectRef.element).getVelocity().update(1.1d, 1.1d, 1.1d);
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = 0L;
        Ref.LongRef longRef2 = new Ref.LongRef();
        longRef2.element = 0L;
        Ref.LongRef longRef3 = new Ref.LongRef();
        longRef3.element = 0L;
        ESPScheduleNative.INSTANCE.resetChannel(this.espTestChannel);
        if (this.ackModeSelectButtonFlag) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$ackTestStart$2(this, longRef3, objectRef, 100L, null), 2, null);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$ackTestStart$3(this, longRef2, longRef, longRef3, 10L, null), 2, null);
        } else {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$ackTestStart$4(this, 10L, null), 2, null);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SchedulerPressTestActivity$ackTestStart$5(this, longRef2, objectRef, longRef, 100L, null), 2, null);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$ackTestStart$6(this, null), 2, null);
    }

    public final void openSwitchTest(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (!this.shakeHandButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.shakeHandKey)).performClick();
        } else if (!this.transmitRateButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.transmitRateKey)).performClick();
        } else if (!this.ackCheckButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.ackTestKey)).performClick();
        }
        if (this.switchButtonFlag) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$openSwitchTest$1(this, null), 2, null);
            return;
        }
        this.switchButtonFlag = true;
        ((Button) _$_findCachedViewById(C5725R.id.switchTestKey)).setText("开关测试(已关闭)");
        Toast.makeText(this, "开关测试按钮已经关闭", 0).show();
        Pdlog.m3273d(this.TAG, "开关测试按钮已经关闭");
    }

    public final void openShakeHandTest(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (!this.switchButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.switchTestKey)).performClick();
        } else if (!this.transmitRateButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.transmitRateKey)).performClick();
        } else if (!this.ackCheckButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.ackTestKey)).performClick();
        }
        if (this.shakeHandButtonFlag) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$openShakeHandTest$1(this, null), 2, null);
            return;
        }
        this.shakeHandButtonFlag = true;
        ((Button) _$_findCachedViewById(C5725R.id.shakeHandKey)).setText("握手测试(已关闭)");
        Toast.makeText(this, "握手测试按钮已经关闭", 0).show();
        Pdlog.m3273d(this.TAG, "握手测试按钮已经关闭");
    }

    public final void openTransmitRateTest(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (!this.switchButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.switchTestKey)).performClick();
        } else if (!this.shakeHandButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.shakeHandKey)).performClick();
        } else if (!this.ackCheckButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.ackTestKey)).performClick();
        }
        if (this.transmitRateButtonFlag) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$openTransmitRateTest$1(this, null), 2, null);
            return;
        }
        this.transmitRateButtonFlag = true;
        ((Button) _$_findCachedViewById(C5725R.id.transmitRateKey)).setText("速率测试(已关闭)");
        Toast.makeText(this, "速率测试按钮已经关闭", 0).show();
        Pdlog.m3273d(this.TAG, "速率测试按钮已经关闭");
    }

    public final void openAckTest(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (!this.switchButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.switchTestKey)).performClick();
        } else if (!this.shakeHandButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.shakeHandKey)).performClick();
        } else if (!this.transmitRateButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.transmitRateKey)).performClick();
        }
        if (this.ackCheckButtonFlag) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SchedulerPressTestActivity$openAckTest$1(this, null), 2, null);
            return;
        }
        this.ackCheckButtonFlag = true;
        ((Button) _$_findCachedViewById(C5725R.id.ackTestKey)).setText("应答测试(已关闭)");
        Toast.makeText(this, "应答测试按钮已经关闭", 0).show();
        Pdlog.m3273d(this.TAG, "应答测试按钮已经关闭");
    }

    public final void ackModeSelect(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.ackModeSelectButtonFlag = !this.ackModeSelectButtonFlag;
        if (this.ackModeSelectButtonFlag) {
            ((Button) _$_findCachedViewById(C5725R.id.ackSelectKey)).setText("主机");
            TextView ack_send_cnt = (TextView) _$_findCachedViewById(C5725R.id.ack_send_cnt);
            Intrinsics.checkExpressionValueIsNotNull(ack_send_cnt, "ack_send_cnt");
            ack_send_cnt.setText("发送： 0 次");
            TextView ack_recv_cnt = (TextView) _$_findCachedViewById(C5725R.id.ack_recv_cnt);
            Intrinsics.checkExpressionValueIsNotNull(ack_recv_cnt, "ack_recv_cnt");
            ack_recv_cnt.setText("应答： 0 次");
            return;
        }
        ((Button) _$_findCachedViewById(C5725R.id.ackSelectKey)).setText("从机");
        TextView ack_send_cnt2 = (TextView) _$_findCachedViewById(C5725R.id.ack_send_cnt);
        Intrinsics.checkExpressionValueIsNotNull(ack_send_cnt2, "ack_send_cnt");
        ack_send_cnt2.setText("接收： 0 次");
        TextView ack_recv_cnt2 = (TextView) _$_findCachedViewById(C5725R.id.ack_recv_cnt);
        Intrinsics.checkExpressionValueIsNotNull(ack_recv_cnt2, "ack_recv_cnt");
        ack_recv_cnt2.setText("回应： 0 次");
    }
}
