package com.pudutech.rgbdlib.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.rgbdlib.C5657R;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.rgbdlib.config.RGBDConfig;
import com.pudutech.rgbdlib.config.RGBDJson;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: DisplayRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class DisplayRGBDActivity$onCreate$10 implements View.OnClickListener {
    final /* synthetic */ DisplayRGBDActivity this$0;

    DisplayRGBDActivity$onCreate$10(DisplayRGBDActivity displayRGBDActivity) {
        this.this$0 = displayRGBDActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        HashMap hashMap4;
        HashMap hashMap5;
        HashMap hashMap6;
        HashMap hashMap7;
        HashMap hashMap8;
        String str2;
        HashMap hashMap9;
        HashMap hashMap10;
        HashMap hashMap11;
        HashMap hashMap12;
        HashMap hashMap13;
        HashMap hashMap14;
        HashMap hashMap15;
        HashMap hashMap16;
        String type;
        RGBDSensor rgbdSensor;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        HashMap hashMap17;
        HashMap hashMap18;
        HashMap hashMap19;
        HashMap hashMap20;
        HashMap hashMap21;
        HashMap hashMap22;
        HashMap hashMap23;
        HashMap hashMap24;
        HashMap hashMap25;
        HashMap hashMap26;
        HashMap hashMap27;
        HashMap hashMap28;
        HashMap hashMap29;
        HashMap hashMap30;
        HashMap hashMap31;
        HashMap hashMap32;
        try {
            Gson gson = new Gson();
            RGBDConfig rGBDConfig = (RGBDConfig) gson.fromJson((Reader) new FileReader(ConfigUtil.CONFIG_DIR + ConfigUtil.RGBD_CONFIG_FILE_NAME), RGBDConfig.class);
            hashMap = this.this$0.saveMap;
            HashMap hashMap33 = hashMap;
            Integer valueOf = Integer.valueOf(C5657R.id.leftView);
            RGBDJson left_rgbd = rGBDConfig.getLeft_rgbd();
            hashMap33.put(valueOf, left_rgbd != null ? left_rgbd.getSerial() : null);
            hashMap2 = this.this$0.saveMap;
            HashMap hashMap34 = hashMap2;
            Integer valueOf2 = Integer.valueOf(C5657R.id.rightView);
            RGBDJson right_rgbd = rGBDConfig.getRight_rgbd();
            hashMap34.put(valueOf2, right_rgbd != null ? right_rgbd.getSerial() : null);
            hashMap3 = this.this$0.saveMap;
            HashMap hashMap35 = hashMap3;
            Integer valueOf3 = Integer.valueOf(C5657R.id.centerView);
            RGBDJson center_rgbd = rGBDConfig.getCenter_rgbd();
            hashMap35.put(valueOf3, center_rgbd != null ? center_rgbd.getSerial() : null);
            hashMap4 = this.this$0.saveMap;
            HashMap hashMap36 = hashMap4;
            Integer valueOf4 = Integer.valueOf(C5657R.id.downView);
            RGBDJson down_rgbd = rGBDConfig.getDown_rgbd();
            hashMap36.put(valueOf4, down_rgbd != null ? down_rgbd.getSerial() : null);
            hashMap5 = this.this$0.saveTypeMap;
            HashMap hashMap37 = hashMap5;
            Integer valueOf5 = Integer.valueOf(C5657R.id.leftView);
            RGBDJson left_rgbd2 = rGBDConfig.getLeft_rgbd();
            hashMap37.put(valueOf5, left_rgbd2 != null ? left_rgbd2.getType() : null);
            hashMap6 = this.this$0.saveTypeMap;
            HashMap hashMap38 = hashMap6;
            Integer valueOf6 = Integer.valueOf(C5657R.id.rightView);
            RGBDJson right_rgbd2 = rGBDConfig.getRight_rgbd();
            hashMap38.put(valueOf6, right_rgbd2 != null ? right_rgbd2.getType() : null);
            hashMap7 = this.this$0.saveTypeMap;
            HashMap hashMap39 = hashMap7;
            Integer valueOf7 = Integer.valueOf(C5657R.id.centerView);
            RGBDJson center_rgbd2 = rGBDConfig.getCenter_rgbd();
            hashMap39.put(valueOf7, center_rgbd2 != null ? center_rgbd2.getType() : null);
            hashMap8 = this.this$0.saveTypeMap;
            HashMap hashMap40 = hashMap8;
            Integer valueOf8 = Integer.valueOf(C5657R.id.downView);
            RGBDJson down_rgbd2 = rGBDConfig.getDown_rgbd();
            hashMap40.put(valueOf8, down_rgbd2 != null ? down_rgbd2.getType() : null);
            RGBDJson left_rgbd3 = rGBDConfig.getLeft_rgbd();
            if (left_rgbd3 != null) {
                hashMap31 = this.this$0.saveMap;
                hashMap32 = this.this$0.viewIdMap;
                String str3 = (String) hashMap31.get(hashMap32.get(Integer.valueOf(C5657R.id.leftView)));
                if (str3 == null) {
                    str3 = "";
                }
                left_rgbd3.setSerial(str3);
            }
            RGBDJson right_rgbd3 = rGBDConfig.getRight_rgbd();
            if (right_rgbd3 != null) {
                hashMap29 = this.this$0.saveMap;
                hashMap30 = this.this$0.viewIdMap;
                String str4 = (String) hashMap29.get(hashMap30.get(Integer.valueOf(C5657R.id.rightView)));
                if (str4 == null) {
                    str4 = "";
                }
                right_rgbd3.setSerial(str4);
            }
            RGBDJson center_rgbd3 = rGBDConfig.getCenter_rgbd();
            if (center_rgbd3 != null) {
                hashMap27 = this.this$0.saveMap;
                hashMap28 = this.this$0.viewIdMap;
                String str5 = (String) hashMap27.get(hashMap28.get(Integer.valueOf(C5657R.id.centerView)));
                if (str5 == null) {
                    str5 = "";
                }
                center_rgbd3.setSerial(str5);
            }
            RGBDJson down_rgbd3 = rGBDConfig.getDown_rgbd();
            if (down_rgbd3 != null) {
                hashMap25 = this.this$0.saveMap;
                hashMap26 = this.this$0.viewIdMap;
                String str6 = (String) hashMap25.get(hashMap26.get(Integer.valueOf(C5657R.id.downView)));
                if (str6 == null) {
                    str6 = "";
                }
                down_rgbd3.setSerial(str6);
            }
            RGBDJson left_rgbd4 = rGBDConfig.getLeft_rgbd();
            if (left_rgbd4 != null) {
                hashMap23 = this.this$0.saveTypeMap;
                hashMap24 = this.this$0.viewIdMap;
                String str7 = (String) hashMap23.get(hashMap24.get(Integer.valueOf(C5657R.id.leftView)));
                if (str7 == null) {
                    str7 = "";
                }
                left_rgbd4.setType(str7);
            }
            RGBDJson right_rgbd4 = rGBDConfig.getRight_rgbd();
            if (right_rgbd4 != null) {
                hashMap21 = this.this$0.saveTypeMap;
                hashMap22 = this.this$0.viewIdMap;
                String str8 = (String) hashMap21.get(hashMap22.get(Integer.valueOf(C5657R.id.rightView)));
                if (str8 == null) {
                    str8 = "";
                }
                right_rgbd4.setType(str8);
            }
            RGBDJson center_rgbd4 = rGBDConfig.getCenter_rgbd();
            if (center_rgbd4 != null) {
                hashMap19 = this.this$0.saveTypeMap;
                hashMap20 = this.this$0.viewIdMap;
                String str9 = (String) hashMap19.get(hashMap20.get(Integer.valueOf(C5657R.id.centerView)));
                if (str9 == null) {
                    str9 = "";
                }
                center_rgbd4.setType(str9);
            }
            RGBDJson down_rgbd4 = rGBDConfig.getDown_rgbd();
            if (down_rgbd4 != null) {
                hashMap17 = this.this$0.saveTypeMap;
                hashMap18 = this.this$0.viewIdMap;
                String str10 = (String) hashMap17.get(hashMap18.get(Integer.valueOf(C5657R.id.downView)));
                down_rgbd4.setType(str10 != null ? str10 : "");
            }
            rGBDConfig.setReset(false);
            RGBDJson left_rgbd5 = rGBDConfig.getLeft_rgbd();
            if (left_rgbd5 != null) {
                z4 = this.this$0.sheildLeftRgbd;
                left_rgbd5.setShield(Boolean.valueOf(z4));
            }
            RGBDJson right_rgbd5 = rGBDConfig.getRight_rgbd();
            if (right_rgbd5 != null) {
                z3 = this.this$0.sheildRightRgbd;
                right_rgbd5.setShield(Boolean.valueOf(z3));
            }
            RGBDJson center_rgbd5 = rGBDConfig.getCenter_rgbd();
            if (center_rgbd5 != null) {
                z2 = this.this$0.sheildCenterRgbd;
                center_rgbd5.setShield(Boolean.valueOf(z2));
            }
            RGBDJson down_rgbd5 = rGBDConfig.getDown_rgbd();
            if (down_rgbd5 != null) {
                z = this.this$0.sheildDownRgbd;
                down_rgbd5.setShield(Boolean.valueOf(z));
            }
            RGBDJson center_rgbd6 = rGBDConfig.getCenter_rgbd();
            if (center_rgbd6 != null && (type = center_rgbd6.getType()) != null && (rgbdSensor = DisplayRGBDActivity.INSTANCE.getRgbdSensor()) != null) {
                rgbdSensor.updateCenterRGBDType(type);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(ConfigUtil.CONFIG_DIR + ConfigUtil.RGBD_CONFIG_FILE_NAME));
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
            DisplayRGBDActivity.access$executeCommand(this.this$0, InvokeServiceData.CALL_TYPE_SYNC);
            Toast.makeText(this.this$0, "保存配置成功", 0).show();
            str2 = this.this$0.TAG;
            Pdlog.m3275i(str2, "save rgbd config as " + gson.toJson(rGBDConfig));
            ConfigUtil.INSTANCE.backupFile(ConfigUtil.CONFIG_DIR, ConfigUtil.CONFIG_BACKUP_DIR, ConfigUtil.RGBD_CONFIG_FILE_NAME);
            DisplayRGBDActivity.access$executeCommand(this.this$0, InvokeServiceData.CALL_TYPE_SYNC);
            this.this$0.stopPreview();
            RGBDSensor rgbdSensor2 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
            if (rgbdSensor2 != null) {
                rgbdSensor2.RefreshConfigure();
            }
            hashMap9 = this.this$0.viewMap;
            hashMap9.put(Integer.valueOf(C5657R.id.leftView), (ImageView) this.this$0._$_findCachedViewById(C5657R.id.leftView));
            hashMap10 = this.this$0.viewMap;
            hashMap10.put(Integer.valueOf(C5657R.id.rightView), (ImageView) this.this$0._$_findCachedViewById(C5657R.id.rightView));
            hashMap11 = this.this$0.viewMap;
            hashMap11.put(Integer.valueOf(C5657R.id.centerView), (ImageView) this.this$0._$_findCachedViewById(C5657R.id.centerView));
            hashMap12 = this.this$0.viewMap;
            hashMap12.put(Integer.valueOf(C5657R.id.downView), (ImageView) this.this$0._$_findCachedViewById(C5657R.id.downView));
            hashMap13 = this.this$0.viewIdMap;
            hashMap13.put(Integer.valueOf(C5657R.id.leftView), Integer.valueOf(C5657R.id.leftView));
            hashMap14 = this.this$0.viewIdMap;
            hashMap14.put(Integer.valueOf(C5657R.id.rightView), Integer.valueOf(C5657R.id.rightView));
            hashMap15 = this.this$0.viewIdMap;
            hashMap15.put(Integer.valueOf(C5657R.id.centerView), Integer.valueOf(C5657R.id.centerView));
            hashMap16 = this.this$0.viewIdMap;
            hashMap16.put(Integer.valueOf(C5657R.id.downView), Integer.valueOf(C5657R.id.downView));
            this.this$0.clickImage = -1;
            this.this$0.startDepthPreview();
            if (new File(MapFilePathConfig.PRE_PARAM_PATH).exists()) {
                return;
            }
            ((Button) this.this$0._$_findCachedViewById(C5657R.id.button_checkTool)).callOnClick();
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3274e(str, "read rgbd config file exception " + e.getLocalizedMessage());
        }
    }
}
