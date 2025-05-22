package com.pudutech.mirsdk.hardware.mcuupdate;

import android.content.Context;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUProcess;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: UpdateMCUTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00017BF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012 \u0010\b\u001a\u001c\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\tø\u0001\u0000¢\u0006\u0002\u0010\rJ \u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)H\u0002J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\u000bH\u0004J\u001e\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u001d2\f\u00100\u001a\b\u0012\u0004\u0012\u00020201H\u0002J\u0010\u00103\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\nH\u0002J\b\u00104\u001a\u00020\u0007H\u0004J\b\u00105\u001a\u00020\u0007H\u0004J\u001c\u00106\u001a\u00020+2\u0006\u0010(\u001a\u00020)2\f\u00100\u001a\b\u0012\u0004\u0012\u00020201R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R+\u0010\b\u001a\u001c\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012X\u0084\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\u00020\u001dX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/mcuupdate/UpdateMCUTask;", "", "context", "Landroid/content/Context;", "sendCAN", "Lkotlin/reflect/KFunction1;", "Lkotlin/UByteArray;", "", "addCANDataListener", "Lkotlin/Function3;", "", "", "Lcom/pudutech/mirsdk/hardware/ICANData;", "(Landroid/content/Context;Lkotlin/reflect/KFunction;Lkotlin/jvm/functions/Function3;)V", "TAG", "getAddCANDataListener", "()Lkotlin/jvm/functions/Function3;", "fileNames", "", "[Ljava/lang/String;", "filePath", "inputStream", "Ljava/io/InputStream;", "getInputStream", "()[Ljava/io/InputStream;", "setInputStream", "([Ljava/io/InputStream;)V", "[Ljava/io/InputStream;", "latestVersionCnt", "", "getLatestVersionCnt", "()I", "setLatestVersionCnt", "(I)V", "getSendCAN", "()Lkotlin/reflect/KFunction;", "testMCUBin", "createProcess", "Lcom/pudutech/mirsdk/hardware/mcuupdate/UpdateMCUProcess;", "fileName", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "doCase", "Lcom/pudutech/mirsdk/hardware/mcuupdate/UpdateMCUTask$Result;", "updateMCUProcess", "targetVersion", "getHardwareVersion", "targetId", "hardwareVersions", "", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareVersion;", "getVersion", "loadFiles", "loadTestFiles", "tryTo", "Result", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class UpdateMCUTask {
    private final String TAG;
    private final Function3<String, byte[], ICANData, Unit> addCANDataListener;
    private final Context context;
    private String[] fileNames;
    private String filePath;
    private InputStream[] inputStream;
    private int latestVersionCnt;
    private final KFunction<Unit> sendCAN;
    private final String testMCUBin;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MachineModel.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MachineModel.BellaBot.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineModel.Hls.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineModel.Ninetales.ordinal()] = 3;
            $EnumSwitchMapping$0[MachineModel.RecycleDog.ordinal()] = 4;
            $EnumSwitchMapping$0[MachineModel.Puductor.ordinal()] = 5;
            $EnumSwitchMapping$0[MachineModel.Phoenix.ordinal()] = 6;
            $EnumSwitchMapping$0[MachineModel.Peanut.ordinal()] = 7;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UpdateMCUTask(Context context, KFunction<Unit> sendCAN, Function3<? super String, ? super byte[], ? super ICANData, Unit> addCANDataListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(sendCAN, "sendCAN");
        Intrinsics.checkParameterIsNotNull(addCANDataListener, "addCANDataListener");
        this.context = context;
        this.sendCAN = sendCAN;
        this.addCANDataListener = addCANDataListener;
        this.TAG = "UpdateMCUTask";
        this.filePath = "mcu_bin";
        this.testMCUBin = "/sdcard/pudu/test_mcu_bin";
    }

    public final KFunction<Unit> getSendCAN() {
        return this.sendCAN;
    }

    public final Function3<String, byte[], ICANData, Unit> getAddCANDataListener() {
        return this.addCANDataListener;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: UpdateMCUTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/mcuupdate/UpdateMCUTask$Result;", "", "isSuccess", "", "description", "", "(ZLjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final /* data */ class Result {
        private final String description;
        private final boolean isSuccess;

        public static /* synthetic */ Result copy$default(Result result, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = result.isSuccess;
            }
            if ((i & 2) != 0) {
                str = result.description;
            }
            return result.copy(z, str);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSuccess() {
            return this.isSuccess;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final Result copy(boolean isSuccess, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            return new Result(isSuccess, description);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.isSuccess == result.isSuccess && Intrinsics.areEqual(this.description, result.description);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.description;
            return i + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(isSuccess=" + this.isSuccess + ", description=" + this.description + ")";
        }

        public Result(boolean z, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.isSuccess = z;
            this.description = description;
        }

        public /* synthetic */ Result(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? "" : str);
        }

        public final String getDescription() {
            return this.description;
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }
    }

    protected final InputStream[] getInputStream() {
        return this.inputStream;
    }

    protected final void setInputStream(InputStream[] inputStreamArr) {
        this.inputStream = inputStreamArr;
    }

    protected final int getLatestVersionCnt() {
        return this.latestVersionCnt;
    }

    protected final void setLatestVersionCnt(int i) {
        this.latestVersionCnt = i;
    }

    protected final void loadFiles() {
        String str = this.filePath;
        try {
        } catch (IOException e) {
            Pdlog.m3274e(this.TAG, "fail to open file. " + e);
            Pdlog.m3274e(this.TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
        }
        if (new File(this.testMCUBin).exists()) {
            loadTestFiles();
            return;
        }
        this.fileNames = this.context.getAssets().list(str);
        Pdlog.m3273d(this.TAG, "load file " + Arrays.toString(this.fileNames));
        String[] strArr = this.fileNames;
        if (strArr == null) {
            Intrinsics.throwNpe();
        }
        int length = strArr.length;
        InputStream[] inputStreamArr = new InputStream[length];
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("/");
            String[] strArr2 = this.fileNames;
            if (strArr2 == null) {
                Intrinsics.throwNpe();
            }
            sb.append(strArr2[i]);
            String sb2 = sb.toString();
            Pdlog.m3273d(this.TAG, "load file nam:" + sb2);
            inputStreamArr[i] = this.context.getAssets().open(sb2);
        }
        this.inputStream = inputStreamArr;
        if (this.fileNames == null || this.inputStream == null) {
            Pdlog.m3274e(this.TAG, "files not exist ");
        }
    }

    protected final void loadTestFiles() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UpdateMCUTask$loadTestFiles$1(this, null), 3, null);
        String str = this.testMCUBin;
        try {
            this.fileNames = new File(this.testMCUBin).list();
            Pdlog.m3273d(this.TAG, "load file " + Arrays.toString(this.fileNames));
            String[] strArr = this.fileNames;
            if (strArr == null) {
                Intrinsics.throwNpe();
            }
            int length = strArr.length;
            InputStream[] inputStreamArr = new InputStream[length];
            for (int i = 0; i < length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("/");
                String[] strArr2 = this.fileNames;
                if (strArr2 == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(strArr2[i]);
                String sb2 = sb.toString();
                Pdlog.m3273d(this.TAG, "load file nam:" + sb2);
                inputStreamArr[i] = new FileInputStream(new File(sb2));
            }
            this.inputStream = inputStreamArr;
        } catch (IOException e) {
            Pdlog.m3274e(this.TAG, "fail to open file. " + e);
            Pdlog.m3274e(this.TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
        }
        if (this.fileNames == null || this.inputStream == null) {
            Pdlog.m3274e(this.TAG, "files not exist ");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUProcess, T] */
    public final Result tryTo(ProductMachineType machineType, List<HardwareVersion> hardwareVersions) {
        Intrinsics.checkParameterIsNotNull(machineType, "machineType");
        Intrinsics.checkParameterIsNotNull(hardwareVersions, "hardwareVersions");
        switch (WhenMappings.$EnumSwitchMapping$0[machineType.getModel().ordinal()]) {
            case 1:
                if (machineType.getMainVersion() == 0) {
                    int minorVersion = machineType.getMinorVersion();
                    if (minorVersion == 0) {
                        this.filePath = "bella_v0_mcu_bin";
                        break;
                    } else if (minorVersion == 1) {
                        this.filePath = "bella_mcu_bin";
                        break;
                    } else if (minorVersion == 2) {
                        this.filePath = "bella_v2_mcu_bin";
                        break;
                    } else if (minorVersion == 3) {
                        this.filePath = "bella_v3_mcu_bin";
                        break;
                    } else {
                        return new Result(true, "not supported bellabot version ,no upgrade required");
                    }
                } else if (machineType.getMainVersion() == 2) {
                    this.filePath = "bella_enclosed_mcu_bin";
                    break;
                } else {
                    return new Result(true, "not supported bellabot version ,no upgrade required");
                }
            case 2:
                this.filePath = "hls_mcu_bin";
                break;
            case 3:
                this.filePath = "ninetales_mcu_bin";
                break;
            case 4:
                if (machineType.getMainVersion() == 0) {
                    int minorVersion2 = machineType.getMinorVersion();
                    if (minorVersion2 != 1 && minorVersion2 != 2) {
                        return new Result(true, "not supported recycledog version ,no upgrade required");
                    }
                } else {
                    return new Result(true, "not supported recycledog version ,no upgrade required");
                }
                break;
            case 5:
                this.filePath = "puductor_mcu_bin";
                break;
            case 6:
                this.filePath = "phoenix_mcu_bin";
                break;
            case 7:
                this.filePath = "peanut_mcu_bin";
                break;
            default:
                return new Result(true, "no upgrade required");
        }
        loadFiles();
        InputStream[] inputStreamArr = this.inputStream;
        if (inputStreamArr == null) {
            Intrinsics.throwNpe();
        }
        int length = inputStreamArr.length;
        for (int i = 0; i < length; i++) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("try to update by bin file = ");
            String[] strArr = this.fileNames;
            if (strArr == null) {
                Intrinsics.throwNpe();
            }
            sb.append(strArr[i]);
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            String[] strArr2 = this.fileNames;
            if (strArr2 == null) {
                Intrinsics.throwNpe();
            }
            byte[] version = getVersion(strArr2[i]);
            if (version.length != 3) {
                String str2 = this.TAG;
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append(' ');
                String[] strArr3 = this.fileNames;
                if (strArr3 == null) {
                    Intrinsics.throwNpe();
                }
                sb2.append(strArr3[i]);
                sb2.append(" file name invalid");
                objArr2[0] = sb2.toString();
                Pdlog.m3275i(str2, objArr2);
            } else {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                String[] strArr4 = this.fileNames;
                if (strArr4 == null) {
                    Intrinsics.throwNpe();
                }
                String str3 = strArr4[i];
                InputStream[] inputStreamArr2 = this.inputStream;
                if (inputStreamArr2 == null) {
                    Intrinsics.throwNpe();
                }
                objectRef.element = createProcess(str3, inputStreamArr2[i], machineType);
                this.addCANDataListener.invoke("mcu_update", new byte[]{22}, new ICANData.Stub() { // from class: com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUTask$tryTo$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.pudutech.mirsdk.hardware.ICANData
                    public void onData(int p0, byte[] data) {
                        String str4;
                        str4 = UpdateMCUTask.this.TAG;
                        Object[] objArr3 = new Object[1];
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("on can data:");
                        sb3.append(data != null ? CommonKt.toHexString(data) : null);
                        objArr3[0] = sb3.toString();
                        Pdlog.m3273d(str4, objArr3);
                        ((UpdateMCUProcess) objectRef.element).setReceive(data);
                    }
                });
                byte[] hardwareVersion = getHardwareVersion(((UpdateMCUProcess) objectRef.element).targetID, hardwareVersions);
                if (hardwareVersion.length == 3 && version[0] == hardwareVersion[0] && version[1] == hardwareVersion[1] && version[2] == hardwareVersion[2]) {
                    Pdlog.m3273d(this.TAG, "no need to update,targetID=" + ((int) ((UpdateMCUProcess) objectRef.element).targetID) + " ,version=" + CollectionsKt.arrayListOf(hardwareVersion));
                } else {
                    Result doCase = doCase((UpdateMCUProcess) objectRef.element, version);
                    if (!doCase.isSuccess()) {
                        return doCase;
                    }
                }
            }
        }
        Pdlog.m3273d(this.TAG, "tryTo success");
        return new Result(true, "update mcu success");
    }

    private final UpdateMCUProcess createProcess(String fileName, InputStream inputStream, ProductMachineType machineType) {
        return new UpdateMCUProcess(fileName, inputStream, machineType, new UpdateMCUProcess.Callback() { // from class: com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUTask$createProcess$callback$1
            @Override // com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUProcess.Callback
            public void sendToMCU(byte[] data) {
                String str;
                str = UpdateMCUTask.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("send to MCU:");
                sb.append(data != null ? CommonKt.toHexString(data) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                Function1 function1 = (Function1) UpdateMCUTask.this.getSendCAN();
                if (data == null) {
                    Intrinsics.throwNpe();
                }
                byte[] copyOf = Arrays.copyOf(data, data.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                function1.invoke(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(copyOf)));
            }

            @Override // com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUProcess.Callback
            public void log(int level, String TAG, String content) {
                Pdlog.m3273d(TAG, content);
            }
        });
    }

    protected final Result doCase(UpdateMCUProcess updateMCUProcess, byte[] targetVersion) {
        Intrinsics.checkParameterIsNotNull(updateMCUProcess, "updateMCUProcess");
        Intrinsics.checkParameterIsNotNull(targetVersion, "targetVersion");
        int i = 2;
        boolean z = true;
        try {
            if (!updateMCUProcess.setIAPMode()) {
                Pdlog.m3274e(this.TAG, updateMCUProcess.getLastError());
                String lastError = updateMCUProcess.getLastError();
                Intrinsics.checkExpressionValueIsNotNull(lastError, "updateMCUProcess.lastError");
                return new Result(false, lastError);
            }
            String str = null;
            byte b = 0;
            byte b2 = 0;
            byte b3 = 0;
            if (updateMCUProcess.checkLatestVersionInIAP(targetVersion)) {
                this.latestVersionCnt++;
                updateMCUProcess.resetToAPP();
                return new Result(z, str, i, b3 == true ? 1 : 0);
            }
            if (!updateMCUProcess.updating(targetVersion)) {
                Pdlog.m3274e(this.TAG, updateMCUProcess.getLastError());
                String lastError2 = updateMCUProcess.getLastError();
                Intrinsics.checkExpressionValueIsNotNull(lastError2, "updateMCUProcess.lastError");
                return new Result(false, lastError2);
            }
            Pdlog.m3273d(this.TAG, "can_id:" + ((int) updateMCUProcess.targetID) + ", update to " + Arrays.toString(targetVersion) + ". success");
            return new Result(z, b2 == true ? 1 : 0, i, b == true ? 1 : 0);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            Pdlog.m3274e(this.TAG, e.toString());
            return new Result(false, "can_id:" + ((int) updateMCUProcess.targetID) + ",mcu update exception," + e.getLocalizedMessage());
        }
    }

    private final byte[] getVersion(String fileName) {
        List split$default = StringsKt.split$default((CharSequence) fileName, new String[]{".", "-"}, false, 0, 6, (Object) null);
        Pdlog.m3273d(this.TAG, "try to update by bin file = " + split$default);
        if (split$default.size() != 3) {
            return new byte[0];
        }
        List split$default2 = StringsKt.split$default((CharSequence) split$default.get(1), new String[]{"_"}, false, 0, 6, (Object) null);
        return split$default2.size() != 3 ? new byte[0] : new byte[]{Byte.parseByte((String) split$default2.get(0)), Byte.parseByte((String) split$default2.get(1)), Byte.parseByte((String) split$default2.get(2))};
    }

    private final byte[] getHardwareVersion(int targetId, List<HardwareVersion> hardwareVersions) {
        for (HardwareVersion hardwareVersion : hardwareVersions) {
            if (hardwareVersion.getBoard().getId() == targetId) {
                return new byte[]{(byte) hardwareVersion.getVer0(), (byte) hardwareVersion.getVer1(), (byte) hardwareVersion.getVer2()};
            }
        }
        return new byte[0];
    }
}
