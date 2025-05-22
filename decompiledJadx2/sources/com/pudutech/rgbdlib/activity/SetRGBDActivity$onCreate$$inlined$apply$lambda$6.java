package com.pudutech.rgbdlib.activity;

import android.os.ParcelFileDescriptor;
import android.view.View;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.rgbdlib.RGBDType;
import com.pudutech.rgbdlib.config.DataJson;
import com.pudutech.rgbdlib.config.ExtrinsicsJson;
import com.pudutech.rgbdlib.config.RGBDConfig;
import com.pudutech.rgbdlib.config.RGBDJson;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: SetRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/pudutech/rgbdlib/activity/SetRGBDActivity$onCreate$FireFox$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class SetRGBDActivity$onCreate$$inlined$apply$lambda$6 implements View.OnClickListener {
    final /* synthetic */ SetRGBDActivity this$0;

    SetRGBDActivity$onCreate$$inlined$apply$lambda$6(SetRGBDActivity setRGBDActivity) {
        this.this$0 = setRGBDActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
        String str;
        z = this.this$0.checkConfig;
        if (z) {
            Gson gson = new Gson();
            RGBDConfig rGBDConfig = new RGBDConfig(null, null, null, null, null, true);
            RGBDJson rGBDJson = new RGBDJson(RGBDType.Orbbec.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.197f), Float.valueOf(0.085f), Float.valueOf(0.2f), Float.valueOf(0.52f), Float.valueOf(-0.61f), Float.valueOf(0.0f)));
            RGBDJson rGBDJson2 = new RGBDJson(RGBDType.Orbbec.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.197f), Float.valueOf(-0.085f), Float.valueOf(0.2f), Float.valueOf(-0.52f), Float.valueOf(-0.61f), Float.valueOf(3.14159f)));
            RGBDJson rGBDJson3 = new RGBDJson(RGBDType.Orbbec.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.219f), Float.valueOf(0.02f), Float.valueOf(0.924f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f)));
            DataJson dataJson = new DataJson("/sdcard/pudu/costmap_data", 0);
            rGBDConfig.setLeft_rgbd(rGBDJson);
            rGBDConfig.setRight_rgbd(rGBDJson2);
            rGBDConfig.setCenter_rgbd(rGBDJson3);
            rGBDConfig.setData(dataJson);
            try {
                str = this.this$0.configFileName;
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
                String json = gson.toJson(rGBDConfig);
                Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(configure)");
                Charset charset = Charsets.UTF_8;
                if (json == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = json.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            } catch (Exception e) {
                Pdlog.m3274e("rgbd", "rgbd config file create err: " + e.getMessage());
            }
        }
        if (SetRGBDActivity.INSTANCE.getRgbdSensor() == null) {
            SetRGBDActivity.INSTANCE.setRgbdSensor(new RGBDSensor(this.this$0));
        }
        RGBDSensor rgbdSensor = SetRGBDActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor != null) {
            MachineInfo.RGBDType rGBDType = MachineInfo.RGBDType.TwoDevice;
            int id = MachineModel.Firefox.getId();
            function4 = this.this$0.leftRGBDSubscription;
            function42 = this.this$0.rightRGBDSubscription;
            function43 = this.this$0.centerRGBDSubscription;
            function44 = this.this$0.downRGBDSubscription;
            rgbdSensor.start(rGBDType, id, function4, function42, function43, function44);
        }
        RGBDSensor rgbdSensor2 = SetRGBDActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor2 != null) {
            SetRGBDActivity setRGBDActivity = this.this$0;
            RGBDSensor rgbdSensor3 = SetRGBDActivity.INSTANCE.getRgbdSensor();
            if (rgbdSensor3 == null) {
                Intrinsics.throwNpe();
            }
            rgbdSensor2.startPreviewActivity(setRGBDActivity, rgbdSensor3);
        }
    }
}
