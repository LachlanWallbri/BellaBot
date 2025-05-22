package com.pudutech.mirsdk.hardware;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.ScheduleCommunication;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.schedulerlib.ScheduleController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: ScheduleCommunicationImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001c\u0010 \u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017H\u0016J\u0006\u0010!\u001a\u00020\u0007J\u0006\u0010\"\u001a\u00020\u001bJ\b\u0010#\u001a\u00020\u001bH\u0016J\b\u0010$\u001a\u00020\u001bH\u0016J\b\u0010%\u001a\u00020\nH\u0016J\u0006\u0010&\u001a\u00020\nJ\u0006\u0010'\u001a\u00020\u001bJ\b\u0010(\u001a\u00020\u001bH\u0002J\u0010\u0010)\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\nH\u0016J\u0012\u0010*\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010+\u001a\u00020\u001b2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/ScheduleCommunicationImpl;", "Lcom/pudutech/mirsdk/hardware/ScheduleCommunication$Stub;", "context", "Landroid/content/Context;", "machineInfo", "Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;", "enableESP", "", "(Landroid/content/Context;Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;Z)V", "TAG", "", "getContext", "()Landroid/content/Context;", "createJob", "Lkotlinx/coroutines/Job;", "destroyJob", "getEnableESP", "()Z", "getMachineInfo", "()Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;", "schJob", "scheduleTransportor", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/ScheduleInfoTransportor;", "scheduler", "Lcom/pudutech/schedulerlib/ScheduleController;", "activeESPVersion", "", "addSchCommInfoListener", "name", "producer", "Lcom/pudutech/mirsdk/hardware/SchCommunicateInfoListener;", "addScheduleInfoProducer", "checkConnection", "close", "createConnection", "destroyConnection", "getESPVersion", "getScheduleGit", "open", "registScheduleMsg", "removeSchCommInfoListener", "removeScheduleInfoProducer", "sendRobotScheduleCommunicationInfo", "p0", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ScheduleCommunicationImpl extends ScheduleCommunication.Stub {
    private final String TAG;
    private final Context context;
    private Job createJob;
    private Job destroyJob;
    private final boolean enableESP;
    private final MachineInfoProcess machineInfo;
    private Job schJob;
    private ThreadSafeListener<ScheduleInfoTransportor> scheduleTransportor;
    private final ScheduleController scheduler;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MachineInfo.ESP32Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MachineInfo.ESP32Type.SingleDevice.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.IntegrationSample.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.IntegrationFactory.ordinal()] = 3;
            $EnumSwitchMapping$0[MachineInfo.ESP32Type.EasyNode.ordinal()] = 4;
            int[] iArr2 = new int[MachineInfo.ESP32Type.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[MachineInfo.ESP32Type.SingleDevice.ordinal()] = 1;
            $EnumSwitchMapping$1[MachineInfo.ESP32Type.IntegrationSample.ordinal()] = 2;
            $EnumSwitchMapping$1[MachineInfo.ESP32Type.IntegrationFactory.ordinal()] = 3;
            $EnumSwitchMapping$1[MachineInfo.ESP32Type.EasyNode.ordinal()] = 4;
        }
    }

    public ScheduleCommunicationImpl(Context context, MachineInfoProcess machineInfo, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(machineInfo, "machineInfo");
        this.context = context;
        this.machineInfo = machineInfo;
        this.enableESP = z;
        this.TAG = "schcom";
        this.scheduleTransportor = new ThreadSafeListener<>();
        this.scheduler = ScheduleController.INSTANCE.getInstance();
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getEnableESP() {
        return this.enableESP;
    }

    public final MachineInfoProcess getMachineInfo() {
        return this.machineInfo;
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public void removeScheduleInfoProducer(String name) {
        ThreadSafeListener<ScheduleInfoTransportor> threadSafeListener = this.scheduleTransportor;
        if (name == null) {
            Intrinsics.throwNpe();
        }
        threadSafeListener.remove(name);
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public void addScheduleInfoProducer(String name, ScheduleInfoTransportor producer) {
        ThreadSafeListener<ScheduleInfoTransportor> threadSafeListener = this.scheduleTransportor;
        if (name == null) {
            Intrinsics.throwNpe();
        }
        if (producer == null) {
            Intrinsics.throwNpe();
        }
        threadSafeListener.add(name, producer);
    }

    public final void open() {
        registScheduleMsg();
        activeESPVersion(this.context);
    }

    public final void close() {
        BuildersKt__BuildersKt.runBlocking$default(null, new ScheduleCommunicationImpl$close$1(this, null), 1, null);
    }

    public final String getScheduleGit() {
        return this.scheduler.getGit();
    }

    public final boolean checkConnection() {
        MachineInfo.ESP32Type eSPMode = this.machineInfo.getMachineInfo().getESPMode();
        if (!this.enableESP) {
            return true;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[eSPMode.ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            Boolean espIsConnected = this.scheduler.espIsConnected();
            if (espIsConnected == null) {
                Intrinsics.throwNpe();
            }
            return espIsConnected.booleanValue();
        }
        Pdlog.m3275i(this.TAG, "MachineInfo not config ESP, should check PCB Version");
        Integer num = this.machineInfo.getMachineInfo().getInt(MachineInfo.IntInfo.uwbTagPcbMajorVersion);
        if (num == null) {
            Intrinsics.throwNpe();
        }
        if (num.intValue() < 1) {
            return true;
        }
        Integer num2 = this.machineInfo.getMachineInfo().getInt(MachineInfo.IntInfo.uwbTagPcbMinorVersion);
        if (num2 == null) {
            Intrinsics.throwNpe();
        }
        if (num2.intValue() < 3) {
            return true;
        }
        Boolean espIsConnected2 = this.scheduler.espIsConnected();
        if (espIsConnected2 == null) {
            Intrinsics.throwNpe();
        }
        return espIsConnected2.booleanValue();
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public String getESPVersion() {
        String espVersion = this.scheduler.getEspVersion();
        return espVersion != null ? espVersion : "0x00";
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public void removeSchCommInfoListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.scheduler.removeInfoListener(name);
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public void addSchCommInfoListener(String name, SchCommunicateInfoListener producer) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(producer, "producer");
        this.scheduler.addInfoListener(name, producer);
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public void sendRobotScheduleCommunicationInfo(RobotScheduleInfo p0) {
        this.scheduler.sendMsg(p0);
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public void createConnection() {
        Job launch$default;
        Job job = this.createJob;
        if (job != null && job.isActive()) {
            Pdlog.m3276v(this.TAG, "create connection job is running");
            return;
        }
        Job job2 = this.destroyJob;
        if (job2 == null || !job2.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new ScheduleCommunicationImpl$createConnection$1(this, null), 3, null);
            this.createJob = launch$default;
        } else {
            Pdlog.m3276v(this.TAG, "destroy connection job is running");
        }
    }

    @Override // com.pudutech.mirsdk.hardware.ScheduleCommunication
    public void destroyConnection() {
        Job launch$default;
        Job job = this.createJob;
        if (job != null && job.isActive()) {
            Pdlog.m3276v(this.TAG, "create connection job is running");
            return;
        }
        Job job2 = this.destroyJob;
        if (job2 == null || !job2.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new ScheduleCommunicationImpl$destroyConnection$1(this, null), 3, null);
            this.destroyJob = launch$default;
        } else {
            Pdlog.m3276v(this.TAG, "destroy connection job is running");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registScheduleMsg() {
        this.scheduler.getInfoListener().add(this.TAG, new ScheduleController.ScheInfoCallback() { // from class: com.pudutech.mirsdk.hardware.ScheduleCommunicationImpl$registScheduleMsg$1
            @Override // com.pudutech.schedulerlib.ScheduleController.ScheInfoCallback
            public void otherSchInfo(final RobotScheduleInfo info) {
                String str;
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(info, "info");
                str = ScheduleCommunicationImpl.this.TAG;
                Pdlog.m3273d(str, "set other robot info from hardware schedule communication module");
                threadSafeListener = ScheduleCommunicationImpl.this.scheduleTransportor;
                threadSafeListener.notify(new Function2<ScheduleInfoTransportor, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.ScheduleCommunicationImpl$registScheduleMsg$1$otherSchInfo$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ScheduleInfoTransportor scheduleInfoTransportor, String str2) {
                        invoke2(scheduleInfoTransportor, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ScheduleInfoTransportor it, String str2) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it.updateOtherRobotInfo(RobotScheduleInfo.this);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void activeESPVersion(Context context) {
        MachineInfo.ESP32Type eSPMode = this.machineInfo.getMachineInfo().getESPMode();
        int i = WhenMappings.$EnumSwitchMapping$1[eSPMode.ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            ScheduleController scheduleController = this.scheduler;
            if (!this.enableESP) {
                eSPMode = MachineInfo.ESP32Type.NODevice;
            }
            scheduleController.init(context, eSPMode);
            Pdlog.m3275i(this.TAG, "MachineInfo config first generation ESP");
            return;
        }
        Pdlog.m3275i(this.TAG, "MachineInfo not config ESP, should check PCB Version");
        Integer num = this.machineInfo.getMachineInfo().getInt(MachineInfo.IntInfo.uwbTagPcbMajorVersion);
        if (num == null) {
            Intrinsics.throwNpe();
        }
        if (num.intValue() >= 1) {
            Integer num2 = this.machineInfo.getMachineInfo().getInt(MachineInfo.IntInfo.uwbTagPcbMinorVersion);
            if (num2 == null) {
                Intrinsics.throwNpe();
            }
            if (num2.intValue() >= 3) {
                this.scheduler.init(context, this.enableESP ? MachineInfo.ESP32Type.SingleDevice : MachineInfo.ESP32Type.NODevice);
                return;
            }
        }
        Pdlog.m3275i(this.TAG, "UWB Tag Version is low, this machine not config esp");
    }
}
