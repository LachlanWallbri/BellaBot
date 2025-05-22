package com.pudutech.rgbdlib.activity;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.rgbdlib.C5657R;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.rgbdlib.RGBDType;
import com.pudutech.rgbdlib.config.DataJson;
import com.pudutech.rgbdlib.config.ExtrinsicsJson;
import com.pudutech.rgbdlib.config.RGBDConfig;
import com.pudutech.rgbdlib.config.RGBDJson;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: SetRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R,\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R,\u0010\f\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\r\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u000e\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/SetRGBDActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "centerRGBDSubscription", "Lkotlin/Function4;", "Landroid/os/ParcelFileDescriptor;", "", "", "checkConfig", "", "configFileName", "", "downRGBDSubscription", "leftRGBDSubscription", "rightRGBDSubscription", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SetRGBDActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static RGBDSensor rgbdSensor;
    private HashMap _$_findViewCache;
    private final boolean checkConfig;
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> leftRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$leftRGBDSubscription$1
        public final void invoke(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
            invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
            return Unit.INSTANCE;
        }
    };
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> rightRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$rightRGBDSubscription$1
        public final void invoke(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
            invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
            return Unit.INSTANCE;
        }
    };
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> centerRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$centerRGBDSubscription$1
        public final void invoke(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
            invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
            return Unit.INSTANCE;
        }
    };
    private final Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit> downRGBDSubscription = new Function4<ParcelFileDescriptor, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$downRGBDSubscription$1
        public final void invoke(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3) {
            invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue());
            return Unit.INSTANCE;
        }
    };
    private final String configFileName = "/sdcard/pudu/config/rgbd.json";

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

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* compiled from: SetRGBDActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/SetRGBDActivity$Companion;", "", "()V", "rgbdSensor", "Lcom/pudutech/rgbdlib/RGBDSensor;", "getRgbdSensor", "()Lcom/pudutech/rgbdlib/RGBDSensor;", "setRgbdSensor", "(Lcom/pudutech/rgbdlib/RGBDSensor;)V", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RGBDSensor getRgbdSensor() {
            return SetRGBDActivity.rgbdSensor;
        }

        public final void setRgbdSensor(RGBDSensor rGBDSensor) {
            SetRGBDActivity.rgbdSensor = rGBDSensor;
        }
    }

    public SetRGBDActivity() {
        boolean z;
        if (new File(this.configFileName).exists()) {
            z = false;
        } else {
            File file = new File(ConfigUtil.CONFIG_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            z = true;
        }
        this.checkConfig = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5657R.layout.activity_set_rgbd);
        SetRGBDActivity setRGBDActivity = this;
        Button button = new Button(setRGBDActivity);
        button.setText("欢乐送2RGBD");
        button.setTextSize(100.0f);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$onCreate$$inlined$apply$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
                String str;
                z = SetRGBDActivity.this.checkConfig;
                if (z) {
                    Gson gson = new Gson();
                    RGBDConfig rGBDConfig = new RGBDConfig(null, null, null, null, null, true);
                    RGBDJson rGBDJson = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.18105f), Float.valueOf(0.08143f), Float.valueOf(0.23012f), Float.valueOf(0.61f), Float.valueOf(-0.61f), Float.valueOf(0.0f)));
                    RGBDJson rGBDJson2 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.18105f), Float.valueOf(-0.08143f), Float.valueOf(0.23012f), Float.valueOf(-0.61f), Float.valueOf(-0.61f), Float.valueOf(3.14159f)));
                    DataJson dataJson = new DataJson("/sdcard/pudu/costmap_data", 0);
                    rGBDConfig.setLeft_rgbd(rGBDJson);
                    rGBDConfig.setRight_rgbd(rGBDJson2);
                    rGBDConfig.setData(dataJson);
                    try {
                        str = SetRGBDActivity.this.configFileName;
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
                    SetRGBDActivity.INSTANCE.setRgbdSensor(new RGBDSensor(SetRGBDActivity.this));
                }
                RGBDSensor rgbdSensor2 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor2 != null) {
                    MachineInfo.RGBDType rGBDType = MachineInfo.RGBDType.TwoDevice;
                    int id = MachineModel.Hls.getId();
                    function4 = SetRGBDActivity.this.leftRGBDSubscription;
                    function42 = SetRGBDActivity.this.rightRGBDSubscription;
                    function43 = SetRGBDActivity.this.centerRGBDSubscription;
                    function44 = SetRGBDActivity.this.downRGBDSubscription;
                    rgbdSensor2.start(rGBDType, id, function4, function42, function43, function44);
                }
                RGBDSensor rgbdSensor3 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor3 != null) {
                    SetRGBDActivity setRGBDActivity2 = SetRGBDActivity.this;
                    RGBDSensor rgbdSensor4 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor4 == null) {
                        Intrinsics.throwNpe();
                    }
                    rgbdSensor3.startPreviewActivity(setRGBDActivity2, rgbdSensor4);
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C5657R.id.layoutSet)).addView(button);
        Button button2 = new Button(setRGBDActivity);
        button2.setText("贝拉3RGBD");
        button2.setTextSize(100.0f);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$onCreate$$inlined$apply$lambda$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
                String str;
                z = SetRGBDActivity.this.checkConfig;
                if (z) {
                    Gson gson = new Gson();
                    RGBDConfig rGBDConfig = new RGBDConfig(null, null, null, null, null, true);
                    RGBDJson rGBDJson = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.20661f), Float.valueOf(0.0797f), Float.valueOf(0.12835f), Float.valueOf(0.61f), Float.valueOf(-0.61f), Float.valueOf(0.0f)));
                    RGBDJson rGBDJson2 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.20661f), Float.valueOf(-0.0797f), Float.valueOf(0.12835f), Float.valueOf(-0.61f), Float.valueOf(-0.61f), Float.valueOf(3.14159f)));
                    RGBDJson rGBDJson3 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, null);
                    DataJson dataJson = new DataJson("/sdcard/pudu/costmap_data", 0);
                    rGBDConfig.setLeft_rgbd(rGBDJson);
                    rGBDConfig.setRight_rgbd(rGBDJson2);
                    rGBDConfig.setCenter_rgbd(rGBDJson3);
                    rGBDConfig.setData(dataJson);
                    try {
                        str = SetRGBDActivity.this.configFileName;
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
                    SetRGBDActivity.INSTANCE.setRgbdSensor(new RGBDSensor(SetRGBDActivity.this));
                }
                RGBDSensor rgbdSensor2 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor2 != null) {
                    MachineInfo.RGBDType rGBDType = MachineInfo.RGBDType.TwoDevice;
                    int id = MachineModel.BellaBot.getId();
                    function4 = SetRGBDActivity.this.leftRGBDSubscription;
                    function42 = SetRGBDActivity.this.rightRGBDSubscription;
                    function43 = SetRGBDActivity.this.centerRGBDSubscription;
                    function44 = SetRGBDActivity.this.downRGBDSubscription;
                    rgbdSensor2.start(rGBDType, id, function4, function42, function43, function44);
                }
                RGBDSensor rgbdSensor3 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor3 != null) {
                    SetRGBDActivity setRGBDActivity2 = SetRGBDActivity.this;
                    RGBDSensor rgbdSensor4 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor4 == null) {
                        Intrinsics.throwNpe();
                    }
                    rgbdSensor3.startPreviewActivity(setRGBDActivity2, rgbdSensor4);
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C5657R.id.layoutSet)).addView(button2);
        Button button3 = new Button(setRGBDActivity);
        button3.setText("好啦3RGBD");
        button3.setTextSize(100.0f);
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$onCreate$$inlined$apply$lambda$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
                String str;
                z = SetRGBDActivity.this.checkConfig;
                if (z) {
                    Gson gson = new Gson();
                    RGBDConfig rGBDConfig = new RGBDConfig(null, null, null, null, null, true);
                    RGBDJson rGBDJson = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.20661f), Float.valueOf(0.0797f), Float.valueOf(0.12835f), Float.valueOf(0.61f), Float.valueOf(-0.61f), Float.valueOf(0.0f)));
                    RGBDJson rGBDJson2 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.20661f), Float.valueOf(-0.0797f), Float.valueOf(0.12835f), Float.valueOf(-0.61f), Float.valueOf(-0.61f), Float.valueOf(3.14159f)));
                    RGBDJson rGBDJson3 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, null);
                    DataJson dataJson = new DataJson("/sdcard/pudu/costmap_data", 0);
                    rGBDConfig.setLeft_rgbd(rGBDJson);
                    rGBDConfig.setRight_rgbd(rGBDJson2);
                    rGBDConfig.setCenter_rgbd(rGBDJson3);
                    rGBDConfig.setData(dataJson);
                    try {
                        str = SetRGBDActivity.this.configFileName;
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
                    SetRGBDActivity.INSTANCE.setRgbdSensor(new RGBDSensor(SetRGBDActivity.this));
                }
                RGBDSensor rgbdSensor2 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor2 != null) {
                    MachineInfo.RGBDType rGBDType = MachineInfo.RGBDType.TwoDevice;
                    int id = MachineModel.RecycleDog.getId();
                    function4 = SetRGBDActivity.this.leftRGBDSubscription;
                    function42 = SetRGBDActivity.this.rightRGBDSubscription;
                    function43 = SetRGBDActivity.this.centerRGBDSubscription;
                    function44 = SetRGBDActivity.this.downRGBDSubscription;
                    rgbdSensor2.start(rGBDType, id, function4, function42, function43, function44);
                }
                RGBDSensor rgbdSensor3 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor3 != null) {
                    SetRGBDActivity setRGBDActivity2 = SetRGBDActivity.this;
                    RGBDSensor rgbdSensor4 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor4 == null) {
                        Intrinsics.throwNpe();
                    }
                    rgbdSensor3.startPreviewActivity(setRGBDActivity2, rgbdSensor4);
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C5657R.id.layoutSet)).addView(button3);
        Button button4 = new Button(setRGBDActivity);
        button4.setText("葫芦2RGBD");
        button4.setTextSize(100.0f);
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$onCreate$$inlined$apply$lambda$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
                String str;
                z = SetRGBDActivity.this.checkConfig;
                if (z) {
                    Gson gson = new Gson();
                    RGBDConfig rGBDConfig = new RGBDConfig(null, null, null, null, null, true);
                    RGBDJson rGBDJson = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.23f), Float.valueOf(0.0f), Float.valueOf(0.12f), Float.valueOf(0.0f), Float.valueOf(5.48f), Float.valueOf(0.0f)));
                    RGBDJson rGBDJson2 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, new ExtrinsicsJson(Float.valueOf(0.23f), Float.valueOf(0.0f), Float.valueOf(0.904f), Float.valueOf(0.0f), Float.valueOf(0.925f), Float.valueOf(0.0f)));
                    DataJson dataJson = new DataJson("/sdcard/pudu/costmap_data", 0);
                    rGBDConfig.setCenter_rgbd(rGBDJson);
                    rGBDConfig.setDown_rgbd(rGBDJson2);
                    rGBDConfig.setData(dataJson);
                    try {
                        str = SetRGBDActivity.this.configFileName;
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
                    SetRGBDActivity.INSTANCE.setRgbdSensor(new RGBDSensor(SetRGBDActivity.this));
                }
                RGBDSensor rgbdSensor2 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor2 != null) {
                    MachineInfo.RGBDType rGBDType = MachineInfo.RGBDType.TwoDevice;
                    int id = MachineModel.Peanut.getId();
                    function4 = SetRGBDActivity.this.leftRGBDSubscription;
                    function42 = SetRGBDActivity.this.rightRGBDSubscription;
                    function43 = SetRGBDActivity.this.centerRGBDSubscription;
                    function44 = SetRGBDActivity.this.downRGBDSubscription;
                    rgbdSensor2.start(rGBDType, id, function4, function42, function43, function44);
                }
                RGBDSensor rgbdSensor3 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor3 != null) {
                    SetRGBDActivity setRGBDActivity2 = SetRGBDActivity.this;
                    RGBDSensor rgbdSensor4 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor4 == null) {
                        Intrinsics.throwNpe();
                    }
                    rgbdSensor3.startPreviewActivity(setRGBDActivity2, rgbdSensor4);
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C5657R.id.layoutSet)).addView(button4);
        Button button5 = new Button(setRGBDActivity);
        button5.setText("火狐1RGBD");
        button5.setTextSize(100.0f);
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.SetRGBDActivity$onCreate$$inlined$apply$lambda$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
                Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
                String str;
                z = SetRGBDActivity.this.checkConfig;
                if (z) {
                    Gson gson = new Gson();
                    RGBDConfig rGBDConfig = new RGBDConfig(null, null, null, null, null, false);
                    RGBDJson rGBDJson = new RGBDJson(RGBDType.RealSense.getStr(), "origin", false, null, null);
                    DataJson dataJson = new DataJson("/sdcard/pudu/costmap_data", 0);
                    rGBDConfig.setCenter_rgbd(rGBDJson);
                    rGBDConfig.setData(dataJson);
                    try {
                        str = SetRGBDActivity.this.configFileName;
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
                    SetRGBDActivity.INSTANCE.setRgbdSensor(new RGBDSensor(SetRGBDActivity.this));
                }
                RGBDSensor rgbdSensor2 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor2 != null) {
                    MachineInfo.RGBDType rGBDType = MachineInfo.RGBDType.TwoDevice;
                    int id = MachineModel.Firefox.getId();
                    function4 = SetRGBDActivity.this.leftRGBDSubscription;
                    function42 = SetRGBDActivity.this.rightRGBDSubscription;
                    function43 = SetRGBDActivity.this.centerRGBDSubscription;
                    function44 = SetRGBDActivity.this.downRGBDSubscription;
                    rgbdSensor2.start(rGBDType, id, function4, function42, function43, function44);
                }
                RGBDSensor rgbdSensor3 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor3 != null) {
                    SetRGBDActivity setRGBDActivity2 = SetRGBDActivity.this;
                    RGBDSensor rgbdSensor4 = SetRGBDActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor4 == null) {
                        Intrinsics.throwNpe();
                    }
                    rgbdSensor3.startPreviewActivity(setRGBDActivity2, rgbdSensor4);
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C5657R.id.layoutSet)).addView(button5);
    }
}
