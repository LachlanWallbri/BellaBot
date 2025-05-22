package com.pudutech.mirsdk.hardware.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineDeviceName;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: machineInfoList.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J`\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000426\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u000b\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u000b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\b0\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/TDialog;", "", "()V", "choice", "", "hardware", "Lcom/pudutech/mirsdk/hardware/activity/HardwareConnection;", "showSingSelect", "", "context", "Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity;", "name", "", ES6Iterator.VALUE_PROPERTY, RequestParameters.POSITION, "provider", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "it", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TDialog {
    private int choice;
    private final HardwareConnection hardware = HardwareConnection.INSTANCE;

    /* JADX WARN: Type inference failed for: r13v10, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v13, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v17, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v19, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v21, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v23, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v25, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v27, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    /* JADX WARN: Type inference failed for: r2v29, types: [T, com.pudutech.mirsdk.hardware.serialize.MachineDeviceName] */
    public final void showSingSelect(final MachineInfoActivity context, String name, String value, final int position, final Function2<? super Integer, ? super Integer, Unit> provider) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LidarVersion[] values = LidarVersion.values();
        int length = values.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = "";
        }
        int length2 = values.length;
        for (int i2 = 0; i2 < length2; i2++) {
            strArr[i2] = values[i2].toString();
        }
        linkedHashMap.put("ldsSensorVersion", strArr);
        MachineInfo.ESP32Type[] values2 = MachineInfo.ESP32Type.values();
        int length3 = values2.length;
        String[] strArr2 = new String[length3];
        for (int i3 = 0; i3 < length3; i3++) {
            strArr2[i3] = "";
        }
        int length4 = values2.length;
        for (int i4 = 0; i4 < length4; i4++) {
            strArr2[i4] = values2[i4].toString();
        }
        linkedHashMap.put("ESP32Version", strArr2);
        MachineInfo.RGBDType[] values3 = MachineInfo.RGBDType.values();
        int length5 = values3.length;
        String[] strArr3 = new String[length5];
        for (int i5 = 0; i5 < length5; i5++) {
            strArr3[i5] = "";
        }
        int length6 = values3.length;
        for (int i6 = 0; i6 < length6; i6++) {
            strArr3[i6] = values3[i6].toString();
        }
        linkedHashMap.put("RGBDVersion", strArr3);
        MachineInfo.AudioType[] values4 = MachineInfo.AudioType.values();
        int length7 = values4.length;
        String[] strArr4 = new String[length7];
        for (int i7 = 0; i7 < length7; i7++) {
            strArr4[i7] = "";
        }
        int length8 = values4.length;
        for (int i8 = 0; i8 < length8; i8++) {
            strArr4[i8] = values4[i8].toString();
        }
        linkedHashMap.put("AudioVersion", strArr4);
        MachineInfo.LoraType[] values5 = MachineInfo.LoraType.values();
        int length9 = values5.length;
        String[] strArr5 = new String[length9];
        for (int i9 = 0; i9 < length9; i9++) {
            strArr5[i9] = "";
        }
        int length10 = values5.length;
        for (int i10 = 0; i10 < length10; i10++) {
            strArr5[i10] = values5[i10].toString();
        }
        linkedHashMap.put("LoraVersion", strArr5);
        MachineInfo.ScanCodeType[] values6 = MachineInfo.ScanCodeType.values();
        int length11 = values6.length;
        String[] strArr6 = new String[length11];
        for (int i11 = 0; i11 < length11; i11++) {
            strArr6[i11] = "";
        }
        int length12 = values6.length;
        for (int i12 = 0; i12 < length12; i12++) {
            strArr6[i12] = values6[i12].toString();
        }
        linkedHashMap.put("ScanCodeDevice", strArr6);
        MachineInfo.MonocularType[] values7 = MachineInfo.MonocularType.values();
        int length13 = values7.length;
        String[] strArr7 = new String[length13];
        for (int i13 = 0; i13 < length13; i13++) {
            strArr7[i13] = "";
        }
        int length14 = values7.length;
        for (int i14 = 0; i14 < length14; i14++) {
            strArr7[i14] = values7[i14].toString();
        }
        linkedHashMap.put("MonocularCamera", strArr7);
        MachineInfo.SlamcoreType[] values8 = MachineInfo.SlamcoreType.values();
        int length15 = values8.length;
        String[] strArr8 = new String[length15];
        for (int i15 = 0; i15 < length15; i15++) {
            strArr8[i15] = "";
        }
        int length16 = values8.length;
        for (int i16 = 0; i16 < length16; i16++) {
            strArr8[i16] = values8[i16].toString();
        }
        linkedHashMap.put("Slamcore", strArr8);
        MachineInfo.HeadType[] values9 = MachineInfo.HeadType.values();
        int length17 = values9.length;
        String[] strArr9 = new String[length17];
        for (int i17 = 0; i17 < length17; i17++) {
            strArr9[i17] = "";
        }
        int length18 = values9.length;
        for (int i18 = 0; i18 < length18; i18++) {
            strArr9[i18] = values9[i18].toString();
        }
        linkedHashMap.put("FboardVersion", strArr9);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = MachineDeviceName.ldsSensorVersion;
        switch (name.hashCode()) {
            case -2119694048:
                if (name.equals("ScanCodeDevice")) {
                    objectRef.element = MachineDeviceName.ScanCodeDevice;
                    break;
                }
                break;
            case -1428729468:
                if (name.equals("Slamcore")) {
                    objectRef.element = MachineDeviceName.Slamware;
                    break;
                }
                break;
            case -1153310815:
                if (name.equals("RGBDVersion")) {
                    objectRef.element = MachineDeviceName.RGBDVersion;
                    break;
                }
                break;
            case -1070428094:
                if (name.equals("AudioVersion")) {
                    objectRef.element = MachineDeviceName.Auddio;
                    break;
                }
                break;
            case -757827197:
                if (name.equals("ldsSensorVersion")) {
                    objectRef.element = MachineDeviceName.ldsSensorVersion;
                    break;
                }
                break;
            case -233530355:
                if (name.equals("MonocularCamera")) {
                    objectRef.element = MachineDeviceName.MonocularCamera;
                    break;
                }
                break;
            case -88719002:
                if (name.equals("LoraVersion")) {
                    objectRef.element = MachineDeviceName.LoraVersion;
                    break;
                }
                break;
            case -70945193:
                if (name.equals("ESP32Version")) {
                    objectRef.element = MachineDeviceName.ESP32Version;
                    break;
                }
                break;
            case 1534008920:
                if (name.equals("FboardVersion")) {
                    objectRef.element = MachineDeviceName.FboardVersion;
                    break;
                }
                break;
        }
        Object obj = linkedHashMap.get(name);
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        final String[] strArr10 = (String[]) obj;
        this.choice = -1;
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(context).setTitle("单选列表").setSingleChoiceItems(strArr10, Integer.parseInt(value), new DialogInterface.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.TDialog$showSingSelect$builder$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i19) {
                TDialog.this.choice = i19;
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.TDialog$showSingSelect$builder$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i19) {
                int i20;
                HardwareConnection hardwareConnection;
                int i21;
                int i22;
                int i23;
                i20 = TDialog.this.choice;
                if (i20 != -1) {
                    hardwareConnection = TDialog.this.hardware;
                    HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                    if (hardwareInterface != null) {
                        MachineDeviceName machineDeviceName = (MachineDeviceName) objectRef.element;
                        i23 = TDialog.this.choice;
                        hardwareInterface.modifyMachineInfo(machineDeviceName, i23);
                    }
                    MachineInfoActivity machineInfoActivity = context;
                    StringBuilder sb = new StringBuilder();
                    sb.append("你选择了");
                    String[] strArr11 = strArr10;
                    i21 = TDialog.this.choice;
                    sb.append(strArr11[i21]);
                    Toast.makeText(machineInfoActivity, sb.toString(), 1).show();
                    Function2 function2 = provider;
                    Integer valueOf = Integer.valueOf(position);
                    i22 = TDialog.this.choice;
                    function2.invoke(valueOf, Integer.valueOf(i22));
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(positiveButton, "AlertDialog.Builder(cont…          }\n            )");
        positiveButton.create().show();
    }
}
