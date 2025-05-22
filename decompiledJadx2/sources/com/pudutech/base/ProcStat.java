package com.pudutech.base;

import android.os.Process;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DelayKt;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* compiled from: ProcStat.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0004\"#$%B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0004H\u0002J*\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000fH\u0002J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u001c\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0002J\u0011\u0010\u0019\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0011\u0010\u001b\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\b\u0010\u001c\u001a\u00020\u0011H\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\n\u0010 \u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010!\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, m3961d2 = {"Lcom/pudutech/base/ProcStat;", "", "()V", "TAG", "", "coreNumber", "", "decodeCPU", "Lkotlin/Pair;", "", "info", "decodePID", "file", "Ljava/io/File;", "decodePIDCPU", "Ljava/util/StringTokenizer;", "diffCPU", "", "cpus_start", "Lcom/pudutech/base/ProcStat$CPUs;", "cpus_end", "diffPID", "pids_start", "Lcom/pudutech/base/ProcStat$Pids;", "pids_end", "procState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readCPUInfo", "readCPUTemperature", "readMemoryInfos", "readOneLine", "filePath", "readPIDInfo", "readProcessInfo", "CPUs", "PdCpuInfo", "PidCpuInfo", "Pids", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ProcStat {
    private final String TAG = "proc";
    private final int coreNumber = Runtime.getRuntime().availableProcessors();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* compiled from: ProcStat.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/base/ProcStat$PdCpuInfo;", "", "id", "", "whole", "", "idle", "hz", "(Ljava/lang/String;JJJ)V", "getHz", "()J", "setHz", "(J)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getIdle", "setIdle", "getWhole", "setWhole", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class PdCpuInfo {
        private long hz;
        private String id;
        private long idle;
        private long whole;

        public PdCpuInfo(String id, long j, long j2, long j3) {
            Intrinsics.checkParameterIsNotNull(id, "id");
            this.id = id;
            this.whole = j;
            this.idle = j2;
            this.hz = j3;
        }

        public final long getHz() {
            return this.hz;
        }

        public final String getId() {
            return this.id;
        }

        public final long getIdle() {
            return this.idle;
        }

        public final long getWhole() {
            return this.whole;
        }

        public final void setHz(long j) {
            this.hz = j;
        }

        public final void setId(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.id = str;
        }

        public final void setIdle(long j) {
            this.idle = j;
        }

        public final void setWhole(long j) {
            this.whole = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* compiled from: ProcStat.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006R&\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/base/ProcStat$CPUs;", "", "cpus", "", "", "Lcom/pudutech/base/ProcStat$PdCpuInfo;", "(Ljava/util/Map;)V", "getCpus", "()Ljava/util/Map;", "setCpus", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class CPUs {
        private Map<String, PdCpuInfo> cpus;

        public CPUs(Map<String, PdCpuInfo> cpus) {
            Intrinsics.checkParameterIsNotNull(cpus, "cpus");
            this.cpus = cpus;
        }

        public final Map<String, PdCpuInfo> getCpus() {
            return this.cpus;
        }

        public final void setCpus(Map<String, PdCpuInfo> map) {
            Intrinsics.checkParameterIsNotNull(map, "<set-?>");
            this.cpus = map;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* compiled from: ProcStat.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/base/ProcStat$PidCpuInfo;", "", "id", "", "name", "cpuinfo", "", "cpuid", "(Ljava/lang/String;Ljava/lang/String;JJ)V", "getCpuid", "()J", "setCpuid", "(J)V", "getCpuinfo", "setCpuinfo", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getName", "setName", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class PidCpuInfo {
        private long cpuid;
        private long cpuinfo;
        private String id;
        private String name;

        public PidCpuInfo(String id, String name, long j, long j2) {
            Intrinsics.checkParameterIsNotNull(id, "id");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.id = id;
            this.name = name;
            this.cpuinfo = j;
            this.cpuid = j2;
        }

        public final long getCpuid() {
            return this.cpuid;
        }

        public final long getCpuinfo() {
            return this.cpuinfo;
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final void setCpuid(long j) {
            this.cpuid = j;
        }

        public final void setCpuinfo(long j) {
            this.cpuinfo = j;
        }

        public final void setId(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.id = str;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* compiled from: ProcStat.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006R&\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/base/ProcStat$Pids;", "", "pids", "", "", "Lcom/pudutech/base/ProcStat$PidCpuInfo;", "(Ljava/util/Map;)V", "getPids", "()Ljava/util/Map;", "setPids", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Pids {
        private Map<String, PidCpuInfo> pids;

        public Pids(Map<String, PidCpuInfo> pids) {
            Intrinsics.checkParameterIsNotNull(pids, "pids");
            this.pids = pids;
        }

        public final Map<String, PidCpuInfo> getPids() {
            return this.pids;
        }

        public final void setPids(Map<String, PidCpuInfo> map) {
            Intrinsics.checkParameterIsNotNull(map, "<set-?>");
            this.pids = map;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0094 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0085 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object procState(Continuation<? super Unit> continuation) {
        ProcStat$procState$1 procStat$procState$1;
        Object coroutine_suspended;
        int i;
        ProcStat procStat;
        CPUs cPUs;
        Pids readPIDInfo;
        Object readCPUInfo;
        Pids pids;
        CPUs cPUs2;
        ProcStat procStat2;
        if (continuation instanceof ProcStat$procState$1) {
            procStat$procState$1 = (ProcStat$procState$1) continuation;
            if ((procStat$procState$1.label & Integer.MIN_VALUE) != 0) {
                procStat$procState$1.label -= Integer.MIN_VALUE;
                Object obj = procStat$procState$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = procStat$procState$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    procStat$procState$1.L$0 = this;
                    procStat$procState$1.label = 1;
                    obj = readCPUInfo(procStat$procState$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    procStat = this;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            pids = (Pids) procStat$procState$1.L$2;
                            cPUs2 = (CPUs) procStat$procState$1.L$1;
                            procStat2 = (ProcStat) procStat$procState$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            Pids readPIDInfo2 = procStat2.readPIDInfo();
                            procStat2.diffCPU(cPUs2, (CPUs) obj);
                            procStat2.diffPID(pids, readPIDInfo2);
                            procStat2.readProcessInfo();
                            procStat2.readMemoryInfos();
                            procStat2.readCPUTemperature();
                            return Unit.INSTANCE;
                        }
                        Pids pids2 = (Pids) procStat$procState$1.L$2;
                        CPUs cPUs3 = (CPUs) procStat$procState$1.L$1;
                        ProcStat procStat3 = (ProcStat) procStat$procState$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        cPUs = cPUs3;
                        readPIDInfo = pids2;
                        procStat = procStat3;
                        procStat$procState$1.L$0 = procStat;
                        procStat$procState$1.L$1 = cPUs;
                        procStat$procState$1.L$2 = readPIDInfo;
                        procStat$procState$1.label = 3;
                        readCPUInfo = procStat.readCPUInfo(procStat$procState$1);
                        if (readCPUInfo == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        pids = readPIDInfo;
                        ProcStat procStat4 = procStat;
                        cPUs2 = cPUs;
                        obj = readCPUInfo;
                        procStat2 = procStat4;
                        Pids readPIDInfo22 = procStat2.readPIDInfo();
                        procStat2.diffCPU(cPUs2, (CPUs) obj);
                        procStat2.diffPID(pids, readPIDInfo22);
                        procStat2.readProcessInfo();
                        procStat2.readMemoryInfos();
                        procStat2.readCPUTemperature();
                        return Unit.INSTANCE;
                    }
                    procStat = (ProcStat) procStat$procState$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                cPUs = (CPUs) obj;
                readPIDInfo = procStat.readPIDInfo();
                procStat$procState$1.L$0 = procStat;
                procStat$procState$1.L$1 = cPUs;
                procStat$procState$1.L$2 = readPIDInfo;
                procStat$procState$1.label = 2;
                if (DelayKt.delay(1000L, procStat$procState$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                procStat$procState$1.L$0 = procStat;
                procStat$procState$1.L$1 = cPUs;
                procStat$procState$1.L$2 = readPIDInfo;
                procStat$procState$1.label = 3;
                readCPUInfo = procStat.readCPUInfo(procStat$procState$1);
                if (readCPUInfo == coroutine_suspended) {
                }
            }
        }
        procStat$procState$1 = new ProcStat$procState$1(this, continuation);
        Object obj2 = procStat$procState$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = procStat$procState$1.label;
        if (i != 0) {
        }
        cPUs = (CPUs) obj2;
        readPIDInfo = procStat.readPIDInfo();
        procStat$procState$1.L$0 = procStat;
        procStat$procState$1.L$1 = cPUs;
        procStat$procState$1.L$2 = readPIDInfo;
        procStat$procState$1.label = 2;
        if (DelayKt.delay(1000L, procStat$procState$1) == coroutine_suspended) {
        }
        procStat$procState$1.L$0 = procStat;
        procStat$procState$1.L$1 = cPUs;
        procStat$procState$1.L$2 = readPIDInfo;
        procStat$procState$1.label = 3;
        readCPUInfo = procStat.readCPUInfo(procStat$procState$1);
        if (readCPUInfo == coroutine_suspended) {
        }
    }

    final /* synthetic */ Object readCPUInfo(Continuation<? super CPUs> continuation) {
        CPUs cPUs = new CPUs(new HashMap());
        try {
            LineIterator lineIterator = FileUtils.lineIterator(new File("/proc/stat"));
            String nextLine = lineIterator.nextLine();
            Intrinsics.checkExpressionValueIsNotNull(nextLine, "cpuStat.nextLine()");
            Pair<String, long[]> decodeCPU = decodeCPU(nextLine);
            cPUs.getCpus().put(decodeCPU.getFirst(), new PdCpuInfo(decodeCPU.getFirst(), decodeCPU.getSecond()[1], decodeCPU.getSecond()[0], 0L));
            int i = this.coreNumber;
            for (int i2 = 0; i2 < i; i2++) {
                String nextLine2 = lineIterator.nextLine();
                Intrinsics.checkExpressionValueIsNotNull(nextLine2, "cpuStat.nextLine()");
                Pair<String, long[]> decodeCPU2 = decodeCPU(nextLine2);
                String readOneLine = readOneLine("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/scaling_cur_freq");
                if (!Intrinsics.areEqual(readOneLine, "")) {
                    cPUs.getCpus().put(decodeCPU2.getFirst(), new PdCpuInfo(decodeCPU2.getFirst(), decodeCPU2.getSecond()[1], decodeCPU2.getSecond()[0], Long.parseLong(readOneLine)));
                }
            }
            lineIterator.close();
        } catch (Exception unused) {
        }
        return cPUs;
    }

    private final String readOneLine(String filePath) {
        try {
            LineIterator lineIterator = FileUtils.lineIterator(new File(filePath));
            if (lineIterator.hasNext()) {
                String nextLine = lineIterator.nextLine();
                Intrinsics.checkExpressionValueIsNotNull(nextLine, "iterator.nextLine()");
                return nextLine;
            }
            lineIterator.close();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private final Pids readPIDInfo() {
        File file = new File("/proc/" + Process.myPid() + "/task/");
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        Pids pids = new Pids(new HashMap());
        for (File file2 : file.listFiles()) {
            try {
                Intrinsics.checkExpressionValueIsNotNull(file2, "file");
                try {
                    Pair<String, Pair<String, long[]>> decodePID = decodePID(file2);
                    if (decodePID != null) {
                        long[] second = decodePID.getSecond().getSecond();
                        pids.getPids().put(decodePID.getFirst(), new PidCpuInfo(decodePID.getFirst(), decodePID.getSecond().getFirst(), second[0] + second[1] + second[2] + second[3], second[4]));
                    }
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
            }
        }
        return pids;
    }

    private final void diffCPU(CPUs cpus_start, CPUs cpus_end) {
        for (Map.Entry<String, PdCpuInfo> entry : cpus_start.getCpus().entrySet()) {
            PdCpuInfo pdCpuInfo = cpus_end.getCpus().get(entry.getKey());
            if (pdCpuInfo == null) {
                Intrinsics.throwNpe();
            }
            PdCpuInfo pdCpuInfo2 = pdCpuInfo;
            double idle = ((r2 - (pdCpuInfo2.getIdle() - entry.getValue().getIdle())) / (pdCpuInfo2.getWhole() - entry.getValue().getWhole())) * 100.0d;
            Pdlog.m3273d(this.TAG, entry.getKey() + " rate " + new BigDecimal(idle).setScale(2, 2) + "% freq " + pdCpuInfo2.getHz() + "hz");
        }
    }

    private final void diffPID(Pids pids_start, Pids pids_end) {
        if (pids_start == null || pids_end == null) {
            return;
        }
        for (Map.Entry<String, PidCpuInfo> entry : pids_start.getPids().entrySet()) {
            String key = entry.getKey();
            PidCpuInfo value = entry.getValue();
            if (pids_end.getPids().containsKey(key)) {
                PidCpuInfo pidCpuInfo = pids_end.getPids().get(key);
                if (pidCpuInfo == null) {
                    Intrinsics.throwNpe();
                }
                double cpuinfo = pidCpuInfo.getCpuinfo() - value.getCpuinfo();
                if (cpuinfo > 1) {
                    Pdlog.m3273d(this.TAG, "p " + key + " n " + value.getName() + "  " + cpuinfo + '%');
                }
            }
        }
    }

    private final String readMemoryInfos() {
        StringBuilder sb = new StringBuilder();
        try {
            LineIterator lineIterator = FileUtils.lineIterator(new File("/proc/meminfo"));
            while (lineIterator.hasNext()) {
                sb.append(lineIterator.nextLine());
                sb.append("\n");
            }
            lineIterator.close();
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append('\n');
            sb2.append((Object) sb);
            Pdlog.m3273d(str, sb2.toString());
        } catch (Exception unused) {
        }
        return sb.toString();
    }

    private final void readCPUTemperature() {
        String readOneLine = readOneLine("/sys/class/thermal/thermal_zone0/temp");
        Pdlog.m3273d(this.TAG, "CPU Temperature " + (Long.parseLong(readOneLine) / 1000.0d));
    }

    private final void readProcessInfo() {
        int myPid = Process.myPid();
        StringBuilder sb = new StringBuilder();
        try {
            LineIterator lineIterator = FileUtils.lineIterator(new File("/proc/" + myPid + "/status"));
            while (lineIterator.hasNext()) {
                sb.append(lineIterator.nextLine());
                sb.append("\n");
            }
            lineIterator.close();
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append('\n');
            sb2.append((Object) sb);
            Pdlog.m3273d(str, sb2.toString());
        } catch (Exception unused) {
        }
        StringsKt.clear(sb);
        try {
            LineIterator lineIterator2 = FileUtils.lineIterator(new File("/proc/" + myPid + "/statm"));
            while (lineIterator2.hasNext()) {
                sb.append(lineIterator2.nextLine());
                sb.append("\n");
            }
            lineIterator2.close();
            Pdlog.m3273d(this.TAG, "statm: " + ((Object) sb));
        } catch (Exception unused2) {
        }
        StringsKt.clear(sb);
        try {
            LineIterator lineIterator3 = FileUtils.lineIterator(new File("/proc/" + myPid + "/io"));
            while (lineIterator3.hasNext()) {
                sb.append(lineIterator3.nextLine());
                sb.append("\n");
            }
            lineIterator3.close();
            Pdlog.m3273d(this.TAG, "\nio: " + ((Object) sb));
        } catch (Exception unused3) {
        }
        File file = new File("/proc/" + myPid + "/fd");
        if (file.isDirectory()) {
            Pdlog.m3273d(this.TAG, "Open FD count: " + file.list().length);
        }
    }

    private final Pair<String, long[]> decodeCPU(String info) {
        StringTokenizer stringTokenizer = new StringTokenizer(info);
        String nextToken = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken, "token.nextToken()");
        Pair<String, long[]> pair = new Pair<>(nextToken, new long[2]);
        String nextToken2 = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken2, "token.nextToken()");
        long parseLong = Long.parseLong(nextToken2);
        String nextToken3 = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken3, "token.nextToken()");
        long parseLong2 = Long.parseLong(nextToken3);
        String nextToken4 = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken4, "token.nextToken()");
        long parseLong3 = Long.parseLong(nextToken4);
        String nextToken5 = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken5, "token.nextToken()");
        long parseLong4 = Long.parseLong(nextToken5);
        String nextToken6 = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken6, "token.nextToken()");
        long parseLong5 = Long.parseLong(nextToken6);
        String nextToken7 = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken7, "token.nextToken()");
        long parseLong6 = Long.parseLong(nextToken7);
        String nextToken8 = stringTokenizer.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken8, "token.nextToken()");
        long parseLong7 = parseLong + parseLong2 + parseLong3 + parseLong4 + parseLong5 + parseLong6 + Long.parseLong(nextToken8);
        pair.getSecond()[0] = parseLong4;
        pair.getSecond()[1] = parseLong7;
        return pair;
    }

    private final Pair<String, Pair<String, long[]>> decodePID(File file) {
        String readOneLine;
        StringTokenizer stringTokenizer = (StringTokenizer) null;
        try {
            readOneLine = readOneLine(file.getAbsolutePath() + "/stat");
        } catch (Exception unused) {
        }
        if (Intrinsics.areEqual(readOneLine, "")) {
            return null;
        }
        stringTokenizer = new StringTokenizer(readOneLine);
        String nextToken = stringTokenizer != null ? stringTokenizer.nextToken() : null;
        String nextToken2 = stringTokenizer != null ? stringTokenizer.nextToken() : null;
        if (stringTokenizer == null) {
            return null;
        }
        long[] decodePIDCPU = decodePIDCPU(stringTokenizer);
        if (nextToken == null) {
            Intrinsics.throwNpe();
        }
        if (nextToken2 == null) {
            Intrinsics.throwNpe();
        }
        return new Pair<>(nextToken, new Pair(nextToken2, decodePIDCPU));
    }

    private final long[] decodePIDCPU(StringTokenizer info) {
        int i = 11;
        do {
            info.nextToken();
            i--;
        } while (i > 0);
        long[] jArr = new long[5];
        String nextToken = info.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken, "info.nextToken()");
        jArr[0] = Long.parseLong(nextToken);
        String nextToken2 = info.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken2, "info.nextToken()");
        jArr[1] = Long.parseLong(nextToken2);
        String nextToken3 = info.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken3, "info.nextToken()");
        jArr[2] = Long.parseLong(nextToken3);
        String nextToken4 = info.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken4, "info.nextToken()");
        jArr[3] = Long.parseLong(nextToken4);
        int i2 = 21;
        do {
            info.nextToken();
            i2--;
        } while (i2 > 0);
        String nextToken5 = info.nextToken();
        Intrinsics.checkExpressionValueIsNotNull(nextToken5, "info.nextToken()");
        jArr[4] = Long.parseLong(nextToken5);
        return jArr;
    }
}
