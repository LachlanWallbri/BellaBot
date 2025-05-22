package com.pudutech.mirsdk.hardware.cloud;

import android.os.SystemClock;
import com.google.gson.Gson;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import com.pudutech.mirsdk.hardware.IHardware;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: BatteryHealth.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\t\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/cloud/BatteryHealth;", "", "hardwareListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IHardware;", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "ReportDuration", "", "ReportScope", "com/pudutech/mirsdk/hardware/cloud/BatteryHealth$ReportScope$1", "Lcom/pudutech/mirsdk/hardware/cloud/BatteryHealth$ReportScope$1;", "cellVoltage", "Lcom/pudutech/mirsdk/hardware/cloud/BatteryData;", "gson", "Lcom/google/gson/Gson;", "health", "healthId", "", "Lkotlin/UByte;", "healthReportTime", "recvHealthId", "snAndType", "report", "", "dataSpice", "Lkotlin/UByteArray;", "report-GBYM_sE", "([B)V", "reportHealth", "reportHealth-GBYM_sE", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class BatteryHealth {
    private final long ReportDuration;
    private final BatteryHealth$ReportScope$1 ReportScope;
    private final BatteryData cellVoltage;
    private final Gson gson;
    private final ThreadSafeListener<IHardware> hardwareListener;
    private final BatteryData health;
    private final List<UByte> healthId;
    private long healthReportTime;
    private final List<UByte> recvHealthId;
    private final BatteryData snAndType;

    /* JADX WARN: Type inference failed for: r5v5, types: [com.pudutech.mirsdk.hardware.cloud.BatteryHealth$ReportScope$1] */
    public BatteryHealth(ThreadSafeListener<IHardware> hardwareListener) {
        Intrinsics.checkParameterIsNotNull(hardwareListener, "hardwareListener");
        this.hardwareListener = hardwareListener;
        this.health = new BatteryData();
        this.snAndType = new BatteryData();
        this.cellVoltage = new BatteryData();
        this.gson = new Gson();
        this.ReportDuration = 600000L;
        this.ReportScope = new CoroutineScope() { // from class: com.pudutech.mirsdk.hardware.cloud.BatteryHealth$ReportScope$1
            private final CoroutineContext coroutineContext = ThreadPoolDispatcherKt.newSingleThreadContext("BatteryHealth");

            @Override // kotlinx.coroutines.CoroutineScope
            public CoroutineContext getCoroutineContext() {
                return this.coroutineContext;
            }
        };
        this.healthId = CollectionsKt.mutableListOf(UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 0)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 1)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 2)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 3)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 4)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 11)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 12)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 64)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 65)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 66)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 67)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 68)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 69)), UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 70)));
        this.recvHealthId = new ArrayList();
        this.health.setMode("battery");
    }

    /* renamed from: report-GBYM_sE, reason: not valid java name */
    public final void m4432reportGBYM_sE(byte[] dataSpice) {
        Intrinsics.checkParameterIsNotNull(dataSpice, "dataSpice");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.healthReportTime;
        if (elapsedRealtime - j > this.ReportDuration || j == 0) {
            BuildersKt.launch$default(this.ReportScope, null, null, new BatteryHealth$report$1(this, dataSpice, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x004a, code lost:
    
        if (r7.health.getData().size() >= (r7.healthId.size() * 5)) goto L6;
     */
    /* renamed from: reportHealth-GBYM_sE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m4431reportHealthGBYM_sE(byte[] dataSpice) {
        boolean z = false;
        if (!this.recvHealthId.contains(UByte.m4522boximpl(UByteArray.m4577getimpl(dataSpice, 0)))) {
            this.recvHealthId.add(UByte.m4522boximpl(UByteArray.m4577getimpl(dataSpice, 0)));
            ArrayList<Byte> data = this.health.getData();
            byte[] copyOf = Arrays.copyOf(dataSpice, dataSpice.length);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
            CollectionsKt.addAll(data, ArraysKt.toTypedArray(copyOf));
        }
        z = true;
        if (z) {
            this.recvHealthId.clear();
            this.healthReportTime = SystemClock.elapsedRealtime();
            this.health.setTimestamp(System.currentTimeMillis() / 1000);
            this.health.setMac(HardwareConfig.INSTANCE.getMAC());
            String json = this.gson.toJson(this.health, BatteryData.class);
            this.health.getData().clear();
            BuildersKt.launch$default(this.ReportScope, null, null, new BatteryHealth$reportHealth$1(this, json, null), 3, null);
        }
    }
}
